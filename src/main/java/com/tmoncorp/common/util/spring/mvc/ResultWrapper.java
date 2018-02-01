package com.tmoncorp.common.util.spring.mvc;

public class ResultWrapper {
    static public <T> Result<T> wrap(T object) {
        return object == null ? null : new Result<>(object);
    }
}
