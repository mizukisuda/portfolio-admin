package com.seattleacademy.team20.config;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
 @Configuration
 @PropertySource({"classpath:/jdbc.properties"})
public class DBConfig {
    @Autowired
    private Environment environment;
    @Bean
    public DataSource datasource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getProperty("jdbc.url"));
        dataSource.setUsername(environment.getProperty("jdbc.username"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        return dataSource;
    }
    @Bean
    public DataSourceTransactionManager transactionManager() {
      return new DataSourceTransactionManager(datasource());
    }
    @Bean
    public JdbcTemplate jdbcTemplate() {
      return new JdbcTemplate(datasource());
    }
}