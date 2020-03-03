package com.mijia.camera.nas;

import android.text.TextUtils;
import java.io.File;
import java.util.ArrayList;
import org.json.JSONObject;

public class NASNode {

    /* renamed from: a  reason: collision with root package name */
    private String f7945a;
    private String b;
    private NASNode c;
    private ArrayList<NASNode> d = new ArrayList<>();
    private NASServer e;

    public NASNode(NASNode nASNode, String str) {
        this.c = nASNode;
        this.e = nASNode.c();
        this.b = str;
        if (!TextUtils.isEmpty(nASNode.a())) {
            str = nASNode.a() + File.separator + str;
        }
        this.f7945a = str;
    }

    public NASNode(NASServer nASServer, String str) {
        String str2;
        this.e = nASServer;
        if (TextUtils.isEmpty(str)) {
            this.b = "";
        } else {
            this.b = str;
        }
        if (TextUtils.isEmpty(this.b)) {
            str2 = this.e.d();
        } else {
            str2 = this.e.d() + File.separator + this.b;
        }
        this.f7945a = str2;
    }

    public void a(String str) {
        this.f7945a = str;
    }

    public String a() {
        return this.f7945a;
    }

    public String b() {
        return this.b;
    }

    public NASServer c() {
        return this.e;
    }

    public void a(ArrayList<NASNode> arrayList) {
        this.d = arrayList;
    }

    public ArrayList<NASNode> d() {
        return this.d;
    }

    public JSONObject e() {
        this.e.c(a());
        return this.e.i();
    }
}
