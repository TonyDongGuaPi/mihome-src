package com.xiaomi.smarthome.feedback.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.feedback.view.ActionBar;
import java.util.HashSet;
import java.util.Set;

public class BatchBar {

    /* renamed from: a  reason: collision with root package name */
    public static final int f15977a = 0;
    public static final int b = 1;
    /* access modifiers changed from: private */
    public ActionBar c = null;
    private Context d;
    private boolean e = false;
    private boolean f = false;
    private View g = null;
    private View h = null;
    /* access modifiers changed from: private */
    public ImageView i;
    private TextView j;
    private View k;
    /* access modifiers changed from: private */
    public Delegate l = null;
    /* access modifiers changed from: private */
    public boolean m = false;
    /* access modifiers changed from: private */
    public BaseAdapter n = null;
    /* access modifiers changed from: private */
    public HashSet<Integer> o = new HashSet<>();
    /* access modifiers changed from: private */
    public int p = 0;
    private ActionBar.OnShowListener q = new ActionBar.OnShowListener() {
        public void b() {
        }

        public void a() {
            if (BatchBar.this.l != null) {
                BatchBar.this.l.onStartBatchMode();
            }
            BatchBar.this.d();
            BatchBar.this.n.notifyDataSetChanged();
        }
    };
    private ActionBar.OnHideListener r = new ActionBar.OnHideListener() {
        public void a() {
        }

        public void b() {
            if (BatchBar.this.l != null) {
                BatchBar.this.l.onExitBatchMode(BatchBar.this.p, BatchBar.this.n);
            }
            int unused = BatchBar.this.p = 0;
            BatchBar.this.o.clear();
            boolean unused2 = BatchBar.this.m = false;
            BatchBar.this.n.notifyDataSetChanged();
            BatchBar.this.c.a();
        }
    };

    public interface Delegate {
        View getContentViewOfBatchActionBar();

        View getContentViewOfBatchSelectBar();

        void onExitBatchMode(int i, BaseAdapter baseAdapter);

        void onStartBatchMode();

        void onUpdateBatchBarViewState(int i);
    }

    public BatchBar(Context context) {
        this.d = context;
    }

    public void a(ViewGroup viewGroup, ViewGroup viewGroup2) {
        this.c = new ActionBar(this.d, viewGroup, viewGroup2);
    }

    public void a(Delegate delegate) {
        this.l = delegate;
    }

    public boolean a() {
        return this.m;
    }

    public void a(BaseAdapter baseAdapter) {
        this.n = baseAdapter;
        this.o.clear();
        this.m = true;
        if (this.g == null) {
            if (this.l != null) {
                this.g = this.l.getContentViewOfBatchSelectBar();
            }
            if (this.g == null) {
                this.e = true;
                this.g = LayoutInflater.from(this.d).inflate(R.layout.batch_select_bar, (ViewGroup) null);
                this.g.findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        BatchBar.this.a(0);
                    }
                });
                this.i = (ImageView) this.g.findViewById(R.id.btn_select_all);
                this.i.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (((Boolean) BatchBar.this.i.getTag()).booleanValue()) {
                            for (int i = 0; i < BatchBar.this.n.getCount(); i++) {
                                BatchBar.this.o.add(Integer.valueOf(i));
                            }
                            BatchBar.this.i.setImageResource(R.drawable.un_select_selector);
                            BatchBar.this.i.setTag(false);
                        } else {
                            BatchBar.this.o.clear();
                            BatchBar.this.i.setImageResource(R.drawable.all_select_selector);
                            BatchBar.this.i.setTag(true);
                        }
                        BatchBar.this.d();
                        BatchBar.this.n.notifyDataSetChanged();
                    }
                });
                this.j = (TextView) this.g.findViewById(R.id.text_prompt);
            }
        }
        if (this.h == null) {
            if (this.l != null) {
                this.h = this.l.getContentViewOfBatchActionBar();
            }
            if (this.h == null) {
                this.f = true;
                this.h = LayoutInflater.from(this.d).inflate(R.layout.batch_action_bar, (ViewGroup) null);
                this.k = this.h.findViewById(R.id.btn_ok);
                this.k.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        BatchBar.this.a(1);
                    }
                });
            }
        }
        this.c.a();
        this.c.a(this.g, this.h);
        this.c.a(this.q);
        this.c.a(this.r);
        this.c.f();
    }

    public void a(int i2) {
        this.p = i2;
        this.c.g();
    }

    public int b() {
        return this.o.size();
    }

    public Set<Integer> c() {
        HashSet hashSet = new HashSet();
        hashSet.addAll(this.o);
        return hashSet;
    }

    public boolean a(Integer num) {
        return this.o.contains(num);
    }

    public void b(Integer num) {
        if (this.o.contains(num)) {
            this.o.remove(num);
        } else {
            this.o.add(num);
        }
        d();
        this.n.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public void d() {
        int b2 = b();
        if (b2 > 0) {
            if (this.e) {
                this.j.setText(this.d.getResources().getQuantityString(R.plurals.selected_cnt_tips, b2, new Object[]{Integer.valueOf(b2)}));
                if (b2 >= this.n.getCount()) {
                    this.i.setImageResource(R.drawable.un_select_selector);
                    this.i.setTag(false);
                } else {
                    this.i.setImageResource(R.drawable.all_select_selector);
                    this.i.setTag(true);
                }
            }
            if (this.f) {
                this.k.setEnabled(true);
            }
        } else {
            if (this.e) {
                this.j.setText(R.string.selected_0_cnt_tips);
                this.i.setImageResource(R.drawable.all_select_selector);
                this.i.setTag(true);
            }
            if (this.f) {
                this.k.setEnabled(false);
            }
        }
        if (this.l != null) {
            this.l.onUpdateBatchBarViewState(b2);
        }
    }
}
