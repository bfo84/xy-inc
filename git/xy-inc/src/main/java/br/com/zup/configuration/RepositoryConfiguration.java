package br.com.zup.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import br.com.zup.constant.Constant;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = { "br.com.zup.domain.entity" })
@EnableJpaRepositories(basePackages = { "br.com.zup.repositories" })
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class RepositoryConfiguration {

	@Value("${jdbc.user}")
	private String username;

	@Value("${jdbc.pass}")
	private String password;

	@Value("${jdbc.driverClassName}")
	private String driverClassName;

	@Value("${jdbc.url}")
	private String url;

	public DataSource dataSource() {

		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setUsername(this.username);
		dataSource.setPassword(this.password);
		dataSource.setJdbcUrl(this.url);
		dataSource.setDriverClassName(this.driverClassName);
		dataSource.setPoolName("XY Inc Connection Pool");

		// Configurações do pool
		dataSource.setMinimumIdle(50);
		dataSource.setMaximumPoolSize(500);
		dataSource.setIdleTimeout(1 * 1000);
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();

		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setShowSql(false);
		jpaVendorAdapter.setDatabase(Database.POSTGRESQL);

		em.setDataSource(dataSource());
		em.setPackagesToScan(Constant.PACKAGE_TO_SCAN);
		em.setJpaVendorAdapter(jpaVendorAdapter);
		return em;
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory().getObject());
		transactionManager.setDataSource(dataSource());
		transactionManager.setJpaDialect(new HibernateJpaDialect());
		return transactionManager;
	}
}
