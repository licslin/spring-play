package org.licslan.tx;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * spring 事务理解
 * @author LICSLAN
 *
 * 1.导入相关依赖
 *    --数据源 数据库驱动 spring-jdbc模块
 * 2.配置数据源 JdbcTemplate(简化操作数据库工具) 操作数据库
 * 3.给方法加上@Transactional表示当前是事务方法
 * 4.加上@EnableTransactionManagement开启基于注解事务管理功能
 * 5.配置事务管理器来管理事务
 * */
@EnableTransactionManagement
@ComponentScan({"org.licslan.dao","org.licslan.service"})
@Configuration
public class TxConfig {

    @Bean
    public DataSource dataSource() {
        ComboPooledDataSource dataSource = new ComboPooledDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("123456");
        dataSource.setJdbcUrl("jdbc:mysql://118.190.58.175:13306/spring_batch");
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() throws PropertyVetoException {
        //@Configuration  类会特殊处理 给容器中加组件的方法，多次调用都是从容器中找组件
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        return jdbcTemplate;
    }

    //注册事务管理器在容器中
    @Bean
    public PlatformTransactionManager platformTransactionManager(){
        return new DataSourceTransactionManager(dataSource());
    }
}
