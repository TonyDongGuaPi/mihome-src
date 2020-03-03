package com.mi.global.bbs.model;

import com.mi.global.bbs.model.TaskItemViewModel;
import java.util.ArrayList;
import java.util.List;

public class TaskNewBieModel extends BaseResult {
    public static final int NEW_BIE_MISSION_FINISH = 4;
    public static final int NEW_BIE_MISSION_FOLLOW_USER = 3;
    private static final int NEW_BIE_MISSION_FOUR_POINT = 4;
    private static final int NEW_BIE_MISSION_ONE_POINT = 1;
    public static final int NEW_BIE_MISSION_SET_NAME = 2;
    private static final int NEW_BIE_MISSION_TWO_POINT = 2;
    public static final int NEW_BIE_MISSION_UPLOAD_AVATOR = 1;
    private TaskDataBean data;

    public static boolean isNewBieMission(int i) {
        return i == 4 || i == 3 || i == 2 || i == 1;
    }

    public TaskDataBean getData() {
        return this.data;
    }

    public static class TaskDataBean {
        private int finish;
        private List<TaskNewBieBean> tasks;

        public List<TaskNewBieBean> getTasks() {
            return this.tasks;
        }

        public int getFinish() {
            return this.finish;
        }

        public static class TaskNewBieBean {
            private int process;
            private int status;
            private int taskid;

            public int getTaskstatus() {
                return this.status;
            }

            public int getTaskId() {
                return this.taskid;
            }

            public int getProcess() {
                return this.process;
            }
        }
    }

    public static List<TaskItemViewModel.TaskItemViewBean> makeTaskData(TaskNewBieModel taskNewBieModel) {
        ArrayList arrayList = new ArrayList();
        if (!(taskNewBieModel == null || taskNewBieModel.data == null || taskNewBieModel.data.getTasks() == null)) {
            int computeFinishProcess = computeFinishProcess(taskNewBieModel.data.getTasks());
            for (TaskDataBean.TaskNewBieBean next : taskNewBieModel.data.getTasks()) {
                TaskItemViewModel.TaskItemViewBean taskItemViewBean = new TaskItemViewModel.TaskItemViewBean();
                taskItemViewBean.setTaskid(next.getTaskId());
                taskItemViewBean.setTaskstatus(next.getTaskstatus());
                taskItemViewBean.setProcess(next.getProcess());
                switch (next.getTaskId()) {
                    case 1:
                        taskItemViewBean.setPoint(2);
                        break;
                    case 2:
                        taskItemViewBean.setPoint(2);
                        break;
                    case 3:
                        taskItemViewBean.setPoint(4);
                        break;
                }
                arrayList.add(taskItemViewBean);
            }
            TaskItemViewModel.TaskItemViewBean taskItemViewBean2 = new TaskItemViewModel.TaskItemViewBean();
            taskItemViewBean2.setTaskid(4);
            taskItemViewBean2.setTaskstatus(taskNewBieModel.data.getFinish());
            taskItemViewBean2.setPoint(1);
            taskItemViewBean2.setProcess(computeFinishProcess);
            arrayList.add(taskItemViewBean2);
        }
        return arrayList;
    }

    private static int computeFinishProcess(List<TaskDataBean.TaskNewBieBean> list) {
        int i = 0;
        for (TaskDataBean.TaskNewBieBean taskstatus : list) {
            if (taskstatus.getTaskstatus() != 0) {
                i++;
            }
        }
        return i;
    }
}
