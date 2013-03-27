package com.youpony.amuse;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ExhibitionChange extends Activity {
	TextView t, alert;
	Button confirm, cancel;
	Item oggetto;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_exhibition_change);
		
		Object og = getIntent().getExtras().get("item_value");
		oggetto = (Item) og;
		
		alert = (TextView) findViewById(R.id.alertText);
		t = (TextView) findViewById(R.id.JSONResult);
		
		//display exhibition change alert text
		alert.setText("Attenzione, questa opera non appartiene alla mostra salvata nei preferiti. " +
				"Premendo il pulsante conferma verrˆ resettata la lista e aggiunto questo oggetto ai preferiti.");
		
		//display object infos
		t.setText("autore: " + oggetto.author + 
				"\n"+ "nome: " + oggetto.name + 
				"\n" + "anno: " + oggetto.year + 
				"\n" + "descrizione: " + oggetto.description +
				"\n" + "mostra: " + oggetto.mostra);
		
		//manage Confirm button action
		confirm = (Button) findViewById(R.id.confirm);
		confirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
					Story.start = false;
					Story.id_mostra = oggetto.e_id;
					PageViewer.values.clear();
					PageViewer.values.add(oggetto);
					Story.files.notifyDataSetChanged();
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
