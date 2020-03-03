package com.xiaomi.miot.store.api;

import android.app.Activity;
import android.content.Intent;

public interface IShareProvider {
    String name();

    boolean onActivityResult(int i, int i2, Intent intent);

    void share(Activity activity, String str, ICallback iCallback);
}
