package com.example.ht1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class LoginFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //Get values from edit fields
        EditText usrname = (EditText) view.findViewById(R.id.editLoginName);
        String username = usrname.getText().toString();
        EditText pwd = (EditText) view.findViewById(R.id.editLoginPwd);
        String password = pwd.getText().toString();
        //check if login is successful
        loginSuccess();
    }

    //              Public

    public boolean loginSuccess() {

        String algorithm = "SHA-512";
        byte[] salt; // get salt from db
        String usernamedb = ""; // Get username from db
        String pwdhashdb = ""; //Get password hash from db
        String pwdhash = generateHash(password); // Create hash for comparison

        // is username in db
        if (username == usernamedb) {
            // does the password match
            if (pwdhash == pwdhashdb) {
                return true;
            }else;
            return false;
        }else;
        return false;
    }

    public static String bytesToStringHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++){
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars)
    }

    //                Private
    private static String generateHash(String password, String algorithm, byte[] salt) throws NoSuchAlgorithmException{
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.reset();
        digest.update(salt);
        byte[] hash = digest.digest(password.getBytes());
        return bytesToStringHex(hash);
    }
    private final static char[] hexArray = "01234656789ABCDEF".toCharArray();


}