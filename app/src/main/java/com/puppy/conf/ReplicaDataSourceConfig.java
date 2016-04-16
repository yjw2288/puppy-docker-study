package com.puppy.conf;

import com.puppy.datasource.ReplicationRoutingDataSource;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class ReplicaDataSourceConfig {
	@Value("${hikari.driverclassname}")
	private String driverClassName;

	@Value("${hikari.jdbc.read.url}")
	private String jdbcReadUrl;

	@Value("${hikari.jdbc.write.url}")
	private String jdbcWriteUrl;

	@Value("${hikari.username}")
	private String userName;

	@Value("${hikari.password}")
	private String password;

	@Value("${hikari.pool.size}")
	private int poolSize;

	@Bean(destroyMethod = "close")
	public DataSource readDataSource() {
		return getDataSource(poolSize, driverClassName, jdbcReadUrl, userName, password);
	}

	@Bean(destroyMethod = "close")
	public DataSource writeDataSource() {
		return getDataSource(poolSize, driverClassName, jdbcWriteUrl, userName, password);
	}

	private DataSource getDataSource(int poolSize, String driverClassName, String jdbcWriteUrl, String userName, String password) {
		final HikariDataSource ds = new HikariDataSource();
		ds.setMaximumPoolSize(poolSize);
		ds.setDriverClassName(driverClassName);
		ds.setJdbcUrl(jdbcWriteUrl);
		ds.setUsername(userName);
		ds.setPassword(password);
		return ds;
	}

	@Bean
	public DataSource routingDataSource(@Qualifier("writeDataSource") DataSource writeDataSource,
		@Qualifier("readDataSource") DataSource readDataSource) {
		ReplicationRoutingDataSource routingDataSource = new ReplicationRoutingDataSource();

		Map<Object, Object> dataSourceMap = new HashMap<>();
		dataSourceMap.put("write", writeDataSource);
		dataSourceMap.put("read", readDataSource);
		routingDataSource.setTargetDataSources(dataSourceMap);
		routingDataSource.setDefaultTargetDataSource(writeDataSource);

		return routingDataSource;
	}

	@Primary
	@Bean
	public DataSource dataSource(@Qualifier("routingDataSource") DataSource routingDataSource) {
		return new LazyConnectionDataSourceProxy(routingDataSource);
	}

}
