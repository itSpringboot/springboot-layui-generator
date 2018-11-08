package com.jcy.factory.model.binding;

import com.jcy.factory.autoconfigation.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.jcy.factory.model.CrudColumn;
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
