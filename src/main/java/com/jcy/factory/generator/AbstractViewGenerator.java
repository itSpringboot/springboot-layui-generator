package com.jcy.factory.generator;

import com.jcy.factory.autoconfigation.GeneratorProperties.Project;
import com.jcy.factory.model.ControllerAttribute;
import com.jcy.factory.model.CrudBean;
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
