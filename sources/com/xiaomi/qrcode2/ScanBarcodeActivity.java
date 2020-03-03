package com.xiaomi.qrcode2;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaomi.qrcode2.camera.CameraManager;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.yp_permission.PermissionCallback;
import com.xiaomi.youpin.yp_permission.YouPinPermissionManager;
import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.DecodeHintType;
import com.xiaomi.zxing.R;
import com.xiaomi.zxing.Result;
import com.xiaomi.zxing.ResultPoint;
import com.xiaomiyoupin.toast.YPDToast;
import com.xiaomiyoupin.toast.dialog.MLAlertDialog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class ScanBarcodeActivity extends Activity {
    public static final String SCAN_RESULT = "scan_result";
    public static final String ScanBarcodeResultBroadCast = "com.xiaomi.ScanBarcodeResultBroadCast";
    public static final String TITLE = "title";

    /* renamed from: a  reason: collision with root package name */
    private static final int f13018a = 100;
    /* access modifiers changed from: private */
    public static final String b = "ScanBarcodeActivity";
    private Context c;
    private CameraManager d;
    private CaptureActivityHandler e;
    private Result f;
    private ViewfinderView g;
    private ViewGroup h;
    private SurfaceView i;
    private Result j;
    /* access modifiers changed from: private */
    public boolean k;
    private InactivityTimer l;
    private BeepManager m;
    private AmbientLightManager n;
    /* access modifiers changed from: private */
    public boolean o = false;
    private boolean p = false;
    /* access modifiers changed from: private */
    public boolean q = false;
    private SurfaceHolder.Callback r;
    private boolean s = false;

    /* access modifiers changed from: package-private */
    public ViewfinderView getViewfinderView() {
        return this.g;
    }

    public Handler getHandler() {
        return this.e;
    }

    /* access modifiers changed from: package-private */
    public CameraManager getCameraManager() {
        return this.d;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT < 24 || !isInMultiWindowMode()) {
            this.c = this;
            getWindow().addFlags(128);
            setContentView(R.layout.zxing_scan_barcode_activity);
            Utils.a((Activity) this, findViewById(R.id.zxing_title_bar));
            findViewById(R.id.zxing_back).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ScanBarcodeActivity.this.onBackPressed();
                }
            });
            TextView textView = (TextView) findViewById(R.id.zxing_title);
            String stringExtra = getIntent().getStringExtra("title");
            if (TextUtils.isEmpty(stringExtra)) {
                textView.setText(R.string.scan_title);
            } else {
                textView.setText(stringExtra);
            }
            findViewById(R.id.scan_file).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Utils.a((Activity) ScanBarcodeActivity.this, 100);
                }
            });
            this.g = (ViewfinderView) findViewById(R.id.viewfinder_view);
            this.h = (ViewGroup) findViewById(R.id.preview_view_group);
            this.k = false;
            this.l = new InactivityTimer(this);
            this.m = new BeepManager(this);
            this.n = new AmbientLightManager(this);
            this.r = new SurfaceHolder.Callback() {
                public void surfaceCreated(SurfaceHolder surfaceHolder) {
                    LogUtils.e(ScanBarcodeActivity.b, "surfaceCreated");
                    if (surfaceHolder == null) {
                        LogUtils.e(ScanBarcodeActivity.b, "*** WARNING *** surfaceCreated() gave us a null surface!");
                    }
                    if (!ScanBarcodeActivity.this.k) {
                        boolean unused = ScanBarcodeActivity.this.k = true;
                        ScanBarcodeActivity.this.a(surfaceHolder);
                    }
                }

                public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
                    LogUtils.e(ScanBarcodeActivity.b, "surfaceChanged");
                }

                public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                    LogUtils.e(ScanBarcodeActivity.b, "surfaceDestroyed");
                    boolean unused = ScanBarcodeActivity.this.k = false;
                }
            };
            if (YouPinPermissionManager.a((Context) this, "android.permission.CAMERA")) {
                this.o = true;
                return;
            }
            TextView textView2 = new TextView(this);
            textView2.setPadding(30, 30, 30, 30);
            textView2.setText(getResources().getString(R.string.camera_permission_explain));
            new MLAlertDialog.Builder(this).setNegativeButton((CharSequence) "取消", (DialogInterface.OnClickListener) $$Lambda$ScanBarcodeActivity$6K0t6xGX1uYBpGmIniuPhsrMoVc.INSTANCE).setPositiveButton((CharSequence) "允许", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialogInterface, int i) {
                    ScanBarcodeActivity.this.a(dialogInterface, i);
                }
            }).setView(textView2).create().show();
            return;
        }
        YPDToast.getInstance().toast((Context) this, "该页面不支持分屏");
        finish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(DialogInterface dialogInterface, int i2) {
        dialogInterface.dismiss();
        a();
    }

    private void a() {
        YouPinPermissionManager.a((Activity) this, "android.permission.CAMERA", (PermissionCallback) new PermissionCallback() {
            public void a() {
                boolean unused = ScanBarcodeActivity.this.o = true;
                if (ScanBarcodeActivity.this.q) {
                    ScanBarcodeActivity.this.b();
                }
            }

            public void a(boolean z) {
                boolean unused = ScanBarcodeActivity.this.o = false;
                if (!z) {
                    YPDToast.getInstance().toast(ScanBarcodeActivity.this.getBaseContext(), "未授予相机权限，扫一扫暂不可用");
                    ScanBarcodeActivity.this.cancelScan();
                }
            }

            public void b() {
                boolean unused = ScanBarcodeActivity.this.o = false;
                YPDToast.getInstance().toast(ScanBarcodeActivity.this.getBaseContext(), "未授予相机权限，扫一扫暂不可用");
                ScanBarcodeActivity.this.cancelScan();
            }
        });
    }

    public void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        super.onMultiWindowModeChanged(z, configuration);
        if (Build.VERSION.SDK_INT >= 24 && z) {
            YPDToast.getInstance().toast((Context) this, "该页面不支持分屏");
            finish();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        String str = b;
        LogUtils.d(str, "onResume mHasCameraPerms " + this.o);
        super.onResume();
        this.q = true;
        if (this.o) {
            b();
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        if (!this.s) {
            this.s = true;
            if (this.i == null) {
                this.i = new SurfaceView(this);
                this.h.addView(this.i);
            }
            this.d = new CameraManager(getApplication());
            this.g.setCameraManager(this.d);
            this.g.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    DecodeHandler.f13001a = true;
                }
            });
            this.e = null;
            this.j = null;
            d();
            this.m.a();
            this.n.a(this.d);
            this.l.c();
            SurfaceHolder holder = this.i.getHolder();
            if (this.k) {
                LogUtils.d(b, "hasSurface initCamera");
                a(holder);
            } else {
                LogUtils.d(b, "no hasSurface addCallback");
                holder.addCallback(this.r);
            }
            this.p = true;
            this.s = false;
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        this.q = false;
        String str = b;
        LogUtils.d(str, "onPause mHasInitCamera " + this.p);
        if (this.p) {
            if (this.e != null) {
                this.e.a();
                this.e = null;
            }
            this.l.b();
            this.n.a();
            this.m.close();
            if (this.d != null) {
                this.d.b();
            }
            if (!this.k) {
                this.i.getHolder().removeCallback(this.r);
            }
            this.p = false;
        }
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.l != null) {
            this.l.d();
        }
        super.onDestroy();
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            cancelScan();
            return true;
        } else if (i2 == 27 || i2 == 80) {
            return true;
        } else {
            switch (i2) {
                case 24:
                    this.d.a(true);
                    return true;
                case 25:
                    this.d.a(false);
                    return true;
                default:
                    return super.onKeyDown(i2, keyEvent);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        if (i2 == 100 && i3 == -1 && intent != null) {
            try {
                Uri data = intent.getData();
                if (data != null) {
                    a(data.toString());
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    private void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            new AsyncTask<String, Integer, Result>() {
                /* access modifiers changed from: protected */
                /* renamed from: a */
                public Result doInBackground(String... strArr) {
                    String str = strArr[0];
                    if (TextUtils.isEmpty(str)) {
                        return null;
                    }
                    String a2 = Utils.a(ScanBarcodeActivity.this.getApplicationContext(), Uri.parse(str));
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    int i = 1;
                    options.inJustDecodeBounds = true;
                    BitmapFactory.decodeFile(a2, options);
                    int i2 = (int) (((float) options.outHeight) / 800.0f);
                    if (i2 > 0) {
                        i = i2;
                    }
                    options.inJustDecodeBounds = false;
                    options.inSampleSize = i;
                    Bitmap decodeFile = BitmapFactory.decodeFile(a2, options);
                    if (decodeFile == null) {
                        return null;
                    }
                    return Utils.a(decodeFile);
                }

                /* access modifiers changed from: protected */
                /* renamed from: a */
                public void onPostExecute(Result result) {
                    if (result != null) {
                        ScanBarcodeActivity.this.scanFinish(result.a());
                    } else {
                        YPDToast.getInstance().toast((Context) ScanBarcodeActivity.this, "无法识别二维码");
                    }
                }
            }.execute(new String[]{str});
        }
    }

    private void a(Bitmap bitmap, Result result) {
        if (this.e == null) {
            this.f = result;
            return;
        }
        if (result != null) {
            this.f = result;
        }
        if (this.f != null) {
            this.e.sendMessage(Message.obtain(this.e, 7, this.f));
        }
        this.f = null;
    }

    public void onBackPressed() {
        super.onBackPressed();
        cancelScan();
    }

    /* access modifiers changed from: package-private */
    public void scanFinish(String str) {
        Intent intent = new Intent();
        if (TextUtils.isEmpty(str)) {
            setResult(0);
        } else {
            intent.putExtra("scan_result", str);
            setResult(-1, intent);
        }
        intent.setAction(ScanBarcodeResultBroadCast);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
        finish();
    }

    /* access modifiers changed from: package-private */
    public void cancelScan() {
        Intent intent = new Intent();
        setResult(0);
        intent.setAction(ScanBarcodeResultBroadCast);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
        finish();
    }

    public void handleDecode(Result result, Bitmap bitmap, float f2) {
        this.l.a();
        this.j = result;
        if (bitmap != null) {
            this.m.b();
            a(bitmap, f2, result);
        }
        scanFinish(result.a());
    }

    private void a(Bitmap bitmap, float f2, Result result) {
        ResultPoint[] c2 = result.c();
        if (c2 != null && c2.length > 0) {
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            paint.setColor(getResources().getColor(R.color.result_points));
            if (c2.length == 2) {
                paint.setStrokeWidth(4.0f);
                Utils.a(canvas, paint, c2[0], c2[1], f2);
            } else if (c2.length == 4 && (result.d() == BarcodeFormat.UPC_A || result.d() == BarcodeFormat.EAN_13)) {
                Utils.a(canvas, paint, c2[0], c2[1], f2);
                Utils.a(canvas, paint, c2[2], c2[3], f2);
            } else {
                paint.setStrokeWidth(10.0f);
                for (ResultPoint resultPoint : c2) {
                    if (resultPoint != null) {
                        canvas.drawPoint(resultPoint.a() * f2, resultPoint.b() * f2, paint);
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            throw new IllegalStateException("No SurfaceHolder provided");
        } else if (this.d.a()) {
            LogUtils.d(b, "initCamera() while already open -- late SurfaceView callback?");
        } else {
            try {
                this.d.a(surfaceHolder);
                if (this.e == null) {
                    Intent intent = getIntent();
                    ArrayList arrayList = new ArrayList();
                    if (intent != null) {
                        BarcodeFormat[] values = BarcodeFormat.values();
                        int[] intArrayExtra = intent.getIntArrayExtra("barcode_format");
                        if (intArrayExtra != null) {
                            for (int i2 = 0; i2 < intArrayExtra.length; i2++) {
                                if (intArrayExtra[i2] >= 0 && intArrayExtra[i2] < values.length) {
                                    arrayList.add(values[intArrayExtra[i2]]);
                                }
                            }
                        } else {
                            int intExtra = intent.getIntExtra("barcode_format", -1);
                            if (intExtra >= 0 && intExtra < values.length) {
                                arrayList.add(values[intExtra]);
                            }
                        }
                    }
                    if (arrayList.size() == 0) {
                        arrayList.add(BarcodeFormat.QR_CODE);
                    }
                    this.e = new CaptureActivityHandler(this, arrayList, (Map<DecodeHintType, ?>) null, (String) null, this.d);
                }
                a((Bitmap) null, (Result) null);
            } catch (IOException e2) {
                e2.printStackTrace();
                LogUtils.e(b, e2);
                c();
            } catch (RuntimeException e3) {
                e3.printStackTrace();
                LogUtils.e(b, "Unexpected error initializing camera", e3);
                c();
            }
        }
    }

    private void c() {
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        final Application application = getApplication();
        builder.setMessage((CharSequence) getString(R.string.msg_camera_framework_bug_new, new Object[]{Utils.a((Context) application)}));
        builder.setNegativeButton((CharSequence) "去设置", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ScanBarcodeActivity.this.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + application.getPackageName())));
                ScanBarcodeActivity.this.finish();
            }
        });
        builder.setPositiveButton((CharSequence) "取消", (DialogInterface.OnClickListener) new FinishListener(this));
        builder.setOnCancelListener(new FinishListener(this));
        builder.show();
    }

    public void restartPreviewAfterDelay(long j2) {
        if (this.e != null) {
            this.e.sendEmptyMessageDelayed(10, j2);
        }
        d();
    }

    private void d() {
        this.g.setVisibility(0);
        this.j = null;
    }

    public void drawViewfinder() {
        this.g.drawViewfinder();
    }
}
