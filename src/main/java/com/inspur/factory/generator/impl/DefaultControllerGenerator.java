package com.inspur.factory.generator.impl;

import com.inspur.factory.generator.AbstractControllerGenerator;
import com.inspur.factory.model.CrudBean;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultControllerGenerator extends AbstractControllerGenerator {

	String author;
	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	protected void writeAttribute(CrudBean crudBean) {
		this.author = crudBean.getAuthor();
	}

}

