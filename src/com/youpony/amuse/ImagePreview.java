package com.youpony.amuse;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ImagePreview extends Activity {
	
	ImageView image;
	EditText comment;
	Button cancel, confirm;
	Bitmap photo;
	ImageDownloader imdown;
	Item oggetto;
	String url;
	File banana;
	
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
		
		Bundle extras = intent.getExtras();
        photo = (Bitmap) extras.get("IMMAGINE");
        
        image.setImageBitmap(photo);
        
        photo = BitmapFactory.decodeFile(intent.getDataString());
        
        
        
//        imdown = new ImageDownloader();
        
        
        
//        String pathName = Environment.getExternalStorageDirectory().getPath();
//        File path = new File(pathName);
//        if(path.exists()){
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            photo = BitmapFactory.decodeFile(pathName, options);
//            image.setImageBitmap(photo);
//        }
         
        
//       url = imdown.download(pathName,image);
		
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
//				Story.start = false;
////				Story.id_mostra = oggetto.e_id;
////				if(url != null){
//					PageViewer.values.add(oggetto);
////					
//					PageViewer.pinterestItems.add(url);
//					Story.pinterestAdapter.notifyDataSetChanged();
//
//					Story.send.setVisibility(View.VISIBLE);
//					Story.line.setVisibility(View.VISIBLE);
				close();
				
			}
		});
					
	}
	
	void close(){
		ImagePreview.this.finish();
		PageViewer.mViewPager.setCurrentItem(1);
	}

}

