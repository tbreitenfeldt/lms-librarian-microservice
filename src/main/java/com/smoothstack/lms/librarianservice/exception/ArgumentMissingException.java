package com.smoothstack.lms.librarianservice.exception;

public class ArgumentMissingException extends RuntimeException {

    private static final long serialVersionUID = 4161022243243086738L;

    public ArgumentMissingException(String message) {
        super(message);
    }

}
