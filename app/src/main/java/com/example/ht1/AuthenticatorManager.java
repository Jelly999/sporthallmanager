package com.example.ht1;

import java.util.Random;

public class AuthenticatorManager {

    // Random 6 integers
    private int authenticatorCode;

    AuthenticatorManager() {
        generateAndStoreDigits();
    }


    public boolean compareDigits(int inputDigits) {
        return (inputDigits == authenticatorCode);
    }


    private void generateAndStoreDigits() {
        Random rnd = new Random();
        authenticatorCode = rnd.nextInt(999999);
    }
}
