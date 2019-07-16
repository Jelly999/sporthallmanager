package com.example.ht1;

//TODO Varaus-luokka, joka pitää sisällään varauksen tiedot

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Reservation {
    private int UUID;                       // Reservation's UUID
    private String title;                   // Title of the reservation
    private Sporthall sporthall;            // The sporthall that is being reserved
    private String sport;             // Describtion of the reservation
    private User owner;                     // owner user of the reservation
    private int maxParticipants;
    private Calendar startCalendar;             // Date at which the reservation starts
    private Calendar endCalendar;               // Date at which the reservation starts
    private List<User> attenderList;   // List of users attending the reservation

    Reservation() {

        attenderList = new ArrayList<>();

        //TODO: Pitäisikö reservation ownerin olla samalla varauksensa attender??
    } // int uniqueID, User owner, Sporthall hall, String newTitle, Calendar reservStartDate, Calendar reservEndDate

    // ======= PUBLIC GETTERS =======

    int getUUID() {return UUID;}
    String getTitle() {return title;}
    String getSport() {return sport;}
    User getOwner() {return owner;}
    int getMaxParticipants() {return maxParticipants;}
    Calendar getStartDate() {return startCalendar;}
    Calendar getEndDate() {return endCalendar;}
    Sporthall getSporthall() {return sporthall;}
    List<User> getAttenderList() {return attenderList;}

    int getAttenderAmount() {
        return attenderList.size();

    }






    // ======= PUBLIC SETTERS =======

    void setUUID(int newID) {UUID = newID;}
    void setTitle(String text) {title = text;}
    void setSport(String text) {sport = text;}
    void setOwner(int ownerID) {

    }
    void setMaxParticipants(int max) {maxParticipants = max;}
    void setStartCalendar(Calendar calend) {}






    // ======= PUBLIC OTHER METHODS =======

    public void setEndFromStartDur(Calendar startDate, int duration) {
        Calendar endDate = (Calendar) startDate.clone();
        endDate.add(Calendar.HOUR_OF_DAY, duration);
        setEndDate(endDate);
    }

    public boolean hasUserAsAttendant(User user) {
        return isUserAttender(user);
    }

    // USED ONLY FOR DEBUGGIN PURPOSES
    public String toString() {
        return (UUID + " " + title + " " + sport + " " + owner.getUserName() + " " + getAttenderAmount());
    }





    // ======= PRIVATE METHODS =======

    // TODO: Turha jos kutsutaan vain addAsAttenderista
    private void addUserTo(User user) {
        attenderList.add(user);
    }

    // TODO: Turha jos kutsutaan vain removeAttenderista
    private void removeUserFrom(User user) {
        attenderList.remove(user);
    }

    private boolean setStartDate(Calendar newStartDate) {
        if (newStartDate != null) {
            startCalendar = newStartDate;
            return true;
        }
        return false;
    }

    private boolean setEndDate(Calendar newEndDate) {
        if (newEndDate != null) {
            endCalendar = newEndDate;
            return true;
        }
        return false;
    }

    // Checks the attenderList for given User
    private boolean isUserAttender(User testUser) {
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
