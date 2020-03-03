package com.xiaomi.qrcode2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.xiaomi.qrcode2.camera.CameraManager;
import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.Result;
import java.util.Collection;
import java.util.Map;

public final class CaptureActivityHandler extends Handler {

    /* renamed from: a  reason: collision with root package name */
    private static final String f12996a = "CaptureActivityHandler";
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
        String str = null;
        switch (message.what) {
            case 6:
                this.d = State.PREVIEW;
                this.e.a(this.c.a(), 5);
                return;
            case 7:
                this.d = State.SUCCESS;
                this.b.handleDecode((Result) message.obj, (Bitmap) null, 1.0f);
                return;
            case 8:
                String str2 = (String) message.obj;
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.addFlags(524288);
                intent.setData(Uri.parse(str2));
                ResolveInfo resolveActivity = this.b.getPackageManager().resolveActivity(intent, 65536);
                if (!(resolveActivity == null || resolveActivity.activityInfo == null)) {
                    str = resolveActivity.activityInfo.packageName;
                    String str3 = f12996a;
                    Log.d(str3, "Using browser in package " + str);
                }
                if ("com.android.browser".equals(str) || "com.android.chrome".equals(str)) {
                    intent.setPackage(str);
                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    intent.putExtra("com.android.browser.application_id", str);
                }
                try {
                    this.b.startActivity(intent);
                    return;
                } catch (ActivityNotFoundException unused) {
                    String str4 = f12996a;
                    Log.w(str4, "Can't find anything to handle VIEW of URI " + str2);
                    return;
                }
            case 10:
                b();
                return;
            case 11:
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
        Message.obtain(this.c.a(), 9).sendToTarget();
        try {
            this.c.join(500);
        } catch (InterruptedException unused) {
        }
        removeMessages(7);
        removeMessages(6);
    }

    private void b() {
        if (this.d == State.SUCCESS) {
            this.d = State.PREVIEW;
            this.e.a(this.c.a(), 5);
            this.b.drawViewfinder();
        }
    }
}
