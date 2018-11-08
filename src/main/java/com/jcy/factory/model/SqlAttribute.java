package com.jcy.factory.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
* @ClassName: SqlAttribute
* @Description: TODO
*/
@Data
@Builder
public class SqlAttribute {
	
	String moduleWid;
	
	String moduleName;
	
	String parentModuleName;
	
	String menuitemWid;
	
	String roleName;
	
	List<Map<String,String>> modulepath;
}
