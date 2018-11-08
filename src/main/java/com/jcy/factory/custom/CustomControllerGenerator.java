package com.jcy.factory.custom;

import com.jcy.factory.generator.AbstractControllerGenerator;
import com.jcy.factory.model.CrudBean;
import lombok.Getter;
import lombok.Setter;

/**
 *
 */
@Getter
@Setter
public class CustomControllerGenerator extends AbstractControllerGenerator {

	String author;

	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	protected void writeAttribute(CrudBean crudBean) {
		CustomCrudBean bean = (CustomCrudBean)crudBean;
		this.author = bean.getAuthor();
	}
	
	/**
	* {@inheritDoc}
	* @Description: 
	*/
	@Override
	protected String templateName() {
		return "TemplateController.java";
	}
	

}

