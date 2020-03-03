package com.mi.global.shop.adapter;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.mi.global.shop.R;
import com.mi.global.shop.adapter.util.ArrayAdapter;
import com.mi.global.shop.widget.CustomTextView;

public class ShareItemAdapter extends ArrayAdapter<ResolveInfo> {
    private static final String b = "NewShareItemAdapter";

    /* renamed from: a  reason: collision with root package name */
    ViewHolder f5509a;
    private PackageManager c;

    public ShareItemAdapter(Context context) {
        super(context);
        this.c = context.getPackageManager();
    }

    public View a(Context context, int i, ResolveInfo resolveInfo, ViewGroup viewGroup) {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.shop_item_system_share, (ViewGroup) null, false);
        this.f5509a = new ViewHolder();
        this.f5509a.f5510a = (ImageView) inflate.findViewById(R.id.iv_app_icon);
        this.f5509a.b = (CustomTextView) inflate.findViewById(R.id.tv_app_name);
        inflate.setTag(this.f5509a);
        return inflate;
    }

    public void a(View view, int i, ResolveInfo resolveInfo) {
        ViewHolder viewHolder = (ViewHolder) view.getTag();
        viewHolder.f5510a.setImageDrawable(resolveInfo.loadIcon(this.c));
        viewHolder.b.setText(resolveInfo.loadLabel(this.c).toString());
    }

    static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        ImageView f5510a;
        CustomTextView b;

        ViewHolder() {
        }
    }
}
