package com.youpony.amuse;

import java.io.IOException;
import java.net.URL;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
//NOT USED
public class DownloadImage extends AsyncTask<String, Void, Bitmap> {
	
	Bitmap bit;
	public final static String URL = "";

	@Override
	protected Bitmap doInBackground(String... params) {
		try {
			bit = loadBitmap(params[0]);
		} catch (IOException e) {
			Log.i("orrudebug", "cannot download image");
			e.printStackTrace();
		}
		return null;
	}
	
	protected void onPostExecut(Bitmap result){
		
	}
	
	public static Bitmap loadBitmap(String url) throws IOException {
	   
		URL newurl = new URL(url); 
		Log.i("orrudebug", "url immagine: " + url);
		Bitmap bitmap = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream()); 

	    return bitmap;
	}
}
