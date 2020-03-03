package com.xiaomi.smarthome.miio.activity;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.config.SHConfig;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.widget.ExpandListView;
import com.xiaomi.smarthome.wificonfig.WifiScanHomelog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class HomeLogWifiSetting extends BaseActivity implements HomeLogContants {
    private static final int m = 100;
    private static final int n = 101;
    private static final int o = 10;
    private static final int r = 3;
    private boolean A = true;
    private SimpleAdapter B = null;
    private SimpleAdapter C = null;
    private View D = null;
    private View E = null;
    /* access modifiers changed from: private */
    public boolean F = false;
    /* access modifiers changed from: private */
    public boolean G = false;
    private BroadcastReceiver H = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals("android.net.wifi.WIFI_STATE_CHANGED")) {
                if (HomeLogWifiSetting.this.p.getWifiState() == 3) {
                    HomeLogWifiSetting.this.p.startScan();
                    List unused = HomeLogWifiSetting.this.q = HomeLogWifiSetting.this.p.getConfiguredNetworks();
                }
            } else if (action.equals("android.net.wifi.SCAN_RESULTS") && HomeLogWifiSetting.this.x) {
                boolean unused2 = HomeLogWifiSetting.this.x = false;
                if (HomeLogWifiSetting.this.t != null) {
                    HomeLogWifiSetting.this.t.dismiss();
                }
                if (HomeLogWifiSetting.this.v == 1) {
                    HomeLogWifiSetting.this.setHomeConfig();
                }
                HomeLogWifiSetting.this.I.removeMessages(3);
            }
        }
    };
    /* access modifiers changed from: private */
    public Handler I = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 3) {
                HomeLogWifiSetting.this.t.dismiss();
                new MLAlertDialog.Builder(HomeLogWifiSetting.this).b((int) R.string.close_wifi_failed).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        HomeLogWifiSetting.this.finish();
                    }
                }).a(false).d();
            }
        }
    };
    /* access modifiers changed from: private */
    public WifiManager p;
    /* access modifiers changed from: private */
    public List<WifiConfiguration> q = new ArrayList();
    private RelativeLayout s;
    /* access modifiers changed from: private */
    public XQProgressDialog t;
    private Dialog u;
    /* access modifiers changed from: private */
    public int v = 0;
    /* access modifiers changed from: private */
    public JSONArray w;
    /* access modifiers changed from: private */
    public boolean x = false;
    /* access modifiers changed from: private */
    public ArrayList<String> y = new ArrayList<>();
    /* access modifiers changed from: private */
    public ArrayList<String> z = new ArrayList<>();

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.wifi_connection_log_setting2);
        this.p = (WifiManager) getSystemService("wifi");
        registerReceiver(this.H, new IntentFilter("android.net.wifi.WIFI_STATE_CHANGED"));
        registerReceiver(this.H, new IntentFilter("android.net.wifi.SCAN_RESULTS"));
        initViews();
        if (getIntent().getExtras() != null) {
            this.A = getIntent().getExtras().getBoolean("isGotoHome", true);
        }
        if (!this.p.isWifiEnabled()) {
            new MLAlertDialog.Builder(this).b((int) R.string.close_wifi_message).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    XQProgressDialog unused = HomeLogWifiSetting.this.t = new XQProgressDialog(HomeLogWifiSetting.this);
                    HomeLogWifiSetting.this.t.setMessage(HomeLogWifiSetting.this.getString(R.string.wifi_setting_wifi_opening));
                    HomeLogWifiSetting.this.t.setCancelable(false);
                    HomeLogWifiSetting.this.t.show();
                    HomeLogWifiSetting.this.p.setWifiEnabled(true);
                    HomeLogWifiSetting.this.I.sendEmptyMessageDelayed(3, 20000);
                    boolean unused2 = HomeLogWifiSetting.this.x = true;
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    HomeLogWifiSetting.this.finish();
                }
            }).a(false).d();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (CoreApi.a().q()) {
            return;
        }
        if (this.u == null) {
            this.u = SHApplication.showLoginDialog(this, true);
            this.u.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    HomeLogWifiSetting.this.finish();
                }
            });
        } else if (!this.u.isShowing()) {
            this.u.show();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.H);
        this.I.removeMessages(3);
    }

    /* access modifiers changed from: package-private */
    public void parseConfig() {
        ArrayList<String> arrayList;
        this.y.clear();
        this.z.clear();
        String c = SHConfig.a().c(HomeLogContants.f11749a);
        if (!TextUtils.isEmpty(c)) {
            try {
                this.w = new JSONArray(c);
            } catch (JSONException unused) {
            }
        }
        if (this.w == null) {
            this.w = new JSONArray();
        }
        for (int i = 0; i < this.w.length(); i++) {
            try {
                JSONObject jSONObject = this.w.getJSONObject(i);
                String optString = jSONObject.optString(HomeLogContants.d);
                if (optString.equals("home")) {
                    arrayList = this.y;
                } else if (optString.equalsIgnoreCase("office")) {
                    arrayList = this.z;
                }
                JSONArray optJSONArray = jSONObject.optJSONArray(HomeLogContants.c);
                if (optJSONArray != null) {
                    if (optJSONArray.length() != 0) {
                        for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                            arrayList.add(convertToQuotedString(optJSONArray.getString(i2)));
                        }
                    }
                }
                String optString2 = jSONObject.optString(HomeLogContants.b);
                if (optString2 != null && !optString2.isEmpty()) {
                    arrayList.add(convertToQuotedString(optString2));
                }
            } catch (JSONException unused2) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public ArrayList<String> createSelectArray(ArrayList<String> arrayList) {
        boolean z2;
        ArrayList<String> arrayList2 = new ArrayList<>();
        for (int i = 0; i < this.q.size(); i++) {
            String convertToQuotedString = convertToQuotedString(this.q.get(i).SSID);
            int i2 = 0;
            while (true) {
                if (i2 >= arrayList.size()) {
                    z2 = false;
                    break;
                } else if (isEqualWifi(arrayList.get(i2), convertToQuotedString)) {
                    z2 = true;
                    break;
                } else {
                    i2++;
                }
            }
            if (!z2) {
                arrayList2.add(convertToQuotedString);
            }
        }
        return arrayList2;
    }

    /* access modifiers changed from: package-private */
    public void initViews() {
        parseConfig();
        this.p = (WifiManager) getSystemService("wifi");
        this.q = this.p.getConfiguredNetworks();
        this.D = findViewById(R.id.home_divider_line);
        this.E = findViewById(R.id.office_divider_line);
        if (this.y.isEmpty()) {
            this.D.setVisibility(8);
        } else {
            this.D.setVisibility(0);
        }
        if (this.z.isEmpty()) {
            this.E.setVisibility(8);
        } else {
            this.E.setVisibility(0);
        }
        this.s = (RelativeLayout) findViewById(R.id.wifi_login_button);
        this.s.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                boolean z;
                if (!HomeLogWifiSetting.this.z.isEmpty() || !HomeLogWifiSetting.this.y.isEmpty()) {
                    boolean z2 = true;
                    boolean z3 = true;
                    for (int i = 0; i < HomeLogWifiSetting.this.y.size(); i++) {
                        String str = (String) HomeLogWifiSetting.this.y.get(i);
                        int i2 = 0;
                        while (true) {
                            if (i2 >= HomeLogWifiSetting.this.z.size()) {
                                break;
                            } else if (((String) HomeLogWifiSetting.this.z.get(i2)).equals(str)) {
                                z3 = false;
                                break;
                            } else {
                                i2++;
                            }
                        }
                        if (!z3) {
                            break;
                        }
                    }
                    if (!z3) {
                        Toast.makeText(HomeLogWifiSetting.this, R.string.choose_same_ssid_error, 0).show();
                        return;
                    }
                    if (HomeLogWifiSetting.this.F) {
                        HomeLogWifiSetting.this.setHomeConfig();
                        z = true;
                    } else {
                        z = false;
                    }
                    if (HomeLogWifiSetting.this.G) {
                        HomeLogWifiSetting.this.setOfficeConfig();
                    } else {
                        z2 = false;
                    }
                    if (!z && HomeLogWifiSetting.this.y.size() > 0) {
                        try {
                            SHConfig.a().a(0, "set_location");
                            JSONObject jSONObject = new JSONObject();
                            jSONObject.put(HomeLogContants.d, "home");
                            JSONArray jSONArray = new JSONArray();
                            for (int i3 = 0; i3 < HomeLogWifiSetting.this.y.size(); i3++) {
                                jSONArray.put(HomeLogWifiSetting.this.y.get(i3));
                            }
                            jSONObject.put(HomeLogContants.c, jSONArray);
                            jSONObject.put(HomeLogContants.e, System.currentTimeMillis());
                            jSONObject.put(HomeLogContants.f, new JSONArray());
                            jSONObject.put(HomeLogContants.g, new JSONObject());
                            if (HomeLogWifiSetting.this.w == null) {
                                JSONArray unused = HomeLogWifiSetting.this.w = new JSONArray();
                            }
                            HomeLogWifiSetting.this.setWifiLocation(jSONObject);
                        } catch (JSONException unused2) {
                        }
                    }
                    if (!z2 && HomeLogWifiSetting.this.z.size() > 0) {
                        try {
                            SHConfig.a().a(0, "set_location");
                            JSONObject jSONObject2 = new JSONObject();
                            jSONObject2.put(HomeLogContants.d, "office");
                            JSONArray jSONArray2 = new JSONArray();
                            for (int i4 = 0; i4 < HomeLogWifiSetting.this.z.size(); i4++) {
                                jSONArray2.put(HomeLogWifiSetting.this.z.get(i4));
                            }
                            jSONObject2.put(HomeLogContants.c, jSONArray2);
                            jSONObject2.put(HomeLogContants.e, System.currentTimeMillis());
                            jSONObject2.put(HomeLogContants.f, new JSONArray());
                            jSONObject2.put(HomeLogContants.g, new JSONObject());
                            if (HomeLogWifiSetting.this.w == null) {
                                JSONArray unused3 = HomeLogWifiSetting.this.w = new JSONArray();
                            }
                            HomeLogWifiSetting.this.setWifiLocation(jSONObject2);
                        } catch (JSONException unused4) {
                        }
                    }
                    HomeLogWifiSetting.this.gotoMainPage();
                    return;
                }
                Toast.makeText(HomeLogWifiSetting.this, R.string.chose_cannot_both_empty, 0).show();
            }
        });
        findViewById(R.id.home_wifi_title).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (HomeLogWifiSetting.this.q == null) {
                    Toast.makeText(HomeLogWifiSetting.this, R.string.wifi_need_open, 0).show();
                    return;
                }
                for (int size = HomeLogWifiSetting.this.q.size() - 1; size >= 0; size--) {
                    if (TextUtils.isEmpty(((WifiConfiguration) HomeLogWifiSetting.this.q.get(size)).SSID)) {
                        HomeLogWifiSetting.this.q.remove(size);
                    }
                }
                String[] strArr = new String[HomeLogWifiSetting.this.q.size()];
                for (int i = 0; i < strArr.length; i++) {
                    strArr[i] = HomeLogWifiSetting.convertToQuotedString(((WifiConfiguration) HomeLogWifiSetting.this.q.get(i)).SSID);
                }
                Intent intent = new Intent();
                intent.setClass(HomeLogWifiSetting.this, WifiChooseListActivity.class);
                intent.putExtra("is_home_list", true);
                intent.putExtra(HomeLogContants.f, strArr);
                intent.putStringArrayListExtra("select_list", HomeLogWifiSetting.this.y);
                HomeLogWifiSetting.this.startActivityForResult(intent, 100);
            }
        });
        findViewById(R.id.office_wifi_title).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (HomeLogWifiSetting.this.q == null) {
                    Toast.makeText(HomeLogWifiSetting.this, R.string.wifi_need_open, 0).show();
                    return;
                }
                for (int size = HomeLogWifiSetting.this.q.size() - 1; size >= 0; size--) {
                    if (TextUtils.isEmpty(((WifiConfiguration) HomeLogWifiSetting.this.q.get(size)).SSID)) {
                        HomeLogWifiSetting.this.q.remove(size);
                    }
                }
                if (HomeLogWifiSetting.this.q.size() == 0) {
                    Toast.makeText(HomeLogWifiSetting.this, R.string.no_wifi_found, 0).show();
                    return;
                }
                String[] strArr = new String[HomeLogWifiSetting.this.q.size()];
                for (int i = 0; i < strArr.length; i++) {
                    strArr[i] = HomeLogWifiSetting.convertToQuotedString(((WifiConfiguration) HomeLogWifiSetting.this.q.get(i)).SSID);
                }
                Intent intent = new Intent();
                intent.setClass(HomeLogWifiSetting.this, WifiChooseListActivity.class);
                intent.putExtra("is_office_list", true);
                intent.putExtra(HomeLogContants.f, strArr);
                intent.putStringArrayListExtra("select_list", HomeLogWifiSetting.this.z);
                HomeLogWifiSetting.this.startActivityForResult(intent, 101);
            }
        });
        a();
        this.C = new SimpleAdapter(this.z);
        this.B = new SimpleAdapter(this.y);
        ((ExpandListView) findViewById(R.id.home_wifi_list)).setAdapter(this.B);
        ((ExpandListView) findViewById(R.id.office_wifi_list)).setAdapter(this.C);
    }

    /* access modifiers changed from: package-private */
    public boolean compareArrayList(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        if (arrayList.size() != arrayList2.size()) {
            return false;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            String str = arrayList.get(i);
            for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                if (!isEqualWifi(str, arrayList2.get(i2))) {
                    return false;
                }
            }
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            intent.getBooleanExtra("is_home_list", false);
            ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("result_list");
            if (i == 100) {
                if (!compareArrayList(this.y, stringArrayListExtra)) {
                    this.F = true;
                    this.y.clear();
                    for (int i3 = 0; i3 < stringArrayListExtra.size(); i3++) {
                        String convertToQuotedString = convertToQuotedString(stringArrayListExtra.get(i3));
                        if (!convertToQuotedString.isEmpty()) {
                            this.y.add(convertToQuotedString);
                        }
                    }
                    this.B.a(this.y);
                    this.B.notifyDataSetChanged();
                    if (this.y.isEmpty()) {
                        this.D.setVisibility(8);
                    } else {
                        this.D.setVisibility(0);
                    }
                } else {
                    return;
                }
            } else if (i == 101) {
                if (!compareArrayList(this.z, stringArrayListExtra)) {
                    this.G = true;
                    this.z.clear();
                    for (int i4 = 0; i4 < stringArrayListExtra.size(); i4++) {
                        String convertToQuotedString2 = convertToQuotedString(stringArrayListExtra.get(i4));
                        if (!convertToQuotedString2.isEmpty()) {
                            this.z.add(convertToQuotedString2);
                        }
                    }
                    this.C.a(this.z);
                    this.C.notifyDataSetChanged();
                    if (this.z.isEmpty()) {
                        this.E.setVisibility(8);
                    } else {
                        this.E.setVisibility(0);
                    }
                } else {
                    return;
                }
            }
            a();
        }
    }

    public static JSONArray createWifiListArray(List<ScanResult> list, String str) {
        int i;
        ArrayList arrayList = new ArrayList();
        Iterator<ScanResult> it = list.iterator();
        while (true) {
            i = 0;
            if (!it.hasNext()) {
                break;
            }
            ScanResult next = it.next();
            if (!next.BSSID.equalsIgnoreCase("00:00:00:00:00:00")) {
                int i2 = 0;
                while (true) {
                    if (i2 >= arrayList.size()) {
                        break;
                    } else if (((Integer) ((Pair) arrayList.get(i2)).first).intValue() >= next.level) {
                        arrayList.add(i2, new Pair(Integer.valueOf(next.level), next.BSSID));
                        i = 1;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i == 0 && arrayList.size() < 10) {
                    arrayList.add(new Pair(Integer.valueOf(next.level), next.BSSID));
                }
                if (arrayList.size() > 10) {
                    arrayList.remove(9);
                }
            }
        }
        JSONArray jSONArray = new JSONArray();
        while (i < arrayList.size()) {
            jSONArray.put(((Pair) arrayList.get(i)).second);
            i++;
        }
        return jSONArray;
    }

    /* access modifiers changed from: package-private */
    public void setOfficeConfig() {
        List<ScanResult> scanResults = this.p.getScanResults();
        if (scanResults.size() == 0) {
            if (this.v == 1) {
                new MLAlertDialog.Builder(this).b((int) R.string.home_set_failed).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        HomeLogWifiSetting.this.finish();
                    }
                }).a(false).d();
            } else {
                this.p.startScan();
                this.t = new XQProgressDialog(this);
                this.t.setMessage(getString(R.string.wifi_reconnect_title));
                this.t.setCancelable(false);
                this.t.show();
                this.p.setWifiEnabled(true);
                this.I.sendEmptyMessageDelayed(3, 20000);
                this.v = 1;
            }
        }
        try {
            SHConfig.a().a(0, "set_location");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(HomeLogContants.d, "office");
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.z.size(); i++) {
                jSONArray.put(this.z.get(i));
            }
            jSONObject.put(HomeLogContants.c, jSONArray);
            jSONObject.put(HomeLogContants.e, System.currentTimeMillis());
            String convertToQuotedString = convertToQuotedString(this.p.getConnectionInfo().getSSID());
            JSONObject jSONObject2 = new JSONObject();
            if (convertToQuotedString.length() > 0) {
                jSONObject2.put(convertToQuotedString, createWifiListArray(scanResults, convertToQuotedString));
            }
            jSONObject.put(HomeLogContants.g, jSONObject2);
            jSONObject.put(HomeLogContants.f, new JSONArray());
            if (this.w == null) {
                this.w = new JSONArray();
            }
            setWifiLocation(jSONObject);
        } catch (JSONException unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public void setHomeConfig() {
        List<ScanResult> scanResults = this.p.getScanResults();
        if (scanResults.size() == 0) {
            if (this.v == 1) {
                new MLAlertDialog.Builder(this).b((int) R.string.home_set_failed).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        HomeLogWifiSetting.this.finish();
                    }
                }).a(false).d();
            } else {
                this.p.startScan();
                this.t = new XQProgressDialog(this);
                this.t.setMessage(getString(R.string.wifi_reconnect_title));
                this.t.setCancelable(false);
                this.t.show();
                this.p.setWifiEnabled(true);
                this.I.sendEmptyMessageDelayed(3, 20000);
                this.v = 1;
            }
        }
        try {
            SHConfig.a().a(0, "set_location");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(HomeLogContants.d, "home");
            JSONArray jSONArray = new JSONArray();
            for (int i = 0; i < this.y.size(); i++) {
                jSONArray.put(this.y.get(i));
            }
            jSONObject.put(HomeLogContants.c, jSONArray);
            String convertToQuotedString = convertToQuotedString(this.p.getConnectionInfo().getSSID());
            jSONObject.put(HomeLogContants.e, System.currentTimeMillis());
            JSONObject jSONObject2 = new JSONObject();
            if (convertToQuotedString.length() > 0) {
                jSONObject2.put(convertToQuotedString, createWifiListArray(scanResults, convertToQuotedString));
            }
            jSONObject.put(HomeLogContants.g, jSONObject2);
            jSONObject.put(HomeLogContants.f, new JSONArray());
            if (this.w == null) {
                this.w = new JSONArray();
            }
            setWifiLocation(jSONObject);
        } catch (JSONException unused) {
        }
    }

    /* access modifiers changed from: package-private */
    public void setWifiLocation(JSONObject jSONObject) {
        JSONArray jSONArray = new JSONArray();
        boolean z2 = false;
        for (int i = 0; i < this.w.length(); i++) {
            try {
                JSONObject jSONObject2 = this.w.getJSONObject(i);
                if (jSONObject2.optString(HomeLogContants.d).equalsIgnoreCase(jSONObject.optString(HomeLogContants.d))) {
                    jSONObject.put(HomeLogContants.e, jSONObject2.optLong(HomeLogContants.e));
                    jSONArray.put(jSONObject);
                    z2 = true;
                } else {
                    jSONArray.put(this.w.getJSONObject(i));
                }
            } catch (JSONException unused) {
            }
        }
        if (!z2) {
            jSONArray.put(jSONObject);
        }
        this.w = jSONArray;
    }

    /* access modifiers changed from: package-private */
    public void gotoMainPage() {
        if (this.w != null) {
            SHConfig.a().a(HomeLogContants.f11749a, this.w.toString());
            if (this.w.length() > 0) {
                SHConfig.a().a(HomeLogContants.l, 0);
            }
        }
        if (this.A) {
            Intent intent = new Intent();
            intent.setClass(this, WifiLogActivity.class);
            intent.putExtra("is_first_set", true);
            startActivity(intent);
        } else {
            setResult(-1);
        }
        sendBroadcast(new Intent(WifiScanHomelog.b));
        finish();
    }

    public static String convertToQuotedString(String str) {
        if (str == null) {
            return "";
        }
        return (str.length() >= 2 && str.charAt(0) == '\"' && str.charAt(str.length() - 1) == '\"') ? str.substring(1, str.length() - 1) : str;
    }

    /* access modifiers changed from: package-private */
    public boolean isEqualWifi(String str, String str2) {
        if (str.startsWith("\"")) {
            str = str.substring(1, str.length() - 1);
        }
        if (str2.startsWith("\"")) {
            str2 = str2.substring(1, str2.length() - 1);
        }
        return str.equals(str2);
    }

    private void a() {
        if (this.y.size() > 0 || this.z.size() > 0) {
            this.s.setEnabled(true);
        } else {
            this.s.setEnabled(false);
        }
    }

    class SimpleAdapter extends BaseAdapter {

        /* renamed from: a  reason: collision with root package name */
        List<String> f11761a;

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        SimpleAdapter(List<String> list) {
            this.f11761a = list;
        }

        public void a(List<String> list) {
            this.f11761a = list;
        }

        public int getCount() {
            return this.f11761a.size();
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = HomeLogWifiSetting.this.getLayoutInflater().inflate(R.layout.wifi_name_list_item, (ViewGroup) null);
            }
            if (view == null) {
                return null;
            }
            View findViewById = view.findViewById(R.id.item_divider);
            if (findViewById != null) {
                findViewById.setVisibility(i == 0 ? 8 : 0);
            }
            TextView textView = (TextView) view.findViewById(R.id.wifi_name_text);
            if (textView != null) {
                textView.setText(this.f11761a.get(i));
            }
            return view;
        }
    }
}
