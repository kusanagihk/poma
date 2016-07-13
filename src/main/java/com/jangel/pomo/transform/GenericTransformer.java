package com.jangel.pomo.transform;

import com.jangel.pomo.builder.PomoBuilder;
import com.jangel.pomo.model.IPomoItem;
import com.jangel.pomo.reflection.ReflectionUtil;

import java.util.Set;

/**
 * Created by jl on 13/7/16.
 */
public class GenericTransformer implements ITransformer {

    private ReflectionUtil reflectionUtil;

    /**
     * ctor.
     */
    public GenericTransformer() {
        this.reflectionUtil = new ReflectionUtil();
    }

    /**
     *
     * @param clz
     * @param <T>
     * @return
     */
    public <T> T transformTo(PomoBuilder builder, Class clz) {
        T target;
        Set<String> keys;

        target = (T) this.reflectionUtil.createObject(clz);
        keys = builder.attributeKeys();

        for (String key : keys) {
            String setter = this.createSetterMethodNameByAttribute(key);
            IPomoItem item = builder.getAttribute(key);

            this.reflectionUtil.invokeMethod(target, setter, item.getValue());
        }   // end -- for (keys)
        return target;
    }

    /**
     * method to create the setter method name
     *
     * @param attribute
     * @return
     */
    private String createSetterMethodNameByAttribute(String attribute) {
        StringBuilder sb = new StringBuilder(100);
        String[] parts;

        // replacement rule (e.g. some_value = some-value = someValue)
        parts = attribute.split("[-_]");
        for (String part : parts) {
            sb.append(this.capitalize(part));
        }   // end -- for (parts)
        sb.insert(0, "set");

        // "set" + attribute.substring(0, 1).toUpperCase() + attribute.substring(1);
        return sb.toString();
    }

    /**
     * capitalize the given String (only the 1st character would be upperCased)
     *
     * @param value
     * @return
     */
    private String capitalize(String value) {
        String val = value;

        if (value != null && !value.isEmpty()) {
            val = value.substring(0, 1).toUpperCase() + value.substring(1);
        }   // end -- if (not null and not empty)
        return val;
    }

}
