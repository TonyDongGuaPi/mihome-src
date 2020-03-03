package org.xutils.http;

import android.text.TextUtils;
import com.alipay.sdk.sys.a;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.util.KeyValue;
import org.xutils.common.util.LogUtil;
import org.xutils.http.body.BodyItemWrapper;
import org.xutils.http.body.FileBody;
import org.xutils.http.body.InputStreamBody;
import org.xutils.http.body.MultipartBody;
import org.xutils.http.body.RequestBody;
import org.xutils.http.body.StringBody;
import org.xutils.http.body.UrlEncodedParamsBody;

abstract class BaseParams {

    /* renamed from: a  reason: collision with root package name */
    private String f10762a = "UTF-8";
    private HttpMethod b;
    private String c;
    private boolean d = false;
    private boolean e = false;
    private RequestBody f;
    private final List<Header> g = new ArrayList();
    private final List<KeyValue> h = new ArrayList();
    private final List<KeyValue> i = new ArrayList();
    private final List<KeyValue> j = new ArrayList();

    BaseParams() {
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f10762a = str;
        }
    }

    public String a() {
        return this.f10762a;
    }

    public void a(HttpMethod httpMethod) {
        this.b = httpMethod;
    }

    public HttpMethod b() {
        return this.b;
    }

    public boolean c() {
        return this.d;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public boolean d() {
        return this.e;
    }

    public void b(boolean z) {
        this.e = z;
    }

    public void a(String str, String str2) {
        Header header = new Header(str, str2, true);
        Iterator<Header> it = this.g.iterator();
        while (it.hasNext()) {
            if (str.equals(it.next().f4233a)) {
                it.remove();
            }
        }
        this.g.add(header);
    }

    public void b(String str, String str2) {
        this.g.add(new Header(str, str2, false));
    }

    public void a(String str, Object obj) {
        if (obj != null) {
            int i2 = 0;
            if (this.b == null || HttpMethod.permitsRequestBody(this.b)) {
                if (TextUtils.isEmpty(str)) {
                    this.c = obj.toString();
                } else if ((obj instanceof File) || (obj instanceof InputStream) || (obj instanceof byte[])) {
                    this.j.add(new KeyValue(str, obj));
                } else if (obj instanceof List) {
                    for (Object arrayItem : (List) obj) {
                        this.i.add(new ArrayItem(str, arrayItem));
                    }
                } else if (obj instanceof JSONArray) {
                    JSONArray jSONArray = (JSONArray) obj;
                    int length = jSONArray.length();
                    while (i2 < length) {
                        this.i.add(new ArrayItem(str, jSONArray.opt(i2)));
                        i2++;
                    }
                } else if (obj.getClass().isArray()) {
                    int length2 = Array.getLength(obj);
                    while (i2 < length2) {
                        this.i.add(new ArrayItem(str, Array.get(obj, i2)));
                        i2++;
                    }
                } else {
                    this.i.add(new KeyValue(str, obj));
                }
            } else if (TextUtils.isEmpty(str)) {
            } else {
                if (obj instanceof List) {
                    for (Object arrayItem2 : (List) obj) {
                        this.h.add(new ArrayItem(str, arrayItem2));
                    }
                } else if (obj.getClass().isArray()) {
                    int length3 = Array.getLength(obj);
                    while (i2 < length3) {
                        this.h.add(new ArrayItem(str, Array.get(obj, i2)));
                        i2++;
                    }
                } else {
                    this.h.add(new KeyValue(str, obj));
                }
            }
        }
    }

    public void c(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            this.h.add(new KeyValue(str, str2));
        }
    }

    public void d(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            this.i.add(new KeyValue(str, str2));
        } else {
            this.c = str2;
        }
    }

    public void a(String str, File file) {
        a(str, file, (String) null, (String) null);
    }

    public void a(String str, Object obj, String str2) {
        a(str, obj, str2, (String) null);
    }

    public void a(String str, Object obj, String str2, String str3) {
        if (!TextUtils.isEmpty(str2) || !TextUtils.isEmpty(str3)) {
            this.j.add(new KeyValue(str, new BodyItemWrapper(obj, str2, str3)));
        } else {
            this.j.add(new KeyValue(str, obj));
        }
    }

    public void b(String str) {
        this.c = str;
    }

    public String e() {
        n();
        return this.c;
    }

    public List<Header> f() {
        return new ArrayList(this.g);
    }

    public List<KeyValue> g() {
        n();
        return new ArrayList(this.h);
    }

    public List<KeyValue> h() {
        n();
        return new ArrayList(this.i);
    }

    public List<KeyValue> i() {
        n();
        return new ArrayList(this.j);
    }

    public List<KeyValue> j() {
        ArrayList arrayList = new ArrayList(this.h.size() + this.i.size());
        arrayList.addAll(this.h);
        arrayList.addAll(this.i);
        return arrayList;
    }

    public String c(String str) {
        for (KeyValue next : this.h) {
            if (str == null && next.f4233a == null) {
                return next.a();
            }
            if (str != null && str.equals(next.f4233a)) {
                return next.a();
            }
        }
        for (KeyValue next2 : this.i) {
            if (str == null && next2.f4233a == null) {
                return next2.a();
            }
            if (str != null && str.equals(next2.f4233a)) {
                return next2.a();
            }
        }
        return null;
    }

    public List<KeyValue> d(String str) {
        ArrayList arrayList = new ArrayList();
        for (KeyValue next : this.h) {
            if (str == null && next.f4233a == null) {
                arrayList.add(next);
            } else if (str != null && str.equals(next.f4233a)) {
                arrayList.add(next);
            }
        }
        for (KeyValue next2 : this.i) {
            if (str == null && next2.f4233a == null) {
                arrayList.add(next2);
            } else if (str != null && str.equals(next2.f4233a)) {
                arrayList.add(next2);
            }
        }
        for (KeyValue next3 : this.j) {
            if (str == null && next3.f4233a == null) {
                arrayList.add(next3);
            } else if (str != null && str.equals(next3.f4233a)) {
                arrayList.add(next3);
            }
        }
        return arrayList;
    }

    public void k() {
        this.h.clear();
        this.i.clear();
        this.j.clear();
        this.c = null;
        this.f = null;
    }

    public void e(String str) {
        if (!TextUtils.isEmpty(str)) {
            Iterator<KeyValue> it = this.h.iterator();
            while (it.hasNext()) {
                if (str.equals(it.next().f4233a)) {
                    it.remove();
                }
            }
            Iterator<KeyValue> it2 = this.i.iterator();
            while (it2.hasNext()) {
                if (str.equals(it2.next().f4233a)) {
                    it2.remove();
                }
            }
            Iterator<KeyValue> it3 = this.j.iterator();
            while (it3.hasNext()) {
                if (str.equals(it3.next().f4233a)) {
                    it3.remove();
                }
            }
            return;
        }
        this.c = null;
    }

    public void a(RequestBody requestBody) {
        this.f = requestBody;
    }

    public RequestBody l() throws IOException {
        String str;
        n();
        if (this.f != null) {
            return this.f;
        }
        if (!TextUtils.isEmpty(this.c)) {
            return new StringBody(this.c, this.f10762a);
        }
        if (this.d || this.j.size() > 0) {
            if (this.d || this.j.size() != 1) {
                this.d = true;
                return new MultipartBody(this.j, this.f10762a);
            }
            Iterator<KeyValue> it = this.j.iterator();
            if (!it.hasNext()) {
                return null;
            }
            Object obj = it.next().b;
            if (obj instanceof BodyItemWrapper) {
                BodyItemWrapper bodyItemWrapper = (BodyItemWrapper) obj;
                Object a2 = bodyItemWrapper.a();
                str = bodyItemWrapper.c();
                obj = a2;
            } else {
                str = null;
            }
            if (obj instanceof File) {
                return new FileBody((File) obj, str);
            }
            if (obj instanceof InputStream) {
                return new InputStreamBody((InputStream) obj, str);
            }
            if (obj instanceof byte[]) {
                return new InputStreamBody(new ByteArrayInputStream((byte[]) obj), str);
            }
            if (obj instanceof String) {
                StringBody stringBody = new StringBody((String) obj, this.f10762a);
                stringBody.a(str);
                return stringBody;
            }
            LogUtil.e("Some params will be ignored for: " + toString());
            return null;
        } else if (this.i.size() > 0) {
            return new UrlEncodedParamsBody(this.i, this.f10762a);
        } else {
            return null;
        }
    }

    public String m() {
        JSONObject jSONObject;
        ArrayList arrayList = new ArrayList(this.h.size() + this.i.size());
        arrayList.addAll(this.h);
        arrayList.addAll(this.i);
        try {
            if (!TextUtils.isEmpty(this.c)) {
                jSONObject = new JSONObject(this.c);
            } else {
                jSONObject = new JSONObject();
            }
            a(jSONObject, (List<KeyValue>) arrayList);
            return jSONObject.toString();
        } catch (JSONException e2) {
            throw new RuntimeException(e2);
        }
    }

    public String toString() {
        n();
        StringBuilder sb = new StringBuilder();
        if (!this.h.isEmpty()) {
            for (KeyValue next : this.h) {
                sb.append(next.f4233a);
                sb.append("=");
                sb.append(next.b);
                sb.append(a.b);
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        if (HttpMethod.permitsRequestBody(this.b)) {
            sb.append("<");
            if (!TextUtils.isEmpty(this.c)) {
                sb.append(this.c);
            } else if (!this.i.isEmpty()) {
                for (KeyValue next2 : this.i) {
                    sb.append(next2.f4233a);
                    sb.append("=");
                    sb.append(next2.b);
                    sb.append(a.b);
                }
                sb.deleteCharAt(sb.length() - 1);
            }
            sb.append(">");
        }
        return sb.toString();
    }

    private void n() {
        JSONObject jSONObject;
        if (!this.i.isEmpty()) {
            if (!HttpMethod.permitsRequestBody(this.b) || !TextUtils.isEmpty(this.c) || this.f != null) {
                this.h.addAll(this.i);
                this.i.clear();
            }
            if (!this.i.isEmpty() && (this.d || this.j.size() > 0)) {
                this.j.addAll(this.i);
                this.i.clear();
            }
            if (this.e && !this.i.isEmpty()) {
                try {
                    if (!TextUtils.isEmpty(this.c)) {
                        jSONObject = new JSONObject(this.c);
                    } else {
                        jSONObject = new JSONObject();
                    }
                    a(jSONObject, this.i);
                    this.c = jSONObject.toString();
                    this.i.clear();
                } catch (JSONException e2) {
                    throw new RuntimeException(e2);
                }
            }
        }
    }

    private void a(JSONObject jSONObject, List<KeyValue> list) throws JSONException {
        JSONArray jSONArray;
        HashSet hashSet = new HashSet(list.size());
        LinkedHashMap linkedHashMap = new LinkedHashMap(list.size());
        for (int i2 = 0; i2 < list.size(); i2++) {
            KeyValue keyValue = list.get(i2);
            String str = keyValue.f4233a;
            if (!TextUtils.isEmpty(str)) {
                if (linkedHashMap.containsKey(str)) {
                    jSONArray = (JSONArray) linkedHashMap.get(str);
                } else {
                    jSONArray = new JSONArray();
                    linkedHashMap.put(str, jSONArray);
                }
                jSONArray.put(RequestParamsHelper.a(keyValue.b));
                if (keyValue instanceof ArrayItem) {
                    hashSet.add(str);
                }
            }
        }
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            String str2 = (String) entry.getKey();
            JSONArray jSONArray2 = (JSONArray) entry.getValue();
            if (jSONArray2.length() > 1 || hashSet.contains(str2)) {
                jSONObject.put(str2, jSONArray2);
            } else {
                jSONObject.put(str2, jSONArray2.get(0));
            }
        }
    }

    public static final class ArrayItem extends KeyValue {
        public ArrayItem(String str, Object obj) {
            super(str, obj);
        }
    }

    public static final class Header extends KeyValue {
        public final boolean c;

        public Header(String str, String str2, boolean z) {
            super(str, str2);
            this.c = z;
        }
    }
}
