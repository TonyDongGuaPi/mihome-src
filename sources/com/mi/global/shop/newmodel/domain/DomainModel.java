package com.mi.global.shop.newmodel.domain;

import com.google.gson.annotations.SerializedName;
import com.mi.global.shop.newmodel.BaseResult;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import okio.Buffer;

public class DomainModel extends BaseResult {
    @SerializedName("cookieDomain")
    public String cookieDomain;
    @SerializedName("dns")
    public ArrayList<DomainHttpDnsModel> domainDnsModels = new ArrayList<>();
    @SerializedName("launchTime")
    public long launchTime;
    @SerializedName("local")
    public String local;
    @SerializedName("sid")
    public String sid;

    public static DomainModel decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static DomainModel decode(ProtoReader protoReader) throws IOException {
        DomainModel domainModel = new DomainModel();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        domainModel.local = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        domainModel.sid = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        domainModel.domainDnsModels.add(DomainHttpDnsModel.decode(protoReader));
                        break;
                    case 4:
                        domainModel.cookieDomain = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 5:
                        domainModel.launchTime = ProtoAdapter.INT64.decode(protoReader).longValue();
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return domainModel;
            }
        }
    }

    public String replaceMatchedDomain(String str) {
        Iterator<DomainHttpDnsModel> it = this.domainDnsModels.iterator();
        while (it.hasNext()) {
            DomainHttpDnsModel next = it.next();
            if (str.contains(next.oldHostname)) {
                return str.replaceFirst(next.oldHostname, next.hostname);
            }
        }
        return "";
    }
}
