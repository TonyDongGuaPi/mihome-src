package com.mi.global.bbs.view.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;

public class ShareDialog_ViewBinding implements Unbinder {
    private ShareDialog target;

    @UiThread
    public ShareDialog_ViewBinding(ShareDialog shareDialog) {
        this(shareDialog, shareDialog.getWindow().getDecorView());
    }

    @UiThread
    public ShareDialog_ViewBinding(ShareDialog shareDialog, View view) {
        this.target = shareDialog;
        shareDialog.shareListView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.share_list_view, "field 'shareListView'", RecyclerView.class);
        shareDialog.shareLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.share_layout, "field 'shareLayout'", LinearLayout.class);
    }

    @CallSuper
    public void unbind() {
        ShareDialog shareDialog = this.target;
        if (shareDialog != null) {
            this.target = null;
            shareDialog.shareListView = null;
            shareDialog.shareLayout = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
