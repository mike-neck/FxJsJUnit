package org.mikeneck.fxjsjunit.application;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.mikeneck.fxjsjunit.builder.FxJsJUnitBuilder;

/**
 * @author mike_neck
 */
abstract public class Launcher extends Statement {

    public Launcher () {}

    Statement base;

    Description description;

    abstract protected void launch ();

    abstract protected void finish ();

    protected void beforeLaunch () {}

    protected void afterLaunch () {}

    protected void beforeFinish () {}

    protected void afterFinish () {}

    @Override
    public void evaluate() throws Throwable {

        beforeLaunch();
        launch();
        afterLaunch();

        try {
            base.evaluate();
        } catch (Throwable throwable) {
            throw throwable;
        } finally {

            beforeFinish();
            finish ();
            afterFinish();

        }
    }

    public void setBase(Statement base) {
        this.base = base;
    }

    public void setDescription (Description description) {
        this.description = description;
    }

    abstract public void setBuilder (FxJsJUnitBuilder builder);
}
