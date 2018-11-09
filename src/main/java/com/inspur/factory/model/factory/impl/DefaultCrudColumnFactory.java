package com.inspur.factory.model.factory.impl;

import com.inspur.factory.autoconfigation.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.inspur.factory.model.CrudColumn;
import com.inspur.factory.model.factory.AbstractCrudColumnFactory;

import java.lang.reflect.Field;

public class DefaultCrudColumnFactory extends AbstractCrudColumnFactory {

	@Override
	protected Class<? extends CrudColumn> crudColumnClass() {
		return CrudColumn.class;
	}

	@Override
	protected void initColumnCustomProperties(CrudColumn column, Field field, ColumnAttributes columnAttributes) {
	}
	

}
