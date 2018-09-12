package com.wesine.device_sdk.glutils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by wesine on 2018/9/12.
 */

public class RendererHolder extends AbstractRendererHolder {
    //	private static final boolean DEBUG = false;	// FIXME 実働時はfalseにすること
    private static final String TAG = RendererHolder.class.getSimpleName();

    public RendererHolder(final int width, final int height,
                          @Nullable final RenderHolderCallback callback) {

        this(width, height,
                3, null, EglTask.EGL_FLAG_RECORDABLE,
                callback);
    }

    public RendererHolder(final int width, final int height,
                          final int maxClientVersion, final EGLBase.IContext sharedContext, final int flags,
                          @Nullable final RenderHolderCallback callback) {

        super(width, height,
                maxClientVersion, sharedContext, flags,
                callback);
    }

    @NonNull
    protected RendererTask createRendererTask(final int width, final int height,
                                              final int maxClientVersion, final EGLBase.IContext sharedContext, final int flags) {

        return new MyRendererTask(this, width, height,
                maxClientVersion, sharedContext, flags);
    }

//================================================================================
// 実装
//================================================================================
    /**
     * ワーカースレッド上でOpenGL|ESを用いてマスター映像を分配描画するためのインナークラス
     */
    protected static final class MyRendererTask extends RendererTask {

        public MyRendererTask(final RendererHolder parent,
                              final int width, final int height) {

            super(parent, width, height);
        }

        public MyRendererTask(@NonNull final AbstractRendererHolder parent,
                              final int width, final int height,
                              final int maxClientVersion,
                              final EGLBase.IContext sharedContext, final int flags) {

            super(parent, width, height, maxClientVersion, sharedContext, flags);
        }
    }
}
