package com.youpony.amuse;

import java.io.File;

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
	ImageDownloader imdown;
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
//		
//		Uri imageUri = intent.getData();
//		
//		Log.d("orrudebug", imageUri.toString());
//        try {
//			photo = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		photo=(Bitmap) intent.getExtras().get("IMMAGINE");



        url = PageViewer.pacchetto.getAbsolutePath();
        Log.d("orrudebug", "percorso bam giù nel canestro " + url);

        //ABBIAMO IL PERCORSO DELL'IMMAGINE, ORA LO BUTTIAMO NEL DOWNLOADER

//		
//		Bundle extras = intent.getExtras();
//		Uri imageUri = (Uri) extras.get("URIFOTO2");
		
//        photo = (Bitmap) extras.get("IMMAGINE");
//        
        image.setImageBitmap(photo);

       //Log.d("orrudebug", url);

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

