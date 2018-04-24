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
}
