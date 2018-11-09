package com.inspur.factory.model.binding.impl;

import com.inspur.factory.autoconfigation.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.inspur.factory.model.CrudColumn;
import com.inspur.factory.model.binding.CrudColumnPropertiesBinding;
import com.inspur.factory.util.StringUtil;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import java.lang.reflect.Field;

/**
* @ClassName: CrudColumnBaseAttrBinding
* @Description: 列基础属性绑定
*/
@Component
public class CrudColumnBaseAttrBinding implements CrudColumnPropertiesBinding {

	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	public void binding(CrudColumn columnModel,Field field, ColumnAttributes configColumnAttributes) {
			Id annotation = field.getAnnotation(Id.class);
			if (annotation != null) {
				columnModel.setPrimary(true);
			}
			columnModel.setColumnName(field.getName());
			
			Column column = field.getAnnotation(Column.class);
			if (column == null) {
				columnModel.setDbColumnName(StringUtil.camelToUnderline(field.getName()));
			}else{
				columnModel.setDbColumnName(column.name());
			}
			
			ApiModelProperty apiModelProperty = field.getAnnotation(ApiModelProperty.class);
			if (apiModelProperty != null) {
				if(!StringUtils.isEmpty(apiModelProperty.value())){
					columnModel.setColumnDesc(apiModelProperty.value().replaceAll("\\w",""));
				}
				columnModel.setCanNull(!apiModelProperty.required());
			}else{
				columnModel.setColumnDesc(field.getName());
				columnModel.setCanNull(true);
			}
			
			columnModel.setColumnType(field.getType().getName());
		
	}

	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	public int getOrder() {
		return -1000;
	}

}
