
import processing.core.PApplet;
import static processing.core.PApplet.println;
import processing.data.JSONArray;
import processing.data.JSONObject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Joey
 */
public class jsonParser extends PApplet {

    JSONObject values;
    JSONArray   kappa;

    public Earthquake mapJSONToEarthquake(String JSONFile) {

        values = loadJSONObject(JSONFile);
        
        kappa = values.getJSONArray("results");
        

        Earthquake earthquake = new Earthquake();

        for (int i = 0; i < values.size(); i++) {
            earthquake.setTimestamp(Utilities.convertStringToTimestamp(values.getString("timestamp")));
            earthquake.setDepth(values.get(i).getDouble("depth"));
            earthquake.setLatitude(values.getDouble("latitude"));
            earthquake.setLongitude(values.getDouble("longitude"));
            earthquake.setQuality(values.getDouble("quality"));
            earthquake.setSize(values.getDouble("size"));
            earthquake.setHumanReadableLocation(values.getString("humanReadableLocation"));

            println(earthquake.getHumanReadableLocation());
        }

        return earthquake;
    }
}
