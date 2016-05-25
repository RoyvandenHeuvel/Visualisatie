package com.mycompany.scattermatrix;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roy van den Heuvel
 */
public class ProcessingApplet {
    public static void main(String[] args) {
        try {
            TxtReader.parseTxt();
        } catch (IOException ex) {
            Logger.getLogger(ProcessingApplet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
