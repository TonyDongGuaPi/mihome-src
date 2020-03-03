package com.xiaomi.smarthome.library.common.network;

import android.support.media.ExifInterface;
import android.text.TextUtils;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.mijia.model.alarmcloud.AlarmCloudManager;
import com.tsmclient.smartcard.CardConstants;
import com.xiaomi.smarthome.library.common.network.HanziToPinyin;
import java.text.Collator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.cybergarage.http.HTTP;

public class HanziToPinyinICS {

    /* renamed from: a  reason: collision with root package name */
    public static final char[] f18629a = {21621, 21710, 23433, 32942, 20985, 20843, 25344, 25203, 37030, 21253, 21329, 22868, 20283, 23620, 36793, 26631, 24971, 37024, 27103, 30326, 23788, 22163, 23111, 39137, 20179, 25805, 20874, 23934, 22092, 21449, 38039, 36799, 20261, 25220, 36710, 25275, 26621, 21507, 20805, 25277, 20986, 27451, 25571, 24029, 30126, 21561, 26486, 36916, 30133, 21254, 20945, 31895, 27718, 23828, 37032, 25619, 21649, 22823, 30136, 24403, 20992, 28098, 24471, 25189, 28783, 27664, 22002, 30008, 20993, 29241, 20163, 19999, 19996, 21783, 22047, 20595, 22534, 37907, 22810, 23104, 35830, 22848, 38821, 32780, 21457, 24070, 26041, 39134, 20998, 20016, 35205, 20175, 32017, 20245, 26094, 35813, 29976, 20872, 30347, 25096, 32102, 26681, 24218, 24037, 21246, 20272, 29916, 32619, 20851, 20809, 24402, 34926, 21593, 21704, 21683, 39032, 33472, 33983, 35779, 40658, 25323, 20136, 22135, 21565, 40769, 21282, 33457, 24576, 29375, 24031, 28784, 26127, 21529, 19980, 21152, 25099, 27743, 33405, 38454, 24062, 21156, 20866, 21244, 21258, 23071, 22104, 20891, 21652, 24320, 21002, 38390, 23611, 21308, 21067, 32943, 38444, 31354, 25248, 21043, 22840, 33967, 23485, 21281, 20111, 22372, 25193, 22403, 26469, 20848, 21879, 25438, 20162, 21202, 22596, 21013, 20486, 22849, 33391, 25769, 21015, 25294, 12295, 28316, 40857, 30620, 22108, 23048, 30055, 25249, 32599, 21603, 22920, 38718, 23258, 37017, 29483, 40636, 27794, 38376, 30015, 21674, 30496, 21941, 21673, 27665, 21517, 35884, 25720, 21726, 27626, 25295, 23419, 22241, 22218, 23404, 35767, 39297, 24641, 33021, 22958, 25288, 23330, 40479, 25423, 24744, 23425, 22942, 20892, 32698, 22900, 22907, 34384, 25386, 21908, 35764, 36276, 25293, 30469, 20051, 25243, 21624, 21943, 21257, 19989, 20559, 21117, 27669, 23000, 20050, 38027, 21078, 20166, 19971, 25488, 21315, 21595, 24708, 30335, 20405, 38737, 37019, 19992, 26354, 24366, 32570, 22795, 21605, 31331, 23046, 24825, 20154, 25172, 26085, 33592, 21433, 22914, 22567, 26741, 38384, 33509, 20200, 27618, 19977, 26706, 25531, 33394, 26862, 20711, 26432, 31579, 23665, 20260, 24368, 22882, 30003, 21319, 23608, 21454, 20070, 21047, 25684, 38377, 21452, 35841, 21550, 22913, 21430, 24554, 25436, 33487, 29435, 22794, 23385, 21766, 20182, 33492, 22349, 38132, 22834, 24529, 29093, 21076, 22825, 20347, 24086, 21381, 22258, 20599, 37568, 28237, 25512, 21534, 25176, 25366, 27498, 24367, 23594, 21361, 22637, 32705, 25373, 20800, 22805, 34430, 20186, 20065, 28785, 20123, 24515, 26143, 20982, 20241, 26100, 36713, 30134, 21195, 20011, 24697, 22830, 24186, 32822, 19968, 27437, 24212, 21727, 20323, 20248, 25180, 40482, 26352, 26197, 21277, 28797, 31948, 29250, 20654, 21017, 36156, 24590, 22679, 21522, 25434, 27838, 24352, 38026, 34567, 36126, 20105, 20043, 20013, 24030, 26417, 25235, 36329, 19987, 22918, 38585, 23442, 21331, 23388, 23447, 37049, 31199, 38075, 21404, 23562, 26152};
    public static final byte[][] b = {new byte[]{Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0, 0, 0}, new byte[]{Constants.TagName.INVOICE_TOKEN, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0, 0}, new byte[]{Constants.TagName.INVOICE_TOKEN, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{Constants.TagName.INVOICE_TOKEN, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0, 0}, new byte[]{Constants.TagName.INVOICE_TOKEN, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.INVOICE_TOKEN, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{Constants.TagName.INVOICE_TOKEN, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{Constants.TagName.INVOICE_TOKEN, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, 0, 0, 0}, new byte[]{Constants.TagName.INVOICE_TOKEN, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.INVOICE_TOKEN, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0, 0}, new byte[]{Constants.TagName.INVOICE_TOKEN, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{Constants.TagName.INVOICE_TOKEN, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0}, new byte[]{Constants.TagName.INVOICE_TOKEN, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0}, new byte[]{Constants.TagName.INVOICE_TOKEN, Constants.TagName.ORDER_BRIEF_INFO, 78, 0, 0, 0}, new byte[]{Constants.TagName.INVOICE_TOKEN, Constants.TagName.ORDER_BRIEF_INFO, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.INVOICE_TOKEN, Constants.TagName.CP_NO, 0, 0, 0, 0}, new byte[]{Constants.TagName.INVOICE_TOKEN, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, Constants.TagName.ACTIVITY_INFO, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.CP_NO, 78, Constants.TagName.ACTIVITY_INFO, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.CP_NO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.ORDER_BRIEF_INFO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 78, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.CP_NO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.CP_NO, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.CP_NO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 78, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.ORDER_BRIEF_INFO, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.CP_NO, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.CP_NO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 78, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_MODEL_NUMBER, 78, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_MODEL_NUMBER, 78, Constants.TagName.ACTIVITY_INFO, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.CP_NO, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.CP_NO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BASEBAND_VERSION, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0, 0}, new byte[]{Constants.TagName.ACTIVITY_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0, 0}, new byte[]{Constants.TagName.ACTIVITY_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{Constants.TagName.ACTIVITY_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0, 0}, new byte[]{Constants.TagName.ACTIVITY_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.ACTIVITY_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{Constants.TagName.ACTIVITY_INFO, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0, 0}, new byte[]{Constants.TagName.ACTIVITY_INFO, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{Constants.TagName.ACTIVITY_INFO, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, 0, 0, 0}, new byte[]{Constants.TagName.ACTIVITY_INFO, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.ACTIVITY_INFO, Constants.TagName.CP_NO, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.ACTIVITY_INFO, Constants.TagName.CP_NO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{Constants.TagName.ACTIVITY_INFO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0, 0}, new byte[]{Constants.TagName.ACTIVITY_INFO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0}, new byte[]{Constants.TagName.ACTIVITY_INFO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO, 0, 0}, new byte[]{Constants.TagName.ACTIVITY_INFO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{Constants.TagName.ACTIVITY_INFO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0}, new byte[]{Constants.TagName.ACTIVITY_INFO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{Constants.TagName.ACTIVITY_INFO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 78, 0, 0, 0}, new byte[]{Constants.TagName.ACTIVITY_INFO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0, 0}, new byte[]{Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0, 0}, new byte[]{Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0, 0}, new byte[]{Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, 0, 0, 0}, new byte[]{Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.BUSINESS_ORDER_TYPE, 77, 0, 0, 0, 0}, new byte[]{Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.CP_NO, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.CP_NO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0, 0}, new byte[]{Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0}, new byte[]{Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO, 0, 0}, new byte[]{Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0}, new byte[]{Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 78, 0, 0, 0}, new byte[]{Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{74, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0, 0}, new byte[]{74, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0}, new byte[]{74, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{74, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0}, new byte[]{74, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0}, new byte[]{74, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0}, new byte[]{74, Constants.TagName.ORDER_BRIEF_INFO, 78, 0, 0, 0}, new byte[]{74, Constants.TagName.ORDER_BRIEF_INFO, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{74, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.CP_NO, 78, Constants.TagName.ACTIVITY_INFO, 0}, new byte[]{74, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{74, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0, 0}, new byte[]{74, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{74, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0}, new byte[]{74, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 78, 0, 0, 0}, new byte[]{TarConstants.U, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0, 0}, new byte[]{TarConstants.U, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{TarConstants.U, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0, 0}, new byte[]{TarConstants.U, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{TarConstants.U, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{TarConstants.U, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0, 0}, new byte[]{TarConstants.U, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{TarConstants.U, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, 0, 0, 0}, new byte[]{TarConstants.U, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{TarConstants.U, Constants.TagName.CP_NO, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{TarConstants.U, Constants.TagName.CP_NO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{TarConstants.U, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0, 0}, new byte[]{TarConstants.U, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0}, new byte[]{TarConstants.U, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO, 0, 0}, new byte[]{TarConstants.U, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{TarConstants.U, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0}, new byte[]{TarConstants.U, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{TarConstants.U, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 78, 0, 0, 0}, new byte[]{TarConstants.U, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{76, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0, 0}, new byte[]{76, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{76, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0, 0}, new byte[]{76, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{76, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{76, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0, 0}, new byte[]{76, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{76, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{76, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0, 0}, new byte[]{76, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0}, new byte[]{76, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{76, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0}, new byte[]{76, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0}, new byte[]{76, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0}, new byte[]{76, Constants.TagName.ORDER_BRIEF_INFO, 78, 0, 0, 0}, new byte[]{76, Constants.TagName.ORDER_BRIEF_INFO, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{76, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{76, Constants.TagName.CP_NO, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{76, Constants.TagName.CP_NO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{76, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0, 0}, new byte[]{76, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{76, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0}, new byte[]{76, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 78, 0, 0, 0}, new byte[]{76, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{77, 0, 0, 0, 0, 0}, new byte[]{77, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0, 0}, new byte[]{77, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{77, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0, 0}, new byte[]{77, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{77, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{77, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0, 0}, new byte[]{77, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{77, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, 0, 0, 0}, new byte[]{77, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{77, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0, 0}, new byte[]{77, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{77, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0}, new byte[]{77, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0}, new byte[]{77, Constants.TagName.ORDER_BRIEF_INFO, 78, 0, 0, 0}, new byte[]{77, Constants.TagName.ORDER_BRIEF_INFO, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{77, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{77, Constants.TagName.CP_NO, 0, 0, 0, 0}, new byte[]{77, Constants.TagName.CP_NO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{77, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0, 0}, new byte[]{78, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0, 0}, new byte[]{78, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{78, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0, 0}, new byte[]{78, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{78, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{78, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0, 0}, new byte[]{78, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{78, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, 0, 0, 0}, new byte[]{78, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{78, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0, 0}, new byte[]{78, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{78, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0}, new byte[]{78, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0}, new byte[]{78, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0}, new byte[]{78, Constants.TagName.ORDER_BRIEF_INFO, 78, 0, 0, 0}, new byte[]{78, Constants.TagName.ORDER_BRIEF_INFO, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{78, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{78, Constants.TagName.CP_NO, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{78, Constants.TagName.CP_NO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{78, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0, 0}, new byte[]{78, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{78, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0}, new byte[]{78, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{Constants.TagName.CP_NO, 0, 0, 0, 0, 0}, new byte[]{Constants.TagName.CP_NO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0, 0}, new byte[]{Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0, 0}, new byte[]{Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0, 0}, new byte[]{Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, 0, 0, 0}, new byte[]{Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0, 0}, new byte[]{Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0}, new byte[]{Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0}, new byte[]{Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.ORDER_BRIEF_INFO, 78, 0, 0, 0}, new byte[]{Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.ORDER_BRIEF_INFO, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.CP_NO, 0, 0, 0, 0}, new byte[]{Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.CP_NO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.ORDER_BRIEF_INFO, 78, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.ORDER_BRIEF_INFO, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.CP_NO, 78, Constants.TagName.ACTIVITY_INFO, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_MAIN_ID, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 78, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, Constants.TagName.CP_NO, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, Constants.TagName.CP_NO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 78, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_QUESTION_FLAG, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{83, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0, 0}, new byte[]{83, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{83, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0, 0}, new byte[]{83, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{83, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{83, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0, 0}, new byte[]{83, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, 0, 0, 0}, new byte[]{83, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{83, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0}, new byte[]{83, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO, 0, 0}, new byte[]{83, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{83, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0}, new byte[]{83, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0}, new byte[]{83, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0}, new byte[]{83, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, 0, 0}, new byte[]{83, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, Constants.TagName.ACTIVITY_INFO, 0}, new byte[]{83, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{83, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.CP_NO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0}, new byte[]{83, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{83, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0}, new byte[]{83, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO, 0}, new byte[]{83, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0}, new byte[]{83, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO}, new byte[]{83, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.ORDER_BRIEF_INFO, 0, 0}, new byte[]{83, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 78, 0, 0}, new byte[]{83, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.CP_NO, 0, 0}, new byte[]{83, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0, 0}, new byte[]{83, Constants.TagName.CP_NO, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{83, Constants.TagName.CP_NO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{83, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0, 0}, new byte[]{83, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{83, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{83, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 78, 0, 0, 0}, new byte[]{83, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.ORDER_BRIEF_INFO, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.CP_NO, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.CP_NO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 78, 0, 0, 0}, new byte[]{Constants.TagName.TERMINAL_BACK_INFO_LIST, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{87, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0, 0}, new byte[]{87, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{87, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0, 0}, new byte[]{87, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{87, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{87, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, 0, 0, 0}, new byte[]{87, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{87, Constants.TagName.CP_NO, 0, 0, 0, 0}, new byte[]{87, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0, 0}, new byte[]{88, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0, 0}, new byte[]{88, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0}, new byte[]{88, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{88, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0}, new byte[]{88, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0}, new byte[]{88, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0}, new byte[]{88, Constants.TagName.ORDER_BRIEF_INFO, 78, 0, 0, 0}, new byte[]{88, Constants.TagName.ORDER_BRIEF_INFO, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{88, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.CP_NO, 78, Constants.TagName.ACTIVITY_INFO, 0}, new byte[]{88, Constants.TagName.ORDER_BRIEF_INFO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{88, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0, 0}, new byte[]{88, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{88, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0}, new byte[]{88, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 78, 0, 0, 0}, new byte[]{89, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0, 0}, new byte[]{89, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0, 0}, new byte[]{89, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{89, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{89, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0, 0}, new byte[]{89, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0, 0}, new byte[]{89, Constants.TagName.ORDER_BRIEF_INFO, 78, 0, 0, 0}, new byte[]{89, Constants.TagName.ORDER_BRIEF_INFO, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{89, Constants.TagName.CP_NO, 0, 0, 0, 0}, new byte[]{89, Constants.TagName.CP_NO, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{89, Constants.TagName.CP_NO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{89, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0, 0}, new byte[]{89, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{89, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0}, new byte[]{89, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 78, 0, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.TERMINAL_MODEL_NUMBER, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, 0, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.CP_NO, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_MODEL_NUMBER, 0, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_MODEL_NUMBER, 78, Constants.TagName.ACTIVITY_INFO, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.CP_NO, 78, Constants.TagName.ACTIVITY_INFO, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.CP_NO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, Constants.TagName.ORDER_BRIEF_INFO, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, Constants.TagName.ACTIVITY_INFO}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.ORDER_BRIEF_INFO, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 78, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.BUSINESS_ORDER_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.CP_NO, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.CP_NO, 78, Constants.TagName.ACTIVITY_INFO, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.CP_NO, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 0, 0, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.TERMINAL_BACK_CONTENT, 78, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.ORDER_BRIEF_INFO, 0, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, 78, 0, 0, 0}, new byte[]{Constants.TagName.PREDEPOSIT_TOTAL, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.CP_NO, 0, 0, 0}};
    private static final String c = "HanziToPinyin";
    private static HashMap<Character, String[]> d = new HashMap<>();
    private static HashMap<String, String[]> e = new HashMap<>();
    private static HashMap<Character, String> f = new HashMap<>();
    private static final String g = "阿";
    private static final String h = "蓙";
    private static final char i = '㐀';
    private static final Collator j = Collator.getInstance(Locale.CHINA);
    private static HanziToPinyinICS k;
    private static final char[] m = {'2', '2', '2', '3', '3', '3', '4', '4', '4', '5', '5', '5', '6', '6', '6', '7', '7', '7', '7', '8', '8', '8', '9', '9', '9', '9'};
    private final boolean l;

    static {
        d.put(38463, new String[]{"A", ExifInterface.LONGITUDE_EAST});
        d.put(33100, new String[]{"YAN", "A"});
        d.put(25303, new String[]{"AO", "O", "NIU"});
        d.put(25170, new String[]{"PA", "BA"});
        d.put(34444, new String[]{"BANG", "BENG"});
        d.put(34180, new String[]{"BAO", "BO"});
        d.put(22561, new String[]{"BAO", "BU", "PU"});
        d.put(26292, new String[]{"BAO", "PU"});
        d.put(36146, new String[]{"BEN", "FEI", "BI"});
        d.put(36153, new String[]{"FEI", "BI"});
        d.put(33218, new String[]{"BI", "BEI"});
        d.put(36767, new String[]{"PI", "BI"});
        d.put(33536, new String[]{"FU", "BI"});
        d.put(25153, new String[]{"BIAN", "PIAN"});
        d.put(20415, new String[]{"BIAN", "PIAN"});
        d.put(33152, new String[]{"PANG", "BANG"});
        d.put(30917, new String[]{"PANG", "BANG"});
        d.put(39584, new String[]{"BIAO", "PIAO"});
        d.put(30058, new String[]{"FAN", "PAN", "BO"});
        d.put(23387, new String[]{"BEI", "BO"});
        d.put(24223, new String[]{"FEI", "BO"});
        d.put(21093, new String[]{"BO", "BAO", "XUE"});
        d.put(27850, new String[]{"BO", "PO", "BAN"});
        d.put(20271, new String[]{"BO", "BAI"});
        d.put(21340, new String[]{"BO", "BU"});
        d.put(20263, new String[]{"CANG", "CHEN"});
        d.put(34255, new String[]{"CANG", "ZANG"});
        d.put(21442, new String[]{"CAN", "SHEN", "CEN"});
        d.put(26366, new String[]{"CENG", "ZENG"});
        d.put(22092, new String[]{"CENG", "CHENG"});
        d.put(24046, new String[]{"CHA", "CHAI"});
        d.put(26597, new String[]{"CHA", "ZHA"});
        d.put(31109, new String[]{"CHAN", "SHAN"});
        d.put(39076, new String[]{"CHAN", "ZHAN"});
        d.put(23409, new String[]{"CHAN", "CAN"});
        d.put(35059, new String[]{"SHANG", "CHANG"});
        d.put(22330, new String[]{"CHANG", "CHANG"});
        d.put(26216, new String[]{"CHEN", "CHANG", "ZE"});
        d.put(38271, new String[]{"CHANG", "ZHANG"});
        d.put(21378, new String[]{"CHANG", "AN", "HAN"});
        d.put(22066, new String[]{"CHAO", "ZHAO", "ZHA"});
        d.put(36710, new String[]{"CHE", "JU"});
        d.put(31216, new String[]{"CHENG", "CHEN"});
        d.put(28548, new String[]{"CHENG", "DENG"});
        d.put(38107, new String[]{"DANG", "CHENG"});
        d.put(20056, new String[]{"CHENG", "SHENG"});
        d.put(26397, new String[]{"CHAO", "ZHAO"});
        d.put(38241, new String[]{"XIN", "CHAN", "TAN"});
        d.put(21273, new String[]{"SHI", "CHI"});
        d.put(37079, new String[]{"XI", "CHI"});
        d.put(27835, new String[]{"ZHI", "CHI"});
        d.put(30259, new String[]{"CHOU", "LU"});
        d.put(19985, new String[]{"CHOU", "NIU"});
        d.put(33261, new String[]{"CHOU", "XIU"});
        d.put(37325, new String[]{"ZHONG", "CHONG"});
        d.put(31181, new String[]{"ZHONG", "CHONG"});
        d.put(30044, new String[]{"CHU", "XU"});
        d.put(38500, new String[]{"CHU", "XU"});
        d.put(20256, new String[]{"CHUAN", "ZHUAN"});
        d.put(21852, new String[]{"CHUO", "CHUAI"});
        d.put(32496, new String[]{"CHUO", "CHAO"});
        d.put(35098, new String[]{"ZHU", "CHU", "ZHE"});
        d.put(26894, new String[]{"ZHUI", "CHUI"});
        d.put(27425, new String[]{"CI", "CHI", "QI"});
        d.put(20282, new String[]{"CI", "SI"});
        d.put(20857, new String[]{"ZI", "CI"});
        d.put(26526, new String[]{"CONG", "ZONG"});
        d.put(25874, new String[]{"CUAN", "ZAN"});
        d.put(21330, new String[]{"ZU", "CU"});
        d.put(34928, new String[]{"SHUAI", "CUI"});
        d.put(25774, new String[]{"CUO", "ZUO"});
        d.put(22823, new String[]{"DA", "DAI"});
        d.put(27795, new String[]{"TA", "DA"});
        d.put(21333, new String[]{"DAN", "CHAN", "SHAN"});
        d.put(21480, new String[]{"DAO", "TAO"});
        d.put(25552, new String[]{"TI", "DI"});
        d.put(36934, new String[]{"DI", "TI"});
        d.put(32735, new String[]{"DI", "ZHAI"});
        d.put(24471, new String[]{"DE", "DEI"});
        d.put(38079, new String[]{"DIAN", "TIAN"});
        d.put(20291, new String[]{"DIAN", "TIAN"});
        d.put(20992, new String[]{"DAO", "DIAO"});
        d.put(35843, new String[]{"DIAO", "TIAO"});
        d.put(37117, new String[]{"DOU", "DU"});
        d.put(24230, new String[]{"DU", "DUO"});
        d.put(22244, new String[]{"TUN", "DUN"});
        d.put(21542, new String[]{"FOU", "PI"});
        d.put(33071, new String[]{"PU", "FU"});
        d.put(36711, new String[]{"YA", "ZHA", "GA"});
        d.put(25179, new String[]{"KANG", "GANG"});
        d.put(30422, new String[]{"GAI", "GE"});
        d.put(21679, new String[]{"GE", "KA", "LO"});
        d.put(38761, new String[]{"GE", "JI"});
        d.put(21512, new String[]{"HE", "GE"});
        d.put(32473, new String[]{"GEI", "JI"});
        d.put(39048, new String[]{"JING", "GENG"});
        d.put(32418, new String[]{"HONG", "GONG"});
        d.put(26552, new String[]{"GOU", "JU"});
        d.put(21617, new String[]{"GUA", "GU"});
        d.put(35895, new String[]{"GU", "YU"});
        d.put(34411, new String[]{"CHONG", "GU"});
        d.put(40516, new String[]{"HU", "GU"});
        d.put(25324, new String[]{"KUO", "GUA"});
        d.put(33694, new String[]{"GUAN", "WAN"});
        d.put(32438, new String[]{"LUN", "GUAN"});
        d.put(28805, new String[]{"JIONG", "GUI"});
        d.put(26727, new String[]{"GUI", "HUI"});
        d.put(28820, new String[]{"QUE", "GUI"});
        d.put(26123, new String[]{"GUI", "JIONG"});
        d.put(20250, new String[]{"HUI", "KUAI", "GUI"});
        d.put(33445, new String[]{"JIE", "GAI"});
        d.put(34430, new String[]{"XIA", "HA"});
        d.put(36713, new String[]{"XUAN", "HAN"});
        d.put(25750, new String[]{"KAN", "HAN"});
        d.put(21683, new String[]{"KE", "HAI"});
        d.put(24055, new String[]{"XIANG", "HANG"});
        d.put(21549, new String[]{"KENG", "HANG"});
        d.put(40664, new String[]{"MO", "HE", "MEI"});
        d.put(21644, new String[]{"HE", "HU", "HUO"});
        d.put(35977, new String[]{"HE", "HAO"});
        d.put(40657, new String[]{"HEI", "HE"});
        d.put(34425, new String[]{"HONG", "JIANG"});
        d.put(37063, new String[]{"XUN", "HUAN"});
        d.put(23536, new String[]{"HUAN", CardConstants.XIAN});
        d.put(22855, new String[]{"QI", "JI"});
        d.put(32521, new String[]{"JI", "QI"});
        d.put(20552, new String[]{"JIE", "JI"});
        d.put(31995, new String[]{"XI", "JI"});
        d.put(31293, new String[]{"JI", "QI"});
        d.put(20127, new String[]{"JI", "QI"});
        d.put(35800, new String[]{"JIE", "JI"});
        d.put(35760, new String[]{"JI", "JIE"});
        d.put(21095, new String[]{"JU", "JI"});
        d.put(31085, new String[]{"JI", "ZHA", "ZHAI"});
        d.put(33540, new String[]{"QIE", "JIA"});
        d.put(22204, new String[]{"JIAO", "JUE"});
        d.put(20389, new String[]{"JIAO", "YAO"});
        d.put(35282, new String[]{"JIAO", "JUE"});
        d.put(33050, new String[]{"JIAO", "JUE"});
        d.put(21119, new String[]{"JIAO", "CHAO"});
        d.put(26657, new String[]{"XIAO", "JIAO"});
        d.put(32564, new String[]{"JIAO", "ZHUO"});
        d.put(35265, new String[]{"JIAN", CardConstants.XIAN});
        d.put(38477, new String[]{"XIANG", "LONG", "JIANG"});
        d.put(35299, new String[]{"JIE", "XIE"});
        d.put(34249, new String[]{"JIE", "JI"});
        d.put(30684, new String[]{"JIN", "QIN"});
        d.put(21170, new String[]{"JIN", "JING"});
        d.put(40863, new String[]{"GUI", "QIU", "CI", "JUN"});
        d.put(21632, new String[]{"JU", "ZUI"});
        d.put(29722, new String[]{"JU", "QU"});
        d.put(33740, new String[]{"JUN", "XUN"});
        d.put(38589, new String[]{"JUN", "JUAN"});
        d.put(21345, new String[]{"KA", "QIA"});
        d.put(30475, new String[]{"KAN", "KAN"});
        d.put(25000, new String[]{"HAN", "KAN"});
        d.put(22391, new String[]{"KE", "KE"});
        d.put(22771, new String[]{"KE", "QIA"});
        d.put(20811, new String[]{"KE", "KEI"});
        d.put(38752, new String[]{"KAO", "KU"});
        d.put(38551, new String[]{"WEI", "KUI"});
        d.put(39740, new String[]{"GUI", "WEI", "KUI"});
        d.put(33554, new String[]{"KUANG", "GUAN", "YUAN"});
        d.put(21895, new String[]{"LA", "YAO"});
        d.put(34013, new String[]{"LAN", "PIE"});
        d.put(28889, new String[]{"LAO", "LUO", "PAO"});
        d.put(38610, new String[]{"LUO", "LAO"});
        d.put(32907, new String[]{"LE", "LEI"});
        d.put(20048, new String[]{"LE", "YUE"});
        d.put(20102, new String[]{"LE", "LIAO"});
        d.put(20457, new String[]{"LIANG", "LIA"});
        d.put(28518, new String[]{"LIAO", "LAO"});
        d.put(30860, new String[]{"LU", "ZHOU", "LIU"});
        d.put(20603, new String[]{"LOU", "LU"});
        d.put(38706, new String[]{"LU", "LOU"});
        d.put(25419, new String[]{"LU", "LUO"});
        d.put(32511, new String[]{"LV", "LU"});
        d.put(20845, new String[]{"LIU", "LU"});
        d.put(32476, new String[]{"LUO", "LAO"});
        d.put(33853, new String[]{"LUO", "LAO", "LA"});
        d.put(25273, new String[]{"MA", "MO"});
        d.put(33033, new String[]{"MAI", "MO"});
        d.put(22475, new String[]{"MAI", HTTP.MAN});
        d.put(34067, new String[]{HTTP.MAN, "WAN"});
        d.put(27667, new String[]{"MANG", "MENG"});
        d.put(27809, new String[]{"MEI", "MO"});
        d.put(31192, new String[]{"MI", "BI"});
        d.put(27852, new String[]{"MI", "BI"});
        d.put(20340, new String[]{"MI", "NAI", "NI"});
        d.put(35884, new String[]{"MIAO", "MIU"});
        d.put(27169, new String[]{"MO", "MU"});
        d.put(25705, new String[]{"MO", "MA", "SA"});
        d.put(27597, new String[]{"MU", "WU"});
        d.put(32554, new String[]{"MIU", "MIAO", "MOU"});
        d.put(24324, new String[]{"NONG", "LONG"});
        d.put(38590, new String[]{"NAN", "NING"});
        d.put(30111, new String[]{"NUE", "YAO"});
        d.put(20060, new String[]{"MIE", "NIE"});
        d.put(23068, new String[]{"NA", "NUO"});
        d.put(21306, new String[]{"QU", "OU"});
        d.put(32321, new String[]{"FAN", "PO"});
        d.put(36843, new String[]{"PO", "PAI"});
        d.put(32982, new String[]{"PANG", "PAN"});
        d.put(21032, new String[]{"PAO", "BAO"});
        d.put(28846, new String[]{"PAO", "BAO"});
        d.put(36898, new String[]{"FENG", "PANG"});
        d.put(34028, new String[]{"PENG", "PANG"});
        d.put(26420, new String[]{"PU", "PO", "PIAO"});
        d.put(28689, new String[]{"PU", "BAO"});
        d.put(26333, new String[]{"BAO", "PU"});
        d.put(26646, new String[]{"XI", "QI"});
        d.put(36426, new String[]{"XI", "QI"});
        d.put(31293, new String[]{"JI", "QI"});
        d.put(33640, new String[]{"XUN", "QIAN"});
        d.put(31140, new String[]{"QIAN", "XUAN"});
        d.put(24378, new String[]{"QIANG", "JIANG"});
        d.put(36228, new String[]{"QIE", "JU"});
        d.put(20146, new String[]{"QIN", "QING"});
        d.put(38592, new String[]{"QUE", "QIAO"});
        d.put(20167, new String[]{"CHOU", "QIU"});
        d.put(22280, new String[]{"QUAN", "JUAN"});
        d.put(33394, new String[]{"SE", "SHAI"});
        d.put(22622, new String[]{"SAI", "SE"});
        d.put(21414, new String[]{"XIA", "SHA"});
        d.put(21484, new String[]{"ZHAO", "SHAO"});
        d.put(26441, new String[]{"SHAN", "SHA"});
        d.put(27748, new String[]{"TANG", "SHANG"});
        d.put(25342, new String[]{"SHI", "SHE"});
        d.put(25240, new String[]{"ZHE", "SHE"});
        d.put(20160, new String[]{"SHEN", "SHI"});
        d.put(33882, new String[]{"SHEN", "REN"});
        d.put(35782, new String[]{"SHI", "ZHI"});
        d.put(20284, new String[]{"SI", "SHI"});
        d.put(23646, new String[]{"SHU", "ZHU"});
        d.put(29087, new String[]{"SHU", "SHOU"});
        d.put(35828, new String[]{"SHUO", "SHUI"});
        d.put(25968, new String[]{"SHU", "SHUO"});
        d.put(24554, new String[]{"SONG", "ZHONG"});
        d.put(23487, new String[]{"SU", "XIU"});
        d.put(30509, new String[]{"GUI", "XU", "SUI"});
        d.put(28601, new String[]{"DAN", "TAN"});
        d.put(27795, new String[]{"TA", "DA"});
        d.put(35203, new String[]{"TAN", "QIN"});
        d.put(35843, new String[]{"DIAO", "TIAO"});
        d.put(35114, new String[]{"TUI", "TUN"});
        d.put(25299, new String[]{"TUO", "TA"});
        d.put(22313, new String[]{"WEI", "XU"});
        d.put(22996, new String[]{"WEI", "QU"});
        d.put(23614, new String[]{"WEI", "YI"});
        d.put(23561, new String[]{"WEI", "YU"});
        d.put(36951, new String[]{"YI", "WEI"});
        d.put(20044, new String[]{"WU", "LA"});
        d.put(21523, new String[]{"XIA", "HE"});
        d.put(32420, new String[]{CardConstants.XIAN, "QIAN"});
        d.put(34892, new String[]{"XING", "HANG", "HENG"});
        d.put(30465, new String[]{"SHENG", "XING"});
        d.put(21066, new String[]{"XIAO", "XUE"});
        d.put(34880, new String[]{"XUE", "XIE"});
        d.put(27575, new String[]{"YIN", "YAN"});
        d.put(21693, new String[]{"YAN", "YE"});
        d.put(32422, new String[]{"YUE", "YAO"});
        d.put(38053, new String[]{"YAO", "YUE"});
        d.put(21494, new String[]{"YE", "XIE"});
        d.put(33406, new String[]{AlarmCloudManager.AI, "YI"});
        d.put(29096, new String[]{"YUN", "YU"});
        d.put(21505, new String[]{"YU", "XU"});
        d.put(21592, new String[]{"YUAN", "YUN"});
        d.put(36128, new String[]{"YUAN", "YUN"});
        d.put(21643, new String[]{"ZA", "ZE", "ZHA"});
        d.put(25321, new String[]{"ZE", "ZHAI"});
        d.put(25166, new String[]{"ZHA", "ZA"});
        d.put(36711, new String[]{"YA", "ZHA"});
        d.put(31896, new String[]{"NIAN", "ZHAN"});
        d.put(29226, new String[]{"ZHUA", "ZHAO"});
        d.put(30528, new String[]{"ZHAO", "ZHUO"});
        d.put(27542, new String[]{"ZHI", "SHI"});
        d.put(33879, new String[]{"ZHU", "ZHE", "ZHUO"});
        d.put(24162, new String[]{"ZHUANG", "CHUANG"});
        d.put(32508, new String[]{"ZONG", "ZENG"});
        d.put(26590, new String[]{"ZUO", "ZHA"});
        d.put(20180, new String[]{"ZI", "ZAI"});
        d.put(20869, new String[]{"NEI"});
        d.put(30655, new String[]{"QU"});
        d.put(26469, new String[]{"LAI"});
        d.put(21449, new String[]{"CHA"});
        d.put(22905, new String[]{"TA"});
        d.put(20799, new String[]{"ER"});
        d.put(27784, new String[]{"SHEN"});
        d.put(36158, new String[]{"JIA"});
        e.put("单于", new String[]{"CHAN", "YU"});
        e.put("长孙", new String[]{"ZHANG", "SUN"});
        e.put("子车", new String[]{"ZI", "JU"});
        e.put("万俟", new String[]{"MO", "QI"});
        e.put("澹台", new String[]{"TAN", "TAI"});
        e.put("尉迟", new String[]{"YU", "CHI"});
        f.put(36158, "JIA");
        f.put(27784, "SHEN");
        f.put(21340, "BU");
        f.put(34180, "BO");
        f.put(23387, "BO");
        f.put(36146, "BEN");
        f.put(36153, "FEI");
        f.put(27850, "BAN");
        f.put(33536, "BI");
        f.put(24223, "BO");
        f.put(30058, "BO");
        f.put(35098, "CHU");
        f.put(37325, "CHONG");
        f.put(21378, "HAN");
        f.put(20256, "CHUAN");
        f.put(21442, "CAN");
        f.put(31181, "CHONG");
        f.put(37079, "CHI");
        f.put(38241, "CHAN");
        f.put(26397, "CHAO");
        f.put(27835, "CHI");
        f.put(21852, "CHUAI");
        f.put(34928, "CUI");
        f.put(26216, "CHANG");
        f.put(19985, "CHOU");
        f.put(30259, "CHOU");
        f.put(38271, "CHANG");
        f.put(27425, "QI");
        f.put(36710, "CHE");
        f.put(32735, "ZHAI");
        f.put(20291, "DIAN");
        f.put(20992, "DIAO");
        f.put(35843, "DIAO");
        f.put(36934, "DI");
        f.put(30422, "GE");
        f.put(28805, "GUI");
        f.put(34411, "GU");
        f.put(28820, "GUI");
        f.put(26123, "GUI");
        f.put(20250, "GUI");
        f.put(33445, "GAI");
        f.put(33554, "KUANG");
        f.put(37063, "HUAN");
        f.put(24055, "XIANG");
        f.put(40657, "HE");
        f.put(36713, "HAN");
        f.put(25750, "HAN");
        f.put(40664, "HE");
        f.put(35265, "JIAN");
        f.put(38477, "JIANG");
        f.put(35282, "JIAO");
        f.put(32564, "JIAO");
        f.put(35760, "JI");
        f.put(29722, "JU");
        f.put(21095, "JI");
        f.put(38589, "JUAN");
        f.put(38551, "KUI");
        f.put(39740, "KUI");
        f.put(25000, "KAN");
        f.put(38752, "KU");
        f.put(20048, "YUE");
        f.put(20845, "LU");
        f.put(21895, "LA");
        f.put(38610, "LUO");
        f.put(20102, "LIAO");
        f.put(32554, "MIAO");
        f.put(20340, "MI");
        f.put(35884, "MIAO");
        f.put(20060, "NIE");
        f.put(38590, "NING");
        f.put(21306, "OU");
        f.put(36898, "PANG");
        f.put(34028, "PENG");
        f.put(26420, "PIAO");
        f.put(32321, "PO");
        f.put(20415, "PIAN");
        f.put(20167, "QIU");
        f.put(21345, "QIA");
        f.put(35203, "TAN");
        f.put(31140, "QIAN");
        f.put(21484, "SHAO");
        f.put(20160, "SHI");
        f.put(25240, "SHE");
        f.put(30509, "SUI");
        f.put(35299, "XIE");
        f.put(31995, "XI");
        f.put(24055, "XIANG");
        f.put(38500, "XU");
        f.put(23536, CardConstants.XIAN);
        f.put(21592, "YUAN");
        f.put(36128, "YUAN");
        f.put(26366, "ZENG");
        f.put(26597, "ZHA");
        f.put(20256, "CHUAN");
        f.put(21484, "SHAO");
        f.put(31085, "ZHAI");
    }

    protected HanziToPinyinICS(boolean z) {
        this.l = z;
    }

    public static HanziToPinyinICS a() {
        synchronized (HanziToPinyinICS.class) {
            if (k != null) {
                HanziToPinyinICS hanziToPinyinICS = k;
                return hanziToPinyinICS;
            }
            k = new HanziToPinyinICS(true);
            HanziToPinyinICS hanziToPinyinICS2 = k;
            return hanziToPinyinICS2;
        }
    }

    private HanziToPinyin.Token b(char c2) {
        int i2;
        int i3;
        HanziToPinyin.Token token = new HanziToPinyin.Token();
        String ch = Character.toString(c2);
        token.f = ch;
        if (c2 < 256) {
            token.e = 1;
            token.g = ch;
            return token;
        } else if (c2 < 13312) {
            token.e = 3;
            token.g = ch;
            return token;
        } else {
            String[] strArr = d.get(Character.valueOf(c2));
            int i4 = 0;
            if (strArr != null) {
                token.e = 2;
                token.h = strArr;
                token.g = token.h[0];
                return token;
            }
            int compare = j.compare(ch, g);
            if (compare < 0) {
                token.e = 3;
                token.g = ch;
                return token;
            }
            if (compare == 0) {
                token.e = 2;
                i2 = 0;
            } else {
                compare = j.compare(ch, h);
                if (compare > 0) {
                    token.e = 3;
                    token.g = ch;
                    return token;
                } else if (compare == 0) {
                    token.e = 2;
                    i2 = f18629a.length - 1;
                } else {
                    i2 = -1;
                }
            }
            token.e = 2;
            if (i2 < 0) {
                int length = f18629a.length - 1;
                i3 = compare;
                int i5 = 0;
                while (i5 <= length) {
                    i2 = (i5 + length) / 2;
                    i3 = j.compare(ch, Character.toString(f18629a[i2]));
                    if (i3 == 0) {
                        break;
                    } else if (i3 > 0) {
                        i5 = i2 + 1;
                    } else {
                        length = i2 - 1;
                    }
                }
            } else {
                i3 = compare;
            }
            if (i3 < 0) {
                i2--;
            }
            StringBuilder sb = new StringBuilder();
            while (i4 < b[i2].length && b[i2][i4] != 0) {
                sb.append((char) b[i2][i4]);
                i4++;
            }
            token.g = sb.toString();
            return token;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0015, code lost:
        r2 = r7.substring(0, 2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.ArrayList<com.xiaomi.smarthome.library.common.network.HanziToPinyin.Token> c(java.lang.String r7) {
        /*
            r6 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r7)
            r1 = 0
            if (r0 == 0) goto L_0x0008
            return r1
        L_0x0008:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            int r2 = r7.length()
            r3 = 0
            r4 = 2
            if (r2 < r4) goto L_0x0042
            java.lang.String r2 = r7.substring(r3, r4)
            java.util.HashMap<java.lang.String, java.lang.String[]> r5 = e
            java.lang.Object r5 = r5.get(r2)
            java.lang.String[] r5 = (java.lang.String[]) r5
            if (r5 == 0) goto L_0x0042
        L_0x0023:
            int r7 = r5.length
            if (r3 >= r7) goto L_0x0041
            com.xiaomi.smarthome.library.common.network.HanziToPinyin$Token r7 = new com.xiaomi.smarthome.library.common.network.HanziToPinyin$Token
            r7.<init>()
            r7.e = r4
            char r1 = r2.charAt(r3)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            r7.f = r1
            r1 = r5[r3]
            r7.g = r1
            r0.add(r7)
            int r3 = r3 + 1
            goto L_0x0023
        L_0x0041:
            return r0
        L_0x0042:
            char r7 = r7.charAt(r3)
            java.lang.Character r7 = java.lang.Character.valueOf(r7)
            java.util.HashMap<java.lang.Character, java.lang.String> r2 = f
            java.lang.Object r2 = r2.get(r7)
            java.lang.String r2 = (java.lang.String) r2
            if (r2 == 0) goto L_0x0067
            com.xiaomi.smarthome.library.common.network.HanziToPinyin$Token r1 = new com.xiaomi.smarthome.library.common.network.HanziToPinyin$Token
            r1.<init>()
            r1.e = r4
            java.lang.String r7 = r7.toString()
            r1.f = r7
            r1.g = r2
            r0.add(r1)
            return r0
        L_0x0067:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.library.common.network.HanziToPinyinICS.c(java.lang.String):java.util.ArrayList");
    }

    public ArrayList<HanziToPinyin.Token> a(String str) {
        return a(str, true, true);
    }

    public ArrayList<HanziToPinyin.Token> a(String str, boolean z, boolean z2) {
        ArrayList<HanziToPinyin.Token> c2;
        ArrayList<HanziToPinyin.Token> arrayList = new ArrayList<>();
        if (!this.l || TextUtils.isEmpty(str)) {
            return arrayList;
        }
        int i2 = 0;
        if (!z2 && (c2 = c(str)) != null && c2.size() > 0) {
            arrayList.addAll(c2);
            i2 = c2.size();
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder();
        int i3 = 1;
        while (i2 < length) {
            char charAt = str.charAt(i2);
            if (charAt == ' ') {
                if (sb.length() > 0) {
                    a(sb, arrayList, i3);
                }
                if (!z) {
                    String valueOf = String.valueOf(' ');
                    arrayList.add(new HanziToPinyin.Token(3, valueOf, valueOf));
                }
            } else {
                if (charAt < 256) {
                    if (i3 != 1 && sb.length() > 0) {
                        a(sb, arrayList, i3);
                    }
                    sb.append(charAt);
                    i3 = 1;
                } else if (charAt < 13312) {
                    if (i3 != 3 && sb.length() > 0) {
                        a(sb, arrayList, i3);
                    }
                    sb.append(charAt);
                } else {
                    HanziToPinyin.Token b2 = b(charAt);
                    if (b2.e == 2) {
                        if (sb.length() > 0) {
                            a(sb, arrayList, i3);
                        }
                        arrayList.add(b2);
                        i3 = 2;
                    } else {
                        if (i3 != b2.e && sb.length() > 0) {
                            a(sb, arrayList, i3);
                        }
                        i3 = b2.e;
                        sb.append(charAt);
                    }
                }
                i2++;
            }
            i3 = 3;
            i2++;
        }
        if (sb.length() > 0) {
            a(sb, arrayList, i3);
        }
        return arrayList;
    }

    private void a(StringBuilder sb, ArrayList<HanziToPinyin.Token> arrayList, int i2) {
        String sb2 = sb.toString();
        arrayList.add(new HanziToPinyin.Token(i2, sb2, sb2));
        sb.setLength(0);
    }

    public static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        StringBuilder sb = new StringBuilder(str.length());
        for (int i2 = 0; i2 < str.length(); i2++) {
            char charAt = str.charAt(i2);
            if (charAt >= 'A' && charAt <= 'Z') {
                sb.append(m[charAt - 'A']);
            } else if (charAt >= 'a' && charAt <= 'z') {
                sb.append(m[charAt - 'a']);
            } else if (charAt >= '0' && charAt <= '9') {
                sb.append((char) charAt);
            }
        }
        return sb.toString();
    }

    public static char a(char c2) {
        if (c2 >= 'A' && c2 <= 'Z') {
            return m[c2 - 'A'];
        }
        if (c2 >= 'a' && c2 <= 'z') {
            return m[c2 - 'a'];
        }
        if (c2 < '0' || c2 > '9') {
            return 0;
        }
        return c2;
    }
}
