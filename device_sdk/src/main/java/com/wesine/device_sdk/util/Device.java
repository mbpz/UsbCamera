package com.wesine.device_sdk.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;

import leo.wesine.wesinecpyrtkey.CpyrtUtil;

/**
 * Created by wesine on 2018/9/12.
 */

public class Device {
    @SuppressLint("StaticFieldLeak")
    private static Application sApplication;

    static WeakReference<Activity> sTopActivityWeakRef;
    static List<Activity> sActivityList = new LinkedList<>();

    private static Application.ActivityLifecycleCallbacks mCallbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            sActivityList.add(activity);
            setTopActivityWeakRef(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {
            setTopActivityWeakRef(activity);
        }

        @Override
        public void onActivityResumed(Activity activity) {
            setTopActivityWeakRef(activity);
        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            sActivityList.remove(activity);
        }
    };

    private Device() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * Init Device.
     * <p>Init it in the class of Application.</p>
     *
     * @param context context
     */
    public static void init(final Context context) {
        if (!isWesineDevice()) {
            throw new UnsupportedOperationException("The device is illegal...");
        }
        Device.sApplication = ((Application) context.getApplicationContext());
        Device.sApplication.registerActivityLifecycleCallbacks(mCallbacks);
    }

    /**
     * Return the context of Application object.
     *
     * @return the context of Application object
     */
    public static Application getApp() {
        if (sApplication != null) return sApplication;
        throw new NullPointerException("u should init first");
    }

    private static void setTopActivityWeakRef(final Activity activity) {
//        if (activity.getClass() == PermissionUtils.PermissionActivity.class) return;
        if (sTopActivityWeakRef == null || !activity.equals(sTopActivityWeakRef.get())) {
            sTopActivityWeakRef = new WeakReference<>(activity);
        }
    }

    private static boolean isWesineDevice() throws RuntimeException {
        try {
            String serial = Build.SERIAL;
            String serialno = serial.replace(" ", "").toUpperCase();
            if (serialno.length() > 10) {
                serialno = serialno.substring(0, 10);
            }
            Log.d("serial", serialno);
            return CpyrtUtil.getCpyrtUtil().validateSerial(serialno, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
