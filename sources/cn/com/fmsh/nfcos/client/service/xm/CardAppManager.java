package cn.com.fmsh.nfcos.client.service.xm;

import android.nfc.Tag;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public interface CardAppManager extends IInterface {
    int applyBusiness(String str, byte[] bArr, String str2) throws RemoteException;

    int applyIssue(int i, int i2, int i3, byte[] bArr, String str, byte[] bArr2, NfcosMainOrder nfcosMainOrder) throws RemoteException;

    int applyIssueByProduct(int i, String str, int i2, byte[] bArr, String str2, byte[] bArr2, NfcosMainOrder nfcosMainOrder) throws RemoteException;

    int applyRecharge(int i, int i2, int i3, byte[] bArr, byte[] bArr2, NfcosMainOrder nfcosMainOrder) throws RemoteException;

    int applyRechargeEx(int i, int i2, int i3, byte[] bArr, byte[] bArr2) throws RemoteException;

    int buyTicket(byte[] bArr, TicketOperateResult ticketOperateResult) throws RemoteException;

    int cancelIssue(int i) throws RemoteException;

    int cancelOrder(byte[] bArr, byte[] bArr2) throws RemoteException;

    int closeApp(byte[] bArr, int i, byte[] bArr2, String str) throws RemoteException;

    int deleteApp(byte[] bArr, int i, byte[] bArr2, String str, CardAppInfo cardAppInfo) throws RemoteException;

    int doIssue(byte[] bArr, byte b, byte[] bArr2, byte[] bArr3) throws RemoteException;

    int doIssueEx(int i, int i2, int i3, byte[] bArr, String str, byte[] bArr2) throws RemoteException;

    int doIssueExByProduct(int i, String str, int i2, byte[] bArr, String str2, byte[] bArr2) throws RemoteException;

    int doRefund(byte[] bArr) throws RemoteException;

    int doUnsolvedOrder(byte[] bArr) throws RemoteException;

    int downloadApplet(int i, byte[] bArr, String str) throws RemoteException;

    int getAppIssueStatus(int i, CardAppStatus cardAppStatus) throws RemoteException;

    int getAppIssueStatusByPlatform(int i, byte[] bArr, String str, CardAppStatus cardAppStatus) throws RemoteException;

    int getInfo(int i, int i2, CardAppInfo cardAppInfo) throws RemoteException;

    int getInvoiceToken(byte[] bArr, InvoiceToken invoiceToken) throws RemoteException;

    int getMaxSlotNumber() throws RemoteException;

    int getTicketInfoBySlot(int i, STTicketInfo sTTicketInfo) throws RemoteException;

    int getTicketRecordsBySlot(int i, List<STTicketRecord> list) throws RemoteException;

    int login(String str, String str2, LoginInfo loginInfo) throws RemoteException;

    int logout() throws RemoteException;

    int modifyPassword(String str, String str2) throws RemoteException;

    int modifyUserInfo(UserInfo userInfo) throws RemoteException;

    int moveApp(byte[] bArr, int i, byte[] bArr2, String str, VoucherInfo voucherInfo) throws RemoteException;

    int openApp(byte[] bArr, int i, byte[] bArr2, String str) throws RemoteException;

    int paid4order(byte[] bArr, byte[] bArr2) throws RemoteException;

    int queryActivities(int i, List<NfcosActivity> list) throws RemoteException;

    int queryBusinessOrder(byte[] bArr, NfcosBusinessOrder nfcosBusinessOrder) throws RemoteException;

    int queryBusinessOrders(int i, int i2, int i3, int[] iArr, int i4, byte[] bArr, List<NfcosBusinessOrder> list) throws RemoteException;

    int queryLastOperate(String str, byte[] bArr, TicketOperateResult ticketOperateResult) throws RemoteException;

    int queryMainOrder(byte[] bArr, NfcosMainOrder nfcosMainOrder) throws RemoteException;

    int queryMainOrders(int i, int i2, int i3, int[] iArr, List<NfcosMainOrder> list) throws RemoteException;

    int queryPayOrder(byte[] bArr, NfcosPayOrder nfcosPayOrder) throws RemoteException;

    int queryPreDeposit(int i, PreDepositInfo preDepositInfo) throws RemoteException;

    int recharge(byte[] bArr, byte[] bArr2) throws RemoteException;

    int register(UserInfo userInfo) throws RemoteException;

    int returnTicket(byte[] bArr) throws RemoteException;

    int switchMode2NFC(Tag tag) throws RemoteException;

    int switchMode2OMA(int i, int i2) throws RemoteException;

    int useTheTicket(int i) throws RemoteException;

    int writeTicket(byte[] bArr) throws RemoteException;

    public static abstract class Stub extends Binder implements CardAppManager {
        private static final String DESCRIPTOR = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager";
        static final int TRANSACTION_applyBusiness = 37;
        static final int TRANSACTION_applyIssue = 16;
        static final int TRANSACTION_applyIssueByProduct = 17;
        static final int TRANSACTION_applyRecharge = 15;
        static final int TRANSACTION_applyRechargeEx = 26;
        static final int TRANSACTION_buyTicket = 38;
        static final int TRANSACTION_cancelIssue = 11;
        static final int TRANSACTION_cancelOrder = 20;
        static final int TRANSACTION_closeApp = 8;
        static final int TRANSACTION_deleteApp = 7;
        static final int TRANSACTION_doIssue = 3;
        static final int TRANSACTION_doIssueEx = 4;
        static final int TRANSACTION_doIssueExByProduct = 5;
        static final int TRANSACTION_doRefund = 28;
        static final int TRANSACTION_doUnsolvedOrder = 27;
        static final int TRANSACTION_downloadApplet = 6;
        static final int TRANSACTION_getAppIssueStatus = 12;
        static final int TRANSACTION_getAppIssueStatusByPlatform = 13;
        static final int TRANSACTION_getInfo = 14;
        static final int TRANSACTION_getInvoiceToken = 29;
        static final int TRANSACTION_getMaxSlotNumber = 42;
        static final int TRANSACTION_getTicketInfoBySlot = 43;
        static final int TRANSACTION_getTicketRecordsBySlot = 44;
        static final int TRANSACTION_login = 31;
        static final int TRANSACTION_logout = 32;
        static final int TRANSACTION_modifyPassword = 33;
        static final int TRANSACTION_modifyUserInfo = 34;
        static final int TRANSACTION_moveApp = 10;
        static final int TRANSACTION_openApp = 9;
        static final int TRANSACTION_paid4order = 19;
        static final int TRANSACTION_queryActivities = 35;
        static final int TRANSACTION_queryBusinessOrder = 22;
        static final int TRANSACTION_queryBusinessOrders = 23;
        static final int TRANSACTION_queryLastOperate = 41;
        static final int TRANSACTION_queryMainOrder = 21;
        static final int TRANSACTION_queryMainOrders = 18;
        static final int TRANSACTION_queryPayOrder = 24;
        static final int TRANSACTION_queryPreDeposit = 36;
        static final int TRANSACTION_recharge = 25;
        static final int TRANSACTION_register = 30;
        static final int TRANSACTION_returnTicket = 40;
        static final int TRANSACTION_switchMode2NFC = 2;
        static final int TRANSACTION_switchMode2OMA = 1;
        static final int TRANSACTION_useTheTicket = 45;
        static final int TRANSACTION_writeTicket = 39;

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static CardAppManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof CardAppManager)) {
                return new Proxy(iBinder);
            }
            return (CardAppManager) queryLocalInterface;
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v2, resolved type: android.nfc.Tag} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v9, resolved type: cn.com.fmsh.nfcos.client.service.xm.UserInfo} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v12, resolved type: cn.com.fmsh.nfcos.client.service.xm.UserInfo} */
        /* JADX WARNING: type inference failed for: r0v1 */
        /* JADX WARNING: type inference failed for: r0v15 */
        /* JADX WARNING: type inference failed for: r0v16 */
        /* JADX WARNING: type inference failed for: r0v17 */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean onTransact(int r11, android.os.Parcel r12, android.os.Parcel r13, int r14) throws android.os.RemoteException {
            /*
                r10 = this;
                r0 = 1598968902(0x5f4e5446, float:1.4867585E19)
                r1 = 1
                if (r11 == r0) goto L_0x059c
                r0 = 0
                switch(r11) {
                    case 1: goto L_0x0584;
                    case 2: goto L_0x0565;
                    case 3: goto L_0x0545;
                    case 4: goto L_0x051c;
                    case 5: goto L_0x04f3;
                    case 6: goto L_0x04d7;
                    case 7: goto L_0x04aa;
                    case 8: goto L_0x048a;
                    case 9: goto L_0x046a;
                    case 10: goto L_0x043d;
                    case 11: goto L_0x0429;
                    case 12: goto L_0x040a;
                    case 13: goto L_0x03e3;
                    case 14: goto L_0x03c0;
                    case 15: goto L_0x038f;
                    case 16: goto L_0x035a;
                    case 17: goto L_0x0325;
                    case 18: goto L_0x02fb;
                    case 19: goto L_0x02e3;
                    case 20: goto L_0x02cb;
                    case 21: goto L_0x02ac;
                    case 22: goto L_0x028d;
                    case 23: goto L_0x025b;
                    case 24: goto L_0x023c;
                    case 25: goto L_0x0224;
                    case 26: goto L_0x01ff;
                    case 27: goto L_0x01eb;
                    case 28: goto L_0x01d7;
                    case 29: goto L_0x01b8;
                    case 30: goto L_0x0199;
                    case 31: goto L_0x0176;
                    case 32: goto L_0x0166;
                    case 33: goto L_0x014e;
                    case 34: goto L_0x012f;
                    case 35: goto L_0x0113;
                    case 36: goto L_0x00f4;
                    case 37: goto L_0x00d8;
                    case 38: goto L_0x00b9;
                    case 39: goto L_0x00a5;
                    case 40: goto L_0x0091;
                    case 41: goto L_0x006e;
                    case 42: goto L_0x005e;
                    case 43: goto L_0x003f;
                    case 44: goto L_0x0023;
                    case 45: goto L_0x000f;
                    default: goto L_0x000a;
                }
            L_0x000a:
                boolean r11 = super.onTransact(r11, r12, r13, r14)
                return r11
            L_0x000f:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                int r11 = r10.useTheTicket(r11)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x0023:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                java.util.ArrayList r12 = new java.util.ArrayList
                r12.<init>()
                int r11 = r10.getTicketRecordsBySlot(r11, r12)
                r13.writeNoException()
                r13.writeInt(r11)
                r13.writeTypedList(r12)
                return r1
            L_0x003f:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                cn.com.fmsh.nfcos.client.service.xm.STTicketInfo r12 = new cn.com.fmsh.nfcos.client.service.xm.STTicketInfo
                r12.<init>()
                int r11 = r10.getTicketInfoBySlot(r11, r12)
                r13.writeNoException()
                r13.writeInt(r11)
                r13.writeInt(r1)
                r12.writeToParcel(r13, r1)
                return r1
            L_0x005e:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                int r11 = r10.getMaxSlotNumber()
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x006e:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                java.lang.String r11 = r12.readString()
                byte[] r12 = r12.createByteArray()
                cn.com.fmsh.nfcos.client.service.xm.TicketOperateResult r14 = new cn.com.fmsh.nfcos.client.service.xm.TicketOperateResult
                r14.<init>()
                int r11 = r10.queryLastOperate(r11, r12, r14)
                r13.writeNoException()
                r13.writeInt(r11)
                r13.writeInt(r1)
                r14.writeToParcel(r13, r1)
                return r1
            L_0x0091:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                byte[] r11 = r12.createByteArray()
                int r11 = r10.returnTicket(r11)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x00a5:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                byte[] r11 = r12.createByteArray()
                int r11 = r10.writeTicket(r11)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x00b9:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                byte[] r11 = r12.createByteArray()
                cn.com.fmsh.nfcos.client.service.xm.TicketOperateResult r12 = new cn.com.fmsh.nfcos.client.service.xm.TicketOperateResult
                r12.<init>()
                int r11 = r10.buyTicket(r11, r12)
                r13.writeNoException()
                r13.writeInt(r11)
                r13.writeInt(r1)
                r12.writeToParcel(r13, r1)
                return r1
            L_0x00d8:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                java.lang.String r11 = r12.readString()
                byte[] r14 = r12.createByteArray()
                java.lang.String r12 = r12.readString()
                int r11 = r10.applyBusiness(r11, r14, r12)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x00f4:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                cn.com.fmsh.nfcos.client.service.xm.PreDepositInfo r12 = new cn.com.fmsh.nfcos.client.service.xm.PreDepositInfo
                r12.<init>()
                int r11 = r10.queryPreDeposit(r11, r12)
                r13.writeNoException()
                r13.writeInt(r11)
                r13.writeInt(r1)
                r12.writeToParcel(r13, r1)
                return r1
            L_0x0113:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                java.util.ArrayList r12 = new java.util.ArrayList
                r12.<init>()
                int r11 = r10.queryActivities(r11, r12)
                r13.writeNoException()
                r13.writeInt(r11)
                r13.writeTypedList(r12)
                return r1
            L_0x012f:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x0143
                android.os.Parcelable$Creator<cn.com.fmsh.nfcos.client.service.xm.UserInfo> r11 = cn.com.fmsh.nfcos.client.service.xm.UserInfo.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r12)
                r0 = r11
                cn.com.fmsh.nfcos.client.service.xm.UserInfo r0 = (cn.com.fmsh.nfcos.client.service.xm.UserInfo) r0
            L_0x0143:
                int r11 = r10.modifyUserInfo(r0)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x014e:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                java.lang.String r11 = r12.readString()
                java.lang.String r12 = r12.readString()
                int r11 = r10.modifyPassword(r11, r12)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x0166:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                int r11 = r10.logout()
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x0176:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                java.lang.String r11 = r12.readString()
                java.lang.String r12 = r12.readString()
                cn.com.fmsh.nfcos.client.service.xm.LoginInfo r14 = new cn.com.fmsh.nfcos.client.service.xm.LoginInfo
                r14.<init>()
                int r11 = r10.login(r11, r12, r14)
                r13.writeNoException()
                r13.writeInt(r11)
                r13.writeInt(r1)
                r14.writeToParcel(r13, r1)
                return r1
            L_0x0199:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x01ad
                android.os.Parcelable$Creator<cn.com.fmsh.nfcos.client.service.xm.UserInfo> r11 = cn.com.fmsh.nfcos.client.service.xm.UserInfo.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r12)
                r0 = r11
                cn.com.fmsh.nfcos.client.service.xm.UserInfo r0 = (cn.com.fmsh.nfcos.client.service.xm.UserInfo) r0
            L_0x01ad:
                int r11 = r10.register(r0)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x01b8:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                byte[] r11 = r12.createByteArray()
                cn.com.fmsh.nfcos.client.service.xm.InvoiceToken r12 = new cn.com.fmsh.nfcos.client.service.xm.InvoiceToken
                r12.<init>()
                int r11 = r10.getInvoiceToken(r11, r12)
                r13.writeNoException()
                r13.writeInt(r11)
                r13.writeInt(r1)
                r12.writeToParcel(r13, r1)
                return r1
            L_0x01d7:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                byte[] r11 = r12.createByteArray()
                int r11 = r10.doRefund(r11)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x01eb:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                byte[] r11 = r12.createByteArray()
                int r11 = r10.doUnsolvedOrder(r11)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x01ff:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                int r3 = r12.readInt()
                int r4 = r12.readInt()
                int r5 = r12.readInt()
                byte[] r6 = r12.createByteArray()
                byte[] r7 = r12.createByteArray()
                r2 = r10
                int r11 = r2.applyRechargeEx(r3, r4, r5, r6, r7)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x0224:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                byte[] r11 = r12.createByteArray()
                byte[] r12 = r12.createByteArray()
                int r11 = r10.recharge(r11, r12)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x023c:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                byte[] r11 = r12.createByteArray()
                cn.com.fmsh.nfcos.client.service.xm.NfcosPayOrder r12 = new cn.com.fmsh.nfcos.client.service.xm.NfcosPayOrder
                r12.<init>()
                int r11 = r10.queryPayOrder(r11, r12)
                r13.writeNoException()
                r13.writeInt(r11)
                r13.writeInt(r1)
                r12.writeToParcel(r13, r1)
                return r1
            L_0x025b:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                int r3 = r12.readInt()
                int r4 = r12.readInt()
                int r5 = r12.readInt()
                int[] r6 = r12.createIntArray()
                int r7 = r12.readInt()
                byte[] r8 = r12.createByteArray()
                java.util.ArrayList r11 = new java.util.ArrayList
                r11.<init>()
                r2 = r10
                r9 = r11
                int r12 = r2.queryBusinessOrders(r3, r4, r5, r6, r7, r8, r9)
                r13.writeNoException()
                r13.writeInt(r12)
                r13.writeTypedList(r11)
                return r1
            L_0x028d:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                byte[] r11 = r12.createByteArray()
                cn.com.fmsh.nfcos.client.service.xm.NfcosBusinessOrder r12 = new cn.com.fmsh.nfcos.client.service.xm.NfcosBusinessOrder
                r12.<init>()
                int r11 = r10.queryBusinessOrder(r11, r12)
                r13.writeNoException()
                r13.writeInt(r11)
                r13.writeInt(r1)
                r12.writeToParcel(r13, r1)
                return r1
            L_0x02ac:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                byte[] r11 = r12.createByteArray()
                cn.com.fmsh.nfcos.client.service.xm.NfcosMainOrder r12 = new cn.com.fmsh.nfcos.client.service.xm.NfcosMainOrder
                r12.<init>()
                int r11 = r10.queryMainOrder(r11, r12)
                r13.writeNoException()
                r13.writeInt(r11)
                r13.writeInt(r1)
                r12.writeToParcel(r13, r1)
                return r1
            L_0x02cb:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                byte[] r11 = r12.createByteArray()
                byte[] r12 = r12.createByteArray()
                int r11 = r10.cancelOrder(r11, r12)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x02e3:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                byte[] r11 = r12.createByteArray()
                byte[] r12 = r12.createByteArray()
                int r11 = r10.paid4order(r11, r12)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x02fb:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                int r3 = r12.readInt()
                int r4 = r12.readInt()
                int r5 = r12.readInt()
                int[] r6 = r12.createIntArray()
                java.util.ArrayList r11 = new java.util.ArrayList
                r11.<init>()
                r2 = r10
                r7 = r11
                int r12 = r2.queryMainOrders(r3, r4, r5, r6, r7)
                r13.writeNoException()
                r13.writeInt(r12)
                r13.writeTypedList(r11)
                return r1
            L_0x0325:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                int r3 = r12.readInt()
                java.lang.String r4 = r12.readString()
                int r5 = r12.readInt()
                byte[] r6 = r12.createByteArray()
                java.lang.String r7 = r12.readString()
                byte[] r8 = r12.createByteArray()
                cn.com.fmsh.nfcos.client.service.xm.NfcosMainOrder r11 = new cn.com.fmsh.nfcos.client.service.xm.NfcosMainOrder
                r11.<init>()
                r2 = r10
                r9 = r11
                int r12 = r2.applyIssueByProduct(r3, r4, r5, r6, r7, r8, r9)
                r13.writeNoException()
                r13.writeInt(r12)
                r13.writeInt(r1)
                r11.writeToParcel(r13, r1)
                return r1
            L_0x035a:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                int r3 = r12.readInt()
                int r4 = r12.readInt()
                int r5 = r12.readInt()
                byte[] r6 = r12.createByteArray()
                java.lang.String r7 = r12.readString()
                byte[] r8 = r12.createByteArray()
                cn.com.fmsh.nfcos.client.service.xm.NfcosMainOrder r11 = new cn.com.fmsh.nfcos.client.service.xm.NfcosMainOrder
                r11.<init>()
                r2 = r10
                r9 = r11
                int r12 = r2.applyIssue(r3, r4, r5, r6, r7, r8, r9)
                r13.writeNoException()
                r13.writeInt(r12)
                r13.writeInt(r1)
                r11.writeToParcel(r13, r1)
                return r1
            L_0x038f:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                int r3 = r12.readInt()
                int r4 = r12.readInt()
                int r5 = r12.readInt()
                byte[] r6 = r12.createByteArray()
                byte[] r7 = r12.createByteArray()
                cn.com.fmsh.nfcos.client.service.xm.NfcosMainOrder r11 = new cn.com.fmsh.nfcos.client.service.xm.NfcosMainOrder
                r11.<init>()
                r2 = r10
                r8 = r11
                int r12 = r2.applyRecharge(r3, r4, r5, r6, r7, r8)
                r13.writeNoException()
                r13.writeInt(r12)
                r13.writeInt(r1)
                r11.writeToParcel(r13, r1)
                return r1
            L_0x03c0:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                int r12 = r12.readInt()
                cn.com.fmsh.nfcos.client.service.xm.CardAppInfo r14 = new cn.com.fmsh.nfcos.client.service.xm.CardAppInfo
                r14.<init>()
                int r11 = r10.getInfo(r11, r12, r14)
                r13.writeNoException()
                r13.writeInt(r11)
                r13.writeInt(r1)
                r14.writeToParcel(r13, r1)
                return r1
            L_0x03e3:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                byte[] r14 = r12.createByteArray()
                java.lang.String r12 = r12.readString()
                cn.com.fmsh.nfcos.client.service.xm.CardAppStatus r0 = new cn.com.fmsh.nfcos.client.service.xm.CardAppStatus
                r0.<init>()
                int r11 = r10.getAppIssueStatusByPlatform(r11, r14, r12, r0)
                r13.writeNoException()
                r13.writeInt(r11)
                r13.writeInt(r1)
                r0.writeToParcel(r13, r1)
                return r1
            L_0x040a:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                cn.com.fmsh.nfcos.client.service.xm.CardAppStatus r12 = new cn.com.fmsh.nfcos.client.service.xm.CardAppStatus
                r12.<init>()
                int r11 = r10.getAppIssueStatus(r11, r12)
                r13.writeNoException()
                r13.writeInt(r11)
                r13.writeInt(r1)
                r12.writeToParcel(r13, r1)
                return r1
            L_0x0429:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                int r11 = r10.cancelIssue(r11)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x043d:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                byte[] r3 = r12.createByteArray()
                int r4 = r12.readInt()
                byte[] r5 = r12.createByteArray()
                java.lang.String r6 = r12.readString()
                cn.com.fmsh.nfcos.client.service.xm.VoucherInfo r11 = new cn.com.fmsh.nfcos.client.service.xm.VoucherInfo
                r11.<init>()
                r2 = r10
                r7 = r11
                int r12 = r2.moveApp(r3, r4, r5, r6, r7)
                r13.writeNoException()
                r13.writeInt(r12)
                r13.writeInt(r1)
                r11.writeToParcel(r13, r1)
                return r1
            L_0x046a:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                byte[] r11 = r12.createByteArray()
                int r14 = r12.readInt()
                byte[] r0 = r12.createByteArray()
                java.lang.String r12 = r12.readString()
                int r11 = r10.openApp(r11, r14, r0, r12)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x048a:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                byte[] r11 = r12.createByteArray()
                int r14 = r12.readInt()
                byte[] r0 = r12.createByteArray()
                java.lang.String r12 = r12.readString()
                int r11 = r10.closeApp(r11, r14, r0, r12)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x04aa:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                byte[] r3 = r12.createByteArray()
                int r4 = r12.readInt()
                byte[] r5 = r12.createByteArray()
                java.lang.String r6 = r12.readString()
                cn.com.fmsh.nfcos.client.service.xm.CardAppInfo r11 = new cn.com.fmsh.nfcos.client.service.xm.CardAppInfo
                r11.<init>()
                r2 = r10
                r7 = r11
                int r12 = r2.deleteApp(r3, r4, r5, r6, r7)
                r13.writeNoException()
                r13.writeInt(r12)
                r13.writeInt(r1)
                r11.writeToParcel(r13, r1)
                return r1
            L_0x04d7:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                byte[] r14 = r12.createByteArray()
                java.lang.String r12 = r12.readString()
                int r11 = r10.downloadApplet(r11, r14, r12)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x04f3:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                int r3 = r12.readInt()
                java.lang.String r4 = r12.readString()
                int r5 = r12.readInt()
                byte[] r6 = r12.createByteArray()
                java.lang.String r7 = r12.readString()
                byte[] r8 = r12.createByteArray()
                r2 = r10
                int r11 = r2.doIssueExByProduct(r3, r4, r5, r6, r7, r8)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x051c:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                int r3 = r12.readInt()
                int r4 = r12.readInt()
                int r5 = r12.readInt()
                byte[] r6 = r12.createByteArray()
                java.lang.String r7 = r12.readString()
                byte[] r8 = r12.createByteArray()
                r2 = r10
                int r11 = r2.doIssueEx(r3, r4, r5, r6, r7, r8)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x0545:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                byte[] r11 = r12.createByteArray()
                byte r14 = r12.readByte()
                byte[] r0 = r12.createByteArray()
                byte[] r12 = r12.createByteArray()
                int r11 = r10.doIssue(r11, r14, r0, r12)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x0565:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                if (r11 == 0) goto L_0x0579
                android.os.Parcelable$Creator r11 = android.nfc.Tag.CREATOR
                java.lang.Object r11 = r11.createFromParcel(r12)
                r0 = r11
                android.nfc.Tag r0 = (android.nfc.Tag) r0
            L_0x0579:
                int r11 = r10.switchMode2NFC(r0)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x0584:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r12.enforceInterface(r11)
                int r11 = r12.readInt()
                int r12 = r12.readInt()
                int r11 = r10.switchMode2OMA(r11, r12)
                r13.writeNoException()
                r13.writeInt(r11)
                return r1
            L_0x059c:
                java.lang.String r11 = "cn.com.fmsh.nfcos.client.service.xm.CardAppManager"
                r13.writeString(r11)
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.com.fmsh.nfcos.client.service.xm.CardAppManager.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        private static class Proxy implements CardAppManager {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public int switchMode2OMA(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int switchMode2NFC(Tag tag) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (tag != null) {
                        obtain.writeInt(1);
                        tag.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int doIssue(byte[] bArr, byte b, byte[] bArr2, byte[] bArr3) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    obtain.writeByte(b);
                    obtain.writeByteArray(bArr2);
                    obtain.writeByteArray(bArr3);
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int doIssueEx(int i, int i2, int i3, byte[] bArr, String str, byte[] bArr2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeByteArray(bArr);
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr2);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int doIssueExByProduct(int i, String str, int i2, byte[] bArr, String str2, byte[] bArr2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    obtain.writeByteArray(bArr);
                    obtain.writeString(str2);
                    obtain.writeByteArray(bArr2);
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int downloadApplet(int i, byte[] bArr, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeByteArray(bArr);
                    obtain.writeString(str);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int deleteApp(byte[] bArr, int i, byte[] bArr2, String str, CardAppInfo cardAppInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(i);
                    obtain.writeByteArray(bArr2);
                    obtain.writeString(str);
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    if (obtain2.readInt() != 0) {
                        cardAppInfo.readFromParcel(obtain2);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int closeApp(byte[] bArr, int i, byte[] bArr2, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(i);
                    obtain.writeByteArray(bArr2);
                    obtain.writeString(str);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int openApp(byte[] bArr, int i, byte[] bArr2, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(i);
                    obtain.writeByteArray(bArr2);
                    obtain.writeString(str);
                    this.mRemote.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int moveApp(byte[] bArr, int i, byte[] bArr2, String str, VoucherInfo voucherInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    obtain.writeInt(i);
                    obtain.writeByteArray(bArr2);
                    obtain.writeString(str);
                    this.mRemote.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    if (obtain2.readInt() != 0) {
                        voucherInfo.readFromParcel(obtain2);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int cancelIssue(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(11, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getAppIssueStatus(int i, CardAppStatus cardAppStatus) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(12, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    if (obtain2.readInt() != 0) {
                        cardAppStatus.readFromParcel(obtain2);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getAppIssueStatusByPlatform(int i, byte[] bArr, String str, CardAppStatus cardAppStatus) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeByteArray(bArr);
                    obtain.writeString(str);
                    this.mRemote.transact(13, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    if (obtain2.readInt() != 0) {
                        cardAppStatus.readFromParcel(obtain2);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getInfo(int i, int i2, CardAppInfo cardAppInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(14, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    if (obtain2.readInt() != 0) {
                        cardAppInfo.readFromParcel(obtain2);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int applyRecharge(int i, int i2, int i3, byte[] bArr, byte[] bArr2, NfcosMainOrder nfcosMainOrder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeByteArray(bArr);
                    obtain.writeByteArray(bArr2);
                    this.mRemote.transact(15, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    if (obtain2.readInt() != 0) {
                        nfcosMainOrder.readFromParcel(obtain2);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int applyIssue(int i, int i2, int i3, byte[] bArr, String str, byte[] bArr2, NfcosMainOrder nfcosMainOrder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeByteArray(bArr);
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr2);
                    this.mRemote.transact(16, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    if (obtain2.readInt() != 0) {
                        nfcosMainOrder.readFromParcel(obtain2);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int applyIssueByProduct(int i, String str, int i2, byte[] bArr, String str2, byte[] bArr2, NfcosMainOrder nfcosMainOrder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    obtain.writeInt(i2);
                    obtain.writeByteArray(bArr);
                    obtain.writeString(str2);
                    obtain.writeByteArray(bArr2);
                    this.mRemote.transact(17, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    if (obtain2.readInt() != 0) {
                        nfcosMainOrder.readFromParcel(obtain2);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int queryMainOrders(int i, int i2, int i3, int[] iArr, List<NfcosMainOrder> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeIntArray(iArr);
                    this.mRemote.transact(18, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.readTypedList(list, NfcosMainOrder.CREATOR);
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int paid4order(byte[] bArr, byte[] bArr2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    obtain.writeByteArray(bArr2);
                    this.mRemote.transact(19, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int cancelOrder(byte[] bArr, byte[] bArr2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    obtain.writeByteArray(bArr2);
                    this.mRemote.transact(20, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int queryMainOrder(byte[] bArr, NfcosMainOrder nfcosMainOrder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(21, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    if (obtain2.readInt() != 0) {
                        nfcosMainOrder.readFromParcel(obtain2);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int queryBusinessOrder(byte[] bArr, NfcosBusinessOrder nfcosBusinessOrder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(22, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    if (obtain2.readInt() != 0) {
                        nfcosBusinessOrder.readFromParcel(obtain2);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int queryBusinessOrders(int i, int i2, int i3, int[] iArr, int i4, byte[] bArr, List<NfcosBusinessOrder> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeIntArray(iArr);
                    obtain.writeInt(i4);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(23, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.readTypedList(list, NfcosBusinessOrder.CREATOR);
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int queryPayOrder(byte[] bArr, NfcosPayOrder nfcosPayOrder) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(24, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    if (obtain2.readInt() != 0) {
                        nfcosPayOrder.readFromParcel(obtain2);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int recharge(byte[] bArr, byte[] bArr2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    obtain.writeByteArray(bArr2);
                    this.mRemote.transact(25, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int applyRechargeEx(int i, int i2, int i3, byte[] bArr, byte[] bArr2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeByteArray(bArr);
                    obtain.writeByteArray(bArr2);
                    this.mRemote.transact(26, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int doUnsolvedOrder(byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(27, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int doRefund(byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(28, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getInvoiceToken(byte[] bArr, InvoiceToken invoiceToken) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(29, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    if (obtain2.readInt() != 0) {
                        invoiceToken.readFromParcel(obtain2);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int register(UserInfo userInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (userInfo != null) {
                        obtain.writeInt(1);
                        userInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(30, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int login(String str, String str2, LoginInfo loginInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(31, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    if (obtain2.readInt() != 0) {
                        loginInfo.readFromParcel(obtain2);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int logout() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(32, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int modifyPassword(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    this.mRemote.transact(33, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int modifyUserInfo(UserInfo userInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (userInfo != null) {
                        obtain.writeInt(1);
                        userInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.mRemote.transact(34, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int queryActivities(int i, List<NfcosActivity> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(35, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.readTypedList(list, NfcosActivity.CREATOR);
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int queryPreDeposit(int i, PreDepositInfo preDepositInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(36, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    if (obtain2.readInt() != 0) {
                        preDepositInfo.readFromParcel(obtain2);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int applyBusiness(String str, byte[] bArr, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    obtain.writeString(str2);
                    this.mRemote.transact(37, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int buyTicket(byte[] bArr, TicketOperateResult ticketOperateResult) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(38, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    if (obtain2.readInt() != 0) {
                        ticketOperateResult.readFromParcel(obtain2);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int writeTicket(byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(39, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int returnTicket(byte[] bArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(40, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int queryLastOperate(String str, byte[] bArr, TicketOperateResult ticketOperateResult) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeByteArray(bArr);
                    this.mRemote.transact(41, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    if (obtain2.readInt() != 0) {
                        ticketOperateResult.readFromParcel(obtain2);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getMaxSlotNumber() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(42, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getTicketInfoBySlot(int i, STTicketInfo sTTicketInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(43, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    if (obtain2.readInt() != 0) {
                        sTTicketInfo.readFromParcel(obtain2);
                    }
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getTicketRecordsBySlot(int i, List<STTicketRecord> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(44, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    obtain2.readTypedList(list, STTicketRecord.CREATOR);
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int useTheTicket(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(45, obtain, obtain2, 0);
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
