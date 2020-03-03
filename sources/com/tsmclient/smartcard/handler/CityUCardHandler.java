package com.tsmclient.smartcard.handler;

import android.nfc.tech.IsoDep;
import android.os.Bundle;
import android.text.TextUtils;
import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.tsmclient.smartcard.ByteArray;
import com.tsmclient.smartcard.CardConstants;
import com.tsmclient.smartcard.Coder;
import com.tsmclient.smartcard.ReaderUtil;
import com.tsmclient.smartcard.apdu.ReadBinaryCommand;
import com.tsmclient.smartcard.apdu.SelectCommand;
import com.tsmclient.smartcard.exception.UnProcessableCardException;
import com.tsmclient.smartcard.terminal.APDUConstants;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CityUCardHandler extends BaseTransCardHandler {
    private static final ByteArray CHONG_QING = ByteArray.wrap(new byte[]{64, 0});
    private static final ByteArray CITYU_AID = ByteArray.wrap(new byte[]{ScriptToolsConst.TagName.CommandSingle, 0, 0, 0, 3, Constants.TagName.ACTIVITY_TOTAL, Constants.TagName.PRODUCT_INFO, 7, 1});
    private static final ByteArray DONG_GUAN = ByteArray.wrap(new byte[]{Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, 48});
    private static final ByteArray GUI_YANG = ByteArray.wrap(new byte[]{Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0});
    private static final ByteArray HA_ER_BIN = ByteArray.wrap(new byte[]{21, 0});
    private static final ByteArray KUN_MING = ByteArray.wrap(new byte[]{Constants.TagName.ORDER_TYPE, 0});
    private static final ByteArray LAN_ZHOU = ByteArray.wrap(new byte[]{Constants.TagName.ELECTRONIC_TYPE, 0});
    private static final ByteArray NAN_CHANG = ByteArray.wrap(new byte[]{51, 0});
    private static final ByteArray NING_BO = ByteArray.wrap(new byte[]{49, Constants.TagName.ORDER_BRIEF_INFO_LIST});
    private static final ByteArray QING_DAO = ByteArray.wrap(new byte[]{Constants.TagName.QUERY_RECORD_COUNT, Constants.TagName.MAIN_ORDER});
    private static final ByteArray SHANG_HAI = ByteArray.wrap(new byte[]{32, 0});
    private static final String TAG = "CityUCardHandler";
    private static final ByteArray XI_AN = ByteArray.wrap(new byte[]{Constants.TagName.ELECTRONIC_TYPE_ID, 0});
    private static final ByteArray ZHENG_ZHOU = ByteArray.wrap(new byte[]{Constants.TagName.TERMINAL_MODEL_NUMBER, 0});
    private static final ByteArray ZHOU_SHAN = ByteArray.wrap(new byte[]{49, Constants.TagName.MAIN_ORDER});
    private ByteArray mAid;
    private String mCardId;

    public CityUCardHandler(String str) {
        this.mCardId = str;
        if ("SPTC".equals(str)) {
            this.mAid = APDUConstants.AID_SPTC;
        } else if ("SPTC_NEW".equals(str)) {
            this.mAid = APDUConstants.AID_SPTC_NEW;
        }
    }

    public Bundle onHandleCard(IsoDep isoDep) throws IOException, UnProcessableCardException {
        return super.onHandleCard(isoDep);
    }

    /* access modifiers changed from: protected */
    public Bundle doHandleCard(Bundle bundle) throws IOException, UnProcessableCardException {
        ArrayList arrayList = new ArrayList();
        Bundle bundle2 = new Bundle();
        selectVerify();
        Map<String, String> cardNumAndValidDate = getCardNumAndValidDate();
        if (cardNumAndValidDate != null) {
            otherVerify();
            int balance = getBalance();
            if (!bundle.getBoolean(ISmartCardHandler.KEY_READ_CARD_OPTION_SKIP_RECORD, false)) {
                if (TextUtils.equals(this.mCardId, CardConstants.QINGDAO)) {
                    readRecord(arrayList, true);
                } else if (this.mAid != null) {
                    readRecord(arrayList, true, (byte) 4, (byte) 0, false);
                } else {
                    readRecord(arrayList, false);
                }
            }
            if (TextUtils.equals(this.mCardId, CardConstants.CQTK)) {
                cardNumAndValidDate.put(CardConstants.KEY_ACCOUNT_NUM, readCQCardNum());
            }
            bundle2.putBoolean("success", true);
            bundle2.putInt("card_type", 2);
            bundle2.putString("card_id", getCardType());
            bundle2.putString(CardConstants.KEY_ACCOUNT_NUM, cardNumAndValidDate.get(CardConstants.KEY_ACCOUNT_NUM));
            bundle2.putString(CardConstants.VALID_START, cardNumAndValidDate.get(CardConstants.VALID_START));
            bundle2.putString(CardConstants.VALID_END, cardNumAndValidDate.get(CardConstants.VALID_END));
            bundle2.putInt(CardConstants.E_BALANCE, balance);
            bundle2.putParcelableArrayList(CardConstants.TRADE_LOG, arrayList);
            bundle2.putString(CardConstants.KEY_ACCOUNT_REAL_NUM, cardNumAndValidDate.get(CardConstants.KEY_ACCOUNT_REAL_NUM));
            return bundle2;
        }
        throw new UnProcessableCardException("CityUCardHandler: unsupported card type");
    }

    /* access modifiers changed from: protected */
    public String getCardType() {
        return this.mCardId;
    }

    /* access modifiers changed from: protected */
    public void selectVerify() throws IOException, UnProcessableCardException {
        SelectCommand selectCommand = new SelectCommand();
        selectCommand.setP1((byte) 4);
        if (this.mInternalRead) {
            selectCommand.setData(this.mAid);
        } else {
            selectCommand.setData(CITYU_AID);
        }
        byte[] transceive = transceive(selectCommand.toRawAPDU().toBytes());
        StringBuilder sb = new StringBuilder();
        sb.append("failed to select ");
        sb.append(this.mInternalRead ? this.mAid : "CityU AID");
        assertResponse(transceive, sb.toString());
    }

    /* access modifiers changed from: protected */
    public Map<String, String> getCardNumAndValidDate() throws IOException, UnProcessableCardException {
        String str;
        String str2;
        String str3;
        HashMap hashMap = new HashMap();
        ReadBinaryCommand readBinaryCommand = new ReadBinaryCommand();
        readBinaryCommand.setP1(Constants.TagName.PREDEPOSIT_TYPE);
        byte[] transceive = transceive(readBinaryCommand.toRawAPDU().toBytes());
        assertResponse(transceive, "failed to get card num");
        ByteArray wrap = ByteArray.wrap(transceive, 2, 2);
        if (this.mAid == null || ByteArray.equals(wrap, SHANG_HAI)) {
            String str4 = null;
            if (ByteArray.equals(wrap, SHANG_HAI)) {
                if (this.mAid == null) {
                    this.mCardId = "SPTC";
                }
                str4 = ByteArray.wrap(transceive, 12, 8).toString();
                str2 = getCardNumByLuhm(ByteArray.wrap(transceive, 16, 4));
                str = getDateString(transceive, 20, 4);
                str3 = getDateString(transceive, 24, 4);
            } else if (ByteArray.equals(wrap, CHONG_QING)) {
                this.mCardId = CardConstants.CQTK;
                String bytesToHexString = Coder.bytesToHexString(ByteArray.wrap(transceive, 4, 4).toBytes());
                str3 = Coder.bytesToHexString(ByteArray.wrap(transceive, 8, 4).toBytes());
                str = bytesToHexString;
                str2 = null;
            } else if (ByteArray.equals(wrap, LAN_ZHOU) || ByteArray.equals(wrap, GUI_YANG) || ByteArray.equals(wrap, NING_BO)) {
                if (ByteArray.equals(wrap, LAN_ZHOU)) {
                    this.mCardId = CardConstants.LANZHOU;
                } else if (ByteArray.equals(wrap, GUI_YANG)) {
                    this.mCardId = CardConstants.GUIYANG;
                } else if (ByteArray.equals(wrap, NING_BO)) {
                    this.mCardId = CardConstants.NINGBO;
                }
                str2 = Coder.bytesToHexString(ByteArray.wrap(transceive, 12, 8).toBytes());
                str = getDateString(transceive, 20, 4);
                str3 = getDateString(transceive, 24, 4);
            } else if (ByteArray.equals(wrap, ZHENG_ZHOU)) {
                this.mCardId = CardConstants.ZHENGZHOU;
                str2 = Coder.bytesToHexString(ByteArray.wrap(transceive, 14, 6).toBytes());
                str = getDateString(transceive, 20, 4);
                str3 = getDateString(transceive, 24, 4);
            } else if (ByteArray.equals(wrap, XI_AN) || ByteArray.equals(wrap, HA_ER_BIN) || ByteArray.equals(wrap, QING_DAO)) {
                if (ByteArray.equals(wrap, XI_AN)) {
                    this.mCardId = CardConstants.XIAN;
                } else if (ByteArray.equals(wrap, HA_ER_BIN)) {
                    this.mCardId = CardConstants.HAERBIN;
                } else if (ByteArray.equals(wrap, QING_DAO)) {
                    this.mCardId = CardConstants.QINGDAO;
                }
                str2 = hexToInt(ByteArray.wrap(transceive, 16, 4), 8);
                str = getDateString(transceive, 20, 4);
                str3 = getDateString(transceive, 24, 4);
            } else if (ByteArray.equals(wrap, KUN_MING)) {
                this.mCardId = CardConstants.KUNMING;
                str2 = hexInvertAndToInt(ByteArray.wrap(transceive, 16, 4).toBytes());
                str = getDateString(transceive, 20, 4);
                str3 = getDateString(transceive, 24, 4);
            } else if (ByteArray.equals(wrap, NAN_CHANG)) {
                this.mCardId = CardConstants.NANCHANG;
                str2 = hexToInt(ByteArray.wrap(transceive, 16, 4), 8);
                str3 = null;
                str = null;
            } else if (ByteArray.equals(wrap, ZHOU_SHAN)) {
                this.mCardId = CardConstants.ZHOUSHAN;
                str2 = Coder.bytesToHexString(ByteArray.wrap(transceive, 10, 10).toBytes());
                str = getDateString(transceive, 20, 4);
                str3 = getDateString(transceive, 24, 4);
            } else {
                this.mCardId = CardConstants.CITYU;
                str3 = null;
                str2 = null;
                str = null;
            }
            hashMap.put(CardConstants.KEY_ACCOUNT_NUM, str2);
            hashMap.put(CardConstants.VALID_START, str);
            hashMap.put(CardConstants.VALID_END, str3);
            hashMap.put(CardConstants.KEY_ACCOUNT_REAL_NUM, str4);
            return hashMap;
        }
        throw new UnProcessableCardException(this.mCardId + " isn't supported");
    }

    private String readCQCardNum() throws IOException, UnProcessableCardException {
        SelectCommand selectCommand = new SelectCommand();
        selectCommand.setP1((byte) 4);
        selectCommand.setData(AID_PSE);
        assertResponse(transceive(selectCommand.toRawAPDU().toBytes()), "failed to select CQCard AID");
        ReadBinaryCommand readBinaryCommand = new ReadBinaryCommand();
        readBinaryCommand.setP1(Constants.TagName.ACTIVITY_END);
        byte[] transceive = transceive(readBinaryCommand.toRawAPDU().toBytes());
        assertResponse(transceive, "failed to get card num");
        return Coder.bytesToHexString(ByteArray.wrap(transceive, 12, 8).toBytes());
    }

    private String getDateString(byte[] bArr, int i, int i2) {
        return Coder.bytesToHexString(ByteArray.wrap(bArr, i, i2).toBytes());
    }

    private String hexToIntAndInvert(ByteArray byteArray) {
        String format = String.format("%010d", new Object[]{Integer.valueOf(Coder.bytesToInt(byteArray.toBytes()))});
        if (Integer.parseInt(format) < 0) {
            return null;
        }
        return ReaderUtil.invertString(format, 2);
    }

    private String hexToUnsignedIntAndInvert(ByteArray byteArray) {
        return ReaderUtil.invertString(String.format("%010d", new Object[]{Long.valueOf(Long.parseLong(byteArray.toString(), 16))}), 2);
    }

    public String getCardNumByLuhm(ByteArray byteArray) {
        String hexToUnsignedIntAndInvert = hexToUnsignedIntAndInvert(byteArray);
        if (TextUtils.isEmpty(hexToUnsignedIntAndInvert)) {
            return null;
        }
        char[] charArray = hexToUnsignedIntAndInvert.toCharArray();
        int length = charArray.length - 1;
        int i = 0;
        int i2 = 0;
        while (length >= 0) {
            int parseInt = Integer.parseInt(charArray[length] + "");
            if (i % 2 == 0 && (parseInt = parseInt * 2) > 9) {
                parseInt -= 9;
            }
            i2 += parseInt;
            length--;
            i++;
        }
        return ((10 - (i2 % 10)) % 10) + hexToUnsignedIntAndInvert;
    }

    private String hexInvertAndToInt(byte... bArr) {
        return String.format("%010d", new Object[]{Integer.valueOf(Coder.bytesToInt(Coder.str2Bcd(ReaderUtil.invertString(Coder.bytesToHexString(bArr), 2))))});
    }

    private String hexToInt(ByteArray byteArray, int i) {
        return String.format("%0" + i + "d", new Object[]{Integer.valueOf(Coder.bytesToInt(byteArray.toBytes()))});
    }

    /* access modifiers changed from: protected */
    public ByteArray getConsumeTag() {
        if (TextUtils.equals(this.mCardId, CardConstants.XIAN)) {
            return ByteArray.wrap(new byte[]{6, 9});
        }
        return super.getConsumeTag();
    }
}
