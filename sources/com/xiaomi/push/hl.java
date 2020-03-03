package com.xiaomi.push;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.service.bf;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class hl {
    private static HashMap<String, ArrayList<hs>> a(Context context, List<hs> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        HashMap<String, ArrayList<hs>> hashMap = new HashMap<>();
        for (hs next : list) {
            a(context, next);
            ArrayList arrayList = hashMap.get(next.c());
            if (arrayList == null) {
                arrayList = new ArrayList();
                hashMap.put(next.c(), arrayList);
            }
            arrayList.add(next);
        }
        return hashMap;
    }

    private static void a(Context context, hn hnVar, HashMap<String, ArrayList<hs>> hashMap) {
        for (Map.Entry next : hashMap.entrySet()) {
            try {
                ArrayList arrayList = (ArrayList) next.getValue();
                if (arrayList != null) {
                    if (arrayList.size() != 0) {
                        b.a("TinyData is uploaded immediately item size:" + arrayList.size());
                        hnVar.a(arrayList, ((hs) arrayList.get(0)).e(), (String) next.getKey());
                    }
                }
            } catch (Exception unused) {
            }
        }
    }

    public static void a(Context context, hn hnVar, List<hs> list) {
        HashMap<String, ArrayList<hs>> a2 = a(context, list);
        if (a2 == null || a2.size() == 0) {
            b.a("TinyData TinyDataCacheUploader.uploadTinyData itemsUploading == null || itemsUploading.size() == 0  ts:" + System.currentTimeMillis());
            return;
        }
        a(context, hnVar, a2);
    }

    private static void a(Context context, hs hsVar) {
        if (hsVar.f99a) {
            hsVar.a("push_sdk_channel");
        }
        if (TextUtils.isEmpty(hsVar.d())) {
            hsVar.f(bf.a());
        }
        hsVar.b(System.currentTimeMillis());
        if (TextUtils.isEmpty(hsVar.e())) {
            hsVar.e(context.getPackageName());
        }
        if (TextUtils.isEmpty(hsVar.c())) {
            hsVar.e(hsVar.e());
        }
    }
}
