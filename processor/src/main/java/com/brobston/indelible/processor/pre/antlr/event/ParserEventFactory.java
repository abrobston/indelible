package com.brobston.indelible.processor.pre.antlr.event;

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

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * A factory interface for creating {@link ParserEvent} implementations.
 *
 * @author Andrew Brobston
 */
public interface ParserEventFactory<T extends ParserRuleContext> {
    public <U extends ParserEvent<T>> U get(T context, boolean isExitEvent);
}
