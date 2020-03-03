package com.tencent.wxop.stat;

class ar implements h {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ aq f9298a;

    ar(aq aqVar) {
        this.f9298a = aqVar;
    }

    public void a() {
        StatServiceImpl.d();
        if (au.b().a() >= StatConfig.f()) {
            au.b().a(StatConfig.f());
        }
    }

    public void b() {
        StatServiceImpl.e();
    }
}
