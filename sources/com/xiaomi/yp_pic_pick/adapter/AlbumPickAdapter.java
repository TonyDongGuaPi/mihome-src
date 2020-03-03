package com.xiaomi.yp_pic_pick.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.xiaomi.yp_pic_pick.R;
import com.xiaomi.yp_pic_pick.bean.PictureAlbum;
import com.xiaomi.yp_pic_pick.widgets.YPSimpleDraweeView;
import com.xiaomi.yp_ui.utils.FrescoImageLoader;
import java.util.ArrayList;

public class AlbumPickAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    ArrayList<PictureAlbum> f19511a;
    Context b;
    LayoutInflater c;
    OnItemClickListener d;
    ResizeOptions e = new ResizeOptions(this.f, this.f);
    private int f;

    public interface OnItemClickListener {
        void a(int i);
    }

    public AlbumPickAdapter(Context context, ArrayList<PictureAlbum> arrayList) {
        this.b = context;
        this.c = LayoutInflater.from(context);
        this.f19511a = arrayList;
        this.f = context.getResources().getDimensionPixelSize(R.dimen.size_80dp);
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return AlbumPickItemViewHolder.a(this, viewGroup);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        AlbumPickItemViewHolder.a(this, viewHolder, i);
    }

    public int getItemCount() {
        if (this.f19511a == null) {
            return 0;
        }
        return this.f19511a.size();
    }

    public Object a(int i) {
        return this.f19511a.get(i);
    }

    public void a(OnItemClickListener onItemClickListener) {
        this.d = onItemClickListener;
    }

    static class AlbumPickItemViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public SimpleDraweeView f19512a;
        public TextView b;
        public ImageView c;
        public TextView d;

        public AlbumPickItemViewHolder(View view) {
            super(view);
            this.f19512a = (SimpleDraweeView) view.findViewById(R.id.album_img);
            this.b = (TextView) view.findViewById(R.id.album_title);
            this.c = (ImageView) view.findViewById(R.id.selected);
            this.d = (TextView) view.findViewById(R.id.album_size);
        }

        public static RecyclerView.ViewHolder a(AlbumPickAdapter albumPickAdapter, ViewGroup viewGroup) {
            return new AlbumPickItemViewHolder(albumPickAdapter.c.inflate(R.layout.album_select_item, viewGroup, false));
        }

        public static void a(final AlbumPickAdapter albumPickAdapter, RecyclerView.ViewHolder viewHolder, final int i) {
            AlbumPickItemViewHolder albumPickItemViewHolder = (AlbumPickItemViewHolder) viewHolder;
            PictureAlbum pictureAlbum = (PictureAlbum) albumPickAdapter.a(i);
            albumPickItemViewHolder.b.setText(pictureAlbum.b());
            FrescoImageLoader.Builder b2 = new FrescoImageLoader.Builder().a((DraweeView<GenericDraweeHierarchy>) albumPickItemViewHolder.f19512a).a(albumPickAdapter.e).b(R.drawable.icon_photo_default);
            if (pictureAlbum.e() != null) {
                b2.a(pictureAlbum.e().toString());
                if (albumPickItemViewHolder.f19512a instanceof YPSimpleDraweeView) {
                    ((YPSimpleDraweeView) albumPickItemViewHolder.f19512a).setSourceUrl(pictureAlbum.e().toString());
                }
            }
            b2.a().a();
            albumPickItemViewHolder.c.setImageResource(pictureAlbum.d() ? R.drawable.icon_img_select : R.drawable.icon_img_unselect);
            albumPickItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (albumPickAdapter.d != null) {
                        albumPickAdapter.d.a(i);
                    }
                }
            });
            albumPickItemViewHolder.d.setText(String.valueOf(pictureAlbum.a().size()));
        }
    }
}
