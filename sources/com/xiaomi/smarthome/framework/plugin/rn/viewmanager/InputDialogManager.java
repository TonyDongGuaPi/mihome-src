package com.xiaomi.smarthome.framework.plugin.rn.viewmanager;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class InputDialogManager extends SimpleViewManager {
    private static String REACT_CLASS = "RCTInputDialog";
    private static final String TAG = "InputDialogManager";

    public String getName() {
        return REACT_CLASS;
    }

    /* access modifiers changed from: protected */
    public InputDialogHostView createViewInstance(ThemedReactContext themedReactContext) {
        InputDialogHostView inputDialogHostView = new InputDialogHostView(themedReactContext);
        ThemedReactContext unused = inputDialogHostView.e = themedReactContext;
        MLAlertDialog.Builder unused2 = inputDialogHostView.b = new MLAlertDialog.Builder(themedReactContext.getCurrentActivity());
        return inputDialogHostView;
    }

    @ReactProp(name = "title")
    public void setTitle(InputDialogHostView inputDialogHostView, @Nullable String str) {
        if (inputDialogHostView.b != null) {
            inputDialogHostView.b.a((CharSequence) str);
        }
        if (inputDialogHostView.d != null) {
            inputDialogHostView.d.setTitle(str);
        }
    }

    @ReactProp(name = "message")
    public void setMessage(InputDialogHostView inputDialogHostView, @Nullable String str) {
        if (inputDialogHostView.b != null) {
            inputDialogHostView.b.b((CharSequence) str);
        }
        if (inputDialogHostView.d != null) {
            inputDialogHostView.d.setMessage(str);
        }
    }

    @ReactProp(defaultBoolean = true, name = "cancelable")
    public void setCancelable(InputDialogHostView inputDialogHostView, boolean z) {
        if (inputDialogHostView.b != null) {
            inputDialogHostView.b.a(z);
        }
        if (inputDialogHostView.d != null) {
            inputDialogHostView.d.setCancelable(z);
        }
    }

    @ReactProp(name = "visible")
    public void setVisible(InputDialogHostView inputDialogHostView, boolean z) {
        boolean unused = inputDialogHostView.c = z;
    }

    @ReactProp(name = "timeout")
    public void setTimeout(InputDialogHostView inputDialogHostView, int i) {
        long unused = inputDialogHostView.i = (long) i;
    }

    @ReactProp(name = "placeholder")
    public void setPlaceholder(InputDialogHostView inputDialogHostView, @Nullable String str) {
        String unused = inputDialogHostView.f = str;
    }

    @ReactProp(name = "singleLine")
    public void setSingleLine(InputDialogHostView inputDialogHostView, boolean z) {
        boolean unused = inputDialogHostView.h = z;
    }

    @ReactProp(name = "defaultText")
    public void setDefaultText(InputDialogHostView inputDialogHostView, String str) {
        String unused = inputDialogHostView.g = str;
    }

    @ReactProp(name = "cancel")
    public void setCancel(final InputDialogHostView inputDialogHostView, @Nullable String str) {
        if (!TextUtils.isEmpty(str)) {
            AnonymousClass1 r0 = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putInt("position", i);
                    if (inputDialogHostView.d != null) {
                        createMap.putString("text", inputDialogHostView.d.getInputView().getText().toString());
                    }
                    LogUtil.c(InputDialogManager.TAG, "rn onCancel  Emitter...");
                    ((RCTEventEmitter) inputDialogHostView.e.getJSModule(RCTEventEmitter.class)).receiveEvent(inputDialogHostView.getId(), "onCancel", createMap);
                }
            };
            if (inputDialogHostView.b != null) {
                inputDialogHostView.b.b((CharSequence) str, (DialogInterface.OnClickListener) r0);
            }
            if (inputDialogHostView.d != null) {
                inputDialogHostView.d.setButton(-2, (CharSequence) str, (DialogInterface.OnClickListener) r0);
                inputDialogHostView.d.getButton(-2).setText(str);
            }
        }
    }

    @ReactProp(name = "confirm")
    public void setConfirm(final InputDialogHostView inputDialogHostView, @Nullable String str) {
        if (!TextUtils.isEmpty(str)) {
            AnonymousClass2 r0 = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    WritableMap createMap = Arguments.createMap();
                    createMap.putInt("position", i);
                    if (inputDialogHostView.d != null) {
                        createMap.putString("text", inputDialogHostView.d.getInputView().getText().toString());
                    }
                    LogUtil.c(InputDialogManager.TAG, "rn onConfirm  Emitter...");
                    ((RCTEventEmitter) inputDialogHostView.e.getJSModule(RCTEventEmitter.class)).receiveEvent(inputDialogHostView.getId(), "onConfirm", createMap);
                }
            };
            if (inputDialogHostView.b != null) {
                inputDialogHostView.b.a((CharSequence) str, (DialogInterface.OnClickListener) r0);
            }
            if (inputDialogHostView.d != null) {
                inputDialogHostView.d.setButton(-1, (CharSequence) str, (DialogInterface.OnClickListener) r0);
                inputDialogHostView.d.getButton(-1).setText(str);
            }
        }
    }

    @javax.annotation.Nullable
    public Map<String, Object> getExportedCustomDirectEventTypeConstants() {
        return MapBuilder.builder().put("onConfirm", MapBuilder.of("registrationName", "onConfirm")).put("onCancel", MapBuilder.of("registrationName", "onCancel")).put("onDismiss", MapBuilder.of("registrationName", "onDismiss")).build();
    }

    /* access modifiers changed from: protected */
    public void addEventEmitters(ThemedReactContext themedReactContext, View view) {
        if (view != null && (view instanceof InputDialogHostView)) {
            final InputDialogHostView inputDialogHostView = (InputDialogHostView) view;
            inputDialogHostView.b.a((MLAlertDialog.DismissCallBack) new MLAlertDialog.DismissCallBack() {
                public void afterDismissCallBack() {
                }

                public void beforeDismissCallBack() {
                    WritableMap createMap = Arguments.createMap();
                    inputDialogHostView.b(inputDialogHostView.d.getInputView());
                    LogUtil.c(InputDialogManager.TAG, "rn onDismiss  Emitter...");
                    ((RCTEventEmitter) inputDialogHostView.e.getJSModule(RCTEventEmitter.class)).receiveEvent(inputDialogHostView.getId(), "onDismiss", createMap);
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onAfterUpdateTransaction(View view) {
        if (view != null && (view instanceof InputDialogHostView)) {
            final InputDialogHostView inputDialogHostView = (InputDialogHostView) view;
            try {
                if (inputDialogHostView.c) {
                    inputDialogHostView.a();
                    if (inputDialogHostView.d != null) {
                        if (!inputDialogHostView.d.isShowing()) {
                            inputDialogHostView.d.show();
                            inputDialogHostView.a(inputDialogHostView.d.getInputView());
                            if (inputDialogHostView.i > 0) {
                                inputDialogHostView.postDelayed(new Runnable() {
                                    public void run() {
                                        boolean unused = inputDialogHostView.c = false;
                                        InputDialogManager.this.onAfterUpdateTransaction(inputDialogHostView);
                                    }
                                }, inputDialogHostView.i);
                            }
                        }
                    } else if (inputDialogHostView.b != null) {
                        MLAlertDialog unused = inputDialogHostView.d = inputDialogHostView.b.d();
                        inputDialogHostView.a(inputDialogHostView.d.getInputView());
                        if (inputDialogHostView.i > 0) {
                            inputDialogHostView.postDelayed(new Runnable() {
                                public void run() {
                                    boolean unused = inputDialogHostView.c = false;
                                    InputDialogManager.this.onAfterUpdateTransaction(inputDialogHostView);
                                }
                            }, inputDialogHostView.i);
                        }
                    }
                } else if (inputDialogHostView.d != null && inputDialogHostView.d.isShowing()) {
                    inputDialogHostView.d.dismiss();
                }
            } catch (Throwable unused2) {
            }
        }
    }

    public void onDropViewInstance(View view) {
        if (view != null && (view instanceof InputDialogHostView)) {
            ((InputDialogHostView) view).b();
            LogUtil.c(TAG, "onDropViewInstance..." + view.getId());
        }
        super.onDropViewInstance(view);
    }

    private class InputDialogHostView extends View {
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
        public String g;
        /* access modifiers changed from: private */
        public boolean h;
        /* access modifiers changed from: private */
        public long i;

        public InputDialogHostView(Context context) {
            super(context);
        }

        public void a() {
            if (this.d == null || this.d.getInputView() == null) {
                this.b.a(this.f, this.h);
                this.b.a().setText(this.g);
                return;
            }
            EditText inputView = this.d.getInputView();
            inputView.setHint(this.f);
            inputView.setSingleLine(this.h);
            inputView.setText(this.g);
        }

        /* access modifiers changed from: private */
        public void a(final EditText editText) {
            if (editText != null) {
                String obj = editText.getText().toString();
                if (!TextUtils.isEmpty(obj)) {
                    editText.setSelection(obj.length());
                }
                new Timer().schedule(new TimerTask() {
                    public void run() {
                        ((InputMethodManager) InputDialogHostView.this.getContext().getSystemService("input_method")).showSoftInput(editText, 2);
                    }
                }, 200);
            }
        }

        /* access modifiers changed from: private */
        public void b(EditText editText) {
            if (editText != null) {
                ((InputMethodManager) getContext().getSystemService("input_method")).hideSoftInputFromWindow(editText.getWindowToken(), 0);
            }
        }

        public void b() {
            if (this.d != null && this.d.isShowing()) {
                this.d.dismiss();
            }
            this.d = null;
            this.b = null;
            this.e = null;
        }
    }
}
