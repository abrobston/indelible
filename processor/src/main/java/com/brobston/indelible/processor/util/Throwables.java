package com.brobston.indelible.processor.util;

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
