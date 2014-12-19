package com.xjj.anes.service.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class CoreApplicationContext implements ApplicationContextAware {
	private Log log = LogFactory.getLog(this.getClass().getName());
	private static CoreApplicationContext coreApplicationContext;
	private ApplicationContext applicationContext;

	public CoreApplicationContext() {
		coreApplicationContext = this;
	}

	public static ApplicationContext getApplicationContext() {
		return coreApplicationContext.applicationContext;
	}

	public void setApplicationContext(ApplicationContext appCtx)
			throws BeansException {
		log.info("Initalizing ApplicationContext......");
		applicationContext = appCtx;
	}
}
