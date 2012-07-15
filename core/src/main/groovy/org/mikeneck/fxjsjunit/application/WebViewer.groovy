package org.mikeneck.fxjsjunit.application

import javafx.application.Application
import javafx.stage.Stage
import java.util.concurrent.ConcurrentMap
import org.mikeneck.fxjsjunit.annotation.InFxThread
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicReference
import org.mikeneck.fxjsjunit.annotation.NoTestAttached

/**
 * @author mike_neck
 */
class WebViewer extends Application {

    private static AtomicReference<WebViewer> viewer = new AtomicReference<>()

    private static ConcurrentMap<String, Browser> browsers = new ConcurrentHashMap<>()

    @NoTestAttached
    @InFxThread
    @Override
    void start(Stage stage) {
        viewer.compareAndSet(null, this)
    }

    @NoTestAttached
    public static boolean isLaunched () {
        return getReference() != null
    }

    @NoTestAttached
    public static WebViewer getReference () {
        return viewer.get()
    }

    public static void allocateBrowser (String id, Browser browser) {
        browsers.putIfAbsent(id, browser)
    }

    public static void removeBrowser (String id) {
        browsers.remove(id)
    }

}
