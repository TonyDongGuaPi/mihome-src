package com.mics.core.data.response;

import android.text.TextUtils;
import com.mics.core.data.request.SendText;
import com.mics.util.GsonUtil;
import com.mics.util.HtmlText;
import com.taobao.weex.el.parse.Operators;
import java.lang.reflect.Type;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MessageResponse {
    private int code;
    private Data data;
    private String msg;

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

    public static class Data {
        private boolean hasMore;
        private List<Message> messageList;

        public boolean isHasMore() {
            return this.hasMore;
        }

        public void setHasMore(boolean z) {
            this.hasMore = z;
        }

        public List<Message> getMessageList() {
            return this.messageList;
        }

        public void setMessageList(List<Message> list) {
            this.messageList = list;
        }

        public static class Message {
            private String content;
            private long createTime;
            private String extraInfo;
            private String fromUserId;
            private String fromUserName;
            private long msgId;
            private String msgType;
            private String umsgId;

            public long getMsgId() {
                return this.msgId;
            }

            public void setMsgId(long j) {
                this.msgId = j;
            }

            public String getUmsgId() {
                return this.umsgId;
            }

            public void setUmsgId(String str) {
                this.umsgId = str;
            }

            public String getFromUserId() {
                return this.fromUserId;
            }

            public void setFromUserId(String str) {
                this.fromUserId = str;
            }

            public String getFromUserName() {
                return this.fromUserName;
            }

            public void setFromUserName(String str) {
                this.fromUserName = str;
            }

            public String getContent() {
                this.content = HtmlText.a(this.content);
                return this.content;
            }

            public void setContent(String str) {
                this.content = str;
            }

            public String getMsgType() {
                return this.msgType;
            }

            public void setMsgType(String str) {
                this.msgType = str;
            }

            public String getExtraInfo() {
                return this.extraInfo;
            }

            public void setExtraInfo(String str) {
                this.extraInfo = str;
            }

            public long getCreateTime() {
                return this.createTime;
            }

            public void setCreateTime(long j) {
                this.createTime = j;
            }

            public int getType() {
                if (SendText.TEXT.equals(getMsgType())) {
                    return 1;
                }
                if (SendText.PIC.equals(getMsgType())) {
                    return 2;
                }
                return "EVENT".equals(getMsgType()) ? 0 : -1;
            }

            public String getAbstractContent() {
                int type = getType();
                if (type == 1) {
                    RobotQuestion robotQuestion = (RobotQuestion) GsonUtil.a(getContent(), (Type) RobotQuestion.class);
                    if (robotQuestion == null) {
                        return getContent();
                    }
                    if (TextUtils.equals(robotQuestion.getFormat(), "text")) {
                        return robotQuestion.getContent();
                    }
                    if (TextUtils.equals(robotQuestion.getFormat(), "news") && robotQuestion.getArticles() != null && robotQuestion.getArticles().size() > 0) {
                        return robotQuestion.getArticles().get(0).getTitle();
                    }
                } else if (type == 2) {
                    return "[图片]";
                }
                return getContent();
            }

            public static class RobotAnswer {
                private String extraType;
                private int needEval;
                private String questionId;

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

        public static class RobotQuestion {
            private static final String questionRegex = "\\$\\{.+[}]";
            private List<Article> articles;
            private String format;
            private List<Params> params;
            private String template;

            public String getFormat() {
                return this.format;
            }

            public void setFormat(String str) {
                this.format = str;
            }

            public String getTemplate() {
                return this.template;
            }

            public void setTemplate(String str) {
                this.template = str;
            }

            public List<Params> getParams() {
                return this.params;
            }

            public void setParams(List<Params> list) {
                this.params = list;
            }

            public List<Article> getArticles() {
                return this.articles;
            }

            public void setArticles(List<Article> list) {
                this.articles = list;
            }

            public static class Params {
                private String color;
                private String key;
                private String showText;
                private String type;
                private String url;

                public String getKey() {
                    return this.key;
                }

                public void setKey(String str) {
                    this.key = str;
                }

                public String getType() {
                    return this.type;
                }

                public void setType(String str) {
                    this.type = str;
                }

                public String getShowText() {
                    return this.showText;
                }

                public void setShowText(String str) {
                    this.showText = str;
                }

                public String getUrl() {
                    return this.url;
                }

                public void setUrl(String str) {
                    this.url = str;
                }

                public String getColor() {
                    return this.color;
                }

                public void setColor(String str) {
                    this.color = str;
                }
            }

            public String getContent() {
                if (TextUtils.isEmpty(this.template) || getParams() == null) {
                    return null;
                }
                String replaceAll = this.template.replaceAll("\n", "<br>");
                Matcher matcher = Pattern.compile(questionRegex, 2).matcher(this.template);
                while (matcher.find()) {
                    String group = matcher.group();
                    String substring = group.substring(2, group.length() - 1);
                    for (Params next : getParams()) {
                        if (next != null && TextUtils.equals(next.getKey(), substring)) {
                            if (TextUtils.equals(next.getType(), "quickAsk")) {
                                replaceAll = replaceAll.replace(group, "<ypQuickAsk> " + next.getShowText() + "</" + HtmlText.f7779a + ">");
                            } else if (TextUtils.equals(next.getType(), "showHumanSkill")) {
                                replaceAll = replaceAll.replace(group, "<ypShowHumanService> " + next.getShowText() + "</" + HtmlText.c + ">");
                            } else if (TextUtils.equals(next.getType(), "toLeaveMessage")) {
                                replaceAll = replaceAll.replace(group, "<ypLeaveMsg> " + next.getShowText() + "</" + HtmlText.b + ">");
                            } else if (TextUtils.equals(next.getType(), "link") || TextUtils.equals(next.getType(), "img")) {
                                replaceAll = replaceAll.replace(group, "<ypLink href='" + next.getUrl() + "'> " + next.getShowText() + "</ypLink>");
                            } else if (TextUtils.equals(next.getType(), "text")) {
                                replaceAll = replaceAll.replace(group, "<font color='" + next.getColor() + "'> " + next.getShowText() + "</font>");
                            } else {
                                String color = next.getColor();
                                if (color == null) {
                                    color = "";
                                }
                                replaceAll = replaceAll.replace(group, "<font color='" + color + "'> " + next.getShowText() + "</font>");
                            }
                        }
                    }
                }
                return replaceAll;
            }

            public static class Article {
                private String description;
                private String picUrl;
                private String title;
                private String type;
                private String url;

                public String getPicUrl() {
                    return this.picUrl;
                }

                public void setPicUrl(String str) {
                    this.picUrl = str;
                }

                public String getDescription() {
                    return this.description;
                }

                public void setDescription(String str) {
                    this.description = str;
                }

                public String getTitle() {
                    return this.title;
                }

                public void setTitle(String str) {
                    this.title = str;
                }

                public String getType() {
                    return this.type;
                }

                public void setType(String str) {
                    this.type = str;
                }

                public String getUrl() {
                    return this.url;
                }

                public void setUrl(String str) {
                    this.url = str;
                }
            }
        }
    }

    public String toString() {
        return "MessageResponse{code=" + this.code + ", msg='" + this.msg + Operators.SINGLE_QUOTE + ", data=" + this.data + Operators.BLOCK_END;
    }
}
