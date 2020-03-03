package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.alipay.sdk.sys.a;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.api.CameraApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.framework.page.CommonActivity;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import com.xiaomi.smarthome.wificonfig.Base64;
import com.xiaomi.smarthome.wificonfig.CameraBarcodeHelpActivity;
import com.xiaomi.smarthome.wificonfig.GifView;
import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.EncodeHintType;
import com.xiaomi.zxing.common.BitMatrix;
import com.xiaomi.zxing.qrcode.QRCodeWriter;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import org.json.JSONObject;

public class QRConfigStep extends SmartConfigStep {

    /* renamed from: a  reason: collision with root package name */
    private static final String f22646a = "89JFSjo8HUbhou5776NJOMp9i90ghg7Y78G78t68899y79HY7g7y87y9ED45Ew30O0jkkl";
    private XQProgressDialog b;
    private Bitmap c = null;
    @BindView(2131428541)
    View contentView;
    /* access modifiers changed from: private */
    public String d;
    @BindView(2131427815)
    ImageView mBarCodeImage;
    @BindView(2131429449)
    Button mGenCode;
    @BindView(2131429562)
    TextView mHelpView;
    @BindView(2131431176)
    Button mNextButton;
    @BindView(2131431868)
    View mResetTips;
    @BindView(2131432904)
    TextView mTipsBtn;
    @BindView(2131432902)
    GifView mTipsGifView;
    @BindView(2131432903)
    ImageView mTipsImageView;
    @BindView(2131432900)
    View mTipsView;

    public void c() {
    }

    public void d() {
    }

    public void e() {
    }

    public SmartConfigStep.Step f() {
        return null;
    }

    public void a(Message message) {
        if (message.what == 120) {
            new Thread() {
                public void run() {
                    super.run();
                    Bitmap unused = QRConfigStep.this.n();
                    QRConfigStep.this.U_().sendEmptyMessage(121);
                }
            }.run();
        } else if (message.what == 121) {
            if (this.c != null) {
                ViewGroup.LayoutParams layoutParams = this.mBarCodeImage.getLayoutParams();
                layoutParams.height = this.c.getHeight();
                layoutParams.width = this.c.getWidth();
                this.mBarCodeImage.setLayoutParams(layoutParams);
                this.mBarCodeImage.invalidate();
                this.mBarCodeImage.setImageBitmap(this.c);
                this.contentView.setVisibility(0);
                this.mTipsView.setVisibility(0);
                this.mResetTips.setVisibility(8);
                k();
            } else {
                j();
            }
            i();
        } else if (message.what == 122) {
            k();
        }
    }

    public void a(Context context) {
        a(context, R.layout.smart_config_qr_connect);
        this.mNextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                QRConfigStep.this.a(SmartConfigStep.Step.STEP_QR_SCAN);
            }
        });
        SpannableStringBuilder valueOf = SpannableStringBuilder.valueOf(this.af.getString(R.string.camera_gen_barcode_tips_help));
        valueOf.setSpan(new ClickableSpan() {
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(QRConfigStep.this.af.getResources().getColor(R.color.class_text_19));
                textPaint.setUnderlineText(true);
            }

            public void onClick(View view) {
                QRConfigStep.this.af.startActivity(new Intent(QRConfigStep.this.af, CameraBarcodeHelpActivity.class));
            }
        }, 0, valueOf.length(), 33);
        this.mHelpView.setHighlightColor(0);
        this.mHelpView.setText(valueOf);
        this.mHelpView.setMovementMethod(LinkMovementMethod.getInstance());
        this.mTipsView.setVisibility(8);
        this.mTipsView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                QRConfigStep.this.mTipsView.setVisibility(8);
            }
        });
        String str = (String) SmartConfigDataProvider.a().a("device_model");
        if (str != null && str.equals("yunyi.camera.v1")) {
            this.mTipsGifView.setMovieResource(R.drawable.barcode_use_tip);
        }
        this.mTipsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                QRConfigStep.this.mTipsView.setVisibility(8);
            }
        });
        if (str == null || !str.equals("yunyi.camera.v1")) {
            this.mResetTips.setVisibility(8);
            g();
            return;
        }
        b();
    }

    public void b() {
        this.mResetTips.setVisibility(0);
        this.contentView.setVisibility(8);
        this.mTipsView.setVisibility(8);
        this.mGenCode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                QRConfigStep.this.g();
            }
        });
    }

    public void g() {
        h();
        CameraApi.getInstance().getBindKey(this.af, new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                String unused = QRConfigStep.this.d = str;
                SmartConfigDataProvider.a().b(SmartConfigDataProvider.z, QRConfigStep.this.d);
                if (QRConfigStep.this.U_() != null) {
                    QRConfigStep.this.U_().sendEmptyMessage(120);
                }
            }

            public void onFailure(Error error) {
                QRConfigStep.this.i();
                QRConfigStep.this.j();
            }
        });
    }

    public void h() {
        i();
        this.b = XQProgressDialog.a(this.af, "", this.af.getString(R.string.camera_waiting));
    }

    public void i() {
        if (this.b != null) {
            this.b.dismiss();
            this.b = null;
        }
    }

    public void j() {
        if (this.af != null) {
            Toast.makeText(this.af, R.string.camera_gen_barcode_error, 0).show();
        }
    }

    /* access modifiers changed from: private */
    public Bitmap n() {
        try {
            this.c = null;
            String o = o();
            if (TextUtils.isEmpty(o)) {
                return null;
            }
            DisplayMetrics displayMetrics = this.af.getResources().getDisplayMetrics();
            int i = (int) (((float) displayMetrics.widthPixels) - (displayMetrics.density * 0.0f));
            Hashtable hashtable = new Hashtable();
            hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix a2 = new QRCodeWriter().a(o, BarcodeFormat.QR_CODE, i, i, hashtable);
            int[] iArr = new int[(i * i)];
            for (int i2 = 0; i2 < i; i2++) {
                for (int i3 = 0; i3 < i; i3++) {
                    if (a2.a(i3, i2)) {
                        iArr[(i2 * i) + i3] = -16777216;
                    } else {
                        iArr[(i2 * i) + i3] = -1;
                    }
                }
            }
            this.c = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
            this.c.setPixels(iArr, 0, i, 0, 0, i, i);
            return this.c;
        } catch (Throwable th) {
            MyLog.a("genBarcodeBitmap", th);
            return null;
        }
    }

    private String o() {
        StringBuilder sb = new StringBuilder();
        String b2 = SmartConfigDataProvider.a().b();
        String d2 = SmartConfigDataProvider.a().d();
        String a2 = a(b2, false);
        String a3 = a(d2, true);
        if (a2 == null || a3 == null) {
            return null;
        }
        sb.append("b=");
        sb.append(this.d);
        sb.append(a.b);
        sb.append("s=");
        sb.append(a2);
        sb.append(a.b);
        sb.append("p=");
        sb.append(a3);
        sb.append("&t=");
        sb.append(SmartConfigDataProvider.a().a(SmartConfigDataProvider.t, ""));
        return sb.toString();
    }

    private String a(String str, boolean z) {
        byte[] bArr;
        if (z) {
            try {
                int length = str.length();
                int length2 = f22646a.length();
                char[] cArr = new char[length];
                for (int i = 0; i < cArr.length; i++) {
                    cArr[i] = 0;
                }
                for (int i2 = 0; i2 < length; i2++) {
                    cArr[i2] = (char) (str.charAt(i2) ^ f22646a.charAt(i2 % length2));
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

    /* access modifiers changed from: package-private */
    public void k() {
        BluetoothLog.c(String.format("checkBindKey bindKey = %s", new Object[]{this.d}));
        CameraApi.getInstance().checkBindKey(this.af, this.d, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (!(QRConfigStep.this.af instanceof CommonActivity) || ((CommonActivity) QRConfigStep.this.af).isValid()) {
                    BluetoothLog.c(String.format("onSuccess %s", new Object[]{jSONObject}));
                    int optInt = jSONObject.optInt("ret");
                    if (optInt == 0) {
                        int optInt2 = jSONObject.optInt("check_after");
                        if (QRConfigStep.this.U_() == null) {
                            return;
                        }
                        if (optInt2 > 0) {
                            QRConfigStep.this.U_().removeMessages(122);
                            QRConfigStep.this.U_().sendEmptyMessageDelayed(122, (long) (optInt2 * 1000));
                            return;
                        }
                        QRConfigStep.this.U_().removeMessages(122);
                        QRConfigStep.this.U_().sendEmptyMessageDelayed(122, 2000);
                    } else if (optInt == 1) {
                        SmartConfigDataProvider.a().b(SmartConfigDataProvider.L, QRConfigStep.this.a(jSONObject.optString("bind_did")));
                        QRConfigStep.this.a(SmartConfigStep.Step.STEP_QR_SCAN);
                    } else if (optInt == -2) {
                        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(QRConfigStep.this.af);
                        builder.b((int) R.string.kuailian_falied_bindkey_invalide);
                        builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                QRConfigStep.this.g();
                            }
                        });
                        builder.d();
                    } else if (optInt == -3) {
                        QRConfigStep.this.a(SmartConfigStep.Step.STEP_BIND_BY_OTHER_ERROR);
                    }
                }
            }

            public void onFailure(Error error) {
                if (!(QRConfigStep.this.af instanceof CommonActivity) || ((CommonActivity) QRConfigStep.this.af).isValid()) {
                    BluetoothLog.c(String.format("onFailure %s", new Object[]{error}));
                    Handler U_ = QRConfigStep.this.U_();
                    if (U_ != null) {
                        U_.removeMessages(122);
                        U_.sendEmptyMessageDelayed(122, 2000);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public String a(String str) {
        String str2 = (String) SmartConfigDataProvider.a().a("device_model");
        if ("yunyi.camera.mj1".equals(str2) || !str2.contains("yunyi.camera") || str.startsWith("yunyi.")) {
            return str;
        }
        return "yunyi." + str;
    }
}
