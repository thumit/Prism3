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
package prism_convenience;

import java.io.IOException;

public class Processing {
	
	
	public Processing() {

	}

	
	public static void playAnimation1() {
		// Start Processing jar file
		try {
			ProcessBuilder pb = new ProcessBuilder("java", "-jar", FilesHandle.get_temporaryFolder().getAbsolutePath() + "/test.jar");
			Process p = pb.start();
			int status = p.waitFor();
			System.out.println("Processing exited with status: " + status);
		} catch (IOException e2) {
			System.err.println("Processing Animation Fail - " + e2.getClass().getName() + ": " + e2.getMessage());
		} catch (InterruptedException e2) {
			System.err.println("Processing Animation Fail - " + e2.getClass().getName() + ": " + e2.getMessage());
		}
		
//		// Start Processing jar file - Another way - this is not working with jar file
//		URL defaultImage = ClassLoader.class.getResource("/test.jar");
//		File imageFile = new File(defaultImage.toURI());
//		System.out.println(imageFile.getAbsolutePath());		
//		ProcessBuilder pb = new ProcessBuilder("java", "-jar", imageFile.getAbsolutePath());
	}
	
	

	
}
