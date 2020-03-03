package com.xiaomi.qrcode2.camera;

import android.annotation.TargetApi;
import android.graphics.Point;
import android.graphics.Rect;
import android.hardware.Camera;
import android.os.Build;
import android.util.Log;
import com.adobe.xmp.XMPConst;
import com.alipay.sdk.util.i;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.qrcode2.BarcodeGenActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

@TargetApi(15)
public final class CameraConfigurationUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f13031a = "CameraConfiguration";
    private static final Pattern b = Pattern.compile(i.b);
    private static final int c = 153600;
    private static final float d = 1.5f;
    private static final float e = 0.0f;
    private static final double f = 0.15d;
    private static final int g = 10;
    private static final int h = 20;
    private static final int i = 400;

    private CameraConfigurationUtils() {
    }

    public static void a(Camera.Parameters parameters, boolean z, boolean z2, boolean z3) {
        String str;
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        if (!z) {
            str = null;
        } else if (z3 || z2) {
            str = a("focus mode", (Collection<String>) supportedFocusModes, "auto");
        } else {
            str = a("focus mode", (Collection<String>) supportedFocusModes, "continuous-picture", "continuous-video", "auto");
        }
        if (!z3 && str == null) {
            str = a("focus mode", (Collection<String>) supportedFocusModes, "macro", "edof");
        }
        if (str == null) {
            return;
        }
        if (str.equals(parameters.getFocusMode())) {
            Log.i(f13031a, "Focus mode already set to " + str);
            return;
        }
        parameters.setFocusMode(str);
    }

    public static void a(Camera.Parameters parameters, boolean z) {
        String str;
        List<String> supportedFlashModes = parameters.getSupportedFlashModes();
        if (z) {
            str = a("flash mode", (Collection<String>) supportedFlashModes, "torch", "on");
        } else {
            str = a("flash mode", (Collection<String>) supportedFlashModes, "off");
        }
        if (str == null) {
            return;
        }
        if (str.equals(parameters.getFlashMode())) {
            Log.i(f13031a, "Flash mode already set to " + str);
            return;
        }
        Log.i(f13031a, "Setting flash mode to " + str);
        parameters.setFlashMode(str);
    }

    public static void b(Camera.Parameters parameters, boolean z) {
        int minExposureCompensation = parameters.getMinExposureCompensation();
        int maxExposureCompensation = parameters.getMaxExposureCompensation();
        float exposureCompensationStep = parameters.getExposureCompensationStep();
        if (!(minExposureCompensation == 0 && maxExposureCompensation == 0)) {
            float f2 = 0.0f;
            if (exposureCompensationStep > 0.0f) {
                if (!z) {
                    f2 = d;
                }
                int round = Math.round(f2 / exposureCompensationStep);
                float f3 = exposureCompensationStep * ((float) round);
                int max = Math.max(Math.min(round, maxExposureCompensation), minExposureCompensation);
                if (parameters.getExposureCompensation() == max) {
                    Log.i(f13031a, "Exposure compensation already set to " + max + " / " + f3);
                    return;
                }
                Log.i(f13031a, "Setting exposure compensation to " + max + " / " + f3);
                parameters.setExposureCompensation(max);
                return;
            }
        }
        Log.i(f13031a, "Camera does not support exposure compensation");
    }

    public static void a(Camera.Parameters parameters) {
        a(parameters, 10, 20);
    }

    public static void a(Camera.Parameters parameters, int i2, int i3) {
        List<int[]> supportedPreviewFpsRange = parameters.getSupportedPreviewFpsRange();
        Log.i(f13031a, "Supported FPS ranges: " + a((Collection<int[]>) supportedPreviewFpsRange));
        if (supportedPreviewFpsRange != null && !supportedPreviewFpsRange.isEmpty()) {
            int[] iArr = null;
            Iterator<int[]> it = supportedPreviewFpsRange.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                int[] next = it.next();
                int i4 = next[0];
                int i5 = next[1];
                if (i4 >= i2 * 1000 && i5 <= i3 * 1000) {
                    iArr = next;
                    break;
                }
            }
            if (iArr == null) {
                Log.i(f13031a, "No suitable FPS range?");
                return;
            }
            int[] iArr2 = new int[2];
            parameters.getPreviewFpsRange(iArr2);
            if (Arrays.equals(iArr2, iArr)) {
                Log.i(f13031a, "FPS range already set to " + Arrays.toString(iArr));
                return;
            }
            Log.i(f13031a, "Setting FPS range to " + Arrays.toString(iArr));
            parameters.setPreviewFpsRange(iArr[0], iArr[1]);
        }
    }

    public static void b(Camera.Parameters parameters) {
        if (parameters.getMaxNumFocusAreas() > 0) {
            Log.i(f13031a, "Old focus areas: " + a((Iterable<Camera.Area>) parameters.getFocusAreas()));
            List<Camera.Area> a2 = a(400);
            Log.i(f13031a, "Setting focus area to : " + a((Iterable<Camera.Area>) a2));
            parameters.setFocusAreas(a2);
            return;
        }
        Log.i(f13031a, "Device does not support focus areas");
    }

    public static void c(Camera.Parameters parameters) {
        if (parameters.getMaxNumMeteringAreas() > 0) {
            Log.i(f13031a, "Old metering areas: " + parameters.getMeteringAreas());
            List<Camera.Area> a2 = a(400);
            Log.i(f13031a, "Setting metering area to : " + a((Iterable<Camera.Area>) a2));
            parameters.setMeteringAreas(a2);
            return;
        }
        Log.i(f13031a, "Device does not support metering areas");
    }

    private static List<Camera.Area> a(int i2) {
        int i3 = -i2;
        return Collections.singletonList(new Camera.Area(new Rect(i3, i3, i2, i2), 1));
    }

    public static void d(Camera.Parameters parameters) {
        if (!parameters.isVideoStabilizationSupported()) {
            Log.i(f13031a, "This device does not support video stabilization");
        } else if (parameters.getVideoStabilization()) {
            Log.i(f13031a, "Video stabilization already enabled");
        } else {
            Log.i(f13031a, "Enabling video stabilization...");
            parameters.setVideoStabilization(true);
        }
    }

    public static void e(Camera.Parameters parameters) {
        if (BarcodeGenActivity.BARCODE.equals(parameters.getSceneMode())) {
            Log.i(f13031a, "Barcode scene mode already set");
            return;
        }
        String a2 = a("scene mode", (Collection<String>) parameters.getSupportedSceneModes(), BarcodeGenActivity.BARCODE);
        if (a2 != null) {
            parameters.setSceneMode(a2);
        }
    }

    public static void a(Camera.Parameters parameters, double d2) {
        if (parameters.isZoomSupported()) {
            Integer b2 = b(parameters, d2);
            if (b2 != null) {
                if (parameters.getZoom() == b2.intValue()) {
                    Log.i(f13031a, "Zoom is already set to " + b2);
                    return;
                }
                Log.i(f13031a, "Setting zoom to " + b2);
                parameters.setZoom(b2.intValue());
                return;
            }
            return;
        }
        Log.i(f13031a, "Zoom is not supported");
    }

    private static Integer b(Camera.Parameters parameters, double d2) {
        List<Integer> zoomRatios = parameters.getZoomRatios();
        Log.i(f13031a, "Zoom ratios: " + zoomRatios);
        int maxZoom = parameters.getMaxZoom();
        if (zoomRatios == null || zoomRatios.isEmpty() || zoomRatios.size() != maxZoom + 1) {
            Log.w(f13031a, "Invalid zoom ratios!");
            return null;
        }
        double d3 = d2 * 100.0d;
        double d4 = Double.POSITIVE_INFINITY;
        int i2 = 0;
        for (int i3 = 0; i3 < zoomRatios.size(); i3++) {
            double intValue = (double) zoomRatios.get(i3).intValue();
            Double.isNaN(intValue);
            double abs = Math.abs(intValue - d3);
            if (abs < d4) {
                i2 = i3;
                d4 = abs;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Chose zoom ratio of ");
        double intValue2 = (double) zoomRatios.get(i2).intValue();
        Double.isNaN(intValue2);
        sb.append(intValue2 / 100.0d);
        Log.i(f13031a, sb.toString());
        return Integer.valueOf(i2);
    }

    public static void f(Camera.Parameters parameters) {
        if ("negative".equals(parameters.getColorEffect())) {
            Log.i(f13031a, "Negative effect already set");
            return;
        }
        String a2 = a("color effect", (Collection<String>) parameters.getSupportedColorEffects(), "negative");
        if (a2 != null) {
            parameters.setColorEffect(a2);
        }
    }

    public static Point a(Camera.Parameters parameters, Point point) {
        List<Camera.Size> supportedPreviewSizes = parameters.getSupportedPreviewSizes();
        if (supportedPreviewSizes == null) {
            Log.w(f13031a, "Device returned no supported preview sizes; using default");
            Camera.Size previewSize = parameters.getPreviewSize();
            if (previewSize != null) {
                return new Point(previewSize.width, previewSize.height);
            }
            throw new IllegalStateException("Parameters contained no preview size!");
        }
        ArrayList<Camera.Size> arrayList = new ArrayList<>(supportedPreviewSizes);
        Collections.sort(arrayList, new Comparator<Camera.Size>() {
            /* renamed from: a */
            public int compare(Camera.Size size, Camera.Size size2) {
                int i = size.height * size.width;
                int i2 = size2.height * size2.width;
                if (i2 < i) {
                    return -1;
                }
                return i2 > i ? 1 : 0;
            }
        });
        if (Log.isLoggable(f13031a, 4)) {
            StringBuilder sb = new StringBuilder();
            for (Camera.Size size : arrayList) {
                sb.append(size.width);
                sb.append('x');
                sb.append(size.height);
                sb.append(' ');
            }
            Log.i(f13031a, "Supported preview sizes: " + sb);
        }
        double d2 = (double) point.x;
        double d3 = (double) point.y;
        Double.isNaN(d2);
        Double.isNaN(d3);
        double d4 = d2 / d3;
        Iterator it = arrayList.iterator();
        while (true) {
            boolean z = false;
            if (it.hasNext()) {
                Camera.Size size2 = (Camera.Size) it.next();
                int i2 = size2.width;
                int i3 = size2.height;
                if (i2 * i3 < c) {
                    it.remove();
                } else {
                    if (i2 < i3) {
                        z = true;
                    }
                    int i4 = z ? i3 : i2;
                    int i5 = z ? i2 : i3;
                    double d5 = (double) i4;
                    double d6 = (double) i5;
                    Double.isNaN(d5);
                    Double.isNaN(d6);
                    if (Math.abs((d5 / d6) - d4) > f) {
                        it.remove();
                    } else if (i4 == point.x && i5 == point.y) {
                        Point point2 = new Point(i2, i3);
                        Log.i(f13031a, "Found preview size exactly matching screen size: " + point2);
                        return point2;
                    }
                }
            } else if (!arrayList.isEmpty()) {
                Camera.Size size3 = (Camera.Size) arrayList.get(0);
                Point point3 = new Point(size3.width, size3.height);
                Log.i(f13031a, "Using largest suitable preview size: " + point3);
                return point3;
            } else {
                Camera.Size previewSize2 = parameters.getPreviewSize();
                if (previewSize2 != null) {
                    Point point4 = new Point(previewSize2.width, previewSize2.height);
                    Log.i(f13031a, "No suitable preview sizes, using default: " + point4);
                    return point4;
                }
                throw new IllegalStateException("Parameters contained no preview size!");
            }
        }
    }

    private static String a(String str, Collection<String> collection, String... strArr) {
        Log.i(f13031a, "Requesting " + str + " value from among: " + Arrays.toString(strArr));
        Log.i(f13031a, "Supported " + str + " values: " + collection);
        if (collection != null) {
            for (String str2 : strArr) {
                if (collection.contains(str2)) {
                    Log.i(f13031a, "Can set " + str + " to: " + str2);
                    return str2;
                }
            }
        }
        Log.i(f13031a, "No supported values match");
        return null;
    }

    private static String a(Collection<int[]> collection) {
        if (collection == null || collection.isEmpty()) {
            return XMPConst.ai;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Operators.ARRAY_START);
        Iterator<int[]> it = collection.iterator();
        while (it.hasNext()) {
            sb.append(Arrays.toString(it.next()));
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(Operators.ARRAY_END);
        return sb.toString();
    }

    private static String a(Iterable<Camera.Area> iterable) {
        if (iterable == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Camera.Area next : iterable) {
            sb.append(next.rect);
            sb.append(Operators.CONDITION_IF_MIDDLE);
            sb.append(next.weight);
            sb.append(' ');
        }
        return sb.toString();
    }

    public static String g(Camera.Parameters parameters) {
        return a((CharSequence) parameters.flatten());
    }

    public static String a(CharSequence charSequence) {
        StringBuilder sb = new StringBuilder(1000);
        sb.append("BOARD=");
        sb.append(Build.BOARD);
        sb.append(10);
        sb.append("BRAND=");
        sb.append(Build.BRAND);
        sb.append(10);
        sb.append("CPU_ABI=");
        sb.append(Build.CPU_ABI);
        sb.append(10);
        sb.append("DEVICE=");
        sb.append(Build.DEVICE);
        sb.append(10);
        sb.append("DISPLAY=");
        sb.append(Build.DISPLAY);
        sb.append(10);
        sb.append("FINGERPRINT=");
        sb.append(Build.FINGERPRINT);
        sb.append(10);
        sb.append("HOST=");
        sb.append(Build.HOST);
        sb.append(10);
        sb.append("ID=");
        sb.append(Build.ID);
        sb.append(10);
        sb.append("MANUFACTURER=");
        sb.append(Build.MANUFACTURER);
        sb.append(10);
        sb.append("MODEL=");
        sb.append(Build.MODEL);
        sb.append(10);
        sb.append("PRODUCT=");
        sb.append(Build.PRODUCT);
        sb.append(10);
        sb.append("TAGS=");
        sb.append(Build.TAGS);
        sb.append(10);
        sb.append("TIME=");
        sb.append(Build.TIME);
        sb.append(10);
        sb.append("TYPE=");
        sb.append(Build.TYPE);
        sb.append(10);
        sb.append("USER=");
        sb.append(Build.USER);
        sb.append(10);
        sb.append("VERSION.CODENAME=");
        sb.append(Build.VERSION.CODENAME);
        sb.append(10);
        sb.append("VERSION.INCREMENTAL=");
        sb.append(Build.VERSION.INCREMENTAL);
        sb.append(10);
        sb.append("VERSION.RELEASE=");
        sb.append(Build.VERSION.RELEASE);
        sb.append(10);
        sb.append("VERSION.SDK_INT=");
        sb.append(Build.VERSION.SDK_INT);
        sb.append(10);
        if (charSequence != null) {
            String[] split = b.split(charSequence);
            Arrays.sort(split);
            for (String append : split) {
                sb.append(append);
                sb.append(10);
            }
        }
        return sb.toString();
    }
}
