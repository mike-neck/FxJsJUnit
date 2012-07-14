package org.mikeneck.fxjsjunit.application

import org.mikeneck.fxjsjunit.builder.FxJsJUnitBuilder
import org.mikeneck.fxjsjunit.annotation.InFxThread
import org.mikeneck.fxjsjunit.annotation.NoTestAttached

/**
 * @author mike_neck
 */
class BrowserManager implements BrowserPreparation {

    FxJsJUnitBuilder builder

    @NoTestAttached
    @InFxThread
    @Override
    void allocateEngine() {

    }

    @NoTestAttached
    @InFxThread
    @Override
    void removeEngine() {
    }
}
