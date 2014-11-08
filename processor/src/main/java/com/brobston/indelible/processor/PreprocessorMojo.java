package com.brobston.indelible.processor;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;

/**
 * Reads the Java 8 source code to create metadata that the
 * indelible post-processor will use to rewrite class files.
 *
 * @author Andrew Brobston
 */
@Mojo(name = "preprocess", defaultPhase = LifecyclePhase.PROCESS_SOURCES, threadSafe = true)
public class PreprocessorMojo extends AbstractMojo {
    @Parameter(defaultValue = "${project.build.sourceDirectory}")
    private File sourceDirectory;

    @Parameter(defaultValue = "${project.build.outputDirectory}")
    private File outputDirectory;

    @Parameter(defaultValue = "${project.build.directory}/indelible")
    private File preprocessOutputDirectory;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {

    }

    public File getSourceDirectory() {
        return sourceDirectory;
    }

    public void setSourceDirectory(File sourceDirectory) {
        this.sourceDirectory = sourceDirectory;
    }

    public File getOutputDirectory() {
        return outputDirectory;
    }

    public void setOutputDirectory(File outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public File getPreprocessOutputDirectory() {
        return preprocessOutputDirectory;
    }

    public void setPreprocessOutputDirectory(File preprocessOutputDirectory) {
        this.preprocessOutputDirectory = preprocessOutputDirectory;
    }

}
