package com.brobston.indelible.processor.pre;

import java.io.File;

/**
 * Created by andrew on 11/8/14.
 */
public interface CodeFileProcessorFactory {
    CodeFileProcessor create(File file);
}
