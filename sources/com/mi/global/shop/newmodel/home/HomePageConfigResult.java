package com.mi.global.shop.newmodel.home;

import com.mi.global.shop.newmodel.BaseResult;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.xiaomi.shopviews.model.item.PageDataBean;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okio.Buffer;

public class HomePageConfigResult extends BaseResult {
    public DataBean data;

    public static HomePageConfigResult decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static HomePageConfigResult decode(ProtoReader protoReader) throws IOException {
        HomePageConfigResult homePageConfigResult = new HomePageConfigResult();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        homePageConfigResult.errno = ProtoAdapter.INT32.decode(protoReader).intValue();
                        break;
                    case 2:
                        homePageConfigResult.errmsg = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        homePageConfigResult.data = DataBean.decode(protoReader);
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return homePageConfigResult;
            }
        }
    }

    public static class DataBean {
        public String ext_page_data_str;
        public String extended;
        public ExtraScreen extraScreen;
        public List<PageDataBean> page_data = new ArrayList();

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
                            dataBean.extended = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 2:
                            dataBean.page_data.add(PageDataBean.a(protoReader));
                            break;
                        case 3:
                            dataBean.ext_page_data_str = ProtoAdapter.STRING.decode(protoReader);
                            break;
                        case 4:
                            dataBean.extraScreen = ExtraScreen.decode(protoReader);
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
