package com.jangel.pomo.builder;

import com.google.gson.Gson;
import com.jangel.pomo.model.BasicPomoItem;
import com.jangel.pomo.model.IPomoItem;
import com.jangel.pomo.reflection.ReflectionUtil;
import com.jangel.pomo.transform.ITransformer;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by jl on 14/6/16.
 */
public class PomoBuilder {

    static private Logger log = Logger.getLogger(PomoBuilder.class);

    private Gson gson;
    /**
     * the core Map storing the dynamic attributes of this POJO (pomo)
     */
    private Map<String, IPomoItem> attributes;
    private ConfigBuilder configs;

    private ReflectionUtil reflectionUtil;

    Object lock = new Object();

    /**
     * ctor. default ctor
     */
    public PomoBuilder(ConfigBuilder configs) {
        this.attributes = new HashMap<String, IPomoItem>();
        this.gson = new Gson();
        this.reflectionUtil = new ReflectionUtil();

        this.configs = configs;
    }

    /**
     *
     * @param key
     * @param value
     * @return
     */
    public PomoBuilder attribute(String key, Object value) {
        boolean processed = false;

        // 1) check if key, value is valid
        if (key == null || key.isEmpty() || value == null) {
            if (log.isInfoEnabled()) {
                log.info("[attribute] either key or value is null > " + key + ", " + value);
            }
            return this;
        }

        // 2) check if any IPomoItem is specified
        if (this.configs != null && this.configs.getPomoItemType() != null) {
            /*
             *  1) new the target class
             *  2) set the key, value to the target object
             */
            if (this.reflectionUtil.isInstanceOf(this.configs.getPomoItemType(), IPomoItem.class.getCanonicalName())) {
                Object model = this.reflectionUtil.createObject(this.configs.getPomoItemType());
                if (model != null) {
                    // performance issue, cast to IPomoItem instead of reflection
                    //this.reflectionUtil.invokeMethod(model, "setKeyValue", key, value);
                    IPomoItem modelItem = (IPomoItem) model;

                    modelItem.setKeyValue(key, value);
                    this.attributes.put(key, modelItem);

                    processed = true;
                }
            }   // end -- if (given class is instanceof IPomoItem)
        }

        // default is "BasicPomoItem"
        if (!processed) {
            this.attributes.put(key, new BasicPomoItem(key, value));
        }
        return this;
    }


    /**
     * add the given Map of attributes
     *
     * @param attrs
     * @return
     */
    public PomoBuilder attributes(final Map<String, Object> attrs) {
        if (attrs != null) {
            Set<String> keys = attrs.keySet();

            for (String key : keys) {
                this.attribute(key, attrs.get(key));
            }   // end -- for (keys)
        }
        return this;
    }

    /**
     * return the value associated with the given key
     *
     * @param key
     * @param <T>
     * @return
     */
    public <T> T getAttribute(String key) {
        T value = null;
        IPomoItem item = this.attributes.get(key);

        if (item != null) {
            value = (T) item.getValue();
        }
        return value;
    }

    /**
     * return the Set of Keys associated with the available attribute(s)
     *
     * @return
     */
    public Set<String> getAttributeKeys() {
        Set<String> keys;

        synchronized (lock) {
            keys = this.attributes.keySet();
        }
        return keys;
    }

    /**
     * transform the given attributes to the given Class type
     * @param clz
     * @param <T>
     * @return
     */
    public <T> T transformTo(Class clz) {
        T target;
        Map<Class, Class> transformerMap = this.configs.getTransfomersMap();
        Class transformerClass = transformerMap.get(clz);
        ITransformer transformer;
        /*
            1) add a repository of transformer(s) (interface ITransformer)
            2) try to get the ITransformer by clz
            3) if valid use that Transformer
            4) else use the default Transformer
            5) maybe Map is a special ITransformer instance though... (done at step 3)
         */
        if (transformerClass == null) {
            if (log.isInfoEnabled()) log.info("*** transformerClass : get default as no match by key-class");
            // get default ITransformer
            transformerClass = transformerMap.get(Object.class);
        }

        if (log.isInfoEnabled()) log.info("*** transformerClass : " + transformerClass);

        // create the ITransformer instance
        transformer = (ITransformer) this.reflectionUtil.createObject(transformerClass);

        // transform
        target = transformer.transformTo(this, clz);

        return target;
    }

    /**
     * remove all attributes
     *
     * @return
     */
    public int clearAttributes() {
        int size;

        synchronized (lock) {
            size = this.attributes.size();
            this.attributes.clear();
        }
        return size;
    }


    /**
     *
     * @return
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(100);

        synchronized (lock) {
            Set<String> keyset = this.attributes.keySet();

            if (keyset.isEmpty()) {
                sb.append("empty pomo");

            } else {
                for (String key : keyset) {
                    IPomoItem item = this.attributes.get(key);

                    if (sb.length()>0) {
                        sb.append(", ");
                    }
                    sb.append("[").
                            append(item.getKey()).
                            append(":").
                            append(item.getValue()).
                            append(":").
                            append(item.getValueClass().getCanonicalName()).
                            append("]");
                }
            }
        }   // end -- sync(lock)
        return sb.toString();
        //return this.gson.toJson(this.attributes);
    }
}
