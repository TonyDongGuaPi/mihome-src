package com.mi.global.bbs.ui.post;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.CategoryView;
import com.mi.global.bbs.view.Editor.FontEditText;
import com.mi.global.bbs.view.Editor.RichEditor;
import com.mi.global.bbs.view.EditorToolbar;

public class PostActivity_ViewBinding implements Unbinder {
    private PostActivity target;

    @UiThread
    public PostActivity_ViewBinding(PostActivity postActivity) {
        this(postActivity, postActivity.getWindow().getDecorView());
    }

    @UiThread
    public PostActivity_ViewBinding(PostActivity postActivity, View view) {
        this.target = postActivity;
        postActivity.mRichEditor = (RichEditor) Utils.findRequiredViewAsType(view, R.id.mRichEditor, "field 'mRichEditor'", RichEditor.class);
        postActivity.mEditorToolbar = (EditorToolbar) Utils.findRequiredViewAsType(view, R.id.mEditorToolbar, "field 'mEditorToolbar'", EditorToolbar.class);
        postActivity.mEditorColorToolbar = (EditorToolbar) Utils.findRequiredViewAsType(view, R.id.mEditorColorToolbar, "field 'mEditorColorToolbar'", EditorToolbar.class);
        postActivity.mCategoryView = (CategoryView) Utils.findRequiredViewAsType(view, R.id.mCategoryView, "field 'mCategoryView'", CategoryView.class);
        postActivity.mCategoryGroupView = (CategoryView) Utils.findRequiredViewAsType(view, R.id.mCategoryGroupView, "field 'mCategoryGroupView'", CategoryView.class);
        postActivity.mCategoryContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.mCategoryContainer, "field 'mCategoryContainer'", LinearLayout.class);
        postActivity.mTitleEditView = (FontEditText) Utils.findRequiredViewAsType(view, R.id.mTitleEditView, "field 'mTitleEditView'", FontEditText.class);
        postActivity.mVoteItemContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.vote_item_container, "field 'mVoteItemContainer'", LinearLayout.class);
        postActivity.mVoteContainer = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.vote_container, "field 'mVoteContainer'", FrameLayout.class);
    }

    @CallSuper
    public void unbind() {
        PostActivity postActivity = this.target;
        if (postActivity != null) {
            this.target = null;
            postActivity.mRichEditor = null;
            postActivity.mEditorToolbar = null;
            postActivity.mEditorColorToolbar = null;
            postActivity.mCategoryView = null;
            postActivity.mCategoryGroupView = null;
            postActivity.mCategoryContainer = null;
            postActivity.mTitleEditView = null;
            postActivity.mVoteItemContainer = null;
            postActivity.mVoteContainer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
