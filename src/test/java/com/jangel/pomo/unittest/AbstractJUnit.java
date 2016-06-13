package com.jangel.pomo.unittest;

import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Created by jl on 14/6/16.
 */
public class AbstractJUnit {

    public void logMethodStart(Logger log, String methodName) {
        if (log.isInfoEnabled()) log.info("*** "+methodName+" started at: "+new Date()+" ***");
    }

    public void logMethodEnd(Logger log, String methodName) {
        if (log.isInfoEnabled()) log.info("*** "+methodName+" ended at: "+new Date()+" ***");
    }

}
