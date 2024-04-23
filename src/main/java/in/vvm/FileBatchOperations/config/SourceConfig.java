// package in.vvm.FileBatchOperations.config;

// import javax.sql.DataSource;

// import org.springframework.boot.jdbc.DataSourceBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.jdbc.datasource.DataSourceTransactionManager;
// import org.springframework.transaction.PlatformTransactionManager;

// @Configuration
// public class SourceConfig {
//     @Bean
//     public DataSource dataSource() {
//         return DataSourceBuilder
//                 .create()
//                 .driverClassName("com.mysql.cj.jdbc.Driver")
//                 .url("jdbc:mysql://localhost:3306/test")
//                 .username("root")
//                 .password("root")
//                 .build();
//     }
// }
