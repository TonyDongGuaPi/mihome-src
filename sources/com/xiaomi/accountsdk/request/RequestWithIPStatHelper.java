package com.xiaomi.accountsdk.request;

import java.util.ArrayList;
import java.util.List;

public abstract class RequestWithIPStatHelper extends IPStatHelper {
    public void finish() {
    }

    public void onBackupIpFailed(int i) {
    }

    public void onBackupIpStarted(int i, String str) {
    }

    public void onBackupIpSucceeded(int i) {
    }

    public void onCachedIpFailed() {
    }

    public void onCachedIpStarted(String str) {
    }

    public void onCachedIpSucceed() {
    }

    public void onDnsIp0Failed() {
    }

    public void onDnsIp0Succeed() {
    }

    public void onDnsResolvingFinished(String str) {
    }

    public void onDnsResolvingStarted() {
    }

    public void onDnsip0Started(String str) {
    }

    public void onHostNameRequestFinished(boolean z) {
    }

    public void onHostNameRequestStarted() {
    }

    public void onIpRequest200Succeed() {
    }

    public void onIpRequestFailed() {
    }

    public void onIpRequestIOSucceed() {
    }

    public void onIpRequestStarted() {
    }

    public void start() {
    }

    public static final class Composition extends RequestWithIPStatHelper {
        private final List<RequestWithIPStatHelper> entities = new ArrayList();

        public Composition(RequestWithIPStatHelper... requestWithIPStatHelperArr) {
            for (RequestWithIPStatHelper requestWithIPStatHelper : requestWithIPStatHelperArr) {
                if (requestWithIPStatHelper != null) {
                    this.entities.add(requestWithIPStatHelper);
                }
            }
        }

        public void onIpRequestStarted() {
            for (RequestWithIPStatHelper onIpRequestStarted : this.entities) {
                onIpRequestStarted.onIpRequestStarted();
            }
        }

        public void onIpRequest200Succeed() {
            for (RequestWithIPStatHelper onIpRequest200Succeed : this.entities) {
                onIpRequest200Succeed.onIpRequest200Succeed();
            }
        }

        public void onIpRequestIOSucceed() {
            for (RequestWithIPStatHelper onIpRequestIOSucceed : this.entities) {
                onIpRequestIOSucceed.onIpRequestIOSucceed();
            }
        }

        public void onIpRequestFailed() {
            for (RequestWithIPStatHelper onIpRequestFailed : this.entities) {
                onIpRequestFailed.onIpRequestFailed();
            }
        }

        public void onCachedIpStarted(String str) {
            for (RequestWithIPStatHelper onCachedIpStarted : this.entities) {
                onCachedIpStarted.onCachedIpStarted(str);
            }
        }

        public void onCachedIpSucceed() {
            for (RequestWithIPStatHelper onCachedIpSucceed : this.entities) {
                onCachedIpSucceed.onCachedIpSucceed();
            }
        }

        public void onCachedIpFailed() {
            for (RequestWithIPStatHelper onCachedIpFailed : this.entities) {
                onCachedIpFailed.onCachedIpFailed();
            }
        }

        public void onDnsip0Started(String str) {
            for (RequestWithIPStatHelper onDnsip0Started : this.entities) {
                onDnsip0Started.onDnsip0Started(str);
            }
        }

        public void onDnsIp0Succeed() {
            for (RequestWithIPStatHelper onDnsIp0Succeed : this.entities) {
                onDnsIp0Succeed.onDnsIp0Succeed();
            }
        }

        public void onDnsIp0Failed() {
            for (RequestWithIPStatHelper onDnsIp0Failed : this.entities) {
                onDnsIp0Failed.onDnsIp0Failed();
            }
        }

        public void onBackupIpStarted(int i, String str) {
            for (RequestWithIPStatHelper onBackupIpStarted : this.entities) {
                onBackupIpStarted.onBackupIpStarted(i, str);
            }
        }

        public void onBackupIpSucceeded(int i) {
            for (RequestWithIPStatHelper onBackupIpSucceeded : this.entities) {
                onBackupIpSucceeded.onBackupIpSucceeded(i);
            }
        }

        public void onBackupIpFailed(int i) {
            for (RequestWithIPStatHelper onBackupIpFailed : this.entities) {
                onBackupIpFailed.onBackupIpFailed(i);
            }
        }

        public void onHostNameRequestStarted() {
            for (RequestWithIPStatHelper onHostNameRequestStarted : this.entities) {
                onHostNameRequestStarted.onHostNameRequestStarted();
            }
        }

        public void onHostNameRequestFinished(boolean z) {
            for (RequestWithIPStatHelper onHostNameRequestFinished : this.entities) {
                onHostNameRequestFinished.onHostNameRequestFinished(z);
            }
        }

        public void onDnsResolvingStarted() {
            for (RequestWithIPStatHelper onDnsResolvingStarted : this.entities) {
                onDnsResolvingStarted.onDnsResolvingStarted();
            }
        }

        public void onDnsResolvingFinished(String str) {
            for (RequestWithIPStatHelper onDnsResolvingFinished : this.entities) {
                onDnsResolvingFinished.onDnsResolvingFinished(str);
            }
        }

        public void start() {
            for (RequestWithIPStatHelper start : this.entities) {
                start.start();
            }
        }

        public void finish() {
            for (RequestWithIPStatHelper finish : this.entities) {
                finish.finish();
            }
        }
    }
}
