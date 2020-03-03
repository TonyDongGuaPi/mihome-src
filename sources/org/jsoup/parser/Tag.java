package org.jsoup.parser;

import android.support.v4.app.NotificationCompat;
import com.alipay.mobile.security.bio.workspace.Env;
import com.alipay.sdk.cons.c;
import com.coloros.mcssdk.mode.CommandMessage;
import com.facebook.share.internal.ShareConstants;
import com.mi.global.shop.model.Tags;
import com.mi.mistatistic.sdk.data.EventData;
import com.taobao.weex.adapter.URIAdapter;
import com.taobao.weex.ui.component.WXBasicComponentType;
import com.unionpay.tsmservice.mi.data.Constant;
import com.xiaomi.jr.hybrid.WebEvent;
import com.xiaomi.miot.support.monitor.core.BaseInfo;
import com.xiaomi.payment.data.MibiConstants;
import com.xiaomi.smarthome.core.server.internal.plugin.PluginManager;
import com.xiaomi.stat.a.j;
import com.xiaomi.stat.d;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import java.util.HashMap;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.AgentOptions;
import org.jsoup.helper.Validate;

public class Tag {

    /* renamed from: a  reason: collision with root package name */
    private static final Map<String, Tag> f3682a = new HashMap();
    private static final String[] k = {"html", TtmlNode.TAG_HEAD, "body", "frameset", WebEvent.A, "noscript", "style", "meta", "link", "title", "frame", "noframes", "section", "nav", "aside", "hgroup", "header", WXBasicComponentType.FOOTER, "p", "h1", "h2", "h3", "h4", PluginManager.h, "h6", "ul", "ol", Env.NAME_PRE, "div", "blockquote", "hr", "address", "figure", "figcaption", c.c, "fieldset", "ins", "del", "dl", "dt", d.s, "li", "table", ShareConstants.FEED_CAPTION_PARAM, "thead", "tfoot", "tbody", "colgroup", Constant.KEY_COL, BaseInfo.KEY_TIME_RECORD, "th", "td", "video", "audio", "canvas", Tags.MiPhoneDetails.DETAILS, "menu", "plaintext", "template", "article", "main", "svg", "math"};
    private static final String[] l = {EventData.e, "base", URIAdapter.FONT, TtmlNode.TAG_TT, "i", "b", "u", "big", "small", "em", "strong", "dfn", "code", "samp", "kbd", "var", "cite", "abbr", "time", "acronym", "mark", "ruby", "rt", "rp", "a", "img", TtmlNode.TAG_BR, "wbr", "map", DTransferConstants.F, j.i, "sup", "bdo", "iframe", WXBasicComponentType.EMBED, "span", "input", "select", WXBasicComponentType.TEXTAREA, "label", "button", "optgroup", "option", "legend", "datalist", "keygen", AgentOptions.k, NotificationCompat.CATEGORY_PROGRESS, "meter", "area", "param", "source", "track", MibiConstants.ee, CommandMessage.COMMAND, "device", "area", "basefont", "bgsound", "menuitem", "param", "source", "track", "data", "bdi", "s"};
    private static final String[] m = {"meta", "link", "base", "frame", "img", TtmlNode.TAG_BR, "wbr", WXBasicComponentType.EMBED, "hr", "input", "keygen", Constant.KEY_COL, CommandMessage.COMMAND, "device", "area", "basefont", "bgsound", "menuitem", "param", "source", "track"};
    private static final String[] n = {"title", "a", "p", "h1", "h2", "h3", "h4", PluginManager.h, "h6", Env.NAME_PRE, "address", "li", "th", "td", WebEvent.A, "style", "ins", "del", "s"};
    private static final String[] o = {Env.NAME_PRE, "plaintext", "title", WXBasicComponentType.TEXTAREA};
    private static final String[] p = {"button", "fieldset", "input", "keygen", EventData.e, AgentOptions.k, "select", WXBasicComponentType.TEXTAREA};
    private static final String[] q = {"input", "keygen", EventData.e, "select", WXBasicComponentType.TEXTAREA};
    private String b;
    private boolean c = true;
    private boolean d = true;
    private boolean e = true;
    private boolean f = false;
    private boolean g = false;
    private boolean h = false;
    private boolean i = false;
    private boolean j = false;

    static {
        for (String tag : k) {
            a(new Tag(tag));
        }
        for (String tag2 : l) {
            Tag tag3 = new Tag(tag2);
            tag3.c = false;
            tag3.d = false;
            a(tag3);
        }
        for (String str : m) {
            Tag tag4 = f3682a.get(str);
            Validate.a((Object) tag4);
            tag4.e = false;
            tag4.f = true;
        }
        for (String str2 : n) {
            Tag tag5 = f3682a.get(str2);
            Validate.a((Object) tag5);
            tag5.d = false;
        }
        for (String str3 : o) {
            Tag tag6 = f3682a.get(str3);
            Validate.a((Object) tag6);
            tag6.h = true;
        }
        for (String str4 : p) {
            Tag tag7 = f3682a.get(str4);
            Validate.a((Object) tag7);
            tag7.i = true;
        }
        for (String str5 : q) {
            Tag tag8 = f3682a.get(str5);
            Validate.a((Object) tag8);
            tag8.j = true;
        }
    }

    private Tag(String str) {
        this.b = str;
    }

    public String a() {
        return this.b;
    }

    public static Tag a(String str, ParseSettings parseSettings) {
        Validate.a((Object) str);
        Tag tag = f3682a.get(str);
        if (tag != null) {
            return tag;
        }
        String a2 = parseSettings.a(str);
        Validate.a(a2);
        Tag tag2 = f3682a.get(a2);
        if (tag2 != null) {
            return tag2;
        }
        Tag tag3 = new Tag(a2);
        tag3.c = false;
        return tag3;
    }

    public static Tag valueOf(String str) {
        return a(str, ParseSettings.b);
    }

    public boolean b() {
        return this.c;
    }

    public boolean c() {
        return this.d;
    }

    public boolean d() {
        return this.c;
    }

    public boolean e() {
        return !this.c;
    }

    public boolean f() {
        return !this.e && !g();
    }

    public boolean g() {
        return this.f;
    }

    public boolean h() {
        return this.f || this.g;
    }

    public boolean i() {
        return f3682a.containsKey(this.b);
    }

    public static boolean a(String str) {
        return f3682a.containsKey(str);
    }

    public boolean j() {
        return this.h;
    }

    public boolean k() {
        return this.i;
    }

    public boolean l() {
        return this.j;
    }

    /* access modifiers changed from: package-private */
    public Tag m() {
        this.g = true;
        return this;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Tag)) {
            return false;
        }
        Tag tag = (Tag) obj;
        if (this.b.equals(tag.b) && this.e == tag.e && this.f == tag.f && this.d == tag.d && this.c == tag.c && this.h == tag.h && this.g == tag.g && this.i == tag.i && this.j == tag.j) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (((((((((((((((this.b.hashCode() * 31) + (this.c ? 1 : 0)) * 31) + (this.d ? 1 : 0)) * 31) + (this.e ? 1 : 0)) * 31) + (this.f ? 1 : 0)) * 31) + (this.g ? 1 : 0)) * 31) + (this.h ? 1 : 0)) * 31) + (this.i ? 1 : 0)) * 31) + (this.j ? 1 : 0);
    }

    public String toString() {
        return this.b;
    }

    private static void a(Tag tag) {
        f3682a.put(tag.b, tag);
    }
}
