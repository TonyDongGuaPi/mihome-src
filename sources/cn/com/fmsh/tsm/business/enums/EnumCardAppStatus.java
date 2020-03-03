package cn.com.fmsh.tsm.business.enums;

public enum EnumCardAppStatus {
    STATUS_NO_APP(1, "应用未安装"),
    STATUS_LOADED(2, "可执行装载文件LOADED"),
    STATUS_INSTALL(3, "上海交通卡应用安装"),
    STATUS_PERSONALIZED(4, "上海交通卡个人化"),
    STATUS_ACTIVATE(5, "上海交通卡开通"),
    STATUS_UNKNOW(10, "未知状态");
    
    private String desc;
    private int id;

    private EnumCardAppStatus(int i, String str) {
        this.id = i;
        this.desc = str;
    }

    public int getId() {
        return this.id;
    }

    public String getDesc() {
        return this.desc;
    }

    public static EnumCardAppStatus instance(int i) {
        for (EnumCardAppStatus enumCardAppStatus : values()) {
            if (enumCardAppStatus.getId() == i) {
                return enumCardAppStatus;
            }
        }
        return null;
    }
}
