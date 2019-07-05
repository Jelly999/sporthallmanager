package com.example.ht1;

//TODO Sali-luokka, joka pitää kirjaa salin varaus-olioista

import android.net.wifi.aware.PublishConfig;

import java.util.ArrayList;

public class Sporthall {
    private static int sequentialUUID = 0;  // Sequential number to keep track of latest UUID
    private int UUID;                       // Sporthall's own UUID
    private String name;                    // Name
    private int maximumCapacity;            // Maximum capacity
    private boolean disabled;               // If sporthall is disabled for repair etc.
    private String universityName;          // Name of the university the sporthall is located in
    private ArrayList<Reservation> reservationsList;

    Sporthall(String hallName, String university, int maxCapacity) {
        UUID = getSequentialUUID();
        name = hallName;
        universityName = university;
        maximumCapacity = maxCapacity;
        disabled = false;
        reservationsList = new ArrayList<>();
    }

    // ======= PUBLIC GETTERS =======

    public int getUUID() {return UUID;}
    public String getName() {return name;}
    public int getMaximumCapacity() {return maximumCapacity;}
    public boolean getDisabled() {return disabled;}
    public String getUniversityName() {return universityName;}

    public Reservation[] getReservations() {return reservationsList.toArray(new Reservation[0]);}

    // ======= PUBLIC SETTERS =======

    public boolean setName(String newName) {
        // Can add integity check which when failed should return False,
        // and not set the given value
        if (newName != null) {
            name = newName;
            return true;
        }
        return false;
    }

    public boolean setMaximumCapacity(int newMaximumCapacity) {
        // Can add integity check which when failed should return False,
        // and not set the given value
        maximumCapacity = newMaximumCapacity;
        return true;
    }


    public void setDisabled(boolean isDisabled) {
        disabled = isDisabled;
    }

    // ======= PUBLIC OTHER METHODS =======

    public boolean addReservation(Reservation reservation) {
        if (reservation != null) {
            reservationsList.add(reservation);
            return true;
        }
        return false;
    }

    public boolean removeReservation(Reservation reservation) {
        if (doesReservationExist(reservation)) {
            reservationsList.remove(reservation);
            reservation = null; //TODO onko tarpeellinen rivi?
            return true;
        }
        return false;
    }

    // USED ONLY FOR DEBUGGIN PURPOSES
    public String toString() {
        return (sequentialUUID + " " + UUID + " " + name + " " + universityName + " " + maximumCapacity + " " + disabled);
    }

    // ======= PRIVATE METHODS =======

    // Only used in the builder as a initializer, DO NOT USE ANYWHERE ELSE!
    private int getSequentialUUID() {
        sequentialUUID++; // Rises the latest UUID by one
        return sequentialUUID; // Returns the latest raised UUID
    }

    private boolean doesReservationExist(Reservation reservation) {
        for (Reservation reserv : reservationsList) {
            if (reserv.equals(reservation)) {
                return true;
            }
        }
        return false;
    }


}
