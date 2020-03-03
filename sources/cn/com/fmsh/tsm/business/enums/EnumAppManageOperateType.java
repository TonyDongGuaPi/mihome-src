package cn.com.fmsh.tsm.business.enums;

public enum EnumAppManageOperateType {
    APP_LOCK(1, "卡上应用关闭"),
    APP_UNLOCK(2, "卡上应用开启"),
    APP_STATUS_QUERY(3, "卡上应用当前状态查询"),
    APP_DELETE(4, "卡上应用删除"),
    APP_MOVE(5, "卡上应用迁出"),
    APP_DOWNLOAD(6, "卡上应用删除");
    
    private String desc;
    private int id;

    private EnumAppManageOperateType(int i, String str) {
        this.id = i;
        this.desc = str;
    }

    public int getId() {
        return this.id;
    }

    public String getDesc() {
        return this.desc;
    }

    public static EnumAppManageOperateType instance(int i) {
        for (EnumAppManageOperateType enumAppManageOperateType : values()) {
            if (enumAppManageOperateType.getId() == i) {
                return enumAppManageOperateType;
            }
        }
        return null;
    }
}
