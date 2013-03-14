package com.youpony.amuse;

import java.io.Console;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.hardware.Camera;
import android.os.Bundle;

/*
 * Fragment containing QrCode reader.
 * Swipe right page.
 */

public class QrCode extends Fragment {
	
		private Camera mCamera;
		private CameraPreview mPreview;
		private SurfaceHolder mHolder;
		private static View rootView;
		private FrameLayout prev;
		private Button start_qr;
		
        public QrCode(){
        	
        }
        

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
                rootView = inflater.inflate(R.layout.activity_qr_code, container, false);
                //IMPORTANT : fix this
                //put camera preview inside frame layout
                prev = (FrameLayout) rootView.findViewById(R.id.qr_frame);
                start_qr = (Button) rootView.findViewById(R.id.button1);
                start_qr.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        if (mPreview == null){
                        	attachCamera();
                        	
                        }
                    }
                });
                Log.d("creato", "il qr");
                return rootView;
            }
        
        // camera on
        public void attachCamera(){
            mPreview =  new CameraPreview(getActivity(), mCamera);;
            prev.addView(mPreview);
            Log.d("orrudebug", "camera is attached - orrudebug");
        }
        
        // camera off
        public void detachCamera(){
        	prev.removeView(mPreview);
        	mPreview = null;
        	Log.d("orrudebug", "camera is detached - orrudebug");
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