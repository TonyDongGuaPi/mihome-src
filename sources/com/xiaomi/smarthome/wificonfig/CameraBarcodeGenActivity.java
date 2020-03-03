package com.xiaomi.smarthome.wificonfig;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.alipay.sdk.sys.a;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.api.CameraApi;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.statistic.StatUtil;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.EncodeHintType;
import com.xiaomi.zxing.common.BitMatrix;
import com.xiaomi.zxing.qrcode.QRCodeWriter;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.TimeZone;

public class CameraBarcodeGenActivity extends BaseActivity {
    public static final int MSG_GEN_CODE = 1;
    public static final int MSG_GEN_REFRES_UI = 2;
    public static final int REQ_WAITING = 1;
    public static final String TAG = "CameraBarcodeGenActivity";

    /* renamed from: a  reason: collision with root package name */
    private static final String f22874a = "89JFSjo8HUbhou5776NJOMp9i90ghg7Y78G78t68899y79HY7g7y87y9ED45Ew30O0jkkl";
    /* access modifiers changed from: private */
    public String b;
    private View c;
    private ImageView d;
    private String e = null;
    private String f = null;
    private Button g;
    private View h;
    private TextView i;
    Bitmap mBitmap;
    GifView mTipsGifView;
    View mTipsView;
    Dialog mXQProgressDialogSimple;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(128);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.screenBrightness = 1.0f;
        getWindow().setAttributes(attributes);
        setContentView(R.layout.activity_camera_gen_barcode);
        this.e = getIntent().getStringExtra(DeviceTagInterface.e);
        this.f = getIntent().getStringExtra("password");
        if (this.f == null) {
            this.f = "";
        }
        this.c = findViewById(R.id.reset_tips);
        findViewById(R.id.gen_barcode).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CameraBarcodeGenActivity.this.updateBarcode();
            }
        });
        this.g = (Button) findViewById(R.id.next);
        this.g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(CameraBarcodeGenActivity.this, CameraBarcodeWaitingActivity.class);
                intent.putExtra("bindKey", CameraBarcodeGenActivity.this.b);
                CameraBarcodeGenActivity.this.startActivityForResult(intent, 1);
            }
        });
        this.i = (TextView) findViewById(R.id.help);
        SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(getString(R.string.camera_gen_barcode_tips_help));
        valueOf.setSpan(new ClickableSpan() {
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(CameraBarcodeGenActivity.this.getResources().getColor(R.color.class_text_19));
                textPaint.setUnderlineText(true);
            }

            public void onClick(View view) {
                CameraBarcodeGenActivity.this.startActivity(new Intent(CameraBarcodeGenActivity.this, CameraBarcodeHelpActivity.class));
            }
        }, 0, valueOf.length(), 33);
        this.i.setHighlightColor(0);
        this.i.setText(valueOf);
        this.i.setMovementMethod(LinkMovementMethod.getInstance());
        this.d = (ImageView) findViewById(R.id.barcode_image);
        this.h = findViewById(R.id.content);
        this.mTipsView = findViewById(R.id.tips);
        this.mTipsView.setVisibility(8);
        this.mTipsGifView = (GifView) findViewById(R.id.tips_gif);
        this.mTipsGifView.setMovieResource(R.drawable.barcode_use_tip);
        findViewById(R.id.tips_ok_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CameraBarcodeGenActivity.this.mTipsView.setVisibility(8);
            }
        });
        showResetInfo();
        MobclickAgent.a((Context) this, StatUtil.b, "wificonfig barcode");
    }

    public void showResetInfo() {
        this.c.setVisibility(0);
        this.h.setVisibility(8);
        this.mTipsView.setVisibility(8);
    }

    public void updateBarcode() {
        showDialog();
        CameraApi.getInstance().getBindKey(this, new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                String unused = CameraBarcodeGenActivity.this.b = str;
                CameraBarcodeGenActivity.this.mHandler.sendEmptyMessage(1);
            }

            public void onFailure(Error error) {
                CameraBarcodeGenActivity.this.dismissDialog();
                CameraBarcodeGenActivity.this.showError();
            }
        });
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 1) {
            new Thread() {
                public void run() {
                    super.run();
                    Bitmap unused = CameraBarcodeGenActivity.this.a();
                    CameraBarcodeGenActivity.this.mHandler.sendEmptyMessage(2);
                }
            }.run();
        } else if (message.what == 2) {
            if (this.mBitmap != null) {
                ViewGroup.LayoutParams layoutParams = this.d.getLayoutParams();
                layoutParams.height = this.mBitmap.getHeight();
                layoutParams.width = this.mBitmap.getWidth();
                this.d.setLayoutParams(layoutParams);
                this.d.invalidate();
                this.d.setImageBitmap(this.mBitmap);
                this.h.setVisibility(0);
                this.mTipsView.setVisibility(0);
                this.c.setVisibility(8);
            } else {
                showError();
            }
            dismissDialog();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.mBitmap = null;
    }

    public void showError() {
        Toast.makeText(this, R.string.camera_gen_barcode_error, 0).show();
    }

    public void showDialog() {
        dismissDialog();
        this.mXQProgressDialogSimple = XQProgressDialog.a(this, "", getString(R.string.camera_waiting));
    }

    public void dismissDialog() {
        if (this.mXQProgressDialogSimple != null) {
            this.mXQProgressDialogSimple.dismiss();
            this.mXQProgressDialogSimple = null;
        }
    }

    /* access modifiers changed from: private */
    public Bitmap a() {
        try {
            this.mBitmap = null;
            String b2 = b();
            if (TextUtils.isEmpty(b2)) {
                return null;
            }
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            int i2 = (int) (((float) displayMetrics.widthPixels) - (displayMetrics.density * 0.0f));
            Hashtable hashtable = new Hashtable();
            hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix a2 = new QRCodeWriter().a(b2, BarcodeFormat.QR_CODE, i2, i2, hashtable);
            int[] iArr = new int[(i2 * i2)];
            for (int i3 = 0; i3 < i2; i3++) {
                for (int i4 = 0; i4 < i2; i4++) {
                    if (a2.a(i4, i3)) {
                        iArr[(i3 * i2) + i4] = -16777216;
                    } else {
                        iArr[(i3 * i2) + i4] = -1;
                    }
                }
            }
            this.mBitmap = Bitmap.createBitmap(i2, i2, Bitmap.Config.ARGB_8888);
            this.mBitmap.setPixels(iArr, 0, i2, 0, 0, i2, i2);
            return this.mBitmap;
        } catch (Throwable th) {
            MyLog.a("genBarcodeBitmap", th);
            return null;
        }
    }

    private String b() {
        StringBuilder sb = new StringBuilder();
        String a2 = a(this.e, false);
        String a3 = a(this.f, true);
        if (a2 == null || a3 == null) {
            return null;
        }
        sb.append("b=");
        sb.append(this.b);
        sb.append(a.b);
        sb.append("s=");
        sb.append(a2);
        sb.append(a.b);
        sb.append("p=");
        sb.append(a3);
        sb.append("&t=");
        sb.append(TimeZone.getDefault().getID());
        return sb.toString();
    }

    private String a(String str, boolean z) {
        byte[] bArr;
        if (z) {
            try {
                int length = str.length();
                int length2 = f22874a.length();
                char[] cArr = new char[length];
                for (int i2 = 0; i2 < cArr.length; i2++) {
                    cArr[i2] = 0;
                }
                for (int i3 = 0; i3 < length; i3++) {
                    cArr[i3] = (char) (str.charAt(i3) ^ f22874a.charAt(i3 % length2));
                    if (cArr[i3] == 0) {
                        cArr[i3] = str.charAt(i3);
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

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 1 && i3 == -1 && intent != null) {
            String stringExtra = intent.getStringExtra("ret");
            if ("cancel".equals(stringExtra)) {
                setResult(0);
                finish();
            } else if ("ok".equals(stringExtra)) {
                setResult(-1);
                finish();
            } else if ("retry".equals(stringExtra)) {
                setResult(-1, intent);
                finish();
            }
        }
    }
}
