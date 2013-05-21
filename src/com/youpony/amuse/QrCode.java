package com.youpony.amuse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.google.zxing.integration.android.IntentIntegrator;

/*
 * Fragment containing QrCode reader.
 * Swipe right page.
 */

public class QrCode extends Fragment {
		
	private static View rootView;
	public static ImageButton start_qr;
	public static String BASE_TEXT = "Start QrCode!";
	
	public QrCode(){
	}    
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	        Bundle savedInstanceState) {
	        rootView = inflater.inflate(R.layout.activity_qr_code, container, false);
	        start_qr = (ImageButton) rootView.findViewById(R.id.button1);
	        start_qr.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View v) {
	                IntentIntegrator integrator = new IntentIntegrator(getActivity());
	            	integrator.initiateScan();    
	            }
	        });
	        Log.d("creato", "il qr");
	        return rootView;
	    }  
	
	@Override
	public void onPause(){
		super.onPause();
		Log.d("pausa", "il qr");
	}
	
	@Override
	public void onResume(){
		super.onResume();
		Log.d("resume", "del qr");
	}
	
	@Override
	public void onStop(){
		super.onStop();
		Log.d("stop", "del qr");
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		//release();
	Log.d("distrutto", "il qr");
	        }
	 }