package cn.com.fmsh.tsm.business.enums;

public enum EnumAppActivationStatus {
    noActivity(0, "未开通"),
    activiting(1, "开通中"),
    activitySucess(2, "开通成功"),
    activityFail(3, "开通失败"),
    closeing(4, "关闭中"),
    closeSucess(5, "关闭成功"),
    closeFail(6, "关闭失败");
    
    private String desc;
    private int id;

    private EnumAppActivationStatus(int i, String str) {
        this.id = i;
        this.desc = str;
    }

    public int getId() {
        return this.id;
    }

    public String getDesc() {
        return this.desc;
    }

    public static EnumAppActivationStatus getActivationStatus4ID(int i) {
        for (EnumAppActivationStatus enumAppActivationStatus : values()) {
            if (enumAppActivationStatus.getId() == i) {
                return enumAppActivationStatus;
            }
        }
        return null;
    }
}
