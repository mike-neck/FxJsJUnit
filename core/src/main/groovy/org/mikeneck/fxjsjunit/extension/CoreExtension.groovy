package org.mikeneck.fxjsjunit.extension

import org.mikeneck.fxjsjunit.Extension
import org.mikeneck.fxjsjunit.builder.FxJsJUnitBuilder
import javafx.application.Platform
import java.util.concurrent.ExecutorService
import java.util.concurrent.Callable
import org.mikeneck.fxjsjunit.builder.DefaultFxJsJUnitBuilder

/**
 * @author mike_neck
 */
class CoreExtension implements Extension {

    static {
        Platform.metaClass.define {
            runLater = {Closure closure ->
                delegate.runLater (new Runnable(){
                    @Override
                    void run() {
                        closure ()
                    }
                })
            }
        }
        ExecutorService.metaClass.define {
            doSubmit = {Closure closure ->
                return delegate.submit {new Callable(){
                    @Override
                    Object call() {
                        return closure ()
                    }
                }}
            }
        }
    }

    @Override
    FxJsJUnitBuilder getBuilder() {
        return new DefaultFxJsJUnitBuilder();
    }
}
