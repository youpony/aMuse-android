package com.youpony.amuse;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/*
 * fragment containing Story.
 * default landing page for the app.
 */
public class Story extends Fragment {
	public Story(){
	}
	
	ListView lv1;
	public static ArrayAdapter<Item> files;
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        //inflate the layout to fragment
		View sView = inflater.inflate(R.layout.activity_story, container, false);
		//set custom list view for fragment
		lv1 = (ListView) sView.findViewById(R.id.listView1);    
	    
	    
	    //only for debugging purposes (and Orr� trolling too)
	    /*
	    Item os1 = new Item();
	    os1.name=("Android");
	    os1.description=("OSX di Google");
	    os1.author=("Google");
	    os1.year=("2004");
	    PageViewer.values.add(os1);
	    Item os2 = new Item();
	    os2.name=("iPhone");
	    values.add(os2);
	    Item os3 = new Item();
	    os3.name=("Windows Phone");
	    values.add(os3);
	    */
 
        files = new ArrayAdapter<Item>(getActivity(), 
                 android.R.layout.simple_list_item_1, 
                 PageViewer.values);

        lv1.setAdapter(files);
        
        lv1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
        	
        	@Override
        	public boolean onItemLongClick(AdapterView<?> a, View v, int position, long id) {
                AlertDialog.Builder adb=new AlertDialog.Builder(getActivity());
                
                adb.setTitle("Delete?");
                adb.setMessage("Are you sure you want to delete " + PageViewer.values.get(position));
                final int positionToRemove = position;
                adb.setNegativeButton("Cancel", null);
                adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        PageViewer.values.remove(positionToRemove);
                        files.notifyDataSetChanged();
                    }});
                adb.show();
                
                return false;
                }
		
        });

        lv1.setOnItemClickListener(new AdapterView.OnItemClickListener(){


			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent info = new Intent(getActivity() ,ItemInfo.class);
				info.putExtra("pos", arg2);
				startActivity(info);
				
			}
        	
        });
        
        return sView;
    }


	public static boolean findName(String name) {
		//Log.i("orrudebug", "size : " + PageViewer.values.size());
		for(int i=0; i<PageViewer.values.size(); i++){
			//Log.i("orrudebug", name + " " + PageViewer.values.get(i));
			if(PageViewer.values.get(i).name.equals(name)){
				//Log.i("orrudebug", "sto iterando, ho trovato");
				
				return true;
			}
		}
		return false;
	}
	public static int findPos(String name){
		for(int i=0; i<PageViewer.values.size(); i++){
			//Log.i("orrudebug", name + " " + PageViewer.values.get(i));
			if(PageViewer.values.get(i).name.equals(name)){
				return i;
			}
		}
		return 0;
	}
	
	
}