package com.inspur.factory.custom;

import java.lang.reflect.Field;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.stereotype.Component;

import com.inspur.factory.autoconfigation.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.inspur.factory.model.CrudColumn;
import com.inspur.factory.model.factory.AbstractCrudColumnFactory;

@Component
public class CustomCrudColumnFactory extends AbstractCrudColumnFactory {

	@Override
	protected Class<? extends CrudColumn> crudColumnClass() {
		return CustomCrudColumn.class;
	}

	@Override
	protected void initColumnCustomProperties(CrudColumn column, Field field, ColumnAttributes columnAttributes) {
		CustomCrudColumn customCrudColumn = (CustomCrudColumn)column;
		if (columnAttributes == null) {
			customCrudColumn.setCustomAtrr("");
			return;
		}
		
		Map<String, Object> extendAttrMap = columnAttributes.getExtendAttrMap();
		customCrudColumn.setCustomAtrr(MapUtils.getString(extendAttrMap, "custom-attr", ""));
	}



}
