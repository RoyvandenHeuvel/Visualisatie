/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.scattermatrix;

/**
 *
 * @author Roy van den Heuvel
 */
public class Student {
    private final int studentNumber;
    private final int age;
    private final double gradeDEV;
    private final double gradeANA;
    private final double gradePRJ;
    private final double gradeSKL;

    public Student(int studentNumber, int age, double gradeDEV, double gradeANA, double gradePRJ, double gradeSKL) {
        this.studentNumber = studentNumber;
        this.age = age;
        this.gradeDEV = gradeDEV;
        this.gradeANA = gradeANA;
        this.gradePRJ = gradePRJ;
        this.gradeSKL = gradeSKL;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public int getAge() {
        return age;
    }

    public double getGradeDEV() {
        return gradeDEV;
    }

    public double getGradeANA() {
        return gradeANA;
    }

    public double getGradePRJ() {
        return gradePRJ;
    }

    public double getGradeSKL() {
        return gradeSKL;
    }
    
    
}
