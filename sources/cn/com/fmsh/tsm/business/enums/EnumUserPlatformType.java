package cn.com.fmsh.tsm.business.enums;

public enum EnumUserPlatformType {
    NFCOS(1, "NFCOS系统平台"),
    THIRD(2, "第三方平台");
    
    private String desc;
    private int id;

    private EnumUserPlatformType(int i, String str) {
        this.id = i;
        this.desc = str;
    }

    public int getId() {
        return this.id;
    }

    public String getDesc() {
        return this.desc;
    }

    public static EnumUserPlatformType getActivationStatus4ID(int i) {
        for (EnumUserPlatformType enumUserPlatformType : values()) {
            if (enumUserPlatformType.getId() == i) {
                return enumUserPlatformType;
            }
        }
        return null;
    }
}
