package com.vipul_acciojob.bookManager;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String str) {
        super(str);
    }
}
