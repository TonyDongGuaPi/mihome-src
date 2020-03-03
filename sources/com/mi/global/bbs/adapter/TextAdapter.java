package com.mi.global.bbs.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.mi.global.bbs.R;
import com.mi.global.bbs.model.SlideMenu;
import java.util.List;

public class TextAdapter extends BaseAdapter {
    public static final String TAG = "TextAdapter";
    private Context mContext;
    private List<SlideMenu> mListData;
    private int mSelectedPosition = -1;

    public long getItemId(int i) {
        return (long) i;
    }

    public TextAdapter(Context context, List<SlideMenu> list) {
        this.mContext = context;
        this.mListData = list;
    }

    public List<SlideMenu> getListData() {
        return this.mListData;
    }

    public void setListData(List<SlideMenu> list) {
        this.mListData = list;
        this.mSelectedPosition = -1;
    }

    public int getSelectedPosition() {
        return this.mSelectedPosition;
    }

    public void setSelectedPosition(int i) {
        this.mSelectedPosition = i;
    }

    public int getCount() {
        return this.mListData.size();
    }

    public SlideMenu getItem(int i) {
        return this.mListData.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = LayoutInflater.from(this.mContext).inflate(R.layout.bbs_choose_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.name.setText(getItem(i).getName());
        if (i == this.mSelectedPosition) {
            viewHolder.name.setTextColor(this.mContext.getResources().getColor(R.color.main_slide_menu_text_select));
        } else {
            viewHolder.name.setTextColor(this.mContext.getResources().getColor(R.color.main_slide_menu_text));
        }
        return view;
    }

    private static class ViewHolder {
        TextView name;

        ViewHolder(View view) {
            this.name = (TextView) view.findViewById(R.id.tv_choose_name);
            view.setTag(this);
        }
    }
}
