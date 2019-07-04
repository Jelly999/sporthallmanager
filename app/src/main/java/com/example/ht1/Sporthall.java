package com.example.ht1;

//TODO Sali-luokka, joka pitää kirjaa salin varaus-olioista

import android.net.wifi.aware.PublishConfig;

public class Sporthall {
    static int sequentialUUID = 0;  // Sequential number to keep track of latest UUID
    int UUID;   // Sporthall's own UUID
    String name; // Sporthall's own name
    int maximumCapacity; // Sporthall's maximum capacity
    boolean reserved;
    boolean disabled;

    Sporthall() {
        UUID = getSequentialUUID();

        // Initializing basic conditions
        reserved = false;
        disabled = false;
    }

    // ======= PUBLIC GETTERS =======

    public int getUUID() {return UUID;}
    public String getName() {return name;}
    public int getMaximumCapacity() {return maximumCapacity;}
    public boolean getReserved() {return reserved;}
    public boolean getDisabled() {return disabled;}

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


    // TODO: Voisi olla ehto reservedille ja disablelle jossa palauttaa false,
    // TODO: kun asettaa samaa ehtoa True = True tyyliin
    public void setReserved(boolean isReserved) {
        reserved = isReserved;
    }

    public void setDisabled(boolean isDisabled) {
        disabled = isDisabled;
    }

    // ======= PRIVATE METHODS =======

    // Only used in the builder as a initializer, DO NOT USE ANYWHERE ELSE!
    private int getSequentialUUID() {
        sequentialUUID++; // Rises the latest UUID by one
        return sequentialUUID; // Returns the latest raised UUID
    }


}
