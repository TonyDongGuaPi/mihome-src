package com.xiaomi.jr.verification;

import android.content.Context;
import com.xiaomi.jr.verification.livenessdetection.DetectorConfig;

public interface IVerificationDelegate {
    void a(Context context, DetectorConfig detectorConfig, String str, String str2);
}
