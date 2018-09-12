package com.wesine.device_sdk.glutils;

import android.graphics.SurfaceTexture;
import android.view.Surface;

/**
 * Created by wesine on 2018/9/12.
 */

public interface IRenderer extends IRendererCommon {

    /**
     * 関係するすべてのリソースを開放する。再利用できない
     */
    public void release();

    /**
     * 描画先のSurfaceをセット
     * @param surface
     */
    public void setSurface(final Surface surface);

    /**
     * 描画先のSurfaceをセット
     * @param surface
     */
    public void setSurface(final SurfaceTexture surface);

    /**
     * Surfaceサイズを変更
     * @param width
     * @param height
     */
    public void resize(final int width, final int height);

    /**
     * 描画要求
     * @param args
     */
    public void requestRender(final Object... args);
}
