package com.xiaomi.smarthome.scenenew.pluginrecommend;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.ChooseGatewayDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.runtime.util.DeviceCategory;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scenenew.manager.PluginRecommendSceneManager;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator;
import com.xiaomi.smarthome.scenenew.pluginrecommend.LightConditionActivity;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.stat.StatClick;
import com.xiaomi.smarthome.stat.StatPopUp;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LightConditionActivity extends BaseActivity implements View.OnClickListener {
    private static String r = "{\"1003\":{\"loock.lock.v6\":\"https://www.xiaomiyoupin.com/detail?gid=109790\",\"loock.lock.v5\":\"https://www.xiaomiyoupin.com/detail?gid=108055\",\"lumi.lock.mcn01\":\"https://www.xiaomiyoupin.com/detail?gid=105377\"},\"1001\":{\"lumi.sensor_motion.aq2\":\"https://www.xiaomiyoupin.com/detail?gid=730&pid=2651\",\"lumi.sensor_motion.v2\":\"https://www.xiaomiyoupin.com/detail?gid=103152&pid=15148\"},\"1002\":{\"lumi.sensor_magnet.v2\":\"https://www.xiaomiyoupin.com/detail?gid=101464\"},\"zigbee_gateway\":{\"lumi.gateway.v3\":\"https://www.xiaomiyoupin.com/detail?gid=103292\"},\"ble_gateway\":{\"xiaomi.wifispeaker.lx04\":\"https://www.xiaomiyoupin.com/detail?gid=105569\",\"zimi.clock.myk01\":\"https://www.xiaomiyoupin.com/detail?gid=103705\"}}";

    /* renamed from: a  reason: collision with root package name */
    private TextView f22021a;
    private View b;
    private View c;
    private TextView d;
    /* access modifiers changed from: private */
    public int e;
    /* access modifiers changed from: private */
    public Home f;
    private MyAdapter g;
    /* access modifiers changed from: private */
    public List<SceneApi.Condition> h = new ArrayList();
    /* access modifiers changed from: private */
    public Set<String> i = new HashSet();
    /* access modifiers changed from: private */
    public RecyclerView j;
    /* access modifiers changed from: private */
    public boolean k = false;
    private SharedPreferences l;
    private ImageView m;
    /* access modifiers changed from: private */
    public BuyAdapter n;
    /* access modifiers changed from: private */
    public String o;
    /* access modifiers changed from: private */
    public boolean p = true;
    /* access modifiers changed from: private */
    public boolean q = true;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.light_rec_scene_condition_activity);
        this.k = getIntent().getBooleanExtra("is_need_ble", false);
        this.e = getIntent().getIntExtra("sr_id", -1);
        this.f = HomeManager.a().j(getIntent().getStringExtra("home_id"));
        if (this.f == null) {
            this.f = HomeManager.a().m();
        }
        this.o = getIntent().getStringExtra("device_model");
        this.f22021a = (TextView) findViewById(R.id.module_a_3_return_title);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(this);
        if (RecommendSceneCreator.a().f21963a.mConditionList.size() > 0) {
            String str = RecommendSceneCreator.a().f21963a.mConditionList.get(0).name;
            TextView textView = this.f22021a;
            Object[] objArr = new Object[1];
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            objArr[0] = str;
            textView.setText(getString(R.string.light_rec_condition_name, objArr));
        } else {
            this.f22021a.setText(getString(R.string.light_rec_condition_name, new Object[]{""}));
        }
        if (RecommendSceneCreator.a().e != null && RecommendSceneCreator.a().e.size() > 0) {
            for (Map.Entry<String, List<SceneApi.Condition>> value : RecommendSceneCreator.a().e.entrySet()) {
                this.h.addAll((Collection) value.getValue());
            }
        }
        if (this.h.size() > 0) {
            StatPopUp statPopUp = STAT.e;
            String str2 = this.o;
            statPopUp.c(str2, this.e + "");
            if (this.k) {
                c();
            } else {
                b();
            }
        } else {
            a();
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        boolean z;
        if (this.c == null) {
            this.c = ((ViewStub) findViewById(R.id.vs_buy_guide)).inflate();
        }
        if (this.b != null) {
            this.b.setVisibility(8);
        }
        this.c.findViewById(R.id.common_white_empty_view).setBackgroundColor(Color.parseColor("#f7f7f7"));
        this.c.findViewById(R.id.common_white_empty_view).setVisibility(0);
        ((ImageView) this.c.findViewById(R.id.empty_icon)).setImageResource(R.drawable.ic_plug_rec_action_empty_1000);
        String string = getString(R.string.light_rec_non_condition_type0, new Object[]{RecommendSceneCreator.a().f21963a.mConditionList.get(0).name});
        this.j = (RecyclerView) this.c.findViewById(R.id.buy_list);
        this.j.setLayoutManager(new GridLayoutManager((Context) this, 3, 1, false));
        this.n = new BuyAdapter();
        this.j.setAdapter(this.n);
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.e + "");
        if (this.e == 1001 || this.e == 1002) {
            Iterator<Device> it = ChooseGatewayDevice.getGatewayDevices((String) null).iterator();
            while (true) {
                if (it.hasNext()) {
                    if (TextUtils.equals(HomeManager.a().q(it.next().did).j(), this.f.j())) {
                        z = true;
                        break;
                    }
                } else {
                    z = false;
                    break;
                }
            }
            if (!z) {
                this.p = false;
                arrayList.add("zigbee_gateway");
                string = getString(R.string.light_rec_non_condition_type1, new Object[]{RecommendSceneCreator.a().f21963a.mConditionList.get(0).name});
                StatPopUp statPopUp = STAT.e;
                String str = this.o;
                statPopUp.e(str, this.e + "");
            } else {
                this.p = true;
                StatPopUp statPopUp2 = STAT.e;
                String str2 = this.o;
                statPopUp2.d(str2, this.e + "");
            }
        } else if (this.e == 1003) {
            if (this.h.size() > 0) {
                arrayList.clear();
            } else {
                List<Device> a2 = RecommendSceneCreator.a(this.f.j());
                if (a2 == null || a2.size() == 0) {
                    this.p = false;
                    arrayList.add("ble_gateway");
                    string = getString(R.string.light_rec_non_condition_type2, new Object[]{RecommendSceneCreator.a().f21963a.mConditionList.get(0).name});
                    StatPopUp statPopUp3 = STAT.e;
                    String str3 = this.o;
                    statPopUp3.e(str3, this.e + "");
                } else {
                    this.p = true;
                    StatPopUp statPopUp4 = STAT.e;
                    String str4 = this.o;
                    statPopUp4.d(str4, this.e + "");
                }
            }
        }
        ((TextView) this.c.findViewById(R.id.common_white_empty_text)).setText(string);
        a(arrayList, new AsyncCallback<List<JSONObject>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<JSONObject> list) {
                LightConditionActivity.this.j.setVisibility(0);
                LightConditionActivity.this.n.a(list);
                LightConditionActivity.this.n.notifyDataSetChanged();
            }

            public void onFailure(Error error) {
                LightConditionActivity.this.j.setVisibility(8);
            }
        });
    }

    /* access modifiers changed from: private */
    public void b() {
        if (this.b == null) {
            this.b = ((ViewStub) findViewById(R.id.vs_condition_list)).inflate();
            this.d = (TextView) this.b.findViewById(R.id.tv_not_connect_ble);
            String string = getString(R.string.light_rec_condition_no_ble);
            int indexOf = string.indexOf("#start#");
            int indexOf2 = string.indexOf("#end#") - "#start#".length();
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append(string.replace("#start#", "").replace("#end#", ""));
            AnonymousClass2 r0 = new ClickableSpan() {
                public void onClick(View view) {
                    List<Device> a2 = RecommendSceneCreator.a(LightConditionActivity.this.f.j());
                    if (a2 == null || a2.size() == 0) {
                        StatPopUp statPopUp = STAT.e;
                        String access$400 = LightConditionActivity.this.o;
                        statPopUp.j(access$400, LightConditionActivity.this.e + "");
                        boolean unused = LightConditionActivity.this.q = true;
                        LightConditionActivity.this.a();
                        return;
                    }
                    StatPopUp statPopUp2 = STAT.e;
                    String access$4002 = LightConditionActivity.this.o;
                    statPopUp2.i(access$4002, LightConditionActivity.this.e + "");
                    Intent intent = new Intent(LightConditionActivity.this, LockDissConnectBleActivity.class);
                    intent.putExtra("home_id", LightConditionActivity.this.f.j());
                    LightConditionActivity.this.startActivity(intent);
                }

                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(LightConditionActivity.this.getResources().getColor(R.color.class_text_33));
                    textPaint.setUnderlineText(true);
                }
            };
            if (indexOf >= 0 && indexOf2 > 0) {
                try {
                    spannableStringBuilder.setSpan(r0, indexOf, indexOf2, 33);
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            this.d.setText(spannableStringBuilder);
            this.d.setMovementMethod(LinkMovementMethod.getInstance());
        }
        if (!this.k || this.i.size() <= 0) {
            this.d.setVisibility(8);
        } else {
            this.d.setVisibility(0);
            StatPopUp statPopUp = STAT.e;
            String str = this.o;
            statPopUp.h(str, this.e + "");
        }
        this.m = (ImageView) this.b.findViewById(R.id.loading);
        RecyclerView recyclerView = (RecyclerView) this.b.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.g = new MyAdapter();
        recyclerView.setAdapter(this.g);
        if (this.c != null) {
            this.c.setVisibility(8);
        }
        this.m.setVisibility(8);
    }

    private void c() {
        final ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < this.h.size(); i2++) {
            if (this.h.get(i2).c != null && a(this.h.get(i2).c.f21523a)) {
                arrayList.add(this.h.get(i2).c.f21523a);
            }
        }
        if (arrayList.size() > 0) {
            PluginRecommendSceneManager.a().a((List<String>) arrayList, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (jSONObject != null) {
                        for (int i = 0; i < arrayList.size(); i++) {
                            JSONArray optJSONArray = jSONObject.optJSONArray((String) arrayList.get(i));
                            if (optJSONArray == null || optJSONArray.length() == 0) {
                                LightConditionActivity.this.i.add(arrayList.get(i));
                            }
                        }
                    }
                    LightConditionActivity.this.b();
                }

                public void onFailure(Error error) {
                    LightConditionActivity.this.b();
                }
            });
        } else {
            b();
        }
    }

    public void onClick(View view) {
        if (view.getId() == R.id.module_a_3_return_btn) {
            finish();
        }
    }

    /* access modifiers changed from: private */
    public boolean a(String str) {
        Device b2 = SmartHomeDeviceManager.a().b(str);
        if (b2 != null && DeviceCategory.fromPid(b2.pid) == DeviceCategory.Bluetooth) {
            return true;
        }
        return false;
    }

    private void a(final List<String> list, final AsyncCallback<List<JSONObject>, Error> asyncCallback) {
        JSONObject jSONObject;
        String popCache = popCache("scene_rec_result_temp_youpin");
        ArrayList arrayList = new ArrayList();
        try {
            if (TextUtils.isEmpty(popCache)) {
                jSONObject = new JSONObject(r);
            } else {
                jSONObject = new JSONObject(popCache);
            }
            for (String next : list) {
                if (jSONObject.has(next)) {
                    arrayList.add(jSONObject.optJSONObject(next));
                }
            }
            if (asyncCallback != null) {
                asyncCallback.onSuccess(arrayList);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            try {
                JSONObject jSONObject2 = new JSONObject(r);
                for (String next2 : list) {
                    if (jSONObject2.has(next2)) {
                        arrayList.add(jSONObject2.optJSONObject(next2));
                    }
                }
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(arrayList);
                }
            } catch (JSONException e3) {
                e3.printStackTrace();
            }
        }
        PluginRecommendSceneManager.a().a((AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                ArrayList arrayList = new ArrayList();
                if (jSONObject != null) {
                    for (String str : list) {
                        if (jSONObject.has(str)) {
                            JSONObject optJSONObject = jSONObject.optJSONObject(str);
                            Iterator<String> keys = optJSONObject.keys();
                            while (keys.hasNext()) {
                                String next = keys.next();
                                try {
                                    JSONObject jSONObject2 = new JSONObject();
                                    jSONObject2.put(next, optJSONObject.optString(next));
                                    arrayList.add(jSONObject2);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    if (arrayList.size() > 0) {
                        LightConditionActivity.this.pushCache("scene_rec_result_temp_youpin", jSONObject.toString());
                    }
                }
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(arrayList);
                }
            }
        });
    }

    public void pushCache(String str, String str2) {
        if (this.l == null) {
            this.l = SHApplication.getAppContext().getSharedPreferences("recommend_scene_buy_guide", 0);
        }
        if (this.l != null) {
            SharedPreferences.Editor edit = this.l.edit();
            edit.putString(str, str2);
            edit.apply();
        }
    }

    public String popCache(String str) {
        if (this.l == null) {
            this.l = SHApplication.getAppContext().getSharedPreferences("recommend_scene_buy_guide", 0);
        }
        return this.l != null ? this.l.getString(str, "") : "";
    }

    public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        public long getItemId(int i) {
            return (long) i;
        }

        public int getItemViewType(int i) {
            return i == 0 ? 0 : 1;
        }

        public MyAdapter() {
        }

        @NonNull
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            if (i == 1) {
                return new ConditionViewHolder(LayoutInflater.from(LightConditionActivity.this).inflate(R.layout.light_rec_scene_condition_item_1, (ViewGroup) null));
            }
            if (i == 0) {
                return new HomeViewHolder(LayoutInflater.from(LightConditionActivity.this).inflate(R.layout.light_rec_scene_condition_item_0, (ViewGroup) null));
            }
            return null;
        }

        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            if (getItemViewType(i) == 0) {
                ((HomeViewHolder) viewHolder).f22029a.setText(TextUtils.isEmpty(LightConditionActivity.this.f.k()) ? "" : LightConditionActivity.this.f.k());
            } else if (getItemViewType(i) == 1) {
                ConditionViewHolder conditionViewHolder = (ConditionViewHolder) viewHolder;
                int i2 = i - 1;
                if (((SceneApi.Condition) LightConditionActivity.this.h.get(i2)).c != null) {
                    Device b = SmartHomeDeviceManager.a().b(((SceneApi.Condition) LightConditionActivity.this.h.get(i2)).c.f21523a);
                    if (b == null) {
                        b = DeviceFactory.k(((SceneApi.Condition) LightConditionActivity.this.h.get(i2)).c.d);
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append(TextUtils.isEmpty(((SceneApi.Condition) LightConditionActivity.this.h.get(i2)).c.c) ? "" : ((SceneApi.Condition) LightConditionActivity.this.h.get(i2)).c.c);
                    if (!LightConditionActivity.this.k || !LightConditionActivity.this.a(((SceneApi.Condition) LightConditionActivity.this.h.get(i2)).c.f21523a)) {
                        if (!b.isOnline) {
                            sb.append(Operators.BRACKET_START_STR);
                            sb.append(LightConditionActivity.this.getString(R.string.offline_device));
                            sb.append(Operators.BRACKET_END_STR);
                        }
                    } else if (LightConditionActivity.this.i.contains(((SceneApi.Condition) LightConditionActivity.this.h.get(i2)).c.f21523a)) {
                        sb.append(Operators.BRACKET_START_STR);
                        sb.append(LightConditionActivity.this.getString(R.string.cant_connect_ble));
                        sb.append(Operators.BRACKET_END_STR);
                    }
                    conditionViewHolder.f22028a.setText(sb.toString());
                    String r = HomeManager.a().r(((SceneApi.Condition) LightConditionActivity.this.h.get(i2)).c.f21523a);
                    TextView textView = conditionViewHolder.e;
                    if (TextUtils.isEmpty(r)) {
                        r = "";
                    }
                    textView.setText(r);
                    if (b != null) {
                        DeviceFactory.b(b.model, conditionViewHolder.f);
                    } else {
                        DeviceFactory.b((String) null, conditionViewHolder.f);
                    }
                } else {
                    DeviceFactory.b((String) null, conditionViewHolder.f);
                    conditionViewHolder.f22028a.setText("");
                }
                conditionViewHolder.b.setVisibility(i < LightConditionActivity.this.h.size() ? 0 : 8);
                conditionViewHolder.c.setVisibility(i == LightConditionActivity.this.h.size() ? 0 : 8);
                if (RecommendSceneCreator.a().h.l == null) {
                    RecommendSceneCreator.a().h.l = new ArrayList();
                }
                if (RecommendSceneCreator.a().h.l.size() == 0) {
                    RecommendSceneCreator.a().h.l.add(LightConditionActivity.this.h.get(0));
                }
                if (a(RecommendSceneCreator.a().h.l.get(0), (SceneApi.Condition) LightConditionActivity.this.h.get(i2))) {
                    conditionViewHolder.d.setVisibility(0);
                    conditionViewHolder.e.setTextColor(LightConditionActivity.this.getResources().getColor(R.color.class_text_33));
                    conditionViewHolder.e.setAlpha(0.6f);
                    conditionViewHolder.f22028a.setTextColor(LightConditionActivity.this.getResources().getColor(R.color.class_text_33));
                } else {
                    conditionViewHolder.d.setVisibility(8);
                    conditionViewHolder.e.setTextColor(LightConditionActivity.this.getResources().getColor(R.color.class_D));
                    conditionViewHolder.e.setAlpha(1.0f);
                    conditionViewHolder.f22028a.setTextColor(LightConditionActivity.this.getResources().getColor(R.color.black));
                }
                conditionViewHolder.itemView.setOnClickListener(new View.OnClickListener(i) {
                    private final /* synthetic */ int f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(View view) {
                        LightConditionActivity.MyAdapter.this.a(this.f$1, view);
                    }
                });
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(int i, View view) {
            RecommendSceneCreator.a().h.l.clear();
            RecommendSceneCreator.a().h.l.add(LightConditionActivity.this.h.get(i - 1));
            notifyDataSetChanged();
            LightConditionActivity.this.setResult(-1);
            LightConditionActivity.this.finish();
        }

        public int getItemCount() {
            if (LightConditionActivity.this.h == null) {
                return 1;
            }
            return 1 + LightConditionActivity.this.h.size();
        }

        private boolean a(SceneApi.Condition condition, SceneApi.Condition condition2) {
            if (condition == null || condition2 == null || condition.f21522a != condition2.f21522a || condition.c == null || condition2.c == null || !TextUtils.equals(condition.c.f21523a, condition2.c.f21523a)) {
                return false;
            }
            return true;
        }
    }

    public class BuyAdapter extends RecyclerView.Adapter<BuyViewHolder> {
        private List<JSONObject> b = new ArrayList();

        public long getItemId(int i) {
            return (long) i;
        }

        public BuyAdapter() {
        }

        @NonNull
        /* renamed from: a */
        public BuyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new BuyViewHolder(LayoutInflater.from(LightConditionActivity.this).inflate(R.layout.light_rec_scene_buy_item, (ViewGroup) null));
        }

        /* renamed from: a */
        public void onBindViewHolder(@NonNull BuyViewHolder buyViewHolder, int i) {
            JSONObject jSONObject = this.b.get(i);
            String next = jSONObject.keys().next();
            if (!TextUtils.isEmpty(next)) {
                DeviceFactory.b(next, buyViewHolder.b);
                buyViewHolder.f22027a.setText(CoreApi.a().d(next).p());
                buyViewHolder.itemView.setOnClickListener(new View.OnClickListener(next, jSONObject) {
                    private final /* synthetic */ String f$1;
                    private final /* synthetic */ JSONObject f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void onClick(View view) {
                        LightConditionActivity.BuyAdapter.this.a(this.f$1, this.f$2, view);
                    }
                });
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(String str, JSONObject jSONObject, View view) {
            if (LightConditionActivity.this.h == null || LightConditionActivity.this.h.size() == 0) {
                if (LightConditionActivity.this.p) {
                    StatClick statClick = STAT.d;
                    String access$400 = LightConditionActivity.this.o;
                    statClick.c(access$400, LightConditionActivity.this.e + "", str);
                } else {
                    StatClick statClick2 = STAT.d;
                    String access$4002 = LightConditionActivity.this.o;
                    statClick2.d(access$4002, LightConditionActivity.this.e + "", str);
                }
            }
            if (LightConditionActivity.this.q) {
                StatClick statClick3 = STAT.d;
                String access$4003 = LightConditionActivity.this.o;
                statClick3.e(access$4003, LightConditionActivity.this.e + "", str);
            }
            UrlDispatchManger.a().c(jSONObject.optString(str));
        }

        public int getItemCount() {
            if (this.b == null) {
                return 0;
            }
            return this.b.size();
        }

        /* access modifiers changed from: private */
        public void a(List<JSONObject> list) {
            this.b.clear();
            this.b.addAll(list);
        }
    }

    public static class ConditionViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public TextView f22028a;
        public View b;
        public View c;
        public View d;
        public TextView e;
        public SimpleDraweeView f;

        public ConditionViewHolder(@NonNull View view) {
            super(view);
            this.f22028a = (TextView) view.findViewById(R.id.device_name);
            this.b = view.findViewById(R.id.divider_x);
            this.c = view.findViewById(R.id.divider_last);
            this.d = view.findViewById(R.id.icon_selected);
            this.e = (TextView) view.findViewById(R.id.room_name);
            this.f = (SimpleDraweeView) view.findViewById(R.id.sd_device_img);
        }
    }

    public static class BuyViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public TextView f22027a;
        public SimpleDraweeView b;

        public BuyViewHolder(@NonNull View view) {
            super(view);
            this.f22027a = (TextView) view.findViewById(R.id.model_name);
            this.b = (SimpleDraweeView) view.findViewById(R.id.sd_model_img);
        }
    }

    public static class HomeViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public TextView f22029a;

        public HomeViewHolder(@NonNull View view) {
            super(view);
            this.f22029a = (TextView) view.findViewById(R.id.tv_room_name);
        }
    }
}
