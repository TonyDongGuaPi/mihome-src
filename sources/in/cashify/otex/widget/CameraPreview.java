package in.cashify.otex.widget;

import android.content.Context;
import android.hardware.Camera;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class CameraPreview extends SurfaceView implements SurfaceHolder.Callback {
    public static final String e = "CameraPreview";

    /* renamed from: a  reason: collision with root package name */
    public Camera f2611a;
    public Camera.Size b;
    public a c;
    public boolean d;

    public interface a {
        void b();
    }

    public CameraPreview(Context context) {
        super(context);
        a();
    }

    public CameraPreview(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public CameraPreview(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public final void a() {
        SurfaceHolder holder = getHolder();
        holder.addCallback(this);
        holder.setType(3);
    }

    public void a(Camera camera) {
        if (this.f2611a != camera) {
            c();
            this.f2611a = camera;
            Camera camera2 = this.f2611a;
            if (camera2 != null) {
                camera2.setPreviewDisplay(getHolder());
                this.f2611a.startPreview();
            }
        }
    }

    public boolean b() {
        return this.d;
    }

    public void c() {
        Camera camera = this.f2611a;
        if (camera != null) {
            try {
                camera.stopPreview();
                this.f2611a.release();
                this.f2611a = null;
            } catch (Throwable unused) {
            }
        }
    }

    public void setPreviewSize(Camera.Size size) {
        this.b = size;
    }

    public void setPreviewStartListener(a aVar) {
        this.c = aVar;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:8:0x000f */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void surfaceChanged(android.view.SurfaceHolder r1, int r2, int r3, int r4) {
        /*
            r0 = this;
            android.view.Surface r2 = r1.getSurface()
            if (r2 != 0) goto L_0x0007
            return
        L_0x0007:
            android.hardware.Camera r2 = r0.f2611a
            if (r2 != 0) goto L_0x000c
            return
        L_0x000c:
            r2.stopPreview()     // Catch:{ Exception -> 0x000f }
        L_0x000f:
            android.hardware.Camera r2 = r0.f2611a     // Catch:{ Exception -> 0x0038 }
            r2.reconnect()     // Catch:{ Exception -> 0x0038 }
            android.hardware.Camera r2 = r0.f2611a     // Catch:{ Exception -> 0x0038 }
            android.hardware.Camera$Parameters r2 = r2.getParameters()     // Catch:{ Exception -> 0x0038 }
            android.hardware.Camera$Size r3 = r0.b     // Catch:{ Exception -> 0x0038 }
            int r3 = r3.width     // Catch:{ Exception -> 0x0038 }
            android.hardware.Camera$Size r4 = r0.b     // Catch:{ Exception -> 0x0038 }
            int r4 = r4.height     // Catch:{ Exception -> 0x0038 }
            r2.setPreviewSize(r3, r4)     // Catch:{ Exception -> 0x0038 }
            r0.requestLayout()     // Catch:{ Exception -> 0x0038 }
            android.hardware.Camera r3 = r0.f2611a     // Catch:{ Exception -> 0x0038 }
            r3.setParameters(r2)     // Catch:{ Exception -> 0x0038 }
            android.hardware.Camera r2 = r0.f2611a     // Catch:{ Exception -> 0x0038 }
            r2.setPreviewDisplay(r1)     // Catch:{ Exception -> 0x0038 }
            android.hardware.Camera r1 = r0.f2611a     // Catch:{ Exception -> 0x0038 }
            r1.startPreview()     // Catch:{ Exception -> 0x0038 }
            goto L_0x0053
        L_0x0038:
            r1 = move-exception
            java.lang.String r2 = e
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Error starting camera preview: "
            r3.append(r4)
            java.lang.String r1 = r1.getMessage()
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            android.util.Log.d(r2, r1)
        L_0x0053:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: in.cashify.otex.widget.CameraPreview.surfaceChanged(android.view.SurfaceHolder, int, int, int):void");
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        this.d = true;
        a aVar = this.c;
        if (aVar != null) {
            aVar.b();
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        try {
            this.d = false;
            this.f2611a.stopPreview();
        } catch (RuntimeException unused) {
        }
    }
}
