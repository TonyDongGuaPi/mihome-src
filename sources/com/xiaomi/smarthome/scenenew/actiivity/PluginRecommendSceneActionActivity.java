package com.xiaomi.smarthome.scenenew.actiivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mi.global.shop.model.Tags;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceStat;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.scene.SceneCreateTimeEdit2Activity;
import com.xiaomi.smarthome.scene.SmartHomeSceneUtility;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scenenew.actiivity.PluginRecommendSceneActionActivity;
import com.xiaomi.smarthome.scenenew.manager.PluginRecommendSceneManager;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneCreator;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PluginRecommendSceneActionActivity extends BaseActivity {
    public static final String KEY_CONNECT_BLE = "connect_ble";
    private static String k = "{\"1000\":{\"xiaomi.wifispeaker.lx06\":\"https://m.xiaomiyoupin.com/detail?gid=113331&source=mijia_p_sh2009\",\"xiaomi.wifispeaker.l06a \":\"https://m.xiaomiyoupin.com/detail?gid=113328&source=mijia_p_sh2010\",\"xiaomi.wifispeaker.lx04\":\"https://m.xiaomiyoupin.com/detail?gid=105569&source=mijia_p_sh2011\",\"xiaomi.wifispeaker.lx5a\":\"https://m.xiaomiyoupin.com/detail?gid=107496&source=mijia_p_sh2012\",\"xiaomi.wifispeaker.lx05\":\"https://m.xiaomiyoupin.com/detail?gid=108117&source=mijia_p_sh2013\",\"zimi.clock.myk01\":\"https://m.xiaomiyoupin.com/detail?gid=103705&source=mijia_p_sh2014\"}}";

    /* renamed from: a  reason: collision with root package name */
    private String f21808a;
    /* access modifiers changed from: private */
    public Device b;
    private int c = -2;
    /* access modifiers changed from: private */
    public List<UIData> d = new ArrayList();
    private TextView e;
    private View f;
    /* access modifiers changed from: private */
    public SceneApi.SmartHomeScene g;
    /* access modifiers changed from: private */
    public int h = 0;
    /* access modifiers changed from: private */
    public MyAdapter i;
    /* access modifiers changed from: private */
    public XQProgressDialog j;
    private View l;
    /* access modifiers changed from: private */
    public RecyclerView m;
    /* access modifiers changed from: private */
    public BuyAdapter n;
    private SharedPreferences o;

    public static class UIData {

        /* renamed from: a  reason: collision with root package name */
        int f21821a = 1;
        public Device b;
        public SceneApi.Action c;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_plugin_recommend_scene_action);
        this.f21808a = getIntent().getStringExtra("home_id");
        this.b = SmartHomeDeviceManager.a().b(getIntent().getStringExtra("did"));
        this.c = getIntent().getIntExtra(KEY_CONNECT_BLE, -2);
        this.e = (TextView) findViewById(R.id.module_a_3_return_title);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                PluginRecommendSceneActionActivity.this.b(view);
            }
        });
        if (this.c == -1) {
            finish();
            return;
        }
        this.g = RecommendSceneCreator.a().h;
        if (this.g == null) {
            finish();
            return;
        }
        STAT.e.n(this.b.model);
        if (this.c == 0) {
            List<Device> a2 = RecommendSceneCreator.a(this.f21808a);
            if (a2 == null || a2.size() == 0) {
                b();
            } else {
                a(a2);
            }
        } else if (this.c == 1) {
            a(RecommendSceneCreator.a().f);
            if (this.d == null || this.d.size() == 0) {
                b();
            } else {
                a();
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        finish();
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, @Nullable Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i3 == -1 && i2 == 1011) {
            if (this.i != null) {
                this.i.notifyDataSetChanged();
            }
            if (this.g != null && !TextUtils.isEmpty(this.g.f)) {
                this.j = XQProgressDialog.a(this, (CharSequence) null, getResources().getString(R.string.smarthome_scene_saving_scene));
                RecommendSceneCreator.a(this.g, (RecommendSceneCreator.SaveSceneCallback) new RecommendSceneCreator.SaveSceneCallback() {
                    public void onSaveLocalFail() {
                        if (PluginRecommendSceneActionActivity.this.isValid()) {
                            PluginRecommendSceneActionActivity.this.j.dismiss();
                            ToastUtil.a((int) R.string.save_fail);
                        }
                    }

                    public void onSaveCloudSuccess(boolean z) {
                        if (PluginRecommendSceneActionActivity.this.isValid()) {
                            PluginRecommendSceneActionActivity.this.j.dismiss();
                            ToastUtil.a((int) R.string.save_success);
                        }
                    }

                    public void onSaveCloudFail(int i, String str) {
                        if (PluginRecommendSceneActionActivity.this.isValid()) {
                            PluginRecommendSceneActionActivity.this.j.dismiss();
                            ToastUtil.a((int) R.string.save_fail);
                            if (PluginRecommendSceneActionActivity.this.i != null) {
                                PluginRecommendSceneActionActivity.this.i.notifyDataSetChanged();
                            }
                        }
                    }
                });
            }
        }
    }

    private void a() {
        UIData uIData = new UIData();
        uIData.f21821a = 2;
        this.d.add(uIData);
        View inflate = ((ViewStub) findViewById(R.id.action_list_view)).inflate();
        this.f = inflate.findViewById(R.id.next_step);
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        this.i = new MyAdapter();
        recyclerView.setAdapter(this.i);
        if (this.g.k != null && this.g.k.size() > 0 && (this.g.k.get(0).g instanceof SceneApi.SHSceneItemPayloadCommon)) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.d.size()) {
                    break;
                } else if (TextUtils.equals(((SceneApi.SHSceneItemPayloadCommon) this.g.k.get(0).g).e, this.d.get(i2).b.did)) {
                    this.h = i2;
                    break;
                } else {
                    i2++;
                }
            }
        }
        if (RecommendSceneCreator.a().f21963a == null || TextUtils.isEmpty(RecommendSceneCreator.a().f21963a.intro)) {
            this.e.setText(R.string.setting);
        } else {
            this.e.setText(getString(R.string.plugin_rec_setting_title, new Object[]{RecommendSceneCreator.a().f21963a.intro}));
        }
        this.f.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                PluginRecommendSceneActionActivity.this.a(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        if (this.h >= 0 && this.h < this.d.size() && this.d.get(this.h).c.f21521a == 0 && ((SceneApi.SHSceneItemPayloadCommon) this.d.get(this.h).c.g).j != null) {
            a(this.d.get(this.h));
        }
    }

    private void b() {
        STAT.e.m("lockspeaker");
        if (RecommendSceneCreator.a().f21963a == null || TextUtils.isEmpty(RecommendSceneCreator.a().f21963a.intro)) {
            this.e.setText(R.string.setting);
        } else {
            this.e.setText(getString(R.string.plugin_rec_setting_title, new Object[]{RecommendSceneCreator.a().f21963a.intro}));
        }
        if (this.l == null) {
            this.l = ((ViewStub) findViewById(R.id.vs_buy_guide)).inflate();
        }
        this.l.findViewById(R.id.common_white_empty_view).setBackgroundColor(Color.parseColor("#f7f7f7"));
        this.l.findViewById(R.id.common_white_empty_view).setVisibility(0);
        ((ImageView) this.l.findViewById(R.id.empty_icon)).setImageResource(R.drawable.ic_plug_rec_action_empty_1000);
        this.m = (RecyclerView) this.l.findViewById(R.id.buy_list);
        this.m.setLayoutManager(new GridLayoutManager((Context) this, 3, 1, false));
        this.n = new BuyAdapter();
        this.m.setAdapter(this.n);
        TextView textView = (TextView) this.l.findViewById(R.id.common_white_empty_text);
        if (RecommendSceneCreator.a().f21963a == null || !TextUtils.equals(RecommendSceneCreator.a().f21963a.sr_id, Tags.LuckyShake.VALUE_SUCCESS_CODE)) {
            textView.setText(R.string.rec_action_empty);
        } else {
            textView.setText(R.string.plugin_rec_action_no_speaker);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(RecommendSceneCreator.a().f21963a.sr_id);
        a(arrayList, new AsyncCallback<List<JSONObject>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<JSONObject> list) {
                PluginRecommendSceneActionActivity.this.m.setVisibility(0);
                PluginRecommendSceneActionActivity.this.n.a(list);
                PluginRecommendSceneActionActivity.this.n.notifyDataSetChanged();
            }

            public void onFailure(Error error) {
                PluginRecommendSceneActionActivity.this.m.setVisibility(8);
            }
        });
    }

    private void a(final List<String> list, final AsyncCallback<List<JSONObject>, Error> asyncCallback) {
        JSONObject jSONObject;
        String popCache = popCache("scene_rec_result_temp_youpin");
        ArrayList arrayList = new ArrayList();
        try {
            if (TextUtils.isEmpty(popCache)) {
                jSONObject = new JSONObject(k);
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
                JSONObject jSONObject2 = new JSONObject(k);
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
                        PluginRecommendSceneActionActivity.this.pushCache("scene_rec_result_temp_youpin", jSONObject.toString());
                    }
                }
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(arrayList);
                }
            }
        });
    }

    public void pushCache(String str, String str2) {
        if (this.o == null) {
            this.o = SHApplication.getAppContext().getSharedPreferences("recommend_scene_buy_guide", 0);
        }
        if (this.o != null) {
            SharedPreferences.Editor edit = this.o.edit();
            edit.putString(str, str2);
            edit.apply();
        }
    }

    public String popCache(String str) {
        if (this.o == null) {
            this.o = SHApplication.getAppContext().getSharedPreferences("recommend_scene_buy_guide", 0);
        }
        return this.o != null ? this.o.getString(str, "") : "";
    }

    private void a(final List<Device> list) {
        if (RecommendSceneCreator.a().f21963a != null && !TextUtils.isEmpty(RecommendSceneCreator.a().f21963a.entryDesc)) {
            this.e.setText(RecommendSceneCreator.a().f21963a.intro);
        }
        RecyclerView recyclerView = (RecyclerView) ((ViewStub) findViewById(R.id.ble_list_view)).inflate().findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerView.Adapter<BleViewHolder>() {
            public long getItemId(int i) {
                return (long) i;
            }

            @NonNull
            /* renamed from: a */
            public BleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                return new BleViewHolder(LayoutInflater.from(PluginRecommendSceneActionActivity.this).inflate(R.layout.item_plugin_rec_action_connect_ble, (ViewGroup) null));
            }

            /* renamed from: a */
            public void onBindViewHolder(@NonNull BleViewHolder bleViewHolder, int i) {
                if (i == list.size() - 1) {
                    bleViewHolder.c.setVisibility(0);
                } else {
                    bleViewHolder.c.setVisibility(8);
                }
                String str = ((Device) list.get(i)).name;
                if (!TextUtils.isEmpty(str)) {
                    bleViewHolder.b.setText(str);
                } else {
                    bleViewHolder.b.setText("");
                }
                DeviceFactory.b(((Device) list.get(i)).model, bleViewHolder.f21816a);
            }

            public int getItemCount() {
                if (list == null) {
                    return 0;
                }
                return list.size();
            }
        });
    }

    private void a(Map<String, List<SceneApi.Action>> map) {
        this.d.clear();
        for (Map.Entry<String, List<SceneApi.Action>> value : map.entrySet()) {
            for (SceneApi.Action action : (List) value.getValue()) {
                UIData uIData = new UIData();
                uIData.c = action;
                uIData.b = SmartHomeDeviceManager.a().b(action.g.e);
                uIData.c.g.e = uIData.b.did;
                if (!(uIData.b == null || action == null)) {
                    this.d.add(uIData);
                }
            }
        }
    }

    public void onActionSelected(int i2, SceneApi.Action action, Intent intent) {
        if (i2 == -1 && intent != null && intent.hasExtra("value") && intent.getStringExtra("value") != null) {
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
                boolean z = false;
                try {
                    sHSceneItemPayloadCommon.f = new JSONObject(intent.getStringExtra("value"));
                    z = true;
                } catch (JSONException unused) {
                }
                if (!z) {
                    try {
                        sHSceneItemPayloadCommon.f = new JSONArray(intent.getStringExtra("value"));
                        z = true;
                    } catch (JSONException unused2) {
                    }
                }
                if (!z) {
                    sHSceneItemPayloadCommon.f = intent.getStringExtra("value");
                }
            } else {
                sHSceneItemPayloadCommon.f = intent.getExtras().get("value");
            }
            action.g = sHSceneItemPayloadCommon;
            if (this.g.k == null) {
                this.g.k = new ArrayList();
            }
            if (this.g.k.size() == 0) {
                this.g.k.add(action);
            } else {
                this.g.k.clear();
                this.g.k.add(action);
            }
            setResult(-1);
            finish();
        }
    }

    private void a(final UIData uIData) {
        final Intent intent = new Intent(((SceneApi.SHSceneItemPayloadCommon) uIData.c.g).j);
        intent.putExtra("action", uIData.c.g.c);
        Object a2 = a(uIData.c);
        if (a2 != null && (a2 instanceof String)) {
            intent.putExtra("value", (String) a2);
        } else if (a2 != null) {
            intent.putExtra("value", a2.toString());
        }
        intent.putExtra("actionId", uIData.c.f);
        intent.putExtra("plug_id", ((SceneApi.SHSceneItemPayloadCommon) uIData.c.g).j);
        intent.putExtra("name", uIData.c.b);
        intent.putExtra("tr_id", uIData.c.d);
        intent.putExtra("scene_type", 2);
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                PluginRecommendSceneActionActivity.this.sendMessage(uIData, 3, intent);
            }
        });
    }

    public void sendMessage(UIData uIData, int i2, Intent intent) {
        UIData uIData2 = uIData;
        PluginRecord d2 = CoreApi.a().d(uIData2.b.model);
        if (d2 != null) {
            final XQProgressHorizontalDialog b2 = XQProgressHorizontalDialog.b(this, getString(R.string.plugin_downloading) + d2.p() + getString(R.string.plugin));
            final PluginDownloadTask pluginDownloadTask = new PluginDownloadTask();
            final boolean z = !d2.l() && !d2.k();
            PluginApi instance = PluginApi.getInstance();
            DeviceStat newDeviceStat = uIData2.b.newDeviceStat();
            final PluginRecord pluginRecord = d2;
            final UIData uIData3 = uIData;
            instance.sendMessage(this, d2, i2, intent, newDeviceStat, (RunningProcess) null, true, new PluginApi.SendMessageCallback((Object) null) {
                public void onDownloadStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                    pluginDownloadTask.a(pluginDownloadTask);
                    if (b2 != null && !PluginRecommendSceneActionActivity.this.isFinishing() && !PluginRecommendSceneActionActivity.this.isDestroyed()) {
                        b2.a(100, 0);
                        b2.c();
                        b2.setCancelable(true);
                        b2.show();
                        b2.setOnCancelListener(new DialogInterface.OnCancelListener(pluginDownloadTask) {
                            private final /* synthetic */ PluginDownloadTask f$1;

                            {
                                this.f$1 = r2;
                            }

                            public final void onCancel(DialogInterface dialogInterface) {
                                CoreApi.a().a(PluginRecord.this.o(), this.f$1, (CoreApi.CancelPluginDownloadCallback) null);
                            }
                        });
                    }
                }

                public void onDownloadProgress(PluginRecord pluginRecord, float f2) {
                    if (z) {
                        int i = (int) (f2 * 100.0f);
                        if (i >= 99) {
                            i = 99;
                        }
                        if (b2 != null) {
                            b2.a(100, i);
                        }
                    } else if (b2 != null) {
                        b2.a(100, (int) (f2 * 100.0f));
                    }
                }

                public void onDownloadSuccess(PluginRecord pluginRecord) {
                    if (!z && b2 != null) {
                        b2.dismiss();
                    }
                }

                public void onDownloadFailure(PluginError pluginError) {
                    if (!z && b2 != null) {
                        b2.dismiss();
                    }
                }

                public void onDownloadCancel() {
                    if (!z && b2 != null) {
                        b2.dismiss();
                    }
                }

                public void onSendSuccess(Bundle bundle) {
                    if (z && b2 != null) {
                        b2.dismiss();
                    }
                }

                public void onSendFailure(Error error) {
                    if (z && b2 != null) {
                        b2.dismiss();
                    }
                }

                public void onSendCancel() {
                    if (z && b2 != null) {
                        b2.dismiss();
                    }
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void a(UIData uIData, Intent intent) {
                    PluginRecommendSceneActionActivity.this.onActionSelected(-1, uIData.c, intent);
                }

                public void onMessageSuccess(Intent intent) {
                    PluginRecommendSceneActionActivity.this.mHandler.post(new Runnable(uIData3, intent) {
                        private final /* synthetic */ PluginRecommendSceneActionActivity.UIData f$1;
                        private final /* synthetic */ Intent f$2;

                        {
                            this.f$1 = r2;
                            this.f$2 = r3;
                        }

                        public final void run() {
                            PluginRecommendSceneActionActivity.AnonymousClass6.this.a(this.f$1, this.f$2);
                        }
                    });
                    this.mObj = null;
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void a(UIData uIData) {
                    PluginRecommendSceneActionActivity.this.onActionSelected(0, uIData.c, (Intent) null);
                }

                public void onMessageFailure(int i, String str) {
                    PluginRecommendSceneActionActivity.this.mHandler.post(new Runnable(uIData3) {
                        private final /* synthetic */ PluginRecommendSceneActionActivity.UIData f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            PluginRecommendSceneActionActivity.AnonymousClass6.this.a(this.f$1);
                        }
                    });
                    this.mObj = null;
                }
            });
        }
    }

    private Object a(SceneApi.Action action) {
        Object obj;
        if (this.g != null && this.g.k != null && this.g.k.size() > 0 && TextUtils.equals(Tags.LuckyShake.VALUE_SUCCESS_CODE, RecommendSceneCreator.a().f21963a.sr_id)) {
            SceneApi.Action action2 = this.g.k.get(0);
            if (!TextUtils.isEmpty(action.e) && TextUtils.equals(action2.e, action.e) && action2.f21521a == 0 && action.f21521a == 0 && (action2.g instanceof SceneApi.SHSceneItemPayloadCommon) && (obj = ((SceneApi.SHSceneItemPayloadCommon) action2.g).f) != null) {
                if (obj instanceof String) {
                    String str = (String) obj;
                    if (!TextUtils.isEmpty(str)) {
                        return str;
                    }
                }
                if (obj instanceof JSONObject) {
                    return (JSONObject) obj;
                }
            }
        }
        return null;
    }

    public static class BuyViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public TextView f21818a;
        public SimpleDraweeView b;

        public BuyViewHolder(@NonNull View view) {
            super(view);
            this.f21818a = (TextView) view.findViewById(R.id.model_name);
            this.b = (SimpleDraweeView) view.findViewById(R.id.sd_model_img);
        }
    }

    public static class TimeSpanViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public TextView f21820a;

        public TimeSpanViewHolder(@NonNull View view) {
            super(view);
            this.f21820a = (TextView) view.findViewById(R.id.time);
        }
    }

    public static class ActionViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public TextView f21815a;
        public View b;
        public View c;
        public View d;

        public ActionViewHolder(@NonNull View view) {
            super(view);
            this.f21815a = (TextView) view.findViewById(R.id.device_name);
            this.b = view.findViewById(R.id.divider_x);
            this.c = view.findViewById(R.id.divider_last);
            this.d = view.findViewById(R.id.icon_selected);
        }
    }

    public static class BleViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public SimpleDraweeView f21816a;
        public TextView b;
        public View c;

        public BleViewHolder(@NonNull View view) {
            super(view);
            this.f21816a = (SimpleDraweeView) view.findViewById(R.id.device_icon);
            this.b = (TextView) view.findViewById(R.id.device_name);
            this.c = view.findViewById(R.id.divider_last);
        }
    }

    public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        public long getItemId(int i) {
            return (long) i;
        }

        public MyAdapter() {
        }

        @NonNull
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            if (i == 1) {
                return new ActionViewHolder(LayoutInflater.from(PluginRecommendSceneActionActivity.this).inflate(R.layout.item_plugin_rec_action, (ViewGroup) null));
            }
            if (i == 2) {
                return new TimeSpanViewHolder(LayoutInflater.from(PluginRecommendSceneActionActivity.this).inflate(R.layout.item_plugin_rec_timespan, (ViewGroup) null));
            }
            return null;
        }

        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            if (getItemViewType(i) == 1) {
                ActionViewHolder actionViewHolder = (ActionViewHolder) viewHolder;
                UIData uIData = (UIData) PluginRecommendSceneActionActivity.this.d.get(i);
                if (uIData != null) {
                    if (uIData.b == null || TextUtils.isEmpty(uIData.b.name)) {
                        actionViewHolder.f21815a.setText("");
                    } else if (uIData.b.isOnline) {
                        actionViewHolder.f21815a.setText(uIData.b.name);
                    } else {
                        TextView textView = actionViewHolder.f21815a;
                        textView.setText(uIData.b.name + Operators.BRACKET_START_STR + PluginRecommendSceneActionActivity.this.getString(R.string.offline_device) + Operators.BRACKET_END_STR);
                    }
                    if (PluginRecommendSceneActionActivity.this.h == i) {
                        actionViewHolder.f21815a.setTextColor(PluginRecommendSceneActionActivity.this.getResources().getColor(R.color.class_text_33));
                        actionViewHolder.d.setVisibility(0);
                    } else {
                        actionViewHolder.f21815a.setTextColor(PluginRecommendSceneActionActivity.this.getResources().getColor(R.color.black));
                        actionViewHolder.d.setVisibility(8);
                    }
                    actionViewHolder.itemView.setOnClickListener(new View.OnClickListener(i) {
                        private final /* synthetic */ int f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void onClick(View view) {
                            PluginRecommendSceneActionActivity.MyAdapter.this.a(this.f$1, view);
                        }
                    });
                }
            } else if (getItemViewType(i) == 2) {
                TimeSpanViewHolder timeSpanViewHolder = (TimeSpanViewHolder) viewHolder;
                SmartHomeSceneUtility.a(PluginRecommendSceneActionActivity.this.g.u, timeSpanViewHolder.f21820a, PluginRecommendSceneActionActivity.this.getString(R.string.scene_exetime_second_day));
                timeSpanViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        PluginRecommendSceneActionActivity.MyAdapter.this.a(view);
                    }
                });
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(int i, View view) {
            int unused = PluginRecommendSceneActionActivity.this.h = i;
            notifyDataSetChanged();
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(View view) {
            Intent intent = new Intent(PluginRecommendSceneActionActivity.this, SceneCreateTimeEdit2Activity.class);
            intent.putExtra("is_from_recommend", true);
            PluginRecommendSceneActionActivity.this.startActivityForResult(intent, 1011);
        }

        public int getItemViewType(int i) {
            if (PluginRecommendSceneActionActivity.this.d == null) {
                return super.getItemViewType(i);
            }
            return ((UIData) PluginRecommendSceneActionActivity.this.d.get(i)).f21821a;
        }

        public int getItemCount() {
            if (PluginRecommendSceneActionActivity.this.d == null) {
                return 0;
            }
            return PluginRecommendSceneActionActivity.this.d.size();
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
            return new BuyViewHolder(LayoutInflater.from(PluginRecommendSceneActionActivity.this).inflate(R.layout.light_rec_scene_buy_item, (ViewGroup) null));
        }

        /* renamed from: a */
        public void onBindViewHolder(@NonNull BuyViewHolder buyViewHolder, int i) {
            JSONObject jSONObject = this.b.get(i);
            String next = jSONObject.keys().next();
            if (!TextUtils.isEmpty(next)) {
                DeviceFactory.b(next, buyViewHolder.b);
                if (CoreApi.a().d(next) == null || TextUtils.isEmpty(CoreApi.a().d(next).p())) {
                    buyViewHolder.f21818a.setText("");
                } else {
                    buyViewHolder.f21818a.setText(CoreApi.a().d(next).p());
                }
                buyViewHolder.itemView.setOnClickListener(new View.OnClickListener(jSONObject, next) {
                    private final /* synthetic */ JSONObject f$1;
                    private final /* synthetic */ String f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void onClick(View view) {
                        PluginRecommendSceneActionActivity.BuyAdapter.this.a(this.f$1, this.f$2, view);
                    }
                });
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(JSONObject jSONObject, String str, View view) {
            STAT.d.l("lockspeaker", PluginRecommendSceneActionActivity.this.b == null ? "" : PluginRecommendSceneActionActivity.this.b.model);
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
}
