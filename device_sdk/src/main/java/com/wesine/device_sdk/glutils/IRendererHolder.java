package com.wesine.device_sdk.glutils;

import android.graphics.SurfaceTexture;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Surface;

/**
 * Created by wesine on 2018/9/12.
 */

public interface IRendererHolder extends IRendererCommon {
    /**
     * 実行中かどうか
     * @return
     */
    public boolean isRunning();
    /**
     * 関係するすべてのリソースを開放する。再利用できない
     */
    public void release();

    @Nullable
    public EGLBase.IContext getContext();

    /**
     * マスター用の映像を受け取るためのSurfaceを取得
     * @return
     */
    public Surface getSurface();

    /**
     * マスター用の映像を受け取るためのSurfaceTextureを取得
     * @return
     */
    public SurfaceTexture getSurfaceTexture();

    /**
     * マスター用の映像を受け取るためのマスターをチェックして無効なら再生成要求する
     */
    public void reset();

    /**
     * マスター映像サイズをサイズ変更要求
     * @param width
     * @param height
     */
    public void resize(final int width, final int height)
            throws IllegalStateException;

    /**
     * 分配描画用のSurfaceを追加
     * このメソッドは指定したSurfaceが追加されるか
     * interruptされるまでカレントスレッドをブロックする。
     * @param id 普通は#hashCodeを使う
     * @param surface, should be one of Surface, SurfaceTexture or SurfaceHolder
     * @param isRecordable
     */
    public void addSurface(final int id, final Object surface,
                           final boolean isRecordable)
            throws IllegalStateException, IllegalArgumentException;

    /**
     * 分配描画用のSurfaceを追加
     * このメソッドは指定したSurfaceが追加されるか
     * interruptされるまでカレントスレッドをブロックする。
     * @param id 普通は#hashCodeを使う
     * @param surface, should be one of Surface, SurfaceTexture or SurfaceHolder
     * @param isRecordable
     * @param maxFps 0以下なら制限しない
     */
    public void addSurface(final int id, final Object surface,
                           final boolean isRecordable, final int maxFps)
            throws IllegalStateException, IllegalArgumentException;

    /**
     * 分配描画用のSurfaceを削除
     * このメソッドは指定したSurfaceが削除されるか
     * interruptされるまでカレントスレッドをブロックする。
     * @param id
     */
    public void removeSurface(final int id);

    /**
     * 分配描画用のSurfaceを全て削除
     * このメソッドはSurfaceが削除されるか
     * interruptされるまでカレントスレッドをブロックする。
     */
    public void removeSurfaceAll();

    /**
     * 分配描画用のSurfaceを指定した色で塗りつぶす
     * @param id
     * @param color
     */
    public void clearSurface(final int id, final int color);

    /**
     * 分配描画用のSurfaceを指定した色で塗りつぶす
     * @param color
     */
    public void clearSurfaceAll(final int color);

    /**
     * モデルビュー変換行列をセット
     * @param id
     * @param offset
     * @param matrix offset以降に16要素以上
     */
    public void setMvpMatrix(final int id,
                             final int offset, @NonNull final float[] matrix);

    /**
     * 分配描画用のSurfaceへの描画が有効かどうかを取得
     * @param id
     * @return
     */
    public boolean isEnabled(final int id);

    /**
     * 分配描画用のSurfaceへの描画の有効・無効を切替
     * @param id
     * @param enable
     */
    public void setEnabled(final int id, final boolean enable);

    /**
     * 強制的に現在の最新のフレームを描画要求する
     * 分配描画用Surface全てが更新されるので注意
     */
    public void requestFrame();

    /**
     * 追加されている分配描画用のSurfaceの数を取得
     * @return
     */
    public int getCount();

    /**
     * 静止画を撮影する
     * 撮影完了を待機しない
     * @param path
     */
    public void captureStillAsync(final String path);

    /**
     * 静止画を撮影する
     * 撮影完了を待機しない
     * @param path
     * @param captureCompression JPEGの圧縮率, pngの時は無視
     */
    public void captureStillAsync(final String path, final int captureCompression);

    /**
     * 静止画を撮影する
     * 撮影完了を待機する
     * @param path
     */
    public void captureStill(final String path);

    /**
     * 静止画を撮影する
     * 撮影完了を待機する
     * @param path
     * @param captureCompression JPEGの圧縮率, pngの時は無視
     */
    public void captureStill(final String path, final int captureCompression);
}
