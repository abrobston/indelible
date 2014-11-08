package com.brobston.indelible.processor.pre;

import java.util.Collection;

/**
 * Created by andrew on 11/2/14.
 */
public interface CodeFileProcessor {

    Collection<String> getClassNamesInFile();
    void process(CodeMetadata codeMetadata);
}
