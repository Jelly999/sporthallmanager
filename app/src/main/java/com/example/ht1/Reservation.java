package com.example.ht1;

//TODO Varaus-luokka, joka pitää sisällään varauksen tiedot

import java.util.ArrayList;

public class Reservation {
    static int sequentialUUID = 0;  // Sequential number to keep track of latest UUID
    int UUID;   // Reservation ID
    String title; // Title of the reservation
    String describtion; // Describtion of the reservation
    int attenderAmount;
    User reservationOwner;
    ArrayList<User> attenderList;

    Reservation(User owner, String newTitle) {
        sequentialUUID = getSequentialUUID();
        title = newTitle;
        attenderAmount = 0;
        reservationOwner = owner;
        attenderList = new ArrayList<>();
    }

    // ======= PUBLIC GETTERS =======

    int getUUID() {return UUID;}
    String getTitle() {return title;}
    String getDescribtion() {return describtion;}
    int getAttenderAmount() {return attenderAmount;}
    User getReservationOwner() {return reservationOwner;}
    ArrayList<User> getAttenderList() {return attenderList;}


    // ======= PUBLIC SETTERS =======

    public boolean setTitle(String newTitle) {
        if (newTitle != null) {
            title = newTitle;
            return true;
        }
        return false;
    }

    public boolean setDescribtion(String newDescription) {
        if (newDescription != null) {
            describtion = newDescription;
            return true;
        }
        return false;
    }

    // ======= PUBLIC OTHER METHODS =======

    public boolean addAsAttender(User user) {
        if (user != null) {
            if (!doesUserExistAsAttender(user)) {
                addUser(user);
                return true;
            }
        }
        return false;
    }

    public boolean removeAttender(User user) {
        if (user != null) {
            if (doesUserExistAsAttender(user)) {
                removeUser(user);
                return true;
            }
        }
        return false;
    }

    // ======= PRIVATE METHODS =======

    private void addUser(User user) {
        attenderList.add(user);
        attenderAmount = attenderList.size();
    }

    private void removeUser(User user) {
        attenderList.remove(user);
        attenderAmount = attenderList.size();
    }

    // Only used in the builder as a initializer, DO NOT USE ANYWHERE ELSE!
    private int getSequentialUUID() {
        sequentialUUID++; // Rises the latest UUID by one
        return sequentialUUID; // Returns the latest raised UUID
    }

    private boolean doesUserExistAsAttender(User testUser) {
        if (testUser != null) {
            for (User user : attenderList) {
                if (user.equals(testUser)) {
                    return true;
                }
            }
        }
        return false;
    }
}
