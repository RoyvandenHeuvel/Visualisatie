package com.mycompany.scattermatrix;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Roy van den Heuvel
 */
public class TxtReader {
    
    public static int youngest = Integer.MAX_VALUE;
    public static int oldest = Integer.MIN_VALUE;
    public static ArrayList<Student> students;
    
    public static void parseTxt() throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(new File("studentcijfers.txt")));
        
        br.readLine(); // skip the first line of the file.
        br.readLine(); // skip the second line of the file.

        String txtLine;
        students = new ArrayList<>();

        while ((txtLine = br.readLine()) != null) {
            String[] splitLine = txtLine.split("\\s+");
            
            int studentNumber = Integer.parseInt(splitLine[0]);
            int age = Integer.parseInt(splitLine[1]);
            double gradeANA = Double.parseDouble(commasToPeriods(splitLine[2]));
            double gradeDEV = Double.parseDouble(commasToPeriods(splitLine[3]));
            double gradePRJ = Double.parseDouble(commasToPeriods(splitLine[4]));
            double gradeSKL = Double.parseDouble(commasToPeriods(splitLine[5]));
            
            Student stdnt = new Student(studentNumber, age, gradeANA, gradeDEV, gradePRJ, gradeSKL);
            students.add(stdnt);
            
            if(age > oldest){
                oldest = age;
            }
            if(age < youngest){
                youngest = age;
            }
        }
    }
    
    public static String commasToPeriods(String stringWithCommas){
        return stringWithCommas.replaceAll(",", ".");
    }
    
}
