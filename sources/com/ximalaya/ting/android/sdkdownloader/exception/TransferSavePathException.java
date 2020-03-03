package com.ximalaya.ting.android.sdkdownloader.exception;

import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.model.track.Track;

public class TransferSavePathException extends BaseRuntimeException {
    private static int BASE_CODE;
    public static final int CODE_DB_ERROR;
    public static final int CODE_FILE_TRANSFER_ERROR;
    public static final int CODE_SAVE_PATH_NO_SAVE;
    private int code;
    private Track track;

    static {
        int i = BASE_CODE + 1;
        BASE_CODE = i;
        CODE_SAVE_PATH_NO_SAVE = i;
        int i2 = BASE_CODE + 1;
        BASE_CODE = i2;
        CODE_DB_ERROR = i2;
        int i3 = BASE_CODE + 1;
        BASE_CODE = i3;
        CODE_FILE_TRANSFER_ERROR = i3;
    }

    public TransferSavePathException(int i, String str) {
        super(str);
        this.code = i;
    }

    public TransferSavePathException(int i, String str, Track track2) {
        super(str);
        this.code = i;
        this.track = track2;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public int getCode() {
        return this.code;
    }

    public Track getTrack() {
        return this.track;
    }

    public void setTrack(Track track2) {
        this.track = track2;
    }

    public String toString() {
        return "TransferSavePathException{code=" + this.code + ", track=" + this.track + Operators.BLOCK_END;
    }
}
