package com.tmoncorp.common.util.validator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public interface DateTimeChecker {
    LocalDateTime getStartDate();
    LocalDateTime getEndDate();

    @JsonIgnore
    default boolean isFinished() {
        return isWrongTime() == false && getEndDate().isBefore(LocalDateTime.now());
    }

    @JsonIgnore
    default boolean isAvailable() {
        LocalDateTime now = LocalDateTime.now();
        return isWrongTime() == false && getStartDate().isBefore(now) && getEndDate().isAfter(now);
    }

    @JsonIgnore
    default boolean isWrongTime() {
        return getStartDate() == null || getEndDate() == null || getStartDate().isAfter(getEndDate());
    }
}
