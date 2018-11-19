package ${javaAttribute.controller.packageName};

import com.inspur.common.util.UUIDutil;
import com.inspur.common.web.controller.BaseControllerImpl;
import com.inspur.common.web.model.Page;
import com.inspur.common.web.model.RequestPage;
import com.inspur.common.web.model.ResponseResult;
import com.inspur.common.web.service.AbstractService;
import ${javaAttribute.model.fullName};
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * ${javaAttribute.controller.name}
 */
@RestController
@RequestMapping(value = "${controllerAttribute.controllerRequestMapping}")
public class ${javaAttribute.controller.name} extends BaseControllerImpl<${javaAttribute.model.name}, ${javaAttribute.primaryKeyType}>{

	public ${javaAttribute.controller.name}(@Qualifier("${javaAttribute.service.parametterName}") AbstractService service) {
		super(service);
	}
}
