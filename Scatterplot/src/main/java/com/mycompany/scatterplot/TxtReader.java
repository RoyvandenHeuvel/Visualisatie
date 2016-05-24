package com.mycompany.scatterplot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Roy van den Heuvel
 */
public class TxtReader {

    static BufferedReader br;
    static ArrayList<ScatterData> scatterDataArray;
    static int lowestEIG1 = Integer.MAX_VALUE;
    static int highestEIG1 = Integer.MIN_VALUE;
    static double lowestEIG2 = Double.MAX_VALUE;
    static double highestEIG2 = Double.MIN_VALUE;

    public static ArrayList<ScatterData> parseTxt() throws FileNotFoundException, IOException {
        br = new BufferedReader(new FileReader(new File("scatterplot.txt")));

        br.readLine(); // skip the first line of the file.

        String txtLine;
        scatterDataArray = new ArrayList<>();

        while ((txtLine = br.readLine()) != null) {

            String[] splitLine = txtLine.split("\\s+");
            int cat = Integer.parseInt(splitLine[0]);
            int eig1 = Integer.parseInt(splitLine[1]);
            double eig2 = Double.parseDouble(splitLine[2].replaceAll(",", "."));

            ScatterData sd = new ScatterData(cat, eig1, eig2);

            scatterDataArray.add(sd);

        }

        for (ScatterData scatterData : scatterDataArray) {
            if (scatterData.getEIG1() >= highestEIG1) {
                highestEIG1 = scatterData.getEIG1();
            }
            if (scatterData.getEIG1() <= lowestEIG1) {
                lowestEIG1 = scatterData.getEIG1();
            }
            if (scatterData.getEIG2() >= highestEIG2) {
                highestEIG2 = scatterData.getEIG2();
            }
            if (scatterData.getEIG2() <= lowestEIG2) {
                lowestEIG2 = scatterData.getEIG2();
            }
        }

        return scatterDataArray;

    }
}
