package com.mi.global.shop.imageselector.adapter;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.mi.global.shop.R;
import com.mi.global.shop.imageselector.MultiImageSelectorActivity;
import com.mi.global.shop.imageselector.bean.Image;
import com.mi.global.shop.imageselector.view.SquaredSimpleDraweeView;
import com.mi.global.shop.util.fresco.FrescoUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

public class ImageGridAdapter extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    public static final String f6924a = "ImageGridAdapter";
    private static final int c = 0;
    private static final int d = 1;
    final int b;
    /* access modifiers changed from: private */
    public Context e;
    private LayoutInflater f;
    private boolean g = true;
    /* access modifiers changed from: private */
    public boolean h = true;
    /* access modifiers changed from: private */
    public int i;
    private ArrayList<Image> j = new ArrayList<>();
    /* access modifiers changed from: private */
    public ArrayList<String> k = new ArrayList<>();

    public void a(Image image) {
    }

    public long getItemId(int i2) {
        return (long) i2;
    }

    public int getViewTypeCount() {
        return 2;
    }

    public ImageGridAdapter(Context context, boolean z, int i2) {
        int i3;
        this.e = context;
        this.f = (LayoutInflater) context.getSystemService("layout_inflater");
        this.g = z;
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (Build.VERSION.SDK_INT >= 13) {
            Point point = new Point();
            windowManager.getDefaultDisplay().getSize(point);
            i3 = point.x;
        } else {
            i3 = windowManager.getDefaultDisplay().getWidth();
        }
        this.b = i3 / i2;
    }

    public void a(boolean z) {
        this.h = z;
    }

    public void b(boolean z) {
        if (this.g != z) {
            this.g = z;
            notifyDataSetChanged();
        }
    }

    public boolean a() {
        return this.g;
    }

    public void a(ArrayList<String> arrayList) {
        this.k = arrayList;
    }

    public void a(int i2) {
        this.i = i2;
    }

    public void b(ArrayList<Image> arrayList) {
        if (arrayList == null || arrayList.size() <= 0) {
            this.j.clear();
        } else {
            this.j = arrayList;
        }
        notifyDataSetChanged();
    }

    public ArrayList<Image> b() {
        return this.j;
    }

    public int getItemViewType(int i2) {
        return (!this.g || i2 != 0) ? 1 : 0;
    }

    public int getCount() {
        return this.g ? this.j.size() + 1 : this.j.size();
    }

    /* renamed from: b */
    public Image getItem(int i2) {
        if (!this.g) {
            return this.j.get(i2);
        }
        if (i2 == 0) {
            return null;
        }
        return this.j.get(i2 - 1);
    }

    public View getView(int i2, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (a() && i2 == 0) {
            return this.f.inflate(R.layout.shop_mis_list_item_camera, viewGroup, false);
        }
        if (view == null) {
            view = this.f.inflate(R.layout.shop_mis_list_item_image, viewGroup, false);
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
        SimpleDraweeView f6925a;
        ImageView b;
        View c;

        ViewHolder(View view) {
            this.f6925a = (SquaredSimpleDraweeView) view.findViewById(R.id.image);
            this.b = (ImageView) view.findViewById(R.id.checkmark);
            this.c = view.findViewById(R.id.mask);
            view.setTag(this);
        }

        /* access modifiers changed from: package-private */
        public void a(final Image image) {
            if (image != null) {
                if (ImageGridAdapter.this.h) {
                    this.b.setVisibility(0);
                    this.b.setSelected(false);
                    this.c.setVisibility(8);
                    if (ImageGridAdapter.this.k != null && !TextUtils.isEmpty(image.f6928a)) {
                        Iterator it = ImageGridAdapter.this.k.iterator();
                        while (it.hasNext()) {
                            if (image.f6928a.equals((String) it.next())) {
                                this.b.setSelected(true);
                                this.c.setVisibility(0);
                            }
                        }
                    }
                } else {
                    this.b.setVisibility(8);
                }
                if (new File(image.f6928a).exists()) {
                    FrescoUtils.b(image.f6928a, this.f6925a, (BasePostprocessor) null, ImageGridAdapter.this.b, ImageGridAdapter.this.b, (BaseControllerListener) null);
                } else {
                    this.f6925a.setImageResource(R.drawable.shop_default_pic_small_inverse);
                }
                this.b.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (ImageGridAdapter.this.k == null || ImageGridAdapter.this.k.size() != ImageGridAdapter.this.i) {
                            if (ViewHolder.this.b.isSelected()) {
                                ViewHolder.this.b.setSelected(false);
                                ViewHolder.this.c.setVisibility(8);
                                if (ImageGridAdapter.this.k != null) {
                                    ImageGridAdapter.this.k.remove(image.f6928a);
                                }
                            } else {
                                ViewHolder.this.b.setSelected(true);
                                ViewHolder.this.c.setVisibility(0);
                                if (ImageGridAdapter.this.k != null) {
                                    ImageGridAdapter.this.k.add(image.f6928a);
                                }
                            }
                            if (ImageGridAdapter.this.e instanceof MultiImageSelectorActivity) {
                                ((MultiImageSelectorActivity) ImageGridAdapter.this.e).updateDoneText();
                                return;
                            }
                            return;
                        }
                        Toast.makeText(ImageGridAdapter.this.e, R.string.mis_msg_amount_limit, 0).show();
                    }
                });
            }
        }
    }
}
