package com.mycompany.earthquakevisualisationdev8;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static processing.core.PApplet.map;
import processing.core.PVector;

public class Utilities {

    public static Timestamp convertStringToTimestamp(String str_date) {
        try {
            DateFormat formatter;
            formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
            Date date = formatter.parse(str_date);
            java.sql.Timestamp timeStampDate = new Timestamp(date.getTime());

            return timeStampDate;
        } catch (ParseException e) {
            System.out.println("Exception :" + e);
            return null;
        }
    }

    public static PVector geoToPixel(PVector geoLocation) {

        int mapScreenWidth = 1024;
        int mapScreenHeight = 726;
        float mapGeoLeft = -25.0f;
        float mapGeoRight = -13.0f;
        float mapGeoBottom = 63.1f;
        float mapGeoTop = 66.8f;

        PVector p = new PVector();

        p.x = map(geoLocation.y, mapGeoLeft, mapGeoRight, 0, mapScreenWidth);
        p.y = map(geoLocation.x, mapGeoTop, mapGeoBottom, 0, mapScreenHeight);

        return p;
    }
}
