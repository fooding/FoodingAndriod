package com.fooding.connectserver;


import android.util.Log;


import com.example.yoonmin.sgen.Example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by davinnovation on 2016-01-15.
 */

public class findFood {

    String search_url = "http://api.nal.usda.gov/ndb/search/?format=json&";
    String api_key = "api_key=Jx0U18MOGfyfJMuIGsI61Mgh3q3jPTGfFCKFLjvO";

    public boolean canfind(String tag)
    {
        String requests = search_url + api_key + "&q="+tag;

        try {
            URL url = new URL(requests);
            Food food = readJSON(url);
            if (food.dbnum == 0)
                return false;
            return true;
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Food readJSON(URL request) {
        Food food = new Food();
        food.dbnum = 0;
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) request.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            JSONObject json = new JSONObject(getStringFromInputStream(in));

            food = parseJSON(json);

            return food;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return food;
        } catch (Exception e) {
            food.dbnum = 1;
            return food;
        }
    }

    private static String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        return sb.toString();
    }

    private Food parseJSON(JSONObject json) throws JSONException {
        Food f = new Food();
        if(json.getJSONObject("errors") == null )
        {
            f.dbnum = 0;
            return f;
        }
        f.dbnum = json.getJSONArray("item").getJSONObject(0).getInt("ndbno");
        return f;
    }
}
