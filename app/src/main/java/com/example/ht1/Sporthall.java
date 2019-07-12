package com.example.ht1;

//TODO Sali-luokka, joka pitää kirjaa salin varaus-olioista

import android.net.wifi.aware.PublishConfig;

import java.net.IDN;
import java.sql.Struct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Sporthall {
    private int UUID;                       // Sporthall's own UUID
    private String name;                    // Name
    //private int maximumCapacity;            // Maximum capacity
    private String type;
    private boolean disabled;               // If sporthall is disabled for repair etc.
    private String universityName;          // Name of the university the sporthall is located in
    private ArrayList<Reservation> reservationsList; // List of all the reservations for this sporthall

    Sporthall() {
        reservationsList = new ArrayList<>();
    }




    // ======= PUBLIC GETTERS =======

    public int getUUID() {return UUID;}
    public String getName() {return name;}
    //public int getMaximumCapacity() {return maximumCapacity;}
    public String getType() {return type;}
    public boolean getDisabled() {return disabled;}
    public String getUniversityName() {return universityName;}
    public int getReservationAmount() {return reservationsList.size();}

    public ArrayList<Reservation> getReservations() {return reservationsList;}





    // ======= PUBLIC SETTERS =======
    public void setUUID(int ID) {UUID = ID;}
    public void setName(String nam) {name = nam;}
   //public void setMaximumCapacity(int capacity) {maximumCapacity = capacity;}
    public void setType(String newType) {type = newType;}
    public void setDisabled(boolean isDisabled) {disabled = isDisabled;}
    public void setUniversityName(String UNIname) {universityName = UNIname;}

    public void setReservationsList(ArrayList<Reservation> resevations) {reservationsList = resevations;}



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

    public void removeAllUserReservations(User owner) { // Removes all reservations by owner iterating
        Iterator iterator = reservationsList.iterator();
        while (iterator.hasNext()) {
            if (((Reservation)iterator.next()).getOwner().equals(owner)) {
                iterator.remove();
            }
        }
    }

    // USED ONLY FOR DEBUGGIN PURPOSES
    public String toString() {
        return (UUID + " " + name + " " + universityName + " " + disabled);
    }




    // ======= PRIVATE METHODS =======

    private boolean doesReservationExist(Reservation reservation) {
        for (Reservation reserv : reservationsList) {
            if (reserv.equals(reservation)) {
                return true;
            }
        }
        return false;
    }


}
