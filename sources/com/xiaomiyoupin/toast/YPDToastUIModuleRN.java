package com.xiaomiyoupin.toast;

import android.content.Context;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.module.annotations.ReactModule;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ReactModule(name = "YPDToastModule")
public class YPDToastUIModuleRN extends ReactContextBaseJavaModule implements IToast {
    private List<Integer> idList;

    public YPDToastUIModuleRN(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @ReactMethod
    public String getName() {
        return YPDToast.getInstance().getRNModuleName();
    }

    @ReactMethod
    public void getInnerVersion(Callback callback) {
        if (callback != null) {
            callback.invoke("20190618");
        }
    }

    @ReactMethod
    public void makeToast(String str) {
        makeToast(str, 1.0d);
    }

    @ReactMethod
    public void makeToast(String str, double d) {
        makeToast(str, 1001, d);
    }

    @ReactMethod
    public void makeToast(String str, int i, double d) {
        YPDToastInterceptor interceptor = YPDToast.getInstance().getInterceptor();
        if (interceptor != null) {
            interceptor.makeToast((Context) getCurrentActivity(), str, i, (long) d);
        }
    }

    @ReactMethod
    public void makeToastActivityIndicator(String str, boolean z) {
        YPDToastInterceptor interceptor = YPDToast.getInstance().getInterceptor();
        if (interceptor != null) {
            Object makeToast = interceptor.makeToast((Context) getCurrentActivity(), str, 1, z);
            if (makeToast instanceof Integer) {
                addToastId((Integer) makeToast);
            }
        }
    }

    @ReactMethod
    public void dismiss() {
        YPDToastInterceptor interceptor = YPDToast.getInstance().getInterceptor();
        if (interceptor != null && this.idList != null) {
            synchronized (this) {
                Iterator<Integer> it = this.idList.iterator();
                while (it.hasNext()) {
                    interceptor.dismiss(it.next());
                    it.remove();
                }
            }
        }
    }

    private void addToastId(Integer num) {
        synchronized (this) {
            if (this.idList == null) {
                this.idList = new ArrayList();
            }
            this.idList.add(num);
        }
    }
}
