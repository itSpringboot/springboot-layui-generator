package com.jcy.factory.model;

import com.jcy.factory.autoconfigation.GeneratorProperties.ModelAttributes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CrudBean {
	
	private String description;
	
	private String simpleName;
	
	private String fullName;

	private String author;
	
	private List<CrudColumn> columns;
	
	private ModelAttributes modelAttributes;
	
}
