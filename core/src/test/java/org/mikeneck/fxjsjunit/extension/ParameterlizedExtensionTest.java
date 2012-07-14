package org.mikeneck.fxjsjunit.extension;

import groovy.lang.Closure;
import org.junit.Before;
import org.junit.Test;
import org.mikeneck.fxjsjunit.FxJsJUnit;
import org.mikeneck.fxjsjunit.builder.FxJsJUnitBuilder;

import java.lang.reflect.Constructor;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author mike_neck
 */
public class ParameterlizedExtensionTest {

    private static final String HELPER_CLASS = "org.mikeneck.fxjsjunit.extension.ParameterlizedExtensionHelper";

    private TestHelper helper;

    @Before
    public void setup () throws ReflectiveOperationException {
        FxJsJUnit.option(new Extend());

        @SuppressWarnings("unchecked")
        Class<TestHelper> klass = (Class<TestHelper>) Class.forName(HELPER_CLASS);
        Constructor<TestHelper> constructor = klass.getConstructor();
        helper = constructor.newInstance();
    }

    @Test
    public void longValue () throws ExecutionException, InterruptedException {
        ExtensionHelper<Long> tester = helper.longHelper();

        Closure<Long> closure = tester.getClosure(-100L);
        Future<Long> future = tester.helpCall(closure);

        assertThat(future.get(), is(-100L));
    }

    @Test
    public void beanValue () throws ExecutionException, InterruptedException {
        ExtensionHelper<Bean> tester = helper.beanHelper();

        Closure<Bean> closure = tester.getClosure(new Bean("test name"));
        Future<Bean> future = tester.helpCall(closure);

        assertThat(future.get(), is(new Bean("test name")));
    }

    private class Extend extends ParameterlizedExtension {
        @Override
        public FxJsJUnitBuilder toBuilder() {
            return null;
        }
    }

    public interface TestHelper {
        public ExtensionHelper<Long> longHelper ();
        public ExtensionHelper<Bean> beanHelper ();
    }

    public interface ExtensionHelper<V> {
        public Closure<V> getClosure (V value);
        public Future<V> helpCall (Closure<V> closure);
    }

    public class Bean {
        private String name;

        public Bean (String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Bean bean = (Bean) o;

            if (name != null ? !name.equals(bean.name) : bean.name != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }
    }
}
