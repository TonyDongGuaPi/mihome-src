package com.xiaomi.passport.ui.settings;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.settings.SimpleDialogFragment;
import com.xiaomi.passport.utils.AsyncTestMarker;

public class SimpleAsyncTask<T> extends AsyncTask<Void, Void, T> {
    private boolean dialogCancelable;
    private final String dialogMessage;
    private final DoInBackgroundRunnable<T> doInBackgroundRunnable;
    private final FragmentManager fragmentManager;
    private SimpleDialogFragment mProgressDialog;
    private final OnPostExecuteRunnable<T> onPostExecuteRunnable;

    public interface DoInBackgroundRunnable<T> {
        T run();
    }

    public interface OnPostExecuteRunnable<T> {
        void run(T t);
    }

    private SimpleAsyncTask(Builder<T> builder) {
        this.fragmentManager = builder.fragmentManager;
        this.dialogMessage = builder.dialogMessage;
        this.dialogCancelable = builder.dialogCancelable;
        this.doInBackgroundRunnable = builder.doInBackgroundRunnable;
        this.onPostExecuteRunnable = builder.onPostExecuteRunnable;
    }

    /* access modifiers changed from: protected */
    public T doInBackground(Void... voidArr) {
        if (this.doInBackgroundRunnable != null) {
            return this.doInBackgroundRunnable.run();
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public void onPreExecute() {
        super.onPreExecute();
        showProgressDialog();
        AsyncTestMarker.increment();
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(T t) {
        dismissProgressDialog();
        super.onPostExecute(t);
        if (this.onPostExecuteRunnable != null) {
            this.onPostExecuteRunnable.run(t);
        }
        AsyncTestMarker.decrement();
    }

    /* access modifiers changed from: protected */
    public void onCancelled(T t) {
        super.onCancelled(t);
        dismissProgressDialog();
        AsyncTestMarker.decrement();
    }

    public boolean isRunning() {
        return getStatus() != AsyncTask.Status.FINISHED;
    }

    private void showProgressDialog() {
        if (this.fragmentManager != null && !TextUtils.isEmpty(this.dialogMessage)) {
            this.mProgressDialog = new SimpleDialogFragment.AlertDialogFragmentBuilder(2).setMessage(this.dialogMessage).create();
            this.mProgressDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                public void onDismiss(DialogInterface dialogInterface) {
                    SimpleAsyncTask.this.cancel(true);
                }
            });
            this.mProgressDialog.setCancelable(this.dialogCancelable);
            this.mProgressDialog.showAllowingStateLoss(this.fragmentManager, "SimpleAsyncTask");
        }
    }

    private void dismissProgressDialog() {
        if (this.mProgressDialog != null && this.mProgressDialog.getActivity() != null && !this.mProgressDialog.getActivity().isFinishing()) {
            this.mProgressDialog.dismissAllowingStateLoss();
            this.mProgressDialog = null;
        }
    }

    public static class Builder<T> {
        /* access modifiers changed from: private */
        public boolean dialogCancelable = true;
        /* access modifiers changed from: private */
        public String dialogMessage;
        /* access modifiers changed from: private */
        public DoInBackgroundRunnable<T> doInBackgroundRunnable;
        /* access modifiers changed from: private */
        public FragmentManager fragmentManager;
        /* access modifiers changed from: private */
        public OnPostExecuteRunnable<T> onPostExecuteRunnable;

        public Builder<T> setProgressDialogMessage(FragmentManager fragmentManager2, String str) {
            this.fragmentManager = fragmentManager2;
            this.dialogMessage = str;
            return this;
        }

        public Builder<T> setProgressDialogCancelable(boolean z) {
            this.dialogCancelable = z;
            return this;
        }

        public Builder<T> setDoInBackgroundRunnable(DoInBackgroundRunnable<T> doInBackgroundRunnable2) {
            this.doInBackgroundRunnable = doInBackgroundRunnable2;
            return this;
        }

        public Builder<T> setOnPostExecuteRunnable(OnPostExecuteRunnable<T> onPostExecuteRunnable2) {
            this.onPostExecuteRunnable = onPostExecuteRunnable2;
            return this;
        }

        public SimpleAsyncTask<T> build() {
            return new SimpleAsyncTask<>(this);
        }
    }

    public enum ResultType {
        SUCCESS {
            public int getErrorMessageResId() {
                return 0;
            }
        },
        ERROR_PASSWORD {
            public int getErrorMessageResId() {
                return R.string.passport_bad_authentication;
            }
        },
        ERROR_AUTH_FAIL {
            public int getErrorMessageResId() {
                return R.string.auth_fail_warning;
            }
        },
        ERROR_NETWORK {
            public int getErrorMessageResId() {
                return R.string.passport_error_network;
            }
        },
        ERROR_SERVER {
            public int getErrorMessageResId() {
                return R.string.passport_error_server;
            }
        },
        ERROR_ACCESS_DENIED {
            public int getErrorMessageResId() {
                return R.string.passport_access_denied;
            }
        },
        ERROR_CAPTCHA {
            public int getErrorMessageResId() {
                return R.string.passport_wrong_captcha;
            }
        },
        ERROR_DEVICE_ID {
            public int getErrorMessageResId() {
                return R.string.passport_error_device_id;
            }
        },
        ERROR_VERIFY_CODE {
            public int getErrorMessageResId() {
                return R.string.passport_wrong_vcode;
            }
        },
        ERROR_USER_ACTION_RESTRICTED {
            public int getErrorMessageResId() {
                return R.string.service_error;
            }
        },
        ERROR_GET_PHONE_VERIFY_CODE_OVER_LIMIT {
            public int getErrorMessageResId() {
                return R.string.get_phone_verifycode_exceed_limit;
            }
        },
        ERROR_SAME_NEW_AND_OLD_PASS {
            public int getErrorMessageResId() {
                return R.string.same_new_and_old_pwd;
            }
        },
        ERROR_UNKNOWN {
            public int getErrorMessageResId() {
                return R.string.passport_error_unknown;
            }
        };

        public abstract int getErrorMessageResId();

        public boolean success() {
            return this == SUCCESS;
        }
    }
}
