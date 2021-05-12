package com.devsuperior.dscatalog.services.exceptions;

public class EntityNotFoundException extends RuntimeException{

    public EntityNotFoundException(Long id) {
        super(String.format("Entity with ID %s not found", id));
    }
}
