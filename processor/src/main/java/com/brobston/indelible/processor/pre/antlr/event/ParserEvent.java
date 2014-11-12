package com.brobston.indelible.processor.pre.antlr.event;

import org.antlr.v4.runtime.ParserRuleContext;

/**
 * Created by andrew on 11/9/14.
 */
public interface ParserEvent<T extends ParserRuleContext> {
    T getParserRuleContext();
}
