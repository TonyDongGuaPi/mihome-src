package com.xiaomi.smarthome.camera.v4.view;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.FrameLayout;
import com.mijia.debug.SDKLog;
import com.xiaomi.smarthome.camera.VideoFrame;
import com.xiaomi.smarthome.camera.XmVideoViewGl;
import com.xiaomi.smarthome.fastvideo.VideoFrameDecoder;
import com.xiaomi.smarthome.fastvideo.VideoGlSurfaceView;

public class VideoViewGl extends FrameLayout {
    static final float MAX_LAND_SCALE = 2.0f;
    static final float MAX_SCALE = 1.5f;
    private static final String TAG = "VideoView";
    public boolean isTouchable = true;
    private GestureDetector mDetector;
    IVideoViewListener mIVideoViewListener;
    boolean mIsFull = false;
    float mLastX = 0.0f;
    float mLastY = 0.0f;
    private ScaleGestureDetector mScaleDetector;
    VideoGlSurfaceView mSurfaceView;
    /* access modifiers changed from: private */
    public boolean mZooming = false;

    public interface IVideoViewListener {
        void onVideoViewClick();
    }

    public void setVideoViewListener(IVideoViewListener iVideoViewListener) {
        this.mIVideoViewListener = iVideoViewListener;
    }

    public VideoViewGl(Context context) {
        super(context);
        init((VideoFrameDecoder) null);
    }

    public VideoViewGl(Context context, VideoFrameDecoder videoFrameDecoder) {
        super(context);
        init(videoFrameDecoder);
    }

    public VideoViewGl(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init((VideoFrameDecoder) null);
    }

    private void init(VideoFrameDecoder videoFrameDecoder) {
        if (videoFrameDecoder != null) {
            this.mSurfaceView = new VideoGlSurfaceView(getContext(), (AttributeSet) null, videoFrameDecoder);
        } else {
            this.mSurfaceView = new VideoGlSurfaceView(getContext(), (AttributeSet) null, true, 2);
        }
        this.mSurfaceView.setKeepScreenOn(true);
        addView(this.mSurfaceView, -1, -1);
        this.mScaleDetector = new ScaleGestureDetector(getContext(), new ScaleGestureDetector.OnScaleGestureListener() {
            public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {
            }

            public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {
                boolean unused = VideoViewGl.this.mZooming = true;
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
        this.mDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
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
                VideoViewGl.this.mIVideoViewListener.onVideoViewClick();
                return true;
            }

            public boolean onDoubleTap(MotionEvent motionEvent) {
                SDKLog.b(VideoViewGl.TAG, "onDoubleTap:" + VideoViewGl.this.mSurfaceView.getScale());
                if (VideoViewGl.this.mIsFull) {
                    double scale = (double) VideoViewGl.this.mSurfaceView.getScale();
                    Double.isNaN(scale);
                    if (Math.abs(scale - 1.0d) > 0.1d) {
                        VideoViewGl.this.mSurfaceView.setScale(1.0f, true);
                    } else {
                        VideoViewGl.this.mSurfaceView.setScale(VideoViewGl.MAX_LAND_SCALE, true);
                    }
                } else if (((double) VideoViewGl.this.mSurfaceView.getScale()) > 1.0d) {
                    VideoViewGl.this.mSurfaceView.setScale(1.0f, true);
                } else if (((double) VideoViewGl.this.mSurfaceView.getScale()) > 0.9d) {
                    VideoViewGl.this.mSurfaceView.setScale(VideoViewGl.this.mSurfaceView.getMiniScale(), true);
                } else {
                    VideoViewGl.this.mSurfaceView.setScale(1.0f, true);
                }
                return true;
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 1) {
            this.mIsFull = false;
        } else {
            this.mIsFull = true;
        }
    }

    public void setVideoFrameSize(int i, int i2, boolean z) {
        if (this.mSurfaceView != null) {
            this.mSurfaceView.reset();
        }
        this.mIsFull = z;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
        layoutParams.width = i;
        layoutParams.height = i2;
        layoutParams.gravity = 17;
        setLayoutParams(layoutParams);
    }

    public void setIsFull(boolean z) {
        this.mIsFull = z;
        float scale = this.mSurfaceView.getScale();
        if (this.mIsFull && ((double) scale) < 1.0d) {
            this.mSurfaceView.setScale(1.0f, false);
            this.mSurfaceView.requestRender();
        }
    }

    public VideoGlSurfaceView getSurfaceView() {
        return this.mSurfaceView;
    }

    public void setDistortCallBack(VideoGlSurfaceView.DistortCallBack distortCallBack) {
        System.out.println("mytest:468.0##48.0");
        if (this.mSurfaceView != null) {
            this.mSurfaceView.setDistortCallBak(distortCallBack, 0.24375f, 0.04411765f);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.mDetector == null || this.mScaleDetector == null || this.mSurfaceView == null || !this.isTouchable) {
            return true;
        }
        this.mDetector.onTouchEvent(motionEvent);
        this.mScaleDetector.onTouchEvent(motionEvent);
        if (motionEvent.getAction() == 0) {
            this.mLastX = motionEvent.getX();
            this.mLastY = motionEvent.getY();
            return true;
        } else if (motionEvent.getAction() == 2) {
            if (!this.mZooming) {
                this.mSurfaceView.move((float) ((int) (motionEvent.getX() - this.mLastX)), (float) ((int) (-(motionEvent.getY() - this.mLastY))), false);
                this.mSurfaceView.requestRender();
                this.mLastX = motionEvent.getX();
                this.mLastY = motionEvent.getY();
            }
            return true;
        } else if (motionEvent.getAction() != 1 && motionEvent.getAction() != 3) {
            return true;
        } else {
            this.mZooming = false;
            return true;
        }
    }

    public void drawVideoFrame(VideoFrame videoFrame) {
        this.mSurfaceView.drawVideoFrame(videoFrame);
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

    public void release() {
        this.mDetector = null;
        setDistortCallBack((VideoGlSurfaceView.DistortCallBack) null);
        this.mScaleDetector = null;
        if (this.mSurfaceView != null) {
            this.mSurfaceView.onPause();
        }
    }
}
