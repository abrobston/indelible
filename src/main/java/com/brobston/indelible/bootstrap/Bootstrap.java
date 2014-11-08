package com.brobston.indelible.bootstrap;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * Created by andrew on 11/1/14.
 */
public interface Bootstrap {
    void bootstrapReference(MethodHandles.Lookup lookup, String argument, MethodType methodType);
}

