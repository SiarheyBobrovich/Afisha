package by.it_academy.afisha.spring_config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Map;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories("by.it_academy.afisha.dao")
@PropertySource(value = "classpath:hibernate.properties")
public class DaoConfig {

    @Bean("entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean createLocalContainerEntityManagerFactoryBean(
            @Autowired DriverManagerDataSource dataSource
    ) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();

        factoryBean.setDataSource(dataSource);
        factoryBean.setPackagesToScan("by.it_academy.afisha.dao.entity");
        factoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        Properties properties = new Properties(2);
        properties.putAll(Map.of("hibernate.hbm2ddl.auto", "none",
                "hibernate.dialect", "org.hibernate.dialect.PostgreSQL10Dialect"));
        factoryBean.setJpaProperties(properties);

        return factoryBean;
    }

    @Bean("dataSource")
    public DriverManagerDataSource createDataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/postgres?currentSchema=afisha");
        dataSource.setUsername("postgres");
        dataSource.setPassword("172143");

        return dataSource;
    }

    @Bean("transactionManager")
    public JpaTransactionManager createTransactionManager(@Autowired LocalContainerEntityManagerFactoryBean managerFactory) {
        JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(managerFactory.getObject());

        return jpaTransactionManager;
    }

    @Bean("persistenceExceptionTranslationPostProcessor")
    public PersistenceExceptionTranslationPostProcessor createProcessor() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}
