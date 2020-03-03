package com.xiaomi.infra.galaxy.fds.result;

import com.xiaomi.infra.galaxy.fds.bean.ObjectBean;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ListObjectsResult {

    /* renamed from: a  reason: collision with root package name */
    private String f1372a;
    private String b;
    private int c;
    private String d;
    private boolean e;
    private String f;
    private List<ObjectBean> g;
    private List<String> h;
    private String i;

    public String a() {
        return this.b;
    }

    public void a(String str) {
        this.b = str;
    }

    public int b() {
        return this.c;
    }

    public void a(int i2) {
        this.c = i2;
    }

    public boolean c() {
        return this.e;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public List<ObjectBean> d() {
        return this.g;
    }

    public void a(List<ObjectBean> list) {
        this.g = list;
    }

    public List<String> e() {
        return this.h;
    }

    public void b(List<String> list) {
        this.h = list;
    }

    public String f() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }

    public String g() {
        return this.f;
    }

    public void c(String str) {
        this.f = str;
    }

    public String h() {
        return this.f1372a;
    }

    public void d(String str) {
        this.f1372a = str;
    }

    public void e(String str) {
        this.i = str;
    }

    public String i() {
        return this.i;
    }
}
