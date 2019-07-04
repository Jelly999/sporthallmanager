package com.example.ht1;

public class User {

    private static int sequentialUUID = 0;  // Sequential number to keep track of latest UUID
    private int UUID;
    private String userName;
    private boolean admin;

    User(String name) {
        UUID = getSequentialUUID();
        userName = name;
        admin = false;
    }

    // ======= PUBLIC GETTERS =======

    public int getUUID() {return UUID;}
    public String getUserName() {return userName;}
    public boolean isAdmin() {return admin;}


    // ======= PUBLIC SETTERS =======

    public void setUserName(String newName) {
        userName = newName;
    }

    public void setAdminPrivilege(boolean isAdmin) {
        admin = isAdmin;
    }

    // ======= PRIVATE METHODS =======

    // Only used in the builder as a initializer, DO NOT USE ANYWHERE ELSE!
    private int getSequentialUUID() {
        sequentialUUID++; // Rises the latest UUID by one
        return sequentialUUID; // Returns the latest raised UUID
    }
}
