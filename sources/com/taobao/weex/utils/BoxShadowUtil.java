package com.taobao.weex.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.taobao.weex.common.WXThread;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class BoxShadowUtil {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    private static final String TAG = "BoxShadowUtil";
    private static boolean sBoxShadowEnabled = true;
    private static Pattern sColorPattern;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(1126280505193997917L, "com/taobao/weex/utils/BoxShadowUtil", 160);
        $jacocoData = a2;
        return a2;
    }

    public BoxShadowUtil() {
        $jacocoInit()[0] = true;
    }

    static /* synthetic */ void access$000(View view, List list, float f, float[] fArr) {
        boolean[] $jacocoInit = $jacocoInit();
        setNormalBoxShadow(view, list, f, fArr);
        $jacocoInit[157] = true;
    }

    static /* synthetic */ void access$100(View view, List list, float f, float[] fArr) {
        boolean[] $jacocoInit = $jacocoInit();
        setInsetBoxShadow(view, list, f, fArr);
        $jacocoInit[158] = true;
    }

    static {
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[159] = true;
    }

    public static void setBoxShadowEnabled(boolean z) {
        boolean[] $jacocoInit = $jacocoInit();
        sBoxShadowEnabled = z;
        $jacocoInit[1] = true;
        WXLogUtils.w(TAG, "Switch box-shadow status: " + z);
        $jacocoInit[2] = true;
    }

    public static boolean isBoxShadowEnabled() {
        boolean[] $jacocoInit = $jacocoInit();
        boolean z = sBoxShadowEnabled;
        $jacocoInit[3] = true;
        return z;
    }

    public static void setBoxShadow(View view, String str, float[] fArr, int i, float f) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!sBoxShadowEnabled) {
            $jacocoInit[4] = true;
            WXLogUtils.w(TAG, "box-shadow was disabled by config");
            $jacocoInit[5] = true;
        } else if (view == null) {
            $jacocoInit[6] = true;
            WXLogUtils.w(TAG, "Target view is null!");
            $jacocoInit[7] = true;
        } else {
            if (!TextUtils.isEmpty(str)) {
                $jacocoInit[8] = true;
            } else if (Build.VERSION.SDK_INT < 18) {
                $jacocoInit[9] = true;
            } else {
                $jacocoInit[10] = true;
                view.getOverlay().clear();
                $jacocoInit[11] = true;
                WXLogUtils.d(TAG, "Remove all box-shadow");
                $jacocoInit[12] = true;
                return;
            }
            BoxShadowOptions[] parseBoxShadows = parseBoxShadows(str, i);
            if (parseBoxShadows == null) {
                $jacocoInit[13] = true;
            } else if (parseBoxShadows.length == 0) {
                $jacocoInit[14] = true;
            } else {
                final ArrayList arrayList = new ArrayList();
                final ArrayList arrayList2 = new ArrayList();
                int length = parseBoxShadows.length;
                $jacocoInit[16] = true;
                int i2 = 0;
                int i3 = 0;
                while (i3 < length) {
                    BoxShadowOptions boxShadowOptions = parseBoxShadows[i3];
                    if (boxShadowOptions == null) {
                        $jacocoInit[17] = true;
                    } else if (boxShadowOptions.isInset) {
                        $jacocoInit[18] = true;
                        arrayList2.add(0, boxShadowOptions);
                        $jacocoInit[19] = true;
                    } else {
                        arrayList.add(0, boxShadowOptions);
                        $jacocoInit[20] = true;
                    }
                    i3++;
                    $jacocoInit[21] = true;
                }
                if (fArr == null) {
                    $jacocoInit[22] = true;
                } else if (fArr.length != 8) {
                    $jacocoInit[23] = true;
                    WXLogUtils.w(TAG, "Length of radii must be 8");
                    $jacocoInit[24] = true;
                } else {
                    $jacocoInit[25] = true;
                    while (i2 < fArr.length) {
                        $jacocoInit[27] = true;
                        fArr[i2] = WXViewUtils.getRealSubPxByWidth(fArr[i2], i);
                        i2++;
                        $jacocoInit[28] = true;
                    }
                    $jacocoInit[26] = true;
                }
                final View view2 = view;
                final float f2 = f;
                final float[] fArr2 = fArr;
                view.post(WXThread.secure((Runnable) new Runnable() {
                    private static transient /* synthetic */ boolean[] $jacocoData;

                    private static /* synthetic */ boolean[] $jacocoInit() {
                        boolean[] zArr = $jacocoData;
                        if (zArr != null) {
                            return zArr;
                        }
                        boolean[] a2 = Offline.a(-6589894432348729476L, "com/taobao/weex/utils/BoxShadowUtil$1", 11);
                        $jacocoData = a2;
                        return a2;
                    }

                    {
                        boolean[] $jacocoInit = $jacocoInit();
                        $jacocoInit[0] = true;
                    }

                    public void run() {
                        boolean[] $jacocoInit = $jacocoInit();
                        if (Build.VERSION.SDK_INT < 18) {
                            $jacocoInit[1] = true;
                        } else {
                            $jacocoInit[2] = true;
                            view2.getOverlay().clear();
                            $jacocoInit[3] = true;
                            if (arrayList.size() <= 0) {
                                $jacocoInit[4] = true;
                            } else {
                                $jacocoInit[5] = true;
                                BoxShadowUtil.access$000(view2, arrayList, f2, fArr2);
                                $jacocoInit[6] = true;
                            }
                            if (arrayList2.size() <= 0) {
                                $jacocoInit[7] = true;
                            } else {
                                $jacocoInit[8] = true;
                                BoxShadowUtil.access$100(view2, arrayList2, f2, fArr2);
                                $jacocoInit[9] = true;
                            }
                        }
                        $jacocoInit[10] = true;
                    }
                }));
                $jacocoInit[29] = true;
                return;
            }
            WXLogUtils.w(TAG, "Failed to parse box-shadow: " + str);
            $jacocoInit[15] = true;
        }
    }

    private static void drawShadow(Canvas canvas, BoxShadowOptions boxShadowOptions) {
        boolean[] $jacocoInit = $jacocoInit();
        RectF rectF = new RectF(0.0f, 0.0f, ((float) boxShadowOptions.viewWidth) + (boxShadowOptions.spread * 2.0f), ((float) boxShadowOptions.viewHeight) + (boxShadowOptions.spread * 2.0f));
        if (boxShadowOptions.topLeft == null) {
            $jacocoInit[30] = true;
        } else {
            $jacocoInit[31] = true;
            rectF.offset(boxShadowOptions.topLeft.x, boxShadowOptions.topLeft.y);
            $jacocoInit[32] = true;
        }
        float f = boxShadowOptions.blur;
        float f2 = boxShadowOptions.blur;
        if (boxShadowOptions.hShadow <= 0.0f) {
            $jacocoInit[33] = true;
        } else {
            f += boxShadowOptions.hShadow * 2.0f;
            $jacocoInit[34] = true;
        }
        if (boxShadowOptions.vShadow <= 0.0f) {
            $jacocoInit[35] = true;
        } else {
            f2 += boxShadowOptions.vShadow * 2.0f;
            $jacocoInit[36] = true;
        }
        rectF.offset(f, f2);
        $jacocoInit[37] = true;
        Paint paint = new Paint();
        $jacocoInit[38] = true;
        paint.setAntiAlias(true);
        $jacocoInit[39] = true;
        paint.setColor(boxShadowOptions.color);
        $jacocoInit[40] = true;
        paint.setStyle(Paint.Style.FILL);
        if (boxShadowOptions.blur <= 0.0f) {
            $jacocoInit[41] = true;
        } else {
            $jacocoInit[42] = true;
            paint.setMaskFilter(new BlurMaskFilter(boxShadowOptions.blur, BlurMaskFilter.Blur.NORMAL));
            $jacocoInit[43] = true;
        }
        Path path = new Path();
        float[] fArr = new float[8];
        int i = 0;
        $jacocoInit[44] = true;
        while (i < boxShadowOptions.radii.length) {
            if (boxShadowOptions.radii[i] == 0.0f) {
                fArr[i] = 0.0f;
                $jacocoInit[45] = true;
            } else {
                fArr[i] = boxShadowOptions.radii[i] + boxShadowOptions.spread;
                $jacocoInit[46] = true;
            }
            i++;
            $jacocoInit[47] = true;
        }
        path.addRoundRect(rectF, fArr, Path.Direction.CCW);
        $jacocoInit[48] = true;
        canvas.drawPath(path, paint);
        $jacocoInit[49] = true;
    }

    private static void setNormalBoxShadow(View view, List<BoxShadowOptions> list, float f, float[] fArr) {
        float f2 = f;
        boolean[] $jacocoInit = $jacocoInit();
        int height = view.getHeight();
        $jacocoInit[50] = true;
        int width = view.getWidth();
        $jacocoInit[51] = true;
        view.getLayoutParams();
        if (height == 0) {
            $jacocoInit[52] = true;
        } else if (width == 0) {
            $jacocoInit[53] = true;
        } else {
            if (Build.VERSION.SDK_INT >= 18) {
                $jacocoInit[55] = true;
                $jacocoInit[56] = true;
                int i = 0;
                int i2 = 0;
                for (BoxShadowOptions next : list) {
                    next.viewWidth = width;
                    next.viewHeight = height;
                    next.radii = fArr;
                    $jacocoInit[57] = true;
                    Rect targetCanvasRect = next.getTargetCanvasRect();
                    $jacocoInit[58] = true;
                    if (i >= targetCanvasRect.width()) {
                        $jacocoInit[59] = true;
                    } else {
                        $jacocoInit[60] = true;
                        i = targetCanvasRect.width();
                        $jacocoInit[61] = true;
                    }
                    if (i2 >= targetCanvasRect.height()) {
                        $jacocoInit[62] = true;
                    } else {
                        $jacocoInit[63] = true;
                        i2 = targetCanvasRect.height();
                        $jacocoInit[64] = true;
                    }
                    $jacocoInit[65] = true;
                }
                float[] fArr2 = fArr;
                $jacocoInit[66] = true;
                Bitmap createBitmap = Bitmap.createBitmap((int) (((float) i) * f2), (int) (((float) i2) * f2), Bitmap.Config.ARGB_4444);
                if (Build.VERSION.SDK_INT < 19) {
                    $jacocoInit[67] = true;
                } else {
                    $jacocoInit[68] = true;
                    WXLogUtils.d(TAG, "Allocation memory for box-shadow: " + (createBitmap.getAllocationByteCount() / 1024) + " KB");
                    $jacocoInit[69] = true;
                }
                Canvas canvas = new Canvas(createBitmap);
                $jacocoInit[70] = true;
                $jacocoInit[71] = true;
                for (BoxShadowOptions next2 : list) {
                    $jacocoInit[72] = true;
                    Rect targetCanvasRect2 = next2.getTargetCanvasRect();
                    $jacocoInit[73] = true;
                    $jacocoInit[74] = true;
                    $jacocoInit[75] = true;
                    next2.topLeft = new PointF(((float) (i - targetCanvasRect2.width())) / 2.0f, ((float) (i2 - targetCanvasRect2.height())) / 2.0f);
                    $jacocoInit[76] = true;
                    BoxShadowOptions scale = next2.scale(f2);
                    $jacocoInit[77] = true;
                    drawShadow(canvas, scale);
                    $jacocoInit[78] = true;
                }
                $jacocoInit[79] = true;
                OverflowBitmapDrawable overflowBitmapDrawable = new OverflowBitmapDrawable(view.getResources(), createBitmap, new Point((i - width) / 2, (i2 - height) / 2), new Rect(0, 0, width, height), fArr, (AnonymousClass1) null);
                $jacocoInit[80] = true;
                view.getOverlay().add(overflowBitmapDrawable);
                $jacocoInit[81] = true;
                ViewParent parent = view.getParent();
                if (parent == null) {
                    $jacocoInit[82] = true;
                } else {
                    $jacocoInit[83] = true;
                    parent.requestLayout();
                    if (!(parent instanceof ViewGroup)) {
                        $jacocoInit[84] = true;
                    } else {
                        $jacocoInit[85] = true;
                        ((ViewGroup) parent).invalidate(overflowBitmapDrawable.getBounds());
                        $jacocoInit[86] = true;
                    }
                }
                $jacocoInit[87] = true;
            } else {
                Log.w(TAG, "Call setNormalBoxShadow() requires API level 18 or higher.");
                $jacocoInit[88] = true;
            }
            $jacocoInit[89] = true;
            return;
        }
        Log.w(TAG, "Target view is invisible, ignore set shadow.");
        $jacocoInit[54] = true;
    }

    private static void setInsetBoxShadow(View view, List<BoxShadowOptions> list, float f, float[] fArr) {
        List<BoxShadowOptions> list2 = list;
        boolean[] $jacocoInit = $jacocoInit();
        if (view == null) {
            $jacocoInit[90] = true;
        } else if (list2 == null) {
            $jacocoInit[91] = true;
        } else {
            if (view.getWidth() == 0) {
                $jacocoInit[93] = true;
            } else if (view.getHeight() == 0) {
                $jacocoInit[94] = true;
            } else {
                if (Build.VERSION.SDK_INT >= 18) {
                    $jacocoInit[96] = true;
                    Drawable[] drawableArr = new Drawable[list.size()];
                    $jacocoInit[97] = true;
                    int i = 0;
                    $jacocoInit[98] = true;
                    while (i < list.size()) {
                        $jacocoInit[99] = true;
                        BoxShadowOptions boxShadowOptions = list2.get(i);
                        $jacocoInit[100] = true;
                        drawableArr[i] = new InsetShadowDrawable(view.getWidth(), view.getHeight(), boxShadowOptions.hShadow, boxShadowOptions.vShadow, boxShadowOptions.blur, boxShadowOptions.spread, boxShadowOptions.color, fArr, (AnonymousClass1) null);
                        i++;
                        $jacocoInit[101] = true;
                    }
                    LayerDrawable layerDrawable = new LayerDrawable(drawableArr);
                    $jacocoInit[102] = true;
                    view.getOverlay().add(layerDrawable);
                    $jacocoInit[103] = true;
                    view.invalidate();
                    $jacocoInit[104] = true;
                } else {
                    Log.w(TAG, "Call setInsetBoxShadow() requires API level 18 or higher.");
                    $jacocoInit[105] = true;
                }
                $jacocoInit[106] = true;
                return;
            }
            WXLogUtils.w(TAG, "Target view is invisible, ignore set shadow.");
            $jacocoInit[95] = true;
            return;
        }
        WXLogUtils.w(TAG, "Illegal arguments");
        $jacocoInit[92] = true;
    }

    public static BoxShadowOptions[] parseBoxShadows(String str, int i) {
        int i2;
        boolean[] $jacocoInit = $jacocoInit();
        if (sColorPattern != null) {
            $jacocoInit[107] = true;
        } else {
            $jacocoInit[108] = true;
            sColorPattern = Pattern.compile("([rR][gG][bB][aA]?)\\((\\d+\\s*),\\s*(\\d+\\s*),\\s*(\\d+\\s*)(?:,\\s*(\\d+(?:\\.\\d+)?))?\\)");
            $jacocoInit[109] = true;
        }
        Matcher matcher = sColorPattern.matcher(str);
        $jacocoInit[110] = true;
        while (true) {
            i2 = 0;
            if (!matcher.find()) {
                break;
            }
            $jacocoInit[111] = true;
            String group = matcher.group();
            $jacocoInit[112] = true;
            str = str.replace(group, "#" + String.format("%8s", new Object[]{Integer.toHexString(WXResourceUtils.getColor(group, -16777216))}).replaceAll("\\s", "0"));
            $jacocoInit[113] = true;
        }
        String[] split = str.split(",");
        if (split == null) {
            $jacocoInit[114] = true;
        } else if (split.length <= 0) {
            $jacocoInit[115] = true;
        } else {
            BoxShadowOptions[] boxShadowOptionsArr = new BoxShadowOptions[split.length];
            $jacocoInit[116] = true;
            while (i2 < split.length) {
                $jacocoInit[117] = true;
                boxShadowOptionsArr[i2] = parseBoxShadow(split[i2], i);
                i2++;
                $jacocoInit[118] = true;
            }
            $jacocoInit[119] = true;
            return boxShadowOptionsArr;
        }
        $jacocoInit[120] = true;
        return null;
    }

    private static BoxShadowOptions parseBoxShadow(String str, int i) {
        boolean[] $jacocoInit = $jacocoInit();
        BoxShadowOptions boxShadowOptions = new BoxShadowOptions(i, (AnonymousClass1) null);
        $jacocoInit[121] = true;
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[122] = true;
            return null;
        }
        $jacocoInit[123] = true;
        String replaceAll = str.replaceAll("\\s*,\\s+", ",");
        $jacocoInit[124] = true;
        if (!replaceAll.contains("inset")) {
            $jacocoInit[125] = true;
        } else {
            boxShadowOptions.isInset = true;
            $jacocoInit[126] = true;
            replaceAll = replaceAll.replace("inset", "");
            $jacocoInit[127] = true;
        }
        String trim = replaceAll.trim();
        $jacocoInit[128] = true;
        ArrayList arrayList = new ArrayList(Arrays.asList(trim.split("\\s+")));
        $jacocoInit[129] = true;
        String str2 = (String) arrayList.get(arrayList.size() - 1);
        $jacocoInit[130] = true;
        if (TextUtils.isEmpty(str2)) {
            $jacocoInit[131] = true;
        } else {
            $jacocoInit[132] = true;
            if (str2.startsWith("#")) {
                $jacocoInit[133] = true;
            } else if (str2.startsWith("rgb")) {
                $jacocoInit[134] = true;
            } else if (!WXResourceUtils.isNamedColor(str2)) {
                $jacocoInit[135] = true;
            } else {
                $jacocoInit[136] = true;
            }
            boxShadowOptions.color = WXResourceUtils.getColor(str2, -16777216);
            $jacocoInit[137] = true;
            arrayList.remove(arrayList.size() - 1);
            try {
                $jacocoInit[138] = true;
            } catch (Throwable th) {
                $jacocoInit[154] = true;
                th.printStackTrace();
                $jacocoInit[155] = true;
            }
        }
        int i2 = 2;
        if (arrayList.size() >= 2) {
            $jacocoInit[139] = true;
            if (TextUtils.isEmpty((CharSequence) arrayList.get(0))) {
                $jacocoInit[141] = true;
            } else {
                $jacocoInit[142] = true;
                float floatValue = WXUtils.getFloat(((String) arrayList.get(0)).trim(), Float.valueOf(0.0f)).floatValue();
                $jacocoInit[143] = true;
                boxShadowOptions.hShadow = WXViewUtils.getRealSubPxByWidth(floatValue, i);
                $jacocoInit[144] = true;
            }
            if (TextUtils.isEmpty((CharSequence) arrayList.get(1))) {
                $jacocoInit[145] = true;
            } else {
                $jacocoInit[146] = true;
                float floatValue2 = WXUtils.getFloat(((String) arrayList.get(1)).trim(), Float.valueOf(0.0f)).floatValue();
                $jacocoInit[147] = true;
                boxShadowOptions.vShadow = WXViewUtils.getRealPxByWidth(floatValue2, i);
                $jacocoInit[148] = true;
            }
            $jacocoInit[149] = true;
            while (i2 < arrayList.size()) {
                $jacocoInit[150] = true;
                List access$500 = BoxShadowOptions.access$500(boxShadowOptions);
                $jacocoInit[151] = true;
                ((BoxShadowOptions.IParser) access$500.get(i2 - 2)).parse((String) arrayList.get(i2));
                i2++;
                $jacocoInit[152] = true;
            }
            $jacocoInit[153] = true;
            $jacocoInit[156] = true;
            return boxShadowOptions;
        }
        $jacocoInit[140] = true;
        return null;
    }

    private static class OverflowBitmapDrawable extends BitmapDrawable {
        private static transient /* synthetic */ boolean[] $jacocoData;
        private int paddingX;
        private int paddingY;
        private float[] radii;
        private Rect viewRect;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-948289180055550427L, "com/taobao/weex/utils/BoxShadowUtil$OverflowBitmapDrawable", 13);
            $jacocoData = a2;
            return a2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ OverflowBitmapDrawable(Resources resources, Bitmap bitmap, Point point, Rect rect, float[] fArr, AnonymousClass1 r6) {
            this(resources, bitmap, point, rect, fArr);
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[12] = true;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        private OverflowBitmapDrawable(Resources resources, Bitmap bitmap, Point point, Rect rect, float[] fArr) {
            super(resources, bitmap);
            boolean[] $jacocoInit = $jacocoInit();
            this.paddingX = point.x;
            this.paddingY = point.y;
            this.viewRect = rect;
            this.radii = fArr;
            $jacocoInit[0] = true;
            setBounds(-this.paddingX, -this.paddingY, rect.width() + this.paddingX, rect.height() + this.paddingY);
            $jacocoInit[1] = true;
        }

        public void draw(Canvas canvas) {
            boolean[] $jacocoInit = $jacocoInit();
            Rect clipBounds = canvas.getClipBounds();
            $jacocoInit[2] = true;
            Rect rect = new Rect(clipBounds);
            $jacocoInit[3] = true;
            rect.inset((-this.paddingX) * 2, (-this.paddingY) * 2);
            $jacocoInit[4] = true;
            canvas.clipRect(rect, Region.Op.REPLACE);
            $jacocoInit[5] = true;
            Path path = new Path();
            $jacocoInit[6] = true;
            RectF rectF = new RectF(clipBounds);
            $jacocoInit[7] = true;
            path.addRoundRect(rectF, this.radii, Path.Direction.CCW);
            $jacocoInit[8] = true;
            canvas.clipPath(path, Region.Op.DIFFERENCE);
            $jacocoInit[9] = true;
            canvas.translate((float) clipBounds.left, (float) clipBounds.top);
            $jacocoInit[10] = true;
            super.draw(canvas);
            $jacocoInit[11] = true;
        }
    }

    private static class InsetShadowDrawable extends Drawable {
        private static transient /* synthetic */ boolean[] $jacocoData = null;
        private static final int BOTTOM_TO_TOP = 3;
        private static final int LEFT_TO_RIGHT = 0;
        private static final int RIGHT_TO_LEFT = 2;
        private static final int TOP_TO_BOTTOM = 1;
        private float blurRadius;
        private float height;
        private Paint paint;
        private Path[] paths;
        private float[] radii;
        private Shader[] shades;
        private int shadowColor;
        private float shadowXSize;
        private float shadowYSize;
        private float width;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(8232373294552553014L, "com/taobao/weex/utils/BoxShadowUtil$InsetShadowDrawable", 57);
            $jacocoData = a2;
            return a2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ InsetShadowDrawable(int i, int i2, float f, float f2, float f3, float f4, int i3, float[] fArr, AnonymousClass1 r9) {
            this(i, i2, f, f2, f3, f4, i3, fArr);
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[56] = true;
        }

        private InsetShadowDrawable(int i, int i2, float f, float f2, float f3, float f4, int i3, float[] fArr) {
            boolean[] $jacocoInit = $jacocoInit();
            this.shades = new Shader[4];
            this.paths = new Path[4];
            this.blurRadius = f3;
            this.shadowColor = i3;
            this.width = ((float) i) + (f * 2.0f);
            this.height = ((float) i2) + (2.0f * f2);
            this.shadowXSize = f + f4;
            this.shadowYSize = f2 + f4;
            this.radii = fArr;
            $jacocoInit[0] = true;
            setBounds(0, 0, i, i2);
            $jacocoInit[1] = true;
            prepare();
            $jacocoInit[2] = true;
        }

        private void prepare() {
            boolean[] $jacocoInit = $jacocoInit();
            PointF pointF = new PointF(0.0f, 0.0f);
            $jacocoInit[3] = true;
            PointF pointF2 = new PointF(this.width, 0.0f);
            $jacocoInit[4] = true;
            PointF pointF3 = new PointF(pointF2.x, this.height);
            $jacocoInit[5] = true;
            PointF pointF4 = new PointF(pointF.x, pointF3.y);
            $jacocoInit[6] = true;
            PointF pointF5 = new PointF(this.shadowXSize, this.shadowYSize);
            $jacocoInit[7] = true;
            PointF pointF6 = new PointF(pointF2.x - this.shadowXSize, pointF5.y);
            $jacocoInit[8] = true;
            PointF pointF7 = new PointF(pointF6.x, pointF3.y - this.shadowYSize);
            $jacocoInit[9] = true;
            PointF pointF8 = new PointF(pointF5.x, pointF7.y);
            $jacocoInit[10] = true;
            float f = pointF5.x - this.blurRadius;
            float f2 = pointF5.y;
            float f3 = pointF5.x;
            float f4 = pointF5.y;
            LinearGradient linearGradient = new LinearGradient(f, f2, f3, f4, this.shadowColor, 0, Shader.TileMode.CLAMP);
            $jacocoInit[11] = true;
            float f5 = pointF5.x;
            float f6 = pointF5.y - this.blurRadius;
            float f7 = pointF5.x;
            float f8 = pointF5.y;
            PointF pointF9 = pointF3;
            float f9 = f7;
            LinearGradient linearGradient2 = new LinearGradient(f5, f6, f9, f8, this.shadowColor, 0, Shader.TileMode.CLAMP);
            $jacocoInit[12] = true;
            float f10 = pointF7.x + this.blurRadius;
            float f11 = pointF7.y;
            float f12 = pointF7.x;
            float f13 = pointF7.y;
            PointF pointF10 = pointF6;
            float f14 = f12;
            LinearGradient linearGradient3 = new LinearGradient(f10, f11, f14, f13, this.shadowColor, 0, Shader.TileMode.CLAMP);
            $jacocoInit[13] = true;
            float f15 = pointF7.x;
            float f16 = pointF7.y + this.blurRadius;
            float f17 = pointF7.x;
            float f18 = pointF7.y;
            PointF pointF11 = pointF7;
            float f19 = f17;
            LinearGradient linearGradient4 = new LinearGradient(f15, f16, f19, f18, this.shadowColor, 0, Shader.TileMode.CLAMP);
            this.shades[0] = linearGradient;
            this.shades[1] = linearGradient2;
            this.shades[2] = linearGradient3;
            this.shades[3] = linearGradient4;
            $jacocoInit[14] = true;
            Path path = new Path();
            $jacocoInit[15] = true;
            path.moveTo(pointF.x, pointF.y);
            $jacocoInit[16] = true;
            path.lineTo(pointF5.x, pointF5.y);
            $jacocoInit[17] = true;
            path.lineTo(pointF8.x, pointF8.y);
            $jacocoInit[18] = true;
            path.lineTo(pointF4.x, pointF4.y);
            $jacocoInit[19] = true;
            path.close();
            $jacocoInit[20] = true;
            Path path2 = new Path();
            $jacocoInit[21] = true;
            path2.moveTo(pointF.x, pointF.y);
            $jacocoInit[22] = true;
            path2.lineTo(pointF2.x, pointF2.y);
            $jacocoInit[23] = true;
            PointF pointF12 = pointF10;
            path2.lineTo(pointF12.x, pointF12.y);
            $jacocoInit[24] = true;
            path2.lineTo(pointF5.x, pointF5.y);
            $jacocoInit[25] = true;
            path2.close();
            $jacocoInit[26] = true;
            Path path3 = new Path();
            $jacocoInit[27] = true;
            path3.moveTo(pointF2.x, pointF2.y);
            $jacocoInit[28] = true;
            PointF pointF13 = pointF9;
            path3.lineTo(pointF13.x, pointF13.y);
            $jacocoInit[29] = true;
            PointF pointF14 = pointF11;
            path3.lineTo(pointF14.x, pointF14.y);
            $jacocoInit[30] = true;
            path3.lineTo(pointF12.x, pointF12.y);
            $jacocoInit[31] = true;
            path3.close();
            $jacocoInit[32] = true;
            Path path4 = new Path();
            $jacocoInit[33] = true;
            path4.moveTo(pointF4.x, pointF4.y);
            $jacocoInit[34] = true;
            path4.lineTo(pointF13.x, pointF13.y);
            $jacocoInit[35] = true;
            path4.lineTo(pointF14.x, pointF14.y);
            $jacocoInit[36] = true;
            path4.lineTo(pointF8.x, pointF8.y);
            $jacocoInit[37] = true;
            path4.close();
            this.paths[0] = path;
            this.paths[1] = path2;
            this.paths[2] = path3;
            this.paths[3] = path4;
            $jacocoInit[38] = true;
            this.paint = new Paint();
            $jacocoInit[39] = true;
            this.paint.setAntiAlias(true);
            $jacocoInit[40] = true;
            this.paint.setStyle(Paint.Style.FILL);
            $jacocoInit[41] = true;
            this.paint.setColor(this.shadowColor);
            $jacocoInit[42] = true;
        }

        public void draw(Canvas canvas) {
            boolean[] $jacocoInit = $jacocoInit();
            Rect clipBounds = canvas.getClipBounds();
            $jacocoInit[43] = true;
            Path path = new Path();
            $jacocoInit[44] = true;
            RectF rectF = new RectF(clipBounds);
            $jacocoInit[45] = true;
            path.addRoundRect(rectF, this.radii, Path.Direction.CCW);
            $jacocoInit[46] = true;
            canvas.clipPath(path);
            $jacocoInit[47] = true;
            canvas.translate((float) clipBounds.left, (float) clipBounds.top);
            $jacocoInit[48] = true;
            int i = 0;
            while (i < 4) {
                Shader shader = this.shades[i];
                Path path2 = this.paths[i];
                $jacocoInit[49] = true;
                this.paint.setShader(shader);
                $jacocoInit[50] = true;
                canvas.drawPath(path2, this.paint);
                i++;
                $jacocoInit[51] = true;
            }
            $jacocoInit[52] = true;
        }

        public void setAlpha(@IntRange(from = 0, to = 255) int i) {
            $jacocoInit()[53] = true;
        }

        public void setColorFilter(@Nullable ColorFilter colorFilter) {
            $jacocoInit()[54] = true;
        }

        public int getOpacity() {
            $jacocoInit()[55] = true;
            return -1;
        }
    }

    public static class BoxShadowOptions {
        private static transient /* synthetic */ boolean[] $jacocoData;
        public float blur;
        public int color;
        public float hShadow;
        public boolean isInset;
        private List<IParser> optionParamParsers;
        public float[] radii;
        public float spread;
        public PointF topLeft;
        public float vShadow;
        public int viewHeight;
        public int viewWidth;
        private int viewport;

        private interface IParser {
            void parse(String str);
        }

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-1429342297971159011L, "com/taobao/weex/utils/BoxShadowUtil$BoxShadowOptions", 35);
            $jacocoData = a2;
            return a2;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        /* synthetic */ BoxShadowOptions(int i, AnonymousClass1 r3) {
            this(i);
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[32] = true;
        }

        static /* synthetic */ List access$500(BoxShadowOptions boxShadowOptions) {
            boolean[] $jacocoInit = $jacocoInit();
            List<IParser> list = boxShadowOptions.optionParamParsers;
            $jacocoInit[33] = true;
            return list;
        }

        static /* synthetic */ int access$600(BoxShadowOptions boxShadowOptions) {
            boolean[] $jacocoInit = $jacocoInit();
            int i = boxShadowOptions.viewport;
            $jacocoInit[34] = true;
            return i;
        }

        private BoxShadowOptions(int i) {
            boolean[] $jacocoInit = $jacocoInit();
            this.viewport = 750;
            this.blur = 0.0f;
            this.spread = 0.0f;
            this.radii = new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f};
            this.color = -16777216;
            this.isInset = false;
            this.viewWidth = 0;
            this.viewHeight = 0;
            this.topLeft = null;
            if (this.viewport == 0) {
                $jacocoInit[0] = true;
            } else {
                this.viewport = i;
                $jacocoInit[1] = true;
            }
            this.optionParamParsers = new ArrayList();
            $jacocoInit[2] = true;
            AnonymousClass1 r5 = new IParser(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ BoxShadowOptions this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(6810845126110548435L, "com/taobao/weex/utils/BoxShadowUtil$BoxShadowOptions$1", 7);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    this.this$0 = r3;
                    $jacocoInit[0] = true;
                }

                public void parse(String str) {
                    boolean[] $jacocoInit = $jacocoInit();
                    if (TextUtils.isEmpty(str)) {
                        $jacocoInit[1] = true;
                    } else {
                        $jacocoInit[2] = true;
                        float floatValue = WXUtils.getFloat(str, Float.valueOf(0.0f)).floatValue();
                        $jacocoInit[3] = true;
                        this.this$0.spread = WXViewUtils.getRealSubPxByWidth(floatValue, BoxShadowOptions.access$600(this.this$0));
                        $jacocoInit[4] = true;
                        WXLogUtils.w(BoxShadowUtil.TAG, "Experimental box-shadow attribute: spread");
                        $jacocoInit[5] = true;
                    }
                    $jacocoInit[6] = true;
                }
            };
            $jacocoInit[3] = true;
            AnonymousClass2 r1 = new IParser(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ BoxShadowOptions this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(8303201070123065098L, "com/taobao/weex/utils/BoxShadowUtil$BoxShadowOptions$2", 6);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    this.this$0 = r3;
                    $jacocoInit[0] = true;
                }

                public void parse(String str) {
                    boolean[] $jacocoInit = $jacocoInit();
                    if (TextUtils.isEmpty(str)) {
                        $jacocoInit[1] = true;
                    } else {
                        $jacocoInit[2] = true;
                        float floatValue = WXUtils.getFloat(str, Float.valueOf(0.0f)).floatValue();
                        $jacocoInit[3] = true;
                        this.this$0.blur = WXViewUtils.getRealSubPxByWidth(floatValue, BoxShadowOptions.access$600(this.this$0));
                        $jacocoInit[4] = true;
                    }
                    $jacocoInit[5] = true;
                }
            };
            $jacocoInit[4] = true;
            this.optionParamParsers.add(r1);
            $jacocoInit[5] = true;
            this.optionParamParsers.add(r5);
            $jacocoInit[6] = true;
        }

        public BoxShadowOptions scale(float f) {
            boolean[] $jacocoInit = $jacocoInit();
            if (f <= 0.0f) {
                $jacocoInit[7] = true;
            } else if (f > 1.0f) {
                $jacocoInit[8] = true;
            } else {
                $jacocoInit[9] = true;
                BoxShadowOptions boxShadowOptions = new BoxShadowOptions(this.viewport);
                boxShadowOptions.hShadow = this.hShadow * f;
                boxShadowOptions.vShadow = this.vShadow * f;
                boxShadowOptions.blur = this.blur * f;
                boxShadowOptions.spread = this.spread * f;
                int i = 0;
                $jacocoInit[10] = true;
                while (i < this.radii.length) {
                    boxShadowOptions.radii[i] = this.radii[i] * f;
                    i++;
                    $jacocoInit[11] = true;
                }
                boxShadowOptions.viewHeight = (int) (((float) this.viewHeight) * f);
                boxShadowOptions.viewWidth = (int) (((float) this.viewWidth) * f);
                if (this.topLeft == null) {
                    $jacocoInit[12] = true;
                } else {
                    $jacocoInit[13] = true;
                    boxShadowOptions.topLeft = new PointF();
                    boxShadowOptions.topLeft.x = this.topLeft.x * f;
                    boxShadowOptions.topLeft.y = this.topLeft.y * f;
                    $jacocoInit[14] = true;
                }
                boxShadowOptions.color = this.color;
                boxShadowOptions.isInset = this.isInset;
                $jacocoInit[15] = true;
                WXLogUtils.d(BoxShadowUtil.TAG, "Scaled BoxShadowOptions: [" + f + "] " + boxShadowOptions);
                $jacocoInit[16] = true;
                return boxShadowOptions;
            }
            $jacocoInit[17] = true;
            return null;
        }

        public Rect getTargetCanvasRect() {
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[18] = true;
            $jacocoInit[19] = true;
            Rect rect = new Rect(0, 0, this.viewWidth + (((int) (this.blur + this.spread + Math.abs(this.hShadow))) * 2), this.viewHeight + (((int) (this.blur + this.spread + Math.abs(this.vShadow))) * 2));
            $jacocoInit[20] = true;
            return rect;
        }

        public String toString() {
            boolean[] $jacocoInit = $jacocoInit();
            $jacocoInit[21] = true;
            StringBuffer stringBuffer = new StringBuffer("BoxShadowOptions{");
            $jacocoInit[22] = true;
            stringBuffer.append("h-shadow=");
            stringBuffer.append(this.hShadow);
            $jacocoInit[23] = true;
            stringBuffer.append(", v-shadow=");
            stringBuffer.append(this.vShadow);
            $jacocoInit[24] = true;
            stringBuffer.append(", blur=");
            stringBuffer.append(this.blur);
            $jacocoInit[25] = true;
            stringBuffer.append(", spread=");
            stringBuffer.append(this.spread);
            $jacocoInit[26] = true;
            stringBuffer.append(", corner-radius=");
            stringBuffer.append(Operators.ARRAY_START_STR + this.radii[0] + "," + this.radii[2] + "," + this.radii[4] + "," + this.radii[6] + Operators.ARRAY_END_STR);
            $jacocoInit[27] = true;
            stringBuffer.append(", color=#");
            stringBuffer.append(Integer.toHexString(this.color));
            $jacocoInit[28] = true;
            stringBuffer.append(", inset=");
            stringBuffer.append(this.isInset);
            $jacocoInit[29] = true;
            stringBuffer.append(Operators.BLOCK_END);
            $jacocoInit[30] = true;
            String stringBuffer2 = stringBuffer.toString();
            $jacocoInit[31] = true;
            return stringBuffer2;
        }
    }
}
