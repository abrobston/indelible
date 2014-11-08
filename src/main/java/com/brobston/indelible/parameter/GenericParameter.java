package com.brobston.indelible.parameter;

import java.io.Serializable;

public interface GenericParameter extends Serializable {
    String getTypeVariableName();
    Class<?> getImplementingClass();
}