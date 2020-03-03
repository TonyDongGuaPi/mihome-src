package com.mi.global.bbs.model;

import java.util.List;

public class TaskItemViewModel extends BaseResult {
    private List<TaskItemViewBean> taskItemViewBeen;

    public List<TaskItemViewBean> getTaskItemViewBeen() {
        return this.taskItemViewBeen;
    }

    public static class TaskItemViewBean {
        private String callback;
        private String desc;
        private String directurl;
        private String picurl;
        private int point;
        private int process;
        private int taskid;
        private int taskstatus;

        public int getTaskid() {
            return this.taskid;
        }

        public void setTaskid(int i) {
            this.taskid = i;
        }

        public int getTaskstatus() {
            return this.taskstatus;
        }

        public void setTaskstatus(int i) {
            this.taskstatus = i;
        }

        public String getDesc() {
            return this.desc;
        }

        public void setDesc(String str) {
            this.desc = str;
        }

        public String getCallback() {
            return this.callback;
        }

        public void setCallback(String str) {
            this.callback = str;
        }

        public int getPoint() {
            return this.point;
        }

        public void setPoint(int i) {
            this.point = i;
        }

        public String getPicurl() {
            return this.picurl;
        }

        public void setPicurl(String str) {
            this.picurl = str;
        }

        public String getDirecturl() {
            return this.directurl;
        }

        public void setDirecturl(String str) {
            this.directurl = str;
        }

        public int getProcess() {
            return this.process;
        }

        public void setProcess(int i) {
            this.process = i;
        }
    }
}
