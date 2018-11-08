package com.jcy.factory.generator;

import com.jcy.factory.autoconfigation.GeneratorProperties.Project;
import com.jcy.factory.model.Clazz;
import com.jcy.factory.model.CrudBean;
import com.jcy.factory.model.JavaAttribute;
import com.jcy.factory.model.ServiceAttribute;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractServiceGenerator extends AbstractJavaGenerator {

	ServiceAttribute serviceAttribute;

	@Override
	protected final void initGeneratorConfig(CrudBean crudBean) {
		super.initGeneratorConfig(crudBean);
		this.serviceAttribute = this.serviceAttribute(crudBean);
	}

	private ServiceAttribute serviceAttribute(CrudBean crudBean) {
		return ServiceAttribute.builder()
				.orderBySql(queryOrderSql(crudBean)).build();
	}
	
	@Override
	protected Clazz curClazz(JavaAttribute javaAttribute) {
		return javaAttribute.getService();
	}

	@Override
	protected String projectPath(Project project) {
		return project.getJavaProjectPath();
	}

	@Override
	protected String templateName() {
		return "TemplateService.java";
	}
}
