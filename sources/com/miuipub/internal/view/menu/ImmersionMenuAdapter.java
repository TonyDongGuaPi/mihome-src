package com.miuipub.internal.view.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;

public class ImmersionMenuAdapter extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    private LayoutInflater f8301a;
    private ArrayList<MenuItem> b = new ArrayList<>();

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return view;
    }

    ImmersionMenuAdapter(Context context, Menu menu) {
        this.f8301a = LayoutInflater.from(context);
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

    public void a(Menu menu) {
        a(menu, this.b);
        notifyDataSetChanged();
    }
}
