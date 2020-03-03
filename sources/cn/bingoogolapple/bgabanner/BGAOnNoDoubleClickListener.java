package cn.bingoogolapple.bgabanner;

import android.view.View;

public abstract class BGAOnNoDoubleClickListener implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private int f539a = 1000;
    private long b = 0;

    public abstract void a(View view);

    public BGAOnNoDoubleClickListener() {
    }

    public BGAOnNoDoubleClickListener(int i) {
        this.f539a = i;
    }

    public void onClick(View view) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.b > ((long) this.f539a)) {
            this.b = currentTimeMillis;
            a(view);
        }
    }
}
