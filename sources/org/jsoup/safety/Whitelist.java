package org.jsoup.safety;

import com.alipay.mobile.security.bio.workspace.Env;
import com.facebook.share.internal.ShareConstants;
import com.unionpay.tsmservice.mi.data.Constant;
import com.xiaomi.miot.support.monitor.core.BaseInfo;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.smarthome.core.server.internal.plugin.PluginManager;
import com.xiaomi.stat.a.j;
import com.xiaomi.stat.d;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.jsoup.helper.Validate;
import org.jsoup.internal.Normalizer;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;

public class Whitelist {

    /* renamed from: a  reason: collision with root package name */
    private Set<TagName> f3691a = new HashSet();
    private Map<TagName, Set<AttributeKey>> b = new HashMap();
    private Map<TagName, Map<AttributeKey, AttributeValue>> c = new HashMap();
    private Map<TagName, Map<AttributeKey, Set<Protocol>>> d = new HashMap();
    private boolean e = false;

    public static Whitelist a() {
        return new Whitelist();
    }

    public static Whitelist b() {
        return new Whitelist().a("b", "em", "i", "strong", "u");
    }

    public static Whitelist c() {
        return new Whitelist().a("a", "b", "blockquote", TtmlNode.TAG_BR, "cite", "code", d.s, "dl", "dt", "em", "i", "li", "ol", "p", Env.NAME_PRE, DTransferConstants.F, "small", "span", "strike", "strong", j.i, "sup", "u", "ul").a("a", "href").a("blockquote", "cite").a(DTransferConstants.F, "cite").a("a", "href", "ftp", "http", "https", "mailto").a("blockquote", "cite", "http", "https").a("cite", "cite", "http", "https").a("a", "rel", "nofollow");
    }

    public static Whitelist d() {
        return c().a("img").a("img", "align", "alt", "height", "src", "title", "width").a("img", "src", "http", "https");
    }

    public static Whitelist e() {
        return new Whitelist().a("a", "b", "blockquote", TtmlNode.TAG_BR, ShareConstants.FEED_CAPTION_PARAM, "cite", "code", Constant.KEY_COL, "colgroup", d.s, "div", "dl", "dt", "em", "h1", "h2", "h3", "h4", PluginManager.h, "h6", "i", "img", "li", "ol", "p", Env.NAME_PRE, DTransferConstants.F, "small", "span", "strike", "strong", j.i, "sup", "table", "tbody", "td", "tfoot", "th", "thead", BaseInfo.KEY_TIME_RECORD, "u", "ul").a("a", "href", "title").a("blockquote", "cite").a(Constant.KEY_COL, "span", "width").a("colgroup", "span", "width").a("img", "align", "alt", "height", "src", "title", "width").a("ol", "start", "type").a(DTransferConstants.F, "cite").a("table", MibiConstants.ee, "width").a("td", "abbr", "axis", "colspan", "rowspan", "width").a("th", "abbr", "axis", "colspan", "rowspan", "scope", "width").a("ul", "type").a("a", "href", "ftp", "http", "https", "mailto").a("blockquote", "cite", "http", "https").a("cite", "cite", "http", "https").a("img", "src", "http", "https").a(DTransferConstants.F, "cite", "http", "https");
    }

    public Whitelist a(String... strArr) {
        Validate.a((Object) strArr);
        for (String str : strArr) {
            Validate.a(str);
            this.f3691a.add(TagName.a(str));
        }
        return this;
    }

    public Whitelist b(String... strArr) {
        Validate.a((Object) strArr);
        for (String str : strArr) {
            Validate.a(str);
            TagName a2 = TagName.a(str);
            if (this.f3691a.remove(a2)) {
                this.b.remove(a2);
                this.c.remove(a2);
                this.d.remove(a2);
            }
        }
        return this;
    }

    public Whitelist a(String str, String... strArr) {
        Validate.a(str);
        Validate.a((Object) strArr);
        Validate.a(strArr.length > 0, "No attribute names supplied.");
        TagName a2 = TagName.a(str);
        if (!this.f3691a.contains(a2)) {
            this.f3691a.add(a2);
        }
        HashSet hashSet = new HashSet();
        for (String str2 : strArr) {
            Validate.a(str2);
            hashSet.add(AttributeKey.a(str2));
        }
        if (this.b.containsKey(a2)) {
            this.b.get(a2).addAll(hashSet);
        } else {
            this.b.put(a2, hashSet);
        }
        return this;
    }

    public Whitelist b(String str, String... strArr) {
        Validate.a(str);
        Validate.a((Object) strArr);
        Validate.a(strArr.length > 0, "No attribute names supplied.");
        TagName a2 = TagName.a(str);
        HashSet hashSet = new HashSet();
        for (String str2 : strArr) {
            Validate.a(str2);
            hashSet.add(AttributeKey.a(str2));
        }
        if (this.f3691a.contains(a2) && this.b.containsKey(a2)) {
            Set set = this.b.get(a2);
            set.removeAll(hashSet);
            if (set.isEmpty()) {
                this.b.remove(a2);
            }
        }
        if (str.equals(":all")) {
            for (TagName next : this.b.keySet()) {
                Set set2 = this.b.get(next);
                set2.removeAll(hashSet);
                if (set2.isEmpty()) {
                    this.b.remove(next);
                }
            }
        }
        return this;
    }

    public Whitelist a(String str, String str2, String str3) {
        Validate.a(str);
        Validate.a(str2);
        Validate.a(str3);
        TagName a2 = TagName.a(str);
        if (!this.f3691a.contains(a2)) {
            this.f3691a.add(a2);
        }
        AttributeKey a3 = AttributeKey.a(str2);
        AttributeValue a4 = AttributeValue.a(str3);
        if (this.c.containsKey(a2)) {
            this.c.get(a2).put(a3, a4);
        } else {
            HashMap hashMap = new HashMap();
            hashMap.put(a3, a4);
            this.c.put(a2, hashMap);
        }
        return this;
    }

    public Whitelist a(String str, String str2) {
        Validate.a(str);
        Validate.a(str2);
        TagName a2 = TagName.a(str);
        if (this.f3691a.contains(a2) && this.c.containsKey(a2)) {
            AttributeKey a3 = AttributeKey.a(str2);
            Map map = this.c.get(a2);
            map.remove(a3);
            if (map.isEmpty()) {
                this.c.remove(a2);
            }
        }
        return this;
    }

    public Whitelist a(boolean z) {
        this.e = z;
        return this;
    }

    public Whitelist a(String str, String str2, String... strArr) {
        Map map;
        Set set;
        Validate.a(str);
        Validate.a(str2);
        Validate.a((Object) strArr);
        TagName a2 = TagName.a(str);
        AttributeKey a3 = AttributeKey.a(str2);
        if (this.d.containsKey(a2)) {
            map = this.d.get(a2);
        } else {
            HashMap hashMap = new HashMap();
            this.d.put(a2, hashMap);
            map = hashMap;
        }
        if (map.containsKey(a3)) {
            set = (Set) map.get(a3);
        } else {
            HashSet hashSet = new HashSet();
            map.put(a3, hashSet);
            set = hashSet;
        }
        for (String str3 : strArr) {
            Validate.a(str3);
            set.add(Protocol.a(str3));
        }
        return this;
    }

    public Whitelist b(String str, String str2, String... strArr) {
        Validate.a(str);
        Validate.a(str2);
        Validate.a((Object) strArr);
        TagName a2 = TagName.a(str);
        AttributeKey a3 = AttributeKey.a(str2);
        Validate.a(this.d.containsKey(a2), "Cannot remove a protocol that is not set.");
        Map map = this.d.get(a2);
        Validate.a(map.containsKey(a3), "Cannot remove a protocol that is not set.");
        Set set = (Set) map.get(a3);
        for (String str3 : strArr) {
            Validate.a(str3);
            set.remove(Protocol.a(str3));
        }
        if (set.isEmpty()) {
            map.remove(a3);
            if (map.isEmpty()) {
                this.d.remove(a2);
            }
        }
        return this;
    }

    /* access modifiers changed from: protected */
    public boolean a(String str) {
        return this.f3691a.contains(TagName.a(str));
    }

    /* access modifiers changed from: protected */
    public boolean a(String str, Element element, Attribute attribute) {
        TagName a2 = TagName.a(str);
        AttributeKey a3 = AttributeKey.a(attribute.getKey());
        Set set = this.b.get(a2);
        if (set == null || !set.contains(a3)) {
            if (this.c.get(a2) != null) {
                Attributes b2 = b(str);
                String a4 = attribute.getKey();
                if (b2.h(a4)) {
                    return b2.d(a4).equals(attribute.getValue());
                }
            }
            if (str.equals(":all") || !a(":all", element, attribute)) {
                return false;
            }
            return true;
        } else if (!this.d.containsKey(a2)) {
            return true;
        } else {
            Map map = this.d.get(a2);
            if (!map.containsKey(a3) || a(element, attribute, (Set<Protocol>) (Set) map.get(a3))) {
                return true;
            }
            return false;
        }
    }

    private boolean a(Element element, Attribute attribute, Set<Protocol> set) {
        String a2 = element.a(attribute.getKey());
        if (a2.length() == 0) {
            a2 = attribute.getValue();
        }
        if (!this.e) {
            attribute.setValue(a2);
        }
        for (Protocol protocol : set) {
            String protocol2 = protocol.toString();
            if (!protocol2.equals("#")) {
                if (Normalizer.a(a2).startsWith(protocol2 + ":")) {
                    return true;
                }
            } else if (c(a2)) {
                return true;
            }
        }
        return false;
    }

    private boolean c(String str) {
        return str.startsWith("#") && !str.matches(".*\\s.*");
    }

    /* access modifiers changed from: package-private */
    public Attributes b(String str) {
        Attributes attributes = new Attributes();
        TagName a2 = TagName.a(str);
        if (this.c.containsKey(a2)) {
            for (Map.Entry entry : this.c.get(a2).entrySet()) {
                attributes.a(((AttributeKey) entry.getKey()).toString(), ((AttributeValue) entry.getValue()).toString());
            }
        }
        return attributes;
    }

    static class TagName extends TypedValue {
        TagName(String str) {
            super(str);
        }

        static TagName a(String str) {
            return new TagName(str);
        }
    }

    static class AttributeKey extends TypedValue {
        AttributeKey(String str) {
            super(str);
        }

        static AttributeKey a(String str) {
            return new AttributeKey(str);
        }
    }

    static class AttributeValue extends TypedValue {
        AttributeValue(String str) {
            super(str);
        }

        static AttributeValue a(String str) {
            return new AttributeValue(str);
        }
    }

    static class Protocol extends TypedValue {
        Protocol(String str) {
            super(str);
        }

        static Protocol a(String str) {
            return new Protocol(str);
        }
    }

    static abstract class TypedValue {

        /* renamed from: a  reason: collision with root package name */
        private String f3692a;

        TypedValue(String str) {
            Validate.a((Object) str);
            this.f3692a = str;
        }

        public int hashCode() {
            return 31 + (this.f3692a == null ? 0 : this.f3692a.hashCode());
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            TypedValue typedValue = (TypedValue) obj;
            if (this.f3692a == null) {
                if (typedValue.f3692a != null) {
                    return false;
                }
            } else if (!this.f3692a.equals(typedValue.f3692a)) {
                return false;
            }
            return true;
        }

        public String toString() {
            return this.f3692a;
        }
    }
}
