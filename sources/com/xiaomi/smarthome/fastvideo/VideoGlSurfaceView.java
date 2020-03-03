package com.xiaomi.smarthome.fastvideo;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import com.xiaomi.h264videoplayer.R;
import com.xiaomi.smarthome.camera.VideoFrame;
import com.xiaomi.smarthome.fastvideo.AndroidH26xDecoderUtil;
import com.xiaomi.smarthome.fastvideo.decoder.MediaCodecUtil;
import java.util.concurrent.LinkedBlockingQueue;

public class VideoGlSurfaceView extends PhotoView {
    public static final int FILTER_DISTORT_NONE = 1;
    public static final int FILTER_DISTORT_WATER = 2;
    public static final int FILTER_NONE = 0;
    private static int i;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public int f15903a = 0;
    private boolean b;
    private int c;
    /* access modifiers changed from: private */
    public DistortCallBack d;
    private float e = 0.0f;
    private float f = 0.0f;
    private int g;
    private int h;

    public interface DistortCallBack {
        boolean isDistort();
    }

    public VideoGlSurfaceView(Context context) {
        super(context);
    }

    public VideoGlSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public VideoGlSurfaceView(Context context, AttributeSet attributeSet, boolean z, int i2) {
        super(context, attributeSet);
        if (attributeSet != null) {
            LogUtil.c(PhotoView.TAG, "attrs != null");
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.videoDecoder);
            this.b = obtainStyledAttributes.getBoolean(R.styleable.videoDecoder_hardDecoder, z);
            this.c = obtainStyledAttributes.getInt(R.styleable.videoDecoder_h265Decoder, i2);
            obtainStyledAttributes.recycle();
        } else {
            LogUtil.c(PhotoView.TAG, "attrs == null");
            this.b = z;
            this.c = i2;
        }
        init();
        if (i != 0) {
            setZOrderMediaOverlay(true);
        }
        i++;
    }

    /* access modifiers changed from: protected */
    public void init() {
        if (this.b) {
            AndroidH26xDecoderUtil.DecoderProperties a2 = AndroidH26xDecoderUtil.a(new MediaCodecUtil.CodecKey(this.c, 1920, 1080));
            if (a2 != null) {
                this.mVideoFrameDecoder = new VideoFrameDecoderGPU(this, a2);
            } else {
                LogUtil.c(PhotoView.TAG, "decoderProperty is null");
            }
        }
        if (this.mVideoFrameDecoder == null) {
            this.mVideoFrameDecoder = new VideoFrameDecoderFFMPEG(this, this.c);
        }
    }

    public VideoGlSurfaceView(Context context, AttributeSet attributeSet, VideoFrameDecoder videoFrameDecoder) {
        super(context, attributeSet);
        this.mVideoFrameDecoder = videoFrameDecoder;
        this.mVideoFrameDecoder.a(this);
    }

    public LinkedBlockingQueue<VideoFrame> getAVFrameQueue() {
        return this.mAVFrameQueue;
    }

    public void changeVideoFrameDecoder(final VideoFrameDecoder videoFrameDecoder) {
        if (videoFrameDecoder != this.mVideoFrameDecoder) {
            queue(new Runnable() {
                public void run() {
                    if (VideoGlSurfaceView.this.mVideoFrameDecoder != null) {
                        VideoGlSurfaceView.this.mVideoFrameDecoder.f();
                    }
                    VideoGlSurfaceView.this.mVideoFrameDecoder = videoFrameDecoder;
                    if (VideoGlSurfaceView.this.mAVFrameQueue != null) {
                        VideoGlSurfaceView.this.mAVFrameQueue.clear();
                    }
                    if (VideoGlSurfaceView.this.mVideoFrameDecoder != null) {
                        VideoGlSurfaceView.this.mVideoFrameDecoder.e();
                    }
                }
            });
        }
    }

    public void drawVideoFrame(VideoFrame videoFrame) {
        if (this.isInitial) {
            try {
                this.mAVFrameQueue.put(videoFrame);
                requestRender();
            } catch (InterruptedException unused) {
                clearQueue();
            }
        }
    }

    public void setDistortCallBak(DistortCallBack distortCallBack) {
        this.d = distortCallBack;
    }

    public void setDistortCallBak(DistortCallBack distortCallBack, float f2, float f3) {
        this.e = f2;
        this.f = f3;
        this.d = distortCallBack;
    }

    public void initWater(final boolean z, final boolean z2) {
        if (this.d != null) {
            queue(new Runnable() {
                public void run() {
                    if (VideoGlSurfaceView.this.d != null) {
                        int i = (!VideoGlSurfaceView.this.d.isDistort() || !z) ? 0 : z2 ? 2 : 1;
                        if (VideoGlSurfaceView.this.f15903a != i) {
                            VideoGlSurfaceView.this.a(i);
                            int unused = VideoGlSurfaceView.this.f15903a = i;
                        }
                    }
                }
            });
        }
    }

    public void setFilter(final Filter filter) {
        if (!this.isInitial) {
            this.mFilter = filter;
        } else {
            queue(new Runnable() {
                public void run() {
                    if (VideoGlSurfaceView.this.mFilter != null) {
                        VideoGlSurfaceView.this.mFilter.e();
                    }
                    VideoGlSurfaceView.this.mFilter = filter;
                    if (VideoGlSurfaceView.this.mFilter != null) {
                        VideoGlSurfaceView.this.mFilter.d();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public Photo appFilter(Photo photo) {
        if (this.mFilter == null) {
            return photo;
        }
        if (this.mMiddlePhoto != null || photo == null) {
            this.mMiddlePhoto.b(photo.b(), photo.c());
        } else {
            this.mMiddlePhoto = Photo.a(photo.b(), photo.c());
        }
        this.mFilter.a(photo, this.mMiddlePhoto);
        return this.mMiddlePhoto;
    }

    /* access modifiers changed from: protected */
    public void initial() {
        synchronized (this) {
            super.initial();
            if (this.mFilter != null) {
                this.mFilter.d();
            }
            clearQueue();
            if (this.mVideoFrameDecoder != null) {
                this.mVideoFrameDecoder.e();
            }
        }
    }

    public void drawFrame() {
        super.drawFrame();
        if (this.mVideoFrameDecoder != null) {
            this.mVideoFrameDecoder.g();
        }
    }

    /* access modifiers changed from: protected */
    public void release() {
        super.release();
        if (this.mFilter != null) {
            this.mFilter.e();
        }
        if (this.mMiddlePhoto != null) {
            this.mMiddlePhoto.e();
            this.mMiddlePhoto = null;
        }
        this.mAVFrameQueue.clear();
        if (this.mVideoFrameDecoder != null) {
            this.mVideoFrameDecoder.f();
        }
    }

    public int getVideoWidth() {
        if (this.mVideoFrameDecoder != null) {
            return this.mVideoFrameDecoder.i();
        }
        return 0;
    }

    public int getVideoHeight() {
        if (this.mVideoFrameDecoder != null) {
            return this.mVideoFrameDecoder.j();
        }
        return 0;
    }

    public boolean isGPUDecoder() {
        return this.mVideoFrameDecoder != null && (this.mVideoFrameDecoder instanceof VideoFrameDecoderGPU);
    }

    public void onPause() {
        synchronized (this) {
            LogUtil.a("Camera", "on pause");
            super.onPause();
        }
    }

    public void onResume() {
        synchronized (this) {
            LogUtil.a("Camera", "on resume");
            super.onResume();
        }
    }

    public void clearQueue() {
        this.mAVFrameQueue.clear();
    }

    public void setHeight(int i2) {
        this.h = i2;
    }

    public void setWidth(int i2) {
        this.g = i2;
    }

    public void setDistort(float f2, float f3, float f4) {
        this.e = f2;
        this.f = f3;
    }

    public void initWater(final int i2) {
        queue(new Runnable() {
            public void run() {
                int i = i2;
                if (VideoGlSurfaceView.this.f15903a != i) {
                    VideoGlSurfaceView.this.a(i);
                    int unused = VideoGlSurfaceView.this.f15903a = i;
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        if (i2 == 1) {
            CorrectinglensDistortionFilter correctinglensDistortionFilter = new CorrectinglensDistortionFilter(getContext());
            correctinglensDistortionFilter.a(0.0f, 0.0f);
            setFilter(correctinglensDistortionFilter);
        } else if (i2 == 2) {
            CorrectinglensDistortionFilter correctinglensDistortionFilter2 = new CorrectinglensDistortionFilter(getContext());
            correctinglensDistortionFilter2.a(this.e, this.f);
            setFilter(correctinglensDistortionFilter2);
        } else {
            setFilter((Filter) null);
        }
    }

    public void setDistort(float f2, float f3) {
        this.e = f2;
        this.f = f3;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        i--;
    }
}
