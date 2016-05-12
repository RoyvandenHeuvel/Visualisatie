package com.mycompany.earthquakevisualisationdev8;


public class Earthquake {
    
    private float latitude;
    private float longitude;
    private float depth;
    private float size;
    
    
    
    public Earthquake(){
        
    }

    public Earthquake(float latitude, float longitude, float depth, float size) {
        
        this.latitude = latitude;
        this.longitude = longitude;
        this.depth = depth;
        this.size = size;
        
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getDepth() {
        return depth;
    }

    public void setDepth(float depth) {
        this.depth = depth;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

}
