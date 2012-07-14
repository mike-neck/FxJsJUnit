package org.mikeneck.fxjsjunit.application

import org.junit.runner.Description
import java.util.concurrent.atomic.AtomicReference
import javafx.application.Application
import org.mikeneck.fxjsjunit.builder.FxJsJUnitBuilder
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Launches JavaFX-Application in a lifecycle of JUnit Test
 * @author mike_neck
 */
class LauncherImpl extends Launcher {

    static AtomicReference<ExecutorService> LOCK = new AtomicReference<>(null)

    Description description

    private BrowserPreparation preparation

    @Override
    protected void launch () {
        if (LOCK.compareAndSet(null, Executors.newSingleThreadExecutor())) {
            LOCK.get().execute {
                Application.launch(WebViewer)
            }
        }
        while (!WebViewer.launched) {
            sleep 100L
        }
        preparation.allocateEngine()
    }

    @Override
    protected void finish () {
        preparation.removeEngine()
    }

    public void setBuilder (FxJsJUnitBuilder builder) {
        this.preparation = new BrowserManager(builder: builder)
    }
}
