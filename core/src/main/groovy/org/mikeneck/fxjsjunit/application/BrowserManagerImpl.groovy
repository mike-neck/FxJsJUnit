package org.mikeneck.fxjsjunit.application

import org.mikeneck.fxjsjunit.builder.FxJsJUnitBuilder

import org.mikeneck.fxjsjunit.JsJUnit

/**
 * @author mike_neck
 */
class BrowserManagerImpl implements BrowserManager {

    FxJsJUnitBuilder builder

    @Override
    JsJUnit allocateEngine() {
        def browser = new WebBrowser(builder.url())
        WebViewer.allocateBrowser(builder.identifier(), browser)
        return new JavaScript(browser: browser)
    }

    @Override
    void removeEngine() {
        WebViewer.removeBrowser(builder.identifier())
    }
}
