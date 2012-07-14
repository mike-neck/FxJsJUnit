package org.mikeneck.fxjsjunit.extension;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.mikeneck.fxjsjunit.FxJsJUnit;
import org.mikeneck.fxjsjunit.JsJUnit;
import org.mikeneck.fxjsjunit.application.Launcher;
import org.mikeneck.fxjsjunit.builder.FxJsJUnitBuilder;
import org.mikeneck.fxjsjunit.builder.LauncherInformation;


/**
 * @author mike
 */
public class DefaultFxJsJUnit extends FxJsJUnit {

    private Launcher launcher;

    public DefaultFxJsJUnit(FxJsJUnitBuilder builder) {
        this.builder = builder;
    }

    @Override
    public JsJUnit getTester() {
        return null;
    }

    @Override
    public Statement apply(Statement base, Description description) {
        launcher = LauncherInformation
                    .load()
                    .statement(base)
                    .description(description)
                    .builder(builder);
        return launcher;
    }
}
