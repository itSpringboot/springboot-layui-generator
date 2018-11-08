package com.jcy.factory.model.factory.impl;

import com.jcy.factory.autoconfigation.GeneratorProperties.ModelAttributes;
import com.jcy.factory.model.CrudBean;
import com.jcy.factory.model.CrudColumn;
import com.jcy.factory.model.factory.AbstractCrudBeanFactory;

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
