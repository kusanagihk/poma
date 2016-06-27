package com.jangel.pomo.reflection.models;

import java.util.Random;

/**
 * Created by jl on 28/6/16.
 */
public class TestingModelPublicAccess {

    int rndNum;

    public TestingModelPublicAccess() {
        System.out.println("inside ctor of TestingModelPublicAccess");
        rndNum = new Random(System.currentTimeMillis()).nextInt(20000000);
    }

    public String toString() {
        return "rnd is " + this.rndNum;
    }

}
