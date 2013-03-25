package com.youpony.amuse;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
	ArrayList<String> values;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        //inflate the layout to fragment
		View sView = inflater.inflate(R.layout.activity_story, container, false);
		//set custom list view for fragment
		lv1 = (ListView) sView.findViewById(R.id.listView1);    
	    values = new ArrayList<String>();
	          
	    values.add("Android");
	    values.add("iPhone");
	    values.add("Windows");

 
        final ArrayAdapter<String> files = new ArrayAdapter<String>(getActivity(), 
                 android.R.layout.simple_list_item_1, 
                 values);

        lv1.setAdapter(files);
        
        lv1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        	
        	@Override
        	public boolean onItemLongClick(AdapterView<?> a, View v, int position, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(getActivity());
                
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + values.get(position));
                final int positionToRemove = position;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        values.remove(positionToRemove);
                        files.notifyDataSetChanged();
                    }});
                adb.show();
                
                return false;
                }
		
        });

        
        return sView;
    }

	
	
}