package com.mipay.sdk;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

public interface IMipay {
    void pay(Activity activity, String str, Bundle bundle);

    void pay(Fragment fragment, String str, Bundle bundle);
}
