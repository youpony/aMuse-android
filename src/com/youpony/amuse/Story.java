package com.youpony.amuse;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.youpony.amuse.adapters.ItemsAdapter;

/*
 * fragment containing Story.
 * default landing page for the app.
 */
public class Story extends Fragment {
	
	
	private ListView listViewLeft;
	private ListView listViewRight;
	
	//call Story.<leftorright>Adapter.notifyDataChanged() from outer classes when item list is modified
	public static ItemsAdapter leftAdapter;
	public static ItemsAdapter rightAdapter;

	int[] leftViewsHeights;
	int[] rightViewsHeights;
	
	//DA SISTEMARE INSERIMENTO OGGETTI IN LISTA
	ListView lv1;
	//public static ArrayAdapter<Item> files;
	public static int id_mostra;
	public static boolean start = true;
	int current = 0;
	int removable;
	
	public static String[] debugArray;
	Button send;
	

	//ItemsActivity itemPreview;
	
	public Story(){
		
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {	

		
		debugArray = new String[]{"0&11"};//,"0&10","0&20","0&27","0&14","0&13","0&26","0&28","0&29","0&17","0&15","0&16","0&18","0&9"};
		 
		for(int i=0; i<debugArray.length; i++){
			Intent qrResult = new Intent(PageViewer.getAppContext(), QrResult.class);
	  		String resultString = debugArray[i];
	  		qrResult.putExtra(PageViewer.EXTRA_MESSAGE, resultString);
	  		startActivity(qrResult);
		}
		
		
		View sView = inflater.inflate(R.layout.activity_story, container, false);
		listViewLeft = (ListView) sView.findViewById(R.id.list_view_left);
		listViewRight = (ListView) sView.findViewById(R.id.list_view_right);
		
		send = (Button) sView.findViewById(R.id.send_button);
		send.setText("Send story");
		
		send.setOnClickListener(sendListener);
		
		
		loadItems();
		
		//listViewLeft.setOnTouchListener(touchListener);
		//listViewRight.setOnTouchListener(touchListener);		
		listViewLeft.setOnScrollListener(scrollListener);
		listViewRight.setOnScrollListener(scrollListener);
		listViewLeft.setOnItemLongClickListener(longListener);
		listViewRight.setOnItemLongClickListener(longListener);
		listViewLeft.setOnItemClickListener(clickListener);
		listViewRight.setOnItemClickListener(clickListener);
		
		return sView;
	}
	
	//on click listener (accessing object infos)
	
	OnItemClickListener clickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> whichList, View arg1, int position,
				long id) {
			if( whichList.equals(listViewLeft)){
				Intent info = new Intent(PageViewer.getAppContext(), ItemInfo.class);
				info.putExtra("pos", (position*2));
				startActivity(info);
			}
			else{
				Intent info = new Intent(PageViewer.getAppContext(), ItemInfo.class);
				info.putExtra("pos", ((position*2)+1));
				startActivity(info);
			}
		}

		
	};
	
	OnClickListener sendListener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			Intent sendStory = new Intent(PageViewer.getAppContext(), SendStory.class);
			startActivity(sendStory);
		}
	
	};
	
	
	//Listener for deleting object from list
	
	OnItemLongClickListener longListener = new OnItemLongClickListener() {
		
		@Override
    	public boolean onItemLongClick(AdapterView<?> a, View v, int position, long id) {
            
			removable = position;
			
			AlertDialog.Builder adb=new AlertDialog.Builder(getActivity());
            adb.setTitle("Delete?");
            
            if( a.equals(listViewLeft)){
            	current = 0;
            }
            else if( a.equals(listViewRight)){
            	current = 1;
            }
            
            adb.setMessage("Are you sure you want to delete " + position);
            
            adb.setNegativeButton("Cancel", null);
            adb.setPositiveButton("Ok", new AlertDialog.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                	//DA SISTEMARE ELIMINAZIONE, LAYOUT PRONTO ALL'IMPLEMENTAZIONE
                	if( current == 0){
                		PageViewer.values.remove(removable * 2);
                		PageViewer.leftItems.remove(removable);
                		leftAdapter.notifyDataSetChanged();
                		leftViewsHeights = null;
                		leftViewsHeights = new int[PageViewer.leftItems.size()];
                		Log.i("orrudebug", "size della lista sinistra: " + leftViewsHeights.length);
                		
                		
                	}
                	else if( current == 1){
                		PageViewer.values.remove((removable * 2) + 1);
                		PageViewer.rightItems.remove(removable);
                		rightAdapter.notifyDataSetChanged();
                		rightViewsHeights = null;
                		rightViewsHeights = new int[PageViewer.rightItems.size()];
                		Log.i("orrudebug", "size della lista destra: " + rightViewsHeights.length);
                		
                	}
                	Log.i("orrudebug", "cancellato l'oggetto " + removable);
                }});
                
            adb.show();
            
			return false;
		}
	};    
	
	// Not passing the touch event to the opposite list
	/*
	 * 
	 * 
	OnTouchListener touchListener = new OnTouchListener() {					
		boolean dispatched = false;
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			
			if (v.equals(listViewLeft) && !dispatched) {
				dispatched = true;
				listViewRight.dispatchTouchEvent(event); //chiama l'evento onTouch anche sull'oggetto a sinistra/destra
			} else if (v.equals(listViewRight) && !dispatched) {
				dispatched = true;
				listViewLeft.dispatchTouchEvent(event);
			}
			
			dispatched = false;
			return false;
		}
	};
	*/
	
	/**
	 * Synchronizing scrolling 
	 * Distance from the top of the first visible element opposite list:
	 * sum_heights(opposite invisible screens) - sum_heights(invisible screens) + distance from top of the first visible child
	 */
	OnScrollListener scrollListener = new OnScrollListener() {
		int index, top;
		
		@Override
		public void onScrollStateChanged(AbsListView v, int scrollState) {	
		}
		
		@Override
		public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
			/*
			if(view.equals(listViewLeft)){
				Log.i("orrudebug", "scroll a sx");
				saveListScrollPosition(view);
				listViewRight.setSelectionFromTop(index, top);
			}
			else if(view.equals(listViewRight)){
				Log.i("orrudebug", "scroll a dx");
				saveListScrollPosition(view);
				listViewLeft.setSelectionFromTop(index, top);
			}
			*/
			
			//MISSING SYNCRONIZED SCROLL
			 /*if (view.getChildAt(0) != null) {
			 
				if (view.equals(listViewLeft) ){
					leftViewsHeights[view.getFirstVisiblePosition()] = view.getChildAt(0).getHeight();
					
					int h = 0;
					for (int i = 0; i < listViewRight.getFirstVisiblePosition(); i++) {
						h += rightViewsHeights[i];
						Log.i("orrudebug", "1) list height right " + h);
					}
					
					int hi = 0;
					for (int i = 0; i < listViewLeft.getFirstVisiblePosition(); i++) {
						hi += leftViewsHeights[i];
						Log.i("orrudebug", "1) list height left" + h);
					}
					
					int top = h - hi + view.getChildAt(0).getTop();
					listViewRight.setSelectionFromTop(listViewRight.getFirstVisiblePosition(), top);
				} else if (view.equals(listViewRight)) {
					rightViewsHeights[view.getFirstVisiblePosition()] = view.getChildAt(0).getHeight();
					
					int h = 0;
					for (int i = 0; i < listViewLeft.getFirstVisiblePosition(); i++) {
						h += leftViewsHeights[i];
						Log.i("orrudebug", "2) list height left" + h);
					}
					
					int hi = 0;
					for (int i = 0; i < listViewRight.getFirstVisiblePosition(); i++) {
						hi += rightViewsHeights[i];
						Log.i("orrudebug", "2) list height right " + h);
					}
					
					int top = h - hi + view.getChildAt(0).getTop();
					listViewLeft.setSelectionFromTop(listViewLeft.getFirstVisiblePosition(), top);
				}
				
			}*/
		}
		
		private void saveListScrollPosition(AbsListView _listview1)
	    {
	    // save index and top position
	    index = _listview1.getFirstVisiblePosition();
	    View view = _listview1.getChildAt(0);
	    top = (view == null) ? 0 : view.getTop();
	    }
	};
	
	private void loadItems(){
		
		
		leftAdapter = new ItemsAdapter(this.getActivity(), R.layout.item, PageViewer.leftItems);
		rightAdapter = new ItemsAdapter(this.getActivity(), R.layout.item, PageViewer.rightItems);
		listViewLeft.setAdapter(leftAdapter);
		listViewRight.setAdapter(rightAdapter);
		
		leftViewsHeights = new int[PageViewer.leftItems.size()];
		rightViewsHeights = new int[PageViewer.rightItems.size()];	
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