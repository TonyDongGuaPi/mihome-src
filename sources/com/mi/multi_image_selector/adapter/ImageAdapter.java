package com.mi.multi_image_selector.adapter;

import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.mi.multi_image_selector.R;
import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    final int f7390a;
    private Context b;
    private LayoutInflater c;
    /* access modifiers changed from: private */
    public List<String> d = new ArrayList();

    public long getItemId(int i) {
        return (long) i;
    }

    public ImageAdapter(Context context, int i) {
        int i2;
        this.b = context;
        this.c = (LayoutInflater) context.getSystemService("layout_inflater");
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (Build.VERSION.SDK_INT >= 13) {
            Point point = new Point();
            windowManager.getDefaultDisplay().getSize(point);
            i2 = point.x;
        } else {
            i2 = windowManager.getDefaultDisplay().getWidth();
        }
        this.f7390a = i2 / i;
    }

    public List<String> a() {
        return this.d;
    }

    public void a(List<String> list) {
        this.d.clear();
        if (list == null || list.size() <= 0) {
            this.d.clear();
        } else {
            this.d.addAll(list);
        }
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.d.size();
    }

    /* renamed from: a */
    public String getItem(int i) {
        return this.d.get(i);
    }

    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.c.inflate(R.layout.bbs_grid_item, viewGroup, false);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ImageAdapter.this.d.remove(i);
                ImageAdapter.this.notifyDataSetChanged();
            }
        });
        String a2 = getItem(i);
        if (!TextUtils.isEmpty(a2)) {
            Uri parse = Uri.parse("file://" + a2);
            viewHolder.f7392a.getLayoutParams().width = this.f7390a;
            viewHolder.f7392a.getLayoutParams().height = this.f7390a;
            Glide.c(this.b).a(parse).a(viewHolder.f7392a);
        }
        return view;
    }

    static class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public ImageView f7392a;
        public ImageView b;

        ViewHolder(View view) {
            this.f7392a = (ImageView) view.findViewById(R.id.image);
            this.b = (ImageView) view.findViewById(R.id.checkmark);
            view.setTag(this);
        }
    }
}
