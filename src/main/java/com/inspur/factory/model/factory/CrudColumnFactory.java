package com.inspur.factory.model.factory;

import com.inspur.factory.autoconfigation.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.inspur.factory.model.CrudColumn;

import java.lang.reflect.Field;

public interface CrudColumnFactory {
	
	CrudColumn newInstance(Field field, ColumnAttributes columnAttributes);
	
}
