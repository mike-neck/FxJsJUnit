package org.mikeneck.fxjsjunit.application;

import org.mikeneck.fxjsjunit.annotation.InFxThread;

/**
 * @author mike_neck
 */
public interface BrowserPreparation {

    @InFxThread
    public void allocateEngine ();

    @InFxThread
    public void removeEngine ();
}
