package com.inspur.factory.model.factory;

import com.inspur.factory.autoconfigation.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.inspur.factory.model.CrudColumn;
import com.inspur.factory.model.binding.CrudColumnPropertiesBindingHelper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;

public abstract class AbstractCrudColumnFactory implements CrudColumnFactory {
	
	@Autowired
	CrudColumnPropertiesBindingHelper columnPropertiesBindingHelper;

	@Override
	@SneakyThrows
	public CrudColumn newInstance(Field field,ColumnAttributes columnAttributes){
		CrudColumn column = crudColumnClass().newInstance();
		this.initColumnProperties(column, field, columnAttributes);
		this.initColumnCustomProperties(column, field, columnAttributes);
		return column;
	}
	
	protected abstract Class<? extends CrudColumn> crudColumnClass();
	
	protected abstract void initColumnCustomProperties(CrudColumn column, Field field,ColumnAttributes columnAttributes);

	private void initColumnProperties(CrudColumn column, Field field,ColumnAttributes columnAttributes){
		if (columnAttributes != null) {
			column.setColumnAttributes(columnAttributes);
		}
		columnPropertiesBindingHelper.binding(column, field, columnAttributes);
	}

}
