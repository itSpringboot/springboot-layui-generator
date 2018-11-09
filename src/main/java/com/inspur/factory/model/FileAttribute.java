package com.inspur.factory.model;

import lombok.Builder;
import lombok.Data;

import java.io.File;

@Data
@Builder
public class FileAttribute{

	/**
	 * 生成的代码文件
	 */
	protected File codeFile;
	
	/**
	 * 项目路径
	 */
	protected String projectPath;
	
	/**
	 * 模板名称 例如 TemplateController.java
	 */
	protected String templateName;
}	
