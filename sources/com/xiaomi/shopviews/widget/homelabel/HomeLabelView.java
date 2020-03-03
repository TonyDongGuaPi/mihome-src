package com.xiaomi.shopviews.widget.homelabel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.shopviews.model.HomeSection;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.model.IHomeItemView;
import com.xiaomi.shopviews.widget.R;

public class HomeLabelView extends LinearLayout implements IHomeItemView {

    /* renamed from: a  reason: collision with root package name */
    private TextView f13265a = ((TextView) findViewById(R.id.label_content));
    private TextView b = ((TextView) findViewById(R.id.label_title));

    /* access modifiers changed from: private */
    public void a(HomeSectionItem homeSectionItem) {
    }

    public void draw(HomeSection homeSection) {
    }

    public HomeLabelView(Context context) {
        super(context);
        LayoutInflater.from(getContext()).inflate(R.layout.listitem_label, this);
    }

    private boolean a(HomeSection homeSection) {
        return homeSection == null || homeSection.mBody == null || homeSection.mBody.mItems == null || homeSection.mBody.mItems.size() == 0;
    }

    public void bind(HomeSection homeSection, int i, int i2) {
        if (!a(homeSection)) {
            final HomeSectionItem homeSectionItem = homeSection.mBody.mItems.get(0);
            this.b.setText(homeSectionItem.mLabelTitle);
            this.f13265a.setText(homeSectionItem.mLabelContent);
            setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    HomeLabelView.this.a(homeSectionItem);
                }
            });
        }
    }
}
