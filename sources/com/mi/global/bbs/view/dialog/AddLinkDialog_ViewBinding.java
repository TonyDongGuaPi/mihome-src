package com.mi.global.bbs.view.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.Editor.FontEditText;

public class AddLinkDialog_ViewBinding implements Unbinder {
    private AddLinkDialog target;
    private View view2131493194;
    private View view2131493196;

    @UiThread
    public AddLinkDialog_ViewBinding(AddLinkDialog addLinkDialog) {
        this(addLinkDialog, addLinkDialog.getWindow().getDecorView());
    }

    @UiThread
    public AddLinkDialog_ViewBinding(final AddLinkDialog addLinkDialog, View view) {
        this.target = addLinkDialog;
        addLinkDialog.mAddLinkText = (FontEditText) Utils.findRequiredViewAsType(view, R.id.add_link_edit_view, "field 'mAddLinkText'", FontEditText.class);
        addLinkDialog.mDialogRoot = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.dialog_root, "field 'mDialogRoot'", FrameLayout.class);
        addLinkDialog.mDialogContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.dialog_content, "field 'mDialogContent'", LinearLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.dialog_close_bt, "method 'onViewClicked'");
        this.view2131493194 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addLinkDialog.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.dialog_done_bt, "method 'onViewClicked'");
        this.view2131493196 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addLinkDialog.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        AddLinkDialog addLinkDialog = this.target;
        if (addLinkDialog != null) {
            this.target = null;
            addLinkDialog.mAddLinkText = null;
            addLinkDialog.mDialogRoot = null;
            addLinkDialog.mDialogContent = null;
            this.view2131493194.setOnClickListener((View.OnClickListener) null);
            this.view2131493194 = null;
            this.view2131493196.setOnClickListener((View.OnClickListener) null);
            this.view2131493196 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
