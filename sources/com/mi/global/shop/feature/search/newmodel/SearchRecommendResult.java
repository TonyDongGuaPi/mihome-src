package com.mi.global.shop.feature.search.newmodel;

import com.google.gson.annotations.SerializedName;
import com.mi.global.shop.base.BaseResult;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import okio.Buffer;

public class SearchRecommendResult extends BaseResult {
    @SerializedName("data")
    public Data data = new Data();

    public static SearchRecommendResult decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static SearchRecommendResult decode(ProtoReader protoReader) throws IOException {
        SearchRecommendResult searchRecommendResult = new SearchRecommendResult();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        searchRecommendResult.errno = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        searchRecommendResult.errmsg = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        searchRecommendResult.data = Data.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return searchRecommendResult;
            }
        }
    }

    public static class Recommend {
        @SerializedName("link")
        public String link;
        @SerializedName("name")
        public String name;

        public static Recommend decode(byte[] bArr) throws IOException {
            return decode(new ProtoReader(new Buffer().write(bArr)));
        }

        public static Recommend decode(ProtoReader protoReader) throws IOException {
            Recommend recommend = new Recommend();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            recommend.name = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 2:
                            recommend.link = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        default:
                            protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                            break;
                    }
                } else {
                    protoReader.endMessage(beginMessage);
                    return recommend;
                }
            }
        }
    }

    public static class Data {
        @SerializedName("recommend")
        public ArrayList<Recommend> recommend = new ArrayList<>();

        public static Data decode(byte[] bArr) throws IOException {
            return decode(new ProtoReader(new Buffer().write(bArr)));
        }

        public static Data decode(ProtoReader protoReader) throws IOException {
            Data data = new Data();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag == -1) {
                    protoReader.endMessage(beginMessage);
                    return data;
                } else if (nextTag != 1) {
                    protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                } else {
                    data.recommend.add(Recommend.decode(protoReader));
                }
            }
        }
    }
}
