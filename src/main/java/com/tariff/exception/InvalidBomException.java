package com.tariff.exception;

public class InvalidBomException extends RuntimeException {
    public InvalidBomException(String itemId) {
        super("Total cost share for item '" + itemId + "' must equal 1.0");
    }
}