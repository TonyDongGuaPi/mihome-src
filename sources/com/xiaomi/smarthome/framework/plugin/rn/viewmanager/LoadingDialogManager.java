package com.xiaomi.smarthome.framework.plugin.rn.viewmanager;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import java.util.Map;

public class LoadingDialogManager extends SimpleViewManager {
    private static String REACT_CLASS = "RCTLoadingDialog";

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public LoadingDialogHostView createViewInstance(ThemedReactContext themedReactContext) {
        LoadingDialogHostView loadingDialogHostView = new LoadingDialogHostView(themedReactContext);
        ThemedReactContext unused = loadingDialogHostView.d = themedReactContext;
        if (themedReactContext.getCurrentActivity() != null) {
            XQProgressDialog unused2 = loadingDialogHostView.b = new XQProgressDialog(themedReactContext.getCurrentActivity());
            loadingDialogHostView.b.setCancelable(false);
        }
        return loadingDialogHostView;
    }

    @ReactProp(defaultBoolean = true, name = "cancelable")
    public void setCancelable(LoadingDialogHostView loadingDialogHostView, @Nullable boolean z) {
        if (loadingDialogHostView.b != null) {
            loadingDialogHostView.b.setCancelable(z);
        }
    }

    @ReactProp(name = "title")
    public void setTitle(LoadingDialogHostView loadingDialogHostView, @Nullable String str) {
        if (loadingDialogHostView.b != null) {
            loadingDialogHostView.b.setTitle(str);
        }
    }

    @ReactProp(name = "visible")
    public void setVisible(LoadingDialogHostView loadingDialogHostView, boolean z) {
        if (loadingDialogHostView.b != null) {
            boolean unused = loadingDialogHostView.c = z;
        }
    }

    @ReactProp(name = "timeout")
    public void setTimeout(LoadingDialogHostView loadingDialogHostView, int i) {
        if (loadingDialogHostView.b != null) {
            long unused = loadingDialogHostView.e = (long) i;
        }
    }

    @ReactProp(name = "message")
    public void setMessage(LoadingDialogHostView loadingDialogHostView, @Nullable String str) {
        if (loadingDialogHostView.b != null) {
            loadingDialogHostView.b.setMessage(str);
        }
    }

    @javax.annotation.Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder().put("onDismiss", MapBuilder.of("registrationName", "onDismiss")).build();
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext themedReactContext, View view) {
        if (view != null && (view instanceof LoadingDialogHostView)) {
            final LoadingDialogHostView loadingDialogHostView = (LoadingDialogHostView) view;
            if (loadingDialogHostView.b != null) {
                loadingDialogHostView.b.setDismissCallBack(new MLAlertDialog.DismissCallBack() {
                    public void afterDismissCallBack() {
                    }

                    public void beforeDismissCallBack() {
                        ((RCTEventEmitter) loadingDialogHostView.d.getJSModule(RCTEventEmitter.class)).receiveEvent(loadingDialogHostView.getId(), "onDismiss", Arguments.createMap());
                    }
                });
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(View view) {
        if (view != null && (view instanceof LoadingDialogHostView)) {
            final LoadingDialogHostView loadingDialogHostView = (LoadingDialogHostView) view;
            try {
                if (loadingDialogHostView.b == null) {
                    return;
                }
                if (loadingDialogHostView.c) {
                    if (!loadingDialogHostView.b.isShowing()) {
                        loadingDialogHostView.b.show();
                        if (view != null && loadingDialogHostView.e > 0) {
                            view.postDelayed(new Runnable() {
                                public void run() {
                                    boolean unused = loadingDialogHostView.c = false;
                                    LoadingDialogManager.this.onAfterUpdateTransaction(loadingDialogHostView);
                                }
                            }, loadingDialogHostView.e);
                        }
                    }
                } else if (loadingDialogHostView.b.isShowing()) {
                    loadingDialogHostView.b.dismiss();
                }
            } catch (Throwable unused) {
            }
        }
    }

    public void onDropViewInstance(View view) {
        if (view != null && (view instanceof LoadingDialogHostView)) {
            ((LoadingDialogHostView) view).a();
            LogUtil.c("RCTLoadingDialog", "onDropViewInstance..." + view.getId());
        }
        super.onDropViewInstance(view);
    }

    private class LoadingDialogHostView extends View {
        /* access modifiers changed from: private */
        public XQProgressDialog b;
        /* access modifiers changed from: private */
        public boolean c;
        /* access modifiers changed from: private */
        public ThemedReactContext d;
        /* access modifiers changed from: private */
        public long e;

        public LoadingDialogHostView(Context context) {
            super(context);
        }

        public void a() {
            if (this.b != null && this.b.isShowing()) {
                this.b.dismiss();
            }
            this.b = null;
            this.d = null;
        }
    }
}
