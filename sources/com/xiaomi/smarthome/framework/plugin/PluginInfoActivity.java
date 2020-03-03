package com.xiaomi.smarthome.framework.plugin;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginPackageInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import java.util.ArrayList;
import java.util.List;

public class PluginInfoActivity extends BaseActivity {
    public static final int PLUGIN_DEVICE = 1;
    public static final int PLUGIN_PACKAGE_DOWNLOADED = 3;
    public static final int PLUGIN_PACKAGE_INSTALLED = 2;
    DeviceAdapter mDeviceAdapter;
    DownloadedAdapter mDownloadedAdapter;
    List<PluginPackageInfo> mDownloadedPackageInfoList = new ArrayList();
    InstalledAdapter mInstalledAdapter;
    List<PluginPackageInfo> mInstalledPackageInfoList = new ArrayList();
    List<PluginRecord> mInstalledPluginInfoList = new ArrayList();
    ListView mListView;
    int mType;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mType = getIntent().getIntExtra("type", 1);
        setContentView(R.layout.plugin_info_activity);
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        textView.setText(R.string.developer_plugin_device_info);
        for (PluginRecord next : CoreApi.a().O()) {
            if (next.l()) {
                this.mInstalledPluginInfoList.add(next);
            }
        }
        this.mInstalledPackageInfoList = CoreApi.a().Q();
        this.mDownloadedPackageInfoList = CoreApi.a().R();
        this.mDeviceAdapter = new DeviceAdapter();
        this.mInstalledAdapter = new InstalledAdapter();
        this.mDownloadedAdapter = new DownloadedAdapter();
        this.mListView = (ListView) findViewById(R.id.plugin_info_list);
        switch (this.mType) {
            case 1:
                textView.setText(R.string.developer_plugin_device_info);
                this.mListView.setAdapter(this.mDeviceAdapter);
                return;
            case 2:
                textView.setText(R.string.developer_plugin_package_info_installed);
                this.mListView.setAdapter(this.mInstalledAdapter);
                return;
            case 3:
                textView.setText(R.string.developer_plugin_package_info_downloaded);
                this.mListView.setAdapter(this.mDownloadedAdapter);
                return;
            default:
                return;
        }
    }

    class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f17136a;

        ViewHolder() {
        }
    }

    class DeviceAdapter extends BaseAdapter {
        public long getItemId(int i) {
            return (long) i;
        }

        DeviceAdapter() {
        }

        public int getCount() {
            return PluginInfoActivity.this.mInstalledPluginInfoList.size();
        }

        public Object getItem(int i) {
            return PluginInfoActivity.this.mInstalledPluginInfoList.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            PluginRecord pluginRecord = PluginInfoActivity.this.mInstalledPluginInfoList.get(i);
            if (view == null) {
                view = PluginInfoActivity.this.getLayoutInflater().inflate(R.layout.plugin_info_item, (ViewGroup) null);
                viewHolder = new ViewHolder();
                viewHolder.f17136a = (TextView) view.findViewById(R.id.text);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
                viewHolder.f17136a.setText("");
            }
            if (i % 2 == 0) {
                viewHolder.f17136a.setBackgroundColor(Color.parseColor("#EFEFEF"));
            }
            StringBuilder sb = new StringBuilder();
            PluginDeviceInfo c = pluginRecord.c();
            sb.append("Model: " + c.c());
            sb.append("\n");
            sb.append("Name: " + c.k());
            sb.append("\n");
            sb.append("MinAppVersion: " + c.j());
            sb.append("\n\n");
            if (pluginRecord.l()) {
                PluginPackageInfo h = pluginRecord.h();
                sb.append("---------- Installed ----------");
                sb.append("\n");
                sb.append("VersionCode: " + h.g());
                sb.append("\n");
                sb.append("PluginId: " + h.a());
                sb.append("\n");
                sb.append("PackageId: " + h.b());
                sb.append("\n");
                sb.append("PackageName: " + h.h());
                sb.append("\n");
                sb.append("PackageType: " + h.i());
                sb.append("\n");
                sb.append("MinApiLevel: " + h.j());
                sb.append("\n");
                sb.append("Platform: " + h.l());
                sb.append("\n");
                sb.append("ModelList: " + h.m().toString());
            }
            viewHolder.f17136a.setText(sb.toString());
            return view;
        }
    }

    class InstalledAdapter extends BaseAdapter {
        public long getItemId(int i) {
            return (long) i;
        }

        InstalledAdapter() {
        }

        public int getCount() {
            return PluginInfoActivity.this.mInstalledPackageInfoList.size();
        }

        public Object getItem(int i) {
            return PluginInfoActivity.this.mInstalledPackageInfoList.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            PluginPackageInfo pluginPackageInfo = PluginInfoActivity.this.mInstalledPackageInfoList.get(i);
            if (view == null) {
                view = PluginInfoActivity.this.getLayoutInflater().inflate(R.layout.plugin_info_item, (ViewGroup) null);
                viewHolder = new ViewHolder();
                viewHolder.f17136a = (TextView) view.findViewById(R.id.text);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
                viewHolder.f17136a.setText("");
            }
            if (i % 2 == 0) {
                viewHolder.f17136a.setBackgroundColor(Color.parseColor("#EFEFEF"));
            }
            StringBuilder sb = new StringBuilder();
            sb.append("VersionCode: " + pluginPackageInfo.g());
            sb.append("\n");
            sb.append("PluginId: " + pluginPackageInfo.a());
            sb.append("\n");
            sb.append("PackageId: " + pluginPackageInfo.b());
            sb.append("\n");
            sb.append("PackageName: " + pluginPackageInfo.h());
            sb.append("\n");
            sb.append("PackageType: " + pluginPackageInfo.i());
            sb.append("\n");
            sb.append("MinApiLevel: " + pluginPackageInfo.j());
            sb.append("\n");
            sb.append("Platform: " + pluginPackageInfo.l());
            sb.append("\n");
            sb.append("ModelList: " + pluginPackageInfo.m().toString());
            viewHolder.f17136a.setText(sb.toString());
            return view;
        }
    }

    class DownloadedAdapter extends BaseAdapter {
        public long getItemId(int i) {
            return (long) i;
        }

        DownloadedAdapter() {
        }

        public int getCount() {
            return PluginInfoActivity.this.mDownloadedPackageInfoList.size();
        }

        public Object getItem(int i) {
            return PluginInfoActivity.this.mDownloadedPackageInfoList.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            PluginPackageInfo pluginPackageInfo = PluginInfoActivity.this.mDownloadedPackageInfoList.get(i);
            if (view == null) {
                view = PluginInfoActivity.this.getLayoutInflater().inflate(R.layout.plugin_info_item, (ViewGroup) null);
                viewHolder = new ViewHolder();
                viewHolder.f17136a = (TextView) view.findViewById(R.id.text);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
                viewHolder.f17136a.setText("");
            }
            if (i % 2 == 0) {
                viewHolder.f17136a.setBackgroundColor(Color.parseColor("#EFEFEF"));
            }
            StringBuilder sb = new StringBuilder();
            sb.append("VersionCode: " + pluginPackageInfo.g());
            sb.append("\n");
            sb.append("PluginId: " + pluginPackageInfo.a());
            sb.append("\n");
            sb.append("PackageId: " + pluginPackageInfo.b());
            sb.append("\n");
            sb.append("PackageName: " + pluginPackageInfo.h());
            sb.append("\n");
            sb.append("PackageType: " + pluginPackageInfo.i());
            sb.append("\n");
            sb.append("MinApiLevel: " + pluginPackageInfo.j());
            sb.append("\n");
            sb.append("Platform: " + pluginPackageInfo.l());
            sb.append("\n");
            sb.append("ModelList: " + pluginPackageInfo.m().toString());
            viewHolder.f17136a.setText(sb.toString());
            return view;
        }
    }
}
