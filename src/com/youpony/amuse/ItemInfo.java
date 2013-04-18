package com.youpony.amuse;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemInfo extends Activity {

	TextView t;
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
		v = (ImageView) findViewById(R.id.imageView);
		t.setText("autore: " + item.author + "\n"+ "nome: " + item.name + "\n" + "anno: " + item.year + "\n" + "descrizione: " + item.description);
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
				if(pos%2 == 0){
					PageViewer.leftItems.remove(pos/2);
					Story.leftAdapter.notifyDataSetChanged();
				}
				else{
					PageViewer.rightItems.remove((pos-1)/2);
					Story.rightAdapter.notifyDataSetChanged();
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
