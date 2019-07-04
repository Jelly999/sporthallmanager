package com.example.ht1;

//TODO Sali-luokka, joka pitää kirjaa salin varaus-olioista

import android.net.wifi.aware.PublishConfig;

public class Sporthall {
    private static int sequentialUUID = 0;  // Sequential number to keep track of latest UUID
    private int UUID;               // Sporthall's own UUID
    private String name;            // Name
    private int maximumCapacity;    // Maximum capacity
    private boolean disabled;       // If sporthall is disabled for repair etc.
    private String universityName;      // Name of the university the sporthall is located in

    Sporthall(String hallName, String university, int maxCapacity) {
        UUID = getSequentialUUID();
        name = hallName;
        universityName = university;
        maximumCapacity = maxCapacity;
        disabled = false;
    }

    // ======= PUBLIC GETTERS =======

    public int getUUID() {return UUID;}
    public String getName() {return name;}
    public int getMaximumCapacity() {return maximumCapacity;}
    public boolean getDisabled() {return disabled;}
    public String getUniversityName() {return universityName;}

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


    // TODO: Voisi olla ehto Disablelle jossa palauttaa false,
    // TODO: kun asettaa samaa ehtoa True = True tyyliin

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
