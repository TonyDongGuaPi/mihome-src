package in.cashify.otex.widget;

import a.a.a.e.b.c;
import a.a.a.e.c.d;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.Camera;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import in.cashify.otex.ExchangeManager;
import in.cashify.otex.R;
import in.cashify.otex.widget.CameraPreview;
import in.cashify.otex.widget.CircleRoadProgress;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class DiagnoseCameraHeaderView extends DiagnoseHeaderView implements Camera.PictureCallback, CameraPreview.a, CircleRoadProgress.b {
    public CameraPreview b;
    public Handler c;
    public final Semaphore d = new Semaphore(1);
    public HandlerThread e;
    public Camera.CameraInfo f;
    public int g;
    public Camera.Size h;
    public Camera i;
    public d j;
    public a.a.a.b k;
    public c.C0002c l;

    public class a implements Runnable {
        public a() {
        }

        public void run() {
            try {
                if (DiagnoseCameraHeaderView.this.d.tryAcquire(2500, TimeUnit.MILLISECONDS)) {
                    Camera unused = DiagnoseCameraHeaderView.this.i = Camera.open(DiagnoseCameraHeaderView.this.d());
                    if (DiagnoseCameraHeaderView.this.i != null) {
                        Camera.Parameters parameters = DiagnoseCameraHeaderView.this.i.getParameters();
                        if (DiagnoseCameraHeaderView.this.e()) {
                            parameters.setFlashMode("auto");
                        }
                        if (DiagnoseCameraHeaderView.this.b != null) {
                            int a2 = DiagnoseCameraHeaderView.this.a(DiagnoseCameraHeaderView.this.f);
                            DiagnoseCameraHeaderView.this.i.setDisplayOrientation(a2);
                            parameters.setRotation(a2);
                        }
                        List<Camera.Size> supportedPictureSizes = parameters.getSupportedPictureSizes();
                        if (supportedPictureSizes != null && supportedPictureSizes.size() > 0) {
                            Camera.Size unused2 = DiagnoseCameraHeaderView.this.h = supportedPictureSizes.get(0);
                            int i = DiagnoseCameraHeaderView.this.h.width;
                            for (Camera.Size next : supportedPictureSizes) {
                                if (next.width < i) {
                                    i = DiagnoseCameraHeaderView.this.h.width;
                                    Camera.Size unused3 = DiagnoseCameraHeaderView.this.h = next;
                                }
                            }
                            parameters.setPictureSize(DiagnoseCameraHeaderView.this.h.width, DiagnoseCameraHeaderView.this.h.height);
                            DiagnoseCameraHeaderView.this.i.setParameters(parameters);
                            DiagnoseCameraHeaderView.this.b.setPreviewSize(DiagnoseCameraHeaderView.this.h);
                        }
                        DiagnoseCameraHeaderView.this.b.a(DiagnoseCameraHeaderView.this.i);
                        DiagnoseCameraHeaderView.this.a(DiagnoseCameraHeaderView.this.i, 1000);
                    }
                    DiagnoseCameraHeaderView.this.d.release();
                    return;
                }
                throw new RuntimeException("Time out waiting to lock camera opening.");
            } catch (Exception unused4) {
            } catch (Throwable th) {
                DiagnoseCameraHeaderView.this.d.release();
                throw th;
            }
        }
    }

    public class b implements Runnable {

        /* renamed from: a  reason: collision with root package name */
        public final /* synthetic */ Camera f2615a;

        public b(Camera camera) {
            this.f2615a = camera;
        }

        public void run() {
            try {
                this.f2615a.takePicture((Camera.ShutterCallback) null, (Camera.PictureCallback) null, DiagnoseCameraHeaderView.this);
            } catch (Exception unused) {
            }
        }
    }

    public DiagnoseCameraHeaderView(Context context) {
        super(context);
    }

    public DiagnoseCameraHeaderView(Context context, int i2) {
        super(context, i2);
    }

    public static int a(byte[] bArr, double d2) {
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, bArr.length, new BitmapFactory.Options());
        int width = decodeByteArray.getWidth();
        int height = decodeByteArray.getHeight();
        Random random = new Random();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < height) {
            int i5 = i4;
            int i6 = i3;
            for (int i7 = 0; i7 < width; i7 += 5 + random.nextInt(10)) {
                int pixel = decodeByteArray.getPixel(i7, i2);
                int i8 = (pixel >> 16) & 255;
                int i9 = (pixel >> 8) & 255;
                int i10 = pixel & 255;
                if (Math.sqrt((double) ((i8 * i8) + (i10 * i10) + (i9 * i9))) > d2) {
                    i5++;
                }
                i6++;
            }
            i2 += 5 + random.nextInt(10);
            i3 = i6;
            i4 = i5;
        }
        float f2 = 0.0f;
        if (i3 > 0) {
            f2 = ((float) i4) / ((float) i3);
        }
        return (int) (f2 * 100.0f);
    }

    private int getCameraFacing() {
        return this.j.f().equals(ExchangeManager.h.FRONT_CAMERA.a()) ? 1 : 0;
    }

    public int a(Camera.CameraInfo cameraInfo) {
        int i2 = 0;
        switch (this.g) {
            case 1:
                i2 = 90;
                break;
            case 2:
                i2 = 180;
                break;
            case 3:
                i2 = 270;
                break;
        }
        return (cameraInfo.facing == 1 ? 360 - ((cameraInfo.orientation + i2) % 360) : (cameraInfo.orientation - i2) + 360) % 360;
    }

    public void a() {
    }

    public void a(Context context, int i2) {
        super.a(context, i2);
        this.b = (CameraPreview) this.f2616a.findViewById(R.id.cameraPreview);
    }

    public void a(Camera camera, long j2) {
        if (camera != null && this.e.isAlive()) {
            this.c.postDelayed(new b(camera), j2);
        }
    }

    public void b() {
        if (this.i == null) {
            f();
        }
    }

    public void c() {
        try {
            this.d.acquire();
            this.b.c();
            this.i.release();
            this.i = null;
        } catch (Throwable unused) {
        }
        this.d.release();
    }

    public final int d() {
        int numberOfCameras = Camera.getNumberOfCameras();
        for (int i2 = 0; i2 < numberOfCameras; i2++) {
            Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
            Camera.getCameraInfo(i2, cameraInfo);
            if (cameraInfo.facing == getCameraFacing()) {
                this.f = cameraInfo;
                return i2;
            }
        }
        return -1;
    }

    public final boolean e() {
        List<String> supportedFlashModes = this.i.getParameters().getSupportedFlashModes();
        if (supportedFlashModes == null) {
            return false;
        }
        for (String equals : supportedFlashModes) {
            if ("on".equals(equals)) {
                return true;
            }
        }
        return false;
    }

    public void f() {
        Handler handler = this.c;
        if (handler != null) {
            handler.post(new a());
        }
    }

    public void g() {
        this.e = new HandlerThread("CameraBackground");
        this.e.start();
        this.c = new Handler(this.e.getLooper());
    }

    public String getRequestKey() {
        return this.j.f().equals(ExchangeManager.h.FRONT_CAMERA.a()) ? "cf" : "cb";
    }

    public void h() {
        if (Build.VERSION.SDK_INT >= 18) {
            HandlerThread handlerThread = this.e;
            if (handlerThread != null) {
                handlerThread.quitSafely();
            }
        } else {
            this.e.quit();
        }
        try {
            this.e.join();
            this.e = null;
            this.c = null;
        } catch (Throwable unused) {
        }
    }

    public void onPictureTaken(byte[] bArr, Camera camera) {
        int a2 = a(bArr, this.j.b());
        if (a2 > this.j.c()) {
            this.k = new a.a.a.b(getRequestKey(), Integer.valueOf(a2), true);
            c.C0002c cVar = this.l;
            if (cVar != null) {
                cVar.a(this.k);
                return;
            }
            return;
        }
        try {
            camera.startPreview();
            a(camera, 0);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void setPictureTakenCallBack(c.C0002c cVar) {
        this.l = cVar;
    }

    public void setmCameraContext(d dVar) {
        this.j = dVar;
    }
}
