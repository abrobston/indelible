package com.brobston.indelible.processor.module;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.assistedinject.Assisted;
import com.google.inject.throwingproviders.CheckedProvider;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;

import java.io.IOException;

/**
 * Created by andrew on 11/8/14.
 */
public class CharStreamProvider implements IOProvider<CharStream> {
    private final String fileName;
    private final String encoding;

    @Inject
    CharStreamProvider(@Assisted("fileName") String fileName, @Assisted("encoding") String encoding) {
        this.fileName = fileName;
        this.encoding = encoding;
    }

    @Override
    public CharStream get() throws IOException {
        return new ANTLRFileStream(fileName, encoding);
    }
}
