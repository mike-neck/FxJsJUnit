package org.mikeneck.fxjsjunit;

/**
 * @author mike_neck
 */
public class FxJsJUnitCannotGetStartedException extends RuntimeException {

    public FxJsJUnitCannotGetStartedException(Exception e) {
        super(e);
    }

    public FxJsJUnitCannotGetStartedException(String message) {
        super(message);
    }
}
