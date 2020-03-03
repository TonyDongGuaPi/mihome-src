package com.mobikwik.sdk.ui.frag;

import android.view.View;
import android.widget.AdapterView;

class e implements AdapterView.OnItemClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ d f8395a;

    e(d dVar) {
        this.f8395a = dVar;
    }

    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
        this.f8395a.a(this.f8395a.c.getIntent(), false, i);
    }
}
