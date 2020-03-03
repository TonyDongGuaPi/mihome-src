package cn.com.fmsh.tsm.business.bean;

import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import com.xiaomi.smarthome.auth.AuthCode;

public class Activity {
    private String code;
    private String definition;
    private String end;
    private String name;
    private String payChannel;
    private int payMin;
    private int remainder;
    private String start;
    private int status;
    private int total;

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getStart() {
        return this.start;
    }

    public void setStart(String str) {
        this.start = str;
    }

    public String getEnd() {
        return this.end;
    }

    public void setEnd(String str) {
        this.end = str;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int i) {
        this.total = i;
    }

    public int getRemainder() {
        return this.remainder;
    }

    public void setRemainder(int i) {
        this.remainder = i;
    }

    public String getDefinition() {
        return this.definition;
    }

    public void setDefinition(String str) {
        this.definition = str;
    }

    public String getPayChannel() {
        return this.payChannel;
    }

    public void setPayChannel(String str) {
        this.payChannel = str;
    }

    public int getPayMin() {
        return this.payMin;
    }

    public void setPayMin(int i) {
        this.payMin = i;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public static Activity fromTag(ITag iTag) throws FMCommunicationMessageException {
        FMLog log = LogFactory.getInstance().getLog();
        if (iTag == null) {
            if (log != null) {
                log.warn(BusinessOrder.class.getName(), " 转换Tag对象到活动信息时，Tag对象为空");
            }
            return null;
        }
        ITag[] itemTags = iTag.getItemTags();
        if (itemTags == null || itemTags.length < 1) {
            if (log != null) {
                log.warn(BusinessOrder.class.getName(), " 转换Tag对象到活动信息时，Tag对象为空");
            }
            return null;
        }
        Activity activity = new Activity();
        for (ITag iTag2 : itemTags) {
            switch (iTag2.getId()) {
                case -126:
                    activity.setName(iTag2.getStringVal());
                    break;
                case -125:
                    activity.setCode(iTag2.getStringVal());
                    break;
                case -124:
                    activity.setStart(iTag2.getStringVal());
                    break;
                case -123:
                    activity.setEnd(iTag2.getStringVal());
                    break;
                case -122:
                    activity.setTotal(iTag2.getIntVal());
                    break;
                case -121:
                    activity.setRemainder(iTag2.getIntVal());
                    break;
                case -120:
                    activity.setDefinition(iTag2.getStringVal());
                    break;
                case -118:
                    activity.setPayChannel(iTag2.getStringVal());
                    break;
                case -117:
                    activity.setPayMin(iTag2.getIntVal());
                    break;
                case AuthCode.r /*-115*/:
                    activity.setStatus(iTag2.getBytesVal()[0]);
                    break;
            }
        }
        return activity;
    }
}
