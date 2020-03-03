package cn.com.fmsh.tsm.business.core;

import cn.com.fmsh.communication.core.LinkInfo;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.Level;
import cn.com.fmsh.util.log.LogFactory;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Configration {
    private List<byte[]> aids = new ArrayList();
    private Map<Integer, String> businessAndServer = new HashMap();
    private String businessVersion;
    private String companyCode = "";
    private FMLog fmLog = LogFactory.getInstance().getLog();
    private Map<String, List<Key>> keyList = new HashMap();
    private Level logLevel = Level.ERROR;
    private final String logTag = Configration.class.getName();
    private byte orderSource = 0;
    private String sdkVersion = "";
    private String serverDomain;
    private int serverPort;
    private Map<String, LinkInfo> serveres = new HashMap();
    private int socketTimeout;
    private byte[] terminalNumber;
    private byte[] terminalType;
    private String userCode;

    public String getBusinessVersion() {
        return this.businessVersion;
    }

    public void setBusinessVersion(String str) {
        this.businessVersion = str;
    }

    public String getServerDomain() {
        return this.serverDomain;
    }

    public void setServerDomain(String str) {
        this.serverDomain = str;
    }

    public int getServerPort() {
        return this.serverPort;
    }

    public void setServerPort(int i) {
        this.serverPort = i;
    }

    public byte[] getTerminalType() {
        return this.terminalType;
    }

    public void setTerminalType(byte[] bArr) {
        this.terminalType = bArr;
    }

    public byte[] getTerminalNumber() {
        return this.terminalNumber;
    }

    public void setTerminalNumber(byte[] bArr) {
        this.terminalNumber = bArr;
    }

    public String getUserCode() {
        return this.userCode;
    }

    public void setUserCode(String str) {
        this.userCode = str;
    }

    public void addKey(String str, int i, byte[] bArr, byte[] bArr2) {
        if (str != null && bArr != null && bArr2 != null && i != -1) {
            Key key = new Key();
            key.index = i;
            key.exponent = bArr;
            key.modulus = bArr2;
            List list = this.keyList.get(str);
            if (list == null) {
                list = new ArrayList();
                this.keyList.put(str, list);
            }
            list.add(key);
        } else if (this.fmLog != null) {
            this.fmLog.warn(this.logTag, "加载配置文件中密钥信息时，待加载的密钥信息无效");
        }
    }

    public void addServers(String str, int i, int i2, String str2) {
        if (str != null && str.length() > 0 && i > 0 && str2 != null && str2.length() > 0) {
            LinkInfo linkInfo = new LinkInfo();
            linkInfo.setAddress(str);
            linkInfo.setPort(i);
            linkInfo.setTimeout(i2);
            this.serveres.put(str2, linkInfo);
        }
    }

    public String[] getKeies4Server() {
        return (String[]) this.serveres.keySet().toArray(new String[0]);
    }

    public LinkInfo getLinkInfo(String str) {
        return this.serveres.get(str);
    }

    public Key[] getKeys(String str) {
        List list = this.keyList.get(str);
        if (list != null) {
            return (Key[]) list.toArray(new Key[0]);
        }
        if (this.fmLog == null) {
            return null;
        }
        FMLog fMLog = this.fmLog;
        String str2 = this.logTag;
        fMLog.warn(str2, "配置文件没有[" + str + "]对应的key");
        return null;
    }

    public void addAid(byte[] bArr) {
        if (bArr != null && bArr.length >= 1) {
            this.aids.add(bArr);
        }
    }

    public byte[][] getAids() {
        return (byte[][]) this.aids.toArray((byte[][]) Array.newInstance(byte.class, new int[]{1, 1}));
    }

    public int getSocketTimeout() {
        return this.socketTimeout;
    }

    public void setSocketTimeout(int i) {
        this.socketTimeout = i;
    }

    public byte getOrderSource() {
        return this.orderSource;
    }

    public void setOrderSource(byte b) {
        this.orderSource = b;
    }

    public String getCompanyCode() {
        return this.companyCode;
    }

    public void setCompanyCode(String str) {
        this.companyCode = str;
    }

    public void addBusinessAndServer(int i, String str) {
        if (str != null) {
            this.businessAndServer.put(Integer.valueOf(i), str);
        }
    }

    public String getServer4Business(int i) {
        return this.businessAndServer.get(Integer.valueOf(i));
    }

    public class Key {
        public byte[] exponent;
        public int index = 0;
        public byte[] modulus;

        public Key() {
        }
    }

    public String getSdkVersion() {
        return this.sdkVersion;
    }

    public void setSdkVersion(String str) {
        this.sdkVersion = str;
    }

    public Level getLogLevel() {
        return this.logLevel;
    }

    public void setLogLevel(Level level) {
        this.logLevel = level;
    }
}
