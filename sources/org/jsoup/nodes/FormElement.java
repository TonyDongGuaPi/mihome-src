package org.jsoup.nodes;

import com.taobao.weex.common.Constants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.helper.Validate;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

public class FormElement extends Element {
    private final Elements b = new Elements();

    public FormElement(Tag tag, String str, Attributes attributes) {
        super(tag, str, attributes);
    }

    public Elements b() {
        return this.b;
    }

    public FormElement c(Element element) {
        this.b.add(element);
        return this;
    }

    /* access modifiers changed from: protected */
    public void j(Node node) {
        super.j(node);
        this.b.remove(node);
    }

    public Connection e() {
        String a2 = c("action") ? a("action") : d();
        Validate.a(a2, "Could not determine a form action URL for submit. Ensure you set a base URI when parsing.");
        return Jsoup.b(a2).a((Collection<Connection.KeyVal>) f()).a(d("method").toUpperCase().equals("POST") ? Connection.Method.POST : Connection.Method.GET);
    }

    public List<Connection.KeyVal> f() {
        Element first;
        ArrayList arrayList = new ArrayList();
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            Element element = (Element) it.next();
            if (element.u().l() && !element.c(Constants.Name.DISABLED)) {
                String d = element.d("name");
                if (d.length() != 0) {
                    String d2 = element.d("type");
                    if ("select".equals(element.t())) {
                        boolean z = false;
                        Iterator it2 = element.k("option[selected]").iterator();
                        while (it2.hasNext()) {
                            arrayList.add(HttpConnection.KeyVal.a(d, ((Element) it2.next()).U()));
                            z = true;
                        }
                        if (!z && (first = element.k("option").first()) != null) {
                            arrayList.add(HttpConnection.KeyVal.a(d, first.U()));
                        }
                    } else if (!"checkbox".equalsIgnoreCase(d2) && !"radio".equalsIgnoreCase(d2)) {
                        arrayList.add(HttpConnection.KeyVal.a(d, element.U()));
                    } else if (element.c("checked")) {
                        arrayList.add(HttpConnection.KeyVal.a(d, element.U().length() > 0 ? element.U() : "on"));
                    }
                }
            }
        }
        return arrayList;
    }
}
