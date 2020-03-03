package com.xiaomi.smarthome.scenenew.actiivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
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
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.light.group.LightGroupMemberUpdateActivity;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scenenew.actiivity.PluginRecommendSceneActivity;
import com.xiaomi.smarthome.scenenew.manager.PluginRecommendSceneManager;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator;
import com.xiaomi.smarthome.scenenew.model.PackagingCondition;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PluginRecommendSceneActivity extends BaseActivity implements View.OnClickListener {
    public static final String EXTRA_DID = "did";
    public static final String EXTRA_IS_FROM_PLUGIN = "is_from_plugin";
    public static final String EXTRA_SRID = "sr_id";
    public static final String KEY_FIRST_CREATE_RECOMMEND = "first_in_recommend_scene_";
    public static final int REQUEST_ACTION = 1010;
    private static Set<Integer> e = new HashSet();
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Device f21822a = null;
    /* access modifiers changed from: private */
    public Home b = null;
    /* access modifiers changed from: private */
    public int c;
    /* access modifiers changed from: private */
    public int d = -2;
    /* access modifiers changed from: private */
    public List<SceneApi.SmartHomeScene> f = new ArrayList();
    /* access modifiers changed from: private */
    public List<UIData> g = new ArrayList();
    /* access modifiers changed from: private */
    public SceneApi.SmartHomeScene h;
    /* access modifiers changed from: private */
    public boolean i = false;
    /* access modifiers changed from: private */
    public boolean j = false;
    private RecyclerView k;
    private MyAdapter l;
    private View m;
    private View n;
    /* access modifiers changed from: private */
    public TextView o;
    /* access modifiers changed from: private */
    public XQProgressDialog p;

    public static class UIData {

        /* renamed from: a  reason: collision with root package name */
        public boolean f21831a = false;
        public SceneApi.SmartHomeScene b;
        public String c;
        public List<SceneApi.Condition> d;
    }

    static {
        e.add(1000);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_plugin_recommend_scene);
        this.c = getIntent().getIntExtra("sr_id", -1);
        String stringExtra = getIntent().getStringExtra("did");
        if (!TextUtils.isEmpty(stringExtra)) {
            this.f21822a = SmartHomeDeviceManager.a().b(stringExtra);
            this.b = HomeManager.a().q(stringExtra);
        }
        if (getIntent().getBooleanExtra(EXTRA_IS_FROM_PLUGIN, false)) {
            STAT.d.aC(this.f21822a == null ? "" : this.f21822a.model);
        }
        if (this.c <= 0 || this.f21822a == null) {
            finish();
            return;
        }
        if (getIntent().getBooleanExtra("is_from_home", false)) {
            STAT.e.m(this.f21822a.model, "plugin");
        } else {
            STAT.e.m(this.f21822a.model, "gt");
        }
        this.o = (TextView) findViewById(R.id.module_a_3_return_title);
        this.k = (RecyclerView) findViewById(R.id.recycler_view);
        this.k.setLayoutManager(new LinearLayoutManager(this));
        this.l = new MyAdapter();
        this.k.setAdapter(this.l);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(this);
        findViewById(R.id.module_a_3_right_btn).setOnClickListener(this);
        ((ImageView) findViewById(R.id.module_a_3_right_btn)).setImageResource(R.drawable.btn_notice);
        if (RecommendSceneCreator.a().f21963a == null || TextUtils.isEmpty(RecommendSceneCreator.a().f21963a.intro)) {
            this.o.setText(R.string.setting);
        } else {
            this.o.setText(getString(R.string.plugin_rec_setting_title, new Object[]{RecommendSceneCreator.a().f21963a.intro}));
        }
        c();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        RecommendSceneCreator.a().c();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, @Nullable Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i3 == -1 && i2 == 1010) {
            a();
        }
    }

    private void a() {
        SceneApi.SmartHomeScene smartHomeScene = RecommendSceneCreator.a().h;
        smartHomeScene.i = this.c;
        smartHomeScene.t = false;
        smartHomeScene.n = true;
        smartHomeScene.o = false;
        ((SceneApi.SHSceneItemPayloadCommon) this.h.k.get(0).g).j = null;
        if (smartHomeScene.l != null && smartHomeScene.l.size() > 0) {
            if (smartHomeScene.l.size() == 1) {
                smartHomeScene.q = 0;
            } else {
                smartHomeScene.q = 1;
            }
        }
        this.p = XQProgressDialog.a(this, (CharSequence) null, getResources().getString(R.string.smarthome_scene_saving_scene));
        RecommendSceneCreator.a(smartHomeScene, (RecommendSceneCreator.SaveSceneCallback) new RecommendSceneCreator.SaveSceneCallback() {
            public void onSaveLocalFail() {
                if (PluginRecommendSceneActivity.this.isValid()) {
                    PluginRecommendSceneActivity.this.p.dismiss();
                }
            }

            public void onSaveCloudSuccess(boolean z) {
                PluginRecommendSceneActivity.this.c();
            }

            public void onSaveCloudFail(int i, String str) {
                if (PluginRecommendSceneActivity.this.isValid()) {
                    PluginRecommendSceneActivity.this.p.dismiss();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void b() {
        if (this.m == null) {
            this.m = ((ViewStub) findViewById(R.id.guide)).inflate();
        }
        if (!SharePrefsManager.b((Context) this, CoreApi.a().s() + this.c, KEY_FIRST_CREATE_RECOMMEND, true)) {
            this.m.findViewById(R.id.go_setting).setVisibility(8);
        }
        SharePrefsManager.a((Context) this, CoreApi.a().s() + this.c, KEY_FIRST_CREATE_RECOMMEND, false);
        this.m.setVisibility(0);
        this.m.findViewById(R.id.close).setOnClickListener(this);
        this.m.findViewById(R.id.go_setting).setOnClickListener(this);
        this.m.setOnClickListener(this);
        Resources resources = getResources();
        int identifier = resources.getIdentifier("ic_plugin_rec_scene_guide_" + this.c, "drawable", SHApplication.getAppContext().getPackageName());
        if (identifier > 0) {
            ((SimpleDraweeView) this.m.findViewById(R.id.tips_gif)).setImageResource(identifier);
        }
        if (RecommendSceneCreator.a().f21963a != null && !TextUtils.isEmpty(RecommendSceneCreator.a().f21963a.intro)) {
            ((TextView) this.m.findViewById(R.id.title)).setText(RecommendSceneCreator.a().f21963a.intro);
        }
        if (this.c == 1000) {
            ((TextView) this.m.findViewById(R.id.rec_desc)).setText(R.string.plugin_rec_guide_lockspeaker);
            this.m.findViewById(R.id.rec_desc).setVisibility(0);
        } else if (!TextUtils.isEmpty(RecommendSceneCreator.a().f21963a.cardDesc)) {
            ((TextView) this.m.findViewById(R.id.rec_desc)).setText(RecommendSceneCreator.a().f21963a.cardDesc);
            this.m.findViewById(R.id.rec_desc).setVisibility(0);
        } else {
            this.m.findViewById(R.id.rec_desc).setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        RecommendSceneCreator.a().c();
        e();
        d();
        if (e.contains(Integer.valueOf(this.c))) {
            this.d = -1;
            f();
        }
    }

    private void d() {
        PluginRecommendSceneManager.a().b(this.f21822a.did, this.c, new AsyncCallback<PluginRecommendSceneInfo, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(PluginRecommendSceneInfo pluginRecommendSceneInfo) {
                if (pluginRecommendSceneInfo != null) {
                    int i = 0;
                    while (true) {
                        if (i >= pluginRecommendSceneInfo.mSceneItems.size()) {
                            break;
                        }
                        String str = pluginRecommendSceneInfo.mSceneItems.get(i).sr_id;
                        if (TextUtils.equals(str, PluginRecommendSceneActivity.this.c + "")) {
                            RecommendSceneCreator.a().f21963a = pluginRecommendSceneInfo.mSceneItems.get(i);
                            break;
                        }
                        i++;
                    }
                    if (RecommendSceneCreator.a().f21963a != null) {
                        PluginRecommendSceneActivity pluginRecommendSceneActivity = PluginRecommendSceneActivity.this;
                        if (SharePrefsManager.b((Context) pluginRecommendSceneActivity, CoreApi.a().s() + PluginRecommendSceneActivity.this.c, PluginRecommendSceneActivity.KEY_FIRST_CREATE_RECOMMEND, true)) {
                            PluginRecommendSceneActivity.this.b();
                        }
                        if (RecommendSceneCreator.a().f21963a == null || TextUtils.isEmpty(RecommendSceneCreator.a().f21963a.intro)) {
                            PluginRecommendSceneActivity.this.o.setText(R.string.setting);
                        } else {
                            PluginRecommendSceneActivity.this.o.setText(PluginRecommendSceneActivity.this.getString(R.string.plugin_rec_setting_title, new Object[]{RecommendSceneCreator.a().f21963a.intro}));
                        }
                        if (RecommendSceneCreator.a().f21963a.mConditionList != null && pluginRecommendSceneInfo.mConditionScIds != null) {
                            for (PluginRecommendSceneInfo.ConditionActionItem next : RecommendSceneCreator.a().f21963a.mConditionList) {
                                if (next.modelListJobj == null) {
                                    break;
                                }
                                Iterator<String> keys = next.modelListJobj.keys();
                                while (keys.hasNext()) {
                                    int optInt = next.modelListJobj.optInt(keys.next());
                                    if (!RecommendSceneCreator.a().c.containsKey(Integer.valueOf(optInt)) && optInt > 0) {
                                        JSONObject jSONObject = pluginRecommendSceneInfo.mConditionScIds;
                                        JSONObject optJSONObject = jSONObject.optJSONObject(optInt + "");
                                        if (optJSONObject != null) {
                                            try {
                                                SceneApi.Condition a2 = SceneApi.Condition.a(optJSONObject);
                                                a2.c.k = optInt;
                                                RecommendSceneCreator.a().c.put(Integer.valueOf(optInt), a2);
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (RecommendSceneCreator.a().f21963a.mActionList != null && pluginRecommendSceneInfo.mActionSaIds != null) {
                            for (PluginRecommendSceneInfo.ConditionActionItem next2 : RecommendSceneCreator.a().f21963a.mActionList) {
                                if (next2.modelListJobj == null) {
                                    break;
                                }
                                Iterator<String> keys2 = next2.modelListJobj.keys();
                                while (keys2.hasNext()) {
                                    String next3 = keys2.next();
                                    int optInt2 = next2.modelListJobj.optInt(next3);
                                    if (!RecommendSceneCreator.a().d.containsKey(Integer.valueOf(optInt2)) && optInt2 > 0) {
                                        JSONObject jSONObject2 = pluginRecommendSceneInfo.mActionSaIds;
                                        JSONObject optJSONObject2 = jSONObject2.optJSONObject(optInt2 + "");
                                        if (optJSONObject2 != null) {
                                            try {
                                                SceneApi.Action a3 = SceneApi.Action.a(optJSONObject2);
                                                if (a3.f21521a == 0) {
                                                    SceneApi.SHSceneItemPayload sHSceneItemPayload = a3.g;
                                                    sHSceneItemPayload.c = next3 + "." + a3.g.c;
                                                    a3.g.h = 0;
                                                }
                                                RecommendSceneCreator.a().d.put(Integer.valueOf(optInt2), a3);
                                            } catch (JSONException e2) {
                                                e2.printStackTrace();
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (RecommendSceneCreator.a().f21963a.mConditionList != null && RecommendSceneCreator.a().f21963a.mConditionList.size() > 0) {
                            int optInt3 = RecommendSceneCreator.a().f21963a.mConditionList.get(0).modelListJobj.optInt(PluginRecommendSceneActivity.this.f21822a.model);
                            if (optInt3 > 0) {
                                PluginRecommendSceneActivity.this.a(optInt3);
                            } else {
                                boolean unused = PluginRecommendSceneActivity.this.j = true;
                                if (PluginRecommendSceneActivity.this.i && PluginRecommendSceneActivity.this.j) {
                                    PluginRecommendSceneActivity.this.g();
                                }
                            }
                        }
                        if (RecommendSceneCreator.a().f21963a.mActionList != null && RecommendSceneCreator.a().f21963a.mActionList.size() > 0) {
                            for (int i2 = 0; i2 < RecommendSceneCreator.a().f21963a.mActionList.size(); i2++) {
                                JSONObject jSONObject3 = RecommendSceneCreator.a().f21963a.mActionList.get(i2).modelListJobj;
                                if (jSONObject3 != null) {
                                    Iterator<String> keys3 = jSONObject3.keys();
                                    while (keys3.hasNext()) {
                                        String next4 = keys3.next();
                                        List access$1100 = PluginRecommendSceneActivity.this.a(next4, PluginRecommendSceneActivity.this.b);
                                        if (!(access$1100 == null || access$1100.size() == 0)) {
                                            int optInt4 = jSONObject3.optInt(next4);
                                            SceneApi.Action action = RecommendSceneCreator.a().d.get(Integer.valueOf(optInt4));
                                            if (action != null && action.f21521a == 0) {
                                                List access$1200 = PluginRecommendSceneActivity.this.a(action, (List<Device>) access$1100);
                                                Map<String, List<SceneApi.Action>> map = RecommendSceneCreator.a().f;
                                                map.put(optInt4 + "", access$1200);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public List<Device> a(String str, Home home) {
        ArrayList arrayList = new ArrayList();
        for (Device next : HomeManager.a().o(home.j())) {
            if (TextUtils.equals(str, next.model)) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void a(final int i2) {
        PluginRecommendSceneManager a2 = PluginRecommendSceneManager.a();
        String str = this.f21822a.did;
        a2.a(str, i2 + "", (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                SceneApi.Condition condition;
                PackagingCondition packagingCondition;
                JSONArray optJSONArray = jSONObject.optJSONArray(LightGroupMemberUpdateActivity.KEY_MEMBER);
                if (!(optJSONArray == null || optJSONArray.length() <= 0 || (condition = RecommendSceneCreator.a().c.get(Integer.valueOf(i2))) == null)) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        try {
                            packagingCondition = PackagingCondition.a(optJSONArray.getJSONObject(i));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            packagingCondition = null;
                        }
                        if (packagingCondition != null) {
                            List access$1300 = PluginRecommendSceneActivity.this.a(condition, packagingCondition.b);
                            if (access$1300 == null || access$1300.size() <= 0) {
                                RecommendSceneCreator.a().e.put(packagingCondition.f21984a, (Object) null);
                            } else {
                                RecommendSceneCreator.a().e.put(packagingCondition.f21984a, access$1300);
                            }
                        }
                    }
                }
                boolean unused = PluginRecommendSceneActivity.this.j = true;
                if (PluginRecommendSceneActivity.this.i && PluginRecommendSceneActivity.this.j) {
                    PluginRecommendSceneActivity.this.g();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public List<SceneApi.Action> a(SceneApi.Action action, List<Device> list) {
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
                arrayList.add(a3);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public List<SceneApi.Condition> a(SceneApi.Condition condition, List<JSONObject> list) {
        ArrayList arrayList = new ArrayList();
        if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.DEVICE) {
            if (condition.c == null) {
                condition.c = new SceneApi.ConditionDeviceCommon();
            }
            try {
                JSONObject a2 = condition.a();
                for (int i2 = 0; i2 < list.size(); i2++) {
                    SceneApi.Condition a3 = SceneApi.Condition.a(a2);
                    a3.c.f21523a = this.f21822a.did;
                    a3.c.c = this.f21822a.name;
                    a3.c.d = this.f21822a.model;
                    if (!TextUtils.isEmpty(list.get(i2).optString("key"))) {
                        a3.c.j = list.get(i2).optString("key");
                    }
                    if (!TextUtils.isEmpty(list.get(i2).optString("did"))) {
                        a3.c.f21523a = list.get(i2).optString("did");
                    }
                    if (!TextUtils.isEmpty(list.get(i2).optString("name"))) {
                        a3.c.b = list.get(i2).optString("name");
                    }
                    if (list.get(i2).opt("keyid") != null) {
                        if (this.c == 1000) {
                            ((SceneApi.ConditionDeviceCommon) a3.c).l = "0002" + list.get(i2).opt("keyid");
                        } else {
                            ((SceneApi.ConditionDeviceCommon) a3.c).l = list.get(i2).opt("keyid");
                        }
                    }
                    if (!TextUtils.isEmpty(list.get(i2).optString("deviceName"))) {
                        a3.c.c = list.get(i2).optString("deviceName");
                    }
                    if (list.get(i2).optInt("tempId") > 0) {
                        a3.c.k = list.get(i2).optInt("tempId");
                    }
                    if (list.get(i2).optInt("sc_id") > 0) {
                        a3.c.k = list.get(i2).optInt("sc_id");
                    }
                    if (!TextUtils.isEmpty(list.get(i2).optString("extra"))) {
                        ((SceneApi.ConditionDeviceCommon) a3.c).m = list.get(i2).optString("extra");
                    }
                    ((SceneApi.ConditionDeviceCommon) a3.c).e = -1;
                    ((SceneApi.ConditionDeviceCommon) a3.c).g = -1;
                    ((SceneApi.ConditionDeviceCommon) a3.c).f = -1;
                    ((SceneApi.ConditionDeviceCommon) a3.c).h = -1;
                    arrayList.add(a3);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        return arrayList;
    }

    private void e() {
        PluginRecommendSceneManager.a().a(this.f21822a.did, this.c, (AsyncCallback<List<SceneApi.SmartHomeScene>, Error>) new AsyncCallback<List<SceneApi.SmartHomeScene>, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(List<SceneApi.SmartHomeScene> list) {
                PluginRecommendSceneActivity.this.f.clear();
                PluginRecommendSceneActivity.this.f.addAll(list);
                boolean unused = PluginRecommendSceneActivity.this.i = true;
                if (PluginRecommendSceneActivity.this.i && PluginRecommendSceneActivity.this.j) {
                    PluginRecommendSceneActivity.this.g();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void f() {
        PluginRecommendSceneManager.a().a(this.f21822a.did, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (jSONObject != null) {
                    JSONArray optJSONArray = jSONObject.optJSONArray(PluginRecommendSceneActivity.this.f21822a.did);
                    if (optJSONArray == null || optJSONArray.length() == 0) {
                        int unused = PluginRecommendSceneActivity.this.d = 0;
                    } else {
                        int unused2 = PluginRecommendSceneActivity.this.d = 1;
                    }
                } else {
                    int unused3 = PluginRecommendSceneActivity.this.d = 0;
                }
            }

            public void onFailure(Error error) {
                int unused = PluginRecommendSceneActivity.this.d = -1;
            }
        });
    }

    /* access modifiers changed from: private */
    public void g() {
        this.g.clear();
        ArrayList<String> arrayList = new ArrayList<>();
        boolean z = true;
        for (String next : RecommendSceneCreator.a().e.keySet()) {
            List<SceneApi.Condition> list = RecommendSceneCreator.a().e.get(next);
            UIData uIData = new UIData();
            SceneApi.SmartHomeScene a2 = a(list);
            if (a2 != null) {
                uIData.b = a2;
                uIData.b.l = list;
                if (uIData.b.k.size() > 0 && uIData.b.k.get(0).f21521a == 0 && (uIData.b.k.get(0).g instanceof SceneApi.SHSceneItemPayloadCommon)) {
                    SceneApi.SHSceneItemPayloadCommon sHSceneItemPayloadCommon = (SceneApi.SHSceneItemPayloadCommon) uIData.b.k.get(0).g;
                    Map<String, List<SceneApi.Action>> map = RecommendSceneCreator.a().f;
                    List list2 = map.get(sHSceneItemPayloadCommon.i + "");
                    if (list2 != null && list2.size() > 0) {
                        for (int i2 = 0; i2 < list2.size(); i2++) {
                            if (((SceneApi.Action) list2.get(i2)).g instanceof SceneApi.SHSceneItemPayloadCommon) {
                                sHSceneItemPayloadCommon.j = ((SceneApi.SHSceneItemPayloadCommon) ((SceneApi.Action) list2.get(i2)).g).j;
                            }
                        }
                    }
                }
                uIData.c = next;
                if (z) {
                    uIData.f21831a = true;
                    z = false;
                }
                this.g.add(uIData);
                arrayList.add(next);
            }
        }
        for (String remove : arrayList) {
            RecommendSceneCreator.a().e.remove(remove);
        }
        boolean z2 = true;
        for (String next2 : RecommendSceneCreator.a().e.keySet()) {
            UIData uIData2 = new UIData();
            if (z2) {
                uIData2.f21831a = true;
                z2 = false;
            }
            uIData2.c = next2;
            uIData2.d = RecommendSceneCreator.a().e.get(next2);
            this.g.add(uIData2);
        }
        if (this.p != null && this.p.isShowing()) {
            this.p.dismiss();
        }
        this.l.notifyDataSetChanged();
        if (this.l.getItemCount() <= 0) {
            if (this.n == null) {
                this.n = ((ViewStub) findViewById(R.id.empty_view)).inflate();
            }
            this.n.findViewById(R.id.common_white_empty_view).setVisibility(0);
            if (this.c == 1000) {
                ((TextView) this.n.findViewById(R.id.common_white_empty_text)).setText(R.string.plugin_rec_con_no_member);
            } else {
                ((TextView) this.n.findViewById(R.id.common_white_empty_text)).setText(R.string.no_data_tips);
            }
            Resources resources = getResources();
            int identifier = resources.getIdentifier("ic_plugin_rec_condition_null_" + this.c, "drawable", SHApplication.getAppContext().getPackageName());
            if (identifier > 0) {
                ((ImageView) this.n.findViewById(R.id.empty_icon)).setImageResource(identifier);
            }
        } else if (this.n != null) {
            this.n.findViewById(R.id.common_white_empty_view).setVisibility(8);
        }
    }

    private SceneApi.SmartHomeScene a(List<SceneApi.Condition> list) {
        if (!(list == null || this.f == null || this.f.size() <= 0)) {
            for (SceneApi.SmartHomeScene next : this.f) {
                boolean z = false;
                if (next.l != null) {
                    for (SceneApi.Condition next2 : next.l) {
                        Iterator<SceneApi.Condition> it = list.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (isLockSpeakerSceneCondition(next2, it.next())) {
                                    z = true;
                                    continue;
                                    break;
                                }
                            } else {
                                break;
                            }
                        }
                        if (z) {
                            break;
                        }
                    }
                    if (z) {
                        for (SceneApi.Action next3 : next.k) {
                            if (next3.f21521a == 0 && (next3.g instanceof SceneApi.SHSceneItemPayloadCommon)) {
                                String str = ((SceneApi.SHSceneItemPayloadCommon) next3.g).e;
                                if (SmartHomeDeviceManager.a().b(str) != null && TextUtils.equals(HomeManager.a().q(str).j(), this.b.j())) {
                                    return next;
                                }
                            }
                        }
                        continue;
                    } else {
                        continue;
                    }
                }
            }
        }
        return null;
    }

    public boolean isLockSpeakerSceneCondition(SceneApi.Condition condition, SceneApi.Condition condition2) {
        if (condition.f21522a != condition2.f21522a || !(condition.c instanceof SceneApi.ConditionDeviceCommon) || !(condition2.c instanceof SceneApi.ConditionDeviceCommon)) {
            return false;
        }
        SceneApi.ConditionDeviceCommon conditionDeviceCommon = (SceneApi.ConditionDeviceCommon) condition.c;
        SceneApi.ConditionDeviceCommon conditionDeviceCommon2 = (SceneApi.ConditionDeviceCommon) condition2.c;
        if (conditionDeviceCommon.l.equals(conditionDeviceCommon2.l) && TextUtils.equals(conditionDeviceCommon.f21523a, conditionDeviceCommon2.f21523a)) {
            return true;
        }
        return false;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.close || id == R.id.go_setting) {
            this.m.setVisibility(8);
        } else if (id == R.id.module_a_3_return_btn) {
            finish();
        } else if (id == R.id.module_a_3_right_btn) {
            b();
        }
    }

    public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        public long getItemId(int i) {
            return (long) i;
        }

        public MyAdapter() {
        }

        @NonNull
        /* renamed from: a */
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MyViewHolder(LayoutInflater.from(PluginRecommendSceneActivity.this).inflate(R.layout.item_plugin_recommend_scene, (ViewGroup) null));
        }

        /* renamed from: a */
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
            UIData uIData = (UIData) PluginRecommendSceneActivity.this.g.get(i);
            if (uIData.f21831a) {
                a(myViewHolder, uIData.b != null, i != 0);
            }
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(uIData.c)) {
                sb.append(uIData.c);
            }
            if (uIData.b != null) {
                myViewHolder.g.setVisibility(0);
                myViewHolder.d.setVisibility(0);
                myViewHolder.e.setVisibility(8);
                myViewHolder.f.setVisibility(8);
                myViewHolder.d.setChecked(uIData.b.n);
                if (uIData.b.n) {
                    myViewHolder.h.setTextColor(PluginRecommendSceneActivity.this.getResources().getColor(R.color.black));
                } else {
                    myViewHolder.h.setTextColor(PluginRecommendSceneActivity.this.getResources().getColor(R.color.class_D));
                }
                myViewHolder.d.setOnPerformCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(uIData) {
                    private final /* synthetic */ PluginRecommendSceneActivity.UIData f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        PluginRecommendSceneActivity.MyAdapter.this.a(this.f$1, compoundButton, z);
                    }
                });
                myViewHolder.h.setText(a(uIData));
            } else {
                if (uIData.d == null || uIData.d.size() == 0) {
                    sb.append(PluginRecommendSceneActivity.this.getString(R.string.plugin_rec_con_mem_null_finger));
                    myViewHolder.c.setTextColor(PluginRecommendSceneActivity.this.getResources().getColor(R.color.class_D));
                    myViewHolder.h.setTextColor(PluginRecommendSceneActivity.this.getResources().getColor(R.color.class_D));
                    myViewHolder.e.setVisibility(8);
                    myViewHolder.f.setVisibility(8);
                } else {
                    myViewHolder.c.setTextColor(PluginRecommendSceneActivity.this.getResources().getColor(R.color.black));
                    myViewHolder.h.setTextColor(PluginRecommendSceneActivity.this.getResources().getColor(R.color.black));
                    myViewHolder.e.setVisibility(0);
                    myViewHolder.f.setVisibility(0);
                }
                myViewHolder.g.setVisibility(8);
                myViewHolder.d.setVisibility(8);
                myViewHolder.h.setText("");
                if (i == PluginRecommendSceneActivity.this.g.size() - 1) {
                    myViewHolder.k.setVisibility(0);
                } else {
                    myViewHolder.k.setVisibility(8);
                }
            }
            myViewHolder.c.setText(sb.toString());
            myViewHolder.itemView.setOnClickListener(new View.OnClickListener(uIData) {
                private final /* synthetic */ PluginRecommendSceneActivity.UIData f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    PluginRecommendSceneActivity.MyAdapter.this.a(this.f$1, view);
                }
            });
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(UIData uIData, final CompoundButton compoundButton, final boolean z) {
            SceneApi.SmartHomeScene unused = PluginRecommendSceneActivity.this.h = uIData.b;
            if (PluginRecommendSceneActivity.this.h != null) {
                PluginRecommendSceneActivity.this.h.n = z;
                XQProgressDialog unused2 = PluginRecommendSceneActivity.this.p = XQProgressDialog.a(PluginRecommendSceneActivity.this, (CharSequence) null, PluginRecommendSceneActivity.this.getResources().getString(R.string.smarthome_scene_saving_scene));
                RecommendSceneCreator.a(PluginRecommendSceneActivity.this.h, (RecommendSceneCreator.SaveSceneCallback) new RecommendSceneCreator.SaveSceneCallback() {
                    public void onSaveLocalFail() {
                        if (PluginRecommendSceneActivity.this.isValid()) {
                            PluginRecommendSceneActivity.this.p.dismiss();
                            compoundButton.setChecked(!z);
                            MyAdapter.this.notifyDataSetChanged();
                        }
                    }

                    public void onSaveCloudSuccess(boolean z) {
                        if (PluginRecommendSceneActivity.this.isValid()) {
                            PluginRecommendSceneActivity.this.p.dismiss();
                            MyAdapter.this.notifyDataSetChanged();
                            ToastUtil.a((int) R.string.save_success);
                        }
                    }

                    public void onSaveCloudFail(int i, String str) {
                        if (PluginRecommendSceneActivity.this.isValid()) {
                            PluginRecommendSceneActivity.this.p.dismiss();
                            compoundButton.setChecked(!z);
                            MyAdapter.this.notifyDataSetChanged();
                        }
                    }
                });
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(UIData uIData, View view) {
            if (PluginRecommendSceneActivity.this.d == -1) {
                PluginRecommendSceneActivity.this.f();
            } else if (uIData.b != null || (uIData.d != null && uIData.d.size() != 0)) {
                SceneApi.SmartHomeScene unused = PluginRecommendSceneActivity.this.h = uIData.b;
                if (PluginRecommendSceneActivity.this.h == null) {
                    SceneApi.SmartHomeScene unused2 = PluginRecommendSceneActivity.this.h = new SceneApi.SmartHomeScene();
                    PluginRecommendSceneActivity.this.h.l = uIData.d;
                }
                RecommendSceneCreator.a().h = PluginRecommendSceneActivity.this.h;
                SceneApi.SmartHomeScene access$1800 = PluginRecommendSceneActivity.this.h;
                access$1800.g = uIData.c + RecommendSceneCreator.a().f21963a.intro;
                Intent intent = new Intent(PluginRecommendSceneActivity.this, PluginRecommendSceneActionActivity.class);
                intent.putExtra(PluginRecommendSceneActionActivity.KEY_CONNECT_BLE, PluginRecommendSceneActivity.this.d);
                intent.putExtra("did", PluginRecommendSceneActivity.this.f21822a.did);
                if (PluginRecommendSceneActivity.this.b != null) {
                    intent.putExtra("home_id", PluginRecommendSceneActivity.this.b.j());
                }
                PluginRecommendSceneActivity.this.startActivityForResult(intent, 1010);
            }
        }

        private void a(MyViewHolder myViewHolder, boolean z, boolean z2) {
            if (myViewHolder.b == null) {
                myViewHolder.b = myViewHolder.f21830a.inflate();
                myViewHolder.i = (TextView) myViewHolder.b.findViewById(R.id.title);
                myViewHolder.j = myViewHolder.b.findViewById(R.id.divider);
            }
            StringBuilder sb = new StringBuilder();
            if (RecommendSceneCreator.a().f21963a != null && !TextUtils.isEmpty(RecommendSceneCreator.a().f21963a.entryDesc)) {
                sb.append(RecommendSceneCreator.a().f21963a.intro);
                sb.append("-");
            }
            if (z) {
                sb.append(PluginRecommendSceneActivity.this.getString(R.string.rec_setted));
            } else {
                sb.append(PluginRecommendSceneActivity.this.getString(R.string.unset));
            }
            myViewHolder.i.setText(sb.toString());
            if (z2) {
                myViewHolder.j.setVisibility(0);
            } else {
                myViewHolder.j.setVisibility(8);
            }
        }

        private String a(UIData uIData) {
            Object obj;
            JSONArray jSONArray;
            StringBuilder sb = new StringBuilder();
            if (PluginRecommendSceneActivity.this.c == 1000) {
                sb.append(PluginRecommendSceneActivity.this.getString(R.string.plugin_rec_action_hint_lockspeaker));
                if (uIData.b != null && uIData.b.k != null && uIData.b.k.size() > 0 && uIData.b.k.get(0).f21521a == 0 && (uIData.b.k.get(0).g instanceof SceneApi.SHSceneItemPayloadCommon) && (obj = ((SceneApi.SHSceneItemPayloadCommon) uIData.b.k.get(0).g).f) != null) {
                    if ((obj instanceof String) && !TextUtils.isEmpty((String) obj)) {
                        sb.append(obj);
                    } else if (obj instanceof JSONObject) {
                        JSONObject jSONObject = (JSONObject) obj;
                        if (jSONObject != null && jSONObject.has("text") && !TextUtils.isEmpty(jSONObject.optString("text"))) {
                            sb.append(jSONObject.optString("text"));
                        }
                    } else if ((obj instanceof JSONArray) && (jSONArray = (JSONArray) obj) != null && jSONArray.length() == 3 && !TextUtils.isEmpty(jSONArray.optString(0))) {
                        sb.append(jSONArray.optString(0));
                    }
                }
            } else if (RecommendSceneCreator.a().f21963a != null && !TextUtils.isEmpty(RecommendSceneCreator.a().f21963a.intro)) {
                sb.append(RecommendSceneCreator.a().f21963a.intro);
            }
            return sb.toString();
        }

        public int getItemCount() {
            if (PluginRecommendSceneActivity.this.g == null) {
                return 0;
            }
            return PluginRecommendSceneActivity.this.g.size();
        }
    }

    private static class MyViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public ViewStub f21830a;
        public View b;
        public TextView c;
        public SwitchButton d;
        public View e;
        public View f;
        public View g;
        public TextView h;
        public TextView i;
        public View j;
        public View k;

        public MyViewHolder(@NonNull View view) {
            super(view);
            this.f21830a = (ViewStub) view.findViewById(R.id.group_title);
            this.c = (TextView) view.findViewById(R.id.condition_name);
            this.d = (SwitchButton) view.findViewById(R.id.open_check);
            this.e = view.findViewById(R.id.go_setting_arrow);
            this.k = view.findViewById(R.id.divider1);
            this.f = view.findViewById(R.id.go_setting);
            this.g = view.findViewById(R.id.action_layout);
            this.h = (TextView) view.findViewById(R.id.action_name);
        }
    }
}
