package com.jcy.factory.model.factory;

import com.jcy.factory.autoconfigation.GeneratorProperties.ModelAttributes;
import com.jcy.factory.model.CrudBean;

public interface CrudBeanFactory {

    CrudBean newInstance(ModelAttributes modelAttributes, String javaProjectPath);

}
