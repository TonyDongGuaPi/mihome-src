package com.xiaomi.smarthome.library.common.widget;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import java.util.ArrayList;
import java.util.List;

public class ChipsAdapter extends BaseAdapter implements Filterable {

    /* renamed from: a  reason: collision with root package name */
    Filter f18777a = new Filter() {
        public CharSequence convertResultToString(Object obj) {
            return ((ChipsItem) obj).b;
        }

        /* access modifiers changed from: protected */
        public Filter.FilterResults performFiltering(CharSequence charSequence) {
            Log.d("filter", "" + charSequence);
            Filter.FilterResults filterResults = new Filter.FilterResults();
            if (charSequence != null) {
                ChipsAdapter.this.c.clear();
                int i = 0;
                while (i < ChipsAdapter.this.b.size()) {
                    try {
                        if (((ChipsItem) ChipsAdapter.this.b.get(i)).f18780a.toLowerCase().startsWith(charSequence.toString().toLowerCase())) {
                            ChipsAdapter.this.c.add(ChipsAdapter.this.b.get(i));
                        } else if (((ChipsItem) ChipsAdapter.this.b.get(i)).b.toLowerCase().startsWith(charSequence.toString().toLowerCase())) {
                            ChipsAdapter.this.c.add(ChipsAdapter.this.b.get(i));
                        }
                        i++;
                    } catch (Exception unused) {
                    }
                }
                filterResults.values = ChipsAdapter.this.c;
                filterResults.count = ChipsAdapter.this.c.size();
            } else {
                ChipsAdapter.this.c.clear();
                ChipsAdapter.this.c.addAll(ChipsAdapter.this.b);
            }
            return filterResults;
        }

        /* access modifiers changed from: protected */
        public void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
            if (filterResults == null || filterResults.count <= 0) {
                ChipsAdapter.this.notifyDataSetInvalidated();
            } else {
                ChipsAdapter.this.notifyDataSetChanged();
            }
        }
    };
    /* access modifiers changed from: private */
    public List<ChipsItem> b;
    /* access modifiers changed from: private */
    public ArrayList<ChipsItem> c;
    private Context d;
    private LayoutInflater e;
    private String f = getClass().getSimpleName();

    public long getItemId(int i) {
        return (long) i;
    }

    public ChipsAdapter(Context context, List<ChipsItem> list) {
        this.d = context;
        this.b = list;
        this.c = new ArrayList<>();
        this.c.addAll(list);
    }

    public int getCount() {
        return this.c.size();
    }

    /* renamed from: a */
    public ChipsItem getItem(int i) {
        return this.c.get(i);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            if (this.e == null) {
                this.e = LayoutInflater.from(this.d);
            }
            view = this.e.inflate(R.layout.chips_adapter, (ViewGroup) null);
            viewHolder = new ViewHolder();
            viewHolder.b = (SimpleDraweeView) view.findViewById(R.id.user_icon);
            viewHolder.f18779a = (TextView) view.findViewById(R.id.user_name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (TextUtils.isEmpty(this.c.get(i).c)) {
            viewHolder.b.setImageURI(CommonUtils.c((int) R.drawable.default_head_01));
        } else {
            viewHolder.b.setImageURI(Uri.parse(this.c.get(i).c));
        }
        viewHolder.f18779a.setText(String.format("%s(%s)", new Object[]{this.c.get(i).f18780a, this.c.get(i).b}));
        return view;
    }

    class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f18779a;
        SimpleDraweeView b;

        ViewHolder() {
        }
    }

    public Filter getFilter() {
        return this.f18777a;
    }
}
