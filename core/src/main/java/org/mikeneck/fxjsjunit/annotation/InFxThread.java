package org.mikeneck.fxjsjunit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation should be given to an class or methods which will work on JavaFX thread.
 * <br/>
 *
 * This annotation won't certify whether classes or methods with this annotation works on
 * JavaFX thread really, <i>i.e. this annotation is only a mark to make a programmer
 * take thread model in conscious.</i>
 *
 * @author mike_neck
 */
@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface InFxThread {
}
