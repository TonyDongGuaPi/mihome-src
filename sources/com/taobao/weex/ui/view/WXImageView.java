package com.taobao.weex.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.component.WXImage;
import com.taobao.weex.ui.view.gesture.WXGesture;
import com.taobao.weex.ui.view.gesture.WXGestureObservable;
import com.taobao.weex.utils.ImageDrawable;
import com.taobao.weex.utils.WXLogUtils;
import java.lang.ref.WeakReference;
import java.util.Arrays;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXImageView extends ImageView implements WXImage.Measurable, IRenderResult<WXImage>, IRenderStatus<WXImage>, WXGestureObservable {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private float[] borderRadius;
    private boolean enableBitmapAutoManage = true;
    private boolean gif;
    private boolean isBitmapReleased = false;
    private boolean mOutWindowVisibilityChangedReally;
    private WeakReference<WXImage> mWeakReference;
    private WXGesture wxGesture;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(7713650621389941067L, "com/taobao/weex/ui/view/WXImageView", 90);
        $jacocoData = a2;
        return a2;
    }

    public /* synthetic */ void holdComponent(WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        holdComponent((WXImage) wXComponent);
        $jacocoInit[88] = true;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public WXImageView(Context context) {
        super(context);
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[0] = true;
    }

    public void setImageResource(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        Drawable drawable = getResources().getDrawable(i);
        $jacocoInit[1] = true;
        setImageDrawable(drawable);
        $jacocoInit[2] = true;
    }

    public void setImageDrawable(@Nullable Drawable drawable, boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.gif = z;
        $jacocoInit[3] = true;
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if (layoutParams == null) {
            $jacocoInit[4] = true;
        } else {
            $jacocoInit[5] = true;
            ImageView.ScaleType scaleType = getScaleType();
            float[] fArr = this.borderRadius;
            int i = layoutParams.width;
            $jacocoInit[6] = true;
            int i2 = layoutParams.height;
            $jacocoInit[7] = true;
            $jacocoInit[8] = true;
            Drawable createImageDrawable = ImageDrawable.createImageDrawable(drawable, scaleType, fArr, (i - getPaddingLeft()) - getPaddingRight(), (i2 - getPaddingTop()) - getPaddingBottom(), z);
            if (!(createImageDrawable instanceof ImageDrawable)) {
                $jacocoInit[9] = true;
            } else {
                ImageDrawable imageDrawable = (ImageDrawable) createImageDrawable;
                $jacocoInit[10] = true;
                if (Arrays.equals(imageDrawable.getCornerRadii(), this.borderRadius)) {
                    $jacocoInit[11] = true;
                } else {
                    $jacocoInit[12] = true;
                    imageDrawable.setCornerRadii(this.borderRadius);
                    $jacocoInit[13] = true;
                }
            }
            super.setImageDrawable(createImageDrawable);
            if (this.mWeakReference == null) {
                $jacocoInit[14] = true;
            } else {
                $jacocoInit[15] = true;
                WXImage wXImage = (WXImage) this.mWeakReference.get();
                if (wXImage == null) {
                    $jacocoInit[16] = true;
                } else {
                    $jacocoInit[17] = true;
                    wXImage.readyToRender();
                    $jacocoInit[18] = true;
                }
            }
        }
        $jacocoInit[19] = true;
    }

    public void setImageDrawable(@Nullable Drawable drawable) {
        boolean[] $jacocoInit = $jacocoInit();
        setImageDrawable(drawable, this.gif);
        $jacocoInit[20] = true;
    }

    public void setImageBitmap(@Nullable Bitmap bitmap) {
        BitmapDrawable bitmapDrawable;
        boolean[] $jacocoInit = $jacocoInit();
        if (bitmap == null) {
            bitmapDrawable = null;
            $jacocoInit[21] = true;
        } else {
            BitmapDrawable bitmapDrawable2 = new BitmapDrawable(getResources(), bitmap);
            $jacocoInit[22] = true;
            bitmapDrawable = bitmapDrawable2;
        }
        setImageDrawable(bitmapDrawable);
        $jacocoInit[23] = true;
    }

    public void registerGestureListener(WXGesture wXGesture) {
        boolean[] $jacocoInit = $jacocoInit();
        this.wxGesture = wXGesture;
        $jacocoInit[24] = true;
    }

    public WXGesture getGestureListener() {
        boolean[] $jacocoInit = $jacocoInit();
        WXGesture wXGesture = this.wxGesture;
        $jacocoInit[25] = true;
        return wXGesture;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        boolean[] $jacocoInit = $jacocoInit();
        boolean onTouchEvent = super.onTouchEvent(motionEvent);
        if (this.wxGesture == null) {
            $jacocoInit[26] = true;
        } else {
            $jacocoInit[27] = true;
            onTouchEvent |= this.wxGesture.onTouch(this, motionEvent);
            $jacocoInit[28] = true;
        }
        $jacocoInit[29] = true;
        return onTouchEvent;
    }

    public void setBorderRadius(@NonNull float[] fArr) {
        boolean[] $jacocoInit = $jacocoInit();
        this.borderRadius = fArr;
        $jacocoInit[30] = true;
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean[] $jacocoInit = $jacocoInit();
        super.onLayout(z, i, i2, i3, i4);
        if (!z) {
            $jacocoInit[31] = true;
        } else {
            $jacocoInit[32] = true;
            setImageDrawable(getDrawable(), this.gif);
            $jacocoInit[33] = true;
        }
        $jacocoInit[34] = true;
    }

    public void holdComponent(WXImage wXImage) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mWeakReference = new WeakReference<>(wXImage);
        $jacocoInit[35] = true;
    }

    @Nullable
    public WXImage getComponent() {
        WXImage wXImage;
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mWeakReference != null) {
            wXImage = (WXImage) this.mWeakReference.get();
            $jacocoInit[36] = true;
        } else {
            wXImage = null;
            $jacocoInit[37] = true;
        }
        $jacocoInit[38] = true;
        return wXImage;
    }

    public int getNaturalWidth() {
        boolean[] $jacocoInit = $jacocoInit();
        Drawable drawable = getDrawable();
        if (drawable == null) {
            $jacocoInit[39] = true;
        } else if (drawable instanceof ImageDrawable) {
            $jacocoInit[40] = true;
            int bitmapWidth = ((ImageDrawable) drawable).getBitmapWidth();
            $jacocoInit[41] = true;
            return bitmapWidth;
        } else if (drawable instanceof BitmapDrawable) {
            $jacocoInit[42] = true;
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap != null) {
                $jacocoInit[43] = true;
                int width = bitmap.getWidth();
                $jacocoInit[44] = true;
                return width;
            }
            WXLogUtils.w("WXImageView", "Bitmap on " + drawable.toString() + " is null");
            $jacocoInit[45] = true;
        } else {
            WXLogUtils.w("WXImageView", "Not supported drawable type: " + drawable.getClass().getSimpleName());
            $jacocoInit[46] = true;
        }
        $jacocoInit[47] = true;
        return -1;
    }

    public int getNaturalHeight() {
        boolean[] $jacocoInit = $jacocoInit();
        Drawable drawable = getDrawable();
        if (drawable == null) {
            $jacocoInit[48] = true;
        } else if (drawable instanceof ImageDrawable) {
            $jacocoInit[49] = true;
            int bitmapHeight = ((ImageDrawable) drawable).getBitmapHeight();
            $jacocoInit[50] = true;
            return bitmapHeight;
        } else if (drawable instanceof BitmapDrawable) {
            $jacocoInit[51] = true;
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            if (bitmap != null) {
                $jacocoInit[52] = true;
                int height = bitmap.getHeight();
                $jacocoInit[53] = true;
                return height;
            }
            WXLogUtils.w("WXImageView", "Bitmap on " + drawable.toString() + " is null");
            $jacocoInit[54] = true;
        } else {
            WXLogUtils.w("WXImageView", "Not supported drawable type: " + drawable.getClass().getSimpleName());
            $jacocoInit[55] = true;
        }
        $jacocoInit[56] = true;
        return -1;
    }

    public void dispatchWindowVisibilityChanged(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        this.mOutWindowVisibilityChangedReally = true;
        $jacocoInit[57] = true;
        super.dispatchWindowVisibilityChanged(i);
        this.mOutWindowVisibilityChangedReally = false;
        $jacocoInit[58] = true;
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        super.onWindowVisibilityChanged(i);
        if (!this.mOutWindowVisibilityChangedReally) {
            $jacocoInit[59] = true;
        } else if (i == 0) {
            $jacocoInit[60] = true;
            autoRecoverImage();
            $jacocoInit[61] = true;
        } else {
            autoReleaseImage();
            $jacocoInit[62] = true;
        }
        $jacocoInit[63] = true;
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onAttachedToWindow();
        $jacocoInit[64] = true;
        autoRecoverImage();
        $jacocoInit[65] = true;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onDetachedFromWindow();
        $jacocoInit[66] = true;
        autoReleaseImage();
        $jacocoInit[67] = true;
    }

    public void onStartTemporaryDetach() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onStartTemporaryDetach();
        $jacocoInit[68] = true;
        autoReleaseImage();
        $jacocoInit[69] = true;
    }

    public void onFinishTemporaryDetach() {
        boolean[] $jacocoInit = $jacocoInit();
        super.onFinishTemporaryDetach();
        $jacocoInit[70] = true;
        autoRecoverImage();
        $jacocoInit[71] = true;
    }

    public void setEnableBitmapAutoManage(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        this.enableBitmapAutoManage = z;
        $jacocoInit[72] = true;
    }

    public void autoReleaseImage() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.enableBitmapAutoManage) {
            $jacocoInit[73] = true;
        } else if (this.isBitmapReleased) {
            $jacocoInit[74] = true;
        } else {
            this.isBitmapReleased = true;
            $jacocoInit[75] = true;
            WXImage component = getComponent();
            if (component == null) {
                $jacocoInit[76] = true;
            } else {
                $jacocoInit[77] = true;
                component.autoReleaseImage();
                $jacocoInit[78] = true;
            }
        }
        $jacocoInit[79] = true;
    }

    public void autoRecoverImage() {
        boolean[] $jacocoInit = $jacocoInit();
        if (!this.enableBitmapAutoManage) {
            $jacocoInit[80] = true;
        } else if (!this.isBitmapReleased) {
            $jacocoInit[81] = true;
        } else {
            $jacocoInit[82] = true;
            WXImage component = getComponent();
            if (component == null) {
                $jacocoInit[83] = true;
            } else {
                $jacocoInit[84] = true;
                component.autoRecoverImage();
                $jacocoInit[85] = true;
            }
            this.isBitmapReleased = false;
            $jacocoInit[86] = true;
        }
        $jacocoInit[87] = true;
    }
}
