package org.titan;

import java.io.File;
import java.net.URL;

/**
 * @author starboyate
 */
public class ArgusPomPath {
	private static File PROXY_SERVICE_POM_File;

	public static File getPomFile() {
		if (PROXY_SERVICE_POM_File == null) {
			PROXY_SERVICE_POM_File = findPomFile();
		}
		return PROXY_SERVICE_POM_File;
	}

	public static String getClassResourcePath() {

		String userDir = System.getProperty("user.dir");
		return userDir;
	}

	private static File findPomFile() {
		File pomFile = null;
		try {
			File file = new File(ArgusPomPath.getClassResourcePath());
			String pom = file.getPath() + "/pom.xml";
			pomFile = new File(pom);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return pomFile;
	}


}
