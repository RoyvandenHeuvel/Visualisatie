package com.mycompany.scattermatrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import processing.core.PApplet;
import static processing.core.PApplet.map;
import processing.core.PVector;

/**
 *
 * @author Roy van den Heuvel
 */
public class ProcessingApplet extends PApplet {

    final static int windowHeight = 850;
    final static int windowWidth = 850;
    static ArrayList<Student> parseResults;
    final int RED = color(255, 0, 0);
    final int ORANGE = color(255, 100, 0);
    final int YELLOW = color(255, 190, 0);
    final int GREEN = color(0, 255, 0);
    final int BLUE = color(0, 0, 255);
    final int BLACK = color(0, 0, 0);
    final int WHITE = color(255, 255, 255);

    public static void main(String[] args) {
        try {
            parseResults = TxtReader.parseTxt();
        } catch (IOException ex) {
            Logger.getLogger(ProcessingApplet.class.getName()).log(Level.SEVERE, null, ex);
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
        //horizontal row 1
        rect(25, 25, 200, 200); // DEV/DEV
        rect(225, 25, 200, 200); // DEV/ANA
        rect(425, 25, 200, 200); // DEV/PRJ
        rect(625, 25, 200, 200); // DEV/SKL
        //horizontal row 2
        rect(25, 225, 200, 200);  // DEV/ANA
        rect(225, 225, 200, 200); // ANA/ANA
        rect(425, 225, 200, 200); // ANA/PRJ
        rect(625, 225, 200, 200); // ANA/SKL
        //horizontal row 3
        rect(25, 425, 200, 200);  //DEV/PRJ
        rect(225, 425, 200, 200); //DEV/ANA
        rect(425, 425, 200, 200); // PRJ/PRJ
        rect(625, 425, 200, 200); // DEV/SKL
        //horizontal row 4
        rect(25, 625, 200, 200); // SKL/DEV
        rect(225, 625, 200, 200); //SKL/ANA
        rect(425, 625, 200, 200); // SKL/PRJ
        rect(625, 625, 200, 200); // SKL/SKL

        fill(BLACK);
        textSize(14);

        text("DEV", 110, 125);
        text("ANA", 310, 325);
        text("PRJ", 520, 525);
        text("SKL", 720, 725);

        drawSection(225, 25, 200, 200, 2, 1, parseResults);
        drawSection(425, 25, 200, 200, 3, 1, parseResults);
        drawSection(625, 25, 200, 200, 4, 1, parseResults);

        drawSection(25, 225, 200, 200, 1, 2, parseResults);
        drawSection(425, 225, 200, 200, 3, 2, parseResults);
        drawSection(625, 225, 200, 200, 4, 2, parseResults);

        drawSection(25, 425, 200, 200, 1, 3, parseResults);
        drawSection(225, 425, 200, 200, 2, 3, parseResults);
        drawSection(625, 425, 200, 200, 4, 3, parseResults);

        drawSection(25, 625, 200, 200, 1, 4, parseResults);
        drawSection(225, 625, 200, 200, 2, 4, parseResults);
        drawSection(425, 625, 200, 200, 3, 4, parseResults);

    }

    public void drawSection(int x, int y, int width, int height, int toMapToX, int toMapToY, ArrayList<Student> list) {
        float lowestANA = TxtReader.lowestANA;
        float highestANA = TxtReader.highestANA;
        float lowestDEV = TxtReader.lowestDEV;
        float highestDEV = TxtReader.highestDEV;
        float lowestPRJ = TxtReader.lowestPRJ;
        float highestPRJ = TxtReader.highestPRJ;
        float lowestSKL = TxtReader.lowestSKL;
        float highestSKL = TxtReader.highestSKL;

        int left;
        int right;
        int top;
        int bottom;

        left = x;
        right = x + width;
        top = y;
        bottom = y + height;

        for (Student student : list) {

            float mappedX;
            float mappedY;

            int age = student.getAge();
            int ageStep = (TxtReader.oldest - TxtReader.youngest) / 5;
            int minAge = TxtReader.youngest;

            switch (toMapToX) {
                case 1:
                    mappedX = map((float) student.getGradeDEV(), lowestDEV, highestDEV, left, right);
                    break;
                case 2:
                    mappedX = map((float) student.getGradeANA(), lowestANA, highestANA, left, right);
                    break;
                case 3:
                    mappedX = map((float) student.getGradePRJ(), lowestPRJ, highestPRJ, left, right);
                    break;
                case 4:
                    mappedX = map((float) student.getGradeSKL(), lowestSKL, highestSKL, left, right);
                    break;
                default:
                    System.out.println("Invalid toMapToX");
                    return;
            }

            switch (toMapToY) {
                case 1:
                    mappedY = map((float) student.getGradeDEV(), lowestDEV, highestDEV, top, bottom);
                    break;
                case 2:
                    mappedY = map((float) student.getGradeANA(), lowestANA, highestANA, top, bottom);
                    break;
                case 3:
                    mappedY = map((float) student.getGradePRJ(), lowestPRJ, highestPRJ, top, bottom);
                    break;
                case 4:
                    mappedY = map((float) student.getGradeSKL(), lowestSKL, highestSKL, top, bottom);
                    break;
                default:
                    System.out.println("Invalid toMapToY");
                    return;
            }

            if (age < minAge + ageStep) {
                fill(BLUE);
            } else if (age < minAge + ageStep * 2) {
                fill(GREEN);
            } else if (age < minAge + ageStep * 3) {
                fill(YELLOW);
            } else if (age < minAge + ageStep * 4) {
                fill(ORANGE);
            } else if (age < minAge + ageStep * 5) {
                fill(RED);
            }

            ellipse(mappedX, mappedY, 5, 5);
        }

    }

}
