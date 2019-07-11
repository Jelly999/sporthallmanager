package com.example.ht1;

//TODO Varaushallinta-luokka, joka pitää kirjaa sali-olioista

import java.util.ArrayList;
import java.util.Calendar;

public class ReservationManager {
    private ArrayList<Sporthall> sporthallsList;

    ReservationManager() {
        sporthallsList = new ArrayList<>();
    }





    // ======= PUBLIC GET METHODS =======

    // Arrays received from getters should not be saved anywhere permanently

    // Public method for getting all the reservations that the user has ownership of
    public String[] getAllUserOwnedReservationsInfo(User user) {
        ArrayList<Reservation> reservations = new ArrayList<>();

        for (Sporthall hall : sporthallsList) { // Goes through all the sporthalls
            // TODO Tähän jotain
        }
        return null;
    }


    // Public method for getting all the reservations the user is attending
    public String[] getAllUserAttendancesInfo(User user) {
        ArrayList<Reservation> reservations = new ArrayList<>();

        for (Sporthall hall : sporthallsList) { // Goes through all the sporthalls
            // TODO Tähän jotain
        }
        return null;
    }




/*
    // ======= PUBLIC OTHER METHODS =======

    public void addNewReservation(int UUID, User owner, Sporthall sporthall, String title, Calendar startDate, Calendar endDate) {
        // What reservation requires:
        // User owner, String newTitle, Date reservStartDate, Date reservEndDate

        if (isDateFaulty(startDate, endDate)) { // Is date faulty (ends before starts)
            if (!isTimeSlotReserved(sporthall, startDate, endDate)) { // Is time slot NOT reserved
                //Reservation reservation = new Reservation(UUID, owner, sporthall, title, startDate, endDate);
                //addReservation(sporthall, reservation); // Private method to invoke reservations
                // TODO reservation lisäys
            }
        }
    }

    public void addNewWeeklyReservation(int UUID, User owner, Sporthall sporthall, String title, Calendar startDate, Calendar endDate, int durationInWeeks) {

        if (isWeeklyReservationPossible(sporthall, startDate, endDate, durationInWeeks)) {
            for (int i = 0; i < durationInWeeks; i++) {
                //Reservation reservation = new Reservation(UUID, owner, sporthall, title, startDate, endDate);
                //addReservation(sporthall, reservation); // Private method to invoke reservation
                // TODO Reservation lisäys
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

    public void removeAllUserReservations(User owner) {

        for (Sporthall sporthall : sporthallsList) { // Goes through every sporthall
            sporthall.removeAllUserReservations(owner);
        }
    }




    // ======= PUBLIC BOOLEAN METHODS =======

    public boolean isTimeSlotReserved(Sporthall sporthall, Calendar startDate, Calendar endDate) {

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

    public boolean isWeeklyReservationPossible(Sporthall sporthall, Calendar startDate, Calendar endDate, int DurationInWeeks) {
        for (int i = 0; i < DurationInWeeks; i++) {
            if(isTimeSlotReserved(sporthall, startDate, endDate)) { // For every week checks if reservation is possible
                return false; // if the time slot is reserved returns false
            }

            addAWeekToDate(startDate);
            addAWeekToDate(endDate);
        }
        return true; // if all the weeks are gone through without falses, then it is possible
        // to reserve the given timeslot
    }





    // ======= PRIVATE OTHER METHODS =======

    private void addReservation(Sporthall sporthall, Reservation reservation) {
        sporthall.addReservation(reservation);
    }


    // Checks if both dates are not null, and if the startDate is before or equal to endDate
    private boolean isDateFaulty(Calendar startDate, Calendar endDate) {
        if (startDate != null && endDate != null) {
            return isDateBefore(startDate, endDate);
        }
        return false;
    }

    private boolean isDateBefore(Calendar date, Calendar compareTo) {
        return date.before(compareTo);
    }

    // Return true if first date is after or equal the second
    private boolean isDateBeforeEqual(Calendar date1, Calendar date2) {
        return ((date1.compareTo(date2)) <= 0); // is before or equal
    }

    // Return true if first date is before or equal the second
    private boolean isDateAfterEqual(Calendar date1, Calendar date2) {
        return ((date1.compareTo(date2)) >= 0); // is after or equal
    }

    private void addAWeekToDate(Calendar date) {
        date.add(Calendar.DAY_OF_YEAR, 7);
        // TODO TÄMÄN PITÄISI kasvattaa viikkoa yhdellä!
    }
*/
}
