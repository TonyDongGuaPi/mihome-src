package com.mi.global.shop.feature.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.mi.global.shop.feature.search.newmodel.SearchResult;
import java.util.List;

public class GirdDropDownAdapter extends BaseAdapter {
    private int checkItemPosition = 0;
    private Context context;
    private List<SearchResult.AllCategoriesBean> list;

    public Object getItem(int i) {
        return null;
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public void setCheckItem(int i) {
        this.checkItemPosition = i;
        notifyDataSetChanged();
    }

    public GirdDropDownAdapter(Context context2, List<SearchResult.AllCategoriesBean> list2) {
        this.context = context2;
        this.list = list2;
    }

    public void setData(List<SearchResult.AllCategoriesBean> list2) {
        if (list2 != null) {
            if (this.list != null) {
                this.list.clear();
            }
            this.list = list2;
            notifyDataSetChanged();
        }
    }

    public int getCount() {
        if (this.list == null) {
            return 0;
        }
        return this.list.size();
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view != null) {
            viewHolder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(this.context).inflate(R.layout.feature_search_item_list_drop_down, (ViewGroup) null);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }
        viewHolder.mText.setText(this.list.get(i).cat_name);
        return view;
    }

    static class ViewHolder {
        TextView mText;

        ViewHolder(View view) {
            this.mText = (TextView) view.findViewById(R.id.text);
        }
    }
}
