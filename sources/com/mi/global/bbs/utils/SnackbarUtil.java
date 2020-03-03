package com.mi.global.bbs.utils;

import android.support.design.widget.Snackbar;
import android.view.View;
import com.mi.global.bbs.R;

public class SnackbarUtil {
    /* access modifiers changed from: private */
    public static Snackbar mSnackbar;

    public static void show(View view, String str, int i) {
        if (i == 0) {
            mSnackbar = Snackbar.make(view, (CharSequence) str, -1);
        } else {
            mSnackbar = Snackbar.make(view, (CharSequence) str, 0);
        }
        mSnackbar.show();
        mSnackbar.setAction(R.string.close, (View.OnClickListener) new View.OnClickListener() {
            public void onClick(View view) {
                SnackbarUtil.mSnackbar.dismiss();
            }
        });
    }
}
