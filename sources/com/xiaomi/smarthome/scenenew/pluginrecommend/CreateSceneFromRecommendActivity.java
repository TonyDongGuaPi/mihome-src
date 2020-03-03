package com.xiaomi.smarthome.scenenew.pluginrecommend;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.model.Tags;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.scene.SceneCreateTimeEdit2Activity;
import com.xiaomi.smarthome.scene.SmartHomeSceneUtility;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scenenew.manager.PluginRecommendSceneManager;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.stat.StatClick;
import com.xiaomi.smarthome.stat.StatPopUp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CreateSceneFromRecommendActivity extends BaseActivity implements View.OnClickListener, RecommendSceneCreator.SelectionBuilder {
    public static final String EXTRA_DID = "did";
    public static final String EXTRA_NEED_CHOOSE_DETAIL = "need_choose_detail";
    public static final String EXTRA_SRID = "sr_id";
    public static final String EXTRA_USID = "us_id";
    public static final int REQUEST_ACTION_DEV = 1003;
    public static final int REQUEST_CODE_CHOOSE_FENCING = 104;
    public static final int REQUEST_CODE_NUM_PICKER = 103;
    public static final int REQUEST_CONDITION_DEV = 1002;
    /* access modifiers changed from: private */
    public boolean A = false;
    private boolean B = false;
    private boolean C = false;
    private SceneApi.Action D = null;
    private SceneApi.Condition E = null;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public RecommendUI f22003a;
    private SimpleDraweeView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private View f;
    private TextView g;
    private TextView h;
    private View i;
    private TextView j;
    private TextView k;
    private TextView l;
    private View m;
    private View n;
    /* access modifiers changed from: private */
    public XQProgressDialog o;
    private View p;
    private View q;
    private SwitchButton r;
    private View s;
    /* access modifiers changed from: private */
    public SceneApi.SmartHomeScene t;
    private TextView u;
    /* access modifiers changed from: private */
    public int v;
    /* access modifiers changed from: private */
    public Device w = null;
    /* access modifiers changed from: private */
    public Home x = null;
    /* access modifiers changed from: private */
    public boolean y = false;
    /* access modifiers changed from: private */
    public boolean z = false;

    public List<SceneApi.Action> getAdditionActions() {
        return null;
    }

    public List<SceneApi.Condition> getAdditionConditions() {
        return null;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.plugin_rec_scene_main_activity);
        this.v = getIntent().getIntExtra("sr_id", -1);
        String stringExtra = getIntent().getStringExtra("us_id");
        String stringExtra2 = getIntent().getStringExtra("did");
        if (!TextUtils.isEmpty(stringExtra2)) {
            this.w = SmartHomeDeviceManager.a().b(stringExtra2);
            this.x = HomeManager.a().q(stringExtra2);
        }
        if (this.x == null) {
            this.x = HomeManager.a().m();
        }
        if (this.v <= 0 || this.w == null || this.x == null) {
            finish();
            return;
        }
        StatPopUp statPopUp = STAT.e;
        String str = this.w.model;
        statPopUp.b(str, this.v + "");
        this.j = (TextView) findViewById(R.id.module_a_3_return_title);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(this);
        if (RecommendSceneCreator.a().f21963a == null || TextUtils.isEmpty(RecommendSceneCreator.a().f21963a.entryDesc)) {
            this.j.setText(R.string.setting);
        } else {
            this.j.setText(RecommendSceneCreator.a().f21963a.intro);
        }
        if (RecommendSceneCreator.a().f21963a == null || TextUtils.isEmpty(RecommendSceneCreator.a().f21963a.intro)) {
            this.j.setText(R.string.setting);
        } else {
            this.j.setText(RecommendSceneCreator.a().f21963a.intro);
        }
        this.b = (SimpleDraweeView) findViewById(R.id.recommend_sdv);
        this.f = findViewById(R.id.listitem_condition_select);
        this.p = findViewById(R.id.listitem_condition_detail_select);
        this.q = findViewById(R.id.icon_go_detail);
        this.c = (TextView) findViewById(R.id.tv_condition_device_name);
        this.k = (TextView) findViewById(R.id.tv_condition_select);
        this.d = (TextView) findViewById(R.id.tv_condition_detail_select);
        this.e = (TextView) findViewById(R.id.tv_condition_detail_name);
        this.m = findViewById(R.id.listitem_delay_payload);
        this.r = (SwitchButton) findViewById(R.id.open_check);
        this.s = findViewById(R.id.divider);
        this.n = findViewById(R.id.listitem_delay_minute);
        this.g = (TextView) findViewById(R.id.tv_delay_minute);
        this.h = (TextView) findViewById(R.id.tv_timespan);
        this.i = findViewById(R.id.listitem_timespan);
        this.l = (TextView) findViewById(R.id.tv_enable);
        this.p = findViewById(R.id.listitem_condition_detail_select);
        this.u = (TextView) findViewById(R.id.tv_not_connect_ble);
        c(stringExtra);
        if (RecommendSceneCreator.b(this.w.did)) {
            a(this.w.did);
        }
    }

    /* access modifiers changed from: private */
    public void a(final boolean z2, boolean z3) {
        String string = getString(R.string.device_rec_condition_no_ble);
        int indexOf = string.indexOf("#start#");
        int indexOf2 = string.indexOf("#end#") - "#start#".length();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(string.replace("#start#", "").replace("#end#", ""));
        AnonymousClass1 r7 = new ClickableSpan() {
            public void onClick(View view) {
                List<Device> a2 = RecommendSceneCreator.a(CreateSceneFromRecommendActivity.this.x.j());
                if (a2 == null || a2.size() == 0) {
                    STAT.e.j(CreateSceneFromRecommendActivity.this.w.model, RecommendSceneCreator.a().f21963a.sr_id);
                    Intent intent = new Intent(CreateSceneFromRecommendActivity.this, ActionConditionDeviceSelectActivity.class);
                    intent.putExtra("home_id", CreateSceneFromRecommendActivity.this.x.j());
                    intent.putExtra("is_only_ble", z2);
                    intent.putExtra("only_buy_guide", true);
                    CreateSceneFromRecommendActivity.this.startActivity(intent);
                    return;
                }
                STAT.e.i(CreateSceneFromRecommendActivity.this.w.model, RecommendSceneCreator.a().f21963a.sr_id);
                Intent intent2 = new Intent(CreateSceneFromRecommendActivity.this, LockDissConnectBleActivity.class);
                intent2.putExtra("home_id", CreateSceneFromRecommendActivity.this.x.j());
                intent2.putExtra("sr_id", RecommendSceneCreator.a().f21963a.sr_id);
                CreateSceneFromRecommendActivity.this.startActivity(intent2);
            }

            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(CreateSceneFromRecommendActivity.this.getResources().getColor(R.color.class_text_33));
                textPaint.setUnderlineText(true);
            }
        };
        if (indexOf >= 0 && indexOf2 > 0) {
            try {
                spannableStringBuilder.setSpan(r7, indexOf, indexOf2, 33);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        this.u.setText(spannableStringBuilder);
        this.u.setHighlightColor(0);
        this.u.setMovementMethod(LinkMovementMethod.getInstance());
        this.u.setVisibility(0);
    }

    private void a(final String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        PluginRecommendSceneManager.a().a((List<String>) arrayList, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject != null) {
                    JSONArray optJSONArray = jSONObject.optJSONArray(str);
                    if (optJSONArray == null || optJSONArray.length() == 0) {
                        CreateSceneFromRecommendActivity.this.a(true, false);
                    }
                }
            }
        });
    }

    private boolean b(String str) {
        PluginRecord d2 = CoreApi.a().d(str);
        return (d2 == null || d2.c() == null || d2.c().t() != Device.PID_SUBDEVICE) ? false : true;
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityResult(int r4, int r5, android.content.Intent r6) {
        /*
            r3 = this;
            r0 = -1
            if (r5 != r0) goto L_0x0118
            r3.c()
            r5 = 1003(0x3eb, float:1.406E-42)
            r0 = 0
            if (r4 != r5) goto L_0x0041
            com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator r4 = com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator.a()
            com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$RecommendSceneItem r4 = r4.f21963a
            java.util.Map<java.lang.Integer, com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$CommonSceneAction> r4 = r4.mCommonActions
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r5 = r3.t
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Action> r5 = r5.k
            java.lang.Object r5 = r5.get(r0)
            com.xiaomi.smarthome.scene.api.SceneApi$Action r5 = (com.xiaomi.smarthome.scene.api.SceneApi.Action) r5
            int r5 = r5.f
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Object r4 = r4.get(r5)
            com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$CommonSceneAction r4 = (com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo.CommonSceneAction) r4
            com.xiaomi.smarthome.scenenew.pluginrecommend.RecommendUI r5 = r3.f22003a
            boolean r4 = com.xiaomi.smarthome.scenenew.pluginrecommend.RecommendUI.b((com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo.CommonSceneAction) r4)
            if (r4 == 0) goto L_0x0036
            r3.a()
            goto L_0x0113
        L_0x0036:
            boolean r4 = r3.b()
            if (r4 == 0) goto L_0x0113
            r3.d()
            goto L_0x0113
        L_0x0041:
            r5 = 1002(0x3ea, float:1.404E-42)
            if (r4 != r5) goto L_0x007d
            com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator r4 = com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator.a()
            com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$RecommendSceneItem r4 = r4.f21963a
            java.util.Map<java.lang.Integer, com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$CommonSceneCondition> r4 = r4.mCommonConditions
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r5 = r3.t
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r5 = r5.l
            java.lang.Object r5 = r5.get(r0)
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r5 = (com.xiaomi.smarthome.scene.api.SceneApi.Condition) r5
            com.xiaomi.smarthome.scene.api.SceneApi$ConditionDevice r5 = r5.c
            int r5 = r5.k
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)
            java.lang.Object r4 = r4.get(r5)
            com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$CommonSceneCondition r4 = (com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo.CommonSceneCondition) r4
            com.xiaomi.smarthome.scenenew.pluginrecommend.RecommendUI r5 = r3.f22003a
            boolean r4 = com.xiaomi.smarthome.scenenew.pluginrecommend.RecommendUI.a((com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo.CommonSceneCondition) r4)
            if (r4 == 0) goto L_0x0072
            r3.a()
            goto L_0x0113
        L_0x0072:
            boolean r4 = r3.b()
            if (r4 == 0) goto L_0x0113
            r3.d()
            goto L_0x0113
        L_0x007d:
            r5 = 103(0x67, float:1.44E-43)
            if (r4 == r5) goto L_0x0091
            r5 = 104(0x68, float:1.46E-43)
            if (r4 != r5) goto L_0x0086
            goto L_0x0091
        L_0x0086:
            boolean r4 = r3.b()
            if (r4 == 0) goto L_0x0113
            r3.d()
            goto L_0x0113
        L_0x0091:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r4 = r3.t
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r4 = r4.l
            java.lang.Object r4 = r4.get(r0)
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r4 = (com.xiaomi.smarthome.scene.api.SceneApi.Condition) r4
            com.xiaomi.smarthome.scene.api.SceneApi$ConditionDevice r4 = r4.c
            com.xiaomi.smarthome.scene.api.SceneApi$ConditionDeviceCommon r4 = (com.xiaomi.smarthome.scene.api.SceneApi.ConditionDeviceCommon) r4
            if (r6 == 0) goto L_0x00b1
            java.lang.String r5 = "key_name"
            boolean r5 = r6.hasExtra(r5)
            if (r5 == 0) goto L_0x00b1
            java.lang.String r5 = "key_name"
            java.lang.String r5 = r6.getStringExtra(r5)
            r4.b = r5
        L_0x00b1:
            if (r6 == 0) goto L_0x00fb
            java.lang.String r5 = "value"
            boolean r5 = r6.hasExtra(r5)
            if (r5 == 0) goto L_0x00fb
            java.lang.String r5 = "value"
            java.lang.String r5 = r6.getStringExtra(r5)
            if (r5 == 0) goto L_0x00ee
            r5 = 1
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00d2 }
            java.lang.String r2 = "value"
            java.lang.String r2 = r6.getStringExtra(r2)     // Catch:{ JSONException -> 0x00d2 }
            r1.<init>(r2)     // Catch:{ JSONException -> 0x00d2 }
            r4.l = r1     // Catch:{ JSONException -> 0x00d2 }
            r0 = 1
        L_0x00d2:
            if (r0 != 0) goto L_0x00e2
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ JSONException -> 0x00e2 }
            java.lang.String r2 = "value"
            java.lang.String r2 = r6.getStringExtra(r2)     // Catch:{ JSONException -> 0x00e2 }
            r1.<init>(r2)     // Catch:{ JSONException -> 0x00e2 }
            r4.l = r1     // Catch:{ JSONException -> 0x00e2 }
            goto L_0x00e3
        L_0x00e2:
            r5 = r0
        L_0x00e3:
            if (r5 != 0) goto L_0x0113
            java.lang.String r5 = "value"
            java.lang.String r5 = r6.getStringExtra(r5)
            r4.l = r5
            goto L_0x0113
        L_0x00ee:
            android.os.Bundle r5 = r6.getExtras()
            java.lang.String r6 = "value"
            java.lang.Object r5 = r5.get(r6)
            r4.l = r5
            goto L_0x0113
        L_0x00fb:
            com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator r5 = com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator.a()
            com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$RecommendSceneItem r5 = r5.f21963a
            java.util.Map<java.lang.Integer, com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$CommonSceneCondition> r5 = r5.mCommonConditions
            int r6 = r4.k
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            java.lang.Object r5 = r5.get(r6)
            com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$CommonSceneCondition r5 = (com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo.CommonSceneCondition) r5
            java.lang.Object r5 = r5.mValue
            r4.l = r5
        L_0x0113:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r4 = r3.t
            r3.a((com.xiaomi.smarthome.scene.api.SceneApi.SmartHomeScene) r4)
        L_0x0118:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scenenew.pluginrecommend.CreateSceneFromRecommendActivity.onActivityResult(int, int, android.content.Intent):void");
    }

    private void a() {
        if (this.t != null) {
            if (this.t.k != null && this.t.k.size() > 0) {
                this.D = this.t.k.get(0);
                this.f22003a.a((BaseActivity) this, this.t.k.get(0), (RecommendSceneCreator.OnSelectCallback) new RecommendSceneCreator.OnSelectCallback() {
                    public void a(int i, Intent intent) {
                        CreateSceneFromRecommendActivity.this.a(i, CreateSceneFromRecommendActivity.this.t.k.get(0), intent);
                    }
                });
            }
            if (this.t.l != null && this.t.l.size() > 0) {
                this.E = this.t.l.get(0);
                this.f22003a.a((BaseActivity) this, this.t.l.get(0), (RecommendSceneCreator.OnSelectCallback) new RecommendSceneCreator.OnSelectCallback() {
                    public void a(int i, Intent intent) {
                        CreateSceneFromRecommendActivity.this.a(i, CreateSceneFromRecommendActivity.this.t.l.get(0), intent);
                    }
                });
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(SceneApi.SmartHomeScene smartHomeScene) {
        if (this.t.n) {
            this.l.setText(R.string.light_rec_enable_1);
            this.l.setBackgroundResource(R.drawable.common_flow_tag_item_normal_bg);
            this.l.setTextColor(getResources().getColor(R.color.common_button));
        } else {
            this.l.setText(R.string.light_rec_enable_0);
            this.l.setBackgroundResource(R.drawable.selector_ble_mesh_button);
            this.l.setTextColor(getResources().getColor(R.color.white));
        }
        if (this.t.k == null || this.t.k.size() == 0 || this.t.l == null || this.t.l.size() == 0) {
            this.l.setEnabled(this.t.n);
        } else {
            this.l.setEnabled(true);
        }
        SmartHomeSceneUtility.a(this.t.u, this.h, getString(R.string.scene_exetime_second_day));
        String str = null;
        String c2 = (this.t.l == null || this.t.l.size() <= 0) ? null : this.f22003a.c(this.t.l.get(0));
        if (TextUtils.isEmpty(c2)) {
            if (this.t.k != null && this.t.k.size() > 0) {
                c2 = this.f22003a.c(this.t.k.get(0));
                str = this.f22003a.b(this.t.k.get(0));
            }
            if (!TextUtils.isEmpty(c2)) {
                this.d.setText(c2);
                if (!TextUtils.isEmpty(str)) {
                    this.e.setText(str);
                }
                this.p.setVisibility(0);
                if (this.f22003a.d(this.t, 0)) {
                    this.q.setVisibility(0);
                } else {
                    this.q.setVisibility(8);
                }
            } else {
                this.p.setVisibility(8);
            }
        } else {
            this.d.setText(c2);
            String b2 = this.f22003a.b(this.t.l.get(0));
            if (!TextUtils.isEmpty(b2)) {
                this.e.setText(b2);
            }
            this.p.setVisibility(0);
            if (this.f22003a.e(this.t, 0)) {
                this.q.setVisibility(0);
            } else {
                this.q.setVisibility(8);
            }
        }
        String c3 = this.f22003a.c(smartHomeScene, 0);
        if (TextUtils.isEmpty(c3)) {
            this.t.k.clear();
            c3 = getString(R.string.light_rec_non_condition);
        }
        if (!TextUtils.isEmpty(c3)) {
            this.c.setText(c3);
        } else {
            this.c.setText("");
        }
    }

    private boolean b() {
        if (this.t != null && !TextUtils.isEmpty(this.t.f) && this.t.n) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void c(String str) {
        RecommendSceneCreator.a().c();
        RecommendSceneCreator.a().a((RecommendSceneCreator.SelectionBuilder) this);
        a(str, (AsyncCallback<SceneApi.SmartHomeScene, Error>) new AsyncCallback<SceneApi.SmartHomeScene, Error>() {
            /* renamed from: a */
            public void onSuccess(SceneApi.SmartHomeScene smartHomeScene) {
                RecommendSceneCreator.a().h = CreateSceneFromRecommendActivity.this.b(smartHomeScene);
                SceneApi.SmartHomeScene unused = CreateSceneFromRecommendActivity.this.t = RecommendSceneCreator.a().h;
                boolean unused2 = CreateSceneFromRecommendActivity.this.z = true;
                if (CreateSceneFromRecommendActivity.this.y && CreateSceneFromRecommendActivity.this.z) {
                    CreateSceneFromRecommendActivity.this.c();
                }
            }

            public void onFailure(Error error) {
                CreateSceneFromRecommendActivity.this.a(CreateSceneFromRecommendActivity.this.t);
            }
        });
        a((AsyncCallback<PluginRecommendSceneInfo, Error>) new AsyncCallback<PluginRecommendSceneInfo, Error>() {
            /* renamed from: a */
            public void onSuccess(PluginRecommendSceneInfo pluginRecommendSceneInfo) {
                if (pluginRecommendSceneInfo != null) {
                    RecommendSceneCreator a2 = RecommendSceneCreator.a();
                    a2.f21963a = pluginRecommendSceneInfo.getItemBy(CreateSceneFromRecommendActivity.this.v + "");
                }
                if (RecommendSceneCreator.a().f21963a != null) {
                    if (TextUtils.equals(RecommendSceneCreator.a().f21963a.sr_id, Tags.LuckyShake.VALUE_FAIL_HITTED)) {
                        boolean unused = CreateSceneFromRecommendActivity.this.A = true;
                    }
                    RecommendSceneCreator.a().f21963a.mCommonActions.clear();
                    RecommendSceneCreator.a().f21963a.mCommonActions.putAll(pluginRecommendSceneInfo.mCommonActions);
                    RecommendSceneCreator.a().f21963a.mCommonConditions.clear();
                    RecommendSceneCreator.a().f21963a.mCommonConditions.putAll(pluginRecommendSceneInfo.mCommonConditions);
                    if (CreateSceneFromRecommendActivity.this.f22003a == null) {
                        RecommendUI unused2 = CreateSceneFromRecommendActivity.this.f22003a = RecommendUI.c(RecommendSceneCreator.a().f21963a);
                    }
                    RecommendSceneCreator.a().a(pluginRecommendSceneInfo.mConditionScIds, pluginRecommendSceneInfo.mActionSaIds);
                    RecommendSceneCreator.a().b();
                    CreateSceneFromRecommendActivity.this.a(RecommendSceneCreator.a().f21963a);
                    boolean unused3 = CreateSceneFromRecommendActivity.this.y = true;
                    if (CreateSceneFromRecommendActivity.this.y && CreateSceneFromRecommendActivity.this.z) {
                        CreateSceneFromRecommendActivity.this.c();
                        return;
                    }
                    return;
                }
                ToastUtil.a((int) R.string.scene_log_scene_no_exist);
                CreateSceneFromRecommendActivity.this.finish();
            }

            public void onFailure(Error error) {
                CreateSceneFromRecommendActivity.this.c();
            }
        });
    }

    private void a(String str, final AsyncCallback<SceneApi.SmartHomeScene, Error> asyncCallback) {
        SceneApi.SmartHomeScene e2;
        if (!(TextUtils.isEmpty(str) || (e2 = SceneManager.x().e(str)) == null || asyncCallback == null)) {
            asyncCallback.onSuccess(e2);
        }
        PluginRecommendSceneManager.a().a(this.w.did, this.v, (AsyncCallback<List<SceneApi.SmartHomeScene>, Error>) new AsyncCallback<List<SceneApi.SmartHomeScene>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<SceneApi.SmartHomeScene> list) {
                if (list == null || list.size() <= 0) {
                    asyncCallback.onSuccess(null);
                } else if (asyncCallback != null) {
                    asyncCallback.onSuccess(list.get(0));
                }
            }

            public void onFailure(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        });
    }

    private void a(AsyncCallback<PluginRecommendSceneInfo, Error> asyncCallback) {
        PluginRecommendSceneManager.a().b(this.w.did, this.v, asyncCallback);
    }

    /* access modifiers changed from: private */
    public SceneApi.SmartHomeScene b(SceneApi.SmartHomeScene smartHomeScene) {
        if (smartHomeScene == null) {
            return null;
        }
        boolean z2 = false;
        if (smartHomeScene.l != null && smartHomeScene.l.size() > 0) {
            int size = smartHomeScene.l.size() - 1;
            while (true) {
                if (size >= 0) {
                    if (smartHomeScene.l.get(size).c != null && SmartHomeDeviceManager.a().b(smartHomeScene.l.get(size).c.f21523a) == null) {
                        z2 = true;
                        break;
                    }
                    size--;
                } else {
                    break;
                }
            }
        }
        if (!z2 && smartHomeScene.k != null && smartHomeScene.k.size() > 0) {
            int size2 = smartHomeScene.k.size() - 1;
            while (true) {
                if (size2 >= 0) {
                    if (smartHomeScene.k.get(size2).g != null && (smartHomeScene.k.get(size2).g instanceof SceneApi.SHSceneItemPayloadCommon) && smartHomeScene.k.get(size2).f21521a == 0 && SmartHomeDeviceManager.a().b(smartHomeScene.k.get(size2).g.e) == null) {
                        z2 = true;
                        break;
                    }
                    size2--;
                } else {
                    break;
                }
            }
        }
        if (z2) {
            return null;
        }
        return smartHomeScene;
    }

    /* access modifiers changed from: private */
    public void a(PluginRecommendSceneInfo.RecommendSceneItem recommendSceneItem) {
        if (RecommendSceneCreator.a().f21963a == null || TextUtils.isEmpty(RecommendSceneCreator.a().f21963a.intro)) {
            this.j.setText(R.string.setting);
        } else {
            this.j.setText(RecommendSceneCreator.a().f21963a.intro);
        }
        this.b.setHierarchy(new GenericDraweeHierarchyBuilder(getResources()).setFadeDuration(200).setPlaceholderImage(getResources().getDrawable(R.drawable.recommend_scene_list_default_icon)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_XY).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
        if (!TextUtils.isEmpty(recommendSceneItem.cardImgUrl)) {
            this.b.setImageURI(Uri.parse(recommendSceneItem.cardImgUrl));
        } else {
            this.b.setImageResource(R.drawable.recommend_scene_list_default_icon);
        }
        String str = null;
        if (RecommendSceneCreator.a().f21963a.isActionDevice) {
            str = RecommendSceneCreator.a().f21963a.selectConditionHint;
        } else if (RecommendSceneCreator.a().f21963a.isConditionDevice) {
            str = RecommendSceneCreator.a().f21963a.selectActionHint;
        }
        if (!TextUtils.isEmpty(str)) {
            TextView textView = this.k;
            Object[] objArr = new Object[1];
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            objArr[0] = str;
            textView.setText(getString(R.string.light_rec_condition_name, objArr));
        } else {
            String str2 = RecommendSceneCreator.a().f21963a.selectDeviceHint;
            if (TextUtils.isEmpty(str2)) {
                str2 = getString(R.string.light_rec_condition_name, new Object[]{""});
            }
            this.k.setText(str2);
        }
        if (recommendSceneItem.needDelay) {
            this.m.setVisibility(0);
            this.n.setVisibility(0);
            return;
        }
        this.m.setVisibility(8);
        this.n.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.t == null) {
            RecommendSceneCreator.a().h = new SceneApi.SmartHomeScene();
            this.t = RecommendSceneCreator.a().h;
            this.t.n = false;
            this.t.o = true;
            this.t.i = this.v;
            this.t.u = new SceneApi.EffectiveTimeSpan();
            this.t.k = new ArrayList();
            this.t.l = new ArrayList();
            if (RecommendSceneCreator.a().f21963a != null) {
                this.f22003a.a(this.t);
                this.f22003a.a(this.t, this.w);
            }
        }
        a(this.t);
        this.f.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.r.setOnClickListener(this);
        this.i.setOnClickListener(this);
        this.l.setOnClickListener(this);
        if (!this.C) {
            this.C = true;
            if (RecommendSceneCreator.a().f21963a.isActionDevice) {
                if (this.t.l == null || this.t.l.size() == 0) {
                    StatPopUp statPopUp = STAT.e;
                    String str = this.w.model;
                    statPopUp.g(str, this.v + "");
                    return;
                }
                StatPopUp statPopUp2 = STAT.e;
                String str2 = this.w.model;
                statPopUp2.f(str2, this.v + "");
            } else if (!RecommendSceneCreator.a().f21963a.isConditionDevice) {
            } else {
                if (this.t.k == null || this.t.k.size() == 0) {
                    StatPopUp statPopUp3 = STAT.e;
                    String str3 = this.w.model;
                    statPopUp3.g(str3, this.v + "");
                    return;
                }
                StatPopUp statPopUp4 = STAT.e;
                String str4 = this.w.model;
                statPopUp4.f(str4, this.v + "");
            }
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.listitem_condition_detail_select:
                if (this.q.getVisibility() != 8) {
                    if (this.t != null) {
                        if (this.t.k != null && this.t.k.size() > 0) {
                            this.D = this.t.k.get(0);
                        }
                        if (this.t.l != null && this.t.l.size() > 0) {
                            this.E = this.t.l.get(0);
                        }
                    }
                    if (this.f22003a.c) {
                        if (this.f22003a.e(this.t, 0)) {
                            this.f22003a.a((BaseActivity) this, this.t.l.get(0), (RecommendSceneCreator.OnSelectCallback) new RecommendSceneCreator.OnSelectCallback() {
                                public void a(int i, Intent intent) {
                                    CreateSceneFromRecommendActivity.this.a(i, CreateSceneFromRecommendActivity.this.t.l.get(0), intent);
                                }
                            });
                        } else {
                            this.f22003a.a((BaseActivity) this, this.t.k.get(0), (RecommendSceneCreator.OnSelectCallback) new RecommendSceneCreator.OnSelectCallback() {
                                public void a(int i, Intent intent) {
                                    CreateSceneFromRecommendActivity.this.a(i, CreateSceneFromRecommendActivity.this.t.k.get(0), intent);
                                }
                            });
                        }
                    }
                    if (!this.f22003a.b) {
                        return;
                    }
                    if (this.f22003a.d(this.t, 0)) {
                        this.f22003a.a(this, this.t, 0, new RecommendSceneCreator.OnSelectCallback() {
                            public void a(int i, Intent intent) {
                                CreateSceneFromRecommendActivity.this.a(i, CreateSceneFromRecommendActivity.this.t.k.get(0), intent);
                            }
                        });
                        return;
                    } else {
                        this.f22003a.a((BaseActivity) this, this.t.l.get(0), (RecommendSceneCreator.OnSelectCallback) new RecommendSceneCreator.OnSelectCallback() {
                            public void a(int i, Intent intent) {
                                CreateSceneFromRecommendActivity.this.a(i, CreateSceneFromRecommendActivity.this.t.l.get(0), intent);
                            }
                        });
                        return;
                    }
                } else {
                    return;
                }
            case R.id.listitem_condition_select:
                Intent intent = new Intent(this, ActionConditionDeviceSelectActivity.class);
                intent.putExtra("home_id", this.x.j());
                intent.putExtra("device_model", this.w.model);
                intent.putExtra("sr_id", this.v);
                if (RecommendSceneCreator.a().f21963a.isActionDevice) {
                    if (!this.t.l.isEmpty()) {
                        this.E = this.t.l.get(0);
                    }
                    intent.putExtra("need_condition", true);
                    startActivityForResult(intent, 1002);
                    return;
                }
                if (!this.t.k.isEmpty()) {
                    this.D = this.t.k.get(0);
                }
                intent.putExtra("need_action", true);
                startActivityForResult(intent, 1003);
                return;
            case R.id.listitem_timespan:
                Intent intent2 = new Intent(this, SceneCreateTimeEdit2Activity.class);
                intent2.putExtra("is_from_recommend", true);
                startActivityForResult(intent2, 1011);
                return;
            case R.id.module_a_3_return_btn:
                finish();
                return;
            case R.id.tv_enable:
                this.t.n = true ^ this.t.n;
                if (this.t.n && TextUtils.isEmpty(this.t.f)) {
                    StatClick statClick = STAT.d;
                    String str = this.w.model;
                    statClick.s(str, this.v + "");
                }
                d();
                return;
            default:
                return;
        }
    }

    /* JADX WARNING: type inference failed for: r6v32, types: [com.xiaomi.smarthome.scene.api.SceneApi$ConditionDevice] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r5, com.xiaomi.smarthome.scene.api.SceneApi.Condition r6, android.content.Intent r7) {
        /*
            r4 = this;
            r0 = -1
            if (r5 != r0) goto L_0x0102
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r5 = new com.xiaomi.smarthome.scene.api.SceneApi$Condition
            r5.<init>()
            com.xiaomi.smarthome.scene.api.SceneApi$ConditionDeviceCommon r0 = new com.xiaomi.smarthome.scene.api.SceneApi$ConditionDeviceCommon
            r0.<init>()
            com.xiaomi.smarthome.scene.api.SceneApi$Condition$LAUNCH_TYPE r1 = com.xiaomi.smarthome.scene.api.SceneApi.Condition.LAUNCH_TYPE.DEVICE
            r5.f21522a = r1
            int r1 = r6.k
            r5.k = r1
            r1 = 0
            com.xiaomi.smarthome.scene.api.SceneApi$ConditionDevice r2 = r6.c
            if (r2 == 0) goto L_0x005e
            com.xiaomi.smarthome.scene.api.SceneApi$ConditionDevice r2 = r6.c
            boolean r2 = r2 instanceof com.xiaomi.smarthome.scene.api.SceneApi.ConditionDeviceCommon
            if (r2 == 0) goto L_0x005e
            com.xiaomi.smarthome.scene.api.SceneApi$ConditionDevice r6 = r6.c
            r0 = r6
            com.xiaomi.smarthome.scene.api.SceneApi$ConditionDeviceCommon r0 = (com.xiaomi.smarthome.scene.api.SceneApi.ConditionDeviceCommon) r0
            com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator r6 = com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator.a()
            com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$RecommendSceneItem r6 = r6.f21963a
            if (r6 == 0) goto L_0x005e
            com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator r6 = com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator.a()
            com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$RecommendSceneItem r6 = r6.f21963a
            java.util.Map<java.lang.Integer, com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$CommonSceneCondition> r6 = r6.mCommonConditions
            if (r6 == 0) goto L_0x005e
            com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator r6 = com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator.a()
            com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$RecommendSceneItem r6 = r6.f21963a
            java.util.Map<java.lang.Integer, com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$CommonSceneCondition> r6 = r6.mCommonConditions
            int r1 = r0.k
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            java.lang.Object r6 = r6.get(r1)
            r1 = r6
            com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo$CommonSceneCondition r1 = (com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo.CommonSceneCondition) r1
            if (r1 == 0) goto L_0x005e
            java.lang.String r6 = r1.mName
            r0.b = r6
            java.lang.String r6 = r1.mKey
            r0.j = r6
            java.lang.String r6 = r1.mKey
            r0.j = r6
            int r6 = r1.id
            r0.k = r6
        L_0x005e:
            if (r7 == 0) goto L_0x0070
            java.lang.String r6 = "key_name"
            boolean r6 = r7.hasExtra(r6)
            if (r6 == 0) goto L_0x0070
            java.lang.String r6 = "key_name"
            java.lang.String r6 = r7.getStringExtra(r6)
            r0.b = r6
        L_0x0070:
            r5.c = r0
            if (r7 == 0) goto L_0x00bc
            java.lang.String r6 = "value"
            boolean r6 = r7.hasExtra(r6)
            if (r6 == 0) goto L_0x00bc
            java.lang.String r6 = "value"
            java.lang.String r6 = r7.getStringExtra(r6)
            if (r6 == 0) goto L_0x00af
            r6 = 0
            r1 = 1
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0094 }
            java.lang.String r3 = "value"
            java.lang.String r3 = r7.getStringExtra(r3)     // Catch:{ JSONException -> 0x0094 }
            r2.<init>(r3)     // Catch:{ JSONException -> 0x0094 }
            r0.l = r2     // Catch:{ JSONException -> 0x0094 }
            r6 = 1
        L_0x0094:
            if (r6 != 0) goto L_0x00a4
            org.json.JSONArray r2 = new org.json.JSONArray     // Catch:{ JSONException -> 0x00a4 }
            java.lang.String r3 = "value"
            java.lang.String r3 = r7.getStringExtra(r3)     // Catch:{ JSONException -> 0x00a4 }
            r2.<init>(r3)     // Catch:{ JSONException -> 0x00a4 }
            r0.l = r2     // Catch:{ JSONException -> 0x00a4 }
            r6 = 1
        L_0x00a4:
            if (r6 != 0) goto L_0x00c2
            java.lang.String r6 = "value"
            java.lang.String r6 = r7.getStringExtra(r6)
            r0.l = r6
            goto L_0x00c2
        L_0x00af:
            android.os.Bundle r6 = r7.getExtras()
            java.lang.String r7 = "value"
            java.lang.Object r6 = r6.get(r7)
            r0.l = r6
            goto L_0x00c2
        L_0x00bc:
            if (r1 == 0) goto L_0x00c2
            java.lang.Object r6 = r1.mValue
            r0.l = r6
        L_0x00c2:
            r5.c = r0
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r6 = r4.t
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r6 = r6.l
            if (r6 != 0) goto L_0x00d3
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r6 = r4.t
            java.util.ArrayList r7 = new java.util.ArrayList
            r7.<init>()
            r6.l = r7
        L_0x00d3:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r6 = r4.t
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r6 = r6.l
            int r6 = r6.size()
            if (r6 != 0) goto L_0x00e5
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r6 = r4.t
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r6 = r6.l
            r6.add(r5)
            goto L_0x00f3
        L_0x00e5:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r6 = r4.t
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r6 = r6.l
            r6.clear()
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r6 = r4.t
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r6 = r6.l
            r6.add(r5)
        L_0x00f3:
            boolean r5 = r4.b()
            if (r5 == 0) goto L_0x00fc
            r4.d()
        L_0x00fc:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r5 = r4.t
            r4.a((com.xiaomi.smarthome.scene.api.SceneApi.SmartHomeScene) r5)
            goto L_0x012b
        L_0x0102:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r5 = r4.t
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r5 = r5.l
            if (r5 == 0) goto L_0x0126
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r5 = r4.t
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r5 = r5.l
            boolean r5 = r5.isEmpty()
            if (r5 != 0) goto L_0x0126
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r5 = r4.t
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r5 = r5.l
            r5.clear()
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r5 = r4.E
            if (r5 == 0) goto L_0x0126
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r5 = r4.t
            java.util.List<com.xiaomi.smarthome.scene.api.SceneApi$Condition> r5 = r5.l
            com.xiaomi.smarthome.scene.api.SceneApi$Condition r6 = r4.E
            r5.add(r6)
        L_0x0126:
            com.xiaomi.smarthome.scene.api.SceneApi$SmartHomeScene r5 = r4.t
            r4.a((com.xiaomi.smarthome.scene.api.SceneApi.SmartHomeScene) r5)
        L_0x012b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scenenew.pluginrecommend.CreateSceneFromRecommendActivity.a(int, com.xiaomi.smarthome.scene.api.SceneApi$Condition, android.content.Intent):void");
    }

    /* access modifiers changed from: private */
    public void a(int i2, SceneApi.Action action, Intent intent) {
        if (i2 == -1) {
            if (intent != null && intent.hasExtra("value") && intent.getStringExtra("value") != null) {
                if (intent != null && intent.hasExtra("key_name")) {
                    action.c = intent.getStringExtra("key_name");
                }
                SceneApi.SHSceneItemPayloadCommon sHSceneItemPayloadCommon = new SceneApi.SHSceneItemPayloadCommon();
                sHSceneItemPayloadCommon.e = action.g.e;
                sHSceneItemPayloadCommon.i = ((SceneApi.SHSceneItemPayloadCommon) action.g).i;
                sHSceneItemPayloadCommon.c = ((SceneApi.SHSceneItemPayloadCommon) action.g).c;
                sHSceneItemPayloadCommon.j = ((SceneApi.SHSceneItemPayloadCommon) action.g).j;
                if (intent == null || !intent.hasExtra("value")) {
                    sHSceneItemPayloadCommon.f = action.g.f;
                } else if (intent.getStringExtra("value") != null) {
                    boolean z2 = false;
                    try {
                        sHSceneItemPayloadCommon.f = new JSONObject(intent.getStringExtra("value"));
                        z2 = true;
                    } catch (JSONException unused) {
                    }
                    if (!z2) {
                        try {
                            sHSceneItemPayloadCommon.f = new JSONArray(intent.getStringExtra("value"));
                            z2 = true;
                        } catch (JSONException unused2) {
                        }
                    }
                    if (!z2) {
                        sHSceneItemPayloadCommon.f = intent.getStringExtra("value");
                    }
                } else {
                    sHSceneItemPayloadCommon.f = intent.getExtras().get("value");
                }
                action.g = sHSceneItemPayloadCommon;
                if (this.t.k == null) {
                    this.t.k = new ArrayList();
                }
                if (this.t.k.size() == 0) {
                    this.t.k.add(action);
                } else {
                    this.t.k.clear();
                    this.t.k.add(action);
                }
                if (b()) {
                    d();
                }
                a(this.t);
            }
        } else if (i2 == 0) {
            if (this.t.k != null && !this.t.k.isEmpty()) {
                this.t.k.clear();
                if (this.D != null) {
                    this.t.k.add(this.D);
                }
            }
            a(this.t);
        }
    }

    public Map<String, List<SceneApi.Action>> getSelectionActions(Map<Integer, SceneApi.Action> map) {
        HashMap hashMap = new HashMap();
        JSONObject jSONObject = RecommendSceneCreator.a().f21963a.mActionList.get(0).modelListJobj;
        if (jSONObject != null) {
            Iterator<String> keys = jSONObject.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                List<Device> a2 = a(next, this.x);
                if (!(a2 == null || a2.size() == 0)) {
                    int optInt = jSONObject.optInt(next);
                    SceneApi.Action action = map.get(Integer.valueOf(optInt));
                    if (action != null) {
                        List<SceneApi.Action> a3 = a(action, a2);
                        if (hashMap.containsKey(optInt + "")) {
                            if (hashMap.get(optInt + "") != null) {
                                ((List) hashMap.get(optInt + "")).addAll(a3);
                            }
                        }
                        hashMap.put(optInt + "", a3);
                    }
                }
            }
        }
        return hashMap;
    }

    public Map<String, List<SceneApi.Condition>> getSelctionConditions(Map<Integer, SceneApi.Condition> map) {
        HashMap hashMap = new HashMap();
        if (RecommendSceneCreator.a().f21963a.mConditionList != null && RecommendSceneCreator.a().f21963a.mConditionList.size() > 0) {
            for (int i2 = 0; i2 < RecommendSceneCreator.a().f21963a.mConditionList.size(); i2++) {
                JSONObject jSONObject = RecommendSceneCreator.a().f21963a.mConditionList.get(i2).modelListJobj;
                if (jSONObject != null) {
                    Iterator<String> keys = jSONObject.keys();
                    while (keys.hasNext()) {
                        String next = keys.next();
                        List<Device> a2 = a(next, this.x);
                        if (!(a2 == null || a2.size() == 0)) {
                            int optInt = jSONObject.optInt(next);
                            Map<String, JSONObject> map2 = RecommendSceneCreator.a().b;
                            JSONObject jSONObject2 = map2.get(optInt + "");
                            if (jSONObject2 != null) {
                                List<SceneApi.Condition> a3 = a(optInt, jSONObject2, a2);
                                if (hashMap.containsKey(optInt + "")) {
                                    if (hashMap.get(optInt + "") != null) {
                                        ((List) hashMap.get(optInt + "")).addAll(a3);
                                    }
                                }
                                hashMap.put(optInt + "", a3);
                            }
                        }
                    }
                }
            }
        }
        return hashMap;
    }

    private List<Device> a(String str, Home home) {
        ArrayList arrayList = new ArrayList();
        for (Device next : HomeManager.a().o(home.j())) {
            if (TextUtils.equals(str, next.model)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    private List<SceneApi.Condition> a(int i2, JSONObject jSONObject, List<Device> list) {
        ArrayList arrayList = new ArrayList();
        try {
            for (Device next : list) {
                String format = String.format(jSONObject.optString("key"), new Object[]{next.model});
                JSONObject jSONObject2 = new JSONObject(jSONObject.toString());
                jSONObject2.put("key", format);
                jSONObject2.put("model", next.model);
                SceneApi.Condition a2 = SceneApi.Condition.a(jSONObject2);
                if (a2.f21522a == SceneApi.Condition.LAUNCH_TYPE.DEVICE) {
                    if (a2.c != null && (a2.c instanceof SceneApi.ConditionDeviceCommon)) {
                        ((SceneApi.ConditionDeviceCommon) a2.c).f21523a = next.did;
                        ((SceneApi.ConditionDeviceCommon) a2.c).d = next.model;
                        ((SceneApi.ConditionDeviceCommon) a2.c).c = next.name;
                        ((SceneApi.ConditionDeviceCommon) a2.c).k = i2;
                        if (this.f22003a.d.get(i2, false).booleanValue()) {
                            a2 = this.f22003a.a(a2);
                        }
                    }
                    arrayList.add(a2);
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    private List<SceneApi.Action> a(SceneApi.Action action, List<Device> list) {
        ArrayList arrayList = new ArrayList();
        if (action.g == null) {
            action.g = new SceneApi.SHSceneItemPayloadCommon();
        }
        try {
            JSONObject a2 = action.a();
            for (Device next : list) {
                SceneApi.Action a3 = SceneApi.Action.a(a2);
                a3.g.e = next.did;
                a3.e = next.model;
                if (this.f22003a.e.get(action.f, false).booleanValue()) {
                    a3 = this.f22003a.a(a3);
                }
                arrayList.add(a3);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    private void d() {
        this.o = XQProgressDialog.a(this, (CharSequence) null, getResources().getString(R.string.smarthome_scene_saving_scene));
        if (TextUtils.isEmpty(this.t.g)) {
            this.t.g = SmartHomeSceneUtility.a(this.t);
        }
        RecommendSceneCreator.a(this.t, (RecommendSceneCreator.SaveSceneCallback) new RecommendSceneCreator.SaveSceneCallback() {
            public void onSaveLocalFail() {
                if (CreateSceneFromRecommendActivity.this.isValid()) {
                    CreateSceneFromRecommendActivity.this.o.dismiss();
                }
                ToastUtil.a((int) R.string.save_fail);
                CreateSceneFromRecommendActivity.this.c();
            }

            public void onSaveCloudSuccess(boolean z) {
                if (CreateSceneFromRecommendActivity.this.isValid()) {
                    CreateSceneFromRecommendActivity.this.o.dismiss();
                }
                CreateSceneFromRecommendActivity.this.c((String) null);
            }

            public void onSaveCloudFail(int i, String str) {
                if (CreateSceneFromRecommendActivity.this.isValid()) {
                    CreateSceneFromRecommendActivity.this.o.dismiss();
                }
                if (i == -9000) {
                    ToastUtil.a((int) R.string.smarthome_scene_getway_offline);
                } else {
                    ToastUtil.a((int) R.string.save_fail);
                }
                CreateSceneFromRecommendActivity.this.c();
            }
        });
    }
}
