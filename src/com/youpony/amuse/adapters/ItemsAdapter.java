package com.youpony.amuse.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.youpony.amuse.R;

public class ItemsAdapter extends ArrayAdapter<String>{

    Context context; 
    LayoutInflater inflater;
    int layoutResourceId;
    float imageWidth;
    TextView title;
    
    public ItemsAdapter(Context context, int layoutResourceId, ArrayList<String> items) {
        super(context, layoutResourceId, items);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        
        float width = ((Activity)context).getWindowManager().getDefaultDisplay().getWidth();
        float margin = (int)convertDpToPixel(10f, (Activity)context);
        // two images, three margins of 10dips
		imageWidth = ((width - (3 * margin)) / 2);
    }

	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FrameLayout row = (FrameLayout) convertView;
        ItemHolder holder;
        String item = getItem(position);
        
		if (row == null) {
			holder = new ItemHolder();
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = (FrameLayout) inflater.inflate(layoutResourceId, parent, false);
            TextView title = (TextView)row.findViewById(R.id.item_title);
            title.setText("titolo");
            ImageView itemImage = (ImageView)row.findViewById(R.id.item_image);
			holder.itemImage = itemImage;
		} else {
			holder = (ItemHolder) row.getTag();
		}
		
		row.setTag(holder);
		setImageBitmap(item, holder.itemImage);
        return row;
    }

    public static class ItemHolder
    {
    	ImageView itemImage;
    }
	
    // resize the image proportionately so it fits the entire space
	private void setImageBitmap(String item, ImageView imageView){
        Bitmap bitmap = BitmapFactory.decodeFile(item);
		float i = ((float) imageWidth) / ((float) bitmap.getWidth());
		float imageHeight = i * (bitmap.getHeight());
		FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) imageView.getLayoutParams();
		params.height = (int) imageHeight;
		params.width = (int) imageWidth;
		imageView.setLayoutParams(params);
		imageView.setImageBitmap(bitmap);
	}
	
	public static float convertDpToPixel(float dp, Context context){
	    Resources resources = context.getResources();
	    DisplayMetrics metrics = resources.getDisplayMetrics();
	    float px = dp * (metrics.densityDpi/160f);
	    return px;
	}

}