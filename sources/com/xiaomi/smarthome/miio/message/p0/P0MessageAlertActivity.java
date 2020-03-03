package com.xiaomi.smarthome.miio.message.p0;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.util.CalendarUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.widget.drwables.TextDrawable;
import com.xiaomi.smarthome.library.common.widget.spans.CenterVerticalSpan;
import com.xiaomi.smarthome.messagecenter.DevicePushMessageManager;
import com.xiaomi.smarthome.messagecenter.ui.MessageCenterActivity;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.message.p0.model.P0Message;
import com.xiaomi.smarthome.miio.message.p0.model.P0MessageList;
import com.xiaomi.smarthome.multikey.PowerMultikeyApi;
import com.xiaomi.smarthome.printer.DeviceImageApi;
import com.xiaomi.smarthome.scene.push.ScenePushCallback;
import com.xiaomi.smarthome.scene.push.ScenePushListener;
import com.xiaomi.smarthome.stat.STAT;
import java.util.HashSet;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class P0MessageAlertActivity extends BaseActivity {
    public static final String P0_MSG_DATA = "p0_msg_data";
    private static final float f = 0.8f;

    /* renamed from: a  reason: collision with root package name */
    private P0MessageList f13615a;
    private TextView b;
    private TextView c;
    /* access modifiers changed from: private */
    public SimpleDraweeView d;
    private View e;

    public static void startActivity(Context context, P0MessageList p0MessageList) {
        if (p0MessageList != null && p0MessageList.a() != null && !p0MessageList.a().isEmpty()) {
            Intent intent = new Intent(context, P0MessageAlertActivity.class);
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            intent.putExtra(P0_MSG_DATA, p0MessageList);
            context.startActivity(intent);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.f13615a = (P0MessageList) intent.getParcelableExtra(P0_MSG_DATA);
        if (this.f13615a == null || this.f13615a.a() == null || this.f13615a.a().isEmpty()) {
            finish();
            return;
        }
        setContentView(R.layout.activity_p0_message_alert_layout);
        a();
    }

    private void a() {
        this.b = (TextView) findViewById(R.id.i_know_tips);
        this.c = (TextView) findViewById(R.id.open_details);
        this.d = (SimpleDraweeView) findViewById(R.id.icon);
        ViewStub viewStub = (ViewStub) findViewById(R.id.single_vs);
        ViewStub viewStub2 = (ViewStub) findViewById(R.id.triple_vs);
        TitleBarUtil.b((Activity) this);
        if (this.f13615a.a().size() > 1) {
            this.e = viewStub2.inflate();
            c();
            return;
        }
        this.e = viewStub.inflate();
        b();
    }

    private void b() {
        if (this.f13615a == null || this.f13615a.a() == null || this.f13615a.a().isEmpty()) {
            finish();
            return;
        }
        P0Message p0Message = this.f13615a.a().get(0);
        if (p0Message == null) {
            finish();
            return;
        }
        final Device b2 = SmartHomeDeviceManager.a().b(p0Message.a());
        if (b2 == null) {
            finish();
            return;
        }
        STAT.c.j(b2.model, this.f13615a.a().size());
        Room p = HomeManager.a().p(b2.did);
        Home q = p == null ? HomeManager.a().q(b2.did) : HomeManager.a().j(p.f());
        new PowerMultikeyApi().a(b2, (AsyncCallback<DeviceImageApi.DeviceImageEntity, Error>) new AsyncCallback<DeviceImageApi.DeviceImageEntity, Error>() {
            /* renamed from: a */
            public void onSuccess(DeviceImageApi.DeviceImageEntity deviceImageEntity) {
                P0MessageAlertActivity.this.d.setImageURI(deviceImageEntity.f21157a);
            }

            public void onFailure(Error error) {
                DeviceFactory.b(b2.model, P0MessageAlertActivity.this.d);
            }
        });
        ((TextView) this.e.findViewById(R.id.device_name)).setText(p0Message.d());
        a(b2, (TextView) this.e.findViewById(R.id.home_room_name), q, p);
        a((TextView) this.e.findViewById(R.id.date_time), p0Message.e());
        this.c.setOnClickListener(new View.OnClickListener(b2, p0Message) {
            private final /* synthetic */ Device f$1;
            private final /* synthetic */ P0Message f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void onClick(View view) {
                P0MessageAlertActivity.this.a(this.f$1, this.f$2, view);
            }
        });
        this.b.setOnClickListener(new View.OnClickListener(b2) {
            private final /* synthetic */ Device f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                P0MessageAlertActivity.this.a(this.f$1, view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Device device, P0Message p0Message, View view) {
        a(device, p0Message);
        STAT.d.bo(device.model);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Device device, View view) {
        finish();
        STAT.d.bp(device.model);
    }

    private void a(Device device, P0Message p0Message) {
        if (p0Message != null && p0Message.f() != null && !TextUtils.isEmpty(p0Message.f().params)) {
            try {
                JSONObject jSONObject = new JSONObject(p0Message.f().params);
                if (!jSONObject.isNull("body")) {
                    jSONObject = jSONObject.optJSONObject("body");
                }
                if (jSONObject != null) {
                    PluginRecord d2 = CoreApi.a().d(device.model);
                    final String jSONObject2 = jSONObject.toString();
                    if (d2 == null) {
                        ScenePushListener.a("", jSONObject2, false, (HashSet<ScenePushCallback>) null);
                        finish();
                    } else if (d2.k() || d2.l()) {
                        ScenePushListener.a("", jSONObject2, false, (HashSet<ScenePushCallback>) null);
                        SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                            public void run() {
                                P0MessageAlertActivity.this.finish();
                            }
                        }, 500);
                    } else {
                        DevicePushMessageManager.a(this, jSONObject2, d2, device, new PluginApi.SendMessageCallback() {
                            public void onDownInfoSuccess(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                                ScenePushListener.a("", jSONObject2, false, (HashSet<ScenePushCallback>) null);
                                SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                                    public void run() {
                                        P0MessageAlertActivity.this.finish();
                                    }
                                }, 300);
                            }
                        });
                    }
                } else {
                    finish();
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
                finish();
            }
        }
    }

    private void a(Device device, TextView textView, Home home, Room room) {
        if (home != null) {
            String k = home.k();
            String string = room == null ? getResources().getString(R.string.default_room) : room.e();
            SpannableString spannableString = new SpannableString(k + " | " + string + " | " + device.name);
            spannableString.setSpan(new RelativeSizeSpan(0.8f), k.length(), k.length() + " | ".length(), 0);
            spannableString.setSpan(new CenterVerticalSpan(DisplayUtils.a(5.0f)), k.length(), k.length() + " | ".length(), 33);
            RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(0.8f);
            int length = (k + " | " + string).length();
            spannableString.setSpan(relativeSizeSpan, length, (k + " | " + string + " | ").length(), 0);
            CenterVerticalSpan centerVerticalSpan = new CenterVerticalSpan(DisplayUtils.a(5.0f));
            int length2 = (k + " | " + string).length();
            spannableString.setSpan(centerVerticalSpan, length2, (k + " | " + string + " | ").length(), 33);
            textView.setText(spannableString);
            return;
        }
        String string2 = getResources().getString(R.string.share_title);
        SpannableString spannableString2 = new SpannableString(string2 + " | " + device.name);
        spannableString2.setSpan(new RelativeSizeSpan(0.8f), string2.length(), string2.length() + " | ".length(), 0);
        spannableString2.setSpan(new CenterVerticalSpan(DisplayUtils.a(5.0f)), string2.length(), string2.length() + " | ".length(), 33);
        textView.setText(spannableString2);
    }

    private void a(TextView textView, long j) {
        String str;
        String[] e2 = CalendarUtils.e(j);
        if (e2 != null && e2.length != 0) {
            String str2 = e2[0];
            if (e2.length < 2) {
                str = e2[0];
            } else {
                str = e2[0] + " | " + e2[1];
            }
            SpannableString spannableString = new SpannableString(str);
            if (e2.length >= 2) {
                spannableString.setSpan(new RelativeSizeSpan(0.8f), str2.length(), str2.length() + " | ".length(), 0);
                spannableString.setSpan(new CenterVerticalSpan(DisplayUtils.a(4.0f)), str2.length(), str2.length() + " | ".length(), 33);
            }
            textView.setText(spannableString);
        }
    }

    private void c() {
        String str;
        Device b2;
        if (this.f13615a == null || this.f13615a.a() == null || this.f13615a.a().isEmpty()) {
            finish();
            return;
        }
        this.d.setImageResource(R.drawable.p0_msg_alert_icon);
        LayoutInflater from = LayoutInflater.from(getApplicationContext());
        ViewGroup viewGroup = (ViewGroup) this.e;
        List<P0Message> a2 = this.f13615a.a();
        int min = Math.min(a2.size(), 3);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < min && i < 3) {
            P0Message p0Message = a2.get(i);
            if (!(p0Message == null || (b2 = SmartHomeDeviceManager.a().b(p0Message.a())) == null)) {
                sb.append("/");
                sb.append(b2.model);
                View inflate = from.inflate(R.layout.p0_message_alert_triple_item, viewGroup, false);
                Room p = HomeManager.a().p(b2.did);
                Home q = p == null ? HomeManager.a().q(b2.did) : HomeManager.a().j(p.f());
                ((TextView) inflate.findViewById(R.id.device_name)).setText(p0Message.d());
                a(b2, (TextView) inflate.findViewById(R.id.home_room_name), q, p);
                a((TextView) inflate.findViewById(R.id.date_time), p0Message.e());
                if (i == min - 1) {
                    inflate.findViewById(R.id.divide_line).setVisibility(8);
                }
                viewGroup.addView(inflate);
            }
            i++;
        }
        int b3 = this.f13615a.b();
        if (b3 > 3) {
            if (b3 < 10) {
                str = b3 + "";
            } else {
                str = "9+";
            }
            TextDrawable textDrawable = new TextDrawable(str);
            textDrawable.b(-65536);
            textDrawable.setBounds(0, 0, DisplayUtils.a(15.0f), DisplayUtils.a(15.0f));
            this.c.setCompoundDrawables((Drawable) null, (Drawable) null, textDrawable, (Drawable) null);
        }
        STAT.c.j(sb.toString(), min);
        this.c.setOnClickListener(new View.OnClickListener(sb) {
            private final /* synthetic */ StringBuilder f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                P0MessageAlertActivity.this.b(this.f$1, view);
            }
        });
        this.b.setOnClickListener(new View.OnClickListener(sb) {
            private final /* synthetic */ StringBuilder f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(View view) {
                P0MessageAlertActivity.this.a(this.f$1, view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(StringBuilder sb, View view) {
        Intent intent = new Intent();
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        intent.setClass(this, MessageCenterActivity.class);
        startActivity(intent);
        finish();
        STAT.d.bo(sb.toString());
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(StringBuilder sb, View view) {
        finish();
        STAT.d.bp(sb.toString());
    }
}
