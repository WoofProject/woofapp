package com.enterprises.woof.woof;

public class TrackimoDevice {

        private double age;
        private double altitude;
        private boolean gps;
        private double location_id;
        private double lat;
        private double lng;
        private double speed;
        private double battery;
        private boolean is_triangulated;
        private String speed_unit;
        private double device_id;
        private double time;
        private String type;


    public TrackimoDevice(double age, double altitude, boolean gps, double location_id, double lat, double lng, double speed, double battery, boolean is_triangulated, String speed_unit, double device_id, double time, String type) {
        this.age = age;
        this.altitude = altitude;
        this.gps = gps;
        this.location_id = location_id;
        this.lat = lat;
        this.lng = lng;
        this.speed = speed;
        this.battery = battery;
        this.is_triangulated = is_triangulated;
        this.speed_unit = speed_unit;
        this.device_id = device_id;
        this.time = time;
        this.type = type;
    }

    public TrackimoDevice(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public TrackimoDevice() {

    }

    public void setLat(double lat) {
        this.lat = lat;
    }
    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

}
