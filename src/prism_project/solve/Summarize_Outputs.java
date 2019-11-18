/*******************************************************************************
 * Copyright (C) 2016-2018 PRISM Development Team
 * 
 * PRISM is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * PRISM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with PRISM.  If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package prism_project.solve;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Summarize_Outputs {
	File runFolder;
	int total_iterations;
	File[] output_general_outputs_file;
	File[] output_variables_file;
	File[] output_constraints_file;
	File[] output_management_overview_file;
	File[] output_management_details_file;
	File[] output_fly_constraints_file ;
	File[] output_basic_constraints_file;
	File[] output_flow_constraints_file;
	
	public Summarize_Outputs(File runFolder, int total_iterations) {
		this.runFolder = runFolder;
		this.total_iterations = total_iterations;
		
		output_general_outputs_file = new File[total_iterations + 1];
		output_variables_file = new File[total_iterations + 1];
		output_constraints_file = new File[total_iterations + 1];
		output_management_overview_file = new File[total_iterations + 1];
		output_management_details_file = new File[total_iterations + 1];
		output_fly_constraints_file = new File[total_iterations + 1];
		output_basic_constraints_file = new File[total_iterations + 1];
		output_flow_constraints_file = new File[total_iterations + 1];
		for (int iter = 0; iter <= total_iterations; iter++) {	// Loop all iterations
			// all outputs				
			output_general_outputs_file[iter] = new File(runFolder.getAbsolutePath() + "/output_01_general_outputs_" + iter + ".txt");
//			output_variables_file[iter] = new File(runFolder.getAbsolutePath() + "/output_02_variables_" + iter + ".txt");
//			output_constraints_file[iter] = new File(runFolder.getAbsolutePath() + "/output_03_constraints_" + iter + ".txt");	
//			output_management_overview_file[iter] = new File(runFolder.getAbsolutePath() + "/output_04_management_overview_" + iter + ".txt");
//			output_management_details_file[iter] = new File(runFolder.getAbsolutePath() + "/output_05_management_details_" + iter + ".txt");	
//			output_fly_constraints_file[iter] = new File(runFolder.getAbsolutePath() + "/output_05_fly_constraints_" + iter + ".txt");	
			output_basic_constraints_file[iter] = new File(runFolder.getAbsolutePath() + "/output_06_basic_constraints_" + iter + ".txt");
//			output_flow_constraints_file[iter] = new File(runFolder.getAbsolutePath() + "/output_07_flow_constraints_" + iter + ".txt");
		}
		
		summarize_output_01();
		summarize_output_06();
	}
	
	private void summarize_output_01() {
		int total_row = get_data_from_output(output_general_outputs_file[0]).length;
		int total_col = total_iterations + 2;	 // description iter0 iter1 ... iterM        (total iterations = M)
		String[] summarize_column_name = new String[total_col];
		// column name
		summarize_column_name[0] = "description";
		for (int iter = 0; iter <= total_iterations; iter++) {	// Loop all iterations
			summarize_column_name[iter + 1] = "iteration " + iter;
		}
		// data
		String[][] summarize_data = new String[total_row][total_col];
		for (int iter = 0; iter <= total_iterations; iter++) {	// Loop all iterations
			int col = iter + 1;
			String[][] data = get_data_from_output(output_general_outputs_file[iter]);
			for (int row = 0; row < total_row; row++) {
				if (iter == 0) {
					summarize_data[row][0] = data[row][0];
				}
				summarize_data[row][col] = data[row][1];
			}
		}
		// file
		File summarize_output_general_outputs_file = new File(runFolder.getAbsolutePath() + "/summarize_output_01_general_outputs.txt");	
		create_file(summarize_output_general_outputs_file, summarize_data, summarize_column_name);
	}
	
	private void summarize_output_06() {
		int total_row = get_data_from_output(output_basic_constraints_file[0]).length;
		int total_col = 2 * (total_iterations + 1) + 4;	 // bc_id ... var_name iter0 iter1 ... iterM        (total iterations = M)
		String[] summarize_column_name = new String[total_col];
		// column name
		summarize_column_name[0] = "bc_id";
		summarize_column_name[1] = "bc_description";
		summarize_column_name[2] = "var_id";
		summarize_column_name[3] = "var_name";
		for (int iter = 0; iter <= total_iterations; iter++) {	// Loop all iterations
			summarize_column_name[iter + 4] = "var_value_iteration_" + iter;
			summarize_column_name[iter + 4 + total_iterations + 1] = "penalty_iteration_" + iter;
		}
		// data
		String[][] summarize_data = new String[total_row][total_col];
		for (int iter = 0; iter <= total_iterations; iter++) {	// Loop all iterations
			String[][] data = get_data_from_output(output_basic_constraints_file[iter]);
			for (int row = 0; row < total_row; row++) {
				if (iter == 0) {
					summarize_data[row][0] = data[row][0];
					summarize_data[row][1] = data[row][1];
					summarize_data[row][2] = data[row][8];
					summarize_data[row][3] = data[row][9];
				}
				summarize_data[row][iter + 4] = data[row][10];
				summarize_data[row][iter + 4 + total_iterations + 1] = data[row][12];
			}
		}
		// file
		File summarize_output_general_outputs_file = new File(runFolder.getAbsolutePath() + "/summarize_output_06_basic_constraints.txt");	
		create_file(summarize_output_general_outputs_file, summarize_data, summarize_column_name);
	}
	
	private String[][] get_data_from_output(File file) {
		String[][] data = null;
		String delimited = "\t";		// tab delimited
		try {		
			// All lines to be in array
			List<String> list;
			list = Files.readAllLines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8);
			list.remove(0);	// Remove the first row (Column names)
			String[] a = list.toArray(new String[list.size()]);
								
			int total_rows = a.length;
			int total_columns = a[0].split(delimited).length;				
			data = new String[total_rows][total_columns];
		
			// read all values from all rows and columns
			for (int i = 0; i < total_rows; i++) {
				String[] row_value = a[i].split(delimited);
				for (int j = 0; j < total_columns; j++) {
					data[i][j] = row_value[j];
				}
			}
		} catch (IOException e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return data;
	}
	
	private void create_file(File file, Object[][] data, String[] column_name) {
		// Delete the old file before writing new contents
		if (file.exists()) {
			file.delete();
		}
		
		if (data != null && data.length > 0) {
			try (BufferedWriter fileOut = new BufferedWriter(new FileWriter(file))) {
				for (int j = 0; j < column_name.length; j++) {
					fileOut.write(column_name[j] + "\t");
				}
				
				for (int i = 0; i < data.length; i++) {
					fileOut.newLine();
					for (int j = 0; j < data[i].length; j++) {
						fileOut.write(data[i][j] + "\t");
					}		
				}
				fileOut.close();
			} catch (IOException e) {
				System.err.println(e.getClass().getName() + ": " + e.getMessage());
			} 
		}
	}
}
