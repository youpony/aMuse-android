package com.youpony.amuse;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class QrResult extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_qr_result);
		Intent intent = getIntent();
		String message = intent.getStringExtra(PageViewer.EXTRA_MESSAGE);
		
		//display qrCode content as textView
		TextView textView = (TextView) findViewById(R.id.stringa);
		textView.setText(message);
		
		//manage Confirm button action
		Button confirm = (Button) findViewById(R.id.confirm);
		
		//manage Cancel button action
		Button cancel = (Button) findViewById(R.id.cancel);
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
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

}
