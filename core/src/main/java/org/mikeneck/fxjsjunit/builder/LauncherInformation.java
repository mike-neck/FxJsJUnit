package org.mikeneck.fxjsjunit.builder;

import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.mikeneck.fxjsjunit.FxJsJUnitCannotGetStartedException;
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

    public static class StatementBuilder {
        public DescriptionBuilder statement(Statement s) {
            return new DescriptionBuilder(s);
        }
    }

    public static class DescriptionBuilder {

        private Statement statement;

        public DescriptionBuilder(Statement statement) {
            this.statement = statement;
        }

        public Builder description(Description desc) {
            return new Builder(statement, desc);
        }
    }

    public static class Builder {

        private Statement statement;

        private Description description;

        public Builder(Statement statement, Description desc) {
            this.statement = statement;
            this.description = desc;
        }

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
