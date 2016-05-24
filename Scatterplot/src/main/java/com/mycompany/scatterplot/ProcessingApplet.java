package com.mycompany.scatterplot;

import java.io.IOException;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PVector;

/**
 *
 * @author Roy van den Heuvel
 */
public class ProcessingApplet extends PApplet {

    final static int windowHeight = 800;
    final static int windowWidth = 800;
    final static int marginBetweenGraphs = 40;
    static ArrayList<ScatterData> scatterList;

    final int RED = color(255, 0, 0);
    final int ORANGE = color(255, 100, 0);
    final int YELLOW = color(255, 190, 0);
    final int GREEN = color(0, 255, 0);
    final int BLUE = color(0, 0, 255);
    final int BLACK = color(0, 0, 0);
    final int WHITE = color(255, 255, 255);

    public static void main(String[] args) {
        try {
            scatterList = TxtReader.parseTxt();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }

        PApplet.main(new String[]{ProcessingApplet.class.getName()});

    }

    @Override
    public void setup() {
        size(windowWidth, windowHeight);
    }

    @Override
    public void draw() {

        fill(WHITE);
        rect(marginBetweenGraphs, marginBetweenGraphs, windowWidth - 2 * marginBetweenGraphs, windowHeight - 2 * marginBetweenGraphs);

        for (ScatterData scatterData : scatterList) {
            PVector toPlotXY = mapDataToGraph(scatterData, marginBetweenGraphs, windowHeight - marginBetweenGraphs, marginBetweenGraphs, windowWidth - marginBetweenGraphs);

            if (scatterData.getCategory() == 1) {
                fill(BLUE);
            }
            if (scatterData.getCategory() == 2) {
                fill(GREEN);
            }
            if (scatterData.getCategory() == 3) {
                fill(ORANGE);
            }
            if (scatterData.getCategory() == 4) {
                fill(RED);
            }

            ellipse(toPlotXY.x, toPlotXY.y, 7, 7);

        }

        fill(BLACK);
        textSize(13);
        int y = 55;
        int textVerticleOffset = 14;
        int offsetFromRight = 200;
        text("CAT 1: Blue", windowWidth - offsetFromRight, y);
        text("CAT 2: Green", windowWidth - offsetFromRight, y + textVerticleOffset);
        text("CAT 3: Orange", windowWidth - offsetFromRight, y + (textVerticleOffset * 2));
        text("CAT 4: Red", windowWidth - offsetFromRight, y + (textVerticleOffset * 3));

        text("EIG1 values are between " + TxtReader.lowestEIG1 + " (left) and " + TxtReader.highestEIG1 + " (right)", 75, windowHeight - 70);
        text("EIG2 values are between " + TxtReader.lowestEIG2 + " (bottom) and " + TxtReader.highestEIG2 + " (top)", 75, windowHeight - 50);

        textSize(14);
        text("Scatterplot of EIG1 and EIG2, with EIG1 on x-axis and EIG2 on y-axis", windowWidth / 5, 20);

    }

    public PVector mapDataToGraph(ScatterData sData, float top, float bottom, float left, float right) {
        PVector pVect = new PVector();
        float x = sData.getEIG1();
        float y = (float) sData.getEIG2();

        float mappedX = map(x, (float) TxtReader.lowestEIG1, (float) TxtReader.highestEIG1, left, right);
        float mappedY = map(y, (float) TxtReader.lowestEIG2, (float) TxtReader.highestEIG2, bottom, top);

        pVect.x = mappedX;
        pVect.y = mappedY;

        return pVect;
    }
}
