package com.xiaomi.jr.deeplink;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.xiaomi.jr.deeplink.CustomDeeplinkHandler;
import com.xiaomi.jr.deeplink.DeeplinkConfig;
import java.util.Iterator;

public class DeeplinkPolicy {
    private static final String KEY_TARGET_TYPE = "__mifi_deeplink_target_type";
    private static final String TAG = "DeeplinkPolicy";

    public interface DeeplinkMatcher {
        boolean match(String str);
    }

    public interface DeeplinkProcessor {
        String process(Object obj, String str);
    }

    public interface TargetProcessor {
        void process(Object obj, String str, Target target);
    }

    public enum TargetType {
        UNSUPPORTED,
        ACTIVITY,
        CUSTOM
    }

    public static class Target {
        public Bundle extras;
        public String url;

        class Builder {
            Builder() {
            }
        }

        public Target(String str, Bundle bundle) {
            this.url = str;
            this.extras = bundle == null ? new Bundle() : bundle;
        }
    }

    public static class ActivityTarget extends Target {
        public Class<? extends Activity> clazz;
        public TargetProcessor processor;

        public ActivityTarget(Class<? extends Activity> cls, TargetProcessor targetProcessor, String str, Bundle bundle) {
            super(str, bundle);
            this.clazz = cls;
            this.processor = targetProcessor;
        }
    }

    public static class CustomTarget extends Target {
        public CustomDeeplinkHandler.Handler handler;

        public CustomTarget(CustomDeeplinkHandler.Handler handler2, String str, Bundle bundle) {
            super(str, bundle);
            this.handler = handler2;
        }
    }

    static void openDeeplink(Object obj, String str, Bundle bundle) {
        Intent buildIntent = buildIntent(Utils.toContext(obj), str, bundle);
        if (buildIntent == null) {
            return;
        }
        if (((TargetType) buildIntent.getSerializableExtra(KEY_TARGET_TYPE)) == TargetType.CUSTOM) {
            CustomDeeplinkHandler.handleIntent(obj, buildIntent);
            return;
        }
        int intExtra = buildIntent.getIntExtra(DeeplinkConstants.KEY_REQUEST_CODE, 0);
        if (intExtra != 0) {
            DeeplinkUtils.startActivityForResult(obj, buildIntent, intExtra);
        } else {
            DeeplinkUtils.startActivity(obj, buildIntent);
        }
    }

    @VisibleForTesting(otherwise = 2)
    public static Intent buildIntent(Context context, String str, Bundle bundle) {
        String str2 = null;
        if (context == null) {
            return null;
        }
        Uri parse = Uri.parse(str);
        if (parse.isOpaque()) {
            return new Intent("android.intent.action.VIEW", parse);
        }
        if (DeeplinkConfig.sDeeplinkProcessor != null) {
            str = DeeplinkConfig.sDeeplinkProcessor.process(context, str);
        }
        Target buildTarget = buildTarget(str);
        TargetType targetType = TargetType.UNSUPPORTED;
        if (buildTarget instanceof ActivityTarget) {
            targetType = TargetType.ACTIVITY;
        } else if (buildTarget instanceof CustomTarget) {
            targetType = TargetType.CUSTOM;
        }
        if (DeeplinkUtils.DEBUG) {
            StringBuilder sb = new StringBuilder();
            sb.append("build target, type=");
            sb.append(targetType);
            sb.append(", url=");
            sb.append(buildTarget.url);
            sb.append(", extras=");
            if (bundle != null) {
                str2 = bundle.toString();
            }
            sb.append(str2);
            Log.i(TAG, sb.toString());
        }
        Intent intent = new Intent();
        intent.putExtra(KEY_TARGET_TYPE, targetType);
        intent.putExtra("url", buildTarget.url);
        if (bundle != null) {
            intent.putExtras(bundle);
            intent.addFlags(bundle.getInt(DeeplinkConstants.KEY_INTENT_FLAGS));
        }
        if (targetType == TargetType.CUSTOM) {
            intent.putExtra(CustomDeeplinkHandler.KEY_HANDLER_ID, ((CustomTarget) buildTarget).handler.hashCode());
            intent.setClass(context.getApplicationContext(), CustomDeeplinkProxyActivity.class);
        } else if (targetType == TargetType.ACTIVITY) {
            ActivityTarget activityTarget = (ActivityTarget) buildTarget;
            intent.setClass(context.getApplicationContext(), activityTarget.clazz);
            if (activityTarget.processor != null) {
                activityTarget.processor.process(context, str, buildTarget);
            }
        }
        if (DeeplinkConfig.sBasicTargetProcessor != null) {
            DeeplinkConfig.sBasicTargetProcessor.process(context, str, buildTarget);
        }
        intent.putExtras(buildTarget.extras);
        intent.addFlags(buildTarget.extras.getInt(DeeplinkConstants.KEY_INTENT_FLAGS));
        return intent;
    }

    static Target buildTarget(String str) {
        String compatDeprecatedUrl = compatDeprecatedUrl(str);
        String normal = DeeplinkConfig.sUrlTranslator != null ? DeeplinkConfig.sUrlTranslator.toNormal(compatDeprecatedUrl) : compatDeprecatedUrl;
        Target target = null;
        DeeplinkConfig.RouteBuilder routeBuilder = DeeplinkConfig.sSimpleRouteBuilderTable.get(Utils.clearQuery(normal));
        if (routeBuilder != null) {
            target = routeBuilder.build(compatDeprecatedUrl);
        }
        if (target == null) {
            Iterator<DeeplinkMatcher> it = DeeplinkConfig.sPatternRouteBuilderTable.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DeeplinkMatcher next = it.next();
                if (next.match(normal)) {
                    DeeplinkConfig.RouteBuilder routeBuilder2 = DeeplinkConfig.sPatternRouteBuilderTable.get(next);
                    if (routeBuilder2 != null) {
                        target = routeBuilder2.build(compatDeprecatedUrl);
                    }
                }
            }
        }
        if (target == null) {
            target = DeeplinkConfig.sDefaultRouteBuilder.build(compatDeprecatedUrl);
        }
        String str2 = target.url != null ? target.url : compatDeprecatedUrl;
        target.url = str2;
        if (DeeplinkConfig.sUrlTranslator != null) {
            target.url = DeeplinkConfig.sUrlTranslator.toEnv(str2);
        }
        target.url = Utils.appendAllQueries(compatDeprecatedUrl, target.url);
        return target;
    }

    private static String compatDeprecatedUrl(String str) {
        String str2 = DeeplinkConfig.sDeprecatedDeeplinkTable.get(Utils.clearQuery(str));
        return str2 != null ? Utils.appendAllQueries(str, str2) : str;
    }
}
