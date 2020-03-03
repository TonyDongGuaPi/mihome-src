package com.xiaomi.smarthome.miio.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.format.Time;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.config.SHConfig;
import com.xiaomi.smarthome.config.WifiConnectionConfig;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.framework.page.BaseWhiteActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.FontManager;
import com.xiaomi.smarthome.library.common.util.SmartHomeTitleMoreMenuHelper;
import com.xiaomi.smarthome.miio.page.smartgroup.SmartGroupConstants;
import com.xiaomi.smarthome.wificonfig.WifiLogManager;
import com.xiaomi.smarthome.wificonfig.WifiScanHomelog;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WifiLogActivity extends BaseWhiteActivity implements View.OnClickListener {
    public static final int ActionMode_AtHome = 1;
    public static final int ActionMode_AtOffice = 4;
    public static final int ActionMode_OutHome = 2;
    public static final int ActionMode_OutOffice = 8;
    public static final int ActionMode_Unknown = 0;
    public static final int LOAD_DATA_FINISH = 2;
    public static final int REFRESH = 1;

    /* renamed from: a  reason: collision with root package name */
    private ListView f11814a;
    /* access modifiers changed from: private */
    public BaseAdapter b;
    private ImageView c;
    /* access modifiers changed from: private */
    public View d;
    /* access modifiers changed from: private */
    public Typeface e;
    /* access modifiers changed from: private */
    public View f;
    private View g;
    private TextView h;
    private TextView i;
    private TextView j;
    private TextView k;
    /* access modifiers changed from: private */
    public JSONArray l;
    private boolean m = false;
    List<Log> mAllLogs = new ArrayList();
    XQProgressDialog mProcessDialog;
    private Log n;
    /* access modifiers changed from: private */
    public WifiLogHandler o;
    private WifiLogManager.IListener p = new WifiLogManager.IListener() {
        public void a() {
            TreeMap<String, String> c = WifiConnectionConfig.a().c();
            try {
                JSONArray unused = WifiLogActivity.this.l = new JSONArray(SHConfig.a().c(HomeLogContants.f11749a));
            } catch (JSONException unused2) {
            }
            long j = 0;
            try {
                if (WifiLogActivity.this.l != null) {
                    j = WifiLogActivity.this.l.getJSONObject(0).getLong(HomeLogContants.e);
                }
            } catch (JSONException unused3) {
            }
            List<Log> a2 = Log.a((Map<String, String>) c, j);
            Message obtainMessage = WifiLogActivity.this.o.obtainMessage();
            obtainMessage.what = 2;
            obtainMessage.obj = a2;
            WifiLogActivity.this.o.sendMessage(obtainMessage);
        }
    };

    private static class WifiLogHandler extends Handler {

        /* renamed from: a  reason: collision with root package name */
        private WeakReference<WifiLogActivity> f11826a;

        private WifiLogHandler(WifiLogActivity wifiLogActivity) {
            this.f11826a = new WeakReference<>(wifiLogActivity);
        }

        public void handleMessage(Message message) {
            WifiLogActivity wifiLogActivity;
            if (this.f11826a != null && (wifiLogActivity = (WifiLogActivity) this.f11826a.get()) != null && !wifiLogActivity.isFinishing()) {
                wifiLogActivity.a(message);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(Message message) {
        if (message.what == 1) {
            refreshHint();
            this.o.sendEmptyMessageDelayed(1, 1000);
        } else if (message.what == 2) {
            this.mAllLogs.clear();
            this.mAllLogs.addAll((List) message.obj);
            if (TextUtils.isEmpty(SHConfig.a().c(HomeLogContants.f11749a))) {
                startActivity(new Intent(this, HomeLogWifiSetting.class));
                finish();
                return;
            }
            refreshHint();
            if (this.mAllLogs.size() > 0 && this.mAllLogs.get(this.mAllLogs.size() - 1).g == null) {
                if (this.mAllLogs.size() == 2) {
                    this.n = this.mAllLogs.get(this.mAllLogs.size() - 1);
                }
                this.mAllLogs.remove(this.mAllLogs.size() - 1);
            }
            if (this.mAllLogs.size() > 0 && this.mAllLogs.get(this.mAllLogs.size() - 1).f11823a) {
                this.mAllLogs.remove(this.mAllLogs.size() - 1);
            }
            refreshView();
            this.b.notifyDataSetChanged();
            this.o.sendEmptyMessageDelayed(1, 1000);
            if (this.mProcessDialog != null) {
                this.mProcessDialog.dismiss();
            }
        }
    }

    public static class Log {

        /* renamed from: a  reason: collision with root package name */
        boolean f11823a;
        String b;
        String c;
        String d;
        String e;
        String f;
        String g;
        int h = 0;
        long i;
        String j;
        /* access modifiers changed from: private */
        public int k;

        public boolean a(Log log) {
            return this.b.equalsIgnoreCase(log.b) && this.c.equalsIgnoreCase(log.c) && this.e.equalsIgnoreCase(this.e);
        }

        public static String a(long j2) {
            String str;
            String str2;
            String str3;
            int i2 = (int) (j2 / 1000);
            if (i2 == 0) {
                return "0" + SHApplication.getAppContext().getString(R.string.second);
            }
            int i3 = i2 / 60;
            if (i3 == 0) {
                return "" + i2 + SHApplication.getAppContext().getString(R.string.second);
            }
            int i4 = i3 / 60;
            int i5 = i3 % 60;
            if (i4 == 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("");
                sb.append(i5);
                sb.append(SHApplication.getAppContext().getString(R.string.minute));
                if (i2 == 0) {
                    str3 = "";
                } else {
                    str3 = (i2 % 60) + SHApplication.getAppContext().getString(R.string.second);
                }
                sb.append(str3);
                return sb.toString();
            }
            int i6 = i4 / 24;
            if (i6 == 0) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("");
                sb2.append(i4);
                sb2.append(SHApplication.getAppContext().getString(R.string.hour));
                if (i5 == 0) {
                    str2 = "";
                } else {
                    str2 = i5 + SHApplication.getAppContext().getString(R.string.minute);
                }
                sb2.append(str2);
                return sb2.toString();
            }
            StringBuilder sb3 = new StringBuilder();
            sb3.append("");
            sb3.append(i6);
            sb3.append(SHApplication.getAppContext().getString(R.string.day));
            if (i4 == 0) {
                str = "";
            } else {
                str = (i4 % 24) + SHApplication.getAppContext().getString(R.string.hour);
            }
            sb3.append(str);
            return sb3.toString();
        }

        public static Log a(long j2, String str, Log log) {
            Log log2 = new Log();
            log2.i = j2;
            Time time = new Time();
            time.set(log2.i);
            log2.b = String.valueOf(time.monthDay);
            log2.c = String.valueOf(time.month + 1);
            log2.d = String.valueOf(time.weekDay);
            log2.e = String.valueOf(time.year);
            log2.j = str;
            log2.k = 0;
            Object[] objArr = new Object[2];
            StringBuilder sb = new StringBuilder();
            sb.append(time.hour < 10 ? "0" : "");
            sb.append(String.valueOf(time.hour));
            objArr[0] = sb.toString();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(time.minute < 10 ? "0" : "");
            sb2.append(String.valueOf(time.minute));
            objArr[1] = sb2.toString();
            log2.f = String.format("%s:%s", objArr);
            if (log2.j.equalsIgnoreCase("home")) {
                log2.h = R.drawable.at_home;
                if (log != null && log.h == 0) {
                    log.h = R.drawable.out_home;
                }
            } else if (log2.j.equalsIgnoreCase("office")) {
                log2.h = R.drawable.at_office;
                if (log != null && log.h == 0) {
                    log.h = R.drawable.out_office;
                }
            } else {
                log2.h = 0;
            }
            if (log == null) {
                return log2;
            }
            if (log.j.equalsIgnoreCase(log2.j)) {
                return null;
            }
            if (log != null) {
                if (log.j.equalsIgnoreCase("home")) {
                    log.f += " " + SHApplication.getAppContext().getString(R.string.header_title_home);
                    log.k = 1;
                    if (log2.j.equalsIgnoreCase("office")) {
                        log.k += 8;
                    }
                } else if (log.j.equalsIgnoreCase("office")) {
                    log.f += " " + SHApplication.getAppContext().getString(R.string.header_title_office);
                    log.k = 4;
                    if (log2.j.equalsIgnoreCase("home")) {
                        log.k += 2;
                    }
                } else if (log2.j.equalsIgnoreCase("home")) {
                    log.f += " " + SHApplication.getAppContext().getString(R.string.header_leave_home);
                    log.k = 2;
                } else {
                    log.f += " " + SHApplication.getAppContext().getString(R.string.header_leave_office);
                    log.k = 8;
                }
            }
            if (log.i - log2.i < 300000) {
                return null;
            }
            if (log2.j.equalsIgnoreCase(HomeLogContants.j)) {
                log.g = SHApplication.getAppContext().getString(R.string.item_outside) + a(log.i - log2.i);
            } else if (log2.j.equalsIgnoreCase("home")) {
                log.g = SHApplication.getAppContext().getString(R.string.item_home) + a(log.i - log2.i);
            } else {
                log.g = SHApplication.getAppContext().getString(R.string.item_office) + a(log.i - log2.i);
            }
            return log2;
        }

        public static List<Log> a(Map<String, String> map, long j2) {
            ArrayList arrayList = new ArrayList();
            Log log = null;
            long j3 = Long.MAX_VALUE;
            for (Map.Entry next : map.entrySet()) {
                long longValue = Long.valueOf((String) next.getKey()).longValue();
                new Time().set(longValue);
                if (Long.valueOf((String) next.getKey()).longValue() >= j2 && j3 - longValue >= 300000) {
                    Log a2 = a(longValue, (String) next.getValue(), log);
                    if (a2 != null) {
                        if (arrayList.size() == 0 || !((Log) arrayList.get(arrayList.size() - 1)).a(a2)) {
                            Log log2 = new Log();
                            log2.f11823a = true;
                            log2.b = a2.b;
                            log2.c = a2.c;
                            log2.d = a2.d;
                            log2.e = a2.e;
                            arrayList.add(log2);
                        }
                        arrayList.add(a2);
                        log = a2;
                    }
                    j3 = longValue;
                }
            }
            return arrayList;
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.wifi_log_layout);
        this.m = getIntent().getBooleanExtra("is_first_set", false);
        this.o = new WifiLogHandler();
        WifiLogManager.a().a(this.p);
        initViews();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        WifiLogManager.a().b(this.p);
        if (this.o != null) {
            this.o.removeCallbacksAndMessages((Object) null);
        }
        super.onDestroy();
    }

    /* access modifiers changed from: package-private */
    public void initViews() {
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.my_phone_name);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WifiLogActivity.this.finish();
            }
        });
        this.c = (ImageView) findViewById(R.id.module_a_3_return_more_more_btn);
        this.f = findViewById(R.id.common_white_empty_view);
        this.f.setVisibility(8);
        this.d = findViewById(R.id.wifi_location_hint);
        this.d.setVisibility(8);
        this.g = findViewById(R.id.wifi_location_now);
        this.h = (TextView) findViewById(R.id.wifi_location_hint_now_time);
        this.i = (TextView) findViewById(R.id.wifi_location_hint_now_status);
        this.j = (TextView) findViewById(R.id.wifi_location_hint_now);
        this.g.setVisibility(0);
        this.c.setVisibility(0);
        this.k = (TextView) findViewById(R.id.adjust_wifi_setting);
        this.k.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                WifiLogActivity.this.startActivity(new Intent(WifiLogActivity.this, HomeLogWifiSetting.class));
                WifiLogActivity.this.finish();
            }
        });
        this.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AnonymousClass1 r2 = new SmartHomeTitleMoreMenuHelper() {
                    public TextView a() {
                        return null;
                    }

                    public Device c() {
                        return null;
                    }

                    public Context b() {
                        return WifiLogActivity.this;
                    }

                    public boolean a(int i) {
                        if (i == R.string.delete) {
                            new MLAlertDialog.Builder(WifiLogActivity.this).b((CharSequence) WifiLogActivity.this.getResources().getString(R.string.log_clear_all_logs)).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    WifiConnectionConfig.a().d();
                                    WifiLogActivity.this.mAllLogs.clear();
                                    WifiLogActivity.this.b.notifyDataSetChanged();
                                    WifiLogManager.a().d();
                                    WifiLogActivity.this.f.setVisibility(0);
                                }
                            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).b().show();
                            return true;
                        }
                        if (i == R.string.setting_reset) {
                            new MLAlertDialog.Builder(WifiLogActivity.this).b((CharSequence) WifiLogActivity.this.getResources().getString(R.string.wifi_log_reset_query)).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    WifiLogManager.a().c();
                                    SHConfig a2 = SHConfig.a();
                                    a2.a(-1, "set_location");
                                    a2.a(HomeLogContants.f11749a, "");
                                    WifiConnectionConfig.a().d();
                                    WifiLogActivity.this.sendBroadcast(new Intent(WifiScanHomelog.b));
                                    WifiLogActivity.this.startActivity(new Intent(WifiLogActivity.this, HomeLogWifiSetting.class));
                                    WifiLogActivity.this.finish();
                                }
                            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).b().show();
                        }
                        if (i == R.string.menu_correct) {
                            WifiLogActivity.this.startActivity(new Intent(WifiLogActivity.this, HomeLogWifiSetting.class));
                            WifiLogActivity.this.finish();
                        }
                        return true;
                    }
                };
                r2.a(new int[]{R.string.menu_correct, R.string.delete, R.string.setting_reset});
                r2.j();
            }
        });
        initData();
        this.f11814a = (ListView) findViewById(R.id.log_list);
        this.b = new SimpleAdapter(this);
        this.f11814a.setAdapter(this.b);
        this.e = FontManager.a(SmartGroupConstants.f);
    }

    /* access modifiers changed from: package-private */
    public void refreshView() {
        if (this.mAllLogs.size() == 0) {
            this.f.setVisibility(0);
        }
        if (this.m) {
            ((ImageView) findViewById(R.id.empty_icon)).setImageResource(R.drawable.wifi_setting_success_check);
            TextView textView = (TextView) findViewById(R.id.common_white_empty_text_2);
            ((TextView) findViewById(R.id.common_white_empty_text)).setText(R.string.wifi_log_set_success);
            textView.setVisibility(0);
            textView.setText(R.string.wifi_log_set_success_sub_title);
        } else {
            ((ImageView) findViewById(R.id.empty_icon)).setImageResource(R.drawable.no_wifi_log);
            ((TextView) findViewById(R.id.common_white_empty_text)).setText(R.string.log_no_logs);
        }
        WifiManager wifiManager = (WifiManager) getSystemService("wifi");
        if (SHConfig.a().a("wifi_show_disable") == -1 && !wifiManager.isWifiEnabled()) {
            this.d.setVisibility(0);
            this.d.postDelayed(new Runnable() {
                public void run() {
                    WifiLogActivity.this.d.setVisibility(8);
                }
            }, 5000);
            SHConfig.a().a(0, "wifi_show_disable");
        }
    }

    /* access modifiers changed from: package-private */
    public void refreshHint() {
        Log log;
        long j2;
        if (this.l != null && this.l.length() != 0) {
            try {
                if (this.l.length() >= 2 || SHConfig.a().b(HomeLogContants.l) <= 0) {
                    this.j.setVisibility(0);
                    this.k.setVisibility(8);
                    boolean z = true;
                    if (this.mAllLogs.size() == 0 && this.n == null) {
                        try {
                            j2 = this.l.getJSONObject(0).getLong(HomeLogContants.e);
                            int i2 = 0;
                            while (i2 < this.l.length()) {
                                try {
                                    JSONObject optJSONObject = this.l.getJSONObject(i2).optJSONObject(HomeLogContants.g);
                                    if (optJSONObject != null && optJSONObject.length() > 0) {
                                        if (this.l.getJSONObject(i2).optString(HomeLogContants.d).equalsIgnoreCase("home")) {
                                            this.i.setText(R.string.header_title_home);
                                            this.j.setText(SHApplication.getAppContext().getString(R.string.header_content_home) + Log.a(System.currentTimeMillis() - j2));
                                        } else {
                                            this.i.setText(R.string.header_title_office);
                                            this.j.setText(SHApplication.getAppContext().getString(R.string.header_content_office) + Log.a(System.currentTimeMillis() - j2));
                                        }
                                        z = false;
                                    }
                                    if (z) {
                                        this.i.setText(R.string.header_title_outside);
                                        this.j.setText(SHApplication.getAppContext().getString(R.string.header_content_outside) + Log.a(System.currentTimeMillis() - j2));
                                    }
                                    i2++;
                                } catch (JSONException unused) {
                                }
                            }
                        } catch (JSONException unused2) {
                            j2 = 0;
                        }
                        this.h.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(j2)));
                        return;
                    }
                    if (this.n != null) {
                        log = this.n;
                    } else {
                        log = this.mAllLogs.get(1);
                    }
                    if (log.j.equals("home")) {
                        this.i.setText(R.string.header_title_home);
                        this.j.setText(SHApplication.getAppContext().getString(R.string.header_content_home) + Log.a(System.currentTimeMillis() - log.i));
                        if (log.k == 0) {
                            int unused3 = log.k = 1;
                        }
                    } else if (log.j.equals("office")) {
                        this.i.setText(R.string.header_title_office);
                        this.j.setText(SHApplication.getAppContext().getString(R.string.header_content_office) + Log.a(System.currentTimeMillis() - log.i));
                        if (log.k == 0) {
                            int unused4 = log.k = 4;
                        }
                    } else {
                        this.i.setText(R.string.header_title_outside);
                        this.j.setText(SHApplication.getAppContext().getString(R.string.header_content_outside) + Log.a(System.currentTimeMillis() - log.i));
                        if (log.k == 0) {
                            int unused5 = log.k = 10;
                        }
                    }
                    this.h.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(log.i)));
                    return;
                }
                this.h.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(SHConfig.a().b(HomeLogContants.l))));
                this.i.setText(R.string.wifi_change_hint);
                this.j.setVisibility(8);
                this.k.setVisibility(0);
            } catch (Throwable unused6) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void initData() {
        long j2;
        if (TextUtils.isEmpty(SHConfig.a().c(HomeLogContants.f11749a))) {
            this.mProcessDialog = new XQProgressDialog(this);
            this.mProcessDialog.setCancelable(false);
            this.mProcessDialog.setMessage(getResources().getString(R.string.log_downlaoding));
            this.mProcessDialog.show();
        } else {
            TreeMap<String, String> c2 = WifiConnectionConfig.a().c();
            try {
                this.l = new JSONArray(SHConfig.a().c(HomeLogContants.f11749a));
            } catch (JSONException unused) {
            }
            if (this.l == null) {
                this.mProcessDialog = new XQProgressDialog(this);
                this.mProcessDialog.setCancelable(false);
                this.mProcessDialog.setMessage(getResources().getString(R.string.log_downlaoding));
                this.mProcessDialog.show();
                WifiLogManager.a().a(true);
                return;
            }
            try {
                j2 = this.l.getJSONObject(0).getLong(HomeLogContants.e);
            } catch (JSONException unused2) {
                j2 = 0;
            }
            this.mAllLogs.addAll(Log.a((Map<String, String>) c2, j2));
            refreshHint();
            if (this.mAllLogs.size() > 0 && this.mAllLogs.get(this.mAllLogs.size() - 1).g == null) {
                if (this.mAllLogs.size() == 2) {
                    this.n = this.mAllLogs.get(this.mAllLogs.size() - 1);
                }
                this.mAllLogs.remove(this.mAllLogs.size() - 1);
            }
            if (this.mAllLogs.size() > 0 && this.mAllLogs.get(this.mAllLogs.size() - 1).f11823a) {
                this.mAllLogs.remove(this.mAllLogs.size() - 1);
            }
        }
        WifiLogManager.a().a(true);
    }

    class ViewTag {

        /* renamed from: a  reason: collision with root package name */
        RelativeLayout f11825a;
        RelativeLayout b;
        TextView c;
        TextView d;
        TextView e;
        RelativeLayout f;
        ImageView g;
        ImageView h;
        ImageView i;
        ImageView j;
        TextView k;
        TextView l;
        int m;

        ViewTag() {
        }
    }

    public void onClick(View view) {
        Log log = this.mAllLogs.get(((ViewTag) view.getTag()).m);
        if (log != null && log.k != 0) {
            Intent intent = new Intent(this, WifiLogStatActivity.class);
            int b2 = log.k;
            if ((log.k & 1) == 1) {
                intent.putExtra("type", 0);
                b2 = 1;
            } else if ((log.k & 4) == 4) {
                intent.putExtra("type", 1);
                b2 = 4;
            } else if ((log.k & 2) == 2) {
                intent.putExtra("type", 2);
                b2 = 2;
            } else if ((log.k & 8) == 8) {
                intent.putExtra("type", 3);
                b2 = 8;
            }
            ArrayList arrayList = new ArrayList();
            for (int size = this.mAllLogs.size() - 1; size >= 0; size--) {
                Log log2 = this.mAllLogs.get(size);
                if ((log2.k & b2) != 0) {
                    arrayList.add(String.valueOf(log2.i));
                    if (log2 == log) {
                        intent.putExtra("current", arrayList.size() - 1);
                    }
                }
            }
            intent.putStringArrayListExtra("list", arrayList);
            startActivity(intent);
        }
    }

    class SimpleAdapter extends BaseAdapter {

        /* renamed from: a  reason: collision with root package name */
        final int[] f11824a = {R.string.sunday, R.string.monday, R.string.tuesday, R.string.wednesday, R.string.thursday, R.string.friday, R.string.saturday};

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        public SimpleAdapter(Context context) {
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = WifiLogActivity.this.getLayoutInflater().inflate(R.layout.wifi_log_item, (ViewGroup) null);
                ViewTag viewTag = new ViewTag();
                viewTag.f11825a = (RelativeLayout) view.findViewById(R.id.log_time_container);
                viewTag.b = (RelativeLayout) view.findViewById(R.id.log_content);
                viewTag.c = (TextView) view.findViewById(R.id.day_text);
                viewTag.d = (TextView) view.findViewById(R.id.month_text);
                viewTag.e = (TextView) view.findViewById(R.id.week_text);
                viewTag.h = (ImageView) view.findViewById(R.id.feed_item_line_top);
                viewTag.g = (ImageView) view.findViewById(R.id.feed_item_line_circle_top);
                viewTag.f = (RelativeLayout) view.findViewById(R.id.top_line_container);
                viewTag.i = (ImageView) view.findViewById(R.id.feed_item_line_circle_bottom);
                viewTag.j = (ImageView) view.findViewById(R.id.feed_item_icon);
                viewTag.k = (TextView) view.findViewById(R.id.log_title_text);
                viewTag.l = (TextView) view.findViewById(R.id.log_detail_text);
                view.setTag(viewTag);
                view.setOnClickListener(WifiLogActivity.this);
            }
            Log log = WifiLogActivity.this.mAllLogs.get(i);
            ViewTag viewTag2 = (ViewTag) view.getTag();
            if (log.f11823a) {
                viewTag2.f11825a.setVisibility(0);
                viewTag2.b.setVisibility(8);
                viewTag2.i.setVisibility(0);
                viewTag2.c.setTypeface(WifiLogActivity.this.e);
                viewTag2.c.setText(log.b);
                TextView textView = viewTag2.d;
                textView.setText(log.c + WifiLogActivity.this.getString(R.string.month));
                viewTag2.e.setText(this.f11824a[Integer.valueOf(log.d).intValue()]);
            } else {
                viewTag2.f11825a.setVisibility(8);
                viewTag2.b.setVisibility(0);
                viewTag2.f.setVisibility(8);
                viewTag2.i.setVisibility(8);
                viewTag2.h.setVisibility(8);
                viewTag2.g.setVisibility(8);
                int i2 = i + 1;
                if (i2 >= WifiLogActivity.this.mAllLogs.size() || WifiLogActivity.this.mAllLogs.get(i2).f11823a) {
                    viewTag2.i.setVisibility(0);
                }
                if (WifiLogActivity.this.mAllLogs.get(i - 1).f11823a) {
                    viewTag2.f.setVisibility(0);
                    viewTag2.h.setVisibility(0);
                    viewTag2.g.setVisibility(0);
                }
                viewTag2.k.setText(log.f);
                viewTag2.l.setText(log.g);
                viewTag2.j.setImageResource(log.h);
            }
            viewTag2.m = i;
            return view;
        }

        public int getCount() {
            return WifiLogActivity.this.mAllLogs.size();
        }
    }
}
