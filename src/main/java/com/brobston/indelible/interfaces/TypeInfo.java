package com.brobston.indelible.interfaces;

import com.brobston.indelible.parameter.GenericParameter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Created by andrew on 10/26/14.
 */
public interface TypeInfo {
    default Collection<GenericParameter> getGenericParameters() {
        return Collections.unmodifiableCollection(new ArrayList<GenericParameter>());
    }
}
