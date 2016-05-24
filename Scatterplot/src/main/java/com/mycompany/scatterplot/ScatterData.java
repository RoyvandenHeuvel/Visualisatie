package com.mycompany.scatterplot;

/**
 *
 * @author Roy van den Heuvel
 */
public class ScatterData {
    private int category;
    private int EIG1;
    private double EIG2;

    public ScatterData(){
        
    }
    
    public ScatterData(int cat, int eig1, double eig2){
        this.EIG1 = eig1;
        this.category = cat;
        this.EIG2 = eig2;
    }
    
    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getEIG1() {
        return EIG1;
    }

    public void setEIG1(int EIG1) {
        this.EIG1 = EIG1;
    }

    public double getEIG2() {
        return EIG2;
    }

    public void setEIG2(double EIG2) {
        this.EIG2 = EIG2;
    }
    
    
    
}
