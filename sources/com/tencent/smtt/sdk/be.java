package com.tencent.smtt.sdk;

import com.tencent.tbs.video.interfaces.IUserStateChangedListener;

class be implements IUserStateChangedListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ bd f9158a;

    be(bd bdVar) {
        this.f9158a = bdVar;
    }

    public void onUserStateChanged() {
        this.f9158a.f9157a.c();
    }
}
