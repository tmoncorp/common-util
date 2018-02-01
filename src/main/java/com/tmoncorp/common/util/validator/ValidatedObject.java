package com.tmoncorp.common.util.validator;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.tmoncorp.common.util.json.BaseModelSupport;

import java.util.Map;

public class ValidatedObject<T extends Validatable> {
    @JsonUnwrapped
    private final T object;
    private final Validation validation;

    public ValidatedObject(T object, Validator<T> validator) {
        this.object = object;
        this.validation = new Validation(validator.isValid(object), validator.getResultDetails(object));
    }

    public Validation getValidation() {
        return validation;
    }

    public T getObject() {
        return object;
    }

    private static final class Validation extends BaseModelSupport {
        private final boolean isValid;
        private final Map<Object, Boolean> resultDetails;

        public Validation(boolean isValid, Map<Object, Boolean> resultDetails) {
            this.isValid = isValid;
            this.resultDetails = resultDetails;
        }

        public boolean getIsValid() {
            return isValid;
        }

        public Map<Object, Boolean> getResultDetails() {
            return resultDetails;
        }
    }
}
