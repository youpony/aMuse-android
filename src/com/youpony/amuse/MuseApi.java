package com.youpony.amuse;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
/*
 * NOT USED RIGHT NOW! ONLY FOR FUTURE DEVELOPMENT
 */
public class MuseApi extends AsyncTask<String, Void, String>{

	public final static String BASEURL = "http://10.23.11.217:8000/";
	private String url;
	
	@Override
	protected String doInBackground(String... params) {
		url = params[0];
		HttpClient http = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		return null;
	}

		
}
	
	


