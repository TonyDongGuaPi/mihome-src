package com.mi.global.shop.widget.gallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.mi.global.shop.widget.gallery.GestureDetector;
import com.mi.global.shop.widget.gallery.ImageViewTouchBase;
import com.mi.global.shop.widget.gallery.ScaleGestureDetector;
import com.mi.log.LogUtil;

public class ZoomImageView extends ImageViewTouchBase {
    private static final String TAG = "ZoomImageView";
    private GestureDetector mGestureDetector = new GestureDetector(getContext(), new MyGestureListener(), (Handler) null, true);
    /* access modifiers changed from: private */
    public OnImageTapListener mImageTapListener;
    /* access modifiers changed from: private */
    public boolean mOnScale;
    private ScaleGestureDetector mScaleGestureDetector = new ScaleGestureDetector(getContext(), new MyGestureScaleListener());

    public interface OnImageTapListener {
        void a();
    }

    public /* bridge */ /* synthetic */ void clear() {
        super.clear();
    }

    public /* bridge */ /* synthetic */ void setImageBitmap(Bitmap bitmap) {
        super.setImageBitmap(bitmap);
    }

    public /* bridge */ /* synthetic */ void setImageBitmapResetBase(Bitmap bitmap, boolean z) {
        super.setImageBitmapResetBase(bitmap, z);
    }

    public /* bridge */ /* synthetic */ void setImageRotateBitmapResetBase(RotateBitmap rotateBitmap, boolean z) {
        super.setImageRotateBitmapResetBase(rotateBitmap, z);
    }

    public /* bridge */ /* synthetic */ void setRecycler(ImageViewTouchBase.Recycler recycler) {
        super.setRecycler(recycler);
    }

    public void setOnTapListener(OnImageTapListener onImageTapListener) {
        this.mImageTapListener = onImageTapListener;
    }

    public ZoomImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public boolean isZoomedOut() {
        return ((double) (getScale() - 1.0f)) > 0.1d;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        LogUtil.b(TAG, "The action is:" + motionEvent.getAction());
        if (!this.mOnScale) {
            this.mGestureDetector.a(motionEvent);
        }
        try {
            this.mScaleGestureDetector.a(motionEvent);
            return true;
        } catch (Exception unused) {
            return true;
        }
    }

    private class MyGestureScaleListener implements ScaleGestureDetector.OnScaleGestureListener {
        private float b;
        private float c;
        private float d;

        private MyGestureScaleListener() {
        }

        public boolean a(ScaleGestureDetector scaleGestureDetector, float f, float f2) {
            LogUtil.b(ZoomImageView.TAG, "gesture onScale");
            float scale = ZoomImageView.this.getScale() * scaleGestureDetector.f();
            this.b = scale;
            this.c = f;
            this.d = f2;
            if (!scaleGestureDetector.a()) {
                return true;
            }
            ZoomImageView.this.zoomToNoCenter(scale, f, f2);
            return true;
        }

        public boolean a(ScaleGestureDetector scaleGestureDetector) {
            LogUtil.b(ZoomImageView.TAG, "gesture onScaleStart");
            boolean unused = ZoomImageView.this.mOnScale = true;
            return true;
        }

        public void b(ScaleGestureDetector scaleGestureDetector) {
            if (this.b > ZoomImageView.this.mMaxZoom) {
                ZoomImageView.this.zoomToNoCenterWithAni(this.b / ZoomImageView.this.mMaxZoom, 1.0f, this.c, this.b);
                this.b = ZoomImageView.this.mMaxZoom;
                ZoomImageView.this.zoomToNoCenterValue(this.b, this.c, this.d);
            } else if (this.b < ZoomImageView.this.mMinZoom) {
                ZoomImageView.this.zoomToNoCenterWithAni(this.b, ZoomImageView.this.mMinZoom, this.c, this.d);
                this.b = ZoomImageView.this.mMinZoom;
                ZoomImageView.this.zoomToNoCenterValue(this.b, this.c, this.d);
            } else {
                ZoomImageView.this.zoomToNoCenter(this.b, this.c, this.d);
            }
            boolean unused = ZoomImageView.this.mOnScale = false;
        }
    }

    private class MyGestureListener extends GestureDetector.SimpleOnGestureListener {
        private MyGestureListener() {
        }

        public boolean a(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            LogUtil.b(ZoomImageView.TAG, "gesture onScroll");
            if (ZoomImageView.this.mOnScale) {
                return true;
            }
            ZoomImageView.this.panBy(-f, -f2);
            ZoomImageView.this.center(true, true);
            return true;
        }

        public boolean e(MotionEvent motionEvent) {
            return super.e(motionEvent);
        }

        public boolean a(MotionEvent motionEvent) {
            if (ZoomImageView.this.mImageTapListener == null) {
                return true;
            }
            ZoomImageView.this.mImageTapListener.a();
            return true;
        }

        public boolean b(MotionEvent motionEvent) {
            if (ZoomImageView.this.mBaseZoom < 1.0f) {
                if (ZoomImageView.this.getScale() > 2.0f) {
                    ZoomImageView.this.zoomTo(1.0f);
                    return true;
                }
                ZoomImageView.this.zoomToPoint(3.0f, motionEvent.getX(), motionEvent.getY());
                return true;
            } else if (ZoomImageView.this.getScale() > (ZoomImageView.this.mMinZoom + ZoomImageView.this.mMaxZoom) / 2.0f) {
                ZoomImageView.this.zoomTo(ZoomImageView.this.mMinZoom);
                return true;
            } else {
                ZoomImageView.this.zoomToPoint(ZoomImageView.this.mMaxZoom, motionEvent.getX(), motionEvent.getY());
                return true;
            }
        }
    }
}
