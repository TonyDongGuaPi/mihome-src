package com.xiaomi.passport.ui.settings;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Window;
import com.alipay.sdk.widget.j;
import com.xiaomi.accountsdk.utils.AccountLog;
import java.lang.reflect.InvocationTargetException;

public class SimpleDialogFragment extends DialogFragment {
    public static final String ARG_CANCELABLE = "cancelable";
    public static final String ARG_MESSAGE = "msg_res_id";
    public static final String ARG_TITLE = "title";
    public static final String ARG_TYPE = "type";
    private static final String TAG = "SimpleDialogFragment";
    public static final int TYPE_ALERT = 1;
    public static final int TYPE_PROGRESS = 2;
    private boolean mCancelable = true;
    private CharSequence mMessage;
    private DialogInterface.OnClickListener mNegativeButtonClickListener;
    private int mNegativeButtonTextRes;
    private DialogInterface.OnDismissListener mOnDismissListener;
    private DialogInterface.OnClickListener mPositiveButtonClickListener;
    private int mPositiveButtonTextRes;
    private String mTitle;
    private int mType;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mType = arguments.getInt("type");
            this.mMessage = arguments.getCharSequence("msg_res_id");
            this.mTitle = arguments.getString("title");
            this.mCancelable = arguments.getBoolean("cancelable", true);
            return;
        }
        throw new IllegalStateException("no argument");
    }

    public Dialog onCreateDialog(Bundle bundle) {
        switch (this.mType) {
            case 1:
                try {
                    if (isMiuiActivity()) {
                        Class<?> cls = Class.forName("miui.app.AlertDialog$Builder");
                        Object newInstance = cls.getConstructor(new Class[]{Context.class}).newInstance(new Object[]{getActivity()});
                        cls.getMethod(j.d, new Class[]{CharSequence.class}).invoke(newInstance, new Object[]{this.mTitle});
                        cls.getMethod("setMessage", new Class[]{CharSequence.class}).invoke(newInstance, new Object[]{this.mMessage});
                        cls.getMethod("setCancelable", new Class[]{Boolean.TYPE}).invoke(newInstance, new Object[]{Boolean.valueOf(this.mCancelable)});
                        if (this.mPositiveButtonTextRes > 0) {
                            cls.getMethod("setPositiveButton", new Class[]{Integer.TYPE, DialogInterface.OnClickListener.class}).invoke(newInstance, new Object[]{Integer.valueOf(this.mPositiveButtonTextRes), this.mPositiveButtonClickListener});
                        }
                        if (this.mNegativeButtonTextRes > 0) {
                            cls.getMethod("setNegativeButton", new Class[]{Integer.TYPE, DialogInterface.OnClickListener.class}).invoke(newInstance, new Object[]{Integer.valueOf(this.mNegativeButtonTextRes), this.mNegativeButtonClickListener});
                        }
                        return (Dialog) cls.getMethod("create", new Class[0]).invoke(newInstance, new Object[0]);
                    }
                } catch (ClassNotFoundException e) {
                    AccountLog.e(TAG, "AlertDialog reflect exception: ", e);
                } catch (InstantiationException e2) {
                    AccountLog.e(TAG, "AlertDialog reflect exception: ", e2);
                } catch (IllegalAccessException e3) {
                    AccountLog.e(TAG, "AlertDialog reflect exception: ", e3);
                } catch (IllegalArgumentException e4) {
                    AccountLog.e(TAG, "AlertDialog reflect exception: ", e4);
                } catch (InvocationTargetException e5) {
                    AccountLog.e(TAG, "AlertDialog reflect exception: ", e5);
                } catch (NoSuchMethodException e6) {
                    AccountLog.e(TAG, "AlertDialog reflect exception: ", e6);
                }
                AlertDialog.Builder title = new AlertDialog.Builder(getActivity()).setMessage(this.mMessage).setCancelable(this.mCancelable).setTitle((CharSequence) this.mTitle);
                if (this.mPositiveButtonTextRes > 0) {
                    title.setPositiveButton(this.mPositiveButtonTextRes, this.mPositiveButtonClickListener);
                }
                if (this.mNegativeButtonTextRes > 0) {
                    title.setNegativeButton(this.mNegativeButtonTextRes, this.mNegativeButtonClickListener);
                }
                return title.create();
            case 2:
                try {
                    if (isMiuiActivity()) {
                        Class<?> cls2 = Class.forName("miui.app.ProgressDialog");
                        Object newInstance2 = cls2.getConstructor(new Class[]{Context.class}).newInstance(new Object[]{getActivity()});
                        cls2.getMethod("setMessage", new Class[]{CharSequence.class}).invoke(newInstance2, new Object[]{this.mMessage});
                        cls2.getMethod("setCancelable", new Class[]{Boolean.TYPE}).invoke(newInstance2, new Object[]{Boolean.valueOf(this.mCancelable)});
                        ((Window) cls2.getMethod("getWindow", new Class[0]).invoke(newInstance2, new Object[0])).setGravity(80);
                        return (Dialog) newInstance2;
                    }
                } catch (ClassNotFoundException e7) {
                    AccountLog.e(TAG, "ProgressDialog reflect exception: ", e7);
                } catch (InstantiationException e8) {
                    AccountLog.e(TAG, "ProgressDialog reflect exception: ", e8);
                } catch (IllegalAccessException e9) {
                    AccountLog.e(TAG, "ProgressDialog reflect exception: ", e9);
                } catch (IllegalArgumentException e10) {
                    AccountLog.e(TAG, "ProgressDialog reflect exception: ", e10);
                } catch (InvocationTargetException e11) {
                    AccountLog.e(TAG, "ProgressDialog reflect exception: ", e11);
                } catch (NoSuchMethodException e12) {
                    AccountLog.e(TAG, "ProgressDialog reflect exception: ", e12);
                }
                ProgressDialog progressDialog = new ProgressDialog(getActivity());
                progressDialog.setMessage(this.mMessage);
                progressDialog.setCancelable(this.mCancelable);
                progressDialog.getWindow().setGravity(80);
                return progressDialog;
            default:
                throw new IllegalStateException("unknown dialog type:" + this.mType);
        }
    }

    private boolean isMiuiActivity() {
        try {
            return Class.forName("miui.app.Activity").isInstance(getActivity());
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    public void setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.mOnDismissListener = onDismissListener;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        if (this.mOnDismissListener != null) {
            this.mOnDismissListener.onDismiss(dialogInterface);
        }
    }

    public void setNegativeButton(int i, DialogInterface.OnClickListener onClickListener) {
        this.mNegativeButtonTextRes = i;
        this.mNegativeButtonClickListener = onClickListener;
    }

    public void setPositiveButton(int i, DialogInterface.OnClickListener onClickListener) {
        this.mPositiveButtonTextRes = i;
        this.mPositiveButtonClickListener = onClickListener;
    }

    public static final class AlertDialogFragmentBuilder {
        private boolean mCancelable = true;
        private boolean mCreated;
        private CharSequence mMessage;
        private String mTitle;
        private int mType;

        public AlertDialogFragmentBuilder(int i) {
            this.mType = i;
        }

        public AlertDialogFragmentBuilder setMessage(CharSequence charSequence) {
            this.mMessage = charSequence;
            return this;
        }

        public AlertDialogFragmentBuilder setCancelable(boolean z) {
            this.mCancelable = z;
            return this;
        }

        public AlertDialogFragmentBuilder setTitle(String str) {
            this.mTitle = str;
            return this;
        }

        public SimpleDialogFragment create() {
            if (!this.mCreated) {
                this.mCreated = true;
                SimpleDialogFragment simpleDialogFragment = new SimpleDialogFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title", this.mTitle);
                bundle.putCharSequence("msg_res_id", this.mMessage);
                bundle.putBoolean("cancelable", this.mCancelable);
                bundle.putInt("type", this.mType);
                simpleDialogFragment.setArguments(bundle);
                return simpleDialogFragment;
            }
            throw new IllegalStateException("dialog has been created");
        }
    }

    @Deprecated
    public void dismiss() {
        super.dismiss();
    }

    public void showAllowingStateLoss(FragmentManager fragmentManager, String str) {
        if (fragmentManager == null) {
            AccountLog.w(TAG, "invalid parameter");
            return;
        }
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        beginTransaction.add(this, str);
        beginTransaction.commitAllowingStateLoss();
    }
}
