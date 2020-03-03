package com.xiaomi.smarthome.newui;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.multi_item.IAdapter;
import com.xiaomi.smarthome.newui.MainOperationAdapter;
import com.xiaomi.smarthome.operation.Operation;
import com.xiaomi.smarthome.operation.OperationCollector;
import com.xiaomi.smarthome.operation.OperationRoute;
import com.xiaomi.smarthome.operation.mainpage.MainPageBannerHelper;
import com.xiaomi.smarthome.operation.mainpage.MainPagePopHelper;
import com.xiaomi.smarthome.stat.STAT;

public class MainOperationAdapter extends IAdapter {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public boolean f20317a = false;
    private boolean b = false;
    /* access modifiers changed from: private */
    public MainPageBannerHelper c;
    /* access modifiers changed from: private */
    public Operation d;

    /* access modifiers changed from: protected */
    public int a() {
        return 1;
    }

    MainOperationAdapter() {
    }

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new SimpleViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_operation_banner, viewGroup, false));
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        ((SimpleViewHolder) viewHolder).a();
    }

    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        Context context = recyclerView.getContext();
        if (context instanceof FragmentActivity) {
            this.c = OperationCollector.a((FragmentActivity) context);
            this.c.p();
            this.c.a((MainPageBannerHelper.OnOperationReadyListener) new MainPageBannerHelper.OnOperationReadyListener() {
                public final void onOperationReady(Operation operation) {
                    MainOperationAdapter.this.a(operation);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void a(Operation operation) {
        this.d = operation;
        if (g()) {
            notifyItemInserted(0);
        }
    }

    private boolean g() {
        return this.b && this.d != null;
    }

    public void a(boolean z) {
        if (z != this.b) {
            this.b = z;
        }
    }

    public void b() {
        if (this.c != null) {
            this.c.p();
        }
    }

    public void c() {
        this.f20317a = true;
    }

    public void f() {
        this.f20317a = false;
    }

    public int getItemCount() {
        return g() ? 1 : 0;
    }

    private class SimpleViewHolder extends RecyclerView.ViewHolder {
        SimpleViewHolder(View view) {
            super(view);
        }

        public void a() {
            if (this.itemView instanceof MainPagePopHelper) {
                ((MainPagePopHelper) this.itemView).attachAdUrl(MainOperationAdapter.this.d.d);
            }
            ImageView imageView = (ImageView) this.itemView.findViewById(R.id.banner_img);
            imageView.setImageBitmap(MainOperationAdapter.this.d.i);
            this.itemView.findViewById(R.id.close).setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    MainOperationAdapter.SimpleViewHolder.this.b(view);
                }
            });
            this.itemView.setOnClickListener(new View.OnClickListener() {
                public final void onClick(View view) {
                    MainOperationAdapter.SimpleViewHolder.this.a(view);
                }
            });
            if (MainOperationAdapter.this.f20317a) {
                imageView.setAlpha(0.5f);
            } else {
                imageView.setAlpha(1.0f);
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void b(View view) {
            if (!MainOperationAdapter.this.f20317a && MainOperationAdapter.this.c != null) {
                MainOperationAdapter.this.c.o();
                Operation unused = MainOperationAdapter.this.d = null;
                MainOperationAdapter.this.notifyItemRemoved(0);
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(View view) {
            if (!MainOperationAdapter.this.f20317a && MainOperationAdapter.this.d != null) {
                OperationRoute.a(MainOperationAdapter.this.d);
                STAT.d.ak(MainOperationAdapter.this.d.d);
            }
        }
    }

    public void a(Rect rect, View view, int i, RecyclerView recyclerView, RecyclerView.State state) {
        int a2 = DisplayUtils.a(10.0f);
        rect.bottom = a2;
        rect.right = a2;
        rect.left = a2;
    }
}
