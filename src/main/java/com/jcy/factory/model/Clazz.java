package com.jcy.factory.model;

import com.jcy.factory.util.StringUtil;
import lombok.Data;

@Data
public class Clazz {
	
	private String name;
	
	private String fullName;
	
	private String packageName;
	
	private String parametterName;
	
	public Clazz(String fullName){
		int lastIndexOf = fullName.lastIndexOf(".");
		this.name = fullName.substring(lastIndexOf+1);
		this.packageName = fullName.substring(0,lastIndexOf);
		this.fullName = fullName;
		this.parametterName = StringUtil.firstToLowerCase(this.name);
	}
	
}
