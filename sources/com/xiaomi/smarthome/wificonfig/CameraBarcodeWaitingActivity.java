package com.xiaomi.smarthome.wificonfig;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.umeng.analytics.MobclickAgent;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.CameraApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.statistic.StatUtil;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.widget.PieProgressBar;
import org.json.JSONObject;

public class CameraBarcodeWaitingActivity extends BaseActivity {
    public static final int KUAILIAN_TIME = 120000;
    protected static final int MSG_UPDATE_CHECKOUT = 2;
    protected static final int MSG_UPDATE_KUAILIAN_PROGRESS = 1;

    /* renamed from: a  reason: collision with root package name */
    private String f22886a;
    private TextView b;
    private TextView c;
    private PieProgressBar d;
    private TextView e;
    private TextView f;
    private TextView g;
    private ImageView h;
    private View i;
    private View j;
    private View k;
    private View l;
    private View m;
    protected long mStartTime;
    private View n;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_camera_gen_barcode_waiting);
        this.f22886a = getIntent().getStringExtra("bindKey");
        this.d = (PieProgressBar) findViewById(R.id.kuailian_progress);
        this.b = (TextView) findViewById(R.id.kuailianing_main_title);
        this.c = (TextView) findViewById(R.id.kuailianing_main_sub_title);
        this.e = (TextView) findViewById(R.id.connecting_progress);
        this.g = (TextView) findViewById(R.id.finish_title);
        this.f = (TextView) findViewById(R.id.kuailianing_final_info);
        this.h = (ImageView) findViewById(R.id.icon_final);
        this.i = findViewById(R.id.fouth_step);
        this.j = findViewById(R.id.final_step);
        this.k = findViewById(R.id.finish_error_btn_container);
        this.l = findViewById(R.id.finish_success_btn);
        this.m = findViewById(R.id.retry_btn);
        this.n = findViewById(R.id.cancel_btn);
        a();
        checkBindKey();
        MobclickAgent.a((Context) this, StatUtil.b, "BarcodeConnection");
    }

    /* access modifiers changed from: package-private */
    public void checkBindKey() {
        CameraApi.getInstance().checkBindKey(this, this.f22886a, new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                int optInt = jSONObject.optInt("ret");
                if (optInt == 0) {
                    int optInt2 = jSONObject.optInt("check_after");
                    if (optInt2 > 0) {
                        CameraBarcodeWaitingActivity.this.mHandler.removeMessages(2);
                        CameraBarcodeWaitingActivity.this.mHandler.sendEmptyMessageDelayed(2, (long) (optInt2 * 1000));
                        return;
                    }
                    CameraBarcodeWaitingActivity.this.a(false, R.string.kuailian_failed);
                } else if (optInt == 1) {
                    String optString = jSONObject.optString("bind_did");
                    if (!optString.startsWith("yunyi.")) {
                        optString = "yunyi." + optString;
                    }
                    Device b = SmartHomeDeviceManager.a().b(optString);
                    if (b != null) {
                        b.setOwner(true);
                    }
                    SmartHomeDeviceManager.a().p();
                    CameraBarcodeWaitingActivity.this.a(true, R.string.kuailian_success);
                } else if (optInt == -2 || optInt == -3) {
                    MLAlertDialog.Builder builder = new MLAlertDialog.Builder(CameraBarcodeWaitingActivity.this);
                    builder.b((int) R.string.kuailian_falied_bindkey_invalide);
                    builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent();
                            intent.putExtra("ret", "retry");
                            CameraBarcodeWaitingActivity.this.setResult(-1, intent);
                            CameraBarcodeWaitingActivity.this.finish();
                        }
                    });
                    builder.d();
                } else {
                    CameraBarcodeWaitingActivity.this.a(false, R.string.kuailian_failed);
                }
            }

            public void onFailure(Error error) {
                CameraBarcodeWaitingActivity.this.a(false, R.string.kuailian_failed);
            }
        });
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                float currentTimeMillis = (((float) (System.currentTimeMillis() - this.mStartTime)) * 100.0f) / 120000.0f;
                if (currentTimeMillis > 101.0f) {
                    a(false, R.string.kuailian_failed);
                    return;
                }
                this.d.setPercent(currentTimeMillis);
                this.c.setText(R.string.kuailian_sub_main_title_waiting);
                this.mHandler.sendEmptyMessageDelayed(1, 100);
                return;
            case 2:
                checkBindKey();
                return;
            default:
                return;
        }
    }

    private void a() {
        this.d.setPercentView(this.e);
        this.mStartTime = System.currentTimeMillis();
        this.mHandler.sendEmptyMessageDelayed(1, 100);
        this.b.setText(String.format(getString(R.string.kuailian_main_title_3), new Object[]{SHApplication.getAppContext().getString(R.string.camera_name)}));
        this.g.setVisibility(0);
        this.g.setText(R.string.cancel);
        this.g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("ret", "cancel");
                CameraBarcodeWaitingActivity.this.setResult(-1, intent);
                CameraBarcodeWaitingActivity.this.finish();
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(boolean z, int i2) {
        this.i.setVisibility(8);
        this.mHandler.removeMessages(1);
        this.g.setVisibility(4);
        this.j.setVisibility(0);
        this.f.setText(i2);
        if (z) {
            MobclickAgent.a((Context) this, StatUtil.b, "BarcodeConnectionSuccess");
            this.k.setVisibility(8);
            this.h.setImageResource(R.drawable.kuailian_success_icon);
            this.l.setVisibility(0);
            this.l.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("ret", "ok");
                    CameraBarcodeWaitingActivity.this.setResult(-1, intent);
                    CameraBarcodeWaitingActivity.this.finish();
                }
            });
            return;
        }
        MobclickAgent.a((Context) this, StatUtil.b, "BarcodeConnectionFail");
        this.h.setImageResource(R.drawable.kuailian_failed_icon);
        this.k.setVisibility(0);
        this.l.setVisibility(8);
        this.n.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("ret", "cancel");
                CameraBarcodeWaitingActivity.this.setResult(-1, intent);
                CameraBarcodeWaitingActivity.this.finish();
            }
        });
        this.m.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("ret", "retry");
                CameraBarcodeWaitingActivity.this.setResult(-1, intent);
                CameraBarcodeWaitingActivity.this.finish();
            }
        });
    }
}
