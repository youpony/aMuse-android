package com.youpony.amuse;

import android.os.Bundle;
import com.youpony.amuse.ImageDownloader;
import android.app.Activity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ExhibitionChange extends Activity {
	TextView t1,t2,t3,t4,titolo1,titolo2,titolo3,titolo4, alert, title;
	Button confirm, cancel;
	Item oggetto;
	ImageDownloader downloader;
	ImageView imageView;
	String im;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exhibition_change);
		
		downloader = new ImageDownloader();
		im = new String();
		
		imageView = new ImageView(this);
		titolo1= new TextView(this);
		titolo2= new TextView(this);
		titolo3= new TextView(this);
		titolo4= new TextView(this);
		
		t1 = new TextView(this);
		t2 = new TextView(this);
		t3 = new TextView(this);
		t4 = new TextView(this);
		alert = new TextView(this);
		
		Object og = getIntent().getExtras().get("item_value");
		oggetto = (Item) og;
		
		alert = (TextView) findViewById(R.id.alertText);
		titolo1 = (TextView) findViewById(R.id.autoreTitle);
		t1 = (TextView) findViewById(R.id.autoreText);
		titolo2 = (TextView) findViewById(R.id.annoTitle);
		t2 = (TextView) findViewById(R.id.annoText);
		titolo3 = (TextView) findViewById(R.id.descTitle);
		t3 = (TextView) findViewById(R.id.descText);
		titolo4 = (TextView) findViewById(R.id.mostraTitle);
		t4 = (TextView) findViewById(R.id.mostraText);
		imageView = (ImageView) findViewById(R.id.imageView);
		title = (TextView) findViewById(R.id.title);
		
		//display exhibition change alert text
		alert.setText("Attenzione, questa opera non appartiene alla mostra salvata nei preferiti. " +
				"Premendo il pulsante conferma verrà resettata la lista e aggiunto questo oggetto ai preferiti.");
		
		//display object infos
		if(oggetto.author!="ignoto") {
			t1.setText(oggetto.author);
		} else {
			titolo1.setVisibility(2);
		}
		
		if(oggetto.year!="0") {
			t2.setText(oggetto.year);
		} else{
			titolo2.setVisibility(2);
		}
		
		
		t3.setText(oggetto.description);
		t4.setText(oggetto.mostra);
		
		//display title
		title.setText(oggetto.name);
		
		//add scroll listener on Text
		t3.setMovementMethod(new ScrollingMovementMethod());
		
		//display image
		if( oggetto.url != null){
			oggetto.url = downloader.download(oggetto.url, imageView);
		}
		else{
			Log.i("orrudebug", "non c'è l'immagine di questo oggetto");
		}
		//manage Confirm button action
		confirm = (Button) findViewById(R.id.confirm);
		confirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
					Story.start = false;
					Story.id_mostra = oggetto.e_id;
					PageViewer.values.clear();
					PageViewer.values.add(oggetto);
					PageViewer.pinterestItems.clear();
					Story.pinterestAdapter.clear();
					PageViewer.pinterestItems.add(im);
					Story.pinterestAdapter.notifyDataSetChanged();
//					Story.leftAdapter.clear();
//					Story.rightAdapter.clear();
//					PageViewer.leftItems.add(im);
//					Story.leftAdapter.notifyDataSetChanged();
//					Story.rightAdapter.notifyDataSetChanged();
					close();
			}
		});
		
		//manage Cancel button action
		cancel = (Button) findViewById(R.id.cancel);
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
		getMenuInflater().inflate(R.menu.exhibition_change, menu);
		return true;
	}

	void close(){
		this.finish();
		PageViewer.mViewPager.setCurrentItem(1);
	}
	

}
