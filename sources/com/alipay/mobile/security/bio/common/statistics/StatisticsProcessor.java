package com.alipay.mobile.security.bio.common.statistics;

import android.content.Context;
import com.alipay.mobile.security.bio.service.BioServiceManager;
import java.util.HashMap;
import java.util.Map;

public class StatisticsProcessor {

    /* renamed from: a  reason: collision with root package name */
    private static StatisticsProcessor f980a;
    private final RecordExtService b;
    private Map<String, String> c;

    public static StatisticsProcessor getInstance(Context context) {
        if (f980a == null) {
            f980a = new StatisticsProcessor(context);
        }
        return f980a;
    }

    private StatisticsProcessor(Context context) {
        if (BioServiceManager.getCurrentInstance() != null) {
            this.b = (RecordExtService) BioServiceManager.getCurrentInstance().getBioService(RecordExtService.class);
        } else {
            this.b = null;
        }
    }

    public void init(String str) {
        if (this.b != null) {
            this.b.setUniqueID(str);
        }
    }

    public void setGlobalMap(Map map) {
        if (this.c == null) {
            this.c = new HashMap();
        }
        this.c.putAll(map);
    }

    public void write(RecordExtAction recordExtAction) {
        if (this.b != null) {
            if (this.c != null) {
                this.b.write(recordExtAction, this.c);
            } else {
                this.b.write(recordExtAction);
            }
        }
    }

    public void writeWithKey(RecordExtAction recordExtAction, String str, String str2) {
        if (this.b != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(str, str2);
            if (this.c != null) {
                hashMap.putAll(this.c);
            }
            this.b.write(recordExtAction, hashMap);
        }
    }

    public void writeWithMap(RecordExtAction recordExtAction, Map map) {
        if (this.b != null) {
            if (!(this.c == null || map == null)) {
                map.putAll(this.c);
            }
            this.b.write(recordExtAction, map);
        }
    }

    public void writeWithKeys(RecordExtAction recordExtAction, String str, String str2, String str3, String str4) {
        if (this.b != null) {
            HashMap hashMap = new HashMap();
            hashMap.put(str, str2);
            hashMap.put(str3, str4);
            if (this.c != null) {
                hashMap.putAll(this.c);
            }
            this.b.write(recordExtAction, hashMap);
        }
    }

    public void release() {
        if (f980a != null) {
            f980a = null;
        }
    }
}
