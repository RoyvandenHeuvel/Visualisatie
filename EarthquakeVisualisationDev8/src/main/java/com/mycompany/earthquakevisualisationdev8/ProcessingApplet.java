/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.earthquakevisualisationdev8;

import com.google.gson.JsonObject;
import static com.mycompany.earthquakevisualisationdev8.Utilities.geoToPixel;
import java.io.IOException;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

/**
 *
 * @author Roy van den Heuvel
 */
public class ProcessingApplet extends PApplet {

    PImage img;

    @Override
    public void setup() {
        img = loadImage("iceland.jpg");
        size(1024, 726);
        stroke(155, 0, 0);
    }

    @Override
    public void settings() {
        size(1024, 726);
    }

    @Override
    public void draw() {
        image(img, 0, 0);

        stroke(255);

        strokeWeight(0.5f);

        PVector p;  
        p = geoToPixel(new PVector(63.6f, -19.3f));
        System.out.println(p.x + " " + p.y);
        ellipse(p.x, p.y, 5, 10);

        if (mousePressed) {
            line(mouseX, mouseY, pmouseX, pmouseY);
        }
    }

    public static void main(String[] args) {
        PApplet.main(new String[]{ProcessingApplet.class.getName()});
        JsonObject obj = null;
        try {
            JsonManager jsonManager = new JsonManager();
            obj = jsonManager.getJsonFromUrl("http://apis.is/earthquake/is");
            System.out.println(obj.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        PVector vector = new PVector();
        vector.x = 63.6f;
        vector.y = -19.3f;
        System.out.println(geoToPixel(vector).toString());
    }

}
