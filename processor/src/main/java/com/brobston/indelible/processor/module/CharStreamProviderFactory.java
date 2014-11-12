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

import com.google.inject.assistedinject.Assisted;

/**
 * Created by andrew on 11/8/14.
 */
public interface CharStreamProviderFactory {
    CharStreamProvider create(@Assisted("fileName") String fileName, @Assisted("encoding") String encoding);
}
