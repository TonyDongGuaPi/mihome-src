package javax.jmdns.impl.constants;

import com.drew.metadata.exif.ExifDirectoryBase;
import com.xiaomi.smarthome.framework.openapi.ApiConst;
import com.xiaomi.stat.a.l;
import com.xiaomi.stat.d;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import java.util.logging.Logger;

public enum DNSRecordType {
    TYPE_IGNORE("ignore", 0),
    TYPE_A("a", 1),
    TYPE_NS("ns", 2),
    TYPE_MD(d.G, 3),
    TYPE_MF(d.X, 4),
    TYPE_CNAME("cname", 5),
    TYPE_SOA("soa", 6),
    TYPE_MB("mb", 7),
    TYPE_MG("mg", 8),
    TYPE_MR("mr", 9),
    TYPE_NULL("null", 10),
    TYPE_WKS("wks", 11),
    TYPE_PTR("ptr", 12),
    TYPE_HINFO("hinfo", 13),
    TYPE_MINFO("minfo", 14),
    TYPE_MX("mx", 15),
    TYPE_TXT("txt", 16),
    TYPE_RP("rp", 17),
    TYPE_AFSDB("afsdb", 18),
    TYPE_X25("x25", 19),
    TYPE_ISDN("isdn", 20),
    TYPE_RT("rt", 21),
    TYPE_NSAP("nsap", 22),
    TYPE_NSAP_PTR("nsap-otr", 23),
    TYPE_SIG(DTransferConstants.n, 24),
    TYPE_KEY("key", 25),
    TYPE_PX("px", 26),
    TYPE_GPOS("gpos", 27),
    TYPE_AAAA("aaaa", 28),
    TYPE_LOC("loc", 29),
    TYPE_NXT("nxt", 30),
    TYPE_EID(l.a.g, 31),
    TYPE_NIMLOC("nimloc", 32),
    TYPE_SRV("srv", 33),
    TYPE_ATMA("atma", 34),
    TYPE_NAPTR("naptr", 35),
    TYPE_KX("kx", 36),
    TYPE_CERT("cert", 37),
    TYPE_A6("a6", 38),
    TYPE_DNAME("dname", 39),
    TYPE_SINK("sink", 40),
    TYPE_OPT("opt", 41),
    TYPE_APL("apl", 42),
    TYPE_DS("ds", 43),
    TYPE_SSHFP("sshfp", 44),
    TYPE_RRSIG("rrsig", 46),
    TYPE_NSEC("nsec", 47),
    TYPE_DNSKEY("dnskey", 48),
    TYPE_UINFO("uinfo", 100),
    TYPE_UID("uid", 101),
    TYPE_GID(ApiConst.j, 102),
    TYPE_UNSPEC("unspec", 103),
    TYPE_TKEY("tkey", 249),
    TYPE_TSIG("tsig", 250),
    TYPE_IXFR("ixfr", 251),
    TYPE_AXFR("axfr", 252),
    TYPE_MAILA("mails", 253),
    TYPE_MAILB("mailb", ExifDirectoryBase.g),
    TYPE_ANY("any", 255);
    
    private static Logger logger;
    private final String _externalName;
    private final int _index;

    static {
        logger = Logger.getLogger(DNSRecordType.class.getName());
    }

    private DNSRecordType(String str, int i) {
        this._externalName = str;
        this._index = i;
    }

    public String externalName() {
        return this._externalName;
    }

    public int indexValue() {
        return this._index;
    }

    public static DNSRecordType typeForName(String str) {
        if (str != null) {
            String lowerCase = str.toLowerCase();
            for (DNSRecordType dNSRecordType : values()) {
                if (dNSRecordType._externalName.equals(lowerCase)) {
                    return dNSRecordType;
                }
            }
        }
        logger.severe("Could not find record type for name: " + str);
        return TYPE_IGNORE;
    }

    public static DNSRecordType typeForIndex(int i) {
        for (DNSRecordType dNSRecordType : values()) {
            if (dNSRecordType._index == i) {
                return dNSRecordType;
            }
        }
        logger.severe("Could not find record type for index: " + i);
        return TYPE_IGNORE;
    }

    public String toString() {
        return name() + " index " + indexValue();
    }
}
