package com.xjj.anes.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE, ElementType.METHOD }) //使用范围
@Retention(RetentionPolicy.RUNTIME)	//保持性策略：对VM有效
@Documented	//表示此注解可以被文档化
@Inherited	//如果这个标注被用于一个类，则它的子类也继承了这个标注
public @interface SysMenu {
	/**
	 * 整个系统唯一
	 */
	String id() default "";

	/**
	 * 菜单名称
	 */
	String name() default "";

	/**
	 * 所属模块
	 */
	String parent() default "";
	
	/**
	 * 排序号
	 */
	int orderNo() default 0;

	/**
	 * 链接地址，存放前端页面的静态地址
	 */
	String uri() default "";

	String equals() default "";
}