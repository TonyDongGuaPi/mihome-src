package com.mi.global.bbs.view.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;

public class EmoDialog_ViewBinding implements Unbinder {
    private EmoDialog target;

    @UiThread
    public EmoDialog_ViewBinding(EmoDialog emoDialog, View view) {
        this.target = emoDialog;
        emoDialog.mEmoPager = (ViewPager) Utils.findRequiredViewAsType(view, R.id.emo_pager, "field 'mEmoPager'", ViewPager.class);
        emoDialog.mEmoPageIndicate = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.emo_page_indicate, "field 'mEmoPageIndicate'", LinearLayout.class);
    }

    @CallSuper
    public void unbind() {
        EmoDialog emoDialog = this.target;
        if (emoDialog != null) {
            this.target = null;
            emoDialog.mEmoPager = null;
            emoDialog.mEmoPageIndicate = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
