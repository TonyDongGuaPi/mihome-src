package com.mi.multi_image_selector.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.mi.multi_image_selector.R;
import com.mi.multi_image_selector.bean.Folder;
import java.util.ArrayList;
import java.util.List;

public class FolderAdapter extends BaseAdapter {

    /* renamed from: a  reason: collision with root package name */
    int f7388a;
    int b = 0;
    /* access modifiers changed from: private */
    public Context c;
    private LayoutInflater d;
    private List<Folder> e = new ArrayList();

    public long getItemId(int i) {
        return (long) i;
    }

    public FolderAdapter(Context context) {
        this.c = context;
        this.d = (LayoutInflater) context.getSystemService("layout_inflater");
        this.f7388a = this.c.getResources().getDimensionPixelOffset(R.dimen.mis_folder_cover_size);
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
            view = this.d.inflate(R.layout.bbs_mis_list_item_folder, viewGroup, false);
            viewHolder = new ViewHolder(view);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (viewHolder != null) {
            viewHolder.a(getItem(i));
            if (this.b == i) {
                viewHolder.e.setVisibility(0);
            } else {
                viewHolder.e.setVisibility(4);
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
        ImageView f7389a;
        TextView b;
        TextView c;
        TextView d;
        ImageView e;

        ViewHolder(View view) {
            this.f7389a = (ImageView) view.findViewById(R.id.cover);
            this.b = (TextView) view.findViewById(R.id.name);
            this.c = (TextView) view.findViewById(R.id.path);
            this.d = (TextView) view.findViewById(R.id.size);
            this.e = (ImageView) view.findViewById(R.id.indicator);
            view.setTag(this);
        }

        /* access modifiers changed from: package-private */
        public void a(Folder folder) {
            if (folder != null) {
                this.b.setText(folder.f7395a);
                this.c.setText(folder.b);
                if (folder.d != null) {
                    this.d.setText(String.format("%d%s", new Object[]{Integer.valueOf(folder.d.size()), FolderAdapter.this.c.getResources().getString(R.string.mis_photo_unit)}));
                } else {
                    TextView textView = this.d;
                    textView.setText("*" + FolderAdapter.this.c.getResources().getString(R.string.mis_photo_unit));
                }
                if (folder.c != null) {
                    Uri parse = Uri.parse("file://" + folder.c.f7396a);
                    this.f7389a.getLayoutParams().width = 72;
                    this.f7389a.getLayoutParams().height = 72;
                    Glide.c(FolderAdapter.this.c).a(parse).a(this.f7389a);
                    return;
                }
                this.f7389a.setImageResource(R.drawable.bbs_mis_default_error);
            }
        }
    }
}
