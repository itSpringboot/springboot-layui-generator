package com.inspur.factory.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ControllerAttribute{
	
	private String controllerRequestMapping;
	
	private String viewPath;

	private String EditViewPath;
	/**
	 * 排序sql
	 */
	protected String orderBySql;
}
