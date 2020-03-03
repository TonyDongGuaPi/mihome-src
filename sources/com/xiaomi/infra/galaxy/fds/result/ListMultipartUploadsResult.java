package com.xiaomi.infra.galaxy.fds.result;

import com.xiaomi.infra.galaxy.fds.bean.MultipartUploadBean;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ListMultipartUploadsResult {

    /* renamed from: a  reason: collision with root package name */
    private String f1371a;
    private String b;
    private int c;
    private String d;
    private boolean e;
    private String f;
    private List<MultipartUploadBean> g;
    private List<String> h;
    private String i;

    public String a() {
        return this.f1371a;
    }

    public void a(String str) {
        this.f1371a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public int c() {
        return this.c;
    }

    public void a(int i2) {
        this.c = i2;
    }

    public String d() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public boolean e() {
        return this.e;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public String f() {
        return this.f;
    }

    public void d(String str) {
        this.f = str;
    }

    public List<MultipartUploadBean> g() {
        return this.g;
    }

    public void a(List<MultipartUploadBean> list) {
        this.g = list;
    }

    public List<String> h() {
        return this.h;
    }

    public void b(List<String> list) {
        this.h = list;
    }

    public String i() {
        return this.i;
    }

    public void e(String str) {
        this.i = str;
    }
}
