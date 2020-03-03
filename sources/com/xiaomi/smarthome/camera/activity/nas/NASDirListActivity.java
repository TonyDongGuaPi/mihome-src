package com.xiaomi.smarthome.camera.activity.nas;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mijia.camera.nas.NASInfo;
import com.mijia.camera.nas.NASNode;
import com.mijia.camera.nas.NASServer;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.view.CameraPullDownRefreshListView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import java.util.ArrayList;
import java.util.List;

public class NASDirListActivity extends CameraBaseActivity {
    private SimpleAdapter mAdapter;
    private NASInfo mCurrentInfo;
    /* access modifiers changed from: private */
    public NASNode mCurrentNode;
    /* access modifiers changed from: private */
    public List<NASNode> mData = new ArrayList();
    private View mEmptyView;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler(Looper.getMainLooper());
    private ImageView mIcon;
    private CameraPullDownRefreshListView mListView;
    /* access modifiers changed from: private */
    public int mPollCnt = 3;
    /* access modifiers changed from: private */
    public XQProgressDialog mXQProgressDialog;
    /* access modifiers changed from: private */
    public ImageView titleBarReturn;

    static /* synthetic */ int access$1810(NASDirListActivity nASDirListActivity) {
        int i = nASDirListActivity.mPollCnt;
        nASDirListActivity.mPollCnt = i - 1;
        return i;
    }

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.camera_activity_device_smb_discovery_list);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.mCurrentInfo = (NASInfo) intent.getParcelableExtra("data");
        this.mCurrentNode = new NASNode(this.mCurrentInfo.a(), this.mCurrentInfo.a().e());
        initView();
        loadNASServerList();
    }

    private void initView() {
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.smb_choose_storage_title);
        this.titleBarReturn = (ImageView) findViewById(R.id.title_bar_return);
        this.titleBarReturn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NASDirListActivity.this.finish();
            }
        });
        findViewById(R.id.title_bar_more).setVisibility(8);
        this.mListView = (CameraPullDownRefreshListView) findViewById(R.id.list);
        this.mAdapter = new SimpleAdapter();
        this.mListView.setAdapter(this.mAdapter);
        this.mEmptyView = findViewById(R.id.white_empty_view);
        this.mEmptyView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NASDirListActivity.this.loadNASServerList();
            }
        });
        this.mIcon = (ImageView) findViewById(R.id.empty_icon);
        initProgressDialog();
    }

    private void initProgressDialog() {
        this.mXQProgressDialog = new XQProgressDialog(this);
        this.mXQProgressDialog.setMessage(getString(R.string.smb_waiting));
        this.mXQProgressDialog.setCancelable(true);
    }

    /* access modifiers changed from: private */
    public void loadNASServerList() {
        this.mXQProgressDialog.show();
        this.mCurrentNode.a("");
        this.mCameraDevice.l().a(this.mCurrentNode, (Callback<List<NASNode>>) new Callback<List<NASNode>>() {
            public void onSuccess(List<NASNode> list) {
                NASDirListActivity.this.mXQProgressDialog.dismiss();
                if (list != null) {
                    List unused = NASDirListActivity.this.mData = list;
                }
                NASDirListActivity.this.mHandler.post(new Runnable() {
                    public void run() {
                        if (NASDirListActivity.this.mData.size() == 0) {
                            NASDirListActivity.this.showEmptyView();
                        } else {
                            NASDirListActivity.this.showContentView();
                        }
                    }
                });
            }

            public void onFailure(int i, String str) {
                NASDirListActivity.this.mXQProgressDialog.dismiss();
                ToastUtil.a((Context) NASDirListActivity.this, (int) R.string.retrieve_data_fail);
                NASDirListActivity.this.showErrorView();
            }
        });
    }

    /* access modifiers changed from: private */
    public void showErrorView() {
        this.mEmptyView.setVisibility(0);
        this.mIcon.setImageDrawable(getResources().getDrawable(R.drawable.camera_file_loading_fail));
        ((TextView) this.mEmptyView.findViewById(R.id.white_empty_text)).setText("" + getString(R.string.smb_retrieve_share_directory_fail));
        this.mListView.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void showEmptyView() {
        this.mEmptyView.setVisibility(0);
        this.mIcon.setImageDrawable(getResources().getDrawable(R.drawable.camera_smb_empty));
        ((TextView) this.mEmptyView.findViewById(R.id.white_empty_text)).setText("" + getString(R.string.smb_no_share_directory));
        this.mListView.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void showContentView() {
        this.mEmptyView.setVisibility(8);
        this.mListView.setVisibility(0);
        this.mAdapter.notifyDataSetChanged();
    }

    private class SimpleAdapter extends BaseAdapter {
        public long getItemId(int i) {
            return 0;
        }

        private SimpleAdapter() {
        }

        public int getCount() {
            return NASDirListActivity.this.mData.size();
        }

        public Object getItem(int i) {
            if (i < 0 || i >= NASDirListActivity.this.mData.size()) {
                return null;
            }
            return NASDirListActivity.this.mData.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(NASDirListActivity.this).inflate(R.layout.camera_smb_dir_list_item, viewGroup, false);
                viewHolder = new ViewHolder();
                ImageView unused = viewHolder.icon = (ImageView) view.findViewById(R.id.device_icon);
                TextView unused2 = viewHolder.name = (TextView) view.findViewById(R.id.smb_name);
                View unused3 = viewHolder.select = view.findViewById(R.id.select_icon);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            final NASNode nASNode = (NASNode) NASDirListActivity.this.mData.get(i);
            String b = nASNode.b();
            viewHolder.name.setText(b);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    NASDirListActivity.this.setNewDir(nASNode);
                }
            });
            if (TextUtils.equals(b, NASDirListActivity.this.mCurrentNode.b())) {
                viewHolder.select.setVisibility(0);
            } else {
                viewHolder.select.setVisibility(4);
            }
            return view;
        }
    }

    private class ViewHolder {
        /* access modifiers changed from: private */
        public ImageView icon;
        /* access modifiers changed from: private */
        public TextView name;
        /* access modifiers changed from: private */
        public View select;

        private ViewHolder() {
        }
    }

    /* access modifiers changed from: private */
    public void setNewDir(final NASNode nASNode) {
        if (TextUtils.isEmpty(nASNode.b())) {
            ToastUtil.a((Context) this, (int) R.string.smb_tip_empty_dir);
        } else if (!TextUtils.equals(nASNode.b(), this.mCurrentNode.b())) {
            if (TextUtils.isEmpty(this.mCurrentNode.b())) {
                this.mXQProgressDialog.show();
                changeNASStorageDir(nASNode);
                return;
            }
            MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
            this.titleBarReturn.setTag(0);
            builder.a((int) R.string.smb_dialog_change_dir_type_title).a((CharSequence[]) new String[]{getString(R.string.smb_dialog_change_dir_type_1), getString(R.string.smb_dialog_change_dir_type_3)}, 0, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    NASDirListActivity.this.titleBarReturn.setTag(Integer.valueOf(i));
                }
            }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    NASDirListActivity.this.mXQProgressDialog.show();
                    Object tag = NASDirListActivity.this.titleBarReturn.getTag();
                    if (tag != null && (tag instanceof Integer)) {
                        if (((Integer) tag).intValue() == 1) {
                            NASDirListActivity.this.deleteOldVideoWithChangeDir(nASNode);
                        } else {
                            NASDirListActivity.this.changeNASStorageDir(nASNode);
                        }
                    }
                }
            });
            builder.d();
        }
    }

    /* access modifiers changed from: private */
    public void deleteOldVideoWithChangeDir(final NASNode nASNode) {
        if (this.mCameraDevice != null) {
            this.mCameraDevice.l().d(new Callback<Object>() {
                public void onSuccess(Object obj) {
                    if (!NASDirListActivity.this.isFinishing() && nASNode != null) {
                        NASDirListActivity.this.changeNASStorageDir(nASNode);
                    }
                }

                public void onFailure(int i, String str) {
                    if (!NASDirListActivity.this.isFinishing()) {
                        NASDirListActivity.this.mXQProgressDialog.dismiss();
                        ToastUtil.a((Context) NASDirListActivity.this, (int) R.string.smb_tip_set_fail);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void changeNASStorageDir(final NASNode nASNode) {
        NASServer c = nASNode.c();
        c.c(nASNode.a());
        this.mCurrentInfo.a(c);
        this.mCameraDevice.l().a(this.mCurrentInfo, (Callback<Object>) new Callback<Object>() {
            public void onSuccess(Object obj) {
                if (!NASDirListActivity.this.isFinishing()) {
                    NASDirListActivity.this.setResult(-1);
                    NASDirListActivity.this.startPolling(nASNode.a());
                }
            }

            public void onFailure(int i, String str) {
                if (!NASDirListActivity.this.isFinishing()) {
                    NASDirListActivity.this.mXQProgressDialog.dismiss();
                    ToastUtil.a((Context) NASDirListActivity.this, (int) R.string.smb_tip_set_fail);
                }
            }
        });
    }

    public void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacksAndMessages((Object) null);
    }

    public boolean isValid() {
        if (isFinishing()) {
            return false;
        }
        if (Build.VERSION.SDK_INT < 17 || !isDestroyed()) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void startPolling(final String str) {
        this.mXQProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                NASDirListActivity.this.mHandler.removeCallbacksAndMessages((Object) null);
            }
        });
        this.mPollCnt = 5;
        this.mHandler.postDelayed(new Runnable() {
            public void run() {
                if (NASDirListActivity.this.isValid()) {
                    NASDirListActivity.access$1810(NASDirListActivity.this);
                    if (NASDirListActivity.this.mPollCnt < 0) {
                        NASDirListActivity.this.mXQProgressDialog.dismiss();
                        ToastUtil.a((Context) NASDirListActivity.this, (int) R.string.smb_tip_set_fail);
                        return;
                    }
                    NASDirListActivity.this.mCameraDevice.l().b(new Callback<NASInfo>() {
                        public void onSuccess(NASInfo nASInfo) {
                            if (nASInfo == null) {
                                NASDirListActivity.this.mHandler.postDelayed(this, 2000);
                            } else if (TextUtils.equals(str, nASInfo.b())) {
                                NASDirListActivity.this.mHandler.post(new Runnable() {
                                    public void run() {
                                        NASDirListActivity.this.mXQProgressDialog.dismiss();
                                        ToastUtil.a((Context) NASDirListActivity.this, (int) R.string.smb_tip_set_success);
                                        if (TextUtils.isEmpty(NASDirListActivity.this.mCurrentNode.b())) {
                                            LocalBroadcastManager.getInstance(NASDirListActivity.this).sendBroadcast(new Intent("go_smbinfo_clear_top_activity"));
                                            Intent intent = new Intent();
                                            intent.setClass(NASDirListActivity.this, NASInfoActivity.class);
                                            NASDirListActivity.this.startActivity(intent);
                                        }
                                        NASDirListActivity.this.finish();
                                    }
                                });
                            } else {
                                NASDirListActivity.this.mHandler.postDelayed(this, 2000);
                            }
                        }

                        public void onFailure(int i, String str) {
                            if (NASDirListActivity.this.isValid()) {
                                NASDirListActivity.this.mHandler.postDelayed(this, 2000);
                            }
                        }
                    });
                }
            }
        }, 2000);
    }
}
