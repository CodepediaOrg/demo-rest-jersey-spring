package org.codingpedia.demo.rest.helpers;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean;

/**
 * Stackoverflow solution to copy non-null properties only, see {@link <a href="http://stackoverflow.com/questions/1301697/helper-in-order-to-copy-non-null-properties-from-object-to-another-java">Helper in order to copy non null properties from object to another ? (Java)</a>}
 * 
 */
public class NullAwareBeanUtilsBean extends BeanUtilsBean{
	
    @Override
    public void copyProperty(Object dest, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
        if(value==null)return;
        super.copyProperty(dest, name, value);
    }
    
}
