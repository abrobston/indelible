package com.brobston.indelible.parameter;

/**
 * Created by andrew on 10/26/14.
 */
public abstract class WildcardGenericParameter implements GenericParameter {
    @Override
    public String getTypeVariableName() {
        return "?";
    }

    @Override
    public Class<?> getImplementingClass() {
        return Object.class;
    }
}
