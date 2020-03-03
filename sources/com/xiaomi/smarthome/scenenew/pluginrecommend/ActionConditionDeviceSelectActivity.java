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
import com.alipay.mobile.common.logging.strategy.LogStrategyManager;
import com.facebook.drawee.view.SimpleDraweeView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.ChooseGatewayDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.PluginRecommendSceneInfo;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scenenew.manager.PluginRecommendSceneManager;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator;
import com.xiaomi.smarthome.scenenew.pluginrecommend.ActionConditionDeviceSelectActivity;
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
import org.json.JSONObject;

public class ActionConditionDeviceSelectActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private TextView f21993a;
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
    public List<SceneApi.Action> i = new ArrayList();
    /* access modifiers changed from: private */
    public Set<String> j = new HashSet();
    /* access modifiers changed from: private */
    public RecyclerView k;
    private SharedPreferences l;
    private ImageView m;
    /* access modifiers changed from: private */
    public BuyAdapter n;
    /* access modifiers changed from: private */
    public String o;
    /* access modifiers changed from: private */
    public boolean p = false;
    /* access modifiers changed from: private */
    public boolean q = false;
    /* access modifiers changed from: private */
    public boolean r;
    /* access modifiers changed from: private */
    public boolean s;
    private boolean t;
    private boolean u;
    private boolean v;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.rec_scene_condition_action_activity);
        this.p = getIntent().getBooleanExtra("need_condition", false);
        this.q = getIntent().getBooleanExtra("need_action", false);
        this.t = getIntent().getBooleanExtra("only_buy_guide", false);
        this.u = getIntent().getBooleanExtra("is_only_ble", false);
        this.v = getIntent().getBooleanExtra("is_only_zigbee", false);
        this.e = getIntent().getIntExtra("sr_id", -1);
        if (RecommendSceneCreator.a().f21963a != null) {
            String str = RecommendSceneCreator.a().f21963a.sr_id;
            if (TextUtils.equals(str, this.e + "")) {
                this.f = HomeManager.a().j(getIntent().getStringExtra("home_id"));
                if (this.f == null) {
                    this.f = HomeManager.a().m();
                }
                this.o = getIntent().getStringExtra("device_model");
                this.f21993a = (TextView) findViewById(R.id.module_a_3_return_title);
                findViewById(R.id.module_a_3_return_btn).setOnClickListener(this);
                a();
                return;
            }
        }
        finish();
    }

    private void a() {
        if (this.t) {
            c();
            return;
        }
        String str = null;
        if (this.p) {
            if (RecommendSceneCreator.a().f21963a.isActionDevice) {
                str = RecommendSceneCreator.a().f21963a.selectConditionHint;
            } else if (RecommendSceneCreator.a().f21963a.isConditionDevice) {
                str = RecommendSceneCreator.a().f21963a.selectActionHint;
            }
            if (!TextUtils.isEmpty(str)) {
                TextView textView = this.f21993a;
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
                this.f21993a.setText(str2);
            }
            if (RecommendSceneCreator.a().e != null && RecommendSceneCreator.a().e.size() > 0) {
                for (Map.Entry<String, List<SceneApi.Condition>> value : RecommendSceneCreator.a().e.entrySet()) {
                    this.h.addAll((Collection) value.getValue());
                }
            }
            if (this.h.size() > 0) {
                StatPopUp statPopUp = STAT.e;
                String str3 = this.o;
                statPopUp.c(str3, this.e + "");
                g();
                return;
            }
            d();
        } else if (this.q) {
            if (RecommendSceneCreator.a().f21963a.isActionDevice) {
                str = RecommendSceneCreator.a().f21963a.selectConditionHint;
            } else if (RecommendSceneCreator.a().f21963a.isConditionDevice) {
                str = RecommendSceneCreator.a().f21963a.selectActionHint;
            }
            if (!TextUtils.isEmpty(str)) {
                TextView textView2 = this.f21993a;
                Object[] objArr2 = new Object[1];
                if (TextUtils.isEmpty(str)) {
                    str = "";
                }
                objArr2[0] = str;
                textView2.setText(getString(R.string.light_rec_condition_name, objArr2));
            } else {
                String str4 = RecommendSceneCreator.a().f21963a.selectDeviceHint;
                if (TextUtils.isEmpty(str4)) {
                    str4 = getString(R.string.light_rec_condition_name, new Object[]{""});
                }
                this.f21993a.setText(str4);
            }
            if (RecommendSceneCreator.a().f != null && RecommendSceneCreator.a().f.size() > 0) {
                for (Map.Entry<String, List<SceneApi.Action>> value2 : RecommendSceneCreator.a().f.entrySet()) {
                    this.i.addAll((Collection) value2.getValue());
                }
            }
            if (this.i.size() > 0) {
                StatPopUp statPopUp2 = STAT.e;
                String str5 = this.o;
                statPopUp2.c(str5, this.e + "");
                g();
                return;
            }
            d();
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        String str;
        if (this.b == null) {
            this.b = ((ViewStub) findViewById(R.id.vs_condition_list)).inflate();
            this.d = (TextView) this.b.findViewById(R.id.tv_not_connect_ble);
            if (this.e == 1000) {
                str = getString(R.string.light_rec_condition_no_ble);
            } else {
                str = getString(R.string.device_rec_condition_no_gateway);
            }
            int indexOf = str.indexOf("#start#");
            int indexOf2 = str.indexOf("#end#") - "#start#".length();
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append(str.replace("#start#", "").replace("#end#", ""));
            AnonymousClass1 r0 = new ClickableSpan() {
                public void onClick(View view) {
                    List<Device> a2 = RecommendSceneCreator.a(ActionConditionDeviceSelectActivity.this.f.j());
                    if (a2 == null || a2.size() == 0) {
                        StatPopUp statPopUp = STAT.e;
                        String access$100 = ActionConditionDeviceSelectActivity.this.o;
                        statPopUp.j(access$100, ActionConditionDeviceSelectActivity.this.e + "");
                        boolean unused = ActionConditionDeviceSelectActivity.this.s = true;
                        ActionConditionDeviceSelectActivity.this.d();
                        return;
                    }
                    StatPopUp statPopUp2 = STAT.e;
                    String access$1002 = ActionConditionDeviceSelectActivity.this.o;
                    statPopUp2.i(access$1002, ActionConditionDeviceSelectActivity.this.e + "");
                    Intent intent = new Intent(ActionConditionDeviceSelectActivity.this, LockDissConnectBleActivity.class);
                    intent.putExtra("home_id", ActionConditionDeviceSelectActivity.this.f.j());
                    intent.putExtra("sr_id", ActionConditionDeviceSelectActivity.this.e);
                    ActionConditionDeviceSelectActivity.this.startActivity(intent);
                }

                public void updateDrawState(TextPaint textPaint) {
                    super.updateDrawState(textPaint);
                    textPaint.setColor(ActionConditionDeviceSelectActivity.this.getResources().getColor(R.color.class_text_33));
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
        if (this.j.size() > 0) {
            this.d.setVisibility(0);
            StatPopUp statPopUp = STAT.e;
            String str2 = this.o;
            statPopUp.h(str2, this.e + "");
        } else {
            this.d.setVisibility(8);
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
        if (this.c == null) {
            this.c = ((ViewStub) findViewById(R.id.vs_buy_guide)).inflate();
        }
        if (this.b != null) {
            this.b.setVisibility(8);
        }
        this.c.findViewById(R.id.common_white_empty_view).setBackgroundColor(Color.parseColor("#f7f7f7"));
        this.c.findViewById(R.id.common_white_empty_view).setVisibility(0);
        ((ImageView) this.c.findViewById(R.id.empty_icon)).setImageResource(R.drawable.ic_plug_rec_action_empty_1000);
        ((TextView) this.c.findViewById(R.id.common_white_empty_text)).setText(R.string.device_rec_no_gateway);
        this.k = (RecyclerView) this.c.findViewById(R.id.buy_list);
        this.k.setLayoutManager(new GridLayoutManager((Context) this, 3, 1, false));
        this.n = new BuyAdapter();
        this.k.setAdapter(this.n);
        ArrayList arrayList = new ArrayList();
        if (this.u) {
            arrayList.add("ble_gateway");
        }
        if (this.v) {
            arrayList.add("zigbee_gateway");
        }
        a(arrayList, new AsyncCallback<List<JSONObject>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<JSONObject> list) {
                ActionConditionDeviceSelectActivity.this.k.setVisibility(0);
                ActionConditionDeviceSelectActivity.this.n.a(list);
                ActionConditionDeviceSelectActivity.this.n.notifyDataSetChanged();
            }

            public void onFailure(Error error) {
                ActionConditionDeviceSelectActivity.this.k.setVisibility(8);
            }
        });
    }

    /* access modifiers changed from: private */
    public void d() {
        String string;
        boolean z;
        String string2;
        if (this.c == null) {
            this.c = ((ViewStub) findViewById(R.id.vs_buy_guide)).inflate();
        }
        if (this.b != null) {
            this.b.setVisibility(8);
        }
        this.c.findViewById(R.id.common_white_empty_view).setBackgroundColor(Color.parseColor("#f7f7f7"));
        this.c.findViewById(R.id.common_white_empty_view).setVisibility(0);
        ((ImageView) this.c.findViewById(R.id.empty_icon)).setImageResource(R.drawable.ic_plug_rec_action_empty_1000);
        String string3 = getString(R.string.light_rec_non_condition_type2, new Object[]{""});
        if (TextUtils.isEmpty(RecommendSceneCreator.a().f21963a.selectActionHint) && TextUtils.isEmpty(RecommendSceneCreator.a().f21963a.selectConditionHint)) {
            string3 = getString(R.string.light_rec_non_condition_type0, new Object[]{getString(R.string.smarthome_scene_create_auto_device)});
        } else if (RecommendSceneCreator.a().f21963a.isConditionDevice) {
            string3 = getString(R.string.light_rec_non_condition_type0, new Object[]{RecommendSceneCreator.a().f21963a.selectActionHint});
        } else if (RecommendSceneCreator.a().f21963a.isActionDevice) {
            string3 = getString(R.string.light_rec_non_condition_type0, new Object[]{RecommendSceneCreator.a().f21963a.selectConditionHint});
        }
        this.k = (RecyclerView) this.c.findViewById(R.id.buy_list);
        this.k.setLayoutManager(new GridLayoutManager((Context) this, 3, 1, false));
        this.n = new BuyAdapter();
        this.k.setAdapter(this.n);
        ArrayList arrayList = new ArrayList();
        if (RecommendSceneCreator.a().f21963a.isActionDevice && this.h.isEmpty()) {
            arrayList.add(this.e + "");
        } else if (RecommendSceneCreator.a().f21963a.isConditionDevice && this.i.isEmpty()) {
            arrayList.add(this.e + "");
        }
        if (e()) {
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
                this.r = false;
                arrayList.add("zigbee_gateway");
                if (TextUtils.isEmpty(RecommendSceneCreator.a().f21963a.selectActionHint) && TextUtils.isEmpty(RecommendSceneCreator.a().f21963a.selectConditionHint)) {
                    string2 = getString(R.string.light_rec_non_condition_type1, new Object[]{getString(R.string.smarthome_scene_create_auto_device)});
                } else if (RecommendSceneCreator.a().f21963a.isConditionDevice) {
                    string2 = getString(R.string.light_rec_non_condition_type1, new Object[]{RecommendSceneCreator.a().f21963a.selectActionHint});
                } else {
                    if (RecommendSceneCreator.a().f21963a.isActionDevice) {
                        string2 = getString(R.string.light_rec_non_condition_type1, new Object[]{RecommendSceneCreator.a().f21963a.selectConditionHint});
                    }
                    StatPopUp statPopUp = STAT.e;
                    String str = this.o;
                    statPopUp.e(str, this.e + "");
                }
                string3 = string2;
                StatPopUp statPopUp2 = STAT.e;
                String str2 = this.o;
                statPopUp2.e(str2, this.e + "");
            } else {
                this.r = true;
                StatPopUp statPopUp3 = STAT.e;
                String str3 = this.o;
                statPopUp3.d(str3, this.e + "");
            }
        } else if (f()) {
            if ((!this.q || this.i.size() <= 0) && (!this.p || this.h.size() <= 0)) {
                List<Device> a2 = RecommendSceneCreator.a(this.f.j());
                if (a2 == null || a2.size() == 0) {
                    this.r = false;
                    arrayList.add("ble_gateway");
                    if (TextUtils.isEmpty(RecommendSceneCreator.a().f21963a.selectActionHint) && TextUtils.isEmpty(RecommendSceneCreator.a().f21963a.selectConditionHint)) {
                        string = getString(R.string.light_rec_non_condition_type2, new Object[]{getString(R.string.smarthome_scene_create_auto_device)});
                    } else if (RecommendSceneCreator.a().f21963a.isConditionDevice) {
                        string = getString(R.string.light_rec_non_condition_type2, new Object[]{RecommendSceneCreator.a().f21963a.selectActionHint});
                    } else {
                        if (RecommendSceneCreator.a().f21963a.isActionDevice) {
                            string = getString(R.string.light_rec_non_condition_type2, new Object[]{RecommendSceneCreator.a().f21963a.selectConditionHint});
                        }
                        StatPopUp statPopUp4 = STAT.e;
                        String str4 = this.o;
                        statPopUp4.e(str4, this.e + "");
                    }
                    string3 = string;
                    StatPopUp statPopUp42 = STAT.e;
                    String str42 = this.o;
                    statPopUp42.e(str42, this.e + "");
                } else {
                    this.r = true;
                    StatPopUp statPopUp5 = STAT.e;
                    String str5 = this.o;
                    statPopUp5.d(str5, this.e + "");
                }
            } else {
                arrayList.clear();
            }
        }
        ((TextView) this.c.findViewById(R.id.common_white_empty_text)).setText(string3);
        a(arrayList, new AsyncCallback<List<JSONObject>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<JSONObject> list) {
                ActionConditionDeviceSelectActivity.this.k.setVisibility(0);
                ActionConditionDeviceSelectActivity.this.n.a(list);
                ActionConditionDeviceSelectActivity.this.n.notifyDataSetChanged();
            }

            public void onFailure(Error error) {
                ActionConditionDeviceSelectActivity.this.k.setVisibility(8);
            }
        });
    }

    private void a(List<String> list, AsyncCallback<List<JSONObject>, Error> asyncCallback) {
        boolean contains = list.contains("zigbee_gateway");
        boolean contains2 = list.contains("ble_gateway");
        PluginRecommendSceneManager a2 = PluginRecommendSceneManager.a();
        a2.a(this.e + "", RecommendSceneCreator.a().f21963a.isActionDevice ? LogStrategyManager.SP_STRATEGY_KEY_TRIGGER : "Action", contains2, contains, asyncCallback);
    }

    private boolean e() {
        if (RecommendSceneCreator.a().f21963a.isConditionDevice) {
            return a(RecommendSceneCreator.a().f21963a.mConditionList);
        }
        if (RecommendSceneCreator.a().f21963a.isActionDevice) {
            return a(RecommendSceneCreator.a().f21963a.mActionList);
        }
        return false;
    }

    private boolean f() {
        List<PluginRecommendSceneInfo.ConditionActionItem> list;
        if (RecommendSceneCreator.a().f21963a.isConditionDevice) {
            list = RecommendSceneCreator.a().f21963a.mActionList;
        } else {
            list = RecommendSceneCreator.a().f21963a.isActionDevice ? RecommendSceneCreator.a().f21963a.mConditionList : null;
        }
        if (list == null || list.isEmpty()) {
            return false;
        }
        int i2 = 0;
        boolean z = false;
        while (i2 < list.size() && list.get(i2) != null && list.get(i2).modelListJobj != null) {
            Iterator<String> keys = list.get(i2).modelListJobj.keys();
            while (true) {
                if (!keys.hasNext()) {
                    break;
                }
                PluginRecord d2 = CoreApi.a().d(keys.next());
                if (d2 == null || d2.c() == null || d2.c().t() != Device.PID_BLUETOOTH) {
                    z = true;
                } else {
                    z = false;
                }
            }
            z = true;
            if (z) {
                break;
            }
            i2++;
        }
        if (z) {
            return false;
        }
        return true;
    }

    private boolean a(List<PluginRecommendSceneInfo.ConditionActionItem> list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        int i2 = 0;
        boolean z = false;
        while (true) {
            if (i2 >= list.size()) {
                break;
            } else if (list.get(i2) == null || list.get(i2).modelListJobj == null) {
                z = true;
            } else {
                Iterator<String> keys = list.get(i2).modelListJobj.keys();
                while (true) {
                    if (!keys.hasNext()) {
                        break;
                    }
                    String next = keys.next();
                    if (!TextUtils.isEmpty(next)) {
                        PluginRecord d2 = CoreApi.a().d(next);
                        if (d2 == null || d2.c() == null || d2.c().t() != Device.PID_SUBDEVICE) {
                            z = true;
                        } else {
                            z = false;
                        }
                    }
                }
                z = true;
                if (z) {
                    break;
                }
                i2++;
            }
        }
        z = true;
        if (z) {
            return false;
        }
        return true;
    }

    private void g() {
        final ArrayList arrayList = new ArrayList();
        int i2 = 0;
        if (RecommendSceneCreator.a().f21963a.isActionDevice) {
            while (i2 < this.h.size()) {
                if (this.h.get(i2).c != null && RecommendSceneCreator.b(this.h.get(i2).c.f21523a)) {
                    arrayList.add(this.h.get(i2).c.f21523a);
                }
                i2++;
            }
        } else if (RecommendSceneCreator.a().f21963a.isConditionDevice) {
            while (i2 < this.i.size()) {
                if (this.i.get(i2).g != null && RecommendSceneCreator.b(this.i.get(i2).g.e)) {
                    arrayList.add(this.i.get(i2).g.e);
                }
                i2++;
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
                                ActionConditionDeviceSelectActivity.this.j.add(arrayList.get(i));
                            }
                        }
                    }
                    ActionConditionDeviceSelectActivity.this.b();
                }

                public void onFailure(Error error) {
                    ActionConditionDeviceSelectActivity.this.b();
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

    public static class HomeViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public TextView f22001a;

        public HomeViewHolder(@NonNull View view) {
            super(view);
            this.f22001a = (TextView) view.findViewById(R.id.tv_room_name);
        }
    }

    public static class BuyViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public TextView f21999a;
        public SimpleDraweeView b;

        public BuyViewHolder(@NonNull View view) {
            super(view);
            this.f21999a = (TextView) view.findViewById(R.id.model_name);
            this.b = (SimpleDraweeView) view.findViewById(R.id.sd_model_img);
        }
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
                return new ConditionViewHolder(LayoutInflater.from(ActionConditionDeviceSelectActivity.this).inflate(R.layout.light_rec_scene_condition_item_1, (ViewGroup) null));
            }
            if (i == 0) {
                return new HomeViewHolder(LayoutInflater.from(ActionConditionDeviceSelectActivity.this).inflate(R.layout.light_rec_scene_condition_item_0, (ViewGroup) null));
            }
            return null;
        }

        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            if (getItemViewType(i) == 0) {
                ((HomeViewHolder) viewHolder).f22001a.setText(TextUtils.isEmpty(ActionConditionDeviceSelectActivity.this.f.k()) ? "" : ActionConditionDeviceSelectActivity.this.f.k());
            } else if (getItemViewType(i) == 1) {
                ConditionViewHolder conditionViewHolder = (ConditionViewHolder) viewHolder;
                if (ActionConditionDeviceSelectActivity.this.p) {
                    int i2 = i - 1;
                    if (((SceneApi.Condition) ActionConditionDeviceSelectActivity.this.h.get(i2)).c != null) {
                        conditionViewHolder.a(((SceneApi.Condition) ActionConditionDeviceSelectActivity.this.h.get(i2)).c);
                        conditionViewHolder.itemView.setOnClickListener(new View.OnClickListener(i) {
                            private final /* synthetic */ int f$1;

                            {
                                this.f$1 = r2;
                            }

                            public final void onClick(View view) {
                                ActionConditionDeviceSelectActivity.MyAdapter.this.b(this.f$1, view);
                            }
                        });
                        return;
                    }
                }
                if (ActionConditionDeviceSelectActivity.this.q) {
                    int i3 = i - 1;
                    if (ActionConditionDeviceSelectActivity.this.i.get(i3) != null) {
                        conditionViewHolder.a((SceneApi.Action) ActionConditionDeviceSelectActivity.this.i.get(i3));
                        conditionViewHolder.itemView.setOnClickListener(new View.OnClickListener(i) {
                            private final /* synthetic */ int f$1;

                            {
                                this.f$1 = r2;
                            }

                            public final void onClick(View view) {
                                ActionConditionDeviceSelectActivity.MyAdapter.this.a(this.f$1, view);
                            }
                        });
                        return;
                    }
                }
                DeviceFactory.b((String) null, conditionViewHolder.f);
                conditionViewHolder.f22000a.setText("");
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void b(int i, View view) {
            RecommendSceneCreator.a().h.l.clear();
            RecommendSceneCreator.a().h.l.add(ActionConditionDeviceSelectActivity.this.h.get(i - 1));
            notifyDataSetChanged();
            ActionConditionDeviceSelectActivity.this.setResult(-1);
            ActionConditionDeviceSelectActivity.this.finish();
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(int i, View view) {
            if (!RecommendSceneCreator.a().h.k.isEmpty()) {
                int i2 = i - 1;
                if (!(RecommendSceneCreator.a().h.k.get(0).f != ((SceneApi.Action) ActionConditionDeviceSelectActivity.this.i.get(i2)).f || ((SceneApi.Action) ActionConditionDeviceSelectActivity.this.i.get(i2)).g == null || RecommendSceneCreator.a().h.k.get(0).g == null)) {
                    ((SceneApi.Action) ActionConditionDeviceSelectActivity.this.i.get(i2)).g.f = RecommendSceneCreator.a().h.k.get(0).g.f;
                }
            }
            RecommendSceneCreator.a().h.k.clear();
            RecommendSceneCreator.a().h.k.add(ActionConditionDeviceSelectActivity.this.i.get(i - 1));
            notifyDataSetChanged();
            ActionConditionDeviceSelectActivity.this.setResult(-1);
            ActionConditionDeviceSelectActivity.this.finish();
        }

        public int getItemCount() {
            if (ActionConditionDeviceSelectActivity.this.q) {
                if (ActionConditionDeviceSelectActivity.this.i == null) {
                    return 1;
                }
                return 1 + ActionConditionDeviceSelectActivity.this.i.size();
            } else if (!ActionConditionDeviceSelectActivity.this.p) {
                return 0;
            } else {
                if (ActionConditionDeviceSelectActivity.this.h == null) {
                    return 1;
                }
                return 1 + ActionConditionDeviceSelectActivity.this.h.size();
            }
        }

        private boolean a(SceneApi.Condition condition, SceneApi.Condition condition2) {
            if (condition == null || condition2 == null || condition.f21522a != condition2.f21522a || condition.c == null || condition2.c == null || !TextUtils.equals(condition.c.f21523a, condition2.c.f21523a)) {
                return false;
            }
            return true;
        }
    }

    public class ConditionViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public TextView f22000a;
        public View b;
        public View c;
        public View d;
        public TextView e;
        public SimpleDraweeView f;

        public ConditionViewHolder(View view) {
            super(view);
            this.f22000a = (TextView) view.findViewById(R.id.device_name);
            this.b = view.findViewById(R.id.divider_x);
            this.c = view.findViewById(R.id.divider_last);
            this.d = view.findViewById(R.id.icon_selected);
            this.e = (TextView) view.findViewById(R.id.room_name);
            this.f = (SimpleDraweeView) view.findViewById(R.id.sd_device_img);
        }

        public void a(SceneApi.ConditionDevice conditionDevice) {
            Device b2 = SmartHomeDeviceManager.a().b(conditionDevice.f21523a);
            if (b2 == null) {
                b2 = DeviceFactory.k(conditionDevice.d);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(TextUtils.isEmpty(conditionDevice.c) ? "" : conditionDevice.c);
            if (RecommendSceneCreator.b(conditionDevice.f21523a)) {
                if (ActionConditionDeviceSelectActivity.this.j.contains(conditionDevice.f21523a)) {
                    sb.append(Operators.BRACKET_START_STR);
                    sb.append(ActionConditionDeviceSelectActivity.this.getString(R.string.cant_connect_ble));
                    sb.append(Operators.BRACKET_END_STR);
                }
            } else if (!b2.isOnline) {
                sb.append(Operators.BRACKET_START_STR);
                sb.append(ActionConditionDeviceSelectActivity.this.getString(R.string.offline_device));
                sb.append(Operators.BRACKET_END_STR);
            }
            this.f22000a.setText(sb.toString());
            String r = HomeManager.a().r(conditionDevice.f21523a);
            TextView textView = this.e;
            if (TextUtils.isEmpty(r)) {
                r = "";
            }
            textView.setText(r);
            if (b2 != null) {
                DeviceFactory.b(b2.model, this.f);
            } else {
                DeviceFactory.b((String) null, this.f);
            }
        }

        public void a(SceneApi.Action action) {
            if (action.g != null) {
                Device b2 = SmartHomeDeviceManager.a().b(action.g.e);
                if (b2 == null) {
                    b2 = DeviceFactory.k(action.e);
                }
                StringBuilder sb = new StringBuilder();
                sb.append(TextUtils.isEmpty(b2.name) ? "" : b2.name);
                if (RecommendSceneCreator.b(b2.did)) {
                    if (ActionConditionDeviceSelectActivity.this.j.contains(b2.did)) {
                        sb.append(Operators.BRACKET_START_STR);
                        sb.append(ActionConditionDeviceSelectActivity.this.getString(R.string.cant_connect_ble));
                        sb.append(Operators.BRACKET_END_STR);
                    }
                } else if (!b2.isOnline) {
                    sb.append(Operators.BRACKET_START_STR);
                    sb.append(ActionConditionDeviceSelectActivity.this.getString(R.string.offline_device));
                    sb.append(Operators.BRACKET_END_STR);
                }
                this.f22000a.setText(sb.toString());
                String r = HomeManager.a().r(b2.did);
                TextView textView = this.e;
                if (TextUtils.isEmpty(r)) {
                    r = "";
                }
                textView.setText(r);
                if (b2 != null) {
                    DeviceFactory.b(b2.model, this.f);
                } else {
                    DeviceFactory.b((String) null, this.f);
                }
            }
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
            return new BuyViewHolder(LayoutInflater.from(ActionConditionDeviceSelectActivity.this).inflate(R.layout.light_rec_scene_buy_item, (ViewGroup) null));
        }

        /* renamed from: a */
        public void onBindViewHolder(@NonNull BuyViewHolder buyViewHolder, int i) {
            JSONObject jSONObject = this.b.get(i);
            String trim = jSONObject.keys().next().trim();
            if (!TextUtils.isEmpty(trim)) {
                DeviceFactory.b(trim, buyViewHolder.b);
                PluginRecord d = CoreApi.a().d(trim);
                if (d == null || TextUtils.isEmpty(d.p())) {
                    buyViewHolder.f21999a.setText("");
                } else {
                    buyViewHolder.f21999a.setText(d.p());
                }
                buyViewHolder.itemView.setOnClickListener(new View.OnClickListener(trim, jSONObject) {
                    private final /* synthetic */ String f$1;
                    private final /* synthetic */ JSONObject f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void onClick(View view) {
                        ActionConditionDeviceSelectActivity.BuyAdapter.this.a(this.f$1, this.f$2, view);
                    }
                });
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(String str, JSONObject jSONObject, View view) {
            if (ActionConditionDeviceSelectActivity.this.q) {
                if (ActionConditionDeviceSelectActivity.this.h == null || ActionConditionDeviceSelectActivity.this.h.size() == 0) {
                    if (ActionConditionDeviceSelectActivity.this.r) {
                        StatClick statClick = STAT.d;
                        String access$100 = ActionConditionDeviceSelectActivity.this.o;
                        statClick.c(access$100, ActionConditionDeviceSelectActivity.this.e + "", str);
                    } else {
                        StatClick statClick2 = STAT.d;
                        String access$1002 = ActionConditionDeviceSelectActivity.this.o;
                        statClick2.d(access$1002, ActionConditionDeviceSelectActivity.this.e + "", str);
                    }
                }
            } else if (ActionConditionDeviceSelectActivity.this.p && (ActionConditionDeviceSelectActivity.this.i == null || ActionConditionDeviceSelectActivity.this.i.size() == 0)) {
                if (ActionConditionDeviceSelectActivity.this.r) {
                    StatClick statClick3 = STAT.d;
                    String access$1003 = ActionConditionDeviceSelectActivity.this.o;
                    statClick3.c(access$1003, ActionConditionDeviceSelectActivity.this.e + "", str);
                } else {
                    StatClick statClick4 = STAT.d;
                    String access$1004 = ActionConditionDeviceSelectActivity.this.o;
                    statClick4.d(access$1004, ActionConditionDeviceSelectActivity.this.e + "", str);
                }
            }
            if (ActionConditionDeviceSelectActivity.this.s) {
                StatClick statClick5 = STAT.d;
                String access$1005 = ActionConditionDeviceSelectActivity.this.o;
                statClick5.e(access$1005, ActionConditionDeviceSelectActivity.this.e + "", str);
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
            Iterator<String> keys;
            this.b.clear();
            for (int i = 0; i < list.size(); i++) {
                if (!(list.get(i) == null || (keys = list.get(i).keys()) == null || CoreApi.a().d(keys.next().trim()) == null)) {
                    this.b.add(list.get(i));
                }
            }
        }
    }
}
