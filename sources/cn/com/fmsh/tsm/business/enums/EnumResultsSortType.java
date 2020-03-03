package cn.com.fmsh.tsm.business.enums;

public enum EnumResultsSortType {
    FORWARD(1, "向前查询（即小于被查询的字段） "),
    BACKWARD(2, "向后查询（即大于被查询的字段）");
    
    private String desc;
    private int id;

    private EnumResultsSortType(int i, String str) {
        this.id = i;
        this.desc = str;
    }

    public int getId() {
        return this.id;
    }

    public String getDesc() {
        return this.desc;
    }

    public static EnumResultsSortType instance(int i) {
        for (EnumResultsSortType enumResultsSortType : values()) {
            if (enumResultsSortType.getId() == i) {
                return enumResultsSortType;
            }
        }
        return null;
    }
}
