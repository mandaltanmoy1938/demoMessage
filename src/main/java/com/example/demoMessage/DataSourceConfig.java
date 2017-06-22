package com.example.demoMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Tanmoy Mandal on 12/29/2016.
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
    /*
	 * set all the parameter into some variable to further use in this class
	 * only
	 *
	 * the data came from application.properties file using @Value annotation we
	 * just set the value which is already defined in the properties file
	 */

    @Value("${spring.datasource.driver-class-name}")
    private String DATABASE_DRIVER_CLASS_NAME;

    @Value("${spring.datasource.url}")
    private String DATABASE_URL;

    @Value("${spring.datasource.username}")
    private String DATABASE_USER_NAME;

    @Value("${spring.datasource.password}")
    private String DATABASE_PASSWORD;

    @Value("${spring.jpa.properties.hibernate.dialect}")
    private String HIBERNATE_DIALECT;

    @Value("${spring.jpa.show-sql}")
    private String HIBRENATE_SHOW_SQL;

    @Value("${hibernate.hbm2ddl.auto}")
    private String HIBERNATE_HBM2DDL_AUTO;

    @Value("${entitymanager.packagesToScan}")
    private String ENTITY_MANAGER_PACKEGES_TO_SCAN;

    @Bean
    public DataSource dataSource(){
        // configure datasource for accessing database through hibernate
        DriverManagerDataSource driverManagerDataSource= new DriverManagerDataSource();
        driverManagerDataSource.setDriverClassName(DATABASE_DRIVER_CLASS_NAME);
        driverManagerDataSource.setUrl(DATABASE_URL);
        driverManagerDataSource.setUsername(DATABASE_USER_NAME);
        driverManagerDataSource.setPassword(DATABASE_PASSWORD);
        return driverManagerDataSource;
    }

    @Bean
    public LocalSessionFactoryBean localSessionFactoryBean(){
        LocalSessionFactoryBean localSessionFactoryBean=new LocalSessionFactoryBean();
        localSessionFactoryBean.setDataSource(dataSource());
        localSessionFactoryBean.setPackagesToScan(ENTITY_MANAGER_PACKEGES_TO_SCAN);

        // setting Util.Properties to add extra property to hibernate5
        Properties hibernateProperties=new Properties();
        hibernateProperties.put("spring.jpa.properties.hibernate.dialect", HIBERNATE_DIALECT);
        hibernateProperties.put("spring.jpa.show-sql", HIBRENATE_SHOW_SQL);
        hibernateProperties.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);

        localSessionFactoryBean.setHibernateProperties(hibernateProperties);
        return localSessionFactoryBean;
    }

    @Bean
    public HibernateTransactionManager hibernateTransactionManager(){
        // set Hibernate Transaction manager
        HibernateTransactionManager hibernateTransactionManager=new HibernateTransactionManager();
        hibernateTransactionManager.setSessionFactory(localSessionFactoryBean().getObject());
        return hibernateTransactionManager;
    }
}
