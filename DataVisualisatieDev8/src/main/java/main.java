/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Roy van den Heuvel
 */
public class main {
    public static void main(String[] args) {
        jsonParser parser = new jsonParser();
        parser.setup();
        parser.mapJSONToEarthquake();
    }
}
