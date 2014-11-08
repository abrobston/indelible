package com.brobston.indelible.processor;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * Modifies Java 8 class files to add the indelible attributes
 * and redirections.
 *
 * @author Andrew Brobston
 */
@Mojo(name = "postprocess", defaultPhase = LifecyclePhase.PROCESS_CLASSES, threadSafe = true)
public class PostprocessorMojo extends AbstractMojo {
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

    }
}
