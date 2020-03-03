package com.mi.global.shop.widget.share;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ResolveInfo;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mi.global.shop.R;
import com.mi.global.shop.adapter.ShareItemAdapter;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.xmsf.account.LoginManager;
import java.util.ArrayList;

public class ShareSystemDialog extends Dialog {
    public ShareSystemDialog(Context context) {
        super(context);
    }

    public ShareSystemDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private Context f7292a;
        private View b;
        /* access modifiers changed from: private */
        public AdapterView.OnItemClickListener c;
        /* access modifiers changed from: private */
        public DialogInterface.OnClickListener d;
        private GridView e;
        private ShareItemAdapter f;
        private ArrayList<ResolveInfo> g;
        private TextView h;
        private boolean i;

        public Builder(Context context) {
            this.f7292a = context;
        }

        public Builder a(AdapterView.OnItemClickListener onItemClickListener) {
            this.c = onItemClickListener;
            return this;
        }

        public Builder a(DialogInterface.OnClickListener onClickListener) {
            this.d = onClickListener;
            return this;
        }

        public Builder a(ArrayList<ResolveInfo> arrayList) {
            this.g = arrayList;
            return this;
        }

        public Builder a(boolean z) {
            this.i = z;
            return this;
        }

        public ShareSystemDialog a() {
            final ShareSystemDialog shareSystemDialog = new ShareSystemDialog(this.f7292a, R.style.Dialog);
            View inflate = ((LayoutInflater) this.f7292a.getSystemService("layout_inflater")).inflate(R.layout.shop_dialog_system_share_layout, (ViewGroup) null);
            this.e = (GridView) inflate.findViewById(R.id.gv_share);
            this.f = new ShareItemAdapter(this.f7292a);
            if (this.g != null) {
                this.f.a(this.g);
            }
            this.e.setAdapter(this.f);
            b();
            shareSystemDialog.addContentView(inflate, new LinearLayout.LayoutParams(-1, -2));
            if (this.c != null) {
                this.e.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                        Builder.this.c.onItemClick(adapterView, view, i, j);
                    }
                });
            }
            if (this.d != null) {
                ((CustomButtonView) inflate.findViewById(R.id.btn_cancel)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Builder.this.d.onClick(shareSystemDialog, -2);
                    }
                });
            }
            this.h = (TextView) inflate.findViewById(R.id.tv_share_hint);
            if (this.i) {
                a((Dialog) shareSystemDialog);
            }
            shareSystemDialog.setContentView(inflate);
            return shareSystemDialog;
        }

        public void b() {
            int height = ((WindowManager) this.f7292a.getSystemService("window")).getDefaultDisplay().getHeight();
            ViewGroup.LayoutParams layoutParams = this.e.getLayoutParams();
            if (this.g.size() > 12) {
                layoutParams.height = height / 2;
            }
            this.e.setLayoutParams(layoutParams);
        }

        public void a(Dialog dialog) {
            if (!LoginManager.u().x()) {
                this.h.setText(Html.fromHtml("Share after <font color='#298CDA'>signing in</font>, get Mi Tokens!"));
                this.h.setVisibility(0);
                this.h.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                    }
                });
            }
        }
    }
}
