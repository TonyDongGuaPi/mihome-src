package cn.fraudmetrix.octopus.aspirit.g;

import cn.fraudmetrix.octopus.aspirit.g.e;
import cn.fraudmetrix.octopus.aspirit.g.f;
import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class a implements e {

    /* renamed from: a  reason: collision with root package name */
    protected String f642a;
    protected String b;
    protected String c;
    protected String d;
    protected int e;
    protected String f;
    protected Date g = new Date();
    protected List<String> h = new ArrayList();
    private f.a i = f.a.None;
    private int j;
    private e.a k = null;

    public a(String str, String str2, String str3, String str4, int i2) {
        this.f642a = str2;
        this.c = str4;
        this.d = str3;
        this.e = i2;
        this.f = str;
        this.j = 0;
    }

    public f.a a(String str, int i2) {
        return this.i;
    }

    public String a() {
        return this.f;
    }

    public void a(e.a aVar) {
        this.k = aVar;
    }

    /* access modifiers changed from: protected */
    public void a(f.a aVar) {
        if (this.i != aVar) {
            this.i = aVar;
            if (this.k != null) {
                this.k.a(this);
            }
        }
    }

    public void a(String str) {
        this.f642a = str;
    }

    public void a(String str, String str2) {
        this.h.add(str2);
        this.b = str;
    }

    public String b() {
        return this.f642a;
    }

    public void b(String str) {
        this.d = str;
    }

    public String c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return (this.h == null || this.h.size() == 0) ? "" : this.h.size() == 1 ? this.h.get(0) : JSON.toJSONString(this.h);
    }

    public void f() {
        this.g = new Date();
        a(f.a.LoadUrl);
        this.j++;
    }

    public void g() {
        a(f.a.Complete);
    }

    public String h() {
        return this.b;
    }

    public boolean i() {
        return this.j >= 3;
    }

    public f.a j() {
        return this.i;
    }
}
