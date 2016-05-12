package com.mycompany.earthquakevisualisationdev8;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import static com.mycompany.earthquakevisualisationdev8.Utilities.geoToPixel;
import java.io.IOException;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PVector;

public class ProcessingApplet extends PApplet {

    static ArrayList<Earthquake> earthquakes;
    PImage img;
    final int RED = color(255, 0, 0);
    final int ORANGE = color(255, 100, 0);
    final int YELLOW = color(255, 190, 0);
    final int GREEN = color(0, 255, 0);
    final int BLUE = color(0, 0, 255);
    final int BLACK = color(0, 0, 0);

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

        if (earthquakes != null) {
            for (Earthquake earthquake : earthquakes) {

                float size = earthquake.getSize();
                float pxSize = 10;

                if (size < 0.0f) {
                    pxSize = 15;
                }
                if (size > 0.0f && size <= 0.3f) {
                    pxSize = 20;
                }
                if (size > 0.3f && size <= 0.6f) {
                    pxSize = 25;
                }
                if (size > 0.6f && size <= 0.9f) {
                    pxSize = 30;
                }
                if (size > 0.9f) {
                    pxSize = 35;
                }

                float depth = earthquake.getDepth();

                if (depth < 2) {
                    fill(GREEN);
                }

                if (depth < 4 && depth >= 2) {
                    fill(BLUE);
                }

                if (depth < 6 && depth >= 4) {
                    fill(YELLOW);
                }

                if (depth < 8 && depth >= 6) {
                    fill(ORANGE);
                }

                if (depth > 8) {
                    fill(RED);
                }

                PVector pixelLocation = geoToPixel(new PVector(earthquake.getLatitude(), earthquake.getLongitude()));
                ellipse(pixelLocation.x, pixelLocation.y, pxSize, pxSize);
            }
        }

        textSize(20);

        fill(BLACK);

        text("Map van aardbevingen in IJsland", 350, 20);

        textSize(11);
        text("Gegevens van: http://apis.is/earthquake/is", 375, 35);

        ellipse(765, 360, 15, 15);
        text("Omvang lager dan 0.0", 785, 364);

        ellipse(765, 400, 20, 20);
        text("Omvang tussen 0.0 en 0.3", 785, 404);

        ellipse(765, 440, 25, 25);
        text("Omvang tussen 0.3 en 0.6", 785, 444);

        ellipse(765, 480, 30, 30);
        text("Omvang tussen 0.6 en 0.9", 785, 484);

        ellipse(765, 520, 35, 35);
        text("Omvang groter dan 0.9", 785, 524);

        fill(RED);
        ellipse(780, 560, 12, 12);
        text("Diepte boven de 8", 795, 564);

        fill(ORANGE);
        ellipse(780, 580, 12, 12);
        text("Diepte tussen 6 en 8", 795, 584);

        fill(YELLOW);
        ellipse(780, 600, 12, 12);
        text("Diepte tussen 4 en 6", 795, 604);

        fill(GREEN);
        ellipse(780, 620, 12, 12);
        text("Diepte tussen 2 en 4", 795, 624);

        fill(BLUE);
        ellipse(780, 640, 12, 12);
        text("Diepte lager dan 2", 795, 644);

    }

    public static void main(String[] args) {
        PApplet.main(new String[]{ProcessingApplet.class.getName()});
        JsonObject obj = null;
        try {
            JsonManager jsonManager = new JsonManager();
            obj = jsonManager.getJsonFromUrl("http://apis.is/earthquake/is");
            System.out.println(obj.toString());
        } catch (IOException e) {
            System.out.println("JSON file could not be retrieved from the servers.");
        }
        if (obj != null) {
            JsonElement element = obj.get("results");
            if (element.isJsonArray()) {
                JsonArray jsonArray = element.getAsJsonArray();
                earthquakes = new ArrayList();
                for (int i = 0; i < jsonArray.size(); i++) {
                    Earthquake eq = new Earthquake();
                    eq.setLatitude(jsonArray.get(i).getAsJsonObject().get("latitude").getAsFloat());
                    eq.setLongitude(jsonArray.get(i).getAsJsonObject().get("longitude").getAsFloat());
                    eq.setDepth(jsonArray.get(i).getAsJsonObject().get("depth").getAsFloat());
                    eq.setSize(jsonArray.get(i).getAsJsonObject().get("size").getAsFloat());

                    earthquakes.add(eq);
                }
            }
        }
    }

}
