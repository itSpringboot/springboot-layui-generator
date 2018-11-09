package com.inspur.factory.generator;

import com.inspur.factory.autoconfigation.GeneratorProperties.Project;
import com.inspur.factory.model.ControllerAttribute;
import com.inspur.factory.model.CrudBean;
import lombok.Getter;
import lombok.Setter;

/**
 * @ClassName: AbstractViewGenerator
 * @Description: 视图生成器
 */
@Getter
@Setter
public abstract class AbstractViewGenerator extends AbstractGenerator{
	
	ControllerAttribute controllerAttribute;
	
	@Override
	protected final void initGeneratorConfig(CrudBean crudBean) {
		super.initGeneratorConfig(crudBean);
		this.controllerAttribute = this.controllerAttribute(crudBean);
	}
		
	private ControllerAttribute controllerAttribute(CrudBean beanModel){
		return ControllerAttribute.builder().controllerRequestMapping(controllerRequestMapping(beanModel))
				.viewPath(viewPath(beanModel)).build();
	}

	protected String controllerRequestMapping(CrudBean beanModel) {
		return beanModel.getModelAttributes().getJavaAttributes().getControllerRequestMapping();
	}
	
	protected String viewPath(CrudBean beanModel) {
		return beanModel.getModelAttributes().getViewAttributes().getViewPath();
	}
	
	@Override
	protected String projectPath(Project project) {
		return project.getViewProjectPath();
	}
	
}
