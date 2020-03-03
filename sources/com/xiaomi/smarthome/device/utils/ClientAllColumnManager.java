package com.xiaomi.smarthome.device.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.server.internal.util.LocaleUtil;
import com.xiaomi.smarthome.device.utils.DeviceSortUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.api.UserConfig;
import com.xiaomi.smarthome.framework.api.UserConfigApi;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.common.widget.nestscroll.internal.WeatherHeaderView;
import com.xiaomi.smarthome.miio.areainfo.AreaInfoManager;
import com.xiaomi.smarthome.miio.page.GroupType;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;

public class ClientAllColumnManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f15418a = "column_data_updated_action";
    public static final String b = "type";
    public static final String c = "column";
    public static final String d = "expand_state";
    private static final String e = "client_all_column_prefs";
    private static final String f = "client_all_columns";
    private static final String g = "display";
    private static final String h = "hidden";
    private static ClientAllColumnManager i;
    /* access modifiers changed from: private */
    public boolean j = false;
    /* access modifiers changed from: private */
    public boolean k = false;
    private ArrayList<DeviceSortUtil.GroupOrderInfo> l = new ArrayList<>();
    private ArrayList<DeviceSortUtil.GroupOrderInfo> m = new ArrayList<>();
    private ArrayList<DeviceSortUtil.GroupOrderInfo> n = new ArrayList<>();
    private ArrayList<DeviceSortUtil.GroupOrderInfo> o;
    private ArrayList<DeviceSortUtil.GroupOrderInfo> p = new ArrayList<>();

    private ClientAllColumnManager() {
    }

    public static ClientAllColumnManager a() {
        if (i == null) {
            i = new ClientAllColumnManager();
        }
        return i;
    }

    public synchronized boolean b() {
        return this.j;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001c, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void c() {
        /*
            r2 = this;
            monitor-enter(r2)
            boolean r0 = r2.j     // Catch:{ all -> 0x001d }
            if (r0 != 0) goto L_0x001b
            boolean r0 = r2.k     // Catch:{ all -> 0x001d }
            if (r0 == 0) goto L_0x000a
            goto L_0x001b
        L_0x000a:
            r0 = 1
            r2.k = r0     // Catch:{ all -> 0x001d }
            android.os.Handler r0 = com.xiaomi.smarthome.application.SHApplication.getGlobalWorkerHandler()     // Catch:{ all -> 0x001d }
            com.xiaomi.smarthome.device.utils.ClientAllColumnManager$1 r1 = new com.xiaomi.smarthome.device.utils.ClientAllColumnManager$1     // Catch:{ all -> 0x001d }
            r1.<init>()     // Catch:{ all -> 0x001d }
            r0.post(r1)     // Catch:{ all -> 0x001d }
            monitor-exit(r2)
            return
        L_0x001b:
            monitor-exit(r2)
            return
        L_0x001d:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.ClientAllColumnManager.c():void");
    }

    public void d() {
        this.j = false;
        this.k = false;
        this.l.clear();
        this.m.clear();
        this.n = new ArrayList<>();
        this.o = null;
        this.p = new ArrayList<>();
    }

    /* access modifiers changed from: private */
    public String k() {
        if (!CoreApi.a().q()) {
            return e;
        }
        return CoreApi.a().s() + JSMethod.NOT_SET + e;
    }

    /* access modifiers changed from: private */
    public void l() {
        a(SHApplication.getAppContext().getSharedPreferences(k(), 0).getString(f, ""));
    }

    /* access modifiers changed from: private */
    public void m() {
        UserConfigApi.a().a(SHApplication.getAppContext(), 0, new String[]{"4"}, (AsyncCallback<ArrayList<UserConfig>, Error>) new AsyncCallback<ArrayList<UserConfig>, Error>() {
            /* renamed from: a */
            public void onSuccess(ArrayList<UserConfig> arrayList) {
                UserConfig userConfig;
                boolean z = true;
                boolean unused = ClientAllColumnManager.this.j = true;
                boolean unused2 = ClientAllColumnManager.this.k = false;
                if (arrayList != null && arrayList.size() > 0 && (userConfig = arrayList.get(0)) != null && userConfig.D != null && userConfig.D.size() > 0 && TextUtils.equals(ClientAllColumnManager.this.k(), userConfig.D.get(0).f16437a)) {
                    final String str = userConfig.D.get(0).b;
                    if (ClientAllColumnManager.this.a(str)) {
                        AsyncTaskUtils.a(new AsyncTask<Void, Void, Void>() {
                            /* access modifiers changed from: protected */
                            /* renamed from: a */
                            public Void doInBackground(Void... voidArr) {
                                ClientAllColumnManager.this.c(str);
                                return null;
                            }
                        }, new Void[0]);
                    }
                    z = false;
                }
                if (z) {
                    ClientAllColumnManager.this.b("column");
                }
            }

            public void onFailure(Error error) {
                boolean unused = ClientAllColumnManager.this.k = false;
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0078, code lost:
        return false;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x004a  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x004e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean a(java.lang.String r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            boolean r0 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x0079 }
            r1 = 0
            if (r0 != 0) goto L_0x006f
            r0 = 0
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0026 }
            r2.<init>(r5)     // Catch:{ JSONException -> 0x0026 }
            java.lang.String r5 = "display"
            org.json.JSONArray r5 = r2.getJSONArray(r5)     // Catch:{ JSONException -> 0x0026 }
            java.lang.String r3 = "hidden"
            org.json.JSONArray r2 = r2.getJSONArray(r3)     // Catch:{ JSONException -> 0x0026 }
            java.util.ArrayList r5 = r4.a((org.json.JSONArray) r5)     // Catch:{ JSONException -> 0x0026 }
            java.util.ArrayList r2 = r4.a((org.json.JSONArray) r2)     // Catch:{ JSONException -> 0x0027 }
            r0 = 1
            r0 = r2
            r2 = 1
            goto L_0x0028
        L_0x0026:
            r5 = r0
        L_0x0027:
            r2 = 0
        L_0x0028:
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r3 = r4.n     // Catch:{ all -> 0x0079 }
            boolean r3 = r4.a((java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil.GroupOrderInfo>) r5, (java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil.GroupOrderInfo>) r3)     // Catch:{ all -> 0x0079 }
            if (r3 == 0) goto L_0x0048
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r3 = r4.n     // Catch:{ all -> 0x0079 }
            if (r3 == 0) goto L_0x003c
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r3 = r4.n     // Catch:{ all -> 0x0079 }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x0079 }
            if (r3 == 0) goto L_0x0077
        L_0x003c:
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r3 = r4.p     // Catch:{ all -> 0x0079 }
            if (r3 == 0) goto L_0x0048
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r3 = r4.p     // Catch:{ all -> 0x0079 }
            boolean r3 = r3.isEmpty()     // Catch:{ all -> 0x0079 }
            if (r3 == 0) goto L_0x0077
        L_0x0048:
            if (r5 == 0) goto L_0x004c
            r4.n = r5     // Catch:{ all -> 0x0079 }
        L_0x004c:
            if (r0 == 0) goto L_0x0050
            r4.p = r0     // Catch:{ all -> 0x0079 }
        L_0x0050:
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r5 = r4.l     // Catch:{ all -> 0x0079 }
            r5.clear()     // Catch:{ all -> 0x0079 }
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r5 = r4.m     // Catch:{ all -> 0x0079 }
            r5.clear()     // Catch:{ all -> 0x0079 }
            r4.n()     // Catch:{ all -> 0x0079 }
            r4.f()     // Catch:{ all -> 0x0079 }
            com.xiaomi.smarthome.miio.page.GroupType r5 = com.xiaomi.smarthome.miio.page.GroupType.DeviceList     // Catch:{ all -> 0x0079 }
            r4.b((com.xiaomi.smarthome.miio.page.GroupType) r5)     // Catch:{ all -> 0x0079 }
            r4.e()     // Catch:{ all -> 0x0079 }
            java.lang.String r5 = "column"
            r4.b((java.lang.String) r5)     // Catch:{ all -> 0x0079 }
            monitor-exit(r4)
            return r2
        L_0x006f:
            r4.n()     // Catch:{ all -> 0x0079 }
            java.lang.String r5 = "column"
            r4.b((java.lang.String) r5)     // Catch:{ all -> 0x0079 }
        L_0x0077:
            monitor-exit(r4)
            return r1
        L_0x0079:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.ClientAllColumnManager.a(java.lang.String):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0038, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized boolean a(java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil.GroupOrderInfo> r5, java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil.GroupOrderInfo> r6) {
        /*
            r4 = this;
            monitor-enter(r4)
            r0 = 0
            if (r5 == 0) goto L_0x0037
            if (r6 != 0) goto L_0x0007
            goto L_0x0037
        L_0x0007:
            int r1 = r5.size()     // Catch:{ all -> 0x0034 }
            int r2 = r6.size()     // Catch:{ all -> 0x0034 }
            if (r1 == r2) goto L_0x0013
            monitor-exit(r4)
            return r0
        L_0x0013:
            r1 = 0
        L_0x0014:
            int r2 = r5.size()     // Catch:{ all -> 0x0034 }
            if (r1 >= r2) goto L_0x0031
            java.lang.Object r2 = r5.get(r1)     // Catch:{ all -> 0x0034 }
            com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo r2 = (com.xiaomi.smarthome.device.utils.DeviceSortUtil.GroupOrderInfo) r2     // Catch:{ all -> 0x0034 }
            java.lang.Object r3 = r6.get(r1)     // Catch:{ all -> 0x0034 }
            com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo r3 = (com.xiaomi.smarthome.device.utils.DeviceSortUtil.GroupOrderInfo) r3     // Catch:{ all -> 0x0034 }
            boolean r2 = r2.a((com.xiaomi.smarthome.device.utils.DeviceSortUtil.GroupOrderInfo) r3)     // Catch:{ all -> 0x0034 }
            if (r2 != 0) goto L_0x002e
            monitor-exit(r4)
            return r0
        L_0x002e:
            int r1 = r1 + 1
            goto L_0x0014
        L_0x0031:
            monitor-exit(r4)
            r5 = 1
            return r5
        L_0x0034:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        L_0x0037:
            monitor-exit(r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.ClientAllColumnManager.a(java.util.ArrayList, java.util.ArrayList):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0077, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0094, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void n() {
        /*
            r7 = this;
            monitor-enter(r7)
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r7.n     // Catch:{ all -> 0x0095 }
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L_0x002f
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r7.n     // Catch:{ all -> 0x0095 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0095 }
            r3 = 0
        L_0x000e:
            boolean r4 = r0.hasNext()     // Catch:{ all -> 0x0095 }
            if (r4 == 0) goto L_0x002d
            java.lang.Object r4 = r0.next()     // Catch:{ all -> 0x0095 }
            com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo r4 = (com.xiaomi.smarthome.device.utils.DeviceSortUtil.GroupOrderInfo) r4     // Catch:{ all -> 0x0095 }
            com.xiaomi.smarthome.miio.page.GroupType r4 = r4.f15433a     // Catch:{ all -> 0x0095 }
            int r4 = r4.ordinal()     // Catch:{ all -> 0x0095 }
            com.xiaomi.smarthome.miio.page.GroupType r5 = com.xiaomi.smarthome.miio.page.GroupType.PhoneIR     // Catch:{ all -> 0x0095 }
            int r5 = r5.ordinal()     // Catch:{ all -> 0x0095 }
            if (r4 != r5) goto L_0x002a
            r0 = 1
            goto L_0x0031
        L_0x002a:
            int r3 = r3 + 1
            goto L_0x000e
        L_0x002d:
            r0 = 0
            goto L_0x0031
        L_0x002f:
            r0 = 0
            r3 = 0
        L_0x0031:
            if (r0 != 0) goto L_0x005d
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r4 = r7.p     // Catch:{ all -> 0x0095 }
            if (r4 == 0) goto L_0x005d
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r3 = r7.p     // Catch:{ all -> 0x0095 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x0095 }
            r4 = 0
        L_0x003e:
            boolean r5 = r3.hasNext()     // Catch:{ all -> 0x0095 }
            if (r5 == 0) goto L_0x005e
            java.lang.Object r5 = r3.next()     // Catch:{ all -> 0x0095 }
            com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo r5 = (com.xiaomi.smarthome.device.utils.DeviceSortUtil.GroupOrderInfo) r5     // Catch:{ all -> 0x0095 }
            com.xiaomi.smarthome.miio.page.GroupType r5 = r5.f15433a     // Catch:{ all -> 0x0095 }
            int r5 = r5.ordinal()     // Catch:{ all -> 0x0095 }
            com.xiaomi.smarthome.miio.page.GroupType r6 = com.xiaomi.smarthome.miio.page.GroupType.PhoneIR     // Catch:{ all -> 0x0095 }
            int r6 = r6.ordinal()     // Catch:{ all -> 0x0095 }
            if (r5 != r6) goto L_0x005a
            r1 = 1
            goto L_0x005e
        L_0x005a:
            int r4 = r4 + 1
            goto L_0x003e
        L_0x005d:
            r4 = r3
        L_0x005e:
            boolean r3 = com.xiaomi.smarthome.device.utils.IRDeviceUtil.c()     // Catch:{ all -> 0x0095 }
            if (r3 == 0) goto L_0x0078
            if (r0 != 0) goto L_0x0076
            if (r1 == 0) goto L_0x0069
            goto L_0x0076
        L_0x0069:
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r7.n     // Catch:{ all -> 0x0095 }
            com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo r1 = new com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo     // Catch:{ all -> 0x0095 }
            com.xiaomi.smarthome.miio.page.GroupType r3 = com.xiaomi.smarthome.miio.page.GroupType.PhoneIR     // Catch:{ all -> 0x0095 }
            r1.<init>(r3, r2)     // Catch:{ all -> 0x0095 }
            r0.add(r1)     // Catch:{ all -> 0x0095 }
            goto L_0x0093
        L_0x0076:
            monitor-exit(r7)
            return
        L_0x0078:
            if (r0 == 0) goto L_0x0086
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r7.l     // Catch:{ all -> 0x0095 }
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r1 = r7.n     // Catch:{ all -> 0x0095 }
            java.lang.Object r1 = r1.remove(r4)     // Catch:{ all -> 0x0095 }
            r0.add(r1)     // Catch:{ all -> 0x0095 }
            goto L_0x0093
        L_0x0086:
            if (r1 == 0) goto L_0x0093
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r7.m     // Catch:{ all -> 0x0095 }
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r1 = r7.p     // Catch:{ all -> 0x0095 }
            java.lang.Object r1 = r1.remove(r4)     // Catch:{ all -> 0x0095 }
            r0.add(r1)     // Catch:{ all -> 0x0095 }
        L_0x0093:
            monitor-exit(r7)
            return
        L_0x0095:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.ClientAllColumnManager.n():void");
    }

    public synchronized void e() {
        boolean z;
        if (this.n != null) {
            Iterator<DeviceSortUtil.GroupOrderInfo> it = this.n.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (it.next().f15433a.equals(GroupType.LiteScene)) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        z = false;
        if (!z && this.p != null) {
            Iterator<DeviceSortUtil.GroupOrderInfo> it2 = this.p.iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (it2.next().f15433a.ordinal() == GroupType.LiteScene.ordinal()) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        if (!z) {
            if (this.n.size() > 0) {
                a(1, new DeviceSortUtil.GroupOrderInfo(GroupType.LiteScene, true));
            } else {
                a(0, new DeviceSortUtil.GroupOrderInfo(GroupType.LiteScene, true));
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:50:0x00c7, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x00e7, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00c8 A[SYNTHETIC, Splitter:B:51:0x00c8] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void f() {
        /*
            r7 = this;
            monitor-enter(r7)
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r7.n     // Catch:{ all -> 0x00e8 }
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x002f
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r7.n     // Catch:{ all -> 0x00e8 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x00e8 }
            r3 = 0
        L_0x000e:
            boolean r4 = r0.hasNext()     // Catch:{ all -> 0x00e8 }
            if (r4 == 0) goto L_0x002d
            java.lang.Object r4 = r0.next()     // Catch:{ all -> 0x00e8 }
            com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo r4 = (com.xiaomi.smarthome.device.utils.DeviceSortUtil.GroupOrderInfo) r4     // Catch:{ all -> 0x00e8 }
            com.xiaomi.smarthome.miio.page.GroupType r4 = r4.f15433a     // Catch:{ all -> 0x00e8 }
            int r4 = r4.ordinal()     // Catch:{ all -> 0x00e8 }
            com.xiaomi.smarthome.miio.page.GroupType r5 = com.xiaomi.smarthome.miio.page.GroupType.LocationInfo     // Catch:{ all -> 0x00e8 }
            int r5 = r5.ordinal()     // Catch:{ all -> 0x00e8 }
            if (r4 != r5) goto L_0x002a
            r0 = 1
            goto L_0x0031
        L_0x002a:
            int r3 = r3 + 1
            goto L_0x000e
        L_0x002d:
            r0 = 0
            goto L_0x0031
        L_0x002f:
            r0 = 0
            r3 = 0
        L_0x0031:
            if (r0 != 0) goto L_0x005d
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r4 = r7.p     // Catch:{ all -> 0x00e8 }
            if (r4 == 0) goto L_0x005d
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r3 = r7.p     // Catch:{ all -> 0x00e8 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ all -> 0x00e8 }
            r4 = 0
        L_0x003e:
            boolean r5 = r3.hasNext()     // Catch:{ all -> 0x00e8 }
            if (r5 == 0) goto L_0x005e
            java.lang.Object r5 = r3.next()     // Catch:{ all -> 0x00e8 }
            com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo r5 = (com.xiaomi.smarthome.device.utils.DeviceSortUtil.GroupOrderInfo) r5     // Catch:{ all -> 0x00e8 }
            com.xiaomi.smarthome.miio.page.GroupType r5 = r5.f15433a     // Catch:{ all -> 0x00e8 }
            int r5 = r5.ordinal()     // Catch:{ all -> 0x00e8 }
            com.xiaomi.smarthome.miio.page.GroupType r6 = com.xiaomi.smarthome.miio.page.GroupType.LocationInfo     // Catch:{ all -> 0x00e8 }
            int r6 = r6.ordinal()     // Catch:{ all -> 0x00e8 }
            if (r5 != r6) goto L_0x005a
            r3 = 1
            goto L_0x005f
        L_0x005a:
            int r4 = r4 + 1
            goto L_0x003e
        L_0x005d:
            r4 = r3
        L_0x005e:
            r3 = 0
        L_0x005f:
            boolean r5 = com.xiaomi.smarthome.core.server.internal.util.LocaleUtil.c()     // Catch:{ all -> 0x00e8 }
            if (r5 == 0) goto L_0x00c8
            if (r0 != 0) goto L_0x00c6
            if (r3 == 0) goto L_0x006a
            goto L_0x00c6
        L_0x006a:
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r7.l     // Catch:{ all -> 0x00e8 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x00e8 }
        L_0x0070:
            boolean r2 = r0.hasNext()     // Catch:{ all -> 0x00e8 }
            if (r2 == 0) goto L_0x0096
            java.lang.Object r2 = r0.next()     // Catch:{ all -> 0x00e8 }
            com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo r2 = (com.xiaomi.smarthome.device.utils.DeviceSortUtil.GroupOrderInfo) r2     // Catch:{ all -> 0x00e8 }
            com.xiaomi.smarthome.miio.page.GroupType r3 = r2.f15433a     // Catch:{ all -> 0x00e8 }
            int r3 = r3.ordinal()     // Catch:{ all -> 0x00e8 }
            com.xiaomi.smarthome.miio.page.GroupType r4 = com.xiaomi.smarthome.miio.page.GroupType.LocationInfo     // Catch:{ all -> 0x00e8 }
            int r4 = r4.ordinal()     // Catch:{ all -> 0x00e8 }
            if (r3 != r4) goto L_0x0070
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r7.n     // Catch:{ all -> 0x00e8 }
            r0.add(r2)     // Catch:{ all -> 0x00e8 }
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r7.l     // Catch:{ all -> 0x00e8 }
            r0.remove(r2)     // Catch:{ all -> 0x00e8 }
            monitor-exit(r7)
            return
        L_0x0096:
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r7.m     // Catch:{ all -> 0x00e8 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x00e8 }
        L_0x009c:
            boolean r2 = r0.hasNext()     // Catch:{ all -> 0x00e8 }
            if (r2 == 0) goto L_0x00c2
            java.lang.Object r2 = r0.next()     // Catch:{ all -> 0x00e8 }
            com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo r2 = (com.xiaomi.smarthome.device.utils.DeviceSortUtil.GroupOrderInfo) r2     // Catch:{ all -> 0x00e8 }
            com.xiaomi.smarthome.miio.page.GroupType r3 = r2.f15433a     // Catch:{ all -> 0x00e8 }
            int r3 = r3.ordinal()     // Catch:{ all -> 0x00e8 }
            com.xiaomi.smarthome.miio.page.GroupType r4 = com.xiaomi.smarthome.miio.page.GroupType.LocationInfo     // Catch:{ all -> 0x00e8 }
            int r4 = r4.ordinal()     // Catch:{ all -> 0x00e8 }
            if (r3 != r4) goto L_0x009c
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r7.p     // Catch:{ all -> 0x00e8 }
            r0.add(r2)     // Catch:{ all -> 0x00e8 }
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r7.m     // Catch:{ all -> 0x00e8 }
            r0.remove(r2)     // Catch:{ all -> 0x00e8 }
            monitor-exit(r7)
            return
        L_0x00c2:
            com.xiaomi.smarthome.library.common.widget.nestscroll.internal.WeatherHeaderView.setIsSupportWeather(r1)     // Catch:{ all -> 0x00e8 }
            goto L_0x00e6
        L_0x00c6:
            monitor-exit(r7)
            return
        L_0x00c8:
            com.xiaomi.smarthome.library.common.widget.nestscroll.internal.WeatherHeaderView.setIsSupportWeather(r2)     // Catch:{ all -> 0x00e8 }
            if (r0 == 0) goto L_0x00d9
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r7.l     // Catch:{ all -> 0x00e8 }
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r1 = r7.n     // Catch:{ all -> 0x00e8 }
            java.lang.Object r1 = r1.remove(r4)     // Catch:{ all -> 0x00e8 }
            r0.add(r1)     // Catch:{ all -> 0x00e8 }
            goto L_0x00e6
        L_0x00d9:
            if (r3 == 0) goto L_0x00e6
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r7.m     // Catch:{ all -> 0x00e8 }
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r1 = r7.p     // Catch:{ all -> 0x00e8 }
            java.lang.Object r1 = r1.remove(r4)     // Catch:{ all -> 0x00e8 }
            r0.add(r1)     // Catch:{ all -> 0x00e8 }
        L_0x00e6:
            monitor-exit(r7)
            return
        L_0x00e8:
            r0 = move-exception
            monitor-exit(r7)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.ClientAllColumnManager.f():void");
    }

    private void b(GroupType groupType) {
        if (!a(groupType.ordinal(), this.n) && !a(groupType.ordinal(), this.p)) {
            this.n.add(new DeviceSortUtil.GroupOrderInfo(groupType, true));
        }
    }

    private boolean a(int i2, ArrayList<DeviceSortUtil.GroupOrderInfo> arrayList) {
        if (arrayList == null) {
            return false;
        }
        Iterator<DeviceSortUtil.GroupOrderInfo> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().f15433a.ordinal() == i2) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        Intent intent = new Intent(f15418a);
        intent.putExtra("type", str);
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(intent);
    }

    public synchronized void g() {
        final String o2 = o();
        if (!TextUtils.isEmpty(o2)) {
            SHApplication.getGlobalWorkerHandler().post(new Runnable() {
                public void run() {
                    ClientAllColumnManager.this.c(o2);
                    ClientAllColumnManager.this.d(o2);
                }
            });
        }
    }

    public synchronized void a(DeviceSortUtil.GroupOrderInfo groupOrderInfo) {
        a(this.n.size(), groupOrderInfo);
    }

    public synchronized void a(int i2, DeviceSortUtil.GroupOrderInfo groupOrderInfo) {
        Iterator<DeviceSortUtil.GroupOrderInfo> it = this.n.iterator();
        while (it.hasNext()) {
            if (it.next().f15433a.ordinal() == groupOrderInfo.f15433a.ordinal()) {
                return;
            }
        }
        this.n.add(i2, groupOrderInfo);
        int i3 = 0;
        Iterator<DeviceSortUtil.GroupOrderInfo> it2 = this.p.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            } else if (it2.next().f15433a.ordinal() == groupOrderInfo.f15433a.ordinal()) {
                this.p.remove(i3);
                break;
            } else {
                i3++;
            }
        }
        b("column");
        g();
    }

    public synchronized void b(DeviceSortUtil.GroupOrderInfo groupOrderInfo) {
        int i2 = 0;
        Iterator<DeviceSortUtil.GroupOrderInfo> it = this.n.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            } else if (it.next().f15433a.ordinal() == groupOrderInfo.f15433a.ordinal()) {
                this.n.remove(i2);
                break;
            } else {
                i2++;
            }
        }
        this.p.add(groupOrderInfo);
        b("column");
        g();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0025, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(int r2, boolean r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r1.n     // Catch:{ all -> 0x0026 }
            if (r0 == 0) goto L_0x0024
            if (r2 < 0) goto L_0x0024
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r1.n     // Catch:{ all -> 0x0026 }
            int r0 = r0.size()     // Catch:{ all -> 0x0026 }
            if (r2 < r0) goto L_0x0010
            goto L_0x0024
        L_0x0010:
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r1.n     // Catch:{ all -> 0x0026 }
            java.lang.Object r2 = r0.get(r2)     // Catch:{ all -> 0x0026 }
            com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo r2 = (com.xiaomi.smarthome.device.utils.DeviceSortUtil.GroupOrderInfo) r2     // Catch:{ all -> 0x0026 }
            r2.b = r3     // Catch:{ all -> 0x0026 }
            java.lang.String r2 = "expand_state"
            r1.b((java.lang.String) r2)     // Catch:{ all -> 0x0026 }
            r1.g()     // Catch:{ all -> 0x0026 }
            monitor-exit(r1)
            return
        L_0x0024:
            monitor-exit(r1)
            return
        L_0x0026:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.ClientAllColumnManager.a(int, boolean):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0033, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(int r2, int r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            if (r2 == r3) goto L_0x0032
            if (r2 < 0) goto L_0x0032
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r1.n     // Catch:{ all -> 0x002f }
            int r0 = r0.size()     // Catch:{ all -> 0x002f }
            if (r2 >= r0) goto L_0x0032
            if (r3 < 0) goto L_0x0032
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r1.n     // Catch:{ all -> 0x002f }
            int r0 = r0.size()     // Catch:{ all -> 0x002f }
            if (r3 < r0) goto L_0x0018
            goto L_0x0032
        L_0x0018:
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r1.n     // Catch:{ all -> 0x002f }
            java.lang.Object r2 = r0.remove(r2)     // Catch:{ all -> 0x002f }
            com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo r2 = (com.xiaomi.smarthome.device.utils.DeviceSortUtil.GroupOrderInfo) r2     // Catch:{ all -> 0x002f }
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r1.n     // Catch:{ all -> 0x002f }
            r0.add(r3, r2)     // Catch:{ all -> 0x002f }
            java.lang.String r2 = "column"
            r1.b((java.lang.String) r2)     // Catch:{ all -> 0x002f }
            r1.g()     // Catch:{ all -> 0x002f }
            monitor-exit(r1)
            return
        L_0x002f:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        L_0x0032:
            monitor-exit(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.ClientAllColumnManager.a(int, int):void");
    }

    /* access modifiers changed from: private */
    public void c(String str) {
        SharedPreferences.Editor edit = SHApplication.getAppContext().getSharedPreferences(k(), 0).edit();
        edit.remove(f);
        edit.putString(f, str);
        edit.apply();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0083, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized java.lang.String o() {
        /*
            r6 = this;
            monitor-enter(r6)
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r6.n     // Catch:{ all -> 0x0084 }
            r1 = 0
            if (r0 == 0) goto L_0x000e
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r6.n     // Catch:{ all -> 0x0084 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0084 }
            if (r0 == 0) goto L_0x001b
        L_0x000e:
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r6.p     // Catch:{ all -> 0x0084 }
            if (r0 == 0) goto L_0x0082
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r6.p     // Catch:{ all -> 0x0084 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0084 }
            if (r0 == 0) goto L_0x001b
            goto L_0x0082
        L_0x001b:
            org.json.JSONObject r0 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0080 }
            r0.<init>()     // Catch:{ JSONException -> 0x0080 }
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r2 = r6.n     // Catch:{ JSONException -> 0x0080 }
            org.json.JSONArray r2 = r6.a((java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil.GroupOrderInfo>) r2)     // Catch:{ JSONException -> 0x0080 }
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r3 = r6.p     // Catch:{ JSONException -> 0x0080 }
            org.json.JSONArray r3 = r6.a((java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil.GroupOrderInfo>) r3)     // Catch:{ JSONException -> 0x0080 }
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r4 = r6.l     // Catch:{ JSONException -> 0x0080 }
            boolean r4 = r4.isEmpty()     // Catch:{ JSONException -> 0x0080 }
            if (r4 != 0) goto L_0x004e
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r4 = r6.l     // Catch:{ JSONException -> 0x0080 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ JSONException -> 0x0080 }
        L_0x003a:
            boolean r5 = r4.hasNext()     // Catch:{ JSONException -> 0x0080 }
            if (r5 == 0) goto L_0x004e
            java.lang.Object r5 = r4.next()     // Catch:{ JSONException -> 0x0080 }
            com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo r5 = (com.xiaomi.smarthome.device.utils.DeviceSortUtil.GroupOrderInfo) r5     // Catch:{ JSONException -> 0x0080 }
            org.json.JSONObject r5 = r5.a()     // Catch:{ JSONException -> 0x0080 }
            r2.put(r5)     // Catch:{ JSONException -> 0x0080 }
            goto L_0x003a
        L_0x004e:
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r4 = r6.m     // Catch:{ JSONException -> 0x0080 }
            boolean r4 = r4.isEmpty()     // Catch:{ JSONException -> 0x0080 }
            if (r4 != 0) goto L_0x0070
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r4 = r6.m     // Catch:{ JSONException -> 0x0080 }
            java.util.Iterator r4 = r4.iterator()     // Catch:{ JSONException -> 0x0080 }
        L_0x005c:
            boolean r5 = r4.hasNext()     // Catch:{ JSONException -> 0x0080 }
            if (r5 == 0) goto L_0x0070
            java.lang.Object r5 = r4.next()     // Catch:{ JSONException -> 0x0080 }
            com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo r5 = (com.xiaomi.smarthome.device.utils.DeviceSortUtil.GroupOrderInfo) r5     // Catch:{ JSONException -> 0x0080 }
            org.json.JSONObject r5 = r5.a()     // Catch:{ JSONException -> 0x0080 }
            r3.put(r5)     // Catch:{ JSONException -> 0x0080 }
            goto L_0x005c
        L_0x0070:
            java.lang.String r4 = "display"
            r0.put(r4, r2)     // Catch:{ JSONException -> 0x0080 }
            java.lang.String r2 = "hidden"
            r0.put(r2, r3)     // Catch:{ JSONException -> 0x0080 }
            java.lang.String r0 = r0.toString()     // Catch:{ JSONException -> 0x0080 }
            monitor-exit(r6)
            return r0
        L_0x0080:
            monitor-exit(r6)
            return r1
        L_0x0082:
            monitor-exit(r6)
            return r1
        L_0x0084:
            r0 = move-exception
            monitor-exit(r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.ClientAllColumnManager.o():java.lang.String");
    }

    private JSONArray a(ArrayList<DeviceSortUtil.GroupOrderInfo> arrayList) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        if (arrayList != null) {
            Iterator<DeviceSortUtil.GroupOrderInfo> it = arrayList.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next().a());
            }
        }
        return jSONArray;
    }

    private ArrayList<DeviceSortUtil.GroupOrderInfo> a(JSONArray jSONArray) throws JSONException {
        int length;
        ArrayList<DeviceSortUtil.GroupOrderInfo> arrayList = new ArrayList<>();
        if (jSONArray == null || (length = jSONArray.length()) <= 0) {
            return arrayList;
        }
        for (int i2 = 0; i2 < length; i2++) {
            DeviceSortUtil.GroupOrderInfo a2 = DeviceSortUtil.GroupOrderInfo.a(jSONArray.getJSONObject(i2));
            if (a2 != null) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void d(String str) {
        UserConfig userConfig = new UserConfig();
        userConfig.B = 0;
        userConfig.C = "4";
        userConfig.D = new ArrayList<>();
        userConfig.D.add(new UserConfig.UserConfigAttr(k(), str));
        UserConfigApi.a().a(SHApplication.getAppContext(), userConfig, (AsyncCallback<Void, Error>) null);
    }

    private boolean p() {
        return ServerCompact.j(SHApplication.getAppContext());
    }

    public synchronized ArrayList<DeviceSortUtil.GroupOrderInfo> h() {
        if (CoreApi.a().D()) {
            if (this.o == null) {
                this.o = new ArrayList<>();
            }
            if (this.o.isEmpty()) {
                this.o.add(new DeviceSortUtil.GroupOrderInfo(GroupType.DeviceList, true));
            }
            if (!p()) {
                WeatherHeaderView.setIsSupportWeather(false);
                if (this.o.size() > 1) {
                    this.o.remove(0);
                }
            } else if (this.o.size() == 1) {
                WeatherHeaderView.setIsSupportWeather(true);
            }
            return this.o;
        }
        if (this.j) {
            if (this.n == null || this.n.isEmpty()) {
                if (this.p != null) {
                    if (this.p.isEmpty()) {
                    }
                }
                if (this.n == null) {
                    this.n = new ArrayList<>();
                }
                WeatherHeaderView.setIsSupportWeather(false);
                if (LocaleUtil.c()) {
                    WeatherHeaderView.setIsSupportWeather(true);
                    AreaInfoManager.a().b();
                }
                this.n.add(new DeviceSortUtil.GroupOrderInfo(GroupType.LiteScene, true));
                if (IRDeviceUtil.c()) {
                    this.n.add(new DeviceSortUtil.GroupOrderInfo(GroupType.PhoneIR, true));
                }
                this.n.add(new DeviceSortUtil.GroupOrderInfo(GroupType.DeviceList, true));
            }
            this.l.clear();
            this.m.clear();
            n();
            f();
            b(GroupType.DeviceList);
            e();
        }
        return this.n;
    }

    public boolean i() {
        if (CoreApi.a().D() && !p()) {
            return false;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0047, code lost:
        return false;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized boolean a(com.xiaomi.smarthome.miio.page.GroupType r6) {
        /*
            r5 = this;
            monitor-enter(r5)
            int r0 = r6.ordinal()     // Catch:{ all -> 0x0048 }
            com.xiaomi.smarthome.miio.page.GroupType r1 = com.xiaomi.smarthome.miio.page.GroupType.LocationInfo     // Catch:{ all -> 0x0048 }
            int r1 = r1.ordinal()     // Catch:{ all -> 0x0048 }
            r2 = 1
            if (r0 != r1) goto L_0x0016
            boolean r0 = r5.p()     // Catch:{ all -> 0x0048 }
            if (r0 == 0) goto L_0x0016
            monitor-exit(r5)
            return r2
        L_0x0016:
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r5.n     // Catch:{ all -> 0x0048 }
            r1 = 0
            if (r0 == 0) goto L_0x0046
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r5.n     // Catch:{ all -> 0x0048 }
            boolean r0 = r0.isEmpty()     // Catch:{ all -> 0x0048 }
            if (r0 == 0) goto L_0x0024
            goto L_0x0046
        L_0x0024:
            java.util.ArrayList<com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo> r0 = r5.n     // Catch:{ all -> 0x0048 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ all -> 0x0048 }
        L_0x002a:
            boolean r3 = r0.hasNext()     // Catch:{ all -> 0x0048 }
            if (r3 == 0) goto L_0x0044
            java.lang.Object r3 = r0.next()     // Catch:{ all -> 0x0048 }
            com.xiaomi.smarthome.device.utils.DeviceSortUtil$GroupOrderInfo r3 = (com.xiaomi.smarthome.device.utils.DeviceSortUtil.GroupOrderInfo) r3     // Catch:{ all -> 0x0048 }
            com.xiaomi.smarthome.miio.page.GroupType r3 = r3.f15433a     // Catch:{ all -> 0x0048 }
            int r3 = r3.ordinal()     // Catch:{ all -> 0x0048 }
            int r4 = r6.ordinal()     // Catch:{ all -> 0x0048 }
            if (r3 != r4) goto L_0x002a
            monitor-exit(r5)
            return r2
        L_0x0044:
            monitor-exit(r5)
            return r1
        L_0x0046:
            monitor-exit(r5)
            return r1
        L_0x0048:
            r6 = move-exception
            monitor-exit(r5)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.device.utils.ClientAllColumnManager.a(com.xiaomi.smarthome.miio.page.GroupType):boolean");
    }

    public synchronized ArrayList<DeviceSortUtil.GroupOrderInfo> j() {
        return this.p;
    }

    public String a(Context context, int i2) {
        int i3;
        if (i2 == GroupType.LocationInfo.ordinal()) {
            i3 = R.string.group_type_locationinfo;
        } else if (i2 == GroupType.PhoneIR.ordinal()) {
            i3 = R.string.group_type_phoneir;
        } else if (i2 == GroupType.DeviceList.ordinal()) {
            i3 = R.string.group_type_devicelist;
        } else if (i2 == GroupType.ExpCenter.ordinal()) {
            i3 = R.string.group_type_expcenter;
        } else if (i2 == GroupType.WebInfo.ordinal()) {
            i3 = R.string.group_type_webinfo;
        } else {
            i3 = i2 == GroupType.LiteScene.ordinal() ? R.string.main_page_my_scene : 0;
        }
        if (i3 != 0) {
            return context.getString(i3);
        }
        return null;
    }
}
