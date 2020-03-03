package com.mi.global.bbs.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.R;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.utils.DownloadPluginTask;
import com.mi.global.bbs.utils.GoogleTrackerUtil;
import com.mi.global.bbs.utils.Utils;
import com.mi.global.bbs.view.PercentageRing;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class DownloadPluginAcitvity extends BaseActivity {
    private static final String TAG = "DownloadPluginAcitvity";
    private String activityName;
    /* access modifiers changed from: private */
    public String downloadUrl;
    /* access modifiers changed from: private */
    public Handler handler;
    /* access modifiers changed from: private */
    public PercentageRing mPercentageRing;
    /* access modifiers changed from: private */
    public String md5 = null;
    /* access modifiers changed from: private */
    public volatile DownloadPluginTask task = null;
    View.OnClickListener titleBackListener = new View.OnClickListener() {
        public void onClick(View view) {
            DownloadPluginAcitvity.this.onBackPressed();
        }
    };
    private String url;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.bbs_download_plugin);
        setTitle((CharSequence) getString(R.string.bbs_loading));
        setTitleAndBack(getString(R.string.bbs_loading), this.titleBackListener);
        GoogleTrackerUtil.sendRecordPage(TAG);
        this.mPercentageRing = (PercentageRing) findViewById(R.id.percentage_ring);
        this.url = getIntent().getStringExtra("url");
        if (this.url == null) {
            finish();
            return;
        }
        List<NameValuePair> parse = URLEncodedUtils.parse(URI.create(this.url), "UTF-8");
        for (int i = 0; i < parse.size(); i++) {
            BasicNameValuePair basicNameValuePair = (BasicNameValuePair) parse.get(i);
            if ("activityName".equals(basicNameValuePair.getName())) {
                this.activityName = basicNameValuePair.getValue();
            } else if ("downloadUrl".equals(basicNameValuePair.getName())) {
                this.downloadUrl = basicNameValuePair.getValue();
            } else if ("md5".equals(basicNameValuePair.getName())) {
                this.md5 = basicNameValuePair.getValue();
            }
        }
        if (Utils.Preference.getBooleanPref(BBSApplication.getInstance(), this.url, false)) {
            Intent intent = new Intent(this, PluginLoadingActivity.class);
            intent.putExtra("acName", this.activityName);
            startActivity(intent);
            finish();
            return;
        }
        download();
    }

    private void download() {
        GoogleTrackerUtil.sendRecordEvent("home", "Plugin", "Download");
        String str = getExternalFilesDir((String) null) + "/micommunity/";
        if (!new File(str).exists()) {
            new File(str).mkdirs();
        }
        final String str2 = str + this.downloadUrl.substring(this.downloadUrl.lastIndexOf(47) + 1);
        this.handler = new Handler() {
            public void handleMessage(Message message) {
                if (message.what != 1) {
                    int i = message.what;
                    super.handleMessage(message);
                } else if (message.arg1 == 100) {
                    DownloadPluginAcitvity.this.mPercentageRing.setCurrentPercent(99.0f);
                } else {
                    DownloadPluginAcitvity.this.mPercentageRing.setCurrentPercent((float) message.arg1);
                }
            }
        };
        new Thread(new Runnable() {
            public void run() {
                try {
                    DownloadPluginTask unused = DownloadPluginAcitvity.this.task = new DownloadPluginTask(DownloadPluginAcitvity.this.handler);
                    if (DownloadPluginAcitvity.this.task.httpDownload(DownloadPluginAcitvity.this.downloadUrl, str2, DownloadPluginAcitvity.this.md5)) {
                        Message message = new Message();
                        message.what = 2;
                        DownloadPluginAcitvity.this.handler.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    DownloadPluginAcitvity.this.finish();
                }
            }
        }).start();
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    public void finish() {
        if (this.task != null) {
            this.task.cancel();
        }
        super.finish();
    }
}
