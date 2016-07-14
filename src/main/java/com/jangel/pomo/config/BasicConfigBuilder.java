package com.jangel.pomo.config;

import com.jangel.pomo.builder.ConfigBuilder;
import com.jangel.pomo.transform.GenericTransformer;
import com.jangel.pomo.transform.MapTransformer;

import java.util.HashMap;
import java.util.Map;

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
        // Map, HashMap => MapTransformer
        builder.setTransformerInstance(Map.class, MapTransformer.class);
        builder.setTransformerInstance(HashMap.class, MapTransformer.class);

        return builder;
    }

}
