package com.mi.global.shop.activity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.shop.R;

public class PushManagerActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private PushManagerActivity f5425a;

    @UiThread
    public PushManagerActivity_ViewBinding(PushManagerActivity pushManagerActivity) {
        this(pushManagerActivity, pushManagerActivity.getWindow().getDecorView());
    }

    @UiThread
    public PushManagerActivity_ViewBinding(PushManagerActivity pushManagerActivity, View view) {
        this.f5425a = pushManagerActivity;
        pushManagerActivity.pushRecycleView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.push_recycleView, "field 'pushRecycleView'", RecyclerView.class);
    }

    @CallSuper
    public void unbind() {
        PushManagerActivity pushManagerActivity = this.f5425a;
        if (pushManagerActivity != null) {
            this.f5425a = null;
            pushManagerActivity.pushRecycleView = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
