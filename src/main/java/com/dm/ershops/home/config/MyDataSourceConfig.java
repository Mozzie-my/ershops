package com.dm.ershops.home.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class MyDataSourceConfig {
///druid控制
    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() throws SQLException {
        DruidDataSource druidDataSource=new DruidDataSource();
        //加入监控功能
        druidDataSource.setFilters("stat");
        return druidDataSource;
    }

    @Bean
    public ServletRegistrationBean startViewServlet(){
        StatViewServlet startViewServlet=new StatViewServlet();
        ServletRegistrationBean<StatViewServlet> registrationBean=new ServletRegistrationBean<StatViewServlet>(startViewServlet, "/druid/*" );
        return registrationBean;
    }
}
