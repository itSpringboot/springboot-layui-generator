package ${javaAttribute.service.packageName};

import com.inspur.common.web.service.AbstractService;
import ${javaAttribute.model.fullName};
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

/**
 * ${javaAttribute.service.name}
 */
@Service
public class ${javaAttribute.service.name} extends AbstractService<${javaAttribute.model.name}, ${javaAttribute.primaryKeyType}>{

	public ${javaAttribute.service.name}(@Qualifier("${javaAttribute.model.parametterName}Mapper") Mapper<${javaAttribute.model.name}> mapper) {
		super(mapper);
	}

}
