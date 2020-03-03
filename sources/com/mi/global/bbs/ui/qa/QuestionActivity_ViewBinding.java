package com.mi.global.bbs.ui.qa;

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
import com.mi.global.bbs.view.Editor.FontTextView;
import com.mi.global.bbs.view.Editor.RichEditor;
import com.mi.global.bbs.view.EditorToolbar;

public class QuestionActivity_ViewBinding implements Unbinder {
    private QuestionActivity target;

    @UiThread
    public QuestionActivity_ViewBinding(QuestionActivity questionActivity) {
        this(questionActivity, questionActivity.getWindow().getDecorView());
    }

    @UiThread
    public QuestionActivity_ViewBinding(QuestionActivity questionActivity, View view) {
        this.target = questionActivity;
        questionActivity.mRichEditor = (RichEditor) Utils.findRequiredViewAsType(view, R.id.mRichEditor, "field 'mRichEditor'", RichEditor.class);
        questionActivity.mEditorToolbar = (EditorToolbar) Utils.findRequiredViewAsType(view, R.id.mEditorToolbar, "field 'mEditorToolbar'", EditorToolbar.class);
        questionActivity.mEditorColorToolbar = (EditorToolbar) Utils.findRequiredViewAsType(view, R.id.mEditorColorToolbar, "field 'mEditorColorToolbar'", EditorToolbar.class);
        questionActivity.mCategoryView = (CategoryView) Utils.findRequiredViewAsType(view, R.id.mCategoryView, "field 'mCategoryView'", CategoryView.class);
        questionActivity.mCategoryGroupView = (CategoryView) Utils.findRequiredViewAsType(view, R.id.mCategoryGroupView, "field 'mCategoryGroupView'", CategoryView.class);
        questionActivity.mCategoryContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.mCategoryContainer, "field 'mCategoryContainer'", LinearLayout.class);
        questionActivity.inviteNotifyLayout = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.invite_notify_layout, "field 'inviteNotifyLayout'", LinearLayout.class);
        questionActivity.inviteNotify = (FontTextView) Utils.findRequiredViewAsType(view, R.id.invite_notify, "field 'inviteNotify'", FontTextView.class);
        questionActivity.mTitleEditView = (FontEditText) Utils.findRequiredViewAsType(view, R.id.mTitleEditView, "field 'mTitleEditView'", FontEditText.class);
        questionActivity.mVoteItemContainer = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.vote_item_container, "field 'mVoteItemContainer'", LinearLayout.class);
        questionActivity.mVoteContainer = (FrameLayout) Utils.findRequiredViewAsType(view, R.id.vote_container, "field 'mVoteContainer'", FrameLayout.class);
    }

    @CallSuper
    public void unbind() {
        QuestionActivity questionActivity = this.target;
        if (questionActivity != null) {
            this.target = null;
            questionActivity.mRichEditor = null;
            questionActivity.mEditorToolbar = null;
            questionActivity.mEditorColorToolbar = null;
            questionActivity.mCategoryView = null;
            questionActivity.mCategoryGroupView = null;
            questionActivity.mCategoryContainer = null;
            questionActivity.inviteNotifyLayout = null;
            questionActivity.inviteNotify = null;
            questionActivity.mTitleEditView = null;
            questionActivity.mVoteItemContainer = null;
            questionActivity.mVoteContainer = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
