package com.jcy.factory.model.binding.impl;

import com.jcy.factory.autoconfigation.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.jcy.factory.model.CrudColumn;
import com.jcy.factory.model.binding.CrudColumnPropertiesBinding;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

/**
* @ClassName: CrudColumnNullableBinding
* @Description: 列空置绑定
*/
@Component
public class CrudColumnNullableBinding implements CrudColumnPropertiesBinding {

	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	public void binding(CrudColumn columnModel,Field field, ColumnAttributes configColumnAttributes) {
		if (configColumnAttributes == null) {
			return;
		}
		boolean canNull = columnModel.getCanNull();
		if (canNull) {
			boolean nullable = configColumnAttributes.getNullable();
			columnModel.setCanNull(nullable);
		}
		
	}

	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	public int getOrder() {
		return -999;
	}

}
