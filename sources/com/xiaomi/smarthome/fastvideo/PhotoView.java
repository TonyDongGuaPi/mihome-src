package com.xiaomi.smarthome.fastvideo;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.renderscript.Matrix4f;
import android.support.annotation.FloatRange;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import com.drew.metadata.exif.makernotes.FujifilmMakernoteDirectory;
import com.xiaomi.smarthome.camera.VideoFrame;
import com.xiaomi.smarthome.camera.XmVideoViewGl;
import com.xiaomi.smarthome.fastvideo.CustomChooseConfig;
import com.xiaomi.smarthome.fastvideo.RendererUtils;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class PhotoView extends GLSurfaceView {
    public static final String TAG = "PhotoView";
    public static final int VIDEO_QUEUE_SIZE = 45;

    /* renamed from: a  reason: collision with root package name */
    private static final float f15887a = 1.0f;
    private static final float b = 1.0f;
    private static final float c = 0.01f;
    float alpha;
    float blue;
    int count;
    /* access modifiers changed from: private */
    public final PhotoRenderer d;
    /* access modifiers changed from: private */
    public float e;
    private boolean f;
    Photo firstPhoto;
    private boolean g;
    float green;
    /* access modifiers changed from: private */
    public OnScreenWindowChangedListener h;
    boolean isInitial;
    protected LinkedBlockingQueue<VideoFrame> mAVFrameQueue;
    long mAnimaStartTime;
    long mAnimaTime;
    float mAnimalScale;
    protected Filter mFilter;
    boolean mFlipX;
    boolean mFlipY;
    public int mHeight;
    Interpolator mInterpolator;
    boolean mIsFinger;
    boolean mIsFull;
    boolean mIsResume;
    int mMaxOffsetX;
    int mMaxOffsetY;
    public int mMaxTextureSize;
    protected Photo mMiddlePhoto;
    float mMiniScale;
    boolean mNeedMine;
    float mOffsetX;
    float mOffsetY;
    int mRotation;
    public float mScale;
    volatile int mSetRotation;
    float mStartScale;
    public float mStoredScaleLandscape;
    public float mStoredScalePortrait;
    float mTargeScaleOffset;
    protected VideoFrameDecoder mVideoFrameDecoder;
    public int mWidth;
    float red;

    public interface OnScreenWindowChangedListener {
        void a(boolean z, int i, int i2, int i3, int i4, int i5, int i6);
    }

    public static class RendererPhoto {

        /* renamed from: a  reason: collision with root package name */
        Photo f15896a;
        RectF b;
    }

    public void drawFrame() {
    }

    public void snap(XmVideoViewGl.PhotoSnapCallback photoSnapCallback) {
        this.d.a(photoSnapCallback);
    }

    public PhotoView(Context context) {
        super(context);
        this.mScale = 1.0f;
        this.e = 1.0f;
        this.mStoredScalePortrait = 0.0f;
        this.mStoredScaleLandscape = 0.0f;
        this.mIsFinger = false;
        this.mTargeScaleOffset = 0.0f;
        this.mAnimalScale = 0.0f;
        this.mOffsetX = 0.0f;
        this.mOffsetY = 0.0f;
        this.mNeedMine = false;
        this.mAnimaTime = 300;
        this.alpha = 1.0f;
        this.red = 0.0f;
        this.green = 0.0f;
        this.blue = 0.0f;
        this.mIsResume = false;
        this.isInitial = false;
        this.mIsFull = false;
        this.mRotation = 0;
        this.mSetRotation = 0;
        this.mFlipX = false;
        this.mFlipY = false;
        this.mInterpolator = new AccelerateDecelerateInterpolator();
        this.mAVFrameQueue = new LinkedBlockingQueue<>(45);
        this.f = false;
        this.count = 2;
        this.h = null;
        this.d = null;
    }

    public PhotoView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mScale = 1.0f;
        this.e = 1.0f;
        this.mStoredScalePortrait = 0.0f;
        this.mStoredScaleLandscape = 0.0f;
        this.mIsFinger = false;
        this.mTargeScaleOffset = 0.0f;
        this.mAnimalScale = 0.0f;
        this.mOffsetX = 0.0f;
        this.mOffsetY = 0.0f;
        this.mNeedMine = false;
        this.mAnimaTime = 300;
        this.alpha = 1.0f;
        this.red = 0.0f;
        this.green = 0.0f;
        this.blue = 0.0f;
        this.mIsResume = false;
        this.isInitial = false;
        this.mIsFull = false;
        this.mRotation = 0;
        this.mSetRotation = 0;
        this.mFlipX = false;
        this.mFlipY = false;
        this.mInterpolator = new AccelerateDecelerateInterpolator();
        this.mAVFrameQueue = new LinkedBlockingQueue<>(45);
        this.f = false;
        this.count = 2;
        this.h = null;
        if (a(context)) {
            this.d = new PhotoRenderer();
            setEGLContextClientVersion(2);
            setEGLConfigChooser(new CustomChooseConfig.ComponentSizeChooser(8, 8, 8, 8, 0, 0));
            getHolder().setFormat(-3);
            setRenderer(this.d);
            setRenderMode(0);
            return;
        }
        throw new RuntimeException("not support gles 2.0");
    }

    private boolean a(Context context) {
        return ((ActivityManager) context.getSystemService("activity")).getDeviceConfigurationInfo().reqGlEsVersion >= 131072;
    }

    public void setOnScreenWindowChangedListener(OnScreenWindowChangedListener onScreenWindowChangedListener) {
        this.h = onScreenWindowChangedListener;
    }

    public void reset() {
        this.mIsFinger = false;
        if (!this.mNeedMine || this.mIsFull) {
            this.mScale = 1.0f;
        } else {
            this.mScale = 0.0f;
        }
        this.mStartScale = this.mScale;
        this.mOffsetX = 0.0f;
        this.mOffsetY = 0.0f;
        this.mTargeScaleOffset = 0.0f;
        this.mAnimalScale = 0.0f;
    }

    public float getScale() {
        return this.mScale;
    }

    public float getFullScale() {
        return this.e;
    }

    public void setMiniScale(boolean z) {
        this.mNeedMine = z;
        if (z) {
            this.mScale = 0.0f;
        } else {
            this.mScale = 1.0f;
        }
    }

    public void setScale(float f2, boolean z) {
        Log.d(TAG, "setScale, scale:" + f2 + ", animal:" + z);
        if (f2 < this.mMiniScale) {
            f2 = this.mMiniScale;
        }
        if (z) {
            this.mTargeScaleOffset = f2 - this.mScale;
            this.mStartScale = this.mScale;
            this.mAnimalScale = f2;
            this.mAnimaStartTime = System.currentTimeMillis();
        } else {
            this.mScale = f2;
            this.mStartScale = this.mScale;
            this.mTargeScaleOffset = 0.0f;
            this.mAnimalScale = 0.0f;
        }
        if (((double) f2) > 1.0d) {
            this.mIsFinger = true;
        } else {
            this.mIsFinger = false;
        }
        requestRender();
    }

    public float getMiniScale() {
        return this.mMiniScale;
    }

    public float getOffsetX() {
        return this.mOffsetX;
    }

    public void setOffsetX(float f2) {
        this.mOffsetX = f2;
    }

    public float getOffsetY() {
        return this.mOffsetY;
    }

    public void setOffsetY(float f2) {
        this.mOffsetY = f2;
    }

    public void move(float f2, float f3, boolean z) {
        LogUtil.a(TAG, "move, x:" + f2 + ", y:" + f3 + ", isFinger:" + z);
        this.mOffsetX = this.mOffsetX + f2;
        this.mOffsetY = this.mOffsetY + f3;
        if (((double) this.mScale) > 1.0d) {
            this.mIsFinger = z;
        } else {
            this.mIsFinger = false;
        }
    }

    public void queue(Runnable runnable) {
        this.d.f15894a.add(runnable);
        requestRender();
    }

    public void remove(Runnable runnable) {
        this.d.f15894a.remove(runnable);
    }

    public void flush() {
        this.d.f15894a.clear();
    }

    public void setPhoto(Photo photo) {
        if (photo != null) {
            this.d.a(photo);
            this.mWidth = photo.b();
            this.mHeight = photo.c();
        }
    }

    public void setRenderMatrix(float[] fArr) {
        this.d.a(fArr);
    }

    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        this.mIsResume = true;
        queue(new Runnable() {
            public void run() {
                if (!PhotoView.this.isInitial) {
                    PhotoView.this.isInitial = true;
                    PhotoView.this.initial();
                }
            }
        });
    }

    public void onPause() {
        Log.d(TAG, "onPause");
        this.mIsResume = false;
        queueEvent(new Runnable() {
            public void run() {
                PhotoView.this.flush();
                if (PhotoView.this.isInitial) {
                    PhotoView.this.release();
                    PhotoView.this.isInitial = false;
                }
            }
        });
        super.onPause();
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        super.surfaceChanged(surfaceHolder, i, i2, i3);
        Log.d(TAG, "surfaceChanged");
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.g = true;
        super.surfaceCreated(surfaceHolder);
        Log.d(TAG, "surfaceCreated");
        onResume();
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        super.surfaceDestroyed(surfaceHolder);
        Log.d(TAG, "surfaceDestroyed");
        if (this.f) {
            onPause();
        }
    }

    /* access modifiers changed from: protected */
    public void initial() {
        LogUtil.a(TAG, "initial");
    }

    /* access modifiers changed from: protected */
    public void release() {
        LogUtil.a(TAG, "release");
        clearQueue();
        this.d.b();
        if (this.firstPhoto != null) {
            this.firstPhoto.e();
            this.firstPhoto = null;
        }
        if (this.mMiddlePhoto != null) {
            this.mMiddlePhoto.e();
            this.mMiddlePhoto = null;
        }
        Log.d(TAG, "after release");
    }

    public void setRotation(int i) {
        this.mSetRotation = i;
        requestRender();
    }

    public void setFlip(boolean z, boolean z2) {
        this.mFlipX = z;
        this.mFlipY = z2;
    }

    public void setFirstBitmap(final Bitmap bitmap) {
        if (bitmap != null) {
            queue(new Runnable() {
                public void run() {
                    PhotoView.this.firstPhoto = Photo.a(bitmap);
                    Log.d(PhotoView.TAG, "setFirstBitmap mScale:" + PhotoView.this.mScale);
                    PhotoView.this.setPhoto(PhotoView.this.firstPhoto);
                    bitmap.recycle();
                }
            });
        }
    }

    class PhotoRenderer implements GLSurfaceView.Renderer {

        /* renamed from: a  reason: collision with root package name */
        final Vector<Runnable> f15894a = new Vector<>();
        RendererUtils.RenderContext b;
        List<RendererPhoto> c = new ArrayList();
        int d;
        int e;
        int f;
        int g;
        int h;
        int i;
        int j;
        int k;
        /* access modifiers changed from: private */
        public Photo m;

        PhotoRenderer() {
        }

        /* access modifiers changed from: package-private */
        public void a(Photo photo) {
            this.m = photo;
            PhotoView.this.requestRender();
        }

        /* access modifiers changed from: package-private */
        public void a(float[] fArr) {
            RendererUtils.a(this.b, fArr);
        }

        public void onDrawFrame(GL10 gl10) {
            Runnable remove;
            synchronized (this.f15894a) {
                remove = !this.f15894a.isEmpty() ? this.f15894a.remove(0) : null;
            }
            if (remove != null) {
                remove.run();
            }
            gl10.glClear(FujifilmMakernoteDirectory.P);
            if (!this.f15894a.isEmpty()) {
                PhotoView.this.requestRender();
            }
            if (PhotoView.this.mIsResume) {
                RendererUtils.a(this.b, PhotoView.this.red, PhotoView.this.green, PhotoView.this.blue);
                PhotoView.this.drawFrame();
                if (this.m != null) {
                    a();
                    c(this.m.b(), this.m.c());
                    RendererUtils.a(this.b, this.m.a(), this.d, this.e);
                    for (RendererPhoto next : this.c) {
                        a(next.b, next.f15896a.b(), next.f15896a.c());
                        RendererUtils.a(this.b, next.f15896a.a(), this.d, this.e);
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void a() {
            if (PhotoView.this.mAnimaStartTime != 0) {
                long currentTimeMillis = System.currentTimeMillis() - PhotoView.this.mAnimaStartTime;
                if (currentTimeMillis > PhotoView.this.mAnimaTime) {
                    if (PhotoView.this.mAnimalScale > 0.0f) {
                        PhotoView.this.mScale = PhotoView.this.mAnimalScale;
                    } else {
                        PhotoView.this.mScale = PhotoView.this.mStartScale + PhotoView.this.mTargeScaleOffset;
                    }
                    PhotoView.this.mAnimaStartTime = 0;
                    PhotoView.this.mAnimalScale = 0.0f;
                    return;
                }
                Interpolator interpolator = PhotoView.this.mInterpolator;
                double d2 = (double) currentTimeMillis;
                Double.isNaN(d2);
                double d3 = (double) PhotoView.this.mAnimaTime;
                Double.isNaN(d3);
                float interpolation = interpolator.getInterpolation((float) ((d2 * 1.0d) / d3));
                PhotoView.this.mScale = PhotoView.this.mStartScale + (interpolation * PhotoView.this.mTargeScaleOffset);
                PhotoView.this.requestRender();
            }
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:100:0x027b  */
        /* JADX WARNING: Removed duplicated region for block: B:105:? A[RETURN, SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:14:0x0022  */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x0027  */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x0041  */
        /* JADX WARNING: Removed duplicated region for block: B:41:0x011f  */
        /* JADX WARNING: Removed duplicated region for block: B:70:0x021b  */
        /* JADX WARNING: Removed duplicated region for block: B:73:0x0224  */
        /* JADX WARNING: Removed duplicated region for block: B:76:0x0250  */
        /* JADX WARNING: Removed duplicated region for block: B:77:0x0252  */
        /* JADX WARNING: Removed duplicated region for block: B:79:0x0255  */
        /* JADX WARNING: Removed duplicated region for block: B:80:0x0257  */
        /* JADX WARNING: Removed duplicated region for block: B:82:0x025a  */
        /* JADX WARNING: Removed duplicated region for block: B:83:0x025c  */
        /* JADX WARNING: Removed duplicated region for block: B:86:0x0260  */
        /* JADX WARNING: Removed duplicated region for block: B:87:0x0262  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(int r18, int r19) {
            /*
                r17 = this;
                r0 = r17
                r9 = r18
                r1 = 640(0x280, float:8.97E-43)
                if (r9 != r1) goto L_0x0011
                r1 = 368(0x170, float:5.16E-43)
                r2 = r19
                if (r2 != r1) goto L_0x0013
                r1 = 360(0x168, float:5.04E-43)
                goto L_0x0014
            L_0x0011:
                r2 = r19
            L_0x0013:
                r1 = r2
            L_0x0014:
                r2 = 1920(0x780, float:2.69E-42)
                if (r9 != r2) goto L_0x001e
                r2 = 1088(0x440, float:1.525E-42)
                if (r1 != r2) goto L_0x001e
                r1 = 1080(0x438, float:1.513E-42)
            L_0x001e:
                r2 = 1280(0x500, float:1.794E-42)
                if (r9 != r2) goto L_0x0027
                r1 = 720(0x2d0, float:1.009E-42)
                r10 = 720(0x2d0, float:1.009E-42)
                goto L_0x0028
            L_0x0027:
                r10 = r1
            L_0x0028:
                android.renderscript.Matrix4f r1 = new android.renderscript.Matrix4f
                r1.<init>()
                float r2 = (float) r9
                float r3 = (float) r10
                float r4 = r2 / r3
                int r5 = r0.d
                float r5 = (float) r5
                int r6 = r0.e
                float r6 = (float) r6
                float r5 = r5 / r6
                float r6 = r5 / r4
                r7 = 1065353216(0x3f800000, float:1.0)
                int r8 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
                r11 = 0
                if (r8 >= 0) goto L_0x011f
                float r4 = r4 / r5
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                boolean r5 = r5.mIsFull
                if (r5 == 0) goto L_0x0052
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                r5.mMiniScale = r6
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float unused = r5.e = r7
                goto L_0x005b
            L_0x0052:
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                r5.mMiniScale = r6
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float unused = r5.e = r7
            L_0x005b:
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r5 = r5.mScale
                int r5 = (r5 > r11 ? 1 : (r5 == r11 ? 0 : -1))
                if (r5 != 0) goto L_0x006b
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r6 = r6.mMiniScale
                r5.mScale = r6
            L_0x006b:
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r6 = r0.d
                float r6 = (float) r6
                float r6 = r6 * r4
                com.xiaomi.smarthome.fastvideo.PhotoView r8 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r8 = r8.mScale
                float r6 = r6 * r8
                int r8 = r0.d
                float r8 = (float) r8
                float r6 = r6 - r8
                int r6 = (int) r6
                r5.mMaxOffsetX = r6
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r6 = r0.e
                float r6 = (float) r6
                com.xiaomi.smarthome.fastvideo.PhotoView r8 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r8 = r8.mScale
                float r6 = r6 * r8
                int r8 = r0.e
                float r8 = (float) r8
                float r6 = r6 - r8
                int r6 = (int) r6
                r5.mMaxOffsetY = r6
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r5 = r5.mOffsetX
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r6 = r6.mMaxOffsetX
                int r6 = -r6
                float r6 = (float) r6
                int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
                if (r5 >= 0) goto L_0x00a9
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r6 = r6.mMaxOffsetX
                int r6 = -r6
                float r6 = (float) r6
                r5.mOffsetX = r6
            L_0x00a9:
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r5 = r5.mOffsetX
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r6 = r6.mMaxOffsetX
                float r6 = (float) r6
                int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
                if (r5 <= 0) goto L_0x00bf
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r6 = r6.mMaxOffsetX
                float r6 = (float) r6
                r5.mOffsetX = r6
            L_0x00bf:
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r5 = r5.mOffsetY
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r6 = r6.mMaxOffsetY
                int r6 = -r6
                float r6 = (float) r6
                int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
                if (r5 >= 0) goto L_0x00d7
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r6 = r6.mMaxOffsetY
                int r6 = -r6
                float r6 = (float) r6
                r5.mOffsetY = r6
            L_0x00d7:
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r5 = r5.mOffsetY
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r6 = r6.mMaxOffsetY
                float r6 = (float) r6
                int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
                if (r5 <= 0) goto L_0x00ed
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r6 = r6.mMaxOffsetY
                float r6 = (float) r6
                r5.mOffsetY = r6
            L_0x00ed:
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r5 = r5.mScale
                float r4 = r4 * r5
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r5 = r5.mScale
                r1.scale(r4, r5, r11)
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r6 = r6.mOffsetX
                int r8 = r0.d
                float r8 = (float) r8
                float r8 = r8 * r4
                float r6 = r6 / r8
                com.xiaomi.smarthome.fastvideo.PhotoView r8 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r8 = r8.mOffsetY
                int r12 = r0.e
                float r12 = (float) r12
                float r12 = r12 * r5
                float r8 = r8 / r12
                com.xiaomi.smarthome.fastvideo.PhotoView r12 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r12 = r12.mScale
                double r12 = (double) r12
                r14 = 4607182418800017408(0x3ff0000000000000, double:1.0)
                int r16 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
                if (r16 >= 0) goto L_0x011a
                r8 = 0
            L_0x011a:
                r1.translate(r6, r8, r11)
                goto L_0x020b
            L_0x011f:
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                boolean r4 = r4.mIsFull
                if (r4 == 0) goto L_0x012a
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                r4.mMiniScale = r7
                goto L_0x0130
            L_0x012a:
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r5 = r7 / r6
                r4.mMiniScale = r5
            L_0x0130:
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float unused = r4.e = r7
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r4 = r4.mScale
                int r4 = (r4 > r11 ? 1 : (r4 == r11 ? 0 : -1))
                if (r4 != 0) goto L_0x0145
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r5 = r5.mMiniScale
                r4.mScale = r5
            L_0x0145:
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r5 = r0.d
                float r5 = (float) r5
                com.xiaomi.smarthome.fastvideo.PhotoView r8 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r8 = r8.mScale
                float r5 = r5 * r8
                int r8 = r0.d
                float r8 = (float) r8
                float r5 = r5 - r8
                int r5 = (int) r5
                r4.mMaxOffsetX = r5
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r5 = r0.e
                float r5 = (float) r5
                float r5 = r5 * r6
                com.xiaomi.smarthome.fastvideo.PhotoView r8 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r8 = r8.mScale
                float r5 = r5 * r8
                int r8 = r0.e
                float r8 = (float) r8
                float r5 = r5 - r8
                int r5 = (int) r5
                r4.mMaxOffsetY = r5
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r4 = r4.mOffsetX
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r5 = r5.mMaxOffsetX
                int r5 = -r5
                float r5 = (float) r5
                int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
                if (r4 >= 0) goto L_0x0183
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r5 = r5.mMaxOffsetX
                int r5 = -r5
                float r5 = (float) r5
                r4.mOffsetX = r5
            L_0x0183:
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r4 = r4.mOffsetX
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r5 = r5.mMaxOffsetX
                float r5 = (float) r5
                int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
                if (r4 <= 0) goto L_0x0199
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r5 = r5.mMaxOffsetX
                float r5 = (float) r5
                r4.mOffsetX = r5
            L_0x0199:
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r4 = r4.mOffsetY
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r5 = r5.mMaxOffsetY
                int r5 = -r5
                float r5 = (float) r5
                int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
                if (r4 >= 0) goto L_0x01b1
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r5 = r5.mMaxOffsetY
                int r5 = -r5
                float r5 = (float) r5
                r4.mOffsetY = r5
            L_0x01b1:
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r4 = r4.mOffsetY
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r5 = r5.mMaxOffsetY
                float r5 = (float) r5
                int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
                if (r4 <= 0) goto L_0x01c7
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r5 = r5.mMaxOffsetY
                float r5 = (float) r5
                r4.mOffsetY = r5
            L_0x01c7:
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r4 = r4.mScale
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r5 = r5.mScale
                float r5 = r5 * r6
                r1.scale(r4, r5, r11)
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                boolean r6 = r6.mIsFull
                if (r6 == 0) goto L_0x01f4
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r6 = r6.mScale
                int r6 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
                if (r6 != 0) goto L_0x01f4
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r6 = r6.mOffsetY
                int r6 = (r6 > r11 ? 1 : (r6 == r11 ? 0 : -1))
                if (r6 != 0) goto L_0x01f4
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView r8 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r8 = r8.mMaxOffsetY
                int r8 = -r8
                float r8 = (float) r8
                r6.mOffsetY = r8
            L_0x01f4:
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r6 = r6.mOffsetX
                int r8 = r0.d
                float r8 = (float) r8
                float r8 = r8 * r4
                float r6 = r6 / r8
                com.xiaomi.smarthome.fastvideo.PhotoView r8 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r8 = r8.mOffsetY
                int r12 = r0.e
                float r12 = (float) r12
                float r12 = r12 * r5
                float r8 = r8 / r12
                r1.translate(r6, r8, r11)
            L_0x020b:
                com.xiaomi.smarthome.fastvideo.PhotoView r12 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r12 = r12.mRotation
                float r12 = (float) r12
                r1.rotate(r12, r11, r11, r7)
                com.xiaomi.smarthome.fastvideo.PhotoView r11 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                boolean r11 = r11.mFlipX
                r12 = -1082130432(0xffffffffbf800000, float:-1.0)
                if (r11 == 0) goto L_0x021e
                r1.scale(r12, r7, r7)
            L_0x021e:
                com.xiaomi.smarthome.fastvideo.PhotoView r11 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                boolean r11 = r11.mFlipY
                if (r11 == 0) goto L_0x0227
                r1.scale(r7, r12, r7)
            L_0x0227:
                com.xiaomi.smarthome.fastvideo.RendererUtils$RenderContext r11 = r0.b
                float[] r1 = r1.getArray()
                r11.f15899a = r1
                float r1 = r7 / r4
                float r4 = r7 - r1
                float r4 = r4 - r6
                float r4 = r4 * r2
                r6 = 1073741824(0x40000000, float:2.0)
                float r4 = r4 / r6
                int r4 = (int) r4
                float r11 = (float) r4
                float r1 = r1 * r2
                float r11 = r11 + r1
                int r1 = (int) r11
                float r2 = r7 / r5
                float r5 = r2 - r7
                float r5 = r5 - r8
                float r5 = r5 * r3
                float r5 = r5 / r6
                int r5 = (int) r5
                float r6 = (float) r5
                float r2 = r2 * r3
                float r6 = r6 - r2
                int r2 = (int) r6
                r3 = 0
                if (r4 >= 0) goto L_0x0252
                r11 = 0
                goto L_0x0253
            L_0x0252:
                r11 = r4
            L_0x0253:
                if (r1 <= r9) goto L_0x0257
                r12 = r9
                goto L_0x0258
            L_0x0257:
                r12 = r1
            L_0x0258:
                if (r5 <= 0) goto L_0x025c
                r13 = 0
                goto L_0x025d
            L_0x025c:
                r13 = r5
            L_0x025d:
                int r3 = r3 - r10
                if (r2 >= r3) goto L_0x0262
                r14 = r3
                goto L_0x0263
            L_0x0262:
                r14 = r2
            L_0x0263:
                int r1 = r0.f
                if (r1 != r9) goto L_0x027b
                int r1 = r0.g
                if (r1 != r10) goto L_0x027b
                int r1 = r0.h
                if (r1 != r11) goto L_0x027b
                int r1 = r0.i
                if (r1 != r13) goto L_0x027b
                int r1 = r0.j
                if (r1 != r12) goto L_0x027b
                int r1 = r0.k
                if (r1 == r14) goto L_0x02a3
            L_0x027b:
                com.xiaomi.smarthome.fastvideo.PhotoView r1 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView$OnScreenWindowChangedListener r1 = r1.h
                if (r1 == 0) goto L_0x0297
                com.xiaomi.smarthome.fastvideo.PhotoView r1 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView$OnScreenWindowChangedListener r1 = r1.h
                com.xiaomi.smarthome.fastvideo.PhotoView r2 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                boolean r2 = r2.mIsFinger
                r3 = r18
                r4 = r10
                r5 = r11
                r6 = r13
                r7 = r12
                r8 = r14
                r1.a(r2, r3, r4, r5, r6, r7, r8)
            L_0x0297:
                r0.f = r9
                r0.g = r10
                r0.h = r11
                r0.i = r13
                r0.j = r12
                r0.k = r14
            L_0x02a3:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.fastvideo.PhotoView.PhotoRenderer.a(int, int):void");
        }

        /* access modifiers changed from: package-private */
        /* JADX WARNING: Removed duplicated region for block: B:101:? A[RETURN, SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:14:0x0022  */
        /* JADX WARNING: Removed duplicated region for block: B:15:0x0027  */
        /* JADX WARNING: Removed duplicated region for block: B:18:0x0043  */
        /* JADX WARNING: Removed duplicated region for block: B:41:0x011f  */
        /* JADX WARNING: Removed duplicated region for block: B:66:0x0205  */
        /* JADX WARNING: Removed duplicated region for block: B:69:0x020e  */
        /* JADX WARNING: Removed duplicated region for block: B:72:0x023a  */
        /* JADX WARNING: Removed duplicated region for block: B:73:0x023c  */
        /* JADX WARNING: Removed duplicated region for block: B:75:0x023f  */
        /* JADX WARNING: Removed duplicated region for block: B:76:0x0241  */
        /* JADX WARNING: Removed duplicated region for block: B:78:0x0244  */
        /* JADX WARNING: Removed duplicated region for block: B:79:0x0246  */
        /* JADX WARNING: Removed duplicated region for block: B:82:0x024a  */
        /* JADX WARNING: Removed duplicated region for block: B:83:0x024c  */
        /* JADX WARNING: Removed duplicated region for block: B:96:0x0265  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void b(int r18, int r19) {
            /*
                r17 = this;
                r0 = r17
                r9 = r19
                r1 = 640(0x280, float:8.97E-43)
                if (r9 != r1) goto L_0x0011
                r1 = 368(0x170, float:5.16E-43)
                r2 = r18
                if (r2 != r1) goto L_0x0013
                r1 = 360(0x168, float:5.04E-43)
                goto L_0x0014
            L_0x0011:
                r2 = r18
            L_0x0013:
                r1 = r2
            L_0x0014:
                r2 = 1920(0x780, float:2.69E-42)
                if (r9 != r2) goto L_0x001e
                r2 = 1088(0x440, float:1.525E-42)
                if (r1 != r2) goto L_0x001e
                r1 = 1080(0x438, float:1.513E-42)
            L_0x001e:
                r2 = 1280(0x500, float:1.794E-42)
                if (r9 != r2) goto L_0x0027
                r1 = 720(0x2d0, float:1.009E-42)
                r10 = 720(0x2d0, float:1.009E-42)
                goto L_0x0028
            L_0x0027:
                r10 = r1
            L_0x0028:
                android.renderscript.Matrix4f r1 = new android.renderscript.Matrix4f
                r1.<init>()
                float r2 = (float) r9
                float r3 = (float) r10
                float r4 = r2 / r3
                int r5 = r0.d
                float r5 = (float) r5
                int r6 = r0.e
                float r6 = (float) r6
                float r5 = r5 / r6
                float r6 = r5 / r4
                r7 = 1065353216(0x3f800000, float:1.0)
                int r8 = (r6 > r7 ? 1 : (r6 == r7 ? 0 : -1))
                r11 = 4607182418800017408(0x3ff0000000000000, double:1.0)
                r13 = 0
                if (r8 >= 0) goto L_0x011f
                float r4 = r4 / r5
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                boolean r5 = r5.mIsFull
                if (r5 == 0) goto L_0x0054
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float unused = r5.e = r7
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                r5.mMiniScale = r7
                goto L_0x005d
            L_0x0054:
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                r5.mMiniScale = r6
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float unused = r5.e = r7
            L_0x005d:
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r5 = r5.mScale
                int r5 = (r5 > r13 ? 1 : (r5 == r13 ? 0 : -1))
                if (r5 != 0) goto L_0x006d
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r6 = r6.mMiniScale
                r5.mScale = r6
            L_0x006d:
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r6 = r0.d
                float r6 = (float) r6
                float r6 = r6 * r4
                com.xiaomi.smarthome.fastvideo.PhotoView r8 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r8 = r8.mScale
                float r6 = r6 * r8
                int r8 = r0.d
                float r8 = (float) r8
                float r6 = r6 - r8
                int r6 = (int) r6
                r5.mMaxOffsetX = r6
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r6 = r0.e
                float r6 = (float) r6
                com.xiaomi.smarthome.fastvideo.PhotoView r8 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r8 = r8.mScale
                float r6 = r6 * r8
                int r8 = r0.e
                float r8 = (float) r8
                float r6 = r6 - r8
                int r6 = (int) r6
                r5.mMaxOffsetY = r6
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r5 = r5.mOffsetX
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r6 = r6.mMaxOffsetX
                int r6 = -r6
                float r6 = (float) r6
                int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
                if (r5 >= 0) goto L_0x00ab
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r6 = r6.mMaxOffsetX
                int r6 = -r6
                float r6 = (float) r6
                r5.mOffsetX = r6
            L_0x00ab:
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r5 = r5.mOffsetX
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r6 = r6.mMaxOffsetX
                float r6 = (float) r6
                int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
                if (r5 <= 0) goto L_0x00c1
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r6 = r6.mMaxOffsetX
                float r6 = (float) r6
                r5.mOffsetX = r6
            L_0x00c1:
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r5 = r5.mOffsetY
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r6 = r6.mMaxOffsetY
                int r6 = -r6
                float r6 = (float) r6
                int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
                if (r5 >= 0) goto L_0x00d9
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r6 = r6.mMaxOffsetY
                int r6 = -r6
                float r6 = (float) r6
                r5.mOffsetY = r6
            L_0x00d9:
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r5 = r5.mOffsetY
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r6 = r6.mMaxOffsetY
                float r6 = (float) r6
                int r5 = (r5 > r6 ? 1 : (r5 == r6 ? 0 : -1))
                if (r5 <= 0) goto L_0x00ef
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r6 = r6.mMaxOffsetY
                float r6 = (float) r6
                r5.mOffsetY = r6
            L_0x00ef:
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r5 = r5.mScale
                float r4 = r4 * r5
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r5 = r5.mScale
                r1.scale(r4, r5, r13)
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r6 = r6.mOffsetX
                int r8 = r0.d
                float r8 = (float) r8
                float r8 = r8 * r4
                float r6 = r6 / r8
                com.xiaomi.smarthome.fastvideo.PhotoView r8 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r8 = r8.mOffsetY
                int r14 = r0.e
                float r14 = (float) r14
                float r14 = r14 * r5
                float r8 = r8 / r14
                com.xiaomi.smarthome.fastvideo.PhotoView r14 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r14 = r14.mScale
                double r14 = (double) r14
                int r16 = (r14 > r11 ? 1 : (r14 == r11 ? 0 : -1))
                if (r16 >= 0) goto L_0x011a
                r8 = 0
            L_0x011a:
                r1.translate(r6, r8, r13)
                goto L_0x01f5
            L_0x011f:
                com.xiaomi.smarthome.fastvideo.PhotoView r8 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r4 = r4 / r5
                r8.mMiniScale = r4
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                boolean r4 = r4.mIsFull
                if (r4 == 0) goto L_0x0130
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float unused = r4.e = r6
                goto L_0x0135
            L_0x0130:
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float unused = r4.e = r7
            L_0x0135:
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r4 = r4.mScale
                int r4 = (r4 > r13 ? 1 : (r4 == r13 ? 0 : -1))
                if (r4 != 0) goto L_0x0145
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r5 = r5.mMiniScale
                r4.mScale = r5
            L_0x0145:
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r5 = r0.e
                float r5 = (float) r5
                float r5 = r5 * r6
                com.xiaomi.smarthome.fastvideo.PhotoView r8 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r8 = r8.mScale
                float r5 = r5 * r8
                int r8 = r0.e
                float r8 = (float) r8
                float r5 = r5 - r8
                int r5 = (int) r5
                r4.mMaxOffsetY = r5
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r5 = r0.d
                float r5 = (float) r5
                com.xiaomi.smarthome.fastvideo.PhotoView r8 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r8 = r8.mScale
                float r5 = r5 * r8
                int r8 = r0.d
                float r8 = (float) r8
                float r5 = r5 - r8
                int r5 = (int) r5
                r4.mMaxOffsetX = r5
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r4 = r4.mOffsetX
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r5 = r5.mMaxOffsetX
                int r5 = -r5
                float r5 = (float) r5
                int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
                if (r4 >= 0) goto L_0x0183
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r5 = r5.mMaxOffsetX
                int r5 = -r5
                float r5 = (float) r5
                r4.mOffsetX = r5
            L_0x0183:
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r4 = r4.mOffsetX
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r5 = r5.mMaxOffsetX
                float r5 = (float) r5
                int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
                if (r4 <= 0) goto L_0x0199
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r5 = r5.mMaxOffsetX
                float r5 = (float) r5
                r4.mOffsetX = r5
            L_0x0199:
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r4 = r4.mOffsetY
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r5 = r5.mMaxOffsetY
                int r5 = -r5
                float r5 = (float) r5
                int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
                if (r4 >= 0) goto L_0x01b1
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r5 = r5.mMaxOffsetY
                int r5 = -r5
                float r5 = (float) r5
                r4.mOffsetY = r5
            L_0x01b1:
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r4 = r4.mOffsetY
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r5 = r5.mMaxOffsetY
                float r5 = (float) r5
                int r4 = (r4 > r5 ? 1 : (r4 == r5 ? 0 : -1))
                if (r4 <= 0) goto L_0x01c7
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView r5 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r5 = r5.mMaxOffsetY
                float r5 = (float) r5
                r4.mOffsetY = r5
            L_0x01c7:
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r4 = r4.mScale
                float r5 = r6 * r4
                com.xiaomi.smarthome.fastvideo.PhotoView r4 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r4 = r4.mScale
                r1.scale(r4, r5, r13)
                com.xiaomi.smarthome.fastvideo.PhotoView r6 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r6 = r6.mOffsetX
                int r8 = r0.d
                float r8 = (float) r8
                float r8 = r8 * r4
                float r6 = r6 / r8
                com.xiaomi.smarthome.fastvideo.PhotoView r8 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r8 = r8.mOffsetY
                int r14 = r0.e
                float r14 = (float) r14
                float r14 = r14 * r5
                float r8 = r8 / r14
                com.xiaomi.smarthome.fastvideo.PhotoView r14 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                float r14 = r14.mScale
                double r14 = (double) r14
                int r16 = (r14 > r11 ? 1 : (r14 == r11 ? 0 : -1))
                if (r16 >= 0) goto L_0x01f2
                r6 = 0
            L_0x01f2:
                r1.translate(r6, r8, r13)
            L_0x01f5:
                com.xiaomi.smarthome.fastvideo.PhotoView r11 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                int r11 = r11.mRotation
                float r11 = (float) r11
                r1.rotate(r11, r13, r13, r7)
                com.xiaomi.smarthome.fastvideo.PhotoView r11 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                boolean r11 = r11.mFlipX
                r12 = -1082130432(0xffffffffbf800000, float:-1.0)
                if (r11 == 0) goto L_0x0208
                r1.scale(r12, r7, r7)
            L_0x0208:
                com.xiaomi.smarthome.fastvideo.PhotoView r11 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                boolean r11 = r11.mFlipY
                if (r11 == 0) goto L_0x0211
                r1.scale(r7, r12, r7)
            L_0x0211:
                com.xiaomi.smarthome.fastvideo.RendererUtils$RenderContext r11 = r0.b
                float[] r1 = r1.getArray()
                r11.f15899a = r1
                float r1 = r7 / r4
                float r4 = r7 - r1
                float r4 = r4 - r6
                float r4 = r4 * r2
                r6 = 1073741824(0x40000000, float:2.0)
                float r4 = r4 / r6
                int r4 = (int) r4
                float r11 = (float) r4
                float r1 = r1 * r2
                float r11 = r11 + r1
                int r1 = (int) r11
                float r2 = r7 / r5
                float r5 = r2 - r7
                float r5 = r5 - r8
                float r5 = r5 * r3
                float r5 = r5 / r6
                int r5 = (int) r5
                float r6 = (float) r5
                float r2 = r2 * r3
                float r6 = r6 - r2
                int r2 = (int) r6
                r3 = 0
                if (r4 >= 0) goto L_0x023c
                r11 = 0
                goto L_0x023d
            L_0x023c:
                r11 = r4
            L_0x023d:
                if (r1 <= r9) goto L_0x0241
                r12 = r9
                goto L_0x0242
            L_0x0241:
                r12 = r1
            L_0x0242:
                if (r5 <= 0) goto L_0x0246
                r13 = 0
                goto L_0x0247
            L_0x0246:
                r13 = r5
            L_0x0247:
                int r3 = r3 - r10
                if (r2 >= r3) goto L_0x024c
                r14 = r3
                goto L_0x024d
            L_0x024c:
                r14 = r2
            L_0x024d:
                int r1 = r0.f
                if (r1 != r9) goto L_0x0265
                int r1 = r0.g
                if (r1 != r10) goto L_0x0265
                int r1 = r0.h
                if (r1 != r11) goto L_0x0265
                int r1 = r0.i
                if (r1 != r13) goto L_0x0265
                int r1 = r0.j
                if (r1 != r12) goto L_0x0265
                int r1 = r0.k
                if (r1 == r14) goto L_0x028d
            L_0x0265:
                com.xiaomi.smarthome.fastvideo.PhotoView r1 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView$OnScreenWindowChangedListener r1 = r1.h
                if (r1 == 0) goto L_0x0281
                com.xiaomi.smarthome.fastvideo.PhotoView r1 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                com.xiaomi.smarthome.fastvideo.PhotoView$OnScreenWindowChangedListener r1 = r1.h
                com.xiaomi.smarthome.fastvideo.PhotoView r2 = com.xiaomi.smarthome.fastvideo.PhotoView.this
                boolean r2 = r2.mIsFinger
                r3 = r19
                r4 = r10
                r5 = r11
                r6 = r13
                r7 = r12
                r8 = r14
                r1.a(r2, r3, r4, r5, r6, r7, r8)
            L_0x0281:
                r0.f = r9
                r0.g = r10
                r0.h = r11
                r0.i = r13
                r0.j = r12
                r0.k = r14
            L_0x028d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.fastvideo.PhotoView.PhotoRenderer.b(int, int):void");
        }

        /* access modifiers changed from: package-private */
        public void c(int i2, int i3) {
            if (PhotoView.this.mRotation != PhotoView.this.mSetRotation) {
                PhotoView.this.mRotation = PhotoView.this.mSetRotation;
                PhotoView.this.mAnimaStartTime = 0;
                PhotoView.this.mScale = 0.0f;
            }
            if (PhotoView.this.mRotation == 90 || PhotoView.this.mRotation == 270) {
                b(i2, i3);
            } else {
                a(i2, i3);
            }
        }

        /* access modifiers changed from: package-private */
        public void a(RectF rectF, int i2, int i3) {
            if (PhotoView.this.mRotation != PhotoView.this.mSetRotation) {
                PhotoView.this.mRotation = PhotoView.this.mSetRotation;
                PhotoView.this.mAnimaStartTime = 0;
                PhotoView.this.mScale = 0.0f;
            }
            if (PhotoView.this.mRotation == 90 || PhotoView.this.mRotation == 270) {
                c(rectF, i2, i3);
            } else {
                b(rectF, i2, i3);
            }
        }

        /* access modifiers changed from: package-private */
        public void b(RectF rectF, int i2, int i3) {
            Matrix4f matrix4f = new Matrix4f();
            float b2 = (((float) this.d) / ((float) this.e)) / (((float) this.m.b()) / ((float) this.m.c()));
            float width = rectF.width() * ((float) this.m.b());
            float height = rectF.height() * ((float) this.m.c());
            if (b2 < 1.0f) {
                float c2 = (PhotoView.this.mScale * (((float) this.e) * width)) / ((float) (this.d * this.m.c()));
                float c3 = (PhotoView.this.mScale * height) / ((float) this.m.c());
                matrix4f.scale(c2, c3, 0.0f);
                matrix4f.translate((((rectF.centerX() * ((float) this.m.b())) - ((float) (this.m.b() / 2))) * 2.0f) / width, ((((float) (this.m.c() / 2)) - (rectF.centerY() * ((float) this.m.c()))) * 2.0f) / height, 0.0f);
                float f2 = PhotoView.this.mScale * 1.0f;
                float f3 = PhotoView.this.mScale;
                if (PhotoView.this.mOffsetX < ((float) (-PhotoView.this.mMaxOffsetX))) {
                    PhotoView.this.mOffsetX = (float) (-PhotoView.this.mMaxOffsetX);
                }
                if (PhotoView.this.mOffsetX > ((float) PhotoView.this.mMaxOffsetX)) {
                    PhotoView.this.mOffsetX = (float) PhotoView.this.mMaxOffsetX;
                }
                if (PhotoView.this.mOffsetY < ((float) (-PhotoView.this.mMaxOffsetY))) {
                    PhotoView.this.mOffsetY = (float) (-PhotoView.this.mMaxOffsetY);
                }
                if (PhotoView.this.mOffsetY > ((float) PhotoView.this.mMaxOffsetY)) {
                    PhotoView.this.mOffsetY = (float) PhotoView.this.mMaxOffsetY;
                }
                float f4 = (PhotoView.this.mOffsetX * PhotoView.this.mScale) / ((((float) this.d) * f2) * c2);
                float f5 = (PhotoView.this.mOffsetY * PhotoView.this.mScale) / ((((float) this.e) * f3) * c3);
                if (((double) PhotoView.this.mScale) < 1.0d) {
                    f5 = 0.0f;
                }
                matrix4f.translate(f4, f5, 0.0f);
            } else {
                float b3 = (PhotoView.this.mScale * (((float) this.d) * height)) / ((float) (this.e * this.m.b()));
                float b4 = (PhotoView.this.mScale * width) / ((float) this.m.b());
                matrix4f.scale(b4, b3, 0.0f);
                matrix4f.translate((((rectF.centerX() * ((float) this.m.b())) - ((float) (this.m.b() / 2))) * 2.0f) / width, ((((float) (this.m.c() / 2)) - (rectF.centerY() * ((float) this.m.c()))) * 2.0f) / ((height * b2) * b2), 0.0f);
                float f6 = PhotoView.this.mScale * 1.0f;
                float f7 = PhotoView.this.mScale;
                if (PhotoView.this.mOffsetX < ((float) (-PhotoView.this.mMaxOffsetX))) {
                    PhotoView.this.mOffsetX = (float) (-PhotoView.this.mMaxOffsetX);
                }
                if (PhotoView.this.mOffsetX > ((float) PhotoView.this.mMaxOffsetX)) {
                    PhotoView.this.mOffsetX = (float) PhotoView.this.mMaxOffsetX;
                }
                if (PhotoView.this.mOffsetY < ((float) (-PhotoView.this.mMaxOffsetY))) {
                    PhotoView.this.mOffsetY = (float) (-PhotoView.this.mMaxOffsetY);
                }
                if (PhotoView.this.mOffsetY > ((float) PhotoView.this.mMaxOffsetY)) {
                    PhotoView.this.mOffsetY = (float) PhotoView.this.mMaxOffsetY;
                }
                float f8 = (PhotoView.this.mOffsetX * PhotoView.this.mScale) / ((((float) this.d) * f7) * b4);
                float f9 = (PhotoView.this.mOffsetY * PhotoView.this.mScale) / ((((float) this.e) * f6) * b3);
                if (((double) PhotoView.this.mScale) < 1.0d) {
                    f9 = 0.0f;
                }
                matrix4f.translate(f8, f9, 0.0f);
            }
            matrix4f.rotate((float) PhotoView.this.mRotation, 0.0f, 0.0f, 1.0f);
            if (PhotoView.this.mFlipX) {
                matrix4f.scale(-1.0f, 1.0f, 1.0f);
            }
            if (PhotoView.this.mFlipY) {
                matrix4f.scale(1.0f, -1.0f, 1.0f);
            }
            this.b.f15899a = matrix4f.getArray();
        }

        /* access modifiers changed from: package-private */
        public void c(RectF rectF, int i2, int i3) {
            float f2;
            float f3;
            float f4;
            float f5;
            int i4 = i2;
            int i5 = i3;
            Matrix4f matrix4f = new Matrix4f();
            float f6 = (float) i5;
            float f7 = (float) i4;
            float f8 = f6 / f7;
            float f9 = ((float) this.d) / ((float) this.e);
            float f10 = f9 / f8;
            if (f10 < 1.0f) {
                float f11 = f8 / f9;
                if (PhotoView.this.mIsFull) {
                    PhotoView.this.mMiniScale = 1.0f;
                } else {
                    PhotoView.this.mMiniScale = f10;
                }
                if (PhotoView.this.mScale == 0.0f) {
                    PhotoView.this.mScale = PhotoView.this.mMiniScale;
                }
                PhotoView.this.mMaxOffsetX = (int) (((((float) this.d) * f11) * PhotoView.this.mScale) - ((float) this.d));
                PhotoView.this.mMaxOffsetY = (int) ((((float) this.e) * PhotoView.this.mScale) - ((float) this.e));
                if (PhotoView.this.mOffsetX < ((float) (-PhotoView.this.mMaxOffsetX))) {
                    PhotoView.this.mOffsetX = (float) (-PhotoView.this.mMaxOffsetX);
                }
                if (PhotoView.this.mOffsetX > ((float) PhotoView.this.mMaxOffsetX)) {
                    PhotoView.this.mOffsetX = (float) PhotoView.this.mMaxOffsetX;
                }
                if (PhotoView.this.mOffsetY < ((float) (-PhotoView.this.mMaxOffsetY))) {
                    PhotoView.this.mOffsetY = (float) (-PhotoView.this.mMaxOffsetY);
                }
                if (PhotoView.this.mOffsetY > ((float) PhotoView.this.mMaxOffsetY)) {
                    PhotoView.this.mOffsetY = (float) PhotoView.this.mMaxOffsetY;
                }
                f5 = f11 * PhotoView.this.mScale;
                f4 = PhotoView.this.mScale;
                matrix4f.scale(f5, f4, 0.0f);
                f3 = PhotoView.this.mOffsetX / (((float) this.d) * f5);
                f2 = PhotoView.this.mOffsetY / (((float) this.e) * f4);
                if (((double) PhotoView.this.mScale) < 1.0d) {
                    f2 = 0.0f;
                }
                matrix4f.translate(f3, f2, 0.0f);
            } else {
                if (PhotoView.this.mIsFull) {
                    PhotoView.this.mMiniScale = 1.0f;
                } else {
                    PhotoView.this.mMiniScale = f10;
                }
                if (PhotoView.this.mScale == 0.0f) {
                    PhotoView.this.mScale = PhotoView.this.mMiniScale;
                }
                PhotoView.this.mMaxOffsetY = (int) (((((float) this.e) * f10) * PhotoView.this.mScale) - ((float) this.e));
                PhotoView.this.mMaxOffsetX = (int) ((((float) this.d) * PhotoView.this.mScale) - ((float) this.d));
                if (PhotoView.this.mOffsetX < ((float) (-PhotoView.this.mMaxOffsetX))) {
                    PhotoView.this.mOffsetX = (float) (-PhotoView.this.mMaxOffsetX);
                }
                if (PhotoView.this.mOffsetX > ((float) PhotoView.this.mMaxOffsetX)) {
                    PhotoView.this.mOffsetX = (float) PhotoView.this.mMaxOffsetX;
                }
                if (PhotoView.this.mOffsetY < ((float) (-PhotoView.this.mMaxOffsetY))) {
                    PhotoView.this.mOffsetY = (float) (-PhotoView.this.mMaxOffsetY);
                }
                if (PhotoView.this.mOffsetY > ((float) PhotoView.this.mMaxOffsetY)) {
                    PhotoView.this.mOffsetY = (float) PhotoView.this.mMaxOffsetY;
                }
                f4 = f10 * PhotoView.this.mScale;
                f5 = PhotoView.this.mScale;
                matrix4f.scale(f5, f4, 0.0f);
                f3 = PhotoView.this.mOffsetX / (((float) this.d) * f5);
                f2 = PhotoView.this.mOffsetY / (((float) this.e) * f4);
                if (((double) PhotoView.this.mScale) < 1.0d) {
                    f3 = 0.0f;
                }
                matrix4f.translate(f3, f2, 0.0f);
            }
            matrix4f.rotate((float) PhotoView.this.mRotation, 0.0f, 0.0f, 1.0f);
            if (PhotoView.this.mFlipX) {
                matrix4f.scale(-1.0f, 1.0f, 1.0f);
            }
            if (PhotoView.this.mFlipY) {
                matrix4f.scale(1.0f, -1.0f, 1.0f);
            }
            this.b.f15899a = matrix4f.getArray();
            float f12 = 1.0f / f5;
            int i6 = (int) ((((1.0f - f12) - f3) * f6) / 2.0f);
            int i7 = (int) (((float) i6) + (f12 * f6));
            float f13 = 1.0f / f4;
            int i8 = (int) ((((f13 - 1.0f) - f2) * f7) / 2.0f);
            int i9 = (int) (((float) i8) - (f13 * f7));
            int i10 = i6 < 0 ? 0 : i6;
            int i11 = i7 > i5 ? i5 : i7;
            int i12 = i8 > 0 ? 0 : i8;
            int i13 = 0 - i4;
            int i14 = i9 < i13 ? i13 : i9;
            if (this.f != i5 || this.g != i4 || this.h != i10 || this.i != i12 || this.j != i11 || this.k != i14) {
                if (PhotoView.this.h != null) {
                    PhotoView.this.h.a(PhotoView.this.mIsFinger, i3, i2, i10, i12, i11, i14);
                }
                this.f = i5;
                this.g = i4;
                this.h = i10;
                this.i = i12;
                this.j = i11;
                this.k = i14;
            }
        }

        public void onSurfaceChanged(GL10 gl10, int i2, int i3) {
            this.d = i2;
            this.e = i3;
        }

        public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
            Log.d(PhotoView.TAG, "onSurfaceCreated" + PhotoView.this.toString());
            GLES20.glEnable(GlslFilter.f);
            IntBuffer allocate = IntBuffer.allocate(1);
            GLES20.glGetIntegerv(3379, allocate);
            PhotoView.this.mMaxTextureSize = allocate.get(0);
            GLES20.glGetError();
            this.b = RendererUtils.b();
        }

        public void b() {
            RendererUtils.b(this.b);
            for (RendererPhoto rendererPhoto : this.c) {
                rendererPhoto.f15896a.e();
            }
            if (this.m != null) {
                this.m.e();
                this.m = null;
            }
            this.c.clear();
        }

        public void a(String str) {
            Bitmap a2;
            if (this.m != null && this.m.a() >= 0 && (a2 = RendererUtils.a(this.m.a(), this.m.b(), this.m.c())) != null) {
                try {
                    FileOutputStream fileOutputStream = new FileOutputStream(str);
                    a2.compress(Bitmap.CompressFormat.JPEG, 90, fileOutputStream);
                    fileOutputStream.close();
                } catch (IOException unused) {
                }
                a2.recycle();
            }
        }

        public void a(final XmVideoViewGl.PhotoSnapCallback photoSnapCallback) {
            if (photoSnapCallback != null) {
                if (this.m != null) {
                    PhotoView.this.queue(new Runnable() {
                        public void run() {
                            Bitmap a2 = RendererUtils.a(PhotoRenderer.this.m.a(), PhotoRenderer.this.m.b(), PhotoRenderer.this.m.c());
                            if (photoSnapCallback != null) {
                                photoSnapCallback.onSnap(a2);
                            }
                        }
                    });
                    PhotoView.this.requestRender();
                } else if (photoSnapCallback != null) {
                    photoSnapCallback.onSnap((Bitmap) null);
                }
            }
        }
    }

    public boolean isInitial() {
        return this.isInitial;
    }

    public void setIsFull(boolean z) {
        this.mIsFull = z;
        if (this.mIsFull) {
            this.mStoredScalePortrait = this.mScale;
            reset();
            if (this.mStoredScaleLandscape != 0.0f) {
                this.mScale = this.mStoredScaleLandscape;
            }
        } else {
            this.mStoredScaleLandscape = Math.max(1.0f, this.mScale);
            reset();
            if (this.mStoredScalePortrait != 0.0f) {
                this.mScale = this.mStoredScalePortrait;
            }
        }
        requestRender();
    }

    public void setIsFullForRN(boolean z) {
        this.mIsFull = z;
        if (this.mIsFull) {
            this.mStoredScalePortrait = this.mScale;
            reset();
            if (this.mStoredScaleLandscape != 0.0f) {
                this.mScale = this.mStoredScaleLandscape;
            }
        } else {
            this.mStoredScaleLandscape = 1.0f;
            reset();
            if (this.mStoredScalePortrait != 0.0f) {
                this.mScale = this.mStoredScalePortrait;
            }
        }
        requestRender();
    }

    public int addPhoto(final XmVideoViewGl.IDrawBitmapCallback iDrawBitmapCallback, final Bitmap bitmap, final RectF rectF) {
        queue(new Runnable() {
            public void run() {
                RendererPhoto rendererPhoto = new RendererPhoto();
                rendererPhoto.b = rectF;
                rendererPhoto.f15896a = Photo.a(bitmap);
                PhotoView.this.d.c.add(rendererPhoto);
                iDrawBitmapCallback.onBitmapCreated(PhotoView.this.d.c.size() - 1);
            }
        });
        return 0;
    }

    public void updatePhoto(final int i, final Bitmap bitmap, final RectF rectF) {
        queue(new Runnable() {
            public void run() {
                if (PhotoView.this.d.c.size() > i) {
                    RendererPhoto rendererPhoto = PhotoView.this.d.c.get(i);
                    rendererPhoto.b = rectF;
                    rendererPhoto.f15896a.b(bitmap);
                }
            }
        });
    }

    public void removePhoto(final int i) {
        queue(new Runnable() {
            public void run() {
                if (PhotoView.this.d.c.size() > i) {
                    PhotoView.this.d.c.remove(i).f15896a.e();
                }
            }
        });
    }

    public void setAlpha(@FloatRange(from = 0.0d, to = 1.0d) float f2) {
        this.alpha = f2;
        if (this.d.b != null) {
            RendererUtils.a(this.d.b, (int) (f2 * 255.0f));
        }
    }

    public void setBg(float f2, float f3, float f4) {
        this.red = f2;
        this.green = f3;
        this.blue = f4;
    }

    public void clearQueue() {
        if (this.mAVFrameQueue != null) {
            this.mAVFrameQueue.clear();
        }
    }

    public void setAutoRelease(boolean z) {
        this.f = z;
    }
}
