package com.imi.fastjson.annotation;

import com.imi.fastjson.parser.Feature;
import com.imi.fastjson.serializer.SerializerFeature;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface JSONField {
    boolean deserialize() default true;

    String format() default "";

    String name() default "";

    Feature[] parseFeatures() default {};

    boolean serialize() default true;

    SerializerFeature[] serialzeFeatures() default {};
}
