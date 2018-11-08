package com.jcy.factory.generator.binding;

import com.jcy.factory.autoconfigation.GeneratorConfigation;
import com.jcy.factory.autoconfigation.GeneratorProperties.ModelAttributes;
import com.jcy.factory.autoconfigation.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.jcy.factory.generator.AbstractGenerator;
import com.jcy.factory.model.ExtendAttribute;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
* @ClassName: GeneratorModelAttributeBinding
* @Description: 生成器模型属性绑定
*/
@Component
public class GeneratorModelAttributeBinding {
	
	@Autowired
	ModelAttributeBinding modelAttributeBinding;
	/**
	 * 
	* @Title: bingdingGeneratorModelAttribute
	* @Description: 绑定AbstractGenerator的模型属性到freemarker视图
	* @param generatorConfigation
	* @return
	 */
	public Map<String, Object> bingdingGeneratorModelAttribute(AbstractGenerator generator, GeneratorConfigation generatorConfigation){
		Map<String, Object> model = new LinkedHashMap<String, Object>();
		ExtendAttribute extendAttribute = this.prepareExtractModelParams(generator.getModelName(), generatorConfigation);
		generator.addExtractModelAttribute(generator.getModelName(), extendAttribute, model);
		this.bindingObjectFieldToModel(model, generator);
		return model;
	}
	
	private void bindingObjectFieldToModel(Map<String,Object> model, Object object){
		modelAttributeBinding.binding(model, object);
	}
	
	private ExtendAttribute prepareExtractModelParams(String modelName,GeneratorConfigation generatorConfigation){
		ExtendAttribute extendAttribute = new ExtendAttribute();
		List<ModelAttributes> modelAttributes = generatorConfigation.getGeneratorProperties().getModelAttributes();
		for (ModelAttributes modelAttributes1 : modelAttributes) {
			if (modelAttributes1.getModelName().equals(modelName)) {
				Map<String, Object> beanExtendAttrMap = modelAttributes1.getExtendAttrMap();
				if (MapUtils.isEmpty(beanExtendAttrMap)) {
					return extendAttribute;
				}
				extendAttribute.setBeanExtendAttrMap(beanExtendAttrMap);
				
				Map<String, ColumnAttributes> columnAttrMap = modelAttributes1.getColumnAttrMap();
				if (MapUtils.isNotEmpty(columnAttrMap)) {
					this.prepareExtractColumnParams(extendAttribute, columnAttrMap);
				}
			}
		}
		return extendAttribute;
	}
	
	private void prepareExtractColumnParams(ExtendAttribute extendAttribute,Map<String, ColumnAttributes> columnAttrMap){
		Set<Entry<String, ColumnAttributes>> entrySet = columnAttrMap.entrySet();
		for (Entry<String, ColumnAttributes> entry : entrySet) {
			Map<String, Object> extendAttrMap = entry.getValue().getExtendAttrMap();
			if (MapUtils.isNotEmpty(columnAttrMap)) {
				extendAttribute.getColumnExtendAttrMap().put(entry.getKey(), extendAttrMap);
			}
		}
	}
}
