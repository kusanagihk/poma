package com.jangel.pomo.reflection;

import com.jangel.pomo.unittest.AbstractJUnit;
import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by jl on 27/6/16.
 */
public class ReflectionUtilTest extends AbstractJUnit {

    static Logger log = Logger.getLogger(ReflectionUtil.class);

    @Test
    public void doCheckIfInstanceOfByClassName() {
        this.logMethodStart(log, "doCheckIfInstanceOfByClassName");
        ReflectionUtil instance = new ReflectionUtil();

        String runtimeExceptionClass = RuntimeException.class.getCanonicalName();
        String targetClassName_1 = IllegalArgumentException.class.getCanonicalName();
        String targetClassName_2 = IOException.class.getCanonicalName();

        String runnableInterfaceClassName = Runnable.class.getCanonicalName();
        String targetClassName_3 = Thread.class.getCanonicalName();
        String targetClassName_4 = Object.class.getCanonicalName();
        boolean isInstanceOf;

        // class inheritance testing
        isInstanceOf = instance.isInstanceOf(targetClassName_1, runtimeExceptionClass);
        Assert.assertThat("IllegalArgumentException should be an instance of RuntimeException~", isInstanceOf, CoreMatchers.is(true));
        log.info("IllegalArgumentException is an instance of RuntimeException~");

        isInstanceOf = instance.isInstanceOf(targetClassName_2, runtimeExceptionClass);
        Assert.assertThat("IOException should NOT be an instance of RuntimeException~", isInstanceOf, CoreMatchers.is(false));
        log.info("IOException is NOT an instance of RuntimeException~");

        // interface testing
        isInstanceOf = instance.isInstanceOf(targetClassName_3, runnableInterfaceClassName);
        Assert.assertThat("Thread should be an instance of Runnable interface~", isInstanceOf, CoreMatchers.is(true));
        log.info("Thread should be an instance of Runnable interface~");

        isInstanceOf = instance.isInstanceOf(targetClassName_4, runnableInterfaceClassName);
        Assert.assertThat("Object should NOT be an instance of Runnable interface~", isInstanceOf, CoreMatchers.is(false));
        log.info("Object should NOT be an instance of Runnable interface~");

        log.info("all test(s) passed");
        this.logMethodEnd(log, "doCheckIfInstanceOfByClassName");
    }

    @Test
    public void doCheckIfInstanceOfByClass() {
        this.logMethodStart(log, "doCheckIfInstanceOfByClass");



        this.logMethodEnd(log, "doCheckIfInstanceOfByClass");
    }

    @Test
    public void doCreateObject() {

    }

}
