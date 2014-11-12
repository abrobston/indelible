package com.brobston.indelible.processor;

/*
 * Indelible: to counteract type erasure.
 * Copyright © 2014, Brobston Development, Inc.
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of either the GNU General Public
 * License, version 2, or the three-clause BSD License, at your
 * option.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License or the BSD License for more details.
 */

import com.brobston.indelible.processor.module.*;
import com.brobston.indelible.processor.pre.*;
import com.brobston.indelible.processor.util.Throwables;
import com.google.inject.*;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Reads the Java 8 source code to create metadata that the
 * indelible post-processor will use to rewrite class files.
 *
 * @author Andrew Brobston
 */
@Mojo(name = "preprocess", defaultPhase = LifecyclePhase.PROCESS_SOURCES, threadSafe = true)
public class PreprocessorMojo extends AbstractMojo {
    private static final Injector injector = Guice.createInjector(new ProcessorModule());

    @Parameter(defaultValue = "${project.build.sourceDirectory}")
    private File sourceDirectory;

    @Parameter(defaultValue = "${project.build.outputDirectory}")
    private File outputDirectory;

    @Parameter(defaultValue = "${project.build.directory}/indelible")
    private File preprocessOutputDirectory;

    @Parameter(defaultValue = "${project.build.sourceEncoding}")
    private String encoding;



    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        boolean hasConfigError = false;
        if (!getSourceDirectory().isDirectory()) {
            getLog().error(String.format("The source directory '%0' is not a directory", getSourceDirectory()));
            hasConfigError = true;
        }
        if (!getOutputDirectory().isDirectory()) {
            getLog().error(String.format("The output directory '%0' is not a directory", getOutputDirectory()));
            hasConfigError = true;
        }
        if (!getPreprocessOutputDirectory().isDirectory()) {
            getLog().error(String.format("The preprocess output directory '%0' is not a directory", getPreprocessOutputDirectory()));
            hasConfigError = true;
        }

        if (hasConfigError) {
            throw new MojoFailureException("There were configuration errors.  Please see the log.");
        }

        Injector childInjector = injector.createChildInjector(new Module() {
            @Override
            public void configure(Binder binder) {
                binder.bind(File.class).annotatedWith(SourceDirectory.class).toInstance(getSourceDirectory());
                binder.bind(File.class).annotatedWith(OutputDirectory.class).toInstance(getOutputDirectory());
                binder.bind(File.class).annotatedWith(PreprocessOutputDirectory.class).toInstance(getPreprocessOutputDirectory());
                binder.bind(String.class).annotatedWith(Encoding.class).toInstance(getEncoding());
            }
        });

        CodeFileProcessorFactory codeFileProcessorFactory = childInjector.getInstance(CodeFileProcessorFactory.class);
        CodeMetadataFactory codeMetadataFactory = childInjector.getInstance(CodeMetadataFactory.class);
        Provider<CodeMetadataPersistence> codeMetadataPersistenceProvider = childInjector.getProvider(CodeMetadataPersistence.class);

        try {
            getJavaFiles(getSourceDirectory()).map(codeFileProcessorFactory::create)
                    .parallel()
                    .<CodeMetadata>flatMap(codeFileProcessor -> Throwables.propagate(codeFileProcessor::getClassNamesInFile)
                            .stream()
                            .map(codeMetadataFactory::create)
                            .peek(new Consumer<CodeMetadata>() {
                                @Override
                                public void accept(CodeMetadata codeMetadata) {
                                    try {
                                        codeFileProcessor.process(codeMetadata);
                                    } catch (Exception e) {
                                        throw com.google.common.base.Throwables.propagate(e);
                                    }
                                }
                            }))
                            .forEach(codeMetadata -> codeMetadataPersistenceProvider.get().save(codeMetadata));
        }
        catch (Exception e) {
            throw new MojoExecutionException("IOException", e);
        }
    }

    private Stream<File> getJavaFiles(File directory) {
        return Stream.concat(Arrays.stream(directory.listFiles(file -> file.isFile() && file.getName().endsWith(".java"))),
                Arrays.stream(directory.listFiles(file -> file.isDirectory()))
                        .flatMap(this::getJavaFiles));
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

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }
}
