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
        
        /*
        
        lv1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        	@Override
        	public boolean onItemLongClick(AdapterView<?> av, View v, int pos, long id) {
        	        return onLongListItemClick(v,pos,id);
        	}
        	protected boolean onLongListItemClick(View v, final int pos, long id) {
        	        String str=files.getItem(pos).toString();
        	        Log.i("ListView", "onLongListItemClick stirng=" + str);
        	        AlertDialog.Builder builder = new  
        	                AlertDialog.Builder(EmailReceiversActivity.this);
        	            builder.setMessage("Are you sure you want to delete?")
        	            .setCancelable(false)
        	            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        	                       public void onClick(DialogInterface dialog, int id) {
        	                    	   values.remove(pos);
        	                    	   files.notifyDataSetChanged();
        	                       }
        	                   })
        	                   .setNegativeButton("No", new DialogInterface.OnClickListener() {
        	                       public void onClick(DialogInterface dialog, int id) {
        	                            dialog.cancel();
        	                       }
        	                   });
        	            AlertDialog alert = builder.create();
        	            alert.show();
        	            return true;
        	        }

        	    }); */

        /*
        lv1.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<String> a, View v, int position, long id) {
                AlertDialog.Builder adb=new AlertDialog;
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + position);
                final int positionToRemove = position;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        MyDataObject.remove(positionToRemove);
                        files.notifyDataSetChanged();
                    }});
                adb.show();
                } 
        });
        */
        
        
        
        lv1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        	
        	@Override
        	public boolean onItemLongClick(AdapterView<?> a, View v, int position, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(getActivity());
                
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + position);
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