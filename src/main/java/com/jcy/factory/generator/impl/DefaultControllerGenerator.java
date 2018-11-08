package com.jcy.factory.generator.impl;

import com.jcy.factory.generator.AbstractControllerGenerator;
import com.jcy.factory.model.CrudBean;
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

