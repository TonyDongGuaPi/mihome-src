package com.mi.global.shop.imageselector.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.mi.global.shop.R;
import com.mi.global.shop.imageselector.bean.Folder;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.widget.CustomTextView;
import java.util.ArrayList;
import java.util.List;

public class FolderAdapter extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    int f6919a;
    int b = 0;
    private Context c;
    private LayoutInflater d;
    private List<Folder> e = new ArrayList();

    public long getItemId(int i) {
        return (long) i;
    }

    public FolderAdapter(Context context) {
        this.c = context;
        this.d = (LayoutInflater) context.getSystemService("layout_inflater");
        this.f6919a = this.c.getResources().getDimensionPixelOffset(R.dimen.folder_cover_size);
    }

    public void a(List<Folder> list) {
        if (list == null || list.size() <= 0) {
            this.e.clear();
        } else {
            this.e = list;
        }
        notifyDataSetChanged();
    }

    public int getCount() {
        return this.e.size();
    }

    /* renamed from: a */
    public Folder getItem(int i) {
        return this.e.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            view = this.d.inflate(R.layout.shop_mis_list_item_folder, viewGroup, false);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (viewHolder != null) {
            viewHolder.a(getItem(i));
            if (this.b == i) {
                viewHolder.d.setVisibility(0);
            } else {
                viewHolder.d.setVisibility(4);
            }
        }
        return view;
    }

    private int b() {
        int i = 0;
        if (this.e != null && this.e.size() > 0) {
            for (Folder folder : this.e) {
                i += folder.d.size();
            }
        }
        return i;
    }

    public void b(int i) {
        if (this.b != i) {
            this.b = i;
            notifyDataSetChanged();
        }
    }

    public int a() {
        return this.b;
    }

    class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        SimpleDraweeView f6920a;
        CustomTextView b;
        CustomTextView c;
        ImageView d;

        ViewHolder(View view) {
            this.f6920a = (SimpleDraweeView) view.findViewById(R.id.cover);
            this.b = (CustomTextView) view.findViewById(R.id.name);
            this.c = (CustomTextView) view.findViewById(R.id.size);
            this.d = (ImageView) view.findViewById(R.id.indicator);
            view.setTag(this);
        }

        /* access modifiers changed from: package-private */
        public void a(Folder folder) {
            if (folder != null) {
                this.b.setText(folder.f6927a);
                if (folder.d != null) {
                    this.c.setText(String.format("(%d)", new Object[]{Integer.valueOf(folder.d.size())}));
                } else {
                    this.c.setText("(*)");
                }
                if (folder.c != null) {
                    FrescoUtils.b(folder.c.f6928a, this.f6920a, (BasePostprocessor) null, R.dimen.folder_cover_size, R.dimen.folder_cover_size, (BaseControllerListener) null);
                } else {
                    this.f6920a.setImageResource(R.drawable.shop_default_pic_small_inverse);
                }
            }
        }
    }
}
