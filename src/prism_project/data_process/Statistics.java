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
	
	
	public double get_back_transformed_number(double random_draw, String transform_function, double parameter_a, double parameter_b ) {
		// use the function here to back transform, the result is adjusted if < 0 (percentage always >= 0)
		double back_transform = random_draw;	// NOTE NOTE NOTE NOTE NOTE we need to change this 
		
		
		if (back_transform < 0) back_transform = 0;		// = 0 if the back transform < 0
		return back_transform;
	}
}
	
	
	
