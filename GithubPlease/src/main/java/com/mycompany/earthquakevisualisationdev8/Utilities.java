/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.earthquakevisualisationdev8;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import processing.core.PVector;

/**
 *
 * @author Roy van den Heuvel
 */
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
        float mapGeoRight = 13.0f;
        float mapGeoBottom = 63.1f;
        float mapGeoTop = 66.8f;

        return new PVector(mapScreenWidth * (geoLocation.x - mapGeoLeft) / (mapGeoRight - mapGeoLeft),
                mapScreenHeight - mapScreenHeight * (geoLocation.y - mapGeoBottom) / (mapGeoTop - mapGeoBottom));
    }
}
