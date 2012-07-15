package org.mikeneck.fxjsjunit.application

import java.util.concurrent.atomic.AtomicReference
import javafx.application.Application
import org.mikeneck.fxjsjunit.builder.FxJsJUnitBuilder
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import org.mikeneck.fxjsjunit.annotation.NoTestAttached
import org.mikeneck.fxjsjunit.JsJUnit

/**
 * Launches JavaFX-Application in a lifecycle of JUnit Test
 * @author mike_neck
 */
class LauncherImpl extends Launcher {

    static AtomicReference<ExecutorService> LOCK = new AtomicReference<>(null)

    private BrowserManager preparation

    private JsJUnit jsJUnit

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
        jsJUnit = preparation.allocateEngine()
    }

    @NoTestAttached
    @Override
    protected void finish () {
        preparation.removeEngine()
    }

    @NoTestAttached
    public void setBuilder (FxJsJUnitBuilder builder) {
        this.preparation = new BrowserManagerImpl(builder: builder)
    }

    @Override
    public JsJUnit getJsJUnit () {
        return jsJUnit
    }
}
