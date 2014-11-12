package com.brobston.indelible.processor.module;

import com.google.inject.assistedinject.Assisted;

/**
 * Created by andrew on 11/8/14.
 */
public interface CharStreamProviderFactory {
    CharStreamProvider create(@Assisted("fileName") String fileName, @Assisted("encoding") String encoding);
}
