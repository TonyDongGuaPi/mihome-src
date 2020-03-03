package cn.tongdun.android.core.qgg9qgg9999g9g;

import android.content.Context;
import android.hardware.SensorManager;
import android.view.WindowManager;
import cn.tongdun.android.core.qgg9qgg9999g9g.gqg9qq9gqq9q9q.gqg9qq9gqq9q9q;

final class q99qgq99 implements gqg9qq9gqq9q9q {
    private final Context gqg9qq9gqq9q9q;
    private final WindowManager q9qq99qg9qqgqg9gqgg9;
    private final SensorManager qgg9qgg9999g9g;

    private q99qgq99(Context context, SensorManager sensorManager, WindowManager windowManager) {
        this.gqg9qq9gqq9q9q = context;
        this.qgg9qgg9999g9g = sensorManager;
        this.q9qq99qg9qqgqg9gqgg9 = windowManager;
    }

    static q99qgq99 gqg9qq9gqq9q9q(Context context, SensorManager sensorManager, WindowManager windowManager) {
        return new q99qgq99(context, sensorManager, windowManager);
    }

    public Object gqg9qq9gqq9q9q() {
        return gqq9qggqgg9g99.gqg9qq9gqq9q9q(this.gqg9qq9gqq9q9q, this.qgg9qgg9999g9g, this.q9qq99qg9qqgqg9gqgg9);
    }
}
