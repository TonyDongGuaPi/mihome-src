package com.mics.core.data.response;

public class QueuePositionResponse {
    private int code;
    private Data data;
    private Object msg;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public Object getMsg() {
        return this.msg;
    }

    public void setMsg(Object obj) {
        this.msg = obj;
    }

    public Data getData() {
        return this.data;
    }

    public void setData(Data data2) {
        this.data = data2;
    }

    public static class Data {
        private int position;

        public int getPosition() {
            return this.position;
        }

        public void setPosition(int i) {
            this.position = i;
        }
    }
}
