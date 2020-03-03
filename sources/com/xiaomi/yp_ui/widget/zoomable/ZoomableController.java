package com.xiaomi.yp_ui.widget.zoomable;

import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.MotionEvent;

public interface ZoomableController {

    public interface Listener {
        void a(Matrix matrix);
    }

    int A();

    int B();

    void a(RectF rectF);

    void a(Listener listener);

    boolean a(MotionEvent motionEvent);

    void b(RectF rectF);

    void b(boolean z);

    boolean b();

    boolean k();

    float q();

    boolean t();

    Matrix u();

    int w();

    int x();

    int y();

    int z();
}
