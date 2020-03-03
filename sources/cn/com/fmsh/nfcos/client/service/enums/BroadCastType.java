package cn.com.fmsh.nfcos.client.service.enums;

public enum BroadCastType {
    ISSUER_PROCESS(1, "应用发行进度"),
    PLATFORM_NOTICE(2, "平台消息"),
    SOCKET_EXCEPTION_RECONNECT(3, "网络异常后重连提示");
    
    private String desc;
    private int id;

    private BroadCastType(int i, String str) {
        this.id = i;
        this.desc = str;
    }

    public int getId() {
        return this.id;
    }

    public String getDesc() {
        return this.desc;
    }
}
