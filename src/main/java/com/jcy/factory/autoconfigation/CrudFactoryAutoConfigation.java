package com.jcy.factory.autoconfigation;

import com.jcy.factory.model.factory.CrudBeanFactory;
import com.jcy.factory.model.factory.CrudColumnFactory;
import com.jcy.factory.model.factory.impl.DefaultCrudBeanFactory;
import com.jcy.factory.model.factory.impl.DefaultCrudColumnFactory;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

public class CrudFactoryAutoConfigation {

	@Autowired
	GeneratorPropertiesWarpper generatorPropertiesWarpper;

	@Bean
	@ConditionalOnMissingBean(CrudBeanFactory.class)
	@SneakyThrows
	public CrudBeanFactory crudBeanFactory(CrudColumnFactory crudColumnFactory) {
		return new DefaultCrudBeanFactory().setCrudColumnFactory(crudColumnFactory);
	}
	
	@Bean
	@SneakyThrows
	@ConditionalOnMissingBean(CrudColumnFactory.class)
	public CrudColumnFactory crudColumnFactory() {
		return new DefaultCrudColumnFactory();
	}
}
