package com.xiaomi.smarthome.framework.plugin.rn.viewmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import java.util.Map;

public class MessageDialogManager extends SimpleViewManager {
    private static String REACT_CLASS = "RCTMessageDialog";
    private static final String TAG = "MessageDialogManager";

    public String getName() {
        LogUtil.c(TAG, "getName...");
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public MessageDialogHostView createViewInstance(ThemedReactContext themedReactContext) {
        MessageDialogHostView messageDialogHostView = new MessageDialogHostView(themedReactContext);
        ThemedReactContext unused = messageDialogHostView.e = themedReactContext;
        if (themedReactContext.getCurrentActivity() != null) {
            MLAlertDialog.Builder unused2 = messageDialogHostView.b = new MLAlertDialog.Builder(themedReactContext.getCurrentActivity());
        } else {
            LogUtil.c(TAG, "currentActivity is null ");
        }
        return messageDialogHostView;
    }

    @ReactProp(name = "title")
    public void setTitle(MessageDialogHostView messageDialogHostView, @Nullable String str) {
        boolean unused = messageDialogHostView.g = true;
        if (messageDialogHostView.b != null) {
            messageDialogHostView.b.a((CharSequence) str);
        }
        if (messageDialogHostView.d != null) {
            messageDialogHostView.d.setTitle(str);
        }
    }

    @ReactProp(name = "visible")
    public void setVisible(MessageDialogHostView messageDialogHostView, boolean z) {
        boolean unused = messageDialogHostView.c = z;
    }

    @ReactProp(name = "timeout")
    public void setTimeout(MessageDialogHostView messageDialogHostView, int i) {
        long unused = messageDialogHostView.f = (long) i;
    }

    @ReactProp(name = "message")
    public void setMessage(MessageDialogHostView messageDialogHostView, @Nullable String str) {
        boolean unused = messageDialogHostView.g = true;
        if (messageDialogHostView.b != null) {
            messageDialogHostView.b.b((CharSequence) str);
        }
        if (messageDialogHostView.d != null) {
            messageDialogHostView.d.setMessage(str);
        }
    }

    @ReactProp(defaultBoolean = true, name = "cancelable")
    public void setCancelable(MessageDialogHostView messageDialogHostView, @Nullable boolean z) {
        if (messageDialogHostView.b != null) {
            messageDialogHostView.b.a(z);
        }
        if (messageDialogHostView.d != null) {
            messageDialogHostView.d.setCancelable(z);
        }
    }

    @ReactProp(name = "cancel")
    public void setCancel(final MessageDialogHostView messageDialogHostView, @Nullable String str) {
        boolean unused = messageDialogHostView.g = true;
        if (!TextUtils.isEmpty(str)) {
            AnonymousClass1 r0 = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putInt("position", i);
                    ((RCTEventEmitter) messageDialogHostView.e.getJSModule(RCTEventEmitter.class)).receiveEvent(messageDialogHostView.getId(), "onCancel", createMap);
                }
            };
            if (messageDialogHostView.b != null) {
                messageDialogHostView.b.b((CharSequence) str, (DialogInterface.OnClickListener) r0);
            }
            if (messageDialogHostView.d != null) {
                messageDialogHostView.d.setButton(-2, (CharSequence) str, (DialogInterface.OnClickListener) r0);
                messageDialogHostView.d.getButton(-2).setText(str);
            }
        } else if (messageDialogHostView.b != null) {
            messageDialogHostView.b.b((CharSequence) str, (DialogInterface.OnClickListener) null);
        }
    }

    @ReactProp(name = "confirm")
    public void setConfirm(final MessageDialogHostView messageDialogHostView, @Nullable String str) {
        boolean unused = messageDialogHostView.g = true;
        if (!TextUtils.isEmpty(str)) {
            AnonymousClass2 r0 = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putInt("position", i);
                    ((RCTEventEmitter) messageDialogHostView.e.getJSModule(RCTEventEmitter.class)).receiveEvent(messageDialogHostView.getId(), "onConfirm", createMap);
                }
            };
            if (messageDialogHostView.b != null) {
                messageDialogHostView.b.a((CharSequence) str, (DialogInterface.OnClickListener) r0);
            }
            if (messageDialogHostView.d != null) {
                messageDialogHostView.d.setButton(-1, (CharSequence) str, (DialogInterface.OnClickListener) r0);
                messageDialogHostView.d.getButton(-1).setText(str);
            }
        } else if (messageDialogHostView.b != null) {
            messageDialogHostView.b.a((CharSequence) str, (DialogInterface.OnClickListener) null);
        }
    }

    @javax.annotation.Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        LogUtil.c(TAG, "getExportedCustomDirectEventTypeConstants...");
        return MapBuilder.builder().put("onConfirm", MapBuilder.of("registrationName", "onConfirm")).put("onCancel", MapBuilder.of("registrationName", "onCancel")).put("onDismiss", MapBuilder.of("registrationName", "onDismiss")).build();
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext themedReactContext, View view) {
        if (view != null && (view instanceof MessageDialogHostView)) {
            final MessageDialogHostView messageDialogHostView = (MessageDialogHostView) view;
            if (messageDialogHostView.b != null) {
                messageDialogHostView.b.a((MLAlertDialog.DismissCallBack) new MLAlertDialog.DismissCallBack() {
                    public void afterDismissCallBack() {
                    }

                    public void beforeDismissCallBack() {
                        LogUtil.c(MessageDialogManager.TAG, "rn dismiss  is exec...");
                        ((RCTEventEmitter) messageDialogHostView.e.getJSModule(RCTEventEmitter.class)).receiveEvent(messageDialogHostView.getId(), "onDismiss", Arguments.createMap());
                    }
                });
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(final View view) {
        if (view != null && (view instanceof MessageDialogHostView)) {
            final MessageDialogHostView messageDialogHostView = (MessageDialogHostView) view;
            try {
                MLAlertDialog b = messageDialogHostView.d;
                if (messageDialogHostView.g) {
                    if (b != null && b.isShowing()) {
                        b.dismiss();
                    }
                    RnPluginLog.a("MessageDialog  should rebuild dialog...");
                    MLAlertDialog unused = messageDialogHostView.d = null;
                    b = null;
                }
                if (messageDialogHostView.c) {
                    if (b != null) {
                        if (!b.isShowing()) {
                            b.show();
                            if (view != null && messageDialogHostView.f > 0) {
                                view.postDelayed(new Runnable() {
                                    public void run() {
                                        boolean unused = messageDialogHostView.c = false;
                                        MessageDialogManager.this.onAfterUpdateTransaction(view);
                                    }
                                }, messageDialogHostView.f);
                            }
                        }
                    } else if (messageDialogHostView.b != null) {
                        MLAlertDialog unused2 = messageDialogHostView.d = messageDialogHostView.b.d();
                        boolean unused3 = messageDialogHostView.g = false;
                        if (view != null && messageDialogHostView.f > 0) {
                            view.postDelayed(new Runnable() {
                                public void run() {
                                    boolean unused = messageDialogHostView.c = false;
                                    MessageDialogManager.this.onAfterUpdateTransaction(view);
                                }
                            }, messageDialogHostView.f);
                        }
                    }
                } else if (b != null && b.isShowing()) {
                    b.dismiss();
                }
            } catch (Throwable unused4) {
            }
        }
    }

    public void onDropViewInstance(View view) {
        if (view != null && (view instanceof MessageDialogHostView)) {
            ((MessageDialogHostView) view).a();
            LogUtil.c("RCTMessageDialog", "onDropViewInstance..." + view.getId());
        }
        super.onDropViewInstance(view);
    }

    private class MessageDialogHostView extends View {
        /* access modifiers changed from: private */
        public MLAlertDialog.Builder b;
        /* access modifiers changed from: private */
        public boolean c;
        /* access modifiers changed from: private */
        public MLAlertDialog d;
        /* access modifiers changed from: private */
        public ThemedReactContext e;
        /* access modifiers changed from: private */
        public long f;
        /* access modifiers changed from: private */
        public boolean g = true;

        public MessageDialogHostView(Context context) {
            super(context);
        }

        public void a() {
            if (this.d != null && this.d.isShowing()) {
                this.d.dismiss();
            }
            this.b = null;
            this.d = null;
            this.e = null;
        }
    }
}
