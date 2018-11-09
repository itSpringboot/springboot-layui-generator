package com.inspur.factory.model.factory.impl;

import com.inspur.factory.autoconfigation.GeneratorProperties.ModelAttributes;
import com.inspur.factory.model.CrudBean;
import com.inspur.factory.model.CrudColumn;
import com.inspur.factory.model.factory.AbstractCrudBeanFactory;

import java.util.Comparator;

public class DefaultCrudBeanFactory extends AbstractCrudBeanFactory {
	
	protected Class<? extends CrudBean> crudBeanClass(){
		return CrudBean.class;
	}

	@Override
	protected void initBeanCustomProperties(CrudBean bean, ModelAttributes modelAttributes) {
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
