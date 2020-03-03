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
import com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog;
import java.util.Map;

public class ProgressDialogManager extends SimpleViewManager {
    private static String REACT_CLASS = "RCTProgressDialog";

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public View createViewInstance(ThemedReactContext themedReactContext) {
        ProgressDialogHostView progressDialogHostView = new ProgressDialogHostView(themedReactContext);
        ThemedReactContext unused = progressDialogHostView.d = themedReactContext;
        XQProgressHorizontalDialog unused2 = progressDialogHostView.b = new XQProgressHorizontalDialog(themedReactContext.getCurrentActivity());
        progressDialogHostView.b.c();
        progressDialogHostView.b.setCancelable(false);
        return progressDialogHostView;
    }

    @ReactProp(defaultBoolean = true, name = "cancelable")
    public void setCancelable(ProgressDialogHostView progressDialogHostView, @Nullable boolean z) {
        if (progressDialogHostView.b != null) {
            progressDialogHostView.b.setCancelable(z);
        }
    }

    @ReactProp(name = "title")
    public void setTitle(ProgressDialogHostView progressDialogHostView, @Nullable String str) {
        progressDialogHostView.b.setTitle(str);
    }

    @ReactProp(name = "visible")
    public void setVisible(ProgressDialogHostView progressDialogHostView, boolean z) {
        boolean unused = progressDialogHostView.c = z;
    }

    @ReactProp(name = "message")
    public void setMessage(ProgressDialogHostView progressDialogHostView, @Nullable String str) {
        progressDialogHostView.b.setMessage(str);
    }

    @ReactProp(name = "timeout")
    public void setTimeout(ProgressDialogHostView progressDialogHostView, int i) {
        long unused = progressDialogHostView.g = (long) i;
    }

    @ReactProp(name = "max")
    public void setMax(ProgressDialogHostView progressDialogHostView, int i) {
        int unused = progressDialogHostView.e = i;
    }

    @ReactProp(name = "progress")
    public void setProgress(ProgressDialogHostView progressDialogHostView, int i) {
        int unused = progressDialogHostView.f = i;
    }

    @javax.annotation.Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder().put("onDismiss", MapBuilder.of("registrationName", "onDismiss")).build();
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext themedReactContext, View view) {
        if (view != null && (view instanceof ProgressDialogHostView)) {
            final ProgressDialogHostView progressDialogHostView = (ProgressDialogHostView) view;
            progressDialogHostView.b.setDismissCallBack(new MLAlertDialog.DismissCallBack() {
                public void afterDismissCallBack() {
                }

                public void beforeDismissCallBack() {
                    ((RCTEventEmitter) progressDialogHostView.d.getJSModule(RCTEventEmitter.class)).receiveEvent(progressDialogHostView.getId(), "onDismiss", Arguments.createMap());
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(View view) {
        if (view != null && (view instanceof ProgressDialogHostView)) {
            final ProgressDialogHostView progressDialogHostView = (ProgressDialogHostView) view;
            try {
                if (progressDialogHostView.b != null) {
                    progressDialogHostView.b.a(progressDialogHostView.e, progressDialogHostView.f);
                    if (progressDialogHostView.c) {
                        if (!progressDialogHostView.b.isShowing()) {
                            progressDialogHostView.b.show();
                            if (view != null && progressDialogHostView.g > 0) {
                                view.postDelayed(new Runnable() {
                                    public void run() {
                                        boolean unused = progressDialogHostView.c = false;
                                        ProgressDialogManager.this.onAfterUpdateTransaction(progressDialogHostView);
                                    }
                                }, progressDialogHostView.g);
                            }
                        }
                    } else if (progressDialogHostView.b.isShowing()) {
                        progressDialogHostView.b.dismiss();
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    public void onDropViewInstance(View view) {
        if (view != null && (view instanceof ProgressDialogHostView)) {
            ((ProgressDialogHostView) view).a();
            LogUtil.c("RCTProgressDialog", "onDropViewInstance..." + view.getId());
        }
        super.onDropViewInstance(view);
    }

    private class ProgressDialogHostView extends View {
        /* access modifiers changed from: private */
        public XQProgressHorizontalDialog b;
        /* access modifiers changed from: private */
        public boolean c;
        /* access modifiers changed from: private */
        public ThemedReactContext d;
        /* access modifiers changed from: private */
        public int e = 100;
        /* access modifiers changed from: private */
        public int f;
        /* access modifiers changed from: private */
        public long g;

        public ProgressDialogHostView(Context context) {
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
