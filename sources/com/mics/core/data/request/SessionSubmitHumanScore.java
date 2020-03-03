package com.mics.core.data.request;

public class SessionSubmitHumanScore extends Session {
    private String comment;
    private String gradeId;
    private int isSolved;
    private String phone;
    private int score;
    private String showType;
    private String subSessionId;

    public String getGradeId() {
        return this.gradeId;
    }

    public void setGradeId(String str) {
        this.gradeId = str;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String str) {
        this.comment = str;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int i) {
        this.score = i;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String str) {
        this.phone = str;
    }

    public String getShowType() {
        return this.showType;
    }

    public void setShowType(String str) {
        this.showType = str;
    }

    public int getIsSolved() {
        return this.isSolved;
    }

    public void setIsSolved(int i) {
        this.isSolved = i;
    }

    public String getSubSessionId() {
        return this.subSessionId;
    }

    public void setSubSessionId(String str) {
        this.subSessionId = str;
    }
}
