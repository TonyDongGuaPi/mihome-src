package com.xiaomi.plugin.update.pojo;

public class UpdateInfo {
    private String bak_url;
    private String changelog;
    private String force_version;
    private int publish_time;
    private String sign;
    private String type;
    private String url;
    private String version;

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getBak_url() {
        return this.bak_url;
    }

    public void setBak_url(String str) {
        this.bak_url = str;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String str) {
        this.sign = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public int getPublish_time() {
        return this.publish_time;
    }

    public void setPublish_time(int i) {
        this.publish_time = i;
    }

    public String getChangelog() {
        return this.changelog;
    }

    public void setChangelog(String str) {
        this.changelog = str;
    }

    public String getForce_version() {
        return this.force_version;
    }

    public void setForce_version(String str) {
        this.force_version = str;
    }
}
