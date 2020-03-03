package com.mi.global.shop.newmodel.domain;

import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import java.io.IOException;
import java.util.ArrayList;
import okio.Buffer;

public class DomainHttpDnsModel {
    public boolean enable;
    public String hostname;
    public ArrayList<String> ips = new ArrayList<>();
    public String oldHostname;
    public long ttl;

    public static DomainHttpDnsModel decode(byte[] bArr) throws IOException {
        return decode(new ProtoReader(new Buffer().write(bArr)));
    }

    public static DomainHttpDnsModel decode(ProtoReader protoReader) throws IOException {
        DomainHttpDnsModel domainHttpDnsModel = new DomainHttpDnsModel();
        long beginMessage = protoReader.beginMessage();
        while (true) {
            int nextTag = protoReader.nextTag();
            if (nextTag != -1) {
                switch (nextTag) {
                    case 1:
                        domainHttpDnsModel.hostname = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 2:
                        domainHttpDnsModel.oldHostname = ProtoAdapter.STRING.decode(protoReader);
                        break;
                    case 3:
                        domainHttpDnsModel.ips.add(ProtoAdapter.STRING.decode(protoReader));
                        break;
                    case 4:
                        domainHttpDnsModel.ttl = ProtoAdapter.INT64.decode(protoReader).longValue();
                        break;
                    case 5:
                        domainHttpDnsModel.enable = ProtoAdapter.BOOL.decode(protoReader).booleanValue();
                        break;
                    default:
                        protoReader.peekFieldEncoding().rawProtoAdapter().decode(protoReader);
                        break;
                }
            } else {
                protoReader.endMessage(beginMessage);
                return domainHttpDnsModel;
            }
        }
    }
}
