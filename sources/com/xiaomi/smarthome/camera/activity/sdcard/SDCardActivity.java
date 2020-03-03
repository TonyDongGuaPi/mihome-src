package com.xiaomi.smarthome.camera.activity.sdcard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.mijia.app.Event;
import com.mijia.camera.Utils.CameraOperationUtils;
import com.mijia.model.sdcard.SdcardManager;
import com.mijia.model.sdcard.SdcardManagerEx;
import com.mijia.model.sdcard.TimeItem;
import com.mijia.model.sdcard.TimeItemDay;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.BaseSelectActivity;
import com.xiaomi.smarthome.camera.view.HourOfDayView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.util.List;

public class SDCardActivity extends BaseSelectActivity implements HourOfDayView.HourOfDayViewListener {
    private SDCardAdapter mAdapter;
    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (SDCardActivity.this.mCameraDevice == null) {
                return;
            }
            if (SDCardActivity.this.mCameraDevice.n()) {
                if (SDCardActivity.this.mCameraDevice.d().f().equals(intent.getAction()) && SDCardActivity.this.mCameraSdcardFileManagerEx != null) {
                    SDCardActivity.this.setData(SDCardActivity.this.mCameraSdcardFileManagerEx.g());
                }
            } else if (SDCardActivity.this.mCameraDevice.c().f().equals(intent.getAction()) && SDCardActivity.this.mCameraSdcardFileManager != null) {
                SDCardActivity.this.setData(SDCardActivity.this.mCameraSdcardFileManager.g());
            }
        }
    };
    /* access modifiers changed from: private */
    public SdcardManager mCameraSdcardFileManager;
    /* access modifiers changed from: private */
    public SdcardManagerEx mCameraSdcardFileManagerEx;
    private View mEmptyLayout;
    private ListView mListView;
    private LocalBroadcastManager mLocalBroadcastManager;
    XQProgressDialog mProgressDialog;

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.camera_activity_sdcard_file);
        this.mLocalBroadcastManager = LocalBroadcastManager.getInstance(activity());
        if (this.mCameraDevice != null) {
            if (this.mCameraDevice.n()) {
                this.mCameraSdcardFileManagerEx = this.mCameraDevice.d();
                Event.e();
            } else {
                if (this.isV4) {
                    Event.e();
                }
                this.mCameraSdcardFileManager = this.mCameraDevice.c();
            }
        }
        initView();
    }

    public void onResume() {
        super.onResume();
        if (this.mCameraDevice.n()) {
            if (this.mCameraSdcardFileManagerEx != null) {
                this.mCameraSdcardFileManagerEx.j();
                this.mCameraSdcardFileManagerEx.a(40000);
            }
        } else if (this.mCameraSdcardFileManager != null) {
            this.mCameraSdcardFileManager.j();
            this.mCameraSdcardFileManager.a(40000);
        }
        IntentFilter intentFilter = new IntentFilter();
        if (this.mCameraDevice.n()) {
            if (this.mCameraSdcardFileManagerEx != null) {
                intentFilter.addAction(this.mCameraSdcardFileManagerEx.f());
            }
        } else if (this.mCameraSdcardFileManager != null) {
            intentFilter.addAction(this.mCameraSdcardFileManager.f());
        }
        this.mLocalBroadcastManager.registerReceiver(this.mBroadcastReceiver, intentFilter);
        if (this.mCameraDevice == null) {
            return;
        }
        if (this.mCameraDevice.n()) {
            if (this.mCameraSdcardFileManagerEx != null) {
                setData(this.mCameraSdcardFileManagerEx.g());
            }
        } else if (this.mCameraSdcardFileManager != null) {
            setData(this.mCameraSdcardFileManager.g());
        }
    }

    private void initView() {
        initSelectView();
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.record_files_sdcard);
        this.mEmptyLayout = findViewById(R.id.empty_layout);
        this.mEmptyLayout.setVisibility(0);
        this.mListView = (ListView) findViewById(R.id.list_view);
        this.mAdapter = new SDCardAdapter(activity());
        this.mAdapter.setDayViewListener(this);
        this.mListView.setAdapter(this.mAdapter);
        findViewById(R.id.select_save).setOnClickListener(this);
        findViewById(R.id.select_delete).setOnClickListener(this);
    }

    /* access modifiers changed from: private */
    public void setData(List<TimeItemDay> list) {
        if (!this.mIsMultiSelectMode) {
            if (list.isEmpty()) {
                this.mListView.setVisibility(8);
                this.mEditBtn.setVisibility(8);
                this.mEmptyLayout.setVisibility(0);
                return;
            }
            this.mAdapter.setData(list);
            this.mEditBtn.setVisibility(0);
            this.mListView.setVisibility(0);
            this.mEmptyLayout.setVisibility(8);
        }
    }

    /* access modifiers changed from: protected */
    public int getSelectCount() {
        return this.mAdapter.getSelectCount();
    }

    /* access modifiers changed from: protected */
    public int getDataCount() {
        return this.mAdapter.getDataCount();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivSelectAll:
                if (this.mSelectAllShowed) {
                    this.mSelectAllShowed = false;
                    this.mSelectAllBtn.setImageResource(R.drawable.camera_edit_deselect_all_black);
                    this.mAdapter.selectAll();
                } else {
                    this.mSelectAllShowed = true;
                    this.mSelectAllBtn.setImageResource(R.drawable.camera_edit_select_all_black);
                    this.mAdapter.unSelectAll();
                }
                refreshSelectTitle();
                return;
            case R.id.ivSelectAllCancel:
                setMultiSelectMode(false);
                return;
            case R.id.select_delete:
                List<TimeItem> selectItems = this.mAdapter.getSelectItems();
                initProgressDlg();
                CameraOperationUtils.a(activity(), this.mCameraDevice, selectItems, this.mProgressDialog, new Callback<Void>() {
                    public void onSuccess(Void voidR) {
                        if (!SDCardActivity.this.isFinishing()) {
                            SDCardActivity.this.runMainThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(SDCardActivity.this.activity(), R.string.delete_sucess, 0).show();
                                    SDCardActivity.this.disProgressDlg();
                                    SDCardActivity.this.setMultiSelectMode(false);
                                    if (SDCardActivity.this.mCameraDevice == null) {
                                        return;
                                    }
                                    if (SDCardActivity.this.mCameraDevice.n()) {
                                        if (SDCardActivity.this.mCameraSdcardFileManagerEx != null) {
                                            SDCardActivity.this.setData(SDCardActivity.this.mCameraSdcardFileManagerEx.g());
                                        }
                                    } else if (SDCardActivity.this.mCameraSdcardFileManager != null) {
                                        SDCardActivity.this.setData(SDCardActivity.this.mCameraSdcardFileManager.g());
                                    }
                                }
                            });
                        }
                    }

                    public void onFailure(int i, String str) {
                        if (!SDCardActivity.this.isFinishing()) {
                            SDCardActivity.this.runMainThread(new Runnable() {
                                public void run() {
                                    SDCardActivity.this.disProgressDlg();
                                    Toast.makeText(SDCardActivity.this.activity(), R.string.delete_failed, 0).show();
                                }
                            });
                        }
                    }
                });
                return;
            case R.id.select_save:
                List<TimeItem> selectItems2 = this.mAdapter.getSelectItems();
                initProgressDlg();
                CameraOperationUtils.b(activity(), this.mCameraDevice, selectItems2, this.mProgressDialog, new Callback<Void>() {
                    public void onSuccess(Void voidR) {
                        if (!SDCardActivity.this.isFinishing()) {
                            SDCardActivity.this.runMainThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(SDCardActivity.this.activity(), R.string.save_sucess, 0).show();
                                    SDCardActivity.this.disProgressDlg();
                                    SDCardActivity.this.setMultiSelectMode(false);
                                    if (SDCardActivity.this.mCameraDevice == null) {
                                        return;
                                    }
                                    if (SDCardActivity.this.mCameraDevice.n()) {
                                        if (SDCardActivity.this.mCameraSdcardFileManagerEx != null) {
                                            SDCardActivity.this.setData(SDCardActivity.this.mCameraSdcardFileManagerEx.g());
                                        }
                                    } else if (SDCardActivity.this.mCameraSdcardFileManager != null) {
                                        SDCardActivity.this.setData(SDCardActivity.this.mCameraSdcardFileManager.g());
                                    }
                                }
                            });
                        }
                    }

                    public void onFailure(int i, String str) {
                        if (!SDCardActivity.this.isFinishing()) {
                            SDCardActivity.this.runMainThread(new Runnable() {
                                public void run() {
                                    SDCardActivity.this.disProgressDlg();
                                    Toast.makeText(SDCardActivity.this.activity(), R.string.save_failed, 0).show();
                                }
                            });
                        }
                    }
                });
                return;
            case R.id.title_bar_more:
                if (this.mCameraDevice.isReadOnlyShared()) {
                    ToastUtil.a((Context) activity(), (int) R.string.auth_fail);
                    return;
                } else {
                    setMultiSelectMode(true);
                    return;
                }
            case R.id.title_bar_return:
                onBackPressed();
                return;
            default:
                return;
        }
    }

    public void onClick(int i, int i2) {
        Intent intent = new Intent(this, SDCardHourActivity.class);
        intent.putExtra("TimeItemsDays", i);
        intent.putExtra("TimeItemsHour", i2);
        startActivity(intent);
    }

    public void onLongClick() {
        if (this.mCameraDevice.isReadOnlyShared()) {
            ToastUtil.a((Context) activity(), (int) R.string.auth_fail);
        } else if (!this.mIsMultiSelectMode) {
            this.mAdapter.setMultiSelectMode(true);
            setMultiSelectMode(true, true);
        }
    }

    public void onSelectChanged() {
        refreshSelectTitle();
    }

    public void initProgressDlg() {
        if (this.mProgressDialog == null) {
            this.mProgressDialog = new XQProgressDialog(activity());
        }
    }

    public void disProgressDlg() {
        if (this.mProgressDialog != null && this.mProgressDialog.isShowing()) {
            this.mProgressDialog.dismiss();
        }
    }

    public void setMultiSelectMode(boolean z) {
        super.setMultiSelectMode(z);
        this.mAdapter.setMultiSelectMode(z);
    }

    public void onPause() {
        if (this.mCameraDevice != null) {
            if (this.mCameraDevice.n()) {
                if (this.mCameraSdcardFileManagerEx != null) {
                    this.mCameraSdcardFileManagerEx.b();
                }
            } else if (this.mCameraSdcardFileManager != null) {
                this.mCameraSdcardFileManager.b();
            }
        }
        this.mLocalBroadcastManager.unregisterReceiver(this.mBroadcastReceiver);
        super.onPause();
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mCameraSdcardFileManagerEx != null || this.isV4) {
            Event.f();
        }
    }

    public void onBackPressed() {
        if (this.mIsMultiSelectMode) {
            this.mAdapter.setMultiSelectMode(false);
            setMultiSelectMode(false);
            return;
        }
        super.onBackPressed();
    }
}
