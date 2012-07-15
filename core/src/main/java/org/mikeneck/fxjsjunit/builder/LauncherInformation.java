package org.mikeneck.fxjsjunit.builder;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.mikeneck.fxjsjunit.FxJsJUnitCannotGetStartedException;
import org.mikeneck.fxjsjunit.annotation.NoTestAttached;
import org.mikeneck.fxjsjunit.application.Launcher;

import java.lang.reflect.Constructor;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author mike
 */
public class LauncherInformation {

    private static final String LAUNCHER = "org.mikeneck.fxjsjunit.application.LauncherImpl";

    private static AtomicReference<Class<Launcher>> KLASS = new AtomicReference<>();

    private LauncherInformation () {}

    @NoTestAttached
    public static synchronized StatementBuilder load () {
        try {
            Class<Launcher> klass = KLASS.get();
            if (klass == null) {
                @SuppressWarnings ("unchecked")
                Class<Launcher> clazz = (Class<Launcher>) Class.forName(LAUNCHER);
                KLASS.compareAndSet(null, clazz);
            }
            return new StatementBuilder();
        } catch (ReflectiveOperationException e) {
            throw new FxJsJUnitCannotGetStartedException(e);
        }
    }

    @NoTestAttached
    public static class StatementBuilder {
        @NoTestAttached
        public DescriptionBuilder statement(Statement s) {
            return new DescriptionBuilder(s);
        }
    }

    @NoTestAttached
    public static class DescriptionBuilder {

        private Statement statement;

        @NoTestAttached
        private DescriptionBuilder(Statement statement) {
            this.statement = statement;
        }

        @NoTestAttached
        public Builder description(Description desc) {
            return new Builder(statement, desc);
        }
    }

    @NoTestAttached
    public static class Builder {

        private Statement statement;

        private Description description;

        @NoTestAttached
        private Builder(Statement statement, Description desc) {
            this.statement = statement;
            this.description = desc;
        }

        @NoTestAttached
        public Launcher builder (FxJsJUnitBuilder b) throws FxJsJUnitCannotGetStartedException {
            Class<Launcher> klass = LauncherInformation.KLASS.get();
            try {
                Constructor<Launcher> constructor = klass.getConstructor();

                Launcher launcher = constructor.newInstance();
                launcher.setBase(statement);
                launcher.setDescription(description);
                launcher.setBuilder(b);

                return launcher;
            } catch (ReflectiveOperationException e) {
                throw new FxJsJUnitCannotGetStartedException(e);
            }
        }
    }
}
