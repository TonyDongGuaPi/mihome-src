package com.mi.global.shop.imageselector.adapter;

import android.app.Activity;
import android.graphics.Point;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.mi.global.shop.R;
import com.mi.global.shop.imageselector.view.SquaredSimpleDraweeView;
import com.mi.global.shop.util.fresco.FrescoUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    public static int f6921a = 5;
    final int b;
    private Activity c;
    private LayoutInflater d;
    /* access modifiers changed from: private */
    public List<String> e = new ArrayList();

    public long getItemId(int i) {
        return (long) i;
    }

    public ImageAdapter(Activity activity, int i) {
        int i2;
        this.c = activity;
        this.d = (LayoutInflater) activity.getSystemService("layout_inflater");
        WindowManager windowManager = (WindowManager) activity.getSystemService("window");
        if (Build.VERSION.SDK_INT >= 13) {
            Point point = new Point();
            windowManager.getDefaultDisplay().getSize(point);
            i2 = point.x;
        } else {
            i2 = windowManager.getDefaultDisplay().getWidth();
        }
        this.b = i2 / i;
    }

    public void a(List<String> list) {
        this.e.clear();
        if (list == null || list.size() <= 0) {
            this.e.clear();
        } else {
            this.e = list;
        }
        notifyDataSetChanged();
    }

    public List<String> a() {
        return this.e;
    }

    public int getCount() {
        return this.e.size() < f6921a ? this.e.size() + 1 : this.e.size();
    }

    /* renamed from: a */
    public String getItem(int i) {
        return this.e.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        View view2;
        final int i2 = i;
        if (view == null) {
            view2 = this.d.inflate(R.layout.shop_grid_item, viewGroup, false);
            viewHolder = new ViewHolder(view2);
        } else {
            viewHolder = (ViewHolder) view.getTag();
            view2 = view;
        }
        viewHolder.b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ImageAdapter.this.e.remove(i2);
                ImageAdapter.this.notifyDataSetChanged();
            }
        });
        if (i2 == this.e.size()) {
            FrescoUtils.a(R.drawable.shop_add_image_icon, viewHolder.f6923a, (BasePostprocessor) null, this.b, this.b, (BaseControllerListener) null);
        } else {
            String a2 = getItem(i);
            if (!TextUtils.isEmpty(a2) && new File(a2).exists()) {
                FrescoUtils.b(a2, viewHolder.f6923a, (BasePostprocessor) null, this.b, this.b, (BaseControllerListener) null);
            }
        }
        return view2;
    }

    static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public SimpleDraweeView f6923a;
        public ImageView b;

        ViewHolder(View view) {
            this.f6923a = (SquaredSimpleDraweeView) view.findViewById(R.id.image);
            this.b = (ImageView) view.findViewById(R.id.checkmark);
            view.setTag(this);
        }
    }
}
