package org.mikeneck.fxjsjunit.application;

import netscape.javascript.JSException;

/**
 * an interface class
 * @author mike_neck
 */
public interface Browser {

    public Object callFunction(String callee) throws JSException;

    public void finish();
}
