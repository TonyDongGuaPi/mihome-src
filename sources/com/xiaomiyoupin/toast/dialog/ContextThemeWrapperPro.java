package com.xiaomiyoupin.toast.dialog;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.view.ContextThemeWrapper;
import com.xiaomiyoupin.toast.YPDToast;

class ContextThemeWrapperPro extends ContextThemeWrapper {
    private Resources.Theme theme;

    public ContextThemeWrapperPro(Context context) {
        attachBaseContext(context);
    }

    public void setTheme(int i) {
        Resources.Theme theme2 = getTheme();
        if (theme2 != null) {
            onApplyThemeResource(theme2, i, true);
        }
    }

    public Resources.Theme getTheme() {
        if (this.theme != null) {
            return this.theme;
        }
        this.theme = getResources().newTheme();
        return this.theme;
    }

    public Resources getResources() {
        Context applicationContext = YPDToast.getInstance().getApplicationContext();
        if (applicationContext == null) {
            applicationContext = getBaseContext();
        }
        return applicationContext.getResources();
    }

    public AssetManager getAssets() {
        Context applicationContext = YPDToast.getInstance().getApplicationContext();
        if (applicationContext == null) {
            applicationContext = getBaseContext();
        }
        return applicationContext.getAssets();
    }
}
