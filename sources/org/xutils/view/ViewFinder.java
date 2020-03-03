package org.xutils.view;

import android.app.Activity;
import android.view.View;

final class ViewFinder {

    /* renamed from: a  reason: collision with root package name */
    private View f11943a;
    private Activity b;

    public ViewFinder(View view) {
        this.f11943a = view;
    }

    public ViewFinder(Activity activity) {
        this.b = activity;
    }

    public View a(int i) {
        if (this.f11943a != null) {
            return this.f11943a.findViewById(i);
        }
        if (this.b != null) {
            return this.b.findViewById(i);
        }
        return null;
    }

    public View a(ViewInfo viewInfo) {
        return a(viewInfo.f11944a, viewInfo.b);
    }

    public View a(int i, int i2) {
        View a2 = i2 > 0 ? a(i2) : null;
        if (a2 != null) {
            return a2.findViewById(i);
        }
        return a(i);
    }
}
