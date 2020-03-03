package com.xiaomi.smarthome.camera.activity.sdcard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.Utils.TimeUtils;
import com.mijia.app.Event;
import com.mijia.camera.Utils.CameraOperationUtils;
import com.mijia.model.sdcard.DownloadSdCardManager;
import com.mijia.model.sdcard.TimeItem;
import com.mijia.model.sdcard.TimeItemDay;
import com.mijia.model.sdcard.TimeItemHour;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.BaseSelectActivity;
import com.xiaomi.smarthome.camera.view.recycle.RecyclerClickListener;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class SDCardHourActivity extends BaseSelectActivity implements DownloadSdCardManager.OnDownloadListener, RecyclerClickListener {
    /* access modifiers changed from: private */
    public SDCardHourAdapter mAdapter;
    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (SDCardHourActivity.this.mCameraDevice == null) {
                return;
            }
            if (SDCardHourActivity.this.mCameraDevice.n()) {
                if (SDCardHourActivity.this.mCameraDevice.d().f().equals(intent.getAction())) {
                    SDCardHourActivity.this.refreshData();
                }
            } else if (SDCardHourActivity.this.mCameraDevice.c().f().equals(intent.getAction())) {
                SDCardHourActivity.this.refreshData();
            }
        }
    };
    private int mDay;
    DownloadSdCardManager mDownloadSdCardFileManager;
    private LinearLayout mEmptyLayout;
    private int mHour;
    private RecyclerView mListView;
    LocalBroadcastManager mLocalBroadcastManager;
    HashSet<Long> mNeedCheckList = new HashSet<>();
    private XQProgressDialog mProgressDialog;
    SimpleDateFormat mSimpleDateFormat;
    SimpleDateFormat mSimpleDateFormat1;
    List<TimeItemData> mTimeItemDataList = new ArrayList();
    List<TimeItem> mTimeItemList = new ArrayList();

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.camera_activity_sdcard_hour);
        this.mDay = getIntent().getIntExtra("TimeItemsDays", 0);
        this.mHour = getIntent().getIntExtra("TimeItemsHour", 0);
        this.mSimpleDateFormat = new SimpleDateFormat(getString(R.string.simple_date_format_mm_dd));
        this.mSimpleDateFormat1 = new SimpleDateFormat(getString(R.string.simple_date_format_hh_mm));
        this.mSimpleDateFormat.setTimeZone(TimeUtils.a());
        this.mSimpleDateFormat1.setTimeZone(TimeUtils.a());
        this.mLocalBroadcastManager = LocalBroadcastManager.getInstance(activity());
        this.mDownloadSdCardFileManager = this.mCameraDevice.h();
        this.mCameraDevice.h().a((DownloadSdCardManager.OnDownloadListener) this);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(this.mCameraDevice.b().f());
        this.mLocalBroadcastManager.registerReceiver(this.mBroadcastReceiver, intentFilter);
        initView();
    }

    private void initView() {
        initSelectView();
        this.mEmptyLayout = (LinearLayout) findViewById(R.id.empty_layout);
        this.mListView = (RecyclerView) findViewById(R.id.list_view);
        this.mAdapter = new SDCardHourAdapter(activity(), this, this.mDid, this.isV4);
        this.mListView.setLayoutManager(new GridLayoutManager(activity(), 3));
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.addItemDecoration(new SDCardItemDecoration());
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.record_files_sdcard_title);
        findViewById(R.id.select_save).setOnClickListener(this);
        findViewById(R.id.select_delete).setOnClickListener(this);
    }

    /* access modifiers changed from: protected */
    public int getSelectCount() {
        return this.mAdapter.getSelectCount();
    }

    /* access modifiers changed from: protected */
    public int getDataCount() {
        return this.mAdapter.getItemCount();
    }

    public void onResume() {
        super.onResume();
        refreshData();
    }

    public void setMultiSelectMode(boolean z) {
        super.setMultiSelectMode(z);
        this.mAdapter.setSelectMode(z);
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
                ArrayList<TimeItem> selectItems = this.mAdapter.getSelectItems();
                initProgressDlg();
                CameraOperationUtils.a(activity(), this.mCameraDevice, selectItems, this.mProgressDialog, new Callback<Void>() {
                    public void onSuccess(Void voidR) {
                        if (!SDCardHourActivity.this.isFinishing()) {
                            SDCardHourActivity.this.runMainThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(SDCardHourActivity.this.activity(), R.string.delete_sucess, 0).show();
                                    SDCardHourActivity.this.disProgressDlg();
                                    SDCardHourActivity.this.setMultiSelectMode(false);
                                    SDCardHourActivity.this.refreshData();
                                }
                            });
                        }
                    }

                    public void onFailure(int i, String str) {
                        if (!SDCardHourActivity.this.isFinishing()) {
                            SDCardHourActivity.this.runMainThread(new Runnable() {
                                public void run() {
                                    SDCardHourActivity.this.disProgressDlg();
                                    Toast.makeText(SDCardHourActivity.this.activity(), R.string.delete_failed, 0).show();
                                }
                            });
                        }
                    }
                });
                return;
            case R.id.select_down:
                ArrayList<TimeItem> selectItems2 = this.mAdapter.getSelectItems();
                if (selectItems2.size() != 0) {
                    for (TimeItem b : selectItems2) {
                        this.mDownloadSdCardFileManager.b(b);
                    }
                    refreshData();
                    setMultiSelectMode(false);
                    return;
                }
                return;
            case R.id.select_save:
                ArrayList<TimeItem> selectItems3 = this.mAdapter.getSelectItems();
                initProgressDlg();
                CameraOperationUtils.b(activity(), this.mCameraDevice, selectItems3, this.mProgressDialog, new Callback<Void>() {
                    public void onSuccess(Void voidR) {
                        if (!SDCardHourActivity.this.isFinishing()) {
                            SDCardHourActivity.this.runMainThread(new Runnable() {
                                public void run() {
                                    Toast.makeText(SDCardHourActivity.this.activity(), R.string.save_sucess, 0).show();
                                    SDCardHourActivity.this.disProgressDlg();
                                    SDCardHourActivity.this.setMultiSelectMode(false);
                                    SDCardHourActivity.this.refreshData();
                                }
                            });
                        }
                    }

                    public void onFailure(int i, String str) {
                        if (!SDCardHourActivity.this.isFinishing()) {
                            SDCardHourActivity.this.runMainThread(new Runnable() {
                                public void run() {
                                    SDCardHourActivity.this.disProgressDlg();
                                    Toast.makeText(SDCardHourActivity.this.activity(), R.string.save_failed, 0).show();
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

    /* access modifiers changed from: private */
    public void refreshData() {
        List<TimeItemDay> g = this.mCameraDevice.c().g();
        if (this.mCameraDevice.n()) {
            g = this.mCameraDevice.d().g();
        }
        if (this.mDay < 0 || this.mDay >= g.size()) {
            this.mTimeItemList = new ArrayList();
        } else {
            TimeItemHour b = g.get(this.mDay).b(this.mHour);
            if (b != null) {
                this.mTimeItemList = b.b;
            } else {
                this.mTimeItemList = new ArrayList();
            }
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.mTimeItemList.size(); i++) {
            TimeItem timeItem = this.mTimeItemList.get(i);
            TimeItemData timeItemData = new TimeItemData();
            timeItemData.timeItem = timeItem;
            timeItemData.title = this.mSimpleDateFormat1.format(Long.valueOf(timeItem.f8098a)) + "-" + this.mSimpleDateFormat1.format(Long.valueOf(timeItem.b()));
            timeItemData.subTitle = this.mSimpleDateFormat.format(Long.valueOf(timeItem.f8098a));
            timeItemData.localFile = this.mCameraDevice.b().a(timeItem.f8098a);
            timeItemData.downloadItem = this.mDownloadSdCardFileManager.a(timeItem);
            arrayList.add(timeItemData);
            this.mNeedCheckList.add(Long.valueOf(timeItem.f8098a));
        }
        this.mTimeItemDataList = arrayList;
        if (this.mTimeItemDataList.isEmpty()) {
            this.mListView.setVisibility(8);
            this.mEmptyLayout.setVisibility(0);
            this.mEditBtn.setVisibility(8);
        } else {
            this.mListView.setVisibility(0);
            this.mEmptyLayout.setVisibility(8);
            this.mEditBtn.setVisibility(0);
        }
        this.mAdapter.setData(this.mTimeItemDataList);
        this.mAdapter.notifyDataSetChanged();
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

    public void onDestroy() {
        super.onDestroy();
        if (this.mAdapter != null) {
            this.mAdapter.destroyDisplayImageOptions();
        }
        if (this.mCameraDevice != null) {
            this.mCameraDevice.h().a((DownloadSdCardManager.OnDownloadListener) null);
        }
        if (this.mLocalBroadcastManager != null) {
            this.mLocalBroadcastManager.unregisterReceiver(this.mBroadcastReceiver);
        }
    }

    public void onDownloading(final DownloadSdCardManager.DownloadItem downloadItem, String str, int i) {
        runMainThread(new Runnable() {
            public void run() {
                if (SDCardHourActivity.this.mNeedCheckList.contains(Long.valueOf(downloadItem.f8073a.f8098a))) {
                    for (int i = 0; i < SDCardHourActivity.this.mTimeItemDataList.size(); i++) {
                        if (SDCardHourActivity.this.mTimeItemDataList.get(i).timeItem.equals(downloadItem.f8073a)) {
                            SDCardHourActivity.this.mTimeItemDataList.get(i).downloadItem = downloadItem;
                            SDCardHourActivity.this.mAdapter.notifyItemChanged(i);
                            return;
                        }
                    }
                }
            }
        });
    }

    public void onDownloadSuccess(DownloadSdCardManager.DownloadItem downloadItem, String str) {
        if (this.mNeedCheckList.contains(Long.valueOf(downloadItem.f8073a.f8098a))) {
            runMainThread(new Runnable() {
                public void run() {
                    SDCardHourActivity.this.refreshData();
                }
            });
        }
    }

    public void onDownloadFailed(DownloadSdCardManager.DownloadItem downloadItem, String str, int i, String str2) {
        if (this.mNeedCheckList.contains(Long.valueOf(downloadItem.f8073a.f8098a))) {
            runMainThread(new Runnable() {
                public void run() {
                    SDCardHourActivity.this.refreshData();
                }
            });
        }
    }

    public void onRecyclerClick(View view) {
        int childLayoutPosition = this.mListView.getChildLayoutPosition(view);
        if (!this.mIsMultiSelectMode) {
            Event.a(Event.aT);
            if (view.getTag() != null) {
                TimeItemData timeItemData = (TimeItemData) view.getTag();
                Intent intent = new Intent(this, SDCardPlayerActivity.class);
                if (this.mCameraDevice != null && this.mCameraDevice.n()) {
                    intent.setClass(this, SDCardPlayerExActivity.class);
                }
                intent.putExtra("mDay", this.mDay);
                intent.putExtra("mHour", this.mHour);
                intent.putExtra("pos", childLayoutPosition);
                intent.putExtra("time", timeItemData.timeItem);
                startActivity(intent);
            }
        } else if (childLayoutPosition >= 0 && childLayoutPosition < this.mAdapter.getItemCount()) {
            this.mAdapter.selectToggle(childLayoutPosition);
            refreshSelectTitle();
            this.mAdapter.notifyItemChanged(childLayoutPosition);
        }
    }

    public void onRecyclerLongClick(View view) {
        if (this.mCameraDevice.isReadOnlyShared()) {
            ToastUtil.a((Context) activity(), (int) R.string.auth_fail);
            return;
        }
        int childLayoutPosition = this.mListView.getChildLayoutPosition(view);
        if (childLayoutPosition >= 0 && childLayoutPosition < this.mAdapter.getItemCount()) {
            this.mAdapter.selectToggle(childLayoutPosition);
            if (!this.mIsMultiSelectMode) {
                setMultiSelectMode(true, true);
                this.mAdapter.setSelectMode(true);
            } else {
                this.mAdapter.notifyItemChanged(childLayoutPosition);
            }
            refreshSelectTitle();
        }
    }
}
