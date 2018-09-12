package com.wesine.device_sdk.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;

/**
 * Created by wesine on 2018/9/12.
 */

public class AspectRatioTextureView extends TextureView    // API >= 14
        implements IAspectRatioView {

    private static final boolean DEBUG = true;    // TODO set false on release
    private static final String TAG = "AbstractCameraView";

    private double mRequestedAspect = -1.0;
    private CameraViewInterface.Callback mCallback;

    public AspectRatioTextureView(final Context context) {
        this(context, null, 0);
    }

    public AspectRatioTextureView(final Context context, final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AspectRatioTextureView(final Context context, final AttributeSet attrs, final int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAspectRatio(final double aspectRatio) {
        if (aspectRatio < 0) {
            throw new IllegalArgumentException();
        }
        if (mRequestedAspect != aspectRatio) {
            mRequestedAspect = aspectRatio;
            requestLayout();
        }
    }

    @Override
    public void setAspectRatio(final int width, final int height) {
        setAspectRatio(width / (double) height);
    }

    @Override
    public double getAspectRatio() {
        return mRequestedAspect;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (mRequestedAspect > 0) {
            int initialWidth = MeasureSpec.getSize(widthMeasureSpec);
            int initialHeight = MeasureSpec.getSize(heightMeasureSpec);

            final int horizPadding = getPaddingLeft() + getPaddingRight();
            final int vertPadding = getPaddingTop() + getPaddingBottom();
            initialWidth -= horizPadding;
            initialHeight -= vertPadding;

            final double viewAspectRatio = (double) initialWidth / initialHeight;
            final double aspectDiff = mRequestedAspect / viewAspectRatio - 1;

            if (Math.abs(aspectDiff) > 0.01) {
                if (aspectDiff > 0) {
                    // width priority decision
                    initialHeight = (int) (initialWidth / mRequestedAspect);
                } else {
                    // height priority decision
                    initialWidth = (int) (initialHeight * mRequestedAspect);
                }
                initialWidth += horizPadding;
                initialHeight += vertPadding;
                widthMeasureSpec = MeasureSpec.makeMeasureSpec(initialWidth, MeasureSpec.EXACTLY);
                heightMeasureSpec = MeasureSpec.makeMeasureSpec(initialHeight, MeasureSpec.EXACTLY);
            }
        }

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
