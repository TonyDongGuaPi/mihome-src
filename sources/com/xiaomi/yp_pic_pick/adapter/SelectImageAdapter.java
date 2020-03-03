package com.xiaomi.yp_pic_pick.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.xiaomi.yp_pic_pick.R;
import com.xiaomi.yp_pic_pick.bean.PictureItem;
import com.xiaomi.yp_ui.utils.FrescoUtils;
import java.util.ArrayList;

public class SelectImageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    ArrayList<PictureItem> f19519a = new ArrayList<>();
    Context b;
    LayoutInflater c;
    OnItemClickListener d;
    ResizeOptions e;
    int f = -1;
    private int g;

    public interface OnItemClickListener {
        void a(int i);
    }

    public SelectImageAdapter(Context context) {
        this.b = context;
        this.c = LayoutInflater.from(context);
        this.g = context.getResources().getDimensionPixelSize(R.dimen.size_52dp);
        this.e = new ResizeOptions(this.g, this.g);
    }

    public void a(ArrayList<PictureItem> arrayList) {
        this.f = -1;
        this.f19519a.clear();
        if (arrayList != null) {
            this.f19519a.addAll(arrayList);
        }
        notifyDataSetChanged();
    }

    public void a(ArrayList<PictureItem> arrayList, int i) {
        this.f = i;
        this.f19519a.clear();
        if (arrayList != null) {
            this.f19519a.addAll(arrayList);
        }
        notifyDataSetChanged();
    }

    public int a() {
        return this.f;
    }

    public void a(int i) {
        this.f = i;
        notifyDataSetChanged();
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return SelectImageViewHolder.a(this, viewGroup);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        SelectImageViewHolder.a(this, viewHolder, i);
    }

    public int getItemCount() {
        if (this.f19519a == null) {
            return 0;
        }
        return this.f19519a.size();
    }

    public Object b(int i) {
        return this.f19519a.get(i);
    }

    public void a(OnItemClickListener onItemClickListener) {
        this.d = onItemClickListener;
    }

    static class SelectImageViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public SimpleDraweeView f19520a;
        public View b;

        public SelectImageViewHolder(View view) {
            super(view);
            this.f19520a = (SimpleDraweeView) view.findViewById(R.id.album_img);
            this.b = view.findViewById(R.id.overlay);
        }

        public static RecyclerView.ViewHolder a(SelectImageAdapter selectImageAdapter, ViewGroup viewGroup) {
            return new SelectImageViewHolder(selectImageAdapter.c.inflate(R.layout.select_image_item, viewGroup, false));
        }

        public static void a(final SelectImageAdapter selectImageAdapter, RecyclerView.ViewHolder viewHolder, final int i) {
            SelectImageViewHolder selectImageViewHolder = (SelectImageViewHolder) viewHolder;
            PictureItem pictureItem = (PictureItem) selectImageAdapter.b(i);
            if (selectImageAdapter.f == i) {
                selectImageViewHolder.b.setVisibility(0);
            } else {
                selectImageViewHolder.b.setVisibility(4);
            }
            FrescoUtils.a(selectImageViewHolder.f19520a, pictureItem.a().toString(), selectImageAdapter.e, R.drawable.icon_photo_default);
            selectImageViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (selectImageAdapter.d != null) {
                        selectImageAdapter.d.a(i);
                    }
                }
            });
        }
    }
}
