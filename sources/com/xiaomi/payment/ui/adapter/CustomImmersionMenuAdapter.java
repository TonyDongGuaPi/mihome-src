package com.xiaomi.payment.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mibi.common.data.ArrayAdapter;
import com.xiaomi.payment.platform.R;
import com.xiaomi.payment.ui.item.MenuListItem;

public class CustomImmersionMenuAdapter extends ArrayAdapter<MenuListItem.ItemData> {
    protected LayoutInflater c;

    public CustomImmersionMenuAdapter(Context context) {
        super(context);
        this.c = LayoutInflater.from(context);
    }

    public View a(Context context, int i, MenuListItem.ItemData itemData, ViewGroup viewGroup) {
        MenuListItem menuListItem = (MenuListItem) this.c.inflate(R.layout.mibi_menu_list_item, viewGroup, false);
        menuListItem.bind();
        return menuListItem;
    }

    public void a(View view, int i, MenuListItem.ItemData itemData) {
        ((MenuListItem) view).rebind(itemData);
    }
}
