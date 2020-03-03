package com.mics.core.ui.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mics.R;
import com.mics.core.MiCS;
import com.mics.util.FrescoImageLoader;
import com.mics.util.HtmlText;
import java.util.List;

public class CardMultiView extends RelativeLayout implements View.OnClickListener, HtmlText.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private Context f7750a;
    private SimpleDraweeView b;
    private int c;
    private TextView d;
    private int e;
    private TextView f;
    private int g;
    private HtmlText.OnClickListener h;

    public void showHumanService(String str) {
    }

    public void toLeaveMessage(String str) {
    }

    public CardMultiView(Context context) {
        this(context, (AttributeSet) null);
    }

    public CardMultiView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CardMultiView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = 2018001;
        this.e = 2018002;
        this.g = 2018003;
        this.f7750a = context;
        a();
    }

    private void a() {
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        this.b = new SimpleDraweeView(this.f7750a);
        this.b.setId(this.c);
        this.b.setAspectRatio(2.0f);
        this.b.setLayoutParams(layoutParams);
        addView(this.b);
        float applyDimension = TypedValue.applyDimension(1, 40.0f, getResources().getDisplayMetrics());
        float applyDimension2 = TypedValue.applyDimension(1, 14.0f, getResources().getDisplayMetrics());
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, (int) applyDimension);
        layoutParams2.addRule(8, this.c);
        this.d = new TextView(this.f7750a);
        this.d.setId(this.e);
        this.d.setLayoutParams(layoutParams2);
        this.d.setBackgroundColor(Color.parseColor("#66000000"));
        this.d.setTextColor(-1);
        this.d.setTextSize(1, 11.0f);
        this.d.setGravity(16);
        int i = (int) applyDimension2;
        this.d.setPadding(i, 0, i, 0);
        this.d.setMaxLines(2);
        this.d.setEllipsize(TextUtils.TruncateAt.END);
        this.d.setVisibility(8);
        addView(this.d);
        float applyDimension3 = TypedValue.applyDimension(1, 12.0f, getResources().getDisplayMetrics());
        float applyDimension4 = TypedValue.applyDimension(1, 9.0f, getResources().getDisplayMetrics());
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams3.addRule(3, this.c);
        this.f = new TextView(this.f7750a);
        this.f.setId(this.g);
        this.f.setLayoutParams(layoutParams3);
        this.f.setBackgroundResource(R.drawable.mics_bg_card_mutli);
        this.f.setTextColor(-16777216);
        this.f.setTextSize(1, 13.0f);
        this.f.setGravity(16);
        int i2 = (int) applyDimension3;
        int i3 = (int) applyDimension4;
        this.f.setPadding(i2, i3, i2, i3);
        this.f.setVisibility(8);
        addView(this.f);
    }

    public void setData(List<Article> list, HtmlText.OnClickListener onClickListener) {
        if (list != null && list.size() != 0) {
            b();
            this.h = onClickListener;
            int size = list.size();
            Article article = list.get(0);
            FrescoImageLoader.Builder builder = new FrescoImageLoader.Builder();
            builder.a(9.0f, 9.0f, 0.0f, 0.0f);
            builder.a((DraweeView<GenericDraweeHierarchy>) this.b);
            builder.a(article.c());
            builder.a().a();
            this.b.setOnClickListener(this);
            this.b.setTag(article.d());
            if (size == 1) {
                this.d.setVisibility(8);
                this.d.setOnClickListener((View.OnClickListener) null);
                this.f.setVisibility(0);
                this.f.setText(HtmlText.a(this.f7750a, article.a(), this));
                this.f.setOnClickListener(this);
                this.f.setTag(article.d());
                return;
            }
            this.f.setVisibility(8);
            this.f.setOnClickListener((View.OnClickListener) null);
            this.d.setVisibility(0);
            this.d.setText(HtmlText.a(this.f7750a, article.a(), this));
            this.d.setOnClickListener(this);
            float applyDimension = TypedValue.applyDimension(1, 14.0f, getResources().getDisplayMetrics());
            for (int i = 1; i < list.size(); i++) {
                View a2 = a(LayoutInflater.from(this.f7750a), this, R.layout.mics_item_kit_card_multi);
                a2.setId(2018100 + i);
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                if (i == 1) {
                    layoutParams.addRule(3, this.c);
                } else {
                    layoutParams.addRule(3, (i + 2018110) - 1);
                }
                a2.setLayoutParams(layoutParams);
                a2.setTag(list.get(i).d());
                a2.setOnClickListener(this);
                ((TextView) a2.findViewById(R.id.tv_content)).setText(list.get(i).a());
                FrescoImageLoader.Builder builder2 = new FrescoImageLoader.Builder();
                builder2.a((DraweeView<GenericDraweeHierarchy>) (SimpleDraweeView) a2.findViewById(R.id.iv_cover));
                builder2.a(list.get(i).c());
                builder2.a().a();
                addView(a2);
                View view = new View(this.f7750a);
                view.setId(2018110 + i);
                view.setBackgroundColor(Color.parseColor("#eff0f1"));
                RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, 2);
                layoutParams2.addRule(3, a2.getId());
                int i2 = (int) applyDimension;
                layoutParams2.setMargins(i2, 0, i2, 0);
                view.setLayoutParams(layoutParams2);
                addView(view);
            }
            removeViewAt(getChildCount() - 1);
            View view2 = new View(this.f7750a);
            view2.setBackgroundResource(R.drawable.mics_bg_card_mutli);
            RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
            layoutParams3.addRule(3, this.c);
            layoutParams3.addRule(8, getChildAt(getChildCount() - 1).getId());
            view2.setLayoutParams(layoutParams3);
            addView(view2, 3);
        }
    }

    private void b() {
        removeAllViews();
        addView(this.b);
        addView(this.d);
        addView(this.f);
    }

    private static View a(LayoutInflater layoutInflater, ViewGroup viewGroup, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(viewGroup.getContext());
        }
        return layoutInflater.inflate(i, viewGroup, false);
    }

    public void onClick(String str) {
        if (this.h != null) {
            this.h.onClick(str);
        }
    }

    public void onClick(View view) {
        String str = (String) view.getTag();
        if (!TextUtils.isEmpty(str)) {
            MiCS.a().a(str);
        }
    }

    public static class Article {

        /* renamed from: a  reason: collision with root package name */
        private String f7751a;
        private String b;
        private String c;
        private String d;
        private String e;

        public String a() {
            return this.f7751a;
        }

        public void a(String str) {
            this.f7751a = str;
        }

        public String b() {
            return this.b;
        }

        public void b(String str) {
            this.b = str;
        }

        public String c() {
            return this.c;
        }

        public void c(String str) {
            this.c = str;
        }

        public String d() {
            return this.d;
        }

        public void d(String str) {
            this.d = str;
        }

        public String e() {
            return this.e;
        }

        public void e(String str) {
            this.e = str;
        }
    }
}
