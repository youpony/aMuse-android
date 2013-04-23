package com.youpony.amuse;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class SendJSONAsync extends AsyncTask<JSONObject, Void, HttpResponse> {

	@Override
	protected HttpResponse doInBackground(JSONObject... params) {
		Log.i("orrudebug", params[0].toString());
		try{
			//instantiates httpclient to make request
		    DefaultHttpClient httpclient = new DefaultHttpClient();

		    //url with the post data
		    HttpPost httpost = new HttpPost(JSONParsing.BASEURL + "api/s/");
		    
		    ArrayList<NameValuePair> list = new ArrayList<NameValuePair>();
		    list.add(new BasicNameValuePair("email", params[0].getString("email")));
		    list.add(new BasicNameValuePair("name", params[0].getString("name")));
		    list.add(new BasicNameValuePair("posts", params[0].getString("posts")));
		    
		    httpost.setEntity(new UrlEncodedFormEntity(list));
		    //Handles what is returned from the page 
		    HttpResponse response = (HttpResponse) httpclient.execute(httpost);
		    Log.i("orrudebug", "HTTPResponse received in [" + (System.currentTimeMillis()) + "ms]");
		    Log.i("orrudebug", "HTTPResponse: " + response.toString());
		    return response;
		    
		}
		catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	

}
