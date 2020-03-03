package com.mi.global.bbs.view.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.mi.global.bbs.R;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.utils.ImeUtils;
import com.mi.global.bbs.utils.TextHelper;
import com.mi.global.bbs.utils.ViewUtils;
import com.mi.global.bbs.view.Editor.FontEditText;

public class AddLinkDialog extends Dialog {
    private static final long ANIMATION_TIME = 200;
    private BaseActivity ctx;
    private LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
    AddLinkCompletedListener mAddLinkCompletedListener;
    @BindView(2131492958)
    FontEditText mAddLinkText;
    @BindView(2131493195)
    LinearLayout mDialogContent;
    @BindView(2131493198)
    FrameLayout mDialogRoot;
    /* access modifiers changed from: private */
    public Animation mInAnimation;
    private Animation mOutAnimation;

    public interface AddLinkCompletedListener {
        void onAddLinkComplete(String str);
    }

    public AddLinkDialog(BaseActivity baseActivity) {
        super(baseActivity, R.style.AddPollDialog);
        this.ctx = baseActivity;
        setContentView(R.layout.bbs_add_link_dialog);
        ButterKnife.bind((Dialog) this);
        WindowManager.LayoutParams layoutParams2 = new WindowManager.LayoutParams();
        Window window = getWindow();
        layoutParams2.copyFrom(window.getAttributes());
        layoutParams2.width = -1;
        layoutParams2.height = -1;
        layoutParams2.gravity = 80;
        window.setAttributes(layoutParams2);
        this.mInAnimation = AnimationUtils.loadAnimation(baseActivity, R.anim.slide_from_bottom_to_top_enter);
        this.mOutAnimation = AnimationUtils.loadAnimation(baseActivity, R.anim.slide_from_top_to_bottom_out);
        this.mOutAnimation.setDuration(200);
        this.mInAnimation.setDuration(200);
        setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                AddLinkDialog.this.mDialogContent.startAnimation(AddLinkDialog.this.mInAnimation);
            }
        });
        this.mInAnimation.setAnimationListener(new ViewUtils.DefaultAnimationListener() {
            public void onAnimationEnd(Animation animation) {
                ImeUtils.showIme(AddLinkDialog.this.mAddLinkText);
            }
        });
        this.layoutParams.topMargin = baseActivity.getResources().getDimensionPixelOffset(R.dimen.common_padding);
    }

    @OnClick({2131493194, 2131493196})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.dialog_close_bt) {
            dismiss();
        } else if (view.getId() == R.id.dialog_done_bt) {
            finishAndDismiss();
        }
    }

    private void finishAndDismiss() {
        String obj = this.mAddLinkText.getText().toString();
        if (!TextUtils.isEmpty(obj) && TextHelper.isLink(obj) && this.mAddLinkCompletedListener != null) {
            this.mAddLinkCompletedListener.onAddLinkComplete(obj);
        }
        dismiss();
    }

    public void dismiss() {
        ImeUtils.hideIme(this.mAddLinkText);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(200);
        this.mDialogContent.startAnimation(this.mOutAnimation);
        this.mDialogRoot.startAnimation(alphaAnimation);
        this.mOutAnimation.setAnimationListener(new ViewUtils.DefaultAnimationListener() {
            public void onAnimationEnd(Animation animation) {
                AddLinkDialog.super.dismiss();
            }
        });
    }

    public void setAddLinkCompletedListener(AddLinkCompletedListener addLinkCompletedListener) {
        this.mAddLinkCompletedListener = addLinkCompletedListener;
    }
}
