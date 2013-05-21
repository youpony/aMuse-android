package com.youpony.amuse;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

public class FotoInfo extends Activity {

    TextView title;
    ImageView v;
    int pos;
    Item item;
    ImageDownloader downloader;
    Bitmap bitmap;

    //TODO SALVARE IL COMMENTO

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto_info);
        Bundle b = getIntent().getExtras();
        pos = b.getInt("pos");
        Log.d("orrudebug","pos "+ pos);
        item = new Item();

        v = new ImageView(this);
        try{
            item = PageViewer.values.get(pos);
        }
        catch (Exception e){
            Log.i("orrudebug", "can't find object with position " + pos);
        }
        v = (ImageView) findViewById(R.id.imageprev);

        title = (TextView) findViewById(R.id.title);

        Log.d("orrudebug","funziona l'url? " +item.url);

        bitmap = PageViewer.decodeSampledBitmapFromFile(item.url,400,280);

        v.setImageBitmap(bitmap);


//        downloader = new ImageDownloader();
//        if( item.url != null){
//            downloader.download(item.url, v);
//            Log.i("orrudebug", item.url);
//        }



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
                    Story.start = true;
                    Story.send.setVisibility(View.INVISIBLE);
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
        this.finish();
        PageViewer.mViewPager.setCurrentItem(1);
    }


}
