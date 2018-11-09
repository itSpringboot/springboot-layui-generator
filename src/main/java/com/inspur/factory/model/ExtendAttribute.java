package com.inspur.factory.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
* @ClassName: ExtendAttribute
* @Description:额外扩展属性
*/
@Getter
@Setter
public class ExtendAttribute {
	
	/**
	 * 模型bean扩展属性
	 */
	Map<String, Object> beanExtendAttrMap = new HashMap<String, Object>();
	
	/**
	 * 列扩展属性
	 *  key 列名       value 属性键值对
	 */
	Map<String, Map<String, Object>> columnExtendAttrMap = new HashMap<String, Map<String,Object>>();
}
