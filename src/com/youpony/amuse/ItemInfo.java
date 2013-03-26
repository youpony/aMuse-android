package com.youpony.amuse;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ItemInfo extends Activity {

	TextView t;
	int pos;
	Item item;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item_info);
		Bundle b = getIntent().getExtras();
		pos = b.getInt("pos");
		item = new Item();
		item = Story.files.getItem(pos);
		
		t = (TextView) findViewById(R.id.Iteminfo);
		t.setText("autore: " + item.author + "\n"+ "nome: " + item.name + "\n" + "anno: " + item.year + "\n" + "descrizione: " + item.description);
		
		//manage Delete button action
		Button delete = (Button) findViewById(R.id.delete);
		delete.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				
				Story.files.remove(Story.files.getItem(pos));
				Story.files.notifyDataSetChanged();
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
