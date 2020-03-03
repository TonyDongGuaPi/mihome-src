package com.xiaomi.smarthome.family;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.ToastUtil;

@Deprecated
public class FamilyAddMemberErrorActivity extends BaseActivity {
    public static final String DOWNLOAD_URL = "https://home.mi.com/download";
    private static final String g = "SENT_SMS_ACTION";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public String f15640a;
    private View b;
    private View c;
    private TextView d;
    private ImageView e;
    /* access modifiers changed from: private */
    public String f;
    private BroadcastReceiver h = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (getResultCode() == -1) {
                FamilyAddMemberErrorActivity.this.mHandler.post(new Runnable() {
                    public void run() {
                        FamilyAddMemberErrorActivity.this.c();
                    }
                });
            } else {
                ToastUtil.a((Context) FamilyAddMemberErrorActivity.this, (int) R.string.family_invite_msg_send_fail);
            }
        }
    };
    private View.OnClickListener i = new View.OnClickListener() {
        public void onClick(final View view) {
            if (view.isEnabled()) {
                view.setEnabled(false);
                FamilyAddMemberErrorActivity.this.mHandler.postDelayed(new Runnable() {
                    public void run() {
                        view.setEnabled(true);
                    }
                }, 400);
                int id = view.getId();
                if (id == R.id.btn_add_complete) {
                    FamilyAddMemberErrorActivity.this.finish();
                } else if (id == R.id.btn_continue_add) {
                    FamilyAddMemberErrorActivity.this.finish();
                } else if (id == R.id.tv_invite_family_member) {
                    if (FamilyAddMemberErrorActivity.this.f == null) {
                        String unused = FamilyAddMemberErrorActivity.this.f = "";
                    }
                    FamilyAddMemberErrorActivity.this.sendSMS(FamilyAddMemberErrorActivity.this.f15640a, String.format(FamilyAddMemberErrorActivity.this.getString(R.string.family_invite_msg_detail), new Object[]{FamilyAddMemberErrorActivity.this.f, "https://home.mi.com/download"}));
                }
            }
        }
    };

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.family_add_member_error);
        Intent intent = getIntent();
        this.f15640a = intent.getStringExtra("userId");
        if (this.f15640a == null || this.f15640a.isEmpty()) {
            finish();
        }
        this.f = intent.getStringExtra(FamilyCreateEditActivity.FAMILY_CURRENT_USER_NAME);
        a();
        b();
        d();
    }

    private void a() {
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        if (textView != null) {
            textView.setText(R.string.family_add_member);
        }
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                FamilyAddMemberErrorActivity.this.finish();
            }
        });
    }

    private void b() {
        ((TextView) findViewById(R.id.user_name)).setText(this.f15640a);
        this.b = findViewById(R.id.btn_continue_container);
        this.c = findViewById(R.id.tv_invite_family_member);
        this.d = (TextView) findViewById(R.id.tv_handle_detail);
        this.e = (ImageView) findViewById(R.id.icon_confirm);
        this.c.setOnClickListener(this.i);
        findViewById(R.id.btn_continue_add).setOnClickListener(this.i);
        findViewById(R.id.btn_add_complete).setOnClickListener(this.i);
    }

    public void onResume() {
        super.onResume();
        registerReceiver(this.h, new IntentFilter(g));
    }

    public void onPause() {
        super.onPause();
        unregisterReceiver(this.h);
    }

    /* access modifiers changed from: private */
    public void c() {
        this.e.setVisibility(0);
        this.d.setText(R.string.family_invite_msg_send_success);
        this.b.setVisibility(0);
        this.c.setVisibility(8);
    }

    private void d() {
        this.e.setVisibility(8);
        this.d.setText(R.string.family_invite_msg);
        this.b.setVisibility(8);
        this.c.setVisibility(0);
    }

    public void sendSMS(String str, String str2) {
        PendingIntent broadcast = PendingIntent.getBroadcast(this, 0, new Intent(g), 0);
        SmsManager smsManager = SmsManager.getDefault();
        for (String sendTextMessage : smsManager.divideMessage(str2)) {
            smsManager.sendTextMessage(str, (String) null, sendTextMessage, broadcast, (PendingIntent) null);
        }
    }
}
