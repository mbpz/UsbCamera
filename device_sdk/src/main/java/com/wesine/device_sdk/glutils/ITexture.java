package com.wesine.device_sdk.glutils;

import android.graphics.Bitmap;

import java.io.IOException;

/**
 * Created by wesine on 2018/9/12.
 */

public interface ITexture {
    void release();

    void bind();
    void unbind();

    int getTexTarget();
    int getTexture();

    float[] getTexMatrix();
    void getTexMatrix(float[] matrix, int offset);

    int getTexWidth();
    int getTexHeight();

    void loadTexture(String filePath) throws NullPointerException, IOException;
    void loadTexture(Bitmap bitmap) throws NullPointerException;
}
