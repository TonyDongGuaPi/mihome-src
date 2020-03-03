package com.mics.core.ui.data;

import android.text.TextUtils;
import com.mics.util.GsonUtil;
import java.lang.reflect.Type;

public class RobotAnswerData extends BaseData {
    private boolean hasChoose;
    private String questionId;

    public void setExtra(String str) {
        RobotAnswer robotAnswer;
        if (!TextUtils.isEmpty(str) && (robotAnswer = (RobotAnswer) GsonUtil.a(str, (Type) RobotAnswer.class)) != null && TextUtils.equals("robotAnswer", robotAnswer.getExtraType()) && robotAnswer.getNeedEval() == 1) {
            setQuestionId(robotAnswer.getQuestionId());
        }
    }

    public boolean isHasChoose() {
        return this.hasChoose;
    }

    public void setHasChoose(boolean z) {
        this.hasChoose = z;
    }

    public String getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(String str) {
        this.questionId = str;
    }

    private static class RobotAnswer {
        private String extraType;
        private int needEval;
        private String questionId;

        private RobotAnswer() {
        }

        public String getExtraType() {
            return this.extraType;
        }

        public void setExtraType(String str) {
            this.extraType = str;
        }

        public int getNeedEval() {
            return this.needEval;
        }

        public void setNeedEval(int i) {
            this.needEval = i;
        }

        public String getQuestionId() {
            return this.questionId;
        }

        public void setQuestionId(String str) {
            this.questionId = str;
        }
    }
}
