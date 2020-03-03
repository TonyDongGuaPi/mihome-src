package com.xiaomi.youpin.pojo;

public class YingkebaoData {
    private Resource resource;

    public Resource getResource() {
        return this.resource;
    }

    public void setResource(Resource resource2) {
        this.resource = resource2;
    }

    public static class Resource {
        private int elapse;
        private int end_time;
        private String etag;
        private int interval;
        private String jump_url;
        private String pic_url;
        private int start_time;
        private int toast_count;
        private int toast_days;

        public String getPic_url() {
            return this.pic_url;
        }

        public void setPic_url(String str) {
            this.pic_url = str;
        }

        public String getJump_url() {
            return this.jump_url;
        }

        public void setJump_url(String str) {
            this.jump_url = str;
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

        public int getToast_days() {
            return this.toast_days;
        }

        public void setToast_days(int i) {
            this.toast_days = i;
        }

        public int getToast_count() {
            return this.toast_count;
        }

        public void setToast_count(int i) {
            this.toast_count = i;
        }

        public int getInterval() {
            return this.interval;
        }

        public void setInterval(int i) {
            this.interval = i;
        }

        public int getElapse() {
            return this.elapse;
        }

        public void setElapse(int i) {
            this.elapse = i;
        }

        public String getEtag() {
            return this.etag;
        }

        public void setEtag(String str) {
            this.etag = str;
        }
    }
}
