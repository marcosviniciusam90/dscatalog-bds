package com.devsuperior.dscatalog.resources.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@SuperBuilder
@NoArgsConstructor
public class ValidationError extends StandardError{
    private List<FieldError> errors;

    public void addError(String fieldName, String message) {
        errors.add(new FieldError(fieldName, message));
    }
}
