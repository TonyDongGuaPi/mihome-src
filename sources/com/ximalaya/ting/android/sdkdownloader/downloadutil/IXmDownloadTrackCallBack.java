package com.ximalaya.ting.android.sdkdownloader.downloadutil;

import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.sdkdownloader.task.Callback;

public interface IXmDownloadTrackCallBack {
    void a(Track track, long j, long j2);

    void a(Track track, Callback.CancelledException cancelledException);

    void a(Track track, Throwable th);

    void b();

    void b(Track track);

    void c(Track track);

    void d(Track track);
}
