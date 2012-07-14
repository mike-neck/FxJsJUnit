package org.mikeneck.fxjsjunit.extension

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.Future

/**
 * @author mike_neck
 */
class ParameterlizedExtensionHelper implements ParameterlizedExtensionTest.TestHelper {

    private static final ExecutorService service = Executors.newFixedThreadPool(2)

    @Override
    ParameterlizedExtensionTest.ExtensionHelper<Long> longHelper() {
        return new LongHelper()
    }

    @Override
    ParameterlizedExtensionTest.ExtensionHelper<ParameterlizedExtensionTest.Bean> beanHelper() {
        return new BeanHelper()
    }

    class LongHelper implements ParameterlizedExtensionTest.ExtensionHelper<Long> {
        @Override
        Closure<Long> getClosure(Long value) {
            return {return value}
        }

        @Override
        Future<Long> helpCall(Closure<Long> closure) {
            return service.doSubmit (closure)
        }
    }

    class BeanHelper implements ParameterlizedExtensionTest.ExtensionHelper<ParameterlizedExtensionTest.Bean> {
        @Override
        Closure<ParameterlizedExtensionTest.Bean> getClosure(ParameterlizedExtensionTest.Bean value) {
            return {return value}
        }

        @Override
        Future<ParameterlizedExtensionTest.Bean> helpCall(Closure<ParameterlizedExtensionTest.Bean> closure) {
            return service.doSubmit (closure)
        }
    }
}
