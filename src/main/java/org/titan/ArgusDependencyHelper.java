package org.titan;

import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.shared.invoker.*;

import java.io.File;
import java.util.Collections;
import java.util.Properties;

/**
 * @author starboyate
 */
public class ArgusDependencyHelper {
	@Parameter(defaultValue = "${maven.home}")
	private static File mavenHome;
	public static void generateDependencyFile(File pomFile) {
		String dependencyPath = ArgusPomPath.getClassResourcePath() + File.separator + "src/main/resources" + File.separator + "dependency.txt";
		File file = new File(dependencyPath);
		if (!file.exists()) {
			InvocationRequest request = new DefaultInvocationRequest();
			Properties properties = new Properties();
			properties.setProperty("outputFile", file.getPath());
			properties.setProperty("outputAbsoluteArtifactFilename", "true");
			properties.setProperty("includeScope", "runtime");
			request.setPomFile(pomFile.exists() ? pomFile : ArgusPomPath.getPomFile());
			request.setGoals(Collections.singletonList("dependency:tree"));
			request.setProperties(properties);
			invoke(request);
		}
	}



	private static void invoke(InvocationRequest request) {
		Invoker invoker = new DefaultInvoker();
//		String mavenHome = getMavenHome();
//		if (StringUtils.isBlank(mavenHome)) {
//			throw new IllegalArgumentException("can not find MAVEN_HOME, Please set env variables");
//		}
		invoker.setMavenHome(mavenHome);
		try {
			InvocationResult result = invoker.execute(request);
			if (result.getExitCode() != 0) {
				throw new Exception("generate dependency file error");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

//	private static String getMavenHome() {
//		String mavenHome = System.getenv("MAVEN_HOME");
//		if (!StringUtils.isBlank(mavenHome)) {
//			int binIndex = mavenHome.indexOf(System.lineSeparator() + "bin");
//			if (binIndex > 0) {
//				return mavenHome.substring(0, binIndex);
//			}
//		}
//		return mavenHome;
//	}

}
