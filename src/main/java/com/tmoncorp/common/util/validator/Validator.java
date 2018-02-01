package com.tmoncorp.common.util.validator;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class Validator<T extends Validatable> {
    private final CheckList<T> checkList;

    public Validator(CheckList<T> checkList) {
        this.checkList = checkList;
    }

    public boolean isValid(T object) {
        return isInvalid(object) == false;
    }

    public boolean isInvalid(T object) {
        return object == null ? true : checkList.values().stream().anyMatch(each -> each.test(object) == false);
    }

    public Map<Object, Boolean> getResultDetails(T object) {
        ImmutableMap.Builder<Object, Boolean> builder = ImmutableMap.builder();
        checkList.forEach((id, checkItem) -> builder.put(id, object == null ? false : checkItem.test(object)));
        return builder.build();
    }
}
