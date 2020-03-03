package com.xiaomiyoupin.toast;

public interface IToast {
    void dismiss();

    void makeToast(String str);

    void makeToast(String str, double d);

    void makeToast(String str, int i, double d);

    void makeToastActivityIndicator(String str, boolean z);
}
