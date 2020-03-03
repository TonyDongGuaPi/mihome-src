package com.mics.core.data.request;

public class SessionSubmitRobotScore extends Session {
    private int isSatisfied;
    private String questionId;

    public String getQuestionId() {
        return this.questionId;
    }

    public void setQuestionId(String str) {
        this.questionId = str;
    }

    public int getIsSatisfied() {
        return this.isSatisfied;
    }

    public void setIsSatisfied(int i) {
        this.isSatisfied = i;
    }
}
