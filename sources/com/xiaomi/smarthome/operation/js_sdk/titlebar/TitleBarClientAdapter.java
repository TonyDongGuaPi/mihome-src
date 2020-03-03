package com.xiaomi.smarthome.operation.js_sdk.titlebar;

public class TitleBarClientAdapter implements ITitleBar {

    /* renamed from: a  reason: collision with root package name */
    private ITitleBar f21108a;

    public TitleBarClientAdapter() {
    }

    public TitleBarClientAdapter(ITitleBar iTitleBar) {
        this.f21108a = iTitleBar;
    }

    public void onReceivedTitle(String str) {
        if (this.f21108a != null) {
            this.f21108a.onReceivedTitle(str);
        }
    }

    public void onProgressChanged(int i) {
        if (this.f21108a != null) {
            this.f21108a.onProgressChanged(i);
        }
    }

    public void setShareButtonEnable(boolean z) {
        if (this.f21108a != null) {
            this.f21108a.setShareButtonEnable(z);
        }
    }

    public void onBackPressed(boolean z) {
        if (this.f21108a != null) {
            this.f21108a.onBackPressed(z);
        }
    }

    public void setOptionButton(String str) {
        if (this.f21108a != null) {
            this.f21108a.setOptionButton(str);
        }
    }

    public void setPopMenu(String str) {
        if (this.f21108a != null) {
            this.f21108a.setPopMenu(str);
        }
    }

    public void setNavigationBar(String str) {
        if (this.f21108a != null) {
            this.f21108a.setNavigationBar(str);
        }
    }

    public void setNavigationBarLoading(String str) {
        if (this.f21108a != null) {
            this.f21108a.setNavigationBarLoading(str);
        }
    }
}
