package org.mikeneck.fxjsjunit;

import org.junit.rules.TestRule;
import org.mikeneck.fxjsjunit.builder.FxJsJUnitBuilder;
import org.mikeneck.fxjsjunit.extension.ParametarlizedExtension;

/**
 * this class starts JavaFX application which offers WebEngine in the lifecycle of JUnit
 * and you can get interface of WebEngine via {@link org.mikeneck.fxjsjunit.FxJsJUnit#getTester()}.
 *
 * @author mike_neck
 */
public abstract class FxJsJUnit implements TestRule {

    protected FxJsJUnitBuilder builder;

    public static FxJsJUnitBuilder option (Class<? extends Extension> extension) {
        try {
            @SuppressWarnings("unchecked")
            Extension ex = (Extension) extension.getConstructors()[0].newInstance();
            return ex.getBuilder();
        } catch (ReflectiveOperationException e) {
            throw new FxJsJUnitCannotGetStartedException(e);
        }
    }

    public static FxJsJUnitBuilder option (ParametarlizedExtension extension) {
        return extension.toBuilder();
    }

    /**
     * get interface to {@code WebEngine}.
     * @return - the instance of {@link JsJUnit}
     */
    abstract public JsJUnit getTester ();
}
