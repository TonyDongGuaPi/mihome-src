package com.mi.global.bbs.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.mi.global.bbs.model.TaskItemViewModel;
import java.util.ArrayList;
import java.util.List;

public class TaskModel extends BaseResult {
    public static final Parcelable.Creator<TaskModel> CREATOR = new Parcelable.Creator<TaskModel>() {
        public TaskModel createFromParcel(Parcel parcel) {
            return new TaskModel(parcel);
        }

        public TaskModel[] newArray(int i) {
            return new TaskModel[i];
        }
    };
    private List<TasksBean> tasks;

    public int describeContents() {
        return 0;
    }

    public List<TasksBean> getTasks() {
        return this.tasks;
    }

    public void setTasks(List<TasksBean> list) {
        this.tasks = list;
    }

    public static class TasksBean {
        private String callback;
        private String desc;
        private String directurl;
        private String picurl;
        private int point;
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
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeList(this.tasks);
    }

    public TaskModel() {
    }

    protected TaskModel(Parcel parcel) {
        super(parcel);
        this.tasks = new ArrayList();
        parcel.readList(this.tasks, TasksBean.class.getClassLoader());
    }

    public static List<TaskItemViewModel.TaskItemViewBean> makeTaskItemViewModelData(List<TasksBean> list) {
        ArrayList arrayList = new ArrayList();
        for (TasksBean next : list) {
            TaskItemViewModel.TaskItemViewBean taskItemViewBean = new TaskItemViewModel.TaskItemViewBean();
            taskItemViewBean.setTaskid(next.getTaskid());
            taskItemViewBean.setTaskstatus(next.getTaskstatus());
            taskItemViewBean.setDesc(next.getDesc());
            taskItemViewBean.setCallback(next.getCallback());
            taskItemViewBean.setPoint(next.getPoint());
            taskItemViewBean.setPicurl(next.getPicurl());
            taskItemViewBean.setDirecturl(next.getDirecturl());
            if (next.getTaskstatus() != 0) {
                taskItemViewBean.setProcess(1);
            } else {
                taskItemViewBean.setProcess(0);
            }
            arrayList.add(taskItemViewBean);
        }
        return arrayList;
    }
}
