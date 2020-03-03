package cn.com.fmsh.tsm.business.bean;

import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;

public class StationInfo {
    private String ename;
    private String id;
    private String name;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getEname() {
        return this.ename;
    }

    public void setEname(String str) {
        this.ename = str;
    }

    public static StationInfo fromTag(ITag iTag) throws FMCommunicationMessageException {
        FMLog log = LogFactory.getInstance().getLog();
        if (iTag == null) {
            if (log != null) {
                log.warn(BusinessOrder.class.getName(), " 转换Tag对象到地铁站信息对象时，Tag对象为空");
            }
            return null;
        }
        ITag[] itemTags = iTag.getItemTags();
        if (itemTags == null || itemTags.length < 1) {
            if (log != null) {
                log.warn(BusinessOrder.class.getName(), " 转换Tag对象到反馈信息时，Tag对象为空");
            }
            return null;
        }
        StationInfo stationInfo = new StationInfo();
        for (ITag iTag2 : itemTags) {
            switch (iTag2.getId()) {
                case -65:
                    stationInfo.setId(iTag2.getStringVal());
                    break;
                case -64:
                    stationInfo.setEname(iTag2.getStringVal());
                    break;
                case -63:
                    stationInfo.setName(iTag2.getStringVal());
                    break;
            }
        }
        return stationInfo;
    }
}
