package com.xiaomi.smarthome.camera.activity.local;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.Utils.FileUtil;
import com.mijia.app.AppConfig;
import com.mijia.camera.CameraPlayerEx;
import com.mijia.camera.ICameraPlayerListener;
import com.mijia.camera.Utils.Util;
import com.mijia.debug.SDKLog;
import com.mijia.model.local.LocalFileManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.IStreamCmdMessageListener;
import com.xiaomi.smarthome.camera.activity.BaseSelectActivity;
import com.xiaomi.smarthome.camera.activity.CommandTreatment;
import com.xiaomi.smarthome.camera.activity.timelapse.TimeLapseTaskManager;
import com.xiaomi.smarthome.camera.activity.timelapse.Timelapse;
import com.xiaomi.smarthome.camera.activity.timelapse.TimelapseTask;
import com.xiaomi.smarthome.camera.view.recycle.RecyclerClickListener;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;

public class AlbumActivity extends BaseSelectActivity implements ICameraPlayerListener, IStreamCmdMessageListener, RecyclerClickListener {
    private static final String TAG = "AlbumActivity";
    ArrayList<TimelapseTask> batcheDelTLArray;
    int batcheDelTNum = 0;
    private AlbumAdapter mAdapter;
    private CameraPlayerEx mCameraPlayer;
    /* access modifiers changed from: private */
    public LinearLayout mEmptyLayout;
    /* access modifiers changed from: private */
    public RecyclerView mListView;
    /* access modifiers changed from: private */
    public LinearLayout mLoadLayout;
    LocalFileManager mLocalFileManager;
    SimpleDateFormat mSimpleDateFormat1;
    private TimeLapseTaskManager mTImeLapseTaskManager;
    private List<LocalFileData> picData = new ArrayList();
    ArrayList<TimelapseTask> preDelTLArray;
    /* access modifiers changed from: private */
    public List<LocalFileData> selecttimelapseList = new ArrayList();
    private ArrayList<LocalFileData> timeLapseData = new ArrayList<>();

    public void onDisConnected() {
    }

    public void onDisconnectedWithCode(int i) {
    }

    public void onPauseCamera() {
    }

    public void onPrepare(int i) {
    }

    public void onRetry(int i, String str, int i2) {
    }

    public void onVideoInfo(int i, int i2, int i3, int i4, int i5) {
    }

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.camera_activity_album);
        this.mLocalFileManager = this.mCameraDevice.b();
        this.mSimpleDateFormat1 = new SimpleDateFormat("mm:ss");
        this.mSimpleDateFormat1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        initView();
        if (this.mCameraDevice.o()) {
            this.mTImeLapseTaskManager = new TimeLapseTaskManager(this.mCameraDevice, this);
        }
    }

    public void onResume() {
        super.onResume();
        if (this.mCameraDevice.o()) {
            if (Timelapse.timeLapseList != null) {
                Timelapse.timeLapseList.clear();
            }
            startPlay();
        }
        loadData();
    }

    public void onPause() {
        super.onPause();
        if (this.mCameraDevice.o() && this.mCameraPlayer != null) {
            this.mCameraPlayer.w();
            this.mCameraPlayer = null;
        }
    }

    private void initView() {
        initSelectView();
        this.mEmptyLayout = (LinearLayout) findViewById(R.id.empty_layout);
        this.mLoadLayout = (LinearLayout) findViewById(R.id.loading_layout);
        this.mListView = (RecyclerView) findViewById(R.id.list_view);
        findViewById(R.id.select_delete).setOnClickListener(this);
        this.mAdapter = new AlbumAdapter(this, this, this.isV4);
        this.mListView.setAdapter(this.mAdapter);
        this.mListView.setLayoutManager(new GridLayoutManager(this, 3));
        this.mListView.addItemDecoration(new AlbumDecoration());
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.album);
        ((AnimationDrawable) ((ImageView) findViewById(R.id.loading_img)).getDrawable()).start();
    }

    private void loadData() {
        this.mLocalFileManager.b((Callback<Void>) new Callback<Void>() {
            public void onSuccess(Void voidR) {
                if (!AlbumActivity.this.isFinishing()) {
                    AlbumActivity.this.mLoadLayout.setVisibility(8);
                    AlbumActivity.this.setData(AlbumActivity.this.mLocalFileManager.g());
                    AlbumActivity.this.handleTimeLapseData();
                }
            }

            public void onFailure(int i, String str) {
                if (!AlbumActivity.this.isFinishing()) {
                    AlbumActivity.this.mEmptyLayout.setVisibility(0);
                    AlbumActivity.this.mListView.setVisibility(8);
                    AlbumActivity.this.mLoadLayout.setVisibility(8);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleTimeLapseData() {
        if (!this.mCameraDevice.o()) {
            refreshData();
            return;
        }
        loadTimeLapseData();
        refreshData();
    }

    /* access modifiers changed from: package-private */
    public void setData(List<LocalFileManager.LocalFile> list) {
        this.picData = new ArrayList();
        for (int size = list.size() - 1; size >= 0; size--) {
            LocalFileManager.LocalFile localFile = list.get(size);
            LocalFileData localFileData = new LocalFileData();
            localFileData.item = localFile;
            localFileData.videoType = 0;
            localFileData.title = this.mSimpleDateFormat1.format(Long.valueOf(localFile.b));
            this.picData.add(localFileData);
        }
    }

    /* access modifiers changed from: package-private */
    public void refreshData() {
        SDKLog.b(TAG, "setData timeLapseData===" + this.timeLapseData.size());
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(this.picData);
        arrayList.addAll(this.timeLapseData);
        if (arrayList.isEmpty()) {
            this.mEmptyLayout.setVisibility(0);
            this.mListView.setVisibility(8);
            this.mEditBtn.setVisibility(8);
        } else {
            this.mEmptyLayout.setVisibility(8);
            this.mListView.setVisibility(0);
            this.mAdapter.setData(arrayList);
            this.mEditBtn.setVisibility(0);
        }
        SDKLog.b(TAG, "相册中总长度=" + arrayList.size());
        this.mAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public int getSelectCount() {
        return this.mAdapter.getSelectCount();
    }

    /* access modifiers changed from: protected */
    public int getDataCount() {
        return this.mAdapter.getItemCount();
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
                startPlay();
                delete();
                return;
            case R.id.title_bar_more:
                setMultiSelectMode(true);
                return;
            case R.id.title_bar_return:
                onBackPressed();
                return;
            default:
                return;
        }
    }

    public void setMultiSelectMode(boolean z) {
        super.setMultiSelectMode(z);
        this.mAdapter.setSelectMode(z);
    }

    public void onBackPressed() {
        if (this.mIsMultiSelectMode) {
            setMultiSelectMode(false);
        } else {
            super.onBackPressed();
        }
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mAdapter != null) {
            this.mAdapter.destroyDisplayImageOptions();
        }
    }

    public void onRecyclerClick(View view) {
        int childLayoutPosition = this.mListView.getChildLayoutPosition(view);
        if (this.mIsMultiSelectMode) {
            this.mAdapter.selectToggle(childLayoutPosition);
            refreshSelectTitle();
            this.mAdapter.notifyItemChanged(childLayoutPosition);
            return;
        }
        LocalFileData item = this.mAdapter.getItem(childLayoutPosition);
        if (item != null) {
            Intent intent = new Intent();
            SDKLog.b(TAG, "onRecyclerClick localFileData.videoType=" + item.videoType);
            if (item.videoType == 3) {
                if (item.videoFile != null) {
                    intent.putExtra("file_path", item.videoFile.getAbsolutePath());
                    SDKLog.b(TAG, "onRecyclerClick localFileData.file_path=" + item.videoFile.getAbsolutePath());
                }
                intent.putExtra("file_realtime", item.realStartTimeInSec);
                intent.putExtra("video_path", item.videoPath);
                intent.putExtra("file_time", item.startTimeInSec);
                intent.setClass(this, TimelapsePhotographPlayActivity.class);
                startActivity(intent);
                return;
            }
            SDKLog.b(TAG, "onRecyclerClick localFileData.item.path=" + item.item.d);
            intent.putExtra("file_path", item.item.d);
            if (item.item.e) {
                intent.setClass(this, LocalVideoPlayActivity.class);
                startActivity(intent);
                return;
            }
            intent.setClass(this, LocalPicActivity.class);
            startActivity(intent);
        }
    }

    public void onRecyclerLongClick(View view) {
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

    private void delete() {
        final ArrayList<LocalFileData> selectItems = this.mAdapter.getSelectItems();
        if (selectItems.isEmpty()) {
            Toast.makeText(this, R.string.bottom_action_tip, 0).show();
            return;
        }
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        builder.b((int) R.string.delete_alert);
        builder.a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                ArrayList arrayList = new ArrayList();
                List unused = AlbumActivity.this.selecttimelapseList = new ArrayList();
                Iterator it = selectItems.iterator();
                while (it.hasNext()) {
                    LocalFileData localFileData = (LocalFileData) it.next();
                    SDKLog.b(AlbumActivity.TAG, "data=" + localFileData.videoType);
                    SDKLog.b(AlbumActivity.TAG, "data=" + localFileData.videoPath);
                    SDKLog.b(AlbumActivity.TAG, "data=" + localFileData.startTimeInSec);
                    SDKLog.b(AlbumActivity.TAG, "data=" + localFileData.imgPath);
                    if (localFileData.videoType == 3) {
                        AlbumActivity.this.selecttimelapseList.add(localFileData);
                    } else {
                        arrayList.add(localFileData.item);
                    }
                }
                AlbumActivity.this.setMultiSelectMode(false);
                AlbumActivity.this.deleteTimeLapse(AlbumActivity.this.selecttimelapseList);
                AlbumActivity.this.mLocalFileManager.a((List<LocalFileManager.LocalFile>) arrayList, (Callback<Void>) new Callback<Void>() {
                    public void onSuccess(Void voidR) {
                        if (!AlbumActivity.this.isFinishing()) {
                            AlbumActivity.this.mHandler.post(new Runnable() {
                                public void run() {
                                    AlbumActivity.this.setData(AlbumActivity.this.mLocalFileManager.g());
                                    AlbumActivity.this.refreshData();
                                }
                            });
                        }
                    }

                    public void onFailure(int i, String str) {
                        if (!AlbumActivity.this.isFinishing()) {
                            AlbumActivity.this.mHandler.post(new Runnable() {
                                public void run() {
                                    AlbumActivity.this.setData(AlbumActivity.this.mLocalFileManager.g());
                                    AlbumActivity.this.refreshData();
                                }
                            });
                        }
                    }
                });
            }
        });
        builder.b((int) R.string.cancel, (DialogInterface.OnClickListener) null);
        builder.d();
    }

    private void loadTimeLapseData() {
        SDKLog.b(TAG, "----------------loadTimeLapseData------------------");
        this.timeLapseData = new ArrayList<>();
        try {
            refreshSDCardFolder(this, FileUtil.f(this.mCameraDevice.getDid()));
            Timelapse.getSavedTimeLapseData(this.mCameraDevice);
            if (this.mTImeLapseTaskManager != null) {
                SDKLog.b(TAG, "发送了获取延时摄影列表的命令");
                this.mTImeLapseTaskManager.getTimelapseList(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
            SDKLog.b(TAG, "Exception: " + e.getMessage());
        }
        if (Timelapse.timeLapseList == null || Timelapse.timeLapseList.size() <= 0) {
            SDKLog.b(TAG, "Timelapse list length: 0");
            return;
        }
        SDKLog.b(TAG, "Timelapse list length: " + Timelapse.timeLapseList.size());
        Iterator<TimelapseTask> it = Timelapse.timeLapseList.iterator();
        while (it.hasNext()) {
            addTimelapseData(this, this.timeLapseData, it.next());
        }
        SDKLog.b(TAG, "延时摄像的长度=" + this.timeLapseData.size());
    }

    public void refreshSDCardFolder(Context context, String str) {
        File file = new File(str);
        if (file.isDirectory()) {
            File[] listFiles = file.listFiles();
            for (File file2 : listFiles) {
                if (file2.isFile()) {
                    Uri parse = Uri.parse("file://" + file2.getAbsolutePath());
                    SDKLog.b(TAG, "loadTimeLapseData ----------- f.getAbsolutePath()=" + file2.getAbsolutePath());
                    sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", parse));
                } else {
                    refreshSDCardFolder(context, file2.getAbsolutePath());
                }
            }
        }
    }

    public ArrayList<LocalFileData> addTimelapseData(Context context, ArrayList<LocalFileData> arrayList, TimelapseTask timelapseTask) {
        if (timelapseTask == null || arrayList == null) {
            return arrayList;
        }
        int hasItem = hasItem(arrayList, timelapseTask);
        if (hasItem >= 0) {
            arrayList.get(hasItem).copyTimeLapse(timelapseTask, this.mCameraDevice);
        } else {
            LocalFileData localFileData = new LocalFileData(timelapseTask, this.mCameraDevice);
            localFileData.imgPath = timelapseTask.getLatestPicPath();
            LocalFileData.timelapseSaveType = timelapseTask.getTimelapseSaveType();
            SDKLog.b(TAG, "tem.recordStatus=" + localFileData.recordStatus);
            arrayList.add(localFileData);
        }
        return arrayList;
    }

    private int hasItem(ArrayList<LocalFileData> arrayList, TimelapseTask timelapseTask) {
        for (int i = 0; i < arrayList.size(); i++) {
            LocalFileData localFileData = arrayList.get(i);
            if (localFileData.getStartTimeInSec() == timelapseTask.getStartTimestampInUTCSeconds() && localFileData.getVideoType() == 3) {
                return i;
            }
        }
        return -1;
    }

    /* access modifiers changed from: private */
    public void deleteTimeLapse(List<LocalFileData> list) {
        this.preDelTLArray = new ArrayList<>();
        this.preDelTLArray.clear();
        for (LocalFileData next : list) {
            SDKLog.b(TAG, " 删除Timelapse：" + next.getStartTimeInSec());
            SDKLog.b(TAG, " 删除Timelapse：" + next.videoPath);
            this.preDelTLArray.add(new TimelapseTask(3, (int) (next.getStartTimeInSec() + ((long) Util.d())), next.getStartTimeInSec()));
        }
        if (this.preDelTLArray.size() > 0) {
            SDKLog.c(TAG, "=====================================0");
            if (this.mTImeLapseTaskManager != null) {
                SDKLog.b(TAG, "发送了删除延时摄影的命令 preDelTLArray.size==" + this.preDelTLArray.size());
                this.mTImeLapseTaskManager.deleteRecord(this.preDelTLArray, this);
            }
        }
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        switch (message.what) {
            case CommandTreatment.GET_TIMELAPSE_TASK_FROM_SD:
                if (message.obj != null) {
                    SDKLog.b(TAG, "======SD timeLapseTask size ==========" + ((ArrayList) message.obj).size());
                    Iterator it = ((ArrayList) message.obj).iterator();
                    while (it.hasNext()) {
                        TimelapseTask timelapseTask = (TimelapseTask) it.next();
                        SDKLog.b(TAG, "==============tlt==============" + timelapseTask.status);
                        long startTimestampInUTCSeconds = timelapseTask.getStartTimestampInUTCSeconds();
                        if (Timelapse.getTimeLapseFromList(startTimestampInUTCSeconds) != null) {
                            SDKLog.b(TAG, "==============列表中有");
                            Timelapse.getTimeLapseFromList(startTimestampInUTCSeconds).setTimelapseSaveType(1);
                            Timelapse.getTimeLapseFromList(startTimestampInUTCSeconds).setFileStatus(timelapseTask.getFileStatus());
                            if (timelapseTask.status == 2) {
                                Timelapse.getTimeLapseFromList(startTimestampInUTCSeconds).refreshState();
                            }
                        } else {
                            SDKLog.b(TAG, "==============列表没有，新建,taskID=" + timelapseTask.getTaskID());
                            timelapseTask.createLocalFolder(true, false, this.mCameraDevice);
                            Timelapse.timeLapseList.add(timelapseTask);
                            addTimelapseData(activity(), this.timeLapseData, timelapseTask);
                        }
                    }
                    refreshData();
                    SDKLog.b(TAG, "相册中的数据的总长度=" + this.mAdapter.getItemCount());
                    return;
                }
                return;
            case CommandTreatment.DEL_VIDEO_CLIP_RESULT:
                SDKLog.b(TAG, "相册中的数据删除成功 msg.arg1=" + message.arg1);
                SDKLog.b(TAG, "相册中的删除数据后的长度=" + this.mAdapter.getItemCount());
                if (message.arg1 == 1) {
                    SDKLog.b(TAG, "相册中的数据删除成功前 timeLapseData===" + this.timeLapseData.size());
                    SDKLog.b(TAG, "相册中的数据删除成功前 preDelTLArray===" + this.selecttimelapseList.size());
                    SDKLog.b(TAG, "相册中的数据删除成功preDelTLArray.size=" + this.preDelTLArray.size());
                    if (this.preDelTLArray.size() > 3) {
                        deteBatcheTimeLapse();
                        SDKLog.b(TAG, "相册中的数据删除成功batcheDelTNum=" + this.batcheDelTNum);
                        if (this.batcheDelTNum == 0) {
                            boolean removeAll = this.timeLapseData.removeAll(this.selecttimelapseList);
                            SDKLog.b(TAG, "相册中的数据删除成功后 timeLapseData===" + this.timeLapseData.size());
                            SDKLog.b(TAG, "相册中的数据删除成功后 result===" + removeAll);
                            deleteLocalTimeLapse(this.preDelTLArray);
                            setData(this.mLocalFileManager.g());
                        }
                    } else {
                        boolean removeAll2 = this.timeLapseData.removeAll(this.selecttimelapseList);
                        SDKLog.b(TAG, "相册中的数据删除成功后 timeLapseData===" + this.timeLapseData.size());
                        SDKLog.b(TAG, "相册中的数据删除成功后 result===" + removeAll2);
                        deleteLocalTimeLapse(this.preDelTLArray);
                        setData(this.mLocalFileManager.g());
                    }
                    refreshData();
                    return;
                }
                SDKLog.b(TAG, "延时摄影删除失败");
                this.batcheDelTNum = 0;
                ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.delete_timelapse_failed));
                return;
            default:
                return;
        }
    }

    private void deteBatcheTimeLapse() {
        this.batcheDelTLArray = new ArrayList<>();
        this.batcheDelTLArray.clear();
        this.batcheDelTNum += 3;
        SDKLog.b(TAG, "deteBatcheTimeLapse batcheDelTNum=" + this.batcheDelTNum);
        if (this.preDelTLArray.size() > 3) {
            for (int i = this.batcheDelTNum; i < this.preDelTLArray.size(); i++) {
                this.batcheDelTLArray.add(this.preDelTLArray.get(i));
            }
        }
        SDKLog.b(TAG, "deteBatcheTimeLapse batcheDelTLArray.size()=" + this.batcheDelTLArray.size());
        if (this.batcheDelTLArray.size() <= 0) {
            SDKLog.b(TAG, "要删除的延时摄影为空");
            this.batcheDelTNum = 0;
        } else if (this.mCameraPlayer != null) {
            SDKLog.b(TAG, "deteBatcheTimeLapse 发送了删除延时摄影的命令 preDelTLArray.size==" + this.batcheDelTLArray.size());
            this.mTImeLapseTaskManager.deleteRecord(this.batcheDelTLArray, this);
        }
    }

    private void deleteLocalTimeLapse(ArrayList<TimelapseTask> arrayList) {
        Iterator<TimelapseTask> it = arrayList.iterator();
        while (it.hasNext()) {
            TimelapseTask next = it.next();
            SDKLog.b(TAG, " 删除Timelapse22：" + next.startTimestampInUTCSeconds);
            TimelapseTask timeLapseFromList = Timelapse.getTimeLapseFromList(next.startTimestampInUTCSeconds);
            if (timeLapseFromList != null) {
                timeLapseFromList.deleLocalDataNew((File) null);
            } else {
                return;
            }
        }
    }

    public void onVideoPlayError(int i, String str) {
        SDKLog.b(TAG, "onVideoPlayError .....error=" + i + ",errorInfo=" + str);
    }

    private void startPlay() {
        if (this.mCameraDevice.o()) {
            StringBuilder sb = new StringBuilder();
            sb.append("mCameraPlayer =");
            sb.append(this.mCameraPlayer == null ? "" : Boolean.valueOf(this.mCameraPlayer.l()));
            SDKLog.b(TAG, sb.toString());
            if (this.mCameraPlayer != null) {
                this.mCameraPlayer.w();
                this.mCameraPlayer = null;
            }
            if (this.mCameraPlayer == null) {
                this.mCameraPlayer = new CameraPlayerEx(this.mCameraDevice, this);
            }
            this.mCameraPlayer.d();
        }
    }

    public void onConnected() {
        SDKLog.b(TAG, "onConnected  。。。。");
        if (this.mCameraPlayer != null) {
            SDKLog.b(TAG, "连接摄像头成功,");
            this.mCameraPlayer.A();
            this.mCameraPlayer.z();
        }
    }

    public void onServerCmd(int i, byte[] bArr) {
        SDKLog.b(TAG, "CommandTreatment onServerCmd type=" + i + ",mHandler=" + this.mHandler);
        new CommandTreatment(this.mHandler, this.mCameraDevice).processData(bArr);
    }

    public void onSendCmdError() {
        SDKLog.b(TAG, "onSendCmdError");
        ToastUtil.a((Context) activity(), (CharSequence) getString(R.string.operation_failed));
    }

    class AlbumDecoration extends RecyclerView.ItemDecoration {
        private int space = ((int) (AppConfig.d * 2.0f));

        AlbumDecoration() {
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            if ((recyclerView.getChildAdapterPosition(view) + 1) % 3 == 0) {
                rect.set(0, this.space, 0, 0);
            } else {
                rect.set(0, this.space, this.space, 0);
            }
        }
    }
}
