package org.jsoup.nodes;

import java.io.IOException;
import kotlin.text.Typography;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;

public class DocumentType extends LeafNode {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3664a = "PUBLIC";
    public static final String b = "SYSTEM";
    private static final String g = "name";
    private static final String h = "pubSysKey";
    private static final String i = "publicId";
    private static final String j = "systemId";

    public String a() {
        return "#doctype";
    }

    /* access modifiers changed from: package-private */
    public void b(Appendable appendable, int i2, Document.OutputSettings outputSettings) {
    }

    public /* bridge */ /* synthetic */ String a(String str) {
        return super.a(str);
    }

    public /* bridge */ /* synthetic */ Node a(String str, String str2) {
        return super.a(str, str2);
    }

    public /* bridge */ /* synthetic */ Node b(String str) {
        return super.b(str);
    }

    public /* bridge */ /* synthetic */ int c() {
        return super.c();
    }

    public /* bridge */ /* synthetic */ boolean c(String str) {
        return super.c(str);
    }

    public /* bridge */ /* synthetic */ String d() {
        return super.d();
    }

    public /* bridge */ /* synthetic */ String d(String str) {
        return super.d(str);
    }

    public DocumentType(String str, String str2, String str3) {
        Validate.a((Object) str);
        Validate.a((Object) str2);
        Validate.a((Object) str3);
        a("name", str);
        a(i, str2);
        if (g(i)) {
            a(h, f3664a);
        }
        a(j, str3);
    }

    public DocumentType(String str, String str2, String str3, String str4) {
        a("name", str);
        a(i, str2);
        if (g(i)) {
            a(h, f3664a);
        }
        a(j, str3);
    }

    public DocumentType(String str, String str2, String str3, String str4, String str5) {
        a("name", str);
        if (str2 != null) {
            a(h, str2);
        }
        a(i, str3);
        a(j, str4);
    }

    public void e(String str) {
        if (str != null) {
            a(h, str);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Appendable appendable, int i2, Document.OutputSettings outputSettings) throws IOException {
        if (outputSettings.e() != Document.OutputSettings.Syntax.html || g(i) || g(j)) {
            appendable.append("<!DOCTYPE");
        } else {
            appendable.append("<!doctype");
        }
        if (g("name")) {
            appendable.append(" ").append(d("name"));
        }
        if (g(h)) {
            appendable.append(" ").append(d(h));
        }
        if (g(i)) {
            appendable.append(" \"").append(d(i)).append('\"');
        }
        if (g(j)) {
            appendable.append(" \"").append(d(j)).append('\"');
        }
        appendable.append(Typography.e);
    }

    private boolean g(String str) {
        return !StringUtil.a(d(str));
    }
}
