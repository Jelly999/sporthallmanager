package com.example.ht1;

public class User {

    private int UUID;                       // User's UUID
    private String userName;                // Username
    private boolean admin;                  // Is the user admin (True = yes, False = no)
    private boolean disabled;               // Is the user disabled -||-

    User(int uniqueID, String name) {
        UUID = uniqueID;
        userName = name;
        admin = false;  // User is not admin by default
        disabled = false; // User is not disabled by default
    }




    // ======= PUBLIC GETTERS =======

    public int getUUID() {return UUID;}
    public String getUserName() {return userName;}
    public boolean isAdmin() {return admin;}
    public boolean isDisabled() {return disabled;}





    // ======= PUBLIC SETTERS =======

    public void setUserName(String newName) { userName = newName; }
    public void setAdminPrivilege(boolean isAdmin) { admin = isAdmin; }
    public void setDisabledStatus(boolean isDisabled) {disabled = isDisabled;}




    // ======= PUBLIC OTHER METHODS =======

    // USED ONLY FOR DEBUGGIN PURPOSES
    public String toString() {
        return (UUID + " " + userName + " " + admin);
    }




    // ======= PRIVATE METHODS =======

}
