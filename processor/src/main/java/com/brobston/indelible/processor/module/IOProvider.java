package com.brobston.indelible.processor.module;

import com.google.inject.throwingproviders.CheckedProvider;

import java.io.IOException;

/**
 * Created by andrew on 11/8/14.
 */
public interface IOProvider<T> extends CheckedProvider<T> {
    T get() throws IOException;
}
