package com.xiaomi.qrcode;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.xiaomi.qrcode.camera.CameraManager;
import com.xiaomi.smarthome.OpenExternalBrowserCompat;
import com.xiaomi.smarthome.R;
import java.util.Collection;
import java.util.Map;

public final class CaptureActivityHandler extends Handler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12954a = "CaptureActivityHandler";
    private final ScanBarcodeActivity b;
    private final DecodeThread c;
    private State d = State.SUCCESS;
    private final CameraManager e;

    private enum State {
        PREVIEW,
        SUCCESS,
        DONE
    }

    CaptureActivityHandler(ScanBarcodeActivity scanBarcodeActivity, Collection<BarcodeFormat> collection, Map<DecodeHintType, ?> map, String str, CameraManager cameraManager) {
        this.b = scanBarcodeActivity;
        this.c = new DecodeThread(scanBarcodeActivity, collection, map, str, new ViewfinderResultPointCallback(scanBarcodeActivity.getViewfinderView()));
        this.c.start();
        this.e = cameraManager;
        cameraManager.c();
        b();
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case R.id.decode_failed /*2131428689*/:
                this.d = State.PREVIEW;
                this.e.a(this.c.a(), (int) R.id.decode);
                return;
            case R.id.decode_succeeded /*2131428690*/:
                this.d = State.SUCCESS;
                this.b.handleDecode((Result) message.obj, (Bitmap) null, 1.0f);
                return;
            case R.id.launch_product_query /*2131430317*/:
                OpenExternalBrowserCompat.a(this.b, (String) message.obj);
                return;
            case R.id.restart_preview /*2131431872*/:
                b();
                return;
            case R.id.return_scan_result /*2131431884*/:
                this.b.setResult(-1, (Intent) message.obj);
                this.b.finish();
                return;
            default:
                return;
        }
    }

    public void a() {
        this.d = State.DONE;
        this.e.d();
        Message.obtain(this.c.a(), R.id.quit).sendToTarget();
        try {
            this.c.join(500);
        } catch (InterruptedException unused) {
        }
        removeMessages(R.id.decode_succeeded);
        removeMessages(R.id.decode_failed);
    }

    private void b() {
        if (this.d == State.SUCCESS) {
            this.d = State.PREVIEW;
            this.e.a(this.c.a(), (int) R.id.decode);
            this.b.drawViewfinder();
        }
    }
}
