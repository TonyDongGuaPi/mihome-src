package com.tencent.smtt.sdk;

import android.content.Context;
import android.webkit.ValueCallback;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsContext;
import com.tencent.smtt.export.external.jscore.interfaces.IX5JsValue;
import java.net.URL;

public final class JsContext {

    /* renamed from: a  reason: collision with root package name */
    private final JsVirtualMachine f9074a;
    private final IX5JsContext b;
    /* access modifiers changed from: private */
    public ExceptionHandler c;
    private String d;

    public interface ExceptionHandler {
        void handleException(JsContext jsContext, JsError jsError);
    }

    public JsContext(Context context) {
        this(new JsVirtualMachine(context));
    }

    public JsContext(JsVirtualMachine jsVirtualMachine) {
        if (jsVirtualMachine != null) {
            this.f9074a = jsVirtualMachine;
            this.b = this.f9074a.a();
            try {
                this.b.setPerContextData(this);
            } catch (AbstractMethodError unused) {
            }
        } else {
            throw new IllegalArgumentException("The virtualMachine value can not be null");
        }
    }

    public static JsContext current() {
        return (JsContext) X5JsCore.a();
    }

    public void addJavascriptInterface(Object obj, String str) {
        this.b.addJavascriptInterface(obj, str);
    }

    public void destroy() {
        this.b.destroy();
    }

    public void evaluateJavascript(String str, ValueCallback<String> valueCallback) {
        evaluateJavascript(str, valueCallback, (URL) null);
    }

    public void evaluateJavascript(String str, ValueCallback<String> valueCallback, URL url) {
        this.b.evaluateJavascript(str, valueCallback, url);
    }

    public JsValue evaluateScript(String str) {
        return evaluateScript(str, (URL) null);
    }

    public JsValue evaluateScript(String str, URL url) {
        IX5JsValue evaluateScript = this.b.evaluateScript(str, url);
        if (evaluateScript == null) {
            return null;
        }
        return new JsValue(this, evaluateScript);
    }

    public void evaluateScriptAsync(String str, ValueCallback<JsValue> valueCallback, URL url) {
        this.b.evaluateScriptAsync(str, valueCallback == null ? null : new c(this, valueCallback), url);
    }

    public ExceptionHandler exceptionHandler() {
        return this.c;
    }

    public String name() {
        return this.d;
    }

    public void removeJavascriptInterface(String str) {
        this.b.removeJavascriptInterface(str);
    }

    public void setExceptionHandler(ExceptionHandler exceptionHandler) {
        IX5JsContext iX5JsContext;
        d dVar;
        this.c = exceptionHandler;
        if (exceptionHandler == null) {
            iX5JsContext = this.b;
            dVar = null;
        } else {
            iX5JsContext = this.b;
            dVar = new d(this);
        }
        iX5JsContext.setExceptionHandler(dVar);
    }

    public void setName(String str) {
        this.d = str;
        this.b.setName(str);
    }

    public void stealValueFromOtherCtx(String str, JsContext jsContext, String str2) {
        this.b.stealValueFromOtherCtx(str, jsContext.b, str2);
    }

    public JsVirtualMachine virtualMachine() {
        return this.f9074a;
    }
}
