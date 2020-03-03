package org.reactnative.camera;

import android.annotation.SuppressLint;
import android.media.CamcorderProfile;
import android.media.MediaActionSound;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.google.android.cameraview.CameraView;
import com.taobao.weex.common.Constants;
import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.MultiFormatReader;
import com.xiaomi.zxing.Result;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.reactnative.barcodedetector.RNBarcodeDetector;
import org.reactnative.camera.tasks.BarCodeScannerAsyncTaskDelegate;
import org.reactnative.camera.tasks.BarcodeDetectorAsyncTaskDelegate;
import org.reactnative.camera.tasks.FaceDetectorAsyncTaskDelegate;
import org.reactnative.camera.tasks.PictureSavedDelegate;
import org.reactnative.camera.tasks.ResolveTakenPictureAsyncTask;
import org.reactnative.camera.tasks.TextRecognizerAsyncTaskDelegate;
import org.reactnative.camera.utils.RNFileUtils;
import org.reactnative.facedetector.RNFaceDetector;

public class RNCameraView extends CameraView implements LifecycleEventListener, BarCodeScannerAsyncTaskDelegate, BarcodeDetectorAsyncTaskDelegate, FaceDetectorAsyncTaskDelegate, PictureSavedDelegate, TextRecognizerAsyncTaskDelegate {
    /* access modifiers changed from: private */
    public int A;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public ThemedReactContext f4130a;
    /* access modifiers changed from: private */
    public Queue<Promise> b = new ConcurrentLinkedQueue();
    public volatile boolean barCodeScannerTaskLock = false;
    /* access modifiers changed from: private */
    public Map<Promise, ReadableMap> c = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public Map<Promise, File> d = new ConcurrentHashMap();
    /* access modifiers changed from: private */
    public Promise e;
    private List<String> f = null;
    public volatile boolean faceDetectorTaskLock = false;
    /* access modifiers changed from: private */
    public Boolean g = false;
    public volatile boolean googleBarcodeDetectorTaskLock = false;
    /* access modifiers changed from: private */
    public boolean h = false;
    /* access modifiers changed from: private */
    public boolean i = true;
    /* access modifiers changed from: private */
    public boolean j = false;
    /* access modifiers changed from: private */
    public Boolean k = false;
    /* access modifiers changed from: private */
    public Boolean l = false;
    /* access modifiers changed from: private */
    public MultiFormatReader m;
    /* access modifiers changed from: private */
    public RNFaceDetector n;
    /* access modifiers changed from: private */
    public RNBarcodeDetector o;
    /* access modifiers changed from: private */
    public boolean p = false;
    /* access modifiers changed from: private */
    public boolean q = false;
    /* access modifiers changed from: private */
    public boolean r = false;
    /* access modifiers changed from: private */
    public boolean s = false;
    private int t = RNFaceDetector.f;
    public volatile boolean textRecognizerTaskLock = false;
    private int u = RNFaceDetector.d;
    private int v = RNFaceDetector.b;
    private int w = RNBarcodeDetector.d;
    /* access modifiers changed from: private */
    public int x = RNBarcodeDetector.f4120a;
    private boolean y = true;
    /* access modifiers changed from: private */
    public int z;

    @SuppressLint({"all"})
    public void requestLayout() {
    }

    public RNCameraView(ThemedReactContext themedReactContext) {
        super(themedReactContext, true);
        this.f4130a = themedReactContext;
        themedReactContext.addLifecycleEventListener(this);
        addCallback(new CameraView.Callback() {
            public void onCameraOpened(CameraView cameraView) {
                RNCameraViewHelper.a((ViewGroup) cameraView);
            }

            public void onMountError(CameraView cameraView) {
                RNCameraViewHelper.a((ViewGroup) cameraView, "Camera view threw an error - component could not be rendered.");
            }

            public void onPictureTaken(CameraView cameraView, byte[] bArr, int i) {
                Promise promise = (Promise) RNCameraView.this.b.poll();
                ReadableMap readableMap = (ReadableMap) RNCameraView.this.c.remove(promise);
                if (readableMap.hasKey("fastMode") && readableMap.getBoolean("fastMode")) {
                    promise.resolve((Object) null);
                }
                File file = (File) RNCameraView.this.d.remove(promise);
                if (Build.VERSION.SDK_INT >= 11) {
                    new ResolveTakenPictureAsyncTask(bArr, promise, readableMap, file, i, RNCameraView.this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                } else {
                    new ResolveTakenPictureAsyncTask(bArr, promise, readableMap, file, i, RNCameraView.this).execute(new Void[0]);
                }
                RNCameraViewHelper.b((ViewGroup) cameraView);
            }

            public void onVideoRecorded(CameraView cameraView, String str, int i, int i2) {
                if (RNCameraView.this.e != null) {
                    if (str != null) {
                        WritableMap createMap = Arguments.createMap();
                        createMap.putBoolean("isRecordingInterrupted", RNCameraView.this.l.booleanValue());
                        createMap.putInt("videoOrientation", i);
                        createMap.putInt("deviceOrientation", i2);
                        createMap.putString("uri", RNFileUtils.b(new File(str)).toString());
                        RNCameraView.this.e.resolve(createMap);
                    } else {
                        RNCameraView.this.e.reject("E_RECORDING", "Couldn't stop recording - there is none in progress");
                    }
                    Boolean unused = RNCameraView.this.k = false;
                    Boolean unused2 = RNCameraView.this.l = false;
                    Promise unused3 = RNCameraView.this.e = null;
                }
            }

            /* JADX WARNING: type inference failed for: r15v2 */
            /* JADX WARNING: type inference failed for: r15v4 */
            /* JADX WARNING: Incorrect type for immutable var: ssa=int, code=?, for r15v1, types: [int, boolean] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onFramePreview(com.google.android.cameraview.CameraView r26, byte[] r27, int r28, int r29, int r30) {
                /*
                    r25 = this;
                    r0 = r25
                    r1 = r26
                    r15 = r27
                    org.reactnative.camera.RNCameraView r2 = org.reactnative.camera.RNCameraView.this
                    int r2 = r2.getFacing()
                    org.reactnative.camera.RNCameraView r3 = org.reactnative.camera.RNCameraView.this
                    int r3 = r3.getCameraOrientation()
                    r4 = r30
                    int r16 = org.reactnative.camera.RNCameraViewHelper.a(r4, r2, r3)
                    org.reactnative.camera.RNCameraView r2 = org.reactnative.camera.RNCameraView.this
                    boolean r2 = r2.r
                    r14 = 0
                    r13 = 1
                    if (r2 == 0) goto L_0x002e
                    org.reactnative.camera.RNCameraView r2 = org.reactnative.camera.RNCameraView.this
                    boolean r2 = r2.barCodeScannerTaskLock
                    if (r2 != 0) goto L_0x002e
                    boolean r2 = r1 instanceof org.reactnative.camera.tasks.BarCodeScannerAsyncTaskDelegate
                    if (r2 == 0) goto L_0x002e
                    r2 = 1
                    goto L_0x002f
                L_0x002e:
                    r2 = 0
                L_0x002f:
                    org.reactnative.camera.RNCameraView r3 = org.reactnative.camera.RNCameraView.this
                    boolean r3 = r3.p
                    if (r3 == 0) goto L_0x0043
                    org.reactnative.camera.RNCameraView r3 = org.reactnative.camera.RNCameraView.this
                    boolean r3 = r3.faceDetectorTaskLock
                    if (r3 != 0) goto L_0x0043
                    boolean r3 = r1 instanceof org.reactnative.camera.tasks.FaceDetectorAsyncTaskDelegate
                    if (r3 == 0) goto L_0x0043
                    r8 = 1
                    goto L_0x0044
                L_0x0043:
                    r8 = 0
                L_0x0044:
                    org.reactnative.camera.RNCameraView r3 = org.reactnative.camera.RNCameraView.this
                    boolean r3 = r3.q
                    if (r3 == 0) goto L_0x0059
                    org.reactnative.camera.RNCameraView r3 = org.reactnative.camera.RNCameraView.this
                    boolean r3 = r3.googleBarcodeDetectorTaskLock
                    if (r3 != 0) goto L_0x0059
                    boolean r3 = r1 instanceof org.reactnative.camera.tasks.BarcodeDetectorAsyncTaskDelegate
                    if (r3 == 0) goto L_0x0059
                    r17 = 1
                    goto L_0x005b
                L_0x0059:
                    r17 = 0
                L_0x005b:
                    org.reactnative.camera.RNCameraView r3 = org.reactnative.camera.RNCameraView.this
                    boolean r3 = r3.s
                    if (r3 == 0) goto L_0x0070
                    org.reactnative.camera.RNCameraView r3 = org.reactnative.camera.RNCameraView.this
                    boolean r3 = r3.textRecognizerTaskLock
                    if (r3 != 0) goto L_0x0070
                    boolean r3 = r1 instanceof org.reactnative.camera.tasks.TextRecognizerAsyncTaskDelegate
                    if (r3 == 0) goto L_0x0070
                    r18 = 1
                    goto L_0x0072
                L_0x0070:
                    r18 = 0
                L_0x0072:
                    if (r2 != 0) goto L_0x007b
                    if (r8 != 0) goto L_0x007b
                    if (r17 != 0) goto L_0x007b
                    if (r18 != 0) goto L_0x007b
                    return
                L_0x007b:
                    int r3 = r15.length
                    double r3 = (double) r3
                    r5 = 4609434218613702656(0x3ff8000000000000, double:1.5)
                    r12 = r28
                    double r9 = (double) r12
                    java.lang.Double.isNaN(r9)
                    double r9 = r9 * r5
                    r11 = r29
                    double r5 = (double) r11
                    java.lang.Double.isNaN(r5)
                    double r9 = r9 * r5
                    int r5 = (r3 > r9 ? 1 : (r3 == r9 ? 0 : -1))
                    if (r5 >= 0) goto L_0x0094
                    return
                L_0x0094:
                    if (r2 == 0) goto L_0x00b4
                    org.reactnative.camera.RNCameraView r2 = org.reactnative.camera.RNCameraView.this
                    r2.barCodeScannerTaskLock = r13
                    r3 = r1
                    org.reactnative.camera.tasks.BarCodeScannerAsyncTaskDelegate r3 = (org.reactnative.camera.tasks.BarCodeScannerAsyncTaskDelegate) r3
                    org.reactnative.camera.tasks.BarCodeScannerAsyncTask r9 = new org.reactnative.camera.tasks.BarCodeScannerAsyncTask
                    org.reactnative.camera.RNCameraView r2 = org.reactnative.camera.RNCameraView.this
                    com.xiaomi.zxing.MultiFormatReader r4 = r2.m
                    r2 = r9
                    r5 = r27
                    r6 = r28
                    r7 = r29
                    r2.<init>(r3, r4, r5, r6, r7)
                    java.lang.Void[] r2 = new java.lang.Void[r14]
                    r9.execute(r2)
                L_0x00b4:
                    if (r8 == 0) goto L_0x010e
                    org.reactnative.camera.RNCameraView r2 = org.reactnative.camera.RNCameraView.this
                    r2.faceDetectorTaskLock = r13
                    r3 = r1
                    org.reactnative.camera.tasks.FaceDetectorAsyncTaskDelegate r3 = (org.reactnative.camera.tasks.FaceDetectorAsyncTaskDelegate) r3
                    org.reactnative.camera.tasks.FaceDetectorAsyncTask r10 = new org.reactnative.camera.tasks.FaceDetectorAsyncTask
                    org.reactnative.camera.RNCameraView r2 = org.reactnative.camera.RNCameraView.this
                    org.reactnative.facedetector.RNFaceDetector r4 = r2.n
                    org.reactnative.camera.RNCameraView r2 = org.reactnative.camera.RNCameraView.this
                    android.content.res.Resources r2 = r2.getResources()
                    android.util.DisplayMetrics r2 = r2.getDisplayMetrics()
                    float r9 = r2.density
                    org.reactnative.camera.RNCameraView r2 = org.reactnative.camera.RNCameraView.this
                    int r19 = r2.getFacing()
                    org.reactnative.camera.RNCameraView r2 = org.reactnative.camera.RNCameraView.this
                    int r20 = r2.getWidth()
                    org.reactnative.camera.RNCameraView r2 = org.reactnative.camera.RNCameraView.this
                    int r21 = r2.getHeight()
                    org.reactnative.camera.RNCameraView r2 = org.reactnative.camera.RNCameraView.this
                    int r22 = r2.z
                    org.reactnative.camera.RNCameraView r2 = org.reactnative.camera.RNCameraView.this
                    int r23 = r2.A
                    r2 = r10
                    r5 = r27
                    r6 = r28
                    r7 = r29
                    r8 = r16
                    r1 = r10
                    r10 = r19
                    r11 = r20
                    r12 = r21
                    r15 = 1
                    r13 = r22
                    r15 = 0
                    r14 = r23
                    r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
                    java.lang.Void[] r2 = new java.lang.Void[r15]
                    r1.execute(r2)
                    goto L_0x010f
                L_0x010e:
                    r15 = 0
                L_0x010f:
                    if (r17 == 0) goto L_0x01bd
                    org.reactnative.camera.RNCameraView r1 = org.reactnative.camera.RNCameraView.this
                    r2 = 1
                    r1.googleBarcodeDetectorTaskLock = r2
                    org.reactnative.camera.RNCameraView r1 = org.reactnative.camera.RNCameraView.this
                    int r1 = r1.x
                    int r2 = org.reactnative.barcodedetector.RNBarcodeDetector.f4120a
                    if (r1 != r2) goto L_0x0127
                    org.reactnative.camera.RNCameraView r1 = org.reactnative.camera.RNCameraView.this
                    boolean unused = r1.j = r15
                    r3 = 1
                    goto L_0x014f
                L_0x0127:
                    org.reactnative.camera.RNCameraView r1 = org.reactnative.camera.RNCameraView.this
                    int r1 = r1.x
                    int r2 = org.reactnative.barcodedetector.RNBarcodeDetector.b
                    if (r1 != r2) goto L_0x013f
                    org.reactnative.camera.RNCameraView r1 = org.reactnative.camera.RNCameraView.this
                    org.reactnative.camera.RNCameraView r2 = org.reactnative.camera.RNCameraView.this
                    boolean r2 = r2.j
                    r3 = 1
                    r2 = r2 ^ r3
                    boolean unused = r1.j = r2
                    goto L_0x014f
                L_0x013f:
                    r3 = 1
                    org.reactnative.camera.RNCameraView r1 = org.reactnative.camera.RNCameraView.this
                    int r1 = r1.x
                    int r2 = org.reactnative.barcodedetector.RNBarcodeDetector.c
                    if (r1 != r2) goto L_0x014f
                    org.reactnative.camera.RNCameraView r1 = org.reactnative.camera.RNCameraView.this
                    boolean unused = r1.j = r3
                L_0x014f:
                    org.reactnative.camera.RNCameraView r1 = org.reactnative.camera.RNCameraView.this
                    boolean r1 = r1.j
                    if (r1 == 0) goto L_0x0168
                    r1 = r27
                    r2 = 0
                    r14 = 1
                L_0x015b:
                    int r3 = r1.length
                    if (r2 >= r3) goto L_0x016b
                    byte r3 = r1[r2]
                    r3 = r3 ^ -1
                    byte r3 = (byte) r3
                    r1[r2] = r3
                    int r2 = r2 + 1
                    goto L_0x015b
                L_0x0168:
                    r1 = r27
                    r14 = 1
                L_0x016b:
                    r17 = r26
                    r3 = r17
                    org.reactnative.camera.tasks.BarcodeDetectorAsyncTaskDelegate r3 = (org.reactnative.camera.tasks.BarcodeDetectorAsyncTaskDelegate) r3
                    org.reactnative.camera.tasks.BarcodeDetectorAsyncTask r13 = new org.reactnative.camera.tasks.BarcodeDetectorAsyncTask
                    org.reactnative.camera.RNCameraView r2 = org.reactnative.camera.RNCameraView.this
                    org.reactnative.barcodedetector.RNBarcodeDetector r4 = r2.o
                    org.reactnative.camera.RNCameraView r2 = org.reactnative.camera.RNCameraView.this
                    android.content.res.Resources r2 = r2.getResources()
                    android.util.DisplayMetrics r2 = r2.getDisplayMetrics()
                    float r9 = r2.density
                    org.reactnative.camera.RNCameraView r2 = org.reactnative.camera.RNCameraView.this
                    int r10 = r2.getFacing()
                    org.reactnative.camera.RNCameraView r2 = org.reactnative.camera.RNCameraView.this
                    int r11 = r2.getWidth()
                    org.reactnative.camera.RNCameraView r2 = org.reactnative.camera.RNCameraView.this
                    int r12 = r2.getHeight()
                    org.reactnative.camera.RNCameraView r2 = org.reactnative.camera.RNCameraView.this
                    int r19 = r2.z
                    org.reactnative.camera.RNCameraView r2 = org.reactnative.camera.RNCameraView.this
                    int r20 = r2.A
                    r2 = r13
                    r5 = r27
                    r6 = r28
                    r7 = r29
                    r8 = r16
                    r24 = r13
                    r13 = r19
                    r14 = r20
                    r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14)
                    java.lang.Void[] r2 = new java.lang.Void[r15]
                    r3 = r24
                    r3.execute(r2)
                    goto L_0x01c1
                L_0x01bd:
                    r1 = r27
                    r17 = r26
                L_0x01c1:
                    if (r18 == 0) goto L_0x020f
                    org.reactnative.camera.RNCameraView r2 = org.reactnative.camera.RNCameraView.this
                    r3 = 1
                    r2.textRecognizerTaskLock = r3
                    r2 = r17
                    org.reactnative.camera.tasks.TextRecognizerAsyncTaskDelegate r2 = (org.reactnative.camera.tasks.TextRecognizerAsyncTaskDelegate) r2
                    org.reactnative.camera.tasks.TextRecognizerAsyncTask r14 = new org.reactnative.camera.tasks.TextRecognizerAsyncTask
                    org.reactnative.camera.RNCameraView r3 = org.reactnative.camera.RNCameraView.this
                    com.facebook.react.uimanager.ThemedReactContext r3 = r3.f4130a
                    org.reactnative.camera.RNCameraView r4 = org.reactnative.camera.RNCameraView.this
                    android.content.res.Resources r4 = r4.getResources()
                    android.util.DisplayMetrics r4 = r4.getDisplayMetrics()
                    float r8 = r4.density
                    org.reactnative.camera.RNCameraView r4 = org.reactnative.camera.RNCameraView.this
                    int r9 = r4.getFacing()
                    org.reactnative.camera.RNCameraView r4 = org.reactnative.camera.RNCameraView.this
                    int r10 = r4.getWidth()
                    org.reactnative.camera.RNCameraView r4 = org.reactnative.camera.RNCameraView.this
                    int r11 = r4.getHeight()
                    org.reactnative.camera.RNCameraView r4 = org.reactnative.camera.RNCameraView.this
                    int r12 = r4.z
                    org.reactnative.camera.RNCameraView r4 = org.reactnative.camera.RNCameraView.this
                    int r13 = r4.A
                    r1 = r14
                    r4 = r27
                    r5 = r28
                    r6 = r29
                    r7 = r16
                    r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13)
                    java.lang.Void[] r1 = new java.lang.Void[r15]
                    r14.execute(r1)
                L_0x020f:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: org.reactnative.camera.RNCameraView.AnonymousClass1.onFramePreview(com.google.android.cameraview.CameraView, byte[], int, int, int):void");
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z2, int i2, int i3, int i4, int i5) {
        int i6;
        int i7;
        View view = getView();
        if (view != null) {
            float f2 = (float) (i4 - i2);
            float f3 = (float) (i5 - i3);
            float f4 = getAspectRatio().toFloat();
            int i8 = getResources().getConfiguration().orientation;
            setBackgroundColor(-16777216);
            if (i8 == 2) {
                float f5 = f4 * f3;
                if (f5 < f2) {
                    i7 = (int) (f2 / f4);
                    i6 = (int) f2;
                } else {
                    i6 = (int) f5;
                    i7 = (int) f3;
                }
            } else {
                float f6 = f4 * f2;
                if (f6 > f3) {
                    i7 = (int) f6;
                    i6 = (int) f2;
                } else {
                    i6 = (int) (f3 / f4);
                    i7 = (int) f3;
                }
            }
            int i9 = (int) ((f2 - ((float) i6)) / 2.0f);
            int i10 = (int) ((f3 - ((float) i7)) / 2.0f);
            this.z = i9;
            this.A = i10;
            view.layout(i9, i10, i6 + i9, i7 + i10);
        }
    }

    public void setBarCodeTypes(List<String> list) {
        this.f = list;
        a();
    }

    public void setPlaySoundOnCapture(Boolean bool) {
        this.g = bool;
    }

    public void takePicture(final ReadableMap readableMap, final Promise promise, final File file) {
        this.mBgHandler.post(new Runnable() {
            public void run() {
                RNCameraView.this.b.add(promise);
                RNCameraView.this.c.put(promise, readableMap);
                RNCameraView.this.d.put(promise, file);
                if (RNCameraView.this.g.booleanValue()) {
                    new MediaActionSound().play(0);
                }
                try {
                    RNCameraView.super.takePicture(readableMap);
                } catch (Exception e) {
                    RNCameraView.this.b.remove(promise);
                    RNCameraView.this.c.remove(promise);
                    RNCameraView.this.d.remove(promise);
                    promise.reject("E_TAKE_PICTURE_FAILED", e.getMessage());
                }
            }
        });
    }

    public void onPictureSaved(WritableMap writableMap) {
        RNCameraViewHelper.a((ViewGroup) this, writableMap);
    }

    public void record(final ReadableMap readableMap, final Promise promise, final File file) {
        this.mBgHandler.post(new Runnable() {
            public void run() {
                try {
                    String string = readableMap.hasKey("path") ? readableMap.getString("path") : RNFileUtils.a(file, ".mp4");
                    int i = readableMap.hasKey("maxDuration") ? readableMap.getInt("maxDuration") : -1;
                    int i2 = readableMap.hasKey("maxFileSize") ? readableMap.getInt("maxFileSize") : -1;
                    CamcorderProfile camcorderProfile = CamcorderProfile.get(1);
                    if (readableMap.hasKey(Constants.Name.QUALITY)) {
                        camcorderProfile = RNCameraViewHelper.a(readableMap.getInt(Constants.Name.QUALITY));
                    }
                    CamcorderProfile camcorderProfile2 = camcorderProfile;
                    if (readableMap.hasKey("videoBitrate")) {
                        camcorderProfile2.videoBitRate = readableMap.getInt("videoBitrate");
                    }
                    if (RNCameraView.super.record(string, i * 1000, i2, readableMap.hasKey("mute") ? !readableMap.getBoolean("mute") : true, camcorderProfile2, readableMap.hasKey("orientation") ? readableMap.getInt("orientation") : 0)) {
                        Boolean unused = RNCameraView.this.k = true;
                        Promise unused2 = RNCameraView.this.e = promise;
                        return;
                    }
                    promise.reject("E_RECORDING_FAILED", "Starting video recording failed. Another recording might be in progress.");
                } catch (IOException unused3) {
                    promise.reject("E_RECORDING_FAILED", "Starting video recording failed - could not create video file.");
                }
            }
        });
    }

    private void a() {
        this.m = new MultiFormatReader();
        EnumMap enumMap = new EnumMap(DecodeHintType.class);
        EnumSet<E> noneOf = EnumSet.noneOf(BarcodeFormat.class);
        if (this.f != null) {
            for (String str : this.f) {
                String str2 = (String) CameraModule.VALID_BARCODE_TYPES.get(str);
                if (str2 != null) {
                    noneOf.add(BarcodeFormat.valueOf(str2));
                }
            }
        }
        enumMap.put(DecodeHintType.POSSIBLE_FORMATS, noneOf);
        this.m.a((Map<DecodeHintType, ?>) enumMap);
    }

    public void setShouldScanBarCodes(boolean z2) {
        if (z2 && this.m == null) {
            a();
        }
        this.r = z2;
        setScanning(this.p || this.q || this.r || this.s);
    }

    public void onBarCodeRead(Result result, int i2, int i3) {
        String barcodeFormat = result.d().toString();
        if (this.r && this.f.contains(barcodeFormat)) {
            RNCameraViewHelper.a(this, result, i2, i3);
        }
    }

    public void onBarCodeScanningTaskCompleted() {
        this.barCodeScannerTaskLock = false;
        if (this.m != null) {
            this.m.a();
        }
    }

    private void b() {
        this.n = new RNFaceDetector(this.f4130a);
        this.n.c(this.t);
        this.n.b(this.u);
        this.n.a(this.v);
        this.n.a(this.y);
    }

    public void setFaceDetectionLandmarks(int i2) {
        this.u = i2;
        if (this.n != null) {
            this.n.b(i2);
        }
    }

    public void setFaceDetectionClassifications(int i2) {
        this.v = i2;
        if (this.n != null) {
            this.n.a(i2);
        }
    }

    public void setFaceDetectionMode(int i2) {
        this.t = i2;
        if (this.n != null) {
            this.n.c(i2);
        }
    }

    public void setTracking(boolean z2) {
        this.y = z2;
        if (this.n != null) {
            this.n.a(z2);
        }
    }

    public void setShouldDetectFaces(boolean z2) {
        if (z2 && this.n == null) {
            b();
        }
        this.p = z2;
        setScanning(this.p || this.q || this.r || this.s);
    }

    public void onFacesDetected(WritableArray writableArray) {
        if (this.p) {
            RNCameraViewHelper.a((ViewGroup) this, writableArray);
        }
    }

    public void onFaceDetectionError(RNFaceDetector rNFaceDetector) {
        if (this.p) {
            RNCameraViewHelper.a((ViewGroup) this, rNFaceDetector);
        }
    }

    public void onFaceDetectingTaskCompleted() {
        this.faceDetectorTaskLock = false;
    }

    private void c() {
        this.o = new RNBarcodeDetector(this.f4130a);
        this.o.a(this.w);
    }

    public void setShouldGoogleDetectBarcodes(boolean z2) {
        if (z2 && this.o == null) {
            c();
        }
        this.q = z2;
        setScanning(this.p || this.q || this.r || this.s);
    }

    public void setGoogleVisionBarcodeType(int i2) {
        this.w = i2;
        if (this.o != null) {
            this.o.a(i2);
        }
    }

    public void setGoogleVisionBarcodeMode(int i2) {
        this.x = i2;
    }

    public void onBarcodesDetected(WritableArray writableArray) {
        if (this.q) {
            RNCameraViewHelper.b(this, writableArray);
        }
    }

    public void onBarcodeDetectionError(RNBarcodeDetector rNBarcodeDetector) {
        if (this.q) {
            RNCameraViewHelper.a((ViewGroup) this, rNBarcodeDetector);
        }
    }

    public void onBarcodeDetectingTaskCompleted() {
        this.googleBarcodeDetectorTaskLock = false;
    }

    public void setShouldRecognizeText(boolean z2) {
        this.s = z2;
        setScanning(this.p || this.q || this.r || this.s);
    }

    public void onTextRecognized(WritableArray writableArray) {
        if (this.s) {
            RNCameraViewHelper.c(this, writableArray);
        }
    }

    public void onTextRecognizerTaskCompleted() {
        this.textRecognizerTaskLock = false;
    }

    public void onHostResume() {
        if (d()) {
            this.mBgHandler.post(new Runnable() {
                public void run() {
                    if ((RNCameraView.this.h && !RNCameraView.this.isCameraOpened()) || RNCameraView.this.i) {
                        boolean unused = RNCameraView.this.h = false;
                        boolean unused2 = RNCameraView.this.i = false;
                        RNCameraView.this.start();
                    }
                }
            });
        } else {
            RNCameraViewHelper.a((ViewGroup) this, "Camera permissions not granted - component could not be rendered.");
        }
    }

    public void onHostPause() {
        if (this.k.booleanValue()) {
            this.l = true;
        }
        if (!this.h && isCameraOpened()) {
            this.h = true;
            stop();
        }
    }

    @RequiresApi(api = 18)
    public void onHostDestroy() {
        if (this.n != null) {
            this.n.b();
        }
        if (this.o != null) {
            this.o.b();
        }
        this.m = null;
        stop();
        this.f4130a.removeLifecycleEventListener(this);
        cleanup();
    }

    private boolean d() {
        if (Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(getContext(), "android.permission.CAMERA") == 0) {
            return true;
        }
        return false;
    }
}
