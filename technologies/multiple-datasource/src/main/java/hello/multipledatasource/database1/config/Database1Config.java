package hello.multipledatasource.database1.config;

import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;


@EnableJpaRepositories(
        basePackages = "hello.multipledatasource.database1.repository",
        entityManagerFactoryRef = "firstEntityManager",
        transactionManagerRef = "firstTransactionManager"
)
@Configuration
public class Database1Config {
    @Primary
    @Bean
    public PlatformTransactionManager firstTransactionManager() {
        final JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
        jpaTransactionManager.setEntityManagerFactory(firstEntityManager().getObject());

        return jpaTransactionManager;
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean firstEntityManager() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(firstDataSource());
        em.setPackagesToScan("hello.multipledatasource.database1.entity");
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());

        return em;
    }

    @Primary
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource firstDataSource() {
        return DataSourceBuilder.create().build();
    }
}
