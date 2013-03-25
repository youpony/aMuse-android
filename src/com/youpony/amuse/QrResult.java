package com.youpony.amuse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class QrResult extends Activity {

	String id, exib;
	private static String url;
	
	//set JSON object fields
	private static final String author = "author";
	private static final String year = "year";
	private static final String name = "name";
	private static final String desc = "desc";
	
	String APIauthor;
	public static JSONObject json;
	TextView t;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_qr_result);
		
		//set TextView
		t = new TextView(this);
		t = (TextView) findViewById(R.id.JSONResult);
		
		Intent intent = getIntent();
		setApi(intent);
		PageViewer.mViewPager.postInvalidate();
		
		new JSONParsing(){
			protected void onPostExecute(JSONObject jData){
				updateExhibitions(jData);
			}
		}.execute(JSONParsing.ITEM + id + "/");
		
		//manage Confirm button action
		Button confirm = (Button) findViewById(R.id.confirm);
		
		//manage Cancel button action
		Button cancel = (Button) findViewById(R.id.cancel);
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				QrResult.this.finish();
				PageViewer.mViewPager.setCurrentItem(1);
				
			}
		});
	}

	protected void updateExhibitions(JSONObject jData) {
		Log.i("orrudebug", "doinBackground :" + jData.toString());
		t.setText(jData.toString());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.qr_result, menu);
		
		return true;
	}
	
	private void setApi(Intent intent){
		String message = intent.getStringExtra(PageViewer.EXTRA_MESSAGE);
		String tmp[];
		String del = "&";
		tmp = message.split(del, 2);
		exib = tmp[0];
		id = tmp[1];
	}

}
