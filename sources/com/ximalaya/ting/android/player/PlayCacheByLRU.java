package com.ximalaya.ting.android.player;

import android.text.TextUtils;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;

public class PlayCacheByLRU {

    /* renamed from: a  reason: collision with root package name */
    public static int f2277a = 5;
    private static PlayCacheByLRU c;
    /* access modifiers changed from: private */
    public volatile LinkedHashMap<String, String> b;

    private PlayCacheByLRU() {
    }

    public static synchronized PlayCacheByLRU a() {
        PlayCacheByLRU playCacheByLRU;
        synchronized (PlayCacheByLRU.class) {
            if (c == null) {
                c = new PlayCacheByLRU();
            }
            playCacheByLRU = c;
        }
        return playCacheByLRU;
    }

    public static void b() {
        if (c != null) {
            c.d();
            c = null;
        }
    }

    public synchronized void c() {
        if (this.b == null) {
            this.b = new LinkedHashMap<String, String>(f2277a, 0.75f, true) {
                /* access modifiers changed from: protected */
                public boolean removeEldestEntry(Map.Entry<String, String> entry) {
                    if (size() <= PlayCacheByLRU.f2277a) {
                        return false;
                    }
                    PlayerUtil.b(entry.getKey());
                    return true;
                }
            };
            try {
                Iterator<String> keys = new JSONObject(PlayerUtil.e(XMediaPlayerConstants.q)).keys();
                if (keys != null) {
                    while (keys.hasNext()) {
                        this.b.put(keys.next(), "");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return;
    }

    public synchronized void a(String str) {
        if (str != null) {
            if (str.startsWith("http")) {
                this.b.put(MD5.b(str), "");
                try {
                    JSONObject jSONObject = new JSONObject();
                    for (Map.Entry next : this.b.entrySet()) {
                        jSONObject.put((String) next.getKey(), next.getValue());
                    }
                    PlayerUtil.a(jSONObject.toString(), XMediaPlayerConstants.q);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return;
    }

    public synchronized void b(String str) {
        if (this.b == null) {
            c();
        }
        this.b.clear();
        a(str);
    }

    public synchronized void d() {
        File[] listFiles;
        try {
            File file = new File(XMediaPlayerConstants.o);
            if (file.exists() && (listFiles = file.listFiles(new FilenameFilter() {
                public boolean accept(File file, String str) {
                    int lastIndexOf;
                    if (XMediaPlayerConstants.p.equals(str)) {
                        return false;
                    }
                    if (PlayCacheByLRU.this.b == null || TextUtils.isEmpty(str) || (lastIndexOf = str.lastIndexOf(46)) == -1) {
                        return true;
                    }
                    return !PlayCacheByLRU.this.b.containsKey(str.substring(0, lastIndexOf));
                }
            })) != null) {
                for (File file2 : listFiles) {
                    if (file2 != null) {
                        file2.delete();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
}
