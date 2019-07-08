package com.example.ht1;

//TODO Varaus-luokka, joka pitää sisällään varauksen tiedot

import java.util.ArrayList;
import java.util.Calendar;

public class Reservation {
    private static int sequentialUUID = 0;  // Sequential number to keep track of latest UUID
    private int UUID;                       // Reservation's UUID
    private String title;                   // Title of the reservation
    private Sporthall sporthall;            // The sporthall that is being reserved
    private String describtion;             // Describtion of the reservation
    private User Owner;                     // Owner user of the reservation
    private Calendar startCalendar;             // Date at which the reservation starts
    private Calendar endCalendar;               // Date at which the reservation starts
    private ArrayList<User> attenderList;   // List of users attending the reservation

    Reservation(User owner, Sporthall hall, String newTitle, Calendar reservStartDate, Calendar reservEndDate) {
        UUID = getSequentialUUID();
        title = newTitle;
        sporthall = hall;
        Owner = owner;
        startCalendar = reservStartDate;
        endCalendar = reservEndDate;
        attenderList = new ArrayList<>();

        //TODO: Pitäisikö reservation ownerin olla samalla varauksensa attender??
    }

    // ======= PUBLIC GETTERS =======

    int getUUID() {return UUID;}
    String getTitle() {return title;}
    String getDescribtion() {return describtion;}
    User getOwner() {return Owner;}
    Calendar getStartDate() {return startCalendar;}
    Calendar getEndDate() {return endCalendar;}
    Sporthall getSporthall() {return sporthall;}
    ArrayList<User> getAttenderList() {return attenderList;}

    int getAttenderAmount() {
        return attenderList.size();
    }






    // ======= PUBLIC SETTERS =======

    public boolean setTitle(String newTitle) {
        if (newTitle != null) {
            title = newTitle;
            return true;
        }
        return false;
    }

    public boolean setDescribtion(String newDescription) {
        if (newDescription != null) {
            describtion = newDescription;
            return true;
        }
        return false;
    }

    // No internal error handling or integrity check, so the check that
    // start is before end etc. must be done in Reservation Manager
    public void setDate(Calendar startDate, Calendar endDate) {
        setStartDate(startDate);
        setEndDate(endDate);
    }






    // ======= PUBLIC OTHER METHODS =======

    public boolean addAsAttender(User user) {
        if (user != null) {   // User is not null
            if (!isUserAttender(user)) {   // User CANNOT be found from attenderList
                addUserTo(user);
                return true;
            }
        }
        return false;   // Returns false if not possible due to conditions above
    }

    public boolean removeAttender(User user) {
        if (user != null) {   // User is not null
            if (isUserAttender(user)) {   // User CAN be found from attenderList
                removeUserFrom(user);
                return true;
            }
        }
        return false;   // Returns false if not possible due to conditions above
    }

    public boolean hasUserAsAttendant(User user) {
        return isUserAttender(user);
    }

    // USED ONLY FOR DEBUGGIN PURPOSES
    public String toString() {
        return (sequentialUUID + " " + UUID + " " + title + " " + describtion + " " + Owner.getUserName() + " " + getAttenderAmount());
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

    // Only used in the builder as a initializer, DO NOT USE ANYWHERE ELSE!
    private int getSequentialUUID() {
        sequentialUUID++; // Rises the latest UUID by one
        return sequentialUUID; // Returns the latest raised UUID
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
