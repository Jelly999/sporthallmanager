package com.example.ht1;

//TODO Varaushallinta-luokka, joka pitää kirjaa sali-olioista

import java.util.ArrayList;
import java.util.Date;

public class ReservationManager {
    ArrayList<Sporthall> sporthallsList;

    ReservationManager() {
        sporthallsList = new ArrayList<>();
    }


    // ======= PUBLIC GET METHODS =======

    // Arrays received from getters should not be saved anywhere permanently

    // Public method for getting all the reservations that the user has ownership of
    public Reservation[] getAllUserOwnedReservations(User user) {
        ArrayList<Reservation> reservations = new ArrayList<>();

        for (Sporthall hall : sporthallsList) { // Goes through all the sporthalls
            for (Reservation reserv : hall.getReservations()) { // Goes through all the reservation of each sporthall
                if (reserv.getOwner().equals(user)) { // Compares the owner of the reservation with given user
                    reservations.add(reserv);
                }
            }
        }
        return reservations.toArray(new Reservation[0]);    // ArrayList to array
    }

    // Public method for getting all the reservations the user is attending
    public Reservation[] getAllUserAttendances(User user) {
        ArrayList<Reservation> reservations = new ArrayList<>();

        for (Sporthall hall : sporthallsList) { // Goes through all the sporthalls
            for (Reservation reserv : hall.getReservations()) { // Goes through all the reservations of each sporthall
                if (reserv.hasUserAsAttendant(user)) { // Uses reservation's method to check if user is attending
                    reservations.add(reserv);
                }
            }
        }
        return reservations.toArray(new Reservation[0]);    // ArrayList to array
    }


    // ======= PUBLIC OTHER METHODS =======

    public void addNewReservation(User owner, Sporthall sporthall, String title, Date startDate, Date endDate) {
        // What reservation requires:
        // User owner, String newTitle, Date reservStartDate, Date reservEndDate

        //TODO: Lisää eheys-vaatimukset
        if (isDateFaulty(startDate, endDate)) { // Is date faulty (ends before starts)
            if (isTimeSlotReserved(sporthall, startDate, endDate)) {
                Reservation reservation = new Reservation(owner, sporthall, title, startDate, endDate);
                sporthall.addReservation(reservation);
            }
        }
    }

    public boolean removeReservation(Sporthall sporthall, Reservation reservation) {
        if (reservation != null) {
            return sporthall.removeReservation(reservation);
        }
        return false;
    }

    public void clearAllSporthalls(Boolean areYouSerious) {
        if (areYouSerious) {
            sporthallsList.clear();
        }
    }

    public boolean isTimeSlotReserved(Sporthall sporthall, Date startDate, Date endDate) {

        // Gets all the existing reservations for the specified sporthall
        for (Reservation reser : sporthall.getReservations()) {

            // If the end and/or start is NOT before the start of any other event
            if (!(isDateBeforeEqual(startDate, reser.getStartDate()) && isDateBeforeEqual(endDate, reser.getStartDate()))) {

                // As start and/or end are NOT before, check if neither of them is NOT fully aftert the event
                if (!(isDateAfterEqual(startDate, reser.getEndDate()) && isDateAfterEqual(endDate, reser.getEndDate()))) {

                    // So if both is not fully before nor is it fully after any event, it must
                    // overlap with pre-existing event
                    return false;
                }
            }
        }
        // Return true if the whole list can be iterated without any conflicts (falses)
        return  true;
    }


    // ======= PRIVATE OTHER METHODS =======


    // Checks if both dates are not null, and if the startDate is before or equal to endDate
    private boolean isDateFaulty(Date startDate, Date endDate) {
        if (startDate != null && endDate != null) {
            return isDateBefore(startDate, endDate);
        }
        return false;
    }

    private boolean isDateBefore(Date date, Date compareTo) {
        return date.before(compareTo);
    }

    // Return true if first date is after or equal the second
    private boolean isDateBeforeEqual(Date date1, Date date2) {
        return ((date1.compareTo(date2)) <= 0); // is before or equal
    }

    // Return true if first date is before or equal the second
    private boolean isDateAfterEqual(Date date1, Date date2) {
        return ((date1.compareTo(date2)) >= 0); // is after or equal
    }
}
