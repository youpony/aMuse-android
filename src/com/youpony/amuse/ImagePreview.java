package com.youpony.amuse;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class ImagePreview extends Activity {

	ImageView image;
	EditText comment;
	Button cancel, confirm;
	Bitmap photo;
	Item oggetto;
	String url;
    ImageDownloader downloader;


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


        url = (String) intent.getExtras().get("IMMAGINE");


        oggetto.type="FOTO";
        oggetto.bigPic=url;

        //ad oggetto.url dai l'indirizzo in cache con imageDownloader

        //BISOGNA SALVARE LA FOTO IN BASSA QUALITA' DA QUALCHE PARTE E LAVORARE CON QUELLA, QUANDO SI MANDA LA FOTO SI USA QUELLA NORMALE


        photo = PageViewer.decodeSampledBitmapFromFile(url,400,240);
        oggetto.url = (String) ImageDownloader.getCacheDirectory(image.getContext()).toString() + File.separator;

        String path = oggetto.url;
        OutputStream fOut = null;
        File file = new File(path, "image_" + CameraTab.imageNum +".jpg");
        try {
            fOut = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        photo.compress(Bitmap.CompressFormat.JPEG, 85, fOut);
//        fOut.flush();
//        fOut.close();

        try {
            MediaStore.Images.Media.insertImage(getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//                imagedodown di oggetto.url

        downloader = new ImageDownloader();
        if( oggetto.url != null){
//            FIX THIS
            oggetto.url = downloader.download(oggetto.url, image);
            Log.i("orrudebug", oggetto.url);
        }

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

                PageViewer.values.add(oggetto);
                PageViewer.pinterestItems.add(oggetto.url);
                Story.pinterestAdapter.notifyDataSetChanged();

                Story.send.setVisibility(View.VISIBLE);
                Story.tutorial.setVisibility(View.INVISIBLE);
				close();
				
			}
		});
					
	}
	
	void close(){
		ImagePreview.this.finish();
		PageViewer.mViewPager.setCurrentItem(1);
	}

}

