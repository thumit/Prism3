package prismConvenienceClass;

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
