package com.inspur.factory.custom;

import java.util.Comparator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.inspur.factory.autoconfigation.GeneratorProperties.ModelAttributes;
import com.inspur.factory.model.CrudBean;
import com.inspur.factory.model.CrudColumn;
import com.inspur.factory.model.factory.AbstractCrudBeanFactory;
import com.inspur.factory.model.factory.CrudColumnFactory;

@Component
public class CustomCrudBeanFactory extends AbstractCrudBeanFactory {
	
	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Autowired
	@Override
	public AbstractCrudBeanFactory setCrudColumnFactory(CrudColumnFactory crudColumnFactory) {
		this.crudColumnFactory = crudColumnFactory;
		return this;
	}
	
	@Override
	protected Class<? extends CrudBean> crudBeanClass() {
		return CustomCrudBean.class;
	}

	@Override
	protected void initBeanCustomProperties(CrudBean bean,ModelAttributes modelAttributes) {
		CustomCrudBean newInstance = (CustomCrudBean)bean;
		Map<String, Object> extendAttrMap = modelAttributes.getExtendAttrMap();
		Object author = extendAttrMap.get("author");
		newInstance.setAuthor(String.valueOf(author));
	}

	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	protected Comparator<CrudColumn> columnsComparator() {
		return null;
	}
	
	

}
