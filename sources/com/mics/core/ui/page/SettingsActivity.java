package com.mics.core.ui.page;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.mics.R;
import com.mics.constant.API;
import com.mics.core.MiCS;
import com.mics.core.business.ChatManager;
import com.mics.core.data.business.ChatList;
import com.mics.core.data.business.ChatParams;
import com.mics.network.GoCallback;
import com.mics.network.GoFailure;
import com.mics.util.GsonUtil;
import com.mics.util.KeyboardUtils;
import com.mics.widget.reminder.MessageReminder;
import java.lang.reflect.Type;
import java.util.Timer;
import java.util.TimerTask;

public class SettingsActivity extends AppCompatActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public TextView f7739a;
    /* access modifiers changed from: private */
    public int b = 5;
    private Timer c;
    private TextView d;
    private TextView e;
    private TextView f;
    /* access modifiers changed from: private */
    public EditText g;

    static /* synthetic */ int access$010(SettingsActivity settingsActivity) {
        int i = settingsActivity.b;
        settingsActivity.b = i - 1;
        return i;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.mics_activity_settings);
        if (!MiCS.b()) {
            this.f7739a = (TextView) findViewById(R.id.tv_hint);
            this.f7739a.setVisibility(0);
            this.c = new Timer();
            this.c.scheduleAtFixedRate(new TimerTask() {
                public void run() {
                    SettingsActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            if (SettingsActivity.this.b <= 0) {
                                SettingsActivity.this.b();
                                SettingsActivity.this.finish();
                            }
                            SettingsActivity.this.f7739a.setText(Html.fromHtml("<font>请先初始化MiCS ┑(￣Д ￣)┍</font><br/><br/><font color='#1890ff'>" + SettingsActivity.this.b + "s</font><font> 后页面自动关闭</font>"));
                            SettingsActivity.access$010(SettingsActivity.this);
                        }
                    });
                }
            }, 0, 1000);
        }
        a();
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            try {
                Drawable drawable = getResources().getDrawable(R.drawable.mics_back_material);
                drawable.setColorFilter(getResources().getColor(R.color.micsColorAccent), PorterDuff.Mode.SRC_ATOP);
                getSupportActionBar().setHomeAsUpIndicator(drawable);
                ActionBar supportActionBar = getSupportActionBar();
                supportActionBar.setTitle((CharSequence) Html.fromHtml("<font color='" + "#4290f7" + "'>" + getResources().getString(R.string.mics_sdk_name) + "</font>"));
            } catch (Exception unused) {
                getSupportActionBar().setTitle((CharSequence) getResources().getString(R.string.mics_sdk_name));
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        finish();
        return true;
    }

    private void a() {
        ChatParams chatParams;
        this.d = (TextView) findViewById(R.id.tv_host_desc);
        ((TextView) findViewById(R.id.tv_host_title)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, SettingHostActivity.class);
                intent.putExtra("setting_type", 1);
                SettingsActivity.this.startActivity(intent);
            }
        });
        this.e = (TextView) findViewById(R.id.tv_ws_desc);
        ((TextView) findViewById(R.id.tv_ws_title)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, SettingHostActivity.class);
                intent.putExtra("setting_type", 2);
                SettingsActivity.this.startActivity(intent);
            }
        });
        ((TextView) findViewById(R.id.tv_sdk_config)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SettingsActivity.this.startActivity(new Intent(SettingsActivity.this, SettingSDKActivity.class));
            }
        });
        ((TextView) findViewById(R.id.tv_sdk_user_config)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MessageReminder.a().a(false, MiCS.a().o(), (String) null, "content", "664");
                Toast.makeText(view.getContext(), "暂未实现", 0).show();
            }
        });
        this.f = (TextView) findViewById(R.id.tv_sdk_config_cur_user);
        ((TextView) findViewById(R.id.tv_chat_list)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChatManager.a().a((GoCallback<ChatList>) new GoCallback<ChatList>() {
                    public void a(String str, ChatList chatList) {
                    }

                    public void a(String str, GoFailure goFailure) {
                    }
                });
                Toast.makeText(view.getContext(), "暂未实现", 0).show();
            }
        });
        Switch switchR = (Switch) findViewById(R.id.switch_vibrate);
        switchR.setChecked(MiCS.a().c());
        switchR.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                MiCS.a().a(z);
            }
        });
        this.g = (EditText) findViewById(R.id.et_merchant);
        final String stringExtra = getIntent().getStringExtra("data");
        if (!TextUtils.isEmpty(stringExtra) && (chatParams = (ChatParams) GsonUtil.a(stringExtra, (Type) ChatParams.class)) != null) {
            this.g.setText(chatParams.getMerchantId());
        }
        ((TextView) findViewById(R.id.tv_merchant_start)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Editable text = SettingsActivity.this.g.getText();
                if (!TextUtils.isEmpty(text)) {
                    ChatParams chatParams = (ChatParams) GsonUtil.a(stringExtra, ChatParams.class);
                    if (chatParams == null) {
                        chatParams = new ChatParams();
                    }
                    chatParams.setMerchantId(text.toString());
                    MiCS.a((Context) SettingsActivity.this, chatParams);
                }
                KeyboardUtils.a(view);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        String t = API.t();
        String u = API.u();
        this.d.setText(u + "  |  " + t);
        String x = API.x();
        String y = API.y();
        this.e.setText(y + "  |  " + x);
        this.d.setFocusable(true);
        this.d.setFocusableInTouchMode(true);
        this.d.requestFocus();
        if (MiCS.b()) {
            this.f.setText("当前用户（" + MiCS.a().n() + "）");
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        b();
    }

    /* access modifiers changed from: private */
    public void b() {
        if (this.c != null) {
            this.c.cancel();
            this.c = null;
        }
    }
}
