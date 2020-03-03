package com.xiaomi.mishopsdk.widget.base;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import com.xiaomi.mishopsdk.R;

@Deprecated
public abstract class BaseDialog extends Dialog {
    private Context context;
    private Animation dismissAnim;
    /* access modifiers changed from: private */
    public boolean isDismissing;
    private View rootView;
    private Animation showAnimtion;

    /* access modifiers changed from: protected */
    public abstract View createView(Context context2);

    /* access modifiers changed from: protected */
    public void customLayoutParams(WindowManager.LayoutParams layoutParams) {
    }

    /* access modifiers changed from: protected */
    public Animation getDismissAnimation(Context context2) {
        return null;
    }

    /* access modifiers changed from: protected */
    public Animation getShowAnimation(Context context2) {
        return null;
    }

    public BaseDialog(Context context2) {
        this(context2, R.style.mishopsdk_MMTheme_DataSheet);
    }

    public BaseDialog(Context context2, int i) {
        super(context2, i);
        this.isDismissing = false;
        this.context = context2;
        View createView = createView(context2);
        if (createView != null) {
            setContentView(createView);
        }
        this.showAnimtion = getShowAnimation(context2);
        this.dismissAnim = getDismissAnimation(context2);
        if (this.dismissAnim != null) {
            this.dismissAnim.setAnimationListener(new Animation.AnimationListener() {
                public void onAnimationRepeat(Animation animation) {
                }

                public void onAnimationStart(Animation animation) {
                }

                public void onAnimationEnd(Animation animation) {
                    BaseDialog.super.dismiss();
                    boolean unused = BaseDialog.this.isDismissing = false;
                }
            });
        }
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        customLayoutParams(attributes);
        if (!(this.showAnimtion == null && this.dismissAnim == null)) {
            attributes.windowAnimations = 0;
        }
        onWindowAttributesChanged(attributes);
    }

    public void setContentView(View view) {
        super.setContentView(view);
        this.rootView = view;
    }

    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
        this.rootView = view;
    }

    public void show() {
        super.show();
        if (this.rootView != null && this.showAnimtion != null) {
            this.rootView.startAnimation(this.showAnimtion);
            this.isDismissing = false;
        }
    }

    public void dismiss() {
        if (this.rootView == null || this.dismissAnim == null) {
            super.dismiss();
        } else if (!this.isDismissing) {
            this.isDismissing = true;
            this.rootView.startAnimation(this.dismissAnim);
        }
    }
}
