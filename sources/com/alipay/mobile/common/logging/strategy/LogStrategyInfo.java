package com.alipay.mobile.common.logging.strategy;

import java.util.ArrayList;
import java.util.List;

public class LogStrategyInfo {
    boolean isWrite;
    List<String> sendCondition = new ArrayList();
    int threshold;
    List<String> uploadEvents = new ArrayList();
}
