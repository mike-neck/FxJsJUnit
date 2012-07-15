package org.mikeneck.fxjsjunit.builder;

import org.mikeneck.fxjsjunit.FxJsJUnit;
import org.mikeneck.fxjsjunit.FxJsJUnitCannotGetStartedException;
import org.mikeneck.fxjsjunit.annotation.NoTestAttached;
import org.mikeneck.fxjsjunit.extension.DefaultFxJsJUnit;

/**
 * @author mike_neck
 */
public class DefaultFxJsJUnitBuilder implements FxJsJUnitBuilder {

    private String url;

    private String identifier;

    @NoTestAttached
    @Override
    public FxJsJUnitBuilder address(String url) {
        this.url = url;
        return this;
    }

    @NoTestAttached
    @Override
    public FxJsJUnitBuilder identifiedBy(String identifier) {
        this.identifier = identifier;
        return this;
    }

    @NoTestAttached
    @Override
    public FxJsJUnitBuilder identifiedBy(Class<?> identifier) {
        this.identifier = identifier.getName();
        return this;
    }

    @NoTestAttached
    @Override
    public FxJsJUnit get() throws FxJsJUnitCannotGetStartedException {
        if (identifier == null || identifier.isEmpty() || url == null || url.isEmpty()) {
            throw new FxJsJUnitCannotGetStartedException("Please give enough information for FxJsJUnitBuilder : " + toString());
        }
        return new DefaultFxJsJUnit(this);
    }

    @NoTestAttached
    @Override
    public String identifier() {
        return this.identifier;
    }

    @NoTestAttached
    @Override
    public String url() {
        return this.url;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        return builder
                .append("[")
                .append("identifier => \"")
                .append(identifier)
                .append("\", url => \"")
                .append(url)
                .append("\"]")
                .toString();

    }
}
