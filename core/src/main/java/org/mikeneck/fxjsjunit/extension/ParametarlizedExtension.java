package org.mikeneck.fxjsjunit.extension;

import org.mikeneck.fxjsjunit.builder.FxJsJUnitBuilder;

/**
 * @author mike_neck
 */
public abstract class ParametarlizedExtension {

    static {
        try {
            Class.forName("org.mikeneck.fxjsjunit.extension.CoreExtension");
        } catch (ClassNotFoundException e) {
        }
    }

    abstract public FxJsJUnitBuilder toBuilder ();
}
