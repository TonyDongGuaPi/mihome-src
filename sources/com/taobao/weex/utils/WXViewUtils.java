package com.taobao.weex.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.taobao.weex.WXEnvironment;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.WXSDKManager;
import com.taobao.weex.common.WXErrorCode;
import com.taobao.weex.common.WXRuntimeException;
import com.taobao.weex.ui.component.WXComponent;
import com.taobao.weex.ui.flat.widget.Widget;
import com.taobao.weex.ui.flat.widget.WidgetGroup;
import com.taobao.weex.ui.view.border.BorderDrawable;
import com.xiaomi.smarthome.download.Downloads;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXViewUtils {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final int DIMENSION_UNSET = -1;
    @Deprecated
    public static final int OPAQUE = -1;
    @Deprecated
    public static final int TRANSLUCENT = -3;
    @Deprecated
    public static final int TRANSPARENT = -2;
    private static int mScreenHeight = 0;
    private static int mScreenWidth = 0;
    private static final boolean mUseWebPx = false;
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    @Retention(RetentionPolicy.SOURCE)
    public @interface Opacity {
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-5773325709829513648L, "com/taobao/weex/utils/WXViewUtils", Downloads.STATUS_QUEUED_FOR_WIFI);
        $jacocoData = a2;
        return a2;
    }

    public WXViewUtils() {
        $jacocoInit()[0] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[195] = true;
    }

    @SuppressLint({"NewApi"})
    public static int generateViewId() {
        boolean[] $jacocoInit = $jacocoInit();
        if (Build.VERSION.SDK_INT < 17) {
            $jacocoInit[1] = true;
            while (true) {
                int i = sNextGeneratedId.get();
                int i2 = i + 1;
                if (i2 <= 16777215) {
                    $jacocoInit[2] = true;
                } else {
                    $jacocoInit[3] = true;
                    i2 = 1;
                }
                if (sNextGeneratedId.compareAndSet(i, i2)) {
                    $jacocoInit[4] = true;
                    return i;
                }
                $jacocoInit[5] = true;
            }
        } else {
            int generateViewId = View.generateViewId();
            $jacocoInit[6] = true;
            return generateViewId;
        }
    }

    public static int getWeexHeight(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str);
        if (sDKInstance != null) {
            $jacocoInit[7] = true;
            int weexHeight = sDKInstance.getWeexHeight();
            if (weexHeight >= 0) {
                $jacocoInit[8] = true;
            } else if (weexHeight == -2) {
                $jacocoInit[9] = true;
            } else {
                int screenHeight = getScreenHeight(WXEnvironment.sApplication);
                $jacocoInit[11] = true;
                return screenHeight;
            }
            $jacocoInit[10] = true;
            return weexHeight;
        }
        $jacocoInit[12] = true;
        return -3;
    }

    public static int getWeexWidth(String str) {
        boolean[] $jacocoInit = $jacocoInit();
        WXSDKInstance sDKInstance = WXSDKManager.getInstance().getSDKInstance(str);
        if (sDKInstance != null) {
            $jacocoInit[13] = true;
            int weexWidth = sDKInstance.getWeexWidth();
            if (weexWidth >= 0) {
                $jacocoInit[14] = true;
            } else if (weexWidth == -2) {
                $jacocoInit[15] = true;
            } else {
                int screenWidth = getScreenWidth(WXEnvironment.sApplication);
                $jacocoInit[17] = true;
                return screenWidth;
            }
            $jacocoInit[16] = true;
            return weexWidth;
        }
        $jacocoInit[18] = true;
        return -3;
    }

    @Deprecated
    public static int getScreenWidth() {
        boolean[] $jacocoInit = $jacocoInit();
        int screenWidth = getScreenWidth(WXEnvironment.sApplication);
        $jacocoInit[19] = true;
        return screenWidth;
    }

    @Deprecated
    public static int setScreenWidth(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        mScreenWidth = i;
        $jacocoInit[20] = true;
        return i;
    }

    public static float getScreenDensity(Context context) {
        boolean[] $jacocoInit = $jacocoInit();
        if (context == null) {
            $jacocoInit[21] = true;
        } else {
            try {
                $jacocoInit[22] = true;
                Resources resources = context.getResources();
                $jacocoInit[23] = true;
                float f = resources.getDisplayMetrics().density;
                $jacocoInit[24] = true;
                return f;
            } catch (Exception e) {
                $jacocoInit[25] = true;
                WXLogUtils.e("getScreenDensityDpi exception:" + e.getMessage());
                $jacocoInit[26] = true;
            }
        }
        $jacocoInit[27] = true;
        return 3.0f;
    }

    public static int getScreenWidth(Context context) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        if (context != null) {
            $jacocoInit[28] = true;
            Resources resources = context.getResources();
            $jacocoInit[29] = true;
            mScreenWidth = resources.getDisplayMetrics().widthPixels;
            if (!WXEnvironment.SETTING_FORCE_VERTICAL_SCREEN) {
                $jacocoInit[30] = true;
            } else {
                $jacocoInit[31] = true;
                mScreenHeight = resources.getDisplayMetrics().heightPixels;
                if (mScreenHeight > mScreenWidth) {
                    i = mScreenWidth;
                    $jacocoInit[32] = true;
                } else {
                    i = mScreenHeight;
                    $jacocoInit[33] = true;
                }
                mScreenWidth = i;
                $jacocoInit[34] = true;
            }
            $jacocoInit[35] = true;
        } else if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[36] = true;
        } else {
            $jacocoInit[37] = true;
            WXRuntimeException wXRuntimeException = new WXRuntimeException("Error Context is null When getScreenHeight");
            $jacocoInit[38] = true;
            throw wXRuntimeException;
        }
        int i2 = mScreenWidth;
        $jacocoInit[39] = true;
        return i2;
    }

    @Deprecated
    public static int getScreenHeight() {
        boolean[] $jacocoInit = $jacocoInit();
        int screenHeight = getScreenHeight(WXEnvironment.sApplication);
        $jacocoInit[40] = true;
        return screenHeight;
    }

    public static int getScreenHeight(Context context) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        if (context != null) {
            $jacocoInit[41] = true;
            Resources resources = context.getResources();
            $jacocoInit[42] = true;
            mScreenHeight = context.getResources().getDisplayMetrics().heightPixels;
            if (!WXEnvironment.SETTING_FORCE_VERTICAL_SCREEN) {
                $jacocoInit[43] = true;
            } else {
                $jacocoInit[44] = true;
                mScreenWidth = resources.getDisplayMetrics().widthPixels;
                if (mScreenHeight > mScreenWidth) {
                    i = mScreenHeight;
                    $jacocoInit[45] = true;
                } else {
                    i = mScreenWidth;
                    $jacocoInit[46] = true;
                }
                mScreenHeight = i;
                $jacocoInit[47] = true;
            }
            $jacocoInit[48] = true;
        } else if (!WXEnvironment.isApkDebugable()) {
            $jacocoInit[49] = true;
        } else {
            $jacocoInit[50] = true;
            WXRuntimeException wXRuntimeException = new WXRuntimeException("Error Context is null When getScreenHeight");
            $jacocoInit[51] = true;
            throw wXRuntimeException;
        }
        int i2 = mScreenHeight;
        $jacocoInit[52] = true;
        return i2;
    }

    @Deprecated
    public static float getRealPxByWidth(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        float realPxByWidth = getRealPxByWidth(f, 750);
        $jacocoInit[53] = true;
        return realPxByWidth;
    }

    public static float getRealPxByWidth(float f, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (Float.isNaN(f)) {
            $jacocoInit[54] = true;
            return f;
        }
        float screenWidth = (f * ((float) getScreenWidth())) / ((float) i);
        $jacocoInit[55] = true;
        double d = (double) screenWidth;
        float f2 = 1.0f;
        if (d <= 0.005d) {
            $jacocoInit[56] = true;
        } else if (screenWidth >= 1.0f) {
            $jacocoInit[57] = true;
        } else {
            $jacocoInit[58] = true;
            $jacocoInit[60] = true;
            return f2;
        }
        f2 = (float) Math.rint(d);
        $jacocoInit[59] = true;
        $jacocoInit[60] = true;
        return f2;
    }

    @Deprecated
    public static float getRealSubPxByWidth(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        float realSubPxByWidth = getRealSubPxByWidth(f, 750);
        $jacocoInit[61] = true;
        return realSubPxByWidth;
    }

    public static float getRealSubPxByWidth(float f, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (Float.isNaN(f)) {
            $jacocoInit[62] = true;
            return f;
        }
        float screenWidth = (f * ((float) getScreenWidth())) / ((float) i);
        if (((double) screenWidth) <= 0.005d) {
            $jacocoInit[63] = true;
        } else if (screenWidth >= 1.0f) {
            $jacocoInit[64] = true;
        } else {
            $jacocoInit[65] = true;
            screenWidth = 1.0f;
            $jacocoInit[67] = true;
            return screenWidth;
        }
        $jacocoInit[66] = true;
        $jacocoInit[67] = true;
        return screenWidth;
    }

    @Deprecated
    public static float getWeexPxByReal(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        float weexPxByReal = getWeexPxByReal(f, 750);
        $jacocoInit[68] = true;
        return weexPxByReal;
    }

    public static float getWeexPxByReal(float f, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        if (Float.isNaN(f)) {
            $jacocoInit[69] = true;
            return f;
        }
        float screenWidth = (f * ((float) i)) / ((float) getScreenWidth());
        $jacocoInit[70] = true;
        return screenWidth;
    }

    @Deprecated
    public static float getRealPxByWidth2(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        float realPxByWidth2 = (float) getRealPxByWidth2(f, 750);
        $jacocoInit[71] = true;
        return realPxByWidth2;
    }

    public static int getRealPxByWidth2(float f, int i) {
        int i2;
        boolean[] $jacocoInit = $jacocoInit();
        float screenWidth = (f * ((float) getScreenWidth())) / ((float) i);
        if (((double) screenWidth) <= 0.005d) {
            $jacocoInit[72] = true;
        } else if (screenWidth >= 1.0f) {
            $jacocoInit[73] = true;
        } else {
            $jacocoInit[74] = true;
            i2 = 1;
            $jacocoInit[76] = true;
            return i2;
        }
        i2 = ((int) screenWidth) - 1;
        $jacocoInit[75] = true;
        $jacocoInit[76] = true;
        return i2;
    }

    @Deprecated
    public static float getWebPxByWidth(float f) {
        boolean[] $jacocoInit = $jacocoInit();
        float webPxByWidth = getWebPxByWidth(f, 750);
        $jacocoInit[77] = true;
        return webPxByWidth;
    }

    public static float getWebPxByWidth(float f, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        double d = (double) f;
        if (d >= -1.9999d) {
            $jacocoInit[78] = true;
        } else if (d <= -2.005d) {
            $jacocoInit[79] = true;
        } else {
            $jacocoInit[80] = true;
            return Float.NaN;
        }
        float screenWidth = (f * ((float) i)) / ((float) getScreenWidth());
        if (((double) screenWidth) <= 0.005d) {
            $jacocoInit[81] = true;
        } else if (screenWidth >= 1.0f) {
            $jacocoInit[82] = true;
        } else {
            $jacocoInit[83] = true;
            screenWidth = 1.0f;
            $jacocoInit[85] = true;
            return screenWidth;
        }
        $jacocoInit[84] = true;
        $jacocoInit[85] = true;
        return screenWidth;
    }

    public static int dip2px(float f) {
        float f2;
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        try {
            $jacocoInit[86] = true;
            Resources resources = WXEnvironment.getApplication().getResources();
            $jacocoInit[87] = true;
            f2 = resources.getDisplayMetrics().density;
            $jacocoInit[88] = true;
        } catch (Exception e) {
            $jacocoInit[89] = true;
            WXLogUtils.e("[WXViewUtils] dip2px:", (Throwable) e);
            $jacocoInit[90] = true;
            f2 = 2.0f;
        }
        float f3 = (f * f2) + 0.5f;
        if (f3 <= 0.0f) {
            $jacocoInit[91] = true;
        } else if (f3 >= 1.0f) {
            $jacocoInit[92] = true;
        } else {
            $jacocoInit[93] = true;
            i = 1;
            $jacocoInit[95] = true;
            return i;
        }
        i = (int) f3;
        $jacocoInit[94] = true;
        $jacocoInit[95] = true;
        return i;
    }

    public static boolean onScreenArea(View view) {
        int i;
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = false;
        if (view == null) {
            $jacocoInit[96] = true;
        } else if (view.getVisibility() != 0) {
            $jacocoInit[97] = true;
        } else {
            int[] iArr = new int[2];
            $jacocoInit[99] = true;
            view.getLocationOnScreen(iArr);
            $jacocoInit[100] = true;
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams != null) {
                i = layoutParams.height;
                $jacocoInit[101] = true;
            } else {
                i = view.getHeight();
                $jacocoInit[102] = true;
            }
            if (iArr[1] <= 0) {
                $jacocoInit[103] = true;
            } else if (iArr[1] - getScreenHeight(WXEnvironment.sApplication) < 0) {
                $jacocoInit[104] = true;
                $jacocoInit[109] = true;
                z = true;
                $jacocoInit[111] = true;
                return z;
            } else {
                $jacocoInit[105] = true;
            }
            if (i + iArr[1] <= 0) {
                $jacocoInit[106] = true;
            } else if (iArr[1] > 0) {
                $jacocoInit[107] = true;
            } else {
                $jacocoInit[108] = true;
                $jacocoInit[109] = true;
                z = true;
                $jacocoInit[111] = true;
                return z;
            }
            $jacocoInit[110] = true;
            $jacocoInit[111] = true;
            return z;
        }
        $jacocoInit[98] = true;
        return false;
    }

    public static int multiplyColorAlpha(int i, int i2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (i2 == 255) {
            $jacocoInit[112] = true;
            return i;
        } else if (i2 == 0) {
            int i3 = i & 16777215;
            $jacocoInit[113] = true;
            return i3;
        } else {
            int i4 = i & 16777215;
            int i5 = i4 | ((((i >>> 24) * (i2 + (i2 >> 7))) >> 8) << 24);
            $jacocoInit[114] = true;
            return i5;
        }
    }

    public static int getOpacityFromColor(int i) {
        boolean[] $jacocoInit = $jacocoInit();
        int i2 = i >>> 24;
        if (i2 == 255) {
            $jacocoInit[115] = true;
            return -1;
        } else if (i2 == 0) {
            $jacocoInit[116] = true;
            return -2;
        } else {
            $jacocoInit[117] = true;
            return -3;
        }
    }

    public static void setBackGround(View view, Drawable drawable, WXComponent wXComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        if (Build.VERSION.SDK_INT >= 16) {
            $jacocoInit[118] = true;
            try {
                view.setBackground(drawable);
                $jacocoInit[121] = true;
            } catch (Exception unused) {
                if (wXComponent == null) {
                    $jacocoInit[122] = true;
                    return;
                }
                String instanceId = wXComponent.getInstanceId();
                WXErrorCode wXErrorCode = WXErrorCode.WX_RENDER_ERR_TEXTURE_SETBACKGROUND;
                StringBuilder sb = new StringBuilder();
                $jacocoInit[123] = true;
                sb.append(wXComponent.getComponentType());
                sb.append(" setBackGround for android view");
                String sb2 = sb.toString();
                StringBuilder sb3 = new StringBuilder();
                WXErrorCode wXErrorCode2 = WXErrorCode.WX_RENDER_ERR_TEXTURE_SETBACKGROUND;
                $jacocoInit[124] = true;
                sb3.append(wXErrorCode2.getErrorMsg());
                sb3.append(": TextureView doesn't support displaying a background drawable!");
                String sb4 = sb3.toString();
                $jacocoInit[125] = true;
                WXExceptionUtils.commitCriticalExceptionRT(instanceId, wXErrorCode, sb2, sb4, (Map<String, String>) null);
                $jacocoInit[126] = true;
            }
        } else {
            $jacocoInit[119] = true;
            view.setBackgroundDrawable(drawable);
            $jacocoInit[120] = true;
        }
        $jacocoInit[127] = true;
    }

    @Nullable
    public static BorderDrawable getBorderDrawable(@NonNull View view) {
        boolean[] $jacocoInit = $jacocoInit();
        Drawable background = view.getBackground();
        if (background instanceof BorderDrawable) {
            BorderDrawable borderDrawable = (BorderDrawable) background;
            $jacocoInit[128] = true;
            return borderDrawable;
        }
        if (!(background instanceof LayerDrawable)) {
            $jacocoInit[129] = true;
        } else {
            $jacocoInit[130] = true;
            LayerDrawable layerDrawable = (LayerDrawable) background;
            if (layerDrawable.getNumberOfLayers() <= 1) {
                $jacocoInit[131] = true;
            } else {
                $jacocoInit[132] = true;
                Drawable drawable = layerDrawable.getDrawable(0);
                if (!(drawable instanceof BorderDrawable)) {
                    $jacocoInit[133] = true;
                } else {
                    BorderDrawable borderDrawable2 = (BorderDrawable) drawable;
                    $jacocoInit[134] = true;
                    return borderDrawable2;
                }
            }
        }
        $jacocoInit[135] = true;
        return null;
    }

    public static void clipCanvasWithinBorderBox(View view, Canvas canvas) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!clipCanvasDueToAndroidVersion(canvas)) {
            $jacocoInit[136] = true;
        } else {
            $jacocoInit[137] = true;
            if (!clipCanvasIfAnimationExist(view)) {
                $jacocoInit[138] = true;
            } else {
                $jacocoInit[139] = true;
                Drawable background = view.getBackground();
                if (!(background instanceof BorderDrawable)) {
                    $jacocoInit[140] = true;
                } else {
                    BorderDrawable borderDrawable = (BorderDrawable) background;
                    $jacocoInit[141] = true;
                    if (!borderDrawable.isRounded()) {
                        $jacocoInit[142] = true;
                    } else {
                        $jacocoInit[143] = true;
                        if (!clipCanvasIfBackgroundImageExist(view, borderDrawable)) {
                            $jacocoInit[144] = true;
                        } else {
                            $jacocoInit[145] = true;
                            RectF rectF = new RectF(0.0f, 0.0f, (float) view.getWidth(), (float) view.getHeight());
                            $jacocoInit[146] = true;
                            Path contentPath = borderDrawable.getContentPath(rectF);
                            $jacocoInit[147] = true;
                            canvas.clipPath(contentPath);
                            $jacocoInit[148] = true;
                        }
                    }
                }
            }
        }
        $jacocoInit[149] = true;
    }

    public static void clipCanvasWithinBorderBox(Widget widget, Canvas canvas) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!clipCanvasDueToAndroidVersion(canvas)) {
            $jacocoInit[150] = true;
        } else {
            $jacocoInit[151] = true;
            if (!clipCanvasIfAnimationExist((View) null)) {
                $jacocoInit[152] = true;
            } else {
                $jacocoInit[153] = true;
                BorderDrawable backgroundAndBorder = widget.getBackgroundAndBorder();
                if (backgroundAndBorder == null) {
                    $jacocoInit[154] = true;
                } else {
                    $jacocoInit[155] = true;
                    if (!backgroundAndBorder.isRounded()) {
                        $jacocoInit[156] = true;
                    } else if (!clipCanvasIfBackgroundImageExist(widget, backgroundAndBorder)) {
                        $jacocoInit[157] = true;
                    } else {
                        $jacocoInit[158] = true;
                        RectF rectF = new RectF(0.0f, 0.0f, (float) widget.getBorderBox().width(), (float) widget.getBorderBox().height());
                        $jacocoInit[159] = true;
                        Path contentPath = backgroundAndBorder.getContentPath(rectF);
                        $jacocoInit[160] = true;
                        canvas.clipPath(contentPath);
                        $jacocoInit[161] = true;
                    }
                    canvas.clipRect(widget.getBorderBox());
                    $jacocoInit[162] = true;
                }
            }
        }
        $jacocoInit[163] = true;
    }

    private static boolean clipCanvasDueToAndroidVersion(Canvas canvas) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        if (Build.VERSION.SDK_INT >= 18) {
            $jacocoInit[164] = true;
        } else {
            $jacocoInit[165] = true;
            if (!canvas.isHardwareAccelerated()) {
                $jacocoInit[166] = true;
            } else {
                z = false;
                $jacocoInit[168] = true;
                $jacocoInit[169] = true;
                return z;
            }
        }
        $jacocoInit[167] = true;
        z = true;
        $jacocoInit[169] = true;
        return z;
    }

    private static boolean clipCanvasIfAnimationExist(View view) {
        boolean[] $jacocoInit = $jacocoInit();
        if (Build.VERSION.SDK_INT != 24) {
            $jacocoInit[170] = true;
            return true;
        }
        $jacocoInit[171] = true;
        return false;
    }

    private static boolean clipCanvasIfBackgroundImageExist(@NonNull View view, @NonNull BorderDrawable borderDrawable) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!(view instanceof ViewGroup)) {
            $jacocoInit[172] = true;
        } else {
            ViewGroup viewGroup = (ViewGroup) view;
            $jacocoInit[173] = true;
            int childCount = viewGroup.getChildCount();
            $jacocoInit[174] = true;
            int i = 0;
            while (i < childCount) {
                $jacocoInit[176] = true;
                View childAt = viewGroup.getChildAt(i);
                $jacocoInit[177] = true;
                if (!(childAt.getBackground() instanceof BorderDrawable)) {
                    $jacocoInit[178] = true;
                } else {
                    $jacocoInit[179] = true;
                    if (!((BorderDrawable) childAt.getBackground()).hasImage()) {
                        $jacocoInit[180] = true;
                    } else if (Build.VERSION.SDK_INT >= 21) {
                        $jacocoInit[181] = true;
                    } else {
                        $jacocoInit[182] = true;
                        return false;
                    }
                }
                i++;
                $jacocoInit[183] = true;
            }
            $jacocoInit[175] = true;
        }
        $jacocoInit[184] = true;
        return true;
    }

    private static boolean clipCanvasIfBackgroundImageExist(@NonNull Widget widget, @NonNull BorderDrawable borderDrawable) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!(widget instanceof WidgetGroup)) {
            $jacocoInit[185] = true;
        } else {
            $jacocoInit[186] = true;
            $jacocoInit[187] = true;
            for (Widget backgroundAndBorder : ((WidgetGroup) widget).getChildren()) {
                $jacocoInit[189] = true;
                if (!backgroundAndBorder.getBackgroundAndBorder().hasImage()) {
                    $jacocoInit[190] = true;
                } else if (Build.VERSION.SDK_INT >= 21) {
                    $jacocoInit[191] = true;
                } else {
                    $jacocoInit[192] = true;
                    return false;
                }
                $jacocoInit[193] = true;
            }
            $jacocoInit[188] = true;
        }
        $jacocoInit[194] = true;
        return true;
    }
}
