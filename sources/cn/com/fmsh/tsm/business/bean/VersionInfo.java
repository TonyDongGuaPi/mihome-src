package cn.com.fmsh.tsm.business.bean;

public class VersionInfo {
    boolean isUpdate;
    String url;
    String viersion;

    public String getViersion() {
        return this.viersion;
    }

    public void setViersion(String str) {
        this.viersion = str;
    }

    public boolean isUpdate() {
        return this.isUpdate;
    }

    public void setUpdate(boolean z) {
        this.isUpdate = z;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
