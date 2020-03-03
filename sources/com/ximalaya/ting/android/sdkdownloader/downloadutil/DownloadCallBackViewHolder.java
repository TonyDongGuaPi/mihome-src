package com.ximalaya.ting.android.sdkdownloader.downloadutil;

import com.ximalaya.ting.android.opensdk.model.track.Track;

public abstract class DownloadCallBackViewHolder implements IXmDownloadTrackCallBack {

    /* renamed from: a  reason: collision with root package name */
    private Track f2344a;

    public DownloadCallBackViewHolder(Track track) {
        this.f2344a = track;
    }

    public Track a() {
        return this.f2344a;
    }

    public void a(Track track) {
        this.f2344a = track;
    }

    public final boolean equals(Object obj) {
        if (obj == null || !(obj instanceof DownloadCallBackViewHolder)) {
            return false;
        }
        DownloadCallBackViewHolder downloadCallBackViewHolder = (DownloadCallBackViewHolder) obj;
        if (downloadCallBackViewHolder.a() == null) {
            return false;
        }
        return downloadCallBackViewHolder.a().equals(this.f2344a);
    }

    public final int hashCode() {
        if (this.f2344a != null) {
            return this.f2344a.hashCode();
        }
        return super.hashCode();
    }
}
