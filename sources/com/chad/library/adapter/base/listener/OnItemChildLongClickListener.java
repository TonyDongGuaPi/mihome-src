package com.chad.library.adapter.base.listener;

import android.view.View;
import com.chad.library.adapter.base.BaseQuickAdapter;

public abstract class OnItemChildLongClickListener extends SimpleClickListener {
    public void a(BaseQuickAdapter baseQuickAdapter, View view, int i) {
    }

    public void b(BaseQuickAdapter baseQuickAdapter, View view, int i) {
    }

    public void c(BaseQuickAdapter baseQuickAdapter, View view, int i) {
    }

    public abstract void e(BaseQuickAdapter baseQuickAdapter, View view, int i);

    public void d(BaseQuickAdapter baseQuickAdapter, View view, int i) {
        e(baseQuickAdapter, view, i);
    }
}
