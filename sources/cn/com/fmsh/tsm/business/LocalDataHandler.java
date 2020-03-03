package cn.com.fmsh.tsm.business;

import cn.com.fmsh.tsm.business.bean.BusinessOrder;
import cn.com.fmsh.tsm.business.bean.Notice;
import cn.com.fmsh.tsm.business.bean.StationInfo;
import java.util.ArrayList;
import java.util.List;

public interface LocalDataHandler {
    void deleteHistoryByFaceId(String str);

    void deleteHistoryById(String str);

    void deleteNotiecById(long j);

    String[] findAllFaceId();

    ArrayList<Notice> findAllNotice();

    BusinessOrder findRechargeRecordByInfo(String str, String str2, String str3, String str4);

    BusinessOrder findRechargeRecordByOrder(String str);

    int getMaxNoticeId();

    String getVersion4StationInfo();

    void insertHistory(String str, int i, int i2, String str2, int i3, int i4, String str3);

    void insertNotice(Notice notice);

    void insertTrade(String str, String str2, String str3, String str4, String str5, int i, String str6);

    int updateStationInfo(String str, List<StationInfo> list);
}
