package com.xiaomi.smarthome.miio.miband;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.api.MiBandServerApi;
import com.xiaomi.smarthome.framework.page.BaseWhiteActivity;
import com.xiaomi.smarthome.miio.miband.utils.AccessManager;
import java.util.Calendar;
import java.util.Date;
import org.json.JSONObject;

public class MiBandMainOldActivity extends BaseWhiteActivity {
    public static final int GET_OAUTH_FAILED = 4097;
    public static final int GET_OAUTH_SUCCESS = 4096;
    public static final String MAC = "mac";
    public static final String TAG = "mi band";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public TextView f19427a;
    private Button b;
    private Button c;
    /* access modifiers changed from: private */
    public String d;
    /* access modifiers changed from: private */
    public String e;
    private View.OnClickListener f = new View.OnClickListener() {
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.btn_get_info:
                    MiBandMainOldActivity.this.b();
                    return;
                default:
                    return;
            }
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.miband_main_layout);
        d();
        c();
        e();
        a();
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 4096:
                b();
                return;
            case 4097:
                Toast.makeText(this, R.string.miband_get_access_failed, 0).show();
                return;
            default:
                return;
        }
    }

    private void a() {
        this.mHandler.post(new Runnable() {
            public void run() {
                AccessManager.c().a((Activity) MiBandMainOldActivity.this, (AsyncResponseCallback<AccessManager.AccessInfo>) new AsyncResponseCallback<AccessManager.AccessInfo>() {
                    public void a(AccessManager.AccessInfo accessInfo) {
                        Message obtainMessage = MiBandMainOldActivity.this.mHandler.obtainMessage(4096);
                        String unused = MiBandMainOldActivity.this.d = accessInfo.b;
                        String unused2 = MiBandMainOldActivity.this.e = accessInfo.c;
                        MiBandMainOldActivity.this.mHandler.sendMessage(obtainMessage);
                    }

                    public void a(int i) {
                        MiBandMainOldActivity.this.mHandler.sendMessage(MiBandMainOldActivity.this.mHandler.obtainMessage(4097));
                    }

                    public void a(int i, Object obj) {
                        MiBandMainOldActivity.this.mHandler.sendMessage(MiBandMainOldActivity.this.mHandler.obtainMessage(4097));
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void b() {
        Calendar instance = Calendar.getInstance();
        Date time = instance.getTime();
        instance.add(6, -7);
        MiBandServerApi.a().a(instance.getTime(), time, this.d, this.e, new AsyncResponseCallback<JSONObject>() {
            public void a(final JSONObject jSONObject) {
                MiBandMainOldActivity.this.mHandler.post(new Runnable() {
                    public void run() {
                        MiBandMainOldActivity.this.f19427a.setText(jSONObject.toString());
                    }
                });
            }

            public void a(int i) {
                Toast.makeText(MiBandMainOldActivity.this, R.string.miband_get_info_failed, 0).show();
            }

            public void a(int i, Object obj) {
                Toast.makeText(MiBandMainOldActivity.this, R.string.miband_get_info_failed, 0).show();
            }
        });
    }

    private void c() {
        ((TextView) findViewById(R.id.module_a_3_return_transparent_title)).setText(R.string.miband_title);
    }

    private void d() {
        this.f19427a = (TextView) findViewById(R.id.tv_info);
        this.b = (Button) findViewById(R.id.btn_get_info);
        this.c = (Button) findViewById(R.id.btn_get_oauth);
    }

    private void e() {
        if (this.b != null) {
            this.b.setOnClickListener(this.f);
        }
        if (this.c != null) {
            this.c.setVisibility(8);
            this.c.setOnClickListener(this.f);
        }
    }
}
