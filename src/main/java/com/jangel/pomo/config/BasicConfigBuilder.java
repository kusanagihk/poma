package com.jangel.pomo.config;

import com.jangel.pomo.builder.ConfigBuilder;
import com.jangel.pomo.transform.GenericTransformer;

/**
 * Created by jl on 13/7/16.
 */
public class BasicConfigBuilder {

    /**
     *  basic config
     */
    static public ConfigBuilder buildBasicConfig() {
        ConfigBuilder builder = new ConfigBuilder();
        /*
         *  Object => generic type => GenericTransformer
         */
        builder.setTransformerInstance(Object.class, GenericTransformer.class);

        return builder;
    }

}
