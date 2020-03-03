package miuipub.hybrid;

import com.miuipub.internal.hybrid.HybridManager;

public class Callback {

    /* renamed from: a  reason: collision with root package name */
    private HybridManager f2933a;
    private PageContext b;
    private String c;

    public Callback(HybridManager hybridManager, PageContext pageContext, String str) {
        this.f2933a = hybridManager;
        this.b = pageContext;
        this.c = str;
    }

    public void a(Response response) {
        this.f2933a.a(response, this.b, this.c);
    }
}
