package com.jcy.factory.model.factory;

import com.jcy.factory.autoconfigation.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.jcy.factory.model.CrudColumn;

import java.lang.reflect.Field;

public interface CrudColumnFactory {
	
	CrudColumn newInstance(Field field, ColumnAttributes columnAttributes);
	
}
