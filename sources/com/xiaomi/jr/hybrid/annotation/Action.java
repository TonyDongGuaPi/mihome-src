package com.xiaomi.jr.hybrid.annotation;

import com.xiaomi.jr.hybrid.FeatureUtil;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Action {
    FeatureUtil.Mode mode() default FeatureUtil.Mode.SYNC;

    Class<?> paramClazz() default Object.class;
}
