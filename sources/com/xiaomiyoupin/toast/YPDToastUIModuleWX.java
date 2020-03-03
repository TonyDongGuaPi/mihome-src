package com.xiaomiyoupin.toast;

import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class YPDToastUIModuleWX extends WXModule implements IToast {
    private List<Integer> idList;

    @JSMethod(uiThread = true)
    public void getInnerVersion(JSCallback jSCallback) {
        if (jSCallback != null) {
            jSCallback.invoke("20190618");
        }
    }

    @JSMethod(uiThread = true)
    public void makeToast(String str) {
        makeToast(str, 1.0d);
    }

    @JSMethod(uiThread = true)
    public void makeToast(String str, double d) {
        makeToast(str, 1001, d);
    }

    @JSMethod(uiThread = true)
    public void makeToast(String str, int i, double d) {
        YPDToastInterceptor interceptor = YPDToast.getInstance().getInterceptor();
        if (interceptor != null) {
            interceptor.makeToast(this.mWXSDKInstance.getContext(), str, i, (long) d);
        }
    }

    @JSMethod(uiThread = true)
    public void makeToastActivityIndicator(String str, boolean z) {
        YPDToastInterceptor interceptor = YPDToast.getInstance().getInterceptor();
        if (interceptor != null) {
            Object makeToast = interceptor.makeToast(this.mWXSDKInstance.getContext(), str, 1, z);
            if (makeToast instanceof Integer) {
                addToastId((Integer) makeToast);
            }
        }
    }

    @JSMethod(uiThread = true)
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
