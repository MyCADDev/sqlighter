package com.vals.a2ios.amfibian.impl;

import com.vals.a2ios.amfibian.intf.AnObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.vals.a2ios.amfibian.intf.AnAttrib;

/**
 * Created by vsayenko on 9/26/15.
 *
 */
public class AnObjectImpl<T> implements AnObject<T> {
    @SuppressWarnings("rawtypes")
    private AnObject parentAnObject;
    private Map<String, Object> nativeObjectMap;
    private Map<String, Object> jsonMap;
    private T nativeObject;
    protected Class<T> nativeClass;
    private Map<String, AnAttrib> attribMap = new LinkedHashMap<String, AnAttrib>();

    private CustomConverter jsonCustomGetConverter;
    private CustomConverter jsonCustomSetConverter;

    /**
     * Simple, initial built in converters between JSON and Native
     * formats. YMMV, so you may want to implement yours once you get
     * deep into your project's implementation.
     */
    private static CustomConverter jsonCustomGetGlobalConverter= new JsonSimpleGetConverter();
    private static CustomConverter jsonCustomSetGlobalConverter = new JsonSimpleSetConverter();

    public AnObjectImpl() {
    }

    public AnObjectImpl(Class<T> anObjClass, AnObject<?> parentAnObject) {
        init(anObjClass, parentAnObject);
    }

    public AnObjectImpl(Class<T> anObjClass, String[] propertyNames, AnObject<?> parentAnObject) {
        init(anObjClass, propertyNames, parentAnObject);
    }

    public AnObjectImpl(Class<T> anObjClass, AnAttrib[] propertyMappers, AnObject<?> parentAnObject) {
        init(anObjClass, propertyMappers, parentAnObject);
    }

    public AnObjectImpl(Class<T> anObjClass, String[] propertyNames) {
        init(anObjClass, propertyNames);
    }

    public AnObjectImpl(Class<T> anObjClass, AnAttrib[] propertyMappers) {
        init(anObjClass, propertyMappers);
    }

    public AnObject getParentAnObject() {
        return parentAnObject;
    }

    @Override
    public void resetNativeObject() throws Exception {
        setNativeObject(nativeClass.newInstance());
    }

    private void clearMaps() {
        jsonMap = null;
        nativeObjectMap = null;
    }

    private boolean isEmpty(Map<?,?> map) {
        return map == null || map.size() == 0;
    }

    private <K,V> Map<K, V> ensureMap(Map<K, V> map) {
        if(map == null) {
            return new HashMap<K, V>();
        }
        return map;
    }

	@SuppressWarnings("unchecked")
	public void setNativeObject(T o) throws Exception {
        this.nativeObject = o;
        clearMaps();
        if (parentAnObject != null) {
            parentAnObject.setNativeObject(o);
        }
    }

    public T getNativeObject() {
        return nativeObject;
    }

    @Override
    public Class<T> getNativeClass() {
        return nativeClass;
    }

    @Override
    @SuppressWarnings("unchecked")
	public Map<String, AnAttrib> getAllAttribMap() {
        Map<String, AnAttrib> p = new HashMap<String, AnAttrib>();
        if(parentAnObject != null) {
            p.putAll(parentAnObject.getAllAttribMap());
        }
        if (attribMap != null) {
            p.putAll(attribMap);
        }
        return p;
    }

    public AnAttrib[] getAllAttribs() {
        Map<String, AnAttrib> map = getAllAttribMap();
        AnAttrib[] attribs = new AnAttrib[map.size()];
        Set<String> keys = map.keySet();
        int i = 0;
        for (String key: keys) {
            attribs[i++] = map.get(key);
        }
        return attribs;
    }

    public AnAttrib[] getOwnAttribs() {
        Map<String, AnAttrib> map = attribMap;
        AnAttrib[] attribs = new AnAttrib[map.size()];
        Set<String> keys = map.keySet();
        int i = 0;
        for (String key: keys) {
            attribs[i++] = map.get(key);
        }
        return attribs;

    }

    @Override
    public AnAttrib getAttrib(String propertyName) {
        AnAttrib a = attribMap.get(propertyName);
        if (a == null && parentAnObject != null) {
            a = parentAnObject.getAttrib(propertyName);
        }
        return a;
    }

    @SuppressWarnings("unchecked")
	public void setNativeClass(Class<T> anObjClass) {
        this.nativeClass = anObjClass;
        Class<?> superClass = anObjClass.getSuperclass();
        if (superClass != null) {
            if (parentAnObject != null) {
                parentAnObject.setNativeClass(superClass);
            }
        }
    }

    protected void init(Class<T> anObjClass, AnObject<?> parentMapper) {
        this.parentAnObject = parentMapper;
        setNativeClass(anObjClass);
    }
    
    protected void init(Class<T> anObjClass, AnAttrib[] propertyMappers , AnObject<?> parentMapper) {
        init(anObjClass, parentMapper);
        initAttribs(propertyMappers);
    }
    
    protected void init(Class<T> anObjClass, AnAttrib[] propertyMappers) {
        initAttribs(propertyMappers);
        setNativeClass(anObjClass);
    }

    protected void init(Class<T> anObjClass, String[] propertyNames, AnObject<?> parentMapper) {
    		this.parentAnObject = parentMapper;
    		init(anObjClass, propertyNames);
    }

    protected void init(Class<T> anObjClass, String[] propertyNames) {    
        AnAttrib[] list = stringsToAttribs(propertyNames);
        init(anObjClass, list);
    }
    
    private AnAttrib[] stringsToAttribs(String[] propertyNames) {
        AnAttrib[] list = null;
        if (propertyNames != null) {
                list = new AnAttrib[propertyNames.length];
                int idx = 0;
                for (String propertyName: propertyNames) {
                        AnAttrib a = new AnAttribImpl(propertyName);
                        list[idx++] = a;
                }
        }    
        return list;
    }
    
    private void initAttribs(AnAttrib[] attribMappers)  {
        for (AnAttrib pm: attribMappers) {
            addAttrib(pm);
        }
    }
    
    @Override
    public void addAttrib(AnAttrib anAttribMapper) {
        anAttribMapper.setAnObject(this);
        attribMap.put(anAttribMapper.getAttribName(), anAttribMapper);
    }

    @Override
    public Map<String, Object> asJsonMap() throws Exception {
        if (isEmpty(jsonMap)/*jsonMap == null*/) {
        	jsonMap = ensureMap(jsonMap); // new HashMap<String, Object>();
            Set<String> p = attribMap.keySet();
            for (String attrName : p) {
                AnAttrib attr =  attribMap.get(attrName);
                Object value = getValue(
                        AnObjectImpl.getJsonCustomGetGlobalConverter(),
                        jsonCustomGetConverter,
                        attr);
                if (value != null) {
                	jsonMap.put(attr.getJsonOrAttribName(), value);
                }
            }
        }
        if (parentAnObject != null) {
            @SuppressWarnings("unchecked")
			Map<String, Object> parentJsonMap = parentAnObject.asJsonMap();
            Set<String> keys = parentJsonMap.keySet();
            for (String k: keys) {
            	jsonMap.put(k, parentJsonMap.get(k));
            }
        }
        return jsonMap;
    }

    @Override
    public synchronized Map<String, Object> getMap(T nativeObject) throws Exception {
        return asNativeMap(nativeObject);
    }

    @Override
    public synchronized Map<String, Object> asNativeMap(T nativeObject) throws Exception {
        setNativeObject(nativeObject);
        if (isEmpty(nativeObjectMap)/*nativeObjectMap == null*/) {
            nativeObjectMap = ensureMap(nativeObjectMap); //new HashMap<String, Object>();
            Set<String> p = attribMap.keySet();
            for (String pName : p) {
                AnAttrib pm =  attribMap.get(pName);
                Object value = pm.getValue();
                if (value != null) {
                    nativeObjectMap.put(pName, pm.getValue());
                }
            }
        }
        if (parentAnObject != null) {
            @SuppressWarnings("unchecked")
			Map<String, Object> parentMap = parentAnObject.asNativeMap(nativeObject);
            Set<String> keys = parentMap.keySet();
            for (String k: keys) {
                nativeObjectMap.put(k, parentMap.get(k));
            }
        }
        return nativeObjectMap;
    }

    @Override
    public synchronized JSONObject asJSONObject(T nativeObject) throws Exception {
        setNativeObject(nativeObject);
        getMap(nativeObject);
        return new JSONObject(asJsonMap());
    }

    @Override
    public synchronized T asNativeObject(JSONObject jsonObject) throws Exception {
        if (nativeObject == null) {
            resetNativeObject();
        }
        if (parentAnObject != null) {
            parentAnObject.asNativeObject(jsonObject);
        }
        Set<String> attrObjsKeys = attribMap.keySet();
        for (String attribName: attrObjsKeys) {
            AnAttrib attr = attribMap.get(attribName);
            if(!jsonObject.isNull(attr.getJsonOrAttribName())) {
                Object attrValue = jsonObject.get(attr.getJsonOrAttribName());
                if (attrValue != null) {
                    setValue(AnObjectImpl.getJsonCustomSetGlobalConverter(), jsonCustomSetConverter, attr, attrValue);
                }
            }
        }
        return nativeObject;
    }

    protected void setValue(CustomConverter globalConverter,
            CustomConverter converter, AnAttrib attrib, Object value) throws Exception {
        if(attrib.getCustomSetConverter() == null) {
            if(converter != null) {
                attrib.setValue(converter.convert(attrib, value));
                return;
            } else if(globalConverter != null) {
                attrib.setValue(globalConverter.convert(attrib, value));
                return;
            }
        }
        attrib.setValue(value);
    }

    @Override
    public synchronized T asNativeObject(String jsonString) throws Exception {
        return asNativeObject(new JSONObject(jsonString));
    }

    @Override
    public synchronized Collection<T> asList(String jsonArrayString) throws Exception {
        JSONArray jsonArray = new JSONArray(jsonArrayString);
        List<T> l = new LinkedList<T>();
        for (int i = 0; i < jsonArray.length(); i++) {
            Object o = jsonArray.get(i);
            this.resetNativeObject();
            T t = asNativeObject((JSONObject) o);
            l.add(t);
        }
        resetNativeObject();
        return l;
    }

    @Override
    public synchronized String asJsonString(T nativeObject) throws Exception {
        return asJSONObject(nativeObject).toString();
    }

    @Override
    public synchronized JSONArray asJSONArray(Collection<T> objects) throws Exception {
        JSONArray ja = new JSONArray();
        int idx = 0;
        if(objects != null) {
            for (T obj : objects) {
                JSONObject jo = asJSONObject(obj);
                ja.put(idx++, jo);
            }
        }
        return ja;
    }

    @Override
    public synchronized String asJsonArrayString(Collection<T> objects) throws Exception {
        JSONArray ja = asJSONArray(objects);
        return ja.toString();
    }

    protected Object getValue(
            CustomConverter globalConverter,
            CustomConverter converter,
            AnAttrib attrb) throws Exception {
        if(attrb.getCustomGetConverter() == null) {
            if (converter != null) {
                return converter.convert(attrb, attrb.getValue());
            }
            if (globalConverter != null) {
                return globalConverter.convert(attrb, attrb.getValue());
            }
        }
        return attrb.getValue();
    }

    /**
     *
     * @return
     */
    @Override
    public CustomConverter getJsonCustomSetConverter() {
        return jsonCustomSetConverter;
    }

    /**
     *
     * @return
     */
    @Override
    public void setJsonCustomSetConverter(CustomConverter jsonCustomSetConverter) {
        this.jsonCustomSetConverter = jsonCustomSetConverter;
    }

    /**
     *
     * @return
     */
    @Override
    public CustomConverter getJsonCustomGetConverter() {
        return jsonCustomGetConverter;
    }

    /**
     *
     * @return
     */
    @Override
    public void setJsonCustomGetConverter(CustomConverter jsonCustomGetConverter) {
        this.jsonCustomGetConverter = jsonCustomGetConverter;
    }

    public static CustomConverter getJsonCustomGetGlobalConverter() {
        return AnObjectImpl.jsonCustomGetGlobalConverter;
    }

    public static void setJsonCustomGetGlobalConverter(CustomConverter jsonCustomGetGlobalConverter) {
        AnObjectImpl.jsonCustomGetGlobalConverter = jsonCustomGetGlobalConverter;
    }

    public static CustomConverter getJsonCustomSetGlobalConverter() {
        return AnObjectImpl.jsonCustomSetGlobalConverter;
    }

    public static void setJsonCustomSetGlobalConverter(CustomConverter jsonCustomSetGlobalConverter) {
        AnObjectImpl.jsonCustomSetGlobalConverter = jsonCustomSetGlobalConverter;
    }

    public static class JsonSimpleGetConverter implements CustomConverter {
        @Override
        public Object convert(AnAttrib attrib, Object value) {
            if(value != null && value instanceof Date) {
                Date d = (Date)value;
                return new Long(d.getTime());
            }
            return value;
        }
    }

    public static class JsonSimpleSetConverter implements CustomConverter {
        private List<String> conversionWarnings = new LinkedList<String>();

        /**
         * (Auto) converts from source obj type to target if different.
         *
         * For example, sometimes json representation is different from object's expected
         * representation, like a Date could be represented by long (milli) seconds value.
         *
         * @param value object to convert
         * @param attrib
         * @return object pre converted to target type, if possible.
         */
        @Override
        public Object convert(AnAttrib attrib, Object value) {
            if (value == null) {
                return null;
            }
            Class<?> objClass = value.getClass();
            Method m = attrib.getSetter();
            String attribName = attrib.getAttribName();
            Class<?>[] paramTypes = m.getParameterTypes();
            Class<?> p = paramTypes[0];
            if(p.equals(objClass)) {
                return value;
            }
            /**
             * if not equivalent, try to auto-convert
             */
            Constructor<?>[] cs = p.getConstructors();
            for (Constructor<?> c : cs) {
                Class<?>[] cParamTypes = c.getParameterTypes();
                if(cParamTypes.length != 1) {
                    continue;
                }
                try {
               /*
                * Work through single parameter constructors
                */
                    if (cParamTypes[0].equals(objClass)) {
                    /*
                     * There's a constructor with source obj class as an input.
                     */
                        Object newObject = c.newInstance(value);
                        return newObject;
                    } else if (objClass.getSimpleName().equalsIgnoreCase(cParamTypes[0].getSimpleName())) {
                    /*
                     * Try to instantiate through "similar" constructor
                     * Example - source - Long (millisec)
                     * target - Date
                     * // java
                     * Date d = new Date(Long.longValue());
                     * iOS - JavaUtilDate will be used
                     */
                        Object newObject = c.newInstance(value);
                        return newObject;
                    } else if (cParamTypes[0].equals(String.class)) {
                    /*
                     * Through string constructor...
                     * Integer source;
                     * Long target = new Long(source.toString());
                     */
                        Object newObject = c.newInstance(value.toString());
                        return newObject;
                    }
                } catch (Throwable t) {
                    conversionWarnings.add("Error setting: " + attribName +
                            " from: " + objClass.getName() +
                            " constr. param: " + cParamTypes[0].getName() +
                            " simple name: " + cParamTypes[0].getSimpleName());
                }
            } // end for
            conversionWarnings.add("*** Final. Could not set: " + attribName + " from: " + objClass.getName());
            return null;
        }
    }
}
