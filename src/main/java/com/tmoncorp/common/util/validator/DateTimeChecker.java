package com.tmoncorp.common.util.validator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public interface DateTimeChecker {
    LocalDateTime getStartDate();
    LocalDateTime getEndDate();

    @JsonIgnore
    default boolean isFinished() {
        return isFinished(LocalDateTime.now());
    }

    @JsonIgnore
    default boolean isFinished(LocalDateTime currentTime) {
        return isWrongTime() == false && getEndDate().isBefore(currentTime);
    }

    @JsonIgnore
    default boolean isAvailable() {
        return isAvailable(LocalDateTime.now());
    }

    @JsonIgnore
    default boolean isAvailable(LocalDateTime currentTime) {
        return isWrongTime() == false && getStartDate().isBefore(currentTime) && getEndDate().isAfter(currentTime);
    }

    @JsonIgnore
    default boolean isWrongTime() {
        return getStartDate() == null || getEndDate() == null || getStartDate().isAfter(getEndDate());
    }
}
