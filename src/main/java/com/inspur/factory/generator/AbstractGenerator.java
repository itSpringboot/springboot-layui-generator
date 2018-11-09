package com.inspur.factory.generator;

import com.inspur.factory.autoconfigation.GeneratorConfigation;
import com.inspur.factory.autoconfigation.GeneratorProperties.Project;
import com.inspur.factory.generator.annotation.ModelIgnoreAttribute;
import com.inspur.factory.generator.binding.GeneratorModelAttributeBinding;
import com.inspur.factory.model.CrudBean;
import com.inspur.factory.model.CrudColumn;
import com.inspur.factory.model.ExtendAttribute;
import com.inspur.factory.model.FileAttribute;
import com.inspur.factory.model.factory.AbstractCrudBeanFactory;
import com.inspur.factory.model.factory.AbstractCrudColumnFactory;
import freemarker.template.Template;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileSystemException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@Setter
@Getter
@Slf4j
public abstract class AbstractGenerator implements Generator {
	
	protected String modelName;

	@ModelIgnoreAttribute
	FileAttribute fileAttribute;
	
	@ModelIgnoreAttribute
	@Autowired
	GeneratorModelAttributeBinding generatorModelAttributeSetter;
	
	protected List<CrudColumn> columns;
	
	@Override
	public final File genCode(CrudBean crudBean, GeneratorConfigation generatorConfigation) {
		this.fileAttribute = this.fileAttribute(crudBean, generatorConfigation);
		this.initGeneratorConfig(crudBean);
		this.writeAttribute(crudBean);
		File codeFile = this.getCodeFile();
		//只有所有模板文件属性加载init方法完毕，此时才知道文件路径和文件名信息，这里再设置
		this.fileAttribute.setCodeFile(codeFile);
		Map<String, Object> modelAttribute = generatorModelAttributeSetter.bingdingGeneratorModelAttribute(this, generatorConfigation);
		this.processTemplateToFile(generatorConfigation,modelAttribute);
		this.checkFile(codeFile);
		return codeFile;
	}
	
	/**
	 * 
	* {@inheritDoc}
	* @Description:初始化生成器配置
	 */
	protected void initGeneratorConfig(CrudBean crudBean){
		this.modelName  = crudBean.getSimpleName().toLowerCase();
		this.columns = crudBean.getColumns();
	}
	
	/**
	 * 
	* @Title: writeAttribute
	* @Description: 给子类覆盖的属性写入方法,给genarator成员变量设值即可
	* @param crudBean
	 */
	protected abstract void writeAttribute(CrudBean crudBean);
	
	/**
	 * {@inheritDoc}
	* @Title: projectPath
	* @Description: 配置生成代码的项目路径
	* @param project
	* @return
	 */
	protected abstract String projectPath(Project project);
	
	/**
	 * {@inheritDoc}
	* @Title: templateName
	* @Description: 使用的模板名称  
	* @return String    返回类型
	 */
	protected abstract String templateName();
	
	/**
	 * {@inheritDoc}
	* @Title: getCodeFile
	* @Description:生成的代码文件
	* @return File    返回类型
	 */
	protected abstract File getCodeFile();
	
	private FileAttribute fileAttribute(CrudBean beanModel, GeneratorConfigation generatorConfigation){
		return FileAttribute.builder().projectPath(projectPath(generatorConfigation.getProject()))
				.templateName(templateName()).build();
	}
	
	
	/**
	* {@inheritDoc}
	* @Title: addExtractModelAttribute
	* @Description: 额外属性设置 自定义属性设置时可覆盖该方法,<br> 
	* 设置到model中的属性，和{@link AbstractGenerator} 及子类的成员变量都将写入视图
	* 自定义模型时推荐继承{@link AbstractCrudBeanFactory}<br>
	* 自定义模型列时推荐继承{@link AbstractCrudColumnFactory}<br>
	* @param modelName 当前模型名称
	* @param extendAttribute 从配置中获取的额外属性
	* @param model
	 */
	public void addExtractModelAttribute(String modelName, ExtendAttribute extendAttribute, Map<String, Object> model){
	}
	
	/**
	 * 
	* @Title: processTemplateToFile
	* @Description: 调用freemarker生成代码文件
	* @param generatorConfigation
	* @param model
	 */
	@SneakyThrows
	private void processTemplateToFile(GeneratorConfigation generatorConfigation, Map<String, Object> model){
		Set<Entry<String,Object>> entrySet = model.entrySet();
		log.debug("模板参数:");
		//1.8
		entrySet.forEach(entry -> log.debug("key: {},value: {}",entry.getKey(),entry.getValue()));
		Template template = generatorConfigation.getFreeMarkerconfiguration().getTemplate(fileAttribute.getTemplateName());
		String content = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);  
		File codeFile = fileAttribute.getCodeFile();
		FileUtils.write(codeFile, content, "utf-8");
		log.info("生成文件路径：{}",codeFile.getAbsolutePath());
		log.info("文件内容:");
		log.info(content);
	}
	
	/**
	 * 
	* @Title: checkFile
	* @Description: 检查生成的文件是否存在
	* @param file
	 */
	@SneakyThrows
	private void checkFile(File file){
		if (!file.exists()) {
			throw new FileNotFoundException("生成文件失败");
		}
		if (file.length() == 0) {
			throw new FileSystemException(file.getAbsolutePath(),"生成文件长度为0",null);
		}
	}
	
}
