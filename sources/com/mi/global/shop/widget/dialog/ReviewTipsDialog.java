package com.mi.global.shop.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.adapter.ArrayAdapter;
import com.mi.global.shop.R;
import com.mi.global.shop.widget.CustomButtonView;
import com.mi.global.shop.widget.CustomTextView;
import java.util.ArrayList;

public class ReviewTipsDialog extends Dialog {

    public class Builder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private Builder f7228a;

        @UiThread
        public Builder_ViewBinding(Builder builder, View view) {
            this.f7228a = builder;
            builder.lvTips = (ListView) Utils.findRequiredViewAsType(view, R.id.lv_tips, "field 'lvTips'", ListView.class);
            builder.btnOk = (CustomButtonView) Utils.findRequiredViewAsType(view, R.id.btn_ok, "field 'btnOk'", CustomButtonView.class);
        }

        @CallSuper
        public void unbind() {
            Builder builder = this.f7228a;
            if (builder != null) {
                this.f7228a = null;
                builder.lvTips = null;
                builder.btnOk = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public ReviewTipsDialog(Context context) {
        super(context);
    }

    public ReviewTipsDialog(Context context, int i) {
        super(context, i);
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private Context f7225a;
        private TipsAdapter b;
        @BindView(2131493034)
        CustomButtonView btnOk;
        @BindView(2131493702)
        ListView lvTips;

        public Builder(Context context) {
            this.f7225a = context;
        }

        public ReviewTipsDialog a() {
            final ReviewTipsDialog reviewTipsDialog = new ReviewTipsDialog(this.f7225a, R.style.Dialog);
            View inflate = ((LayoutInflater) this.f7225a.getSystemService("layout_inflater")).inflate(R.layout.shop_review_tips_dialog, (ViewGroup) null);
            ButterKnife.bind((Object) this, inflate);
            reviewTipsDialog.setCanceledOnTouchOutside(true);
            this.btnOk.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    reviewTipsDialog.dismiss();
                }
            });
            this.b = new TipsAdapter(this.f7225a);
            ArrayList arrayList = new ArrayList();
            arrayList.add(this.f7225a.getResources().getString(R.string.review_tips_1));
            arrayList.add(this.f7225a.getResources().getString(R.string.review_tips_2));
            arrayList.add(this.f7225a.getResources().getString(R.string.review_tips_3));
            arrayList.add(this.f7225a.getResources().getString(R.string.review_tips_4));
            this.b.a(arrayList);
            this.lvTips.setAdapter(this.b);
            this.lvTips.setDividerHeight(0);
            reviewTipsDialog.setContentView(inflate);
            return reviewTipsDialog;
        }

        public static class TipsAdapter extends ArrayAdapter<String> {
            private static final String e = "TipsAdapter";
            ViewHolder d;

            public TipsAdapter(Context context) {
                super(context);
            }

            public View a(Context context, int i, String str, ViewGroup viewGroup) {
                View inflate = LayoutInflater.from(this.f6732a).inflate(R.layout.shop_item_review_tips, (ViewGroup) null);
                this.d = new ViewHolder();
                this.d.f7227a = (CustomTextView) inflate.findViewById(R.id.tv_tip);
                inflate.setTag(this.d);
                return inflate;
            }

            public void a(View view, int i, String str) {
                ViewHolder viewHolder = (ViewHolder) view.getTag();
                if (!str.contains("#")) {
                    viewHolder.f7227a.setText(str);
                } else if (str.split("#").length == 3) {
                    CustomTextView customTextView = viewHolder.f7227a;
                    customTextView.setText(Html.fromHtml(str.split("#")[0] + "<font color='#424242' style='font-weight:bold'><b>" + str.split("#")[1] + "</b></font>" + str.split("#")[2]));
                } else if (str.split("#").length == 2) {
                    CustomTextView customTextView2 = viewHolder.f7227a;
                    customTextView2.setText(Html.fromHtml(str.split("#")[0] + "<font color='#424242' style='font-weight:bold'><b>" + str.split("#")[1] + "</b></font>"));
                }
            }

            static class ViewHolder {

                /* renamed from: a  reason: collision with root package name */
                CustomTextView f7227a;

                ViewHolder() {
                }
            }
        }
    }
}
