package org.mikeneck.fxjsjunit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author mike_neck
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.CONSTRUCTOR, ElementType.METHOD})
public @interface NoTestAttached {
}
