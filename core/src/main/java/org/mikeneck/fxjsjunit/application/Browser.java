package org.mikeneck.fxjsjunit.application;

import netscape.javascript.JSException;
import org.mikeneck.fxjsjunit.annotation.InFxThread;

/**
 * an interface class for operating WebEngine.
 * @author mike_neck
 */
@InFxThread
public interface Browser {

    public void load ();

    public Object callFunction(String callee) throws JSException;
}
