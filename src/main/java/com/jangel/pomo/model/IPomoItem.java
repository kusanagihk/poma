package com.jangel.pomo.model;

/**
 * Created by jl on 15/6/16.
 */
public interface IPomoItem {
    void setKey(String key);
    String getKey();

    void setValue(Object value);
    Object getValue();

    void setValueClass(Class valueClass);
    Class getValueClass();
}
