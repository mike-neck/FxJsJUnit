package org.mikeneck.fxjsjunit.application

import org.junit.runners.model.Statement
import org.junit.runner.Description
import java.util.concurrent.atomic.AtomicReference
import javafx.application.Application
import org.mikeneck.fxjsjunit.builder.FxJsJUnitBuilder
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * @author mike_neck
 */
class Launcher extends Statement {

    static AtomicReference<ExecutorService> LOCK = new AtomicReference<>(null)

    private Statement base

    private Description description

    FxJsJUnitBuilder builder

    public Launcher (Statement base, Description description, FxJsJUnitBuilder builder) {
        this.base = base
        this.description = description
        this.builder = builder
    }

    private void launch () {
        if (LOCK.compareAndSet(null, Executors.newSingleThreadExecutor())) {
            LOCK.get().execute {
                Application.launch(WebViewer)
            }
        }
        while (!WebViewer.launched) {
            sleep 100L
        }
    }

    @Override
    void evaluate() {

        beforeLaunch()
        launch()
        afterLaunch()

        base.evaluate()

        beforeFinish()
        finish ()
        afterFinish()
    }

    protected void beforeLaunch () {}

    protected void afterLaunch () {}

    protected void beforeFinish () {}

    protected void afterFinish () {}
}
