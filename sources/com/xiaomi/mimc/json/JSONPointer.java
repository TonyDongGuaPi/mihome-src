package com.xiaomi.mimc.json;

import com.mi.global.shop.model.Tags;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.smarthome.fastvideo.IOUtils;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JSONPointer {

    /* renamed from: a  reason: collision with root package name */
    private static final String f11201a = "utf-8";
    private final List<String> b;

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private final List<String> f11202a = new ArrayList();

        public JSONPointer a() {
            return new JSONPointer(this.f11202a);
        }

        public Builder a(String str) {
            if (str != null) {
                this.f11202a.add(str);
                return this;
            }
            throw new NullPointerException("token cannot be null");
        }

        public Builder a(int i) {
            this.f11202a.add(String.valueOf(i));
            return this;
        }
    }

    public static Builder a() {
        return new Builder();
    }

    public JSONPointer(String str) {
        String str2;
        if (str == null) {
            throw new NullPointerException("pointer cannot be null");
        } else if (str.isEmpty() || str.equals("#")) {
            this.b = Collections.emptyList();
        } else {
            if (str.startsWith("#/")) {
                try {
                    str2 = URLDecoder.decode(str.substring(2), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            } else if (str.startsWith("/")) {
                str2 = str.substring(1);
            } else {
                throw new IllegalArgumentException("a JSON pointer should start with '/' or '#/'");
            }
            this.b = new ArrayList();
            int i = -1;
            while (true) {
                int i2 = i + 1;
                int indexOf = str2.indexOf(47, i2);
                if (i2 == indexOf || i2 == str2.length()) {
                    this.b.add("");
                } else if (indexOf >= 0) {
                    this.b.add(a(str2.substring(i2, indexOf)));
                } else {
                    this.b.add(a(str2.substring(i2)));
                }
                if (indexOf >= 0) {
                    i = indexOf;
                } else {
                    return;
                }
            }
        }
    }

    public JSONPointer(List<String> list) {
        this.b = new ArrayList(list);
    }

    private String a(String str) {
        return str.replace("~1", "/").replace("~0", Constants.J).replace("\\\"", "\"").replace("\\\\", Tags.MiHome.TEL_SEPARATOR4);
    }

    public Object a(Object obj) throws JSONPointerException {
        if (this.b.isEmpty()) {
            return obj;
        }
        for (String next : this.b) {
            if (obj instanceof JSONObject) {
                obj = ((JSONObject) obj).p(a(next));
            } else if (obj instanceof JSONArray) {
                obj = a(obj, next);
            } else {
                throw new JSONPointerException(String.format("value [%s] is not an array or object therefore its key %s cannot be resolved", new Object[]{obj, next}));
            }
        }
        return obj;
    }

    private Object a(Object obj, String str) throws JSONPointerException {
        int parseInt;
        try {
            parseInt = Integer.parseInt(str);
            JSONArray jSONArray = (JSONArray) obj;
            if (parseInt < jSONArray.a()) {
                return jSONArray.a(parseInt);
            }
            throw new JSONPointerException(String.format("index %d is out of bounds - the array has %d elements", new Object[]{Integer.valueOf(parseInt), Integer.valueOf(jSONArray.a())}));
        } catch (JSONException e) {
            throw new JSONPointerException("Error reading value at index position " + parseInt, e);
        } catch (NumberFormatException e2) {
            throw new JSONPointerException(String.format("%s is not an array index", new Object[]{str}), e2);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("");
        for (String b2 : this.b) {
            sb.append(IOUtils.f15883a);
            sb.append(b(b2));
        }
        return sb.toString();
    }

    private String b(String str) {
        return str.replace(Constants.J, "~0").replace("/", "~1").replace(Tags.MiHome.TEL_SEPARATOR4, "\\\\").replace("\"", "\\\"");
    }

    public String b() {
        try {
            StringBuilder sb = new StringBuilder("#");
            for (String encode : this.b) {
                sb.append(IOUtils.f15883a);
                sb.append(URLEncoder.encode(encode, "utf-8"));
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
