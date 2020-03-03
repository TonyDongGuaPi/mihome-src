package com.mi.global.shop.model;

import com.google.gson.annotations.SerializedName;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import okio.Buffer;

public class UserCentralListData {
    public GroupInfo groupInfo;
    public ArrayList<UserCentralData> itemInfo = new ArrayList<>();

    public static UserCentralListData decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static UserCentralListData decode(ProtoReader protoReader) throws IOException {
        UserCentralListData userCentralListData = new UserCentralListData();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        userCentralListData.groupInfo = GroupInfo.decode(protoReader);
                        break;
                    case 2:
                        userCentralListData.itemInfo.add(UserCentralData.decode(protoReader));
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return userCentralListData;
            }
        }
    }

    static class GroupInfo {
        @SerializedName("name")
        public String name;

        GroupInfo() {
        }

        public static GroupInfo decode(byte[] bArr) throws IOException {
            return decode(new ProtoReader(new Buffer().write(bArr)));
        }

        public static GroupInfo decode(ProtoReader protoReader) throws IOException {
            GroupInfo groupInfo = new GroupInfo();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag == -1) {
                    protoReader.endMessage(beginMessage);
                    return groupInfo;
                } else if (nextTag != 1) {
                    protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                } else {
                    groupInfo.name = ProtoAdapter.STRING.decode(protoReader);
                }
            }
        }
    }
}
