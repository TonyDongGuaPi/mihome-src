package com.reactnative.camera;

import android.media.CamcorderProfile;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.LifecycleEventListener;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.cameraview.CameraView;
import com.google.android.gms.vision.face.Face;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.reactnative.camera.facedetector.RNFaceDetector;
import com.reactnative.camera.tasks.BarCodeScannerAsyncTask;
import com.reactnative.camera.tasks.BarCodeScannerAsyncTaskDelegate;
import com.reactnative.camera.tasks.FaceDetectorAsyncTask;
import com.reactnative.camera.tasks.FaceDetectorAsyncTaskDelegate;
import com.reactnative.camera.tasks.ResolveTakenPictureAsyncTask;
import com.reactnative.camera.utils.ImageDimensions;
import com.reactnative.camera.utils.RNFileUtils;
import com.taobao.weex.common.Constants;
import java.io.File;
import java.io.IOException;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class RNCameraView extends CameraView implements LifecycleEventListener, BarCodeScannerAsyncTaskDelegate, FaceDetectorAsyncTaskDelegate {

    /* renamed from: a  reason: collision with root package name */
    private ThemedReactContext f8667a;
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
    private boolean g = false;
    private boolean h = true;
    /* access modifiers changed from: private */
    public final MultiFormatReader i = new MultiFormatReader();
    /* access modifiers changed from: private */
    public final RNFaceDetector j;
    /* access modifiers changed from: private */
    public boolean k = false;
    /* access modifiers changed from: private */
    public boolean l = false;
    private int m = RNFaceDetector.f;
    private int n = RNFaceDetector.d;
    private int o = RNFaceDetector.b;

    public void requestLayout() {
    }

    public RNCameraView(ThemedReactContext themedReactContext) {
        super(themedReactContext, true);
        a();
        this.f8667a = themedReactContext;
        this.j = new RNFaceDetector(themedReactContext);
        b();
        themedReactContext.addLifecycleEventListener(this);
        addCallback(new CameraView.Callback() {
            public void onCameraOpened(CameraView cameraView) {
                RNCameraViewHelper.b(cameraView);
            }

            public void onMountError(CameraView cameraView) {
                RNCameraViewHelper.a((ViewGroup) cameraView);
            }

            public void a(CameraView cameraView, byte[] bArr) {
                Promise promise = (Promise) RNCameraView.this.b.poll();
                new ResolveTakenPictureAsyncTask(bArr, promise, (ReadableMap) RNCameraView.this.c.remove(promise), (File) RNCameraView.this.d.remove(promise)).execute(new Void[0]);
            }

            public void a(CameraView cameraView, String str) {
                if (RNCameraView.this.e != null) {
                    if (str != null) {
                        WritableMap createMap = Arguments.createMap();
                        createMap.putString("uri", RNFileUtils.b(new File(str)).toString());
                        RNCameraView.this.e.resolve(createMap);
                    } else {
                        RNCameraView.this.e.reject("E_RECORDING", "Couldn't stop recording - there is none in progress");
                    }
                    Promise unused = RNCameraView.this.e = null;
                }
            }

            public void onFramePreview(CameraView cameraView, byte[] bArr, int i, int i2, int i3) {
                CameraView cameraView2 = cameraView;
                int a2 = RNCameraViewHelper.a(i3, RNCameraView.this.getFacing());
                if (RNCameraView.this.l && !RNCameraView.this.barCodeScannerTaskLock && (cameraView2 instanceof BarCodeScannerAsyncTaskDelegate)) {
                    RNCameraView.this.barCodeScannerTaskLock = true;
                    new BarCodeScannerAsyncTask((BarCodeScannerAsyncTaskDelegate) cameraView2, RNCameraView.this.i, bArr, i, i2).execute(new Void[0]);
                }
                if (RNCameraView.this.k && !RNCameraView.this.faceDetectorTaskLock && (cameraView2 instanceof FaceDetectorAsyncTaskDelegate)) {
                    RNCameraView.this.faceDetectorTaskLock = true;
                    new FaceDetectorAsyncTask((FaceDetectorAsyncTaskDelegate) cameraView2, RNCameraView.this.j, bArr, i, i2, a2).execute(new Void[0]);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        View view = getView();
        if (view != null) {
            setBackgroundColor(-16777216);
            view.layout(0, 0, i4 - i2, i5 - i3);
        }
    }

    public void onViewAdded(View view) {
        if (getView() != view && getView() != null) {
            removeView(getView());
            addView(getView(), 0);
        }
    }

    public void setBarCodeTypes(List<String> list) {
        this.f = list;
        a();
    }

    public void takePicture(ReadableMap readableMap, Promise promise, File file) {
        this.b.add(promise);
        this.c.put(promise, readableMap);
        this.d.put(promise, file);
        super.takePicture();
    }

    public void record(ReadableMap readableMap, Promise promise, File file) {
        try {
            String a2 = RNFileUtils.a(file, ".mp4");
            int i2 = readableMap.hasKey("maxDuration") ? readableMap.getInt("maxDuration") : -1;
            int i3 = readableMap.hasKey("maxFileSize") ? readableMap.getInt("maxFileSize") : -1;
            CamcorderProfile camcorderProfile = CamcorderProfile.get(1);
            if (readableMap.hasKey(Constants.Name.QUALITY)) {
                camcorderProfile = RNCameraViewHelper.a(readableMap.getInt(Constants.Name.QUALITY));
            }
            if (super.record(a2, i2 * 1000, i3, !readableMap.hasKey("mute"), camcorderProfile)) {
                this.e = promise;
            } else {
                promise.reject("E_RECORDING_FAILED", "Starting video recording failed. Another recording might be in progress.");
            }
        } catch (IOException unused) {
            promise.reject("E_RECORDING_FAILED", "Starting video recording failed - could not create video file.");
        }
    }

    private void a() {
        EnumMap enumMap = new EnumMap(DecodeHintType.class);
        EnumSet<E> noneOf = EnumSet.noneOf(BarcodeFormat.class);
        if (this.f != null) {
            for (String next : this.f) {
                if (((String) CameraModule.VALID_BARCODE_TYPES.get(next)) != null) {
                    noneOf.add(BarcodeFormat.valueOf(next));
                }
            }
        }
        enumMap.put(DecodeHintType.POSSIBLE_FORMATS, noneOf);
        this.i.setHints(enumMap);
    }

    public void setShouldScanBarCodes(boolean z) {
        this.l = z;
        setScanning(this.k || this.l);
    }

    public void onBarCodeRead(Result result) {
        String barcodeFormat = result.getBarcodeFormat().toString();
        if (this.l && this.f.contains(barcodeFormat)) {
            RNCameraViewHelper.a((ViewGroup) this, result);
        }
    }

    public void onBarCodeScanningTaskCompleted() {
        this.barCodeScannerTaskLock = false;
        this.i.reset();
    }

    private void b() {
        this.j.c(this.m);
        this.j.b(this.n);
        this.j.a(this.o);
        this.j.a(true);
    }

    public void setFaceDetectionLandmarks(int i2) {
        this.n = i2;
        if (this.j != null) {
            this.j.b(i2);
        }
    }

    public void setFaceDetectionClassifications(int i2) {
        this.o = i2;
        if (this.j != null) {
            this.j.a(i2);
        }
    }

    public void setFaceDetectionMode(int i2) {
        this.m = i2;
        if (this.j != null) {
            this.j.c(i2);
        }
    }

    public void setShouldDetectFaces(boolean z) {
        this.k = z;
        setScanning(this.k || this.l);
    }

    public void onFacesDetected(SparseArray<Face> sparseArray, int i2, int i3, int i4) {
        if (this.k) {
            if (sparseArray == null) {
                sparseArray = new SparseArray<>();
            }
            RNCameraViewHelper.a(this, sparseArray, new ImageDimensions(i2, i3, i4, getFacing()));
        }
    }

    public void onFaceDetectionError(RNFaceDetector rNFaceDetector) {
        if (this.k) {
            RNCameraViewHelper.a((ViewGroup) this, rNFaceDetector);
        }
    }

    public void onFaceDetectingTaskCompleted() {
        this.faceDetectorTaskLock = false;
    }

    public void onHostResume() {
        if (!c()) {
            Arguments.createMap().putString("message", "Camera permissions not granted - component could not be rendered.");
            RNCameraViewHelper.a((ViewGroup) this);
        } else if ((this.g && !isCameraOpened()) || this.h) {
            this.g = false;
            this.h = false;
            if (!Build.FINGERPRINT.contains(MessengerShareContentUtility.TEMPLATE_GENERIC_TYPE)) {
                start();
            }
        }
    }

    public void onHostPause() {
        if (!this.g && isCameraOpened()) {
            this.g = true;
            stop();
        }
    }

    public void onHostDestroy() {
        this.j.b();
        stop();
    }

    private boolean c() {
        if (Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(getContext(), "android.permission.CAMERA") == 0) {
            return true;
        }
        return false;
    }
}
