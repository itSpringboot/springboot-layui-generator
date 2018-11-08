package com.jcy.factory.generator.impl;

import com.jcy.factory.generator.AbstractViewGenerator;
import com.jcy.factory.model.CrudBean;
import com.jcy.factory.model.CrudColumn;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DefaultThymeleafViewGenerator extends AbstractViewGenerator {

	@Override
	protected String templateName() {
		return "template.html";
	}

	private List<CrudColumn> queryColumns = new ArrayList<CrudColumn>();


	@Override
	protected File getCodeFile() {
		String projectPath = this.getFileAttribute().getProjectPath();
		String viewPath = this.getControllerAttribute().getViewPath().replaceAll("\\.", "/");
		String filePath = projectPath + "/src/main/resources/templates/" + viewPath + ".html";
		return new File(filePath);
	}

	/**
	 * {@inheritDoc}
	 * @Description: 写入属性 查询条件列
	 */
	@Override
	protected void writeAttribute(CrudBean crudBean) {
		List<CrudColumn> columns2 = crudBean.getColumns();
		for (CrudColumn crudColumn : columns2) {
			if (crudColumn.getColumnAttributes().getQueryable()) {
				queryColumns.add(crudColumn);
			}
		}
	}

}
