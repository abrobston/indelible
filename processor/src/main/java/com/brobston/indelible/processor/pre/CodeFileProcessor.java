package com.brobston.indelible.processor.pre;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by andrew on 11/2/14.
 */
public interface CodeFileProcessor {
    Collection<String> getClassNamesInFile() throws IOException;
    void process(CodeMetadata codeMetadata) throws IOException;
}
