package org.mikeneck.fxjsjunit;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.model.Statement;
import org.mikeneck.fxjsjunit.builder.FxJsJUnitBuilder;
import org.mikeneck.fxjsjunit.extension.CoreExtension;
import org.mikeneck.fxjsjunit.extension.ParametarlizedExtension;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author mike_neck
 */
@RunWith(Enclosed.class)
public class FxJsJUnitTest {

    public static class OptionTest {
        @Test
        public void classOption () {
            FxJsJUnitBuilder builder = FxJsJUnit.option(CoreExtension.class);
            assertThat(builder, is(instanceOf(FxJsJUnitBuilder.class)));
        }

        @Test
        public void parameterOption () {
            FxJsJUnit fxJsJUnit = FxJsJUnit
                    .option(SomeExtention.port(3000))
                    .identifiedBy(this.getClass())
                    .get();
            assertThat (fxJsJUnit, is(instanceOf(FxJsJUnit.class)));
        }

        private static class SomeExtention {
            public static ParametarlizedExtension port (int portNumber) {
                return new SomeParameter(portNumber);
            }
        }

        private static class SomeParameter extends ParametarlizedExtension {

            private final SomeBuilder builder;

            public SomeParameter (int portNumber) {
                builder = new SomeBuilder(portNumber);
            }

            @Override
            public FxJsJUnitBuilder toBuilder() {
                return builder;
            }
        }

        private static class SomeBuilder implements FxJsJUnitBuilder {
            private int port;
            private String url;
            private String identifier;

            public SomeBuilder(int portNumber) {
                this.port = portNumber;
            }

            @Override
            public FxJsJUnitBuilder address(String url) {
                this.url = url;
                return this;
            }

            @Override
            public FxJsJUnitBuilder identifiedBy(String identifier) {
                this.identifier = identifier;
                return this;
            }

            @Override
            public FxJsJUnitBuilder identifiedBy(Class<?> identifier) {
                this.identifiedBy(identifier.getName());
                return this;
            }

            @Override
            public FxJsJUnit get() {
                return new SomeJsJUnit();
            }

            @Override
            public String identifier() {
                return this.identifier;
            }

            @Override
            public String url() {
                return this.url;
            }
        }

        private static class SomeJsJUnit extends FxJsJUnit {
            @Override
            public JsJUnit getTester() {
                return null;
            }

            @Override
            public Statement apply(Statement base, Description description) {
                return null;
            }
        }
    }
}
