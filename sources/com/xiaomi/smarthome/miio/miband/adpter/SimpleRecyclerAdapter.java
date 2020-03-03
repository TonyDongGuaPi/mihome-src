package com.xiaomi.smarthome.miio.miband.adpter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseIntArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.miio.miband.data.DataManager;
import com.xiaomi.smarthome.miio.miband.data.StepDataItem;
import java.util.ArrayList;
import java.util.List;

public class SimpleRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int l = 0;
    private static final int m = 1;
    private static final int n = 2;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public List<StepDataItem> f19444a = new ArrayList();
    private float b;
    private Context c;
    private int d = -1;
    private SparseIntArray e = new SparseIntArray();
    /* access modifiers changed from: private */
    public int f = -1;
    private float g = -1.0f;
    private float h = -1.0f;
    private int i = 34;
    private int j = 3;
    private LinearLayoutManager k;
    /* access modifiers changed from: private */
    public Resources o;
    /* access modifiers changed from: private */
    public OnItemClickedListener p;

    public interface OnItemClickedListener {
        void a(int i, StepDataItem stepDataItem);
    }

    private boolean d(int i2) {
        return i2 == 0;
    }

    public SimpleRecyclerAdapter(Context context, LinearLayoutManager linearLayoutManager) {
        this.c = context;
        this.o = this.c.getResources();
        this.b = this.o.getDisplayMetrics().density;
        this.k = linearLayoutManager;
    }

    public void a(List<StepDataItem> list) {
        if (list == null) {
            this.f19444a.clear();
        } else {
            this.f19444a = list;
        }
    }

    public void a(int i2) {
        this.f = i2;
    }

    public int a() {
        return this.f;
    }

    public StepDataItem b() {
        if (this.f < 1 || this.f >= this.f19444a.size() + 1) {
            return null;
        }
        return this.f19444a.get(this.f - 1);
    }

    public void a(OnItemClickedListener onItemClickedListener) {
        this.p = onItemClickedListener;
    }

    public void a(float f2, float f3) {
        this.g = f2;
        this.h = f3;
    }

    public void b(int i2) {
        this.j = i2;
        if (this.j == 3) {
            this.i = 34;
        } else if (this.j == 2) {
            this.i = 34;
        } else if (this.j == 1) {
            this.i = 34;
        } else if (this.j == 0) {
            this.i = 34;
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        if (i2 == 1) {
            return new SimpleItemViewHolder(LayoutInflater.from(this.c).inflate(R.layout.miband_num_list_item_view, viewGroup, false));
        }
        if (i2 == 0) {
            return new SimpleHeaderViewHolder(LayoutInflater.from(this.c).inflate(R.layout.miband_num_list_item_view, viewGroup, false));
        }
        if (i2 == 2) {
            return new SimpleHeaderViewHolder(LayoutInflater.from(this.c).inflate(R.layout.miband_num_list_item_view, viewGroup, false));
        }
        return new SimpleItemViewHolder(LayoutInflater.from(this.c).inflate(R.layout.miband_num_list_item_view, viewGroup, false));
    }

    public void c(int i2) {
        this.f = i2;
        this.e.clear();
        super.notifyDataSetChanged();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i2) {
        if (viewHolder instanceof SimpleItemViewHolder) {
            SimpleItemViewHolder simpleItemViewHolder = (SimpleItemViewHolder) viewHolder;
            final StepDataItem stepDataItem = this.f19444a.get(i2 - 1);
            ViewGroup.LayoutParams layoutParams = simpleItemViewHolder.b.getLayoutParams();
            float b2 = b((float) stepDataItem.g);
            if (b2 < 0.01f) {
                simpleItemViewHolder.b.setVisibility(8);
            } else {
                simpleItemViewHolder.b.setVisibility(0);
                layoutParams.width = (int) (this.b * 32.0f);
                layoutParams.height = (int) b2;
                simpleItemViewHolder.b.setMinimumHeight(layoutParams.height);
                simpleItemViewHolder.b.setLayoutParams(layoutParams);
            }
            if (this.f == i2) {
                simpleItemViewHolder.f19448a.setTextColor(this.c.getResources().getColor(R.color.sport_bar_bg_select_text));
                simpleItemViewHolder.b.setBackgroundResource(R.drawable.miband_num_bar_bg_sel);
            } else {
                simpleItemViewHolder.f19448a.setTextColor(this.c.getResources().getColor(R.color.sport_bar_bg_normal_text));
                simpleItemViewHolder.b.setBackgroundResource(R.drawable.miband_num_bar_bg_nor);
            }
            simpleItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    int unused = SimpleRecyclerAdapter.this.f = i2;
                    SimpleRecyclerAdapter.this.notifyDataSetChanged();
                }
            });
            simpleItemViewHolder.itemView.getLayoutParams().width = (int) (((float) this.i) * this.b);
            simpleItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (SimpleRecyclerAdapter.this.p != null) {
                        int unused = SimpleRecyclerAdapter.this.f = i2;
                        SimpleRecyclerAdapter.this.p.a(i2, stepDataItem);
                        SimpleRecyclerAdapter.this.notifyDataSetChanged();
                    }
                }
            });
            simpleItemViewHolder.f19448a.setText(DataManager.a(this.c, this.j, stepDataItem.i));
            a(simpleItemViewHolder, i2);
            return;
        }
        boolean z = viewHolder instanceof SimpleHeaderViewHolder;
    }

    public void a(int i2, View view) {
        if (view != null) {
            View findViewById = view.findViewById(R.id.v_bar);
            ((TextView) view.findViewById(R.id.date_tv)).setTextColor(this.c.getResources().getColor(R.color.sport_bar_bg_select_text));
            findViewById.setBackgroundResource(R.drawable.miband_num_bar_bg_sel);
            view.invalidate();
        }
    }

    private float a(float f2) {
        if (this.g == -1.0f || this.h == -1.0f) {
            if (((double) f2) < 0.001d) {
                return 0.0f;
            }
            return this.b * 110.0f;
        } else if (this.g == this.h) {
            return this.b * 110.0f;
        } else {
            if (f2 < 0.005f) {
                return 0.0f;
            }
            if (f2 < 0.01f) {
                f2 = 0.01f;
            }
            float f3 = this.b * 10.0f;
            return (((f2 - this.h) * ((this.b * 220.0f) - f3)) / (this.g - this.h)) + f3;
        }
    }

    private float b(float f2) {
        if (this.g == -1.0f || this.h == -1.0f || this.g == 0.0f) {
            if (((double) f2) < 0.001d) {
                return 0.0f;
            }
            return this.b * 110.0f;
        } else if (this.g == this.h) {
            return this.b * 110.0f;
        } else {
            return f2 * ((this.b * 220.0f) / this.g);
        }
    }

    public int getItemCount() {
        return this.f19444a.size() + 2;
    }

    public int getItemViewType(int i2) {
        if (d(i2)) {
            return 0;
        }
        return e(i2) ? 2 : 1;
    }

    private boolean e(int i2) {
        return i2 == this.f19444a.size() + 1;
    }

    private void a(SimpleItemViewHolder simpleItemViewHolder, int i2) {
        if (this.e.get(i2, -1) == -1) {
            AnimatorSet animatorSet = new AnimatorSet();
            simpleItemViewHolder.b.setPivotY((float) simpleItemViewHolder.b.getLayoutParams().height);
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(simpleItemViewHolder.b, "scaleY", new float[]{0.0f, 1.0f});
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(simpleItemViewHolder.b, "alpha", new float[]{0.0f, 1.0f});
            animatorSet.play(ofFloat).with(ofFloat2).with(ObjectAnimator.ofFloat(simpleItemViewHolder.f19448a, "scaleX", new float[]{0.0f, 1.0f}));
            animatorSet.setDuration(600);
            animatorSet.start();
            this.d = i2;
            this.e.put(i2, i2);
        }
    }

    public final class SimpleItemViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f19448a;
        View b;

        public SimpleItemViewHolder(View view) {
            super(view);
            this.f19448a = (TextView) view.findViewById(R.id.date_tv);
            this.b = view.findViewById(R.id.v_bar);
        }
    }

    public final class SimpleHeaderViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f19447a;
        View b;

        public SimpleHeaderViewHolder(View view) {
            super(view);
            this.f19447a = (TextView) view.findViewById(R.id.date_tv);
            this.b = view.findViewById(R.id.v_bar);
            this.f19447a.setVisibility(4);
            this.b.setVisibility(4);
            if (SimpleRecyclerAdapter.this.f19444a.size() > 0) {
                view.getLayoutParams().width = (int) (((float) (SimpleRecyclerAdapter.this.o.getDisplayMetrics().widthPixels / 2)) - (SimpleRecyclerAdapter.this.o.getDisplayMetrics().density * 25.0f));
                return;
            }
            view.getLayoutParams().width = SimpleRecyclerAdapter.this.o.getDisplayMetrics().widthPixels / 2;
        }
    }
}
