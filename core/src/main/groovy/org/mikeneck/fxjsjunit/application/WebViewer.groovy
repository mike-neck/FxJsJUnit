package org.mikeneck.fxjsjunit.application

import javafx.application.Application
import javafx.stage.Stage
import java.util.concurrent.ConcurrentMap
import org.mikeneck.fxjsjunit.annotation.InFxThread
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicReference

/**
 * @author mike_neck
 */
class WebViewer extends Application {

    private static AtomicReference<WebViewer> viewer = new AtomicReference<>()

    private static ConcurrentMap<String, Browser> browsers = new ConcurrentHashMap<>()

    @InFxThread
    @Override
    void start(Stage stage) {
        viewer.compareAndSet(null, this)
    }

    public static boolean isLaunched () {
        return getReference() != null
    }

    public static WebViewer getReference () {
        return viewer.get()
    }
}
