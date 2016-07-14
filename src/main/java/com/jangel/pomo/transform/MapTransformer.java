package com.jangel.pomo.transform;

import com.jangel.pomo.builder.PomoBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by jl on 14/7/16.
 */
public class MapTransformer implements ITransformer {

    /**
     * {@inheritDoc}
     */
    public <T> T transformTo(PomoBuilder builder, Class clz) {
        Map<String, Object> target = new HashMap<String, Object>();
        Set<String> keys = builder.attributeKeys();

        for (String key : keys) {
            target.put(key, builder.getAttribute(key));
        }
        return (T) target;
    }
}
