
import java.sql.Timestamp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Joey
 */
public class Earthquake {
    
    private Timestamp timestamp;
    private Double latitude;
    private Double longitude;
    private Double depth;
    private Double size;
    private Double quality;
    private String humanReadableLocation;

    public Earthquake(Timestamp timestamp, Double latitude, Double longitude, Double depth, Double size, Double quality, String humanReadableLocation) {
        this.timestamp = timestamp;
        this.latitude = latitude;
        this.longitude = longitude;
        this.depth = depth;
        this.size = size;
        this.quality = quality;
        this.humanReadableLocation = humanReadableLocation;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getDepth() {
        return depth;
    }

    public void setDepth(Double depth) {
        this.depth = depth;
    }

    public Double getSize() {
        return size;
    }

    public void setSize(Double size) {
        this.size = size;
    }

    public Double getQuality() {
        return quality;
    }

    public void setQuality(Double quality) {
        this.quality = quality;
    }

    public String getHumanReadableLocation() {
        return humanReadableLocation;
    }

    public void setHumanReadableLocation(String humanReadableLocation) {
        this.humanReadableLocation = humanReadableLocation;
    }

}
