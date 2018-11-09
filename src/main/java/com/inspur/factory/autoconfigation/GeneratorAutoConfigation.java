package com.inspur.factory.autoconfigation;

import com.inspur.factory.generator.AbstractControllerGenerator;
import com.inspur.factory.generator.AbstractServiceGenerator;
import com.inspur.factory.generator.impl.DefaultControllerGenerator;
import com.inspur.factory.generator.impl.DefaultJsGenerator;
import com.inspur.factory.generator.impl.DefaultServiceGenerator;
import com.inspur.factory.generator.impl.DefaultThymeleafViewGenerator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties({GeneratorProperties.class,GeneratorPropertiesWarpper.class})
@Import({CrudFactoryAutoConfigation.class,GeneratorConfigation.class})
public class GeneratorAutoConfigation {
	
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = "crudgen", name = "controller-enabled",havingValue="true", matchIfMissing = true)
	public AbstractControllerGenerator controllerBuilder(){
		return new DefaultControllerGenerator();
	}
	
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = "crudgen", name = "service-enabled",havingValue="true", matchIfMissing = true)
	public AbstractServiceGenerator serviceGenerator(){
		return new DefaultServiceGenerator();
	}
	
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = "crudgen", name = "view-enabled",havingValue="true", matchIfMissing = true)
	public DefaultThymeleafViewGenerator viewGenerator(){
		return new DefaultThymeleafViewGenerator();
	}

	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnProperty(prefix = "crudgen", name = "edit-view-enabled",havingValue="true", matchIfMissing = true)
	public DefaultJsGenerator jsGenerator(){
		return new DefaultJsGenerator();
	}

}
