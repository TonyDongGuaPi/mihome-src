package com.xiaomi.smarthome.light.group;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.spec.instance.SpecProperty;
import com.xiaomi.smarthome.device.api.spec.instance.SpecService;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.initdevice.InitDeviceRoomActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.newui.card.MiotSpecCardManager;
import com.xiaomi.smarthome.newui.card.spec.SpecUtils;
import com.xiaomi.smarthome.stat.STAT;
import org.json.JSONObject;

public class LightGroupTestActivity extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f19171a = "LightGroupTestActivity";
    /* access modifiers changed from: private */
    public String b;
    /* access modifiers changed from: private */
    public Device c;
    /* access modifiers changed from: private */
    public boolean d = true;
    @BindView(2131430969)
    ImageView moduleA3ReturnBtn;
    @BindView(2131430975)
    TextView moduleA3ReturnTitle;
    @BindView(2131430980)
    ImageView moduleA3RightBtn;
    @BindView(2131430983)
    TextView moduleA3RightTextBtn;
    @BindView(2131432436)
    View shortcut;
    @BindView(2131433389)
    TextView tvModify;
    @BindView(2131433398)
    TextView tvNext;

    public static void open(Activity activity, String str, int i) {
        Intent intent = new Intent();
        intent.setClass(activity, LightGroupTestActivity.class);
        intent.putExtra("did", str);
        activity.startActivityForResult(intent, i);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_light_group_test);
        this.b = getIntent().getStringExtra("did");
        if (TextUtils.isEmpty(this.b)) {
            a(0);
        }
        this.c = SmartHomeDeviceManager.a().b(this.b);
        if (this.c == null || !LightGroupManager.a().b(this.c)) {
            a(0);
        }
        ButterKnife.bind((Activity) this);
        a();
        STAT.c.o(this.c.model);
    }

    private void a() {
        this.moduleA3RightBtn.setVisibility(8);
        this.moduleA3RightTextBtn.setVisibility(8);
        this.moduleA3ReturnTitle.setText(R.string.light_group_creat_confirm);
        this.moduleA3ReturnBtn.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                LightGroupTestActivity.this.b(view);
            }
        });
        this.tvModify.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                LightGroupTestActivity.this.a(view);
            }
        });
        this.tvNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(LightGroupTestActivity.this, InitDeviceRoomActivity.class);
                intent.putExtra("device_id", LightGroupTestActivity.this.b);
                LightGroupTestActivity.this.startActivity(intent);
                LightGroupTestActivity.this.a(-1);
                STAT.d.bh(LightGroupTestActivity.this.c.model);
            }
        });
        this.shortcut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                try {
                    LightGroupTestActivity.this.shortcut.setEnabled(false);
                    if (LightGroupTestActivity.this.d) {
                        STAT.d.be(LightGroupTestActivity.this.c.model);
                    } else {
                        STAT.d.bf(LightGroupTestActivity.this.c.model);
                    }
                    Pair pair = (Pair) SpecUtils.a(MiotSpecCardManager.f().a(LightGroupTestActivity.this.c)).get("p:light:on").get(0);
                    MiotSpecCardManager.f().a(LightGroupTestActivity.this.b, (SpecService) pair.first, (SpecProperty) pair.second, Boolean.valueOf(LightGroupTestActivity.this.d), new AsyncCallback<JSONObject, Error>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            String access$400 = LightGroupTestActivity.f19171a;
                            LogUtil.c(access$400, "setDeviceProp onSuccess: " + jSONObject);
                            boolean unused = LightGroupTestActivity.this.d = LightGroupTestActivity.this.d ^ true;
                            LightGroupTestActivity.this.shortcut.setBackgroundResource(LightGroupTestActivity.this.d ? R.drawable.icon_shortcut_off : R.drawable.icon_shortcut_on);
                            LightGroupTestActivity.this.shortcut.setEnabled(true);
                        }

                        public void onFailure(Error error) {
                            String access$400 = LightGroupTestActivity.f19171a;
                            LogUtil.c(access$400, "setDeviceProp onFailure: " + error);
                            LightGroupTestActivity.this.shortcut.setEnabled(true);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    LightGroupTestActivity.this.shortcut.setEnabled(true);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        onBackPressed();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        onBackPressed();
        STAT.d.bg(this.c.model);
    }

    public void onBackPressed() {
        b();
    }

    private void b() {
        new MLAlertDialog.Builder(this).a((int) R.string.light_group_reselect_title).b((int) R.string.light_group_reselect_msg).a((int) R.string.confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                LightGroupTestActivity.this.a(0);
                STAT.d.bi(LightGroupTestActivity.this.c.model);
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                STAT.d.bj(LightGroupTestActivity.this.c.model);
            }
        }).d();
    }

    /* access modifiers changed from: private */
    public void a(int i) {
        setResult(i);
        finish();
    }
}
