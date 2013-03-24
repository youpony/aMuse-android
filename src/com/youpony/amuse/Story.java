package com.youpony.amuse;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/*
 * fragment containing Story.
 * default landing page for the app.
 */
public class Story extends Fragment {
	public Story(){
	}

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        //inflate the layout to fragment
		View sView = inflater.inflate(R.layout.activity_story, container, false);
		//set custom text view for fragment
		TextView storyTextView = (TextView) sView.findViewById(R.id.section_label);
        storyTextView.setText("Questa è la tab dove comparirà la Story.");
        
        return sView;
    }
	
	
}