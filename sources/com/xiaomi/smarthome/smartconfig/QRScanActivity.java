package com.xiaomi.smarthome.smartconfig;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alipay.sdk.sys.a;
import com.xiaomi.qrcode.QRCodeGenerator;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.api.CameraApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.wificonfig.Base64;
import com.xiaomi.smarthome.wificonfig.CameraBarcodeHelpActivity;
import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.EncodeHintType;
import com.xiaomi.zxing.common.BitMatrix;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;

public class QRScanActivity extends BaseActivity {
    public static final String FLAG_GOTO_CONFIG = "flag_goto_config";

    /* renamed from: a  reason: collision with root package name */
    private static final int f22313a = 2;
    private static final int b = 3;
    private static final String c = "89JFSjo8HUbhou5776NJOMp9i90ghg7Y78G78t68899y79HY7g7y87y9ED45Ew30O0jkkl";
    private XQProgressDialog d;
    private Bitmap e = null;
    /* access modifiers changed from: private */
    public String f;
    /* access modifiers changed from: private */
    public int g;
    @BindView(2131427814)
    ImageView mBarCodeImage;
    @BindView(2131428541)
    View mContentView;
    @BindView(2131431226)
    TextView mHelpView;
    @BindView(2131430969)
    ImageView mIvReturn;
    @BindView(2131429559)
    Button mNextButton;
    @BindView(2131432919)
    View mTitleBar;
    @BindView(2131433001)
    TextView mTvTopDesc;

    public void handleMessage(Message message) {
        if (message.what == 120) {
            new Thread() {
                public void run() {
                    super.run();
                    Bitmap unused = QRScanActivity.this.a();
                    if (QRScanActivity.this.mHandler != null) {
                        QRScanActivity.this.mHandler.sendEmptyMessage(121);
                    }
                }
            }.run();
        } else if (message.what == 121) {
            if (this.e != null) {
                this.mContentView.setVisibility(0);
                this.mBarCodeImage.setImageBitmap(this.e);
            } else {
                Toast.makeText(this, R.string.camera_gen_barcode_error, 0).show();
            }
            dismissDialog();
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.smart_config_qr_camera);
        ButterKnife.bind((Activity) this);
        final String str = (String) SmartConfigDataProvider.a().a("device_model");
        this.mIvReturn.setImageResource(R.drawable.common_close_img);
        this.mIvReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                QRScanActivity.this.onBackPressed();
            }
        });
        this.mTvTopDesc.setVisibility(0);
        SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(getString(R.string.camera_nothing_heard));
        valueOf.setSpan(new ClickableSpan() {
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(Color.parseColor("#527acc"));
                textPaint.setUnderlineText(false);
            }

            public void onClick(View view) {
                Intent intent = new Intent(QRScanActivity.this, CameraBarcodeHelpActivity.class);
                intent.putExtra("model", str);
                intent.putExtra("url", "/views/faqDetail.html?question=" + QRScanActivity.this.getString(R.string.param_camera_nothing_heard));
                QRScanActivity.this.startActivityForResult(intent, 2);
            }
        }, 0, valueOf.length(), 33);
        this.mHelpView.setHighlightColor(0);
        this.mHelpView.setText(valueOf);
        this.mHelpView.setMovementMethod(LinkMovementMethod.getInstance());
        this.mNextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                QRScanActivity.this.setResult(300);
                QRScanActivity.this.finish();
            }
        });
        this.mBarCodeImage.postDelayed(new Runnable() {
            public void run() {
                int a2 = DisplayUtils.a(214.0f);
                int a3 = DisplayUtils.a(214.0f);
                int unused = QRScanActivity.this.g = Math.min(a2, a3);
                BluetoothLog.c(String.format("Barcode width = %d, height = %d, size = %d", new Object[]{Integer.valueOf(a3), Integer.valueOf(a2), Integer.valueOf(QRScanActivity.this.g)}));
            }
        }, 200);
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                QRScanActivity.this.updateBarcode();
            }
        }, 300);
    }

    /* access modifiers changed from: private */
    public Bitmap a() {
        try {
            this.e = null;
            String b2 = b();
            if (TextUtils.isEmpty(b2)) {
                return null;
            }
            int i = this.g;
            Hashtable hashtable = new Hashtable();
            hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix a2 = new QRCodeGenerator().a(b2, BarcodeFormat.QR_CODE, i, i, hashtable);
            int[] iArr = new int[(i * i)];
            for (int i2 = 0; i2 < i; i2++) {
                for (int i3 = 0; i3 < i; i3++) {
                    if (a2.a(i3, i2)) {
                        iArr[(i2 * i) + i3] = -16777216;
                    } else {
                        iArr[(i2 * i) + i3] = -460552;
                    }
                }
            }
            this.e = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
            this.e.setPixels(iArr, 0, i, 0, 0, i, i);
            return this.e;
        } catch (Throwable th) {
            MyLog.a("genBarcodeBitmap", th);
            return null;
        }
    }

    private String b() {
        StringBuilder sb = new StringBuilder();
        String b2 = SmartConfigDataProvider.a().b();
        String d2 = SmartConfigDataProvider.a().d();
        String a2 = a(b2, false);
        String a3 = a(d2, true);
        if (a2 == null || a3 == null) {
            return null;
        }
        sb.append("b=");
        sb.append(this.f);
        sb.append(a.b);
        sb.append("s=");
        sb.append(a2);
        sb.append(a.b);
        sb.append("p=");
        sb.append(a3);
        if (supportTimeArea()) {
            sb.append("&t=");
            sb.append(SmartConfigDataProvider.a().a(SmartConfigDataProvider.t, ""));
        }
        if (supportTimeArea() && CoreApi.a().D()) {
            ServerBean F = CoreApi.a().F();
            sb.append("&r=");
            sb.append(F == null ? "" : F.f1530a);
        }
        return sb.toString();
    }

    private String a(String str, boolean z) {
        byte[] bArr;
        if (z) {
            try {
                int length = str.length();
                int length2 = c.length();
                char[] cArr = new char[length];
                for (int i = 0; i < cArr.length; i++) {
                    cArr[i] = 0;
                }
                for (int i2 = 0; i2 < length; i2++) {
                    cArr[i2] = (char) (str.charAt(i2) ^ c.charAt(i2 % length2));
                    if (cArr[i2] == 0) {
                        cArr[i2] = str.charAt(i2);
                    }
                }
                bArr = new String(cArr).getBytes("UTF-8");
            } catch (UnsupportedEncodingException unused) {
                return null;
            }
        } else {
            bArr = str.getBytes("UTF-8");
        }
        return new String(Base64.a(bArr));
    }

    public void updateBarcode() {
        showDialog();
        BluetoothLog.c(String.format("updateBarcode getBindKey", new Object[0]));
        CameraApi.getInstance().getBindKey(this, new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                BluetoothLog.c(String.format("onSuccess %s", new Object[]{str}));
                String unused = QRScanActivity.this.f = str;
                SmartConfigDataProvider.a().b(SmartConfigDataProvider.z, QRScanActivity.this.f);
                if (QRScanActivity.this.mHandler != null) {
                    QRScanActivity.this.mHandler.sendEmptyMessageDelayed(120, 300);
                }
            }

            public void onFailure(Error error) {
                BluetoothLog.c(String.format("onFailure %s", new Object[]{error}));
                QRScanActivity.this.dismissDialog();
                Toast.makeText(QRScanActivity.this, R.string.camera_gen_barcode_error, 0).show();
            }
        });
    }

    public void showDialog() {
        dismissDialog();
        this.d = XQProgressDialog.a(this, "", getString(R.string.camera_waiting));
    }

    public void dismissDialog() {
        if (this.d != null) {
            this.d.dismiss();
            this.d = null;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean supportTimeArea() {
        String str = (String) SmartConfigDataProvider.a().a("device_model");
        if (!TextUtils.isEmpty(str)) {
            return !str.equalsIgnoreCase("chuangmi.camera.xiaobai");
        }
        return true;
    }

    private void a(String str) {
        Intent intent = new Intent(this, CameraHelpActivity.class);
        intent.putExtra("model", str);
        startActivityForResult(intent, 3);
    }

    public void onBackPressed() {
        super.onBackPressed();
        setResult(200);
        finish();
        overridePendingTransition(0, R.anim.activity_slide_out_bottom);
    }
}
