package com.xiaomi.smarthome.smartconfig.step;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.alipay.sdk.sys.a;
import com.libra.sinvoice.SinVoice;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.device.api.CameraApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.framework.page.CommonActivity;
import com.xiaomi.smarthome.framework.webview.WebShellActivity;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.newui.widget.RippleLayout;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.step.SmartConfigStep;
import java.util.Locale;
import org.json.JSONObject;

public class SWCameraStep extends SmartConfigStep implements View.OnClickListener, SinVoice.Listener, RippleLayout.TouchListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f22675a = "89JFSjo8HUbhou5776NJOMp9i90ghg7Y78G78t68899y79HY7g7y87y9ED45Ew30O0jkkl";
    /* access modifiers changed from: private */
    public String b;
    private XQProgressDialog c;
    /* access modifiers changed from: private */
    public SinVoice d;
    @BindView(2131430969)
    ImageView mIvReturn;
    @BindView(2131429559)
    Button mNextButton;
    @BindView(2131431927)
    RippleLayout mRipple;
    @BindView(2131432919)
    View mTitleBar;
    @BindView(2131429479)
    TextView mTvGoQR;
    @BindView(2131431226)
    TextView mTvNothingHeared;
    @BindView(2131432233)
    TextView mTvSecDesc;
    @BindView(2131433001)
    TextView mTvTopDesc;

    public void V_() {
    }

    public void W_() {
    }

    public void X_() {
    }

    public void Y_() {
    }

    public void Z_() {
    }

    public void a(String str) {
    }

    public void c() {
    }

    public void d() {
    }

    public void e() {
    }

    public SmartConfigStep.Step f() {
        return null;
    }

    public void g() {
    }

    public void a(Message message) {
        if (message.what == 120) {
            o();
            q();
        } else if (message.what == 122) {
            o();
        }
    }

    /* access modifiers changed from: package-private */
    public void i() {
        if (TextUtils.isEmpty(this.b)) {
            s();
            return;
        }
        TextView textView = this.mTvTopDesc;
        if (textView != null) {
            textView.setText("code已刷新，请重新发送");
        }
    }

    /* access modifiers changed from: package-private */
    public void j() {
        SinVoice sinVoice = this.d;
        if (sinVoice != null && sinVoice.a()) {
            sinVoice.c();
        }
    }

    /* access modifiers changed from: package-private */
    public void k() {
        SinVoice sinVoice;
        String v = v();
        Log.i("zc", "sendVoice---code:" + v);
        if (!TextUtils.isEmpty(v) && (sinVoice = this.d) != null) {
            if (sinVoice.a()) {
                sinVoice.c();
            } else {
                sinVoice.a(v);
            }
            this.mRipple.setSending(true);
        }
    }

    private String v() {
        StringBuilder sb = new StringBuilder();
        String b2 = SmartConfigDataProvider.a().b();
        String d2 = SmartConfigDataProvider.a().d();
        String a2 = QRCameraStep.a(b2, false);
        String a3 = QRCameraStep.a(d2, true);
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
        if (n()) {
            sb.append("&t=");
            sb.append(SmartConfigDataProvider.a().a(SmartConfigDataProvider.t, ""));
        }
        if (n() && CoreApi.a().D()) {
            ServerBean F = CoreApi.a().F();
            sb.append("&r=");
            sb.append(F == null ? "" : F.f1530a);
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public boolean n() {
        String str = (String) SmartConfigDataProvider.a().a("device_model");
        if (!TextUtils.isEmpty(str)) {
            return !str.equalsIgnoreCase("chuangmi.camera.xiaobai");
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void o() {
        BluetoothLog.c(String.format("checkBindKey bindKey = %s", new Object[]{this.b}));
        CameraApi.getInstance().checkBindKey(this.af, this.b, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                Context context = SWCameraStep.this.af;
                if (context != null) {
                    if (!(context instanceof CommonActivity) || ((CommonActivity) context).isValid()) {
                        BluetoothLog.c(String.format("onSuccess %s", new Object[]{jSONObject}));
                        int optInt = jSONObject.optInt("ret");
                        if (optInt == 0) {
                            int optInt2 = jSONObject.optInt("check_after");
                            if (SWCameraStep.this.U_() == null) {
                                return;
                            }
                            if (optInt2 > 0) {
                                SWCameraStep.this.U_().removeMessages(122);
                                SWCameraStep.this.U_().sendEmptyMessageDelayed(122, (long) (optInt2 * 1000));
                                return;
                            }
                            SWCameraStep.this.U_().removeMessages(122);
                            SWCameraStep.this.U_().sendEmptyMessageDelayed(122, 2000);
                        } else if (optInt == 1) {
                            SmartConfigDataProvider.a().b(SmartConfigDataProvider.L, SWCameraStep.this.b(jSONObject.optString("bind_did")));
                            SWCameraStep.this.a(SmartConfigStep.Step.STEP_QR_SCAN);
                            SWCameraStep.this.mRipple.finish();
                            SWCameraStep.this.d.f();
                            SWCameraStep.this.j();
                        } else if (optInt == -2) {
                            SWCameraStep.this.r();
                        } else if (optInt == -3) {
                            SWCameraStep.this.a(SmartConfigStep.Step.STEP_BIND_BY_OTHER_ERROR);
                            SWCameraStep.this.mRipple.finish();
                            SWCameraStep.this.d.f();
                            SWCameraStep.this.j();
                        }
                    }
                }
            }

            public void onFailure(Error error) {
                if (!(SWCameraStep.this.af instanceof CommonActivity) || ((CommonActivity) SWCameraStep.this.af).isValid()) {
                    BluetoothLog.c(String.format("onFailure %s", new Object[]{error}));
                    Handler U_ = SWCameraStep.this.U_();
                    if (U_ != null) {
                        U_.removeMessages(122);
                        U_.sendEmptyMessageDelayed(122, 2000);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public String b(String str) {
        String str2 = (String) SmartConfigDataProvider.a().a("device_model");
        if ("yunyi.camera.mj1".equals(str2) || !str2.contains("yunyi.camera") || str.startsWith("yunyi.")) {
            return str;
        }
        return "yunyi." + str;
    }

    public void a(Context context) {
        a(context, R.layout.smart_config_sw_camera);
        TitleBarUtil.a(this.mTitleBar);
        final boolean booleanValue = ((Boolean) SmartConfigDataProvider.a().a(SmartConfigDataProvider.M, true)).booleanValue();
        this.d = new SinVoice(this.af, this);
        this.d.b(1);
        this.mRipple.setTouchListener(this);
        this.mRipple.post(new Runnable() {
            public void run() {
                SWCameraStep.this.mRipple.startRippleAnimation();
            }
        });
        this.mTvNothingHeared.getPaint().setFlags(8);
        this.mTvNothingHeared.getPaint().setAntiAlias(true);
        this.mTvGoQR.setOnClickListener(this);
        String str = (String) SmartConfigDataProvider.a().a("device_model");
        if (booleanValue) {
            this.mIvReturn.setImageResource(R.drawable.return_icon_nor_3x);
        } else {
            this.mIvReturn.setImageResource(R.drawable.common_close_img);
        }
        this.mIvReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (booleanValue) {
                    SWCameraStep.this.d_(false);
                } else {
                    SWCameraStep.this.D();
                }
                SWCameraStep.this.j();
                SWCameraStep.this.mRipple.finish();
                SWCameraStep.this.d.f();
            }
        });
        this.mTvNothingHeared.setOnClickListener(this);
        this.mNextButton.setOnClickListener(this);
        this.mNextButton.setEnabled(false);
        this.mNextButton.setTextColor(this.af.getResources().getColor(R.color.blue_btn_text_disable));
        r();
        t();
    }

    public void onClick(View view) {
        if (view.getId() == R.id.heard_qr_scaned) {
            j();
            this.mRipple.finish();
            this.d.f();
            a(SmartConfigStep.Step.STEP_QR_SCAN);
        }
        if (view.getId() == R.id.go_qr) {
            j();
            this.d.f();
            this.mRipple.finish();
            a(SmartConfigStep.Step.STEP_QR_CAMERA);
        }
        if (view.getId() == R.id.nothing_heard) {
            u();
            this.mRipple.finish();
        }
    }

    public void p() {
        q();
        this.c = XQProgressDialog.a(this.af, "", this.af.getString(R.string.camera_waiting));
    }

    public void q() {
        if (this.c != null) {
            this.c.dismiss();
            this.c = null;
        }
    }

    public void r() {
        p();
        BluetoothLog.c(String.format("updateBarcode getBindKey", new Object[0]));
        CameraApi.getInstance().getBindKey(this.af, new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                BluetoothLog.c(String.format("onSuccess %s", new Object[]{str}));
                String unused = SWCameraStep.this.b = str;
                SmartConfigDataProvider.a().b(SmartConfigDataProvider.z, SWCameraStep.this.b);
                Handler U_ = SWCameraStep.this.U_();
                if (U_ != null) {
                    U_.sendEmptyMessageDelayed(120, 300);
                }
            }

            public void onFailure(Error error) {
                BluetoothLog.c(String.format("onFailure %s", new Object[]{error}));
                SWCameraStep.this.q();
                SWCameraStep.this.s();
            }
        });
    }

    public void s() {
        if (this.af != null) {
            Toast.makeText(this.af, R.string.camera_gen_barcode_error, 0).show();
        }
    }

    public void b() {
        Log.i("zc", "---onSinVoiceSendFinish---");
        if (!this.ag) {
            this.mTvTopDesc.setText(this.af.getString(R.string.camera_step_sw_desc_send_finish));
            this.mTvSecDesc.setVisibility(0);
            this.mRipple.setSending(false);
            this.d.f();
            this.d = new SinVoice(this.af, this);
            this.d.b(1);
            this.mNextButton.setEnabled(true);
            this.mNextButton.setTextColor(this.af.getResources().getColor(R.color.blue_btn_text_enable));
        }
    }

    /* access modifiers changed from: package-private */
    public void t() {
        AudioManager audioManager = (AudioManager) this.af.getSystemService("audio");
        if (((double) ((((float) audioManager.getStreamVolume(3)) + 0.0f) / ((float) audioManager.getStreamMaxVolume(3)))) < 0.8d) {
            ToastUtil.a((int) R.string.camera_volume_too_low);
        }
    }

    /* access modifiers changed from: package-private */
    public void u() {
        String str;
        Intent intent = new Intent(this.af, WebShellActivity.class);
        Bundle bundle = new Bundle();
        Locale I = CoreApi.a().I();
        if (I == null) {
            I = Locale.getDefault();
        }
        if (CoreApi.a().D()) {
            ServerBean F = CoreApi.a().F();
            StringBuilder sb = new StringBuilder();
            sb.append("https://");
            if (F != null) {
                str = F.f1530a + ".";
            } else {
                str = "";
            }
            sb.append(str);
            sb.append("//home.mi.com/faq/detail.html?id=10164&locale=");
            sb.append(LocaleUtil.a(I));
            bundle.putString("url", sb.toString());
        } else {
            bundle.putString("url", "https:////home.mi.com/faq/detail.html?id=10164&locale=" + LocaleUtil.a(I));
        }
        intent.putExtras(bundle);
        this.af.startActivity(intent);
    }

    public void h() {
        if (!this.ag && !this.d.a()) {
            t();
            k();
            this.mTvTopDesc.setText(this.af.getString(R.string.camera_sound_sending));
            this.mTvSecDesc.setVisibility(8);
        }
    }
}
