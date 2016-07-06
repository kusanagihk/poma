package com.jangel.pomo.reflection;

import org.apache.log4j.Logger;

import java.lang.reflect.Method;

/**
 * Created by jl on 27/6/16.
 */
public class ReflectionUtil {

    static Logger log = Logger.getLogger(ReflectionUtil.class);


    /**
     * check if the targetClass is inherited from the sourceClass (including interface implementation checks)
     * @param targetClassName
     * @param sourceClassName
     * @return
     */
    public boolean isInstanceOf(String targetClassName, String sourceClassName) {
        Class tarClass = this.getClassFromString(targetClassName);
        Class srcClass = this.getClassFromString(sourceClassName);

        return this.isInstanceOf(tarClass, srcClass);
    }

    /**
     * check if the targetClass is inherited from the sourceClass (including interface implementation checks)
     * @param targetClass
     * @param sourceClass
     * @return
     */
    public boolean isInstanceOf(Class targetClass, Class sourceClass) {
        boolean valid = false;

        if (targetClass !=null && sourceClass != null) {
            valid = sourceClass.isAssignableFrom(targetClass);
        }
        return valid;
    }

    /**
     * create class from given className, might have exception, check the logs
     * @param className
     * @return  a valid class object or null (means cannot create the class from string)
     */
    private Class getClassFromString(String className) {
        Class clz = null;

        try {
            clz = Class.forName(className);

        } catch (Exception e) {
            log.error("[getClassFromString] cannot create class from the given className: " + className, e);
        }
        return clz;
    }


    /**
     * create instance from the given class
     * @param clzName
     * @return
     */
    public Object createObject(String clzName) {
        Class clz = this.getClassFromString(clzName);

        return this.createObject(clz);
    }

    /**
     * create instance from the given class
     * @param clz
     * @return
     */
    public Object createObject(Class clz) {
        Object obj = null;

        if (clz != null) {
            try {
                // assume have default constructor or else... exception thrown
                obj = clz.newInstance();

            } catch (Exception e) {
                log.error("[createObject] cannot create object from default constructor, please check if the Class supplied has accessible default constructor or not: " + clz);
            }
        }
        return obj;
    }


    /**
     * invoke a method based on the given targetObject and the methodName + parameters supplied
     *
     * @param targetObject
     * @param methodName
     * @param parameters
     * @return
     */
    public Object invokeMethod(Object targetObject, String methodName, Object... parameters) {
        Object returnVal = null;

        try {
            Method[] methods = targetObject.getClass().getDeclaredMethods();
            Method method = null;

            for (Method m : methods) {
                if (m.getName().equals(methodName)) {
                    Class[] params = m.getParameterTypes();

                    if (params!=null && parameters!=null && params.length == parameters.length) {
                        method = m;
                        break;
                    }   // end -- if (found method with matching parameters)
                }
            }   // end -- method m

            if (method != null) {
                returnVal = method.invoke(targetObject, parameters);

            } else {
                log.warn("[invokeMethod] no such method found: " + methodName);
            }

        } catch (Exception e) {
            log.error("[invokeMethod] failed to invoke the method: " + methodName + " with params: " + parameters, e);
            throw new RuntimeException("[invokeMethod] failed to invoke the method: " + methodName + " with params: " + parameters, e);
        }
        return returnVal;
    }


}
