package com.inspur.factory.autoconfigation;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
* @ClassName: GeneratorProperties
* @Description:代码生成器属性
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "crudgen")
public class GeneratorProperties {
	
	/**
	 * 项目属性  前端项目路径和后端项目路径
	 */
	private Project project;
	
	/**
	 * freemarker模板路径 resources目录下
	 */
	private String templatePath = "/templates";
	
	/**
	 * 是否生成controller(java)文件
	 */
	private boolean controllerEnabled = true;
	
	/**
	 * 是否生成js文件
	 */
	private boolean jsEnabled = true;
	/**
	 * 是否生成视图文件
	 */
	private boolean viewEnabled = true;
	/**
	 * 是否生成分页参数(java)文件
	 */
	private boolean pageModelParamEnabled = true;
	/**
	 * 是否生成service(java)文件
	 */
	private boolean serviceEnabled = true;
	/**
	 * 是否生成sql文件
	 */
	private boolean sqlEnabled = true;
	
	/**
	 * 数据表模型属性集合
	 */
	private List<ModelAttributes> modelAttributes = new ArrayList<ModelAttributes>();
	
	
	/**
	* @ClassName: Project
	* @Description: 项目属性  前端项目路径和后端项目路径
	* @author  hyluan
	* @date 2018年1月5日 下午1:25:20
	* @Copyright: Copyright (c) 2017 wisedu
	 */
	@Setter
	@Getter
	public static class Project{
		/**
		 * 后端项目路径
		 */
		private String javaProjectPath;
		/**
		 *  前端项目路径
		 */
		private String viewProjectPath;
	}
	
	/**
	 * 
	* @ClassName: ModelAttributes
	* @Description: 数据表模型属性
	* @author  hyluan
	* @date 2018年1月5日 下午1:26:01
	* @Copyright: Copyright (c) 2017 wisedu
	 */
	@Setter
	@Getter
	public static class ModelAttributes {
		
		/**
		 * 模型名称
		 */
		private String modelName;
		
		/**
		 * java类相关属性
		 */
		private JavaAttributes javaAttributes = new JavaAttributes();
		
		/**
		 * 视图相关属性
		 */
		private ViewAttributes viewAttributes = new ViewAttributes();

		/**
		 * 菜单
		 */
		private MenuAttributes menuAttributes = new MenuAttributes();
		
		/**
		 * 列属性hash表  key：列名（java属性，非数据库字段） value：ColumnAttributes 列属性
		 */
		private Map<String,ColumnAttributes> columnAttrMap = new HashMap<String,ColumnAttributes>();
		
		/**
		 * 模型扩展属性 用户自定义
		 */
		private Map<String,Object> extendAttrMap = new HashMap<String,Object>();
		
		/**
		 * 
		* @ClassName: JavaAttributes
		* @Description: java类属性
		* @author  hyluan
		* @date 2018年1月5日 下午1:30:00
		* @Copyright: Copyright (c) 2017 wisedu
		 */
		@Setter
		@Getter
		public static class JavaAttributes {
			
			/**
			 * 模型包路径 com.lhy.tool
			 */
			private String modelPackage;
			/**
			 * 服务包路径
			 */
			private String servicePackage;
			/**
			 * 控制器路径
			 */
			private String controllerPackage;
			/**
			 * 控制器请求映射路径
			 */
			private String controllerRequestMapping;
			/**
			 * 自定义排序sql语句 例如：  userId asc, userCode desc 默认为空
			 */
			private String queryOrderSql = "";

		}
		
		/**
		 * 
		* @ClassName: ViewAttributes
		* @Description:视图属性
		* @author  hyluan
		* @date 2018年1月5日 下午1:33:03
		* @Copyright: Copyright (c) 2017 wisedu
		 */
		@Setter
		@Getter
		public static class ViewAttributes {
			/**
			 * 视图相对路径
			 */
			private String viewPath;

			private String editViewPath;
		}
		
		/**
		 * 
		* @ClassName: ColumnAttributes
		* @Description: 模型列属性
		* @author  hyluan
		* @date 2018年1月5日 下午1:33:27
		* @Copyright: Copyright (c) 2017 wisedu
		 */
		@Setter
		@Getter
		@ToString
		public static class ColumnAttributes {
			
			/**
			 * 别名 即页面显示字段名称 如果为空去数据库字段描述， 如字段描述为空，取字段名称
			 */
			private String alias;
			
			/**
			 * 是否是查询条件
			 */
			private boolean queryable;
			
			/**
			 * 索引 即排序号
			 */
			private int index = 99999;
			
			/**
			 * 是否可为空
			 */
			private boolean nullable = true;
			
			/**
			 * 是否唯一
			 */
			private boolean uniqueable;
			
			/**
			 * 表格中是否显示该列
			 */
			private boolean gridShowable = true;
			
			/**
			 * 前端数据类型  text combobox databox等
			 */
			private String type;
			
			/**
			 * 模型列扩展属性， 用户自定义
			 */
			private Map<String,Object> extendAttrMap = new HashMap<String,Object>();
			
		}
		
		/**
		 * 
		* @ClassName: MenuAttributes
		* @Description: 菜单属性
		* @author  hyluan
		* @date 2018年1月5日 下午1:35:44
		* @Copyright: Copyright (c) 2017 wisedu
		 */
		@Setter
		@Getter
		public static class MenuAttributes {

			/**
			 * 二级模块名称
			 */
			private String moduleName;

			/**
			 * 需要生成菜单的角色名称
			 */
			private String roleName = "实施管理员";

			/**
			 * 父模块名称
			 */
			private String parentModuleName;

		}
	}
}
