package org.jsoup.nodes;

import com.brentvatne.react.ReactVideoViewManager;
import com.facebook.react.bridge.BaseJavaModule;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.family.ShareDeviceActivity;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;

public class Attribute implements Cloneable, Map.Entry<String, String> {
    private static final String[] b = {"allowfullscreen", BaseJavaModule.METHOD_TYPE_ASYNC, Constants.Name.AUTOFOCUS, "checked", MessengerShareContentUtility.WEBVIEW_RATIO_COMPACT, "declare", "default", "defer", Constants.Name.DISABLED, "formnovalidate", "hidden", "inert", "ismap", "itemscope", "multiple", ReactVideoViewManager.PROP_MUTED, "nohref", "noresize", "noshade", "novalidate", "nowrap", "open", ShareDeviceActivity.KEY_SHARE_TYPE_READONLY, "required", "reversed", "seamless", "selected", "sortable", "truespeed", "typemustmatch"};

    /* renamed from: a  reason: collision with root package name */
    Attributes f3656a;
    private String c;
    private String d;

    public Attribute(String str, String str2) {
        this(str, str2, (Attributes) null);
    }

    public Attribute(String str, String str2, Attributes attributes) {
        Validate.a((Object) str);
        this.c = str.trim();
        Validate.a(str);
        this.d = str2;
        this.f3656a = attributes;
    }

    /* renamed from: a */
    public String getKey() {
        return this.c;
    }

    public void a(String str) {
        int a2;
        Validate.a((Object) str);
        String trim = str.trim();
        Validate.a(trim);
        if (!(this.f3656a == null || (a2 = this.f3656a.a(this.c)) == -1)) {
            this.f3656a.c[a2] = trim;
        }
        this.c = trim;
    }

    /* renamed from: b */
    public String getValue() {
        return this.d;
    }

    /* renamed from: b */
    public String setValue(String str) {
        int a2;
        String c2 = this.f3656a.c(this.c);
        if (!(this.f3656a == null || (a2 = this.f3656a.a(this.c)) == -1)) {
            this.f3656a.d[a2] = str;
        }
        this.d = str;
        return c2;
    }

    public String c() {
        StringBuilder sb = new StringBuilder();
        try {
            a((Appendable) sb, new Document("").m());
            return sb.toString();
        } catch (IOException e) {
            throw new SerializationException((Throwable) e);
        }
    }

    protected static void a(String str, String str2, Appendable appendable, Document.OutputSettings outputSettings) throws IOException {
        appendable.append(str);
        if (!a(str, str2, outputSettings)) {
            appendable.append("=\"");
            Entities.a(appendable, Attributes.b(str2), outputSettings, true, false, false);
            appendable.append('\"');
        }
    }

    /* access modifiers changed from: protected */
    public void a(Appendable appendable, Document.OutputSettings outputSettings) throws IOException {
        a(this.c, this.d, appendable, outputSettings);
    }

    public String toString() {
        return c();
    }

    public static Attribute a(String str, String str2) {
        return new Attribute(str, Entities.a(str2, true), (Attributes) null);
    }

    /* access modifiers changed from: protected */
    public boolean d() {
        return c(this.c);
    }

    protected static boolean c(String str) {
        return str.startsWith("data-") && str.length() > "data-".length();
    }

    /* access modifiers changed from: protected */
    public final boolean a(Document.OutputSettings outputSettings) {
        return a(this.c, this.d, outputSettings);
    }

    protected static boolean a(String str, String str2, Document.OutputSettings outputSettings) {
        return (str2 == null || "".equals(str2) || str2.equalsIgnoreCase(str)) && outputSettings.e() == Document.OutputSettings.Syntax.html && d(str);
    }

    /* access modifiers changed from: protected */
    public boolean e() {
        return Arrays.binarySearch(b, this.c) >= 0 || this.d == null;
    }

    protected static boolean d(String str) {
        return Arrays.binarySearch(b, str) >= 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Attribute attribute = (Attribute) obj;
        if (this.c == null ? attribute.c != null : !this.c.equals(attribute.c)) {
            return false;
        }
        if (this.d != null) {
            return this.d.equals(attribute.d);
        }
        if (attribute.d == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = (this.c != null ? this.c.hashCode() : 0) * 31;
        if (this.d != null) {
            i = this.d.hashCode();
        }
        return hashCode + i;
    }

    /* renamed from: f */
    public Attribute clone() {
        try {
            return (Attribute) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
