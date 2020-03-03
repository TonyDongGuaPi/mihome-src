package com.mi.global.bbs.view.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;

public class UpdateDialog_ViewBinding implements Unbinder {
    private UpdateDialog target;

    @UiThread
    public UpdateDialog_ViewBinding(UpdateDialog updateDialog) {
        this(updateDialog, updateDialog.getWindow().getDecorView());
    }

    @UiThread
    public UpdateDialog_ViewBinding(UpdateDialog updateDialog, View view) {
        this.target = updateDialog;
        updateDialog.updateDesContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.update_dialog_des_content, "field 'updateDesContent'", LinearLayout.class);
        updateDialog.updateDialogBt = (TextView) Utils.findRequiredViewAsType(view, R.id.update_dialog_bt, "field 'updateDialogBt'", TextView.class);
        updateDialog.updateDialogCloseBt = (ImageView) Utils.findRequiredViewAsType(view, R.id.update_dialog_close_bt, "field 'updateDialogCloseBt'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        UpdateDialog updateDialog = this.target;
        if (updateDialog != null) {
            this.target = null;
            updateDialog.updateDesContent = null;
            updateDialog.updateDialogBt = null;
            updateDialog.updateDialogCloseBt = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
