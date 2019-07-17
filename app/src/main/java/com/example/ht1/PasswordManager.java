package com.example.ht1;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class PasswordManager {

    PasswordManager() {
        //ASD
    }

    /*public static void hashTest() {
        String password = "admin";
        String userName = "admin";
        String hash = getHashedPassword(password, userName);
        //Log.d("HASH", hash);
    }*/


    public static String getHashedPassword(String password, String userName) {
        if (password != null && userName != null) {
            return get_SHA_512_SecurePassword(password, userName);
        }
        return null;
    }

    public static String authNumbers() { // It will generate 6 digit random Number.
        Random rnd = new Random();
        int number = rnd.nextInt(999999);
        return String.format("%06d", number);
    }
    // Checks if authentication code is correct
    public boolean authenticated(View view){
        String randomintvalue = authNumbers();
        TextView randomint = view.findViewById(R.id.text_randint_auth);
        randomint.setText(randomintvalue);
        EditText value = view.findViewById(R.id.edit_inputint_auth);
        String input = value.getText().toString();
        if (randomintvalue == input){
            return true;
        } else;
        return false;
    }

    private static boolean passwordIsCompliant(String str) {
        char ch;
        boolean capitalFlag = false;
        boolean lowerCaseFlag = false;
        boolean numberFlag = false;
        boolean lengthFlag = false;
        if (str.length() > 12){
            lengthFlag = true;
        }
        for(int i=0;i < str.length();i++) {
            ch = str.charAt(i);
            if( Character.isDigit(ch)) {
                numberFlag = true;
            }
            else if (Character.isUpperCase(ch)) {
                capitalFlag = true;
            } else if (Character.isLowerCase(ch)) {
                lowerCaseFlag = true;
            }
            if(numberFlag && capitalFlag && lowerCaseFlag && lengthFlag)
                return true;
        }
        return false;
    }

    private static String get_SHA_512_SecurePassword(String passwordToHash, String   salt){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes(StandardCharsets.UTF_8));
            byte[] bytes = md.digest(passwordToHash.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return generatedPassword;
    }
}
