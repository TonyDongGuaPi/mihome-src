package com.miui.tsmclient.model;

import android.content.Context;
import android.os.Bundle;
import com.miui.tsmclient.common.mvp.IModel;
import com.miui.tsmclient.common.mvp.OnModelChangedListener;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BaseModel implements IModel {
    public static final int DATA_ID_ERROR = Integer.MAX_VALUE;
    private Context mContext;
    private ExecutorService mExecutor = Executors.newCachedThreadPool();
    private OnModelChangedListener mListener;

    /* access modifiers changed from: protected */
    public void onInit() {
    }

    /* access modifiers changed from: protected */
    public void onRelease() {
    }

    public static <T extends BaseModel> T create(Context context, Class<T> cls) {
        try {
            T t = (BaseModel) cls.newInstance();
            t.init(context, (OnModelChangedListener) null);
            return t;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public void init(Context context, OnModelChangedListener onModelChangedListener) {
        this.mContext = context;
        this.mListener = onModelChangedListener;
        onInit();
    }

    public void release() {
        onRelease();
        this.mExecutor.shutdownNow();
    }

    public Context getContext() {
        return this.mContext;
    }

    /* access modifiers changed from: protected */
    public void runOnBackground(Runnable runnable) {
        this.mExecutor.submit(runnable);
    }

    /* access modifiers changed from: protected */
    public final void notifyChanged(int i) {
        notifyChanged(i, (Bundle) null);
    }

    /* access modifiers changed from: protected */
    public final void notifyChanged(int i, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (this.mListener != null) {
            this.mListener.onModelChanged(i, bundle);
        }
    }
}
