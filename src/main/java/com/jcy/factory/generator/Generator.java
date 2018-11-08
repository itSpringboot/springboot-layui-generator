package com.jcy.factory.generator;

import com.jcy.factory.autoconfigation.GeneratorConfigation;
import com.jcy.factory.model.CrudBean;

import java.io.File;

public interface Generator {
	
	/**
	 * 
	* @Title: genCode
	* @Description: 生成增删改查代码
	* @param crudBean
	* @param generatorConfigation
	* @return
	 */
	File genCode(CrudBean crudBean, GeneratorConfigation generatorConfigation);
}
