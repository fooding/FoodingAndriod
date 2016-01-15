package com.fooding.connectserver;


import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by davinnovation on 2016-01-15.
 */

public class findFood {

    String search_url = "http://api.nal.usda.gov/ndb/search/?";
    String api_key = "&api_key=Jx0U18MOGfyfJMuIGsI61Mgh3q3jPTGfFCKFLjvO";

    public boolean canfind(String tag)
    {
        String request = search_url + api_key + "&q="+tag;

        try {
            JSONObject json = new JSONObject(readJSON(request));
            if (!json.getBoolean(tag))
                return false;
            return true;
        } catch(JSONException e) {
            return false;
        }
    }

    public String readJSON(String request){
        StringBuilder JSONdata = new StringBuilder();
        HttpClient httpClient = new DefaultHttpClient();
        byte[] buffer = new byte[1024];


        try {
            HttpGet httpGetRequest = new HttpGet( request );
            HttpResponse httpResponse = httpClient.execute(httpGetRequest);

            StatusLine statusLine = httpResponse.getStatusLine(); //문자열 HTTP⁄1.1 200 OK
            int statusCode = statusLine.getStatusCode();
            if ( statusCode == 200 ) //서버가 요청한 페이지를 제공했다면
            {
                HttpEntity entity = httpResponse.getEntity();
                if (entity != null) {
                    InputStream inputStream = entity.getContent();

                    try {

                        int bytesRead = 0;
                        BufferedInputStream bis = new BufferedInputStream(inputStream);

                        while ((bytesRead = bis.read(buffer) ) != -1) {
                            String line = new String(buffer, 0, bytesRead);

                            JSONdata.append(line);

                        }

                    } catch (Exception e) {
                        Log.e("logcat", Log.getStackTraceString(e));
                    } finally {
                        try {
                            inputStream.close();
                        } catch (Exception ignore) {
                        }
                    }
                }
            }

        }catch(Exception e){
            Log.e("logcat", Log.getStackTraceString(e));
        }finally{
            httpClient.getConnectionManager().shutdown();
            return JSONdata.toString();
        }
    }
}
