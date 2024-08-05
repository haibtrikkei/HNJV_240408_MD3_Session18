package ra.rekkei.demo_hibernate.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "ra.rekkei")
public class WebConfig {

    @Bean
    public ViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource source = new DriverManagerDataSource();
        source.setDriverClassName("com.mysql.cj.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/db_hibernate_jv240408?createDatabaseIfNotExist=true");
        source.setUsername("root");
        source.setPassword("1234$");
        return source;
    }

    @Bean
    public SessionFactory sessionFactory(){
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setPackagesToScan("ra.rekkei.demo_hibernate.model.entity");
        Properties props = new Properties();
        props.put("hibernate.show_sql","true");
        props.put("hibernate.dialect","org.hibernate.dialect.MySQL5Dialect");
        props.put("hibernate.hbm2ddl.auto","update");
        factoryBean.setHibernateProperties(props);
        try {
            factoryBean.afterPropertiesSet();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return factoryBean.getObject();
    }
}
