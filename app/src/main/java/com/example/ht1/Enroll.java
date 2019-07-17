package com.example.ht1;

import java.util.ArrayList;
import java.util.List;

public class Enroll {

    private int EnrollID;
    private int ReserveID;
    private int UserUUID;
    private List<Enroll> enrollsList;

    Enroll() {
        enrollsList = new ArrayList<>();
    }


    // ======= PUBLIC GETTERS =======

    public int getEnrollID() {return EnrollID;}
    public int getReserveID() {return ReserveID;}
    public int getUserUUID() {return UserUUID;}


    // ======= PUBLIC SETTERS =======

    public void setEnrollID(int newEnrollID) {EnrollID = newEnrollID;}
    public void setReserveID(int newReserveID) {ReserveID = newReserveID;}
    public void setUserUUID(int newUserID) {UserUUID = newUserID;}

}
