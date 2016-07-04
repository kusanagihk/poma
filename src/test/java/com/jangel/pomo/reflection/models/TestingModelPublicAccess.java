package com.jangel.pomo.reflection.models;

import java.util.Random;

/**
 * Created by jl on 28/6/16.
 */
public class TestingModelPublicAccess {

    int rndNum;
    private String name;
    private Boolean male = Boolean.TRUE;
    private int age = -1;
    private String complexAttribute1;
    private String complexAttribute2;
    private String complexAttribute3;
    private String complexAttributeForTesting;

    /**
     * ctor
     */
    public TestingModelPublicAccess() {
        System.out.println("inside ctor of TestingModelPublicAccess");
        rndNum = new Random(System.currentTimeMillis()).nextInt(20000000);
    }

    public int getRndNum() {
        return rndNum;
    }

    public void setRndNum(int rndNum) {
        this.rndNum = rndNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getMale() {
        return male;
    }

    public void setMale(Boolean male) {
        this.male = male;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getComplexAttribute1() {
        return complexAttribute1;
    }

    public void setComplexAttribute1(String complexAttribute1) {
        this.complexAttribute1 = complexAttribute1;
    }

    public String getComplexAttribute2() {
        return complexAttribute2;
    }

    public void setComplexAttribute2(String complexAttribute2) {
        this.complexAttribute2 = complexAttribute2;
    }

    public String getComplexAttribute3() {
        return complexAttribute3;
    }

    public void setComplexAttribute3(String complexAttribute3) {
        this.complexAttribute3 = complexAttribute3;
    }

    public String getComplexAttributeForTesting() {
        return complexAttributeForTesting;
    }

    public void setComplexAttributeForTesting(String complexAttributeForTesting) {
        this.complexAttributeForTesting = complexAttributeForTesting;
    }

    @Override
    public String toString() {
        return "TestingModelPublicAccess{" +
                "rndNum=" + rndNum +
                ", name='" + name + '\'' +
                ", male=" + male +
                ", age=" + age +
                ", complexAttribute1='" + complexAttribute1 + '\'' +
                ", complexAttribute2='" + complexAttribute2 + '\'' +
                ", complexAttribute3='" + complexAttribute3 + '\'' +
                ", complexAttributeForTesting='" + complexAttributeForTesting + '\'' +
                '}';
    }
}

