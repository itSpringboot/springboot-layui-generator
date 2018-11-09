package com.inspur.factory.generator;

import com.inspur.factory.autoconfigation.GeneratorProperties.Project;
import com.inspur.factory.model.Clazz;
import com.inspur.factory.model.ControllerAttribute;
import com.inspur.factory.model.CrudBean;
import com.inspur.factory.model.JavaAttribute;
import lombok.Getter;
import lombok.Setter;

/**
 * 抽象代码生成器
 */
@Getter
@Setter
public abstract class AbstractControllerGenerator extends AbstractJavaGenerator {

	ControllerAttribute controllerAttribute;

	@Override
	protected final void initGeneratorConfig(CrudBean beanModel) {
		super.initGeneratorConfig(beanModel);
		this.controllerAttribute = this.controllerAttribute(beanModel);
	}
	
	private ControllerAttribute controllerAttribute(CrudBean beanModel){
		return ControllerAttribute.builder().controllerRequestMapping(controllerRequestMapping(beanModel))
				.viewPath(viewPath(beanModel)).orderBySql(super.queryOrderSql(beanModel))
				.build();
	}

	protected String controllerRequestMapping(CrudBean beanModel) {
		return beanModel.getModelAttributes().getJavaAttributes().getControllerRequestMapping();
	}
	
	protected String viewPath(CrudBean beanModel) {
		return beanModel.getModelAttributes().getViewAttributes().getViewPath();
	}

	@Override
	protected final Clazz curClazz(JavaAttribute javaAttribute) {
		return javaAttribute.getController();
	}

	@Override
	protected String templateName() {
		return "TemplateController.java";
	}

	@Override
	protected String projectPath(Project project) {
		return project.getViewProjectPath();
	}

}
