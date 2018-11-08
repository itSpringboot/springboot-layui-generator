package ${javaAttribute.controller.packageName};

import com.example.commonweb.model.Page;
import com.example.commonweb.model.RequestPage;
import com.example.commonweb.model.ResponseResult;
import com.example.commonweb.service.AbstractService;
import com.example.commonweb.web.BaseControllerImpl;
import ${javaAttribute.model.fullName};
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * ${javaAttribute.controller.name}
 */
@Controller
@RequestMapping(value = "${controllerAttribute.controllerRequestMapping}")
public class ${javaAttribute.controller.name} extends BaseControllerImpl<${javaAttribute.model.name}, ${javaAttribute.primaryKeyType}>{

	public ${javaAttribute.controller.name}(@Qualifier("${javaAttribute.service.parametterName}") AbstractService service) {
		super(service);
	}
	/**
	 * 分页查询列表数据
	 */
	@Override
	@RequestMapping("/query")
	@ResponseBody
	public Page<${javaAttribute.model.name}> query(@Validated @ModelAttribute RequestPage requestPage, @Validated @ModelAttribute ${javaAttribute.model.name} ${javaAttribute.model.parametterName}) {
		return super.query(requestPage, ${javaAttribute.model.parametterName});
	}
	/**
	 * 根据主键查询数据
	 */
	@RequestMapping("/get")
	@ResponseBody
	public ${javaAttribute.model.name} get(@RequestParam(value="id") ${javaAttribute.primaryKeyType} ${javaAttribute.primaryKey} ) {
			return super.get(${javaAttribute.primaryKey});
	}
	/**
	 * 编辑操作
	 */
	@RequestMapping("/edit/{${javaAttribute.primaryKey}}")
	public String edit(@PathVariable ${javaAttribute.primaryKeyType} ${javaAttribute.primaryKey} ,ModelMap map) {
			map.addAttribute("data", super.get(id));
			return "jcy${controllerAttribute.controllerRequestMapping}edit";
	}
	/**
	 * 根据主键删除数据
	 */
	@RequestMapping("/delete/{${javaAttribute.primaryKey}}")
	@ResponseBody
	public ResponseResult delete(@PathVariable ${javaAttribute.primaryKeyType} ${javaAttribute.primaryKey} ) {
			return super.delete(${javaAttribute.primaryKey});
	}

	/**
	 *保存操作
	 */
	@RequestMapping(value = "/save", produces = {"application/json;charset=UTF-8"})
	public String save(@Validated @ModelAttribute ${javaAttribute.model.name} ${javaAttribute.model.parametterName}){
		if(${javaAttribute.model.parametterName}.getId()==null||"".equals(${javaAttribute.model.parametterName}.getId())){
			${javaAttribute.model.parametterName}.setId(UUID.randomUUID().toString().replace("-",""));
			super.create(${javaAttribute.model.parametterName});
		}else{
			super.update(${javaAttribute.model.parametterName});
		}
		return "redirect:/admin/jcy${controllerAttribute.controllerRequestMapping}";
	}
}
