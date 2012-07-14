package org.mikeneck.fxjsjunit.builder;

import org.mikeneck.fxjsjunit.FxJsJUnit;
import org.mikeneck.fxjsjunit.extension.DefaultFxJsJUnit;

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
        return new DefaultFxJsJUnit(this);
    }

    @Override
    public String identifier() {
        return this.identifier;
    }

    @Override
    public String url() {
        return this.url;
    }
}
