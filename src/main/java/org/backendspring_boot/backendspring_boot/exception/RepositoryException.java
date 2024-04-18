package org.backendspring_boot.backendspring_boot.exception;

public class RepositoryException extends Exception{
    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(){
        super();
    }
}
