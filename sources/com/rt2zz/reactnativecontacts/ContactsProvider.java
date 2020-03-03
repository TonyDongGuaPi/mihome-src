package com.rt2zz.reactnativecontacts;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.mi.global.bbs.http.ParamKey;
import com.taobao.weex.common.Constants;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.smarthome.download.Downloads;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.cli.HelpFormatter;

public class ContactsProvider {

    /* renamed from: a  reason: collision with root package name */
    public static final int f8719a = -1;
    /* access modifiers changed from: private */
    public static final List<String> b = new ArrayList<String>() {
        {
            add("_id");
            add("contact_id");
            add("raw_contact_id");
            add("lookup");
            add(Downloads.COLUMN_MIME_TYPE);
            add("display_name");
            add("photo_uri");
            add("data1");
            add("data2");
            add("data5");
            add("data3");
            add("data4");
            add("data6");
            add("data1");
            add("data2");
            add("data3");
            add("data1");
            add("data1");
            add("data2");
            add("data3");
            add("data1");
            add("data4");
            add("data5");
            add("data1");
            add("data2");
            add("data3");
            add("data4");
            add("data5");
            add("data6");
            add("data7");
            add("data8");
            add("data9");
            add("data10");
            add("data1");
            add("data2");
        }
    };
    private static final List<String> c = new ArrayList<String>() {
        {
            addAll(ContactsProvider.b);
        }
    };
    private static final List<String> d = new ArrayList<String>() {
        {
            add("photo_uri");
        }
    };
    private final ContentResolver e;

    public ContactsProvider(ContentResolver contentResolver) {
        this.e = contentResolver;
    }

    public WritableArray a(String str) {
        Cursor query = this.e.query(ContactsContract.Data.CONTENT_URI, (String[]) c.toArray(new String[c.size()]), "display_name LIKE ?", new String[]{Operators.MOD + str + Operators.MOD}, (String) null);
        try {
            Map<String, Contact> a2 = a(query);
            WritableArray createArray = Arguments.createArray();
            for (Contact a3 : a2.values()) {
                createArray.pushMap(a3.a());
            }
            return createArray;
        } finally {
            if (query != null) {
                query.close();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0020, code lost:
        r0 = r8.getColumnIndex("contact_id");
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.facebook.react.bridge.WritableMap b(java.lang.String r8) {
        /*
            r7 = this;
            r0 = 1
            java.lang.String[] r3 = new java.lang.String[r0]
            java.lang.String r1 = "contact_id"
            r2 = 0
            r3[r2] = r1
            java.lang.String r4 = "_id= ?"
            java.lang.String[] r5 = new java.lang.String[r0]
            r5[r2] = r8
            android.content.ContentResolver r1 = r7.e
            android.net.Uri r2 = android.provider.ContactsContract.RawContacts.CONTENT_URI
            r6 = 0
            android.database.Cursor r8 = r1.query(r2, r3, r4, r5, r6)
            r8.getCount()
            boolean r0 = r8.moveToNext()
            if (r0 == 0) goto L_0x002f
            java.lang.String r0 = "contact_id"
            int r0 = r8.getColumnIndex(r0)
            r1 = -1
            if (r0 != r1) goto L_0x002a
            goto L_0x002f
        L_0x002a:
            java.lang.String r0 = r8.getString(r0)
            goto L_0x0030
        L_0x002f:
            r0 = 0
        L_0x0030:
            r8.close()
            com.facebook.react.bridge.WritableMap r8 = r7.c(r0)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rt2zz.reactnativecontacts.ContactsProvider.b(java.lang.String):com.facebook.react.bridge.WritableMap");
    }

    public WritableMap c(String str) {
        Cursor query = this.e.query(ContactsContract.Data.CONTENT_URI, (String[]) c.toArray(new String[c.size()]), "contact_id = ?", new String[]{str}, (String) null);
        try {
            Map<String, Contact> a2 = a(query);
            if (a2.values().size() > 0) {
                return a2.values().iterator().next().a();
            }
            return null;
        } finally {
            if (query != null) {
                query.close();
            }
        }
    }

    public WritableArray a() {
        Cursor query = this.e.query(Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI, "data"), (String[]) b.toArray(new String[b.size()]), (String) null, (String[]) null, (String) null);
        try {
            Map<String, Contact> a2 = a(query);
            Cursor query2 = this.e.query(ContactsContract.Data.CONTENT_URI, (String[]) c.toArray(new String[c.size()]), "mimetype=? OR mimetype=? OR mimetype=? OR mimetype=? OR mimetype=? OR mimetype=?", new String[]{"vnd.android.cursor.item/email_v2", "vnd.android.cursor.item/phone_v2", "vnd.android.cursor.item/name", "vnd.android.cursor.item/organization", "vnd.android.cursor.item/postal-address_v2", "vnd.android.cursor.item/contact_event"}, (String) null);
            try {
                Map<String, Contact> a3 = a(query2);
                WritableArray createArray = Arguments.createArray();
                for (Contact a4 : a2.values()) {
                    createArray.pushMap(a4.a());
                }
                for (Contact a5 : a3.values()) {
                    createArray.pushMap(a5.a());
                }
                return createArray;
            } finally {
                if (query2 != null) {
                    query2.close();
                }
            }
        } finally {
            if (query != null) {
                query.close();
            }
        }
    }

    @NonNull
    private Map<String, Contact> a(Cursor cursor) {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        while (cursor != null && cursor.moveToNext()) {
            int columnIndex = cursor.getColumnIndex("contact_id");
            int columnIndex2 = cursor.getColumnIndex("_id");
            int columnIndex3 = cursor.getColumnIndex("raw_contact_id");
            if (columnIndex != -1) {
                str = cursor.getString(columnIndex);
            } else {
                str = String.valueOf(-1);
            }
            if (columnIndex2 != -1) {
                str2 = cursor.getString(columnIndex2);
            } else {
                str2 = String.valueOf(-1);
            }
            if (columnIndex3 != -1) {
                str3 = cursor.getString(columnIndex3);
            } else {
                str3 = String.valueOf(-1);
            }
            if (!linkedHashMap.containsKey(str)) {
                linkedHashMap.put(str, new Contact(str));
            }
            Contact contact = (Contact) linkedHashMap.get(str);
            String string = cursor.getString(cursor.getColumnIndex(Downloads.COLUMN_MIME_TYPE));
            String string2 = cursor.getString(cursor.getColumnIndex("display_name"));
            String unused = contact.b = str3;
            if (!TextUtils.isEmpty(string2) && TextUtils.isEmpty(contact.c)) {
                String unused2 = contact.c = string2;
            }
            if (TextUtils.isEmpty(contact.m)) {
                String string3 = cursor.getString(cursor.getColumnIndex("photo_uri"));
                if (!TextUtils.isEmpty(string3)) {
                    String unused3 = contact.m = string3;
                    boolean unused4 = contact.l = true;
                }
            }
            if (string.equals("vnd.android.cursor.item/name")) {
                String unused5 = contact.d = cursor.getString(cursor.getColumnIndex("data2"));
                String unused6 = contact.e = cursor.getString(cursor.getColumnIndex("data5"));
                String unused7 = contact.f = cursor.getString(cursor.getColumnIndex("data3"));
                String unused8 = contact.g = cursor.getString(cursor.getColumnIndex("data4"));
                String unused9 = contact.h = cursor.getString(cursor.getColumnIndex("data6"));
            } else if (string.equals("vnd.android.cursor.item/phone_v2")) {
                String string4 = cursor.getString(cursor.getColumnIndex("data1"));
                int i = cursor.getInt(cursor.getColumnIndex("data2"));
                if (!TextUtils.isEmpty(string4)) {
                    switch (i) {
                        case 1:
                            str5 = "home";
                            break;
                        case 2:
                            str5 = "mobile";
                            break;
                        case 3:
                            str5 = "work";
                            break;
                        default:
                            str5 = "other";
                            break;
                    }
                    contact.o.add(new Contact.Item(str5, string4, str2));
                }
            } else if (string.equals("vnd.android.cursor.item/email_v2")) {
                String string5 = cursor.getString(cursor.getColumnIndex("data1"));
                int i2 = cursor.getInt(cursor.getColumnIndex("data2"));
                if (!TextUtils.isEmpty(string5)) {
                    if (i2 != 4) {
                        switch (i2) {
                            case 0:
                                if (cursor.getString(cursor.getColumnIndex("data3")) == null) {
                                    str4 = "";
                                    break;
                                } else {
                                    str4 = cursor.getString(cursor.getColumnIndex("data3")).toLowerCase();
                                    break;
                                }
                            case 1:
                                str4 = "home";
                                break;
                            case 2:
                                str4 = "work";
                                break;
                            default:
                                str4 = "other";
                                break;
                        }
                    } else {
                        str4 = "mobile";
                    }
                    contact.n.add(new Contact.Item(str4, string5, str2));
                }
            } else if (string.equals("vnd.android.cursor.item/organization")) {
                String unused10 = contact.i = cursor.getString(cursor.getColumnIndex("data1"));
                String unused11 = contact.j = cursor.getString(cursor.getColumnIndex("data4"));
                String unused12 = contact.k = cursor.getString(cursor.getColumnIndex("data5"));
            } else if (string.equals("vnd.android.cursor.item/postal-address_v2")) {
                contact.p.add(new Contact.PostalAddressItem(cursor));
            } else if (string.equals("vnd.android.cursor.item/contact_event") && cursor.getInt(cursor.getColumnIndex("data2")) == 3) {
                try {
                    List asList = Arrays.asList(cursor.getString(cursor.getColumnIndex("data1")).replace(HelpFormatter.f, "").split("-"));
                    if (asList.size() == 2) {
                        int parseInt = Integer.parseInt((String) asList.get(0));
                        int parseInt2 = Integer.parseInt((String) asList.get(1));
                        if (parseInt >= 1 && parseInt <= 12 && parseInt2 >= 1 && parseInt2 <= 31) {
                            Contact.Birthday unused13 = contact.q = new Contact.Birthday(parseInt, parseInt2);
                        }
                    } else if (asList.size() == 3) {
                        int parseInt3 = Integer.parseInt((String) asList.get(0));
                        int parseInt4 = Integer.parseInt((String) asList.get(1));
                        int parseInt5 = Integer.parseInt((String) asList.get(2));
                        if (parseInt3 > 0 && parseInt4 >= 1 && parseInt4 <= 12 && parseInt5 >= 1 && parseInt5 <= 31) {
                            Contact.Birthday unused14 = contact.q = new Contact.Birthday(parseInt3, parseInt4, parseInt5);
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e2) {
                    Log.w("ContactsProvider", e2.toString());
                }
            }
        }
        return linkedHashMap;
    }

    public String d(String str) {
        Cursor query = this.e.query(ContactsContract.Data.CONTENT_URI, (String[]) d.toArray(new String[d.size()]), "contact_id = ?", new String[]{str}, (String) null);
        if (query != null) {
            try {
                if (query.moveToNext()) {
                    String string = query.getString(query.getColumnIndex("photo_uri"));
                    if (!TextUtils.isEmpty(string)) {
                        return string;
                    }
                }
            } finally {
                if (query != null) {
                    query.close();
                }
            }
        }
        if (query == null) {
            return null;
        }
        query.close();
        return null;
    }

    private static class Contact {

        /* renamed from: a  reason: collision with root package name */
        private String f8720a;
        /* access modifiers changed from: private */
        public String b;
        /* access modifiers changed from: private */
        public String c;
        /* access modifiers changed from: private */
        public String d = "";
        /* access modifiers changed from: private */
        public String e = "";
        /* access modifiers changed from: private */
        public String f = "";
        /* access modifiers changed from: private */
        public String g = "";
        /* access modifiers changed from: private */
        public String h = "";
        /* access modifiers changed from: private */
        public String i = "";
        /* access modifiers changed from: private */
        public String j = "";
        /* access modifiers changed from: private */
        public String k = "";
        /* access modifiers changed from: private */
        public boolean l = false;
        /* access modifiers changed from: private */
        public String m;
        /* access modifiers changed from: private */
        public List<Item> n = new ArrayList();
        /* access modifiers changed from: private */
        public List<Item> o = new ArrayList();
        /* access modifiers changed from: private */
        public List<PostalAddressItem> p = new ArrayList();
        /* access modifiers changed from: private */
        public Birthday q;

        public Contact(String str) {
            this.f8720a = str;
        }

        public WritableMap a() {
            WritableMap createMap = Arguments.createMap();
            createMap.putString("recordID", this.f8720a);
            createMap.putString("rawContactId", this.b);
            createMap.putString("givenName", TextUtils.isEmpty(this.d) ? this.c : this.d);
            createMap.putString("middleName", this.e);
            createMap.putString("familyName", this.f);
            createMap.putString(Constants.Name.PREFIX, this.g);
            createMap.putString(Constants.Name.SUFFIX, this.h);
            createMap.putString(MibiConstants.fg, this.i);
            createMap.putString("jobTitle", this.j);
            createMap.putString("department", this.k);
            createMap.putBoolean("hasThumbnail", this.l);
            createMap.putString("thumbnailPath", this.m == null ? "" : this.m);
            WritableArray createArray = Arguments.createArray();
            for (Item next : this.o) {
                WritableMap createMap2 = Arguments.createMap();
                createMap2.putString("number", next.b);
                createMap2.putString("label", next.f8722a);
                createMap2.putString("id", next.c);
                createArray.pushMap(createMap2);
            }
            createMap.putArray("phoneNumbers", createArray);
            WritableArray createArray2 = Arguments.createArray();
            for (Item next2 : this.n) {
                WritableMap createMap3 = Arguments.createMap();
                createMap3.putString("email", next2.b);
                createMap3.putString("label", next2.f8722a);
                createMap3.putString("id", next2.c);
                createArray2.pushMap(createMap3);
            }
            createMap.putArray("emailAddresses", createArray2);
            WritableArray createArray3 = Arguments.createArray();
            for (PostalAddressItem postalAddressItem : this.p) {
                createArray3.pushMap(postalAddressItem.f8723a);
            }
            createMap.putArray("postalAddresses", createArray3);
            WritableMap createMap4 = Arguments.createMap();
            if (this.q != null) {
                if (this.q.f8721a > 0) {
                    createMap4.putInt("year", this.q.f8721a);
                }
                createMap4.putInt("month", this.q.b);
                createMap4.putInt("day", this.q.c);
                createMap.putMap(ParamKey.birthday, createMap4);
            }
            return createMap;
        }

        public static class Item {

            /* renamed from: a  reason: collision with root package name */
            public String f8722a;
            public String b;
            public String c;

            public Item(String str, String str2, String str3) {
                this.c = str3;
                this.f8722a = str;
                this.b = str2;
            }

            public Item(String str, String str2) {
                this.f8722a = str;
                this.b = str2;
            }
        }

        public static class Birthday {

            /* renamed from: a  reason: collision with root package name */
            public int f8721a = 0;
            public int b = 0;
            public int c = 0;

            public Birthday(int i, int i2, int i3) {
                this.f8721a = i;
                this.b = i2;
                this.c = i3;
            }

            public Birthday(int i, int i2) {
                this.b = i;
                this.c = i2;
            }
        }

        public static class PostalAddressItem {

            /* renamed from: a  reason: collision with root package name */
            public final WritableMap f8723a = Arguments.createMap();

            public PostalAddressItem(Cursor cursor) {
                this.f8723a.putString("label", a(cursor));
                a(cursor, "formattedAddress", "data1");
                a(cursor, "street", "data4");
                a(cursor, "pobox", "data5");
                a(cursor, "neighborhood", "data6");
                a(cursor, "city", "data7");
                a(cursor, "region", "data8");
                a(cursor, "state", "data8");
                a(cursor, "postCode", "data9");
                a(cursor, "country", "data10");
            }

            private void a(Cursor cursor, String str, String str2) {
                String string = cursor.getString(cursor.getColumnIndex(str2));
                if (!TextUtils.isEmpty(string)) {
                    this.f8723a.putString(str, string);
                }
            }

            static String a(Cursor cursor) {
                switch (cursor.getInt(cursor.getColumnIndex("data2"))) {
                    case 0:
                        String string = cursor.getString(cursor.getColumnIndex("data3"));
                        return string != null ? string : "";
                    case 1:
                        return "home";
                    case 2:
                        return "work";
                    default:
                        return "other";
                }
            }
        }
    }
}
