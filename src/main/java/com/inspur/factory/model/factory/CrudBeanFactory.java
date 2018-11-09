package com.inspur.factory.model.factory;

import com.inspur.factory.autoconfigation.GeneratorProperties.ModelAttributes;
import com.inspur.factory.model.CrudBean;

public interface CrudBeanFactory {

    CrudBean newInstance(ModelAttributes modelAttributes, String javaProjectPath);

}
