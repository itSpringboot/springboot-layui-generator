package com.inspur.factory.model.binding;

import com.inspur.factory.autoconfigation.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.inspur.factory.model.CrudColumn;
import org.springframework.core.Ordered;

import java.lang.reflect.Field;

/**
 * @ClassName: CrudColumnPropertiesBinding
 * @Description: 设置crudlColumn属性
 */
public interface CrudColumnPropertiesBinding extends Ordered{

	void binding(CrudColumn columnAttribute, Field field,
                 ColumnAttributes configColumnAttributes);
}
