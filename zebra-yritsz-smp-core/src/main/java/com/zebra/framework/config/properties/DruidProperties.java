package com.zebra.framework.config.properties;

import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.pool.DruidDataSource;
import com.zebra.common.config.ConfigServerDruid;

/**
 * druid 配置属性
 * 
 * @author ruoyi
 */
@Configuration
public class DruidProperties {
	public DruidDataSource dataSource(DruidDataSource datasource, ConfigServerDruid configServerDruid,
			Boolean is_save) {
		datasource.setDbType(configServerDruid.getType());
		datasource.setDriverClassName(configServerDruid.getDriverClassName());
		if (!is_save) {
			datasource.setUsername(configServerDruid.getMasterName());
			datasource.setPassword(configServerDruid.getMasterPassword());
			datasource.setUrl(configServerDruid.getMasterUrl());
		} else {
			datasource.setUsername(configServerDruid.getSaveName());
			datasource.setPassword(configServerDruid.getSavePassword());
			datasource.setUrl(configServerDruid.getSaveUrl());
		}

		/** 配置初始化大小、最小、最大 */
		datasource.setInitialSize(configServerDruid.getInitialSize());
		datasource.setMaxActive(configServerDruid.getMaxActive());
		datasource.setMinIdle(configServerDruid.getMinIdle());

		/** 配置获取连接等待超时的时间 */
		datasource.setMaxWait(configServerDruid.getMaxWait());

		/** 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒 */
		datasource.setTimeBetweenEvictionRunsMillis(configServerDruid.getTimeBetweenEvictionRunsMillis());

		/** 配置一个连接在池中最小、最大生存的时间，单位是毫秒 */
		datasource.setMinEvictableIdleTimeMillis(configServerDruid.getMinEvictableIdleTimeMillis());
		datasource.setMaxEvictableIdleTimeMillis(configServerDruid.getMaxEvictableIdleTimeMillis());

		/**
		 * 用来检测连接是否有效的sql，要求是一个查询语句，常用select
		 * 'x'。如果validationQuery为null，testOnBorrow、testOnReturn、
		 * testWhileIdle都不会起作用。
		 */
		datasource.setValidationQuery(configServerDruid.getValidationQuery());
		/**
		 * 建议配置为true，不影响性能，并且保证安全性。申请连接的时候检测，
		 * 如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
		 */
		datasource.setTestWhileIdle(configServerDruid.isTestWhileIdle());
		/** 申请连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。 */
		datasource.setTestOnBorrow(configServerDruid.isTestOnBorrow());
		/** 归还连接时执行validationQuery检测连接是否有效，做了这个配置会降低性能。 */
		datasource.setTestOnReturn(configServerDruid.isTestOnReturn());
		return datasource;
	}
}
