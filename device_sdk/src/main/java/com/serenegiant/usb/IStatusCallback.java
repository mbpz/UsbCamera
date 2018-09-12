package com.serenegiant.usb;

import java.nio.ByteBuffer;

/**
 * Created by wesine on 2018/9/12.
 */

public interface IStatusCallback {
    void onStatus(int statusClass, int event, int selector, int statusAttribute, ByteBuffer data);
}
