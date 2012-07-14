package org.mikeneck.fxjsjunit.application

import java.util.concurrent.atomic.AtomicReference
import javafx.application.Application
import org.mikeneck.fxjsjunit.builder.FxJsJUnitBuilder
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import org.mikeneck.fxjsjunit.annotation.NoTestAttached

/**
 * Launches JavaFX-Application in a lifecycle of JUnit Test
 * @author mike_neck
 */
class LauncherImpl extends Launcher {

    static AtomicReference<ExecutorService> LOCK = new AtomicReference<>(null)

    private BrowserPreparation preparation

    @NoTestAttached
    public LauncherImpl () {
        super ()
    }

    @NoTestAttached
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

    @NoTestAttached
    @Override
    protected void finish () {
        preparation.removeEngine()
    }

    @NoTestAttached
    public void setBuilder (FxJsJUnitBuilder builder) {
        this.preparation = new BrowserManager(builder: builder)
    }
}
