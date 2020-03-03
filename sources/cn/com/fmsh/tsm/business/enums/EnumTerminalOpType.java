package cn.com.fmsh.tsm.business.enums;

public enum EnumTerminalOpType {
    ANDROID(1, "ANDROID"),
    IOS(2, "iOS"),
    WINDOWS(3, "windows");
    
    private String desc;
    private int id;

    private EnumTerminalOpType(int i, String str) {
        this.id = i;
        this.desc = str;
    }

    public int getId() {
        return this.id;
    }

    public String getDesc() {
        return this.desc;
    }

    public static EnumTerminalOpType getEnumTerminalOpType4ID(int i) {
        for (EnumTerminalOpType enumTerminalOpType : values()) {
            if (enumTerminalOpType.getId() == i) {
                return enumTerminalOpType;
            }
        }
        return null;
    }
}
