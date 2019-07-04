package com.example.ht1;

//TODO Varaushallinta-luokka, joka pitää kirjaa sali-olioista

import java.util.ArrayList;

public class ReservationManager {
    private ArrayList<Reservation> reservationsList;

    ReservationManager() {
        reservationsList = new ArrayList<>();
    }


    // ======= PUBLIC GETTER METHODS =======

    // Public method for getting all the reservations that the user had ownership of
    public Reservation[] getUserOwnedReservations(User user) {
        ArrayList<Reservation> reservations = new ArrayList<>();

        for (Reservation reserv : reservationsList) {
            if (reserv.getOwner().equals(user)) {
                reservations.add(reserv);
            }
        }
        return reservations.toArray(new Reservation[0]);    // ArrayList to array
    }

    // Public method for getting all the reservations the user is attending
    public Reservation[] getUserAttendances(User user) {
        ArrayList<Reservation> reservations = new ArrayList<>();

        for (Reservation reserv : reservationsList) {
            if (reserv.hasUserAsAttendant(user)) {
                reservations.add(reserv);
            }
        }
        return reservations.toArray(new Reservation[0]);    // ArrayList to array
    }



    // ======= PRIVATE OTHER METHODS =======
}
