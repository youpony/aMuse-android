package com.youpony.amuse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class JSONParsing extends AsyncTask<String, Void, JSONObject>{
	InputStream is;
	String json;
	JSONObject jFinal;
	public final static String BASEURL = "http://10.23.11.24:8000/";
	public final static String EXHIBITIONS = BASEURL + "api/e/";
	public final static String ITEM = BASEURL + "api/o/";
	
	@Override
	protected JSONObject doInBackground(String... params) {
		//Log.i("orrudebug", "doInBack attivo");
		try {
            // defaultHttpClient
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet request = new HttpGet(params[0]);
 
            HttpResponse httpResponse = httpClient.execute(request);
            HttpEntity httpEntity = httpResponse.getEntity();
            is = httpEntity.getContent();           
 
        } catch (Exception e) {
            e.printStackTrace();
        } 
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
            
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
        
        
        try {
        	//Log.i("orrudebug", "doInBack attivo");
        	

    		//Log.i("orrudebug", "html retrieved from inner connect() , jFinal: " + json);
    		jFinal = new JSONObject(json);
    		
    		
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
        }
		
		
		return jFinal;
	}
	
	protected void onPostExecute(JSONObject result){
		//Log.i("orrudebug", "doinBackgroun :" + result.toString());
	}
	
}

