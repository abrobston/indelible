package com.brobston.indelible.processor.module;

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
