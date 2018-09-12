package com.wesine.device_sdk.widget;

import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.view.Surface;

import com.wesine.device_sdk.encoder.IVideoEncoder;

/**
 * Created by wesine on 2018/9/12.
 */

public interface CameraViewInterface extends IAspectRatioView {
    public interface Callback {
        public void onSurfaceCreated(CameraViewInterface view, Surface surface);

        public void onSurfaceChanged(CameraViewInterface view, Surface surface, int width, int height);

        public void onSurfaceDestroy(CameraViewInterface view, Surface surface);
    }

    public void onPause();

    public void onResume();

    public void setCallback(Callback callback);

    public SurfaceTexture getSurfaceTexture();

    public Surface getSurface();

    public boolean hasSurface();

    public void setVideoEncoder(final IVideoEncoder encoder);

    public Bitmap captureStillImage();
}
