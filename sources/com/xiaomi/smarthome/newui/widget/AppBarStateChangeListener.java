package com.xiaomi.smarthome.newui.widget;

import android.support.design.widget.AppBarLayout;

public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {

    /* renamed from: a  reason: collision with root package name */
    private State f20822a = State.IDLE;

    public enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    public abstract void a(AppBarLayout appBarLayout, State state);

    public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
            if (this.f20822a != State.EXPANDED) {
                a(appBarLayout, State.EXPANDED);
                this.f20822a = State.EXPANDED;
            }
        } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
            if (this.f20822a != State.COLLAPSED) {
                a(appBarLayout, State.COLLAPSED);
                this.f20822a = State.COLLAPSED;
            }
        } else if (this.f20822a != State.IDLE) {
            a(appBarLayout, State.IDLE);
            this.f20822a = State.IDLE;
        }
    }
}
