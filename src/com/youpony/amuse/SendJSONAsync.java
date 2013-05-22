package com.youpony.amuse;

import android.os.AsyncTask;
import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;

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
		    list.add(new BasicNameValuePair("exhibition", params[0].getString("exhibition")));
		    list.add(new BasicNameValuePair("posts", params[0].getString("posts")));
		    
		    httpost.setEntity(new UrlEncodedFormEntity(list));
		    //Handles what is returned from the page 
		    HttpResponse response = (HttpResponse) httpclient.execute(httpost);
		    Log.i("orrudebug", "HTTPResponse received in [" + (System.currentTimeMillis()) + "ms]");
		    Log.i("orrudebug", "HTTPResponse: " + response.getStatusLine());
		    Log.i("orrudebug", "HTTPResponse: " + response.getEntity());
		    return response;
		    
		}
		catch (Exception e){
			e.printStackTrace();
			return null;
		}
	}

	

}
