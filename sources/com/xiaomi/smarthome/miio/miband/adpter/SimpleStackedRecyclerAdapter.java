package com.xiaomi.smarthome.miio.miband.adpter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.miio.miband.data.DataManager;
import com.xiaomi.smarthome.miio.miband.data.SleepDataItem;
import java.util.ArrayList;
import java.util.List;

public class SimpleStackedRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int j = 0;
    private static final int k = 1;
    private static final int l = 2;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public List<SleepDataItem> f19449a = new ArrayList();
    private float b;
    private Context c;
    private SparseIntArray d = new SparseIntArray();
    /* access modifiers changed from: private */
    public int e = -1;
    private float f = -1.0f;
    private float g = -1.0f;
    private int h = 34;
    private int i = 3;
    /* access modifiers changed from: private */
    public Resources m;
    /* access modifiers changed from: private */
    public OnItemClickedListener n;

    public interface OnItemClickedListener {
        void a(int i, SleepDataItem sleepDataItem);
    }

    private boolean d(int i2) {
        return i2 == 0;
    }

    public SimpleStackedRecyclerAdapter(Context context) {
        this.c = context;
        this.m = this.c.getResources();
        this.b = this.m.getDisplayMetrics().density;
    }

    public void a(List<SleepDataItem> list) {
        if (list == null) {
            this.f19449a.clear();
        } else {
            this.f19449a = list;
        }
    }

    public void a(int i2) {
        this.e = i2;
    }

    public int a() {
        return this.e;
    }

    public SleepDataItem b() {
        if (this.e < 1 || this.e >= this.f19449a.size() + 1) {
            return null;
        }
        return this.f19449a.get(this.e - 1);
    }

    public void a(OnItemClickedListener onItemClickedListener) {
        this.n = onItemClickedListener;
    }

    public void a(float f2, float f3) {
        this.f = f2;
        this.g = f3;
    }

    public void b(int i2) {
        this.i = i2;
        if (this.i == 3) {
            this.h = 34;
        } else if (this.i == 2) {
            this.h = 34;
        } else if (this.i == 1) {
            this.h = 34;
        } else if (this.i == 0) {
            this.h = 34;
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        if (i2 == 1) {
            return new SimpleItemViewHolder(LayoutInflater.from(this.c).inflate(R.layout.miband_sleep_list_item_view, viewGroup, false));
        }
        if (i2 == 0) {
            return new SimpleHeaderViewHolder(LayoutInflater.from(this.c).inflate(R.layout.miband_sleep_list_item_view, viewGroup, false));
        }
        if (i2 == 2) {
            return new SimpleHeaderViewHolder(LayoutInflater.from(this.c).inflate(R.layout.miband_sleep_list_item_view, viewGroup, false));
        }
        return new SimpleItemViewHolder(LayoutInflater.from(this.c).inflate(R.layout.miband_sleep_list_item_view, viewGroup, false));
    }

    public void c(int i2) {
        this.e = i2;
        this.d.clear();
        super.notifyDataSetChanged();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i2) {
        if (viewHolder instanceof SimpleItemViewHolder) {
            SimpleItemViewHolder simpleItemViewHolder = (SimpleItemViewHolder) viewHolder;
            final SleepDataItem sleepDataItem = this.f19449a.get(i2 - 1);
            ViewGroup.LayoutParams layoutParams = simpleItemViewHolder.b.getLayoutParams();
            ViewGroup.LayoutParams layoutParams2 = simpleItemViewHolder.c.getLayoutParams();
            ViewGroup.LayoutParams layoutParams3 = simpleItemViewHolder.d.getLayoutParams();
            float a2 = a((float) (sleepDataItem.b + sleepDataItem.f19464a));
            if (a2 < 0.01f) {
                simpleItemViewHolder.b.setVisibility(8);
                simpleItemViewHolder.c.setVisibility(8);
            } else {
                simpleItemViewHolder.b.setVisibility(0);
                simpleItemViewHolder.c.setVisibility(0);
                layoutParams.width = (int) (this.b * 32.0f);
                layoutParams2.width = (int) (this.b * 32.0f);
                double d2 = (double) a2;
                double d3 = (double) sleepDataItem.b;
                Double.isNaN(d3);
                double d4 = (double) (sleepDataItem.b + sleepDataItem.f19464a);
                Double.isNaN(d4);
                Double.isNaN(d2);
                layoutParams.height = (int) (((d3 * 1.0d) / d4) * d2);
                double d5 = (double) sleepDataItem.f19464a;
                Double.isNaN(d5);
                Double.isNaN(d2);
                double d6 = d2 * d5 * 1.0d;
                double d7 = (double) (sleepDataItem.f19464a + sleepDataItem.b);
                Double.isNaN(d7);
                layoutParams2.height = (int) (d6 / d7);
                layoutParams3.height = (int) a2;
                simpleItemViewHolder.d.setMinimumHeight(layoutParams3.height);
                simpleItemViewHolder.d.setLayoutParams(layoutParams3);
                simpleItemViewHolder.b.setMinimumHeight(layoutParams.height);
                simpleItemViewHolder.b.setLayoutParams(layoutParams);
                simpleItemViewHolder.c.setMinimumHeight(layoutParams2.height);
                simpleItemViewHolder.c.setLayoutParams(layoutParams2);
            }
            if (this.e == i2) {
                simpleItemViewHolder.f19453a.setTextColor(this.c.getResources().getColor(R.color.sleep_bar_bg_select_text));
                simpleItemViewHolder.b.setBackgroundResource(R.drawable.miband_deep_sleep_bar_select);
                simpleItemViewHolder.c.setBackgroundResource(R.drawable.miband_shallow_sleep_bar_select);
            } else {
                simpleItemViewHolder.f19453a.setTextColor(this.c.getResources().getColor(R.color.sleep_bar_bg_normal_text));
                simpleItemViewHolder.b.setBackgroundResource(R.drawable.miband_deep_sleep_bar_normal);
                simpleItemViewHolder.c.setBackgroundResource(R.drawable.miband_shallow_sleep_bar_normal);
            }
            simpleItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    int unused = SimpleStackedRecyclerAdapter.this.e = i2;
                    SimpleStackedRecyclerAdapter.this.notifyDataSetChanged();
                }
            });
            simpleItemViewHolder.itemView.getLayoutParams().width = (int) (((float) this.h) * this.b);
            simpleItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (SimpleStackedRecyclerAdapter.this.n != null) {
                        int unused = SimpleStackedRecyclerAdapter.this.e = i2;
                        SimpleStackedRecyclerAdapter.this.n.a(i2, sleepDataItem);
                        SimpleStackedRecyclerAdapter.this.notifyDataSetChanged();
                    }
                }
            });
            simpleItemViewHolder.f19453a.setText(DataManager.a(this.c, this.i, sleepDataItem.g));
            a(simpleItemViewHolder, i2);
            return;
        }
        boolean z = viewHolder instanceof SimpleHeaderViewHolder;
    }

    private float a(float f2) {
        if (this.f == -1.0f || this.g == -1.0f || this.f == 0.0f) {
            if (((double) f2) < 0.001d) {
                return 0.0f;
            }
            return this.b * 110.0f;
        } else if (this.f == this.g) {
            return this.b * 110.0f;
        } else {
            return f2 * ((this.b * 220.0f) / this.f);
        }
    }

    public int getItemCount() {
        return this.f19449a.size() + 2;
    }

    public int getItemViewType(int i2) {
        if (d(i2)) {
            return 0;
        }
        return e(i2) ? 2 : 1;
    }

    private boolean e(int i2) {
        return i2 == this.f19449a.size() + 1;
    }

    private void a(SimpleItemViewHolder simpleItemViewHolder, int i2) {
        if (this.d.get(i2, -1) == -1) {
            AnimatorSet animatorSet = new AnimatorSet();
            simpleItemViewHolder.d.setPivotY((float) simpleItemViewHolder.d.getLayoutParams().height);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(simpleItemViewHolder.d, "scaleY", new float[]{0.0f, 1.0f});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(simpleItemViewHolder.d, "alpha", new float[]{0.0f, 1.0f});
            animatorSet.play(ofFloat).with(ofFloat2).with(ObjectAnimator.ofFloat(simpleItemViewHolder.f19453a, "scaleX", new float[]{0.0f, 1.0f}));
            animatorSet.setDuration(600);
            animatorSet.start();
            this.d.put(i2, i2);
        }
    }

    public final class SimpleItemViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f19453a;
        View b;
        View c;
        View d;

        public SimpleItemViewHolder(View view) {
            super(view);
            this.f19453a = (TextView) view.findViewById(R.id.date_tv);
            this.b = view.findViewById(R.id.bottom_bar);
            this.c = view.findViewById(R.id.top_bar);
            this.d = view.findViewById(R.id.rl_bar_container);
        }
    }

    public final class SimpleHeaderViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f19452a;
        View b;
        View c;

        public SimpleHeaderViewHolder(View view) {
            super(view);
            this.f19452a = (TextView) view.findViewById(R.id.date_tv);
            this.f19452a.setVisibility(4);
            this.b = view.findViewById(R.id.bottom_bar);
            this.b.setVisibility(4);
            this.c = view.findViewById(R.id.top_bar);
            this.c.setVisibility(4);
            if (SimpleStackedRecyclerAdapter.this.f19449a.size() > 0) {
                view.getLayoutParams().width = (int) (((float) (SimpleStackedRecyclerAdapter.this.m.getDisplayMetrics().widthPixels / 2)) - (SimpleStackedRecyclerAdapter.this.m.getDisplayMetrics().density * 25.0f));
                return;
            }
            view.getLayoutParams().width = SimpleStackedRecyclerAdapter.this.m.getDisplayMetrics().widthPixels / 2;
        }
    }
}
