package com.mics.core.ui.widget;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mics.R;
import com.mics.network.NetworkManager;
import com.mics.widget.util.MiCSToastManager;

public class RobotBottom implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private TextView f7756a;
    private LinearLayout b;
    private TextView c;
    private TextView d;
    private TextView e;
    private String f;
    private OnClickFeedback g;

    public interface OnClickFeedback {
        void a(String str, int i);
    }

    public RobotBottom(View view) {
        this.f7756a = (TextView) view.findViewById(R.id.tv_question);
        this.b = (LinearLayout) view.findViewById(R.id.linear_question_feedback);
        this.c = (TextView) view.findViewById(R.id.tv_question_feedback_yes);
        this.d = (TextView) view.findViewById(R.id.tv_question_feedback_no);
        this.e = (TextView) view.findViewById(R.id.tv_question_feedback_over);
    }

    public void a(String str, boolean z) {
        this.f = str;
        if (str != null) {
            this.f7756a.setText("您的问题是否解决？");
            this.f7756a.setVisibility(0);
            this.e.setText("感谢您的反馈");
            this.e.setVisibility(8);
            if (z) {
                this.b.setVisibility(8);
                this.c.setVisibility(8);
                this.d.setVisibility(8);
                this.e.setVisibility(0);
                return;
            }
            this.b.setVisibility(0);
            this.c.setVisibility(0);
            this.d.setVisibility(0);
            this.c.setText("已解决");
            this.d.setText("未解决");
            this.c.setOnClickListener(this);
            this.d.setOnClickListener(this);
            return;
        }
        this.f7756a.setVisibility(8);
        this.b.setVisibility(8);
        this.c.setVisibility(8);
        this.d.setVisibility(8);
        this.e.setVisibility(8);
    }

    public void onClick(View view) {
        if (!NetworkManager.b()) {
            MiCSToastManager.a().a((CharSequence) "网络异常");
            return;
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.4f);
        alphaAnimation.setDuration(400);
        this.b.setVisibility(8);
        this.c.setVisibility(8);
        this.d.setVisibility(8);
        this.b.setAnimation(alphaAnimation);
        alphaAnimation.start();
        AlphaAnimation alphaAnimation2 = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation2.setDuration(400);
        this.e.setVisibility(0);
        this.e.setAnimation(alphaAnimation2);
        alphaAnimation2.start();
        if (this.g != null) {
            if (view.getId() == this.c.getId()) {
                this.g.a(this.f, 1);
            } else if (view.getId() == this.d.getId()) {
                this.g.a(this.f, 0);
            }
        }
    }

    public void a(OnClickFeedback onClickFeedback) {
        this.g = onClickFeedback;
    }
}
