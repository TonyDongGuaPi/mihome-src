package com.xiaomi.smarthome.framework.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.serverenv.ServerEnvHelper;
import com.xiaomi.smarthome.framework.page.serverenv.ServerEnvInfo;
import com.xiaomi.smarthome.notishortcut.SmartNotiApi;
import java.util.ArrayList;
import java.util.List;

public class SelectServerEnvActivity extends BaseActivity {
    SimpleAdapter mAdapter = new SimpleAdapter();
    Context mContext;
    List<ServerEnvInfo> mServerEnvInfoList = new ArrayList();
    ServerEnvInfo previewServerInfo = new ServerEnvInfo("preview");
    ServerEnvInfo releaseServerInfo = new ServerEnvInfo("release");

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        setContentView(R.layout.select_server_env_activity);
        findViewById(R.id.empty).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SelectServerEnvActivity.this.b();
            }
        });
        ((ListView) findViewById(R.id.listview)).setAdapter(this.mAdapter);
        this.mServerEnvInfoList.add(this.releaseServerInfo);
        this.mServerEnvInfoList.add(this.previewServerInfo);
        a();
    }

    public void onBackPressed() {
        b();
        super.onBackPressed();
    }

    private void a() {
        CoreApi.a().a((Context) this, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                SelectServerEnvActivity.this.mAdapter.notifyDataSetChanged();
            }
        });
        this.mAdapter.notifyDataSetChanged();
    }

    /* access modifiers changed from: private */
    public void a(final ServerEnvInfo serverEnvInfo, AsyncCallback<Void, Error> asyncCallback) {
        if (TextUtils.isEmpty(serverEnvInfo.f17025a)) {
            b();
            return;
        }
        CoreApi.a().a(this.mContext, (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                CoreApi.a().b(serverEnvInfo.f17025a, (AsyncCallback<Void, Error>) null);
            }
        });
        SmartNotiApi.a((Context) this).c(serverEnvInfo.f17025a);
        finish();
        overridePendingTransition(0, 0);
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this.mContext);
        Intent intent = new Intent(ServerEnvHelper.f17023a);
        intent.putExtra("param_key", 1);
        instance.sendBroadcast(intent);
    }

    /* access modifiers changed from: private */
    public void b() {
        finish();
        overridePendingTransition(0, 0);
        LocalBroadcastManager instance = LocalBroadcastManager.getInstance(this.mContext);
        Intent intent = new Intent(ServerEnvHelper.f17023a);
        intent.putExtra("param_key", 2);
        instance.sendBroadcast(intent);
    }

    class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f16914a;

        ViewHolder() {
        }
    }

    class SimpleAdapter extends BaseAdapter {
        public long getItemId(int i) {
            return (long) i;
        }

        SimpleAdapter() {
        }

        public int getCount() {
            return SelectServerEnvActivity.this.mServerEnvInfoList.size();
        }

        public Object getItem(int i) {
            return SelectServerEnvActivity.this.mServerEnvInfoList.get(i);
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = SelectServerEnvActivity.this.getLayoutInflater().inflate(R.layout.international_item, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.f16914a = (TextView) view.findViewById(R.id.text);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
                viewHolder.f16914a.setText("");
            }
            viewHolder.f16914a.setText(SelectServerEnvActivity.this.mServerEnvInfoList.get(i).f17025a);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    SelectServerEnvActivity.this.a(SelectServerEnvActivity.this.mServerEnvInfoList.get(i), (AsyncCallback<Void, Error>) null);
                }
            });
            return view;
        }
    }
}
