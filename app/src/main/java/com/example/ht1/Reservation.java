package com.example.ht1;

//TODO Varaus-luokka, joka pitää sisällään varauksen tiedot

import java.util.ArrayList;

public class Reservation {
    private static int sequentialUUID = 0;  // Sequential number to keep track of latest UUID
    private int UUID;                       // Reservation's UUID
    private String title;                   // Title of the reservation
    private String describtion;             // Describtion of the reservation
    private User reservationOwner;          // Owner user of the reservation
    private ArrayList<User> attenderList;   // List of users attending the reservation

    Reservation(User owner, String newTitle) {
        UUID = getSequentialUUID();
        title = newTitle;
        reservationOwner = owner;
        attenderList = new ArrayList<>();
    }

    // ======= PUBLIC GETTERS =======

    int getUUID() {return UUID;}
    String getTitle() {return title;}
    String getDescribtion() {return describtion;}
    User getReservationOwner() {return reservationOwner;}
    ArrayList<User> getAttenderList() {return attenderList;}

    int getAttenderAmount() {
        return attenderList.size();
    }


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
        if (user != null) {   // User is not null
            if (!doesUserExistAsAttender(user)) {   // User CANNOT be found from attenderList
                addUser(user);
                return true;
            }
        }
        return false;   // Returns false if not possible due to conditions above
    }

    public boolean removeAttender(User user) {
        if (user != null) {   // User is not null
            if (doesUserExistAsAttender(user)) {   // User CAN be found from attenderList
                removeUser(user);
                return true;
            }
        }
        return false;   // Returns false if not possible due to conditions above
    }

    // USED ONLY FOR DEBUGGIN PURPOSES
    public String toString() {
        return (sequentialUUID + " " + UUID + " " + title + " " + describtion + " " + reservationOwner.getUserName() + " " + getAttenderAmount());
    }

    // ======= PRIVATE METHODS =======

    // TODO: Turha jos kutsutaan vain addAsAttenderista
    private void addUser(User user) {
        attenderList.add(user);
    }

    // TODO: Turha jos kutsutaan vain removeAttenderista
    private void removeUser(User user) {
        attenderList.remove(user);
    }

    // Only used in the builder as a initializer, DO NOT USE ANYWHERE ELSE!
    private int getSequentialUUID() {
        sequentialUUID++; // Rises the latest UUID by one
        return sequentialUUID; // Returns the latest raised UUID
    }


    // Checks the attenderList for given User
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
