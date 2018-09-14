package com.doug.usbcamera;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.hardware.usb.UsbDevice;
import android.util.Log;
import android.view.Surface;

import com.serenegiant.usb.USBMonitor;
import com.serenegiant.usb.UVCCamera;
import com.wesine.device_sdk.usbcameracommon.UVCCameraHandler;
import com.wesine.device_sdk.widget.CameraViewInterface;

/**
 * Created by wesine on 2018/9/12.
 */

public class CameraUtil {

    private static final String TAG = "CameraUtil";

    private static final boolean DEBUG = false;    // FIXME set false when production
    private static final float[] BANDWIDTH_FACTORS = {0.5f, 0.5f};

    private Activity activity;
    private UVCCameraHandler cameraHandler;
    private CameraViewInterface cameraView;
    // for accessing USB and USB camera
    private USBMonitor mUSBMonitor;


    public CameraUtil(Activity activity) {
        this.activity = activity;
    }

    public void setCameraView(CameraViewInterface cameraView) {
        this.cameraView = cameraView;
    }

    public void initCamera() {
        if (mUSBMonitor == null) {
            mUSBMonitor = new USBMonitor(activity, mOnDeviceConnectListener);
            mUSBMonitor.register();
        }
        if (cameraView != null) {
            cameraView.setAspectRatio(UVCCamera.DEFAULT_PREVIEW_WIDTH / (float) UVCCamera.DEFAULT_PREVIEW_HEIGHT);
        }
        if (cameraHandler == null) {
            cameraHandler = UVCCameraHandler.createHandler(activity, cameraView, UVCCamera.DEFAULT_PREVIEW_WIDTH, UVCCamera.DEFAULT_PREVIEW_HEIGHT, BANDWIDTH_FACTORS[1]);
        }
    }

    public void onResume() {
        if (cameraView != null) {
            cameraView.onResume();
        }
    }

    public void onPause() {
        if (mUSBMonitor != null) {
            mUSBMonitor.unregister();
        }
        if (cameraView != null) {
            cameraView.onPause();
        }
        if (cameraHandler != null) {
            cameraHandler.close();
        }
    }

    public void onDestroy() {
        if (cameraHandler != null) {
            cameraHandler = null;
        }
        if (mUSBMonitor != null) {
            mUSBMonitor.destroy();
            mUSBMonitor = null;
        }
        cameraView = null;
    }

    private final USBMonitor.OnDeviceConnectListener mOnDeviceConnectListener = new USBMonitor.OnDeviceConnectListener() {
        @Override
        public void onAttach(final UsbDevice device) {
            if (DEBUG) Log.v(TAG, "onAttach:" + device);
        }

        @Override
        public void onConnect(final UsbDevice device, final USBMonitor.UsbControlBlock ctrlBlock, final boolean createNew) {
            if (DEBUG) Log.v(TAG, "onConnect:" + device);
            if (!cameraHandler.isOpened()) {
                cameraHandler.open(ctrlBlock);
                final SurfaceTexture st = cameraView.getSurfaceTexture();
                cameraHandler.startPreview(new Surface(st));
            }
        }

        @Override
        public void onDisconnect(final UsbDevice device, final USBMonitor.UsbControlBlock ctrlBlock) {
            if (DEBUG) Log.v(TAG, "onDisconnect:" + device);
            if ((cameraHandler != null) && !cameraHandler.isEqual(device)) {
//                queueEvent(new Runnable() {
//                    @Override
//                    public void run() {
//                        cameraHandler.close();
//                    }
//                }, 0);
            }
        }

        @Override
        public void onDettach(final UsbDevice device) {
            if (DEBUG) Log.v(TAG, "onDettach:" + device);
        }

        @Override
        public void onCancel(final UsbDevice device) {
            if (DEBUG) Log.v(TAG, "onCancel:");
        }
    };
}
