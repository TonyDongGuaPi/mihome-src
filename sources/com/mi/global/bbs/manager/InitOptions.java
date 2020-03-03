package com.mi.global.bbs.manager;

public class InitOptions {
    /* access modifiers changed from: private */
    public boolean useCrashReporter;
    /* access modifiers changed from: private */
    public boolean useStatistics;

    private InitOptions() {
        this.useCrashReporter = true;
        this.useStatistics = false;
    }

    public static class Builder {
        private boolean crashReporter = true;
        private boolean statistics = false;

        public Builder useCrashReporter(boolean z) {
            this.crashReporter = z;
            return this;
        }

        public Builder useStatistics(boolean z) {
            this.statistics = z;
            return this;
        }

        public InitOptions create() {
            InitOptions initOptions = new InitOptions();
            boolean unused = initOptions.useCrashReporter = this.crashReporter;
            boolean unused2 = initOptions.useStatistics = this.statistics;
            return initOptions;
        }
    }

    public boolean isUseCrashReporter() {
        return this.useCrashReporter;
    }

    public boolean isUseStatistics() {
        return this.useStatistics;
    }
}
