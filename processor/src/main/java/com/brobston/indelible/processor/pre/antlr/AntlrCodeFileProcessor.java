package com.brobston.indelible.processor.pre.antlr;

import com.brobston.indelible.processor.pre.CodeFileProcessor;
import com.brobston.indelible.processor.pre.CodeMetadata;

import java.io.File;
import java.util.Collection;

/**
 * Created by andrew on 11/2/14.
 */
public class AntlrCodeFileProcessor implements CodeFileProcessor {

    public AntlrCodeFileProcessor(File file) {

    }

    @Override
    public Collection<String> getClassNamesInFile() {
        return null;
    }

    @Override
    public void process(CodeMetadata codeMetadata) {

    }
}
