package com.mycompany.earthquakevisualisationdev8;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonManager {
    public JsonObject getJsonFromUrl(String sUrl) throws MalformedURLException, IOException {

        URL url = new URL(sUrl);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        JsonParser jp = new JsonParser(); 
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent())); 
        JsonObject rootobj = root.getAsJsonObject(); 

        return rootobj;
    }
}
