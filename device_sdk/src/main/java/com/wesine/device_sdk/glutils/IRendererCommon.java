package com.wesine.device_sdk.glutils;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by wesine on 2018/9/12.
 */

public interface IRendererCommon {
    public static final int MIRROR_NORMAL = 0;
    public static final int MIRROR_HORIZONTAL = 1;
    public static final int MIRROR_VERTICAL = 2;
    public static final int MIRROR_BOTH = 3;
    public static final int MIRROR_NUM = 4;

    @IntDef({MIRROR_NORMAL, MIRROR_HORIZONTAL, MIRROR_VERTICAL, MIRROR_BOTH})
    @Retention(RetentionPolicy.SOURCE)
    public @interface MirrorMode {
    }

    /**
     * 映像を上下左右反転させるかどうかをセット
     *
     * @param mirror 0:通常, 1:左右反転, 2:上下反転, 3:上下左右反転
     */
    public void setMirror(@MirrorMode final int mirror);

    /**
     * 映像を上下左右反転させるかどうかを取得
     *
     * @return 0:通常, 1:左右反転, 2:上下反転, 3:上下左右反転
     */
    public @MirrorMode
    int getMirror();
}
