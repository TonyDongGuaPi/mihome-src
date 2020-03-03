package com.facebook.react.views.picker;

import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.facebook.infer.annotation.Assertions;
import java.util.List;

class ReactPickerAdapter extends ArrayAdapter<ReactPickerItem> {
    private final LayoutInflater mInflater;
    @Nullable
    private Integer mPrimaryTextColor;

    public ReactPickerAdapter(Context context, List<ReactPickerItem> list) {
        super(context, 0, list);
        this.mInflater = (LayoutInflater) Assertions.assertNotNull(context.getSystemService("layout_inflater"));
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return getView(i, view, viewGroup, false);
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return getView(i, view, viewGroup, true);
    }

    private View getView(int i, View view, ViewGroup viewGroup, boolean z) {
        ReactPickerItem reactPickerItem = (ReactPickerItem) getItem(i);
        boolean z2 = false;
        if (view == null) {
            view = this.mInflater.inflate(z ? 17367049 : 17367048, viewGroup, false);
            view.setTag(((TextView) view).getTextColors());
            z2 = true;
        }
        TextView textView = (TextView) view;
        textView.setText(reactPickerItem.label);
        if (!z && this.mPrimaryTextColor != null) {
            textView.setTextColor(this.mPrimaryTextColor.intValue());
        } else if (reactPickerItem.color != null) {
            textView.setTextColor(reactPickerItem.color.intValue());
        } else if (textView.getTag() != null && !z2) {
            textView.setTextColor((ColorStateList) textView.getTag());
        }
        return textView;
    }

    @Nullable
    public Integer getPrimaryTextColor() {
        return this.mPrimaryTextColor;
    }

    public void setPrimaryTextColor(@Nullable Integer num) {
        this.mPrimaryTextColor = num;
        notifyDataSetChanged();
    }
}
