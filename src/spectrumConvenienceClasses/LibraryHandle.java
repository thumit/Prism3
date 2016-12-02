package spectrumConvenienceClasses;

import java.lang.reflect.Field;
import java.util.Arrays;

public class LibraryHandle {
	public static void addLibraryPath(String pathToAdd) throws Exception {
		
		Field usrPathsField = ClassLoader.class.getDeclaredField("usr_paths");
		usrPathsField.setAccessible(true);

		String[] paths = (String[]) usrPathsField.get(null);
		String[] arrayOfString1;
		
		int j = (arrayOfString1 = paths).length;
		
		for (int i = 0; i < j; i++) {
			String path = arrayOfString1[i];
			if (path.equals(pathToAdd)) {
				return;
			}
		}
		
		String[] newPaths = (String[]) Arrays.copyOf(paths, paths.length + 1);
		newPaths[(newPaths.length - 1)] = pathToAdd;
		usrPathsField.set(null, newPaths);
	}
}