package com.xiaomi.smarthome.framework.plugin.rn.viewmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import java.util.Map;

public class MultiChoseDialogManager extends SimpleViewManager {
    private static String REACT_CLASS = "RCTMultiChoseDialog";

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public View createViewInstance(ThemedReactContext themedReactContext) {
        MultiChoseDialogHostView multiChoseDialogHostView = new MultiChoseDialogHostView(themedReactContext);
        ThemedReactContext unused = multiChoseDialogHostView.e = themedReactContext;
        MLAlertDialog.Builder unused2 = multiChoseDialogHostView.b = new MLAlertDialog.Builder(themedReactContext.getCurrentActivity());
        return multiChoseDialogHostView;
    }

    @ReactProp(name = "title")
    public void setTitle(MultiChoseDialogHostView multiChoseDialogHostView, @Nullable String str) {
        if (multiChoseDialogHostView.b != null) {
            multiChoseDialogHostView.b.a((CharSequence) str);
        }
        if (multiChoseDialogHostView.d != null) {
            multiChoseDialogHostView.d.setTitle(str);
        }
    }

    @ReactProp(defaultBoolean = true, name = "cancelable")
    public void setCancelable(MultiChoseDialogHostView multiChoseDialogHostView, @Nullable boolean z) {
        if (multiChoseDialogHostView.b != null) {
            multiChoseDialogHostView.b.a(z);
        }
        if (multiChoseDialogHostView.d != null) {
            multiChoseDialogHostView.d.setCancelable(z);
        }
    }

    @ReactProp(name = "visible")
    public void setVisible(MultiChoseDialogHostView multiChoseDialogHostView, boolean z) {
        boolean unused = multiChoseDialogHostView.c = z;
    }

    @ReactProp(name = "timeout")
    public void setTimeout(MultiChoseDialogHostView multiChoseDialogHostView, int i) {
        long unused = multiChoseDialogHostView.j = (long) i;
    }

    @ReactProp(name = "dataSource")
    public void setDataSource(MultiChoseDialogHostView multiChoseDialogHostView, @Nullable ReadableArray readableArray) {
        ReadableArray unused = multiChoseDialogHostView.g = readableArray;
        if (multiChoseDialogHostView.g != null) {
            boolean[] unused2 = multiChoseDialogHostView.i = new boolean[multiChoseDialogHostView.g.size()];
        }
    }

    @ReactProp(name = "dataKey")
    public void setDataKey(MultiChoseDialogHostView multiChoseDialogHostView, @Nullable String str) {
        String unused = multiChoseDialogHostView.h = str;
    }

    @ReactProp(name = "checkKey")
    public void setCheckKey(MultiChoseDialogHostView multiChoseDialogHostView, String str) {
        String unused = multiChoseDialogHostView.f = str;
    }

    @ReactProp(name = "cancel")
    public void setCancel(final MultiChoseDialogHostView multiChoseDialogHostView, @Nullable String str) {
        if (!TextUtils.isEmpty(str)) {
            AnonymousClass1 r0 = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putInt("position", i);
                    ((RCTEventEmitter) multiChoseDialogHostView.e.getJSModule(RCTEventEmitter.class)).receiveEvent(multiChoseDialogHostView.getId(), "onCancel", createMap);
                }
            };
            if (multiChoseDialogHostView.b != null) {
                multiChoseDialogHostView.b.b((CharSequence) str, (DialogInterface.OnClickListener) r0);
            }
            if (multiChoseDialogHostView.d != null) {
                multiChoseDialogHostView.d.setButton(-2, (CharSequence) str, (DialogInterface.OnClickListener) r0);
            }
        }
    }

    @ReactProp(name = "confirm")
    public void setConfirm(final MultiChoseDialogHostView multiChoseDialogHostView, @Nullable String str) {
        if (!TextUtils.isEmpty(str)) {
            AnonymousClass2 r0 = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    WritableMap createMap = Arguments.createMap();
                    WritableNativeArray writableNativeArray = new WritableNativeArray();
                    if (multiChoseDialogHostView.i != null) {
                        for (boolean pushBoolean : multiChoseDialogHostView.i) {
                            writableNativeArray.pushBoolean(pushBoolean);
                        }
                    }
                    createMap.putArray("position", writableNativeArray);
                    ((RCTEventEmitter) multiChoseDialogHostView.e.getJSModule(RCTEventEmitter.class)).receiveEvent(multiChoseDialogHostView.getId(), "onConfirm", createMap);
                }
            };
            if (multiChoseDialogHostView.b != null) {
                multiChoseDialogHostView.b.a((CharSequence) str, (DialogInterface.OnClickListener) r0);
            }
            if (multiChoseDialogHostView.d != null) {
                multiChoseDialogHostView.d.setButton(-1, (CharSequence) str, (DialogInterface.OnClickListener) r0);
            }
        }
    }

    @javax.annotation.Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder().put("onConfirm", MapBuilder.of("registrationName", "onConfirm")).put("onCancel", MapBuilder.of("registrationName", "onCancel")).put("onDismiss", MapBuilder.of("registrationName", "onDismiss")).put("onCheck", MapBuilder.of("registrationName", "onCheck")).build();
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext themedReactContext, View view) {
        if (view != null && (view instanceof MultiChoseDialogHostView)) {
            final MultiChoseDialogHostView multiChoseDialogHostView = (MultiChoseDialogHostView) view;
            multiChoseDialogHostView.b.a((MLAlertDialog.DismissCallBack) new MLAlertDialog.DismissCallBack() {
                public void afterDismissCallBack() {
                }

                public void beforeDismissCallBack() {
                    ((RCTEventEmitter) multiChoseDialogHostView.e.getJSModule(RCTEventEmitter.class)).receiveEvent(multiChoseDialogHostView.getId(), "onDismiss", Arguments.createMap());
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(final View view) {
        if (view != null && (view instanceof MultiChoseDialogHostView)) {
            final MultiChoseDialogHostView multiChoseDialogHostView = (MultiChoseDialogHostView) view;
            if (multiChoseDialogHostView.g != null) {
                String[] strArr = new String[multiChoseDialogHostView.g.size()];
                for (int i = 0; i < multiChoseDialogHostView.g.size(); i++) {
                    if (TextUtils.isEmpty(multiChoseDialogHostView.h)) {
                        strArr[i] = multiChoseDialogHostView.g.getString(i);
                    } else {
                        strArr[i] = multiChoseDialogHostView.g.getMap(i).getString(multiChoseDialogHostView.h);
                    }
                }
                if (multiChoseDialogHostView.f != null) {
                    for (int i2 = 0; i2 < multiChoseDialogHostView.g.size(); i2++) {
                        ReadableMap map = multiChoseDialogHostView.g.getMap(i2);
                        switch (map.getType(multiChoseDialogHostView.f)) {
                            case String:
                                if (TextUtils.isEmpty(map.getString(multiChoseDialogHostView.f))) {
                                    break;
                                } else {
                                    multiChoseDialogHostView.i[i2] = true;
                                    break;
                                }
                            case Map:
                                if (map.getMap(multiChoseDialogHostView.f) == null) {
                                    break;
                                } else {
                                    multiChoseDialogHostView.i[i2] = true;
                                    break;
                                }
                            case Array:
                                if (map.getArray(multiChoseDialogHostView.f) == null) {
                                    break;
                                } else {
                                    multiChoseDialogHostView.i[i2] = true;
                                    break;
                                }
                            case Number:
                                if (map.getDouble(multiChoseDialogHostView.f) == 0.0d) {
                                    break;
                                } else {
                                    multiChoseDialogHostView.i[i2] = true;
                                    break;
                                }
                            case Boolean:
                                if (!map.getBoolean(multiChoseDialogHostView.f)) {
                                    break;
                                } else {
                                    multiChoseDialogHostView.i[i2] = true;
                                    break;
                                }
                        }
                    }
                }
                multiChoseDialogHostView.b.a((CharSequence[]) strArr, multiChoseDialogHostView.i, (DialogInterface.OnMultiChoiceClickListener) new DialogInterface.OnMultiChoiceClickListener() {
                    public void onClick(DialogInterface dialogInterface, int i, boolean z) {
                        WritableMap createMap = Arguments.createMap();
                        createMap.putInt("position", i);
                        createMap.putBoolean("check", z);
                        ((RCTEventEmitter) multiChoseDialogHostView.e.getJSModule(RCTEventEmitter.class)).receiveEvent(view.getId(), "onCheck", createMap);
                    }
                });
            }
            try {
                if (multiChoseDialogHostView.c) {
                    if (multiChoseDialogHostView.d != null) {
                        if (!multiChoseDialogHostView.d.isShowing()) {
                            multiChoseDialogHostView.d.show();
                            if (view != null && multiChoseDialogHostView.j > 0) {
                                view.postDelayed(new Runnable() {
                                    public void run() {
                                        boolean unused = multiChoseDialogHostView.c = false;
                                        MultiChoseDialogManager.this.onAfterUpdateTransaction(view);
                                    }
                                }, multiChoseDialogHostView.j);
                            }
                        }
                    } else if (multiChoseDialogHostView.b != null) {
                        MLAlertDialog unused = multiChoseDialogHostView.d = multiChoseDialogHostView.b.d();
                        if (view != null && multiChoseDialogHostView.j > 0) {
                            view.postDelayed(new Runnable() {
                                public void run() {
                                    boolean unused = multiChoseDialogHostView.c = false;
                                    MultiChoseDialogManager.this.onAfterUpdateTransaction(view);
                                }
                            }, multiChoseDialogHostView.j);
                        }
                    }
                } else if (multiChoseDialogHostView.d != null && multiChoseDialogHostView.d.isShowing()) {
                    multiChoseDialogHostView.d.dismiss();
                }
            } catch (Throwable unused2) {
            }
        }
    }

    public void onDropViewInstance(View view) {
        if (view != null && (view instanceof MultiChoseDialogHostView)) {
            ((MultiChoseDialogHostView) view).a();
            LogUtil.c("RCTMultiChoseDialog", "onDropViewInstance..." + view.getId());
        }
        super.onDropViewInstance(view);
    }

    private class MultiChoseDialogHostView extends View {
        /* access modifiers changed from: private */
        public MLAlertDialog.Builder b;
        /* access modifiers changed from: private */
        public boolean c;
        /* access modifiers changed from: private */
        public MLAlertDialog d;
        /* access modifiers changed from: private */
        public ThemedReactContext e;
        /* access modifiers changed from: private */
        public String f;
        /* access modifiers changed from: private */
        public ReadableArray g;
        /* access modifiers changed from: private */
        public String h;
        /* access modifiers changed from: private */
        public boolean[] i;
        /* access modifiers changed from: private */
        public long j;

        public MultiChoseDialogHostView(Context context) {
            super(context);
        }

        public void a() {
            if (this.d != null && this.d.isShowing()) {
                this.d.dismiss();
            }
            this.d = null;
            this.e = null;
        }
    }
}
