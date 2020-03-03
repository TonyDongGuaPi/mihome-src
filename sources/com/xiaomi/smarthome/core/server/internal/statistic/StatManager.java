package com.xiaomi.smarthome.core.server.internal.statistic;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.core.server.internal.CoreAsyncCallback;
import com.xiaomi.smarthome.core.server.internal.CoreError;
import com.xiaomi.smarthome.core.server.internal.account.AccountManager;
import com.xiaomi.smarthome.core.server.internal.plugin.util.PreferenceUtils;
import com.xiaomi.smarthome.core.server.internal.statistic.api.StatApi;
import com.xiaomi.smarthome.core.server.internal.statistic.entity.StatInfoResult;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import com.xiaomi.smarthome.stat.report.StatLogUploader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class StatManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14709a = "sh_stat_logs_public";
    public static final String b = "sh_stat_logs_private";
    private static final long e = 600000;
    private static final long f = 600000;
    private static final int g = 3000;
    private static final int h = 1000;
    private static final long i = 259200000;
    private static final int j = 1;
    private static final int k = 2;
    private static final int l = 3;
    private static final int m = 4;
    private static final String n = "mihome";
    private static final String o = " ";
    private static StatManager p;
    private static Object q = new Object();
    MessageHandlerThread c;
    WorkerHandler d;
    private boolean r = false;
    /* access modifiers changed from: private */
    public Context s = CommonApplication.getAppContext();
    /* access modifiers changed from: private */
    public long t = 600000;
    /* access modifiers changed from: private */
    public int u = 1000;
    private ConcurrentLinkedQueue<RecordInfo> v = new ConcurrentLinkedQueue<>();
    /* access modifiers changed from: private */
    public StatLogUploader w = null;

    private StatManager() {
        e();
    }

    public static boolean a() {
        boolean z = false;
        if (p == null) {
            return false;
        }
        if (p.w != null) {
            return true;
        }
        if (p.w == null) {
            synchronized (p) {
                if (p.w != null) {
                    z = true;
                }
            }
            return z;
        } else if (p.w != null) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean b() {
        if (p == null) {
            return false;
        }
        if (p.w != null) {
            return true;
        }
        synchronized (p) {
            if (p.w == null) {
                p.w = new StatLogUploader() {
                };
            }
        }
        return true;
    }

    public static StatManager c() {
        if (p == null) {
            synchronized (q) {
                if (p == null) {
                    p = new StatManager();
                }
            }
        }
        return p;
    }

    public static String a(String str, String str2) {
        String m2 = AccountManager.a().m();
        long currentTimeMillis = System.currentTimeMillis();
        return m2 + " " + currentTimeMillis + " " + str + " " + str2;
    }

    private void e() {
        boolean z;
        synchronized (q) {
            z = this.r;
            if (!this.r) {
                this.r = true;
            }
        }
        if (!z) {
            this.c = new MessageHandlerThread("StatWorker");
            this.c.start();
            this.d = new WorkerHandler(this.c.getLooper());
            while (!this.v.isEmpty()) {
                this.d.obtainMessage(2, this.v.poll()).sendToTarget();
            }
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    StatManager.this.d();
                }
            }, 600000);
        }
    }

    public void d() {
        this.d.sendEmptyMessage(1);
    }

    /* access modifiers changed from: private */
    public void a(RecordInfo recordInfo) {
        String str;
        String str2;
        if (TextUtils.isEmpty(recordInfo.f14713a) || TextUtils.isEmpty(recordInfo.b)) {
            str2 = recordInfo.c;
            str = recordInfo.d;
        } else {
            str2 = a(recordInfo.f14713a, recordInfo.b);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("key", recordInfo.c);
                jSONObject.put("value", recordInfo.d);
                jSONObject.put("extra", recordInfo.e);
            } catch (JSONException unused) {
            }
            str = jSONObject.toString();
        }
        if (recordInfo.f) {
            PreferenceUtils.a(this.s.getSharedPreferences(b, 0), str2, str);
        } else {
            PreferenceUtils.a(this.s.getSharedPreferences(f14709a, 0), str2, str);
        }
    }

    /* access modifiers changed from: private */
    public void a(Context context) {
        ArrayList arrayList;
        StatManager statManager;
        ArrayList arrayList2;
        Iterator<Map.Entry<String, ?>> it;
        Pattern pattern;
        String str;
        String str2;
        String str3;
        SharedPreferences sharedPreferences;
        long j2;
        String str4;
        String str5;
        String str6;
        StatManager statManager2 = this;
        Context context2 = context;
        char c2 = 0;
        final SharedPreferences sharedPreferences2 = context2.getSharedPreferences(f14709a, 0);
        SharedPreferences sharedPreferences3 = context2.getSharedPreferences(b, 0);
        Pattern compile = Pattern.compile(" ");
        JSONArray jSONArray = new JSONArray();
        final ArrayList arrayList3 = new ArrayList();
        ArrayList<String> arrayList4 = new ArrayList<>();
        Map<String, ?> all = sharedPreferences2.getAll();
        long j3 = i;
        char c3 = 1;
        int i2 = 3;
        if (all != null && all.size() > 0) {
            for (Map.Entry next : all.entrySet()) {
                String str7 = (String) next.getKey();
                if (!TextUtils.isEmpty(str7)) {
                    String[] split = compile.split(str7);
                    if (split.length >= i2) {
                        String str8 = split[c2];
                        try {
                            j2 = Long.parseLong(split[c3]);
                        } catch (Exception unused) {
                            j2 = 0;
                        }
                        if (System.currentTimeMillis() - j2 > j3) {
                            arrayList4.add(str7);
                        } else if (jSONArray.length() > statManager2.u) {
                            break;
                        } else {
                            arrayList3.add(str7);
                            String str9 = split[2];
                            String str10 = split.length > i2 ? split[i2] : "";
                            try {
                                JSONObject jSONObject = new JSONObject((String) next.getValue());
                                str5 = jSONObject.optString("key");
                                try {
                                    str4 = jSONObject.optString("value");
                                } catch (JSONException unused2) {
                                    str4 = "";
                                    str6 = "";
                                    JSONObject jSONObject2 = new JSONObject();
                                    jSONObject2.put("type", str9);
                                    jSONObject2.put("am", str10);
                                    jSONObject2.put("uid", str8);
                                    sharedPreferences = sharedPreferences3;
                                    try {
                                        jSONObject2.put("time", j2 / 1000);
                                        jSONObject2.put("key", str5);
                                        jSONObject2.put("value", str4);
                                        jSONObject2.put("extra", str6);
                                        jSONArray.put(jSONObject2);
                                    } catch (JSONException unused3) {
                                    }
                                    sharedPreferences3 = sharedPreferences;
                                    statManager2 = this;
                                    c2 = 0;
                                    j3 = i;
                                    c3 = 1;
                                    i2 = 3;
                                }
                                try {
                                    str6 = jSONObject.optString("extra");
                                } catch (JSONException unused4) {
                                    str6 = "";
                                    JSONObject jSONObject22 = new JSONObject();
                                    jSONObject22.put("type", str9);
                                    jSONObject22.put("am", str10);
                                    jSONObject22.put("uid", str8);
                                    sharedPreferences = sharedPreferences3;
                                    jSONObject22.put("time", j2 / 1000);
                                    jSONObject22.put("key", str5);
                                    jSONObject22.put("value", str4);
                                    jSONObject22.put("extra", str6);
                                    jSONArray.put(jSONObject22);
                                    sharedPreferences3 = sharedPreferences;
                                    statManager2 = this;
                                    c2 = 0;
                                    j3 = i;
                                    c3 = 1;
                                    i2 = 3;
                                }
                            } catch (JSONException unused5) {
                                str5 = "";
                                str4 = "";
                                str6 = "";
                                JSONObject jSONObject222 = new JSONObject();
                                jSONObject222.put("type", str9);
                                jSONObject222.put("am", str10);
                                jSONObject222.put("uid", str8);
                                sharedPreferences = sharedPreferences3;
                                jSONObject222.put("time", j2 / 1000);
                                jSONObject222.put("key", str5);
                                jSONObject222.put("value", str4);
                                jSONObject222.put("extra", str6);
                                jSONArray.put(jSONObject222);
                                sharedPreferences3 = sharedPreferences;
                                statManager2 = this;
                                c2 = 0;
                                j3 = i;
                                c3 = 1;
                                i2 = 3;
                            }
                            JSONObject jSONObject2222 = new JSONObject();
                            try {
                                jSONObject2222.put("type", str9);
                                jSONObject2222.put("am", str10);
                                jSONObject2222.put("uid", str8);
                                sharedPreferences = sharedPreferences3;
                                jSONObject2222.put("time", j2 / 1000);
                                jSONObject2222.put("key", str5);
                                jSONObject2222.put("value", str4);
                                jSONObject2222.put("extra", str6);
                                jSONArray.put(jSONObject2222);
                            } catch (JSONException unused6) {
                            }
                            sharedPreferences3 = sharedPreferences;
                            statManager2 = this;
                            c2 = 0;
                            j3 = i;
                            c3 = 1;
                            i2 = 3;
                        }
                    }
                    sharedPreferences = sharedPreferences3;
                    sharedPreferences3 = sharedPreferences;
                    statManager2 = this;
                    c2 = 0;
                    j3 = i;
                    c3 = 1;
                    i2 = 3;
                }
            }
        }
        SharedPreferences sharedPreferences4 = sharedPreferences3;
        if (arrayList4.size() > 0) {
            SharedPreferences.Editor edit = sharedPreferences2.edit();
            for (String remove : arrayList4) {
                edit.remove(remove);
            }
            edit.commit();
        }
        JSONArray jSONArray2 = new JSONArray();
        ArrayList arrayList5 = new ArrayList();
        ArrayList<String> arrayList6 = new ArrayList<>();
        Map<String, ?> all2 = sharedPreferences4.getAll();
        if (all2 != null && all2.size() > 0) {
            Iterator<Map.Entry<String, ?>> it2 = all2.entrySet().iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                Map.Entry next2 = it2.next();
                String str11 = (String) next2.getKey();
                String[] split2 = compile.split(str11);
                if (split2.length >= 3) {
                    String str12 = split2[0];
                    long parseLong = Long.parseLong(split2[1]);
                    if (System.currentTimeMillis() - parseLong > i) {
                        arrayList6.add(str11);
                    } else {
                        pattern = compile;
                        statManager = this;
                        if (jSONArray2.length() > statManager.u) {
                            arrayList = arrayList5;
                            break;
                        }
                        arrayList5.add(str11);
                        String str13 = split2[2];
                        String str14 = split2.length > 3 ? split2[3] : "";
                        try {
                            JSONObject jSONObject3 = new JSONObject((String) next2.getValue());
                            str2 = jSONObject3.optString("key");
                            try {
                                str = jSONObject3.optString("value");
                                it = it2;
                            } catch (JSONException unused7) {
                                it = it2;
                                str = "";
                                str3 = "";
                                JSONObject jSONObject4 = new JSONObject();
                                arrayList2 = arrayList5;
                                jSONObject4.put("type", str13);
                                jSONObject4.put("am", str14);
                                jSONObject4.put("uid", str12);
                                try {
                                    jSONObject4.put("time", parseLong / 1000);
                                    jSONObject4.put("key", str2);
                                    jSONObject4.put("value", str);
                                    jSONObject4.put("extra", str3);
                                } catch (JSONException unused8) {
                                }
                                jSONArray2.put(jSONObject4);
                                compile = pattern;
                                it2 = it;
                                arrayList5 = arrayList2;
                            }
                            try {
                                str3 = jSONObject3.optString("extra");
                            } catch (JSONException unused9) {
                                str3 = "";
                                JSONObject jSONObject42 = new JSONObject();
                                arrayList2 = arrayList5;
                                jSONObject42.put("type", str13);
                                jSONObject42.put("am", str14);
                                jSONObject42.put("uid", str12);
                                jSONObject42.put("time", parseLong / 1000);
                                jSONObject42.put("key", str2);
                                jSONObject42.put("value", str);
                                jSONObject42.put("extra", str3);
                                jSONArray2.put(jSONObject42);
                                compile = pattern;
                                it2 = it;
                                arrayList5 = arrayList2;
                            }
                        } catch (JSONException unused10) {
                            it = it2;
                            str2 = "";
                            str = "";
                            str3 = "";
                            JSONObject jSONObject422 = new JSONObject();
                            arrayList2 = arrayList5;
                            jSONObject422.put("type", str13);
                            jSONObject422.put("am", str14);
                            jSONObject422.put("uid", str12);
                            jSONObject422.put("time", parseLong / 1000);
                            jSONObject422.put("key", str2);
                            jSONObject422.put("value", str);
                            jSONObject422.put("extra", str3);
                            jSONArray2.put(jSONObject422);
                            compile = pattern;
                            it2 = it;
                            arrayList5 = arrayList2;
                        }
                        JSONObject jSONObject4222 = new JSONObject();
                        arrayList2 = arrayList5;
                        try {
                            jSONObject4222.put("type", str13);
                            jSONObject4222.put("am", str14);
                            jSONObject4222.put("uid", str12);
                            jSONObject4222.put("time", parseLong / 1000);
                            jSONObject4222.put("key", str2);
                            jSONObject4222.put("value", str);
                            jSONObject4222.put("extra", str3);
                        } catch (JSONException unused11) {
                        }
                        jSONArray2.put(jSONObject4222);
                    }
                } else {
                    arrayList2 = arrayList5;
                    pattern = compile;
                    it = it2;
                }
                compile = pattern;
                it2 = it;
                arrayList5 = arrayList2;
            }
        }
        arrayList = arrayList5;
        statManager = this;
        if (arrayList6.size() > 0) {
            SharedPreferences.Editor edit2 = sharedPreferences4.edit();
            for (String remove2 : arrayList6) {
                edit2.remove(remove2);
            }
            edit2.commit();
        }
        if (jSONArray.length() != 0) {
            StatApi.a().b(statManager.s, jSONArray, new CoreAsyncCallback<StatInfoResult, CoreError>() {
                public void a(CoreError coreError) {
                }

                public void a(StatInfoResult statInfoResult) {
                    SharedPreferences.Editor edit = sharedPreferences2.edit();
                    for (String remove : arrayList3) {
                        edit.remove(remove);
                    }
                    edit.commit();
                    if (statInfoResult != null) {
                        if (((long) statInfoResult.f14722a) * 60000 < 600000) {
                            long unused = StatManager.this.t = 600000;
                        } else {
                            long unused2 = StatManager.this.t = ((long) statInfoResult.f14722a) * 60000;
                        }
                        if (statInfoResult.b > 3000) {
                            int unused3 = StatManager.this.u = 3000;
                        } else {
                            int unused4 = StatManager.this.u = statInfoResult.b;
                        }
                    }
                }
            });
        }
        if (jSONArray2.length() != 0) {
            final SharedPreferences sharedPreferences5 = sharedPreferences4;
            final ArrayList arrayList7 = arrayList;
            StatApi.a().a(statManager.s, jSONArray2, (CoreAsyncCallback<StatInfoResult, CoreError>) new CoreAsyncCallback<StatInfoResult, CoreError>() {
                public void a(CoreError coreError) {
                }

                public void a(StatInfoResult statInfoResult) {
                    SharedPreferences.Editor edit = sharedPreferences5.edit();
                    for (String remove : arrayList7) {
                        edit.remove(remove);
                    }
                    edit.commit();
                }
            });
        }
    }

    public void a(String str, boolean z) {
        if (this.d != null) {
            this.d.obtainMessage(3, str).sendToTarget();
        } else if (this.w != null) {
            this.w.a(str);
        }
    }

    public void a(String str, String str2, String str3, String str4, String str5, boolean z) {
        RecordInfo recordInfo = new RecordInfo();
        recordInfo.f14713a = str;
        recordInfo.b = str2;
        recordInfo.c = str3;
        recordInfo.d = str4;
        recordInfo.e = str5;
        recordInfo.f = z;
        if (this.d == null) {
            this.v.add(recordInfo);
        } else {
            this.d.obtainMessage(2, recordInfo).sendToTarget();
        }
    }

    public void a(StatType statType, String str, String str2, Object obj, Object obj2, boolean z) {
        a(statType.getValue(), str, str2, obj.toString(), obj2.toString(), z);
    }

    class RecordInfo {

        /* renamed from: a  reason: collision with root package name */
        String f14713a;
        String b;
        String c;
        String d;
        String e;
        boolean f;

        RecordInfo() {
        }
    }

    class WorkerHandler extends Handler {
        WorkerHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    StatManager.this.d.removeMessages(1);
                    StatManager.this.a(StatManager.this.s);
                    StatManager.this.d.sendEmptyMessageDelayed(1, StatManager.this.t);
                    return;
                case 2:
                    if (message.obj instanceof RecordInfo) {
                        StatManager.this.a((RecordInfo) message.obj);
                        return;
                    }
                    return;
                case 3:
                    if (StatManager.this.w != null) {
                        StatManager.this.w.a((String) message.obj);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
