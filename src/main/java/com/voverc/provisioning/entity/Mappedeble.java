package com.voverc.provisioning.entity;

import com.googlecode.jmapper.JMapper;
import com.googlecode.jmapper.api.enums.MappingType;
import com.googlecode.jmapper.api.enums.NullPointerControl;

public interface Mappedeble {
    default <T> T map(T destination) {
        return (T) new JMapper(destination.getClass(), this.getClass()).getDestination(destination, this,
                NullPointerControl.NOT_ANY, MappingType.ONLY_NULL_FIELDS, MappingType.ONLY_VALUED_FIELDS);
    }
}
