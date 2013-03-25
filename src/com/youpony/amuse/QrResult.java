package com.youpony.amuse;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class QrResult extends Activity {

	String id, exib;
	MuseApi api;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_qr_result);
		Intent intent = getIntent();
		setApi(intent);
		PageViewer.mViewPager.postInvalidate();
		
		api = new MuseApi();
		
		
		//display qrCode content as textView
		WebView w = (WebView) findViewById(R.id.webresult);
		String url = api.BASEURL + "api" + "/" + "o" + "/" + exib;
		
		w.loadUrl(url);
		
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
		id = tmp[0];
		exib = tmp[1];
	}

}
