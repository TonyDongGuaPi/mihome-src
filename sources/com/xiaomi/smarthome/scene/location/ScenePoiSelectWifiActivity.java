package com.xiaomi.smarthome.scene.location;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshListView;
import com.xiaomi.smarthome.library.common.widget.PullDownDragListView;
import com.xiaomi.smarthome.scene.location.model.WifiGroupData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;

public class ScenePoiSelectWifiActivity extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public List<String> f21589a = new ArrayList();
    /* access modifiers changed from: private */
    public List<String> b = new ArrayList();
    /* access modifiers changed from: private */
    public ArrayList<String> c = new ArrayList<>();
    private boolean d = false;
    /* access modifiers changed from: private */
    public WifiManager e;
    private List<WifiConfiguration> f = new ArrayList();
    /* access modifiers changed from: private */
    public XQProgressDialog g;
    /* access modifiers changed from: private */
    public PullDownDragListView h;
    private View i;
    private WifiGroupData j;
    private SimpleAdapter k;
    private String l;
    private BroadcastReceiver m = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!action.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                action.equals("android.net.wifi.SCAN_RESULTS");
            } else if (ScenePoiSelectWifiActivity.this.e.getWifiState() == 3) {
                ScenePoiSelectWifiActivity.this.h.doRefresh();
            }
        }
    };
    private Map<String, String> n = new HashMap();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.wifi_group_choose_list);
        this.e = (WifiManager) getSystemService("wifi");
        Intent intent = getIntent();
        if (intent != null) {
            this.j = (WifiGroupData) intent.getParcelableExtra("data");
            this.l = intent.getStringExtra("mode");
        }
        d();
        a();
        b();
    }

    private void a() {
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        if (textView != null) {
            textView.setText(R.string.scene_enter_or_leave_wifi);
        }
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ScenePoiSelectWifiActivity.this.setResult(0);
                ScenePoiSelectWifiActivity.this.finish();
            }
        });
        TextView textView2 = (TextView) findViewById(R.id.module_a_3_right_text_btn);
        textView2.setText(R.string.next_button);
        textView2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                JSONArray jSONArray = new JSONArray();
                for (int i = 0; i < ScenePoiSelectWifiActivity.this.c.size(); i++) {
                    int indexOf = ScenePoiSelectWifiActivity.this.b.indexOf((String) ScenePoiSelectWifiActivity.this.c.get(i));
                    if (indexOf >= 0) {
                        jSONArray.put(ScenePoiSelectWifiActivity.this.b.get(indexOf));
                    }
                }
                intent.putExtra("value", jSONArray.toString());
                if (jSONArray.length() > 0) {
                    intent.putExtra("name", jSONArray.optString(0));
                }
                intent.putExtra("wifi_size", jSONArray.length());
                ScenePoiSelectWifiActivity.this.setResult(-1, intent);
                ScenePoiSelectWifiActivity.this.finish();
            }
        });
        this.i = findViewById(R.id.common_white_empty_view);
        this.h = (PullDownDragListView) findViewById(R.id.wifi_list);
        this.k = new SimpleAdapter();
        this.h.addHeaderView(LayoutInflater.from(this).inflate(R.layout.common_list_space_empty, this.h, false));
        this.h.setAdapter(this.k);
        this.h.setRefreshListener(new CustomPullDownRefreshListView.OnRefreshListener() {
            public void startRefresh() {
            }
        });
    }

    private void b() {
        if (this.e.isWifiEnabled()) {
            List<WifiConfiguration> configuredNetworks = this.e.getConfiguredNetworks();
            HashSet hashSet = new HashSet();
            if (configuredNetworks != null) {
                for (WifiConfiguration wifiConfiguration : configuredNetworks) {
                    hashSet.add(convertToQuotedString(wifiConfiguration.SSID));
                }
            }
            List<String> arrayList = new ArrayList<>();
            HashMap hashMap = new HashMap();
            if (!(this.j == null || (arrayList = this.j.c()) == null || arrayList.size() <= 0)) {
                for (String str : arrayList) {
                    hashMap.put(str, str);
                }
            }
            String str2 = null;
            WifiInfo connectionInfo = this.e.getConnectionInfo();
            if (connectionInfo != null) {
                str2 = convertToQuotedString(connectionInfo.getSSID());
                hashSet.remove(str2);
            }
            if (arrayList != null && arrayList.size() > 0) {
                for (String remove : arrayList) {
                    hashSet.remove(remove);
                }
            }
            Map<String, String> c2 = c();
            if (c2 != null && c2.size() > 0) {
                for (String remove2 : c2.keySet()) {
                    hashSet.remove(remove2);
                }
                if (arrayList != null) {
                    for (String str3 : arrayList) {
                        String remove3 = c2.remove(str3);
                        if (!TextUtils.isEmpty(remove3)) {
                            hashMap.put(str3, remove3);
                        }
                    }
                }
                if (!c2.isEmpty()) {
                    this.f21589a.addAll(0, c2.values());
                    this.b.addAll(0, c2.keySet());
                }
            }
            this.f21589a.addAll(hashSet);
            this.b.addAll(hashSet);
            if (arrayList != null && !TextUtils.isEmpty(str2) && !arrayList.contains(str2)) {
                this.f21589a.add(0, str2);
                this.b.add(0, str2);
            }
            if (!(this.j == null || arrayList == null || arrayList.size() <= 0)) {
                ArrayList arrayList2 = new ArrayList();
                for (String str4 : arrayList) {
                    arrayList2.add(hashMap.get(str4));
                }
                this.f21589a.addAll(0, arrayList2);
                this.b.addAll(0, arrayList);
                this.c.addAll(arrayList);
            }
            if (this.f21589a.size() == 0) {
                this.i.setVisibility(0);
                this.h.setVisibility(8);
                return;
            }
            this.i.setVisibility(8);
            this.h.setVisibility(0);
            this.k.notifyDataSetChanged();
        }
    }

    private Map<String, String> c() {
        Map<String, Set<String>> a2;
        DeviceTagInterface<Device> b2 = SmartHomeDeviceHelper.a().b();
        if (b2 == null || (a2 = b2.a(2)) == null || a2.size() == 0) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (String next : a2.keySet()) {
            String b3 = b2.b(next);
            String e2 = b2.e(next);
            if (!TextUtils.isEmpty(b3)) {
                hashMap.put(b3, e2);
            }
        }
        return hashMap;
    }

    public static String convertToQuotedString(String str) {
        if (str == null) {
            return "";
        }
        return (str.length() >= 2 && str.charAt(0) == '\"' && str.charAt(str.length() - 1) == '\"') ? str.substring(1, str.length() - 1) : str;
    }

    private void d() {
        if (!this.e.isWifiEnabled()) {
            registerReceiver(this.m, new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED"));
            new MLAlertDialog.Builder(this).b((int) R.string.close_wifi_message).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    XQProgressDialog unused = ScenePoiSelectWifiActivity.this.g = new XQProgressDialog(ScenePoiSelectWifiActivity.this);
                    ScenePoiSelectWifiActivity.this.g.setMessage(ScenePoiSelectWifiActivity.this.getString(R.string.wifi_setting_wifi_opening));
                    ScenePoiSelectWifiActivity.this.g.setCancelable(false);
                    ScenePoiSelectWifiActivity.this.g.show();
                    ScenePoiSelectWifiActivity.this.e.setWifiEnabled(true);
                    ScenePoiSelectWifiActivity.this.mHandler.postDelayed(new Runnable() {
                        public void run() {
                            ScenePoiSelectWifiActivity.this.g.dismiss();
                            new MLAlertDialog.Builder(ScenePoiSelectWifiActivity.this).b((int) R.string.close_wifi_failed).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    ScenePoiSelectWifiActivity.this.finish();
                                }
                            }).a(false).d();
                        }
                    }, 20000);
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ScenePoiSelectWifiActivity.this.finish();
                }
            }).a(false).d();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        try {
            unregisterReceiver(this.m);
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public boolean isSelected(String str) {
        for (int i2 = 0; i2 < this.c.size(); i2++) {
            if (this.c.get(i2).equals(str)) {
                return true;
            }
        }
        return false;
    }

    class SimpleAdapter extends BaseAdapter {
        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        SimpleAdapter() {
        }

        public int getCount() {
            return ScenePoiSelectWifiActivity.this.f21589a.size();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            String str = (String) ScenePoiSelectWifiActivity.this.f21589a.get(i);
            boolean isSelected = ScenePoiSelectWifiActivity.this.isSelected((String) ScenePoiSelectWifiActivity.this.b.get(i));
            if (view == null) {
                view = ScenePoiSelectWifiActivity.this.getLayoutInflater().inflate(R.layout.wifi_choose_list_item, (ViewGroup) null);
                view.setOnClickListener(new View.OnClickListener() {
                    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v6, resolved type: java.lang.Object} */
                    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: java.lang.String} */
                    /* JADX WARNING: Multi-variable type inference failed */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public void onClick(android.view.View r6) {
                        /*
                            r5 = this;
                            java.lang.Object r0 = r6.getTag()
                            java.lang.Boolean r0 = (java.lang.Boolean) r0
                            boolean r0 = r0.booleanValue()
                            if (r0 != 0) goto L_0x0022
                            com.xiaomi.smarthome.scene.location.ScenePoiSelectWifiActivity$SimpleAdapter r1 = com.xiaomi.smarthome.scene.location.ScenePoiSelectWifiActivity.SimpleAdapter.this
                            com.xiaomi.smarthome.scene.location.ScenePoiSelectWifiActivity r1 = com.xiaomi.smarthome.scene.location.ScenePoiSelectWifiActivity.this
                            java.util.ArrayList r1 = r1.c
                            int r1 = r1.size()
                            r2 = 6
                            if (r1 < r2) goto L_0x0022
                            r6 = 2131494124(0x7f0c04ec, float:1.8611748E38)
                            com.xiaomi.smarthome.library.common.util.ToastUtil.a((int) r6)
                            return
                        L_0x0022:
                            r1 = 2131434014(0x7f0b1a1e, float:1.848983E38)
                            android.view.View r1 = r6.findViewById(r1)
                            android.widget.TextView r1 = (android.widget.TextView) r1
                            r2 = 2131432270(0x7f0b134e, float:1.8486293E38)
                            android.view.View r2 = r6.findViewById(r2)
                            android.widget.ImageView r2 = (android.widget.ImageView) r2
                            r3 = 2131431751(0x7f0b1147, float:1.848524E38)
                            android.view.View r3 = r6.findViewById(r3)
                            android.widget.CheckBox r3 = (android.widget.CheckBox) r3
                            r4 = r0 ^ 1
                            r3.setChecked(r4)
                            java.lang.CharSequence r3 = r1.getText()
                            java.lang.String r3 = r3.toString()
                            com.xiaomi.smarthome.scene.location.ScenePoiSelectWifiActivity$SimpleAdapter r4 = com.xiaomi.smarthome.scene.location.ScenePoiSelectWifiActivity.SimpleAdapter.this
                            com.xiaomi.smarthome.scene.location.ScenePoiSelectWifiActivity r4 = com.xiaomi.smarthome.scene.location.ScenePoiSelectWifiActivity.this
                            java.util.List r4 = r4.f21589a
                            java.lang.CharSequence r1 = r1.getText()
                            java.lang.String r1 = r1.toString()
                            int r1 = r4.indexOf(r1)
                            if (r1 <= 0) goto L_0x006f
                            com.xiaomi.smarthome.scene.location.ScenePoiSelectWifiActivity$SimpleAdapter r3 = com.xiaomi.smarthome.scene.location.ScenePoiSelectWifiActivity.SimpleAdapter.this
                            com.xiaomi.smarthome.scene.location.ScenePoiSelectWifiActivity r3 = com.xiaomi.smarthome.scene.location.ScenePoiSelectWifiActivity.this
                            java.util.List r3 = r3.b
                            java.lang.Object r1 = r3.get(r1)
                            r3 = r1
                            java.lang.String r3 = (java.lang.String) r3
                        L_0x006f:
                            if (r0 == 0) goto L_0x008b
                            r0 = 0
                            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
                            r6.setTag(r0)
                            r6 = 2130843674(0x7f02181a, float:1.7292478E38)
                            r2.setImageResource(r6)
                            com.xiaomi.smarthome.scene.location.ScenePoiSelectWifiActivity$SimpleAdapter r6 = com.xiaomi.smarthome.scene.location.ScenePoiSelectWifiActivity.SimpleAdapter.this
                            com.xiaomi.smarthome.scene.location.ScenePoiSelectWifiActivity r6 = com.xiaomi.smarthome.scene.location.ScenePoiSelectWifiActivity.this
                            java.util.ArrayList r6 = r6.c
                            r6.remove(r3)
                            goto L_0x00a4
                        L_0x008b:
                            r0 = 1
                            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
                            r6.setTag(r0)
                            r6 = 2130843675(0x7f02181b, float:1.729248E38)
                            r2.setImageResource(r6)
                            com.xiaomi.smarthome.scene.location.ScenePoiSelectWifiActivity$SimpleAdapter r6 = com.xiaomi.smarthome.scene.location.ScenePoiSelectWifiActivity.SimpleAdapter.this
                            com.xiaomi.smarthome.scene.location.ScenePoiSelectWifiActivity r6 = com.xiaomi.smarthome.scene.location.ScenePoiSelectWifiActivity.this
                            java.util.ArrayList r6 = r6.c
                            r6.add(r3)
                        L_0x00a4:
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scene.location.ScenePoiSelectWifiActivity.SimpleAdapter.AnonymousClass1.onClick(android.view.View):void");
                    }
                });
            }
            TextView textView = (TextView) view.findViewById(R.id.wifi_text);
            if (textView != null) {
                textView.setText(str);
            }
            ImageView imageView = (ImageView) view.findViewById(R.id.select_flag_image);
            if (imageView != null) {
                if (isSelected) {
                    imageView.setImageResource(R.drawable.wifi_check_press);
                } else {
                    imageView.setImageResource(R.drawable.wifi_check_normal);
                }
            }
            CheckBox checkBox = (CheckBox) view.findViewById(R.id.ratio_btn);
            checkBox.setChecked(isSelected);
            view.setTag(Boolean.valueOf(isSelected));
            checkBox.setClickable(false);
            return view;
        }
    }
}
