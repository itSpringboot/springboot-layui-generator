package com.jcy.factory.model.binding.impl;

import com.jcy.factory.autoconfigation.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.jcy.factory.model.CrudColumn;
import com.jcy.factory.model.binding.CrudColumnPropertiesBinding;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

/**
* @ClassName: CurdColumnAliasBinding
* @Description: 列别名绑定
*/
@Component
public class CurdColumnAliasBinding implements CrudColumnPropertiesBinding {

	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	public void binding(CrudColumn columnModel,Field field, ColumnAttributes configColumnAttributes) {
		if (configColumnAttributes == null) {
			return;
		}
		String columnDesc = columnModel.getColumnDesc();
		String alias = configColumnAttributes.getAlias();
		if (!StringUtils.isEmpty(alias)) {
			columnModel.setColumnDesc(alias);
		}else{
			if (StringUtils.isEmpty(columnDesc)) {
				columnModel.setColumnDesc(columnModel.getColumnName());
			}
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
