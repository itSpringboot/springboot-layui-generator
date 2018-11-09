package com.inspur.factory.generator.impl;

import com.inspur.factory.generator.AbstractViewGenerator;
import com.inspur.factory.model.CrudBean;
import com.inspur.factory.model.CrudColumn;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 非springbootjs生成器
 */
@Getter
@Setter
public class DefaultWebJsGenerator extends AbstractViewGenerator {

    private List<CrudColumn> queryColumns = new ArrayList<CrudColumn>();

    @Override
    protected String templateName() {
        return "templateedit.html";
    }

    /**
     * {@inheritDoc}
     *
     * @Description:
     */
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

    @Override
    protected File getCodeFile() {
        String projectPath = this.getFileAttribute().getProjectPath();
        String viewPath = this.getControllerAttribute().getViewPath().replaceAll("\\.", "/");
        String filePath = projectPath + "/src/main/resources/templates/" + viewPath + "edit.html";
        return new File(filePath);
    }

}
