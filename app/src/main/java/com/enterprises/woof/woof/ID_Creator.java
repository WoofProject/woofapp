package com.enterprises.woof.woof;

public class ID_Counter {

    private int ID_Count = 0;

    public ID_Counter () {}

    public int new_ID() {
        this.ID_Count = ID_Count + 1;
        return ID_Count;
    }

}
