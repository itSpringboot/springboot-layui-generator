package com.jcy.factory.model.binding;

import com.jcy.factory.autoconfigation.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.jcy.factory.model.CrudColumn;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.List;

/**
* @ClassName: CrudColumnPropertiesBindingHelper
* @Description: 设置crudlColumn属性
*/
@Component
@Slf4j
public class CrudColumnPropertiesBindingHelper {
	
	@Autowired(required=false)
	List<CrudColumnPropertiesBinding> columnAttributeBinding;
	
	@PostConstruct
	public void init() {
		log.debug("属性设置类个数为：{}",columnAttributeBinding==null?0:columnAttributeBinding.size());
	}
	
	public void binding(CrudColumn model, Field field,ColumnAttributes configColumnAttributes){
		if (CollectionUtils.isEmpty(columnAttributeBinding)) {
			throw new RuntimeException("属性设置类个数为空，请检查");
		}
		columnAttributeBinding.forEach(crudColumnPropertiesBinding -> 	crudColumnPropertiesBinding.binding(model, field, configColumnAttributes));
	}
}
