package cn.com.fmsh.tsm.business.exception;

import cn.com.fmsh.FM_Exception;
import com.mi.global.shop.model.Tags;
import com.unionpay.tsmservice.data.ResultCode;

public class BusinessException extends FM_Exception {
    private static final long serialVersionUID = 1392832484088949611L;
    private ErrorMessage errorMsg;

    public BusinessException(String str) {
        super(str);
    }

    public BusinessException(String str, ErrorMessage errorMessage) {
        super(str);
        this.errorMsg = errorMessage;
    }

    public ErrorMessage getErrorMsg() {
        return this.errorMsg;
    }

    public enum ErrorMessage {
        local_business_cancel("9001", ErrorType.local, "业务处理过程中，接收到取消指令"),
        local_business_apdu_handler_null("9002", ErrorType.local, "APDU指令处理器未注册"),
        local_business_execute_fail("9003", ErrorType.local, "APDU指令处理器处理APDU请求失败"),
        local_business_init_fail("9004", ErrorType.local, "业务处理器初始化失败"),
        local_business_no_card_app_type("9005", ErrorType.local, "没有对应类型的卡"),
        local_business_apdu_handler_busying("9006", ErrorType.local, "APDU指令处理器正忙"),
        local_message_platform_business_handle_fail("9007", ErrorType.local, "平台处理业务失败"),
        local_business_local_data_handler_null("9008", ErrorType.local, "本地数据处理器为空"),
        local_business_para_error("9009", ErrorType.local, "函数调用时，参数异常"),
        local_communication_connect_fail("9010", ErrorType.local, "终端和平台的链接失败"),
        local_communication_connect_param_error("9011", ErrorType.local, "终端向平台链接请求时，传入的平台链接信息无效"),
        local_communication_disconnect_fail("9012", ErrorType.local, "终端关闭到终端的链接出现异常"),
        local_communication_sign_in_fail("9013", ErrorType.local, "终端签到失败"),
        local_communication_request_param_error("9014", ErrorType.local, "终端向平台业务请求时，终端指令无效"),
        local_communication_no_response("9017", ErrorType.local, "终端未收到平台的应答数据"),
        local_communication_invalid_version("9018", ErrorType.local, "终端通信包的版本无效"),
        local_communication_invalid_format("9019", ErrorType.local, "终端请求数据格式无效"),
        local_communication_invalid_verify("9020", ErrorType.local, "报文检验失败"),
        local_communication_invalid_control("9021", ErrorType.local, "无效的报文控制字"),
        local_communication_invalid_session("9022", ErrorType.local, "无效的会话"),
        local_communication_invalid_session_serial("9023", ErrorType.local, "会话流水错误"),
        local_communication_invalid_direction("9024", ErrorType.local, "会话流水错误"),
        local_communication_invalid_response("9025", ErrorType.local, "通讯应答数据无效"),
        local_communication_no_key("9027", ErrorType.local, "通信时，未找不到通信公钥"),
        local_communication_sign_out_fail("9028", ErrorType.local, "签退失败"),
        local_communication_register_notify_exception("9030", ErrorType.local, "通信消息处理器注册异常"),
        local_message_load_config_fail("9050", ErrorType.local, "加载Message配置文件失败"),
        local_message_command_data_invaild("9051", ErrorType.local, "无效的平台响应数据"),
        local_message_apdu_execute_exception("9053", ErrorType.local, "APDU指令执行异常"),
        local_message_message_handle_exception("9054", ErrorType.local, "Message处理出现异常"),
        local_message_open_mobile_exception("9055", ErrorType.local, "Open mobile出现异常"),
        local_get_app_info_fail("9080", ErrorType.local, "获取卡上应用信息失败"),
        local_apdu_reponse_invalid("9081", ErrorType.local, "apdu指令的响应无效"),
        local_get_app_info_no_sptcc("9082", ErrorType.local, "不是上海交通卡"),
        local_app_load_config_fail("9070", ErrorType.local, "业务配置文件加载失败"),
        local_app_config_invaild_content("9071", ErrorType.local, "业务配置文件内容无效"),
        local_app_query_app_no_fail("9072", ErrorType.local, "终端获取卡的应用序列号失败"),
        local_app_query_server_fail("9073", ErrorType.local, "根据根据业务信息获取待访问的平台失败"),
        business_order_codenot_exist("0001", ErrorType.remote, "交易代码不存在"),
        business_invalid_message_format("0002", ErrorType.remote, "报文格式错误"),
        business_invalid_message_type("0003", ErrorType.remote, "无效的消息类型"),
        business_message_check_fail("0004", ErrorType.remote, "报文检验失败"),
        business_business_no_support(ResultCode.ERROR_DETAIL_SKMS_AGENT_NOT_INSTALL, ErrorType.remote, "该业务暂不支持"),
        business_platform_busy(ResultCode.ERROR_DETAIL_SKMS_AGENT_NEED_UPDATE, ErrorType.remote, "系统忙,请稍候再试"),
        business_invalid_terminal(ResultCode.ERROR_DETAIL_SKMS_AGENT_MUST_UPDATE, ErrorType.remote, "手机终端号无效"),
        business_operate_timeout(ResultCode.ERROR_DETAIL_SKMS_AGENT_ERROR, ErrorType.remote, "操作超时，请重新登录"),
        business_repeat_message("0010", ErrorType.remote, "重复包"),
        business_message_invalid_serial(ResultCode.ERROR_DETAIL_TRANSMIT_APDU, ErrorType.remote, "包序列号不连续"),
        business_serial_not_exist(ResultCode.ERROR_DETAIL_SE_BUSY, ErrorType.remote, "原终端交易流水号不存在"),
        business_system_error(ResultCode.ERROR_DETAIL_NO_AVAILABLE_CHANNEL, ErrorType.remote, "系统错误"),
        business_invalid_message_length("0015", ErrorType.remote, "报文长度错误"),
        business_trade_timeout(ResultCode.ERROR_DETAIL_CASHLOAD_FAIL, ErrorType.remote, "交易超时"),
        business_1920_unknow(ResultCode.ERROR_DETAIL_CASHLOAD_CANCEL, ErrorType.remote, "1920未知错误"),
        business_interface_version_error(ResultCode.ERROR_DETAIL_GETCARDINFO_SPAY_FAIL, ErrorType.remote, "业务接口版本错误"),
        business_merchants_not_exist("0019", ErrorType.remote, "商户不存在"),
        business_business_stop(ResultCode.ERROR_DETAIL_GETCARDINFO_SPAY_CANCLE, ErrorType.remote, "对该商户已停止服务"),
        business_business_will_exist(ResultCode.ERROR_DETAIL_INITIALIZE_SSAMSUNGPAY_FAIL, ErrorType.remote, "业务即将推出，尽请期待"),
        business_system_unknow_error("0099", ErrorType.remote, "系统未知错误"),
        OT_CHECK_FAIL("5011", ErrorType.remote, "应用安装准备本地资格审查失败"),
        OT_APPLY_SIR_FAIL("5012", ErrorType.remote, "向欧贝特申请服务实例引用号（sir）失败"),
        OT_STATE_CHANGE_NOTICE("5013", ErrorType.remote, "向欧贝特发起服务状态变更通知失败"),
        OT_AC_REQUEST_NOTICE("5014", ErrorType.remote, "向欧贝特发起携带AC规则的异步服务部署请求失败"),
        OT_BUSY("9029", ErrorType.remote, "OT平台正在处理安装准备"),
        user_unregistered(Tags.LuckyShake.VALUE_SUCCESS_CODE, ErrorType.remote, "用户未注册"),
        user_incorrect_password("1001", ErrorType.remote, "用户密码不正确"),
        user_not_sign(Tags.LuckyShake.VALUE_FAIL_OVER_TIME, ErrorType.remote, "用户未签约"),
        user_sign_apply(Tags.LuckyShake.VALUE_FAIL_HITTED, ErrorType.remote, "用户在签约申请中"),
        user_sign_fail("1004", ErrorType.remote, "签约失败"),
        user_sign_sucess(Tags.LuckyShake.VALUE_FAIL_REMAIN_CODE, ErrorType.remote, "签约已成功"),
        user_logout("1006", ErrorType.remote, "用户已注销"),
        user_register("1007", ErrorType.remote, "用户已注册"),
        user_severance("1008", ErrorType.remote, "用户已解约"),
        user_not_login("1009", ErrorType.remote, "用户未登录"),
        user_id_not_matching("1010", ErrorType.remote, "用户身份证信息不匹配"),
        user_locked("1011", ErrorType.remote, "用户已锁定"),
        user_freeze("1012", ErrorType.remote, "用户已冻结"),
        user_get_password_count_exceed("1013", ErrorType.remote, "密码找回已经达到当日上限"),
        user_info_incomplete(Tags.LuckyShake.VALUE_HIT_FAIL, ErrorType.remote, "用户注册信息不完整，请补充登记相关信息"),
        user_auth_code_invalid("1016", ErrorType.remote, "用户认证码校验失败"),
        user_auth_code_mobile_invalid("1017", ErrorType.remote, "验证码手机号和注册手机号不一致"),
        not_support_retrieve_email("1018", ErrorType.remote, "密码不支持邮箱找回"),
        user_auth_code_type_invalid("1019", ErrorType.remote, "验证码类型不匹配"),
        user_auth_code_expire("1020", ErrorType.remote, "验证码已过期"),
        sptc_open_exception("1021", ErrorType.remote, "交通卡开通发生异常，请重试"),
        sptc_close_exception("1022", ErrorType.remote, "交通卡关闭发生异常，请重试"),
        sptc_personalization_fail("1023", ErrorType.remote, "交通卡个人化失败"),
        app_issuer_fail("1024", ErrorType.remote, "用户卡发行失败"),
        sptc_data_not_matching("1029", ErrorType.remote, "交通卡数据不匹配"),
        card_invaild_check("1030", ErrorType.remote, "卡片验证无效"),
        card_not_order("1031", ErrorType.remote, "卡片订购关系不存在"),
        card_order_by_other("1032", ErrorType.remote, "卡片订购关系不匹配，已被其他注册用户订购"),
        user_order_open("1033", ErrorType.remote, "用户已订购且卡已开通"),
        user_unsubscribe_closed("1034", ErrorType.remote, "用户已退订且关闭了功能"),
        user_order_no_open("1035", ErrorType.remote, "用户已订购但还未开通"),
        user_order_fail("1036", ErrorType.remote, "用户订购失败"),
        user_unsubscribe_fail("1037", ErrorType.remote, "用户退订失败"),
        user_order_invaild_info("1038", ErrorType.remote, "订购信息数据有问题"),
        sptc_app_not_issuer("1039", ErrorType.remote, "交通卡应用未下载"),
        sptc_personalization("1040", ErrorType.remote, "交通卡已完成个人化"),
        invaild_personalization_info("1041", ErrorType.remote, "个人化数据不足"),
        ack_card_app_unpersonal("1042", ErrorType.remote, "应用未个人化"),
        ack_card_app_unprepare("1043", ErrorType.remote, "应用安装未准备"),
        ack_card_app_order_fail("1044", ErrorType.remote, "用户非订购成功状态"),
        ack_card_app_unorder_fail("1045", ErrorType.remote, "用户非退订成功状态"),
        no_suppert_in_card("1046", ErrorType.remote, "暂不支持内卡"),
        no_suppert_out_card("1047", ErrorType.remote, "暂不支持外卡"),
        business_order_not_exist("1101", ErrorType.remote, "订单不存在"),
        business_order_apply_no_pay("1102", ErrorType.remote, "订单已申请、未扣款"),
        business_order_pay_no_write("1103", ErrorType.remote, "订单已扣款、未充值"),
        business_order_recharge_sucess("1104", ErrorType.remote, "订单已经充值成功"),
        business_order_amount_inconsistent("1105", ErrorType.remote, "订单金额不符"),
        business_order_unsettled_exist("1106", ErrorType.remote, "存在可疑订单"),
        business_order_recharget_fail("1107", ErrorType.remote, "订单交易失败"),
        business_order_apply_refund("1108", ErrorType.remote, "订单申请退款中"),
        business_order_refund("1109", ErrorType.remote, "订单已退款"),
        business_order_rechargeting("1110", ErrorType.remote, "订单正在充值中"),
        business_order_paying("1111", ErrorType.remote, "订单正在支付中"),
        business_order_no_refund("1112", ErrorType.remote, "订单不能退款"),
        business_order_card_no_inconsistent("1113", ErrorType.remote, "订单绑定的卡号和本次交易的卡号不一致"),
        business_order_invoice("1114", ErrorType.remote, "发票已领取"),
        business_order_no_invoice("1115", ErrorType.remote, "该笔交易已失效，不能领取发票"),
        business_unsettled_overrun("1116", ErrorType.remote, "可疑订单超过上限，请进行处理"),
        order_apply_frequent("1126", ErrorType.remote, "发票已领取"),
        app_move_code_invalid("1127", ErrorType.remote, "应用迁移过了有效期"),
        order_amount_low("1128", ErrorType.remote, "订单申请金额小于配置"),
        trade_not_exist("1201", ErrorType.remote, "交易不存在"),
        trade_handling("1202", ErrorType.remote, "交易处理中"),
        trade_fail("1203", ErrorType.remote, "交易失败"),
        trade_sucess("1204", ErrorType.remote, "交易成功"),
        trade_act_check_fail("1207", ErrorType.remote, "活动代码校验失败"),
        trade_act_used("1208", ErrorType.remote, "活动代码已使用"),
        apdu_exec_fail("1209", ErrorType.remote, "发卡终端指令执行失败"),
        no_stock("1210", ErrorType.remote, "卡数据库存不足"),
        ese_no_space("1211", ErrorType.remote, "终端eSE空间不足"),
        load_more("1212", ErrorType.remote, "圈存错误次数超限"),
        apdu_result_ffff("1213", ErrorType.remote, "终端指令执行返回FFFF"),
        card_balance_overrun("3013", ErrorType.remote, "卡上余额超限"),
        amount_invalid("3016", ErrorType.remote, "非法交易金额"),
        pasm_overrun("3017", ErrorType.remote, "PASM金额超过当日上限"),
        card_invalid("3018", ErrorType.remote, "交通卡异常或非法卡"),
        no_activity("5001", ErrorType.remote, "该厂商没有活动信息");
        
        private String desc;
        private String id;
        private ErrorType type;

        enum ErrorType {
            local,
            remote
        }

        private ErrorMessage(String str, ErrorType errorType, String str2) {
            this.id = str;
            this.type = errorType;
            this.desc = str2;
        }

        public String getId() {
            return this.id;
        }

        public ErrorType getType() {
            return this.type;
        }

        public String getDesc() {
            return this.desc;
        }

        public static ErrorMessage instance(String str) {
            for (ErrorMessage errorMessage : values()) {
                if (errorMessage.getId().equals(str)) {
                    return errorMessage;
                }
            }
            return business_system_unknow_error;
        }
    }
}
