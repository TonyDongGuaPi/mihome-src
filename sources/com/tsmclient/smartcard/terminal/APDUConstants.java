package com.tsmclient.smartcard.terminal;

import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.google.android.exoplayer2.extractor.ts.PsExtractor;
import com.tsmclient.smartcard.ByteArray;
import org.apache.commons.compress.archivers.tar.TarConstants;

public class APDUConstants {
    public static final ByteArray AID_BMAC = ByteArray.wrap(new byte[]{-111, Constants.TagName.QUERY_DATA_SORT_TYPE, 0, 0, 20, 1, 0, 1});
    public static final ByteArray AID_CST = ByteArray.wrap(new byte[]{ScriptToolsConst.TagName.CommandSingle, 0, 0, 0, 3, Constants.TagName.ACTIVITY_TOTAL, Constants.TagName.PRODUCT_INFO, 7, 1, Constants.TagName.TERMINAL_BACK_CONTENT, 0});
    public static final ByteArray AID_FAKE_SPDB = ByteArray.wrap(new byte[]{ScriptToolsConst.TagName.CommandSingle, 0, 0, 3, 51, 1, 1, 1, -1, 32, 0, -1, -1, -1, -1, 0});
    public static final ByteArray AID_HZT = ByteArray.wrap(new byte[]{ScriptToolsConst.TagName.CommandSingle, 0, 0, 0, 3, Constants.TagName.ACTIVITY_TOTAL, Constants.TagName.PRODUCT_INFO, 7, 1, 49, 0});
    public static final ByteArray AID_LNT = ByteArray.wrap(new byte[]{89, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.SIM_SEID, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 83, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG});
    public static final ByteArray AID_SPTC = ByteArray.wrap(new byte[]{ScriptToolsConst.TagName.CommandSingle, 0, 0, 0, 3, Constants.TagName.ACTIVITY_TOTAL, Constants.TagName.PRODUCT_INFO, 7, 1});
    public static final ByteArray AID_SPTC_NEW = ByteArray.wrap(new byte[]{ScriptToolsConst.TagName.CommandSingle, 0, 0, 0, 3, Constants.TagName.ACTIVITY_TOTAL, Constants.TagName.PRODUCT_INFO, 7, 1});
    public static final ByteArray AID_ST_ONE_DAY_PASS = ByteArray.wrap(new byte[]{Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.ORDER_BRIEF_INFO_LIST, 77, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BACK_INFO_TYPE, 48, 50, Constants.TagName.TERMINAL_BACK_CONTENT});
    public static final ByteArray AID_ST_THREE_DAY_PASS = ByteArray.wrap(new byte[]{Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.ORDER_BRIEF_INFO_LIST, 77, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BACK_INFO_TYPE, 48, 50, Constants.TagName.INVOICE_TOKEN});
    public static final ByteArray AID_SUZHOUTONG = ByteArray.wrap(new byte[]{83, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 88, Constants.TagName.ORDER_BRIEF_INFO, 78, Constants.TagName.SIM_SEID, 77, Constants.TagName.TERMINAL_BASEBAND_VERSION});
    public static final ByteArray AID_SZT = ByteArray.wrap(new byte[]{83, Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.SIM_SEID, 87, Constants.TagName.TERMINAL_BACK_CONTENT, 76, 76, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.SIM_SEID, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, Constants.TagName.QUERY_DATA_SORT_TYPE});
    public static final ByteArray AID_WHT = ByteArray.wrap(new byte[]{ScriptToolsConst.TagName.CommandSingle, 0, 0, 83, Constants.TagName.INVOICE_TOKEN, 87, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_INFO_LIST, TarConstants.U});
    public static final CommandApdu COMM_PREFIX_ACTIVATE_CARD = new CommandApdu(128, PsExtractor.VIDEO_STREAM_MASK, 1, 1);
    public static final CommandApdu COMM_PREFIX_DEACTIVATE_CARD = new CommandApdu(128, PsExtractor.VIDEO_STREAM_MASK, 1, 0);
    public static final CommandApdu COMM_PREFIX_GET_STATUS = new CommandApdu(128, 242, 64, 0);
    public static final CommandApdu COMM_PREFIX_INIT_UPDATE = new CommandApdu(128, 80, 33, 0);
    public static final CommandApdu COMM_PREFIX_READ_RECORD = new CommandApdu(0, 178, 1, 12);
    public static final CommandApdu COMM_TAG_GET_STATUS = new CommandApdu(92, 3, 79, 159, 112);
    public static final byte[] GET_SEID = {Byte.MIN_VALUE, -54, -97, Byte.MAX_VALUE, 0};
    public static final byte[] ISD = {ScriptToolsConst.TagName.CommandSingle, 0, 0, 1, Constants.TagName.TERMINAL_BACK_MAIN_ID, 0, 0, 0};
    public static final int LENGTH_HOSTCHALLENGE = 16;
    public static final byte[] MC_OPEN = {0, Constants.TagName.ELECTRONIC_ID, 0, 0, 1};
    public static byte[] MIFARE_CARD_AID_PREFFIX = {ScriptToolsConst.TagName.CommandSingle, 0, 0, 3, Constants.TagName.PREDEPOSIT_INFO, 77, 52, 77, 16};
    public static final ByteArray NOT_EXISTS = ByteArray.wrap(new byte[]{Constants.TagName.PAY_ORDER_ID, -126});
    public static byte[] PBOC_CARD_AID_PREFFIX = {ScriptToolsConst.TagName.CommandSingle, 0, 0, 3, 51, 1, 1};
    public static final byte[] SELECT_CRS = {0, ScriptToolsConst.TagName.CommandMultiple, 4, 0, 9, ScriptToolsConst.TagName.CommandSingle, 0, 0, 1, Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, 83, 0};
    public static final byte[] SELECT_ISD = {0, ScriptToolsConst.TagName.CommandMultiple, 4, 0, 8, ScriptToolsConst.TagName.CommandSingle, 0, 0, 1, Constants.TagName.TERMINAL_BACK_MAIN_ID, 0, 0, 0};
    public static final byte[] SELECT_PPSE = {0, ScriptToolsConst.TagName.CommandMultiple, 4, 0, 14, 50, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_CONTENT, 89, Constants.TagName.SIM_SEID, 83, 89, 83, Constants.TagName.SIM_SEID, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_BASEBAND_VERSION, 48, 49};
    public static final ByteArray TAG_AEF_ENTRANCE = ByteArray.wrap(new byte[]{97});
    public static final ByteArray TAG_AID = ByteArray.wrap(new byte[]{Constants.TagName.CP_NO});
    public static final ByteArray TAG_APP = ByteArray.wrap(new byte[]{Constants.TagName.ORDER_BRIEF_INFO_LIST});
    public static final ByteArray TAG_BANK_CUSTOM_DATA = ByteArray.wrap(new byte[]{Constants.TagName.STATION_ID, 12});
    public static final ByteArray TAG_CARD_NUM = ByteArray.wrap(new byte[]{87});
    public static final ByteArray TAG_FCI_2PAY = ByteArray.wrap(new byte[]{-124});
    public static final ByteArray TAG_FCI_DATA_TEMPLATE = ByteArray.wrap(new byte[]{-91});
    public static final ByteArray TAG_FCI_TEMPLATE = ByteArray.wrap(new byte[]{Constants.TagName.ELECTRONIC_END_TIME});
    public static final ByteArray TAG_LIFESTYLE_STATE = ByteArray.wrap(new byte[]{-97, Constants.TagName.ELECTRONIC_ID});
    public static final byte TAG_MORE_DATA = 97;
    public static final ByteArray TAG_PDOL = ByteArray.wrap(new byte[]{-97, ScriptToolsConst.TagName.TagSerial});
}
