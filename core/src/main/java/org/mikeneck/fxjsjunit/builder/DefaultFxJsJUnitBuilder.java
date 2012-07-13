package org.mikeneck.fxjsjunit.builder;

import org.mikeneck.fxjsjunit.FxJsJUnit;

/**
 * @author mike_neck
 */
public class DefaultFxJsJUnitBuilder implements FxJsJUnitBuilder {

    private String url;

    private String identifier;

    @Override
    public FxJsJUnitBuilder address(String url) {
        this.url = url;
        return this;
    }

    @Override
    public FxJsJUnitBuilder identifiedBy(String identifier) {
        this.identifier = identifier;
        return this;
    }

    @Override
    public FxJsJUnitBuilder identifiedBy(Class<?> identifier) {
        this.identifier = identifier.getName();
        return this;
    }

    @Override
    public FxJsJUnit get() {
        return null;
    }
}
