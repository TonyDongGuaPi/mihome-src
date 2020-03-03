package com.miui.supportlite.internal.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.miui.supportlite.R;
import com.miui.supportlite.util.AttributeResolver;
import java.util.ArrayList;

public class ImmersionMenuAdapter extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    private LayoutInflater f8205a;
    private ArrayList<MenuItem> b = new ArrayList<>();

    public long getItemId(int i) {
        return (long) i;
    }

    ImmersionMenuAdapter(Context context, Menu menu) {
        this.f8205a = LayoutInflater.from(context);
        a(menu, this.b);
    }

    private void a(Menu menu, ArrayList<MenuItem> arrayList) {
        arrayList.clear();
        if (menu != null) {
            int size = menu.size();
            for (int i = 0; i < size; i++) {
                MenuItem item = menu.getItem(i);
                if (item.isVisible()) {
                    arrayList.add(item);
                }
            }
        }
    }

    public int getCount() {
        return this.b.size();
    }

    /* renamed from: a */
    public MenuItem getItem(int i) {
        return this.b.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int i2;
        if (view == null) {
            view = this.f8205a.inflate(R.layout.miuisupport_immersion_popup_menu_item, viewGroup, false);
            view.setTag((TextView) view.findViewById(16908308));
        }
        TextView textView = (TextView) view.getTag();
        textView.setText(getItem(i).getTitle());
        ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
        if (getCount() == 1) {
            i2 = AttributeResolver.a(view.getContext(), R.attr.miuisupport_listViewItemBackgroundSingle);
            layoutParams.height = (int) view.getResources().getDimension(R.dimen.miuisupport_immersion_list_item_single);
        } else if (i == 0) {
            i2 = AttributeResolver.a(view.getContext(), R.attr.miuisupport_listViewItemBackgroundTop);
            layoutParams.height = (int) view.getResources().getDimension(R.dimen.miuisupport_immersion_list_item_top);
        } else if (i == getCount() - 1) {
            i2 = AttributeResolver.a(view.getContext(), R.attr.miuisupport_listViewItemBackgroundBottom);
            layoutParams.height = (int) view.getResources().getDimension(R.dimen.miuisupport_immersion_list_item_bottom);
        } else {
            i2 = AttributeResolver.a(view.getContext(), R.attr.miuisupport_listViewItemBackgroundMiddle);
            layoutParams.height = (int) view.getResources().getDimension(R.dimen.miuisupport_immersion_list_item_middle);
        }
        textView.setLayoutParams(layoutParams);
        textView.setBackgroundResource(i2);
        return view;
    }

    public void a(Menu menu) {
        a(menu, this.b);
        notifyDataSetChanged();
    }
}
