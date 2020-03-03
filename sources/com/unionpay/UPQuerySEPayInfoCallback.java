package com.unionpay;

import android.os.Bundle;

public interface UPQuerySEPayInfoCallback {
    void onError(String str, String str2, String str3, String str4);

    void onResult(String str, String str2, int i, Bundle bundle);
}
