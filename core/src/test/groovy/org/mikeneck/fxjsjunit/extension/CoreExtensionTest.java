package org.mikeneck.fxjsjunit.extension;

import groovy.lang.Closure;
import org.junit.Before;
import org.junit.Test;
import org.mikeneck.fxjsjunit.FxJsJUnit;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author mike_neck
 */
public class CoreExtensionTest {

    private static final String HELPER_CLASS = "org.mikeneck.fxjsjunit.extension.CoreExtensionTestHelper";
    private TestHelper helper;

    @Before
    public void setHelper () throws Exception {
        FxJsJUnit.option(CoreExtension.class);
        Class<?> helperClass = Class.forName(HELPER_CLASS);
        helper = (TestHelper) helperClass.getConstructors()[0].newInstance();
    }

    @Test
    public void longValueTest () throws ExecutionException, InterruptedException {
        ExtensionTestHelper<Long> testHelper = helper.longTestHelper();
        Closure<Long> closure = testHelper.getClosure(20L);
        Future<Long> future = testHelper.helpCall(closure);

        assertThat(future.get(), is(20L));
    }

    @Test
    public void testBeanTest () throws ExecutionException, InterruptedException {
        ExtensionTestHelper<TestBean> testHelper = helper.testBeanHelper();
        Closure<TestBean> closure = testHelper.getClosure(new TestBean("test"));
        Future<TestBean> future = testHelper.helpCall(closure);

        assertThat(future.get(), is(new TestBean("test")));
    }

    public interface TestHelper {
        public ExtensionTestHelper<Long> longTestHelper ();
        public ExtensionTestHelper<TestBean> testBeanHelper ();
    }

    public interface ExtensionTestHelper<V> {
        public Closure<V> getClosure (V value);
        public Future<V> helpCall (Closure<V> closure);
    }

    public class TestBean {
        private String beanName;

        TestBean (final String beanName) {
            this.beanName = beanName;
        }

        public String getBeanName() {
            return beanName;
        }

        public void setBeanName(String beanName) {
            this.beanName = beanName;
        }

        @Override
        public int hashCode() {
            return beanName.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof TestBean) {
                TestBean testBean = TestBean.class.cast(obj);
                if (beanName == null && testBean.beanName == null) {
                    return true;
                } else if (beanName == null) {
                    return false;
                } else {
                    return beanName.equals(testBean.beanName);
                }
            } else {
                return false;
            }
        }
    }
}
