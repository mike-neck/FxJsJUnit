package org.mikeneck.fxjsjunit.application

import org.mikeneck.fxjsjunit.builder.FxJsJUnitBuilder
import org.mikeneck.fxjsjunit.annotation.InFxThread

/**
 * @author mike_neck
 */
class BrowserManager implements BrowserPreparation {

    FxJsJUnitBuilder builder

    @InFxThread
    @Override
    void allocateEngine() {

    }

    @InFxThread
    @Override
    void removeEngine() {
    }
}
