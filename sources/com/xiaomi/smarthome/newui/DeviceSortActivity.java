package com.xiaomi.smarthome.newui;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.TextView;
import com.h6ah4i.android.widget.advrecyclerview.animator.SwipeDismissItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.touchguard.RecyclerViewTouchActionGuardManager;
import com.miui.tsmclient.entity.CardInfo;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.page.SmartGroupItemDecoration;
import com.xiaomi.smarthome.newui.adapter.DeviceSortAdapter;

public class DeviceSortActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20250a = "DeviceSortActivity";
    private Button b;
    private Button c;
    private RecyclerView d;
    private DeviceSortAdapter e;
    private RecyclerView.Adapter f;
    private RecyclerViewExpandableItemManager g;
    private RecyclerViewDragDropManager h;
    private RecyclerViewTouchActionGuardManager i;
    private LinearLayoutManager j;
    private int k;
    private String l;
    private String m;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_sort);
        Intent intent = getIntent();
        this.k = intent.getIntExtra("type", -1);
        this.l = intent.getStringExtra(CardInfo.KEY_CARD_GROUP_NAME);
        if (this.k == 4) {
            this.m = intent.getStringExtra("roomID");
        }
        a();
    }

    private void a() {
        TitleBarUtil.a(TitleBarUtil.a(), findViewById(R.id.title_bar_choose_device));
        this.b = (Button) findViewById(R.id.module_a_4_return_more_btn);
        this.b.setText(R.string.cancel);
        this.b.setOnClickListener(this);
        this.c = (Button) findViewById(R.id.module_a_4_return_finish_btn);
        this.c.setText(R.string.complete);
        this.c.setEnabled(false);
        this.c.setOnClickListener(this);
        ((TextView) findViewById(R.id.module_a_4_return_more_title)).setText(this.l);
        this.d = (RecyclerView) findViewById(R.id.device_grid_view);
        this.d.setOverScrollMode(2);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.f == null) {
            resetAdapter();
        }
    }

    public int getType() {
        return this.k;
    }

    public String getGroupName() {
        return this.l;
    }

    public String getRoomID() {
        return this.m;
    }

    public void resetAdapter() {
        this.j = new LinearLayoutManager(getContext());
        this.g = new RecyclerViewExpandableItemManager((Parcelable) null);
        this.i = new RecyclerViewTouchActionGuardManager();
        this.i.b(true);
        this.i.a(true);
        this.h = new RecyclerViewDragDropManager();
        this.h.a((NinePatchDrawable) getContext().getResources().getDrawable(R.drawable.std_list_item_drag_shadow));
        DeviceSortAdapter deviceSortAdapter = new DeviceSortAdapter(this, this.g);
        this.e = deviceSortAdapter;
        this.f = this.g.a((RecyclerView.Adapter) deviceSortAdapter);
        this.f = this.h.a(this.f);
        SwipeDismissItemAnimator swipeDismissItemAnimator = new SwipeDismissItemAnimator();
        swipeDismissItemAnimator.setSupportsChangeAnimations(false);
        this.d.setLayoutManager(this.j);
        this.d.setAdapter(this.f);
        this.d.setItemAnimator(swipeDismissItemAnimator);
        this.d.setHasFixedSize(false);
        this.d.addItemDecoration(new SmartGroupItemDecoration(getResources().getDrawable(R.drawable.std_list_item_divider), true));
        this.i.a(this.d);
        this.h.a(this.d);
        this.g.a(this.d);
        if (SmartHomeDeviceHelper.a().d()) {
            this.h.a(false);
        } else {
            this.h.a(true);
        }
        this.h.b(true);
        this.h.a((int) (((float) ViewConfiguration.getLongPressTimeout()) * 1.5f));
        this.h.a((RecyclerViewDragDropManager.OnItemDragEventListener) new RecyclerViewDragDropManager.OnItemDragEventListener() {
            public void a(int i) {
            }

            public void a(int i, int i2) {
            }

            public void a(int i, int i2, boolean z) {
            }

            public void b(int i, int i2) {
            }
        });
        updateGroupExpendState();
    }

    /* access modifiers changed from: package-private */
    public void updateGroupExpendState() {
        for (int i2 = 0; i2 < this.e.a(); i2++) {
            this.g.a(i2);
        }
    }

    public void orderChanged() {
        this.c.setEnabled(true);
    }

    public void onClick(View view) {
        if (view == this.b) {
            finish();
        } else if (view == this.c) {
            this.e.b();
            ToastUtil.a(getText(R.string.toast_sort_succeed));
            finish();
        }
    }

    public void onBackPressed() {
        if (!this.c.isEnabled()) {
            super.onBackPressed();
            return;
        }
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        builder.a((int) R.string.dialog_title_save_sort);
        builder.a((int) R.string.quit, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                DeviceSortActivity.this.finish();
            }
        });
        builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
        builder.b().show();
    }
}
