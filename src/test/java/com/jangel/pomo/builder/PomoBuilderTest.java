package com.jangel.pomo.builder;

import com.jangel.pomo.config.BasicConfigBuilder;
import com.jangel.pomo.model.BasicPomoItem;
import com.jangel.pomo.reflection.models.TestingModelPublicAccess;
import com.jangel.pomo.unittest.AbstractJUnit;
import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by jl on 14/6/16.
 */
public class PomoBuilderTest extends AbstractJUnit {
    static Logger log = Logger.getLogger(PomoBuilderTest.class);

    static Map<String, Object> attrs = new HashMap<String, Object>();
    static {
        List<String> educationLst = new ArrayList<String>();
        Map<String, Object> mapObject = new HashMap<String, Object>();

        educationLst.add("primary school");
        educationLst.add("high school");
        educationLst.add("college");
        educationLst.add("university");

        mapObject.put("1_name", "name of applicant");
        mapObject.put("2_isMail", Boolean.TRUE);
        mapObject.put("3_age", 39);

        attrs.put("name", "json");
        attrs.put("age", 39);
        attrs.put("height", 166.5f);
        attrs.put("hobbies", new String[]{ "movies", "books", "jazz music" });
        attrs.put("education", educationLst);
        attrs.put("testingObject", new TestingModel());
        attrs.put("mapObject", mapObject);
        attrs.put("testingObjectPublicAccess", new TestingModelPublicAccess());
    }

    private PomoBuilder builder;

    @Before
    public void beforeMethod() {
        this.builder = new PomoBuilder(BasicConfigBuilder.buildBasicConfig());
    }

    @After
    public void afterMethod() {
        this.builder = null;
    }

    /**
     * testing on creating the PomoBuilder through attributes (Map)
     */
    @Test
    public void buildFromMapAttributes() {
        this.logMethodStart(log, "buildFromMapAttributes");

        Assert.assertThat("empty pomo", CoreMatchers.is(builder.toString()));
        log.info("a) by default the PomoBuilder has no attributes and hence return \"empty pomo\" when toString() is called.");

        // b) test on building from Map attributes at once
        builder = builder.attributes(attrs);
        Assert.assertThat(builder, CoreMatchers.notNullValue());
        Assert.assertThat(builder.toString(), CoreMatchers.containsString("java.lang.String[]"));
        log.info("b) builder with a Map of attributes > " + builder.toString());

        this.logMethodEnd(log, "buildFromMapAttributes");
    }

    /**
     * test on creating the PomoBuilder through some config value(s)
     * such as setting the corresponding "KEY_POMO_ITEM_CLASS"
     */
    @Test
    public void buildWithConfigSet() {
        this.logMethodStart(log, "buildWithConfigSet");
        ConfigBuilder cBuilder = BasicConfigBuilder.buildBasicConfig();
        PomoBuilder builder1 = new PomoBuilder(cBuilder);

        // set the PomoItemType to BasicPomoItem (yet)
        cBuilder.setPomoItemType(BasicPomoItem.class.getCanonicalName());

        // check the transformerMap
        Map<Class, Class> transformerMap = cBuilder.getTransfomersMap();
        Assert.assertThat("transfomerMap should not be null", transformerMap, CoreMatchers.notNullValue());
        Assert.assertThat("transfomerMap should not be empty", transformerMap.isEmpty(), CoreMatchers.is(false));
        log.info("0) transformerMap > " + transformerMap);


        Assert.assertThat("empty pomo", CoreMatchers.is(builder1.toString()));
        log.info("a) by default the PomoBuilder has no attributes and hence return \"empty pomo\" when toString() is called.");

        // b) test on building from Map attributes at once
        builder1 = builder1.attributes(attrs);
        Assert.assertThat(builder1, CoreMatchers.notNullValue());
        Assert.assertThat(builder1.toString(), CoreMatchers.containsString("java.lang.String[]"));
        Assert.assertThat(builder1.toString(), CoreMatchers.containsString("java.util.ArrayList"));
        log.info("b) builder with a Map of attributes > " + builder1.toString());

        this.logMethodEnd(log, "buildWithConfigSet");
    }

    /**
     * test on getting back the attribute(s) of a given pomoBuilder
     */
    @Test
    public void getAttributeValueTest() {
        this.logMethodStart(log, "getAttributeValueTest");
        Map<String, String> abilities = new HashMap<String, String>();
        String sValue;
        int iValue;
        float fValue;
        List lValue;
        Map mValue;
        String[] sArrValue;
        TestingModelPublicAccess tObjValue;


        builder.attributes(attrs);
        Assert.assertThat("pomo builder has attributes, one of them is an arrayList",
                builder.toString().contains("java.util.ArrayList"), CoreMatchers.is(true));

        abilities.put("swim", "beginner");
        abilities.put("run", "500 meter champion");
        abilities.put("tennis", "european semi finals");
        builder.attribute("abilities", abilities);
        Assert.assertThat("pomo builder has attributes, one of them is a HashMap",
                builder.toString().contains("java.util.HashMap"), CoreMatchers.is(true));

        // get back attribute values
        sValue = builder.getAttribute("name");
        Assert.assertThat("name attribute = json", sValue, CoreMatchers.is("json"));
        log.info("[String] name attribute = json");

        iValue = builder.getAttribute("age");
        Assert.assertThat("age attribute = 39", iValue, CoreMatchers.is(39));
        log.info("[int] age attribute = 39");

        fValue = builder.getAttribute("height");
        Assert.assertThat("height attribute = 166.5", fValue, CoreMatchers.is(166.5f));
        log.info("[float] height attribute = 166.5");

        sArrValue = builder.getAttribute("hobbies");
        Assert.assertThat("hobbies attribute is a String[]",
                sArrValue.toString().indexOf(String[].class.getName()) > -1, CoreMatchers.is(true));
        log.info("[String array] hobbies is a String[]");
        Assert.assertThat("hobbies 2nd element is 'books'", sArrValue[1], CoreMatchers.equalTo("books"));
        log.info("[String array] hobbies 2nd element is 'books'");
        Assert.assertThat("hobbies has 3 elements", sArrValue.length, CoreMatchers.is(3));
        log.info("[String array] hobbies has 3 elements");

        lValue = builder.getAttribute("education");
        Assert.assertThat("education is an instance of java.util.List",
                lValue instanceof List, CoreMatchers.is(true));
        log.info("[List] education is an instance of java.util.List");
        Assert.assertThat("education List has 4 elements", lValue.size() == 4, CoreMatchers.is(true));
        log.info("[List] education List has 4 elements");
        Assert.assertThat("4 the element of education is 'university'",
                lValue.get(3).toString(), CoreMatchers.equalTo("university"));
        log.info("[List] 4 the element of education is 'university'");
        log.info("[List] value of toString => " + lValue.toString());

        mValue = builder.getAttribute("mapObject");
        Assert.assertThat("mapObject is an instance of Map", mValue instanceof Map, CoreMatchers.is(true));
        log.info("[Map] mapObject is an instance of Map");
        Assert.assertThat("key 2_isMail yields TRUE", (Boolean) mValue.get("2_isMail"), CoreMatchers.equalTo(Boolean.TRUE));
        log.info("[Map] key 2_isMail yields TRUE");
        log.info("[Map] value of toString => " + mValue);

        tObjValue = builder.getAttribute("testingObjectPublicAccess");
        Assert.assertThat("testingObjectPublicAccess is non null", tObjValue, CoreMatchers.notNullValue());
        log.info("[testingObjectPublicAccess] not null, toString => " + tObjValue);

        log.info("all test(s) passed~");
        this.logMethodEnd(log, "getAttributeValueTest");
    }

    /**
     * test on transforming a PomoBuilder into a target Class Type
     */
    @Test
    public void transformationTest() {
        this.logMethodStart(log, "transformationTest");
        TestingModelPublicAccess target_1;
        Map target_2_map_generic;
        HashMap target_2_hashmap_generic;


        builder.attribute("name", "St.Juliana");
        builder.attribute("male", Boolean.FALSE);
        builder.attribute("nonExistingValue", "something you never know about");
        Assert.assertThat("builder contains entry of 'male'",
                builder.toString().indexOf("male") > -1, CoreMatchers.is(true));
        log.info("[builder] builder contains entry of 'male'");
        log.info("[builder] builder toString => " + builder);

        target_1 = builder.transformTo(TestingModelPublicAccess.class);
        Assert.assertThat("target object is not null", target_1, CoreMatchers.notNullValue());
        log.info("[target_1] target object is not null, toString => " + target_1);

        // testing on attribute value replacement (e.g. some-value = some_value = someValue)
        log.info("[target_1] test on using complex attribute names involving '-' or '_' characters");
        builder.attribute("complex-attribute-1", "com value 1");
        builder.attribute("complex_attribute_2", "com value 2");
        builder.attribute("complexAttribute3", "com value 3");
        builder.attribute("male", Boolean.TRUE);
        // mixed _ and - characters
        builder.attribute("complex_attribute-forTesting", "com value 4");
        target_1 = builder.transformTo(TestingModelPublicAccess.class);
        Assert.assertThat("complex value 1 should be 'com value 1'", target_1.getComplexAttribute1(), CoreMatchers.equalTo("com value 1"));
        Assert.assertThat("complex value 2 should be 'com value 2'", target_1.getComplexAttribute2(), CoreMatchers.equalTo("com value 2"));
        Assert.assertThat("complex value 3 should be 'com value 3'", target_1.getComplexAttribute3(), CoreMatchers.equalTo("com value 3"));
        log.info("[target_1] complex attributes set successfully, toString => " + target_1);

        // special class type => Map (must support this type)
        log.info("\r\n[target_2_map_generic] test on using a MapTransformer");
        target_2_map_generic = builder.transformTo(Map.class);
        Assert.assertThat("map should not be null, transformable", target_2_map_generic, CoreMatchers.notNullValue());
        Assert.assertThat("map should not be empty, transformable", target_2_map_generic.isEmpty(), CoreMatchers.is(false));
        Assert.assertThat("should have item 'complex-attribute-1'",
                target_2_map_generic.get("complex-attribute-1").toString(), CoreMatchers.is("com value 1"));
        log.info("[target_2_map_generic] map transformed successfully => " + target_2_map_generic);

        // special class type => Map (must support this type)
        log.info("\r\n[target_2_hashmap_generic] test on using a MapTransformer");
        target_2_hashmap_generic = builder.transformTo(Map.class);
        Assert.assertThat("hmap should not be null, transformable", target_2_hashmap_generic, CoreMatchers.notNullValue());
        Assert.assertThat("hmap should not be empty, transformable", target_2_hashmap_generic.isEmpty(), CoreMatchers.is(false));
        Assert.assertThat("should have item 'complex-attribute-1'",
                target_2_hashmap_generic.get("nonExistingValue").toString(), CoreMatchers.is("something you never know about"));
        log.info("[target_2_hashmap_generic] map transformed successfully => " + target_2_hashmap_generic);

        log.info("all tests have passed");
        this.logMethodEnd(log, "transformationTest");
    }


    /**
     * for testing purpose only
     */
    static public class TestingModel {
        public String version = "1.1";
    }
}
