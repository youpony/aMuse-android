package com.youpony.amuse;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.hardware.Camera;
import android.os.Bundle;

/*
 * Fragment containing QrCode reader.
 * Swipe right page.
 */

public class QrCode extends Fragment {
	
        public QrCode(){
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
                View rootView = inflater.inflate(R.layout.activity_qr_code, container, false);
                return rootView;
            }
        	
        	
        }