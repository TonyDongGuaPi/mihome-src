package com.xiaomi.smarthome.miui;

import android.app.WallpaperManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.miot.BinderParcel;
import com.xiaomi.miot.service.ICallback;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.page.BaseWhiteActivity;
import com.xiaomi.smarthome.framework.permission.PermissionHelper;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.miui.CommonDeviceEditAdapter;
import com.xiaomi.smarthome.newui.dragsort.OnStartDragListener;
import com.xiaomi.smarthome.service.tasks.GetDeviceTask;
import com.yanzhenjie.permission.Action;
import java.util.List;

public class CommonDeviceEditActivity extends BaseWhiteActivity {
    public static final String SHOW_CAMERA_CARD = "show_camera_card";
    public static final String SHOW_CARD_COUNT = "show_card_count";

    /* renamed from: a  reason: collision with root package name */
    private ItemTouchHelper f20084a;
    private GridLayoutManager b;
    private RecyclerView c;
    /* access modifiers changed from: private */
    public AutoMaskLinearLayout d;
    private TextView e;
    /* access modifiers changed from: private */
    public CommonDeviceEditAdapter f;
    private ICallback g;
    private ItemMoveAnimation h = new ItemMoveAnimation();
    private Rect i = new Rect();
    private boolean j = false;
    private int k = 8;
    private BroadcastReceiver l = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if ("action_on_login_success".equals(intent.getAction())) {
                HomeManager.a().b(false);
            }
        }
    };
    private boolean m;

    class ItemMoveAnimation extends DefaultItemAnimator {
        ItemMoveAnimation() {
        }

        public boolean animateMove(RecyclerView.ViewHolder viewHolder, int i, int i2, int i3, int i4) {
            if (!(viewHolder instanceof CommonDeviceEditAdapter.CameraViewHolder) || !CommonDeviceEditActivity.this.f.d()) {
                return super.animateMove(viewHolder, i, i2, i3, i4);
            }
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().setFlags(134217728, 134217728);
        }
        this.m = DarkModeCompat.a((Context) this);
        setContentView(R.layout.activity_common_device_edit);
        Parcelable parcelableExtra = getIntent().getParcelableExtra("extra");
        if (parcelableExtra != null) {
            this.g = ICallback.Stub.asInterface(((BinderParcel) parcelableExtra).a());
        }
        this.d = (AutoMaskLinearLayout) findViewById(R.id.al_root);
        boolean g2 = PermissionHelper.g(this, false, new Action() {
            public void onAction(List<String> list) {
            }
        });
        if (this.m || !g2) {
            this.d.setBackgroundColor(-1);
        } else {
            Drawable drawable = WallpaperManager.getInstance(this).getDrawable();
            if (drawable != null) {
                this.d.setBackgroundDrawable(drawable);
            }
        }
        Intent intent = getIntent();
        if (intent != null) {
            this.j = intent.getBooleanExtra(SHOW_CAMERA_CARD, false);
            this.k = intent.getIntExtra(SHOW_CARD_COUNT, this.k);
        }
        this.e = (TextView) findViewById(R.id.back);
        this.e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CommonDeviceEditActivity.this.finish();
            }
        });
        this.c = (RecyclerView) findViewById(R.id.recyclerview);
        this.f = new CommonDeviceEditAdapter(getContext(), this.d, this.j, this.k);
        this.f20084a = new ItemTouchHelper(new ItemTouchHelperCallback());
        this.f20084a.attachToRecyclerView(this.c);
        this.b = new GridLayoutManager(getContext(), 2);
        this.f.a((OnStartDragListener) new OnStartDragListener() {
            public final void onStartDrag(RecyclerView.ViewHolder viewHolder) {
                CommonDeviceEditActivity.this.a(viewHolder);
            }
        });
        this.c.setLayoutManager(this.b);
        this.c.setItemAnimator(this.h);
        this.f.c();
        this.c.setAdapter(this.f);
        this.c.addOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
            }

            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                CommonDeviceEditActivity.this.d.setScrolledY(-i2);
                CommonDeviceEditActivity.this.d.invalidate();
            }
        });
        this.c.addItemDecoration(new GridSpacingItemDecoration(2, DisplayUtils.a(6.0f), true));
        if (SHApplication.getStateNotifier().a() != 4) {
            LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.l, new IntentFilter("action_on_login_success"));
            return;
        }
        HomeManager.a().b(false);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(RecyclerView.ViewHolder viewHolder) {
        Home m2 = HomeManager.a().m();
        if (m2 == null || m2.p()) {
            this.f20084a.startDrag(viewHolder);
            viewHolder.itemView.getDrawingRect(this.i);
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        try {
            LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.l);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        this.f.e();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        if (this.f.f()) {
            a();
        }
    }

    class ItemTouchHelperCallback extends ItemTouchHelper.Callback {
        public boolean isLongPressDragEnabled() {
            return false;
        }

        public void onSwiped(RecyclerView.ViewHolder viewHolder, int i) {
        }

        ItemTouchHelperCallback() {
        }

        public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return makeMovementFlags(15, 0);
        }

        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder2) {
            return CommonDeviceEditActivity.this.f.a(viewHolder.getAdapterPosition(), viewHolder2.getAdapterPosition());
        }

        public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            super.clearView(recyclerView, viewHolder);
        }

        public void onMoved(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, int i, RecyclerView.ViewHolder viewHolder2, int i2, int i3, int i4) {
            super.onMoved(recyclerView, viewHolder, i, viewHolder2, i2, i3, i4);
        }

        public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int i) {
            super.onSelectedChanged(viewHolder, i);
            if (i == 0) {
                CommonDeviceEditActivity.this.d.stopDrawDash();
                CommonDeviceEditActivity.this.f.a();
            }
        }
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int b;
        private int c;
        private boolean d;

        public GridSpacingItemDecoration(int i, int i2, boolean z) {
            this.b = i;
            this.c = i2;
            this.d = z;
        }

        public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
            super.onDraw(canvas, recyclerView, state);
        }

        public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
            super.onDrawOver(canvas, recyclerView, state);
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
            int i = childAdapterPosition % this.b;
            if (this.d) {
                int b2 = CommonDeviceEditActivity.this.f.b(childAdapterPosition);
                if (b2 == 0) {
                    rect.left = this.c;
                    rect.right = this.c;
                } else if (b2 == 1) {
                    rect.left = this.c;
                    rect.right = this.c / 2;
                } else {
                    rect.left = this.c / 2;
                    rect.right = this.c;
                }
                rect.bottom = this.c;
                return;
            }
            rect.left = (this.c * i) / this.b;
            rect.right = this.c - (((i + 1) * this.c) / this.b);
            if (childAdapterPosition >= this.b) {
                rect.top = this.c;
            }
        }
    }

    private void a() {
        if (this.g != null) {
            SHApplication.getGlobalWorkerHandler().post(new GetDeviceTask(this.g, false));
        }
    }
}
