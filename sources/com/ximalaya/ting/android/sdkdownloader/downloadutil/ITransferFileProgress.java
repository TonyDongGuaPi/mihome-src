package com.ximalaya.ting.android.sdkdownloader.downloadutil;

import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.sdkdownloader.exception.TransferSavePathException;

public interface ITransferFileProgress extends IDoSomethingProgress<TransferSavePathException> {
    void a(int i, int i2, Track track);
}
