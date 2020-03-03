package com.xiaomi.smarthome.library.common.dialog;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;

public class SimpleListDialog extends MLAlertDialog {

    /* renamed from: a  reason: collision with root package name */
    public static final int f18586a = 0;
    public static final int b = 1;
    private TextView c;
    private ListView d;
    /* access modifiers changed from: private */
    public SimpleListAdapter e;
    /* access modifiers changed from: private */
    public CharSequence[] f;
    private LinearLayout g;
    private Button h;
    private Button i;
    private String j;
    /* access modifiers changed from: private */
    public IDialogInterface k;
    private int l;
    /* access modifiers changed from: private */
    public View.OnClickListener m;
    private int n;
    /* access modifiers changed from: private */
    public View.OnClickListener o;
    /* access modifiers changed from: private */
    public int p;
    /* access modifiers changed from: private */
    public int q;

    public interface IDialogInterface {
        void a(int i);
    }

    public SimpleListDialog(Context context) {
        this(context, 2131559365);
    }

    public SimpleListDialog(Context context, int i2) {
        super(context, i2);
    }

    public void a(String str) {
        this.j = str;
    }

    public void a(int i2) {
        this.p = i2;
    }

    public void b(int i2) {
        this.q = i2;
    }

    public int a() {
        return this.q;
    }

    public void a(CharSequence[] charSequenceArr, IDialogInterface iDialogInterface) {
        this.f = charSequenceArr;
        this.k = iDialogInterface;
    }

    public void a(int i2, View.OnClickListener onClickListener) {
        this.l = i2;
        this.m = onClickListener;
    }

    public void b(int i2, View.OnClickListener onClickListener) {
        this.n = i2;
        this.o = onClickListener;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.simple_list_dialog, (ViewGroup) null);
        this.g = (LinearLayout) inflate.findViewById(R.id.buttonPanel);
        this.i = (Button) inflate.findViewById(R.id.button2);
        if (this.n > 0) {
            this.i.setText(this.n);
        }
        this.i.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SimpleListDialog.this.o != null) {
                    SimpleListDialog.this.o.onClick(view);
                }
                SimpleListDialog.this.dismiss();
            }
        });
        this.h = (Button) inflate.findViewById(R.id.button1);
        if (this.l > 0) {
            this.h.setText(this.l);
        }
        this.h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SimpleListDialog.this.m != null) {
                    SimpleListDialog.this.m.onClick(view);
                }
                SimpleListDialog.this.dismiss();
            }
        });
        this.c = (TextView) inflate.findViewById(R.id.title);
        this.d = (ListView) inflate.findViewById(R.id.listview);
        this.e = new SimpleListAdapter();
        this.d.setAdapter(this.e);
        if (!TextUtils.isEmpty(this.j)) {
            this.c.setVisibility(0);
            this.c.setText(this.j);
        } else {
            this.c.setVisibility(8);
        }
        if (this.p == 0) {
            this.g.setVisibility(8);
        } else if (this.p == 1) {
            this.g.setVisibility(0);
        }
        setView(inflate);
        super.onCreate(bundle);
    }

    private class SimpleListAdapter extends BaseAdapter {
        public long getItemId(int i) {
            return (long) i;
        }

        private SimpleListAdapter() {
        }

        public int getCount() {
            return SimpleListDialog.this.f.length;
        }

        public Object getItem(int i) {
            return SimpleListDialog.this.f[i];
        }

        private class ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            TextView f18591a;
            ImageView b;

            private ViewHolder() {
            }
        }

        public int getItemViewType(int i) {
            return SimpleListDialog.this.p;
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            int i2;
            ViewHolder viewHolder;
            switch (getItemViewType(i)) {
                case 0:
                    i2 = R.layout.simple_list_dialog_item;
                    break;
                case 1:
                    i2 = R.layout.dialog_list_item_single_choice;
                    break;
                default:
                    i2 = 0;
                    break;
            }
            if (view == null) {
                view = LayoutInflater.from(SimpleListDialog.this.getContext()).inflate(i2, (ViewGroup) null);
                viewHolder = new ViewHolder();
                viewHolder.f18591a = (TextView) view.findViewById(R.id.textview);
                viewHolder.b = (ImageView) view.findViewById(R.id.icon);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.f18591a.setText((CharSequence) getItem(i));
            if (SimpleListDialog.this.p == 0) {
                viewHolder.f18591a.setTextColor(Color.parseColor("#333333"));
            } else if (SimpleListDialog.this.p == 1) {
                viewHolder.f18591a.setTextColor(SimpleListDialog.this.mContext.getResources().getColor(R.color.select_dialog_single_color));
                if (i == SimpleListDialog.this.q) {
                    viewHolder.b.setVisibility(0);
                } else {
                    viewHolder.b.setVisibility(4);
                }
            }
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (SimpleListDialog.this.k != null) {
                        SimpleListDialog.this.k.a(i);
                    }
                    if (SimpleListDialog.this.p == 0) {
                        SimpleListDialog.this.dismiss();
                    } else if (SimpleListDialog.this.p == 1) {
                        int unused = SimpleListDialog.this.q = i;
                        SimpleListDialog.this.e.notifyDataSetChanged();
                    } else {
                        throw new IllegalArgumentException("unknown list mode");
                    }
                }
            });
            return view;
        }
    }
}
