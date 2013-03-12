package com.youpony.amuse;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.hardware.Camera;
import android.os.Bundle;

/*
 * Fragment containing QrCode reader.
 * Swipe right page.
 */

public class QrCode extends Fragment {
	
		Camera mCamera;
		private CameraPreview mPreview;
		
        public QrCode(){
        }
        

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
                View rootView = inflater.inflate(R.layout.activity_qr_code, container, false);
                //IMPORTANT : fix this
                //put camera preview inside frame layout
                //FrameLayout prev = (FrameLayout) rootView.findViewById(R.id.qr_frame);
                //mPreview = new CameraPreview(getActivity(), mCamera);
                //prev.addView(mPreview);
                return rootView;
            }
        	
        	
        }