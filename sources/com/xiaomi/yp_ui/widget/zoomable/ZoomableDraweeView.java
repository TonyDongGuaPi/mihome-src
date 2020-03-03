package com.xiaomi.yp_ui.widget.zoomable;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.support.annotation.Nullable;
import android.support.v4.view.ScrollingView;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.GenericDraweeHierarchyInflater;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.DraweeView;
import com.xiaomi.yp_ui.widget.zoomable.ZoomableController;

public class ZoomableDraweeView extends DraweeView<GenericDraweeHierarchy> implements ScrollingView {
    private static final float HUGE_IMAGE_SCALE_FACTOR_THRESHOLD = 1.1f;
    private static final Class<?> TAG = ZoomableDraweeView.class;
    private boolean mAllowTouchInterceptionWhileZoomed = true;
    private final ControllerListener mControllerListener = new BaseControllerListener<Object>() {
        public void onFinalImageSet(String str, @Nullable Object obj, @Nullable Animatable animatable) {
            ZoomableDraweeView.this.onFinalImageSet();
        }

        public void onRelease(String str) {
            ZoomableDraweeView.this.onRelease();
        }
    };
    private DraweeController mHugeImageController;
    private final RectF mImageBounds = new RectF();
    private GestureDetector mTapGestureDetector;
    private final GestureListenerWrapper mTapListenerWrapper = new GestureListenerWrapper();
    private final RectF mViewBounds = new RectF();
    private ZoomableController mZoomableController;
    private final ZoomableController.Listener mZoomableListener = new ZoomableController.Listener() {
        public void a(Matrix matrix) {
            ZoomableDraweeView.this.onTransformChanged(matrix);
        }
    };

    public ZoomableDraweeView(Context context, GenericDraweeHierarchy genericDraweeHierarchy) {
        super(context);
        setHierarchy(genericDraweeHierarchy);
        init();
    }

    public ZoomableDraweeView(Context context) {
        super(context);
        inflateHierarchy(context, (AttributeSet) null);
        init();
    }

    public ZoomableDraweeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        inflateHierarchy(context, attributeSet);
        init();
    }

    public ZoomableDraweeView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        inflateHierarchy(context, attributeSet);
        init();
    }

    /* access modifiers changed from: protected */
    public void inflateHierarchy(Context context, @Nullable AttributeSet attributeSet) {
        GenericDraweeHierarchyBuilder actualImageScaleType = new GenericDraweeHierarchyBuilder(context.getResources()).setActualImageScaleType(ScalingUtils.ScaleType.FIT_CENTER);
        GenericDraweeHierarchyInflater.updateBuilder(actualImageScaleType, context, attributeSet);
        setAspectRatio(actualImageScaleType.getDesiredAspectRatio());
        setHierarchy(actualImageScaleType.build());
    }

    private void init() {
        this.mZoomableController = createZoomableController();
        this.mZoomableController.a(this.mZoomableListener);
        this.mTapGestureDetector = new GestureDetector(getContext(), this.mTapListenerWrapper);
    }

    /* access modifiers changed from: protected */
    public void getImageBounds(RectF rectF) {
        ((GenericDraweeHierarchy) getHierarchy()).getActualImageBounds(rectF);
    }

    /* access modifiers changed from: protected */
    public void getLimitBounds(RectF rectF) {
        rectF.set(0.0f, 0.0f, (float) getWidth(), (float) getHeight());
    }

    public void setZoomableController(ZoomableController zoomableController) {
        Preconditions.checkNotNull(zoomableController);
        this.mZoomableController.a((ZoomableController.Listener) null);
        this.mZoomableController = zoomableController;
        this.mZoomableController.a(this.mZoomableListener);
    }

    public ZoomableController getZoomableController() {
        return this.mZoomableController;
    }

    public boolean allowsTouchInterceptionWhileZoomed() {
        return this.mAllowTouchInterceptionWhileZoomed;
    }

    public void setAllowTouchInterceptionWhileZoomed(boolean z) {
        this.mAllowTouchInterceptionWhileZoomed = z;
    }

    public void setTapListener(GestureDetector.SimpleOnGestureListener simpleOnGestureListener) {
        this.mTapListenerWrapper.a(simpleOnGestureListener);
    }

    public void setIsLongpressEnabled(boolean z) {
        this.mTapGestureDetector.setIsLongpressEnabled(z);
    }

    public void setController(@Nullable DraweeController draweeController) {
        setControllers(draweeController, (DraweeController) null);
    }

    public void setControllers(@Nullable DraweeController draweeController, @Nullable DraweeController draweeController2) {
        setControllersInternal((DraweeController) null, (DraweeController) null);
        this.mZoomableController.b(false);
        setControllersInternal(draweeController, draweeController2);
    }

    private void setControllersInternal(@Nullable DraweeController draweeController, @Nullable DraweeController draweeController2) {
        removeControllerListener(getController());
        addControllerListener(draweeController);
        this.mHugeImageController = draweeController2;
        super.setController(draweeController);
    }

    private void maybeSetHugeImageController() {
        if (this.mHugeImageController != null && this.mZoomableController.q() > HUGE_IMAGE_SCALE_FACTOR_THRESHOLD) {
            setControllersInternal(this.mHugeImageController, (DraweeController) null);
        }
    }

    private void removeControllerListener(DraweeController draweeController) {
        if (draweeController instanceof AbstractDraweeController) {
            ((AbstractDraweeController) draweeController).removeControllerListener(this.mControllerListener);
        }
    }

    private void addControllerListener(DraweeController draweeController) {
        if (draweeController instanceof AbstractDraweeController) {
            ((AbstractDraweeController) draweeController).addControllerListener(this.mControllerListener);
        }
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        Object callerContext;
        int save = canvas.save();
        canvas.concat(this.mZoomableController.u());
        try {
            super.onDraw(canvas);
            canvas.restoreToCount(save);
        } catch (Exception e) {
            DraweeController controller = getController();
            if (controller == null || !(controller instanceof AbstractDraweeController) || (callerContext = ((AbstractDraweeController) controller).getCallerContext()) == null) {
                throw e;
            }
            throw new RuntimeException(String.format("Exception in onDraw, callerContext=%s", new Object[]{callerContext.toString()}), e);
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        FLog.v(getLogTag(), "onTouchEvent: %d, view %x, received", (Object) Integer.valueOf(actionMasked), (Object) Integer.valueOf(hashCode()));
        if (this.mTapGestureDetector.onTouchEvent(motionEvent)) {
            FLog.v(getLogTag(), "onTouchEvent: %d, view %x, handled by tap gesture detector", (Object) Integer.valueOf(actionMasked), (Object) Integer.valueOf(hashCode()));
            return true;
        } else if (this.mZoomableController.a(motionEvent)) {
            FLog.v(getLogTag(), "onTouchEvent: %d, view %x, handled by zoomable controller", (Object) Integer.valueOf(actionMasked), (Object) Integer.valueOf(hashCode()));
            if (!this.mAllowTouchInterceptionWhileZoomed && !this.mZoomableController.b()) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
            return true;
        } else if (super.onTouchEvent(motionEvent)) {
            FLog.v(getLogTag(), "onTouchEvent: %d, view %x, handled by the super", (Object) Integer.valueOf(actionMasked), (Object) Integer.valueOf(hashCode()));
            return true;
        } else {
            MotionEvent obtain = MotionEvent.obtain(motionEvent);
            obtain.setAction(3);
            this.mTapGestureDetector.onTouchEvent(obtain);
            this.mZoomableController.a(obtain);
            obtain.recycle();
            return false;
        }
    }

    public int computeHorizontalScrollRange() {
        return this.mZoomableController.w();
    }

    public int computeHorizontalScrollOffset() {
        return this.mZoomableController.x();
    }

    public int computeHorizontalScrollExtent() {
        return this.mZoomableController.y();
    }

    public int computeVerticalScrollRange() {
        return this.mZoomableController.z();
    }

    public int computeVerticalScrollOffset() {
        return this.mZoomableController.A();
    }

    public int computeVerticalScrollExtent() {
        return this.mZoomableController.B();
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        FLog.v(getLogTag(), "onLayout: view %x", (Object) Integer.valueOf(hashCode()));
        super.onLayout(z, i, i2, i3, i4);
        updateZoomableControllerBounds();
    }

    /* access modifiers changed from: private */
    public void onFinalImageSet() {
        FLog.v(getLogTag(), "onFinalImageSet: view %x", (Object) Integer.valueOf(hashCode()));
        if (!this.mZoomableController.k()) {
            updateZoomableControllerBounds();
            this.mZoomableController.b(true);
        }
    }

    /* access modifiers changed from: private */
    public void onRelease() {
        FLog.v(getLogTag(), "onRelease: view %x", (Object) Integer.valueOf(hashCode()));
        this.mZoomableController.b(false);
    }

    /* access modifiers changed from: protected */
    public void onTransformChanged(Matrix matrix) {
        FLog.v(getLogTag(), "onTransformChanged: view %x, transform: %s", (Object) Integer.valueOf(hashCode()), (Object) matrix);
        maybeSetHugeImageController();
        invalidate();
    }

    /* access modifiers changed from: protected */
    public void updateZoomableControllerBounds() {
        getImageBounds(this.mImageBounds);
        getLimitBounds(this.mViewBounds);
        this.mZoomableController.a(this.mImageBounds);
        this.mZoomableController.b(this.mViewBounds);
        FLog.v(getLogTag(), "updateZoomableControllerBounds: view %x, view bounds: %s, image bounds: %s", (Object) Integer.valueOf(hashCode()), (Object) this.mViewBounds, (Object) this.mImageBounds);
    }

    /* access modifiers changed from: protected */
    public Class<?> getLogTag() {
        return TAG;
    }

    /* access modifiers changed from: protected */
    public ZoomableController createZoomableController() {
        return AnimatedZoomableController.i();
    }
}
