package com.mics.core.data.response;

import com.taobao.weex.el.parse.Operators;

public class UploadResponse {
    private String code;
    private DataBean data;
    private String msg;

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public DataBean getData() {
        return this.data;
    }

    public void setData(DataBean dataBean) {
        this.data = dataBean;
    }

    public static class DataBean {
        private String content;

        public String getContent() {
            return this.content;
        }

        public void setContent(String str) {
            this.content = str;
        }

        public String toString() {
            return "DataBean{content='" + this.content + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
        }
    }

    public String toString() {
        return "UploadResponse{code='" + this.code + Operators.SINGLE_QUOTE + ", msg='" + this.msg + Operators.SINGLE_QUOTE + ", data=" + this.data + Operators.BLOCK_END;
    }
}
