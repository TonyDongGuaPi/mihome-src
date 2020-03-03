package com.xiaomi.jr.personaldata;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.personaldata.CollectRunnable;
import com.xiaomi.jr.personaldata.ContactAttribute;
import com.xiaomi.smarthome.download.Downloads;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContactsRunnable extends CollectRunnable {
    private static final long c = 2592000000L;
    private static final long d = Long.MAX_VALUE;
    private Map<String, ContactAttribute> e = h();

    /* access modifiers changed from: package-private */
    public int b() {
        return 1;
    }

    /* access modifiers changed from: package-private */
    public String c() {
        return "contacts";
    }

    /* access modifiers changed from: package-private */
    public long d() {
        return 2592000000L;
    }

    /* access modifiers changed from: package-private */
    public long e() {
        return Long.MAX_VALUE;
    }

    static class CollectStatus {

        /* renamed from: a  reason: collision with root package name */
        long f11005a;
        boolean b;
        boolean c;

        CollectStatus() {
        }
    }

    ContactsRunnable(Context context) {
        super(context);
    }

    /* access modifiers changed from: package-private */
    public String[] a() {
        return new String[]{"android.permission.READ_CONTACTS"};
    }

    public CollectRunnable.CollectResult a(long j, long j2) throws Exception {
        if (Build.VERSION.SDK_INT < 18) {
            return null;
        }
        ContentResolver contentResolver = f().getContentResolver();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add("contact_id");
        linkedHashSet.add(Downloads.COLUMN_MIME_TYPE);
        linkedHashSet.add("contact_last_updated_timestamp");
        linkedHashSet.addAll(a(this.e.values()));
        Cursor query = contentResolver.query(ContactsContract.Data.CONTENT_URI, (String[]) linkedHashSet.toArray(new String[linkedHashSet.size()]), "contact_last_updated_timestamp>" + j + " and " + "contact_last_updated_timestamp" + "<=" + j2, (String[]) null, "contact_last_updated_timestamp asc");
        if (query == null) {
            return null;
        }
        if (query.getCount() == 0) {
            query.close();
            return null;
        }
        for (ContactAttribute a2 : this.e.values()) {
            a2.a(query);
        }
        CollectRunnable.CollectResult collectResult = new CollectRunnable.CollectResult();
        JSONArray jSONArray = new JSONArray();
        HashMap hashMap = new HashMap();
        CollectStatus collectStatus = new CollectStatus();
        long j3 = -1;
        int i = -1;
        while (query.moveToNext()) {
            int i2 = query.getInt(0);
            if (i2 != i) {
                if (i != -1) {
                    a(hashMap, collectStatus, jSONArray);
                    if (collectStatus.c) {
                        return null;
                    }
                    if (collectStatus.b) {
                        break;
                    }
                    collectResult.b = j3;
                    hashMap = new HashMap();
                }
                i = i2;
            }
            j3 = query.getLong(2);
            ContactAttribute contactAttribute = this.e.get(query.getString(1));
            if (contactAttribute != null) {
                String a3 = contactAttribute.a();
                if (!hashMap.containsKey(a3)) {
                    hashMap.put(a3, new ArrayList());
                }
                ((List) hashMap.get(a3)).add(contactAttribute.b(query));
            }
        }
        query.close();
        if (!hashMap.isEmpty()) {
            a(hashMap, collectStatus, jSONArray);
            if (collectStatus.c) {
                return null;
            }
        }
        if (!collectStatus.b) {
            collectResult.b = j2;
        }
        collectResult.f11002a = jSONArray.toString();
        MifiLog.c("TestData", "collected " + c() + " count=" + jSONArray.length() + " size=" + collectStatus.f11005a);
        return collectResult;
    }

    private void a(Map<String, List<Object>> map, CollectStatus collectStatus, JSONArray jSONArray) {
        JSONObject jSONObject = new JSONObject();
        for (String next : map.keySet()) {
            List<Object> list = map.get(next);
            try {
                if (list.size() == 1) {
                    jSONObject.put(next, list.get(0));
                } else if (list.size() > 1) {
                    JSONArray jSONArray2 = new JSONArray();
                    for (Object put : list) {
                        jSONArray2.put(put);
                    }
                    jSONObject.put(next, jSONArray2);
                }
            } catch (JSONException e2) {
                e2.printStackTrace();
            }
        }
        collectStatus.f11005a += (long) (jSONObject.toString().length() + 1);
        if (collectStatus.f11005a > 10000) {
            if (jSONArray.length() == 0) {
                collectStatus.c = true;
            }
            collectStatus.b = true;
            return;
        }
        jSONArray.put(jSONObject);
    }

    private Map<String, ContactAttribute> h() {
        ArrayList<ContactAttribute> arrayList = new ArrayList<>();
        arrayList.add(new ContactAttribute.Number());
        arrayList.add(new ContactAttribute.Name());
        arrayList.add(new ContactAttribute.Email());
        arrayList.add(new ContactAttribute.Im());
        arrayList.add(new ContactAttribute.Organization());
        arrayList.add(new ContactAttribute.Postal());
        arrayList.add(new ContactAttribute.Group(f()));
        arrayList.add(new ContactAttribute.Event());
        arrayList.add(new ContactAttribute.Relation());
        HashMap hashMap = new HashMap();
        for (ContactAttribute contactAttribute : arrayList) {
            hashMap.put(contactAttribute.b(), contactAttribute);
        }
        return hashMap;
    }

    private Set<String> a(Collection<ContactAttribute> collection) {
        TreeSet treeSet = new TreeSet();
        for (ContactAttribute c2 : collection) {
            Set<String> c3 = c2.c();
            if (c3 != null) {
                treeSet.addAll(c3);
            }
        }
        return treeSet;
    }
}
