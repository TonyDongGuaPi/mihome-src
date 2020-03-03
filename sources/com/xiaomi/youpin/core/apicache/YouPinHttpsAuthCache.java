package com.xiaomi.youpin.core.apicache;

import android.text.TextUtils;
import android.util.Pair;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.library.crypto.MD5Util;
import com.xiaomi.youpin.core.net.NetRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class YouPinHttpsAuthCache {

    /* renamed from: a  reason: collision with root package name */
    public static final int f23335a = 50;
    private static YouPinHttpsAuthCache b;
    private static Object c = new Object();
    /* access modifiers changed from: private */
    public Map<String, WeakReference<String>> d = new ConcurrentHashMap();

    public interface CacheCallback {
        void a(String str);
    }

    private YouPinHttpsAuthCache() {
    }

    public static YouPinHttpsAuthCache a() {
        if (b == null) {
            synchronized (c) {
                if (b == null) {
                    b = new YouPinHttpsAuthCache();
                }
            }
        }
        return b;
    }

    public void a(final NetRequest netRequest, final CacheCallback cacheCallback) {
        SHApplication.getThreadExecutor().submit(new Runnable() {
            public void run() {
                String str;
                String a2 = YouPinHttpsAuthCache.this.a(netRequest);
                if (YouPinHttpsAuthCache.this.d.containsKey(a2)) {
                    str = (String) ((WeakReference) YouPinHttpsAuthCache.this.d.get(a2)).get();
                    if (TextUtils.isEmpty(str)) {
                        YouPinHttpsAuthCache.this.d.remove(a2);
                    }
                } else {
                    str = null;
                }
                if (TextUtils.isEmpty(str)) {
                    File file = new File(YouPinHttpsAuthCache.this.c(a2));
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
                if (!TextUtils.isEmpty(str)) {
                    if (cacheCallback != null) {
                        cacheCallback.a(str);
                        return;
                    }
                } else if (cacheCallback != null) {
                    cacheCallback.a("");
                }
                YouPinHttpsAuthCache.this.b();
            }
        });
    }

    public void b() {
        Iterator<Map.Entry<String, WeakReference<String>>> it = this.d.entrySet().iterator();
        while (it.hasNext()) {
            if (TextUtils.isEmpty((CharSequence) ((WeakReference) it.next().getValue()).get())) {
                it.remove();
            }
        }
    }

    public void a(NetRequest netRequest, String str) {
        String a2 = a(netRequest);
        this.d.put(a2, new WeakReference(str));
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(a(c(a2)));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.append(str);
            outputStreamWriter.close();
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception unused) {
        }
    }

    public void c() {
        File file = new File(e());
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
                    b((String) ((Pair) arrayList.get(i)).first);
                }
            }
        }
    }

    public void d() {
        this.d.clear();
        File file = new File(e());
        ArrayList arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (listFiles != null) {
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
                b((String) ((Pair) arrayList.get(i)).first);
            }
        }
    }

    public File a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        File parentFile = file.getParentFile();
        if (!parentFile.exists()) {
            parentFile.mkdirs();
        }
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException unused) {
        }
        return file;
    }

    public boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        File file = new File(str);
        if (!file.exists() || file.isDirectory() || !file.delete()) {
            return false;
        }
        return true;
    }

    private String e() {
        return SHApplication.getAppContext().getCacheDir().getPath() + File.separator + "mshttps-auth-cache";
    }

    /* access modifiers changed from: private */
    public String c(String str) {
        return e() + File.separator + str;
    }

    /* access modifiers changed from: private */
    public String a(NetRequest netRequest) {
        return MD5Util.a(netRequest.a() + netRequest.b() + netRequest.c() + netRequest.d());
    }
}
