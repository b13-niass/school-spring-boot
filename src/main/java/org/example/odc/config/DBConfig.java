//package org.example.odc.config;
//import jakarta.persistence.EntityManagerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
//
//import javax.sql.DataSource;
//@Configuration
//@EnableJpaRepositories(basePackages = "org.example.odc.data.repository")
//public class DBConfig {
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
//            DataSource dataSource, HibernateJpaVendorAdapter jpaVendorAdapter) {
//        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
//        em.setDataSource(dataSource);
//        em.setPackagesToScan("org.example.odc.data.entity");
//        em.setJpaVendorAdapter(jpaVendorAdapter);
//        return em;
//    }
//
//    @Bean
//    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(entityManagerFactory);
//        return transactionManager;
//    }
//
//    @Bean
//    public HibernateJpaVendorAdapter jpaVendorAdapter() {
//        // Configure JPA vendor adapter properties if needed
//        return new HibernateJpaVendorAdapter();
//    }
//
//}