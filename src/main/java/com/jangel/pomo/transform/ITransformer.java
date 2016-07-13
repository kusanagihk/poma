package com.jangel.pomo.transform;

import com.jangel.pomo.builder.PomoBuilder;

/**
 * Created by jl on 8/7/16.
 */
public interface ITransformer {

    <T> T transformTo(PomoBuilder builder, Class clz);

}
