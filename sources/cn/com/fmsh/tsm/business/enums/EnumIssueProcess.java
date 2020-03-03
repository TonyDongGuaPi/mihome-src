package cn.com.fmsh.tsm.business.enums;

public enum EnumIssueProcess {
    APPLIED(0, "数据已申请"),
    SSD_KEY_UPDATED(10, "SSD密钥已更新"),
    APP_LOAD(20, "应用已加载"),
    APP_INSTALL(30, "应用已安装"),
    APP_PERSONAL(40, "应用已个人化"),
    APP_ACTIVATION(50, "应用已激活"),
    APP_LOCK(60, "应用已锁定"),
    APP_REMOVE(70, "应用已删除");
    
    private String desc;
    private int id;

    private EnumIssueProcess(int i, String str) {
        this.id = i;
        this.desc = str;
    }

    public int getId() {
        return this.id;
    }

    public String getDesc() {
        return this.desc;
    }

    public static EnumIssueProcess instance(int i) {
        for (EnumIssueProcess enumIssueProcess : values()) {
            if (enumIssueProcess.getId() == i) {
                return enumIssueProcess;
            }
        }
        return null;
    }
}
