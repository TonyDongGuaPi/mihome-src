package com.unionpay;

import android.os.Bundle;

final class c implements UPQuerySEPayInfoCallback {
    c() {
    }

    public final void onError(String str, String str2, String str3, String str4) {
        String unused = UPPayAssistEx.A = str2;
        int unused2 = UPPayAssistEx.s();
    }

    public final void onResult(String str, String str2, int i, Bundle bundle) {
        String unused = UPPayAssistEx.A = str2;
        int unused2 = UPPayAssistEx.s();
    }
}
