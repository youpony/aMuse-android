package com.youpony.amuse;

import android.os.Bundle;
import android.app.Activity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemInfo extends Activity {

	TextView t;
	TextView title;
	ImageView v;
	int pos;
	Item item;
	ImageDownloader downloader;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qr_result);
		Bundle b = getIntent().getExtras();
		pos = b.getInt("pos");
		item = new Item();
		t = new TextView(this);
		v = new ImageView(this);
		try{
			item = PageViewer.values.get(pos);
		}
		catch (Exception e){
			Log.i("orrudebug", "can't find object with position " + pos);
		}
		
		t = (TextView) findViewById(R.id.JSONResult);
		//add scroll listener on Text
		t.setMovementMethod(new ScrollingMovementMethod());
		v = (ImageView) findViewById(R.id.imageView);
		//set text
		t.setText("autore: " + item.author + "\n" + "anno: " + item.year + "\n" + "descrizione: " + item.description);
		title = (TextView) findViewById(R.id.title);
		//set item title
		title.setText(item.name);
		
		downloader = new ImageDownloader();
		if( item.url != null){
			item.url = downloader.download(item.url, v);
		}
		
		//manage Delete button action
		Button delete = (Button) findViewById(R.id.confirm);
		delete.setText("Delete");
		delete.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				
				PageViewer.values.remove(pos);
				PageViewer.pinterestItems.remove(pos);
				Story.pinterestAdapter.notifyDataSetChanged();
				if(PageViewer.values.size() == 0){
					Log.i("orrudebug", "cancellata la lista");
				}
				close();
			}
		});
		
		//manage Cancel button action
		Button cancel = (Button) findViewById(R.id.cancel);
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				close();
				
			}
		});
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.item_info, menu);
		return true;
	}
	
	void close(){
		ItemInfo.this.finish();
		PageViewer.mViewPager.setCurrentItem(1);
	}
	

}
