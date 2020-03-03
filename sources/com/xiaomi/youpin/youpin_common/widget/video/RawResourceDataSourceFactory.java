package com.xiaomi.youpin.youpin_common.widget.video;

import android.content.Context;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;

class RawResourceDataSourceFactory implements DataSource.Factory {

    /* renamed from: a  reason: collision with root package name */
    private final Context f23833a;

    RawResourceDataSourceFactory(Context context) {
        this.f23833a = context;
    }

    public DataSource createDataSource() {
        return new RawResourceDataSource(this.f23833a, (TransferListener) null);
    }
}
