package com.youpony.amuse;


import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.hardware.Camera;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
	
    private SurfaceHolder mHolder;
    private Camera mCameraPrev;
    
    public CameraPreview(Context context, Camera camera) {
        super(context);
        Log.d("cameraPreview", "is create - orrudebug");
        mCameraPrev = camera;
        mCameraPrev = getCameraInstance();
        // Install a SurfaceHolder.Callback so we get notified when the
        // underlying surface is created and destroyed.
        mHolder = getHolder();
        mHolder.addCallback(this);
    }
    
    public void surfaceCreated(SurfaceHolder holder) {
        Log.d("SurfaceView", "is created - orrudebug");
        // The Surface has been created, now tell the camera where to draw the preview.
        try {
            mCameraPrev.setPreviewDisplay(holder);
            mCameraPrev.startPreview();
        } catch (IOException e) {
            Log.d("TAG", "Error setting camera preview: " + e.getMessage());
            
        }
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
    	mCameraPrev.stopPreview();
        mCameraPrev.setPreviewCallback(null); 
        mCameraPrev.release();
        mCameraPrev = null;
        Log.d("surfaceView", "isDestroyed - orrudebug");
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int w, int h) {
        // If your preview can change or rotate, take care of those events here.
        // Make sure to stop the preview before resizing or reformatting it.

        if (mHolder.getSurface() == null){
          // preview surface does not exist
          return;
        }

        // stop preview before making changes
        try {
            mCameraPrev.stopPreview();
        } catch (Exception e){
          // ignore: tried to stop a non-existent preview
        }

        // set preview size and make any resize, rotate or
        // reformatting changes here

        // start preview with new settings
        try {
            mCameraPrev.setPreviewDisplay(mHolder);
            mCameraPrev.startPreview();

        } catch (Exception e){
            Log.d("TAG", "Error starting camera preview: " + e.getMessage());
        }
    }
    
    public static Camera getCameraInstance(){
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        }
        catch (Exception e){
            // Camera is not available (in use or does not exist)
        }
        return c; // returns null if camera is unavailable
    }
}