package javax.annotation.meta;

import java.lang.annotation.Annotation;
import javax.annotation.Nonnull;

public interface TypeQualifierValidator<A extends Annotation> {
    @Nonnull
    When a(@Nonnull A a2, Object obj);
}
