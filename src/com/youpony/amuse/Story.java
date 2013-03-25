package com.youpony.amuse;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/*
 * fragment containing Story.
 * default landing page for the app.
 */
public class Story extends Fragment {
	public Story(){
	}
	
	ListView lv1;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        //inflate the layout to fragment
		View sView = inflater.inflate(R.layout.activity_story, container, false);
		//set custom list view for fragment
		lv1 = (ListView) sView.findViewById(R.id.listView1);    

        String[] values = new String[] { "Android", "iPhone", "WindowsMobile",
             "Blackberry", "WebOS", "Ubuntu", "Windows7", "Max OS X",
        "Linux", "OS/2" };

        ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(), 
                 android.R.layout.simple_list_item_1, 
                 values);

         lv1.setAdapter(files);
		
        return sView;
    }
	
	
}