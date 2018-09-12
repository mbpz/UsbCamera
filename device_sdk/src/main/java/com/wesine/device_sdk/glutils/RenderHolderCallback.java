package com.wesine.device_sdk.glutils;

import android.view.Surface;

/**
 * Created by wesine on 2018/9/12.
 */

public interface RenderHolderCallback {
    public void onCreate(Surface surface);
    public void onFrameAvailable();
    public void onDestroy();
}
