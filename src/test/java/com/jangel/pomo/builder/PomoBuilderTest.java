package com.jangel.pomo.builder;

import com.jangel.pomo.model.BasicPomoItem;
import com.jangel.pomo.unittest.AbstractJUnit;
import org.apache.log4j.Logger;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
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

        educationLst.add("primary school");
        educationLst.add("high school");
        educationLst.add("college");
        educationLst.add("university");

        attrs.put("name", "json");
        attrs.put("age", 39);
        attrs.put("height", 166.5f);
        attrs.put("hobbies", new String[]{ "movies", "books", "jazz music" });
        attrs.put("education", educationLst);
        attrs.put("testingObject", new TestingModel());
    }

    @Test
    public void buildFromMapAttributes() {
        this.logMethodStart(log, "buildFromMapAttributes");
        PomoBuilder builder = new PomoBuilder();

        Assert.assertThat("empty pomo", CoreMatchers.is(builder.toString()));
        log.info("a) by default the PomoBuilder has no attributes and hence return \"empty pomo\" when toString() is called.");

        // b) test on building from Map attributes at once
        builder = builder.attributes(attrs);
        Assert.assertThat(builder, CoreMatchers.notNullValue());
        Assert.assertThat(builder.toString(), CoreMatchers.containsString("java.lang.String[]"));
        log.info("b) builder with a Map of attributes > " + builder.toString());

        this.logMethodEnd(log, "buildFromMapAttributes");
    }

    @Test
    public void buildWithConfigSet() {
        this.logMethodStart(log, "buildWithConfigSet");
        PomoBuilder builder = new PomoBuilder();
        ConfigBuilder cBuilder = new ConfigBuilder();

        // set the PomoItemType to BasicPomoItem (yet)
        cBuilder.setPomoItemType(BasicPomoItem.class.getCanonicalName());
        builder.config(cBuilder);

        Assert.assertThat("empty pomo", CoreMatchers.is(builder.toString()));
        log.info("a) by default the PomoBuilder has no attributes and hence return \"empty pomo\" when toString() is called.");

        // b) test on building from Map attributes at once
        builder = builder.attributes(attrs);
        Assert.assertThat(builder, CoreMatchers.notNullValue());
        Assert.assertThat(builder.toString(), CoreMatchers.containsString("java.lang.String[]"));
        Assert.assertThat(builder.toString(), CoreMatchers.containsString("java.util.ArrayList"));
        log.info("b) builder with a Map of attributes > " + builder.toString());

        this.logMethodEnd(log, "buildWithConfigSet");
    }

    @Test
    public void xportTest() {

    }


    /**
     * for testing purpose only
     */
    static public class TestingModel {
        public String version = "1.1";
    }
}
