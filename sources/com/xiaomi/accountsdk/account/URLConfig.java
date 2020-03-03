package com.xiaomi.accountsdk.account;

import android.util.Base64;

class URLConfig {
    static String HyperTextTransferProtocol = new String(Base64.decode("aHR0cDovLw==", 0));

    private URLConfig() {
    }

    static class Production {
        private Production() {
        }

        static String getPassportCABaseUrl() {
            return URLConfig.HyperTextTransferProtocol + "c.id.mi.com";
        }

        static String getBaseUrl() {
            return URLConfig.HyperTextTransferProtocol + "api.account.xiaomi.com";
        }
    }

    static class Staging {
        private static String getApiHost() {
            return "api.account.preview.n.xiaomi.net";
        }

        static String getHost() {
            return "account.preview.n.xiaomi.net";
        }

        private Staging() {
        }

        static String getBaseUrl() {
            return URLConfig.HyperTextTransferProtocol + getHost();
        }

        static String getApiBaseUrl() {
            return URLConfig.HyperTextTransferProtocol + getApiHost();
        }

        static String getApiDeviceBaseUrl() {
            return URLConfig.HyperTextTransferProtocol + "api.device.preview.n.xiaomi.net";
        }

        static String getOpenBaseUrl() {
            return URLConfig.HyperTextTransferProtocol + "open.account.preview.n.xiaomi.net";
        }
    }
}
