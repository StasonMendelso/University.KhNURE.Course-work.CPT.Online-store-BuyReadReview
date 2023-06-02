package org.teamone.onlinestorebuyreadreview.util.mapper;

public interface Mapper<F, T> {

    T map(F object);

    default T map(F fromObject, T toObject) {
        return toObject;
    }

    default F unmap(T fromObject) {
        return null;
    }
}