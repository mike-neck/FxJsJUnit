package org.mikeneck.fxjsjunit.application;

import org.mikeneck.fxjsjunit.JsJUnit;
import org.mikeneck.fxjsjunit.annotation.InFxThread;

/**
 * @author mike_neck
 */
public interface BrowserManager {

    @InFxThread
    public JsJUnit allocateEngine ();

    @InFxThread
    public void removeEngine ();
}
