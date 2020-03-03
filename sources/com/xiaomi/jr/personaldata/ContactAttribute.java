package com.xiaomi.jr.personaldata;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.SparseArray;
import com.mi.global.bbs.http.ParamKey;
import com.xiaomi.accountsdk.account.AccountIntent;
import com.xiaomi.payment.data.MibiConstants;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

public interface ContactAttribute {
    String a();

    void a(Cursor cursor);

    Object b(Cursor cursor);

    String b();

    Set<String> c();

    public static class BaseContactAttribute implements ContactAttribute {

        /* renamed from: a  reason: collision with root package name */
        protected String f11004a;
        protected String b;
        protected Map<String, Integer> c = new HashMap();

        public Object b(Cursor cursor) {
            return null;
        }

        public BaseContactAttribute(String str, String str2, String[] strArr) {
            this.f11004a = str;
            this.b = str2;
            for (String put : strArr) {
                this.c.put(put, (Object) null);
            }
        }

        public String a() {
            return this.f11004a;
        }

        public String b() {
            return this.b;
        }

        public Set<String> c() {
            return this.c.keySet();
        }

        public void a(Cursor cursor) {
            for (String next : this.c.keySet()) {
                this.c.put(next, Integer.valueOf(cursor.getColumnIndex(next)));
            }
        }
    }

    public static class SingleColumnAttribute extends BaseContactAttribute {
        public SingleColumnAttribute(String str, String str2, String str3) {
            super(str, str2, new String[]{str3});
        }

        public Object b(Cursor cursor) {
            return cursor.getString(((Integer) this.c.values().iterator().next()).intValue());
        }
    }

    public static class MultipleColumnAttribute extends BaseContactAttribute {
        private String[] d;
        private String[] e;

        public MultipleColumnAttribute(String str, String str2, String[] strArr, String[] strArr2) {
            super(str, str2, strArr);
            this.d = strArr;
            this.e = strArr2;
            if (strArr2.length != strArr.length) {
                throw new RuntimeException("dataFields and columnNames length not match");
            }
        }

        public Object b(Cursor cursor) {
            JSONObject jSONObject = new JSONObject();
            for (int i = 0; i < this.d.length; i++) {
                try {
                    jSONObject.put(this.e[i], cursor.getString(((Integer) this.c.get(this.d[i])).intValue()));
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            return jSONObject;
        }
    }

    public static class Name extends SingleColumnAttribute {
        public Name() {
            super("name", "vnd.android.cursor.item/name", "data1");
        }
    }

    public static class Number extends SingleColumnAttribute {
        public Number() {
            super("number", "vnd.android.cursor.item/phone_v2", "data4");
        }
    }

    public static class Email extends SingleColumnAttribute {
        public Email() {
            super("email", "vnd.android.cursor.item/email_v2", "data1");
        }
    }

    public static class Im extends BaseContactAttribute {
        private SparseArray<String> d = new SparseArray<>();

        public Im() {
            super("im", "vnd.android.cursor.item/im", new String[]{"data5", "data6", "data1"});
            this.d.put(0, "aim");
            this.d.put(1, "msn");
            this.d.put(2, "yahoo");
            this.d.put(3, "skype");
            this.d.put(4, AccountIntent.QQ_SNS_TYPE);
            this.d.put(5, "googletalk");
            this.d.put(6, "icq");
            this.d.put(7, "jabber");
            this.d.put(8, "netmeeting");
        }

        public Object b(Cursor cursor) {
            String str;
            int i = cursor.getInt(((Integer) this.c.get("data5")).intValue());
            if (i != -1) {
                str = this.d.get(i);
            } else {
                str = cursor.getString(((Integer) this.c.get("data6")).intValue());
            }
            String string = cursor.getString(((Integer) this.c.get("data1")).intValue());
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("type", str);
                jSONObject.put("number", string);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jSONObject;
        }
    }

    public static class Organization extends MultipleColumnAttribute {
        public Organization() {
            super("organization", "vnd.android.cursor.item/organization", new String[]{"data1", "data4"}, new String[]{MibiConstants.fg, "title"});
        }
    }

    public static class Postal extends SingleColumnAttribute {
        public Postal() {
            super("postal", "vnd.android.cursor.item/postal-address_v2", "data1");
        }
    }

    public static class Group extends SingleColumnAttribute {
        private Context d;
        private Map<String, String> e;

        public Group(Context context) {
            super("group", "vnd.android.cursor.item/group_membership", "data1");
            this.d = context;
        }

        /* renamed from: c */
        public String b(Cursor cursor) {
            if (this.e == null) {
                this.e = d();
            }
            Object b = super.b(cursor);
            if (b != null) {
                return this.e.get((String) b);
            }
            return null;
        }

        private HashMap<String, String> d() {
            ContentResolver contentResolver = this.d.getContentResolver();
            HashMap<String, String> hashMap = new HashMap<>();
            Cursor query = contentResolver.query(ContactsContract.Groups.CONTENT_URI, new String[]{"_id", "title"}, (String) null, (String[]) null, (String) null);
            if (query != null) {
                while (query.moveToNext()) {
                    String string = query.getString(0);
                    String string2 = query.getString(1);
                    if (!TextUtils.isEmpty(string2)) {
                        hashMap.put(string, string2);
                    }
                }
                query.close();
            }
            return hashMap;
        }
    }

    public static class Event extends SingleColumnAttribute {
        public Event() {
            super(ParamKey.birthday, "vnd.android.cursor.item/contact_event", "data1");
        }
    }

    public static class Relation extends SingleColumnAttribute {
        public Relation() {
            super("relation", "vnd.android.cursor.item/relation", "data1");
        }
    }
}
