package com.luv2code.demo.datasources.config;

import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages="com.luv2code.demo.datasources")
@PropertySource({"classpath:employee-persistence-mysql.properties", "classpath:customer-persistence-mysql.properties"})
public class DemoAppConfig {

	// set up variable to hold the properties
	@Autowired
	private Environment env;
	
	// set up a logger for diagnostics
	private Logger logger = Logger.getLogger(getClass().getName());
	
	
	// define a bean for ViewResolver
	@Bean
	public ViewResolver viewResolver() {
		
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		
		return viewResolver;
	}
	
	// define a bean for our security datasource
	
	@Bean
	public DataSource employeeDataSource() {
		
		DataSource theDataSource = buildDataSource("employee");
		
		return theDataSource;
	}

	@Bean
	public DataSource customerDataSource() {
		
		DataSource theDataSource = buildDataSource("customer");
		
		return theDataSource;
	}
	
	private DataSource buildDataSource(String prefix) {
		// create connection pool
		ComboPooledDataSource theDataSource = new ComboPooledDataSource();

		// set the jdbc driver
		try {
			theDataSource.setDriverClass(env.getProperty(prefix + ".jdbc.driver"));		
		}
		catch (PropertyVetoException exc) {
			throw new RuntimeException(exc);
		}
		
		// for sanity's sake, let's log url and user ... just to make sure we are reading the data
		logger.info(prefix + ".jdbc.url=" + env.getProperty(prefix + ".jdbc.url"));
		logger.info(prefix + ".jdbc.user=" + env.getProperty(prefix + ".jdbc.user"));
		
		// set database connection props
		theDataSource.setJdbcUrl(env.getProperty(prefix + ".jdbc.url"));
		theDataSource.setUser(env.getProperty(prefix + ".jdbc.user"));
		theDataSource.setPassword(env.getProperty(prefix + ".jdbc.password"));
		
		// set connection pool props
		theDataSource.setInitialPoolSize(
		getIntProperty(prefix + ".connection.pool.initialPoolSize"));

		theDataSource.setMinPoolSize(
				getIntProperty(prefix + ".connection.pool.minPoolSize"));
		
		theDataSource.setMaxPoolSize(
				getIntProperty(prefix + ".connection.pool.maxPoolSize"));
		
		theDataSource.setMaxIdleTime(
				getIntProperty(prefix + ".connection.pool.maxIdleTime"));
				
		return theDataSource;
	}
	
	// need a helper method 
	// read environment property and convert to int
	
	private int getIntProperty(String propName) {
		
		String propVal = env.getProperty(propName);
		
		// now convert to int
		int intPropVal = Integer.parseInt(propVal);
		
		return intPropVal;
	}
	
	private Properties getHibernateProperties(String prefix) {

		// set hibernate properties
		Properties props = new Properties();

		props.setProperty(prefix + ".hibernate.dialect", env.getProperty(prefix + ".hibernate.dialect"));
		props.setProperty(prefix + ".hibernate.show_sql", env.getProperty(prefix + ".hibernate.show_sql"));
	
		return props;				
	}

	
	@Bean
	public LocalSessionFactoryBean customerSessionFactory() {
		
		// create session factory
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		// set the properties
		sessionFactory.setDataSource(customerDataSource());
		sessionFactory.setPackagesToScan(env.getProperty("customer.hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties("customer"));
		
		return sessionFactory;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager customerTransactionManager(@Qualifier("customerSessionFactory") SessionFactory sessionFactory) {
		
		// setup transaction manager based on session factory
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}	
	

	@Bean
	public LocalSessionFactoryBean employeeSessionFactory() {
		
		// create session factory
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		// set the properties
		sessionFactory.setDataSource(employeeDataSource());
		sessionFactory.setPackagesToScan(env.getProperty("employee.hibernate.packagesToScan"));
		sessionFactory.setHibernateProperties(getHibernateProperties("employee"));
		
		return sessionFactory;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager employeeTransactionManager(@Qualifier("employeeSessionFactory") SessionFactory sessionFactory) {
		
		// setup transaction manager based on session factory
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);

		return txManager;
	}	
	
}
















