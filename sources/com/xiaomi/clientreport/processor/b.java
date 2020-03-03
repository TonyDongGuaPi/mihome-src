package com.xiaomi.clientreport.processor;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.clientreport.data.PerfClientReport;
import com.xiaomi.clientreport.data.a;
import com.xiaomi.push.bl;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import org.mp4parser.boxes.threegpp.ts26244.PerformerBox;

public class b implements IPerfProcessor {

    /* renamed from: a  reason: collision with root package name */
    protected Context f10086a;
    private HashMap<String, HashMap<String, a>> b;

    public b(Context context) {
        this.f10086a = context;
    }

    public static String a(a aVar) {
        return String.valueOf(aVar.e) + "#" + aVar.f;
    }

    private String c(a aVar) {
        String str = "";
        int i = aVar.e;
        String str2 = aVar.f;
        if (i > 0 && !TextUtils.isEmpty(str2)) {
            str = String.valueOf(i) + "#" + str2;
        }
        File externalFilesDir = this.f10086a.getExternalFilesDir(PerformerBox.f3974a);
        if (externalFilesDir == null) {
            com.xiaomi.channel.commonutils.logger.b.d("cannot get folder when to write perf");
            return null;
        }
        if (!externalFilesDir.exists()) {
            externalFilesDir.mkdirs();
        }
        return new File(externalFilesDir, str).getAbsolutePath();
    }

    private String d(a aVar) {
        String c = c(aVar);
        if (TextUtils.isEmpty(c)) {
            return null;
        }
        for (int i = 0; i < 20; i++) {
            String str = c + i;
            if (bl.b(this.f10086a, str)) {
                return str;
            }
        }
        return null;
    }

    public void a() {
        bl.a(this.f10086a, PerformerBox.f3974a, "perfUploading");
        File[] c = bl.c(this.f10086a, "perfUploading");
        if (c != null && c.length > 0) {
            for (File file : c) {
                if (file != null) {
                    List<String> a2 = e.a(this.f10086a, file.getAbsolutePath());
                    file.delete();
                    a(a2);
                }
            }
        }
    }

    public void a(HashMap<String, HashMap<String, a>> hashMap) {
        this.b = hashMap;
    }

    public void a(List<String> list) {
        bl.a(this.f10086a, list);
    }

    public void a(a[] aVarArr) {
        String d = d(aVarArr[0]);
        if (!TextUtils.isEmpty(d)) {
            e.a(d, aVarArr);
        }
    }

    public void b() {
        if (this.b != null) {
            if (this.b.size() > 0) {
                for (String str : this.b.keySet()) {
                    HashMap hashMap = this.b.get(str);
                    if (hashMap != null && hashMap.size() > 0) {
                        a[] aVarArr = new a[hashMap.size()];
                        hashMap.values().toArray(aVarArr);
                        a(aVarArr);
                    }
                }
            }
            this.b.clear();
        }
    }

    public void b(a aVar) {
        if ((aVar instanceof PerfClientReport) && this.b != null) {
            PerfClientReport perfClientReport = (PerfClientReport) aVar;
            String a2 = a((a) perfClientReport);
            String a3 = e.a(perfClientReport);
            HashMap hashMap = this.b.get(a2);
            if (hashMap == null) {
                hashMap = new HashMap();
            }
            PerfClientReport perfClientReport2 = (PerfClientReport) hashMap.get(a3);
            if (perfClientReport2 != null) {
                perfClientReport.b += perfClientReport2.b;
                perfClientReport.c += perfClientReport2.c;
            }
            hashMap.put(a3, perfClientReport);
            this.b.put(a2, hashMap);
        }
    }
}
