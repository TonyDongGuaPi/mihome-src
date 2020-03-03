package com.xiaomi.shopviews.adapter.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaomi.shopviews.model.HomeSectionItem;
import com.xiaomi.shopviews.widget.R;

public class BubbleAdapter extends BaseDataAdapter<HomeSectionItem> {
    private int d;
    private Context e;
    private int f;

    public BubbleAdapter(Context context, int i, int i2) {
        super(context);
        this.e = context;
        this.f = i;
        this.d = i2;
    }

    public void a(View view, int i, HomeSectionItem homeSectionItem) {
        String str;
        TextView textView = (TextView) view.findViewById(R.id.tv_bubbleTitle);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_bubbleContent);
        TextView textView3 = (TextView) view.findViewById(R.id.tv_bubbleContentRight);
        textView.setTextColor(this.f);
        textView2.setTextColor(this.d);
        textView3.setTextColor(this.d);
        if (TextUtils.isEmpty(homeSectionItem.mLabelTitle)) {
            str = "";
        } else {
            str = homeSectionItem.mLabelTitle;
        }
        textView.setText(str);
        if (!TextUtils.isEmpty(homeSectionItem.mLabelContent)) {
            if (homeSectionItem.mLabelContent.length() > 8) {
                textView2.setEllipsize(TextUtils.TruncateAt.END);
            } else {
                textView2.setEllipsize((TextUtils.TruncateAt) null);
            }
            textView2.setText(homeSectionItem.mLabelContent);
            textView3.setText(">");
            return;
        }
        textView2.setText("");
        textView3.setText("");
    }

    public int getCount() {
        if (a().size() > 3) {
            return Integer.MAX_VALUE;
        }
        return a().size();
    }

    public Object getItem(int i) {
        return a().get(i % a().size());
    }

    public long getItemId(int i) {
        return (long) (i % a().size());
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (this.c) {
            int size = i % a().size();
            if (size < 0 || size >= this.b.size()) {
                throw new IllegalStateException("couldn't get view at this position " + size);
            }
            HomeSectionItem homeSectionItem = (HomeSectionItem) this.b.get(size);
            if (view == null) {
                view = a(this.e, homeSectionItem, viewGroup, size);
            }
            a(view, size, homeSectionItem);
            return view;
        }
        throw new IllegalStateException("this should only be called when the data is valid");
    }

    public View a(Context context, HomeSectionItem homeSectionItem, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.item_bubble, (ViewGroup) null);
    }
}
