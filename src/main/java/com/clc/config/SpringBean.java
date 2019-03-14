package com.clc.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * xml 
 * 		spring+hibernate
 * 		mvc+hibernate
 * annotations
 * 	 spring+hibernate
 * 	mvc+hibernate
 * 
 * mvc
 * 		annotiontions  -- @enablemvc -- helloworld
 * 								 sepearatly -- hibernate+spring--crud 
 * 
 * 			VIew...
 * 	mvc -- requestmapping..
 * 
 * @author Yogesh
 *
 */

@Configuration  //it represetns this bean configurations -- bean.xml
@EnableWebMvc
@ComponentScan({ "com.clc.*" })//this represents base packageToScan <context:component-scan base-package="com.clc.*">
@PropertySource(value = { "classpath:database.properties" })//propertyplaceholder bean //<context:property-placeholder location="classpath:db.properties,app.properties" />
public class SpringBean {

	@Value("${jdbc.driverClassName}")String driverClassName;
	@Value("${jdbc.url}")String dbUrl;
	@Value("${jdbc.username}")String dbUserName;
	@Value("${jdbc.password}")String password;
	@Value("${hibernate.dialect}")String hbmDialectPropertyValue;
    @Value("${hibernate.show_sql}")String hbmShowSqlPropertyValue;
    @Value("${hibernate.format_sql}")String hbmFormatSqlPropertyValue;
    @Value("${hibernate.ddl}")String hbmDdlAutoPropertyValue;
	
    static{
    	System.out.println("Loading of BeanXMl");
    }
	String hbmDialectProperty = "hibernate.dialect";
    String hbmShowSqlProperty = "hibernate.show_sql";
    String hbmFormatSqlProperty = "hibernate.format_sql";
    String hbmDdlAutoProperty = "hibernate.hbm2ddl.auto";
    
    
    @Bean  //create object with new -- to spring container madhe add kara -- LocalsessionFactory
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan(new String[] { "com.clc.*" });
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
     }
    
    @Bean  //create object with new -- to spring container madhe add kara -- BasicDataSource
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        System.out.println("driverClassName : "+driverClassName);
        dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(dbUrl);
        dataSource.setUsername(dbUserName);
        dataSource.setPassword(password);
        return dataSource;
    }
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put(hbmDialectProperty,  hbmDialectPropertyValue);
        properties.put(hbmShowSqlProperty,hbmShowSqlPropertyValue);
        properties.put(hbmFormatSqlProperty,hbmFormatSqlPropertyValue);
        properties.put(hbmDdlAutoProperty,hbmDdlAutoPropertyValue);
        return properties;        
    }
	
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/"); //emp
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
    
}
