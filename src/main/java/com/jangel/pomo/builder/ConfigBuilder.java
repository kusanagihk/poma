package com.jangel.pomo.builder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jl on 15/6/16.
 */
public class ConfigBuilder {

    private Map<String, String> configs;

    static private final String KEY_POMO_ITEM_CLASS = "kpic";

    /**
     * ctor.
     */
    public ConfigBuilder() {
        this.configs = new HashMap<String, String>();
    }

    /**
     * set the IPomoItem class / type
     *
     * @param className
     * @return
     */
    public ConfigBuilder setPomoItemType(String className) {
        this.configs.put(KEY_POMO_ITEM_CLASS, className);
        return this;
    }

    /**
     * return the value associated with the KEY_POMO_ITEM_CLASS
     * @return
     */
    public String getPomoItemType() {
        return this.configs.get(KEY_POMO_ITEM_CLASS);
    }


    public Map<String, String> build() {
        return this.configs;
    }
}
