package com.youpony.amuse;

import java.io.File;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

/*
 * Fragment containing Camera.
 * Swipe left page.
 */

public class CameraTab extends Fragment {

	

		Camera mCamera;
		FrameLayout prev;
		private Button start_camera;
		public static int TAKE_PICTURE = 1;
		private Uri outputFileUri;
		
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
                    	TakePhoto();
                	}
            });
			return rootView;
		}
    	
    	public void TakePhoto() {
    		
    		Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
    		File file = new File(Environment.getExternalStorageDirectory(), "test.jpg");
    	
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
            outputFileUri = Uri.fromFile(file);
    		
            getActivity().startActivityForResult(intent, 100);
    	}
	} 