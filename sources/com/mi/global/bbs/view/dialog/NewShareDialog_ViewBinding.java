package com.mi.global.bbs.view.dialog;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;

public class NewShareDialog_ViewBinding implements Unbinder {
    private NewShareDialog target;

    @UiThread
    public NewShareDialog_ViewBinding(NewShareDialog newShareDialog) {
        this(newShareDialog, newShareDialog.getWindow().getDecorView());
    }

    @UiThread
    public NewShareDialog_ViewBinding(NewShareDialog newShareDialog, View view) {
        this.target = newShareDialog;
        newShareDialog.shareListView = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.share_list_view, "field 'shareListView'", RecyclerView.class);
        newShareDialog.saveLy = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.save_ly, "field 'saveLy'", LinearLayout.class);
        newShareDialog.copyLinkLy = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.copy_link, "field 'copyLinkLy'", LinearLayout.class);
        newShareDialog.smsLy = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.sms_ly, "field 'smsLy'", LinearLayout.class);
        newShareDialog.emailLy = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.email_ly, "field 'emailLy'", LinearLayout.class);
        newShareDialog.reportLy = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.report_ly, "field 'reportLy'", LinearLayout.class);
        newShareDialog.newShareTopLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.new_share_top_layout, "field 'newShareTopLayout'", LinearLayout.class);
        newShareDialog.newShareImageLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.new_share_image_layout, "field 'newShareImageLayout'", LinearLayout.class);
        newShareDialog.newShareLinkLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.new_share_link_layout, "field 'newShareLinkLayout'", LinearLayout.class);
        newShareDialog.shareToTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.share_to_title, "field 'shareToTitle'", TextView.class);
        newShareDialog.shareImageImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.share_image_img, "field 'shareImageImg'", ImageView.class);
        newShareDialog.shareLinkImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.share_link_img, "field 'shareLinkImg'", ImageView.class);
        newShareDialog.shareLinkSelectImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.share_link_select, "field 'shareLinkSelectImg'", ImageView.class);
        newShareDialog.shareImgSelectImg = (ImageView) Utils.findRequiredViewAsType(view, R.id.share_image_select, "field 'shareImgSelectImg'", ImageView.class);
        newShareDialog.shareLinkDes = (TextView) Utils.findRequiredViewAsType(view, R.id.share_link_des, "field 'shareLinkDes'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        NewShareDialog newShareDialog = this.target;
        if (newShareDialog != null) {
            this.target = null;
            newShareDialog.shareListView = null;
            newShareDialog.saveLy = null;
            newShareDialog.copyLinkLy = null;
            newShareDialog.smsLy = null;
            newShareDialog.emailLy = null;
            newShareDialog.reportLy = null;
            newShareDialog.newShareTopLayout = null;
            newShareDialog.newShareImageLayout = null;
            newShareDialog.newShareLinkLayout = null;
            newShareDialog.shareToTitle = null;
            newShareDialog.shareImageImg = null;
            newShareDialog.shareLinkImg = null;
            newShareDialog.shareLinkSelectImg = null;
            newShareDialog.shareImgSelectImg = null;
            newShareDialog.shareLinkDes = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
