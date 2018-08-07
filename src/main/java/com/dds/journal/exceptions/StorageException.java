package com.dds.journal.exceptions;

public class StorageException extends RuntimeException{

	private static final long serialVersionUID = -4602923993445692654L;


    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
