package org.mikeneck.fxjsjunit.application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runners.model.Statement;
import org.mikeneck.fxjsjunit.JsJUnit;
import org.mikeneck.fxjsjunit.builder.FxJsJUnitBuilder;

import java.util.Date;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.Assert.assertThat;

/**
 * @author mike_neck
 */
public class LauncherTest {

    private DateNote note;

    private Launcher launcher;

    @Before
    public void setup () {
        this.note = new DateNote();
        this.launcher = new Impl();
        launcher.setBase(new MockStatement());
    }

    @Test
    public void evaluation () throws Throwable {
        launcher.evaluate();

        assertThat(note.getBeforeLaunch(), is(lessThan(note.getLaunchAt())));
        assertThat(note.getLaunchAt(), is(lessThan(note.getAfterLaunch())));
        assertThat(note.getAfterLaunch(), is(lessThan(note.getEvaluateAt())));
        assertThat(note.getEvaluateAt(), is(lessThan(note.getBeforeFinish())));
        assertThat(note.getBeforeFinish(), is(lessThan(note.getFinishAt())));
        assertThat(note.getFinishAt(), is(lessThan(note.getAfterFinish())));
    }

    private class Impl extends Launcher {

        @Override
        protected void beforeLaunch() {
            note.setBeforeLaunch(now());
        }

        @Override
        protected void launch() {
            try {
                Thread.sleep(100L);
                note.setLaunchAt(now());
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                note.setLaunchAt(now());
            }
        }

        @Override
        protected void afterLaunch() {
            note.setAfterLaunch(now());
        }

        @Override
        protected void beforeFinish() {
            note.setBeforeFinish(now());
        }

        @Override
        protected void finish() {
            try {
                Thread.sleep(100L);
                note.setFinishAt(now());
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                note.setFinishAt(now());
            }
        }

        @Override
        protected void afterFinish() {
            note.setAfterFinish(now());
        }

        @Override
        public void setBuilder(FxJsJUnitBuilder b) {
        }

        @Override
        public JsJUnit getJsJUnit() {
            return null;
        }

        private long now () {
            Date date = new Date();
            return date.getTime();
        }
    }

    private class MockStatement extends Statement {
        @Override
        public void evaluate() throws Throwable {
            Thread.sleep(100L);
            Date date = new Date();
            note.setEvaluateAt(date.getTime());
            Thread.sleep(100L);
        }
    }

    private class DateNote {
        private long beforeLaunch = 0L;
        private long launchAt = 0L;
        private long afterLaunch = 0L;
        private long evaluateAt = 0L;
        private long beforeFinish = 0L;
        private long finishAt = 0L;
        private long afterFinish = 0L;

        public long getBeforeLaunch() {
            return beforeLaunch;
        }

        public void setBeforeLaunch(long beforeLaunch) {
            this.beforeLaunch = beforeLaunch;
        }

        public long getLaunchAt() {
            return launchAt;
        }

        public void setLaunchAt(long launchAt) {
            this.launchAt = launchAt;
        }

        public long getAfterLaunch() {
            return afterLaunch;
        }

        public void setAfterLaunch(long afterLaunch) {
            this.afterLaunch = afterLaunch;
        }

        public long getEvaluateAt() {
            return evaluateAt;
        }

        public void setEvaluateAt(long evaluateAt) {
            this.evaluateAt = evaluateAt;
        }

        public long getBeforeFinish() {
            return beforeFinish;
        }

        public void setBeforeFinish(long beforeFinish) {
            this.beforeFinish = beforeFinish;
        }

        public long getFinishAt() {
            return finishAt;
        }

        public void setFinishAt(long finishAt) {
            this.finishAt = finishAt;
        }

        public long getAfterFinish() {
            return afterFinish;
        }

        public void setAfterFinish(long afterFinish) {
            this.afterFinish = afterFinish;
        }
    }
}
