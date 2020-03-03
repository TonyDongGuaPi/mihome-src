package com.mi.global.bbs.view;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;

public class UploadImageView_ViewBinding implements Unbinder {
    private UploadImageView target;

    @UiThread
    public UploadImageView_ViewBinding(UploadImageView uploadImageView) {
        this(uploadImageView, uploadImageView);
    }

    @UiThread
    public UploadImageView_ViewBinding(UploadImageView uploadImageView, View view) {
        this.target = uploadImageView;
        uploadImageView.imageItem = (ImageView) Utils.findRequiredViewAsType(view, R.id.image_item, "field 'imageItem'", ImageView.class);
        uploadImageView.progressItem = (ProgressBar) Utils.findRequiredViewAsType(view, R.id.progress_item, "field 'progressItem'", ProgressBar.class);
        uploadImageView.textItem = (TextView) Utils.findRequiredViewAsType(view, R.id.text_item, "field 'textItem'", TextView.class);
    }

    @CallSuper
    public void unbind() {
        UploadImageView uploadImageView = this.target;
        if (uploadImageView != null) {
            this.target = null;
            uploadImageView.imageItem = null;
            uploadImageView.progressItem = null;
            uploadImageView.textItem = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
