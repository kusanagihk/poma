package com.jangel.pomo.reflection;

import org.apache.log4j.Logger;

/**
 * Created by jl on 27/6/16.
 */
public class ReflectionUtil {

    static Logger log = Logger.getLogger(ReflectionUtil.class);


    /**
     * check if the targetClass is inherited from the sourceClass
     * @param targetClassName
     * @param sourceClassName
     * @return
     */
    public boolean isInstanceOf(String targetClassName, String sourceClassName) {
        boolean valid = false;
        Class tarClass = this.getClassFromString(targetClassName);
        Class srcClass = this.getClassFromString(sourceClassName);

        if (tarClass != null && srcClass != null) {
            Class clz;
            Class currentTarClass = tarClass;

            while ((clz = currentTarClass.getSuperclass())!=null) {
                if (clz.equals(srcClass)) {
                    valid = true;
                    break;

                } else {
                    currentTarClass = currentTarClass.getSuperclass();
                }
            }   // end -- while (superclass checks)
        }

        if (!valid) {
            /*
             * stackoverflow.com
             * questions/10165887/how-to-check-if-an-object-implements-an-interface
             */
// TODO: interface checks....
            tarClass.isAssignableFrom(Runnable.class);
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


}
