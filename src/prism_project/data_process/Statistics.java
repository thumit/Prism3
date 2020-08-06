/*
 * Copyright (C) 2016-2020 PRISM Development Team
 * 
 * PRISM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * PRISM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with PRISM. If not, see <http://www.gnu.org/licenses/>.
 */

package prism_project.data_process;

import java.util.Random;

public class Statistics {
	public Statistics() {
	}
	
	
	public double[] get_deterministic_loss_rates_from_transformed_data(int total_disturbances, double[] mean, double[] std, String[] transform_function, double[] parameter_a, double[] parameter_b) {
		// no need random draw: transformed_loss_rate[] = mean[]
		return get_back_transform_and_adjustment(total_disturbances, mean, transform_function, parameter_a, parameter_b);
	}
	
	
	public double[] get_stochastic_loss_rates_from_transformed_data(int total_disturbances, double[] mean, double[] std, String[] transform_function, double[] parameter_a, double[] parameter_b) {
		// random draw then return the back transformed and adjusted data:  transformed_loss_rate[] = random[] of the mean[] and std[]
		double[] random_draw_loss_rate = new double[total_disturbances];
		for (int k = 0; k < total_disturbances; k++) {
			random_draw_loss_rate[k] = get_gaussian_random_number(mean[k], std[k]);	
		}
		return get_back_transform_and_adjustment(total_disturbances, random_draw_loss_rate, transform_function, parameter_a, parameter_b);
	}
	
	
	public double[] get_back_transform_and_adjustment(int total_disturbances, double[] transformed_loss_rate, String[] transform_function, double[] parameter_a, double[] parameter_b) {
		double[] back_transformed_loss_rate = new double[total_disturbances];
		double[] adjusted_loss_rate = new double[total_disturbances];
		double total_pecentage = 0;
		
		// back transform
		for (int k = 0; k < total_disturbances; k++) {
			back_transformed_loss_rate[k] = get_back_transformed_number(transformed_loss_rate[k], transform_function[k], parameter_a[k], parameter_b[k]);	
			total_pecentage = total_pecentage + back_transformed_loss_rate[k];
		}
		
		// adjustment when necessary. adjustment is based on Eric's suggestion
		if (total_pecentage > 100) {
			for (int k = 0; k < total_disturbances; k++) {
				adjusted_loss_rate[k] = adjusted_loss_rate[k] * 100 / total_pecentage;
			}
			return adjusted_loss_rate;
		} else {
			return back_transformed_loss_rate;
		}
	}
	
	
	public double get_gaussian_random_number(double mean, double std) {
		// draw a random number based on the mean and std under the assumption of normal distribution	
		Random ran = new Random();
		double random_draw = mean + std * ran.nextGaussian();
		return random_draw;	
	}
	
	
	public double get_back_transformed_number(double transformed_loss_rate, String transform_function, double parameter_a, double parameter_b) {
		double f_x = transformed_loss_rate;
		double a = parameter_a;
		double b = parameter_b;
		double back_transform = 0;	// this value is not important, just make it a random 0 here
		
		// use the function here to back transform, the result is adjusted if <0 or >100
		switch (transform_function) {
		case "null":
			back_transform = transformed_loss_rate;		// no transformation
			break;
		case "Inverse":
			back_transform = back_transform_Inverse(f_x, a, b);
			break;
		case "Logarithmic":
			back_transform = back_transform_Logarithmic(f_x, a, b);
			break;
		case "Logarithmic 10":
			back_transform = back_transform_Logarithmic_10(f_x, a, b);
			break;
		case "Square Root":
			back_transform = back_transform_Square_Root(f_x, a, b);
			break;
		case "Exponential":
			back_transform = back_transform_Exponential(f_x, a, b);
			break;
		case "Power":
			back_transform = back_transform_Power(f_x, a, b);
			break;
		case "Arcsine":
			back_transform = back_transform_Arcsine(f_x, a, b);
			break;
		case "Box Cox":
			back_transform = back_transform_Box_Cox(f_x, a, b);
			break;
		}
		if (back_transform < 0) back_transform = 0;			// = 0 if the back transform < 0
		if (back_transform > 100) back_transform = 100;		// = 100 if the back transform > 100
		return back_transform;
	}
	
	
	public double back_transform_Inverse(double f_x, double a, double b) {
		/* 
		 f(x) = 1/(x+a)
		 x = 1/f(x)		if f(x) <> 0
		 x = infinity 	if f(x) = 0
		 */
		if (f_x != 0) return 1 / f_x;
		else return Double.MAX_VALUE;
	}
	
	
	public double back_transform_Logarithmic(double f_x, double a, double b) {
		/* 
		 f(x,a) = log(x+a) where x+a>0
		 x = e^f(x) - a
		 */
		return Math.exp(f_x) - a;
	}
	
	
	public double back_transform_Logarithmic_10(double f_x, double a, double b) {
		/* 
		 f(x,a) = log10(x+a) where x+a>0
		 x = 10^f(x) - a
		 */
		return Math.pow(10, f_x) - a;
	}
	
	
	public double back_transform_Square_Root(double f_x, double a, double b) {
		/* 
		 f(x,a) = sqrt(x+a) where x+a>0
		 x = f(x)^2 - a
		 */
		return Math.pow(f_x, 2) - a;
	}
	
	
	public double back_transform_Exponential(double f_x, double a, double b) {
		/* 
		 f(x) = e^x
		 x = log(f(x))
		 */
		return Math.log(f_x);
	}
	
	
	public double back_transform_Power(double f_x, double a, double b) {
		/* 
		 f(x) = 10^x
		 x = log10(f(x))
		 */
		return Math.log10(f_x);
	}
	
	
	public double back_transform_Arcsine(double f_x, double a, double b) {
		/* 
		 f(x) = log(x+sqrt(x^2 + 1))
		 x = 
		 */
		return -999999;		// Review this. note that arcsin <> arcsinh
	}
	
	
	public double back_transform_Box_Cox(double f_x, double a, double b) {
		/* 
		 f(x,a,b) = ((x+a)^b - 1)/b where b<>0   AND   f(x,a,b) = log(x+a) where b=0	(x+a>0 for both)
		 x + a = e^f(x)					if b=0
		 x + a = (b*f(x) + 1)^(1/b)		otherwise
		 */
		if (b == 0) return Math.exp(f_x) - a;
		else return Math.pow(b * f_x + 1, 1 / b) - a;
	}
}
	
	
	
