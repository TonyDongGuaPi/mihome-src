package com.xiaomiyoupin.toast;

import android.content.Context;

public interface YPDToastInterceptor {
    Object dismiss(Object obj);

    Object makeToast(Context context, String str, int i, long j);

    Object makeToast(Context context, String str, long j, boolean z);
}
