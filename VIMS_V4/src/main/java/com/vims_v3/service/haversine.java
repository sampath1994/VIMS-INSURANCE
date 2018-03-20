package com.vims_v3.service;

/**
 * Created by promod on 11/2/2017.
 */
public class haversine {

    public static final double R = 6372.8; // In kilometers

    public static double findDistance(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
      /*  lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);*/

        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(lat1) * Math.cos(lat2);
       // double c = 2 * Math.asin(Math.sqrt(a));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;    // distance returned in kilometers
    }

}
