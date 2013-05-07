package com.youpony.amuse;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
<<<<<<< HEAD
import android.net.Uri;
=======
import android.hardware.Camera.PictureCallback;
>>>>>>> 110bdd1eebefcad11f94d782e314fb37ad337ff7
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

/*
 * Fragment containing Camera.
 * Swipe left page.
 */

public class CameraTab extends Fragment {

	

		CameraSurfaceView mPreview;
		FrameLayout prev;
<<<<<<< HEAD
		private Button start_camera;
		private static int TAKE_PICTURE = 1;
		private Uri outputFileUri;
=======
		private Button start_camera, takeAPicture;
>>>>>>> 110bdd1eebefcad11f94d782e314fb37ad337ff7
		
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
<<<<<<< HEAD
                    	TakePhoto();
=======
                    	attachCamera();
                    	start_camera.setVisibility(View.INVISIBLE);
                    	takeAPicture.setVisibility(View.VISIBLE);
>>>>>>> 110bdd1eebefcad11f94d782e314fb37ad337ff7
                    	
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
    	
<<<<<<< HEAD
    	private void TakePhoto() {
    		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    		File file = new File(Environment.getExternalStorageDirectory(), "test.jpg");
     
    		outputFileUri = Uri.fromFile(file);
    		intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
    		startActivityForResult(intent, TAKE_PICTURE);
     
    	}
    	
    	public void onActivityResult(int requestCode, int resultCode, Intent data) {
    		
    		    }
    		    
    		    
=======
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
    	
>>>>>>> 110bdd1eebefcad11f94d782e314fb37ad337ff7
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