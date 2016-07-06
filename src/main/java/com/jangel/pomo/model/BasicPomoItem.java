package com.jangel.pomo.model;

/**
 * Created by jl on 15/6/16.
 */
public class BasicPomoItem implements IPomoItem {

    private String key;
    private Object value;
    private Class valueClass;

    /**
     * ctor.
     */
    public BasicPomoItem() {}

    /**
     * ctor
     * @param key
     * @param value
     */
    public BasicPomoItem(String key, Object value) {
        this.setKeyValue(key, value);
    }

    public void setKeyValue(String key, Object value) {
        this.setKey(key);
        this.setValue(value);
        this.setValueClass(value.getClass());
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValueClass(Class valueClass) {
        this.valueClass = valueClass;
    }

    public Class getValueClass() {
        return valueClass;
    }
}
