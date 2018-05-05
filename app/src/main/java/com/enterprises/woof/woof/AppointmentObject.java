package com.enterprises.woof.woof;

import java.sql.Time;
import java.util.Date;

public class AppointmentObject {
    private String title;
    private Date date;
    private Time time;

    public AppointmentObject(String title, Date date, Time time) {
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
