package cn.com.fmsh.communication.core;

public class LinkInfo {
    private String address;
    private int port;
    private int timeout = -1;

    public enum LinkType {
        TCP,
        UDP,
        HTTP
    }

    public int getPort() {
        return this.port;
    }

    public void setPort(int i) {
        this.port = i;
    }

    public int getTimeout() {
        return this.timeout;
    }

    public void setTimeout(int i) {
        this.timeout = i;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }
}
