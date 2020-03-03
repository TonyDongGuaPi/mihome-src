package com.xiaomi.smarthome.core.server.internal.apicache;

import android.text.TextUtils;
import android.util.Pair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.internal.plugin.util.FileUtils;
import com.xiaomi.smarthome.library.crypto.MD5Util;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SmartHomeRc4Cache {

    /* renamed from: a  reason: collision with root package name */
    public static final int f14093a = 50;
    private static SmartHomeRc4Cache b;
    private static Object c = new Object();
    private ExecutorService d = Executors.newCachedThreadPool();
    /* access modifiers changed from: private */
    public Map<String, WeakReference<String>> e = new ConcurrentHashMap();

    public interface CacheCallback {
        void a(String str);
    }

    private SmartHomeRc4Cache() {
    }

    public static SmartHomeRc4Cache a() {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new SmartHomeRc4Cache();
                }
            }
        }
        return b;
    }

    public void a(final NetRequest netRequest, final CacheCallback cacheCallback) {
        this.d.submit(new Runnable() {
            public void run() {
                String str;
                String a2 = SmartHomeRc4Cache.this.a(netRequest);
                if (SmartHomeRc4Cache.this.e.containsKey(a2)) {
                    str = (String) ((WeakReference) SmartHomeRc4Cache.this.e.get(a2)).get();
                    if (TextUtils.isEmpty(str)) {
                        SmartHomeRc4Cache.this.e.remove(a2);
                    }
                } else {
                    str = null;
                }
                if (TextUtils.isEmpty(str)) {
                    File file = new File(SmartHomeRc4Cache.this.a(a2));
                    StringBuffer stringBuffer = new StringBuffer("");
                    try {
                        InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file));
                        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                        for (String readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                            stringBuffer.append(readLine);
                        }
                        inputStreamReader.close();
                    } catch (Exception unused) {
                    }
                    str = stringBuffer.toString();
                }
                if (TextUtils.isEmpty(str) || cacheCallback == null) {
                    SmartHomeRc4Cache.this.b();
                } else {
                    cacheCallback.a(str);
                }
            }
        });
    }

    public void b() {
        Iterator<Map.Entry<String, WeakReference<String>>> it = this.e.entrySet().iterator();
        while (it.hasNext()) {
            if (TextUtils.isEmpty((CharSequence) ((WeakReference) it.next().getValue()).get())) {
                it.remove();
            }
        }
    }

    public void a(NetRequest netRequest, String str) {
        String a2 = a(netRequest);
        this.e.put(a2, new WeakReference(str));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(FileUtils.i(a(a2)));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(str);
            outputStreamWriter.close();
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception unused) {
        }
    }

    public void c() {
        File file = new File(d());
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            if (listFiles.length > 50) {
                for (File file2 : listFiles) {
                    arrayList.add(new Pair(file2.getPath(), Long.valueOf(file2.lastModified())));
                }
                Collections.sort(arrayList, new Comparator<Pair<String, Long>>() {
                    /* renamed from: a */
                    public int compare(Pair<String, Long> pair, Pair<String, Long> pair2) {
                        if (((Long) pair.second).longValue() > ((Long) pair2.second).longValue()) {
                            return -1;
                        }
                        return ((Long) pair.second).longValue() < ((Long) pair2.second).longValue() ? 1 : 0;
                    }
                });
                int size = arrayList.size();
                for (int i = 50; i < size; i++) {
                    FileUtils.d((String) ((Pair) arrayList.get(i)).first);
                }
            }
        }
    }

    private String d() {
        return CoreService.getAppContext().getCacheDir().getPath() + File.separator + "smrc4-cache";
    }

    /* access modifiers changed from: private */
    public String a(String str) {
        return d() + File.separator + str;
    }

    /* access modifiers changed from: private */
    public String a(NetRequest netRequest) {
        return MD5Util.a(netRequest.a() + netRequest.b() + netRequest.d() + netRequest.e());
    }
}
