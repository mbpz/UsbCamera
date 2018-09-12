package com.wesine.device_sdk.util;

import android.annotation.SuppressLint;
import android.os.SystemClock;

/**
 * Created by wesine on 2018/9/12.
 */

public class Time {
    public static boolean prohibitElapsedRealtimeNanos = true;

    private static Time sTime;

    static {
        reset();
    }

    public static long nanoTime() {
        return sTime.timeNs();
    }

    public static void reset() {
        if (!prohibitElapsedRealtimeNanos && BuildCheck.isJellyBeanMr1()) {
            sTime = new TimeJellyBeanMr1();
        } else {
            sTime = new Time();
        }
    }

    private Time() {
    }

    @SuppressLint("NewApi")
    private static class TimeJellyBeanMr1 extends Time {
        public long timeNs() {
            return SystemClock.elapsedRealtimeNanos();
        }
    }

    protected long timeNs() {
        return System.nanoTime();
    }
}
