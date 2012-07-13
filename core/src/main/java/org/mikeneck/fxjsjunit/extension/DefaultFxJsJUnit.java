package org.mikeneck.fxjsjunit.extension;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.mikeneck.fxjsjunit.FxJsJUnit;
import org.mikeneck.fxjsjunit.JsJUnit;

/**
 * @author mike
 */
public class DefaultFxJsJUnit extends FxJsJUnit {
    @Override
    public JsJUnit getTester() {
        return null;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        return null;
    }
}
