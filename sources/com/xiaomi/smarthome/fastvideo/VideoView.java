package com.xiaomi.smarthome.fastvideo;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.support.annotation.FloatRange;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.xiaomi.h264videoplayer.R;
import com.xiaomi.smarthome.camera.VideoFrame;
import com.xiaomi.smarthome.camera.XmVideoViewGl;

public class VideoView extends FrameLayout {
    public static boolean ENABLE_MEDIACODEC = false;
    static final float MAX_LAND_SCALE = 2.0f;
    static final float MAX_SCALE = 1.5f;
    static final String TAG = "VideoView";

    /* renamed from: a  reason: collision with root package name */
    private float f15913a = 4.0f;
    private GestureDetector b;
    private ScaleGestureDetector c;
    /* access modifiers changed from: private */
    public boolean d = false;
    private float e;
    private float f;
    private float g;
    boolean mDisableTouch = false;
    IVideoViewListener mIVideoViewListener;
    boolean mIsFull = false;
    boolean mIsInitialled;
    boolean mIsMinScale = false;
    float mLastX = 0.0f;
    float mLastY = 0.0f;
    VideoGlSurfaceView mSurfaceView;

    public interface IVideoViewListener {
        void a();
    }

    public void setVideoViewListener(IVideoViewListener iVideoViewListener) {
        this.mIVideoViewListener = iVideoViewListener;
    }

    public VideoView(Context context) {
        super(context);
        a((VideoFrameDecoder) null);
    }

    public VideoView(Context context, boolean z, int i) {
        super(context);
        a((VideoFrameDecoder) null, z, i);
    }

    public VideoView(Context context, VideoFrameDecoder videoFrameDecoder) {
        super(context);
        a(videoFrameDecoder);
    }

    public VideoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.videoDecoder);
            boolean z = obtainStyledAttributes.getBoolean(R.styleable.videoDecoder_hardDecoder, true);
            int i = obtainStyledAttributes.getInt(R.styleable.videoDecoder_h265Decoder, 1);
            obtainStyledAttributes.recycle();
            a((VideoFrameDecoder) null, z, i);
            return;
        }
        a((VideoFrameDecoder) null);
    }

    public void setAlpha(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        this.mSurfaceView.setAlpha(f2);
    }

    private void a(VideoFrameDecoder videoFrameDecoder) {
        a(videoFrameDecoder, true, 1);
    }

    private void a(VideoFrameDecoder videoFrameDecoder, boolean z, int i) {
        if (videoFrameDecoder != null) {
            this.mSurfaceView = new VideoGlSurfaceView(getContext(), (AttributeSet) null, videoFrameDecoder);
        } else {
            this.mSurfaceView = new VideoGlSurfaceView(getContext(), (AttributeSet) null, z, i);
        }
        this.mSurfaceView.setBg(0.102f, 0.102f, 0.102f);
        this.mSurfaceView.setAutoRelease(true);
        this.mSurfaceView.setKeepScreenOn(true);
        Log.e(TAG, "Start init GlSurfaceView hard: " + z + "type:  " + i);
        addView(this.mSurfaceView, -1, -1);
        this.c = new ScaleGestureDetector(getContext(), new ScaleGestureDetector.OnScaleGestureListener() {
            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            }

            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                boolean unused = VideoView.this.d = true;
                return true;
            }

            public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
                float f;
                float scale = VideoView.this.mSurfaceView.getScale() * scaleGestureDetector.getScaleFactor();
                if (VideoView.this.mIsFull) {
                    f = Math.max(VideoView.this.mSurfaceView.getMiniScale(), Math.min(scale, VideoView.MAX_LAND_SCALE));
                } else {
                    f = Math.max(VideoView.this.mSurfaceView.getMiniScale(), Math.min(scale, VideoView.MAX_SCALE));
                }
                VideoView.this.mSurfaceView.setScale(f, false);
                VideoView.this.mSurfaceView.requestRender();
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
                if (VideoView.this.mIVideoViewListener == null) {
                    return true;
                }
                VideoView.this.mIVideoViewListener.a();
                return true;
            }

            public boolean onDoubleTap(MotionEvent motionEvent) {
                if (!VideoView.this.mIsFull) {
                    float miniScale = VideoView.this.mSurfaceView.getMiniScale();
                    float scale = VideoView.this.mSurfaceView.getScale();
                    float fullScale = VideoView.this.mSurfaceView.getFullScale();
                    if (((double) miniScale) >= 0.9d) {
                        double scale2 = (double) VideoView.this.mSurfaceView.getScale();
                        Double.isNaN(scale2);
                        if (Math.abs(scale2 - 1.0d) > 0.1d) {
                            VideoView.this.mSurfaceView.setScale(1.0f, true);
                        } else {
                            VideoView.this.mSurfaceView.setScale(VideoView.MAX_LAND_SCALE, true);
                        }
                    } else if (scale == miniScale || scale > fullScale) {
                        VideoView.this.mSurfaceView.setScale(fullScale, true);
                    } else {
                        VideoView.this.mSurfaceView.setScale(miniScale, true);
                    }
                } else {
                    double scale3 = (double) VideoView.this.mSurfaceView.getScale();
                    Double.isNaN(scale3);
                    if (Math.abs(scale3 - 1.0d) > 0.1d) {
                        VideoView.this.mSurfaceView.setScale(1.0f, true);
                    } else {
                        VideoView.this.mSurfaceView.setScale(VideoView.MAX_LAND_SCALE, true);
                    }
                }
                return true;
            }
        });
    }

    public void disableTouch() {
        this.mDisableTouch = true;
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setIsFull(configuration.orientation == 2);
    }

    public void setVideoFrameSize(int i, int i2, boolean z) {
        setIsFull(z);
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(i, i2);
        }
        layoutParams.width = i;
        layoutParams.height = i2;
        setLayoutParams(layoutParams);
    }

    public void setVideoFrameSize(int i, int i2) {
        this.mSurfaceView.reset();
        this.mIsFull = false;
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new ViewGroup.LayoutParams(i, i2);
        }
        layoutParams.width = i;
        layoutParams.height = i2;
        setLayoutParams(layoutParams);
    }

    public void setIsFull(boolean z) {
        this.mIsFull = z;
        if (this.mSurfaceView != null) {
            this.mSurfaceView.setIsFull(z);
        }
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

    public boolean isInitialized() {
        return this.mSurfaceView != null && this.mSurfaceView.isInitial();
    }

    public VideoGlSurfaceView getSurfaceView() {
        return this.mSurfaceView;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.b == null || this.c == null) {
            return true;
        }
        if (this.mDisableTouch) {
            return false;
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
        this.mSurfaceView.drawVideoFrame(videoFrame);
    }

    public boolean isBufferFull() {
        return this.mSurfaceView.mAVFrameQueue.remainingCapacity() == 0;
    }

    public void snap(XmVideoViewGl.PhotoSnapCallback photoSnapCallback) {
        this.mSurfaceView.snap(photoSnapCallback);
    }

    public float getScaleRadio() {
        if (this.mSurfaceView != null) {
            return this.mSurfaceView.mScale;
        }
        return 0.0f;
    }

    public void setScaleRadio(float f2) {
        this.f15913a = f2;
    }

    public void smoothScale(float f2) {
        this.mSurfaceView.setScale(Math.min(Math.max(this.mSurfaceView.getMiniScale(), f2), this.f15913a), true);
        this.mSurfaceView.requestRender();
    }

    public void scaleTo(float f2) {
        this.mSurfaceView.setScale(Math.min(Math.max(this.mSurfaceView.getMiniScale(), f2), this.f15913a), false);
        this.mSurfaceView.requestRender();
    }

    public void setFirstBitmap(Bitmap bitmap) {
        if (this.mSurfaceView != null) {
            this.mSurfaceView.setFirstBitmap(bitmap);
        }
    }
}
