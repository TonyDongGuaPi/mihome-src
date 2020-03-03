package cn.com.fmsh.tsm.business;

import cn.com.fmsh.tsm.business.bean.Activity;
import cn.com.fmsh.tsm.business.bean.BusinessOrder;
import cn.com.fmsh.tsm.business.bean.CardAppInfo;
import cn.com.fmsh.tsm.business.bean.CardAppRecord;
import cn.com.fmsh.tsm.business.bean.CardBusinessStatus;
import cn.com.fmsh.tsm.business.bean.InvoiceToken;
import cn.com.fmsh.tsm.business.bean.LoginInfo;
import cn.com.fmsh.tsm.business.bean.MainOrder;
import cn.com.fmsh.tsm.business.bean.Notice;
import cn.com.fmsh.tsm.business.bean.PasswordPrompt;
import cn.com.fmsh.tsm.business.bean.PayOrder;
import cn.com.fmsh.tsm.business.bean.PreDepositInfo;
import cn.com.fmsh.tsm.business.bean.Product;
import cn.com.fmsh.tsm.business.bean.TerminalBackInfo;
import cn.com.fmsh.tsm.business.bean.TicketOperateResult;
import cn.com.fmsh.tsm.business.bean.UserInfo;
import cn.com.fmsh.tsm.business.bean.VersionInfo;
import cn.com.fmsh.tsm.business.enums.EnumBusinessOrderType;
import cn.com.fmsh.tsm.business.enums.EnumCardAppType;
import cn.com.fmsh.tsm.business.enums.EnumOrderStatus;
import cn.com.fmsh.tsm.business.enums.EnumOrderType;
import cn.com.fmsh.tsm.business.enums.EnumResultsSortType;
import cn.com.fmsh.tsm.business.enums.EnumUserPlatformType;
import cn.com.fmsh.tsm.business.exception.BusinessException;
import java.util.List;

public interface CardAppTrade {
    MainOrder apply4Pay(int i, int i2, byte[] bArr, EnumCardAppType enumCardAppType) throws BusinessException;

    MainOrder applyAct4Pay(byte[] bArr, EnumCardAppType enumCardAppType, byte[] bArr2) throws BusinessException;

    TicketOperateResult buyTicket(String str, byte[] bArr) throws BusinessException;

    int deleteTerminalInfoBack(byte[] bArr) throws BusinessException;

    int doRefund(byte[] bArr) throws BusinessException;

    int doUnsolvedOrder(byte[] bArr, byte[] bArr2) throws BusinessException;

    byte[] getAppNo(EnumCardAppType enumCardAppType) throws BusinessException;

    Integer getBalance(EnumCardAppType enumCardAppType) throws BusinessException;

    CardAppInfo getCardAppInfo(int i, EnumCardAppType enumCardAppType) throws BusinessException;

    List<CardAppRecord> getCardAppRecords(EnumCardAppType enumCardAppType) throws BusinessException;

    EnumCardAppType getCardAppType() throws BusinessException;

    List<EnumCardAppType> getCardAppTypes() throws BusinessException;

    String getFaceID(EnumCardAppType enumCardAppType) throws BusinessException;

    String getInvoiceToken(byte[] bArr) throws BusinessException;

    List<InvoiceToken> getInvoiceTokenVer3() throws BusinessException;

    String getMOC(EnumCardAppType enumCardAppType) throws BusinessException;

    List<Notice> getNotices(int i) throws BusinessException;

    String getTime4Validity(EnumCardAppType enumCardAppType) throws BusinessException;

    boolean isLock4Consume(EnumCardAppType enumCardAppType) throws BusinessException;

    boolean isLock4Load(EnumCardAppType enumCardAppType) throws BusinessException;

    boolean isRun4plateform() throws BusinessException;

    LoginInfo login(String str, String str2) throws BusinessException;

    LoginInfo loginVer2(String str, String str2) throws BusinessException;

    LoginInfo loginVer3(String str, String str2, EnumUserPlatformType enumUserPlatformType, String str3) throws BusinessException;

    int logout() throws BusinessException;

    int modifyPassword(String str, String str2) throws BusinessException;

    int modifyUserInfo(UserInfo userInfo) throws BusinessException;

    int modifyUserInfoVer2(UserInfo userInfo) throws BusinessException;

    List<Activity> queryActivities(EnumCardAppType enumCardAppType) throws BusinessException;

    BusinessOrder queryBusinessOrder(byte[] bArr) throws BusinessException;

    List<BusinessOrder> queryBusinessOrders(int i, int i2, EnumCardAppType enumCardAppType, EnumBusinessOrderType enumBusinessOrderType, EnumOrderStatus enumOrderStatus) throws BusinessException;

    List<BusinessOrder> queryBusinessOrdersVer3(int i, int i2, EnumCardAppType enumCardAppType, EnumBusinessOrderType enumBusinessOrderType, EnumOrderStatus enumOrderStatus, byte[] bArr) throws BusinessException;

    List<BusinessOrder> queryBusinessOrdersVer4(int i, int i2, EnumCardAppType enumCardAppType, EnumBusinessOrderType enumBusinessOrderType, List<EnumOrderStatus> list, byte[] bArr) throws BusinessException;

    CardBusinessStatus queryCardBusinessStatus(String str) throws BusinessException;

    List<BusinessOrder> queryConfirmDoubtOrder(EnumCardAppType enumCardAppType) throws BusinessException;

    TicketOperateResult queryLastOperate(String str, String str2, byte[] bArr) throws BusinessException;

    MainOrder queryMainOrder(byte[] bArr) throws BusinessException;

    List<MainOrder> queryMainOrders(int i, int i2, EnumOrderStatus enumOrderStatus, EnumCardAppType enumCardAppType) throws BusinessException;

    List<MainOrder> queryMainOrdersVer4(int i, int i2, List<EnumOrderStatus> list, EnumCardAppType enumCardAppType) throws BusinessException;

    PayOrder queryPayOrder(byte[] bArr) throws BusinessException;

    List<PayOrder> queryPayOrders(int i, int i2, EnumCardAppType enumCardAppType) throws BusinessException;

    List<PayOrder> queryPayOrdersVer4(int i, int i2, EnumCardAppType enumCardAppType) throws BusinessException;

    PreDepositInfo queryPreDeposit(EnumCardAppType enumCardAppType) throws BusinessException;

    List<PreDepositInfo> queryPreDepositVer2(EnumCardAppType enumCardAppType) throws BusinessException;

    Product queryProduct(String str) throws BusinessException;

    List<Product> queryProducts(String str, EnumCardAppType enumCardAppType, byte[] bArr) throws BusinessException;

    List<TerminalBackInfo> queryTerminalInfoBack(byte[] bArr, byte[] bArr2, int i, EnumResultsSortType enumResultsSortType) throws BusinessException;

    List<BusinessOrder> queryUnsolvedOrder(EnumCardAppType enumCardAppType) throws BusinessException;

    UserInfo queryUserInfo(String str) throws BusinessException;

    UserInfo queryUserInfoVer2(String str) throws BusinessException;

    VersionInfo queryVersion() throws BusinessException;

    int register(UserInfo userInfo) throws BusinessException;

    int registerVer2(UserInfo userInfo) throws BusinessException;

    int registerVer3(UserInfo userInfo) throws BusinessException;

    boolean remoteRecharge(byte[] bArr, byte[] bArr2) throws BusinessException;

    byte[] rentBusinessHandle(int i, byte[] bArr) throws BusinessException;

    PasswordPrompt retrievePassword(String str) throws BusinessException;

    PasswordPrompt retrievePasswordVer2(String str, byte[] bArr, String str2) throws BusinessException;

    boolean returnBusiness(String str, byte[] bArr) throws BusinessException;

    int setOrderStatus(byte[] bArr, EnumOrderType enumOrderType, byte[] bArr2, EnumOrderStatus enumOrderStatus) throws BusinessException;

    int terminalInfoBack(TerminalBackInfo terminalBackInfo) throws BusinessException;

    byte[] terminalInfoReport(int i, byte[] bArr) throws BusinessException;

    int updateStationInfo() throws BusinessException;
}
