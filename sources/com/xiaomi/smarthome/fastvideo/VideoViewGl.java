package com.xiaomi.smarthome.fastvideo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.FrameLayout;
import com.xiaomi.h264videoplayer.R;
import com.xiaomi.smarthome.camera.VideoFrame;
import com.xiaomi.smarthome.camera.XmVideoViewGl;

public class VideoViewGl extends FrameLayout {
    static final float MAX_LAND_SCALE = 2.0f;
    static final float MAX_SCALE = 1.5f;

    /* renamed from: a  reason: collision with root package name */
    private static final String f15916a = "VideoView";
    private GestureDetector b;
    private ScaleGestureDetector c;
    /* access modifiers changed from: private */
    public boolean d;
    private float e;
    public boolean isTouchable;
    IVideoViewListener mIVideoViewListener;
    boolean mIsFull;
    float mLastX;
    float mLastY;
    VideoGlSurfaceView mSurfaceView;

    public interface IVideoViewListener {
        void a();
    }

    public void setVideoViewListener(IVideoViewListener iVideoViewListener) {
        this.mIVideoViewListener = iVideoViewListener;
    }

    public VideoViewGl(Context context) {
        super(context);
        this.mLastX = 0.0f;
        this.mLastY = 0.0f;
        this.mIsFull = false;
        this.isTouchable = true;
        this.d = false;
        this.e = 4.0f;
        a((VideoFrameDecoder) null);
    }

    public VideoViewGl(Context context, VideoFrameDecoder videoFrameDecoder) {
        super(context);
        this.mLastX = 0.0f;
        this.mLastY = 0.0f;
        this.mIsFull = false;
        this.isTouchable = true;
        this.d = false;
        this.e = 4.0f;
        a(videoFrameDecoder);
    }

    public VideoViewGl(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        boolean z;
        this.mLastX = 0.0f;
        this.mLastY = 0.0f;
        this.mIsFull = false;
        int i = 1;
        this.isTouchable = true;
        this.d = false;
        this.e = 4.0f;
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.videoDecoder);
            z = obtainStyledAttributes.getBoolean(R.styleable.videoDecoder_hardDecoder, true);
            i = obtainStyledAttributes.getInt(R.styleable.videoDecoder_h265Decoder, 1);
            obtainStyledAttributes.recycle();
        } else {
            z = true;
        }
        a((VideoFrameDecoder) null, i, z);
    }

    public VideoViewGl(Context context, int i, boolean z) {
        super(context);
        this.mLastX = 0.0f;
        this.mLastY = 0.0f;
        this.mIsFull = false;
        this.isTouchable = true;
        this.d = false;
        this.e = 4.0f;
        a((VideoFrameDecoder) null, i, z);
    }

    private void a(VideoFrameDecoder videoFrameDecoder) {
        a(videoFrameDecoder, 1, true);
    }

    private void a(VideoFrameDecoder videoFrameDecoder, int i, boolean z) {
        if (videoFrameDecoder != null) {
            this.mSurfaceView = new VideoGlSurfaceView(getContext(), (AttributeSet) null, videoFrameDecoder);
        } else {
            this.mSurfaceView = new VideoGlSurfaceView(getContext(), (AttributeSet) null, z, i);
        }
        this.mSurfaceView.setKeepScreenOn(true);
        addView(this.mSurfaceView, -1, -1);
        this.c = new ScaleGestureDetector(getContext(), new ScaleGestureDetector.OnScaleGestureListener() {
            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            }

            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                boolean unused = VideoViewGl.this.d = true;
                return true;
            }

            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                float f;
                float scale = VideoViewGl.this.mSurfaceView.getScale() * scaleGestureDetector.getScaleFactor();
                if (VideoViewGl.this.mIsFull) {
                    f = Math.max(VideoViewGl.this.mSurfaceView.getMiniScale(), Math.min(scale, VideoViewGl.MAX_LAND_SCALE));
                } else {
                    f = Math.max(VideoViewGl.this.mSurfaceView.getMiniScale(), Math.min(scale, VideoViewGl.MAX_SCALE));
                }
                VideoViewGl.this.mSurfaceView.setScale(f, false);
                VideoViewGl.this.mSurfaceView.requestRender();
                return true;
            }
        });
        this.b = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (motionEvent == null || motionEvent2 == null || ((Math.abs(motionEvent.getX() - motionEvent2.getX()) <= 50.0f && Math.abs(motionEvent.getY() - motionEvent2.getY()) <= 50.0f) || (Math.abs(f) <= 500.0f && Math.abs(f2) <= 500.0f))) {
                    return super.onFling(motionEvent, motionEvent2, f, f2);
                }
                return true;
            }

            public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
                if (VideoViewGl.this.mIVideoViewListener == null) {
                    return true;
                }
                VideoViewGl.this.mIVideoViewListener.a();
                return true;
            }

            public boolean onDoubleTap(MotionEvent motionEvent) {
                Log.d(VideoViewGl.f15916a, "onDoubleTap:" + VideoViewGl.this.mSurfaceView.getScale());
                if (!VideoViewGl.this.mIsFull) {
                    if (((double) VideoViewGl.this.mSurfaceView.getScale()) > 1.0d) {
                        VideoViewGl.this.mSurfaceView.setScale(1.0f, true);
                    } else if (((double) VideoViewGl.this.mSurfaceView.getScale()) > 0.9d) {
                        VideoViewGl.this.mSurfaceView.setScale(VideoViewGl.this.mSurfaceView.getMiniScale(), true);
                    } else {
                        VideoViewGl.this.mSurfaceView.setScale(1.0f, true);
                    }
                } else if (((double) VideoViewGl.this.mSurfaceView.getScale()) > 1.0d) {
                    VideoViewGl.this.mSurfaceView.setScale(1.0f, true);
                } else {
                    VideoViewGl.this.mSurfaceView.setScale(VideoViewGl.MAX_LAND_SCALE, true);
                }
                return true;
            }
        });
    }

    public void setVideoFrameSize(int i, int i2, boolean z) {
        this.mSurfaceView.reset();
        this.mIsFull = z;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i2;
        layoutParams.gravity = 17;
        setLayoutParams(layoutParams);
    }

    public void setIsFull(boolean z) {
        this.mIsFull = z;
    }

    public int drawBitmap(XmVideoViewGl.IDrawBitmapCallback iDrawBitmapCallback, Bitmap bitmap, RectF rectF) {
        return this.mSurfaceView.addPhoto(iDrawBitmapCallback, bitmap, rectF);
    }

    public void updateBitmap(int i, Bitmap bitmap, RectF rectF) {
        this.mSurfaceView.updatePhoto(i, bitmap, rectF);
    }

    public void removeBitmap(int i) {
        this.mSurfaceView.removePhoto(i);
    }

    public VideoGlSurfaceView getSurfaceView() {
        return this.mSurfaceView;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.b == null || this.c == null || !this.isTouchable) {
            return true;
        }
        this.b.onTouchEvent(motionEvent);
        this.c.onTouchEvent(motionEvent);
        if (motionEvent.getAction() == 0) {
            this.mLastX = motionEvent.getX();
            this.mLastY = motionEvent.getY();
            return true;
        } else if (motionEvent.getAction() == 2) {
            if (!this.d) {
                this.mSurfaceView.move((float) ((int) (motionEvent.getX() - this.mLastX)), (float) ((int) (-(motionEvent.getY() - this.mLastY))), false);
                this.mSurfaceView.requestRender();
                this.mLastX = motionEvent.getX();
                this.mLastY = motionEvent.getY();
            }
            return true;
        } else if (motionEvent.getAction() != 1 && motionEvent.getAction() != 3) {
            return true;
        } else {
            this.d = false;
            return true;
        }
    }

    public void drawVideoFrame(VideoFrame videoFrame) {
        if (this.mSurfaceView != null) {
            this.mSurfaceView.drawVideoFrame(videoFrame);
        }
    }

    public void snap(XmVideoViewGl.PhotoSnapCallback photoSnapCallback) {
        if (this.mSurfaceView != null) {
            this.mSurfaceView.snap(photoSnapCallback);
        }
    }

    public void initial() {
        if (this.mSurfaceView != null) {
            this.mSurfaceView.onResume();
        }
    }

    public void clearQueue() {
        if (this.mSurfaceView != null) {
            this.mSurfaceView.clearQueue();
        }
    }

    public boolean isBufferFull() {
        return this.mSurfaceView.mAVFrameQueue.remainingCapacity() == 0;
    }

    public float getScaleRadio() {
        if (this.mSurfaceView != null) {
            return this.mSurfaceView.mScale;
        }
        return 0.0f;
    }

    public void setScaleRadio(float f) {
        this.e = f;
    }

    public void smoothScale(float f) {
        float f2 = this.e;
        float miniScale = this.mSurfaceView.getMiniScale();
        if (this.mSurfaceView != null) {
            this.mSurfaceView.setScale(Math.min(Math.max(miniScale, f), f2), true);
            this.mSurfaceView.requestRender();
        }
    }

    public void scaleTo(float f) {
        float f2 = this.e;
        float miniScale = this.mSurfaceView.getMiniScale();
        if (this.mSurfaceView != null) {
            this.mSurfaceView.setScale(Math.min(Math.max(miniScale, f), f2), false);
            this.mSurfaceView.requestRender();
        }
    }

    public void setFirstBitmap(Bitmap bitmap) {
        if (this.mSurfaceView != null) {
            this.mSurfaceView.setFirstBitmap(bitmap);
        }
    }

    public void setDistort(float f, float f2) {
        if (this.mSurfaceView != null) {
            this.mSurfaceView.setDistort(f, f2);
        }
    }

    public void setBg(float f, float f2, float f3) {
        if (this.mSurfaceView != null) {
            this.mSurfaceView.setBg(f, f2, f3);
        }
    }

    public void release() {
        this.b = null;
        this.c = null;
        if (this.mSurfaceView != null) {
            this.mSurfaceView.onPause();
        }
    }
}
