package org.mikeneck.fxjsjunit;

import netscape.javascript.JSException;

import java.util.Date;

/**
 * an interface for accessing WebEngine.
 * Test methods can call JavaScript functions via this interface.
 *
 * @author mike_neck
 */
public interface JsJUnit {

    /**
     * call JavaScript function identified by given {@code java.lang.String}.
     * @param callee - JavaScript function.
     * @return {@code int} value.
     * @throws IllegalStateException - if FxJsJUnit is not prepared.
     * @throws JSException - if the function doesn't exist or some exception is thrown in the function.
     */
    public int callAsInt (String callee) throws IllegalStateException, JSException;

    /**
     * call JavaScript function identified by given {@code java.lang.String}.
     * @param callee - JavaScript function.
     * @return {@code long} value.
     * @throws IllegalStateException - if FxJsJUnit is not prepared.
     * @throws JSException - if the function doesn't exist or some exception is thrown in the function.
     */
    public long callAsLong (String callee) throws IllegalStateException, JSException;

    /**
     * call JavaScript function identified by given {@code java.lang.String}.
     * @param callee - JavaScript function.
     * @return {@code double} value.
     * @throws IllegalStateException - if FxJsJUnit is not prepared.
     * @throws JSException - if the function doesn't exist or some exception is thrown in the function.
     */
    public double callAsDouble (String callee) throws IllegalStateException, JSException;

    /**
     * call JavaScript function identified by given {@code java.lang.String}.
     * @param callee - JavaScript function.
     * @return {@code java.lang.String} value.
     * @throws IllegalStateException - if FxJsJUnit is not prepared.
     * @throws JSException - if the function doesn't exist or some exception is thrown in the function.
     */
    public String callAsString (String callee) throws IllegalStateException, JSException;

    /**
     * call JavaScript function identified by given {@code java.lang.String}.
     * @param callee - JavaScript function.
     * @return {@code java.util.Date} value.
     * @throws IllegalStateException - if FxJsJUnit is not prepared.
     * @throws JSException - if the function doesn't exist or some exception is thrown in the function.
     */
    public Date callAsDate (String callee) throws IllegalStateException, JSException;

    /**
     * call JavaScript function identified by given {@code java.lang.String}.
     * @param callee - JavaScript function.
     * @return {@code java.lang.Object} value.
     * @throws IllegalStateException - if FxJsJUnit is not prepared.
     * @throws JSException - if the function doesn't exist or some exception is thrown in the function.
     */
    public Object call (String callee) throws IllegalStateException, JSException;

    /**
     * call JavaScript function identified by given {@code java.lang.String}.
     * @param callee - JavaScript function.
     * @param expectedModel - an expected Java Class
     * @param <M> - expected model class
     * @return an object whose type is the given class
     * @throws IllegalStateException - if FxJsJUnit is not prepared.
     * @throws JSException - if the function doesn't exist or some exception is thrown in the function.
     * @throws JSMappingException - if a JavaScript Object does not match the given class.
     */
    public <M> M callAs (String callee, Class<M> expectedModel)
            throws IllegalStateException, JSException, JSMappingException;
}
