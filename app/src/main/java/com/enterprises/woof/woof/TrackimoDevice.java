package com.enterprises.woof.woof;

public class TrackimoDevice {

        private double location_id;
        private double device_id;
        private double time;
        private double lat;
        private double lng;
        private int battery;
        private String gps;
        private boolean is_triangulated;


    public TrackimoDevice(double location_id, double device_id, double time, double lat, double lng, int battery, String gps, boolean is_triangulated) {
        this.location_id = location_id;
        this.device_id = device_id;
        this.time = time;
        this.lat = lat;
        this.lng = lng;
        this.battery = battery;
        this.gps = gps;
        this.is_triangulated = is_triangulated;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public double getLocation_id() {
        return location_id;
    }

    public void setLocation_id(double location_id) {
        this.location_id = location_id;
    }

    public double getDevice_id() {
        return device_id;
    }

    public void setDevice_id(double device_id) {
        this.device_id = device_id;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public int getBattery() {
        return battery;
    }

    public void setBattery(int battery) {
        this.battery = battery;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public boolean isIs_triangulated() {
        return is_triangulated;
    }

    public void setIs_triangulated(boolean is_triangulated) {
        this.is_triangulated = is_triangulated;
    }
}
