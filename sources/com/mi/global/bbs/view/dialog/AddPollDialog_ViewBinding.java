package com.mi.global.bbs.view.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.Editor.FontEditText;

public class AddPollDialog_ViewBinding implements Unbinder {
    private AddPollDialog target;
    private View view2131492959;
    private View view2131493194;
    private View view2131493196;

    @UiThread
    public AddPollDialog_ViewBinding(AddPollDialog addPollDialog) {
        this(addPollDialog, addPollDialog.getWindow().getDecorView());
    }

    @UiThread
    public AddPollDialog_ViewBinding(final AddPollDialog addPollDialog, View view) {
        this.target = addPollDialog;
        addPollDialog.mPollItemO = (FontEditText) Utils.findRequiredViewAsType(view, R.id.poll_item_o, "field 'mPollItemO'", FontEditText.class);
        addPollDialog.mPollItemT = (FontEditText) Utils.findRequiredViewAsType(view, R.id.poll_item_t, "field 'mPollItemT'", FontEditText.class);
        addPollDialog.mPollItemContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.poll_item_container, "field 'mPollItemContainer'", LinearLayout.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.add_poll_item_bt, "field 'mAddPollItemBt' and method 'onViewClicked'");
        addPollDialog.mAddPollItemBt = (TextView) Utils.castView(findRequiredView, R.id.add_poll_item_bt, "field 'mAddPollItemBt'", TextView.class);
        this.view2131492959 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addPollDialog.onViewClicked(view);
            }
        });
        addPollDialog.mDialogRoot = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.dialog_root, "field 'mDialogRoot'", FrameLayout.class);
        addPollDialog.mDialogContent = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.dialog_content, "field 'mDialogContent'", LinearLayout.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.dialog_close_bt, "method 'onViewClicked'");
        this.view2131493194 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addPollDialog.onViewClicked(view);
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.dialog_done_bt, "method 'onViewClicked'");
        this.view2131493196 = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                addPollDialog.onViewClicked(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        AddPollDialog addPollDialog = this.target;
        if (addPollDialog != null) {
            this.target = null;
            addPollDialog.mPollItemO = null;
            addPollDialog.mPollItemT = null;
            addPollDialog.mPollItemContainer = null;
            addPollDialog.mAddPollItemBt = null;
            addPollDialog.mDialogRoot = null;
            addPollDialog.mDialogContent = null;
            this.view2131492959.setOnClickListener((View.OnClickListener) null);
            this.view2131492959 = null;
            this.view2131493194.setOnClickListener((View.OnClickListener) null);
            this.view2131493194 = null;
            this.view2131493196.setOnClickListener((View.OnClickListener) null);
            this.view2131493196 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
