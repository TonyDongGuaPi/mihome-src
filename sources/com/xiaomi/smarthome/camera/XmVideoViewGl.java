package com.xiaomi.smarthome.camera;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.support.annotation.FloatRange;
import android.view.View;

public interface XmVideoViewGl {

    public interface IDrawBitmapCallback {
        void onBitmapCreated(int i);
    }

    public interface IVideoViewListener {
        void onVideoViewClick();
    }

    public interface IVideoViewScaleListener {
        void reportVideoViewScaleEvent(int i);
    }

    public interface PhotoSnapCallback {
        void onSnap(Bitmap bitmap);
    }

    void addMp4Player(XmMp4Player xmMp4Player);

    void clearQueue();

    int drawBitmap(IDrawBitmapCallback iDrawBitmapCallback, Bitmap bitmap, RectF rectF);

    void drawVideoFrame(VideoFrame videoFrame);

    Context getContext();

    XmMp4Player getMp4Player();

    float getScale();

    View getSurfaceView();

    void initial();

    boolean isBufferFull();

    boolean isGPUDecoder();

    void onPause();

    void onResume();

    void release();

    void releaseOnlySelf();

    void removeBitmap(int i);

    void setAlpha(@FloatRange(from = 0.0d, to = 1.0d) float f);

    void setAutoRelease(boolean z);

    void setBg(float f, float f2, float f3);

    void setDistort(float f, float f2);

    void setDistort(float f, float f2, float f3);

    void setFirstBitmap(Bitmap bitmap);

    void setHeight(int i);

    void setIsFull(boolean z);

    void setIsFullForRN(boolean z);

    void setMaxScale(float f, float f2);

    void setMiniScale(boolean z);

    void setRotation(int i);

    void setScale(float f, boolean z);

    void setTouch(boolean z);

    void setVideoFrameSize(int i, int i2, boolean z);

    void setVideoViewListener(IVideoViewListener iVideoViewListener);

    void setVideoViewScaleListener(IVideoViewScaleListener iVideoViewScaleListener);

    void setWidth(int i);

    void snap(PhotoSnapCallback photoSnapCallback);

    void updateBitmap(int i, Bitmap bitmap, RectF rectF);
}
