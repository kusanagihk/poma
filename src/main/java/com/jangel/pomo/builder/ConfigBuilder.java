package com.jangel.pomo.builder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jl on 15/6/16.
 */
public class ConfigBuilder {

    private Map<String, Object> configs;
    private Map<Class, Class> transfomersMap;

    static private final String KEY_POMO_ITEM_CLASS = "kpic";
    static private final String KEY_ITRANSFORMERS_MAP = "kim";

    /**
     * ctor.
     */
    public ConfigBuilder() {
        this.configs = new HashMap<String, Object>();
        /*
         *  add the ITransformer Map
         *  key : Class - the target / matching class
         *  value : Class - the ITransformer class
         */
        this.transfomersMap = new HashMap<Class, Class>();
        this.configs.put(KEY_ITRANSFORMERS_MAP, this.transfomersMap);
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
        return (String) this.configs.get(KEY_POMO_ITEM_CLASS);
    }


    /**
     * set / add / replace the Transformer (class) based on the given key (class)
     *
     * @param keyClass
     * @param transformerClass
     * @return
     */
    public ConfigBuilder setTransformerInstance(Class keyClass, Class transformerClass) {
        this.transfomersMap.put(keyClass, transformerClass);
        return this;
    }

    /**
     * return the transformerMap
     * @return
     */
    public Map<Class, Class> getTransfomersMap() {
        return this.transfomersMap;
    }



    public Map<String, Object> build() {
        return this.configs;
    }
}
