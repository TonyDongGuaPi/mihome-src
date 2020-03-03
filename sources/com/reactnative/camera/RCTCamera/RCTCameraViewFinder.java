package com.reactnative.camera.RCTCamera;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.Drawable;
import android.hardware.Camera;
import android.os.AsyncTask;
import android.util.Log;
import android.view.MotionEvent;
import android.view.TextureView;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.HybridBinarizer;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.List;

class RCTCameraViewFinder extends TextureView implements Camera.PreviewCallback, TextureView.SurfaceTextureListener {

    /* renamed from: a  reason: collision with root package name */
    public static volatile boolean f8661a = false;
    /* access modifiers changed from: private */
    public int b;
    private int c;
    private SurfaceTexture d;
    private int e;
    private int f;
    private boolean g;
    private boolean h;
    private Camera i;
    private boolean j = false;
    private float k;
    /* access modifiers changed from: private */
    public final MultiFormatReader l = new MultiFormatReader();

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }

    public RCTCameraViewFinder(Context context, int i2) {
        super(context);
        setSurfaceTextureListener(this);
        this.b = i2;
        a(RCTCamera.a().d());
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i2, int i3) {
        this.d = surfaceTexture;
        this.e = i2;
        this.f = i3;
        e();
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i2, int i3) {
        this.e = i2;
        this.f = i3;
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        this.d = null;
        this.e = 0;
        this.f = 0;
        f();
        return true;
    }

    public int a() {
        return this.b;
    }

    public double b() {
        return (double) (((float) RCTCamera.a().d(this.b)) / ((float) RCTCamera.a().e(this.b)));
    }

    public void a(final int i2) {
        if (this.b != i2) {
            new Thread(new Runnable() {
                public void run() {
                    RCTCameraViewFinder.this.d();
                    int unused = RCTCameraViewFinder.this.b = i2;
                    RCTCameraViewFinder.this.c();
                }
            }).start();
        }
    }

    public void b(int i2) {
        RCTCamera.a().a(this.b, i2);
        this.c = i2;
    }

    public void a(String str) {
        RCTCamera.a().a(this.b, str);
    }

    public void c(int i2) {
        RCTCamera.a().b(this.b, i2);
    }

    public void d(int i2) {
        RCTCamera.a().c(this.b, i2);
    }

    public void a(boolean z) {
        this.j = z;
    }

    public void e(int i2) {
        RCTCamera.a().d(this.b, i2);
    }

    public void c() {
        if (this.d != null) {
            e();
        }
    }

    public void d() {
        if (this.i != null) {
            f();
        }
    }

    private synchronized void e() {
        List<Camera.Size> list;
        Activity g2;
        if (!this.g) {
            boolean z = true;
            this.g = true;
            try {
                this.i = RCTCamera.a().b(this.b);
                Camera.Parameters parameters = this.i.getParameters();
                boolean z2 = this.c == 0;
                if (this.c != 1) {
                    z = false;
                }
                if (!z2) {
                    if (!z) {
                        throw new RuntimeException("Unsupported capture mode:" + this.c);
                    }
                }
                List<String> supportedFocusModes = parameters.getSupportedFocusModes();
                if (z2 && supportedFocusModes.contains("continuous-picture")) {
                    parameters.setFocusMode("continuous-picture");
                } else if (z && supportedFocusModes.contains("continuous-video")) {
                    parameters.setFocusMode("continuous-video");
                } else if (supportedFocusModes.contains("auto")) {
                    parameters.setFocusMode("auto");
                }
                if (z2) {
                    list = parameters.getSupportedPictureSizes();
                } else if (z) {
                    list = RCTCamera.a().a(this.i);
                } else {
                    throw new RuntimeException("Unsupported capture mode:" + this.c);
                }
                Camera.Size a2 = RCTCamera.a().a(list, Integer.MAX_VALUE, Integer.MAX_VALUE);
                parameters.setPictureSize(a2.width, a2.height);
                this.i.setParameters(parameters);
                this.i.setPreviewTexture(this.d);
                this.i.startPreview();
                if (this.j && (g2 = g()) != null) {
                    g2.getWindow().setBackgroundDrawable((Drawable) null);
                }
                this.i.setPreviewCallback(this);
            } catch (NullPointerException e2) {
                e2.printStackTrace();
            } catch (Exception e3) {
                try {
                    e3.printStackTrace();
                    f();
                } catch (Throwable th) {
                    this.g = false;
                    throw th;
                }
            }
            this.g = false;
        }
    }

    private synchronized void f() {
        if (!this.h) {
            this.h = true;
            try {
                if (this.i != null) {
                    this.i.stopPreview();
                    this.i.setPreviewCallback((Camera.PreviewCallback) null);
                    RCTCamera.a().c(this.b);
                    this.i = null;
                }
            } catch (Exception e2) {
                try {
                    e2.printStackTrace();
                } catch (Throwable th) {
                    this.h = false;
                    throw th;
                }
            }
            this.h = false;
        }
    }

    private Activity g() {
        for (Context context = getContext(); context instanceof ContextWrapper; context = ((ContextWrapper) context).getBaseContext()) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
        }
        return null;
    }

    private BarcodeFormat b(String str) {
        if ("aztec".equals(str)) {
            return BarcodeFormat.AZTEC;
        }
        if ("ean13".equals(str)) {
            return BarcodeFormat.EAN_13;
        }
        if ("ean8".equals(str)) {
            return BarcodeFormat.EAN_8;
        }
        if ("qr".equals(str)) {
            return BarcodeFormat.QR_CODE;
        }
        if ("pdf417".equals(str)) {
            return BarcodeFormat.PDF_417;
        }
        if ("upce".equals(str)) {
            return BarcodeFormat.UPC_E;
        }
        if ("datamatrix".equals(str)) {
            return BarcodeFormat.DATA_MATRIX;
        }
        if ("code39".equals(str)) {
            return BarcodeFormat.CODE_39;
        }
        if ("code93".equals(str)) {
            return BarcodeFormat.CODE_93;
        }
        if ("interleaved2of5".equals(str)) {
            return BarcodeFormat.ITF;
        }
        if ("codabar".equals(str)) {
            return BarcodeFormat.CODABAR;
        }
        if ("code128".equals(str)) {
            return BarcodeFormat.CODE_128;
        }
        if ("maxicode".equals(str)) {
            return BarcodeFormat.MAXICODE;
        }
        if ("rss14".equals(str)) {
            return BarcodeFormat.RSS_14;
        }
        if ("rssexpanded".equals(str)) {
            return BarcodeFormat.RSS_EXPANDED;
        }
        if ("upca".equals(str)) {
            return BarcodeFormat.UPC_A;
        }
        if ("upceanextension".equals(str)) {
            return BarcodeFormat.UPC_EAN_EXTENSION;
        }
        Log.v("RCTCamera", "Unsupported code.. [" + str + Operators.ARRAY_END_STR);
        return null;
    }

    private void a(List<String> list) {
        EnumMap enumMap = new EnumMap(DecodeHintType.class);
        EnumSet<E> noneOf = EnumSet.noneOf(BarcodeFormat.class);
        if (list != null) {
            for (String b2 : list) {
                BarcodeFormat b3 = b(b2);
                if (b3 != null) {
                    noneOf.add(b3);
                }
            }
        }
        enumMap.put(DecodeHintType.POSSIBLE_FORMATS, noneOf);
        this.l.setHints(enumMap);
    }

    public void onPreviewFrame(byte[] bArr, Camera camera) {
        if (RCTCamera.a().c() && !f8661a) {
            f8661a = true;
            new ReaderAsyncTask(camera, bArr).execute(new Void[0]);
        }
    }

    private class ReaderAsyncTask extends AsyncTask<Void, Void, Void> {
        private byte[] b;
        private final Camera c;

        ReaderAsyncTask(Camera camera, byte[] bArr) {
            this.c = camera;
            this.b = bArr;
        }

        /* JADX INFO: finally extract failed */
        private Result a(int i, int i2) {
            try {
                Result decodeWithState = RCTCameraViewFinder.this.l.decodeWithState(new BinaryBitmap(new HybridBinarizer(new PlanarYUVLuminanceSource(this.b, i, i2, 0, 0, i, i2, false))));
                RCTCameraViewFinder.this.l.reset();
                return decodeWithState;
            } catch (Throwable th) {
                RCTCameraViewFinder.this.l.reset();
                throw th;
            }
        }

        private Result a() {
            Camera.Size previewSize = this.c.getParameters().getPreviewSize();
            int i = previewSize.width;
            int i2 = previewSize.height;
            Result a2 = a(i, i2);
            if (a2 != null) {
                return a2;
            }
            b(i, i2);
            return a(previewSize.height, previewSize.width);
        }

        private void b(int i, int i2) {
            byte[] bArr = new byte[this.b.length];
            for (int i3 = 0; i3 < i2; i3++) {
                for (int i4 = 0; i4 < i; i4++) {
                    bArr[(((i4 * i2) + i2) - i3) - 1] = this.b[(i3 * i) + i4];
                }
            }
            this.b = bArr;
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public Void doInBackground(Void... voidArr) {
            if (isCancelled()) {
                return null;
            }
            try {
                Result a2 = a();
                if (a2 != null) {
                    ReactApplicationContext reactContextSingleton = RCTCameraModule.getReactContextSingleton();
                    WritableMap createMap = Arguments.createMap();
                    WritableArray createArray = Arguments.createArray();
                    ResultPoint[] resultPoints = a2.getResultPoints();
                    if (resultPoints != null) {
                        for (ResultPoint resultPoint : resultPoints) {
                            WritableMap createMap2 = Arguments.createMap();
                            createMap2.putString("x", String.valueOf(resultPoint.getX()));
                            createMap2.putString(Constants.Name.Y, String.valueOf(resultPoint.getY()));
                            createArray.pushMap(createMap2);
                        }
                    }
                    createMap.putArray("bounds", createArray);
                    createMap.putString("data", a2.getText());
                    createMap.putString("type", a2.getBarcodeFormat().toString());
                    ((DeviceEventManagerModule.RCTDeviceEventEmitter) reactContextSingleton.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)).emit("CameraBarCodeReadAndroid", createMap);
                    RCTCameraViewFinder.this.l.reset();
                    RCTCameraViewFinder.f8661a = false;
                    return null;
                }
                throw new Exception();
            } catch (Throwable unused) {
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.i == null) {
            return false;
        }
        Camera.Parameters parameters = this.i.getParameters();
        int action = motionEvent.getAction();
        if (motionEvent.getPointerCount() > 1) {
            if (action == 5) {
                this.k = a(motionEvent);
            } else if (action == 2 && parameters.isZoomSupported()) {
                this.i.cancelAutoFocus();
                b(motionEvent, parameters);
            }
        } else if (action == 1) {
            a(motionEvent, parameters);
        }
        return true;
    }

    private void b(MotionEvent motionEvent, Camera.Parameters parameters) {
        int maxZoom = parameters.getMaxZoom();
        int zoom = parameters.getZoom();
        float a2 = a(motionEvent);
        if (a2 > this.k) {
            if (zoom < maxZoom) {
                zoom++;
            }
        } else if (a2 < this.k && zoom > 0) {
            zoom--;
        }
        this.k = a2;
        parameters.setZoom(zoom);
        this.i.setParameters(parameters);
    }

    public void a(MotionEvent motionEvent, Camera.Parameters parameters) {
        List<String> supportedFocusModes = parameters.getSupportedFocusModes();
        if (supportedFocusModes != null && supportedFocusModes.contains("auto") && parameters.getMaxNumFocusAreas() != 0) {
            this.i.cancelAutoFocus();
            try {
                Camera.Area a2 = RCTCameraUtils.a(motionEvent, this.e, this.f);
                parameters.setFocusMode("auto");
                ArrayList arrayList = new ArrayList();
                arrayList.add(a2);
                parameters.setFocusAreas(arrayList);
                if (parameters.getMaxNumMeteringAreas() > 0) {
                    parameters.setMeteringAreas(arrayList);
                }
                this.i.setParameters(parameters);
                try {
                    this.i.autoFocus(new Camera.AutoFocusCallback() {
                        public void onAutoFocus(boolean z, Camera camera) {
                            if (z) {
                                camera.cancelAutoFocus();
                            }
                        }
                    });
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            } catch (RuntimeException e3) {
                e3.printStackTrace();
            }
        }
    }

    private float a(MotionEvent motionEvent) {
        float x = motionEvent.getX(0) - motionEvent.getX(1);
        float y = motionEvent.getY(0) - motionEvent.getY(1);
        return (float) Math.sqrt((double) ((x * x) + (y * y)));
    }
}
