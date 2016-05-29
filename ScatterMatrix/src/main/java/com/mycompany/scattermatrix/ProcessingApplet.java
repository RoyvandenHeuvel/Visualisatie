package com.mycompany.scattermatrix;

import java.io.IOException;
import java.text.DecimalFormat;
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

    final static int windowHeight = 1100;
    final static int windowWidth = 1100;
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
        // tile
         text("Matrix Plot of Age and grades for ANA, DEV, PRJ and SKL", 360, 25);
         
        fill(WHITE);
        //horizontal row 1
        rect(125, 125, 200, 200); // DEV/DEV
        rect(325, 125, 200, 200); // DEV/ANA
        rect(525, 125, 200, 200); // DEV/PRJ
        rect(725, 125, 200, 200); // DEV/SKL
        //horizontal row 2
        rect(125, 325, 200, 200);  // DEV/ANA
        rect(325, 325, 200, 200); // ANA/ANA
        rect(525, 325, 200, 200); // ANA/PRJ
        rect(725, 325, 200, 200); // ANA/SKL
        //horizontal row 3
        rect(125, 525, 200, 200);  //DEV/PRJ
        rect(325, 525, 200, 200); //DEV/ANA
        rect(525, 525, 200, 200); // PRJ/PRJ
        rect(725, 525, 200, 200); // DEV/SKL
        //horizontal row 4
        rect(125, 725, 200, 200); // SKL/DEV
        rect(325, 725, 200, 200); //SKL/ANA
        rect(525, 725, 200, 200); // SKL/PRJ
        rect(725, 725, 200, 200); // SKL/SKL

        fill(BLACK);
        textSize(14);

        text("DEV", 210, 225);
        text("ANA", 410, 425);
        text("PRJ", 620, 625);
        text("SKL", 820, 825);

        drawSection(325, 125, 200, 200, 2, 1, parseResults);
        drawSection(525, 125, 200, 200, 3, 1, parseResults);
        drawSection(725, 125, 200, 200, 4, 1, parseResults);

        drawSection(125, 325, 200, 200, 1, 2, parseResults);
        drawSection(525, 325, 200, 200, 3, 2, parseResults);
        drawSection(725, 325, 200, 200, 4, 2, parseResults);

        drawSection(125, 525, 200, 200, 1, 3, parseResults);
        drawSection(325, 525, 200, 200, 2, 3, parseResults);
        drawSection(725, 525, 200, 200, 4, 3, parseResults);

        drawSection(125, 725, 200, 200, 1, 4, parseResults);
        drawSection(325, 725, 200, 200, 2, 4, parseResults);
        drawSection(525, 725, 200, 200, 3, 4, parseResults);

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
        
                // line's on x
        int numberOfSquare = 0;

        for (int j = 125; j < 750; j = j + 200) {
            int start = j;
            int line = 115;

            if (numberOfSquare % 2 == 0) {
                line = 925;
            }
            numberOfSquare++;

            float lowestGrade = 0;
            float higestGrade = 0;

            if (numberOfSquare == 1) {
                lowestGrade = lowestDEV;
                higestGrade = highestDEV;
            }

            if (numberOfSquare == 2) {
                lowestGrade = lowestANA;
                higestGrade = highestANA;
            }

            if (numberOfSquare == 3) {
                lowestGrade = lowestPRJ;
                higestGrade = highestPRJ;
            }

            if (numberOfSquare == 4) {
                lowestGrade = lowestSKL;
                higestGrade = highestSKL;
            }

            float gradeRange = (higestGrade - lowestGrade) / 5;
            float currentGradeX = lowestGrade;
            float currentGradeY = higestGrade;
            DecimalFormat df = new DecimalFormat("#.#");
            
            
            for (int i = start; i <= (start + 200); i = i + 40) {
                // x lines
                line(i, line, i, line + 10);
                // y lines
                line(line, i, line + 10, i);
                
                // for the deviation in placement of above and under, left and right
                if (numberOfSquare % 2 == 0) {
                    text(df.format(currentGradeX), i - 7, line - 5);
                    text(df.format(currentGradeY), line - 30, i + 4);
                } else {
                    text(df.format(currentGradeX), i - 7, line + 30);
                    text(df.format(currentGradeY), line + 15, i + 4);
                }
                currentGradeX = currentGradeX + gradeRange;
                currentGradeY = currentGradeY - gradeRange;
            }

        }

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
            int minAge = TxtReader.youngest;
            int ageStep = (TxtReader.oldest - minAge) / 5;

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
                    mappedY = map((float) student.getGradeDEV(), lowestDEV, highestDEV, bottom, top);
                    break;
                case 2:
                    mappedY = map((float) student.getGradeANA(), lowestANA, highestANA, bottom, top);
                    break;
                case 3:
                    mappedY = map((float) student.getGradePRJ(), lowestPRJ, highestPRJ, bottom, top);
                    break;
                case 4:
                    mappedY = map((float) student.getGradeSKL(), lowestSKL, highestSKL, bottom, top);
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
