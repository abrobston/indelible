package com.brobston.indelible.processor.util;

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

import java.util.concurrent.Callable;
import java.util.function.Consumer;

/**
 * Created by andrew on 11/11/14.
 */
public class Throwables {
    public static <T> T propagate(Callable<T> callable) {
        try {
            return callable.call();
        }
        catch (Exception e) {
            throw com.google.common.base.Throwables.propagate(e);
        }
    }

    public static <T> Consumer<T> propagate(Consumer<? super T> consumer) {
        return new Consumer<T>() {
            @Override
            public void accept(T t) {
                try {
                    consumer.accept(t);
                }
                catch (Exception e) {
                    throw com.google.common.base.Throwables.propagate(e);
                }
            }
        };
    }
}
