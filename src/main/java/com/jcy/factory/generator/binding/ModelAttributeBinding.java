package com.jcy.factory.generator.binding;

import com.jcy.factory.generator.annotation.ModelAttribute;
import com.jcy.factory.generator.annotation.ModelIgnoreAttribute;
import com.jcy.factory.util.ReflectionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * 
* @ClassName: ModelAttributeBinding
* @Description: 模型属性自动设置类
 */
@Component  
@Slf4j
public class ModelAttributeBinding {
	
	/**
	 * 
	* @Title: binding
	* @Description: 扫描一个类中的成员变量，加入到模型map中<br>
	*  标记了 @{@link ModelIgnoreAttribute} 的成员变量将不会写入视图<br>
	*  标记了@{@link ModelAttribute}的 可以设置写入视图的键别名
	* @param modelMap
	* @param object
	 */
	public void binding(Map<String,Object> modelMap, Object object){
		Field[] declaredFields = ReflectionUtils.getAllDeclaredField(object);
		for (Field field : declaredFields) {
			//排除忽略字段
			ModelIgnoreAttribute modelIgnoreAttribute = field.getAnnotation(ModelIgnoreAttribute.class);
			if (modelIgnoreAttribute == null) {
				if(Modifier.isStatic(field.getModifiers())){
					continue;
				}
				ModelAttribute annotation = field.getAnnotation(ModelAttribute.class);
				String key = field.getName();
				if (annotation != null && !StringUtils.isEmpty(annotation.value())) {
					key = annotation.value();
				}
				putModelMap(modelMap, key, getFieldValue(field, object));
			}else {
				log.debug("字段：{}被标记为 @ModelIgnoreAttribute,将不写入视图",object.getClass().getSimpleName()+"."+field.getName());
			}
		}
	}
	
	private void putModelMap(Map<String,Object> modelMap,String key,Object value){
		if (modelMap.containsKey(key)) {
			log.error("key:{}已存在，将被覆盖为：{}",key,value);
		}
		modelMap.put(key, value);
	}
	
	public Object getFieldValue(Field field ,Object generator){
		Object value = ReflectionUtils.getFieldValue(generator, field.getName());
		if (value == null) {
			return "";
		}
		//基本数据类型
		if (value.getClass().isPrimitive()) {
			return value;
		}
		//TODO:其他数据类型，以及map list bean等复杂类型的处理
		return value;
	}
}
