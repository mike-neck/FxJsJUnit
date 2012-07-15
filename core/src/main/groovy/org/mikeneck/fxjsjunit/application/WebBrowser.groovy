package org.mikeneck.fxjsjunit.application

import org.mikeneck.fxjsjunit.annotation.InFxThread
import javafx.scene.web.WebEngine
import javafx.application.Platform
import java.util.concurrent.BlockingQueue
import javafx.concurrent.Worker
import java.util.concurrent.LinkedBlockingQueue
import org.mikeneck.fxjsjunit.annotation.NoTestAttached

/**
 */
@InFxThread
class WebBrowser implements Browser {

    private WebEngine engine

    final String url

    @NoTestAttached
    public WebBrowser (String url) {
        this.url = url
    }

    @NoTestAttached
    @InFxThread
    @Override
    void load() {
        engine = inFxThread {new WebEngine(url)}
    }

    @NoTestAttached
    @InFxThread
    private Worker.State getEngineState () {
        return inFxThread {engine.loadWorker.state}
    }

    @NoTestAttached
    @InFxThread
    @Override
    void synchronizeEngine () {
        while (engineState == Worker.State.RUNNING) {
            sleep 100L
        }
    }

    @NoTestAttached
    @InFxThread
    @Override
    Object callFunction(String callee) {
        return inFxThread {engine.executeScript(callee)}
    }

    protected  <R> R inFxThread (Closure<R> closure) {
        BlockingQueue<R> queue = new LinkedBlockingQueue<>()
        Platform.runLater {
            queue.put(closure ())
        }
        return queue.take()
    }
}
