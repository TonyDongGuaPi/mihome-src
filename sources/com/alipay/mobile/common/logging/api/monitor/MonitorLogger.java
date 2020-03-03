package com.alipay.mobile.common.logging.api.monitor;

import java.util.Map;

public interface MonitorLogger {
    void apm(String str, String str2, Throwable th, Map<String, String> map);

    void crash(Throwable th, String str);

    void dataflow(String str, String str2, long j, String str3, Map<String, String> map, long j2, long j3, long j4);

    void exception(ExceptionID exceptionID, Throwable th);

    void footprint(String str, String str2, String str3, String str4, String str5, Map<String, String> map);

    void keyBizTrace(String str, String str2, String str3, Map<String, String> map);

    void mtBizReport(String str, String str2, String str3, Map<String, String> map);

    void performance(PerformanceID performanceID, Performance performance);
}
