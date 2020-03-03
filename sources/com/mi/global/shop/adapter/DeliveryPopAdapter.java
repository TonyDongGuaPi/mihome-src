package com.mi.global.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mi.global.shop.R;
import com.mi.global.shop.newmodel.user.address.SmartDetailItemData;
import com.mi.global.shop.widget.CustomTextView;
import java.util.ArrayList;

public class DeliveryPopAdapter extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    public ArrayList<SmartDetailItemData> f5489a;
    public String b;
    private Context c;

    public long getItemId(int i) {
        return (long) i;
    }

    public DeliveryPopAdapter(Context context) {
        this.c = context;
    }

    public DeliveryPopAdapter(Context context, ArrayList<SmartDetailItemData> arrayList, String str) {
        this.c = context;
        this.f5489a = arrayList;
        this.b = str;
    }

    public int getCount() {
        return this.f5489a.size();
    }

    public Object getItem(int i) {
        return this.f5489a.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        View view2;
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view2 = LayoutInflater.from(this.c).inflate(R.layout.shop_item_lv_pop_category, (ViewGroup) null);
            viewHolder.f5490a = (CustomTextView) view2.findViewById(R.id.tv_name);
            viewHolder.b = (CustomTextView) view2.findViewById(R.id.tv_detail);
            viewHolder.c = (ImageView) view2.findViewById(R.id.iv_select);
            view2.setTag(viewHolder);
        } else {
            view2 = view;
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.b.setText(this.f5489a.get(i).address);
        viewHolder.f5490a.setText(this.f5489a.get(i).shortName);
        if (!this.f5489a.get(i).isServiceable) {
            viewHolder.c.setImageResource(R.drawable.shop_cannotselected);
            viewHolder.b.setTextColor(this.c.getResources().getColor(R.color.delivery_tv_gray));
            viewHolder.f5490a.setTextColor(this.c.getResources().getColor(R.color.delivery_tv_gray));
        } else {
            viewHolder.b.setTextColor(this.c.getResources().getColor(R.color.delivery_tv_detail));
            viewHolder.f5490a.setTextColor(this.c.getResources().getColor(R.color.delivery_tv_name));
            if (this.f5489a.get(i).id.equals(this.b)) {
                viewHolder.c.setImageResource(R.drawable.shop_selecetd);
            } else {
                viewHolder.c.setImageResource(R.drawable.shop_unselecetd);
            }
        }
        return view2;
    }

    class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f5490a;
        TextView b;
        ImageView c;

        ViewHolder() {
        }
    }
}
