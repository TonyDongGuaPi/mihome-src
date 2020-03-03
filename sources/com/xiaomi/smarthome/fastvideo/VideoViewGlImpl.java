package com.xiaomi.smarthome.fastvideo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import com.xiaomi.smarthome.camera.VideoFrame;
import com.xiaomi.smarthome.camera.XmMp4Player;
import com.xiaomi.smarthome.camera.XmVideoViewGl;

public class VideoViewGlImpl implements XmVideoViewGl {
    private static final String f = "VideoView";

    /* renamed from: a  reason: collision with root package name */
    VideoGlSurfaceView f15919a;
    float b = 0.0f;
    float c = 0.0f;
    boolean d = false;
    public boolean e = true;
    /* access modifiers changed from: private */
    public float g = 1.5f;
    /* access modifiers changed from: private */
    public float h = 2.0f;
    /* access modifiers changed from: private */
    public GestureDetector i;
    /* access modifiers changed from: private */
    public ScaleGestureDetector j;
    /* access modifiers changed from: private */
    public boolean k = false;
    /* access modifiers changed from: private */
    public XmVideoViewGl.IVideoViewListener l;
    private FrameLayout m;
    private XmMp4Player n;
    private Context o;
    private XmVideoViewGl.IVideoViewScaleListener p;
    private View.OnTouchListener q = new View.OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (VideoViewGlImpl.this.i == null || VideoViewGlImpl.this.j == null || !VideoViewGlImpl.this.e) {
                return true;
            }
            VideoViewGlImpl.this.i.onTouchEvent(motionEvent);
            VideoViewGlImpl.this.j.onTouchEvent(motionEvent);
            if (motionEvent.getAction() == 0) {
                VideoViewGlImpl.this.b = motionEvent.getX();
                VideoViewGlImpl.this.c = motionEvent.getY();
                return true;
            } else if (motionEvent.getAction() == 2) {
                if (!VideoViewGlImpl.this.k) {
                    VideoViewGlImpl.this.f15919a.move((float) ((int) (motionEvent.getX() - VideoViewGlImpl.this.b)), (float) ((int) (-(motionEvent.getY() - VideoViewGlImpl.this.c))), false);
                    VideoViewGlImpl.this.f15919a.requestRender();
                    VideoViewGlImpl.this.b = motionEvent.getX();
                    VideoViewGlImpl.this.c = motionEvent.getY();
                }
                return true;
            } else if (motionEvent.getAction() != 1 && motionEvent.getAction() != 3) {
                return true;
            } else {
                boolean unused = VideoViewGlImpl.this.k = false;
                return true;
            }
        }
    };

    public VideoViewGlImpl(Context context, FrameLayout frameLayout, boolean z, int i2, boolean z2) {
        this.m = frameLayout;
        this.f15919a = new VideoGlSurfaceView(context, (AttributeSet) null, z, i2);
        frameLayout.addView(this.f15919a, 0, new FrameLayout.LayoutParams(-1, -1));
        a(context);
        c();
    }

    public VideoViewGlImpl(Context context, FrameLayout frameLayout, boolean z, int i2) {
        this.m = frameLayout;
        this.f15919a = new VideoGlSurfaceView(context, (AttributeSet) null, z, i2);
        frameLayout.addView(this.f15919a, -1, -1);
        a(context);
        c();
    }

    private void c() {
        Window window;
        Context context = getContext();
        if (context != null && (context instanceof Activity) && (window = ((Activity) context).getWindow()) != null) {
            window.addFlags(128);
        }
    }

    public VideoViewGlImpl(Context context, FrameLayout frameLayout, VideoFrameDecoder videoFrameDecoder) {
        this.m = frameLayout;
        this.f15919a = new VideoGlSurfaceView(context, (AttributeSet) null, videoFrameDecoder);
        this.f15919a.setKeepScreenOn(true);
        frameLayout.addView(this.f15919a, -1, -1);
        a(context);
        c();
    }

    public void snap(XmVideoViewGl.PhotoSnapCallback photoSnapCallback) {
        if (this.f15919a != null) {
            this.f15919a.snap(photoSnapCallback);
        }
    }

    private void a(Context context) {
        this.o = context;
        this.j = new ScaleGestureDetector(context, new ScaleGestureDetector.OnScaleGestureListener() {

            /* renamed from: a  reason: collision with root package name */
            float f15920a = 0.0f;

            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
                float scale = VideoViewGlImpl.this.f15919a.getScale();
                if (scale > this.f15920a) {
                    VideoViewGlImpl.this.a(1);
                } else if (scale < this.f15920a) {
                    VideoViewGlImpl.this.a(2);
                }
            }

            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                boolean unused = VideoViewGlImpl.this.k = true;
                this.f15920a = VideoViewGlImpl.this.f15919a.getScale();
                return true;
            }

            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                float f;
                float scale = VideoViewGlImpl.this.f15919a.getScale() * scaleGestureDetector.getScaleFactor();
                if (VideoViewGlImpl.this.d) {
                    f = Math.max(VideoViewGlImpl.this.f15919a.getMiniScale(), Math.min(scale, VideoViewGlImpl.this.h));
                } else {
                    f = Math.max(VideoViewGlImpl.this.f15919a.getMiniScale(), Math.min(scale, VideoViewGlImpl.this.g));
                }
                VideoViewGlImpl.this.f15919a.setScale(f, false);
                VideoViewGlImpl.this.f15919a.requestRender();
                return true;
            }
        });
        this.i = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (motionEvent == null || motionEvent2 == null || ((Math.abs(motionEvent.getX() - motionEvent2.getX()) <= 50.0f && Math.abs(motionEvent.getY() - motionEvent2.getY()) <= 50.0f) || (Math.abs(f) <= 500.0f && Math.abs(f2) <= 500.0f))) {
                    return super.onFling(motionEvent, motionEvent2, f, f2);
                }
                return true;
            }

            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                if (VideoViewGlImpl.this.l == null) {
                    return true;
                }
                VideoViewGlImpl.this.l.onVideoViewClick();
                return true;
            }

            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (!VideoViewGlImpl.this.d) {
                    float miniScale = VideoViewGlImpl.this.f15919a.getMiniScale();
                    float scale = VideoViewGlImpl.this.f15919a.getScale();
                    float fullScale = VideoViewGlImpl.this.f15919a.getFullScale();
                    if (miniScale >= 0.9f) {
                        double scale2 = (double) VideoViewGlImpl.this.f15919a.getScale();
                        Double.isNaN(scale2);
                        if (Math.abs(scale2 - 1.0d) > 0.1d) {
                            VideoViewGlImpl.this.f15919a.setScale(1.0f, true);
                        } else {
                            VideoViewGlImpl.this.f15919a.setScale(VideoViewGlImpl.this.h, true);
                        }
                    } else if (scale == miniScale || scale > fullScale) {
                        VideoViewGlImpl.this.f15919a.setScale(fullScale, true);
                    } else {
                        VideoViewGlImpl.this.f15919a.setScale(miniScale, true);
                    }
                } else {
                    double scale3 = (double) VideoViewGlImpl.this.f15919a.getScale();
                    Double.isNaN(scale3);
                    if (Math.abs(scale3 - 1.0d) > 0.1d) {
                        VideoViewGlImpl.this.f15919a.setScale(1.0f, true);
                    } else {
                        VideoViewGlImpl.this.f15919a.setScale(VideoViewGlImpl.this.h, true);
                    }
                }
                return true;
            }
        });
        this.m.setOnTouchListener(this.q);
    }

    public void setWidth(int i2) {
        this.f15919a.setWidth(i2);
    }

    public void setHeight(int i2) {
        this.f15919a.setHeight(i2);
    }

    public void onPause() {
        LogUtil.a("Camera", "onpause");
        this.f15919a.onPause();
    }

    public void onResume() {
        LogUtil.a("Camera", "onresume");
        this.f15919a.onResume();
    }

    public void initial() {
        if (this.f15919a != null) {
            this.f15919a.onResume();
        }
    }

    public void setVideoViewListener(XmVideoViewGl.IVideoViewListener iVideoViewListener) {
        this.l = iVideoViewListener;
    }

    public void drawVideoFrame(VideoFrame videoFrame) {
        if (this.f15919a != null) {
            this.f15919a.drawVideoFrame(videoFrame);
        }
    }

    public void setBg(float f2, float f3, float f4) {
        if (this.f15919a != null) {
            this.f15919a.setBg(f2, f3, f4);
        }
    }

    public void setAlpha(float f2) {
        if (this.f15919a != null) {
            this.f15919a.setAlpha(f2);
        }
    }

    public void setDistort(float f2, float f3) {
        if (this.f15919a != null) {
            this.f15919a.setDistort(f2, f3);
        }
    }

    public void setDistort(float f2, float f3, float f4) {
        if (this.f15919a != null) {
            this.f15919a.setDistort(f2, f3, f4);
        }
    }

    public void setFirstBitmap(Bitmap bitmap) {
        if (this.f15919a != null) {
            this.f15919a.setFirstBitmap(bitmap);
        }
    }

    public void clearQueue() {
        if (this.f15919a != null) {
            this.f15919a.clearQueue();
        }
    }

    public void release() {
        this.i = null;
        this.j = null;
        this.o = null;
        if (this.f15919a != null) {
            this.f15919a.onPause();
        }
        if (this.m != null) {
            this.m.removeAllViews();
        }
    }

    public void releaseOnlySelf() {
        this.i = null;
        this.j = null;
        this.o = null;
        if (this.f15919a != null) {
            this.f15919a.onPause();
        }
        if (this.m != null) {
            this.m.removeView(this.f15919a);
        }
    }

    public void setAutoRelease(boolean z) {
        if (this.f15919a != null) {
            this.f15919a.setAutoRelease(z);
        }
    }

    public boolean isGPUDecoder() {
        return this.f15919a != null && this.f15919a.isGPUDecoder();
    }

    public void setMiniScale(boolean z) {
        if (this.f15919a != null) {
            this.f15919a.setMiniScale(z);
        }
    }

    public void setRotation(int i2) {
        if (this.f15919a != null) {
            this.f15919a.setRotation(i2);
        }
    }

    public void setIsFull(boolean z) {
        this.d = z;
        if (this.f15919a != null) {
            this.f15919a.setIsFull(z);
        }
        a(3);
    }

    public void setIsFullForRN(boolean z) {
        this.d = z;
        if (this.f15919a != null) {
            this.f15919a.setIsFullForRN(z);
        }
    }

    public int drawBitmap(XmVideoViewGl.IDrawBitmapCallback iDrawBitmapCallback, Bitmap bitmap, RectF rectF) {
        return this.f15919a.addPhoto(iDrawBitmapCallback, bitmap, rectF);
    }

    public void updateBitmap(int i2, Bitmap bitmap, RectF rectF) {
        this.f15919a.updatePhoto(i2, bitmap, rectF);
    }

    public void removeBitmap(int i2) {
        this.f15919a.removePhoto(i2);
    }

    public void setVideoFrameSize(int i2, int i3, boolean z) {
        this.d = z;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.m.getLayoutParams();
        layoutParams.width = i2;
        layoutParams.height = i3;
        layoutParams.gravity = 17;
        this.m.setLayoutParams(layoutParams);
        this.f15919a.setIsFull(z);
    }

    public void addMp4Player(XmMp4Player xmMp4Player) {
        this.n = xmMp4Player;
    }

    public XmMp4Player getMp4Player() {
        return this.n;
    }

    public void setTouch(boolean z) {
        this.e = z;
    }

    public Context getContext() {
        return this.o;
    }

    public void setScale(float f2, boolean z) {
        if (this.f15919a != null) {
            this.f15919a.setScale(f2, z);
        }
    }

    public float getScale() {
        if (this.f15919a != null) {
            return this.f15919a.mScale;
        }
        return 0.0f;
    }

    public boolean isBufferFull() {
        return this.f15919a != null && this.f15919a.mAVFrameQueue.remainingCapacity() == 0;
    }

    public View getSurfaceView() {
        return this.f15919a;
    }

    public void setMaxScale(float f2, float f3) {
        this.g = f2;
        this.h = f3;
    }

    public void setVideoViewScaleListener(XmVideoViewGl.IVideoViewScaleListener iVideoViewScaleListener) {
        this.p = iVideoViewScaleListener;
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        if (this.p != null) {
            this.p.reportVideoViewScaleEvent(i2);
        }
    }

    public float a() {
        if (this.f15919a != null) {
            return this.f15919a.getOffsetX();
        }
        return 0.0f;
    }

    public float b() {
        if (this.f15919a != null) {
            return this.f15919a.getOffsetY();
        }
        return 0.0f;
    }

    public void a(float f2) {
        if (this.f15919a != null) {
            this.f15919a.setOffsetX(f2);
        }
    }

    public void b(float f2) {
        if (this.f15919a != null) {
            this.f15919a.setOffsetY(f2);
        }
    }
}
