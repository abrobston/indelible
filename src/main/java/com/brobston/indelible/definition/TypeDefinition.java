package com.brobston.indelible.definition;

import com.brobston.indelible.parameter.GenericParameter;

import java.util.Collection;

/**
 * Created by andrew on 11/1/14.
 */
public interface TypeDefinition {
    Collection<InterfaceDefinition> getInterfaces();
    Collection<GenericParameter> getGenericParameters();
}
