package com.mi.global.shop.feature.search.newmodel;

import com.google.gson.annotations.SerializedName;
import com.mi.global.shop.base.BaseResult;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import okio.Buffer;

public class SearchWordResult extends BaseResult {
    @SerializedName("data")
    public SearchData data;

    public static SearchWordResult decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static SearchWordResult decode(ProtoReader protoReader) throws IOException {
        SearchWordResult searchWordResult = new SearchWordResult();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        searchWordResult.errno = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        searchWordResult.errmsg = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        searchWordResult.data = SearchData.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return searchWordResult;
            }
        }
    }

    public static class SearchData {
        public ArrayList<DataBean> item;
        public ArrayList<String> list;

        public static SearchData decode(byte[] bArr) throws IOException {
            return decode(new ProtoReader(new Buffer().write(bArr)));
        }

        public static SearchData decode(ProtoReader protoReader) throws IOException {
            SearchData searchData = new SearchData();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 2:
                            searchData.item = new ArrayList<>();
                            searchData.item.add(DataBean.decode(protoReader));
                            break;
                        case 3:
                            searchData.list = new ArrayList<>();
                            searchData.list.add(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        default:
                            protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                            break;
                    }
                } else {
                    protoReader.endMessage(beginMessage);
                    return searchData;
                }
            }
        }
    }

    public static class DataBean {
        public String image;
        public String name;
        public String price;
        public String price_before;
        public String tag;

        public static DataBean decode(byte[] bArr) throws IOException {
            return decode(new ProtoReader(new Buffer().write(bArr)));
        }

        public static DataBean decode(ProtoReader protoReader) throws IOException {
            DataBean dataBean = new DataBean();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            dataBean.name = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 2:
                            dataBean.tag = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 3:
                            dataBean.image = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 4:
                            dataBean.price = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 5:
                            dataBean.price_before = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        default:
                            protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                            break;
                    }
                } else {
                    protoReader.endMessage(beginMessage);
                    return dataBean;
                }
            }
        }
    }
}
