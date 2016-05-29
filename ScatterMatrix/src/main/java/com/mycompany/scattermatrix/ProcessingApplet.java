package com.mycompany.scattermatrix;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import processing.core.PApplet;
/**
 *
 * @author Roy van den Heuvel
 */
public class ProcessingApplet extends PApplet {
    
    final static int windowHeight = 850;
    final static int windowWidth = 850;
    static ArrayList<Student> parseResults;
    
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
        //horizontal row 1
        rect(25,25,200,200);
        rect(225,25,200,200);
        rect(425,25,200,200);
        rect(625,25,200,200);
        //horizontal row 2
        rect(25,225,200,200);
        rect(225,225,200,200);
        rect(425,225,200,200);
        rect(625,225,200,200);
        //horizontal row 3
        rect(25,425,200,200);
        rect(225,425,200,200);
        rect(425,425,200,200);
        rect(625,425,200,200);
        //horizontal row 4
        rect(25,625,200,200);
        rect(225,625,200,200);
        rect(425,625,200,200);
        rect(625,625,200,200);
    }
}
