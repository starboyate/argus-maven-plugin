package org.titan;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.project.MavenProject;



/**
 * @author starboyate
 */
@Mojo(name = "dependency", defaultPhase = LifecyclePhase.COMPILE)
public class ArgusDependencyMojo extends AbstractMojo {


	public void execute() throws MojoExecutionException, MojoFailureException {
		MavenProject project = (MavenProject) this.getPluginContext().get("project");
		ArgusDependencyHelper.generateDependencyFile(project.getFile());
	}

}
