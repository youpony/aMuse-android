package com.youpony.amuse;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.Point;
import android.media.ExifInterface;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.*;

public class ImagePreview extends Activity {

	ImageView image;
	EditText comment;
	Button cancel, confirm;
	Bitmap photo,rotated;
	Item oggetto;
	String url, stringCommento;
    ImageDownloader downloader;
    ExifInterface exif;


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

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;



        photo = PageViewer.decodeSampledBitmapFromFile(url,width,height);
        oggetto.url = (String) ImageDownloader.getCacheDirectory(image.getContext()).toString() + File.separator + "image_" + CameraTab.imageNum +".jpg";

        String path = oggetto.url;
        OutputStream fOut = null;
        File file = new File(path);
        try {
            fOut = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        photo.compress(Bitmap.CompressFormat.JPEG, 70, fOut);

        try {
            MediaStore.Images.Media.insertImage(getContentResolver(),file.getAbsolutePath(),file.getName(),file.getName());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        downloader = new ImageDownloader();
        if( oggetto.url != null){
            downloader.download(oggetto.url, image);
        }

        //TODO ROTATE PHOTO HERE

        Matrix matrix = new Matrix();

        File imageFile = new File(oggetto.url);

        ExifInterface exif = null;
		try {
			exif = new ExifInterface(imageFile.getAbsolutePath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int rotation = (int)exifOrientationToDegrees(
                exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_NORMAL));
		
		matrix.preRotate(rotation);
		Log.i("orrudebug", "orientato: " + rotation);
		
		Bitmap resizedBitmap = Bitmap.createBitmap(
		     photo, 0, 0, photo.getWidth(), photo.getHeight(), matrix, true);

        image.setImageBitmap(resizedBitmap);


        Log.d("orrudebug", "commento asd "+ oggetto.itemCommento);


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

                if (comment.getText().toString().length() != 0) {
                    oggetto.itemCommento=String.valueOf(comment.getText());
                }

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
	
	private static float exifOrientationToDegrees(int exifOrientation) {
        if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
            return 90;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
            return 180;
        } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
            return 270;
        }
        return 0;
    }

}

//TODO SEND PHOTO WITH STORY