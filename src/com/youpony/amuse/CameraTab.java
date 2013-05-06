package com.youpony.amuse;

import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

/*
 * Fragment containing Camera.
 * Swipe left page.
 */

public class CameraTab extends Fragment {

	

		CameraSurfaceView mPreview;
		FrameLayout prev;
		private Button start_camera, takeAPicture;
		
    	public CameraTab(){	
    	}
    	
    	@Override
    	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                 Bundle savedInstanceState) {
    		
			View rootView = inflater.inflate(R.layout.activity_camera, container, false);
			prev = (FrameLayout) rootView.findViewById(R.id.camera_frame);
            start_camera = (Button) rootView.findViewById(R.id.start_camera);
            takeAPicture = (Button)rootView.findViewById(R.id.take_photo);
            
            takeAPicture.setVisibility(View.INVISIBLE);
            
            start_camera.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (mPreview == null){
                    	attachCamera();
                    	start_camera.setVisibility(View.INVISIBLE);
                    	takeAPicture.setVisibility(View.VISIBLE);
                    	
                    }
                }
            });
          
            
            takeAPicture.setOnClickListener(new OnClickListener() 
            {
                    public void onClick(View v) 
                    {
                            Camera camera = mPreview.getCamera();
                            camera.takePicture(null, null, new HandlePictureStorage());
                    }
            });
            
			return rootView;
			
		}
    	
        private class HandlePictureStorage implements PictureCallback
        {

                @Override
                public void onPictureTaken(byte[] picture, Camera camera) 
                {
                        //The picture can be stored or do something else with the data
                        //in this callback such sharing with friends, upload to a Cloud component etc
                        
                        //This is invoked when picture is taken and the data needs to be processed
                        System.out.println("Picture successfully taken: "+picture);
                        
                        String fileName = "shareme.jpg";
                        String mime = "image/jpeg";
                        
                        
                }
        }
    	
    	// camera on
        public void attachCamera(){
        	mPreview = new CameraSurfaceView(getActivity());
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