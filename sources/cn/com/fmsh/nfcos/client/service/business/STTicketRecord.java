package cn.com.fmsh.nfcos.client.service.business;

public class STTicketRecord {
    private String date;
    private int inOutFlag;
    private String stationName;
    private String time;

    public int getInOutFlag() {
        return this.inOutFlag;
    }

    public void setInOutFlag(int i) {
        this.inOutFlag = i;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getStationName() {
        return this.stationName;
    }

    public void setStationName(String str) {
        this.stationName = str;
    }
}
