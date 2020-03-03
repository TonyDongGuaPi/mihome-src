package com.xiaomi.smarthome.framework.plugin.rn.viewmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import java.util.Map;

public class SingleChoseDialogManager extends SimpleViewManager {
    private static String REACT_CLASS = "RCTSingleChoseDialog";
    private static final String TAG = "SingleChoseDialogManager";

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public SingleChoseDialogHostView createViewInstance(ThemedReactContext themedReactContext) {
        SingleChoseDialogHostView singleChoseDialogHostView = new SingleChoseDialogHostView(themedReactContext);
        ThemedReactContext unused = singleChoseDialogHostView.e = themedReactContext;
        MLAlertDialog.Builder unused2 = singleChoseDialogHostView.b = new MLAlertDialog.Builder(themedReactContext.getCurrentActivity());
        return singleChoseDialogHostView;
    }

    @ReactProp(name = "title")
    public void setTitle(SingleChoseDialogHostView singleChoseDialogHostView, @Nullable String str) {
        if (singleChoseDialogHostView.b != null) {
            singleChoseDialogHostView.b.a((CharSequence) str);
        }
        if (singleChoseDialogHostView.d != null) {
            singleChoseDialogHostView.d.setTitle(str);
        }
    }

    @ReactProp(defaultBoolean = true, name = "cancelable")
    public void setCancelable(SingleChoseDialogHostView singleChoseDialogHostView, @Nullable boolean z) {
        if (singleChoseDialogHostView.b != null) {
            singleChoseDialogHostView.b.a(z);
        }
        if (singleChoseDialogHostView.d != null) {
            singleChoseDialogHostView.d.setCancelable(z);
        }
    }

    @ReactProp(name = "visible")
    public void setVisible(SingleChoseDialogHostView singleChoseDialogHostView, boolean z) {
        boolean unused = singleChoseDialogHostView.c = z;
    }

    @ReactProp(name = "dataSource")
    public void setDataSource(SingleChoseDialogHostView singleChoseDialogHostView, @Nullable ReadableArray readableArray) {
        ReadableArray unused = singleChoseDialogHostView.g = readableArray;
    }

    @ReactProp(name = "timeout")
    public void setTimeout(SingleChoseDialogHostView singleChoseDialogHostView, int i) {
        long unused = singleChoseDialogHostView.i = (long) i;
    }

    @ReactProp(name = "dataKey")
    @Deprecated
    public void setDataKey(SingleChoseDialogHostView singleChoseDialogHostView, @Nullable String str) {
        String unused = singleChoseDialogHostView.h = str;
    }

    @ReactProp(name = "check")
    public void setCheck(SingleChoseDialogHostView singleChoseDialogHostView, int i) {
        int unused = singleChoseDialogHostView.f = i;
        if (singleChoseDialogHostView.d != null && singleChoseDialogHostView.f >= 0 && singleChoseDialogHostView.d.getListView() != null) {
            singleChoseDialogHostView.d.getListView().setItemChecked(singleChoseDialogHostView.f, true);
            singleChoseDialogHostView.d.getListView().setSelection(singleChoseDialogHostView.f);
        }
    }

    @ReactProp(name = "cancel")
    public void setCancel(final SingleChoseDialogHostView singleChoseDialogHostView, @Nullable String str) {
        if (!TextUtils.isEmpty(str)) {
            AnonymousClass1 r0 = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putInt("position", singleChoseDialogHostView.f);
                    ((RCTEventEmitter) singleChoseDialogHostView.e.getJSModule(RCTEventEmitter.class)).receiveEvent(singleChoseDialogHostView.getId(), "onCancel", createMap);
                }
            };
            if (singleChoseDialogHostView.b != null) {
                singleChoseDialogHostView.b.b((CharSequence) str, (DialogInterface.OnClickListener) r0);
            }
            if (singleChoseDialogHostView.d != null) {
                singleChoseDialogHostView.d.setButton(-2, (CharSequence) str, (DialogInterface.OnClickListener) r0);
            }
        }
    }

    @ReactProp(name = "confirm")
    public void setConfirm(final SingleChoseDialogHostView singleChoseDialogHostView, @Nullable String str) {
        if (!TextUtils.isEmpty(str)) {
            AnonymousClass2 r0 = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putInt("position", singleChoseDialogHostView.f);
                    ((RCTEventEmitter) singleChoseDialogHostView.e.getJSModule(RCTEventEmitter.class)).receiveEvent(singleChoseDialogHostView.getId(), "onConfirm", createMap);
                }
            };
            if (singleChoseDialogHostView.b != null) {
                singleChoseDialogHostView.b.a((CharSequence) str, (DialogInterface.OnClickListener) r0);
            }
            if (singleChoseDialogHostView.d != null) {
                singleChoseDialogHostView.d.setButton(-1, (CharSequence) str, (DialogInterface.OnClickListener) r0);
            }
        }
    }

    @javax.annotation.Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder().put("onConfirm", MapBuilder.of("registrationName", "onConfirm")).put("onCancel", MapBuilder.of("registrationName", "onCancel")).put("onDismiss", MapBuilder.of("registrationName", "onDismiss")).put("onCheck", MapBuilder.of("registrationName", "onCheck")).build();
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext themedReactContext, View view) {
        if (view != null && (view instanceof SingleChoseDialogHostView)) {
            final SingleChoseDialogHostView singleChoseDialogHostView = (SingleChoseDialogHostView) view;
            singleChoseDialogHostView.b.a((MLAlertDialog.DismissCallBack) new MLAlertDialog.DismissCallBack() {
                public void afterDismissCallBack() {
                }

                public void beforeDismissCallBack() {
                    ((RCTEventEmitter) singleChoseDialogHostView.e.getJSModule(RCTEventEmitter.class)).receiveEvent(singleChoseDialogHostView.getId(), "onDismiss", Arguments.createMap());
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(final View view) {
        if (view != null && (view instanceof SingleChoseDialogHostView)) {
            final SingleChoseDialogHostView singleChoseDialogHostView = (SingleChoseDialogHostView) view;
            if (singleChoseDialogHostView.g != null) {
                int size = singleChoseDialogHostView.g.size();
                String[] strArr = new String[size];
                for (int i = 0; i < size; i++) {
                    if (TextUtils.isEmpty(singleChoseDialogHostView.h)) {
                        strArr[i] = singleChoseDialogHostView.g.getString(i);
                    } else {
                        strArr[i] = singleChoseDialogHostView.g.getMap(i).getString(singleChoseDialogHostView.h);
                    }
                }
                singleChoseDialogHostView.b.a((CharSequence[]) strArr, singleChoseDialogHostView.f, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i) {
                        int unused = singleChoseDialogHostView.f = i;
                        WritableMap createMap = Arguments.createMap();
                        createMap.putInt("position", i);
                        ((RCTEventEmitter) singleChoseDialogHostView.e.getJSModule(RCTEventEmitter.class)).receiveEvent(singleChoseDialogHostView.getId(), "onCheck", createMap);
                    }
                });
            }
            try {
                if (singleChoseDialogHostView.c) {
                    if (singleChoseDialogHostView.d != null) {
                        if (!singleChoseDialogHostView.d.isShowing()) {
                            singleChoseDialogHostView.d.show();
                            if (view != null && singleChoseDialogHostView.i > 0) {
                                view.postDelayed(new Runnable() {
                                    public void run() {
                                        boolean unused = singleChoseDialogHostView.c = false;
                                        SingleChoseDialogManager.this.onAfterUpdateTransaction(view);
                                    }
                                }, singleChoseDialogHostView.i);
                            }
                        }
                    } else if (singleChoseDialogHostView.b != null) {
                        MLAlertDialog unused = singleChoseDialogHostView.d = singleChoseDialogHostView.b.d();
                        if (view != null && singleChoseDialogHostView.i > 0) {
                            view.postDelayed(new Runnable() {
                                public void run() {
                                    boolean unused = singleChoseDialogHostView.c = false;
                                    SingleChoseDialogManager.this.onAfterUpdateTransaction(view);
                                }
                            }, singleChoseDialogHostView.i);
                        }
                    }
                } else if (singleChoseDialogHostView.d != null && singleChoseDialogHostView.d.isShowing()) {
                    singleChoseDialogHostView.d.dismiss();
                }
            } catch (Throwable unused2) {
            }
        }
    }

    public void onDropViewInstance(View view) {
        if (view != null && (view instanceof SingleChoseDialogHostView)) {
            ((SingleChoseDialogHostView) view).a();
            LogUtil.c(TAG, "onDropViewInstance..." + view.getId());
        }
        super.onDropViewInstance(view);
    }

    private class SingleChoseDialogHostView extends View {
        /* access modifiers changed from: private */
        public MLAlertDialog.Builder b;
        /* access modifiers changed from: private */
        public boolean c;
        /* access modifiers changed from: private */
        public MLAlertDialog d;
        /* access modifiers changed from: private */
        public ThemedReactContext e;
        /* access modifiers changed from: private */
        public int f;
        /* access modifiers changed from: private */
        public ReadableArray g;
        /* access modifiers changed from: private */
        public String h;
        /* access modifiers changed from: private */
        public long i;
        private boolean j = true;

        public SingleChoseDialogHostView(Context context) {
            super(context);
        }

        public void a() {
            if (this.d != null && this.d.isShowing()) {
                this.d.dismiss();
            }
            this.d = null;
            this.b = null;
            this.e = null;
        }
    }
}
