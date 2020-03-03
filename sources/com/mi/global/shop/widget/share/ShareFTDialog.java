package com.mi.global.shop.widget.share;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.mi.global.shop.R;

public class ShareFTDialog extends Dialog {
    public ShareFTDialog(Context context) {
        super(context);
    }

    public ShareFTDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private Context f7288a;
        private View b;
        /* access modifiers changed from: private */
        public DialogInterface.OnClickListener c;
        /* access modifiers changed from: private */
        public DialogInterface.OnClickListener d;
        /* access modifiers changed from: private */
        public DialogInterface.OnClickListener e;

        public Builder(Context context) {
            this.f7288a = context;
        }

        public Builder a(DialogInterface.OnClickListener onClickListener) {
            this.d = onClickListener;
            return this;
        }

        public Builder b(DialogInterface.OnClickListener onClickListener) {
            this.e = onClickListener;
            return this;
        }

        public Builder c(DialogInterface.OnClickListener onClickListener) {
            this.c = onClickListener;
            return this;
        }

        public ShareFTDialog a() {
            final ShareFTDialog shareFTDialog = new ShareFTDialog(this.f7288a, R.style.Dialog);
            View inflate = ((LayoutInflater) this.f7288a.getSystemService("layout_inflater")).inflate(R.layout.shop_dialog_share_layout, (ViewGroup) null);
            shareFTDialog.addContentView(inflate, new LinearLayout.LayoutParams(-1, -2));
            if (this.c != null) {
                ((LinearLayout) inflate.findViewById(R.id.ly_share_fb)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Builder.this.c.onClick(shareFTDialog, -1);
                    }
                });
            }
            if (this.d != null) {
                ((LinearLayout) inflate.findViewById(R.id.ly_share_tw)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Builder.this.d.onClick(shareFTDialog, -3);
                    }
                });
            }
            if (this.e != null) {
                ((LinearLayout) inflate.findViewById(R.id.ly_share_cancle)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Builder.this.e.onClick(shareFTDialog, -2);
                    }
                });
            }
            shareFTDialog.setContentView(inflate);
            return shareFTDialog;
        }
    }
}
