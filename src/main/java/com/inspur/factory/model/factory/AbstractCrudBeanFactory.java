package com.inspur.factory.model.factory;

import com.inspur.factory.autoconfigation.GeneratorProperties.ModelAttributes;
import com.inspur.factory.autoconfigation.GeneratorProperties.ModelAttributes.ColumnAttributes;
import com.inspur.factory.model.CrudBean;
import com.inspur.factory.model.CrudColumn;
import com.inspur.factory.util.ClassLoaderUtil;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

@Slf4j
public abstract class AbstractCrudBeanFactory implements CrudBeanFactory {

    @Getter
    @Setter
    protected CrudColumnFactory crudColumnFactory;

    @Override
    public CrudBean newInstance(ModelAttributes modelAttributes, String javaProjectPath) {
        return newInstance(genBeanClass(modelAttributes, javaProjectPath), modelAttributes);
    }

    @SneakyThrows
    protected CrudBean newInstance(Class beanClass, ModelAttributes modelAttributes) {
        CrudBean crudBean = crudBeanClass().newInstance();
        this.initBeanProperties(crudBean, beanClass, modelAttributes);
        this.initBeanCustomProperties(crudBean, modelAttributes);
        return crudBean;
    }

    protected abstract Class<? extends CrudBean> crudBeanClass();

    protected abstract void initBeanCustomProperties(CrudBean bean, ModelAttributes modelAttributes);

    @SuppressWarnings("rawtypes")
    @SneakyThrows
    protected Class genBeanClass(ModelAttributes modelAttributes, String javaProjectPath) {
        String className = modelAttributes.getJavaAttributes().getModelPackage() + "." + modelAttributes.getModelName();
        Class aClass = null;
        try {
            aClass = Class.forName(className);
        } catch (Exception e) {
            String path = javaProjectPath + "/target/classes/";
            log.info("{}，没有获取到模型类，从目标项目：{}加载模型文件class：{}", e, path, className);
            try {
                aClass = ClassLoaderUtil.getClass(path, className);
            } catch (Exception e1) {
                String format = String.format("从目标项目：%s加载模型文件class失败，请检查class文件是否存在：%s", path, className);
                throw new ClassNotFoundException(format,e);
            }
        }
        return aClass;
    }

    private void initBeanProperties(CrudBean crudBean, Class beanClass, ModelAttributes modelAttributes) {
        if (crudColumnFactory == null) {
            throw new NullPointerException("crudColumnFactory为空，建议通过@Bean方式注入一个具体实现工厂，或者通过覆盖setCrudColumnFactory方法注入");
        }
        crudBean.setSimpleName(beanClass.getSimpleName()).setFullName(beanClass.getName())
                .setModelAttributes(modelAttributes);
        ApiModel annotation = (ApiModel) beanClass.getAnnotation(ApiModel.class);
        if (annotation != null) {
            String value = annotation.value();
            crudBean.setDescription(value);
        }

        List<CrudColumn> columns = new ArrayList<CrudColumn>();
        Map<String, ColumnAttributes> configColumns = modelAttributes.getColumnAttrMap();
        Field[] declaredFields = beanClass.getDeclaredFields();
        for (Field field : declaredFields) {
            if (!Modifier.isStatic(field.getModifiers())) { // 判断是否静态属性
                CrudColumn newInstance = crudColumnFactory.newInstance(field, configColumns.get(field.getName()));
                columns.add(newInstance);
            }
        }
        columns = this.sortColumns(columns);
        crudBean.setColumns(columns);
        String author = ObjectUtils.nullSafeToString(modelAttributes.getExtendAttrMap().get("author"));
        crudBean.setAuthor(author);
    }

    /**
     * @param columns
     * @return List
     * @Title: sortColumns
     * @Description: 排序 index升序排列
     */
    private List<CrudColumn> sortColumns(List<CrudColumn> columns) {
        Comparator<CrudColumn> columnsComparator = null;
        if (this.columnsComparator() == null) {
            columnsComparator = defaultColumnsComparator();
        }
        Collections.sort(columns, columnsComparator);
        return columns;
    }

    /**
     * @return Comparator<CrudColumn>
     * @Title: columnsComparator
     * @Description: 列排序比较器 如果为空采用默认列比较器
     */
    protected abstract Comparator<CrudColumn> columnsComparator();

    /**
     * @return Comparator<CrudColumn>
     * @Title: defaultColumnsComparator
     * @Description: 默认列排序比较器
     */
    private Comparator<CrudColumn> defaultColumnsComparator() {
        return Comparator.comparingInt(o -> o.getColumnAttributes().getIndex());
    }

}
