package com.xiaomi.jr.common.lifecycle;

import android.app.Activity;
import android.os.Bundle;

public interface Interceptor {

    public enum Stage {
        CREATE,
        START,
        RESUME,
        PAUSE,
        STOP,
        SAVE,
        DESTROY
    }

    boolean process(Activity activity, Bundle bundle);
}
