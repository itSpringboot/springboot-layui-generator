package com.jcy.factory.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public final class SpringUtil implements BeanFactoryPostProcessor {

	private static ConfigurableListableBeanFactory beanFactory; // Spring应用上下文环境

	private static final Logger LOG = LoggerFactory.getLogger(SpringUtil.class);

	@Override
	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		if(SpringUtil.beanFactory == null){
			String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
			LOG.debug("加载appBean...");
			for(int i=0;i<beanDefinitionNames.length;i++){  
				if(LOG.isTraceEnabled()){
					LOG.trace(beanDefinitionNames[i]);
				}
			}
			SpringUtil.beanFactory = beanFactory;
		}
	}

	/**
	 * 获取对象
	 *
	 * @param name
	 * @return Object 一个以所给名字注册的bean的实例
	 * @throws BeansException
	 *
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name) throws BeansException {
		return (T) beanFactory.getBean(name);
	}

	/**
	 * 获取类型为requiredType的对象
	 *
	 * @param clz
	 * @return
	 * @throws BeansException
	 *
	 */
	public static <T> T getBean(Class<T> clz) throws BeansException {
		@SuppressWarnings("unchecked")
		T result = (T) beanFactory.getBean(clz);
		return result;
	}

	/**
	 * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
	 *
	 * @param name
	 * @return boolean
	 */
	public static boolean containsBean(String name) {
		return beanFactory.containsBean(name);
	}

	/**
	 * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。
	 * 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
	 *
	 * @param name
	 * @return boolean
	 * @throws NoSuchBeanDefinitionException
	 *
	 */
	public static boolean isSingleton(String name)
			throws NoSuchBeanDefinitionException {
		return beanFactory.isSingleton(name);
	}

	/**
	 * @param name
	 * @return Class 注册对象的类型
	 * @throws NoSuchBeanDefinitionException
	 *
	 */
	public static Class getType(String name)
			throws NoSuchBeanDefinitionException {
		return beanFactory.getType(name);
	}

	/**
	 * 如果给定的bean名字在bean定义中有别名，则返回这些别名
	 *
	 * @param name
	 * @return
	 * @throws NoSuchBeanDefinitionException
	 *
	 */
	public static String[] getAliases(String name)
			throws NoSuchBeanDefinitionException {
		return beanFactory.getAliases(name);
	}
	
	/**
	 * 
	* @Title: getBeanFactory
	* @Description: 返回beanFactory
	* @return ConfigurableListableBeanFactory    返回类型
	* @return
	 */
	public static ConfigurableListableBeanFactory getBeanFactory(){
		return beanFactory;
	}

}
