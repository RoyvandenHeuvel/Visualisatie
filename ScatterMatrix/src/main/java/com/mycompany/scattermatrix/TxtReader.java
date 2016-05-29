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
    
    public static float lowestANA= Float.MAX_VALUE;
    public static float lowestDEV= Float.MAX_VALUE;
    public static float lowestSKL= Float.MAX_VALUE;
    public static float lowestPRJ= Float.MAX_VALUE;
    
    public static float highestANA= Float.MIN_VALUE;
    public static float highestDEV= Float.MIN_VALUE;
    public static float highestSKL= Float.MIN_VALUE;
    public static float highestPRJ= Float.MIN_VALUE;
    
    
    public static int youngest = Integer.MAX_VALUE;
    public static int oldest = Integer.MIN_VALUE;
    public static ArrayList<Student> students;
    
    public static ArrayList<Student> parseTxt() throws FileNotFoundException, IOException{
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
            if(lowestANA > gradeANA){
                lowestANA = (float) gradeANA;
            }
            if(lowestDEV > gradeDEV){
                lowestDEV = (float) gradeDEV;
            }
            if(lowestPRJ > gradePRJ){
                lowestPRJ = (float) gradePRJ;
            }
            if(lowestSKL > gradeSKL){
                lowestSKL = (float) gradeSKL;
            }
            
            if(highestANA < gradeANA){
                highestANA = (float) gradeANA;
            }
            if(highestDEV < gradeDEV){
                highestDEV = (float) gradeDEV;
            }
            if(highestPRJ < gradePRJ){
                highestPRJ = (float) gradePRJ;
            }
            if(highestSKL < gradeSKL){
                highestSKL = (float) gradeSKL;
            }
            
            
        }
        
        return students;
    }
    
    public static String commasToPeriods(String stringWithCommas){
        return stringWithCommas.replaceAll(",", ".");
    }
    
}
