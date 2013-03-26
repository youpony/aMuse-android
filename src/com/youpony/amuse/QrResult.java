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
	String a, n, y, d;
	
	//set JSON object fields
	private static final String author = "author";
	private static final String year = "year";
	private static final String name = "name";
	private static final String description = "desc";
	
	String APIauthor;
	public static JSONObject json;
	TextView t;
	Boolean stop = false;
	Item oggetto;
	Button confirm, cancel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		oggetto = new Item();
		//set TextView
		t = new TextView(this);
		
		Intent intent = getIntent();
		setApi(intent);
		PageViewer.mViewPager.postInvalidate();
		
		if(stop == false){
			new JSONParsing(){
				protected void onPostExecute(JSONObject jData){
							updateExhibitions(jData);
							Boolean check = Story.findName(oggetto.name);
							int position = Story.findPos(oggetto.name);
							if(check == true){
								close();
								Intent info = new Intent(PageViewer.getAppContext(), ItemInfo.class);
								info.putExtra("pos", position);
								startActivity(info);
							}
							else if(oggetto.name == null){
								wrongQr();
							}
							else{
								//instantiate layout
								setContentView(R.layout.activity_qr_result);
								t = (TextView) findViewById(R.id.JSONResult);
								
								//display object infos
								t.setText("autore: " + oggetto.author + "\n"+ "nome: " + oggetto.name + "\n" + "anno: " + oggetto.year + "\n" + "descrizione: " + oggetto.description);
								
								//manage Confirm button action
								confirm = (Button) findViewById(R.id.confirm);
								confirm.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(View v) {
										PageViewer.values.add(oggetto);
										Story.files.notifyDataSetChanged();
										close();
									}
								});
								
								//manage Cancel button action
								cancel = (Button) findViewById(R.id.cancel);
								cancel.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(View v) {
										close();
									}
								});
							}
						}
				
				
			}.execute(JSONParsing.ITEM + id + "/");
		}
	}
		
	
	//and now let's get some JSON
	protected void updateExhibitions(JSONObject jData) {
		//Log.i("orrudebug", "doinBackground :" + jData.toString());
		try{
			JSONObject c = jData.getJSONObject("data");
				try {
					n = c.getString(name);
					
				} catch (JSONException e) {
					Log.i("orrudebug", "hai sbagliato API, chiama e ostia contro Luca Colleoni");
					e.printStackTrace();
				}
				try {
					a = c.getString(author);
				} catch (JSONException e) {
					Log.i("orrudebug", "hai sbagliato API, chiama e ostia contro Luca Colleoni");
					e.printStackTrace();
				}
				try {
					d = c.getString(description);
				} catch (JSONException e) {
					Log.i("orrudebug", "hai sbagliato API, chiama e ostia contro Luca Colleoni");
					e.printStackTrace();
				}
				try {
					y = c.getString(year);
				} catch (JSONException e) {
					Log.i("orrudebug", "hai sbagliato API, chiama e ostia contro Luca Colleoni");
					e.printStackTrace();
				}
		}
		catch (JSONException e){
			e.printStackTrace();
		}

		
		oggetto.author = a;
		oggetto.name = n;
		oggetto.year = y;
		oggetto.description = d;
		//t.setText(jData.toString());
	}

	void close(){
		QrResult.this.finish();
		PageViewer.mViewPager.setCurrentItem(1);
	}
	
	void wrongQr(){
		stop = true;
		QrResult.this.finish();
		PageViewer.mViewPager.setCurrentItem(2);
		QrCode.start_qr.setText("wrong QrCode, try again!");
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
		if(message != null){
			Log.i("orrudebug", "scan presente!");
			if(message.contains("&")){
				tmp = message.split(del, 2);
				try { 
					int check = Integer.parseInt(tmp[0]);
					exib = tmp[0];
					} 
				catch(NumberFormatException nFE) { 
					wrongQr();
				}
				try { 
					int check = Integer.parseInt(tmp[1]);
					id = tmp[1];
					} 
				catch(NumberFormatException nFE) { 
					wrongQr();
				}
			}
			else{
				stop = true;
				wrongQr();
			}
		}
		else{
			Log.i("orrudebug", "scan nullo!");
			stop = true;
			close();
		}
	}

}
