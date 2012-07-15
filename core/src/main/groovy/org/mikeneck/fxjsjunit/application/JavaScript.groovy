package org.mikeneck.fxjsjunit.application

import org.mikeneck.fxjsjunit.JsJUnit

/**
 * @author mike_neck
 */
class JavaScript implements JsJUnit {

    private Browser browser

    @Override
    int callAsInt(String callee) {
        return 0
    }

    @Override
    long callAsLong(String callee) {
        return 0
    }

    @Override
    double callAsDouble(String callee) {
        return 0
    }

    @Override
    String callAsString(String callee) {
        return null
    }

    @Override
    Date callAsDate(String callee) {
        return null
    }

    @Override
    Object call(String callee) {
        return null
    }

    @Override
    def <M> M callAs(String callee, Class<M> expectedModel) {
        return null
    }
}
