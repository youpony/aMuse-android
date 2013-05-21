package com.youpony.amuse;

import java.io.File;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;

/*
 * Fragment containing Camera.
 * Swipe left page.
 */


public class CameraTab extends Fragment {

	
		public static int imageNum;
		FrameLayout prev;
		private ImageButton start_camera;
		
    	public CameraTab(){	
    	}
    	
    	@Override
    	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    		
			View rootView = inflater.inflate(R.layout.activity_camera, container, false);
			prev = (FrameLayout) rootView.findViewById(R.id.camera_frame);
            start_camera = (ImageButton) rootView.findViewById(R.id.start_camera);
            start_camera.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    	TakePhoto();
                	}
            });
			return rootView;
		}
    	
    	public void TakePhoto() {
    		
    		Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
    		
    		imageNum=0;
    		File imagesFolder = new File(Environment.getExternalStorageDirectory(), "aMuse");
    		imagesFolder.mkdirs();
    		String fileName = "image_" + String.valueOf(imageNum) + ".jpg";
    		File output = new File(imagesFolder, fileName);
    		while (output.exists()){
    			imageNum++;
    			fileName = "image_" + String.valueOf(imageNum) + ".jpg";
    			output = new File(imagesFolder, fileName);
    		}
    		Uri uriSavedImage = Uri.fromFile(output);
    		intent.putExtra(MediaStore.EXTRA_OUTPUT, uriSavedImage);

    		getActivity().startActivityForResult(intent, 100);
    		
    		 
    	}
	} 