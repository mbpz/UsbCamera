package com.wesine.device_sdk.glutils;

/**
 * Created by wesine on 2018/9/12.
 */

public interface IDrawer2D {
    public void release();
    public float[] getMvpMatrix();
    public IDrawer2D setMvpMatrix(final float[] matrix, final int offset);
    public void getMvpMatrix(final float[] matrix, final int offset);
    public void draw(final int texId, final float[] tex_matrix, final int offset);
    public void draw(final ITexture texture);
    public void draw(final TextureOffscreen offscreen);
}
