package com.xiaomi.smarthome.listcamera;

import android.content.DialogInterface;
import android.graphics.drawable.NinePatchDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.h6ah4i.android.widget.advrecyclerview.animator.SwipeDismissItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.draggable.RecyclerViewDragDropManager;
import com.h6ah4i.android.widget.advrecyclerview.touchguard.RecyclerViewTouchActionGuardManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrapperAdapterUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.listcamera.adapter.CameraSortAdapter;
import com.xiaomi.smarthome.miio.page.SmartGroupItemDecoration;

public class CameraSortActivity extends BaseActivity {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public CameraSortAdapter f19264a;
    private RecyclerView.Adapter b;
    private RecyclerViewDragDropManager c;
    private RecyclerViewTouchActionGuardManager d;
    private LinearLayoutManager e;
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
                CameraSortActivity.this.finish();
                CameraSortActivity.this.overridePendingTransition(0, 0);
            }
        });
        this.mConfirmBtn = (Button) findViewById(R.id.module_a_4_return_finish_btn);
        this.mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                CameraGroupManager.a().b(CameraSortActivity.this.f19264a.c);
                CameraGroupManager.a().a((AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    public void onFailure(Error error) {
                    }

                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                        Toast.makeText(SHApplication.getAppContext(), R.string.toast_sort_succeed, 1).show();
                        CameraSortActivity.this.finish();
                    }
                });
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

    public void onBackPressed() {
        if (!this.mConfirmBtn.isEnabled()) {
            super.onBackPressed();
            return;
        }
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        builder.a((int) R.string.dialog_title_save_sort);
        builder.a((int) R.string.quit, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                CameraSortActivity.this.finish();
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
        if (this.c != null) {
            this.c.b();
            this.c = null;
        }
        if (this.d != null) {
            this.d.b();
            this.d = null;
        }
        if (this.mDeviceGridView != null) {
            this.mDeviceGridView.setItemAnimator((RecyclerView.ItemAnimator) null);
            this.mDeviceGridView.setAdapter((RecyclerView.Adapter) null);
        }
        if (this.b != null) {
            WrapperAdapterUtils.a(this.b);
            this.b = null;
        }
        if (this.f19264a != null) {
            WrapperAdapterUtils.a(this.f19264a);
            this.f19264a = null;
        }
    }

    public void resetAdapter() {
        this.e = new LinearLayoutManager(getContext());
        this.d = new RecyclerViewTouchActionGuardManager();
        this.d.b(true);
        this.d.a(true);
        this.c = new RecyclerViewDragDropManager();
        this.c.a((NinePatchDrawable) getContext().getResources().getDrawable(R.drawable.std_list_item_drag_shadow));
        this.f19264a = new CameraSortAdapter(this);
        this.b = this.c.a((RecyclerView.Adapter) this.f19264a);
        SwipeDismissItemAnimator swipeDismissItemAnimator = new SwipeDismissItemAnimator();
        swipeDismissItemAnimator.setSupportsChangeAnimations(false);
        this.mDeviceGridView.setLayoutManager(this.e);
        this.mDeviceGridView.setAdapter(this.b);
        this.mDeviceGridView.setItemAnimator(swipeDismissItemAnimator);
        this.mDeviceGridView.setHasFixedSize(false);
        this.mDeviceGridView.addItemDecoration(new SmartGroupItemDecoration(getResources().getDrawable(R.drawable.std_list_item_divider), true));
        this.d.a(this.mDeviceGridView);
        this.c.a(this.mDeviceGridView);
        this.c.a(false);
        this.c.b(true);
        this.c.a((int) (((float) ViewConfiguration.getLongPressTimeout()) * 1.5f));
        this.c.a((RecyclerViewDragDropManager.OnItemDragEventListener) new RecyclerViewDragDropManager.OnItemDragEventListener() {
            public void a(int i) {
            }

            public void a(int i, int i2) {
            }

            public void a(int i, int i2, boolean z) {
            }

            public void b(int i, int i2) {
            }
        });
    }

    public void orderChanged() {
        this.mConfirmBtn.setEnabled(true);
    }
}
