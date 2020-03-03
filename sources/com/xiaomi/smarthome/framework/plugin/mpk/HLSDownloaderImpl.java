package com.xiaomi.smarthome.framework.plugin.mpk;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.smarthome.camera.HLSDownloader;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.miio.camera.cloudstorage.Hls2Mp4;
import com.xiaomi.smarthome.miio.camera.cloudstorage.utils.CloudVideoNetUtils;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;

public class HLSDownloaderImpl implements HLSDownloader {
    private static final String TAG = "HLSDownloaderImpl";
    /* access modifiers changed from: private */
    public Hls2Mp4 hls2Mp4;
    /* access modifiers changed from: private */
    public HLSDownloader.OnInfoListenerP onInfoListener;

    public void start(Context context, String str, String str2) {
        if (context == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            LogUtil.b(TAG, "param(s) invalid(empty). m3u8Url:" + str + " mp4FilePath:" + str2);
            if (this.hls2Mp4 != null) {
                this.hls2Mp4.cancel();
                this.hls2Mp4 = null;
                return;
            }
            return;
        }
        if (this.hls2Mp4 == null) {
            this.hls2Mp4 = new Hls2Mp4();
            if (this.onInfoListener != null) {
                this.hls2Mp4.setInfoListener(new Hls2Mp4.OnInfoListener() {
                    public void onStart() {
                        if (HLSDownloaderImpl.this.onInfoListener != null) {
                            LogUtil.a(HLSDownloaderImpl.TAG, "onStart");
                            HLSDownloaderImpl.this.onInfoListener.onStart();
                        }
                    }

                    public void onComplete() {
                        if (HLSDownloaderImpl.this.onInfoListener != null) {
                            LogUtil.a(HLSDownloaderImpl.TAG, "onComplete");
                            HLSDownloaderImpl.this.onInfoListener.onComplete();
                        }
                        if (HLSDownloaderImpl.this.hls2Mp4 != null) {
                            Hls2Mp4 unused = HLSDownloaderImpl.this.hls2Mp4 = null;
                        }
                    }

                    public void onCancelled() {
                        if (HLSDownloaderImpl.this.onInfoListener != null) {
                            LogUtil.a(HLSDownloaderImpl.TAG, "onCancelled");
                            HLSDownloaderImpl.this.onInfoListener.onCancelled();
                        }
                        if (HLSDownloaderImpl.this.hls2Mp4 != null) {
                            Hls2Mp4 unused = HLSDownloaderImpl.this.hls2Mp4 = null;
                        }
                    }

                    public void onInfo(int i) {
                        if (HLSDownloaderImpl.this.onInfoListener != null) {
                            LogUtil.a(HLSDownloaderImpl.TAG, "onInfo:" + i);
                            HLSDownloaderImpl.this.onInfoListener.onInfo(i);
                        }
                    }

                    public void onError(int i) {
                        if (HLSDownloaderImpl.this.onInfoListener != null) {
                            LogUtil.a(HLSDownloaderImpl.TAG, "onError:" + i);
                            HLSDownloaderImpl.this.onInfoListener.onError(i);
                        }
                        if (HLSDownloaderImpl.this.hls2Mp4 != null) {
                            Hls2Mp4 unused = HLSDownloaderImpl.this.hls2Mp4 = null;
                        }
                    }

                    public void onProgress(int i) {
                        if (HLSDownloaderImpl.this.onInfoListener != null) {
                            LogUtil.a(HLSDownloaderImpl.TAG, "onProgress:" + i);
                            HLSDownloaderImpl.this.onInfoListener.onProgress(i);
                        }
                    }

                    public void onSize(int i) {
                        if (HLSDownloaderImpl.this.onInfoListener != null) {
                            HLSDownloaderImpl.this.onInfoListener.onSize(i);
                        }
                    }
                });
            }
        }
        MiServiceTokenInfo tokenInfo = CloudVideoNetUtils.getInstance().getTokenInfo();
        if (tokenInfo != null) {
            this.hls2Mp4.start(str, str2, "Cookie: yetAnotherServiceToken=" + tokenInfo.c);
            return;
        }
        this.hls2Mp4.start(str, str2);
    }

    public boolean isRunning() {
        if (this.hls2Mp4 != null) {
            return this.hls2Mp4.isRunning();
        }
        return false;
    }

    public void cancel() {
        if (this.hls2Mp4 != null) {
            this.hls2Mp4.cancel();
        }
    }

    public void setInfoListener(HLSDownloader.OnInfoListenerP onInfoListenerP) {
        if (onInfoListenerP != null) {
            this.onInfoListener = onInfoListenerP;
        }
    }

    public void removeInfoListener() {
        this.onInfoListener = null;
    }
}
