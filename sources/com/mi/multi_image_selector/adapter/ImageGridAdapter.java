package com.mi.multi_image_selector.adapter;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.mi.multi_image_selector.R;
import com.mi.multi_image_selector.bean.Image;
import com.mi.multi_image_selector.view.SquaredImageView;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImageGridAdapter extends BaseAdapter {
    private static final int b = 0;
    private static final int c = 1;

    /* renamed from: a  reason: collision with root package name */
    final int f7393a;
    /* access modifiers changed from: private */
    public Context d;
    private LayoutInflater e;
    private boolean f = true;
    /* access modifiers changed from: private */
    public boolean g = true;
    private List<Image> h = new ArrayList();
    /* access modifiers changed from: private */
    public List<Image> i = new ArrayList();

    public long getItemId(int i2) {
        return (long) i2;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public ImageGridAdapter(Context context, boolean z, int i2) {
        int i3;
        this.d = context;
        this.e = (LayoutInflater) context.getSystemService("layout_inflater");
        this.f = z;
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (Build.VERSION.SDK_INT >= 13) {
            Point point = new Point();
            windowManager.getDefaultDisplay().getSize(point);
            i3 = point.x;
        } else {
            i3 = windowManager.getDefaultDisplay().getWidth();
        }
        this.f7393a = i3 / i2;
    }

    public void a(boolean z) {
        this.g = z;
    }

    public void b(boolean z) {
        if (this.f != z) {
            this.f = z;
            notifyDataSetChanged();
        }
    }

    public boolean a() {
        return this.f;
    }

    public void a(Image image) {
        if (this.i.contains(image)) {
            this.i.remove(image);
        } else {
            this.i.add(image);
        }
        notifyDataSetChanged();
    }

    public void a(ArrayList<String> arrayList) {
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            Image a2 = a(it.next());
            if (a2 != null) {
                this.i.add(a2);
            }
        }
        if (this.i.size() > 0) {
            notifyDataSetChanged();
        }
    }

    private Image a(String str) {
        if (this.h == null || this.h.size() <= 0) {
            return null;
        }
        for (Image next : this.h) {
            if (next.f7396a.equalsIgnoreCase(str)) {
                return next;
            }
        }
        return null;
    }

    public void a(List<Image> list) {
        this.i.clear();
        if (list == null || list.size() <= 0) {
            this.h.clear();
        } else {
            this.h = list;
        }
        notifyDataSetChanged();
    }

    public int getItemViewType(int i2) {
        return (!this.f || i2 != 0) ? 1 : 0;
    }

    public int getCount() {
        return this.f ? this.h.size() + 1 : this.h.size();
    }

    /* renamed from: a */
    public Image getItem(int i2) {
        if (!this.f) {
            return this.h.get(i2);
        }
        if (i2 == 0) {
            return null;
        }
        return this.h.get(i2 - 1);
    }

    public View getView(int i2, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (a() && i2 == 0) {
            return this.e.inflate(R.layout.bbs_mis_list_item_camera, viewGroup, false);
        }
        if (view == null) {
            view = this.e.inflate(R.layout.bbs_mis_list_item_image, viewGroup, false);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (viewHolder != null) {
            viewHolder.a(getItem(i2));
        }
        return view;
    }

    class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        SquaredImageView f7394a;
        ImageView b;
        View c;

        ViewHolder(View view) {
            this.f7394a = (SquaredImageView) view.findViewById(R.id.image);
            this.b = (ImageView) view.findViewById(R.id.checkmark);
            this.c = view.findViewById(R.id.mask);
            view.setTag(this);
        }

        /* access modifiers changed from: package-private */
        public void a(Image image) {
            if (image != null) {
                if (ImageGridAdapter.this.g) {
                    this.b.setVisibility(0);
                    if (ImageGridAdapter.this.i.contains(image)) {
                        this.b.setImageResource(R.drawable.bbs_mis_btn_selected);
                        this.c.setVisibility(0);
                    } else {
                        this.b.setImageResource(R.drawable.bbs_mis_btn_unselected);
                        this.c.setVisibility(8);
                    }
                } else {
                    this.b.setVisibility(8);
                }
                File file = new File(image.f7396a);
                if (file.exists()) {
                    Glide.c(ImageGridAdapter.this.d).a(file).b((BaseRequestOptions<?>) ((RequestOptions) ((RequestOptions) new RequestOptions().a(R.drawable.bbs_mis_default_error)).e(ImageGridAdapter.this.f7393a, ImageGridAdapter.this.f7393a)).k()).a((ImageView) this.f7394a);
                } else {
                    this.f7394a.setImageResource(R.drawable.bbs_mis_default_error);
                }
            }
        }
    }
}
