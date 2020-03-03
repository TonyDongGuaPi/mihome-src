package com.mi.global.bbs.model;

import android.text.TextUtils;
import com.mi.global.bbs.model.BaseForumCommentsBean;
import com.mi.global.bbs.model.PostDetailModel;
import java.io.Serializable;

public class PostDataItem implements Serializable {
    public static final int POST_DATA_AUTHOR = 5;
    public static final int POST_DATA_COLUMN = 6;
    public static final int POST_DATA_COMMENT = 7;
    public static final int POST_DATA_IMAGE = 2;
    public static final int POST_DATA_PAST_COLUMN_HEADER = 8;
    public static final int POST_DATA_PAST_COLUMN_ITEM = 9;
    public static final int POST_DATA_TEXT = 1;
    public static final int POST_DATA_TITLE = 3;
    public static final int POST_DATA_UNKNOWN = 999;
    public static final int POST_DATA_VIDEO = 10;
    public static final int POST_DATA_VOTE = 4;
    private String alignType;
    private PostDetailModel.DataBean.Author author;
    private PostDetailModel.DataBean.Column column;
    private BaseForumCommentsBean.PostListBean comment;
    private int commentPostion;
    private int commentSize;
    private int dataType;
    private PostDetailModel.DataBean.Column.ColumnThread pastColunm;
    private String postImage;
    private String postTxt;
    private PostDetailModel.DataBean.SpecialInfo specialInfo;
    private PostDetailModel.DataBean.ThreadBean threadBean;

    public int getDataType() {
        return this.dataType;
    }

    public void setDataType(int i) {
        this.dataType = i;
    }

    public String getPostTxt() {
        return TextUtils.isEmpty(this.postTxt) ? "" : this.postTxt.trim();
    }

    public void setPostTxt(String str) {
        this.postTxt = str;
    }

    public String getPostImage() {
        return this.postImage;
    }

    public void setPostImage(String str) {
        this.postImage = str;
    }

    public PostDetailModel.DataBean.Author getAuthor() {
        return this.author;
    }

    public void setAuthor(PostDetailModel.DataBean.Author author2) {
        this.author = author2;
    }

    public String getAlignType() {
        return this.alignType;
    }

    public void setAlignType(String str) {
        this.alignType = str;
    }

    public PostDetailModel.DataBean.ThreadBean getThreadBean() {
        return this.threadBean;
    }

    public void setThreadBean(PostDetailModel.DataBean.ThreadBean threadBean2) {
        this.threadBean = threadBean2;
    }

    public PostDetailModel.DataBean.SpecialInfo getSpecialInfo() {
        return this.specialInfo;
    }

    public void setSpecialInfo(PostDetailModel.DataBean.SpecialInfo specialInfo2) {
        this.specialInfo = specialInfo2;
    }

    public PostDetailModel.DataBean.Column getColumn() {
        return this.column;
    }

    public void setColumn(PostDetailModel.DataBean.Column column2) {
        this.column = column2;
    }

    public BaseForumCommentsBean.PostListBean getComment() {
        return this.comment;
    }

    public void setComment(BaseForumCommentsBean.PostListBean postListBean) {
        this.comment = postListBean;
    }

    public void setCommentPostion(int i) {
        this.commentPostion = i;
    }

    public void setCommentSize(int i) {
        this.commentSize = i;
    }

    public int getCommentSize() {
        return this.commentSize;
    }

    public int getCommentPostion() {
        return this.commentPostion;
    }

    public PostDetailModel.DataBean.Column.ColumnThread getPastColunm() {
        return this.pastColunm;
    }

    public void setPastColunm(PostDetailModel.DataBean.Column.ColumnThread columnThread) {
        this.pastColunm = columnThread;
    }
}
