package com.youpony.amuse;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.*;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * 	CURRENTLY IMPLEMENTS FULLSCREEN SWIPE WITHOUT CLICKABLE TABS!
 *	if you want to switch back to clickable tabs remove the 
 *	comments! And don't forget to change styles back to Theme.Holo.Light!
 **/


public class PageViewer extends FragmentActivity /*implements ActionBar.TabListener*/ {
	
	public static File pacchetto;
	public CameraTab camera;
	public Story story;
	public QrCode qrcode;
	public static SharedPreferences saving;
	
	private static Context context;
	public final static String EXTRA_MESSAGE = "qrCode RESULT";
	
	public static ArrayList<String> pinterestItems = new ArrayList<String>();
	public static ArrayList<Item> values;
	Set<String> listaItem;
	Set<String> listaPic;

	/**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link android.support.v4.app.FragmentPagerAdapter} derivative, which
     * will keep every loaded fragment in memory. If this becomes too memory
     * intensive, it may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    public static ViewPager mViewPager;
    ExifInterface exif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_viewer);
        PageViewer.context = getApplicationContext();
        values = new ArrayList<Item>();
    	
        /*
        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		*/
        
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the app.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        // When swiping between different sections, select the corresponding
        // tab. We can also use ActionBar.Tab#select() to do this if we have
        // a reference to the Tab.
        /*mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                actionBar.setSelectedNavigationItem(position);
            }
        });
		*/
        // For each of the sections in the app, add a tab to the action bar.
        /*for (int i = 0; i < mSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by
            // the adapter. Also specify this Activity object, which implements
            // the TabListener interface, as the callback (listener) for when
            // this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
        */
        mViewPager.setCurrentItem(1);
        /*listaItem = new HashSet<String>();
        listaPic = new HashSet<String>();
        saving = getSharedPreferences("SAVING", Context.MODE_PRIVATE);
        saving.getStringSet("LISTA_PIC", listaPic);
        for(int i=0; i<listaPic.size(); i++){
        	Log.i("orrudebug", listaPic.toArray()[i].toString());
        }
        */
    }

    @Override
    public void onResume(){
    	super.onResume();
    	mViewPager.invalidate();
    	Log.d("orrudebug", "onResume on mViewPager");
    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.page_viewer, menu);
        return true;
    }
    /*
    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in
        // the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
        
        //if (tab.getPosition() == 0){
        	//camera.attachCamera();
        //}
        if(tab.getPosition() == 2){
        	QrCode.start_qr.setText(QrCode.BASE_TEXT);
        }
        Log.d("","Tab is selected - orrudebug");
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    	
    	//if (tab.getPosition() == 0){
    		//camera.detachCamera();
    	//}
    	Log.d("", "Tab is UNselected - orrudebug");
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    	Log.d("", "Tab is REselected - orrudebug");
    }
	*/
    
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // 
            if(position==0){
            	camera = new CameraTab();
            	return camera;
            	}
            else if(position==2){
            	qrcode = new QrCode();
            	return qrcode;
            }
            else if(position==1){
            	story = new Story();
            	return story;
            }
            else{
            	return null;
            }
            
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return getString(R.string.title_section1).toUpperCase(l);
                case 1:
                    return getString(R.string.title_section2).toUpperCase(l);
                case 2:
                    return getString(R.string.title_section3).toUpperCase(l);
            }
            return null;
        }
    }

    /**
     * A dummy fragment representing a section of the app, but that simply
     * displays dummy text.
     */
    public static class DummySectionFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        public static final String ARG_SECTION_NUMBER = "section_number";

        public DummySectionFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_page_viewer_dummy, container, false);
            TextView dummyTextView = (TextView) rootView.findViewById(R.id.section_label);
            dummyTextView.setText(Integer.toString(getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }
	
    public static Context getAppContext(){
    	return PageViewer.context;
    }
    
    //manage QR Code results
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
    	
    	super.onActivityResult(requestCode, resultCode, intent); 

    	IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

    	if (scanResult != null) {
	  		Intent qrResult = new Intent(this, QrResult.class);
	  		String resultString = scanResult.getContents();
	  		qrResult.putExtra(EXTRA_MESSAGE, resultString);
	  		startActivity(qrResult);
	  	  }
  	  	else {
            //Butta fuori dalla camera se premi X
            if (resultCode != RESULT_OK) return;

            //Check that request code matches ours:
  	        if (requestCode == 100){

                pacchetto = new File(Environment.getExternalStorageDirectory()+File.separator + "aMuse" + File.separator+ "image_" + CameraTab.imageNum +".jpg");
                Intent imageResult = new Intent(this, ImagePreview.class);
                imageResult.putExtra("IMMAGINE", pacchetto.getAbsolutePath());
                startActivity(imageResult);

  	        }
  	      }
  	  	}


    public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight) {
    	
    	// BEST QUALITY MATCH
        
    	 //First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
     
        // Calculate inSampleSize, Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        int inSampleSize = 1;
     
        if (height > reqHeight) 
        {
            inSampleSize = Math.round((float)height / (float)reqHeight);
        }
        int expectedWidth = width / inSampleSize;
     
        if (expectedWidth > reqWidth) 
        {
            //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
            inSampleSize = Math.round((float)width / (float)reqWidth);
        }
     
        options.inSampleSize = inSampleSize;
     
        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
     
        return BitmapFactory.decodeFile(path, options);
	}

	@Override
    protected void onStop(){
    	super.onStop();
    }
    
  //Fires after the OnStop() state
    @Override
    protected void onDestroy() {
       super.onDestroy();
       if(PageViewer.values.size() == 0){
	       try {
	          trimCache(this);
	       } catch (Exception e) {
	          e.printStackTrace();
	       }
	       Log.i("orrudebug", "clear cache");
       }
       else{
//    	   salvati la roba
    	   
    	   /*SharedPreferences.Editor editsaving = saving.edit();
    	   for(int i=0; i<PageViewer.values.size(); i++){
    		   listaItem.add(PageViewer.values.get(i).id);
    	   }
    	   for(int i=0; i<PageViewer.pinterestItems.size(); i++){
    		   listaPic.add(PageViewer.pinterestItems.get(i));
    	   }
    	   editsaving.putStringSet("LISTA_ITEM",listaItem);
    	   editsaving.putStringSet("LISTA_PIC", listaPic);
    	   */
    	   
       }
    }

    public static void trimCache(Context context) {
       try {
          File dir = android.os.Environment.getExternalStorageDirectory(); 
          File cacheDir = new File(dir,"data/amuse/images");
          if (cacheDir != null && cacheDir.isDirectory()) {
             deleteDir(cacheDir);
          }
       } catch (Exception e) {
       }
    }

    public static boolean deleteDir(File dir) {
       if (dir != null && dir.isDirectory()) {
          String[] children = dir.list();
          for (int i = 0; i < children.length; i++) {
             boolean success = deleteDir(new File(dir, children[i]));
             if (!success) {
                return false;
             }
          }
       }

       // The directory is now empty so delete it
       return dir.delete();
    }
    
}