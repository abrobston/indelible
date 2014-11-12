package com.brobston.indelible.processor.pre.antlr;

import com.brobston.indelible.processor.module.Encoding;
import com.brobston.indelible.processor.pre.CodeFileProcessor;
import com.brobston.indelible.processor.pre.CodeMetadata;
import com.google.common.eventbus.EventBus;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.Parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by andrew on 11/2/14.
 */
public class AntlrCodeFileProcessor implements CodeFileProcessor {
    private final Parser parser;
    private final File file;
    private final String encoding;
    private ArrayList<String> classNamesInFile;

    @Inject
    AntlrCodeFileProcessor(@Assisted File file, Parser parser, @Encoding String encoding) {
        this.parser = parser;
        this.file = file;
        this.encoding = encoding;
    }

    private void parseFile() {
    }

    @Override
    public synchronized Collection<String> getClassNamesInFile() throws IOException {
        if (classNamesInFile == null) {
            ANTLRFileStream stream = new ANTLRFileStream(getFile().getAbsolutePath(), getEncoding());
        }
        return classNamesInFile;
    }

    @Override
    public synchronized void process(CodeMetadata codeMetadata) throws IOException {

    }

    public Parser getParser() {
        return parser;
    }

    public File getFile() {
        return file;
    }

    public String getEncoding() {
        return encoding;
    }
}
