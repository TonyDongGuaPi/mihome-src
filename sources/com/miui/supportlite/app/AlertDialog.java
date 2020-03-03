package com.miui.supportlite.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.miui.supportlite.R;

public class AlertDialog extends DialogFragment implements DialogInterface {
    private static final String b = "AlertDialog";

    /* renamed from: a  reason: collision with root package name */
    protected Param f8196a;
    private TextView c;
    private CheckBox d;
    private Button e;
    private Button f;
    private TextUpdater g;

    public interface TextUpdater {
        void update(TextView textView, CharSequence charSequence);
    }

    public void cancel() {
    }

    private static class Param {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public Context f8198a;
        /* access modifiers changed from: private */
        public boolean b;
        /* access modifiers changed from: private */
        public CharSequence c;
        /* access modifiers changed from: private */
        public CharSequence d;
        /* access modifiers changed from: private */
        public CharSequence e;
        /* access modifiers changed from: private */
        public DialogInterface.OnClickListener f;
        /* access modifiers changed from: private */
        public CharSequence g;
        /* access modifiers changed from: private */
        public DialogInterface.OnClickListener h;
        /* access modifiers changed from: private */
        public boolean i;
        /* access modifiers changed from: private */
        public CharSequence j;
        /* access modifiers changed from: private */
        public View k;
        /* access modifiers changed from: private */
        public DialogInterface.OnShowListener l;
        /* access modifiers changed from: private */
        public DialogInterface.OnDismissListener m;
        /* access modifiers changed from: private */
        public DialogInterface.OnKeyListener n;

        private Param() {
            this.b = true;
        }
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        Param f8197a = new Param();

        public Builder(Context context) {
            if (context != null) {
                Context unused = this.f8197a.f8198a = context.getApplicationContext();
                return;
            }
            throw new NullPointerException();
        }

        public Builder a(boolean z) {
            boolean unused = this.f8197a.b = z;
            return this;
        }

        public Builder a(int i) {
            return a((CharSequence) this.f8197a.f8198a.getString(i));
        }

        public Builder a(CharSequence charSequence) {
            CharSequence unused = this.f8197a.c = charSequence;
            return this;
        }

        public Builder b(int i) {
            return b((CharSequence) this.f8197a.f8198a.getString(i));
        }

        public Builder b(CharSequence charSequence) {
            CharSequence unused = this.f8197a.d = charSequence;
            return this;
        }

        public Builder a(int i, DialogInterface.OnClickListener onClickListener) {
            return a((CharSequence) this.f8197a.f8198a.getString(i), onClickListener);
        }

        public Builder a(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            CharSequence unused = this.f8197a.e = charSequence;
            DialogInterface.OnClickListener unused2 = this.f8197a.f = onClickListener;
            return this;
        }

        public Builder b(int i, DialogInterface.OnClickListener onClickListener) {
            return b((CharSequence) this.f8197a.f8198a.getString(i), onClickListener);
        }

        public Builder b(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            CharSequence unused = this.f8197a.g = charSequence;
            DialogInterface.OnClickListener unused2 = this.f8197a.h = onClickListener;
            return this;
        }

        public Builder a(boolean z, CharSequence charSequence) {
            boolean unused = this.f8197a.i = z;
            CharSequence unused2 = this.f8197a.j = charSequence;
            return this;
        }

        public Builder a(View view) {
            View unused = this.f8197a.k = view;
            return this;
        }

        public Builder a(DialogInterface.OnShowListener onShowListener) {
            DialogInterface.OnShowListener unused = this.f8197a.l = onShowListener;
            return this;
        }

        public Builder a(DialogInterface.OnDismissListener onDismissListener) {
            DialogInterface.OnDismissListener unused = this.f8197a.m = onDismissListener;
            return this;
        }

        public Builder a(DialogInterface.OnKeyListener onKeyListener) {
            DialogInterface.OnKeyListener unused = this.f8197a.n = onKeyListener;
            return this;
        }

        public AlertDialog a() {
            AlertDialog alertDialog = new AlertDialog();
            alertDialog.f8196a = this.f8197a;
            return alertDialog;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        boolean z;
        if (this.f8196a == null) {
            this.f8196a = new Param();
            new Handler().post(new Runnable() {
                public final void run() {
                    AlertDialog.this.e();
                }
            });
        }
        Dialog dialog = getDialog();
        Window window = dialog.getWindow();
        View inflate = layoutInflater.inflate(R.layout.miuisupport_alert_dialog, (ViewGroup) window.findViewById(16908290), false);
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setGravity(80);
        window.setLayout(-1, -2);
        window.setDimAmount(0.5f);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() {
            public final void onShow(DialogInterface dialogInterface) {
                AlertDialog.this.a(dialogInterface);
            }
        });
        dialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            public final boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return AlertDialog.this.a(dialogInterface, i, keyEvent);
            }
        });
        setCancelable(this.f8196a.b);
        View findViewById = inflate.findViewById(R.id.content);
        if (this.f8196a.b) {
            findViewById.setOnClickListener((View.OnClickListener) null);
            inflate.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    AlertDialog.this.c(view);
                }
            });
        }
        TextView textView = (TextView) inflate.findViewById(R.id.title);
        if (!TextUtils.isEmpty(this.f8196a.c)) {
            textView.setText(this.f8196a.c);
        } else {
            textView.setVisibility(8);
        }
        TextView textView2 = (TextView) inflate.findViewById(R.id.message);
        if (!TextUtils.isEmpty(this.f8196a.d)) {
            if (this.g != null) {
                this.g.update(textView2, this.f8196a.d);
            } else {
                textView2.setText(this.f8196a.d);
            }
            if (textView.getVisibility() == 8) {
                textView2.setPadding(textView2.getPaddingLeft(), (int) getResources().getDimension(R.dimen.miuisupport_alert_dialog_no_title_padding_top), textView2.getPaddingRight(), (int) getResources().getDimension(R.dimen.miuisupport_alert_dialog_no_title_padding_bottom));
            }
        } else {
            textView2.setVisibility(8);
        }
        this.c = textView2;
        CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.check_box);
        if (!TextUtils.isEmpty(this.f8196a.j)) {
            checkBox.setText(this.f8196a.j);
            checkBox.setChecked(this.f8196a.i);
            checkBox.setVisibility(0);
        } else {
            checkBox.setVisibility(8);
        }
        this.d = checkBox;
        if (this.f8196a.k != null) {
            ViewGroup.LayoutParams layoutParams = this.f8196a.k.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new ViewGroup.LayoutParams(-1, -2);
            }
            ViewGroup viewGroup2 = (ViewGroup) this.f8196a.k.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.f8196a.k);
            }
            ((ViewGroup) inflate.findViewById(R.id.custom)).addView(this.f8196a.k, layoutParams);
        } else {
            inflate.findViewById(R.id.custom).setVisibility(8);
        }
        Button button = (Button) inflate.findViewById(R.id.confirm);
        boolean z2 = true;
        if (!TextUtils.isEmpty(this.f8196a.e)) {
            button.setText(this.f8196a.e);
            button.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    AlertDialog.this.b(view);
                }
            });
            z = true;
        } else {
            z = false;
        }
        this.e = button;
        Button button2 = (Button) inflate.findViewById(R.id.cancel);
        if (!TextUtils.isEmpty(this.f8196a.g)) {
            button2.setText(this.f8196a.g);
            button2.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    AlertDialog.this.a(view);
                }
            });
        } else {
            z2 = false;
        }
        this.f = button2;
        if (z && !z2) {
            button.setBackgroundResource(R.drawable.miuisupport_btn_bg_dialog_single_light);
            button2.setVisibility(8);
        } else if (!z && z2) {
            button.setVisibility(8);
            button2.setBackgroundResource(R.drawable.miuisupport_btn_bg_dialog_single_light);
        } else if (!z && !z2) {
            button.setVisibility(8);
            button2.setVisibility(8);
            Log.e(b, "no positive button, no negative button, check invocation");
        }
        return inflate;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void e() {
        dismissAllowingStateLoss();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(DialogInterface dialogInterface) {
        if (this.f8196a.l != null) {
            this.f8196a.l.onShow(this);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean a(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        if (this.f8196a.n != null) {
            return this.f8196a.n.onKey(this, i, keyEvent);
        }
        return false;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        dismissAllowingStateLoss();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        if (this.f8196a.f != null) {
            this.f8196a.f.onClick(this, -1);
        }
        dismissAllowingStateLoss();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        if (this.f8196a.h != null) {
            this.f8196a.h.onClick(this, -2);
        }
        dismissAllowingStateLoss();
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (!(this.f8196a == null || this.f8196a.m == null)) {
            this.f8196a.m.onDismiss(this);
        }
        super.onDismiss(dialogInterface);
    }

    public AlertDialog a(TextUpdater textUpdater) {
        this.g = textUpdater;
        return this;
    }

    public boolean a() {
        return this.d != null && this.d.isChecked();
    }

    public TextView b() {
        return this.c;
    }

    public Button c() {
        return this.e;
    }

    public Button d() {
        return this.f;
    }
}
