package com.mi.global.shop.newmodel.home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import okio.Buffer;

public class ExtraScreen {
    @SerializedName("completePic")
    @Expose
    public String completePic;
    @SerializedName("directURL")
    @Expose
    public String directURL;
    @SerializedName("hintPic")
    @Expose
    public String hintPic;
    @SerializedName("partialPic")
    @Expose
    public String partialPic;
    @SerializedName("placeHolder")
    @Expose
    public String placeHolder;

    public static ExtraScreen decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static ExtraScreen decode(ProtoReader protoReader) throws IOException {
        ExtraScreen extraScreen = new ExtraScreen();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        extraScreen.hintPic = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        extraScreen.partialPic = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        extraScreen.completePic = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 4:
                        extraScreen.placeHolder = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 5:
                        extraScreen.directURL = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return extraScreen;
            }
        }
    }
}
