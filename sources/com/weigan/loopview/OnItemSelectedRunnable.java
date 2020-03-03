package com.weigan.loopview;

final class OnItemSelectedRunnable implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final LoopView f9869a;

    OnItemSelectedRunnable(LoopView loopView) {
        this.f9869a = loopView;
    }

    public final void run() {
        this.f9869a.onItemSelectedListener.onItemSelected(this.f9869a.getSelectedItem());
    }
}
