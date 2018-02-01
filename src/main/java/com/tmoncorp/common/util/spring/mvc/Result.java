package com.tmoncorp.common.util.spring.mvc;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

/**
 * Rest Controller 에서 return type 이 Generic type 인 경우에 DefaultObjectMapper 를 타지 못하는 이슈로
 * result 를 wrapping 해주기 위한 객체
 */
public class Result<T> {
    @JsonUnwrapped
    private final T object;

    public Result(T object) {
        this.object = object;
    }

    public T getObject() {
        return object;
    }
}
