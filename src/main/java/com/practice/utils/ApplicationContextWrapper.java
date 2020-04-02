/* Copyright lotte.net - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited Proprietary and confidential
 *
 * Written by BO통합 Project-Zero TFT team
 */
package com.practice.utils;


import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *
 * @Project LOTTEON FREE Next-Generation
 * @Company lotteon.com
 * @since 2019. 9. 6.
 * @author kjjeong3@lotte.net
 */
@Component
public final class ApplicationContextWrapper implements ApplicationContextAware {

	private static ApplicationContext context;

	private static void setContext(ApplicationContext ctx) {
		context = ctx;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) {
		setContext(applicationContext);
	}

	public static final <T> T getBean(Class<T> clazz) {
		if (clazz == null) {
			throw new IllegalArgumentException("class cannot be null");
		}

		return context.getBean(clazz);
	}

	public static final Object getBean(String beanName) {
		if (StringUtils.isEmpty(beanName)) {
			throw new IllegalArgumentException("beanName cannot be null");
		}

		return context.getBean(beanName);
	}

	public static final ApplicationContext getApplicationContext() {
		return context;
	}
}
