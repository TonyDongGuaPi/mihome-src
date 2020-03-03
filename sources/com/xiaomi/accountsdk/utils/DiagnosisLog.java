package com.xiaomi.accountsdk.utils;

import java.util.List;
import java.util.Map;

public class DiagnosisLog {
    private static DiagnosisLogInterface sLogger = new DiagnosisLogInterface() {
        public String logGetRequest(String str, Map<String, String> map, String str2, Map<String, String> map2, Map<String, String> map3) {
            return null;
        }

        public String logPostRequest(String str, Map<String, String> map, String str2, Map<String, String> map2, Map<String, String> map3, Map<String, String> map4) {
            return null;
        }

        public void logRequestException(Exception exc) {
        }

        public void logResponse(String str, String str2, Map<String, List<String>> map, Map<String, String> map2) {
        }

        public void logResponseCode(String str, int i) {
        }

        public void logResponseDecryptedBody(String str) {
        }
    };

    private DiagnosisLog() {
    }

    public static void set(DiagnosisLogInterface diagnosisLogInterface) {
        sLogger = diagnosisLogInterface;
    }

    public static DiagnosisLogInterface get() {
        return sLogger;
    }
}
