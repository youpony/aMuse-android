package com.youpony.amuse;

import android.hardware.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

/*
 * Fragment containing Camera.
 * Swipe left page.
 */

public class CameraTab extends Fragment {

	

		Camera mCamera;
		CameraPreview mPreview;
		FrameLayout prev;
		private Button start_camera;
		
    	public CameraTab(){	
    	}
    	
    	@Override
    	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                 Bundle savedInstanceState) {
    		
			View rootView = inflater.inflate(R.layout.activity_camera, container, false);
			prev = (FrameLayout) rootView.findViewById(R.id.camera_frame);
            start_camera = (Button) rootView.findViewById(R.id.start_camera);
            start_camera.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (mPreview == null){
                    	attachCamera();
                    	
                    }
                }
            });
			return rootView;
		}
    	
    	// camera on
        public void attachCamera(){
        	mPreview = new CameraPreview(getActivity(), mCamera);
			prev.addView(mPreview);
			Log.d("orrudebug", "camera is attached - orrudebug");
        }
        

        // camera off
        public void detachCamera(){
        	prev.removeView(mPreview);
        	mPreview = null;
        	Log.d("orrudebug", "camera is detached - orrudebug");
        }
    	
    }