package com.mi.widget;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.mi.log.LogUtil;
import com.mi.micomponents.R;

public class BaseAlertDialog extends Dialog {

    /* renamed from: a  reason: collision with root package name */
    public static final int f7431a = R.id.positive;
    public static final int b = R.id.negative;
    /* access modifiers changed from: private */
    public static final String d = BaseAlertDialog.class.getSimpleName();
    private static final int p = 300;
    public HandlerActionListener c;
    private TextView e;
    private TextView f;
    private ImageView g;
    private TextView h;
    private CommonButton i;
    private CommonButton j;
    private CommonButton k;
    /* access modifiers changed from: private */
    public RelativeLayout l;
    private View m;
    private View n;
    private Context o;
    private AnimatorSet q = new AnimatorSet();
    private View.OnClickListener r = new View.OnClickListener() {
        public void onClick(View view) {
            LogUtil.b(BaseAlertDialog.d, "dialog dismiss");
            BaseAlertDialog.this.dismiss();
        }
    };

    public interface HandlerActionListener {
        void a();
    }

    public BaseAlertDialog(Context context) {
        super(context, R.style.Widget_Dialog);
        setContentView(R.layout.base_dialog);
        this.e = (TextView) findViewById(R.id.title);
        this.g = (ImageView) findViewById(R.id.icon);
        this.h = (TextView) findViewById(R.id.message);
        this.i = (CommonButton) findViewById(R.id.positive);
        this.j = (CommonButton) findViewById(R.id.negative);
        this.k = (CommonButton) findViewById(R.id.neutral);
        this.m = findViewById(R.id.bottomBar);
        this.n = findViewById(R.id.bottomBar1);
        this.l = (RelativeLayout) findViewById(R.id.basedialog_parent_layout);
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        Window window = getWindow();
        layoutParams.copyFrom(window.getAttributes());
        layoutParams.width = -1;
        layoutParams.height = -2;
        layoutParams.gravity = 80;
        window.setAttributes(layoutParams);
        this.o = context;
        setOnShowListener(new DialogInterface.OnShowListener() {
            public void onShow(DialogInterface dialogInterface) {
                BaseAlertDialog.this.l.setVisibility(0);
                BaseAlertDialog.this.b();
            }
        });
    }

    /* access modifiers changed from: private */
    public void b() {
        this.l.setPivotX(((float) this.l.getMeasuredWidth()) / 2.0f);
        this.l.setPivotY(((float) this.l.getMeasuredHeight()) / 2.0f);
        this.q.playTogether(new Animator[]{ObjectAnimator.ofFloat(this.l, "translationY", new float[]{300.0f, 0.0f}).setDuration(300), ObjectAnimator.ofFloat(this.l, "alpha", new float[]{0.0f, 1.0f}).setDuration(450)});
        this.q.setDuration((long) Math.abs(300));
        this.q.start();
    }

    public void setTitle(int i2) {
        this.e.setVisibility(0);
        this.e.setText(i2);
    }

    public void a(int i2) {
        this.g.setVisibility(0);
        this.g.setImageResource(i2);
    }

    public void b(int i2) {
        a((CharSequence) getContext().getString(i2));
    }

    public void a(CharSequence charSequence) {
        this.h.setVisibility(0);
        this.h.setText(charSequence);
    }

    public void a(View view) {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
    }

    public void a(int i2, View.OnClickListener onClickListener) {
        this.m.setVisibility(0);
        this.i.setVisibility(0);
        this.j.getVisibility();
        if (i2 > 0) {
            this.i.setText(i2);
        }
        if (onClickListener != null) {
            this.i.setOnClickListener(new OnClickListenerWrapper(onClickListener));
        } else {
            this.i.setOnClickListener(this.r);
        }
    }

    public void a(int i2, View.OnClickListener onClickListener, boolean z) {
        this.m.setVisibility(0);
        this.i.setVisibility(0);
        this.j.getVisibility();
        if (i2 > 0) {
            this.i.setText(i2);
        }
        if (onClickListener == null) {
            this.i.setOnClickListener(this.r);
        } else if (z) {
            this.i.setOnClickListener(new OnClickListenerWrapper(onClickListener));
        } else {
            this.i.setOnClickListener(onClickListener);
        }
    }

    public void a(int i2, int i3, View.OnClickListener onClickListener) {
        this.i.setVisibility(0);
        this.m.setVisibility(0);
        this.j.getVisibility();
        if (i2 > 0) {
            this.i.setText(i2);
        }
        if (i3 > 0) {
            this.i.setTextColor(this.o.getResources().getColor(i3));
        }
        if (onClickListener != null) {
            this.i.setOnClickListener(new OnClickListenerWrapper(onClickListener));
        } else {
            this.i.setOnClickListener(this.r);
        }
    }

    public void b(int i2, View.OnClickListener onClickListener) {
        this.j.setVisibility(0);
        this.m.setVisibility(0);
        this.i.getVisibility();
        if (i2 > 0) {
            this.j.setText(i2);
        }
        if (onClickListener != null) {
            this.j.setOnClickListener(new OnClickListenerWrapper(onClickListener));
        } else {
            this.j.setOnClickListener(this.r);
        }
    }

    public void c(int i2, View.OnClickListener onClickListener) {
        this.n.setVisibility(0);
        this.m.setVisibility(8);
        if (i2 > 0) {
            this.k.setText(i2);
        }
        if (onClickListener != null) {
            this.k.setOnClickListener(new OnClickListenerWrapper(onClickListener));
        } else {
            this.k.setOnClickListener(this.r);
        }
    }

    private class OnClickListenerWrapper implements View.OnClickListener {
        private View.OnClickListener b;

        public OnClickListenerWrapper(View.OnClickListener onClickListener) {
            this.b = onClickListener;
        }

        public void onClick(View view) {
            if (this.b != null) {
                this.b.onClick(view);
            }
            BaseAlertDialog.this.dismiss();
        }
    }

    public void dismiss() {
        super.dismiss();
        if (this.c != null) {
            this.c.a();
        }
    }
}
