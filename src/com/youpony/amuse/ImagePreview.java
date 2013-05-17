package com.youpony.amuse;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class ImagePreview extends Activity {

	ImageView image;
	EditText comment;
	Button cancel, confirm;
	Bitmap photo;
	Item oggetto;
	String url;

	@Override
	protected void onCreate(Bundle savedInstanceState){
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_image);
		
		
		oggetto=new Item();
		image= new ImageView(this);
		comment=new EditText(this);
		
		image = (ImageView) findViewById(R.id.imageView);
		comment = (EditText) findViewById(R.id.comment);
		

		Intent intent = getIntent();

		photo=(Bitmap) intent.getExtras().get("IMMAGINE");

        url = PageViewer.pacchetto.getAbsolutePath();
        Log.d("orrudebug", "percorso bam giù nel canestro " + url);

        //ABBIAMO IL PERCORSO DELL'IMMAGINE, ORA LO DOBBIAMO BUTTARLO NEL DOWNLOADER


        image.setImageBitmap(photo);

        //manage Cancel button action
		cancel = (Button) findViewById(R.id.cancel);
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				close();
				
			}
		});
				
		//manage Confirm button action
		confirm = (Button) findViewById(R.id.confirm);
		confirm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				close();
				
			}
		});
					
	}
	
	void close(){
		ImagePreview.this.finish();
		PageViewer.mViewPager.setCurrentItem(1);
	}

}

