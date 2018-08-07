package com.dds.journal.exceptions;

public class StorageFileNotFoundException extends StorageException{

	private static final long serialVersionUID = -40472058120837246L;

	public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
