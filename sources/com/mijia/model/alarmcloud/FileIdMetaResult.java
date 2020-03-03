package com.mijia.model.alarmcloud;

import android.os.Parcel;
import android.os.Parcelable;
import com.taobao.weex.el.parse.Operators;
import java.util.List;

public class FileIdMetaResult implements Parcelable {
    public static final Parcelable.Creator<FileIdMetaResult> CREATOR = new Parcelable.Creator<FileIdMetaResult>() {
        public FileIdMetaResult createFromParcel(Parcel parcel) {
            return new FileIdMetaResult(parcel);
        }

        public FileIdMetaResult[] newArray(int i) {
            return new FileIdMetaResult[i];
        }
    };
    public boolean babyCrying;
    public List<FaceInfoMeta> faceInfoMetas;
    public String fileId;
    public int offset;

    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "FileIdMetaResult{babyCrying=" + this.babyCrying + ", fileId='" + this.fileId + Operators.SINGLE_QUOTE + ", offset=" + this.offset + ", faceInfoMetas=" + this.faceInfoMetas + Operators.BLOCK_END;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte(this.babyCrying ? (byte) 1 : 0);
        parcel.writeString(this.fileId);
        parcel.writeTypedList(this.faceInfoMetas);
    }

    public FileIdMetaResult() {
    }

    protected FileIdMetaResult(Parcel parcel) {
        this.babyCrying = parcel.readByte() != 0;
        this.fileId = parcel.readString();
        this.faceInfoMetas = parcel.createTypedArrayList(FaceInfoMeta.CREATOR);
    }
}
