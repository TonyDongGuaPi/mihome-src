package com.google.android.exoplayer2.upstream;

import android.support.annotation.Nullable;
import com.google.android.exoplayer2.upstream.DataSource;

public final class FileDataSourceFactory implements DataSource.Factory {
    @Nullable
    private final TransferListener listener;

    public FileDataSourceFactory() {
        this((TransferListener) null);
    }

    public FileDataSourceFactory(@Nullable TransferListener transferListener) {
        this.listener = transferListener;
    }

    public DataSource createDataSource() {
        FileDataSource fileDataSource = new FileDataSource();
        if (this.listener != null) {
            fileDataSource.addTransferListener(this.listener);
        }
        return fileDataSource;
    }
}
