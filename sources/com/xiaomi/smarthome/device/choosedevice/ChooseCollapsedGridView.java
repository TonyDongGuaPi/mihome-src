package com.xiaomi.smarthome.device.choosedevice;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.ChooseDeviceManually;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.smartconfig.HideDeviceEntranceAct;
import com.xiaomi.smarthome.smartconfig.SmartConfigMainActivity;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.List;

public class ChooseCollapsedGridView extends ConstraintLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final int f15303a = 12;
    private final TextView b;
    private final RecyclerView c;
    private final View d;
    private CollapsedAdapter e;
    /* access modifiers changed from: private */
    public ChooseDeviceManually.ChooseManuallyListener f;
    private String g;
    /* access modifiers changed from: private */
    public OnManualCategoryDeviceClicked h;

    public interface OnManualCategoryDeviceClicked {
        void a(PluginRecord pluginRecord, int i);
    }

    public ChooseCollapsedGridView(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    public ChooseCollapsedGridView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ChooseCollapsedGridView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        inflate(context, R.layout.choose_device_grid_layout, this);
        this.b = (TextView) findViewById(R.id.category_name);
        this.c = (RecyclerView) findViewById(R.id.recycler);
        this.d = findViewById(R.id.show_more);
        this.c.setLayoutManager(new GridLayoutManager(context, 3));
        this.e = new CollapsedAdapter();
        this.c.setAdapter(this.e);
        this.c.addItemDecoration(new SimpleItemDecoration());
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setSupportsChangeAnimations(false);
        this.c.setItemAnimator(defaultItemAnimator);
        this.d.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ChooseCollapsedGridView.this.a(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        view.post(new Runnable(view) {
            private final /* synthetic */ View f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                ChooseCollapsedGridView.this.b(this.f$1);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        view.setVisibility(8);
        this.e.a();
        CollapseStateRecord.a().a(this.g);
    }

    public void setChooseDeviceListener(ChooseDeviceManually.ChooseManuallyListener chooseManuallyListener) {
        this.f = chooseManuallyListener;
    }

    public void setSubCategoryData(String str, String str2, List<PluginRecord> list) {
        this.g = str2;
        this.b.setText(str2);
        this.e.a(str, str2);
        this.e.a(list, true);
        this.d.setVisibility(a(list.size()) ? 0 : 8);
    }

    /* access modifiers changed from: private */
    public boolean a(int i) {
        return i > 12 && !CollapseStateRecord.a().b(this.g);
    }

    public void collapsedIfNeeded() {
        this.e.b();
    }

    private class CollapsedAdapter extends RecyclerView.Adapter<VH> {
        private final List<PluginRecord> b = new ArrayList();
        private final List<PluginRecord> c = new ArrayList();
        /* access modifiers changed from: private */
        public String d;
        private String e;

        /* access modifiers changed from: private */
        public boolean a(char c2) {
            return (c2 >= 'a' && c2 <= 'z') || (c2 >= 'A' && c2 <= 'Z') || (c2 >= '0' && c2 <= '9');
        }

        CollapsedAdapter() {
        }

        /* access modifiers changed from: package-private */
        public void a(String str, String str2) {
            this.d = str;
            this.e = str2;
        }

        /* access modifiers changed from: package-private */
        public void a(List<PluginRecord> list, boolean z) {
            this.c.clear();
            this.b.clear();
            this.b.addAll(list);
            if (!z || !ChooseCollapsedGridView.this.a(list.size())) {
                this.c.addAll(list);
            } else {
                this.c.addAll(list.subList(0, 12));
            }
            notifyDataSetChanged();
        }

        /* access modifiers changed from: package-private */
        public void a() {
            if (this.c.size() != this.b.size()) {
                a((List<PluginRecord>) new ArrayList(this.b), false);
                STAT.d.g(this.e, this.b.size());
            }
        }

        /* access modifiers changed from: package-private */
        public void b() {
            if (ChooseCollapsedGridView.this.a(this.c.size())) {
                a((List<PluginRecord>) new ArrayList(this.b), true);
            }
        }

        /* renamed from: a */
        public VH onCreateViewHolder(ViewGroup viewGroup, int i) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.choose_device_list_item, viewGroup, false);
            if (Build.VERSION.SDK_INT > 21) {
                inflate.setBackgroundResource(R.drawable.navi_ripple);
            }
            return new VH(inflate);
        }

        @SuppressLint({"SetTextI18n"})
        /* renamed from: a */
        public void onBindViewHolder(VH vh, int i) {
            vh.a(this.c.get(i), i);
        }

        class VH extends RecyclerView.ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            TextView f15305a;
            SimpleDraweeView b;

            VH(View view) {
                super(view);
                this.f15305a = (TextView) view.findViewById(R.id.name);
                this.b = (SimpleDraweeView) view.findViewById(R.id.image);
            }

            public void a(final PluginRecord pluginRecord, final int i) {
                Context context = this.itemView.getContext();
                String p = pluginRecord.p();
                if (!ServerCompact.c(context).getLanguage().equalsIgnoreCase("zh") || p.length() <= 6 || context.getResources().getConfiguration().fontScale > 1.0f) {
                    this.f15305a.setText(p);
                } else {
                    StringBuilder sb = new StringBuilder(p.substring(0, 6));
                    StringBuilder sb2 = new StringBuilder(p.substring(6));
                    if (CollapsedAdapter.this.a(sb.charAt(sb.length() - 1)) && CollapsedAdapter.this.a(sb2.charAt(0))) {
                        while (sb2.length() > 0 && CollapsedAdapter.this.a(sb2.charAt(0))) {
                            sb.append(sb2.charAt(0));
                            sb2.deleteCharAt(0);
                        }
                    }
                    TextView textView = this.f15305a;
                    textView.setText(sb.toString().trim() + "\n" + sb2.toString().trim());
                }
                DeviceFactory.b(pluginRecord.o(), this.b);
                this.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        SmartConfigMainActivity.DEVICE_FROM_APP_PLUS_TYPE = 7;
                        STAT.d.h(CollapsedAdapter.this.d, pluginRecord.o());
                        if (ChooseCollapsedGridView.this.f != null) {
                            ChooseCollapsedGridView.this.f.chooseConnectDevice(pluginRecord);
                        }
                        if (ChooseCollapsedGridView.this.h != null) {
                            ChooseCollapsedGridView.this.h.a(pluginRecord, i);
                        }
                    }
                });
            }

            private static /* synthetic */ boolean a(Context context, PluginRecord pluginRecord, View view) {
                Intent intent = new Intent(context, HideDeviceEntranceAct.class);
                intent.putExtra("model", pluginRecord.o());
                context.startActivity(intent);
                return true;
            }
        }

        public int getItemCount() {
            return this.c.size();
        }
    }

    private class SimpleItemDecoration extends RecyclerView.ItemDecoration {
        private SimpleItemDecoration() {
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            int b = ((DisplayUtils.b(ChooseCollapsedGridView.this.getContext()) - DisplayUtils.d(ChooseCollapsedGridView.this.getContext(), 87.0f)) - DisplayUtils.d(ChooseCollapsedGridView.this.getContext(), 279.0f)) / 6;
            rect.right = b;
            rect.left = b;
            int d = DisplayUtils.d(ChooseCollapsedGridView.this.getContext(), 10.0f);
            rect.bottom = d;
            rect.top = d;
        }
    }

    public void setOnManualCategoryDeviceClicked(OnManualCategoryDeviceClicked onManualCategoryDeviceClicked) {
        this.h = onManualCategoryDeviceClicked;
    }
}
