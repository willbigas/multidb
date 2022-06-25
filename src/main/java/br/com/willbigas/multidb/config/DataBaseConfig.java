package br.com.willbigas.multidb.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;


@Configuration
public class DataBaseConfig {

    @Bean(name = "database1")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcDatabase1")
    public JdbcTemplate jdbcTemplate1(@Qualifier("database1") DataSource ds) {
        return new JdbcTemplate(ds);
    }

    @Bean(name = "database2")
    @ConfigurationProperties(prefix = "spring.second-data-source")
    public DataSource dataSource2() {
        return  DataSourceBuilder.create().build();
    }

    @Bean(name = "jdbcDatabase2")
    public JdbcTemplate jdbcTemplate2(@Qualifier("database2") DataSource ds) {
        return new JdbcTemplate(ds);
    }

}
