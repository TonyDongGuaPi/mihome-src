package com.youpin.weex.app.model.pojo;

public class UpdateActBean {
    private int code;
    private DataBean data;
    private String description;
    private String message;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public static class DataBean {
        private int application;
        private String author;
        private int bundle_type;
        private int create_time;
        private String desc;
        private int dev_mode;
        private int end_time;
        private int grey;
        private String html_url;
        private int id;
        private String md5;
        private String min_app_version;
        private String min_sdk_version;
        private String name;
        private int platform;
        private int start_time;
        private int status;
        private String title;
        private int update_time;
        private String url;
        private int version;

        public int getId() {
            return this.id;
        }

        public void setId(int i) {
            this.id = i;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public int getApplication() {
            return this.application;
        }

        public void setApplication(int i) {
            this.application = i;
        }

        public int getBundle_type() {
            return this.bundle_type;
        }

        public void setBundle_type(int i) {
            this.bundle_type = i;
        }

        public int getPlatform() {
            return this.platform;
        }

        public void setPlatform(int i) {
            this.platform = i;
        }

        public int getDev_mode() {
            return this.dev_mode;
        }

        public void setDev_mode(int i) {
            this.dev_mode = i;
        }

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public String getUrl() {
            return this.url;
        }

        public void setUrl(String str) {
            this.url = str;
        }

        public String getHtml_url() {
            return this.html_url;
        }

        public void setHtml_url(String str) {
            this.html_url = str;
        }

        public int getVersion() {
            return this.version;
        }

        public void setVersion(int i) {
            this.version = i;
        }

        public String getMin_sdk_version() {
            return this.min_sdk_version;
        }

        public void setMin_sdk_version(String str) {
            this.min_sdk_version = str;
        }

        public String getMin_app_version() {
            return this.min_app_version;
        }

        public void setMin_app_version(String str) {
            this.min_app_version = str;
        }

        public int getStart_time() {
            return this.start_time;
        }

        public void setStart_time(int i) {
            this.start_time = i;
        }

        public int getEnd_time() {
            return this.end_time;
        }

        public void setEnd_time(int i) {
            this.end_time = i;
        }

        public int getGrey() {
            return this.grey;
        }

        public void setGrey(int i) {
            this.grey = i;
        }

        public String getAuthor() {
            return this.author;
        }

        public void setAuthor(String str) {
            this.author = str;
        }

        public int getCreate_time() {
            return this.create_time;
        }

        public void setCreate_time(int i) {
            this.create_time = i;
        }

        public int getUpdate_time() {
            return this.update_time;
        }

        public void setUpdate_time(int i) {
            this.update_time = i;
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getDesc() {
            return this.desc;
        }

        public void setDesc(String str) {
            this.desc = str;
        }

        public String getMd5() {
            return this.md5;
        }

        public void setMd5(String str) {
            this.md5 = str;
        }
    }
}
