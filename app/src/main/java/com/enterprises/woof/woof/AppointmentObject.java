package com.enterprises.woof.woof;


import android.support.annotation.NonNull;

import java.util.Objects;

public class AppointmentObject implements Comparable{
    private String title;
    private String date;
    private String time;

    public AppointmentObject(String title, String date, String time) {
        Objects.requireNonNull(title);
        Objects.requireNonNull(date);
        Objects.requireNonNull(time);
        this.title = title;
        this.date = date;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
