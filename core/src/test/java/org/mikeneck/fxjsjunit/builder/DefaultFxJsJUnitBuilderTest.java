package org.mikeneck.fxjsjunit.builder;


import org.junit.Before;
import org.junit.Test;
import org.mikeneck.fxjsjunit.FxJsJUnitCannotGetStartedException;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author mike
 */
public class DefaultFxJsJUnitBuilderTest {

    private FxJsJUnitBuilder fxJsJUnitBuilder;

    @Before
    public void setup () {
        fxJsJUnitBuilder = new DefaultFxJsJUnitBuilder();
    }

    @Test
    public void identifiedByString () {
        fxJsJUnitBuilder.identifiedBy("Test Identifier");

        assertThat (fxJsJUnitBuilder.identifier(), is("Test Identifier"));
    }

    @Test
    public void identifiedByClass () {
        fxJsJUnitBuilder.identifiedBy(MockForIdentifier.class);

        assertThat(fxJsJUnitBuilder.identifier(),
                is("org.mikeneck.fxjsjunit.builder.DefaultFxJsJUnitBuilderTest$MockForIdentifier"));
    }

    @Test
    public void testUrl () {
        fxJsJUnitBuilder.address("https://github.com/mike-neck/FxJsJUnit");

        assertThat(fxJsJUnitBuilder.url(),
                is("https://github.com/mike-neck/FxJsJUnit"));
    }

    @Test (expected = FxJsJUnitCannotGetStartedException.class)
    public void noInformationBuilderThrowsExceptionOnMethodGet () {
        fxJsJUnitBuilder.get();
    }

    @Test (expected = FxJsJUnitCannotGetStartedException.class)
    public void onlyUrlIsGivenExceptionWillBeThrownOnMethodGet () {
        fxJsJUnitBuilder.address("https://github.com/mike-neck/FxJsJUnit");

        fxJsJUnitBuilder.get();
    }

    @Test (expected = FxJsJUnitCannotGetStartedException.class)
    public void onlyIdentifierIsGivenExceptionWillBeThrownOnMethodGet () {
        fxJsJUnitBuilder.identifiedBy(MockForIdentifier.class);

        fxJsJUnitBuilder.get();
    }

    @Test (expected = FxJsJUnitCannotGetStartedException.class)
    public void blankUrlIsGivenExceptionWillBeThrownOnMethodGet () {
        fxJsJUnitBuilder.address("");
        fxJsJUnitBuilder.identifiedBy("identifier");

        fxJsJUnitBuilder.get();
    }

    @Test (expected = FxJsJUnitCannotGetStartedException.class)
    public void blankIdentifierIsGivenExceptionWillBeThrownOnMethodGet () {
        fxJsJUnitBuilder.address("https://github.com/mike-neck/FxJsJUnit");
        fxJsJUnitBuilder.identifiedBy("");

        fxJsJUnitBuilder.get();
    }

    @Test
    public void normalCase () {
        fxJsJUnitBuilder.address("https://github.com/mike-neck/FxJsJUnit");
        fxJsJUnitBuilder.identifiedBy(MockForIdentifier.class);

        assertThat(fxJsJUnitBuilder.get(), is(not(nullValue())));
    }

    private class MockForIdentifier {}
}
