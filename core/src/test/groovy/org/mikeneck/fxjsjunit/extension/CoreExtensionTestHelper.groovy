package org.mikeneck.fxjsjunit.extension

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

/**
 * @author mike_neck
 */
class CoreExtensionTestHelper implements CoreExtensionTest.TestHelper{

    private static ExecutorService service = Executors.newFixedThreadPool(2)

    @Override
    CoreExtensionTest.ExtensionTestHelper<Long> longTestHelper () {
        return new LongValueTestHelper()
    }

    @Override
    CoreExtensionTest.ExtensionTestHelper<CoreExtensionTest.TestBean> testBeanHelper () {
        return new TestBeanTestHelper()
    }

    public class LongValueTestHelper implements CoreExtensionTest.ExtensionTestHelper<Long> {
        @Override
        Closure<Long> getClosure(Long value) {
            return {return value}
        }

        @Override
        Future<Long> helpCall(Closure<Long> closure) {
            return service.doSubmit(closure)
        }
    }

    public class TestBeanTestHelper implements CoreExtensionTest.ExtensionTestHelper<CoreExtensionTest.TestBean> {

        @Override
        Closure<CoreExtensionTest.TestBean> getClosure(CoreExtensionTest.TestBean value) {
            return {return value}
        }

        @Override
        Future<CoreExtensionTest.TestBean> helpCall(Closure<CoreExtensionTest.TestBean> closure) {
            return service.doSubmit (closure)
        }
    }
}
