package com.xiaomi.mishopsdk.io.http;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import com.mishopsdk.volley.Response;
import com.mishopsdk.volley.VolleyError;
import com.xiaomi.mishopsdk.R;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.account.lib.LoginManager;
import com.xiaomi.mishopsdk.fragment.BaseFragment;
import com.xiaomi.mishopsdk.io.entity.ShopApiBaseResult;
import com.xiaomi.mishopsdk.io.http.ShopApiError;
import com.xiaomi.mishopsdk.widget.LoadingView;
import java.lang.ref.SoftReference;

public abstract class BaseUIController<T> implements Response.Listener<T> {
    private static final boolean DEBUG = false;
    private static final String TAG = "BaseUIController";
    /* access modifiers changed from: private */
    public SoftReference<BaseFragment> mBaseFragment;
    protected SoftReference<LoadingView> mBaseLoadingView;
    private T mData;

    /* access modifiers changed from: protected */
    public abstract void onRefreshUI(T t);

    public BaseUIController(BaseFragment baseFragment, LoadingView loadingView) {
        this.mBaseFragment = new SoftReference<>(baseFragment);
        this.mBaseLoadingView = new SoftReference<>(loadingView);
    }

    public void onStart() {
        LoadingView loadingView = this.mBaseLoadingView.get();
        if (loadingView != null) {
            boolean z = false;
            boolean z2 = this.mData == null;
            if (this.mData == null) {
                z = true;
            }
            loadingView.startLoading(z2, z);
        }
    }

    public void onSuccess(T t, boolean z) {
        LoadingView loadingView = this.mBaseLoadingView.get();
        if (loadingView != null) {
            loadingView.loadingSuccess();
        }
        if (!z) {
            this.mData = t;
            onRefreshUI(t);
        } else if (this.mData == null) {
            this.mData = t;
            onRefreshUI(t);
        }
    }

    public void onError(VolleyError volleyError) {
        LoadingView loadingView = this.mBaseLoadingView.get();
        if (volleyError instanceof ShopApiError) {
            ShopApiError shopApiError = (ShopApiError) volleyError;
            ShopApiError.ErrorType errorType = shopApiError.getErrorType();
            switch (errorType) {
                case NOCONNECTION:
                    if (this.mData == null) {
                        if (loadingView != null) {
                            loadingView.showErrorWithActionButton(errorType.getErrorTipRes(), errorType.getErrorBtnRes(), (Handler.Callback) new Handler.Callback() {
                                public boolean handleMessage(Message message) {
                                    BaseFragment baseFragment = (BaseFragment) BaseUIController.this.mBaseFragment.get();
                                    if (baseFragment == null || !baseFragment.isAdded() || baseFragment.getActivity() == null) {
                                        return true;
                                    }
                                    baseFragment.startActivity(new Intent("android.settings.WIFI_SETTINGS"));
                                    return true;
                                }
                            });
                            return;
                        }
                        return;
                    } else if (loadingView != null) {
                        loadingView.showErrorAsToast(errorType.getErrorTipRes());
                        return;
                    } else {
                        return;
                    }
                case SERVER:
                case TIMEOUT:
                case PARSE:
                case UNKNOW:
                    if (this.mData == null) {
                        if (loadingView != null) {
                            loadingView.showErrorWithActionButton(errorType.getErrorTipRes(), errorType.getErrorBtnRes(), (Handler.Callback) new Handler.Callback() {
                                public boolean handleMessage(Message message) {
                                    BaseFragment baseFragment = (BaseFragment) BaseUIController.this.mBaseFragment.get();
                                    if (baseFragment == null) {
                                        return true;
                                    }
                                    baseFragment.reload(0);
                                    return true;
                                }
                            });
                            return;
                        }
                        return;
                    } else if (loadingView != null) {
                        loadingView.showErrorAsToast(errorType.getErrorTipRes());
                        return;
                    } else {
                        return;
                    }
                case CUSTOM:
                    ShopApiBaseResult apiBaseResult = shopApiError.getApiBaseResult();
                    if (this.mData == null) {
                        final long j = apiBaseResult.mCode;
                        String str = apiBaseResult.mDescription;
                        String errorBtnTip = getErrorBtnTip(j);
                        if (loadingView != null) {
                            loadingView.showErrorWithActionButton(str, errorBtnTip, (Handler.Callback) new Handler.Callback() {
                                public boolean handleMessage(Message message) {
                                    BaseFragment baseFragment = (BaseFragment) BaseUIController.this.mBaseFragment.get();
                                    if (baseFragment == null) {
                                        return true;
                                    }
                                    if (j != 10000200010001000L) {
                                        baseFragment.reload(0);
                                        return true;
                                    } else if (LoginManager.iShopAccountManager == null) {
                                        return true;
                                    } else {
                                        LoginManager.iShopAccountManager.gotoAccount(baseFragment.getActivity());
                                        return true;
                                    }
                                }
                            });
                            return;
                        }
                        return;
                    } else if (loadingView != null) {
                        loadingView.showErrorAsToast(apiBaseResult.mDescription);
                        return;
                    } else {
                        return;
                    }
                default:
                    if (this.mData == null) {
                        if (loadingView != null) {
                            loadingView.showErrorWithActionButton(errorType.getErrorTipRes(), errorType.getErrorBtnRes(), (Handler.Callback) new Handler.Callback() {
                                public boolean handleMessage(Message message) {
                                    BaseFragment baseFragment = (BaseFragment) BaseUIController.this.mBaseFragment.get();
                                    if (baseFragment == null) {
                                        return true;
                                    }
                                    baseFragment.reload(0);
                                    return true;
                                }
                            });
                            return;
                        }
                        return;
                    } else if (loadingView != null) {
                        loadingView.showErrorAsToast(errorType.getErrorTipRes());
                        return;
                    } else {
                        return;
                    }
            }
        } else if (this.mData == null) {
            if (loadingView != null) {
                loadingView.showErrorWithActionButton(R.string.mishopsdk_network_errortip_unknow, R.string.mishopsdk_network_errorbtntip_unknow, (Handler.Callback) new Handler.Callback() {
                    public boolean handleMessage(Message message) {
                        BaseFragment baseFragment = (BaseFragment) BaseUIController.this.mBaseFragment.get();
                        if (baseFragment == null) {
                            return true;
                        }
                        baseFragment.reload(0);
                        return true;
                    }
                });
            }
        } else if (loadingView != null) {
            loadingView.showErrorAsToast(R.string.mishopsdk_network_errortip_unknow);
        }
    }

    private String getErrorBtnTip(long j) {
        if (j == 10000200010001000L) {
            return ShopApp.instance.getString(R.string.mishopsdk_network_errorbtntip_login);
        }
        return ShopApp.instance.getString(R.string.mishopsdk_network_errorbtntip_custom);
    }

    public void onFinish() {
        LoadingView loadingView = this.mBaseLoadingView.get();
        if (loadingView != null) {
            loadingView.stopLoading();
        }
    }
}
