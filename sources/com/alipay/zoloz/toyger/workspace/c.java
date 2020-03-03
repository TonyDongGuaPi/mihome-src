package com.alipay.zoloz.toyger.workspace;

import android.os.Handler;
import android.os.Message;
import com.alipay.zoloz.toyger.extservice.record.ToygerRecordService;
import java.util.HashMap;

class c extends Handler {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ToygerNavigationFragment f1220a;

    c(ToygerNavigationFragment toygerNavigationFragment) {
        this.f1220a = toygerNavigationFragment;
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case 0:
                this.f1220a.mToygerRecordService.write(ToygerRecordService.CLICK_START_CAPTURE);
                this.f1220a.forward(new ToygerCaptureFragment());
                return;
            case 1:
                this.f1220a.mWebView.loadUrl("file:///android_asset/html/nav/facewelcome.html");
                return;
            case 2:
                this.f1220a.mWebView.loadUrl("file:///android_asset/html/nav/facewelcome.html");
                return;
            case 3:
                this.f1220a.mToygerRecordService.write(ToygerRecordService.EXIT_GUIDE_PAGE);
                this.f1220a.mToygerCallback.sendResponse(202);
                this.f1220a.mToygerCallback.finishActivity(false);
                return;
            case 4:
                String str = (String) message.obj;
                if (str != null) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("h5_guide_log", str);
                    this.f1220a.mToygerRecordService.write(ToygerRecordService.DEV_TECH_SEED, hashMap);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
