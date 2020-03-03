package com.xiaomi.smarthome.camera.activity.nas;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mijia.camera.nas.NASServer;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.activity.CameraBaseActivity;
import com.xiaomi.smarthome.camera.view.CameraPullDownRefreshListView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import java.util.ArrayList;
import java.util.List;

public class NASDiscoveryActivity extends CameraBaseActivity {
    private BroadcastReceiver finishReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            NASDiscoveryActivity.this.finish();
        }
    };
    private ImageView ivEmptyIcon;
    private SimpleAdapter mAdapter;
    /* access modifiers changed from: private */
    public List<NASServer> mData = new ArrayList();
    private View mEmptyView;
    /* access modifiers changed from: private */
    public Handler mHandler = new Handler();
    private CameraPullDownRefreshListView mListView;
    /* access modifiers changed from: private */
    public XQProgressDialog mXQProgressDialog;

    public void doCreate(Bundle bundle) {
        super.doCreate(bundle);
        setContentView(R.layout.camera_activity_device_smb_discovery_list);
        initView();
        loadNASServerList();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.finishReceiver, new IntentFilter("go_smbinfo_clear_top_activity"));
    }

    public void onDestroy() {
        super.onDestroy();
        if (this.mXQProgressDialog != null && this.mXQProgressDialog.isShowing()) {
            this.mXQProgressDialog.dismiss();
        }
        try {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.finishReceiver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        ((TextView) findViewById(R.id.title_bar_title)).setText(R.string.smb_storage);
        findViewById(R.id.title_bar_return).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NASDiscoveryActivity.this.finish();
            }
        });
        findViewById(R.id.title_bar_more).setVisibility(8);
        this.mListView = (CameraPullDownRefreshListView) findViewById(R.id.list);
        this.mAdapter = new SimpleAdapter();
        this.mListView.setAdapter(this.mAdapter);
        this.mEmptyView = findViewById(R.id.white_empty_view);
        this.ivEmptyIcon = (ImageView) findViewById(R.id.empty_icon);
        this.ivEmptyIcon.setImageResource(this.isV4 ? R.drawable.camera_v4_file_loading_fail : R.drawable.camera_file_loading_fail);
        this.mEmptyView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                NASDiscoveryActivity.this.loadNASServerList();
            }
        });
        initProgressDialog();
        View inflate = LayoutInflater.from(this).inflate(R.layout.camera_smb_discovery_list_header, this.mListView, false);
        View inflate2 = LayoutInflater.from(this).inflate(R.layout.camera_smb_discovery_list_footer, this.mListView, false);
        inflate2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.mListView.addHeaderView(inflate);
        this.mListView.addFooterView(inflate2);
    }

    private void initProgressDialog() {
        this.mXQProgressDialog = new XQProgressDialog(this);
        this.mXQProgressDialog.setMessage(getString(R.string.smb_waiting));
        this.mXQProgressDialog.setCancelable(true);
    }

    /* access modifiers changed from: private */
    public void loadNASServerList() {
        this.mXQProgressDialog.show();
        this.mCameraDevice.l().a(new Callback<List<NASServer>>() {
            public void onSuccess(List<NASServer> list) {
                NASDiscoveryActivity.this.mXQProgressDialog.dismiss();
                if (list != null) {
                    List unused = NASDiscoveryActivity.this.mData = list;
                }
                NASDiscoveryActivity.this.mHandler.post(new Runnable() {
                    public void run() {
                        if (NASDiscoveryActivity.this.mData.size() == 0) {
                            NASDiscoveryActivity.this.showEmptyView();
                        } else {
                            NASDiscoveryActivity.this.showContentView();
                        }
                    }
                });
            }

            public void onFailure(int i, String str) {
                NASDiscoveryActivity.this.mXQProgressDialog.dismiss();
            }
        });
    }

    /* access modifiers changed from: private */
    public void showEmptyView() {
        this.mEmptyView.setVisibility(0);
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
            return NASDiscoveryActivity.this.mData.size();
        }

        public Object getItem(int i) {
            if (i < 0 || i >= NASDiscoveryActivity.this.mData.size()) {
                return null;
            }
            return NASDiscoveryActivity.this.mData.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(NASDiscoveryActivity.this).inflate(R.layout.camera_smb_discovery_list_item, viewGroup, false);
                viewHolder = new ViewHolder();
                ImageView unused = viewHolder.icon = (ImageView) view.findViewById(R.id.device_icon);
                TextView unused2 = viewHolder.name = (TextView) view.findViewById(R.id.smb_name);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            final NASServer nASServer = (NASServer) NASDiscoveryActivity.this.mData.get(i);
            viewHolder.name.setText(nASServer.a());
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(NASDiscoveryActivity.this, NASAddActivity.class);
                    intent.putExtra("data", nASServer);
                    NASDiscoveryActivity.this.startActivity(intent);
                }
            });
            return view;
        }
    }

    private class ViewHolder {
        /* access modifiers changed from: private */
        public ImageView icon;
        /* access modifiers changed from: private */
        public TextView name;

        private ViewHolder() {
        }
    }
}
