package com.xiaomi.smarthome.scenenew.pluginrecommend;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.model.Tags;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.scene.SceneCreateTimeEdit2Activity;
import com.xiaomi.smarthome.scene.SmartHomeSceneTimerDelay;
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
import org.json.JSONException;
import org.json.JSONObject;

public class LightActionStartActivity extends BaseActivity implements View.OnClickListener, RecommendSceneCreator.SelectionBuilder {
    public static final String EXTRA_DID = "did";
    public static final String EXTRA_SRID = "sr_id";
    public static final String EXTRA_USID = "us_id";
    public static final int REQUEST_CONDITION_DEV = 1002;

    /* renamed from: a  reason: collision with root package name */
    private SimpleDraweeView f22016a;
    private TextView b;
    private View c;
    private TextView d;
    private TextView e;
    private View f;
    private TextView g;
    private TextView h;
    private TextView i;
    private View j;
    private View k;
    /* access modifiers changed from: private */
    public XQProgressDialog l;
    private SwitchButton m;
    private View n;
    /* access modifiers changed from: private */
    public SceneApi.SmartHomeScene o;
    /* access modifiers changed from: private */
    public int p;
    private Device q = null;
    private Home r = null;
    /* access modifiers changed from: private */
    public boolean s = false;
    /* access modifiers changed from: private */
    public boolean t = false;
    /* access modifiers changed from: private */
    public boolean u = false;
    private boolean v = false;

    private void e() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.light_rec_scene_action_activity);
        this.p = getIntent().getIntExtra("sr_id", -1);
        String stringExtra = getIntent().getStringExtra("us_id");
        String stringExtra2 = getIntent().getStringExtra("did");
        if (!TextUtils.isEmpty(stringExtra2)) {
            this.q = SmartHomeDeviceManager.a().b(stringExtra2);
            this.r = HomeManager.a().q(stringExtra2);
        }
        if (this.r == null) {
            this.r = HomeManager.a().m();
        }
        if (this.p <= 0 || this.q == null) {
            finish();
            return;
        }
        StatPopUp statPopUp = STAT.e;
        String str = this.q.model;
        statPopUp.b(str, this.p + "");
        this.g = (TextView) findViewById(R.id.module_a_3_return_title);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(this);
        if (RecommendSceneCreator.a().f21963a == null || TextUtils.isEmpty(RecommendSceneCreator.a().f21963a.entryDesc)) {
            this.g.setText(R.string.setting);
        } else {
            this.g.setText(RecommendSceneCreator.a().f21963a.intro);
        }
        if (RecommendSceneCreator.a().f21963a == null || TextUtils.isEmpty(RecommendSceneCreator.a().f21963a.intro)) {
            this.g.setText(R.string.setting);
        } else {
            this.g.setText(RecommendSceneCreator.a().f21963a.intro);
        }
        this.f22016a = (SimpleDraweeView) findViewById(R.id.recommend_sdv);
        this.c = findViewById(R.id.listitem_condition_select);
        this.b = (TextView) findViewById(R.id.tv_condition_device_name);
        this.h = (TextView) findViewById(R.id.tv_condition_select);
        this.j = findViewById(R.id.listitem_delay_payload);
        this.m = (SwitchButton) findViewById(R.id.open_check);
        this.n = findViewById(R.id.divider);
        this.k = findViewById(R.id.listitem_delay_minute);
        this.d = (TextView) findViewById(R.id.tv_delay_minute);
        this.e = (TextView) findViewById(R.id.tv_timespan);
        this.f = findViewById(R.id.listitem_timespan);
        this.i = (TextView) findViewById(R.id.tv_enable);
        a(stringExtra);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        if (i3 == -1) {
            b();
            if (!a()) {
                b(this.o);
            } else if (i2 == 1 || i2 == 1002 || i2 == 1011) {
                f();
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        RecommendSceneCreator.a().c();
    }

    private boolean a() {
        if (this.o != null && !TextUtils.isEmpty(this.o.f) && this.o.n) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        RecommendSceneCreator.a().c();
        RecommendSceneCreator.a().a((RecommendSceneCreator.SelectionBuilder) this);
        a(str, (AsyncCallback<SceneApi.SmartHomeScene, Error>) new AsyncCallback<SceneApi.SmartHomeScene, Error>() {
            /* renamed from: a */
            public void onSuccess(SceneApi.SmartHomeScene smartHomeScene) {
                RecommendSceneCreator.a().h = LightActionStartActivity.this.a(smartHomeScene);
                SceneApi.SmartHomeScene unused = LightActionStartActivity.this.o = RecommendSceneCreator.a().h;
                if (LightActionStartActivity.this.o != null) {
                    LightActionStartActivity.this.b(LightActionStartActivity.this.o);
                }
                boolean unused2 = LightActionStartActivity.this.t = true;
                if (LightActionStartActivity.this.s && LightActionStartActivity.this.t) {
                    LightActionStartActivity.this.b();
                }
            }

            public void onFailure(Error error) {
                LightActionStartActivity.this.b(LightActionStartActivity.this.o);
            }
        });
        a((AsyncCallback<PluginRecommendSceneInfo, Error>) new AsyncCallback<PluginRecommendSceneInfo, Error>() {
            /* renamed from: a */
            public void onSuccess(PluginRecommendSceneInfo pluginRecommendSceneInfo) {
                if (pluginRecommendSceneInfo != null) {
                    RecommendSceneCreator a2 = RecommendSceneCreator.a();
                    a2.f21963a = pluginRecommendSceneInfo.getItemBy(LightActionStartActivity.this.p + "");
                }
                if (RecommendSceneCreator.a().f21963a != null) {
                    if (TextUtils.equals(RecommendSceneCreator.a().f21963a.sr_id, Tags.LuckyShake.VALUE_FAIL_HITTED)) {
                        boolean unused = LightActionStartActivity.this.u = true;
                    }
                    RecommendSceneCreator.a().a(pluginRecommendSceneInfo.mConditionScIds, pluginRecommendSceneInfo.mActionSaIds);
                    RecommendSceneCreator.a().b();
                    LightActionStartActivity.this.a(RecommendSceneCreator.a().f21963a);
                }
                boolean unused2 = LightActionStartActivity.this.s = true;
                if (LightActionStartActivity.this.s && LightActionStartActivity.this.t) {
                    LightActionStartActivity.this.b();
                }
            }

            public void onFailure(Error error) {
                LightActionStartActivity.this.b();
            }
        });
    }

    /* access modifiers changed from: private */
    public SceneApi.SmartHomeScene a(SceneApi.SmartHomeScene smartHomeScene) {
        Home q2;
        if (smartHomeScene == null) {
            return null;
        }
        if (smartHomeScene.l != null && smartHomeScene.l.size() > 0) {
            for (int size = smartHomeScene.l.size() - 1; size >= 0; size--) {
                if (smartHomeScene.l.get(size).c != null && ((q2 = HomeManager.a().q(smartHomeScene.l.get(size).c.f21523a)) == null || !TextUtils.equals(q2.j(), this.r.j()))) {
                    smartHomeScene.l.remove(size);
                }
            }
        }
        if (smartHomeScene.k != null && smartHomeScene.k.size() > 0) {
            for (int size2 = smartHomeScene.k.size() - 1; size2 >= 0; size2--) {
                if (smartHomeScene.k.get(size2).g != null && (smartHomeScene.k.get(size2).g instanceof SceneApi.SHSceneItemPayloadCommon) && smartHomeScene.k.get(size2).f21521a == 0 && !TextUtils.equals(HomeManager.a().q(smartHomeScene.k.get(size2).g.e).j(), this.r.j())) {
                    smartHomeScene.k.remove(size2);
                }
            }
        }
        return smartHomeScene;
    }

    /* access modifiers changed from: private */
    public void b() {
        List list;
        if (this.o == null) {
            RecommendSceneCreator.a().h = new SceneApi.SmartHomeScene();
            this.o = RecommendSceneCreator.a().h;
            this.o.n = false;
            this.o.o = true;
            this.o.i = this.p;
            this.o.u = new SceneApi.EffectiveTimeSpan();
            this.o.k = new ArrayList();
            SceneApi.Action c2 = c();
            if (c2 != null) {
                this.o.k.add(c2);
                List<SceneApi.Action> d2 = d();
                if (d2 != null && d2.size() == 2 && d2.get(0).g.g > 0) {
                    this.o.k.addAll(d2);
                }
            }
            if (RecommendSceneCreator.a().e != null && RecommendSceneCreator.a().e.size() > 0) {
                String[] strArr = (String[]) RecommendSceneCreator.a().e.keySet().toArray(new String[0]);
                if (strArr.length > 0 && (list = RecommendSceneCreator.a().e.get(strArr[0])) != null && list.size() > 0) {
                    this.o.l.add(list.get(0));
                }
            }
        }
        b(this.o);
        this.c.setOnClickListener(this);
        this.j.setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.m.setOnPerformCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                LightActionStartActivity.this.a(compoundButton, z);
            }
        });
        this.f.setOnClickListener(this);
        this.i.setOnClickListener(this);
        if (!this.v) {
            this.v = true;
            if (this.o.l == null || this.o.l.size() == 0) {
                StatPopUp statPopUp = STAT.e;
                String str = this.q.model;
                statPopUp.g(str, this.p + "");
                return;
            }
            StatPopUp statPopUp2 = STAT.e;
            String str2 = this.q.model;
            statPopUp2.f(str2, this.p + "");
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(CompoundButton compoundButton, boolean z) {
        a(z);
    }

    private void a(boolean z) {
        StatClick statClick = STAT.d;
        String str = this.q.model;
        statClick.n(str, this.p + "");
        if (z) {
            this.k.setVisibility(8);
            this.n.setVisibility(8);
        } else {
            this.k.setVisibility(0);
            this.n.setVisibility(0);
        }
        if (z) {
            this.o.k.addAll(getAdditionActions());
        } else {
            this.o.k.remove(2);
            this.o.k.remove(1);
        }
        if (a()) {
            f();
        } else {
            b(this.o);
        }
    }

    private SceneApi.Action c() {
        List list;
        if (RecommendSceneCreator.a().f == null || (list = RecommendSceneCreator.a().f.get(this.q.name)) == null || list.size() <= 1) {
            return null;
        }
        return (SceneApi.Action) list.get(0);
    }

    private List<SceneApi.Action> d() {
        List list;
        SceneApi.Action action = new SceneApi.Action();
        action.f21521a = 2;
        action.b = SHApplication.getAppContext().getString(R.string.smarthome_scene_delay);
        action.c = getString(R.string.smarthome_scene_delay);
        action.e = "delay";
        SceneApi.SHSceneDelayPayload sHSceneDelayPayload = new SceneApi.SHSceneDelayPayload();
        sHSceneDelayPayload.g = 300;
        sHSceneDelayPayload.c = action.e + ".delay";
        action.g = sHSceneDelayPayload;
        if (RecommendSceneCreator.a().f == null || (list = RecommendSceneCreator.a().f.get(this.q.name)) == null || list.size() <= 1) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(action);
        arrayList.add(list.get(1));
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void b(SceneApi.SmartHomeScene smartHomeScene) {
        SceneApi.Action action;
        SceneApi.Action action2;
        String str;
        if (this.o.n) {
            this.i.setText(R.string.light_rec_enable_1);
            this.i.setBackgroundResource(R.drawable.common_flow_tag_item_normal_bg);
            this.i.setTextColor(getResources().getColor(R.color.common_button));
        } else {
            this.i.setText(R.string.light_rec_enable_0);
            this.i.setBackgroundResource(R.drawable.selector_ble_mesh_button);
            this.i.setTextColor(getResources().getColor(R.color.white));
        }
        if (this.o.k == null || this.o.k.size() == 0 || this.o.l == null || this.o.l.size() == 0) {
            this.i.setEnabled(this.o.n);
        } else {
            this.i.setEnabled(true);
        }
        SmartHomeSceneUtility.a(this.o.u, this.e, getString(R.string.scene_exetime_second_day));
        if (smartHomeScene.k != null && smartHomeScene.k.size() > 0) {
            if (smartHomeScene.k.size() == 1) {
                action = smartHomeScene.k.get(0);
                action2 = null;
            } else if (smartHomeScene.k.size() == 3) {
                SceneApi.Action action3 = smartHomeScene.k.get(0);
                action2 = smartHomeScene.k.get(1);
                action = action3;
            } else {
                action = null;
                action2 = null;
            }
            if (!(action == null || action.f21521a != 0 || action.g == null)) {
                if (action2 == null || action2.g == null || action2.g.g <= 0) {
                    this.m.setChecked(false);
                    this.n.setVisibility(8);
                    this.k.setVisibility(8);
                    this.k.setOnClickListener((View.OnClickListener) null);
                } else {
                    this.m.setChecked(true);
                    this.n.setVisibility(0);
                    this.k.setVisibility(0);
                    this.k.setOnClickListener(this);
                    if (action2.g.g < 60) {
                        str = getResources().getQuantityString(R.plurals.smarthome_scene_delay_detal_sec, action2.g.g % 60, new Object[]{Integer.valueOf(action2.g.g % 60)});
                    } else if (action2.g.g % 60 == 0) {
                        str = getResources().getQuantityString(R.plurals.smarthome_scene_delay_detal_min, action2.g.g / 60, new Object[]{Integer.valueOf(action2.g.g / 60)});
                    } else {
                        str = String.format(getString(R.string.smarthome_scene_delay_detal_min_sec), new Object[]{getResources().getQuantityString(R.plurals.automation_minute, action2.g.g / 60, new Object[]{Integer.valueOf(action2.g.g / 60)}), getResources().getQuantityString(R.plurals.automation_seconds, action2.g.g % 60, new Object[]{Integer.valueOf(action2.g.g % 60)})});
                    }
                    this.d.setText(str);
                }
            }
        }
        if (this.o.l == null || this.o.l.size() <= 0) {
            this.b.setText(R.string.light_rec_non_condition);
        } else if (this.o.l.get(0).c == null) {
            this.b.setText(R.string.light_rec_non_condition);
        } else if (SmartHomeDeviceManager.a().b(this.o.l.get(0).c.f21523a) != null) {
            String str2 = this.o.l.get(0).c.c;
            TextView textView = this.b;
            if (TextUtils.isEmpty(str2)) {
                str2 = "";
            }
            textView.setText(str2);
        } else {
            this.b.setText(R.string.light_rec_non_condition);
            this.o.l.clear();
        }
    }

    /* access modifiers changed from: private */
    public void a(PluginRecommendSceneInfo.RecommendSceneItem recommendSceneItem) {
        if (RecommendSceneCreator.a().f21963a == null || TextUtils.isEmpty(RecommendSceneCreator.a().f21963a.intro)) {
            this.g.setText(R.string.setting);
        } else {
            this.g.setText(RecommendSceneCreator.a().f21963a.intro);
        }
        this.f22016a.setHierarchy(new GenericDraweeHierarchyBuilder(getResources()).setFadeDuration(200).setPlaceholderImage(getResources().getDrawable(R.drawable.recommend_scene_list_default_icon)).setActualImageScaleType(ScalingUtils.ScaleType.CENTER).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
        if (!TextUtils.isEmpty(recommendSceneItem.cardImgUrl)) {
            this.f22016a.setImageURI(Uri.parse(recommendSceneItem.cardImgUrl));
        } else {
            this.f22016a.setImageResource(R.drawable.recommend_scene_list_default_icon);
        }
        if (recommendSceneItem.mConditionList.size() > 0) {
            String str = recommendSceneItem.mConditionList.get(0).name;
            TextView textView = this.h;
            Object[] objArr = new Object[1];
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            objArr[0] = str;
            textView.setText(getString(R.string.light_rec_condition_name, objArr));
            return;
        }
        this.h.setText(getString(R.string.light_rec_condition_name, new Object[]{""}));
    }

    private void a(AsyncCallback<PluginRecommendSceneInfo, Error> asyncCallback) {
        PluginRecommendSceneManager.a().b(this.q.did, this.p, asyncCallback);
    }

    private void a(String str, final AsyncCallback<SceneApi.SmartHomeScene, Error> asyncCallback) {
        SceneApi.SmartHomeScene e2;
        if (!(TextUtils.isEmpty(str) || (e2 = SceneManager.x().e(str)) == null || asyncCallback == null)) {
            asyncCallback.onSuccess(e2);
        }
        PluginRecommendSceneManager.a().a(this.q.did, this.p, (AsyncCallback<List<SceneApi.SmartHomeScene>, Error>) new AsyncCallback<List<SceneApi.SmartHomeScene>, Error>() {
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
                    }
                    arrayList.add(a2);
                }
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    private void f() {
        this.l = XQProgressDialog.a(this, (CharSequence) null, getResources().getString(R.string.smarthome_scene_saving_scene));
        if (TextUtils.isEmpty(this.o.g)) {
            this.o.g = SmartHomeSceneUtility.a(this.o);
        }
        RecommendSceneCreator.a(this.o, (RecommendSceneCreator.SaveSceneCallback) new RecommendSceneCreator.SaveSceneCallback() {
            public void onSaveLocalFail() {
                if (LightActionStartActivity.this.isValid()) {
                    LightActionStartActivity.this.l.dismiss();
                }
                ToastUtil.a((int) R.string.save_fail);
            }

            public void onSaveCloudSuccess(boolean z) {
                if (LightActionStartActivity.this.isValid()) {
                    LightActionStartActivity.this.l.dismiss();
                }
                LightActionStartActivity.this.a((String) null);
            }

            public void onSaveCloudFail(int i, String str) {
                if (LightActionStartActivity.this.isValid()) {
                    LightActionStartActivity.this.l.dismiss();
                }
                if (i == -9000) {
                    ToastUtil.a((int) R.string.smarthome_scene_getway_offline);
                } else {
                    ToastUtil.a((int) R.string.save_fail);
                }
            }
        });
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.listitem_timespan) {
            StatClick statClick = STAT.d;
            String str = this.q.model;
            statClick.p(str, this.p + "");
            Intent intent = new Intent(this, SceneCreateTimeEdit2Activity.class);
            intent.putExtra("is_from_recommend", true);
            startActivityForResult(intent, 1011);
        } else if (id == R.id.module_a_3_return_btn) {
            finish();
        } else if (id != R.id.tv_enable) {
            switch (id) {
                case R.id.listitem_condition_select:
                    Intent intent2 = new Intent(this, LightConditionActivity.class);
                    intent2.putExtra("home_id", this.r.j());
                    intent2.putExtra("device_model", this.q.model);
                    intent2.putExtra("sr_id", this.p);
                    intent2.putExtra("is_need_ble", this.u);
                    startActivityForResult(intent2, 1002);
                    return;
                case R.id.listitem_delay_minute:
                    StatClick statClick2 = STAT.d;
                    String str2 = this.q.model;
                    statClick2.o(str2, this.p + "");
                    Intent intent3 = new Intent(this, SmartHomeSceneTimerDelay.class);
                    intent3.putExtra("is_from_recommend", true);
                    intent3.putExtra("delay_action_pos", 1);
                    startActivityForResult(intent3, 1);
                    return;
                case R.id.listitem_delay_payload:
                    this.m.setChecked(!this.m.isChecked());
                    a(this.m.isChecked());
                    return;
                default:
                    return;
            }
        } else {
            this.o.n = !this.o.n;
            if (this.o.n && TextUtils.isEmpty(this.o.f)) {
                StatClick statClick3 = STAT.d;
                String str3 = this.q.model;
                statClick3.s(str3, this.p + "");
            }
            f();
        }
    }

    public Map<String, List<SceneApi.Action>> getSelectionActions(Map<Integer, SceneApi.Action> map) {
        HashMap hashMap = new HashMap();
        int optInt = RecommendSceneCreator.a().f21963a.mActionList.get(0).modelListJobj.optInt(this.q.model);
        SceneApi.Action action = map.get(Integer.valueOf(optInt));
        if (action != null) {
            action.f = optInt;
            action.e = this.q.model;
            action.f21521a = 0;
            action.g.e = this.q.did;
            ArrayList arrayList = new ArrayList();
            arrayList.add(action);
            hashMap.put(this.q.name, arrayList);
            int optInt2 = RecommendSceneCreator.a().f21963a.mActionList.get(1).modelListJobj.optInt(this.q.model);
            SceneApi.Action action2 = map.get(Integer.valueOf(optInt2));
            if (action2 != null) {
                action2.f = optInt2;
                action2.e = this.q.model;
                action2.f21521a = 0;
                action2.g.e = this.q.did;
                arrayList.add(action2);
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
                        List<Device> a2 = a(next, this.r);
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

    public List<SceneApi.Action> getAdditionActions() {
        ArrayList arrayList = new ArrayList();
        if (this.o != null && this.o.k != null && this.o.k.size() == 3) {
            arrayList.add(this.o.k.get(1));
            arrayList.add(this.o.k.get(2));
        } else if (d() != null) {
            arrayList.addAll(d());
        }
        return arrayList;
    }

    public List<SceneApi.Condition> getAdditionConditions() {
        return new ArrayList();
    }
}
