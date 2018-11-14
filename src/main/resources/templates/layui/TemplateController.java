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
	/**
	 * 分页查询列表数据
	 */
	@RequestMapping("/query")
	public Page<${javaAttribute.model.name}> query(@Validated @ModelAttribute RequestPage requestPage, @Validated @ModelAttribute ${javaAttribute.model.name} ${javaAttribute.model.parametterName}) {
		return super.query(requestPage, ${javaAttribute.model.parametterName});
	}
	/**
	 * 根据主键查询数据
	 */
	@RequestMapping("/get")
	public ${javaAttribute.model.name} get(@RequestParam(value="${javaAttribute.primaryKey}") ${javaAttribute.primaryKeyType} ${javaAttribute.primaryKey} ) {
			return super.get(${javaAttribute.primaryKey});
	}
	/**
	 * 根据主键删除数据
	 */
	@RequestMapping("/delete")
	public ResponseResult delete(@RequestParam(value="${javaAttribute.primaryKey}") ${javaAttribute.primaryKey} ) {
			return super.delete(${javaAttribute.primaryKey});
	}

	/**
	 *保存操作
	 */
	@RequestMapping(value = "/save")
	public ResponseResult save(@Validated @ModelAttribute ${javaAttribute.model.name} ${javaAttribute.model.parametterName}){
		ResponseResult responseResult;
		if(${javaAttribute.model.parametterName}.getId()==null||"".equals(${javaAttribute.model.parametterName}.getId())){
			${javaAttribute.model.parametterName}.setId(UUIDutil.UUID());
			responseResult = super.create(${javaAttribute.model.parametterName});
		}else{
			responseResult = super.update(${javaAttribute.model.parametterName});
		}
		return responseResult;
	}
}
