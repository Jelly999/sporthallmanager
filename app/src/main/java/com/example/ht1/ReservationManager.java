package com.example.ht1;

//TODO Varaushallinta-luokka, joka pitää kirjaa sali-olioista

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ReservationManager {

    public static List<User> usersList;
    public static List<Sporthall> sporthallsList;

    ReservationManager() {
        usersList = new ArrayList<>();
        usersList = SqlManager.getUsersFromDatabase();

        sporthallsList = new ArrayList<>();
        sporthallsList = SqlManager.getSporthallsFromDatabase();
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


    // ======= PUBLIC OTHER METHODS =======

    public void addNewReservation(User owner, Sporthall sporthall, String title, Calendar startDate, int duration) {
        // What reservation requires:
        // User owner, String newTitle, Date reservStartDate, Date reservEndDate
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm");
        Calendar endDate = (Calendar) startDate.clone();
        // end date is cloned from start date
        endDate.add(Calendar.HOUR_OF_DAY, duration);
        // hours of duration are added to the end date

        if (isDateFaulty(startDate, endDate)) { // Is date faulty (ends before starts)
            if (!isTimeSlotReserved(sporthall, startDate, endDate)) { // Is time slot NOT reserved


                String hallIDstr = Integer.toString(sporthall.getUUID());
                String dateStr = format.format(startDate);
                String durStr = Integer.toString(duration);
                String ownnerIDstr = Integer.toString(owner.getUUID());

                // parent hall ID, start time, duration, owner user ID, max participants, recurring
                String[] reservData = {hallIDstr, dateStr, durStr, ownnerIDstr, "0"};
                SqlManager.SQLreservation.insertRow(reservData);
            }
        }
    }

    public void addNewWeeklyReservation(User owner, Sporthall sporthall, String title, Calendar startDate, int duration, int durationInWeeks) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'kk:mm");

        Calendar endDate = (Calendar) startDate.clone();
        // end date is cloned from start date
        endDate.add(Calendar.HOUR_OF_DAY, duration);
        // hours of duration are added to the end date

        if (isWeeklyReservationPossible(sporthall, startDate, endDate, durationInWeeks)) {
            for (int i = 0; i < durationInWeeks; i++) {
                String hallIDstr = Integer.toString(sporthall.getUUID());
                String dateStr = format.format(startDate);
                String durStr = Integer.toString(duration);
                String ownnerIDstr = Integer.toString(owner.getUUID());

                // parent hall ID, start time, duration, owner user ID, max participants, recurring
                String[] reservData = {hallIDstr, dateStr, durStr, ownnerIDstr, "1"};
                SqlManager.SQLreservation.insertRow(reservData);
            }
        }
        updateReservationsFromSQL(sporthall);
    }


    public void updateReservationsFromSQL(Sporthall sporthall) {
        //TODO Tähän se joka ottaa reservationit SQL
    }


    public void logAllUsers(String TAG) {
        for (User user : usersList) {
            Log.d(TAG, user.toString());
        }
    }

    public void logAllSporthalls(String TAG) {
        for (Sporthall sporthall : sporthallsList) {
            Log.d(TAG, sporthall.toString());
        }
    }

    public void logAllReservations(String TAG) {
        for (Sporthall sporthall : sporthallsList) {
            sporthall.logAllReservations(TAG);
        }
    }


    public void deleteUserOwnedReservations(User owner) {
        // TODO HERE GOES THE METHOD CALL SQLMANAGER
    }

    public void deleteUserAttendances(User attender) {
        // TODO HERE GOES THE METHOD CALL SQLMANAGER
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
}
