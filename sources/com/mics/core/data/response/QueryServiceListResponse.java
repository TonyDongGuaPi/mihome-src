package com.mics.core.data.response;

import com.taobao.weex.el.parse.Operators;
import java.util.List;

public class QueryServiceListResponse {
    private int code;
    private Data data;
    private String msg;
    private boolean success;

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public Data getData() {
        return this.data;
    }

    public void setData(Data data2) {
        this.data = data2;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean z) {
        this.success = z;
    }

    public static class Data {
        private List<Groups> groups;
        private String msgType;

        public String getMsgType() {
            return this.msgType;
        }

        public void setMsgType(String str) {
            this.msgType = str;
        }

        public List<Groups> getGroups() {
            return this.groups;
        }

        public void setGroups(List<Groups> list) {
            this.groups = list;
        }

        public static class Groups {
            private List<Contents> contents;
            private String id;
            private String title;
            private boolean top;

            public String getId() {
                return this.id;
            }

            public void setId(String str) {
                this.id = str;
            }

            public String getTitle() {
                return this.title;
            }

            public void setTitle(String str) {
                this.title = str;
            }

            public boolean isTop() {
                return this.top;
            }

            public void setTop(boolean z) {
                this.top = z;
            }

            public List<Contents> getContents() {
                return this.contents;
            }

            public void setContents(List<Contents> list) {
                this.contents = list;
            }

            public static class Contents {
                private Object childGroupId;
                private String code;
                private String img;
                private boolean leaf;
                private String name;
                private boolean supportHuman;
                private boolean supportRobot;
                private int transferType;
                private int version;

                public String getName() {
                    return this.name;
                }

                public void setName(String str) {
                    this.name = str;
                }

                public String getImg() {
                    return this.img;
                }

                public void setImg(String str) {
                    this.img = str;
                }

                public String getCode() {
                    return this.code;
                }

                public void setCode(String str) {
                    this.code = str;
                }

                public Object getChildGroupId() {
                    return this.childGroupId;
                }

                public void setChildGroupId(Object obj) {
                    this.childGroupId = obj;
                }

                public boolean isLeaf() {
                    return this.leaf;
                }

                public void setLeaf(boolean z) {
                    this.leaf = z;
                }

                public boolean isSupportHuman() {
                    return this.supportHuman;
                }

                public void setSupportHuman(boolean z) {
                    this.supportHuman = z;
                }

                public boolean isSupportRobot() {
                    return this.supportRobot;
                }

                public void setSupportRobot(boolean z) {
                    this.supportRobot = z;
                }

                public int getTransferType() {
                    return this.transferType;
                }

                public void setTransferType(int i) {
                    this.transferType = i;
                }

                public int getVersion() {
                    return this.version;
                }

                public void setVersion(int i) {
                    this.version = i;
                }
            }
        }

        public String toString() {
            return "Data{msgType='" + this.msgType + Operators.SINGLE_QUOTE + ", groups=" + this.groups + Operators.BLOCK_END;
        }
    }

    public String toString() {
        return "QueryServiceListResponse{code=" + this.code + ", msg='" + this.msg + Operators.SINGLE_QUOTE + ", data=" + this.data + ", success=" + this.success + Operators.BLOCK_END;
    }
}
