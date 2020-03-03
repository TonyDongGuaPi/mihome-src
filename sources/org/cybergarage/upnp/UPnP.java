package org.cybergarage.upnp;

import org.cybergarage.net.HostInterface;
import org.cybergarage.soap.SOAP;
import org.cybergarage.upnp.ssdp.SSDP;
import org.cybergarage.util.Debug;
import org.cybergarage.xml.Parser;

public class UPnP {
    public static final int DEFAULT_EXPIRED_DEVICE_EXTRA_TIME = 60;
    public static final int DEFAULT_TTL = 4;
    public static final String INMPR03 = "INMPR03";
    public static final int INMPR03_DISCOVERY_OVER_WIRELESS_COUNT = 4;
    public static final String INMPR03_VERSION = "1.0";
    public static final String NAME = "CyberLinkJava";
    public static final int SERVER_RETRY_COUNT = 100;
    public static final int USE_IPV6_ADMINISTRATIVE_SCOPE = 5;
    public static final int USE_IPV6_GLOBAL_SCOPE = 7;
    public static final int USE_IPV6_LINK_LOCAL_SCOPE = 3;
    public static final int USE_IPV6_SITE_LOCAL_SCOPE = 6;
    public static final int USE_IPV6_SUBNET_SCOPE = 4;
    public static final int USE_LOOPBACK_ADDR = 2;
    public static final int USE_ONLY_IPV4_ADDR = 9;
    public static final int USE_ONLY_IPV6_ADDR = 1;
    public static final int USE_SSDP_SEARCHRESPONSE_MULTIPLE_INTERFACES = 8;
    public static final String VERSION = "1.8";
    public static final String XML_CLASS_PROPERTTY = "cyberlink.upnp.xml.parser";
    public static final String XML_DECLARATION = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
    private static int timeToLive = 4;
    private static Parser xmlParser;

    public static final void initialize() {
    }

    public static final String getServerName() {
        String property = System.getProperty("os.name");
        String property2 = System.getProperty("os.version");
        return property + "/" + property2 + " UPnP/1.0 " + NAME + "/" + VERSION;
    }

    public static final void setEnable(int i) {
        switch (i) {
            case 1:
                HostInterface.USE_ONLY_IPV6_ADDR = true;
                return;
            case 2:
                HostInterface.USE_LOOPBACK_ADDR = true;
                return;
            case 3:
                SSDP.setIPv6Address(SSDP.IPV6_LINK_LOCAL_ADDRESS);
                return;
            case 4:
                SSDP.setIPv6Address(SSDP.IPV6_SUBNET_ADDRESS);
                return;
            case 5:
                SSDP.setIPv6Address(SSDP.IPV6_ADMINISTRATIVE_ADDRESS);
                return;
            case 6:
                SSDP.setIPv6Address(SSDP.IPV6_SITE_LOCAL_ADDRESS);
                return;
            case 7:
                SSDP.setIPv6Address(SSDP.IPV6_GLOBAL_ADDRESS);
                return;
            case 9:
                HostInterface.USE_ONLY_IPV4_ADDR = true;
                return;
            default:
                return;
        }
    }

    public static final void setDisable(int i) {
        if (i != 9) {
            switch (i) {
                case 1:
                    HostInterface.USE_ONLY_IPV6_ADDR = false;
                    return;
                case 2:
                    HostInterface.USE_LOOPBACK_ADDR = false;
                    return;
                default:
                    return;
            }
        } else {
            HostInterface.USE_ONLY_IPV4_ADDR = false;
        }
    }

    public static final boolean isEnabled(int i) {
        if (i == 9) {
            return HostInterface.USE_ONLY_IPV4_ADDR;
        }
        switch (i) {
            case 1:
                return HostInterface.USE_ONLY_IPV6_ADDR;
            case 2:
                return HostInterface.USE_LOOPBACK_ADDR;
            default:
                return false;
        }
    }

    private static final String toUUID(int i) {
        String num = Integer.toString(i & 65535, 16);
        String str = "";
        for (int i2 = 0; i2 < 4 - num.length(); i2++) {
            str = str + "0";
        }
        return str + num;
    }

    public static final String createUUID() {
        long currentTimeMillis = System.currentTimeMillis();
        double currentTimeMillis2 = (double) System.currentTimeMillis();
        double random = Math.random();
        Double.isNaN(currentTimeMillis2);
        long j = (long) (currentTimeMillis2 * random);
        return toUUID((int) (currentTimeMillis & 65535)) + "-" + toUUID(((int) ((currentTimeMillis >> 32) | 40960)) & 65535) + "-" + toUUID((int) (65535 & j)) + "-" + toUUID(((int) ((j >> 32) | 57344)) & 65535);
    }

    public static final void setXMLParser(Parser parser) {
        xmlParser = parser;
        SOAP.setXMLParser(parser);
    }

    public static final Parser getXMLParser() {
        if (xmlParser == null) {
            xmlParser = loadDefaultXMLParser();
            if (xmlParser != null) {
                SOAP.setXMLParser(xmlParser);
            } else {
                throw new RuntimeException("No XML parser defined. And unable to laod any. \nTry to invoke UPnP.setXMLParser before UPnP.getXMLParser");
            }
        }
        return xmlParser;
    }

    private static Parser loadDefaultXMLParser() {
        String[] strArr = {System.getProperty(XML_CLASS_PROPERTTY), "org.cybergarage.xml.parser.XmlPullParser", "org.cybergarage.xml.parser.JaxpParser", "org.cybergarage.xml.parser.kXML2Parser", "org.cybergarage.xml.parser.XercesParser"};
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i] != null) {
                try {
                    return (Parser) Class.forName(strArr[i]).newInstance();
                } catch (Throwable th) {
                    Debug.warning("Unable to load " + strArr[i] + " as XMLParser due to " + th);
                }
            }
        }
        return null;
    }

    public static final void setTimeToLive(int i) {
        timeToLive = i;
    }

    public static final int getTimeToLive() {
        return timeToLive;
    }

    static {
        setTimeToLive(4);
    }
}
