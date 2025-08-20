package com.tariff.exception;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String itemId) {
        super("Item '" + itemId + "' not found in BoM");
    }
}