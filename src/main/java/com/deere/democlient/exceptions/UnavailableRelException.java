package com.deere.democlient.exceptions;

public class UnavailableRelException extends Exception {
    public UnavailableRelException() {
        super("The expected link is unavailable. You may have insufficient privileges to the target resource.");
    }
}
