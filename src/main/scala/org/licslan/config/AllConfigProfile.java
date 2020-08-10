package org.licslan.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.licslan.entity.Dog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringValueResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * spring 为我们提供的可以根据当前环境 动态激活和切换一系列组件的功能
 *
 * 开发环境  测试环境  生产环境
 * 数据源（A/B/C）
 * db.user=root
 * db.password=123456
 * db.driverClass=com.mysql.jdbc.Driver
 * */
//@Profile("test")
@PropertySource("classpath:/db.properties")
@Configuration
public class AllConfigProfile implements EmbeddedValueResolverAware {
    @Value("${db.user}")
    private String dbUser;
    @Value("${db.password}")
    private String password;
    @Value("${db.driverClass}")
    private String driverClass;
    private StringValueResolver resolver;


    /***
     * 1.@Value   2.@value参数  3.EmbeddedValueResolverAware
     *
     *
     * 1,加了环境标识的bean，只有这个环境被激活的时候才能注册到容器中
     * 2,写在配置类上面，只有是指定的环境的时候，整个配置类里面的所有配置才能开始生效
     * 3,没有环境标识的bean 任何环境下都是加载的
     *
     * ?那怎么激活使用哪个环境呢？
     * -- 1.使用命令行传动态参数 -Dspring.profiles.active=dev
     * -- 2.代码实现 见C:\Users\licslan\Desktop\scala\licslan-scala\src\test\scala\org\licslan\ProfilesDbTest.java
     *
     * */


    @Bean
    public Dog dog(){
        return new Dog();
    }


    @Profile("dev")
    //@Profile("default")
    @Bean("dev")
    public DataSource dataSourceDev(@Value("${db.password}")String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource =  new ComboPooledDataSource();
        dataSource.setUser(dbUser);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl("jdbc:mysql://118.190.58.175:13306/LICSLIN");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Profile("test")
    @Bean("test")
    public DataSource dataSourceTest(@Value("${db.password}")String password) throws PropertyVetoException {
        ComboPooledDataSource dataSource =  new ComboPooledDataSource();
        dataSource.setUser(dbUser);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl("jdbc:mysql://118.190.58.175:13306/LICSLAN");
        String dc = resolver.resolveStringValue("${db.driverClass}");
        //dataSource.setDriverClass(driverClass);
        dataSource.setDriverClass(dc);
        return dataSource;
    }

    @Profile("prod")
    @Bean("prod")
    public DataSource dataSourceProd() throws PropertyVetoException {
        ComboPooledDataSource dataSource =  new ComboPooledDataSource();
        dataSource.setUser(dbUser);
        dataSource.setPassword(password);
        dataSource.setJdbcUrl("jdbc:mysql://118.190.58.175:13306/spring_batch");
        dataSource.setDriverClass(driverClass);
        return dataSource;
    }

    @Override
    public void setEmbeddedValueResolver(StringValueResolver resolver) {
        this.resolver=resolver;
    }
}
