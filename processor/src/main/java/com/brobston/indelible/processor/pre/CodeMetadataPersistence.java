package com.brobston.indelible.processor.pre;

import com.brobston.indelible.processor.pre.CodeMetadata;

/**
 * Created by andrew on 11/9/14.
 */
public interface CodeMetadataPersistence {
    void save(CodeMetadata codeMetadata);
}
