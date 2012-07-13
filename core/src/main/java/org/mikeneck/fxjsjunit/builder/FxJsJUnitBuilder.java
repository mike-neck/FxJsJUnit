package org.mikeneck.fxjsjunit.builder;

import org.mikeneck.fxjsjunit.FxJsJUnit;

/**
 * @author mike_neck
 */
public interface FxJsJUnitBuilder {

    public FxJsJUnitBuilder address (String url);

    public FxJsJUnitBuilder identifiedBy (String identifier);

    public FxJsJUnitBuilder identifiedBy (Class<?> identifier);

    public FxJsJUnit get();
}
