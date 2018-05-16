package com.enterprises.woof.woof;

public class ID_Creator {

    // Creates a new ID number for new users

    private int ID_Count = 0;

    public ID_Creator() {
        // Once database is set, this should set the ID_Count to the current highest ID number.
    }

    public int new_ID() {
        this.ID_Count = ID_Count + 1;
        return ID_Count;
    }

}
