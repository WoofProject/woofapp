package com.enterprises.woof.woof;


import android.support.annotation.NonNull;

import java.util.Objects;

public class MedicationObject implements Comparable{

    private String title;
    private String day;
    private String time;

    public MedicationObject(String title, String day, String time) {
        Objects.requireNonNull(title);
        Objects.requireNonNull(day);
        Objects.requireNonNull(time);
        this.title = title;
        this.day = day;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public int compareTo(@NonNull Object o) {
        return 0;
    }
}
