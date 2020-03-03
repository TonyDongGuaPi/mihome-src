package com.mi.global.bbs.ui.column;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;

public class QuestionActivity_ViewBinding implements Unbinder {
    private QuestionActivity target;
    private View view2131493396;
    private View view2131493746;

    @UiThread
    public QuestionActivity_ViewBinding(QuestionActivity questionActivity) {
        this(questionActivity, questionActivity.getWindow().getDecorView());
    }

    @UiThread
    public QuestionActivity_ViewBinding(final QuestionActivity questionActivity, View view) {
        this.target = questionActivity;
        questionActivity.mStep = (TextView) Utils.findRequiredViewAsType(view, R.id.step, "field 'mStep'", TextView.class);
        questionActivity.mQuestionText = (TextView) Utils.findRequiredViewAsType(view, R.id.question_text, "field 'mQuestionText'", TextView.class);
        questionActivity.mList = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.list, "field 'mList'", RecyclerView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.hint_text, "field 'mHintText' and method 'onViewClicked'");
        questionActivity.mHintText = (TextView) Utils.castView(findRequiredView, R.id.hint_text, "field 'mHintText'", TextView.class);
        this.view2131493396 = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                questionActivity.onViewClicked(view);
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.next, "field 'mNext' and method 'onViewClicked'");
        questionActivity.mNext = (TextView) Utils.castView(findRequiredView2, R.id.next, "field 'mNext'", TextView.class);
        this.view2131493746 = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                questionActivity.onViewClicked(view);
            }
        });
        questionActivity.mToolbarAgent = Utils.findRequiredView(view, R.id.toolbar_agent, "field 'mToolbarAgent'");
    }

    @CallSuper
    public void unbind() {
        QuestionActivity questionActivity = this.target;
        if (questionActivity != null) {
            this.target = null;
            questionActivity.mStep = null;
            questionActivity.mQuestionText = null;
            questionActivity.mList = null;
            questionActivity.mHintText = null;
            questionActivity.mNext = null;
            questionActivity.mToolbarAgent = null;
            this.view2131493396.setOnClickListener((View.OnClickListener) null);
            this.view2131493396 = null;
            this.view2131493746.setOnClickListener((View.OnClickListener) null);
            this.view2131493746 = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
