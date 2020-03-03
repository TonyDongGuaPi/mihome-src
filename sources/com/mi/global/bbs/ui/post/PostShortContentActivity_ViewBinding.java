package com.mi.global.bbs.ui.post;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.CheckedImageView;
import com.mi.global.bbs.view.Editor.FontEditText;
import com.mi.global.bbs.view.Editor.FontTextView;

public class PostShortContentActivity_ViewBinding implements Unbinder {
    private PostShortContentActivity target;

    @UiThread
    public PostShortContentActivity_ViewBinding(PostShortContentActivity postShortContentActivity) {
        this(postShortContentActivity, postShortContentActivity.getWindow().getDecorView());
    }

    @UiThread
    public PostShortContentActivity_ViewBinding(PostShortContentActivity postShortContentActivity, View view) {
        this.target = postShortContentActivity;
        postShortContentActivity.mContent = (FontEditText) Utils.findRequiredViewAsType(view, R.id.content, "field 'mContent'", FontEditText.class);
        postShortContentActivity.mNumIndicate = (FontTextView) Utils.findRequiredViewAsType(view, R.id.num_indicate, "field 'mNumIndicate'", FontTextView.class);
        postShortContentActivity.mExtraContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.extra_container, "field 'mExtraContainer'", LinearLayout.class);
        postShortContentActivity.mAddImage = (CheckedImageView) Utils.findRequiredViewAsType(view, R.id.add_image, "field 'mAddImage'", CheckedImageView.class);
        postShortContentActivity.mAddUrl = (CheckedImageView) Utils.findRequiredViewAsType(view, R.id.add_url, "field 'mAddUrl'", CheckedImageView.class);
    }

    @CallSuper
    public void unbind() {
        PostShortContentActivity postShortContentActivity = this.target;
        if (postShortContentActivity != null) {
            this.target = null;
            postShortContentActivity.mContent = null;
            postShortContentActivity.mNumIndicate = null;
            postShortContentActivity.mExtraContainer = null;
            postShortContentActivity.mAddImage = null;
            postShortContentActivity.mAddUrl = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
