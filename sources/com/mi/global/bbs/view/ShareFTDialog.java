package com.mi.global.bbs.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.mi.global.bbs.R;

public class ShareFTDialog extends Dialog {
    public ShareFTDialog(Context context) {
        super(context);
    }

    public ShareFTDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public DialogInterface.OnClickListener cancleClickListener;
        private View contentView;
        private Context context;
        /* access modifiers changed from: private */
        public DialogInterface.OnClickListener copyButtonClickListener;
        /* access modifiers changed from: private */
        public DialogInterface.OnClickListener fbButtonClickListener;
        /* access modifiers changed from: private */
        public DialogInterface.OnClickListener twButtonClickListener;
        /* access modifiers changed from: private */
        public DialogInterface.OnClickListener whatsAppClickListener;

        public Builder(Context context2) {
            this.context = context2;
        }

        public Builder setTWButton(DialogInterface.OnClickListener onClickListener) {
            this.twButtonClickListener = onClickListener;
            return this;
        }

        public Builder setCancleButton(DialogInterface.OnClickListener onClickListener) {
            this.cancleClickListener = onClickListener;
            return this;
        }

        public Builder setFBButton(DialogInterface.OnClickListener onClickListener) {
            this.fbButtonClickListener = onClickListener;
            return this;
        }

        public Builder setWhatsappButton(DialogInterface.OnClickListener onClickListener) {
            this.whatsAppClickListener = onClickListener;
            return this;
        }

        public Builder setCopyButton(DialogInterface.OnClickListener onClickListener) {
            this.copyButtonClickListener = onClickListener;
            return this;
        }

        public ShareFTDialog create() {
            final ShareFTDialog shareFTDialog = new ShareFTDialog(this.context, R.style.Dialog);
            View inflate = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.bbs_dialog_share_layout, (ViewGroup) null);
            shareFTDialog.addContentView(inflate, new LinearLayout.LayoutParams(-1, -2));
            if (this.fbButtonClickListener != null) {
                ((LinearLayout) inflate.findViewById(R.id.ly_share_fb)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Builder.this.fbButtonClickListener.onClick(shareFTDialog, -1);
                    }
                });
            }
            if (this.twButtonClickListener != null) {
                ((LinearLayout) inflate.findViewById(R.id.ly_share_tw)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Builder.this.twButtonClickListener.onClick(shareFTDialog, -3);
                    }
                });
            }
            if (this.whatsAppClickListener != null) {
                ((LinearLayout) inflate.findViewById(R.id.ly_share_whatapp)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Builder.this.whatsAppClickListener.onClick(shareFTDialog, -3);
                    }
                });
            }
            if (this.copyButtonClickListener != null) {
                ((LinearLayout) inflate.findViewById(R.id.ly_share_copy)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Builder.this.copyButtonClickListener.onClick(shareFTDialog, -3);
                    }
                });
            } else {
                inflate.findViewById(R.id.ly_share_copy).setVisibility(8);
            }
            if (this.cancleClickListener != null) {
                ((LinearLayout) inflate.findViewById(R.id.ly_share_cancle)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Builder.this.cancleClickListener.onClick(shareFTDialog, -2);
                    }
                });
            }
            shareFTDialog.setContentView(inflate);
            return shareFTDialog;
        }
    }
}
