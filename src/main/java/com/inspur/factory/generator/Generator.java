package com.inspur.factory.generator;

import com.inspur.factory.autoconfigation.GeneratorConfigation;
import com.inspur.factory.model.CrudBean;

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
