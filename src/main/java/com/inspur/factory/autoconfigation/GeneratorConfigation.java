package com.inspur.factory.autoconfigation;

import freemarker.template.Configuration;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

@Data
public class GeneratorConfigation {
	
	@Autowired  
	Configuration freeMarkerconfiguration;
	
	@Autowired
	GeneratorPropertiesWarpper generatorProperties;
	
	GeneratorPropertiesWarpper.Project project;
	
	@PostConstruct
	public void init(){
		initFreeMarkerCongation();
		this.project = generatorProperties.getProject();
	}
	
	private void initFreeMarkerCongation(){
		freeMarkerconfiguration.setClassForTemplateLoading(this.getClass(), generatorProperties.getTemplatePath());
	}
}
