package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.surfaceview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.os.Handler;
import android.os.Looper;
import android.view.Surface;
import android.view.TextureView;
import com.facebook.common.logging.FLog;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.common.ReactConstants;
import com.facebook.react.uimanager.LayoutShadowNode;
import com.facebook.react.uimanager.NativeViewHierarchyOptimizer;
import com.facebook.react.uimanager.ReactShadowNode;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.views.art.ARTVirtualNode;
import javax.annotation.Nullable;

public class MHRSurfaceViewShadowNode extends LayoutShadowNode implements TextureView.SurfaceTextureListener, LifecycleEventListener {

    /* renamed from: a  reason: collision with root package name */
    static final /* synthetic */ boolean f17629a = (!MHRSurfaceViewShadowNode.class.desiredAssertionStatus());
    @Nullable
    private Surface b;
    @Nullable
    private SurfaceTexture c;
    @Nullable
    private Integer d;
    private Handler e;

    public boolean isVirtual() {
        return false;
    }

    public boolean isVirtualAnchor() {
        return true;
    }

    public void onHostDestroy() {
    }

    public void onHostPause() {
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    @ReactProp(customType = "Color", name = "backgroundColor")
    public void setBackgroundColor(Integer num) {
        this.d = num;
        markUpdated();
    }

    public boolean dispatchUpdates(float f, float f2, UIViewOperationQueue uIViewOperationQueue, NativeViewHierarchyOptimizer nativeViewHierarchyOptimizer) {
        boolean dispatchUpdates = super.dispatchUpdates(f, f2, uIViewOperationQueue, nativeViewHierarchyOptimizer);
        if (this.e == null) {
            this.e = new Handler(Looper.myLooper());
        }
        if (hasUnseenUpdates() || dispatchUpdates) {
            a(uIViewOperationQueue);
        }
        return dispatchUpdates;
    }

    private void a(UIViewOperationQueue uIViewOperationQueue) {
        if (this.c != null) {
            this.c.setDefaultBufferSize(getScreenWidth(), getScreenHeight());
        }
        a();
        uIViewOperationQueue.enqueueUpdateExtraData(getReactTag(), this);
    }

    /* access modifiers changed from: private */
    public void a() {
        if (this.b == null || !this.b.isValid()) {
            a((ReactShadowNode) this);
            return;
        }
        try {
            Canvas lockCanvas = this.b.lockCanvas((Rect) null);
            lockCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
            if (this.d != null) {
                lockCanvas.drawColor(this.d.intValue());
            }
            Paint paint = new Paint();
            for (int i = 0; i < getChildCount(); i++) {
                ARTVirtualNode aRTVirtualNode = (ARTVirtualNode) getChildAt(i);
                aRTVirtualNode.draw(lockCanvas, paint, 1.0f);
                aRTVirtualNode.markUpdateSeen();
            }
            if (this.b != null) {
                this.b.unlockCanvasAndPost(lockCanvas);
            }
        } catch (IllegalArgumentException | IllegalStateException e2) {
            FLog.e(ReactConstants.TAG, e2.getClass().getSimpleName() + " in Surface.unlockCanvasAndPost");
        }
    }

    private void a(ReactShadowNode reactShadowNode) {
        for (int i = 0; i < reactShadowNode.getChildCount(); i++) {
            ReactShadowNode childAt = reactShadowNode.getChildAt(i);
            childAt.markUpdateSeen();
            a(childAt);
        }
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        this.c = surfaceTexture;
        this.b = new Surface(surfaceTexture);
        if (!(i == getScreenWidth() && i2 == getScreenHeight())) {
            if (f17629a || this.c != null) {
                this.c.setDefaultBufferSize(getScreenWidth(), getScreenHeight());
            } else {
                throw new AssertionError();
            }
        }
        if (this.e != null) {
            this.e.post(new Runnable() {
                public final void run() {
                    MHRSurfaceViewShadowNode.this.a();
                }
            });
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        surfaceTexture.release();
        this.c = null;
        this.b = null;
        return true;
    }

    public void setThemedContext(ThemedReactContext themedReactContext) {
        super.setThemedContext(themedReactContext);
        if (themedReactContext != null) {
            themedReactContext.addLifecycleEventListener(this);
        }
    }

    public void dispose() {
        if (getThemedContext() != null) {
            getThemedContext().removeLifecycleEventListener(this);
        }
        super.dispose();
    }

    public void onHostResume() {
        a();
    }
}
