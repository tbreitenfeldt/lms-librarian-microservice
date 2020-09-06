package com.smoothstack.lms.librarianservice.exception;

public class IllegalRelationReferenceException extends RuntimeException {

    private static final long serialVersionUID = 929936541372086265L;

    public IllegalRelationReferenceException(String message) {
        super(message);
    }

}
