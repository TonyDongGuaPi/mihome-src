package cn.com.fmsh.tsm.business.core;

import cn.com.fmsh.communication.message.IMessage;
import cn.com.fmsh.communication.message.IMessageHandler;
import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.tsm.business.BusinessExtend;
import cn.com.fmsh.tsm.business.bean.ElectronicAndActivity;
import cn.com.fmsh.tsm.business.bean.ElectronicTakeUp;
import cn.com.fmsh.tsm.business.bean.IdentifyingCode;
import cn.com.fmsh.tsm.business.bean.MainOrder;
import cn.com.fmsh.tsm.business.bean.PromotionMessage;
import cn.com.fmsh.tsm.business.constants.Constants;
import cn.com.fmsh.tsm.business.enums.EnumCardIoType;
import cn.com.fmsh.tsm.business.exception.BusinessException;
import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.FM_CN;
import cn.com.fmsh.util.Util4Java;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import com.xiaomi.smarthome.auth.AuthCode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BusinessExtendImpl implements BusinessExtend {
    private CardBusinessBasic cardBusinessBasic;
    FMLog fmLog = null;
    private final String logTag = CardAppTradeImpl.class.getName();

    public BusinessExtendImpl(CardBusinessBasic cardBusinessBasic2) {
        this.cardBusinessBasic = cardBusinessBasic2;
        this.fmLog = LogFactory.getInstance().getLog();
    }

    public int applyForElectronicTakeUp(byte[] bArr, byte[] bArr2) throws BusinessException {
        byte[] bArr3;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "电子券申领...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("电子券申领") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("电子券申领") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.warn(str2, String.valueOf("电子券申领") + "，消息处理器为空");
            }
            throw new BusinessException(String.valueOf("电子券申领") + "，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else if (bArr == null) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str3 = this.logTag;
                fMLog3.warn(str3, String.valueOf("电子券申领") + "，没有传入电子券标识");
            }
            throw new BusinessException(String.valueOf("电子券申领") + "，没有传入电子券标识", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else if (bArr2 == null) {
            if (this.fmLog != null) {
                FMLog fMLog4 = this.fmLog;
                String str4 = this.logTag;
                fMLog4.warn(str4, String.valueOf("电子券申领") + "，没有传入电子券类型");
            }
            throw new BusinessException(String.valueOf("电子券申领") + "，没有传入电子券类型", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else {
            String server4Business = this.cardBusinessBasic.getServer4Business(4611);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    FMLog fMLog5 = this.fmLog;
                    String str5 = this.logTag;
                    fMLog5.warn(str5, String.valueOf("电子券申领") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("电子券申领") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("电子券申领", server4Business);
            IMessage createMessage = messageHandler.createMessage(4611);
            try {
                ITag createTag = messageHandler.createTag((byte) Constants.TagName.ELECTRONIC_ID);
                createTag.addValue(bArr);
                createMessage.addTag(createTag);
                ITag createTag2 = messageHandler.createTag((byte) Constants.TagName.ELECTRONIC_TYPE_ID);
                createTag2.addValue(bArr2);
                createMessage.addTag(createTag2);
                bArr3 = createMessage.toBytes();
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    FMLog fMLog6 = this.fmLog;
                    String str6 = this.logTag;
                    fMLog6.warn(str6, String.valueOf("电子券申领") + "时，构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
                }
                CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
                cardBusinessBasic2.throwExceptionAndClose(String.valueOf("电子券申领") + "时，构造平台请求数据出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr3 = null;
            }
            byte[] interaction = this.cardBusinessBasic.interaction(bArr3, "电子券申领", false, server4Business);
            byte[] bArr4 = new byte[2];
            System.arraycopy(interaction, 0, bArr4, 0, bArr4.length);
            if (Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, bArr4)) {
                return 0;
            }
            if (this.fmLog != null) {
                FMLog fMLog7 = this.fmLog;
                String str7 = this.logTag;
                fMLog7.warn(str7, "用户信息修改:" + FM_Bytes.bytesToHexString(interaction));
            }
            return FM_CN.bcdBytesToInt(bArr4);
        }
    }

    public MainOrder useElectronicTakeUp(byte[] bArr, byte[] bArr2, byte[] bArr3, EnumCardIoType enumCardIoType) throws BusinessException {
        byte[] bArr4;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "电子券使用...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("电子券使用") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("电子券使用") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.warn(str2, String.valueOf("电子券使用") + "，消息处理器为空");
            }
            throw new BusinessException(String.valueOf("电子券使用") + "，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else if (bArr == null) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str3 = this.logTag;
                fMLog3.warn(str3, String.valueOf("电子券使用") + "，没有传入电子券标识");
            }
            throw new BusinessException(String.valueOf("电子券使用") + "，没有传入电子券标识", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else if (bArr2 == null) {
            if (this.fmLog != null) {
                FMLog fMLog4 = this.fmLog;
                String str4 = this.logTag;
                fMLog4.warn(str4, String.valueOf("电子券使用") + "，没有传入电子券类型");
            }
            throw new BusinessException(String.valueOf("电子券使用") + "，没有传入电子券类型", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else if (bArr3 == null || bArr3.length < 1) {
            if (this.fmLog != null) {
                FMLog fMLog5 = this.fmLog;
                String str5 = this.logTag;
                fMLog5.warn(str5, String.valueOf("电子券使用") + "，传入的卡号无效");
            }
            throw new BusinessException(String.valueOf("电子券使用") + "，传入的卡号无效", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else {
            String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.USE_ELECTRONIC_TAKEUP);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    FMLog fMLog6 = this.fmLog;
                    String str6 = this.logTag;
                    fMLog6.warn(str6, String.valueOf("电子券使用") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("电子券使用") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("电子券使用", server4Business);
            IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.USE_ELECTRONIC_TAKEUP);
            try {
                ITag createTag = messageHandler.createTag((byte) Constants.TagName.ELECTRONIC_ID);
                createTag.addValue(bArr);
                createMessage.addTag(createTag);
                ITag createTag2 = messageHandler.createTag((byte) Constants.TagName.ELECTRONIC_TYPE_ID);
                createTag2.addValue(bArr2);
                createMessage.addTag(createTag2);
                ITag createTag3 = messageHandler.createTag((byte) 15);
                createTag3.addValue(FM_Bytes.bytesToHexString(bArr3));
                createMessage.addTag(createTag3);
                ITag createTag4 = messageHandler.createTag((byte) Constants.TagName.CARD_FORM);
                createTag4.addValue(enumCardIoType.getId());
                createMessage.addTag(createTag4);
                bArr4 = createMessage.toBytes();
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    FMLog fMLog7 = this.fmLog;
                    String str7 = this.logTag;
                    fMLog7.warn(str7, String.valueOf("电子券使用") + "时，构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
                }
                CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
                cardBusinessBasic2.throwExceptionAndClose(String.valueOf("电子券使用") + "时，构造平台请求数据出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr4 = null;
            }
            byte[] interaction = this.cardBusinessBasic.interaction(bArr4, "电子券使用", false, server4Business);
            byte[] bArr5 = new byte[2];
            System.arraycopy(interaction, 0, bArr5, 0, bArr5.length);
            if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, bArr5)) {
                if (this.fmLog != null) {
                    FMLog fMLog8 = this.fmLog;
                    String str8 = this.logTag;
                    fMLog8.error(str8, String.valueOf("电子券使用") + "时，平台处理失败: " + FM_Bytes.bytesToHexString(interaction));
                }
                CardBusinessBasic cardBusinessBasic3 = this.cardBusinessBasic;
                cardBusinessBasic3.throwExceptionAndClose(String.valueOf("电子券使用") + "时，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(bArr5)), false);
            }
            try {
                ITag tag4Id = messageHandler.createMessage(Constants.TradeCode.USE_ELECTRONIC_TAKEUP, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(96);
                if (tag4Id != null) {
                    return MainOrder.fromTag(tag4Id);
                }
            } catch (FMCommunicationMessageException e2) {
                if (this.fmLog != null) {
                    FMLog fMLog9 = this.fmLog;
                    String str9 = this.logTag;
                    fMLog9.error(str9, String.valueOf("电子券使用") + "时，解析平台响应出现异常: " + Util4Java.getExceptionInfo(e2));
                }
                CardBusinessBasic cardBusinessBasic4 = this.cardBusinessBasic;
                cardBusinessBasic4.throwExceptionAndClose(String.valueOf("电子券使用") + "时，解析平台响应失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            }
            return null;
        }
    }

    public ElectronicTakeUp queryElectronicTakeUp(byte[] bArr, byte[] bArr2) throws BusinessException {
        byte[] bArr3;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "电子券详细信息查询...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("电子券详细信息查询") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("电子券详细信息查询") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.warn(str2, String.valueOf("电子券详细信息查询") + "，消息处理器为空");
            }
            throw new BusinessException(String.valueOf("电子券详细信息查询") + "，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else if (bArr == null) {
            if (this.fmLog != null) {
                FMLog fMLog3 = this.fmLog;
                String str3 = this.logTag;
                fMLog3.warn(str3, String.valueOf("电子券详细信息查询") + "，没有传入电子券标识");
            }
            throw new BusinessException(String.valueOf("电子券详细信息查询") + "，没有传入电子券标识", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else if (bArr2 == null) {
            if (this.fmLog != null) {
                FMLog fMLog4 = this.fmLog;
                String str4 = this.logTag;
                fMLog4.warn(str4, String.valueOf("电子券详细信息查询") + "，没有传入电子券类型");
            }
            throw new BusinessException(String.valueOf("电子券详细信息查询") + "，没有传入电子券类型", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else {
            String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_ELECTRONIC_TAKEUP);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    FMLog fMLog5 = this.fmLog;
                    String str5 = this.logTag;
                    fMLog5.warn(str5, String.valueOf("电子券详细信息查询") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("电子券详细信息查询") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("电子券详细信息查询", server4Business);
            IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.QUERY_ELECTRONIC_TAKEUP);
            try {
                ITag createTag = messageHandler.createTag((byte) Constants.TagName.ELECTRONIC_ID);
                createTag.addValue(bArr);
                createMessage.addTag(createTag);
                ITag createTag2 = messageHandler.createTag((byte) Constants.TagName.ELECTRONIC_TYPE_ID);
                createTag2.addValue(bArr2);
                createMessage.addTag(createTag2);
                bArr3 = createMessage.toBytes();
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    FMLog fMLog6 = this.fmLog;
                    String str6 = this.logTag;
                    fMLog6.warn(str6, String.valueOf("电子券详细信息查询") + "时，构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
                }
                CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
                cardBusinessBasic2.throwExceptionAndClose(String.valueOf("电子券详细信息查询") + "时，构造平台请求数据出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr3 = null;
            }
            byte[] interaction = this.cardBusinessBasic.interaction(bArr3, "电子券详细信息查询", false, server4Business);
            if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, Arrays.copyOf(interaction, 2))) {
                if (this.fmLog != null) {
                    FMLog fMLog7 = this.fmLog;
                    String str7 = this.logTag;
                    fMLog7.error(str7, String.valueOf("电子券详细信息查询") + "时，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
                }
                CardBusinessBasic cardBusinessBasic3 = this.cardBusinessBasic;
                cardBusinessBasic3.throwExceptionAndClose(String.valueOf("电子券详细信息查询") + "时，平台处理失败", BusinessException.ErrorMessage.local_message_platform_business_handle_fail, true);
            }
            try {
                ITag tag4Id = messageHandler.createMessage(Constants.TradeCode.QUERY_ELECTRONIC_TAKEUP, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(107);
                if (tag4Id == null) {
                    if (this.fmLog != null) {
                        FMLog fMLog8 = this.fmLog;
                        String str8 = this.logTag;
                        fMLog8.warn(str8, String.valueOf("电子券详细信息查询") + "时,平台响应数据没有包含电子券信息");
                    }
                    CardBusinessBasic cardBusinessBasic4 = this.cardBusinessBasic;
                    cardBusinessBasic4.throwExceptionAndClose(String.valueOf("电子券详细信息查询") + "时,平台响应数据没有包含电子券信息", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
                }
                return ElectronicTakeUp.fromTag(tag4Id);
            } catch (FMCommunicationMessageException unused) {
                if (this.fmLog != null) {
                    FMLog fMLog9 = this.fmLog;
                    String str9 = this.logTag;
                    fMLog9.warn(str9, String.valueOf("电子券详细信息查询") + "时,解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
                }
                CardBusinessBasic cardBusinessBasic5 = this.cardBusinessBasic;
                cardBusinessBasic5.throwExceptionAndClose(String.valueOf("电子券详细信息查询") + "时,解析平台响应数据失败", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
                return null;
            }
        }
    }

    public List<ElectronicTakeUp> queryElectronicTakeUps(byte[] bArr, byte b, int i) throws BusinessException {
        byte[] bArr2;
        ElectronicTakeUp fromTag;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "电子券列表信息查询...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("电子券列表信息查询") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("电子券列表信息查询") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("电子券列表信息查询") + "，消息处理器为空");
            }
            throw new BusinessException(String.valueOf("电子券列表信息查询") + "，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else if (i < 0) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("电子券列表信息查询") + "，传入的电子券查询条数不合法");
            }
            throw new BusinessException(String.valueOf("电子券列表信息查询") + "，传入的电子券查询条数不合法", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else if (b < 0) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("电子券列表信息查询") + "，传入的电子券状态不合法");
            }
            throw new BusinessException(String.valueOf("电子券列表信息查询") + "，传入的电子券状态不合法", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else {
            String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_ELECTRONIC_TAKEUP_LIST);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("电子券列表信息查询") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("电子券列表信息查询") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("电子券列表信息查询", server4Business);
            IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.QUERY_ELECTRONIC_TAKEUP_LIST);
            if (bArr != null) {
                try {
                    if (bArr.length > 0) {
                        ITag createTag = messageHandler.createTag((byte) Constants.TagName.ELECTRONIC_TYPE_ID);
                        createTag.addValue(bArr);
                        createMessage.addTag(createTag);
                    }
                } catch (FMCommunicationMessageException e) {
                    if (this.fmLog != null) {
                        this.fmLog.warn(this.logTag, String.valueOf("电子券列表信息查询") + "时，构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
                    }
                    this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("电子券列表信息查询") + "时，构造平台请求数据出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                    bArr2 = null;
                }
            }
            ITag createTag2 = messageHandler.createTag((byte) Constants.TagName.ELECTRONIC_STATE);
            createTag2.addValue((int) b);
            createMessage.addTag(createTag2);
            ITag createTag3 = messageHandler.createTag((byte) Constants.TagName.QUERY_RECORD_COUNT);
            createTag3.addValue(i);
            createMessage.addTag(createTag3);
            bArr2 = createMessage.toBytes();
            byte[] interaction = this.cardBusinessBasic.interaction(bArr2, "电子券列表信息查询", false, server4Business);
            byte[] copyOf = Arrays.copyOf(interaction, 2);
            if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, copyOf)) {
                if (this.fmLog != null) {
                    this.fmLog.error(this.logTag, String.valueOf("电子券列表信息查询") + "时，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
                }
                this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("电子券列表信息查询") + "时，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(copyOf)), true);
            }
            ArrayList arrayList = new ArrayList();
            try {
                ITag tag4Id = messageHandler.createMessage(Constants.TradeCode.QUERY_ELECTRONIC_TAKEUP_LIST, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(108);
                if (tag4Id == null) {
                    if (this.fmLog != null) {
                        this.fmLog.warn(this.logTag, String.valueOf("电子券列表信息查询") + "时，平台没有电子券信息");
                    }
                    return arrayList;
                }
                ITag[] itemTags = tag4Id.getItemTags();
                if (itemTags != null) {
                    if (itemTags.length >= 1) {
                        for (ITag iTag : itemTags) {
                            if (!(iTag == null || (fromTag = ElectronicTakeUp.fromTag(iTag)) == null)) {
                                arrayList.add(fromTag);
                            }
                        }
                        return arrayList;
                    }
                }
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("电子券列表信息查询") + "时,平台没有电子券信息集合信息为空");
                }
                return arrayList;
            } catch (FMCommunicationMessageException unused) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("电子券列表信息查询") + "时,解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
                }
                this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("电子券列表信息查询") + "时,解析平台响应数据失败", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
            }
        }
    }

    public List<ElectronicTakeUp> queryElectronicTakeUpsVer2(byte[] bArr, byte[] bArr2, int i, int i2) throws BusinessException {
        byte[] bArr3;
        ElectronicTakeUp fromTag;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "电子券列表信息查询...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("电子券列表信息查询") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("电子券列表信息查询") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("电子券列表信息查询") + "，消息处理器为空");
            }
            throw new BusinessException(String.valueOf("电子券列表信息查询") + "，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else if (i2 < 0) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("电子券列表信息查询") + "，传入的电子券查询条数不合法");
            }
            throw new BusinessException(String.valueOf("电子券列表信息查询") + "，传入的电子券查询条数不合法", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else if (i < 0) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("电子券列表信息查询") + "，传入的电子券状态不合法");
            }
            throw new BusinessException(String.valueOf("电子券列表信息查询") + "，传入的电子券状态不合法", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else {
            String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_ELECTRONIC_TAKEUP_LIST_VER2);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("电子券列表信息查询") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("电子券列表信息查询") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("电子券列表信息查询", server4Business);
            IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.QUERY_ELECTRONIC_TAKEUP_LIST_VER2);
            if (bArr2 != null) {
                try {
                    if (bArr2.length > 0) {
                        ITag createTag = messageHandler.createTag((byte) Constants.TagName.ELECTRONIC_TYPE_ID);
                        createTag.addValue(bArr2);
                        createMessage.addTag(createTag);
                    }
                } catch (FMCommunicationMessageException e) {
                    if (this.fmLog != null) {
                        this.fmLog.warn(this.logTag, String.valueOf("电子券列表信息查询") + "时，构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
                    }
                    this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("电子券列表信息查询") + "时，构造平台请求数据出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                    bArr3 = null;
                }
            }
            if (bArr != null && bArr.length > 0) {
                ITag createTag2 = messageHandler.createTag((byte) Constants.TagName.ACTIVITY_INFO);
                createTag2.addValue(bArr);
                createMessage.addTag(createTag2);
            }
            ITag createTag3 = messageHandler.createTag((byte) Constants.TagName.ELECTRONIC_STATE);
            createTag3.addValue(i);
            createMessage.addTag(createTag3);
            ITag createTag4 = messageHandler.createTag((byte) Constants.TagName.QUERY_RECORD_COUNT);
            createTag4.addValue(i2);
            createMessage.addTag(createTag4);
            bArr3 = createMessage.toBytes();
            byte[] interaction = this.cardBusinessBasic.interaction(bArr3, "电子券列表信息查询", false, server4Business);
            byte[] copyOf = Arrays.copyOf(interaction, 2);
            if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, copyOf)) {
                if (this.fmLog != null) {
                    this.fmLog.error(this.logTag, String.valueOf("电子券列表信息查询") + "时，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
                }
                this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("电子券列表信息查询") + "时，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(copyOf)), true);
            }
            ArrayList arrayList = new ArrayList();
            try {
                ITag tag4Id = messageHandler.createMessage(Constants.TradeCode.QUERY_ELECTRONIC_TAKEUP_LIST_VER2, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(108);
                if (tag4Id == null) {
                    if (this.fmLog != null) {
                        this.fmLog.warn(this.logTag, String.valueOf("电子券列表信息查询") + "时，平台没有电子券信息");
                    }
                    return arrayList;
                }
                ITag[] itemTags = tag4Id.getItemTags();
                if (itemTags != null) {
                    if (itemTags.length >= 1) {
                        for (ITag iTag : itemTags) {
                            if (!(iTag == null || (fromTag = ElectronicTakeUp.fromTag(iTag)) == null)) {
                                arrayList.add(fromTag);
                            }
                        }
                        return arrayList;
                    }
                }
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("电子券列表信息查询") + "时,平台没有电子券信息集合信息为空");
                }
                return arrayList;
            } catch (FMCommunicationMessageException unused) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("电子券列表信息查询") + "时,解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
                }
                this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("电子券列表信息查询") + "时,解析平台响应数据失败", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
            }
        }
    }

    public IdentifyingCode obtainIdentifyingCode(int i, String str) throws BusinessException {
        byte[] bArr;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "获取验证码信息...");
        }
        if (i < 0) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str2 = this.logTag;
                fMLog.warn(str2, String.valueOf("获取验证码信息") + "，没有输入获取验证码的类型");
            }
            throw new BusinessException(String.valueOf("获取验证码信息") + "，没有输入获取验证码的类型", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str3 = this.logTag;
                fMLog2.warn(str3, String.valueOf("获取验证码信息") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("获取验证码信息") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    FMLog fMLog3 = this.fmLog;
                    String str4 = this.logTag;
                    fMLog3.warn(str4, String.valueOf("获取验证码信息") + "，消息处理器为空");
                }
                throw new BusinessException(String.valueOf("获取验证码信息") + "，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(1061);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    FMLog fMLog4 = this.fmLog;
                    String str5 = this.logTag;
                    fMLog4.warn(str5, String.valueOf("获取验证码信息") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("获取验证码信息") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("获取验证码信息", server4Business);
            IMessage createMessage = messageHandler.createMessage(1061);
            if (str != null) {
                try {
                    ITag createTag = messageHandler.createTag((byte) 5);
                    createTag.addValue(str);
                    createMessage.addTag(createTag);
                } catch (FMCommunicationMessageException e) {
                    if (this.fmLog != null) {
                        FMLog fMLog5 = this.fmLog;
                        String str6 = this.logTag;
                        fMLog5.warn(str6, String.valueOf("获取验证码信息") + "时，构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
                    }
                    CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
                    cardBusinessBasic2.throwExceptionAndClose(String.valueOf("获取验证码信息") + "时，构造平台请求数据出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                    bArr = null;
                }
            }
            ITag createTag2 = messageHandler.createTag((byte) 11);
            createTag2.addValue(i);
            createMessage.addTag(createTag2);
            bArr = createMessage.toBytes();
            byte[] interaction = this.cardBusinessBasic.interaction(bArr, "获取验证码信息", false, server4Business);
            if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, Arrays.copyOf(interaction, 2))) {
                if (this.fmLog != null) {
                    FMLog fMLog6 = this.fmLog;
                    String str7 = this.logTag;
                    fMLog6.error(str7, String.valueOf("获取验证码信息") + "时，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
                }
                CardBusinessBasic cardBusinessBasic3 = this.cardBusinessBasic;
                cardBusinessBasic3.throwExceptionAndClose(String.valueOf("获取验证码信息") + "时，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(Arrays.copyOf(interaction, 2))), true);
            }
            IdentifyingCode identifyingCode = new IdentifyingCode();
            try {
                IMessage createMessage2 = messageHandler.createMessage(1061, Arrays.copyOfRange(interaction, 2, interaction.length));
                ITag tag4Id = createMessage2.getTag4Id(12);
                if (tag4Id != null) {
                    identifyingCode.setCode(tag4Id.getStringVal());
                }
                ITag tag4Id2 = createMessage2.getTag4Id(64);
                if (tag4Id2 != null) {
                    identifyingCode.setSerial(tag4Id2.getTagValue());
                }
            } catch (FMCommunicationMessageException unused) {
                if (this.fmLog != null) {
                    FMLog fMLog7 = this.fmLog;
                    String str8 = this.logTag;
                    fMLog7.warn(str8, String.valueOf("获取验证码信息") + "时,解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
                }
                CardBusinessBasic cardBusinessBasic4 = this.cardBusinessBasic;
                cardBusinessBasic4.throwExceptionAndClose(String.valueOf("获取验证码信息") + "时,解析平台响应数据失败", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
            }
            return identifyingCode;
        }
    }

    public ElectronicAndActivity queryActivity(int i, int i2) throws BusinessException {
        byte[] bArr;
        int i3 = i;
        int i4 = i2;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "电子券列表信息查询...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("活动（电子券）信息查询") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("活动（电子券）信息查询") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("活动（电子券）信息查询") + "，消息处理器为空");
            }
            throw new BusinessException(String.valueOf("活动（电子券）信息查询") + "，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else if (i3 < 0) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("活动（电子券）信息查询") + "，传入的操作类型不合法");
            }
            throw new BusinessException(String.valueOf("活动（电子券）信息查询") + "，传入的操作类型不合法", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else if (i4 < 0) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("活动（电子券）信息查询") + "，传入的操作时序不合法");
            }
            throw new BusinessException(String.valueOf("活动（电子券）信息查询") + "，传入的操作时序不合法", BusinessException.ErrorMessage.local_message_load_config_fail);
        } else {
            String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_ELECTRONIC_ACTIVITY);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("活动（电子券）信息查询") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("活动（电子券）信息查询") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("活动（电子券）信息查询", server4Business);
            IMessage createMessage = messageHandler.createMessage((int) Constants.TradeCode.QUERY_ELECTRONIC_ACTIVITY);
            try {
                ITag createTag = messageHandler.createTag((byte) Constants.TagName.BUSINESS_ORDER_OP_TYPE);
                createTag.addValue(i3);
                createMessage.addTag(createTag);
                ITag createTag2 = messageHandler.createTag((byte) Constants.TagName.OPERATE_TIMING);
                createTag2.addValue(i4);
                createMessage.addTag(createTag2);
                bArr = createMessage.toBytes();
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("活动（电子券）信息查询") + "时，构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
                }
                this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("活动（电子券）信息查询") + "时，构造平台请求数据出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr = null;
            }
            byte[] interaction = this.cardBusinessBasic.interaction(bArr, "活动（电子券）信息查询", false, server4Business);
            byte[] copyOf = Arrays.copyOf(interaction, 2);
            if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, copyOf)) {
                if (this.fmLog != null) {
                    this.fmLog.error(this.logTag, String.valueOf("活动（电子券）信息查询") + "时，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
                }
                this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("活动（电子券）信息查询") + "时，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(copyOf)), true);
            }
            ElectronicAndActivity electronicAndActivity = new ElectronicAndActivity();
            try {
                IMessage createMessage2 = messageHandler.createMessage(Constants.TradeCode.QUERY_ELECTRONIC_ACTIVITY, Arrays.copyOfRange(interaction, 2, interaction.length));
                ITag tag4Id = createMessage2.getTag4Id(AuthCode.n);
                if (tag4Id != null) {
                    for (ITag iTag : tag4Id.getItemTags()) {
                        if (iTag != null) {
                            byte[] bArr2 = null;
                            byte[] bArr3 = null;
                            for (ITag iTag2 : iTag.getItemTags()) {
                                byte id = iTag2.getId();
                                if (id == -114) {
                                    bArr2 = iTag2.getBytesVal();
                                } else if (id == 28) {
                                    bArr3 = iTag2.getBytesVal();
                                }
                                electronicAndActivity.addUrl(bArr3, bArr2);
                            }
                        }
                    }
                }
                ITag tag4Id2 = createMessage2.getTag4Id(51);
                if (tag4Id2 != null) {
                    for (ITag iTag3 : tag4Id2.getItemTags()) {
                        if (iTag3 != null) {
                            byte[] bArr4 = null;
                            byte[] bArr5 = null;
                            for (ITag iTag4 : iTag3.getItemTags()) {
                                byte id2 = iTag4.getId();
                                if (id2 == 71) {
                                    bArr5 = iTag4.getBytesVal();
                                } else if (id2 == 113) {
                                    bArr4 = iTag4.getBytesVal();
                                }
                                electronicAndActivity.addActivity(bArr4, bArr5);
                            }
                        }
                    }
                }
            } catch (FMCommunicationMessageException unused) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("活动（电子券）信息查询") + "时,解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
                }
                this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("活动（电子券）信息查询") + "时,解析平台响应数据失败", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
            }
            return electronicAndActivity;
        }
    }

    public List<PromotionMessage> queryPromotionMessage() throws BusinessException {
        PromotionMessage fromTag;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "用户促销活动信息查询...");
        }
        if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("用户促销活动信息查询") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("用户促销活动信息查询") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        }
        IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
        if (messageHandler == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("用户促销活动信息查询") + "，消息处理器为空");
            }
            throw new BusinessException(String.valueOf("用户促销活动信息查询") + "，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
        }
        String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_ELECTRONIC_TAKEUP_LIST);
        if (server4Business == null) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("用户促销活动信息查询") + "时，获取处理的平台失败");
            }
            throw new BusinessException(String.valueOf("用户促销活动信息查询") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
        }
        this.cardBusinessBasic.businessReady("用户促销活动信息查询", server4Business);
        byte[] bArr = null;
        try {
            bArr = messageHandler.createMessage((int) Constants.TradeCode.QUERY_PROMOTION_MESSAGE_LIST).toBytes();
        } catch (FMCommunicationMessageException e) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("用户促销活动信息查询") + "时，构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("用户促销活动信息查询") + "时，构造平台请求数据出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
        }
        byte[] interaction = this.cardBusinessBasic.interaction(bArr, "用户促销活动信息查询", false, server4Business);
        byte[] copyOf = Arrays.copyOf(interaction, 2);
        if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, copyOf)) {
            if (this.fmLog != null) {
                this.fmLog.error(this.logTag, String.valueOf("用户促销活动信息查询") + "时，平台响应错误响应码: " + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("用户促销活动信息查询") + "时，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(copyOf)), true);
        }
        ArrayList arrayList = new ArrayList();
        try {
            ITag tag4Id = messageHandler.createMessage(Constants.TradeCode.QUERY_PROMOTION_MESSAGE_LIST, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(-57);
            if (tag4Id == null) {
                if (this.fmLog != null) {
                    this.fmLog.warn(this.logTag, String.valueOf("用户促销活动信息查询") + "时，平台没有用户促销信息");
                }
                return arrayList;
            }
            ITag[] itemTags = tag4Id.getItemTags();
            if (itemTags != null) {
                if (itemTags.length >= 1) {
                    for (ITag iTag : itemTags) {
                        if (!(iTag == null || (fromTag = PromotionMessage.fromTag(iTag)) == null)) {
                            arrayList.add(fromTag);
                        }
                    }
                    return arrayList;
                }
            }
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("用户促销活动信息查询") + "时,平台没有电子券信息集合信息为空");
            }
            return arrayList;
        } catch (FMCommunicationMessageException unused) {
            if (this.fmLog != null) {
                this.fmLog.warn(this.logTag, String.valueOf("用户促销活动信息查询") + "时,解析平台响应数据异常：" + FM_Bytes.bytesToHexString(interaction));
            }
            this.cardBusinessBasic.throwExceptionAndClose(String.valueOf("用户促销活动信息查询") + "时,解析平台响应数据失败", BusinessException.ErrorMessage.local_message_command_data_invaild, false);
        }
    }

    public MainOrder applyPromotion(byte[] bArr) throws BusinessException {
        byte[] bArr2;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (this.fmLog != null) {
            this.fmLog.info(this.logTag, "用户促销活订单申请...");
        }
        if (bArr == null || bArr.length < 1) {
            if (this.fmLog != null) {
                FMLog fMLog = this.fmLog;
                String str = this.logTag;
                fMLog.warn(str, String.valueOf("用户促销活订单申请") + "时，未传入签名数据");
            }
            throw new BusinessException(String.valueOf("用户促销活订单申请") + "时，传入的参数异常", BusinessException.ErrorMessage.local_business_para_error);
        } else if (this.cardBusinessBasic == null) {
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                String str2 = this.logTag;
                fMLog2.warn(str2, String.valueOf("用户促销活订单申请") + "，业务处理对象为空");
            }
            throw new BusinessException(String.valueOf("用户促销活订单申请") + "，业务处理器初始化失败", BusinessException.ErrorMessage.local_business_init_fail);
        } else {
            IMessageHandler messageHandler = this.cardBusinessBasic.getMessageHandler();
            if (messageHandler == null) {
                if (this.fmLog != null) {
                    FMLog fMLog3 = this.fmLog;
                    String str3 = this.logTag;
                    fMLog3.warn(str3, String.valueOf("用户促销活订单申请") + "，消息处理器为空");
                }
                throw new BusinessException(String.valueOf("用户促销活订单申请") + "，消息处理器为空", BusinessException.ErrorMessage.local_message_load_config_fail);
            }
            String server4Business = this.cardBusinessBasic.getServer4Business(Constants.TradeCode.QUERY_ELECTRONIC_TAKEUP_LIST);
            if (server4Business == null) {
                if (this.fmLog != null) {
                    FMLog fMLog4 = this.fmLog;
                    String str4 = this.logTag;
                    fMLog4.warn(str4, String.valueOf("用户促销活订单申请") + "时，获取处理的平台失败");
                }
                throw new BusinessException(String.valueOf("用户促销活订单申请") + "时，获取处理的平台失败", BusinessException.ErrorMessage.local_app_query_server_fail);
            }
            this.cardBusinessBasic.businessReady("用户促销活订单申请", server4Business);
            IMessage createMessage = messageHandler.createMessage(1201);
            try {
                ITag createTag = messageHandler.createTag((byte) Constants.TagName.ACTIVITY_INFO);
                createTag.addValue(bArr);
                createMessage.addTag(createTag);
                bArr2 = createMessage.toBytes();
            } catch (FMCommunicationMessageException e) {
                if (this.fmLog != null) {
                    FMLog fMLog5 = this.fmLog;
                    String str5 = this.logTag;
                    fMLog5.warn(str5, String.valueOf("用户促销活订单申请") + "时，构造平台请求数据出现异常：" + Util4Java.getExceptionInfo(e));
                }
                CardBusinessBasic cardBusinessBasic2 = this.cardBusinessBasic;
                cardBusinessBasic2.throwExceptionAndClose(String.valueOf("用户促销活订单申请") + "时，构造平台请求数据出现异常", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
                bArr2 = null;
            }
            byte[] interaction = this.cardBusinessBasic.interaction(bArr2, "用户促销活订单申请", false, server4Business);
            byte[] bArr3 = new byte[2];
            System.arraycopy(interaction, 0, bArr3, 0, bArr3.length);
            if (!Arrays.equals(Constants.RespCodeonse4Platform.SUCESS, bArr3)) {
                if (this.fmLog != null) {
                    FMLog fMLog6 = this.fmLog;
                    String str6 = this.logTag;
                    fMLog6.error(str6, String.valueOf("用户促销活订单申请") + "时，平台处理失败: " + FM_Bytes.bytesToHexString(interaction));
                }
                CardBusinessBasic cardBusinessBasic3 = this.cardBusinessBasic;
                cardBusinessBasic3.throwExceptionAndClose(String.valueOf("用户促销活订单申请") + "时，平台处理失败", BusinessException.ErrorMessage.instance(FM_Bytes.bytesToHexString(bArr3)), false);
            }
            try {
                ITag tag4Id = messageHandler.createMessage(1201, Arrays.copyOfRange(interaction, 2, interaction.length)).getTag4Id(96);
                if (tag4Id != null) {
                    return MainOrder.fromTag(tag4Id);
                }
            } catch (FMCommunicationMessageException e2) {
                if (this.fmLog != null) {
                    FMLog fMLog7 = this.fmLog;
                    String str7 = this.logTag;
                    fMLog7.error(str7, String.valueOf("用户促销活订单申请") + "时，解析平台响应出现异常: " + Util4Java.getExceptionInfo(e2));
                }
                CardBusinessBasic cardBusinessBasic4 = this.cardBusinessBasic;
                cardBusinessBasic4.throwExceptionAndClose(String.valueOf("用户促销活订单申请") + "时，解析平台响应失败", BusinessException.ErrorMessage.local_message_message_handle_exception, false);
            }
            return null;
        }
    }
}
