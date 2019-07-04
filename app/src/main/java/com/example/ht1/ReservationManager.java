package com.example.ht1;

//TODO Varaushallinta-luokka, joka pitää kirjaa sali-olioista

import java.util.ArrayList;
import java.util.Date;

public class ReservationManager {
    private ArrayList<Reservation> reservationsList;

    ReservationManager() {
        reservationsList = new ArrayList<>();
    }


    // ======= PUBLIC GET METHODS =======

    // Arrays received from getters should not be saved anywhere permanently

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


    // ======= PUBLIC OTHER METHODS =======

    public void addNewReservation(User owner, String title, Date startDate, Date endDate) {
        // What reservation requires:
        // User owner, String newTitle, Date reservStartDate, Date reservEndDate

        if (isDateBefore(startDate, endDate)) { // If startDate is before endDate
            Reservation reservation = new Reservation(owner, title, startDate, endDate);
            reservationsList.add(reservation);
        }
    }

    public void removeReservation(Reservation reservation) {
        if (reservation != null) {
            reservationsList.remove(reservation);
        }
    }

    public void clearAllReservations() {
        reservationsList.clear();
    }


    // ======= PRIVATE OTHER METHODS =======

    private boolean isDateBefore(Date date, Date compareTo) {
        // Return true if first date is before the second
        return date.before(compareTo);
    }
}
