package com.xiaomi.smarthome.miio.page;

import android.content.DialogInterface;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.h6ah4i.android.widget.advrecyclerview.animator.SwipeDismissItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.touchguard.RecyclerViewTouchActionGuardManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrapperAdapterUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.DeviceSortUtil;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;

public class DeviceSortActivity extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public DeviceSortAdapter f19533a;
    private RecyclerView.Adapter b;
    private RecyclerViewExpandableItemManager c;
    private RecyclerViewDragDropManager d;
    private RecyclerViewTouchActionGuardManager e;
    private LinearLayoutManager f;
    Button mCancelBtn;
    Button mConfirmBtn;
    RecyclerView mDeviceGridView;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.client_all_device_sort);
        ((TextView) findViewById(R.id.module_a_4_return_more_title)).setText(R.string.menu_edit_sort);
        this.mCancelBtn = (Button) findViewById(R.id.module_a_4_return_more_btn);
        this.mCancelBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeviceSortActivity.this.finish();
                DeviceSortActivity.this.overridePendingTransition(0, 0);
            }
        });
        this.mConfirmBtn = (Button) findViewById(R.id.module_a_4_return_finish_btn);
        this.mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeviceSortUtil.a(DeviceSortActivity.this.f19533a.d, (AsyncCallback<Void, Error>) null);
                SmartHomeDeviceManager.a().q();
                Toast.makeText(SHApplication.getAppContext(), R.string.toast_sort_succeed, 1).show();
                DeviceSortActivity.this.logDeviceEditEvent("DevicelistDeviceDragged");
                DeviceSortActivity.this.finish();
            }
        });
        this.mDeviceGridView = (RecyclerView) findViewById(R.id.device_grid_view);
        this.mDeviceGridView.setOverScrollMode(2);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        if (this.b == null) {
            resetAdapter();
        }
    }

    public void onPause() {
        super.onPause();
    }

    public void logDeviceEditEvent(String str) {
        CoreApi.a().a(StatType.DEVICE_EDIT, str, (String) null, (String) null, false);
    }

    public void onBackPressed() {
        if (!this.mConfirmBtn.isEnabled()) {
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

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        clearAdapter();
    }

    public void clearAdapter() {
        if (this.d != null) {
            this.d.b();
            this.d = null;
        }
        if (this.c != null) {
            this.c.b();
            this.c = null;
        }
        if (this.e != null) {
            this.e.b();
            this.e = null;
        }
        if (this.mDeviceGridView != null) {
            this.mDeviceGridView.setItemAnimator((RecyclerView.ItemAnimator) null);
            this.mDeviceGridView.setAdapter((RecyclerView.Adapter) null);
        }
        if (this.b != null) {
            WrapperAdapterUtils.a(this.b);
            this.b = null;
        }
        if (this.f19533a != null) {
            WrapperAdapterUtils.a(this.f19533a);
            this.f19533a = null;
        }
    }

    public void resetAdapter() {
        this.f = new LinearLayoutManager(getContext());
        this.c = new RecyclerViewExpandableItemManager((Parcelable) null);
        this.e = new RecyclerViewTouchActionGuardManager();
        this.e.b(true);
        this.e.a(true);
        this.d = new RecyclerViewDragDropManager();
        this.d.a((NinePatchDrawable) getContext().getResources().getDrawable(R.drawable.std_list_item_drag_shadow));
        DeviceSortAdapter deviceSortAdapter = new DeviceSortAdapter(this, this.c);
        this.f19533a = deviceSortAdapter;
        this.b = this.c.a((RecyclerView.Adapter) deviceSortAdapter);
        this.b = this.d.a(this.b);
        SwipeDismissItemAnimator swipeDismissItemAnimator = new SwipeDismissItemAnimator();
        swipeDismissItemAnimator.setSupportsChangeAnimations(false);
        this.mDeviceGridView.setLayoutManager(this.f);
        this.mDeviceGridView.setAdapter(this.b);
        this.mDeviceGridView.setItemAnimator(swipeDismissItemAnimator);
        this.mDeviceGridView.setHasFixedSize(false);
        this.mDeviceGridView.addItemDecoration(new SmartGroupItemDecoration(getResources().getDrawable(R.drawable.std_list_item_divider), true));
        this.e.a(this.mDeviceGridView);
        this.d.a(this.mDeviceGridView);
        this.c.a(this.mDeviceGridView);
        if (SmartHomeDeviceHelper.a().d()) {
            this.d.a(false);
        } else {
            this.d.a(true);
        }
        this.d.b(true);
        this.d.a((int) (((float) ViewConfiguration.getLongPressTimeout()) * 1.5f));
        this.d.a((RecyclerViewDragDropManager.OnItemDragEventListener) new RecyclerViewDragDropManager.OnItemDragEventListener() {
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
        for (int i = 0; i < this.f19533a.a(); i++) {
            this.c.a(i);
        }
    }

    public void orderChanged() {
        this.mConfirmBtn.setEnabled(true);
    }
}
