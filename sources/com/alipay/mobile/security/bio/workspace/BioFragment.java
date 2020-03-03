package com.alipay.mobile.security.bio.workspace;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.alipay.mobile.security.bio.exception.BioObjectNotInitialException;
import com.alipay.mobile.security.bio.service.BioServiceManager;
import com.alipay.mobile.security.bio.utils.BioLog;

public class BioFragment extends Fragment {
    protected BioFragmentCallBack mBioFragmentCallBack;
    protected BioServiceManager mBioServiceManager;

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mBioServiceManager = BioServiceManager.getCurrentInstance();
        if (this.mBioServiceManager == null) {
            BioLog.w((Throwable) new RuntimeException("null == BioServiceManager.getCurrentInstance()"));
            ((BioFragmentContainer) activity).sendResponse(206);
            finish();
            return;
        }
        try {
            this.mBioFragmentCallBack = (BioFragmentCallBack) activity;
        } catch (ClassCastException unused) {
            throw new ClassCastException(activity.toString() + " must BioFragmentService");
        }
    }

    /* access modifiers changed from: protected */
    public <T> T getSystemService(Class<T> cls) {
        if (this.mBioServiceManager != null) {
            return this.mBioServiceManager.getBioService(cls);
        }
        throw new BioObjectNotInitialException("");
    }

    /* access modifiers changed from: protected */
    public <T> T getExtService(Class<T> cls) {
        if (this.mBioServiceManager != null) {
            return this.mBioServiceManager.getBioService(cls);
        }
        throw new BioObjectNotInitialException("");
    }

    public void backward() {
        if (this.mBioFragmentCallBack != null) {
            this.mBioFragmentCallBack.backward((Bundle) null);
        }
    }

    public void forward(BioFragment bioFragment) {
        if (this.mBioFragmentCallBack != null) {
            this.mBioFragmentCallBack.forward((Bundle) null, bioFragment);
        }
    }

    /* access modifiers changed from: protected */
    public void finish() {
        if (this.mBioFragmentCallBack != null) {
            this.mBioFragmentCallBack.finish((Bundle) null);
        }
    }
}
