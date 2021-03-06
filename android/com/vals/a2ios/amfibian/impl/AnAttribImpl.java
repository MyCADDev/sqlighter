package com.vals.a2ios.amfibian.impl;

import com.vals.a2ios.amfibian.intf.AnAttrib;
import com.vals.a2ios.amfibian.intf.AnObject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by vsayenko on 9/26/15.
 */
public class AnAttribImpl implements AnAttrib {
    private AnObject<?> parentAnObject;
    private String attribName;
    private String columnName;
    private String dbColumnDefinition;
    private String jsonName;

    @Deprecated
    public static final String NONAME_CONVERSION_KEY = "nonameConverter";

    /**
     * TODO
     * private CustomConverter customSetConverter;
     * private CustomConverter customGetConverter;
     */

    @Deprecated
    public String defaultConverterKey = NONAME_CONVERSION_KEY;

    @Deprecated
    public String defaultGetConverterKey = NONAME_CONVERSION_KEY;

    @Deprecated
    private Map<String, CustomConverter> converterMap = new HashMap<String, CustomConverter>();

    @Deprecated
    private Map<String, CustomConverter> getConverterMap = new HashMap<String, CustomConverter>();

    /**
     *
     * @parameter attribName
     * @parameter columnName
     * @parameter jsonName
     */
    public AnAttribImpl(String attribName, String columnName, String jsonName) {
        this(attribName);
        this.columnName = columnName;
        this.jsonName = jsonName;
    }

    public AnAttribImpl(String attribColumnJsonName) {
        if (attribColumnJsonName.indexOf(",") != -1) {
            String[] propColumn = attribColumnJsonName.split(",");
            this.attribName = propColumn[0].trim();
            if (propColumn.length > 1 && propColumn[1] != null) {
                this.columnName = propColumn[1].trim();
            }
            if (propColumn.length > 2 && propColumn[2] != null) {
                this.jsonName = propColumn[2].trim();
            }
        } else {
            this.attribName = attribColumnJsonName;
        }
    }

    @Override
    public String getDbColumnDefinition() {
        return dbColumnDefinition;
    }

    @Override
    public void setDbColumnDefinition(String dbColumnDefinition) {
        this.dbColumnDefinition = dbColumnDefinition;
    }

    @Override
    public void setCustomSetConverter(CustomConverter converter) {
        setCustomSetConverter(NONAME_CONVERSION_KEY, converter);
    }
    @Override
    public void setCustomSetConverter(String key, CustomConverter converter) {
        converterMap.put(key, converter);
    }
    @Override
    public CustomConverter getCustomSetConverter(String key) {
        return converterMap.get(key);
    }
    @Override
    public CustomConverter getCustomSetConverter() {
        return converterMap.get(defaultConverterKey);
    }
    @Override
    public void clearCustomSetConverters() {
        converterMap.clear();
    }
    @Override
    public void setDefaultSetConversionKey(String key) {
    	defaultConverterKey = key;
    }

    @Override
    public void setCustomGetConverter(CustomConverter converter) {
        setCustomGetConverter(NONAME_CONVERSION_KEY, converter);
    }
    @Override
    public void setCustomGetConverter(String key, CustomConverter converter) {
        getConverterMap.put(key, converter);
    }
    @Override
    public CustomConverter getCustomGetConverter(String key) {
        return getConverterMap.get(key);
    }
    @Override
    public CustomConverter getCustomGetConverter() {
        return getConverterMap.get(defaultGetConverterKey);
    }
    @Override
    public void clearCustomGetConverters() {
        getConverterMap.clear();
    }
    @Override
    public void setDefaultGetConversionKey(String key) {
    	defaultGetConverterKey = key;
    }
    @Override
    public void setAnObject(AnObject<?> anObject) {
        this.parentAnObject = anObject;
    }

    @Override
    public String getAttribName() {
        return attribName;
    }

    @Override
    public void setAttribName(String attribName) {
        this.attribName = attribName;
    }

    @Override
    public String getColumnName() {
        return columnName;
    }

    @Override
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    @Override
    public void setValue(Object value) throws Exception {
        Method m = getSetter();
        if(m != null) {
            Object convertedValue = null;
           /**
            * See if custom converter is supplied
            */
            CustomConverter cc = getCustomSetConverter();
            if (cc != null) {
                convertedValue = cc.convert(value);
            } else {
                convertedValue = value;
            }
            // System.out.println("before setting " + attribName + " curr value: " + getValue() + " to " + value);
            m.invoke(parentAnObject.getNativeObject(), convertedValue);
            // System.out.println("after setting " + attribName + " curr value: " + getValue());
        }
    }
    
     @Override
    public Object getValue() throws Exception {
        Object value = null;
        Method m = getGetter();
        if(m != null ) {
            value = m.invoke(parentAnObject.getNativeObject());
            CustomConverter cc = getCustomGetConverter();
            if (cc != null) {
                value = cc.convert(value);
                return value;
            }
        }
        return value;
    }
    
    @Override
    public Method getGetter() {
        Method[] methods = parentAnObject.getNativeClass().getMethods();
        for (Method m: methods) {
            if (m.getName().equalsIgnoreCase("get" + attribName)) {
                return m;
            }
        }
        return null;
    }
    
    @Override
    public Method getSetter() {
        Method[] methods = parentAnObject.getNativeClass().getMethods();
        for (Method m: methods) {
            if (m.getName().equalsIgnoreCase("set" + attribName)) {
                return m;
            }
        }
        return null;
    }
    
    @Override
    public Class<?> getAttribClass() {
        Method m = getGetter();
        if (m != null) {
                Class<?> rt = m.getReturnType();
                return rt;
        }
        return null;
    }
    
    @Override
    public String getJsonOrAttribName() {
    	if(jsonName != null && !"".equals(jsonName.trim())) {
    		return jsonName;
    	}
    	return attribName;
    }
    @Override
    public String getColumnOrAttribName() {
    	if(columnName != null && !"".equals(columnName.trim())) {
    		return columnName;
    	}
    	return attribName;
    }
    
}
