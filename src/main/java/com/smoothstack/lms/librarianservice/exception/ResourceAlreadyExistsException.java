package com.smoothstack.lms.librarianservice.exception;

public class ResourceAlreadyExistsException extends RuntimeException {

    private static final long serialVersionUID = -8322521734283962416L;

    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

}
