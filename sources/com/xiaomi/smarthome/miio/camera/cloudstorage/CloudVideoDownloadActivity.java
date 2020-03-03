package com.xiaomi.smarthome.miio.camera.cloudstorage;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.tencent.smtt.sdk.TbsReaderView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoDownloadListViewAdapter;
import com.xiaomi.smarthome.miio.camera.cloudstorage.CloudVideoDownloadManager;
import com.xiaomi.smarthome.miio.camera.cloudstorage.model.CloudVideoDownloadInfo;
import java.io.File;
import java.util.List;

public class CloudVideoDownloadActivity extends CloudVideoBaseActivity implements View.OnClickListener {
    private static final String TAG = "CloudVideoDownloadActivity";
    /* access modifiers changed from: private */
    public CloudVideoDownloadListViewAdapter adapter;
    /* access modifiers changed from: private */
    public int currentDownloadingPos = -1;
    private CloudVideoDownloadListView cvdlvDownloadVideo;
    /* access modifiers changed from: private */
    public String did = null;
    private ImageView ivHeaderLeftBack;
    /* access modifiers changed from: private */
    public ImageView ivHeaderRightSetting;
    private RelativeLayout rlTitleBar;
    private TextView tvBlank;
    /* access modifiers changed from: private */
    public TextView tvDelete;
    private TextView tvTitleBarTitle;
    /* access modifiers changed from: private */
    public String uid = null;
    private Vibrator vibrator;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.cs_activity_download_video);
        this.vibrator = (Vibrator) SHApplication.getAppContext().getSystemService("vibrator");
        this.uid = getIntent().getStringExtra("uid");
        this.did = getIntent().getStringExtra("did");
        initViews();
    }

    private void initViews() {
        this.rlTitleBar = (RelativeLayout) findViewById(R.id.rlTitleBar);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(this.rlTitleBar.getLayoutParams());
        layoutParams.setMargins(0, TitleBarUtil.a(), 0, 0);
        this.rlTitleBar.setLayoutParams(layoutParams);
        this.rlTitleBar.bringToFront();
        this.ivHeaderLeftBack = (ImageView) findViewById(R.id.ivHeaderLeftBack);
        this.ivHeaderLeftBack.setOnClickListener(this);
        this.ivHeaderRightSetting = (ImageView) findViewById(R.id.ivHeaderRightSetting);
        this.ivHeaderRightSetting.setVisibility(0);
        this.ivHeaderRightSetting.setImageResource(R.drawable.ic_edit_rename_selector);
        this.ivHeaderRightSetting.setOnClickListener(this);
        this.tvTitleBarTitle = (TextView) findViewById(R.id.tvTitleBarTitle);
        String stringExtra = getIntent().getStringExtra("title");
        if (!TextUtils.isEmpty(stringExtra)) {
            this.tvTitleBarTitle.setText(stringExtra);
        }
        this.tvDelete = (TextView) findViewById(R.id.tvDelete);
        this.tvDelete.setOnClickListener(this);
        this.tvBlank = (TextView) findViewById(R.id.tvBlank);
        this.cvdlvDownloadVideo = (CloudVideoDownloadListView) findViewById(R.id.cvdlvDownloadVideo);
        this.cvdlvDownloadVideo.setLayoutManager(new LinearLayoutManager(this));
        CloudVideoDownloadManager.getInstance().resetStatus(this.uid, this.did);
        this.adapter = new CloudVideoDownloadListViewAdapter();
        this.adapter.clickListener = new CloudVideoDownloadListViewAdapter.IItemClickListener() {
            public void onItemClick(View view, int i, Object obj) {
                if (CloudVideoDownloadActivity.this.adapter.getEditMode()) {
                    if (CloudVideoDownloadActivity.this.adapter.downloadInfoList.get(i).isSelected) {
                        CloudVideoDownloadActivity.this.adapter.downloadInfoList.get(i).isSelected = false;
                    } else {
                        CloudVideoDownloadActivity.this.adapter.downloadInfoList.get(i).isSelected = true;
                    }
                    CloudVideoDownloadActivity.this.adapter.notifyItemChanged(i, "editMode");
                    return;
                }
                if (obj != null && (obj instanceof CloudVideoDownloadInfo)) {
                    CloudVideoDownloadInfo cloudVideoDownloadInfo = (CloudVideoDownloadInfo) obj;
                    if (cloudVideoDownloadInfo.status == 0) {
                        Intent intent = new Intent(CloudVideoDownloadActivity.this.getContext(), CloudVideoLocalExoPlayerActivity.class);
                        String str = cloudVideoDownloadInfo.mp4FilePath;
                        String str2 = cloudVideoDownloadInfo.m3u8LocalPath;
                        LogUtil.a(CloudVideoDownloadActivity.TAG, "mp4FilePath:" + str + " m3u8LocalPath:" + str2);
                        if (!TextUtils.isEmpty(str2)) {
                            intent.putExtra("m3u8LocalPath", "" + str2);
                        } else if (!TextUtils.isEmpty(str)) {
                            intent = new Intent(CloudVideoDownloadActivity.this.getContext(), CloudVideoLocalPlayerActivity.class);
                            intent.putExtra(TbsReaderView.KEY_FILE_PATH, "" + str);
                        }
                        CloudVideoDownloadActivity.this.startActivity(intent);
                        return;
                    }
                }
                if (obj != null && (obj instanceof CloudVideoDownloadInfo)) {
                    CloudVideoDownloadInfo cloudVideoDownloadInfo2 = (CloudVideoDownloadInfo) obj;
                    if (cloudVideoDownloadInfo2.status == 1 || cloudVideoDownloadInfo2.status == 4) {
                        CloudVideoDownloadManager.getInstance().cancelCurrentDownload(cloudVideoDownloadInfo2.did, cloudVideoDownloadInfo2.fileId);
                        cloudVideoDownloadInfo2.status = 8;
                        CloudVideoDownloadManager.getInstance().updateRecord(cloudVideoDownloadInfo2);
                        CloudVideoDownloadManager.getInstance().pullDownloadFromList(CloudVideoDownloadActivity.this.getApplicationContext(), CloudVideoDownloadActivity.this.uid, CloudVideoDownloadActivity.this.did);
                        CloudVideoDownloadActivity.this.adapter.notifyItemChanged(i, "itemClick");
                        return;
                    }
                }
                if (obj != null && (obj instanceof CloudVideoDownloadInfo)) {
                    CloudVideoDownloadInfo cloudVideoDownloadInfo3 = (CloudVideoDownloadInfo) obj;
                    if (cloudVideoDownloadInfo3.status == 8 || cloudVideoDownloadInfo3.status == 2) {
                        cloudVideoDownloadInfo3.status = 4;
                        CloudVideoDownloadManager.getInstance().updateRecord(cloudVideoDownloadInfo3);
                        CloudVideoDownloadManager.getInstance().pullDownloadFromList(CloudVideoDownloadActivity.this.getApplicationContext(), CloudVideoDownloadActivity.this.uid, CloudVideoDownloadActivity.this.did);
                        CloudVideoDownloadActivity.this.adapter.notifyItemChanged(i, "itemClick");
                    }
                }
            }

            public void onItemLongClick(View view, int i, Object obj) {
                CloudVideoDownloadActivity.this.enterEditMode();
                if (CloudVideoDownloadActivity.this.adapter != null && CloudVideoDownloadActivity.this.adapter.getEditMode()) {
                    CloudVideoDownloadActivity.this.ivHeaderRightSetting.setImageResource(R.drawable.cs_icon_edit_select_all);
                }
            }
        };
        this.adapter.modeChangedListener = new CloudVideoDownloadListViewAdapter.ModeChangedListener() {
            public void onEditModeChanged(boolean z) {
                if (z) {
                    CloudVideoDownloadActivity.this.tvDelete.setVisibility(0);
                } else {
                    CloudVideoDownloadActivity.this.tvDelete.setVisibility(8);
                }
                CloudVideoDownloadActivity.this.getWindow().getDecorView().requestLayout();
            }
        };
        this.cvdlvDownloadVideo.setAdapter(this.adapter);
    }

    public void onBackPressed() {
        if (this.adapter == null || !this.adapter.getEditMode()) {
            super.onBackPressed();
        } else {
            exitEditMode();
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        refreshData();
        download();
        CloudVideoDownloadManager.getInstance().addListener(new CloudVideoDownloadManager.ICloudVideoDownloadListener() {
            public void onStart(final CloudVideoDownloadInfo cloudVideoDownloadInfo) {
                LogUtil.a(CloudVideoDownloadActivity.TAG, "onStart url:" + cloudVideoDownloadInfo.m3u8FilePath + " filePath:" + cloudVideoDownloadInfo.mp4FilePath);
                CloudVideoDownloadActivity.this.getDownloadingIndex(cloudVideoDownloadInfo);
                CloudVideoDownloadActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        CloudVideoDownloadActivity.this.adapter.updateDownloadingItem(cloudVideoDownloadInfo);
                    }
                });
            }

            public void onStop(final CloudVideoDownloadInfo cloudVideoDownloadInfo, int i) {
                cloudVideoDownloadInfo.status = 4;
                CloudVideoDownloadActivity.this.getDownloadingIndex(cloudVideoDownloadInfo);
                CloudVideoDownloadActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        CloudVideoDownloadActivity.this.adapter.updateDownloadingItem(cloudVideoDownloadInfo);
                        CloudVideoDownloadActivity.this.adapter.notifyItemChanged(CloudVideoDownloadActivity.this.currentDownloadingPos, "onStop");
                    }
                });
                LogUtil.a(CloudVideoDownloadActivity.TAG, "onStop:" + i);
            }

            public void onFinish(final CloudVideoDownloadInfo cloudVideoDownloadInfo) {
                LogUtil.a(CloudVideoDownloadActivity.TAG, "onFinish:" + cloudVideoDownloadInfo.mp4FilePath + " m3u8FilePath:" + cloudVideoDownloadInfo.m3u8FilePath);
                cloudVideoDownloadInfo.status = 0;
                CloudVideoDownloadActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        if (!TextUtils.isEmpty(cloudVideoDownloadInfo.mp4FilePath)) {
                            CloudVideoDownloadActivity.this.insertToAlbum(cloudVideoDownloadInfo.mp4FilePath);
                        }
                        CloudVideoDownloadActivity.this.adapter.updateDownloadingItem(cloudVideoDownloadInfo);
                        CloudVideoDownloadActivity.this.adapter.notifyItemChanged(CloudVideoDownloadActivity.this.currentDownloadingPos, "onFinish");
                    }
                });
            }

            public void onCancelled(final CloudVideoDownloadInfo cloudVideoDownloadInfo) {
                cloudVideoDownloadInfo.status = 8;
                CloudVideoDownloadActivity.this.getDownloadingIndex(cloudVideoDownloadInfo);
                CloudVideoDownloadActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        CloudVideoDownloadActivity.this.adapter.updateDownloadingItem(cloudVideoDownloadInfo);
                        CloudVideoDownloadActivity.this.adapter.notifyItemChanged(CloudVideoDownloadActivity.this.currentDownloadingPos, "onCancelled");
                    }
                });
                LogUtil.a(CloudVideoDownloadActivity.TAG, "onCancelled");
            }

            public void onInfo(CloudVideoDownloadInfo cloudVideoDownloadInfo, int i) {
                CloudVideoDownloadActivity.this.getDownloadingIndex(cloudVideoDownloadInfo);
                CloudVideoDownloadActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        CloudVideoDownloadActivity.this.adapter.notifyItemChanged(CloudVideoDownloadActivity.this.currentDownloadingPos, "onInfo");
                    }
                });
                LogUtil.a(CloudVideoDownloadActivity.TAG, "onInfo code:" + i);
            }

            public void onError(final CloudVideoDownloadInfo cloudVideoDownloadInfo, int i) {
                cloudVideoDownloadInfo.status = 2;
                CloudVideoDownloadActivity.this.getDownloadingIndex(cloudVideoDownloadInfo);
                CloudVideoDownloadActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        CloudVideoDownloadActivity.this.adapter.updateDownloadingItem(cloudVideoDownloadInfo);
                        CloudVideoDownloadActivity.this.adapter.notifyItemChanged(CloudVideoDownloadActivity.this.currentDownloadingPos, "onError");
                    }
                });
                LogUtil.a(CloudVideoDownloadActivity.TAG, "onError code:" + i);
            }

            public void onProgress(final CloudVideoDownloadInfo cloudVideoDownloadInfo, int i) {
                LogUtil.a(CloudVideoDownloadActivity.TAG, "progress:" + i + " currentDownloadingPos:" + CloudVideoDownloadActivity.this.currentDownloadingPos);
                cloudVideoDownloadInfo.progress = i;
                cloudVideoDownloadInfo.status = 1;
                CloudVideoDownloadActivity.this.getDownloadingIndex(cloudVideoDownloadInfo);
                CloudVideoDownloadActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        CloudVideoDownloadActivity.this.adapter.updateDownloadingItem(cloudVideoDownloadInfo);
                        CloudVideoDownloadActivity.this.adapter.notifyItemChanged(CloudVideoDownloadActivity.this.currentDownloadingPos, "onProgress");
                    }
                });
            }

            public void onSpeed(CloudVideoDownloadInfo cloudVideoDownloadInfo, int i) {
                CloudVideoDownloadActivity.this.getDownloadingIndex(cloudVideoDownloadInfo);
            }
        });
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ivHeaderLeftBack) {
            onBackPressed();
        } else if (id != R.id.ivHeaderRightSetting) {
            if (id == R.id.tvDelete) {
                confirmDelete();
            }
        } else if (this.adapter == null || !this.adapter.getEditMode()) {
            if (this.adapter == null || this.adapter.getItemCount() <= 0) {
                ToastUtil.a((int) R.string.cs_no_data);
                return;
            }
            enterEditMode();
            if (this.adapter.getEditMode()) {
                this.ivHeaderRightSetting.setImageResource(R.drawable.cs_icon_edit_select_all);
            }
        } else if (this.adapter.getSelectedItemCount() == this.adapter.downloadInfoList.size()) {
            this.ivHeaderRightSetting.setImageResource(R.drawable.cs_icon_edit_select_all);
            this.adapter.clearAll();
        } else {
            this.ivHeaderRightSetting.setImageResource(R.drawable.cs_icon_edit_deselect_all);
            this.adapter.selectAll();
        }
    }

    /* access modifiers changed from: private */
    public void exitEditMode() {
        if (this.adapter != null && !TextUtils.isEmpty(this.uid) && !TextUtils.isEmpty(this.did)) {
            this.adapter.downloadInfoList.clear();
            this.adapter.downloadInfoList = CloudVideoDownloadManager.getInstance().getRecords(this.uid, this.did);
            this.adapter.notifyDataSetChanged();
        }
        if (this.adapter.getEditMode()) {
            this.vibrator.vibrate(100);
            this.adapter.setEditMode(false);
        }
    }

    /* access modifiers changed from: private */
    public void enterEditMode() {
        if (this.adapter != null && !TextUtils.isEmpty(this.uid) && !TextUtils.isEmpty(this.did)) {
            this.adapter.downloadInfoList.clear();
            this.adapter.downloadInfoList = CloudVideoDownloadManager.getInstance().getRecords(this.uid, this.did);
            this.adapter.notifyDataSetChanged();
        }
        if (!this.adapter.getEditMode()) {
            this.vibrator.vibrate(100);
            this.adapter.setEditMode(true);
        }
    }

    private void confirmDelete() {
        new MLAlertDialog.Builder(this).b((CharSequence) getString(R.string.cs_delete_video)).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                CloudVideoDownloadActivity.this.deleteDownloadedVideo();
            }
        }).a((DialogInterface.OnCancelListener) new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                CloudVideoDownloadActivity.this.exitEditMode();
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                CloudVideoDownloadActivity.this.exitEditMode();
            }
        }).d();
    }

    /* access modifiers changed from: private */
    public void deleteDownloadedVideo() {
        if (this.adapter.getSelectedItemCount() > 0) {
            CloudVideoDownloadManager.getInstance().deleteRecords(this.adapter.getSelectedItem());
            refreshData();
        } else {
            ToastUtil.a((int) R.string.cs_select_video);
        }
        exitEditMode();
    }

    private void refreshData() {
        List<CloudVideoDownloadInfo> records = CloudVideoDownloadManager.getInstance().getRecords(this.uid, this.did);
        if (records == null || records.size() <= 0) {
            this.tvBlank.setVisibility(0);
        } else {
            List<CloudVideoDownloadInfo> list = this.adapter.downloadInfoList;
            this.adapter.downloadInfoList = records;
            if (list != null) {
                int size = list.size();
                for (int i = 0; i < size; i++) {
                    this.adapter.updateDownloadingItem(list.get(i));
                }
            }
            this.tvBlank.setVisibility(8);
        }
        if (this.adapter != null && !TextUtils.isEmpty(this.uid) && !TextUtils.isEmpty(this.did)) {
            this.adapter.downloadInfoList.clear();
            this.adapter.downloadInfoList = CloudVideoDownloadManager.getInstance().getRecords(this.uid, this.did);
            this.adapter.notifyDataSetChanged();
        }
    }

    private void download() {
        CloudVideoDownloadManager.getInstance().insertRecords(this.adapter.getSelectedItem());
        CloudVideoDownloadManager.getInstance().pullDownloadFromList(getContext().getApplicationContext(), this.uid, this.did);
    }

    /* access modifiers changed from: private */
    public void getDownloadingIndex(CloudVideoDownloadInfo cloudVideoDownloadInfo) {
        if (this.adapter != null) {
            int i = 0;
            while (i < this.adapter.downloadInfoList.size()) {
                if (cloudVideoDownloadInfo == null || cloudVideoDownloadInfo.id != this.adapter.downloadInfoList.get(i).id) {
                    i++;
                } else {
                    this.currentDownloadingPos = i;
                    return;
                }
            }
        }
        this.currentDownloadingPos = -1;
    }

    /* access modifiers changed from: private */
    public void insertToAlbum(String str) {
        File file = new File(str);
        if (file.exists()) {
            sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", Uri.fromFile(file)));
        }
    }
}
