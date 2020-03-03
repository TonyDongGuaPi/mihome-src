package com.xiaomi.yp_pic_pick.adapter;

import android.app.Activity;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.xiaomi.yp_pic_pick.R;
import com.xiaomi.yp_pic_pick.bean.PictureAlbum;
import com.xiaomi.yp_pic_pick.bean.PictureItem;
import com.xiaomi.yp_pic_pick.widgets.YPSimpleDraweeView;
import com.xiaomi.yp_ui.utils.FrescoUtils;
import java.util.ArrayList;

public class PicturePickAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    public static final int f19514a = 3;
    private static ArrayList<PictureAlbum> b;
    private static int c;
    private ArrayList<PictureItem> d;
    /* access modifiers changed from: private */
    public LayoutInflater e;
    private int f;
    /* access modifiers changed from: private */
    public OnImageSelectListener g;
    /* access modifiers changed from: private */
    public OnImageAboveListener h;
    /* access modifiers changed from: private */
    public OnTakePhotoListener i;
    /* access modifiers changed from: private */
    public ResizeOptions j;

    public interface OnImageAboveListener {
        void a(PictureItem pictureItem, int i);
    }

    public interface OnImageSelectListener {
        void a(PictureItem pictureItem, int i);
    }

    public interface OnTakePhotoListener {
        void a();
    }

    public PicturePickAdapter(Activity activity, ArrayList<PictureAlbum> arrayList) {
        this.e = LayoutInflater.from(activity);
        b = arrayList;
        c = 0;
        Resources resources = activity.getResources();
        this.f = (resources.getDisplayMetrics().widthPixels - (resources.getDimensionPixelSize(R.dimen.size_10dp) * 2)) / 3;
        if (b.size() != 0) {
            this.d = b.get(0).a();
        }
        this.j = new ResizeOptions(this.f, this.f);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        RecyclerView.ViewHolder viewHolder;
        if (i2 == 1) {
            viewHolder = TakePhotoViewHolder.a(this, viewGroup);
        } else {
            viewHolder = i2 == 2 ? NormalImageViewHolder.a(this, viewGroup) : null;
        }
        if (viewHolder != null) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewHolder.itemView.getLayoutParams();
            marginLayoutParams.width = this.f;
            marginLayoutParams.height = this.f;
        }
        return viewHolder;
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        int itemViewType = getItemViewType(i2);
        if (itemViewType == 1) {
            TakePhotoViewHolder.a(this, viewHolder, i2);
        } else if (itemViewType == 2) {
            NormalImageViewHolder.a(this, viewHolder, i2);
        }
    }

    public int getItemCount() {
        if (this.d == null) {
            return 0;
        }
        return this.d.size();
    }

    public Object a(int i2) {
        return this.d.get(i2);
    }

    public int getItemViewType(int i2) {
        return ((PictureItem) a(i2)).e();
    }

    public void b(int i2) {
        if (i2 >= 0 && i2 < b.size()) {
            c = i2;
            this.d = b.get(c).a();
        }
        notifyDataSetChanged();
    }

    public void a(OnImageSelectListener onImageSelectListener) {
        this.g = onImageSelectListener;
    }

    public void a(OnImageAboveListener onImageAboveListener) {
        this.h = onImageAboveListener;
    }

    public void a(OnTakePhotoListener onTakePhotoListener) {
        this.i = onTakePhotoListener;
    }

    static class NormalImageViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public SimpleDraweeView f19515a;
        public ImageView b;

        public NormalImageViewHolder(View view) {
            super(view);
            this.f19515a = (SimpleDraweeView) view.findViewById(R.id.album_img);
            this.b = (ImageView) view.findViewById(R.id.selected);
        }

        public static RecyclerView.ViewHolder a(PicturePickAdapter picturePickAdapter, ViewGroup viewGroup) {
            return new NormalImageViewHolder(picturePickAdapter.e.inflate(R.layout.picture_select_item, viewGroup, false));
        }

        public static void a(final PicturePickAdapter picturePickAdapter, RecyclerView.ViewHolder viewHolder, final int i) {
            final NormalImageViewHolder normalImageViewHolder = (NormalImageViewHolder) viewHolder;
            final PictureItem pictureItem = (PictureItem) picturePickAdapter.a(i);
            FrescoUtils.a(normalImageViewHolder.f19515a, pictureItem.a().toString(), picturePickAdapter.j, R.drawable.icon_photo_default);
            if (normalImageViewHolder.f19515a instanceof YPSimpleDraweeView) {
                ((YPSimpleDraweeView) normalImageViewHolder.f19515a).setSourceUrl(pictureItem.a().toString());
            }
            normalImageViewHolder.b.setImageResource(pictureItem.c() ? R.drawable.icon_img_select : R.drawable.icon_img_unselect);
            normalImageViewHolder.b.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    pictureItem.a(!pictureItem.c());
                    normalImageViewHolder.b.setImageResource(pictureItem.c() ? R.drawable.icon_img_select : R.drawable.icon_img_unselect);
                    if (picturePickAdapter.g != null) {
                        picturePickAdapter.g.a(pictureItem, i);
                    }
                }
            });
            normalImageViewHolder.f19515a.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (picturePickAdapter.h != null) {
                        picturePickAdapter.h.a(pictureItem, i);
                    }
                }
            });
        }
    }

    static class TakePhotoViewHolder extends RecyclerView.ViewHolder {
        public TakePhotoViewHolder(View view) {
            super(view);
        }

        public static RecyclerView.ViewHolder a(PicturePickAdapter picturePickAdapter, ViewGroup viewGroup) {
            return new TakePhotoViewHolder(picturePickAdapter.e.inflate(R.layout.take_photo_item, viewGroup, false));
        }

        public static void a(final PicturePickAdapter picturePickAdapter, RecyclerView.ViewHolder viewHolder, int i) {
            ((TakePhotoViewHolder) viewHolder).itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (picturePickAdapter.i != null) {
                        picturePickAdapter.i.a();
                    }
                }
            });
        }
    }
}
