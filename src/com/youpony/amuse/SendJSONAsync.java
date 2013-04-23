package com.youpony.amuse;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class SendJSONAsync extends AsyncTask<String, Void, HttpResponse> {

	@Override
	protected HttpResponse doInBackground(String... params) {
		try{
			//instantiates httpclient to make request
		    DefaultHttpClient httpclient = new DefaultHttpClient();

		    //url with the post data
		    HttpPost httpost = new HttpPost("http://youpony.pittoni.org");
		    
		    //passes the results to a string builder/entity
		    StringEntity se = new StringEntity(params[0]);

		    //sets the post request as the resulting string
		    httpost.setEntity(se);
		    //sets a request header so the page receving the request
		    //will know what to do with it
		    httpost.setHeader("Accept", "application/json");
		    httpost.setHeader("Content-type", "application/json");

		    //Handles what is returned from the page 
		    ResponseHandler responseHandler = new BasicResponseHandler();
		    return httpclient.execute(httpost, responseHandler);
		}
		catch (Exception e){
			return null;
		}
	}

	

}
