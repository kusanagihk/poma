package com.jangel.pomo.reflection;

import com.jangel.pomo.reflection.models.TestingModelPublicAccess;
import com.jangel.pomo.unittest.AbstractJUnit;
import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.Serializable;
import java.security.Key;
import java.security.interfaces.DSAPublicKey;
import java.util.Random;

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
        log.info("[T] IllegalArgumentException is an instance of RuntimeException~");

        isInstanceOf = instance.isInstanceOf(targetClassName_2, runtimeExceptionClass);
        Assert.assertThat("IOException should NOT be an instance of RuntimeException~", isInstanceOf, CoreMatchers.is(false));
        log.info("[F] IOException is NOT an instance of RuntimeException~");

        // interface testing
        isInstanceOf = instance.isInstanceOf(targetClassName_3, runnableInterfaceClassName);
        Assert.assertThat("Thread should be an instance of Runnable interface~", isInstanceOf, CoreMatchers.is(true));
        log.info("[T] Thread should be an instance of Runnable interface~");

        isInstanceOf = instance.isInstanceOf(targetClassName_4, runnableInterfaceClassName);
        Assert.assertThat("Object should NOT be an instance of Runnable interface~", isInstanceOf, CoreMatchers.is(false));
        log.info("[F] Object should NOT be an instance of Runnable interface~");

        log.info("all test(s) passed");
        this.logMethodEnd(log, "doCheckIfInstanceOfByClassName");
    }

    @Test
    public void doCheckIfInstanceOfByClass() {
        this.logMethodStart(log, "doCheckIfInstanceOfByClass");
        ReflectionUtil instance = new ReflectionUtil();
        boolean valid = false;
        Class runtimeEClass = RuntimeException.class;
        Class testClass_1 = IndexOutOfBoundsException.class;
        Class testClass_2 = IOException.class;

        valid = instance.isInstanceOf(testClass_1, runtimeEClass);
        Assert.assertThat("IndexOutOfBoundsException is a kind of runtime exception", valid, CoreMatchers.is(true));
        log.info("[T] IndexOutOfBoundsException is a kind of runtime exception");
        valid = instance.isInstanceOf(testClass_2, runtimeEClass);
        Assert.assertThat("IOException is NOT a kind of runtime exception", valid, CoreMatchers.is(false));
        log.info("[F] IOException is NOT a kind of runtime exception");

        Class serializableClass = Serializable.class;
        Class testClass_3 = Key.class;
        Class testClass_4 = DSAPublicKey.class;
        Class testClass_5 = Thread.class;

        valid = instance.isInstanceOf(testClass_3, serializableClass);
        Assert.assertThat("Key is a kind of Serializable", valid, CoreMatchers.is(true));
        log.info("[T] Key is a kind of Serializable");
        valid = instance.isInstanceOf(testClass_4, serializableClass);
        Assert.assertThat("DSAPublicKey is a kind of Serializable", valid, CoreMatchers.is(true));
        log.info("[T] DSAPublicKey is a kind of Serializable");
        valid = instance.isInstanceOf(testClass_5, serializableClass);
        Assert.assertThat("String is not a kind of Serializable", valid, CoreMatchers.is(false));
        log.info("[F] String is not a kind of Serializable");


        log.info("all tests passed~");
        this.logMethodEnd(log, "doCheckIfInstanceOfByClass");
    }

    @Test
    public void doCreateObjectByClassName() {
        this.logMethodStart(log, "doCreateObject");
        ReflectionUtil instance = new ReflectionUtil();
        String class_1 = TestingModelPublicAccess.class.getCanonicalName();
        Object finalObj;

        finalObj = instance.createObject(class_1);
        Assert.assertThat("object should be created!!", finalObj, CoreMatchers.notNullValue());
        Assert.assertThat("object should be instance of " + class_1,
                instance.isInstanceOf(finalObj.getClass(), TestingModelPublicAccess.class),
                CoreMatchers.is(true));
        log.info(class_1 + " instance > " + finalObj);


        String class_2 = Random.class.getCanonicalName();
        finalObj = instance.createObject(class_2);
        Assert.assertThat("object should be created!!", finalObj, CoreMatchers.notNullValue());
        Assert.assertThat("object should be instance of " + class_2,
                instance.isInstanceOf(finalObj.getClass(), Random.class),
                CoreMatchers.is(true));
        log.info(class_2 + " instance > " + finalObj);


        log.info("all test(s) passed");
        this.logMethodEnd(log, "doCreateObject");
    }

    @Test
    public void doCreateObjectByClass() {

    }

    @Test
    public void doInvokeMethodOnObjectByReflection() {

    }


}
