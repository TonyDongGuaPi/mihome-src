package com.xiaomi.qrcode;

import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.taobao.weex.common.Constants;
import com.xiaomi.qrcode.camera.CameraManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.ChooseGatewayDevice;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.ResetDevicePage;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.device.bluetooth.connect.single.BleBindActivityV2;
import com.xiaomi.smarthome.device.choosedevice.ScanQRCodeDeviceActivity;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.navigate.PageUrl;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.stat.STAT;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONObject;

public class ScanBarcodeActivity extends BaseActivity implements SurfaceHolder.Callback {
    public static final int FLAG_FROM_CONNECT_DEVICE = 100;
    public static final int FLAG_FROM_FIND_DEVICE = 200;
    public static final String PID = "pid";
    public static final int RESULT_ADD_MANULLY = -3;
    public static final int RESULT_NBIOT = -2;
    public static final String SCAN_RESULT_NBIOT = "nbiot";
    public static final String SCAN_RESULT_XIAOXUN = "scan_result";
    public static final String SHOW_ADD_MANULLY = "show_add_manully";
    public static final String TITLE = "title";

    /* renamed from: a  reason: collision with root package name */
    private static final String f12976a = "ScanBarcodeActivity";
    private static final int b = 11;
    private static final int c = 12;
    /* access modifiers changed from: private */
    public CameraManager d;
    private CaptureActivityHandler e;
    private Result f;
    /* access modifiers changed from: private */
    public ViewfinderView g;
    private Result h;
    private boolean i;
    private InactivityTimer j;
    private BeepManager k;
    private AmbientLightManager l;
    /* access modifiers changed from: private */
    public View m;
    /* access modifiers changed from: private */
    public ObjectAnimator n;
    /* access modifiers changed from: private */
    public String o;
    private XQProgressDialog p;
    private TextView q;
    /* access modifiers changed from: private */
    public boolean r = false;
    private int s;
    private TextView t;
    /* access modifiers changed from: private */
    public long u;
    private boolean v = false;

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i2, int i3, int i4) {
    }

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
        getWindow().addFlags(128);
        setContentView(R.layout.scan_barcode_activity);
        ((ImageView) findViewById(R.id.module_a_3_return_btn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ScanBarcodeActivity.this.a();
            }
        });
        this.s = getIntent().getIntExtra("from", 0);
        this.o = getIntent().getStringExtra("model");
        String stringExtra = getIntent().getStringExtra("desc");
        this.t = (TextView) findViewById(R.id.tips);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.scan_qrcode_add_device);
        findViewById(R.id.goto_support_qrcode_page).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ScanBarcodeActivity.this.startActivity(new Intent(ScanBarcodeActivity.this, ScanQRCodeDeviceActivity.class));
            }
        });
        if (this.s == 200) {
            findViewById(R.id.goto_support_qrcode_page).setVisibility(8);
        }
        if (!TextUtils.isEmpty(stringExtra)) {
            this.t.setText(stringExtra);
        }
        this.m = findViewById(R.id.scan_line);
        this.q = (TextView) findViewById(R.id.add_manully);
        if (getIntent().getBooleanExtra(SHOW_ADD_MANULLY, false)) {
            this.q.setVisibility(0);
            this.q.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ScanBarcodeActivity.this.setResult(-3, new Intent());
                    ScanBarcodeActivity.this.finish();
                }
            });
        }
        this.i = false;
        this.j = new InactivityTimer(this);
        this.k = new BeepManager(this);
        this.l = new AmbientLightManager(this);
        b();
    }

    /* access modifiers changed from: private */
    public void a() {
        Intent intent = new Intent();
        intent.putExtra(Constants.Event.FINISH, false);
        setResult(0, intent);
        finish();
    }

    private void b() {
        int b2 = (DisplayUtils.b((Context) this) * 6) / 9;
        this.m.getLayoutParams().width = b2;
        ViewGroup.LayoutParams layoutParams = ((ViewGroup) this.m.getParent()).getLayoutParams();
        layoutParams.width = b2;
        layoutParams.height = b2;
        this.n = ObjectAnimator.ofFloat(this.m, "translationY", new float[]{this.m.getTranslationY(), (float) b2});
        this.n.setDuration(3000);
        this.n.setRepeatCount(-1);
        this.n.setInterpolator(new LinearInterpolator());
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.d == null) {
            this.d = new CameraManager(getApplication());
            this.g = (ViewfinderView) findViewById(R.id.viewfinder_view);
            this.g.setCameraManager(this.d);
            DarkModeCompat.a((View) this.g, false);
        }
        if (this.m.getWidth() == 0) {
            this.m.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                public void onGlobalLayout() {
                    ScanBarcodeActivity.this.m.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    ViewGroup viewGroup = (ViewGroup) ScanBarcodeActivity.this.m.getParent();
                    ScanBarcodeActivity.this.d.a(new Rect(viewGroup.getLeft(), viewGroup.getTop(), viewGroup.getRight(), viewGroup.getBottom()));
                    ScanBarcodeActivity.this.g.invalidate();
                }
            });
        } else {
            ViewGroup viewGroup = (ViewGroup) this.m.getParent();
            this.d.a(new Rect(viewGroup.getLeft(), viewGroup.getTop(), viewGroup.getRight(), viewGroup.getBottom()));
        }
        this.e = null;
        this.h = null;
        f();
        this.k.a();
        this.l.a(this.d);
        this.j.c();
        SurfaceHolder holder = ((SurfaceView) findViewById(R.id.preview_view)).getHolder();
        if (this.i) {
            a(holder);
        } else {
            holder.addCallback(this);
        }
        this.m.setVisibility(0);
        this.n.start();
        this.u = System.currentTimeMillis();
        STAT.c.j();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        if (this.e != null) {
            this.e.a();
            this.e = null;
        }
        this.j.b();
        this.l.a();
        this.k.close();
        this.d.b();
        if (!this.i) {
            ((SurfaceView) findViewById(R.id.preview_view)).getHolder().removeCallback(this);
        }
        this.n.cancel();
        this.i = false;
        this.v = false;
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        this.j.d();
        super.onDestroy();
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            a();
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

    private void a(Bitmap bitmap, Result result) {
        if (this.e == null) {
            this.f = result;
            return;
        }
        if (result != null) {
            this.f = result;
        }
        if (this.f != null) {
            this.e.sendMessage(Message.obtain(this.e, R.id.decode_succeeded, this.f));
        }
        this.f = null;
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (surfaceHolder == null) {
            Log.e(f12976a, "*** WARNING *** surfaceCreated() gave us a null surface!");
        }
        if (!this.i) {
            this.i = true;
            a(surfaceHolder);
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        this.i = false;
    }

    public void handleDecode(Result result, Bitmap bitmap, float f2) {
        this.j.a();
        this.h = result;
        if (bitmap != null) {
            this.k.b();
            a(bitmap, f2, result);
        }
        this.n.cancel();
        System.currentTimeMillis();
        long j2 = this.u;
        if (this.s != 0) {
            a(result.getText());
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("scan_result", result.getText());
        setResult(-1, intent);
        finish();
    }

    private void a(String str) {
        if (!this.r) {
            this.r = true;
            String stringExtra = getIntent().getStringExtra("pid");
            if (this.s == 100) {
                if (this.o == null) {
                    a();
                }
                showDialog();
                if (d()) {
                    b(str);
                    return;
                }
                QrCodeParser qrCodeParser = new QrCodeParser(str, stringExtra);
                if (qrCodeParser.b() != null) {
                    a(qrCodeParser, stringExtra);
                } else {
                    c(str);
                }
            } else if (this.s == 200) {
                QrCodeParser qrCodeParser2 = new QrCodeParser(str, stringExtra);
                if (qrCodeParser2.b() != null) {
                    a(qrCodeParser2, stringExtra);
                } else if (TextUtils.isEmpty(str) || (!str.contains("url.cn/5kZjV2P") && !str.contains(PageUrl.f))) {
                    c();
                } else {
                    try {
                        Uri parse = Uri.parse(URLDecoder.decode(str, "utf-8"));
                        String queryParameter = parse.getQueryParameter("model");
                        if (!TextUtils.isEmpty(queryParameter)) {
                            Intent intent = new Intent();
                            intent.putExtra("scan_result", queryParameter);
                            String queryParameter2 = parse.getQueryParameter("OOB");
                            if (!TextUtils.isEmpty(queryParameter2)) {
                                intent.putExtra(BleBindActivityV2.KEY_QRCODE_OOB, queryParameter2);
                            }
                            setResult(-1, intent);
                            finish();
                            STAT.d.a(1, System.currentTimeMillis() - this.u, queryParameter);
                            return;
                        }
                        c();
                    } catch (Exception e2) {
                        c();
                        e2.printStackTrace();
                    }
                }
            }
        }
    }

    private void b(String str) {
        BluetoothLog.c(String.format("verifyQrcode qrCode = %s, model = %s", new Object[]{str, this.o}));
        DeviceApi.getInstance().verifyQrcode(this, str, this.o, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                BluetoothLog.c(String.format(">>> onSuccess: %s", new Object[]{jSONObject}));
                if (jSONObject == null) {
                    ScanBarcodeActivity.this.c();
                    return;
                }
                String optString = jSONObject.optString("sid");
                int optInt = jSONObject.optInt("bindType", -1);
                if (TextUtils.isEmpty(optString) || optInt == -1) {
                    ScanBarcodeActivity.this.c();
                    return;
                }
                ScanBarcodeActivity.this.dismissDialog();
                Intent intent = new Intent();
                intent.putExtra("scan_result", jSONObject.toString());
                ScanBarcodeActivity.this.setResult(-1, intent);
                ScanBarcodeActivity.this.finish();
                STAT.d.a(1, System.currentTimeMillis() - ScanBarcodeActivity.this.u, ScanBarcodeActivity.this.o);
            }

            public void onFailure(Error error) {
                BluetoothLog.c(String.format(">>> onFailure: %s", new Object[]{error}));
                ScanBarcodeActivity.this.c();
            }
        });
    }

    private void c(String str) {
        BluetoothLog.c(String.format("verifyQrCode qrcode = %s", new Object[]{str}));
        DeviceApi.getInstance().verifyQrcodeNew(this, str, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject == null || jSONObject.optInt("ret", -1) == -1) {
                    ScanBarcodeActivity.this.c();
                    return;
                }
                ScanBarcodeActivity.this.dismissDialog();
                String optString = jSONObject.optString("bind_did");
                Intent intent = new Intent();
                intent.putExtra("scan_result", optString);
                ScanBarcodeActivity.this.setResult(-1, intent);
                ScanBarcodeActivity.this.finish();
                STAT.d.a(1, System.currentTimeMillis() - ScanBarcodeActivity.this.u, ScanBarcodeActivity.this.o);
            }

            public void onFailure(Error error) {
                ScanBarcodeActivity.this.c();
            }
        });
    }

    /* access modifiers changed from: private */
    public void c() {
        Handler handler = getHandler();
        if (isValid() && handler != null) {
            restartPreviewAfterDelay(3000);
            handler.postDelayed(new Runnable() {
                public void run() {
                    ScanBarcodeActivity.this.dismissDialog();
                    boolean unused = ScanBarcodeActivity.this.r = false;
                    if (!ScanBarcodeActivity.this.n.isRunning()) {
                        ScanBarcodeActivity.this.n.start();
                    }
                    Toast.makeText(ScanBarcodeActivity.this, ScanBarcodeActivity.this.getString(R.string.verifying_qrcode_failed), 0).show();
                }
            }, 1000);
            STAT.d.a(2, System.currentTimeMillis() - this.u, "");
        }
    }

    public void showDialog() {
        dismissDialog();
        this.p = XQProgressDialog.a(this, "", getString(R.string.verifying_qrcode));
    }

    public void dismissDialog() {
        if (this.p != null) {
            this.p.dismiss();
            this.p = null;
        }
    }

    private boolean d() {
        return !TextUtils.isEmpty(this.o) && DeviceFactory.r(this.o);
    }

    private void a(QrCodeParser qrCodeParser, String str) {
        PluginDeviceInfo c2;
        dismissDialog();
        if (TextUtils.isEmpty(str)) {
            String b2 = CoreApi.a().b(Integer.parseInt(qrCodeParser.c()));
            PluginRecord d2 = CoreApi.a().d(b2);
            if (d2 == null || (c2 = d2.c()) == null) {
                c();
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("model", b2);
            bundle.putString(SCAN_RESULT_NBIOT, qrCodeParser.toString());
            int e2 = c2.e();
            if (e2 == 14) {
                ChooseGatewayDevice.selectActivity(this, d2, 12, bundle);
            } else if (e2 == 15) {
                startActivityForResult(new Intent(this, ResetDevicePage.class).putExtras(bundle), 11);
            }
            STAT.d.a(1, System.currentTimeMillis() - this.u, this.o);
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(SCAN_RESULT_NBIOT, qrCodeParser.toString());
        setResult(-2, intent);
        finish();
    }

    private void a(Bitmap bitmap, float f2, Result result) {
        ResultPoint[] resultPoints = result.getResultPoints();
        if (resultPoints != null && resultPoints.length > 0) {
            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            paint.setColor(getResources().getColor(R.color.result_points));
            if (resultPoints.length == 2) {
                paint.setStrokeWidth(4.0f);
                a(canvas, paint, resultPoints[0], resultPoints[1], f2);
            } else if (resultPoints.length == 4 && (result.getBarcodeFormat() == BarcodeFormat.UPC_A || result.getBarcodeFormat() == BarcodeFormat.EAN_13)) {
                a(canvas, paint, resultPoints[0], resultPoints[1], f2);
                a(canvas, paint, resultPoints[2], resultPoints[3], f2);
            } else {
                paint.setStrokeWidth(10.0f);
                for (ResultPoint resultPoint : resultPoints) {
                    if (resultPoint != null) {
                        canvas.drawPoint(resultPoint.getX() * f2, resultPoint.getY() * f2, paint);
                    }
                }
            }
        }
    }

    private static void a(Canvas canvas, Paint paint, ResultPoint resultPoint, ResultPoint resultPoint2, float f2) {
        if (resultPoint != null && resultPoint2 != null) {
            canvas.drawLine(f2 * resultPoint.getX(), f2 * resultPoint.getY(), f2 * resultPoint2.getX(), f2 * resultPoint2.getY(), paint);
        }
    }

    private void a(SurfaceHolder surfaceHolder) {
        if (!this.v) {
            this.v = true;
            if (surfaceHolder == null) {
                throw new IllegalStateException("No SurfaceHolder provided");
            } else if (this.d.a()) {
                Log.w(f12976a, "initCamera() while already open -- late SurfaceView callback?");
            } else {
                try {
                    int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.std_titlebar_height);
                    DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
                    this.d.a(surfaceHolder, displayMetrics.widthPixels, displayMetrics.heightPixels - dimensionPixelSize);
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
                    Log.w(f12976a, e2);
                    e();
                } catch (Exception e3) {
                    Log.w(f12976a, "Unexpected error initializing camera", e3);
                    e();
                }
            }
        }
    }

    private void e() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.msg_camera_framework_bug));
        builder.setPositiveButton(R.string.ok_button, new FinishListener(this));
        builder.setOnCancelListener(new FinishListener(this));
        builder.show();
    }

    public void restartPreviewAfterDelay(long j2) {
        if (this.e != null) {
            this.e.sendEmptyMessageDelayed(R.id.restart_preview, j2);
        }
        f();
    }

    private void f() {
        this.g.setVisibility(0);
        this.h = null;
    }

    public void drawViewfinder() {
        this.g.drawViewfinder();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, @Nullable Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 11) {
            if (i3 == -1) {
                setResult(-2, intent);
            }
            finish();
        } else if (i2 == 12) {
            if (i3 == -1) {
                setResult(-2, intent);
            }
            finish();
        }
    }
}
