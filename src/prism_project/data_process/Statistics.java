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
	
	
	public double[] get_user_loss_rates_from_transformed_data(int total_disturbances, String[] modelling_approach, String[] transform_function, double[] parameter_a, double[] parameter_b, double[] mean, double[] std, double[] back_transformed_global_adjustment) {
		double[] transformed_loss_rate = new double[total_disturbances];
		for (int k = 0; k < total_disturbances; k++) {
			if (modelling_approach[k].equals("Deterministic")) {
				transformed_loss_rate[k] = mean[k];	// no need random draw: transformed_loss_rate[] = mean[]
			} else {	// stochastic
				transformed_loss_rate[k] = get_gaussian_random_number(mean[k], std[k]);	// random draw: transformed_loss_rate[] = random[] of the mean[] and std[]
			}
		}
		return get_back_transform_and_adjustment(total_disturbances, transformed_loss_rate, transform_function, parameter_a, parameter_b, back_transformed_global_adjustment);
	}
	
	
//	public double[] get_deterministic_loss_rates_from_transformed_data(int total_disturbances, String[] transform_function, double[] parameter_a, double[] parameter_b, double[] mean, double[] std) {
//		// no need random draw: transformed_loss_rate[] = mean[]
//		return get_back_transform_and_adjustment(total_disturbances, mean, transform_function, parameter_a, parameter_b);
//	}
	
	
	public double[] get_stochastic_loss_rates_from_transformed_data(int total_disturbances, String[] transform_function, double[] parameter_a, double[] parameter_b, double[] mean, double[] std, double[] back_transformed_global_adjustment) {
		// random draw then return the back transformed and adjusted data:  transformed_loss_rate[] = random[] of the mean[] and std[]
		double[] random_draw_loss_rate = new double[total_disturbances];
		for (int k = 0; k < total_disturbances; k++) {
			random_draw_loss_rate[k] = get_gaussian_random_number(mean[k], std[k]);	
		}
		return get_back_transform_and_adjustment(total_disturbances, random_draw_loss_rate, transform_function, parameter_a, parameter_b, back_transformed_global_adjustment);
	}
	
	
	public double[] get_back_transform_and_adjustment(int total_disturbances, double[] transformed_loss_rate, String[] transform_function, double[] parameter_a, double[] parameter_b, double[] back_transformed_global_adjustment) {
		// back transform
		double[] global_adjusted_back_transformed_loss_rate = new double[total_disturbances];
		double total_pecentage = 0;
		for (int k = 0; k < total_disturbances; k++) {
			global_adjusted_back_transformed_loss_rate[k] = back_transformed_global_adjustment[k] / 100 * get_back_transformed_number(transformed_loss_rate[k], transform_function[k], parameter_a[k], parameter_b[k]);	
			total_pecentage = total_pecentage + global_adjusted_back_transformed_loss_rate[k];
		}
		if (total_pecentage <= 100) return global_adjusted_back_transformed_loss_rate;
		
		/* adjustment (the case when total_pecentage > 100): 
		 Example:	 In the area specified by this variable we have 3 stand replacing disturbances with below global_adjusted_back_transformed_loss_rate
		 SR1 = 80%
		 SR2 = 50%
		 SR3 = 70%
		
		 1. We need to calculate proportional_loss_rate (plr)
		 plr of SR1 = 80 * 100 / (80 + 70 + 50) = 40
		 plr of SR2 = 50 * 100 / (80 + 70 + 50) = 25
		 plr of SR3 = 70 * 100 / (80 + 70 + 50) = 35
		 
		 2.Then calculate the adjusted total percentage (atp) by using a loop
		 atp of loop 1 = 0 + 80 * (100 - 0) / 100 = 80
		 atp of loop 2 = 80 + 50 * (100 - 80) / 100 = 90
		 atp of loop 3 = 90 + 70 * (100 - 90) / 100 = 97	 -->  atp = 97 is the adjusted total percentage 
		 
		 3. Finally, calculate the adjusted loss rate (alr) as final result to apply
		 alr of SR1 = 40 * 97 / 100
		 alr of SR2 = 25 * 97 / 100
		 alr of SR3 = 35 * 97 / 100
		 */		
		double[] proportional_loss_rate = new double[total_disturbances];
		// 1. plr
		for (int k = 0; k < total_disturbances; k++) {
			proportional_loss_rate[k] = global_adjusted_back_transformed_loss_rate[k] * 100 / total_pecentage;
		}
		// 2. atp
		double adjusted_total_percentage = 0;
		for (int k = 0; k < total_disturbances; k++) {
			adjusted_total_percentage = adjusted_total_percentage + global_adjusted_back_transformed_loss_rate[k] * (100 - adjusted_total_percentage) / 100;
		}
		// 3. alr
		double[] adjusted_loss_rate = new double[total_disturbances];
		for (int k = 0; k < total_disturbances; k++) {
			adjusted_loss_rate[k] = proportional_loss_rate[k] * adjusted_total_percentage / 100;
		}
		return adjusted_loss_rate;
	}
	
	
	public double get_gaussian_random_number(double mean, double std) {
		// draw a random number based on the mean and std under the assumption of normal distribution	
		Random ran = new Random();
		double random_draw = mean + std * ran.nextGaussian();
		return random_draw;	
	}
	
	
	public double get_back_transformed_number(double transformed_number, String transform_function, double parameter_a, double parameter_b) {
		double f_x = transformed_number;
		double a = parameter_a;
		double b = parameter_b;
		double back_transform = 0;	// this value is not important, just make it a random 0 here
		
		// use the function here to back transform, the result is adjusted if <0
		switch (transform_function) {
		case "null":
			back_transform = transformed_number;		// no transformation
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
		 f(x,a,b) = (x+a)^b where x+a>0 if b is not integral
		 x = f(x)^(1/b) - a
		 */
		return Math.pow(f_x, 1 / b) - a;
	}
	
	public double back_transform_Arcsine(double f_x, double a, double b) {
		/* 
		 f(x) = asin(x/100)
		 x = sin(f(x)) * 100
		 */
		return Math.sin(f_x) * 100;	// note that arcsin <> arcsinh
	}
	
	public double back_transform_Box_Cox(double f_x, double a, double b) {
		/* 
		 f(x,a,b) = ((x+a)^b - 1)/b where b<>0, x+a>0 if b is not integral  
		 f(x,a,b) = log(x+a) where b=0, x+a>0
		 x + a = e^f(x)					if b=0
		 x + a = (b*f(x) + 1)^(1/b)		otherwise
		 */
		if (b == 0) {
			return Math.exp(f_x) - a;
		} else {
			return Math.pow(b * f_x + 1, 1 / b) - a;
		}
	}
	
	

	
	
	// FOR USING IN NATURAL DISTURBANCES SCREEN
	// FOR USING IN NATURAL DISTURBANCES SCREEN
	// FOR USING IN NATURAL DISTURBANCES SCREEN
	public double[] get_transformed_loss_rates(double[] loss_rate, String transform_function, double parameter_a, double parameter_b) {
		double[] x = loss_rate;
		double a = parameter_a;
		double b = parameter_b;
		int count = x.length;
		double[] f_x = new double[count];
		for (int i = 0; i < count; i++) {
			switch (transform_function) {
			case "null":
				f_x[i] = loss_rate[i];		// no transformation
				break;
			case "Inverse":
				f_x[i] = transform_Inverse(x[i], a, b);
				break;
			case "Logarithmic":
				f_x[i] = transform_Logarithmic(x[i], a, b);
				break;
			case "Logarithmic 10":
				f_x[i] = transform_Logarithmic_10(x[i], a, b);
				break;
			case "Square Root":
				f_x[i] = transform_Square_Root(x[i], a, b);
				break;
			case "Exponential":
				f_x[i] = transform_Exponential(x[i], a, b);
				break;
			case "Power":
				f_x[i] = transform_Power(x[i], a, b);
				break;
			case "Arcsine":
				f_x[i] = transform_Arcsine(x[i], a, b);
				break;
			case "Box Cox":
				f_x[i] = transform_Box_Cox(x[i], a, b);
				break;
			}
		}
		return f_x;
	}
	
	public double transform_Inverse(double x, double a, double b) {
		// f(x) = 1/(x+a)
		return 1 / (x + a);
	}
	
	public double transform_Logarithmic(double x, double a, double b) {
		// f(x,a) = log(x+a) where x+a>0 
		return Math.log(x + a);
	}
	
	public double transform_Logarithmic_10(double x, double a, double b) {
		// f(x,a) = log10(x+a) where x+a>0
		return Math.log10(x + a);
	}
	
	public double transform_Square_Root(double x, double a, double b) {
		// f(x,a) = sqrt(x+a) where x+a>0
		return Math.sqrt(x + a);
	}
	
	public double transform_Exponential(double x, double a, double b) {
		// f(x) = e^x
		return Math.exp(x);
	}
	
	public double transform_Power(double x, double a, double b) {
		// f(x,a,b) = (x+a)^b where x+a>0 if b is not integral
		return Math.pow(x + a, b);
	}
	
	public double transform_Arcsine(double x, double a, double b) {
		// f(x) = asin(x/100)
		return Math.asin(x / 100) * 100;	// note that arcsin <> arcsinh
	}
	
	public double transform_Box_Cox(double x, double a, double b) {
		// f(x,a,b) = ((x+a)^b - 1)/b where b<>0, x+a>0 if b is not integral  
		// f(x,a,b) = log(x+a) where b=0, x+a>0
		if (b == 0) {
			return Math.log(x + a);
		} else {
			return (Math.pow(x + a, b) - 1) / b;
		}
	}
}