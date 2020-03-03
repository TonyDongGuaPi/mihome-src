package com.xiaomiyoupin.toast;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import com.xiaomiyoupin.toast.dialog.IndicatorDialog;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

class YPDToastManager implements IndicatorDialog.OnIndicatorDialogDismissListener {
    private static final int MSG_DISMISS = 10004;
    private static final int MSG_ENQ_TOAST = 10000;
    private static final int MSG_HIDE_TOAST = 10002;
    private static final int MSG_ON_DISMISS = 10003;
    private static final int MSG_SHOW_TOAST = 10001;
    private ToastData mCurrentToastData;
    private Handler mHandler;
    private IndicatorDialog mIndicatorDialog;
    private List<ToastData> mToastArray;

    private static class InstanceHolder {
        /* access modifiers changed from: private */
        public static final YPDToastManager instance = new YPDToastManager();

        private InstanceHolder() {
        }
    }

    private YPDToastManager() {
        this.mHandler = new ToastHandler(Looper.getMainLooper());
        this.mToastArray = new ArrayList();
    }

    static YPDToastManager getInstance() {
        return InstanceHolder.instance;
    }

    private static class ToastHandler extends Handler {
        ToastHandler(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            if (message.what == 10000) {
                if (message.obj instanceof ToastData) {
                    YPDToastManager.getInstance().enqToast((ToastData) message.obj);
                }
            } else if (message.what == 10001) {
                YPDToastManager.getInstance().showToast();
            } else if (message.what == 10002) {
                if (message.obj instanceof ToastData) {
                    YPDToastManager.getInstance().hideToast((ToastData) message.obj);
                }
            } else if (message.what == 10003) {
                if (message.obj instanceof IndicatorDialog) {
                    YPDToastManager.getInstance().handleOnDismiss((IndicatorDialog) message.obj);
                }
            } else if (message.what != 10004) {
                super.handleMessage(message);
            } else if (message.obj != null && ((Integer) message.obj).intValue() > 0) {
                YPDToastManager.getInstance().handleDismiss(((Integer) message.obj).intValue());
            }
        }
    }

    private static class ToastData {
        private static int idIndex;
        WeakReference<Context> contextRef;
        int iconType;
        boolean isCancelable;
        final int mId;
        String title;
        int titleRes = 0;
        int type;

        ToastData(Context context, String str, int i, int i2, boolean z) {
            this.contextRef = new WeakReference<>(context);
            this.type = i;
            this.iconType = i2;
            this.title = str;
            int i3 = idIndex + 1;
            idIndex = i3;
            this.mId = i3;
            this.isCancelable = z;
        }

        /* access modifiers changed from: package-private */
        public boolean isEqual(ToastData toastData) {
            return toastData != null && this.type == toastData.type && this.iconType == toastData.iconType && this.titleRes == toastData.titleRes && TextUtils.equals(this.title, toastData.title);
        }
    }

    public int toast(Context context, int i) {
        return toast(context, context.getString(i));
    }

    public int toast(Context context, String str) {
        return toast(context, str, 5, true);
    }

    public int toast(Context context, String str, int i, boolean z) {
        int i2;
        if (!TextUtils.isEmpty(str)) {
            if (TextUtils.indexOf(str, string(R.string.ypd_toast_mark_success)) != -1) {
                i2 = 2;
            } else if (!(TextUtils.indexOf(str, string(R.string.ypd_toast_mark_failure)) == -1 && TextUtils.indexOf(str, string(R.string.ypd_toast_mark_error)) == -1)) {
                i2 = 3;
            }
            return toast(context, str, i, z, i2);
        }
        i2 = 1;
        return toast(context, str, i, z, i2);
    }

    public int toast(Context context, String str, int i, boolean z, int i2) {
        ToastData toastData = new ToastData(context, str, i, i2, z);
        this.mHandler.obtainMessage(10000, toastData).sendToTarget();
        return toastData.mId;
    }

    public void dismiss(int i) {
        this.mHandler.obtainMessage(10004, Integer.valueOf(i)).sendToTarget();
    }

    /* access modifiers changed from: private */
    public void handleDismiss(int i) {
        if (this.mCurrentToastData != null && this.mCurrentToastData.mId == i) {
            this.mHandler.removeMessages(10002);
            this.mHandler.obtainMessage(10002, this.mCurrentToastData).sendToTarget();
        } else if (!this.mToastArray.isEmpty()) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.mToastArray.size()) {
                    i2 = -1;
                    break;
                } else if (this.mToastArray.get(i2).mId == i) {
                    break;
                } else {
                    i2++;
                }
            }
            if (i2 != -1) {
                this.mToastArray.remove(i2);
            }
        }
    }

    /* access modifiers changed from: private */
    public void enqToast(ToastData toastData) {
        if (toastData != null) {
            this.mToastArray.add(toastData);
            this.mHandler.obtainMessage(10001).sendToTarget();
        }
    }

    private ToastData tryGetNext() {
        if (this.mToastArray.isEmpty()) {
            return null;
        }
        return this.mToastArray.get(0);
    }

    private ToastData getNext() {
        ToastData toastData = null;
        while (!this.mToastArray.isEmpty()) {
            toastData = this.mToastArray.get(0);
            this.mToastArray.remove(0);
            if (toastData.contextRef.get() != null) {
                break;
            }
        }
        if (toastData != null) {
            while (!this.mToastArray.isEmpty() && this.mToastArray.get(0).isEqual(toastData)) {
                this.mToastArray.remove(0);
            }
        }
        return toastData;
    }

    /* access modifiers changed from: private */
    public void showToast() {
        ToastData tryGetNext;
        try {
            if (!this.mToastArray.isEmpty() && (tryGetNext = tryGetNext()) != null) {
                if (this.mCurrentToastData != null) {
                    if (!this.mCurrentToastData.isEqual(tryGetNext)) {
                        return;
                    }
                    if (this.mIndicatorDialog != null) {
                        this.mIndicatorDialog.dismissDialog();
                    }
                }
                ToastData next = getNext();
                this.mCurrentToastData = next;
                this.mIndicatorDialog = new IndicatorDialog((Context) next.contextRef.get()).setDialogTitle(next.title).setDialogType(next.type).setIconType(next.iconType).setDismissListener(this);
                this.mIndicatorDialog.setCancelable(next.isCancelable);
                if (next.titleRes != 0) {
                    this.mIndicatorDialog.setDialogTitle(next.titleRes);
                }
                this.mIndicatorDialog.showDialog();
                if (next.type != 1 && next.type != 2 && next.type != 6) {
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(10002, next), 2500);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            onDismiss(this.mIndicatorDialog);
        }
    }

    public void hideToast(ToastData toastData) {
        if (toastData == this.mCurrentToastData) {
            if (this.mIndicatorDialog != null) {
                this.mIndicatorDialog.dismissDialog();
            }
            this.mIndicatorDialog = null;
            this.mCurrentToastData = null;
        }
        this.mHandler.obtainMessage(10001).sendToTarget();
    }

    public void onDismiss(IndicatorDialog indicatorDialog) {
        this.mHandler.obtainMessage(10003, indicatorDialog).sendToTarget();
    }

    /* access modifiers changed from: private */
    public void handleOnDismiss(IndicatorDialog indicatorDialog) {
        if (this.mIndicatorDialog == indicatorDialog || (this.mIndicatorDialog == null && this.mCurrentToastData != null)) {
            this.mIndicatorDialog = null;
            this.mCurrentToastData = null;
            this.mHandler.removeMessages(10002);
            this.mHandler.obtainMessage(10001).sendToTarget();
        }
    }

    private String string(@StringRes int i) {
        Context applicationContext = YPDToast.getInstance().getApplicationContext();
        if (applicationContext == null) {
            return "";
        }
        return applicationContext.getString(i);
    }
}
