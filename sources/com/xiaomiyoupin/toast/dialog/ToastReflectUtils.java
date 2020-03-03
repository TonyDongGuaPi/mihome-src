package com.xiaomiyoupin.toast.dialog;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;
import java.lang.reflect.Field;

public class ToastReflectUtils {
    private static Toast mToast;
    private static Field sField_TN;
    private static Field sField_TN_Handler;

    static {
        try {
            sField_TN = Toast.class.getDeclaredField("mTN");
            sField_TN.setAccessible(true);
            sField_TN_Handler = sField_TN.getType().getDeclaredField("mHandler");
            sField_TN_Handler.setAccessible(true);
        } catch (Exception unused) {
        }
    }

    private static void hook(Toast toast) {
        try {
            Object obj = sField_TN.get(toast);
            sField_TN_Handler.set(obj, new SafelyHandlerWarpper((Handler) sField_TN_Handler.get(obj)));
        } catch (Exception unused) {
        }
    }

    public static void showToast(Context context, CharSequence charSequence, int i) {
        if (mToast == null) {
            mToast = Toast.makeText(context, charSequence, i);
        } else {
            mToast.setText(charSequence);
        }
        hook(mToast);
        mToast.show();
    }

    public static void showCustomToast(Toast toast) {
        if (toast != null) {
            mToast = toast;
            hook(mToast);
            mToast.show();
        }
    }

    public static class SafelyHandlerWarpper extends Handler {
        private Handler impl;

        public SafelyHandlerWarpper(Handler handler) {
            this.impl = handler;
        }

        public void dispatchMessage(Message message) {
            try {
                super.dispatchMessage(message);
            } catch (Exception unused) {
            }
        }

        public void handleMessage(Message message) {
            this.impl.handleMessage(message);
        }
    }
}
