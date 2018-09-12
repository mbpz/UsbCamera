package com.wesine.device_sdk.glutils;

/**
 * Created by wesine on 2018/9/12.
 */

public interface IDrawer2dES2 extends IDrawer2D {
    public int glGetAttribLocation(final String name);
    public int glGetUniformLocation(final String name);
    public void glUseProgram();
}
