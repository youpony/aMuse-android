package com.youpony.amuse;

import android.content.Intent;
import android.graphics.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/*
 * Fragment containing Camera.
 * Swipe left page.
 */

public class CameraTab extends Fragment {

	 	
    	public CameraTab(){	
    	}
    	
    	@Override
    	public View onCreateView(LayoutInflater inflater, ViewGroup container,
                 Bundle savedInstanceState) {
    		
			View view = inflater.inflate(R.layout.activity_camera, container, false);
			
			return view;
		}
    	
    	
    }