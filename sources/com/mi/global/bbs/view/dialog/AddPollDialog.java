package com.mi.global.bbs.view.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.mi.global.bbs.R;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.utils.ViewUtils;
import com.mi.global.bbs.view.Editor.EndClickEditText;
import com.mi.global.bbs.view.Editor.FontEditText;
import java.util.ArrayList;
import java.util.List;

public class AddPollDialog extends Dialog {
    private static final long ANIMATION_TIME = 200;
    private static final int MAX_ITEM_COUNT = 5;
    private BaseActivity ctx;
    private LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
    @BindView(2131492959)
    TextView mAddPollItemBt;
    @BindView(2131493195)
    LinearLayout mDialogContent;
    @BindView(2131493198)
    FrameLayout mDialogRoot;
    /* access modifiers changed from: private */
    public Animation mInAnimation;
    OnAddCompleteListener mOnAddFinishListener;
    private Animation mOutAnimation;
    @BindView(2131493799)
    LinearLayout mPollItemContainer;
    @BindView(2131493800)
    FontEditText mPollItemO;
    @BindView(2131493801)
    FontEditText mPollItemT;

    public interface OnAddCompleteListener {
        void onAddComplete(List<String> list);
    }

    public AddPollDialog(BaseActivity baseActivity, List<String> list) {
        super(baseActivity, R.style.AddPollDialog);
        this.ctx = baseActivity;
        setContentView(R.layout.bbs_add_poll_dialog);
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
                AddPollDialog.this.mDialogContent.startAnimation(AddPollDialog.this.mInAnimation);
            }
        });
        this.layoutParams.topMargin = baseActivity.getResources().getDimensionPixelOffset(R.dimen.common_padding);
        showOptions(list);
    }

    private void showOptions(List<String> list) {
        for (int i = 0; i < list.size(); i++) {
            String str = list.get(i);
            if (i < 2) {
                ((FontEditText) this.mPollItemContainer.getChildAt(i)).setText(str);
            } else {
                addPollItem(str);
            }
        }
    }

    @OnClick({2131493194, 2131493196, 2131492959})
    public void onViewClicked(View view) {
        if (view.getId() == R.id.dialog_close_bt) {
            dismiss();
        } else if (view.getId() == R.id.dialog_done_bt) {
            finishAndDismiss();
        } else if (view.getId() == R.id.add_poll_item_bt) {
            addPollItem("");
        }
    }

    private void finishAndDismiss() {
        int childCount = this.mPollItemContainer.getChildCount();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < childCount; i++) {
            String obj = ((FontEditText) this.mPollItemContainer.getChildAt(i)).getText().toString();
            if (!TextUtils.isEmpty(obj)) {
                arrayList.add(obj);
            }
        }
        if (this.mOnAddFinishListener != null && arrayList.size() > 1) {
            this.mOnAddFinishListener.onAddComplete(arrayList);
            dismiss();
        }
    }

    private void addPollItem(String str) {
        EndClickEditText endClickEditText = (EndClickEditText) LayoutInflater.from(this.ctx).inflate(R.layout.bbs_dialog_poll_item, this.mPollItemContainer, false);
        if (!TextUtils.isEmpty(str)) {
            endClickEditText.setText(str);
        }
        endClickEditText.setOnEndClickListener(new EndClickEditText.OnEndClickListener() {
            public void onEndClick(View view) {
                AddPollDialog.this.mPollItemContainer.removeView(view);
                AddPollDialog.this.showAddButtonOrNot();
            }
        });
        this.mPollItemContainer.addView(endClickEditText, this.layoutParams);
        showAddButtonOrNot();
    }

    /* access modifiers changed from: private */
    public void showAddButtonOrNot() {
        this.mAddPollItemBt.setVisibility(this.mPollItemContainer.getChildCount() >= 5 ? 8 : 0);
    }

    public void dismiss() {
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(200);
        this.mDialogContent.startAnimation(this.mOutAnimation);
        this.mDialogRoot.startAnimation(alphaAnimation);
        this.mOutAnimation.setAnimationListener(new ViewUtils.DefaultAnimationListener() {
            public void onAnimationEnd(Animation animation) {
                AddPollDialog.super.dismiss();
            }
        });
    }

    public void setOnAddCompleteListener(OnAddCompleteListener onAddCompleteListener) {
        this.mOnAddFinishListener = onAddCompleteListener;
    }
}
