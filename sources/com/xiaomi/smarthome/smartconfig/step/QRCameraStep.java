package com.xiaomi.smarthome.smartconfig.step;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.alipay.sdk.sys.a;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.qrcode.QRCodeGenerator;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.api.CameraApi;
import com.xiaomi.smarthome.feedback.FeedbackCommonProblemActivity;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.log.MyLog;
import com.xiaomi.smarthome.framework.page.CommonActivity;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.wificonfig.Base64;
import com.xiaomi.zxing.BarcodeFormat;
import com.xiaomi.zxing.EncodeHintType;
import com.xiaomi.zxing.common.BitMatrix;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import org.json.JSONObject;

public class QRCameraStep extends SmartConfigStep {

    /* renamed from: a  reason: collision with root package name */
    private static final int f22631a = 1;
    private static final int b = 2;
    private static final int c = 3;
    private static final String d = "89JFSjo8HUbhou5776NJOMp9i90ghg7Y78G78t68899y79HY7g7y87y9ED45Ew30O0jkkl";
    private static final String e = "QRCameraStep";
    private XQProgressDialog f;
    private Bitmap g = null;
    /* access modifiers changed from: private */
    public String h;
    private float i;
    private int j;
    private CompoundButton.OnCheckedChangeListener k = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            QRCameraStep.this.mNextButton.setEnabled(z);
        }
    };
    @BindView(2131427814)
    ImageView mBarCodeImage;
    @BindView(2131428307)
    CheckBox mCbxHeard;
    @BindView(2131432902)
    SimpleDraweeView mGifTips;
    @BindView(2131431226)
    TextView mHelpView;
    @BindView(2131430969)
    ImageView mIvReturn;
    @BindView(2131429559)
    Button mNextButton;
    @BindView(2131431286)
    TextView mOpTips;
    @BindView(2131433584)
    TextView mReload;
    @BindView(2131432919)
    View mTitleBar;

    private void q() {
    }

    private void r() {
    }

    public SmartConfigStep.Step f() {
        return null;
    }

    public void a(Message message) {
        if (message.what == 120) {
            new Thread() {
                public void run() {
                    super.run();
                    Bitmap unused = QRCameraStep.this.o();
                    Handler U_ = QRCameraStep.this.U_();
                    if (U_ != null) {
                        U_.sendEmptyMessage(121);
                    }
                }
            }.run();
        } else if (message.what == 121) {
            if (this.g != null) {
                this.mBarCodeImage.setImageBitmap(this.g);
                a((Activity) this.af, 0.8f);
                this.mCbxHeard.setEnabled(true);
                this.mCbxHeard.setOnCheckedChangeListener(this.k);
                q();
                n();
            } else {
                k();
            }
            i();
        } else if (message.what == 122) {
            n();
        }
    }

    public void a(Context context) {
        a(context, R.layout.smart_config_qr_camera);
        TitleBarUtil.a(this.mTitleBar);
        final boolean booleanValue = ((Boolean) SmartConfigDataProvider.a().a(SmartConfigDataProvider.M, true)).booleanValue();
        final String str = (String) SmartConfigDataProvider.a().a("device_model");
        if (booleanValue) {
            this.mIvReturn.setImageResource(R.drawable.return_icon_nor_3x);
        } else {
            this.mIvReturn.setImageResource(R.drawable.common_close_img);
        }
        this.mIvReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (booleanValue) {
                    QRCameraStep.this.d_(false);
                } else {
                    QRCameraStep.this.D();
                }
            }
        });
        this.mNextButton.setEnabled(false);
        this.mHelpView.getPaint().setAntiAlias(true);
        this.mHelpView.setMovementMethod(LinkMovementMethod.getInstance());
        this.mHelpView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (QRCameraStep.this.af != null) {
                    STAT.d.aU(str);
                    Intent intent = new Intent(QRCameraStep.this.af, FeedbackCommonProblemActivity.class);
                    intent.putExtra("extra_model", str);
                    QRCameraStep.this.af.startActivity(intent);
                }
            }
        });
        this.mCbxHeard.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
        this.mCbxHeard.setEnabled(false);
        this.mNextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.d.aV(str);
                QRCameraStep.this.a(SmartConfigStep.Step.STEP_QR_SCAN);
            }
        });
        this.mBarCodeImage.postDelayed(new Runnable() {
            public final void run() {
                QRCameraStep.this.s();
            }
        }, 200);
        this.mGifTips.setController(((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setUri(new Uri.Builder().scheme("res").path(String.valueOf(R.drawable.camera_gif_tips)).build()).setAutoPlayAnimations(true)).build());
        g();
        STAT.c.g(str);
        this.mReload.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                QRCameraStep.this.mReload.setVisibility(4);
                QRCameraStep.this.g();
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void s() {
        int b2 = DisplayUtils.b(this.af) - (DisplayUtils.a(41.0f) * 2);
        int top = (this.mOpTips.getTop() - this.mBarCodeImage.getTop()) - DisplayUtils.a(15.0f);
        this.j = Math.min(b2, top);
        BluetoothLog.c(String.format("Barcode width = %d, height = %d, size = %d", new Object[]{Integer.valueOf(b2), Integer.valueOf(top), Integer.valueOf(this.j)}));
    }

    public void a(Activity activity, float f2) {
        WindowManager.LayoutParams attributes = activity.getWindow().getAttributes();
        attributes.screenBrightness = f2;
        activity.getWindow().setAttributes(attributes);
    }

    public boolean a() {
        d_(false);
        return true;
    }

    /* access modifiers changed from: package-private */
    public void b() {
        a((Activity) this.af, -1.0f);
    }

    /* access modifiers changed from: protected */
    public void d_(boolean z) {
        b();
        super.d_(z);
    }

    /* access modifiers changed from: protected */
    public void a(SmartConfigStep.Step step) {
        b();
        super.a(step);
    }

    public void g() {
        h();
        BluetoothLog.c(String.format("updateBarcode getBindKey", new Object[0]));
        CameraApi.getInstance().getBindKeyAndExpireTime(this.af, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                BluetoothLog.c(String.format("onSuccess %s", new Object[]{jSONObject}));
                String unused = QRCameraStep.this.h = jSONObject.optString("bindkey");
                SmartConfigDataProvider.a().b(SmartConfigDataProvider.z, QRCameraStep.this.h);
                SmartConfigDataProvider.a().b(SmartConfigDataProvider.A, QRCameraStep.this.h + "," + jSONObject.optString("expire"));
                Handler U_ = QRCameraStep.this.U_();
                if (U_ != null) {
                    U_.sendEmptyMessageDelayed(120, 300);
                }
            }

            public void onFailure(Error error) {
                BluetoothLog.c(String.format("onFailure %s", new Object[]{error}));
                QRCameraStep.this.i();
                QRCameraStep.this.k();
            }
        });
    }

    public void h() {
        i();
        this.f = XQProgressDialog.a(this.af, "", this.af.getString(R.string.camera_waiting));
    }

    public void i() {
        if (this.f != null) {
            this.f.dismiss();
            this.f = null;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean j() {
        String str = (String) SmartConfigDataProvider.a().a("device_model");
        if (!TextUtils.isEmpty(str)) {
            return !str.equalsIgnoreCase("chuangmi.camera.xiaobai");
        }
        return true;
    }

    public void k() {
        if (this.af != null) {
            this.mReload.setVisibility(0);
            Toast.makeText(this.af, R.string.camera_gen_barcode_error, 0).show();
        }
    }

    /* access modifiers changed from: private */
    public Bitmap o() {
        try {
            this.g = null;
            String p = p();
            if (TextUtils.isEmpty(p)) {
                return null;
            }
            int i2 = this.j;
            Hashtable hashtable = new Hashtable();
            hashtable.put(EncodeHintType.CHARACTER_SET, "utf-8");
            BitMatrix a2 = new QRCodeGenerator().a(p, BarcodeFormat.QR_CODE, i2, i2, hashtable);
            int[] iArr = new int[(i2 * i2)];
            for (int i3 = 0; i3 < i2; i3++) {
                for (int i4 = 0; i4 < i2; i4++) {
                    if (a2.a(i4, i3)) {
                        iArr[(i3 * i2) + i4] = -16777216;
                    } else {
                        iArr[(i3 * i2) + i4] = -460552;
                    }
                }
            }
            this.g = Bitmap.createBitmap(i2, i2, Bitmap.Config.ARGB_8888);
            this.g.setPixels(iArr, 0, i2, 0, 0, i2, i2);
            return this.g;
        } catch (Throwable th) {
            MyLog.a("genBarcodeBitmap", th);
            return null;
        }
    }

    private String p() {
        StringBuilder sb = new StringBuilder();
        String b2 = SmartConfigDataProvider.a().b();
        String d2 = SmartConfigDataProvider.a().d();
        String a2 = a(b2, false);
        String a3 = a(d2, true);
        if (a2 == null || a3 == null) {
            return null;
        }
        sb.append("b=");
        sb.append(this.h);
        sb.append(a.b);
        sb.append("s=");
        sb.append(a2);
        sb.append(a.b);
        sb.append("p=");
        sb.append(a3);
        ServerBean F = CoreApi.a().F();
        if (j()) {
            sb.append("&t=");
            sb.append(SmartConfigDataProvider.a().a(SmartConfigDataProvider.t, ""));
        }
        if (!j() || F == null || ServerCompact.c(F)) {
            String H = CoreApi.a().H();
            if (!TextUtils.isEmpty(H) && H.equalsIgnoreCase("preview")) {
                sb.append("&r=pv");
            }
        } else {
            sb.append("&r=");
            sb.append(F.f1530a);
        }
        return sb.toString();
    }

    public static String a(String str, boolean z) {
        byte[] bArr;
        if (z) {
            try {
                int length = str.length();
                int length2 = d.length();
                char[] cArr = new char[length];
                for (int i2 = 0; i2 < cArr.length; i2++) {
                    cArr[i2] = 0;
                }
                for (int i3 = 0; i3 < length; i3++) {
                    cArr[i3] = (char) (str.charAt(i3) ^ d.charAt(i3 % length2));
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

    public void c() {
        BluetoothLog.c(String.format("%s.onResumeStep", new Object[]{getClass().getSimpleName()}));
    }

    public void d() {
        BluetoothLog.c(String.format("%s.onPauseStep", new Object[]{getClass().getSimpleName()}));
        r();
    }

    public void e() {
        BluetoothLog.c(String.format("%s.onFinishStep", new Object[]{getClass().getSimpleName()}));
        r();
    }

    public void a(int i2, int i3, Intent intent) {
        super.a(i2, i3, intent);
    }

    /* access modifiers changed from: package-private */
    public void n() {
        BluetoothLog.c(String.format("checkBindKey bindKey = %s", new Object[]{this.h}));
        CameraApi.getInstance().checkBindKey(this.af, this.h, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                Context context = QRCameraStep.this.af;
                if (context != null) {
                    if (!(context instanceof CommonActivity) || ((CommonActivity) context).isValid()) {
                        LogUtilGrey.a(QRCameraStep.e, "checkBindKey onSuccess:" + jSONObject);
                        int optInt = jSONObject.optInt("ret");
                        if (optInt == 0) {
                            int optInt2 = jSONObject.optInt("check_after");
                            if (QRCameraStep.this.U_() == null) {
                                return;
                            }
                            if (optInt2 > 0) {
                                QRCameraStep.this.U_().removeMessages(122);
                                QRCameraStep.this.U_().sendEmptyMessageDelayed(122, (long) (optInt2 * 1000));
                                return;
                            }
                            QRCameraStep.this.U_().removeMessages(122);
                            QRCameraStep.this.U_().sendEmptyMessageDelayed(122, 2000);
                        } else if (optInt == 1) {
                            SmartConfigDataProvider.a().b(SmartConfigDataProvider.L, QRCameraStep.this.a(jSONObject.optString("bind_did")));
                            QRCameraStep.this.a(SmartConfigStep.Step.STEP_QR_SCAN);
                        } else if (optInt == -2) {
                            QRCameraStep.this.mCbxHeard.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
                            QRCameraStep.this.mCbxHeard.setEnabled(false);
                            QRCameraStep.this.mNextButton.setEnabled(false);
                            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(context);
                            builder.b((int) R.string.kuailian_falied_bindkey_invalide);
                            builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    QRCameraStep.this.g();
                                }
                            }).a(QRCameraStep.this.af.getResources().getColor(R.color.miui_blue), -1);
                            builder.d();
                        } else if (optInt == -3) {
                            QRCameraStep.this.a(SmartConfigStep.Step.STEP_BIND_BY_OTHER_ERROR);
                        }
                    }
                }
            }

            public void onFailure(Error error) {
                if (!(QRCameraStep.this.af instanceof CommonActivity) || ((CommonActivity) QRCameraStep.this.af).isValid()) {
                    LogUtilGrey.a(QRCameraStep.e, "checkBindKey onFailure:" + error);
                    Handler U_ = QRCameraStep.this.U_();
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
