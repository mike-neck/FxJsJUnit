package org.mikeneck.fxjsjunit;

import org.junit.rules.TestRule;

/**
 * this class starts JavaFX application which offers WebEngine in the lifecycle of JUnit
 * and you can get interface of WebEngine via {@link org.mikeneck.fxjsjunit.FxJsJUnit#getTester()}.
 *
 * @author mike_neck
 */
public interface FxJsJUnit extends TestRule {

    /**
     * get interface to {@code WebEngine}.
     * @return - the instance of {@link JsJUnit}
     */
    public JsJUnit getTester ();
}
