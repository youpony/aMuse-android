package com.youpony.amuse;

import android.content.Intent;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
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
		private SurfaceHolder mHolder;
		private static View rootView;
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
    	
        /*
         * Implement this to load camera preview in background, and
         * then attach to the surfaceView without the loading blank page.
         */
        private class CameraLoader extends AsyncTask<Void,Void,Void>{
        	protected void onPreExecute(){
        		}
        	protected Void doInBackground(Void... params) {
				//mPreview = new CameraPreview(PageViewer.getAppContext(), mCamera);
				return null;
			}
			
        	protected void onPostExectue(){
        		//prev.addView(mPreview);
            	
        	}
        }
    	
    }